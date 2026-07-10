/*
 * Copyright 2025-2026 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.assertj.eclipse.collections.api;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.error.ElementsShouldMatch.elementsShouldMatch;
import static org.assertj.core.error.ElementsShouldSatisfy.elementsShouldSatisfy;
import static org.assertj.core.error.ShouldBeAnArray.shouldBeAnArray;
import static org.assertj.core.error.ShouldBeEmpty.shouldBeEmpty;
import static org.assertj.core.error.ShouldBeNullOrEmpty.shouldBeNullOrEmpty;
import static org.assertj.core.error.ShouldContain.shouldContain;
import static org.assertj.core.error.ShouldHaveSameSizeAs.shouldHaveSameSizeAs;
import static org.assertj.core.error.ShouldHaveSize.shouldHaveSize;
import static org.assertj.core.error.ShouldHaveSizeBetween.shouldHaveSizeBetween;
import static org.assertj.core.error.ShouldHaveSizeGreaterThan.shouldHaveSizeGreaterThan;
import static org.assertj.core.error.ShouldHaveSizeGreaterThanOrEqualTo.shouldHaveSizeGreaterThanOrEqualTo;
import static org.assertj.core.error.ShouldHaveSizeLessThan.shouldHaveSizeLessThan;
import static org.assertj.core.error.ShouldHaveSizeLessThanOrEqualTo.shouldHaveSizeLessThanOrEqualTo;
import static org.assertj.core.error.ShouldNotBeEmpty.shouldNotBeEmpty;
import static org.assertj.core.util.Preconditions.checkArgument;
import static org.assertj.eclipse.collections.util.RichIterableUtil.sizeOf;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.assertj.core.annotation.CheckReturnValue;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.AbstractIterableAssert;
import org.assertj.core.api.ThrowingConsumer;
import org.assertj.core.error.UnsatisfiedRequirement;
import org.assertj.core.presentation.PredicateDescription;
import org.eclipse.collections.api.PrimitiveIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.fixed.ArrayAdapter;

/**
 * Base class for implementations of Eclipse Collections {@link RichIterable} assertions.
 *
 * @param <SELF>           the "self" type of this assertion class. Please read &quot;<a href="https://bit.ly/1IZIRcY"
 *                         target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *                         for more details.
 * @param <ACTUAL>         the type of the "actual" value.
 * @param <ELEMENT>        the type of elements of the "actual" value.
 * @param <ELEMENT_ASSERT> used for navigational assertions to return the right assert type.
 */
//@format:off
public abstract class AbstractRichIterableAssert<SELF extends AbstractRichIterableAssert<SELF, ACTUAL, ELEMENT, ELEMENT_ASSERT>,
  ACTUAL extends RichIterable<? extends ELEMENT>,
  ELEMENT,
  ELEMENT_ASSERT extends AbstractAssert<? extends ELEMENT_ASSERT, ELEMENT>>
  extends AbstractIterableAssert<SELF, ACTUAL, ELEMENT, ELEMENT_ASSERT> {
//@format:on

  protected AbstractRichIterableAssert(ACTUAL actual, Class<?> selfType) {
    super(actual, selfType);
  }

  @Override
  public SELF allMatch(Predicate<? super ELEMENT> predicate) {
    return executeAssertion(() -> assertAllMatch(predicate, PredicateDescription.GIVEN));
  }

  @Override
  public SELF allMatch(Predicate<? super ELEMENT> predicate, String predicateDescription) {
    return executeAssertion(() -> assertAllMatch(predicate, new PredicateDescription(predicateDescription)));
  }

  private void assertAllMatch(Predicate<? super ELEMENT> predicate, PredicateDescription predicateDescription) {
    isNotNull();
    requireNonNull(predicate, "The predicate to evaluate should not be null");
    isNotEmpty();

    ImmutableList<? extends ELEMENT> nonMatches = actual.reject(predicate::test).toImmutableList();
    if (nonMatches.isEmpty()) {
      return;
    }

    throw assertionError(elementsShouldMatch(actual, nonMatches.size() == 1 ? nonMatches.getFirst() : nonMatches, predicateDescription));
  }

  @Override
  public SELF allSatisfy(Consumer<? super ELEMENT> requirements) {
    return executeAssertion(() -> assertAllSatisfy(requirements));
  }

  @Override
  public SELF allSatisfy(ThrowingConsumer<? super ELEMENT> requirements) {
    return allSatisfy(((Consumer<? super ELEMENT>) requirements));
  }

  private void assertAllSatisfy(Consumer<? super ELEMENT> requirements) {
    isNotNull();
    isNotEmpty();
    requireNonNull(requirements, "The Consumer<T> expressing the assertions requirements must not be null");

    RichIterable<UnsatisfiedRequirement> unsatisfiedRequirements = actual.collect(element -> failsRequirements(requirements, element))
      .collectIf(Optional::isPresent, Optional::get);
    if (unsatisfiedRequirements.isEmpty()) {
      return;
    }

    throw assertionError(elementsShouldSatisfy(actual, unsatisfiedRequirements.toList(), info));
  }

  private static <E> Optional<UnsatisfiedRequirement> failsRequirements(Consumer<? super E> requirements, E element) {
    try {
      requirements.accept(element);
    } catch (AssertionError ex) {
      return Optional.of(new UnsatisfiedRequirement(element, ex));
    }
    return Optional.empty();
  }

  @Override
  protected void assertContains(ELEMENT[] values) {
    isNotNull();
    requireNonNull(values, "The array of values to look for should not be null");

    if (actual.isEmpty() && values.length == 0) {
      return;
    }

    ArrayAdapter<ELEMENT> valuesList = ArrayAdapter.adapt(values);
    MutableList<ELEMENT> notFound = valuesList.reject(actual::contains);
    if (notFound.isEmpty()) {
      return;
    }

    throw assertionError(shouldContain(actual, valuesList, notFound)); // TODO: ComparisonStrategy???
  }

  @Override
  @CheckReturnValue
  public <T> SELF filteredOn(Function<? super ELEMENT, T> function, T expectedValue) {
    checkArgument(function != null, "The filter function should not be null");
    return internalFilteredOn(element -> Objects.equals(function.apply(element), expectedValue));
  }

  /**
   * Filters the iterable under test keeping only elements matching the given {@link Predicate}.
   * <p>
   * Example: check crew members whose pips > 2:
   *
   * <pre>{@code CrewMember picard   = new CrewMember(1L, new Name("Picard"), 4);
   * CrewMember riker = new CrewMember(2L, new Name("Riker"), 3);
   * CrewMember crusher   = new CrewMember(3L, new Name("Wesley", "Crusher"), 1);
   *
   * ImmutableList<CrewMember> crew = Lists.immutable.of(picard, crusher, riker);
   *
   * assertThat(crew).filteredOn(crewMember -> crewMember.getPips() > 2)
   *                      .containsOnly(picard, riker);}</pre>
   *
   * @param predicate the filter predicate
   * @return a new assertion object with the filtered iterable under test
   * @throws IllegalArgumentException if the given predicate is {@code null}.
   */
  @Override
  @CheckReturnValue
  public SELF filteredOn(Predicate<? super ELEMENT> predicate) {
    checkArgument(predicate != null, "The filter predicate should not be null");
    return internalFilteredOn(predicate::test);
  }

  /**
   * Verifies that the size of the actual RichIterable is equal to the size of the given iterable.
   *
   * @param other the iterable to compare the size of the actual RichIterable with.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given iterable is {@code null}.
   * @throws AssertionError if the size of the actual RichIterable is not equal to the size of the given iterable.
   */
  @Override
  public SELF hasSameSizeAs(Iterable<?> other) {
    return executeAssertion(() -> {
      isNotNull();

      int otherSize = sizeOf(other);
      int actualSize = actual.size();
      if (actualSize == otherSize) {
        return;
      }
      throw assertionError(shouldHaveSameSizeAs(actual, other, actualSize, otherSize));
    });
  }

  /**
   * Verifies that the size of the actual RichIterable matches the size of the given array.
   *
   * @param other the array to compare the size of the actual RichIterable with.
   * @return {@code this} assertion object.
   * @throws AssertionError if the provided array is {@code null}.
   * @throws AssertionError if the size of the actual RichIterable does not match the size of the given array or if the given array is {@code null}.
   */
  @Override
  public SELF hasSameSizeAs(Object other) {
    return executeAssertion(() -> {
      isNotNull();

      if (!(other != null && other.getClass().isArray())) {
        throw assertionError(shouldBeAnArray(other));
      }

      int otherSize = Array.getLength(other);
      int actualSize = actual.size();
      if (actualSize == otherSize) {
        return;
      }

      throw assertionError(shouldHaveSameSizeAs(actual, other, actualSize, otherSize));
    });
  }

  /**
   * Verifies that the actual RichIterable matches the size of the given primitive iterable.
   *
   * @param other the primitive iterable to compare size with
   * @return {@code this} assertion object
   * @throws AssertionError if the actual RichIterable is {@code null}
   * @throws AssertionError if the actual RichIterable does not have the same size as the given primitive iterable
   */
  public SELF hasSameSizeAs(PrimitiveIterable other) {
    return executeAssertion(() -> {
      isNotNull();

      int actualSize = actual.size();
      int otherSize = sizeOf(other);
      if (actualSize == otherSize) {
        return;
      }

      throw assertionError(shouldHaveSameSizeAs(actual, other, actualSize, otherSize));
    });
  }

  /**
   * Verifies that the number of values in the actual RichIterable is equal to the given one.
   * <p>
   * Example:
   * <pre>{@code
   * // assertions will pass
   * assertThat(Sets.immutable.of("TNG", "DS9")).hasSize(2);
   * assertThat(Bags.immutable.of("TNG", "DS9", "VOY")).hasSize(3);
   *
   * // assertions will fail
   * assertThat(Sets.immutable.empty()).hasSize(1);
   * assertThat(Bags.immutable.of("TNG", "DS9", "VOY")).hasSize(2);
   * }</pre>
   *
   * @param expected the expected number of values in the actual collection.
   * @return {@code this} assertion object.
   * @throws AssertionError if the number of values of the actual collection is not equal to the given one.
   */
  @Override
  public SELF hasSize(int expected) {
    return executeAssertion(() -> {
      isNotNull();

      int actualSize = actual.size();
      if (actualSize == expected) {
        return;
      }

      throw assertionError(shouldHaveSize(actual, actualSize, expected));
    });
  }

  /**
   * Verifies that the number of values in the actual RichIterable is between the given boundaries (inclusive).
   * <p>
   * Example:
   * <pre><code class='java'> // assertions will pass
   * assertThat(Lists.immutable.of("TOS", "TNG", "DS9")).hasSizeBetween(2, 3)
   *                                               .hasSizeBetween(3, 4)
   *                                               .hasSizeBetween(3, 3);
   *
   * // assertion will fail
   * assertThat(Lists.immutable.of("TOS", "TNG", "DS9")).hasSizeBetween(4, 6);</code></pre>
   *
   * @param lowerBoundary  the lower boundary compared to which actual size should be greater than or equal to.
   * @param higherBoundary the higher boundary compared to which actual size should be less than or equal to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the number of values of the actual RichIterable is not between the boundaries.
   */
  @Override
  public SELF hasSizeBetween(int lowerBoundary, int higherBoundary) {
    return executeAssertion(() -> {
      isNotNull();

      if (!(higherBoundary >= lowerBoundary)) {
        throw new IllegalArgumentException("The higher boundary <%s> must be greater than the lower boundary <%s>.".formatted(
          higherBoundary,
          lowerBoundary));
      }

      int actualSize = actual.size();
      if (actualSize >= lowerBoundary && actualSize <= higherBoundary) {
        return;
      }

      throw assertionError(shouldHaveSizeBetween(actual, actualSize, lowerBoundary, higherBoundary));
    });
  }

  /**
   * Verifies that the number of values in the actual RichIterable is greater than the given boundary.
   * <p>
   * Example:
   * <pre>{@code // assertion will pass
   * assertThat(Lists.immutable.of("TOS", "TNG", "DS9")).hasSizeGreaterThan(2);
   *
   * // assertion will fail
   * assertThat(Lists.immutable.of("TOS", "TNG", "DS9")).hasSizeGreaterThan(3);
   * }</pre>
   *
   * @param boundary the given value to compare the actual size to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the number of values of the actual iterable is not greater than the boundary.
   */
  @Override
  public SELF hasSizeGreaterThan(int boundary) {
    return executeAssertion(() -> {
      isNotNull();

      int actualSize = actual.size();
      if (actualSize > boundary) {
        return;
      }

      throw assertionError(shouldHaveSizeGreaterThan(actual, actualSize, boundary));
    });
  }

  /**
   * Verifies that the number of values in the actual group is greater than or equal to the given boundary.
   * <p>
   * Example:
   * <pre>{@code // assertions will pass
   * assertThat(Lists.immutable.of("TOS", "TNG", "DS9")).hasSizeGreaterThanOrEqualTo(3);
   * assertThat(Lists.immutable.of("TNG", "DS9")).hasSizeGreaterThanOrEqualTo(1);
   *
   * // assertions will fail
   * assertThat(Lists.immutable.of("TNG", "DS9")).hasSizeGreaterThanOrEqualTo(3);
   * assertThat(Lists.immutable.of("TOS", "TNG", "DS9")).hasSizeGreaterThanOrEqualTo(4);
   * }</pre>
   *
   * @param boundary the given value to compare the actual size to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the number of values of the actual group is not greater than or equal to the boundary.
   */
  @Override
  public SELF hasSizeGreaterThanOrEqualTo(int boundary) {
    return executeAssertion(() -> {
      isNotNull();

      int actualSize = actual.size();
      if (actualSize >= boundary) {
        return;
      }

      throw assertionError(shouldHaveSizeGreaterThanOrEqualTo(actual, actualSize, boundary));
    });
  }

  /**
   * Verifies that the number of values in the actual RichIterable is less than the given boundary.
   * <p>
   * Example:
   * <pre>{@code
   * // assertion will pass
   * assertThat(Lists.immutable.of("TOS", "TNG", "DS9")).hasSizeLessThan(4);
   *
   * // assertion will fail
   * assertThat(Lists.immutable.of("TOS", "TNG", "DS9")).hasSizeLessThan(3);
   * }</pre>
   *
   * @param boundary the given value to compare the actual size to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the number of values of the actual RichIterable is not less than the boundary.
   */
  @Override
  public SELF hasSizeLessThan(int boundary) {
    return executeAssertion(() -> {
      isNotNull();

      int actualSize = actual.size();
      if (actualSize < boundary) {
        return;
      }

      throw assertionError(shouldHaveSizeLessThan(actual, actualSize, boundary));
    });
  }

  /**
   * Verifies that the number of values in the actual RichIterable is less than or equal to the given boundary.
   * <p>
   * Example:
   * <pre>{@code
   * // assertions will pass
   * assertThat(Lists.immutable.of("TOS", "TNG", "DS9")).hasSizeLessThanOrEqualTo(5)
   *                                   .hasSizeLessThanOrEqualTo(3);
   *
   * // assertion will fail
   * assertThat(Lists.immutable.of("TOS", "TNG", "DS9")).hasSizeLessThanOrEqualTo(2);
   * }</pre>
   *
   * @param boundary the given value to compare the actual size to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the number of values of the actual RichIterable is not less than or equal to the boundary.
   */
  @Override
  public SELF hasSizeLessThanOrEqualTo(int boundary) {
    return executeAssertion(() -> {
      isNotNull();

      int actualSize = actual.size();
      if (actualSize <= boundary) {
        return;
      }

      throw assertionError(shouldHaveSizeLessThanOrEqualTo(actual, actualSize, boundary));
    });
  }

  /**
   * Verifies that the actual RichIterable is empty.
   *
   * @throws AssertionError if the actual RichIterable is not empty.
   */
  @Override
  public void isEmpty() {
    executeAssertion(() -> {
      isNotNull();

      if (actual.isEmpty()) {
        return;
      }

      throw assertionError(shouldBeEmpty(actual));
    });
  }

  /**
   * Verifies that the actual RichIterable is not empty.
   *
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual RichIterable is empty.
   */
  @Override
  public SELF isNotEmpty() {
    return executeAssertion(() -> {
      isNotNull();

      if (actual.notEmpty()) {
        return;
      }

      throw assertionError(shouldNotBeEmpty());
    });
  }

  /**
   * Verifies that the actual RichIterable is null or empty.
   *
   * @throws AssertionError if the actual RichIterable is not null or not empty.
   */
  @Override
  public void isNullOrEmpty() {
    executeAssertion(() -> {
      if (actual == null || actual.isEmpty()) {
        return;
      }

      throw assertionError(shouldBeNullOrEmpty(actual));
    });
  }

  /**
   * Helper method that filters via Eclipse Collections to avoid creating any JCL collections.
   *
   * @param predicate The predicate to filter on.
   * @return A new {@link AbstractRichIterableAssert} with the filtered values.
   */
  private SELF internalFilteredOn(org.eclipse.collections.api.block.predicate.Predicate<? super ELEMENT> predicate) {
    checkArgument(predicate != null, "The filter predicate should not be null");
    // TODO: In AbstractIterableAssert the withAssertionState method is package-private. Need to find out how to handle
    //  this method or if we need to use it
    return newAbstractIterableAssert(actual.select(predicate));//.withAssertionState(myself);
  }
}

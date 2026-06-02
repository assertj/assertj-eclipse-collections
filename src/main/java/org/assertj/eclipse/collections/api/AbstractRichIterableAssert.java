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

import static org.assertj.core.error.ShouldBeEmpty.shouldBeEmpty;
import static org.assertj.core.error.ShouldBeNullOrEmpty.shouldBeNullOrEmpty;
import static org.assertj.core.error.ShouldHaveSize.shouldHaveSize;
import static org.assertj.core.error.ShouldHaveSizeGreaterThan.shouldHaveSizeGreaterThan;
import static org.assertj.core.error.ShouldHaveSizeGreaterThanOrEqualTo.shouldHaveSizeGreaterThanOrEqualTo;
import static org.assertj.core.error.ShouldHaveSizeLessThan.shouldHaveSizeLessThan;
import static org.assertj.core.error.ShouldHaveSizeLessThanOrEqualTo.shouldHaveSizeLessThanOrEqualTo;
import static org.assertj.core.error.ShouldNotBeEmpty.shouldNotBeEmpty;
import static org.assertj.core.util.Preconditions.checkArgument;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.AbstractIterableAssert;
import org.eclipse.collections.api.RichIterable;

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
  public <T> SELF filteredOn(Function<? super ELEMENT, T> function, T expectedValue) {
    checkArgument(function != null, "The filter function should not be null");
    return internalFilteredOn(element -> Objects.equals(function.apply(element), expectedValue));
  }

  /**
   * Filters the iterable under test keeping only elements matching the given {@link Predicate}.
   * <p>
   * Example: check old employees whose age &gt; 100:
   *
   * <pre><code class='java'> Employee yoda   = new Employee(1L, new Name("Yoda"), 800);
   * Employee obiwan = new Employee(2L, new Name("Obiwan"), 800);
   * Employee luke   = new Employee(3L, new Name("Luke", "Skywalker"), 26);
   *
   * List&lt;Employee&gt; employees = List.of(yoda, luke, obiwan);
   *
   * assertThat(employees).filteredOn(employee -&gt; employee.getAge() &gt; 100)
   *                      .containsOnly(yoda, obiwan);</code></pre>
   *
   * @param predicate the filter predicate
   * @return a new assertion object with the filtered iterable under test
   * @throws IllegalArgumentException if the given predicate is {@code null}.
   */
  @Override
  public SELF filteredOn(Predicate<? super ELEMENT> predicate) {
    checkArgument(predicate != null, "The filter predicate should not be null");
    return internalFilteredOn(predicate::test);
  }

  /**
   * Verifies that the number of values in the actual RichIterable is equal to the given one.
   * <p>
   * Example:
   * <pre>{@code
   * // assertions will pass
   * assertThat(Sets.immutable.of("TNG", "DS9")).hasSize(2);
   * assertThat(Bags.immutable.of(1, 2, 3)).hasSize(3);
   *
   * // assertions will fail
   * assertThat(Sets.immutable.empty()).hasSize(1);
   * assertThat(Bags.immutable.of(1, 2, 3)).hasSize(2);
   * }</pre>
   *
   * @param expected the expected number of values in the actual collection.
   * @return {@code this} assertion object.
   * @throws AssertionError if the number of values of the actual collection is not equal to the given one.
   */
  @Override
  public SELF hasSize(int expected) {
    isNotNull();

    int actualSize = actual.size();
    if (actualSize == expected) {
      return myself;
    }

    throw assertionError(shouldHaveSize(actual, actualSize, expected));
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
    isNotNull();

    int actualSize = actual.size();
    if (actualSize > boundary) {
      return myself;
    }

    throw assertionError(shouldHaveSizeGreaterThan(actual, actualSize, boundary));
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
    isNotNull();

    int actualSize = actual.size();
    if (actualSize >= boundary) {
      return myself;
    }

    throw assertionError(shouldHaveSizeGreaterThanOrEqualTo(actual, actualSize, boundary));
  }

  /**
   * Verifies that the number of values in the actual RichIterable is less than the given boundary.
   * <p>
   * Example:
   * <pre>{@code
   * // assertion will pass
   * assertThat(Lists.immutable.of(1, 2, 3)).hasSizeLessThan(4);
   *
   * // assertion will fail
   * assertThat(Lists.immutable.of(1, 2, 3)).hasSizeLessThan(3);
   * }</pre>
   *
   * @param boundary the given value to compare the actual size to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the number of values of the actual RichIterable is not less than the boundary.
   */
  @Override
  public SELF hasSizeLessThan(int boundary) {
    isNotNull();

    int actualSize = actual.size();
    if (actualSize < boundary) {
      return myself;
    }

    throw assertionError(shouldHaveSizeLessThan(actual, actualSize, boundary));
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
    isNotNull();

    int actualSize = actual.size();
    if (actualSize <= boundary) {
      return myself;
    }

    throw assertionError(shouldHaveSizeLessThanOrEqualTo(actual, actualSize, boundary));
  }

  /**
   * Verifies that the actual RichIterable is empty.
   *
   * @throws AssertionError if the actual RichIterable is not empty.
   */
  @Override
  public void isEmpty() {
    isNotNull();

    if (actual.isEmpty()) {
      return;
    }

    throw assertionError(shouldBeEmpty(actual));
  }

  /**
   * Verifies that the actual RichIterable is not empty.
   *
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual RichIterable is empty.
   */
  @Override
  public SELF isNotEmpty() {
    isNotNull();

    if (actual.notEmpty()) {
      return myself;
    }

    throw assertionError(shouldNotBeEmpty());
  }

  /**
   * Verifies that the actual RichIterable is null or empty.
   *
   * @throws AssertionError if the actual RichIterable is not null or not empty.
   */
  @Override
  public void isNullOrEmpty() {
    if (actual == null || actual.isEmpty()) {
      return;
    }

    throw assertionError(shouldBeNullOrEmpty(actual));
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

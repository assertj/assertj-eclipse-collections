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
import static org.assertj.core.error.AnyElementShouldMatch.anyElementShouldMatch;
import static org.assertj.core.error.ElementsShouldMatch.elementsShouldMatch;
import static org.assertj.core.error.ElementsShouldSatisfy.elementsShouldSatisfy;
import static org.assertj.core.error.ShouldContain.shouldContain;
import static org.assertj.core.error.ShouldContainAnyOf.shouldContainAnyOf;
import static org.assertj.core.error.ShouldNotContain.shouldNotContain;

import java.util.Optional;

import org.assertj.core.error.UnsatisfiedRequirement;
import org.assertj.core.presentation.PredicateDescription;
import org.eclipse.collections.api.ByteIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.predicate.primitive.BytePredicate;
import org.eclipse.collections.api.block.procedure.primitive.ByteProcedure;
import org.eclipse.collections.api.factory.primitive.ByteLists;
import org.eclipse.collections.api.list.primitive.ByteList;
import org.eclipse.collections.api.list.primitive.ImmutableByteList;

public class ByteIterableAssert extends AbstractPrimitiveIterableAssert<ByteIterableAssert, ByteIterable> {

  public ByteIterableAssert(ByteIterable actual) {
    super(actual, ByteIterableAssert.class);
  }

  public ByteIterableAssert allMatch(BytePredicate predicate) {
    return executeAssertion(() -> assertAllMatch(predicate, PredicateDescription.GIVEN));
  }

  public ByteIterableAssert allMatch(BytePredicate predicate, String predicateDescription) {
    return executeAssertion(() -> assertAllMatch(predicate, new PredicateDescription(predicateDescription)));
  }

  private void assertAllMatch(BytePredicate predicate, PredicateDescription predicateDescription) {
    isNotNull();
    requireNonNull(predicate, "The predicate to evaluate should not be null");
    isNotEmpty();

    ByteList nonMatches = actual.reject(predicate).toList();
    if (nonMatches.isEmpty()) {
      return;
    }

    throw assertionError(elementsShouldMatch(actual, nonMatches.size() == 1 ? nonMatches.getFirst() : nonMatches, predicateDescription));
  }

  public ByteIterableAssert allSatisfy(ByteProcedure requirements) {
    return executeAssertion(() -> {
      isNotNull();
      isNotEmpty();
      requireNonNull(requirements, "The ByteProcedure expressing the assertions requirements must not be null");

      RichIterable<UnsatisfiedRequirement> unsatisfiedRequirements = actual.collect(element -> failsRequirements(requirements, element))
        .collectIf(Optional::isPresent, Optional::get);
      if (unsatisfiedRequirements.isEmpty()) {
        return;
      }

      throw assertionError(elementsShouldSatisfy(actual, unsatisfiedRequirements.toList(), info));
    });
  }

  private static Optional<UnsatisfiedRequirement> failsRequirements(ByteProcedure requirements, byte element) {
    try {
      requirements.value(element);
    } catch (AssertionError ex) {
      return Optional.of(new UnsatisfiedRequirement(element, ex));
    }
    return Optional.empty();
  }

  public ByteIterableAssert anyMatch(BytePredicate predicate) {
    return executeAssertion(() -> assertAnyMatch(predicate, PredicateDescription.GIVEN));
  }

  public ByteIterableAssert anyMatch(BytePredicate predicate, String predicateDescription) {
    return executeAssertion(() -> assertAnyMatch(predicate, new PredicateDescription(predicateDescription)));
  }

  private void assertAnyMatch(BytePredicate predicate, PredicateDescription predicateDescription) {
    isNotNull();
    requireNonNull(predicate, "The predicate to evaluate should not be null");
    if (actual.noneSatisfy(predicate)) {
      throw assertionError(anyElementShouldMatch(actual, predicateDescription));
    }
  }

  public ByteIterableAssert contains(byte... values) {
    return executeAssertion(() -> {
      isNotNull();

      ByteIterable notFound = ByteLists.immutable.of(values).reject(actual::contains);

      if (notFound.isEmpty()) {
        return;
      }

      throw assertionError(shouldContain(actual, values, notFound));
    });
  }

  public ByteIterableAssert containsAnyOf(byte... values) {
    return executeAssertion(() -> {
      isNotNull();
      requireNonNull(values, "The array of values to look for should not be null");

      if (actual.isEmpty() && values.length == 0) {
        return;
      }

      if (actual.containsAny(values)) {
        return;
      }

      throw assertionError(shouldContainAnyOf(actual, values));
    });
  }

  public ByteIterableAssert doesNotContain(byte... values) {
    return executeAssertion(() -> {
      isNotNull();

      ImmutableByteList found = ByteLists.immutable.of(values).select(actual::contains);
      if (found.isEmpty()) {
        return;
      }

      throw assertionError(shouldNotContain(actual, values, found));
    });
  }
}

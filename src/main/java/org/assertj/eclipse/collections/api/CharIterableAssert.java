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
import static org.assertj.core.error.ShouldContain.shouldContain;
import static org.assertj.core.error.ShouldContainAnyOf.shouldContainAnyOf;
import static org.assertj.core.error.ShouldNotContain.shouldNotContain;

import java.util.Optional;

import org.assertj.core.error.UnsatisfiedRequirement;
import org.assertj.core.presentation.PredicateDescription;
import org.eclipse.collections.api.CharIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.predicate.primitive.CharPredicate;
import org.eclipse.collections.api.block.procedure.primitive.CharProcedure;
import org.eclipse.collections.api.factory.primitive.CharLists;
import org.eclipse.collections.api.list.primitive.CharList;
import org.eclipse.collections.api.list.primitive.ImmutableCharList;

public class CharIterableAssert extends AbstractPrimitiveIterableAssert<CharIterableAssert, CharIterable> {
  public CharIterableAssert(CharIterable actual) {
    super(actual, CharIterableAssert.class);
  }

  public CharIterableAssert allMatch(CharPredicate predicate) {
    return executeAssertion(() -> assertAllMatch(predicate, PredicateDescription.GIVEN));
  }

  public CharIterableAssert allMatch(CharPredicate predicate, String predicateDescription) {
    return executeAssertion(() -> assertAllMatch(predicate, new PredicateDescription(predicateDescription)));
  }

  private void assertAllMatch(CharPredicate predicate, PredicateDescription predicateDescription) {
    isNotNull();
    requireNonNull(predicate, "The predicate to evaluate should not be null");
    isNotEmpty();

    CharList nonMatches = actual.reject(predicate).toList();
    if (nonMatches.isEmpty()) {
      return;
    }

    throw assertionError(elementsShouldMatch(actual, nonMatches.size() == 1 ? nonMatches.getFirst() : nonMatches, predicateDescription));
  }

  public CharIterableAssert allSatisfy(CharProcedure requirements) {
    return executeAssertion(() -> {
      isNotNull();
      isNotEmpty();
      requireNonNull(requirements, "The CharProcedure expressing the assertions requirements must not be null");

      RichIterable<UnsatisfiedRequirement> unsatisfiedRequirements = actual.collect(element -> failsRequirements(requirements, element))
        .collectIf(Optional::isPresent, Optional::get);
      if (unsatisfiedRequirements.isEmpty()) {
        return;
      }

      throw assertionError(elementsShouldSatisfy(actual, unsatisfiedRequirements.toList(), info));
    });
  }

  private static Optional<UnsatisfiedRequirement> failsRequirements(CharProcedure requirements, char element) {
    try {
      requirements.value(element);
    } catch (AssertionError ex) {
      return Optional.of(new UnsatisfiedRequirement(element, ex));
    }
    return Optional.empty();
  }

  public CharIterableAssert contains(char... values) {
    return executeAssertion(() -> {
      isNotNull();

      CharIterable notFound = CharLists.immutable.of(values).reject(actual::contains);

      if (notFound.isEmpty()) {
        return;
      }

      throw assertionError(shouldContain(actual, values, notFound));
    });
  }

  public CharIterableAssert containsAnyOf(char... values) {
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

  public CharIterableAssert doesNotContain(char... values) {
    return executeAssertion(() -> {
      isNotNull();

      ImmutableCharList found = CharLists.immutable.of(values).select(actual::contains);
      if (found.isEmpty()) {
        return;
      }

      throw assertionError(shouldNotContain(actual, values, found));
    });
  }
}

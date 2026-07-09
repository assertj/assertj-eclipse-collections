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
import static org.assertj.core.error.ShouldContain.shouldContain;

import org.assertj.core.presentation.PredicateDescription;
import org.eclipse.collections.api.CharIterable;
import org.eclipse.collections.api.block.predicate.primitive.CharPredicate;
import org.eclipse.collections.api.factory.primitive.CharLists;
import org.eclipse.collections.api.list.primitive.CharList;

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
}

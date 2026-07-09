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
import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.block.predicate.primitive.BooleanPredicate;
import org.eclipse.collections.api.factory.primitive.BooleanLists;
import org.eclipse.collections.api.list.primitive.BooleanList;

public class BooleanIterableAssert extends AbstractPrimitiveIterableAssert<BooleanIterableAssert, BooleanIterable> {
  public BooleanIterableAssert(BooleanIterable actual) {
    super(actual, BooleanIterableAssert.class);
  }

  public BooleanIterableAssert allMatch(BooleanPredicate predicate) {
    return executeAssertion(() -> assertAllMatch(predicate, PredicateDescription.GIVEN));
  }

  public BooleanIterableAssert allMatch(BooleanPredicate predicate, String predicateDescription) {
    return executeAssertion(() -> assertAllMatch(predicate, new PredicateDescription(predicateDescription)));
  }

  private void assertAllMatch(BooleanPredicate predicate, PredicateDescription predicateDescription) {
    isNotNull();
    requireNonNull(predicate, "The predicate to evaluate should not be null");
    isNotEmpty();

    BooleanList nonMatches = actual.reject(predicate).toList();
    if (nonMatches.isEmpty()) {
      return;
    }

    throw assertionError(elementsShouldMatch(actual, nonMatches.size() == 1 ? nonMatches.getFirst() : nonMatches, predicateDescription));
  }

  public BooleanIterableAssert contains(boolean... values) {
    return executeAssertion(() -> {
      isNotNull();

      BooleanIterable notFound = BooleanLists.immutable.of(values).reject(actual::contains);

      if (notFound.isEmpty()) {
        return;
      }

      throw assertionError(shouldContain(actual, values, notFound));
    });
  }
}

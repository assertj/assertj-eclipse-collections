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
import org.eclipse.collections.api.ByteIterable;
import org.eclipse.collections.api.block.predicate.primitive.BytePredicate;
import org.eclipse.collections.api.factory.primitive.ByteLists;
import org.eclipse.collections.api.list.primitive.ByteList;

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
}

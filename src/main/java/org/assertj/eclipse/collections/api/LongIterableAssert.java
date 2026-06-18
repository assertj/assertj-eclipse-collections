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

import static org.assertj.core.error.ShouldContain.shouldContain;

import org.eclipse.collections.api.LongIterable;
import org.eclipse.collections.api.factory.primitive.LongLists;

public class LongIterableAssert extends AbstractPrimitiveIterableAssert<LongIterableAssert, LongIterable> {
  public LongIterableAssert(LongIterable actual) {
    super(actual, LongIterableAssert.class);
  }

  public LongIterableAssert contains(long... values) {
    return executeAssertion(() -> {
      isNotNull();

      LongIterable notFound = LongLists.immutable.of(values).reject(actual::contains);

      if (notFound.isEmpty()) {
        return;
      }

      throw assertionError(shouldContain(actual, values, notFound));
    });
  }
}

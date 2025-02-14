/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2025-2025 the original author or authors.
 */
package org.assertj.eclipse.collections.api.multimap;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.multimap.Multimap;
import org.junit.jupiter.api.Test;

public interface AbstractMultimapAssert_IsNullOrEmpty_Contract<KEY, VALUE, I extends Multimap<KEY, VALUE>, A extends AbstractMultimapAssert<A, I, KEY, VALUE>> {
  A assertion(I testInput);

  A softAssertion(SoftAssertions softAssertions, I testInput);

  I testInput();

  I emptyInput();

  default I nullInput() {
    return null;
  }

  @Test
  default void passesEmptyMultimap() {
    assertion(emptyInput()).isNullOrEmpty();
  }

  @Test
  default void passesNullMultimap() {
    assertion(nullInput()).isNullOrEmpty();
  }

  @Test
  default void failsNotNullOrEmpty() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(testInput()).isNullOrEmpty())
      .withMessageContaining("Expecting null or empty but was: " + testInput().toString());
  }

  @Test
  default void softAssertionPassesEmpty() {
    SoftAssertions.assertSoftly(softly -> softAssertion(softly, emptyInput()).isNullOrEmpty());
  }

  @Test
  default void softAssertionPassesNullMultimap() {
    SoftAssertions.assertSoftly(softly -> softAssertion(softly, nullInput()).isNullOrEmpty());
  }
}

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

public interface AbstractMultimapAssert_HasDistinctSizeGreaterThan_Contract<KEY, VALUE, I extends Multimap<KEY, VALUE>, A extends AbstractMultimapAssert<A, I, KEY, VALUE>> {
  I testInput();

  I emptyInput();

  A assertion(I testInput);

  A softAssertion(SoftAssertions softAssertions, I testInput);

  int upperBoundary();

  int lowerBoundary();

  int equalsBoundary();

  /**
   * Test data input that always returns null. Used for testing how assertions handle null.
   */
  default I nullInput() {
    return null;
  }

  @Test
  default void passesGreaterThan() {
    assertion(testInput()).hasDistinctSizeGreaterThan(lowerBoundary());
  }

  @Test
  default void failsLesser() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(testInput()).hasDistinctSizeGreaterThan(upperBoundary()))
      .withMessageContaining("Expecting distinct size of")
      .withMessageContaining(String.format("to be greater than %s but was %s", upperBoundary(), testInput().sizeDistinct()));
  }

  @Test
  default void failsEquals() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(testInput()).hasDistinctSizeGreaterThan(equalsBoundary()))
      .withMessageContaining("Expecting distinct size of")
      .withMessageContaining(String.format("to be greater than %s but was %s", equalsBoundary(), testInput().sizeDistinct()));
  }

  @Test
  default void failsNullMultimap() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(nullInput()).hasDistinctSizeGreaterThan(lowerBoundary()))
      .withMessageContaining("Expecting actual not to be null");
  }

  @Test
  default void failsEmptyMultimap() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(emptyInput()).hasDistinctSizeGreaterThan(lowerBoundary()))
      .withMessageContaining(String.format("to be greater than %s but was 0", lowerBoundary()));
  }

  @Test
  default void softAssertionPasses() {
    SoftAssertions.assertSoftly(softly -> softAssertion(softly, testInput()).hasDistinctSizeGreaterThan(lowerBoundary()));
  }
}

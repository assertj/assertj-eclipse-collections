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
package org.assertj.eclipse.collections.test.api.multimap;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.assertj.eclipse.collections.api.multimap.MultimapAssert;
import org.eclipse.collections.api.multimap.Multimap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class MultimapAssert_HasSizeBetween_Test {

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.test.api.multimap.MultimapTestData#sizeBetweenTestData")
  void passesSizeBetween(Multimap<String, String> actual, int lowerBoundary, int upperBoundary) {
    assertThatNoException().isThrownBy(() -> new MultimapAssert<>(actual).hasSizeBetween(lowerBoundary, upperBoundary));
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.test.api.multimap.MultimapTestData#sizeBetweenInclusiveUpperTestData")
  void passesSizeBetweenInclusiveUpper(Multimap<String, String> actual, int lowerBoundary, int upperBoundary) {
    assertThatNoException().isThrownBy(() -> new MultimapAssert<>(actual).hasSizeBetween(lowerBoundary, upperBoundary));
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.test.api.multimap.MultimapTestData#sizeBetweenInclusiveLowerTestData")
  void passesSizeBetweenInclusiveLower(Multimap<String, String> actual, int lowerBoundary, int upperBoundary) {
    assertThatNoException().isThrownBy(() -> new MultimapAssert<>(actual).hasSizeBetween(lowerBoundary, upperBoundary));
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.test.api.multimap.MultimapTestData#sizeBelowLowerBoundaryTestData")
  void failsSizeFallsBelowLowerBoundary(Multimap<String, String> actual, int lowerBoundary, int upperBoundary) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(actual).hasSizeBetween(lowerBoundary, upperBoundary))
      .withMessageContaining(String.format("Expected size to be between: %s and %s but was: %s", lowerBoundary, upperBoundary, actual.size()));
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.test.api.multimap.MultimapTestData#sizeAboveUpperBoundaryTestData")
  void failsSizeFallsAboveUpperBoundary(Multimap<String, String> actual, int lowerBoundary, int upperBoundary) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(actual).hasSizeBetween(lowerBoundary, upperBoundary))
      .withMessageContaining(String.format("Expected size to be between: %s and %s but was: %s", lowerBoundary, upperBoundary, actual.size()));
  }

  @Test
  void failsNullMultimap() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(null).hasSizeBetween(25, 50))
      .withMessageContaining("Expecting actual not to be null");
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.test.api.multimap.MultimapTestData#emptyMultimaps")
  void failsEmptyMultimap(Multimap<String, String> actual) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(actual).hasSizeBetween(25, 50))
      .withMessageContaining(String.format("Expected size to be between: %s and %s but was: %s", 25, 50, actual.size()));
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.test.api.multimap.MultimapTestData#sizeBetweenTestData")
  void softAssertionPasses(Multimap<String, String> actual, int lowerBoundary, int upperBoundary) {
    SoftAssertions.assertSoftly(softly -> softly.assertThat(actual).hasSizeBetween(lowerBoundary, upperBoundary));
  }
}

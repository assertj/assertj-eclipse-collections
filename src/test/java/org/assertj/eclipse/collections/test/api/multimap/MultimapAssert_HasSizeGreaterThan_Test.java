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

class MultimapAssert_HasSizeGreaterThan_Test {

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.test.api.multimap.MultimapTestData#sizeLowerBoundaryTestData")
  void passesGreaterThan(Multimap<String, String> actual, int lowerBoundary) {
    assertThatNoException().isThrownBy(() -> new MultimapAssert<>(actual).hasSizeGreaterThan(lowerBoundary));
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.test.api.multimap.MultimapTestData#sizeUpperBoundaryTestData")
  void failsLesser(Multimap<String, String> actual, int upperBoundary) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(actual).hasSizeGreaterThan(upperBoundary))
      .withMessageContaining("Expecting size of")
      .withMessageContaining(String.format("to be greater than %s but was %s", upperBoundary, actual.size()));
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.test.api.multimap.MultimapTestData#sizeEqualsTestData")
  void failsEquals(Multimap<String, String> actual, int equalsBoundary) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(actual).hasSizeGreaterThan(equalsBoundary))
      .withMessageContaining("Expecting size of")
      .withMessageContaining(String.format("to be greater than %s but was %s", equalsBoundary, actual.size()));
  }

  @Test
  void failsNullMultimap() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(null).hasSizeGreaterThan(50))
      .withMessageContaining("Expecting actual not to be null");
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.test.api.multimap.MultimapTestData#emptyMultimaps")
  void failsEmptyMultimap(Multimap<String, String> actual) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(actual).hasSizeGreaterThan(5))
      .withMessageContaining(String.format("to be greater than %s but was 0", 5));
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.test.api.multimap.MultimapTestData#sizeLowerBoundaryTestData")
  void softAssertionPasses(Multimap<String, String> actual, int lowerBoundary) {
    SoftAssertions.assertSoftly(softly -> softly.assertThat(actual).hasSizeGreaterThan(lowerBoundary));
  }
}

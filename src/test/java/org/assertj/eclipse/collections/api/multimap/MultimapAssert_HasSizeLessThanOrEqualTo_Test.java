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
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.multimap.Multimap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class MultimapAssert_HasSizeLessThanOrEqualTo_Test {

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#sizeUpperBoundaryTestData")
  void passesLessThan(Multimap<String, String> actual, int upperBoundary) {
    assertThatNoException().isThrownBy(() -> new MultimapAssert<>(actual).hasSizeLessThanOrEqualTo(upperBoundary));
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#sizeEqualsTestData")
  void passesEqual(Multimap<String, String> actual, int equalsBoundary) {
    assertThatNoException().isThrownBy(() -> new MultimapAssert<>(actual).hasSizeLessThanOrEqualTo(equalsBoundary));
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#sizeLowerBoundaryTestData")
  void failsGreater(Multimap<String, String> actual, int lowerBoundary) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(actual).hasSizeLessThanOrEqualTo(lowerBoundary))
      .withMessageContaining("Expecting size of")
      .withMessageContaining(String.format("to be less than or equal to %s but was %s", lowerBoundary, actual.size()));
  }

  @Test
  void failsNullMultimap() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(null).hasSizeLessThanOrEqualTo(42))
      .withMessageContaining("Expecting actual not to be null");
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#sizeUpperBoundaryTestData")
  void softAssertionPasses(Multimap<String, String> actual, int upperBoundary) {
    SoftAssertions.assertSoftly(softly -> softly.assertThat(actual).hasSizeLessThanOrEqualTo(upperBoundary));
  }
}

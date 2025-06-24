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

class MultimapAssert_IsNullOrEmpty_Test {

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#emptyMultimaps")
  void passesEmptyMultimap(Multimap<String, String> actual) {
    assertThatNoException().isThrownBy(() -> new MultimapAssert<>(actual).isNullOrEmpty());
  }

  @Test
  void passesNullMultimap() {
    assertThatNoException().isThrownBy(() -> new MultimapAssert<>(null).isNullOrEmpty());
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#nonEmptyMultimaps")
  void failsNotNullOrEmpty(Multimap<String, String> actual) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(actual).isNullOrEmpty())
      .withMessageContaining("Expecting null or empty but was: " + actual.toString());
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#emptyMultimaps")
  void softAssertionPassesEmpty(Multimap<String, String> actual) {
    SoftAssertions.assertSoftly(softly -> softly.assertThat(actual).isNullOrEmpty());
  }

  @Test
  void softAssertionPassesNullMultimap() {
    assertThatNoException().isThrownBy(() -> SoftAssertions.assertSoftly(softly -> softly.assertThat(null).isNullOrEmpty()));
  }
}

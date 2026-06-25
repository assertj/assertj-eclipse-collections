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
package org.assertj.eclipse.collections.api.primitive;

import org.assertj.eclipse.collections.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

class AbstractPrimitiveIterableAssert_HasSizeLessThan_Test {

  @PrimitiveIterableParameterizedTest
  void passes(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      assertFactory.fromSize(2).hasSizeLessThan(3));
  }

  @PrimitiveIterableParameterizedTest
  void passesEmpty(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      assertFactory.fromEmpty().hasSizeLessThan(3));
  }

  @PrimitiveIterableParameterizedTest
  void failsEquals(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromSize(2).hasSizeLessThan(2))
      .withMessageContaining(String.format("to be less than %s but was 2", 2));
  }

  @PrimitiveIterableParameterizedTest
  void failsGreaterThan(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromSize(2).hasSizeLessThan(1))
      .withMessageContaining(String.format("to be less than %s but was 2", 1));
  }

  @PrimitiveIterableParameterizedTest
  void failsNullInput(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromNull().hasSizeLessThan(3))
      .withMessageContaining("Expecting actual not to be null");
  }

  @PrimitiveIterableParameterizedTest
  void softAssertionPasses(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      SoftAssertions.assertSoftly(softly ->
        assertFactory.softlyFromSize(softly, 2).hasSizeLessThan(3)));
  }
}

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

import java.util.List;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.primitive.IntLists;

class AbstractPrimitiveIterableAssert_HasSameSizeAs_Test {

  @PrimitiveIterableParameterizedTest
  void passesPrimitiveIterable(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      assertFactory.fromSize(2).hasSameSizeAs(IntLists.immutable.of(1, 2)));
  }

  @PrimitiveIterableParameterizedTest
  void passesRichIterable(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      assertFactory.fromSize(2).hasSameSizeAs(Lists.immutable.of("TOS", "TNG")));
  }

  @PrimitiveIterableParameterizedTest
  void passesJcfIterable(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      assertFactory.fromSize(2).hasSameSizeAs(List.of("TOS", "TNG")));
  }

  @PrimitiveIterableParameterizedTest
  void passesArray(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      assertFactory.fromSize(2).hasSameSizeAs(new String[]{"TOS", "TNG"}));
  }

  @PrimitiveIterableParameterizedTest
  void failsPrimitiveIterableDifferentSize(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromSize(2).hasSameSizeAs(IntLists.immutable.of(1, 2, 3)))
      .withMessageContaining("Actual and expected should have same size but actual size is:");
  }

  @PrimitiveIterableParameterizedTest
  void failsRichIterableDifferentSize(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromSize(2).hasSameSizeAs(Lists.immutable.of("TOS", "TNG", "DS9")))
      .withMessageContaining("Actual and expected should have same size but actual size is:");
  }

  @PrimitiveIterableParameterizedTest
  void failsJcfIterableDifferentSize(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromSize(2).hasSameSizeAs(List.of("TOS", "TNG", "DS9")))
      .withMessageContaining("Actual and expected should have same size but actual size is:");
  }

  @PrimitiveIterableParameterizedTest
  void failsArrayDifferentSize(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromSize(2).hasSameSizeAs(new String[]{"TOS", "TNG", "DS9"}))
      .withMessageContaining("Actual and expected should have same size but actual size is:");
  }

  @PrimitiveIterableParameterizedTest
  void failsPrimitiveIterableNullInput(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromNull().hasSameSizeAs(IntLists.immutable.of(1, 2)))
      .withMessageContaining("Expecting actual not to be null");
  }

  @PrimitiveIterableParameterizedTest
  void failsRichIterableNullInput(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromNull().hasSameSizeAs(Lists.immutable.of("TOS", "TNG")))
      .withMessageContaining("Expecting actual not to be null");
  }

  @PrimitiveIterableParameterizedTest
  void failsJcfIterableNullInput(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromNull().hasSameSizeAs(List.of("TOS", "TNG")))
      .withMessageContaining("Expecting actual not to be null");
  }

  @PrimitiveIterableParameterizedTest
  void failsArrayNullInput(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromNull().hasSameSizeAs(new String[]{"TOS", "TNG"}))
      .withMessageContaining("Expecting actual not to be null");
  }

  @PrimitiveIterableParameterizedTest
  void softAssertionPassesPrimitiveIterable(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      SoftAssertions.assertSoftly(softly ->
        assertFactory.softlyFromSize(softly, 2).hasSameSizeAs(IntLists.immutable.of(1, 2))));
  }

  @PrimitiveIterableParameterizedTest
  void softAssertionPassesRichIterable(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      SoftAssertions.assertSoftly(softly ->
        assertFactory.softlyFromSize(softly, 2).hasSameSizeAs(Lists.immutable.of("TOS", "TNG"))));
  }

  @PrimitiveIterableParameterizedTest
  void softAssertionPassesJcfIterable(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      SoftAssertions.assertSoftly(softly ->
        assertFactory.softlyFromSize(softly, 2).hasSameSizeAs(List.of("TOS", "TNG"))));
  }

  @PrimitiveIterableParameterizedTest
  void softAssertionPassesArray(PrimitiveIterableAssertFactory<?> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      SoftAssertions.assertSoftly(softly ->
        assertFactory.softlyFromSize(softly, 2).hasSameSizeAs(new String[]{"TOS", "TNG"})));
  }
}

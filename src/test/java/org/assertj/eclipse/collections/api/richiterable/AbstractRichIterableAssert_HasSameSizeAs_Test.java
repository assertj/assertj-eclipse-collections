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
package org.assertj.eclipse.collections.api.richiterable;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.List;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.factory.Lists;

class AbstractRichIterableAssert_HasSameSizeAs_Test {

  @RichIterableParameterizedTest
  void passesRichIterable(RichIterableAssertFactory<String> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").hasSameSizeAs(Lists.immutable.of("a", "b", "c", "d", "e")));
  }

  @RichIterableParameterizedTest
  void passesJcfIterable(RichIterableAssertFactory<String> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").hasSameSizeAs(List.of("a", "b", "c", "d", "e")));
  }

  @RichIterableParameterizedTest
  void failsRichIterableDifferentSize(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").hasSameSizeAs(Lists.immutable.of("a", "b", "c")))
      .withMessageContaining("Actual and expected should have same size but actual size is:");
  }

  @RichIterableParameterizedTest
  void failsJcfIterableDifferentSize(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").hasSameSizeAs(List.of("a", "b", "c")))
      .withMessageContaining("Actual and expected should have same size but actual size is:");
  }

  @RichIterableParameterizedTest
  void failsRichIterableNullInput(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromNull().hasSameSizeAs(Lists.immutable.of("a", "b", "c", "d", "e")))
      .withMessageContaining("Expecting actual not to be null");
  }

  @RichIterableParameterizedTest
  void failsJcfIterableNullInput(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromNull().hasSameSizeAs(List.of("a", "b", "c", "d", "e")))
      .withMessageContaining("Expecting actual not to be null");
  }

  @RichIterableParameterizedTest
  void softAssertionPassesRichIterable(RichIterableAssertFactory<String> assertFactory) {
    SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromElements(softly, "TOS", "TNG", "DS9", "VOY", "ENT").hasSameSizeAs(Lists.immutable.of("a", "b", "c", "d", "e")));
  }

  @RichIterableParameterizedTest
  void softAssertionPassesJcfIterable(RichIterableAssertFactory<String> assertFactory) {
    SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromElements(softly, "TOS", "TNG", "DS9", "VOY", "ENT").hasSameSizeAs(List.of("a", "b", "c", "d", "e")));
  }

  @RichIterableParameterizedTest
  void passesArray(RichIterableAssertFactory<String> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").hasSameSizeAs(new String[]{"a", "b", "c", "d", "e"}));
  }

  @RichIterableParameterizedTest
  void failsArrayDifferentSize(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").hasSameSizeAs(new String[]{"a", "b", "c"}))
      .withMessageContaining("Actual and expected should have same size but actual size is:");
  }

  @RichIterableParameterizedTest
  void failsArrayNullInput(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromNull().hasSameSizeAs(new String[]{"a", "b", "c", "d", "e"}))
      .withMessageContaining("Expecting actual not to be null");
  }

  @RichIterableParameterizedTest
  void softAssertionPassesArray(RichIterableAssertFactory<String> assertFactory) {
    SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromElements(softly, "TOS", "TNG", "DS9", "VOY", "ENT").hasSameSizeAs(new String[]{"a", "b", "c", "d", "e"}));
  }
}

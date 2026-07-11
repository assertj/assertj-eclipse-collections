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

import org.assertj.eclipse.collections.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

class AbstractRichIterableAssert_DoesNotContain_Test {
  @RichIterableParameterizedTest
  void passesSingleValue(RichIterableAssertFactory<String> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").doesNotContain("DIS"));
  }

  @RichIterableParameterizedTest
  void passesMultipleValues(RichIterableAssertFactory<String> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").doesNotContain("DIS", "PIC", "SNW"));
  }

  @RichIterableParameterizedTest
  void passesEmpty(RichIterableAssertFactory<String> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      assertFactory.fromEmpty().doesNotContain("DS9"));
  }

  @RichIterableParameterizedTest
  void fails(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").doesNotContain("DS9"))
      .withMessageContaining("not to contain")
      .withMessageContaining("but found")
      .withMessageContaining("DS9");
  }

  @RichIterableParameterizedTest
  void failsWithMultipleValues(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").doesNotContain("DIS", "DS9"))
      .withMessageContaining("not to contain")
      .withMessageContaining("but found")
      .withMessageContaining("DS9");
  }

  @RichIterableParameterizedTest
  void failsNull(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromNull().doesNotContain("DS9"))
      .withMessageContaining("Expecting actual not to be null");
  }

  @RichIterableParameterizedTest
  void failsNullInput(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(NullPointerException.class)
      .isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").doesNotContain(null))
      .withMessageContaining("The array of values to look for should not be null");
  }

  @RichIterableParameterizedTest
  void softAssertionPasses(RichIterableAssertFactory<String> assertFactory) {
    SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromElements(softly, "TOS", "TNG", "DS9", "VOY", "ENT").doesNotContain("DIS"));
  }
}

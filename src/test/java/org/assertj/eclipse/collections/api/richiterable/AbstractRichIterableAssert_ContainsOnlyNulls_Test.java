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

class AbstractRichIterableAssert_ContainsOnlyNulls_Test {
  @RichIterableParameterizedTest
  void passesSingleValue(RichIterableAssertFactory<String> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      assertFactory.fromElements((String) null).containsOnlyNulls());
  }

  @RichIterableParameterizedTest
  void passesMultipleValues(RichIterableAssertFactory<String> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      assertFactory.fromElements(null, null, null).containsOnlyNulls());
  }

  @RichIterableParameterizedTest
  void fails(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").containsOnlyNulls())
      .withMessageContaining("to contain only null elements but some elements were not");
  }

  @RichIterableParameterizedTest
  void failsWithSomeNulls(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromElements(null, "DS9", null).containsOnlyNulls())
      .withMessageContaining("to contain only null elements but some elements were not")
      .withMessageContaining("DS9");
  }

  @RichIterableParameterizedTest
  void failsEmpty(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromEmpty().containsOnlyNulls())
      .withMessageContaining("to contain only null elements but it was empty");
  }

  @RichIterableParameterizedTest
  void failsNull(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromNull().containsOnlyNulls())
      .withMessageContaining("Expecting actual not to be null");
  }

  @RichIterableParameterizedTest
  void softAssertionPasses(RichIterableAssertFactory<String> assertFactory) {
    SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromElements(softly, null, null).containsOnlyNulls());
  }
}

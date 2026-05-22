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

import java.util.function.Function;
import java.util.function.Predicate;

class AbstractRichIterableAssert_FilteredOn_Test {
  @RichIterableParameterizedTest
  void filteredOn_function_passes(RichIterableAssertFactory<String> assertFactory) {
    assertThatNoException().isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").filteredOn(s -> s.charAt(0), 'T')
      .hasSize(2)
      .containsOnly("TOS", "TNG"));
  }

  @RichIterableParameterizedTest
  void filteredOn_function_nullFunction_throwsException(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").filteredOn((Function) null, 'T'))
      .withMessageContaining("The filter function should not be null");
  }

  @RichIterableParameterizedTest
  void filteredOn_predicate_passes(RichIterableAssertFactory<String> assertFactory) {
    assertThatNoException().isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").filteredOn(s -> s.startsWith("T"))
      .hasSize(2)
      .containsOnly("TOS", "TNG"));
  }

  @RichIterableParameterizedTest
  void filteredOn_predicate_nullPredicate_throwsException(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").filteredOn((Predicate) null))
      .withMessageContaining("The filter predicate should not be null");
  }
}

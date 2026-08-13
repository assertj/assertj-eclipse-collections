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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.function.Consumer;

import org.assertj.core.api.ThrowingConsumer;

class AbstractRichIterableAssert_FilteredOnAssertions_Test {
  @RichIterableParameterizedTest
  void filteredOnAssertions_consumer_passes(RichIterableAssertFactory<String> assertFactory) {
    assertThatNoException().isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT")
      .filteredOnAssertions((Consumer<String>) s -> assertThat(s).startsWith("T"))
      .hasSize(2)
      .containsOnly("TOS", "TNG"));
  }

  @RichIterableParameterizedTest
  void filteredOnAssertions_consumer_noElementPassesAssertions_filtersEverything(RichIterableAssertFactory<String> assertFactory) {
    assertThatNoException().isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT")
      .filteredOnAssertions((Consumer<String>) s -> assertThat(s).hasSize(4))
      .isEmpty());
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  @RichIterableParameterizedTest
  void filteredOnAssertions_consumer_nullConsumer_throwsException(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").filteredOnAssertions((Consumer) null))
      .withMessageContaining("The element assertions should not be null");
  }

  @SuppressWarnings("RedundantCast")
  @RichIterableParameterizedTest
  void filteredOnAssertions_throwingConsumer_passes(RichIterableAssertFactory<String> assertFactory) {
    assertThatNoException().isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT")
      .filteredOnAssertions((ThrowingConsumer<String>) s -> assertThat(s).startsWith("T"))
      .hasSize(2)
      .containsOnly("TOS", "TNG"));
  }

  @SuppressWarnings("RedundantCast")
  @RichIterableParameterizedTest
  void filteredOnAssertions_throwingConsumer_noElementPassesAssertions_filtersEverything(RichIterableAssertFactory<String> assertFactory) {
    assertThatNoException().isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT")
      .filteredOnAssertions((ThrowingConsumer<String>) s -> assertThat(s).hasSize(4))
      .isEmpty());
  }

  @SuppressWarnings({"RedundantCast", "rawtypes", "unchecked"})
  @RichIterableParameterizedTest
  void filteredOnAssertions_throwingConsumer_nullConsumer_throwsException(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").filteredOnAssertions((ThrowingConsumer) null))
      .withMessageContaining("The element assertions should not be null");
  }
}

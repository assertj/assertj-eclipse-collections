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

import java.util.function.Consumer;

import org.assertj.core.api.ThrowingConsumer;
import org.assertj.eclipse.collections.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

class AbstractRichIterableAssert_AnySatisfy_Test {
  @RichIterableParameterizedTest
  void passes(RichIterableAssertFactory<String> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").anySatisfy((Consumer<String>) s ->
        assertThat(s).contains("9")));
  }

  @RichIterableParameterizedTest
  void failsEmpty(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromEmpty().anySatisfy((Consumer<String>) s ->
        assertThat(s).hasSize(3)))
      .withMessageContaining("to satisfy the given assertions requirements but none did");
  }

  @RichIterableParameterizedTest
  void fails(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").anySatisfy((Consumer<String>) s ->
        assertThat(s).hasSize(4)))
      .withMessageContaining("to satisfy the given assertions requirements but none did");
  }

  @SuppressWarnings("RedundantCast")
  @RichIterableParameterizedTest
  void passesThrowingConsumer(RichIterableAssertFactory<String> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").anySatisfy((ThrowingConsumer<String>) s ->
        assertThat(s).contains("9")));
  }

  @SuppressWarnings("RedundantCast")
  @RichIterableParameterizedTest
  void failsThrowingConsumer(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").anySatisfy((ThrowingConsumer<String>) s ->
        assertThat(s).hasSize(4)))
      .withMessageContaining("to satisfy the given assertions requirements but none did");
  }

  @RichIterableParameterizedTest
  void softAssertionPasses(RichIterableAssertFactory<String> assertFactory) {
    SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromElements(softly, "TOS", "TNG", "DS9", "VOY", "ENT").anySatisfy((Consumer<String>) s ->
      assertThat(s).contains("9")));
  }
}

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
package org.assertj.eclipse.collections.test.api.multimap;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.assertj.core.api.Condition;
import org.assertj.eclipse.collections.api.SoftAssertions;
import org.assertj.eclipse.collections.api.multimap.MultimapAssert;
import org.eclipse.collections.api.multimap.Multimap;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MultimapAssert_HasValueSatisfying_Test {

  @ParameterizedTest
  @MethodSource("successfulConditionTestCases")
  void passesValueSatisfying(Multimap<String, String> actual, Condition<String> condition) {
    assertThatNoException().isThrownBy(() -> new MultimapAssert<>(actual).hasValueSatisfying(condition));
  }

  @ParameterizedTest
  @MethodSource("failureConditionTestCases")
  void failsValueNotSatisfying(Multimap<String, String> actual, Condition<String> condition) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(actual).hasValueSatisfying(condition))
      .withMessageContaining(condition.description().toString());
  }

  @ParameterizedTest
  @MethodSource("successfulConditionTestCases")
  void failsNullMultimap(Multimap<String, String> ignored, Condition<String> condition) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>((Multimap<String, String>) null).hasValueSatisfying(condition))
      .withMessageContaining("Expecting actual not to be null");
  }

  @ParameterizedTest
  @MethodSource("successfulConditionTestCases")
  void softAssertionPasses(Multimap<String, String> actual, Condition<String> condition) {
    SoftAssertions.assertSoftly(softly -> softly.assertThat(actual).hasValueSatisfying(condition));
  }

  private static Stream<Arguments> successfulConditionTestCases() {
    return Stream.of(
      arguments(MultimapTestData.mutableBagMultimap(), new Condition<>(value -> value.equals("Janeway"), "value equals Janeway")),
      arguments(MultimapTestData.mutableListMultimap(), new Condition<>(value -> value.equals("Janeway"), "value equals Janeway")),
      arguments(MultimapTestData.mutableSetMultimap(), new Condition<>(value -> value.equals("Janeway"), "value equals Janeway"))
    );
  }

  private static Stream<Arguments> failureConditionTestCases() {
    return Stream.of(
      arguments(MultimapTestData.mutableBagMultimap(), new Condition<>(value -> value.equals("Kes"), "value equals Kes")),
      arguments(MultimapTestData.mutableListMultimap(), new Condition<>(value -> value.equals("Kes"), "value equals Kes")),
      arguments(MultimapTestData.mutableSetMultimap(), new Condition<>(value -> value.equals("Kes"), "value equals Kes"))
    );
  }
}

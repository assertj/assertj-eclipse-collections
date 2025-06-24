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
import static org.eclipse.collections.impl.tuple.Tuples.pair;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Multimaps;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class MultimapAssert_ContainsEntry_Test {

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#nonEmptyMultimaps")
  void passes(Multimap<String, String> actual) {
    assertThatNoException().isThrownBy(() -> new MultimapAssert<>(actual).containsEntry("ENT", "Reed"));
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#emptyMultimaps")
  void failsEmpty(Multimap<String, String> actual) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(actual).containsEntry("ENT", "Reed"))
      .withMessageContaining("Expecting")
      .withMessageContaining("to contain")
      .withMessageContaining("but could not find the following element(s)");
  }

  @Test
  void failsNullMultimap() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(null).containsEntry("ENT", "Reed"))
      .withMessageContaining("Expecting actual not to be null");
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#nonEmptyMultimaps")
  void failsMissingEntry(Multimap<String, String> actual) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(actual).containsEntry("VOY", "Kes"))
      .withMessageContaining("Expecting")
      .withMessageContaining("to contain")
      .withMessageContaining("but could not find the following element(s)");
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#nonEmptyMultimaps")
  void softAssertionPasses(Multimap<String, String> actual) {
    SoftAssertions.assertSoftly(softly -> softly.assertThat(actual).containsEntry("ENT", "Reed"));
  }
}

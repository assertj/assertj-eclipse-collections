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
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Map;
import java.util.stream.Stream;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MultimapAssert_Contains_Test {

  @ParameterizedTest
  @MethodSource("successfulPairTestCases")
  void containsPairsPasses(Multimap<String, String> actual, Pair<String, String>[] expectedPairs) {
    assertThatNoException().isThrownBy(() -> new MultimapAssert<>(actual).contains(expectedPairs));
  }

  @ParameterizedTest
  @MethodSource("missingPairTestCases")
  void containsPairsFails(Multimap<String, String> actual, Pair<String, String> missingPair) {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() ->
        new MultimapAssert<>(actual).contains(missingPair))
      .withMessageContaining("Expecting")
      .withMessageContaining("but could not find the following element(s)")
      .withMessageContaining(missingPair.toString());
  }

  @Test
  void nullActualWithPair() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() ->
        new MultimapAssert<>(null).contains(pair("TOS", "McCoy")))
      .withMessageContaining("Expecting actual not to be null");
  }

  @ParameterizedTest
  @MethodSource("successfulPairTestCases")
  void softAssertionsWithPairPasses(Multimap<String, String> actual, Pair<String, String>[] expectedPairs) {
    SoftAssertions.assertSoftly(softly -> softly.assertThat(actual).contains(expectedPairs));
  }

  @ParameterizedTest
  @MethodSource("successfulMapEntryTestCases")
  void containsEntriesPasses(Multimap<String, String> actual, Map.Entry<String, String>[] expectedEntries) {
    assertThatNoException().isThrownBy(() -> new MultimapAssert<>(actual).contains(expectedEntries));
  }

  @ParameterizedTest
  @MethodSource("missingMapEntryTestCases")
  void containsEntriesFails(Multimap<String, String> actual, Map.Entry<String, String> missingEntry) {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() ->
        new MultimapAssert<>(actual).contains(missingEntry))
      .withMessageContaining("Expecting")
      .withMessageContaining("but could not find the following element(s)")
      .withMessageContaining(Tuples.pairFrom(missingEntry).toString());
  }

  @Test
  void nullActualWithEntry() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() ->
        new MultimapAssert<>(null).contains((Map.Entry) pair("TOS", "McCoy").toEntry()))
      .withMessageContaining("Expecting actual not to be null");
  }

  @ParameterizedTest
  @MethodSource("successfulMapEntryTestCases")
  void softAssertionsWithEntryPasses(Multimap<String, String> actual, Map.Entry<String, String>[] expectedEntries) {
    SoftAssertions.assertSoftly(softly -> softly.assertThat(actual).contains(expectedEntries));
  }

  private static Stream<Arguments> successfulPairTestCases() {
    return Stream.of(
      arguments(MultimapTestData.mutableBagMultimap(), new Pair[]{pair("TOS", "McCoy"), pair("TNG", "Crusher")}),
      arguments(MultimapTestData.mutableListMultimap(), new Pair[]{pair("TOS", "McCoy"), pair("TNG", "Crusher")}),
      arguments(MultimapTestData.mutableSetMultimap(), new Pair[]{pair("TOS", "McCoy"), pair("TNG", "Crusher")})
    );
  }

  private static Stream<Arguments> successfulMapEntryTestCases() {
    return Stream.of(
      arguments(MultimapTestData.mutableBagMultimap(), new Map.Entry[]{pair("TOS", "McCoy").toEntry(), pair("TNG", "Crusher").toEntry()}),
      arguments(MultimapTestData.mutableListMultimap(), new Map.Entry[]{pair("TOS", "McCoy").toEntry(), pair("TNG", "Crusher").toEntry()}),
      arguments(MultimapTestData.mutableSetMultimap(), new Map.Entry[]{pair("TOS", "McCoy").toEntry(), pair("TNG", "Crusher").toEntry()})
    );
  }

  private static Stream<Arguments> missingPairTestCases() {
    return Stream.of(
      arguments(MultimapTestData.mutableBagMultimap(), pair("VOY", "Kes")),
      arguments(MultimapTestData.mutableListMultimap(), pair("VOY", "Kes")),
      arguments(MultimapTestData.mutableSetMultimap(), pair("VOY", "Kes"))
    );
  }

  private static Stream<Arguments> missingMapEntryTestCases() {
    return Stream.of(
      arguments(MultimapTestData.mutableBagMultimap(), pair("VOY", "Kes").toEntry()),
      arguments(MultimapTestData.mutableListMultimap(), pair("VOY", "Kes").toEntry()),
      arguments(MultimapTestData.mutableSetMultimap(), pair("VOY", "Kes").toEntry())
    );
  }
}

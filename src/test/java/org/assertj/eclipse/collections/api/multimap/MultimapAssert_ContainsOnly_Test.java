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

import java.util.Map;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class MultimapAssert_ContainsOnly_Test {

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#shipMultimaps")
  void passesWithPairs(Multimap<String, String> actual) {
    Pair<String, String>[] exactMatchPairs = new Pair[]{
      pair("TNG", "Enterprise"),
      pair("DS9", "Deep Space Nine"),
      pair("DS9", "Defiant"),
      pair("VOY", "Voyager")
    };
    assertThatNoException().isThrownBy(() -> new MultimapAssert<>(actual).containsOnly(exactMatchPairs));
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#shipMultimaps")
  void passesWithEntries(Multimap<String, String> actual) {
    Map.Entry<String, String>[] exactMatchEntries = new Map.Entry[]{
      pair("TNG", "Enterprise").toEntry(),
      pair("DS9", "Deep Space Nine").toEntry(),
      pair("DS9", "Defiant").toEntry(),
      pair("VOY", "Voyager").toEntry()
    };
    assertThatNoException().isThrownBy(() -> new MultimapAssert<>(actual).containsOnly(exactMatchEntries));
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#shipMultimaps")
  void failsWhenAdditionalKeysOrValuesExistWithPair(Multimap<String, String> actual) {
    Pair<String, String>[] partialMatchMissingPairs = new Pair[]{
      pair("TNG", "Enterprise"),
      pair("DS9", "Defiant"),
      pair("VOY", "Voyager")
    };
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(actual).containsOnly(partialMatchMissingPairs))
      .withMessageContaining("to contain only")
      .withMessageContaining("but the following multimap entries were unexpected");
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#shipMultimaps")
  void failsWhenAdditionalKeysOrValuesExistWithEntry(Multimap<String, String> actual) {
    Map.Entry<String, String>[] partialMatchMissingEntries = new Map.Entry[]{
      pair("TNG", "Enterprise").toEntry(),
      pair("DS9", "Defiant").toEntry(),
      pair("VOY", "Voyager").toEntry()
    };
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(actual).containsOnly(partialMatchMissingEntries))
      .withMessageContaining("to contain only")
      .withMessageContaining("but the following multimap entries were unexpected");
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#shipMultimaps")
  void failsWhenEntryIsMissingWithPair(Multimap<String, String> actual) {
    Pair<String, String>[] partialMatchExtraPairs = new Pair[]{
      pair("TOS", "Enterprise"),
      pair("TNG", "Enterprise"),
      pair("DS9", "Deep Space Nine"),
      pair("DS9", "Defiant"),
      pair("VOY", "Voyager")
    };
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(actual).containsOnly(partialMatchExtraPairs))
      .withMessageContaining("to contain only")
      .withMessageContaining("but could not find the following multimap entries");
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#shipMultimaps")
  void failsWhenEntryIsMissingWithEntry(Multimap<String, String> actual) {
    Map.Entry<String, String>[] partialMatchExtraEntries = new Map.Entry[]{
      pair("TOS", "Enterprise").toEntry(),
      pair("TNG", "Enterprise").toEntry(),
      pair("DS9", "Deep Space Nine").toEntry(),
      pair("DS9", "Defiant").toEntry(),
      pair("VOY", "Voyager").toEntry()
    };
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(actual).containsOnly(partialMatchExtraEntries))
      .withMessageContaining("to contain only")
      .withMessageContaining("but could not find the following multimap entries");
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#emptyMultimaps")
  void failsForEmptyMultimapWithPair(Multimap<String, String> actual) {
    Pair<String, String>[] exactMatchPairs = new Pair[]{
      pair("TNG", "Enterprise"),
      pair("DS9", "Deep Space Nine")
    };
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(actual).containsOnly(exactMatchPairs))
      .withMessageContaining("to contain only")
      .withMessageContaining("but could not find the following multimap entries");
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#emptyMultimaps")
  void failsForEmptyMultimapWithEntry(Multimap<String, String> actual) {
    Map.Entry<String, String>[] exactMatchEntries = new Map.Entry[]{
      pair("TNG", "Enterprise").toEntry(),
      pair("DS9", "Deep Space Nine").toEntry()
    };
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(actual).containsOnly(exactMatchEntries))
      .withMessageContaining("to contain only")
      .withMessageContaining("but could not find the following multimap entries");
  }

  @Test
  void failsForNullMultimapWithPair() {
    Pair<String, String>[] exactMatchPairs = new Pair[]{
      pair("TNG", "Enterprise"),
      pair("DS9", "Deep Space Nine")
    };
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(null).containsOnly(exactMatchPairs))
      .withMessageContaining("Expecting actual not to be null");
  }

  @Test
  void failsForNullMultimapWithEntry() {
    Map.Entry<String, String>[] exactMatchEntries = new Map.Entry[]{
      pair("TNG", "Enterprise").toEntry(),
      pair("DS9", "Deep Space Nine").toEntry()
    };
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new MultimapAssert<>(null).containsOnly(exactMatchEntries))
      .withMessageContaining("Expecting actual not to be null");
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#shipMultimaps")
  void softAssertionPassesWithPairs(Multimap<String, String> actual) {
    Pair<String, String>[] exactMatchPairs = new Pair[]{
      pair("TNG", "Enterprise"),
      pair("DS9", "Deep Space Nine"),
      pair("DS9", "Defiant"),
      pair("VOY", "Voyager")
    };
    SoftAssertions.assertSoftly(softly -> softly.assertThat(actual).containsOnly(exactMatchPairs));
  }

  @ParameterizedTest
  @MethodSource("org.assertj.eclipse.collections.api.multimap.MultimapTestData#shipMultimaps")
  void softAssertionPassesWithEntries(Multimap<String, String> actual) {
    Map.Entry<String, String>[] exactMatchEntries = new Map.Entry[]{
      pair("TNG", "Enterprise").toEntry(),
      pair("DS9", "Deep Space Nine").toEntry(),
      pair("DS9", "Defiant").toEntry(),
      pair("VOY", "Voyager").toEntry()
    };
    SoftAssertions.assertSoftly(softly -> softly.assertThat(actual).containsOnly(exactMatchEntries));
  }
}

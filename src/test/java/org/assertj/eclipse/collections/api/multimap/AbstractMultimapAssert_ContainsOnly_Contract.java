package org.assertj.eclipse.collections.api.multimap;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Map;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.tuple.Pair;
import org.junit.jupiter.api.Test;

public interface AbstractMultimapAssert_ContainsOnly_Contract<KEY, VALUE, I extends Multimap<KEY, VALUE>, A extends AbstractMultimapAssert<A, I, KEY, VALUE>> {
  I testInput();

  I emptyInput();

  A assertion(I testInput);

  A softAssertion(SoftAssertions softAssertions, I testInput);

  Pair<? extends KEY, ? extends VALUE>[] exactMatchPairs();

  Map.Entry<? extends KEY, ? extends VALUE>[] exactMatchEntries();

  Pair<? extends KEY, ? extends VALUE>[] partialMatchMissingPairs();

  Map.Entry<? extends KEY, ? extends VALUE>[] partialMatchMissingEntries();

  Pair<? extends KEY, ? extends VALUE>[] partialMatchExtraPairs();

  Map.Entry<? extends KEY, ? extends VALUE>[] partialMatchExtraEntries();

  /**
   * Test data input that always returns null. Used for testing how assertions handle null.
   */
  default I nullInput() {
    return null;
  }

  @Test
  default void passesWithPairs() {
    assertion(testInput()).containsOnly(exactMatchPairs());
  }

  @Test
  default void passesWithEntries() {
    assertion(testInput()).containsOnly(exactMatchEntries());
  }

  @Test
  default void failsWhenAdditionalKeysOrValuesExistWithPair() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(testInput()).containsOnly(partialMatchMissingPairs()))
      .withMessageContaining("to contain only")
      .withMessageContaining("but the following multimap entries were unexpected");
  }

  @Test
  default void failsWhenAdditionalKeysOrValuesExistWithEntry() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(testInput()).containsOnly(partialMatchMissingEntries()))
      .withMessageContaining("to contain only")
      .withMessageContaining("but the following multimap entries were unexpected");
  }

  @Test
  default void failsWhenEntryIsMissingWithPair() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(testInput()).containsOnly(partialMatchExtraPairs()))
      .withMessageContaining("to contain only")
      .withMessageContaining("but could not find the following multimap entries");
  }

  @Test
  default void failsWhenEntryIsMissingWithEntry() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(testInput()).containsOnly(partialMatchExtraEntries()))
      .withMessageContaining("to contain only")
      .withMessageContaining("but could not find the following multimap entries");
  }

  @Test
  default void failsForEmptyMultimapWithPair() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(emptyInput()).containsOnly(exactMatchPairs()))
      .withMessageContaining("to contain only")
      .withMessageContaining("but could not find the following multimap entries");
  }

  @Test
  default void failsForEmptyMultimapWithEntry() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(emptyInput()).containsOnly(exactMatchEntries()))
      .withMessageContaining("to contain only")
      .withMessageContaining("but could not find the following multimap entries");
  }

  @Test
  default void failsForNullMultimapWithPair() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(nullInput()).containsOnly(exactMatchPairs()))
      .withMessageContaining("Expecting actual not to be null");
  }

  @Test
  default void failsForNullMultimapWithEntry() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(nullInput()).containsOnly(exactMatchEntries()))
      .withMessageContaining("Expecting actual not to be null");
  }

  @Test
  default void softAssertionPassesWithPairs() {
    SoftAssertions.assertSoftly(softly -> softAssertion(softly, testInput()).containsOnly(exactMatchPairs()));
  }

  @Test
  default void softAssertionPassesWithEntries() {
    SoftAssertions.assertSoftly(softly -> softAssertion(softly, testInput()).containsOnly(exactMatchEntries()));
  }
}

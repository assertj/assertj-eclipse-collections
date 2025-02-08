package org.assertj.eclipse.collections.api.multimap;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Multimaps;
import org.junit.jupiter.api.Test;

public interface AbstractMultimapAssert_ContainsEntry_Contract<KEY, VALUE, I extends Multimap<KEY, VALUE>, A extends AbstractMultimapAssert<A, I, KEY, VALUE>> {
  I testInput();

  I emptyInput();

  A assertion(I testInput);

  A softAssertion(SoftAssertions softAssertions, I testInput);

  Pair<KEY, VALUE> expectedEntry();

  Pair<KEY, VALUE> missingPair();

  /**
   * Test data input that always returns null. Used for testing how assertions handle null.
   */
  default I nullInput() {
    return null;
  }

  @Test
  default void passes() {
    assertion(testInput()).containsEntry(expectedEntry().getOne(), expectedEntry().getTwo());
  }

  @Test
  default void failsEmpty() {
    ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.empty();
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(emptyInput()).containsEntry(expectedEntry().getOne(), expectedEntry().getTwo()))
      .withMessageContaining("Expecting")
      .withMessageContaining("to contain")
      .withMessageContaining("but could not find the following element(s)");
  }

  @Test
  default void failsNullMultimap() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(nullInput()).containsEntry(expectedEntry().getOne(), expectedEntry().getTwo()))
      .withMessageContaining("Expecting actual not to be null");
  }

  @Test
  default void softAssertionPasses() {
    SoftAssertions.assertSoftly(softly -> softAssertion(softly, testInput()).containsEntry(expectedEntry().getOne(), expectedEntry().getTwo()));
  }
}

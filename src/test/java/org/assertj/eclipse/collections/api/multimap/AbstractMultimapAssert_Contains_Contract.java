package org.assertj.eclipse.collections.api.multimap;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Map;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.jupiter.api.Test;

public interface AbstractMultimapAssert_Contains_Contract<KEY, VALUE, I extends Multimap<KEY, VALUE>, A extends AbstractMultimapAssert<A, I, KEY, VALUE>> {
  I testInput();

  I emptyInput();

  A assertion(I testInput);

  A softAssertion(SoftAssertions softAssertions, I testInput);

  Pair<KEY, VALUE>[] expectedPairs();

  Map.Entry<KEY, VALUE>[] expectedEntries();

  Pair<KEY, VALUE> missingPair();

  Map.Entry<KEY, VALUE> missingEntry();

  /**
   * Test data input that always returns null. Used for testing how assertions handle null.
   */
  default I nullInput() {
    return null;
  }

  @Test
  default void containsPairsPasses() {
    this.assertion(this.testInput()).contains(this.expectedPairs());
  }

  @Test
  default void containsPairsFails() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() ->
        this.assertion(this.testInput()).contains(this.missingPair()))
      .withMessageContaining("Expecting")
      .withMessageContaining("but could not find the following element(s)")
      .withMessageContaining(this.missingPair().toString());
  }

  @Test
  default void nullActualWithPair() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() ->
        this.assertion(this.nullInput()).contains(this.expectedPairs()))
      .withMessageContaining("Expecting actual not to be null");
  }

  @Test
  default void softAssertionsWithPairPasses() {
    SoftAssertions.assertSoftly(softly -> this.softAssertion(softly, this.testInput())
      .contains(this.expectedPairs()));
  }

  @Test
  default void containsEntriesPasses() {
    this.assertion(this.testInput()).contains(this.expectedEntries());
  }

  @Test
  default void containsEntriesFails() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() ->
        this.assertion(this.testInput()).contains(this.missingEntry()))
      .withMessageContaining("Expecting")
      .withMessageContaining("but could not find the following element(s)")
      .withMessageContaining(Tuples.pairFrom(this.missingEntry()).toString());
  }

  @Test
  default void nullActualWithEntry() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() ->
        this.assertion(this.nullInput()).contains(this.expectedEntries()))
      .withMessageContaining("Expecting actual not to be null");
  }

  @Test
  default void softAssertionsWithEntryPasses() {
    SoftAssertions.assertSoftly(softly -> this.softAssertion(softly, this.testInput())
      .contains(this.expectedEntries()));
  }
}

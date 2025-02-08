package org.assertj.eclipse.collections.api.multimap;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.multimap.Multimap;
import org.junit.jupiter.api.Test;

public interface AbstractMultimapAssert_HasDistinctSize_Contract<KEY, VALUE, I extends Multimap<KEY, VALUE>, A extends AbstractMultimapAssert<A, I, KEY, VALUE>> {
  I testInput();

  I emptyInput();

  A assertion(I testData);

  A softAssertion(SoftAssertions softAssertions, I testData);

  int expectedSize();

  /**
   * Test data input that always returns null. Used for testing how assertions handle null.
   */
  default I nullInput() {
    return null;
  }

  @Test
  default void passes() {
    this.assertion(this.testInput()).hasDistinctSize(this.expectedSize());
  }

  @Test
  default void failsEmpty() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> this.assertion(this.emptyInput()).hasDistinctSize(this.expectedSize()))
      .withMessageContaining(String.format("Expected distinct size: %s but was: 0", this.expectedSize()));
  }

  @Test
  default void failsNullMultimap() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> this.assertion(this.nullInput()).hasDistinctSize(this.expectedSize()))
      .withMessageContaining("Expecting actual not to be null");
  }

  @Test
  default void softAssertionPasses() {
    SoftAssertions.assertSoftly(softly -> this.softAssertion(softly, this.testInput()).hasDistinctSize(this.expectedSize()));
  }
}

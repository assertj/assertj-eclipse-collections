package org.assertj.eclipse.collections.api.multimap;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.multimap.Multimap;
import org.junit.jupiter.api.Test;

public interface AbstractMultimapAssert_HasSize_Contract<KEY, VALUE, I extends Multimap<KEY, VALUE>, A extends AbstractMultimapAssert<A, I, KEY, VALUE>> {
  I testInput();

  I emptyInput();

  A assertion(I testInput);

  A softAssertion(SoftAssertions softAssertions, I testInput);

  int expectedSize();

  /**
   * Test data input that always returns null. Used for testing how assertions handle null.
   */
  default I nullInput() {
    return null;
  }

  @Test
  default void passes() {
    this.assertion(this.testInput()).hasSize(this.expectedSize());
  }

  @Test
  default void failsEmpty() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> this.assertion(this.emptyInput()).hasSize(this.expectedSize()))
      .withMessageContaining(String.format("Expected size: %s but was: 0", this.expectedSize()));
  }

  @Test
  default void failsNullMultimap() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> this.assertion(this.nullInput()).hasSize(this.expectedSize()))
      .withMessageContaining("Expecting actual not to be null");
  }

  @Test
  default void softAssertionPasses() {
    SoftAssertions.assertSoftly(softly -> this.softAssertion(softly, this.testInput()).hasSize(this.expectedSize()));
  }
}

package org.assertj.eclipse.collections.api.multimap;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.multimap.Multimap;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public interface AbstractMultimapAssert_HasSizeGreaterThan_Contract<KEY, VALUE, I extends Multimap<KEY, VALUE>, A extends AbstractMultimapAssert<A, I, KEY, VALUE>> {
  I testInput();

  I emptyInput();

  A assertion(I testInput);

  A softAssertion(SoftAssertions softAssertions, I testInput);

  int upperBoundary();

  int lowerBoundary();

  int equalsBoundary();

  /**
   * Test data input that always returns null. Used for testing how assertions handle null.
   */
  default I nullInput() {
    return null;
  }

  @Test
  default void passesGreaterThan() {
    assertion(testInput()).hasSizeGreaterThan(lowerBoundary());
  }

  @Test
  default void failsLesser() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(testInput()).hasSizeGreaterThan(upperBoundary()))
      .withMessageContaining("Expecting size of")
      .withMessageContaining(String.format("to be greater than %s but was %s", upperBoundary(), testInput().size()));
  }

  @Test
  default void failsEquals() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(testInput()).hasSizeGreaterThan(equalsBoundary()))
      .withMessageContaining("Expecting size of")
      .withMessageContaining(String.format("to be greater than %s but was %s", equalsBoundary(), testInput().size()));
  }

  @Test
  default void failsNullMultimap() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(nullInput()).hasSizeGreaterThan(upperBoundary()))
      .withMessageContaining("Expecting actual not to be null");
  }

  @Test
  default void failsEmptyMultimap() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(emptyInput()).hasSizeGreaterThan(upperBoundary()))
      .withMessageContaining(String.format("to be greater than %s but was 0", upperBoundary()));
  }

  @Test
  default void softAssertionPasses() {
    SoftAssertions.assertSoftly(softly -> softAssertion(softly, testInput()).hasSizeGreaterThan(lowerBoundary()));
  }
}

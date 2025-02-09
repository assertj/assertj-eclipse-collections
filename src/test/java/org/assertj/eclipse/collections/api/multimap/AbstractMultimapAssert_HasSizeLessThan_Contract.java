package org.assertj.eclipse.collections.api.multimap;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.multimap.Multimap;
import org.junit.jupiter.api.Test;

public interface AbstractMultimapAssert_HasSizeLessThan_Contract<KEY, VALUE, I extends Multimap<KEY, VALUE>, A extends AbstractMultimapAssert<A, I, KEY, VALUE>> {
  A assertion(I testInput);

  A softAssertion(SoftAssertions softAssertions, I testInput);

  I testInput();

  I emptyInput();

  int upperBoundary();

  int lowerBoundary();

  int equalsBoundary();

  default I nullInput() {
    return null;
  }

  @Test
  default void passesLessThan() {
    assertion(testInput()).hasSizeLessThan(upperBoundary());
  }

  @Test
  default void failsGreater() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(testInput()).hasSizeLessThan(lowerBoundary()))
      .withMessageContaining("Expecting size of")
      .withMessageContaining(String.format("to be less than %s but was %s", lowerBoundary(), testInput().size()));
  }

  @Test
  default void failsEquals() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(testInput()).hasSizeLessThan(equalsBoundary()))
      .withMessageContaining("Expecting size of")
      .withMessageContaining(String.format("to be less than %s but was %s", equalsBoundary(), testInput().size()));
  }

  @Test
  default void failsNullMultimap() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(nullInput()).hasSizeLessThan(upperBoundary()))
      .withMessageContaining("Expecting actual not to be null");
  }

  @Test
  default void softAssertionPasses() {
    SoftAssertions.assertSoftly(softly -> softAssertion(softly, testInput()).hasSizeLessThan(upperBoundary()));
  }
}

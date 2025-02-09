package org.assertj.eclipse.collections.api.multimap;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.multimap.Multimap;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public interface AbstractMultimapAssert_HasSizeGreaterThanOrEqualTo_Contract<KEY, VALUE, I extends Multimap<KEY, VALUE>, A extends AbstractMultimapAssert<A, I, KEY, VALUE>> {
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
  default void passesGreaterThan() {
    assertion(testInput()).hasSizeGreaterThanOrEqualTo(lowerBoundary());
  }

  @Test
  default void passesEqual() {
    assertion(testInput()).hasSizeGreaterThanOrEqualTo(equalsBoundary());
  }

  @Test
  default void failsLessThan() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(testInput()).hasSizeGreaterThanOrEqualTo(upperBoundary()))
      .withMessageContaining("Expecting size of")
      .withMessageContaining(String.format("to be greater than or equal to %s but was %s", upperBoundary(), testInput().size()));
  }

  @Test
  default void failsNullMultimap() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(nullInput()).hasSizeGreaterThanOrEqualTo(lowerBoundary()))
      .withMessageContaining("Expecting actual not to be null");
  }

  @Test
  default void failsEmptyMultimap() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(emptyInput()).hasSizeGreaterThanOrEqualTo(lowerBoundary()))
      .withMessageContaining(String.format("to be greater than or equal to %s but was 0", lowerBoundary()));
  }

  @Test
  default void softAssertionPasses() {
    SoftAssertions.assertSoftly(softly -> softAssertion(softly, testInput()).hasSizeGreaterThanOrEqualTo(lowerBoundary()));
  }
}

package org.assertj.eclipse.collections.api.multimap;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.multimap.Multimap;
import org.junit.jupiter.api.Test;

public interface AbstractMultimapAssert_ContainsValues_Contract<KEY, VALUE, I extends Multimap<KEY, VALUE>, A extends AbstractMultimapAssert<A, I, KEY, VALUE>> {
  I testInput();

  I emptyInput();

  A assertion(I testInput);

  A softAssertion(SoftAssertions softAssertions, I testInput);

  VALUE[] expectedValues();

  VALUE missingValue();

  /**
   * Test data input that always returns null. Used for testing how assertions handle null.
   */
  default I nullInput() {
    return null;
  }

  @Test
  default void passes() {
    assertion(testInput()).containsValues(expectedValues());
  }

  @Test
  default void failsEmpty() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(emptyInput()).containsValues(expectedValues()))
      .withMessageContaining("Expecting actual")
      .withMessageContaining("{}")
      .withMessageContaining("to contain values");
  }

  @Test
  default void failsMissingKey() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(testInput()).containsValues(missingValue()))
      .withMessageContaining("Expecting actual")
      .withMessageContaining("to contain value")
      .withMessageContaining(missingValue().toString());
  }

  @Test
  default void failsNullMultimap() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(nullInput()).containsValues(expectedValues()))
      .withMessageContaining("Expecting actual not to be null");
  }

  @Test
  default void softAssertionPasses() {
    SoftAssertions.assertSoftly(softly -> softAssertion(softly, testInput()).containsValues(expectedValues()));
  }
}

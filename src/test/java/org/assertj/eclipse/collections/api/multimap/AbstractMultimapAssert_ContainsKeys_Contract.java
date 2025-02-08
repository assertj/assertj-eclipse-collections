package org.assertj.eclipse.collections.api.multimap;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.multimap.Multimap;
import org.junit.jupiter.api.Test;

public interface AbstractMultimapAssert_ContainsKeys_Contract<KEY, VALUE, I extends Multimap<KEY, VALUE>, A extends AbstractMultimapAssert<A, I, KEY, VALUE>> {
  I testInput();

  I emptyInput();

  A assertion(I testInput);

  A softAssertion(SoftAssertions softAssertions, I testInput);

  KEY[] expectedKeys();

  KEY missingKey();

  /**
   * Test data input that always returns null. Used for testing how assertions handle null.
   */
  default I nullInput() {
    return null;
  }

  @Test
  default void passes() {
    assertion(testInput()).containsKeys(expectedKeys());
  }

  @Test
  default void failsEmpty() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(emptyInput()).containsKeys(expectedKeys()))
      .withMessageContaining("Expecting actual")
      .withMessageContaining("{}")
      .withMessageContaining("to contain keys");
  }

  @Test
  default void failsMissingKey() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(testInput()).containsKeys(missingKey()))
      .withMessageContaining("Expecting actual")
      .withMessageContaining("to contain key")
      .withMessageContaining(missingKey().toString());
  }

  @Test
  default void failsNullMultimap() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(nullInput()).containsKeys(expectedKeys()))
      .withMessageContaining("Expecting actual not to be null");
  }

  @Test
  default void softAssertionPasses() {
    SoftAssertions.assertSoftly(softly -> softAssertion(softly, testInput()).containsKeys(expectedKeys()));
  }
}

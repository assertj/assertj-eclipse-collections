package org.assertj.eclipse.collections.api.multimap;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.multimap.Multimap;
import org.junit.jupiter.api.Test;

public interface AbstractMultimapAssert_IsNotEmpty_Contract<KEY, VALUE, I extends Multimap<KEY, VALUE>, A extends AbstractMultimapAssert<A, I, KEY, VALUE>> {
  A assertion(I testInput);

  A softAssertion(SoftAssertions softAssertions, I testInput);

  I testInput();

  I emptyInput();

  default I nullInput() {
    return null;
  }

  @Test
  default void passesNotEmpty() {
    assertion(testInput()).isNotEmpty();
  }

  @Test
  default void failsEmpty() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(emptyInput()).isNotEmpty())
      .withMessageContaining("Expecting actual not to be empty");
  }

  @Test
  default void failsNullMultimap() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(nullInput()).isNotEmpty())
      .withMessageContaining("Expecting actual not to be null");
  }

  @Test
  default void softAssertionPasses() {
    SoftAssertions.assertSoftly(softly -> softAssertion(softly, testInput()).isNotEmpty());
  }
}

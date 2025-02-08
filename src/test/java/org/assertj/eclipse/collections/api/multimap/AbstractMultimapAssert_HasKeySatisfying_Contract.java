package org.assertj.eclipse.collections.api.multimap;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.Condition;
import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.multimap.Multimap;
import org.junit.jupiter.api.Test;

public interface AbstractMultimapAssert_HasKeySatisfying_Contract<KEY, VALUE, I extends Multimap<KEY, VALUE>, A extends AbstractMultimapAssert<A, I, KEY, VALUE>> {
  A assertion(I testInput);

  A softAssertion(SoftAssertions softAssertions, I testInput);

  I testInput();

  I emptyInput();

  Condition<KEY> passingCondition();

  Condition<KEY> failingCondition();

  default I nullInput() {
    return null;
  }

  @Test
  default void passesValueSatisfying() {
    assertion(testInput()).hasKeySatisfying(passingCondition());
  }

  @Test
  default void failsValueNotSatisfying() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(testInput()).hasKeySatisfying(failingCondition()))
      .withMessageContaining(failingCondition().description().toString());
  }

  @Test
  default void failsNullMultimap() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(nullInput()).hasKeySatisfying(passingCondition()))
      .withMessageContaining("Expecting actual not to be null");
  }

  @Test
  default void softAssertionPasses() {
    SoftAssertions.assertSoftly(softly -> softAssertion(softly, testInput()).hasKeySatisfying(passingCondition()));
  }
}

package org.assertj.eclipse.collections.api.multimap;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.Condition;
import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.multimap.Multimap;
import org.junit.jupiter.api.Test;

public interface AbstractMultimapAssert_HasValueSatisfying_Contract<KEY, VALUE, I extends Multimap<KEY, VALUE>, A extends AbstractMultimapAssert<A, I, KEY, VALUE>> {
  A assertion(I testInput);

  A softAssertion(SoftAssertions softAssertions, I testInput);

  I testInput();

  I emptyInput();

  Condition<VALUE> passingCondition();

  Condition<VALUE> failingCondition();

  default I nullInput() {
    return null;
  }

  @Test
  default void passesValueSatisfying() {
    assertion(testInput()).hasValueSatisfying(passingCondition());
  }

  @Test
  default void failsValueNotSatisfying() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(testInput()).hasValueSatisfying(failingCondition()))
      .withMessageContaining(failingCondition().description().toString());
  }

  @Test
  default void failsNullMultimap() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertion(nullInput()).hasValueSatisfying(passingCondition()))
      .withMessageContaining("Expecting actual not to be null");
  }

  @Test
  default void softAssertionPasses() {
    SoftAssertions.assertSoftly(softly -> softAssertion(softly, testInput()).hasValueSatisfying(passingCondition()));
  }
}

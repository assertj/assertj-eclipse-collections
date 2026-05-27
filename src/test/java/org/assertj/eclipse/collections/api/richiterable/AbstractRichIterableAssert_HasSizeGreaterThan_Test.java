package org.assertj.eclipse.collections.api.richiterable;

import org.assertj.eclipse.collections.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

class AbstractRichIterableAssert_HasSizeGreaterThan_Test {
  @RichIterableParameterizedTest
  void passes(RichIterableAssertFactory<String> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").hasSizeGreaterThan(3));
  }

  @RichIterableParameterizedTest
  void failsEmpty(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromEmpty().hasSizeGreaterThan(3))
      .withMessageContaining(String.format("to be greater than %s but was 0", 3));
  }

  @RichIterableParameterizedTest
  void failsEquals(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").hasSizeGreaterThan(5))
      .withMessageContaining(String.format("to be greater than %s but was 5", 5));
  }

  @RichIterableParameterizedTest
  void failsLessThan(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").hasSizeGreaterThan(7))
      .withMessageContaining(String.format("to be greater than %s but was 5", 7));
  }

  @RichIterableParameterizedTest
  void softAssertionPasses(RichIterableAssertFactory<String> assertFactory) {
    SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromElements(softly, "TOS", "TNG", "DS9", "VOY", "ENT").hasSizeGreaterThan(2));
  }
}

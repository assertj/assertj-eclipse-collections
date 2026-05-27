package org.assertj.eclipse.collections.api.richiterable;

import org.assertj.eclipse.collections.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

class AbstractRichIterableAssert_HasSizeGreaterThanOrEqualTo_Test {
  @RichIterableParameterizedTest
  void passes(RichIterableAssertFactory<String> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").hasSizeGreaterThanOrEqualTo(3));
  }

  @RichIterableParameterizedTest
  void passesEquals(RichIterableAssertFactory<String> assertFactory) {
    assertThatNoException().isThrownBy(() ->
      assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").hasSizeGreaterThanOrEqualTo(5));
  }

  @RichIterableParameterizedTest
  void failsEmpty(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromEmpty().hasSizeGreaterThanOrEqualTo(3))
      .withMessageContaining(String.format("to be greater than or equal to %s but was 0", 3));
  }

  @RichIterableParameterizedTest
  void failsLessThan(RichIterableAssertFactory<String> assertFactory) {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> assertFactory.fromElements("TOS", "TNG", "DS9", "VOY", "ENT").hasSizeGreaterThanOrEqualTo(7))
      .withMessageContaining(String.format("to be greater than or equal to %s but was 5", 7));
  }

  @RichIterableParameterizedTest
  void softAssertionPasses(RichIterableAssertFactory<String> assertFactory) {
    SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromElements(softly, "TOS", "TNG", "DS9", "VOY", "ENT").hasSizeGreaterThanOrEqualTo(2));
  }
}

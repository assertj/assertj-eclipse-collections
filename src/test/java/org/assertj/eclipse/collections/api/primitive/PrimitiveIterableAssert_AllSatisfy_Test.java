/*
 * Copyright 2025-2026 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.assertj.eclipse.collections.api.primitive;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.assertj.eclipse.collections.api.BooleanIterableAssert;
import org.assertj.eclipse.collections.api.ByteIterableAssert;
import org.assertj.eclipse.collections.api.CharIterableAssert;
import org.assertj.eclipse.collections.api.DoubleIterableAssert;
import org.assertj.eclipse.collections.api.FloatIterableAssert;
import org.assertj.eclipse.collections.api.IntIterableAssert;
import org.assertj.eclipse.collections.api.LongIterableAssert;
import org.assertj.eclipse.collections.api.ShortIterableAssert;
import org.assertj.eclipse.collections.api.SoftAssertions;
import org.junit.jupiter.api.Nested;

class PrimitiveIterableAssert_AllSatisfy_Test {

  @Nested
  class BooleanTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void passes(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(true, true, true).allSatisfy(value -> assertThat(value).isTrue()));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void failsEmpty(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().allSatisfy(value -> assertThat(value).isTrue()))
        .withMessageContaining("Expecting actual not to be empty");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void fails(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(true, false, true).allSatisfy(value -> assertThat(value).isTrue()))
        .withMessageContaining("to satisfy given requirements, but these elements did not:");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void softAssertionPasses(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromSize(softly, 3).allSatisfy(value -> {
      }));
    }
  }

  @Nested
  class ByteTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void passes(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements((byte) 2, (byte) 4, (byte) 6).allSatisfy(value -> assertThat(value).isEven()));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void failsEmpty(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().allSatisfy(value -> assertThat(value).isEven()))
        .withMessageContaining("Expecting actual not to be empty");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void fails(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements((byte) 2, (byte) 3, (byte) 4).allSatisfy(value -> assertThat(value).isEven()))
        .withMessageContaining("to satisfy given requirements, but these elements did not:");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void softAssertionPasses(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromSize(softly, 3).allSatisfy(value -> assertThat(value).isGreaterThanOrEqualTo((byte) 0)));
    }
  }

  @Nested
  class CharTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void passes(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements('a', 'b', 'c').allSatisfy(value -> assertThat(value).isLowerCase()));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void failsEmpty(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().allSatisfy(value -> assertThat(value).isLowerCase()))
        .withMessageContaining("Expecting actual not to be empty");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void fails(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements('a', 'Z', 'c').allSatisfy(value -> assertThat(value).isLowerCase()))
        .withMessageContaining("to satisfy given requirements, but these elements did not:");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void softAssertionPasses(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromSize(softly, 3).allSatisfy(value -> assertThat(value).isGreaterThanOrEqualTo((char) 0)));
    }
  }

  @Nested
  class DoubleTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void passes(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(2.0, 4.0, 6.0).allSatisfy(value -> assertThat(value).isPositive()));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void failsEmpty(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().allSatisfy(value -> assertThat(value).isPositive()))
        .withMessageContaining("Expecting actual not to be empty");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void fails(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(2.0, -1.0, 4.0).allSatisfy(value -> assertThat(value).isPositive()))
        .withMessageContaining("to satisfy given requirements, but these elements did not:");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void softAssertionPasses(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromSize(softly, 3).allSatisfy(value -> assertThat(value).isGreaterThanOrEqualTo(0.0)));
    }
  }

  @Nested
  class FloatTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void passes(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(2.0f, 4.0f, 6.0f).allSatisfy(value -> assertThat(value).isPositive()));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void failsEmpty(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().allSatisfy(value -> assertThat(value).isPositive()))
        .withMessageContaining("Expecting actual not to be empty");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void fails(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(2.0f, -1.0f, 4.0f).allSatisfy(value -> assertThat(value).isPositive()))
        .withMessageContaining("to satisfy given requirements, but these elements did not:");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void softAssertionPasses(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromSize(softly, 3).allSatisfy(value -> assertThat(value).isGreaterThanOrEqualTo(0.0f)));
    }
  }

  @Nested
  class IntTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void passes(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(2, 4, 6).allSatisfy(value -> assertThat(value).isEven()));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void failsEmpty(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().allSatisfy(value -> assertThat(value).isEven()))
        .withMessageContaining("Expecting actual not to be empty");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void fails(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(2, 3, 4).allSatisfy(value -> assertThat(value).isEven()))
        .withMessageContaining("to satisfy given requirements, but these elements did not:");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void softAssertionPasses(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromSize(softly, 3).allSatisfy(value -> assertThat(value).isGreaterThanOrEqualTo(0)));
    }
  }

  @Nested
  class LongTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void passes(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(2L, 4L, 6L).allSatisfy(value -> assertThat(value).isEven()));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void failsEmpty(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().allSatisfy(value -> assertThat(value).isEven()))
        .withMessageContaining("Expecting actual not to be empty");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void fails(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(2L, 3L, 4L).allSatisfy(value -> assertThat(value).isEven()))
        .withMessageContaining("to satisfy given requirements, but these elements did not:");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void softAssertionPasses(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromSize(softly, 3).allSatisfy(value -> assertThat(value).isGreaterThanOrEqualTo(0L)));
    }
  }

  @Nested
  class ShortTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void passes(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements((short) 2, (short) 4, (short) 6).allSatisfy(value -> assertThat(value).isEven()));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void failsEmpty(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().allSatisfy(value -> assertThat(value).isEven()))
        .withMessageContaining("Expecting actual not to be empty");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void fails(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements((short) 2, (short) 3, (short) 4).allSatisfy(value -> assertThat(value).isEven()))
        .withMessageContaining("to satisfy given requirements, but these elements did not:");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void softAssertionPasses(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromSize(softly, 3).allSatisfy(value -> assertThat(value).isGreaterThanOrEqualTo((short) 0)));
    }
  }
}

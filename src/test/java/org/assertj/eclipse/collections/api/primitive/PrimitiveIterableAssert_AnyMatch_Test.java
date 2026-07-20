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

class PrimitiveIterableAssert_AnyMatch_Test {

  @Nested
  class BooleanTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void passes(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(false, true, false).anyMatch(value -> value));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void failsEmpty(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().anyMatch(value -> value))
        .withMessageContaining("to match given predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void fails(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(false, false, false).anyMatch(value -> value))
        .withMessageContaining("to match given predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void passesWithDescription(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(false, true, false).anyMatch(value -> value, "is true"));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void failsWithDescription(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(false, false, false).anyMatch(value -> value, "is true"))
        .withMessageContaining("to match 'is true' predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void softAssertionPasses(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromSize(softly, 3).anyMatch(value -> true));
    }
  }

  @Nested
  class ByteTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void passes(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements((byte) 1, (byte) 2, (byte) 3).anyMatch(value -> value % 2 == 0));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void failsEmpty(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().anyMatch(value -> value % 2 == 0))
        .withMessageContaining("to match given predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void fails(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements((byte) 1, (byte) 3, (byte) 5).anyMatch(value -> value % 2 == 0))
        .withMessageContaining("to match given predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void passesWithDescription(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements((byte) 1, (byte) 2, (byte) 3).anyMatch(value -> value % 2 == 0, "is even"));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void failsWithDescription(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements((byte) 1, (byte) 3, (byte) 5).anyMatch(value -> value % 2 == 0, "is even"))
        .withMessageContaining("to match 'is even' predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void softAssertionPasses(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromSize(softly, 3).anyMatch(value -> value >= 0));
    }
  }

  @Nested
  class CharTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void passes(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements('a', 'B', 'c').anyMatch(value -> value >= 'A' && value <= 'Z'));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void failsEmpty(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().anyMatch(value -> value >= 'A' && value <= 'Z'))
        .withMessageContaining("to match given predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void fails(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements('a', 'b', 'c').anyMatch(value -> value >= 'A' && value <= 'Z'))
        .withMessageContaining("to match given predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void passesWithDescription(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements('a', 'B', 'c').anyMatch(value -> value >= 'A' && value <= 'Z', "is uppercase"));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void failsWithDescription(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements('a', 'b', 'c').anyMatch(value -> value >= 'A' && value <= 'Z', "is uppercase"))
        .withMessageContaining("to match 'is uppercase' predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void softAssertionPasses(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromSize(softly, 3).anyMatch(value -> value >= 0));
    }
  }

  @Nested
  class DoubleTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void passes(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(-1.0, 2.0, -3.0).anyMatch(value -> value > 0));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void failsEmpty(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().anyMatch(value -> value > 0))
        .withMessageContaining("to match given predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void fails(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(-1.0, -2.0, -3.0).anyMatch(value -> value > 0))
        .withMessageContaining("to match given predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void passesWithDescription(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(-1.0, 2.0, -3.0).anyMatch(value -> value > 0, "is positive"));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void failsWithDescription(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(-1.0, -2.0, -3.0).anyMatch(value -> value > 0, "is positive"))
        .withMessageContaining("to match 'is positive' predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void softAssertionPasses(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromSize(softly, 3).anyMatch(value -> value >= 0));
    }
  }

  @Nested
  class FloatTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void passes(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(-1.0f, 2.0f, -3.0f).anyMatch(value -> value > 0));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void failsEmpty(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().anyMatch(value -> value > 0))
        .withMessageContaining("to match given predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void fails(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(-1.0f, -2.0f, -3.0f).anyMatch(value -> value > 0))
        .withMessageContaining("to match given predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void passesWithDescription(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(-1.0f, 2.0f, -3.0f).anyMatch(value -> value > 0, "is positive"));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void failsWithDescription(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(-1.0f, -2.0f, -3.0f).anyMatch(value -> value > 0, "is positive"))
        .withMessageContaining("to match 'is positive' predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void softAssertionPasses(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromSize(softly, 3).anyMatch(value -> value >= 0));
    }
  }

  @Nested
  class IntTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void passes(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1, 2, 3).anyMatch(value -> value % 2 == 0));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void failsEmpty(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().anyMatch(value -> value % 2 == 0))
        .withMessageContaining("to match given predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void fails(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1, 3, 5).anyMatch(value -> value % 2 == 0))
        .withMessageContaining("to match given predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void passesWithDescription(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1, 2, 3).anyMatch(value -> value % 2 == 0, "is even"));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void failsWithDescription(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1, 3, 5).anyMatch(value -> value % 2 == 0, "is even"))
        .withMessageContaining("to match 'is even' predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void softAssertionPasses(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromSize(softly, 3).anyMatch(value -> value >= 0));
    }
  }

  @Nested
  class LongTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void passes(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1L, 2L, 3L).anyMatch(value -> value % 2 == 0));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void failsEmpty(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().anyMatch(value -> value % 2 == 0))
        .withMessageContaining("to match given predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void fails(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1L, 3L, 5L).anyMatch(value -> value % 2 == 0))
        .withMessageContaining("to match given predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void passesWithDescription(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1L, 2L, 3L).anyMatch(value -> value % 2 == 0, "is even"));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void failsWithDescription(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1L, 3L, 5L).anyMatch(value -> value % 2 == 0, "is even"))
        .withMessageContaining("to match 'is even' predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void softAssertionPasses(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromSize(softly, 3).anyMatch(value -> value >= 0));
    }
  }

  @Nested
  class ShortTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void passes(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements((short) 1, (short) 2, (short) 3).anyMatch(value -> value % 2 == 0));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void failsEmpty(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().anyMatch(value -> value % 2 == 0))
        .withMessageContaining("to match given predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void fails(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements((short) 1, (short) 3, (short) 5).anyMatch(value -> value % 2 == 0))
        .withMessageContaining("to match given predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void passesWithDescription(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements((short) 1, (short) 2, (short) 3).anyMatch(value -> value % 2 == 0, "is even"));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void failsWithDescription(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements((short) 1, (short) 3, (short) 5).anyMatch(value -> value % 2 == 0, "is even"))
        .withMessageContaining("to match 'is even' predicate but none did.");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void softAssertionPasses(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      SoftAssertions.assertSoftly(softly -> assertFactory.softlyFromSize(softly, 3).anyMatch(value -> value >= 0));
    }
  }
}

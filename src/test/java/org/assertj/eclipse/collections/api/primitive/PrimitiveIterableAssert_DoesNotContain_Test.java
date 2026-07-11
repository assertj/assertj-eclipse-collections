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
import org.junit.jupiter.api.Nested;

class PrimitiveIterableAssert_DoesNotContain_Test {

  @Nested
  class BooleanTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void passes(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(false, false, false).doesNotContain(true));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void passesEmpty(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromEmpty().doesNotContain(true));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void fails(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(false, false, false).doesNotContain(false))
        .withMessageContaining("not to contain")
        .withMessageContaining("but found");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void failsNull(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromNull().doesNotContain(true))
        .withMessageContaining("Expecting actual not to be null");
    }
  }

  @Nested
  class ByteTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void passes(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements((byte) 1, (byte) 2, (byte) 3).doesNotContain((byte) 99));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void passesMultipleValues(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements((byte) 1, (byte) 2, (byte) 3).doesNotContain((byte) 97, (byte) 98, (byte) 99));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void passesEmpty(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromEmpty().doesNotContain((byte) 1));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void fails(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements((byte) 1, (byte) 2, (byte) 3).doesNotContain((byte) 2))
        .withMessageContaining("not to contain")
        .withMessageContaining("but found");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void failsWithMultipleValues(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements((byte) 1, (byte) 2, (byte) 3).doesNotContain((byte) 99, (byte) 2))
        .withMessageContaining("not to contain")
        .withMessageContaining("but found");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void failsNull(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromNull().doesNotContain((byte) 1))
        .withMessageContaining("Expecting actual not to be null");
    }
  }

  @Nested
  class CharTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void passes(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements('a', 'b', 'c').doesNotContain('z'));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void passesMultipleValues(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements('a', 'b', 'c').doesNotContain('x', 'y', 'z'));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void passesEmpty(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromEmpty().doesNotContain('a'));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void fails(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements('a', 'b', 'c').doesNotContain('b'))
        .withMessageContaining("not to contain")
        .withMessageContaining("but found");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void failsWithMultipleValues(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements('a', 'b', 'c').doesNotContain('z', 'b'))
        .withMessageContaining("not to contain")
        .withMessageContaining("but found");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void failsNull(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromNull().doesNotContain('a'))
        .withMessageContaining("Expecting actual not to be null");
    }
  }

  @Nested
  class DoubleTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void passes(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1.0, 2.0, 3.0).doesNotContain(99.0));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void passesMultipleValues(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1.0, 2.0, 3.0).doesNotContain(97.0, 98.0, 99.0));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void passesEmpty(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromEmpty().doesNotContain(1.0));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void fails(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1.0, 2.0, 3.0).doesNotContain(2.0))
        .withMessageContaining("not to contain")
        .withMessageContaining("but found");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void failsWithMultipleValues(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1.0, 2.0, 3.0).doesNotContain(99.0, 2.0))
        .withMessageContaining("not to contain")
        .withMessageContaining("but found");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void failsNull(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromNull().doesNotContain(1.0))
        .withMessageContaining("Expecting actual not to be null");
    }
  }

  @Nested
  class FloatTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void passes(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1.0f, 2.0f, 3.0f).doesNotContain(99.0f));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void passesMultipleValues(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1.0f, 2.0f, 3.0f).doesNotContain(97.0f, 98.0f, 99.0f));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void passesEmpty(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromEmpty().doesNotContain(1.0f));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void fails(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1.0f, 2.0f, 3.0f).doesNotContain(2.0f))
        .withMessageContaining("not to contain")
        .withMessageContaining("but found");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void failsWithMultipleValues(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1.0f, 2.0f, 3.0f).doesNotContain(99.0f, 2.0f))
        .withMessageContaining("not to contain")
        .withMessageContaining("but found");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void failsNull(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromNull().doesNotContain(1.0f))
        .withMessageContaining("Expecting actual not to be null");
    }
  }

  @Nested
  class IntTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void passes(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1, 2, 3).doesNotContain(99));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void passesMultipleValues(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1, 2, 3).doesNotContain(97, 98, 99));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void passesEmpty(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromEmpty().doesNotContain(1));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void fails(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1, 2, 3).doesNotContain(2))
        .withMessageContaining("not to contain")
        .withMessageContaining("but found");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void failsWithMultipleValues(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1, 2, 3).doesNotContain(99, 2))
        .withMessageContaining("not to contain")
        .withMessageContaining("but found");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void failsNull(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromNull().doesNotContain(1))
        .withMessageContaining("Expecting actual not to be null");
    }
  }

  @Nested
  class LongTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void passes(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1L, 2L, 3L).doesNotContain(99L));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void passesMultipleValues(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1L, 2L, 3L).doesNotContain(97L, 98L, 99L));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void passesEmpty(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromEmpty().doesNotContain(1L));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void fails(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1L, 2L, 3L).doesNotContain(2L))
        .withMessageContaining("not to contain")
        .withMessageContaining("but found");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void failsWithMultipleValues(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1L, 2L, 3L).doesNotContain(99L, 2L))
        .withMessageContaining("not to contain")
        .withMessageContaining("but found");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void failsNull(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromNull().doesNotContain(1L))
        .withMessageContaining("Expecting actual not to be null");
    }
  }

  @Nested
  class ShortTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void passes(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements((short) 1, (short) 2, (short) 3).doesNotContain((short) 99));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void passesMultipleValues(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements((short) 1, (short) 2, (short) 3).doesNotContain((short) 97, (short) 98, (short) 99));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void passesEmpty(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromEmpty().doesNotContain((short) 1));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void fails(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements((short) 1, (short) 2, (short) 3).doesNotContain((short) 2))
        .withMessageContaining("not to contain")
        .withMessageContaining("but found");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void failsWithMultipleValues(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements((short) 1, (short) 2, (short) 3).doesNotContain((short) 99, (short) 2))
        .withMessageContaining("not to contain")
        .withMessageContaining("but found");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void failsNull(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromNull().doesNotContain((short) 1))
        .withMessageContaining("Expecting actual not to be null");
    }
  }
}

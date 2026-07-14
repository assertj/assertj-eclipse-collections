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

class PrimitiveIterableAssert_ContainsAnyOf_Test {

  @Nested
  class BooleanTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void passes(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(true, false, false).containsAnyOf(true));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void passesBothEmpty(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements().containsAnyOf());
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void passesWithSomeMissing(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(false, false, false).containsAnyOf(true, false));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void fails(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(false, false, false).containsAnyOf(true))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void failsEmpty(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().containsAnyOf(true))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void failsNull(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromNull().containsAnyOf(true))
        .withMessageContaining("Expecting actual not to be null");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void failsNullInput(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatExceptionOfType(NullPointerException.class)
        .isThrownBy(() -> assertFactory.fromElements(true, false, false).containsAnyOf((boolean[]) null))
        .withMessageContaining("The array of values to look for should not be null");
    }
  }

  @Nested
  class ByteTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void passes(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements((byte) 1, (byte) 2, (byte) 3).containsAnyOf((byte) 2));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void passesBothEmpty(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements().containsAnyOf());
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void passesMultipleValues(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements((byte) 1, (byte) 2, (byte) 3).containsAnyOf((byte) 1, (byte) 2, (byte) 3));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void passesWithSomeMissing(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements((byte) 1, (byte) 2, (byte) 3).containsAnyOf((byte) 99, (byte) 2, (byte) 98));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void fails(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements((byte) 1, (byte) 2, (byte) 3).containsAnyOf((byte) 99))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void failsWithMultipleValues(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements((byte) 1, (byte) 2, (byte) 3).containsAnyOf((byte) 97, (byte) 98, (byte) 99))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void failsEmpty(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().containsAnyOf((byte) 1))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void failsNull(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromNull().containsAnyOf((byte) 1))
        .withMessageContaining("Expecting actual not to be null");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void failsNullInput(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatExceptionOfType(NullPointerException.class)
        .isThrownBy(() -> assertFactory.fromElements((byte) 1, (byte) 2, (byte) 3).containsAnyOf((byte[]) null))
        .withMessageContaining("The array of values to look for should not be null");
    }
  }

  @Nested
  class CharTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void passes(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements('a', 'b', 'c').containsAnyOf('b'));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void passesBothEmpty(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements().containsAnyOf());
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void passesMultipleValues(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements('a', 'b', 'c').containsAnyOf('a', 'b', 'c'));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void passesWithSomeMissing(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements('a', 'b', 'c').containsAnyOf('z', 'b', 'y'));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void fails(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements('a', 'b', 'c').containsAnyOf('z'))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void failsWithMultipleValues(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements('a', 'b', 'c').containsAnyOf('x', 'y', 'z'))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void failsEmpty(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().containsAnyOf('a'))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void failsNull(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromNull().containsAnyOf('a'))
        .withMessageContaining("Expecting actual not to be null");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void failsNullInput(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatExceptionOfType(NullPointerException.class)
        .isThrownBy(() -> assertFactory.fromElements('a', 'b', 'c').containsAnyOf((char[]) null))
        .withMessageContaining("The array of values to look for should not be null");
    }
  }

  @Nested
  class DoubleTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void passes(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1.0, 2.0, 3.0).containsAnyOf(2.0));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void passesBothEmpty(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements().containsAnyOf());
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void passesMultipleValues(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1.0, 2.0, 3.0).containsAnyOf(1.0, 2.0, 3.0));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void passesWithSomeMissing(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1.0, 2.0, 3.0).containsAnyOf(99.0, 2.0, 98.0));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void fails(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1.0, 2.0, 3.0).containsAnyOf(99.0))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void failsWithMultipleValues(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1.0, 2.0, 3.0).containsAnyOf(97.0, 98.0, 99.0))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void failsEmpty(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().containsAnyOf(1.0))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void failsNull(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromNull().containsAnyOf(1.0))
        .withMessageContaining("Expecting actual not to be null");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void failsNullInput(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatExceptionOfType(NullPointerException.class)
        .isThrownBy(() -> assertFactory.fromElements(1.0, 2.0, 3.0).containsAnyOf((double[]) null))
        .withMessageContaining("The array of values to look for should not be null");
    }
  }

  @Nested
  class FloatTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void passes(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1.0f, 2.0f, 3.0f).containsAnyOf(2.0f));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void passesBothEmpty(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements().containsAnyOf());
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void passesMultipleValues(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1.0f, 2.0f, 3.0f).containsAnyOf(1.0f, 2.0f, 3.0f));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void passesWithSomeMissing(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1.0f, 2.0f, 3.0f).containsAnyOf(99.0f, 2.0f, 98.0f));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void fails(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1.0f, 2.0f, 3.0f).containsAnyOf(99.0f))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void failsWithMultipleValues(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1.0f, 2.0f, 3.0f).containsAnyOf(97.0f, 98.0f, 99.0f))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void failsEmpty(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().containsAnyOf(1.0f))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void failsNull(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromNull().containsAnyOf(1.0f))
        .withMessageContaining("Expecting actual not to be null");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void failsNullInput(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatExceptionOfType(NullPointerException.class)
        .isThrownBy(() -> assertFactory.fromElements(1.0f, 2.0f, 3.0f).containsAnyOf((float[]) null))
        .withMessageContaining("The array of values to look for should not be null");
    }
  }

  @Nested
  class IntTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void passes(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1, 2, 3).containsAnyOf(2));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void passesBothEmpty(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements().containsAnyOf());
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void passesMultipleValues(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1, 2, 3).containsAnyOf(1, 2, 3));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void passesWithSomeMissing(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1, 2, 3).containsAnyOf(99, 2, 98));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void fails(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1, 2, 3).containsAnyOf(99))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void failsWithMultipleValues(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1, 2, 3).containsAnyOf(97, 98, 99))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void failsEmpty(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().containsAnyOf(1))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void failsNull(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromNull().containsAnyOf(1))
        .withMessageContaining("Expecting actual not to be null");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void failsNullInput(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatExceptionOfType(NullPointerException.class)
        .isThrownBy(() -> assertFactory.fromElements(1, 2, 3).containsAnyOf((int[]) null))
        .withMessageContaining("The array of values to look for should not be null");
    }
  }

  @Nested
  class LongTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void passes(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1L, 2L, 3L).containsAnyOf(2L));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void passesBothEmpty(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements().containsAnyOf());
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void passesMultipleValues(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1L, 2L, 3L).containsAnyOf(1L, 2L, 3L));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void passesWithSomeMissing(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1L, 2L, 3L).containsAnyOf(99L, 2L, 98L));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void fails(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1L, 2L, 3L).containsAnyOf(99L))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void failsWithMultipleValues(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1L, 2L, 3L).containsAnyOf(97L, 98L, 99L))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void failsEmpty(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().containsAnyOf(1L))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void failsNull(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromNull().containsAnyOf(1L))
        .withMessageContaining("Expecting actual not to be null");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void failsNullInput(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatExceptionOfType(NullPointerException.class)
        .isThrownBy(() -> assertFactory.fromElements(1L, 2L, 3L).containsAnyOf((long[]) null))
        .withMessageContaining("The array of values to look for should not be null");
    }
  }

  @Nested
  class ShortTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void passes(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements((short) 1, (short) 2, (short) 3).containsAnyOf((short) 2));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void passesBothEmpty(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements().containsAnyOf());
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void passesMultipleValues(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements((short) 1, (short) 2, (short) 3).containsAnyOf((short) 1, (short) 2, (short) 3));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void passesWithSomeMissing(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements((short) 1, (short) 2, (short) 3).containsAnyOf((short) 99, (short) 2, (short) 98));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void fails(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements((short) 1, (short) 2, (short) 3).containsAnyOf((short) 99))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void failsWithMultipleValues(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements((short) 1, (short) 2, (short) 3).containsAnyOf((short) 97, (short) 98, (short) 99))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void failsEmpty(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromEmpty().containsAnyOf((short) 1))
        .withMessageContaining("to contain at least one of the following elements");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void failsNull(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromNull().containsAnyOf((short) 1))
        .withMessageContaining("Expecting actual not to be null");
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void failsNullInput(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatExceptionOfType(NullPointerException.class)
        .isThrownBy(() -> assertFactory.fromElements((short) 1, (short) 2, (short) 3).containsAnyOf((short[]) null))
        .withMessageContaining("The array of values to look for should not be null");
    }
  }
}

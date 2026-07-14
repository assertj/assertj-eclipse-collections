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

class PrimitiveIterableAssert_Contains_Test {

  @Nested
  class BooleanTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void passes(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(true, false, false).contains(true));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void passesBothEmpty(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements().contains());
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BOOLEAN)
    void fails(PrimitiveIterableAssertFactory<BooleanIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(false, false, false).contains(true))
        .withMessageContaining("to contain");
    }
  }

  @Nested
  class ByteTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void passes(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements((byte) 1, (byte) 2, (byte) 3).contains((byte) 2));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void passesBothEmpty(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements().contains());
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.BYTE)
    void fails(PrimitiveIterableAssertFactory<ByteIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements((byte) 1).contains((byte) 99))
        .withMessageContaining("to contain");
    }
  }

  @Nested
  class CharTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void passes(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements('a', 'b', 'c').contains('b'));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void passesBothEmpty(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements().contains());
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.CHAR)
    void fails(PrimitiveIterableAssertFactory<CharIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements('a').contains('z'))
        .withMessageContaining("to contain");
    }
  }

  @Nested
  class DoubleTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void passes(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1.0, 2.0, 3.0).contains(2.0));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void passesBothEmpty(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements().contains());
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.DOUBLE)
    void fails(PrimitiveIterableAssertFactory<DoubleIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1.0).contains(99.0))
        .withMessageContaining("to contain");
    }
  }

  @Nested
  class FloatTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void passes(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1.0f, 2.0f, 3.0f).contains(2.0f));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void passesBothEmpty(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements().contains());
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.FLOAT)
    void fails(PrimitiveIterableAssertFactory<FloatIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1.0f).contains(99.0f))
        .withMessageContaining("to contain");
    }
  }

  @Nested
  class IntTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void passes(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1, 2, 3).contains(2));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void passesBothEmpty(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements().contains());
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.INT)
    void fails(PrimitiveIterableAssertFactory<IntIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1).contains(99))
        .withMessageContaining("to contain");
    }
  }

  @Nested
  class LongTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void passes(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements(1L, 2L, 3L).contains(2L));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void passesBothEmpty(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements().contains());
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.LONG)
    void fails(PrimitiveIterableAssertFactory<LongIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements(1L).contains(99L))
        .withMessageContaining("to contain");
    }
  }

  @Nested
  class ShortTest {
    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void passes(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements((short) 1, (short) 2, (short) 3).contains((short) 2));
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void passesBothEmpty(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatNoException().isThrownBy(() ->
        assertFactory.fromElements().contains());
    }

    @PrimitiveIterableParameterizedTest(type = PrimitiveType.SHORT)
    void fails(PrimitiveIterableAssertFactory<ShortIterableAssert> assertFactory) {
      assertThatExceptionOfType(AssertionError.class)
        .isThrownBy(() -> assertFactory.fromElements((short) 1).contains((short) 99))
        .withMessageContaining("to contain");
    }
  }
}

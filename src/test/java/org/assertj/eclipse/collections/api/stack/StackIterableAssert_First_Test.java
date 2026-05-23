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
package org.assertj.eclipse.collections.api.stack;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.eclipse.collections.api.StackIterableAssert;
import org.eclipse.collections.api.factory.Stacks;
import org.eclipse.collections.api.stack.ImmutableStack;
import org.junit.jupiter.api.Test;

public class StackIterableAssert_First_Test {
  @Test
  void passes() {
    assertThatNoException().isThrownBy(() -> {
      ImmutableStack<String> list = Stacks.immutable.of("TOS", "TNG", "DS9", "VOY", "ENT");
      new StackIterableAssert<>(list).first().isEqualTo("ENT");
    });
  }

  @Test
  void passesWithCustomAssertFactory() {
    assertThatNoException().isThrownBy(() -> {
      ImmutableStack<String> list = Stacks.immutable.of("TOS", "TNG", "DS9", "VOY", "ENT");
      new StackIterableAssert<>(list).first(InstanceOfAssertFactories.STRING).startsWith("E");
    });
  }

  @Test
  void throwsExceptionWhenActualIsNull() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() ->
      new StackIterableAssert<>(null).first().isEqualTo("ENT")
    ).withMessageContaining("Expecting actual not to be null");
  }

  @Test
  void throwsExceptionWhenFirstElementIsNull() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> {
      ImmutableStack<String> list = Stacks.immutable.of("TOS", "TNG", "DS9", "VOY", null);
      new StackIterableAssert<>(list).first().isEqualTo("ENT");
    }).withMessageContaining("check first element")
      .withMessageContaining("but was: null");
  }
}

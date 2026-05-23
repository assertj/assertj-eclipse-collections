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
package org.assertj.eclipse.collections.api.list;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.eclipse.collections.api.ListIterableAssert;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.junit.jupiter.api.Test;

class ListIterableAssert_First_Test {
  @Test
  void passes() {
    assertThatNoException().isThrownBy(() -> {
      ImmutableList<String> list = Lists.immutable.of("TOS", "TNG", "DS9", "VOY", "ENT");
      new ListIterableAssert<>(list).first().isEqualTo("TOS");
    });
  }

  @Test
  void passesWithCustomAssertFactory() {
    assertThatNoException().isThrownBy(() -> {
      ImmutableList<String> list = Lists.immutable.of("TOS", "TNG", "DS9", "VOY", "ENT");
      new ListIterableAssert<>(list).first(InstanceOfAssertFactories.STRING).startsWith("T");
    });
  }

  @Test
  void throwsExceptionWhenActualIsNull() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() ->
      new ListIterableAssert<>(null).first().isEqualTo("TOS")
    ).withMessageContaining("Expecting actual not to be null");
  }
}

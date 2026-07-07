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
package org.assertj.eclipse.collections.api.bag;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.assertj.eclipse.collections.api.BagAssert;
import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.factory.Bags;
import org.junit.jupiter.api.Test;

class BagAssert_HasDistinctSizeLessThan_Test {
  @Test
  void passes() {
    ImmutableBag<String> bag = createBag();
    assertThatNoException().isThrownBy(() -> new BagAssert<>(bag).hasDistinctSizeLessThan(5));
  }

  @Test
  void passesEmpty() {
    ImmutableBag<String> bag = Bags.immutable.empty();
    assertThatNoException().isThrownBy(() -> new BagAssert<>(bag).hasDistinctSizeLessThan(3));
  }

  @Test
  void failsEquals() {
    ImmutableBag<String> bag = createBag();
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new BagAssert<>(bag).hasDistinctSizeLessThan(3))
      .withMessageContaining(String.format("to be less than %s but was 3", 3));
  }

  @Test
  void failsGreaterThan() {
    ImmutableBag<String> bag = createBag();
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new BagAssert<>(bag).hasDistinctSizeLessThan(2))
      .withMessageContaining(String.format("to be less than %s but was 3", 2));
  }

  @Test
  void failsNullInput() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> new BagAssert<>(null).hasDistinctSizeLessThan(3))
      .withMessageContaining("Expecting actual not to be null");
  }

  @Test
  void softAssertionPasses() {
    ImmutableBag<String> bag = createBag();
    SoftAssertions.assertSoftly(softly -> softly.assertThat(bag).hasDistinctSizeLessThan(5));
  }

  private static ImmutableBag<String> createBag() {
    return Bags.immutable.of("TOS", "TOS", "TNG", "DS9", "DS9");
  }
}

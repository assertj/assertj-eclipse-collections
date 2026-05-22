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
package org.assertj.eclipse.collections.api;

import org.assertj.core.annotation.CheckReturnValue;
import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.set.SetIterable;
import org.eclipse.collections.api.stack.StackIterable;

/**
 * Entry point for assertion methods for the Eclipse Collections library. Each method in this class is a static factory
 * for a type-specific assertion object.
 */
@CheckReturnValue
public class Assertions {
  /**
   * Creates a new {@link Assertions}.
   */
  protected Assertions() {
    // Do nothing
  }

  /**
   * Creates a new instance of {@link BagAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   * @param <T> The type of the elements in the bag
   */
  public static <T> BagAssert<T> assertThat(Bag<T> actual) {
    return new BagAssert<>(actual);
  }

  public static <T> ListIterableAssert<T> assertThat(ListIterable<T> actual) {
    return new ListIterableAssert<>(actual);
  }

  /**
   * Creates a new instance of {@link MultimapAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   * @param <KEY> The type of keys in the BagMultimap
   * @param <VALUE> The type of values in the BagMultimap
   */
  public static <KEY, VALUE> MultimapAssert<KEY, VALUE> assertThat(Multimap<KEY, VALUE> actual) {
    return new MultimapAssert<>(actual);
  }

  /**
   * Creates a new instance of {@link SetIterableAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   * @param <T> The type of the elements in the set
   */
  public static <T> SetIterableAssert<T> assertThat(SetIterable<T> actual) {
    return new SetIterableAssert<>(actual);
  }

  public static <T> StackIterableAssert<T> assertThat(StackIterable<T> actual) {
    return new StackIterableAssert<>(actual);
  }
}

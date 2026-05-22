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
import org.assertj.core.api.SoftAssertionsProvider;
import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.set.SetIterable;
import org.eclipse.collections.api.stack.StackIterable;

/**
 * Soft assertions implementations for Eclipse Collections types.
 */
@CheckReturnValue
public interface EclipseCollectionsSoftAssertionsProvider extends SoftAssertionsProvider {

  /**
   * Create a new, proxied instance of a {@link BagAssert}
   *
   * @param actual the actual value
   * @return the created assertion object
   * @param <T> The type of the elements in the bag
   */
  @SuppressWarnings("unchecked")
  default <T> BagAssert<T> assertThat(Bag<T> actual) {
    return this.proxy(BagAssert.class, Bag.class, actual);
  }

  @SuppressWarnings("unchecked")
  default <T> ListIterableAssert<T> assertThat(ListIterable<T> actual) {
    return this.proxy(ListIterableAssert.class, ListIterable.class, actual);
  }

  /**
   * Creates a new, proxied instance of a {@link MultimapAssert}
   *
   * @param actual the path
   * @return the created assertion object
   * @param <KEY> The type of keys in the actual BagMultimap
   * @param <VALUE> The type of values in the actual BagMultimap
   */
  @SuppressWarnings("unchecked")
  default <KEY, VALUE> MultimapAssert<KEY, VALUE> assertThat(Multimap<KEY, VALUE> actual) {
    return this.proxy(MultimapAssert.class, Multimap.class, actual);
  }

  /**
   * Creates a new, proxied instance of a {@link SetIterableAssert}
   *
   * @param actual the actual value
   * @return the created assertion object
   * @param <T> The type of the elements in the set
   */
  @SuppressWarnings("unchecked")
  default <T> SetIterableAssert<T> assertThat(SetIterable<T> actual) {
    return this.proxy(SetIterableAssert.class, SetIterable.class, actual);
  }

  @SuppressWarnings("unchecked")
  default <T> StackIterableAssert<T> assertThat(StackIterable<T> actual) {
    return this.proxy(StackIterableAssert.class, StackIterable.class, actual);
  }
}

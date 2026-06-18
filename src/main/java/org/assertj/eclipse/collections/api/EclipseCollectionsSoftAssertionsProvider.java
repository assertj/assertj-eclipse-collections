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
import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.ByteIterable;
import org.eclipse.collections.api.CharIterable;
import org.eclipse.collections.api.DoubleIterable;
import org.eclipse.collections.api.FloatIterable;
import org.eclipse.collections.api.IntIterable;
import org.eclipse.collections.api.LongIterable;
import org.eclipse.collections.api.ShortIterable;
import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.set.SetIterable;
import org.eclipse.collections.api.stack.StackIterable;

/**
 * Soft assertions implementations for Eclipse Collections types.
 */
public interface EclipseCollectionsSoftAssertionsProvider extends SoftAssertionsProvider {

  /**
   * Create a new instance of a {@link BagAssert}
   *
   * @param actual the actual value
   * @return the created assertion object
   * @param <T> The type of the elements in the bag
   */
  @CheckReturnValue
  default <T> BagAssert<T> assertThat(Bag<T> actual) {
    return soft(Assertions.assertThat(actual));
  }

  /**
   * Create a new instance of a {@link BooleanIterableAssert}
   *
   * @param actual the actual value
   * @return the created assertion object
   */
  @CheckReturnValue
  default BooleanIterableAssert assertThat(BooleanIterable actual) {
    return soft(Assertions.assertThat(actual));
  }

  /**
   * Create a new instance of a {@link ByteIterableAssert}
   *
   * @param actual the actual value
   * @return the created assertion object
   */
  @CheckReturnValue
  default ByteIterableAssert assertThat(ByteIterable actual) {
    return soft(Assertions.assertThat(actual));
  }

  /**
   * Create a new instance of a {@link CharIterableAssert}
   *
   * @param actual the actual value
   * @return the created assertion object
   */
  @CheckReturnValue
  default CharIterableAssert assertThat(CharIterable actual) {
    return soft(Assertions.assertThat(actual));
  }

  /**
   * Create a new instance of a {@link DoubleIterableAssert}
   *
   * @param actual the actual value
   * @return the created assertion object
   */
  @CheckReturnValue
  default DoubleIterableAssert assertThat(DoubleIterable actual) {
    return soft(Assertions.assertThat(actual));
  }

  /**
   * Create a new instance of a {@link FloatIterableAssert}
   *
   * @param actual the actual value
   * @return the created assertion object
   */
  @CheckReturnValue
  default FloatIterableAssert assertThat(FloatIterable actual) {
    return soft(Assertions.assertThat(actual));
  }

  /**
   * Create a new instance of a {@link IntIterableAssert}
   *
   * @param actual the actual value
   * @return the created assertion object
   */
  @CheckReturnValue
  default IntIterableAssert assertThat(IntIterable actual) {
    return soft(Assertions.assertThat(actual));
  }

  /**
   * Creates a new instance of a {@link ListIterableAssert}
   *
   * @param actual the actual value
   * @return the created assertion object
   * @param <T> The type of the elements in the list
   */
  @CheckReturnValue
  default <T> ListIterableAssert<T> assertThat(ListIterable<T> actual) {
    return soft(Assertions.assertThat(actual));
  }

  /**
   * Creates a new instance of a {@link LongIterableAssert}
   *
   * @param actual the actual value
   * @return the created assertion object
   */
  @CheckReturnValue
  default LongIterableAssert assertThat(LongIterable actual) {
    return soft(Assertions.assertThat(actual));
  }

  /**
   * Creates a new instance of a {@link MultimapAssert}
   *
   * @param actual the path
   * @return the created assertion object
   * @param <KEY> The type of keys in the actual BagMultimap
   * @param <VALUE> The type of values in the actual BagMultimap
   */
  @CheckReturnValue
  default <KEY, VALUE> MultimapAssert<KEY, VALUE> assertThat(Multimap<KEY, VALUE> actual) {
    return soft(Assertions.assertThat(actual));
  }

  /**
   * Creates a new instance of a {@link SetIterableAssert}
   *
   * @param actual the actual value
   * @return the created assertion object
   * @param <T> The type of the elements in the set
   */
  @CheckReturnValue
  default <T> SetIterableAssert<T> assertThat(SetIterable<T> actual) {
    return soft(Assertions.assertThat(actual));
  }

  /**
   * Creates a new instance of a {@link ShortIterableAssert}
   *
   * @param actual the actual value
   * @return the created assertion object
   */
  @CheckReturnValue
  default ShortIterableAssert assertThat(ShortIterable actual) {
    return soft(Assertions.assertThat(actual));
  }

  /**
   * Creates a new instance of a {@link StackIterableAssert}
   *
   * @param actual the actual value
   * @return the created assertion object
   * @param <T> The type of the elements in the stack
   */
  @CheckReturnValue
  default <T> StackIterableAssert<T> assertThat(StackIterable<T> actual) {
    return soft(Assertions.assertThat(actual));
  }
}

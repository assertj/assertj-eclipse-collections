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
import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.api.ByteIterable;
import org.eclipse.collections.api.CharIterable;
import org.eclipse.collections.api.DoubleIterable;
import org.eclipse.collections.api.FloatIterable;
import org.eclipse.collections.api.IntIterable;
import org.eclipse.collections.api.LongIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.ShortIterable;
import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.set.SetIterable;
import org.eclipse.collections.api.stack.StackIterable;

/**
 * Behavior-driven development style entry point for assertion methods for the Eclipse Collections library. Each method
 * in this class is a static factory for a type-specific assertion object.
 */
public class BDDAssertions extends Assertions {
  /**
   * Creates a new <code>{@link BDDAssertions}</code>.
   */
  protected BDDAssertions() {
    // Do nothing
  }

  /**
   * Creates a new instance of {@link BagAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   * @param <T> The type of the elements in the bag
   */
  @CheckReturnValue
  public static <T> BagAssert<T> then(Bag<? extends T> actual) {
    return assertThat(actual);
  }

  /**
   * Creates a new instance of {@link BooleanIterableAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static BooleanIterableAssert then(BooleanIterable actual) {
    return assertThat(actual);
  }

  /**
   * Creates a new instance of {@link ByteIterableAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static ByteIterableAssert then(ByteIterable actual) {
    return assertThat(actual);
  }

  /**
   * Creates a new instance of {@link CharIterableAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static CharIterableAssert then(CharIterable actual) {
    return assertThat(actual);
  }

  /**
   * Creates a new instance of {@link DoubleIterableAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static DoubleIterableAssert then(DoubleIterable actual) {
    return assertThat(actual);
  }

  /**
   * Creates a new instance of {@link FloatIterableAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static FloatIterableAssert then(FloatIterable actual) {
    return assertThat(actual);
  }

  /**
   * Creates a new instance of {@link IntIterableAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static IntIterableAssert then(IntIterable actual) {
    return assertThat(actual);
  }

  /**
   * Creates a new instance of {@link ListIterableAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   * @param <T> The type of the elements in the list
   */
  @CheckReturnValue
  public static <T> ListIterableAssert<T> then(ListIterable<? extends T> actual) {
    return assertThat(actual);
  }

  /**
   * Creates a new instance of {@link LongIterableAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static LongIterableAssert then(LongIterable actual) {
    return assertThat(actual);
  }

  /**
   * Creates a new instance of {@link MultimapAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   * @param <KEY> The type of keys in the BagMultimap
   * @param <VALUE> The type of values in the BagMultimap
   */
  @CheckReturnValue
  public static <KEY, VALUE> MultimapAssert<KEY, VALUE> then(Multimap<KEY, VALUE> actual) {
    return assertThat(actual);
  }

  /**
   * Creates a new instance of {@link RichIterableAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   * @param <T> The type of the elements in the iterable
   */
  @CheckReturnValue
  public static <T> RichIterableAssert<T> then(RichIterable<? extends T> actual) {
    return assertThat(actual);
  }

  /**
   * Creates a new instance of {@link SetIterableAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   * @param <T> The type of the elements in the set
   */
  @CheckReturnValue
  public static <T> SetIterableAssert<T> then(SetIterable<? extends T> actual) {
    return assertThat(actual);
  }

  /**
   * Creates a new instance of {@link ShortIterableAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static ShortIterableAssert then(ShortIterable actual) {
    return assertThat(actual);
  }

  /**
   * Creates a new instance of {@link StackIterableAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   * @param <T> The type of the elements in the stack
   */
  @CheckReturnValue
  public static <T> StackIterableAssert<T> then(StackIterable<? extends T> actual) {
    return assertThat(actual);
  }
}

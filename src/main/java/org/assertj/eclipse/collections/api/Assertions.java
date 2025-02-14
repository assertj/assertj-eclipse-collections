/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2025-2025 the original author or authors.
 */
package org.assertj.eclipse.collections.api;

import org.assertj.core.util.CheckReturnValue;
import org.assertj.eclipse.collections.api.multimap.bag.BagMultimapAssert;
import org.assertj.eclipse.collections.api.multimap.list.ListMultimapAssert;
import org.assertj.eclipse.collections.api.multimap.set.SetMultimapAssert;
import org.eclipse.collections.api.multimap.bag.BagMultimap;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.multimap.set.SetMultimap;

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
   * Creates a new instance of {@link BagMultimapAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   * @param <KEY> The type of keys in the BagMultimap
   * @param <VALUE> The type of values in the BagMultimap
   */
  public static <KEY, VALUE> BagMultimapAssert<KEY, VALUE> assertThat(BagMultimap<KEY, VALUE> actual) {
    return BagMultimapAssert.assertThat(actual);
  }

  /**
   * Creates a new instance of {@link ListMultimapAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   * @param <KEY> The type of keys in the ListMultimap
   * @param <VALUE> The type of values in the ListMultimap
   */
  public static <KEY, VALUE> ListMultimapAssert<KEY, VALUE> assertThat(ListMultimap<KEY, VALUE> actual) {
    return ListMultimapAssert.assertThat(actual);
  }

  /**
   * Creates a new instance of {@link SetMultimapAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   * @param <KEY> The type of keys in the SetMultimap
   * @param <VALUE> The type of values in the SetMultimap
   */
  public static <KEY, VALUE> SetMultimapAssert<KEY, VALUE> assertThat(SetMultimap<KEY, VALUE> actual) {
    return SetMultimapAssert.assertThat(actual);
  }
}

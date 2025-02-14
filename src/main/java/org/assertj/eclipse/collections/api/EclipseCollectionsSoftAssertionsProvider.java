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

import org.assertj.core.api.SoftAssertionsProvider;
import org.assertj.core.util.CheckReturnValue;
import org.assertj.eclipse.collections.api.multimap.bag.BagMultimapAssert;
import org.assertj.eclipse.collections.api.multimap.list.ListMultimapAssert;
import org.assertj.eclipse.collections.api.multimap.set.SetMultimapAssert;
import org.eclipse.collections.api.multimap.bag.BagMultimap;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.multimap.set.SetMultimap;

/**
 * Soft assertions implementations for Eclipse Collections types.
 */
@CheckReturnValue
public interface EclipseCollectionsSoftAssertionsProvider extends SoftAssertionsProvider {
  /**
   * Creates a new, proxied instance of a {@link BagMultimapAssert}
   *
   * @param actual the path
   * @return the created assertion object
   * @param <KEY> The type of keys in the actual BagMultimap
   * @param <VALUE> The type of values in the actual BagMultimap
   */
  @SuppressWarnings("unchecked")
  default <KEY, VALUE> BagMultimapAssert<KEY, VALUE> assertThat(BagMultimap<KEY, VALUE> actual) {
    return this.proxy(BagMultimapAssert.class, BagMultimap.class, actual);
  }

  /**
   * Creates a new, proxied instance of a {@link ListMultimapAssert}
   *
   * @param actual the path
   * @return the created assertion object
   * @param <KEY> The type of keys in the actual ListMultimap
   * @param <VALUE> The type of values in the actual ListMultimap
   */
  @SuppressWarnings("unchecked")
  default <KEY, VALUE> ListMultimapAssert<KEY, VALUE> assertThat(ListMultimap<KEY, VALUE> actual) {
    return this.proxy(ListMultimapAssert.class, ListMultimap.class, actual);
  }

  /**
   * Creates a new, proxied instance of a {@link SetMultimapAssert}
   *
   * @param actual the path
   * @return the created assertion object
   * @param <KEY> The type of keys in the actual SetMultimap
   * @param <VALUE> The type of values in the actual SetMultimap
   */
  @SuppressWarnings("unchecked")
  default <KEY, VALUE> SetMultimapAssert<KEY, VALUE> assertThat(SetMultimap<KEY, VALUE> actual) {
    return this.proxy(SetMultimapAssert.class, SetMultimap.class, actual);
  }
}

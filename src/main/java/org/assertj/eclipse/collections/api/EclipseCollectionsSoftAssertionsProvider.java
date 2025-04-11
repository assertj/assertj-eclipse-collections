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
import org.assertj.eclipse.collections.api.multimap.MultimapAssert;
import org.eclipse.collections.api.multimap.Multimap;

/**
 * Soft assertions implementations for Eclipse Collections types.
 */
@CheckReturnValue
public interface EclipseCollectionsSoftAssertionsProvider extends SoftAssertionsProvider {
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
}

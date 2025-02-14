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
package org.assertj.eclipse.collections.api.multimap.list;

import org.assertj.eclipse.collections.api.Assertions;
import org.assertj.eclipse.collections.api.multimap.AbstractMultimapAssert;
import org.eclipse.collections.api.multimap.list.ListMultimap;

/**
 * Assertion methods for {@link ListMultimap}s.
 * <p>
 * To create an instance of this class, invoke <code>{@link Assertions#assertThat(ListMultimap)}</code>.
 * </p>
 *
 * @param <KEY>    the type of keys in the ListMultimap.
 * @param <VALUE>  the type of values in the ListMultimap.
 */
public class ListMultimapAssert<KEY, VALUE>
  extends AbstractMultimapAssert<ListMultimapAssert<KEY, VALUE>, ListMultimap<KEY, VALUE>, KEY, VALUE> {

  /**
   * Creates a new instance of {@link ListMultimapAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   * @param <KEY> The type of keys in th actual ListMultimap
   * @param <VALUE> The type of values in the actual ListMultimap
   */
  public static <KEY, VALUE> ListMultimapAssert<KEY, VALUE> assertThat(ListMultimap<KEY, VALUE> actual) {
    return new ListMultimapAssert<>(actual);
  }

  /**
   * Creates a new {@link ListMultimapAssert}.
   */
  public ListMultimapAssert(ListMultimap<KEY, VALUE> actual) {
    super(actual, ListMultimapAssert.class);
  }
}

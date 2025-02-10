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

import org.assertj.eclipse.collections.api.multimap.AbstractMultimapAssert;
import org.eclipse.collections.api.multimap.list.ListMultimap;

public class ListMultimapAssert<KEY, VALUE>
  extends AbstractMultimapAssert<ListMultimapAssert<KEY, VALUE>, ListMultimap<KEY, VALUE>, KEY, VALUE> {

  /**
   * Creates a new instance of {@link ListMultimapAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  public static <KEY, VALUE> ListMultimapAssert<KEY, VALUE> assertThat(ListMultimap<KEY, VALUE> actual) {
    return new ListMultimapAssert<>(actual);
  }

  public ListMultimapAssert(ListMultimap<KEY, VALUE> actual) {
    super(actual, ListMultimapAssert.class);
  }
}

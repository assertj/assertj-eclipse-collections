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
package org.assertj.eclipse.collections.api.multimap.bag;

import org.assertj.eclipse.collections.api.Assertions;
import org.assertj.eclipse.collections.api.multimap.AbstractMultimapAssert;
import org.assertj.eclipse.collections.api.multimap.set.SetMultimapAssert;
import org.eclipse.collections.api.multimap.bag.BagMultimap;

/**
 * Assertion methods for {@link BagMultimap}s.
 * <p>
 * To create an instance of this class, invoke <code>{@link Assertions#assertThat(BagMultimap)}</code>.
 * </p>
 *
 * @param <KEY>   the type of keys in the BagMultimap.
 * @param <VALUE> the type of values in the BagMultimap.
 */
public class BagMultimapAssert<KEY, VALUE> extends AbstractMultimapAssert<BagMultimapAssert<KEY, VALUE>, BagMultimap<KEY, VALUE>, KEY, VALUE> {

  /**
   * Creates a new instance of {@link BagMultimapAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   * @param <KEY> The type of keys in the actual BagMultimap
   * @param <VALUE> The type of values in the actual BagMultimap
   */
  public static <KEY, VALUE> BagMultimapAssert<KEY, VALUE> assertThat(BagMultimap<KEY, VALUE> actual) {
    return new BagMultimapAssert<>(actual);
  }

  /**
   * Creates a new {@link BagMultimapAssert}.
   */
  public BagMultimapAssert(BagMultimap<KEY, VALUE> actual) {
    super(actual, BagMultimapAssert.class);
  }
}

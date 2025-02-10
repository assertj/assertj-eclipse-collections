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
 * Behavior-driven development style entry point for assertion methods for the Eclipse Collections library. Each method
 * in this class is a static factory for a type-specific assertion object.
 */
@CheckReturnValue
public class BDDAssertions extends Assertions {
  protected BDDAssertions() {
    // Do nothing
  }

  /**
   * Creates a new instance of {@link BagMultimapAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  public static <KEY, VALUE> BagMultimapAssert<KEY, VALUE> then(BagMultimap<KEY, VALUE> actual) {
    return assertThat(actual);
  }

  /**
   * Creates a new instance of {@link ListMultimapAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  public static <KEY, VALUE> ListMultimapAssert<KEY, VALUE> then(ListMultimap<KEY, VALUE> actual) {
    return assertThat(actual);
  }

  /**
   * Creates a new instance of {@link SetMultimapAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  public static <KEY, VALUE> SetMultimapAssert<KEY, VALUE> then(SetMultimap<KEY, VALUE> actual) {
    return assertThat(actual);
  }
}

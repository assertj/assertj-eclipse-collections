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

@CheckReturnValue
public class Assertions {
  private Assertions() {
    throw new UnsupportedOperationException("Utility class");
  }

  public static <KEY, VALUE> BagMultimapAssert<KEY, VALUE> assertThat(BagMultimap<KEY, VALUE> actual) {
    return BagMultimapAssert.assertThat(actual);
  }

  public static <KEY, VALUE> ListMultimapAssert<KEY, VALUE> assertThat(ListMultimap<KEY, VALUE> actual) {
    return ListMultimapAssert.assertThat(actual);
  }

  public static <KEY, VALUE> SetMultimapAssert<KEY, VALUE> assertThat(SetMultimap<KEY, VALUE> actual) {
    return SetMultimapAssert.assertThat(actual);
  }
}

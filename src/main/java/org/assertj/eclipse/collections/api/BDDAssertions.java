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
import org.assertj.eclipse.collections.api.multimap.MultimapAssert;
import org.eclipse.collections.api.multimap.Multimap;

/**
 * Behavior-driven development style entry point for assertion methods for the Eclipse Collections library. Each method
 * in this class is a static factory for a type-specific assertion object.
 */
@CheckReturnValue
public class BDDAssertions extends Assertions {
  /**
   * Creates a new <code>{@link BDDAssertions}</code>.
   */
  protected BDDAssertions() {
    // Do nothing
  }

  /**
   * Creates a new instance of {@link MultimapAssert}.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   * @param <KEY> The type of keys in the BagMultimap
   * @param <VALUE> The type of values in the BagMultimap
   */
  public static <KEY, VALUE> MultimapAssert<KEY, VALUE> then(Multimap<KEY, VALUE> actual) {
    return assertThat(actual);
  }
}

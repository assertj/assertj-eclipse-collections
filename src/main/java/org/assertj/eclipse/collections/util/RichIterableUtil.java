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
package org.assertj.eclipse.collections.util;

import org.assertj.core.util.IterableUtil;
import org.eclipse.collections.api.PrimitiveIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.multimap.Multimap;

import static java.util.Objects.requireNonNull;

public final class RichIterableUtil {
  private RichIterableUtil() {
    // Do nothing
  }

  public static int sizeOf(Iterable<?> iterable) {
    requireNonNull(iterable, "Iterable must not be null");
    if (iterable instanceof RichIterable<?> richIterable) {
      return richIterable.size();
    }
    return IterableUtil.sizeOf(iterable);
  }

  public static int sizeOf(Multimap<?, ?> multimap) {
    requireNonNull(multimap, "Multimap must not be null");
    return multimap.size();
  }

  public static int sizeOf(PrimitiveIterable primitiveIterable) {
    requireNonNull(primitiveIterable, "PrimitiveIterable must not be null");
    return primitiveIterable.size();
  }
}

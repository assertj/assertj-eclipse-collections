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
package org.assertj.eclipse.collections.api;

import org.assertj.core.api.ObjectAssert;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;

/**
 * Assertion class for {@link RichIterable} instances.
 *
 * @param <T> the type of elements in the RichIterable being asserted
 */
public class RichIterableAssert<T> extends AbstractRichIterableAssert<RichIterableAssert<T>, RichIterable<? extends T>, T, ObjectAssert<T>> {
  public RichIterableAssert(RichIterable<? extends T> actual) {
    super(actual, RichIterableAssert.class);
  }

  @Override
  protected ObjectAssert<T> toAssert(T value) {
    return new ObjectAssert<>(value);
  }

  @Override
  protected RichIterableAssert<T> newAbstractIterableAssert(Iterable<? extends T> iterable) {
    return new RichIterableAssert<>(Lists.immutable.ofAll(iterable));
  }
}

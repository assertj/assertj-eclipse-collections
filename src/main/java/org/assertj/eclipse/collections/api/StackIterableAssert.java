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

import org.assertj.core.annotation.CheckReturnValue;
import org.assertj.core.api.ObjectAssert;
import org.eclipse.collections.api.factory.Stacks;
import org.eclipse.collections.api.stack.ImmutableStack;
import org.eclipse.collections.api.stack.StackIterable;

/**
 * Assertion methods for {@link StackIterable} interface.
 *
 * @param <ELEMENT> the type of elements stored in {@link StackIterable}.
 */
@CheckReturnValue
public class StackIterableAssert<ELEMENT> extends AbstractOrderedIterableAssert<StackIterableAssert<ELEMENT>, StackIterable<? extends ELEMENT>, ELEMENT, ObjectAssert<ELEMENT>> {
  public StackIterableAssert(StackIterable<? extends ELEMENT> actual) {
    super(actual, StackIterableAssert.class);
  }

  @Override
  protected ObjectAssert<ELEMENT> toAssert(ELEMENT value) {
    return new ObjectAssert<>(value);
  }

  @Override
  protected StackIterableAssert<ELEMENT> newAbstractIterableAssert(Iterable<? extends ELEMENT> iterable) {
    ImmutableStack<? extends ELEMENT> elements = Stacks.immutable.ofAll(iterable);
    return new StackIterableAssert<>(elements);
  }
}

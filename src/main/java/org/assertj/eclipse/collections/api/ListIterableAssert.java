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

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.InstanceOfAssertFactory;
import org.assertj.core.api.ObjectAssert;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.ListIterable;

public class ListIterableAssert<ELEMENT> extends AbstractOrderedIterableAssert<ListIterableAssert<ELEMENT>, ListIterable<? extends ELEMENT>, ELEMENT, ObjectAssert<ELEMENT>> {
  public ListIterableAssert(ListIterable<? extends ELEMENT> actual) {
    super(actual, ListIterableAssert.class);
  }

  @Override
  protected ObjectAssert<ELEMENT> toAssert(ELEMENT value) {
    return new ObjectAssert<>(value);
  }

  @Override
  protected ListIterableAssert<ELEMENT> newAbstractIterableAssert(Iterable<? extends ELEMENT> iterable) {
    ImmutableList<? extends ELEMENT> elements = Lists.immutable.ofAll(iterable);
    return new ListIterableAssert<>(elements);
  }

  @Override
  public ObjectAssert<ELEMENT> element(int index) {
    return executeAssertionNavigation(() -> internalElement(index),
      () -> nullElementNavigationAssert("element at index " + index));
  }

  @Override
  public <ASSERT extends AbstractAssert<?, ?>> ASSERT element(int index, InstanceOfAssertFactory<?, ASSERT> assertFactory) {
    return executeAssertionNavigation(() -> internalElement(index).asInstanceOf(assertFactory),
      () -> nullValueAssert(assertFactory));
  }

  private ObjectAssert<ELEMENT> internalElement(int index) {
    isNotEmpty();
    assertThat(index).describedAs(navigationDescription("check index validity"))
      .isBetween(0, actual.size() - 1);
    ELEMENT elementAtIndex = actual.get(index);

    return toAssert(elementAtIndex, navigationDescription("element at index " + index));
  }
}

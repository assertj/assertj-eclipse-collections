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

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.InstanceOfAssertFactory;
import org.eclipse.collections.api.ordered.OrderedIterable;

//@format:off
public abstract class AbstractOrderedIterableAssert<SELF extends AbstractOrderedIterableAssert<SELF, ACTUAL, ELEMENT, ELEMENT_ASSERT>,
  ACTUAL extends OrderedIterable<? extends ELEMENT>,
  ELEMENT,
  ELEMENT_ASSERT extends AbstractAssert<? extends ELEMENT_ASSERT, ELEMENT>>
  extends AbstractRichIterableAssert<SELF, ACTUAL, ELEMENT, ELEMENT_ASSERT> {
//@format:on

  protected AbstractOrderedIterableAssert(ACTUAL actual, Class<?> selfType) {
    super(actual, selfType);
  }

  @Override
  public ELEMENT_ASSERT first() {
    return executeAssertionNavigation(this::internalFirst, () -> nullElementNavigationAssert("check first element"));
  }

  @Override
  public <ASSERT extends AbstractAssert<?, ?>> ASSERT first(InstanceOfAssertFactory<?, ASSERT> assertFactory) {
    return executeAssertionNavigation(() -> internalFirst().asInstanceOf(assertFactory),
      () -> nullValueAssert(assertFactory));
  }

  @Override
  public ELEMENT_ASSERT last() {
    return executeAssertionNavigation(this::internalLast, () -> nullElementNavigationAssert("check last element"));
  }

  @Override
  public <ASSERT extends AbstractAssert<?, ?>> ASSERT last(InstanceOfAssertFactory<?, ASSERT> assertFactory) {
    return executeAssertionNavigation(() -> internalLast().asInstanceOf(assertFactory),
      () -> nullValueAssert(assertFactory));
  }

  private ELEMENT_ASSERT internalFirst() {
    isNotEmpty();
    return toAssert(actual.getFirst(), navigationDescription("check first element"));
  }

  private ELEMENT_ASSERT internalLast() {
    isNotEmpty();
    return toAssert(actual.getLast(), navigationDescription("check last element"));
  }

  // TODO: Decide where this method should live: here, in AbstractRichIterableAssert, or somewhere else
  protected ELEMENT_ASSERT nullElementNavigationAssert(String description) {
    return toAssert(null, navigationDescription(description));
  }
}

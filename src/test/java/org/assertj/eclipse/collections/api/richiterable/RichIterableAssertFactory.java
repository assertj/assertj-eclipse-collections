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
package org.assertj.eclipse.collections.api.richiterable;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.assertj.eclipse.collections.api.AbstractRichIterableAssert;
import org.assertj.eclipse.collections.api.SoftAssertions;

final class RichIterableAssertFactory<ELEMENT> {
  private final String name;
  private final Function<ELEMENT[], AbstractRichIterableAssert<?, ?, ELEMENT, ?>> fromElements;
  private final Supplier<AbstractRichIterableAssert<?, ?, ELEMENT, ?>> fromEmpty;
  private final Supplier<AbstractRichIterableAssert<?, ?, ELEMENT, ?>> fromNull;
  private final BiFunction<SoftAssertions, ELEMENT[], AbstractRichIterableAssert<?, ?, ELEMENT, ?>> softlyFromElements;

  RichIterableAssertFactory(
    String name,
    Function<ELEMENT[], AbstractRichIterableAssert<?, ?, ELEMENT, ?>> fromElements,
    Supplier<AbstractRichIterableAssert<?, ?, ELEMENT, ?>> fromEmpty,
    Supplier<AbstractRichIterableAssert<?, ?, ELEMENT, ?>> fromNull,
    BiFunction<SoftAssertions, ELEMENT[], AbstractRichIterableAssert<?, ?, ELEMENT, ?>> softlyFromElements) {
    this.name = name;
    this.fromElements = fromElements;
    this.fromEmpty = fromEmpty;
    this.fromNull = fromNull;
    this.softlyFromElements = softlyFromElements;
  }

  @SafeVarargs
  public final AbstractRichIterableAssert<?, ?, ELEMENT, ?> fromElements(ELEMENT... elements) {
    return fromElements.apply(elements);
  }

  public AbstractRichIterableAssert<?, ?, ELEMENT, ?> fromEmpty() {
    return fromEmpty.get();
  }

  public AbstractRichIterableAssert<?, ?, ELEMENT, ?> fromNull() {
    return fromNull.get();
  }

  @SafeVarargs
  public final AbstractRichIterableAssert<?, ?, ELEMENT, ?> softlyFromElements(SoftAssertions softAssertions, ELEMENT... elements) {
    return softlyFromElements.apply(softAssertions, elements);
  }

  @Override
  public String toString() {
    return name;
  }
}

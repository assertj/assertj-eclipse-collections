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
package org.assertj.eclipse.collections.api.primitive;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

import org.assertj.eclipse.collections.api.AbstractPrimitiveIterableAssert;
import org.assertj.eclipse.collections.api.SoftAssertions;

class PrimitiveIterableAssertFactory<T extends AbstractPrimitiveIterableAssert<?, ?>> {
  private final String name;
  private final Function<Object[], T> fromElements;
  private final IntFunction<T> fromSize;
  private final Supplier<T> fromEmpty;
  private final Supplier<T> fromNull;
  private final BiFunction<SoftAssertions, Integer, T> softlyFromSize;

  PrimitiveIterableAssertFactory(
    String name,
    Function<Object[], T> fromElements,
    IntFunction<T> fromSize,
    Supplier<T> fromEmpty,
    Supplier<T> fromNull,
    BiFunction<SoftAssertions, Integer, T> softlyFromSize) {
    this.name = name;
    this.fromElements = fromElements;
    this.fromSize = fromSize;
    this.fromEmpty = fromEmpty;
    this.fromNull = fromNull;
    this.softlyFromSize = softlyFromSize;
  }

  public T fromElements(Object... elements) {
    return fromElements.apply(elements);
  }

  public T fromSize(int size) {
    return fromSize.apply(size);
  }

  public T fromEmpty() {
    return fromEmpty.get();
  }

  public T fromNull() {
    return fromNull.get();
  }

  public T softlyFromSize(SoftAssertions softAssertions, int size) {
    return softlyFromSize.apply(softAssertions, size);
  }

  @Override
  public String toString() {
    return name;
  }
}

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

import static org.assertj.eclipse.collections.error.ShouldHaveDistinctSize.shouldHaveDistinctSize;
import static org.assertj.eclipse.collections.error.ShouldHaveDistinctSizeBetween.shouldHaveDistinctSizeBetween;
import static org.assertj.eclipse.collections.error.ShouldHaveDistinctSizeGreaterThan.shouldHaveDistinctSizeGreaterThan;
import static org.assertj.eclipse.collections.error.ShouldHaveDistinctSizeGreaterThanOrEqualTo.shouldHaveDistinctSizeGreaterThanOrEqualTo;
import static org.assertj.eclipse.collections.error.ShouldHaveDistinctSizeLessThan.shouldHaveDistinctSizeLessThan;
import static org.assertj.eclipse.collections.error.ShouldHaveDistinctSizeLessThanOrEqualTo.shouldHaveDistinctSizeLessThanOrEqualTo;

import org.assertj.core.api.ObjectAssert;
import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.factory.Bags;

/**
 * Assertion methods for the {@link Bag} interface.
 *
 * @param <ELEMENT> the type of elements stored in {@link Bag}.
 */
public class BagAssert<ELEMENT> extends AbstractRichIterableAssert<BagAssert<ELEMENT>, Bag<? extends ELEMENT>, ELEMENT, ObjectAssert<ELEMENT>> {
  public BagAssert(Bag<? extends ELEMENT> elements) {
    super(elements, BagAssert.class);
  }

  public BagAssert<ELEMENT> hasDistinctSize(int expected) {
    return executeAssertion(() -> {
      isNotNull();

      int actualDistinctSize = actual.sizeDistinct();
      if (actualDistinctSize == expected) {
        return;
      }

      throw assertionError(shouldHaveDistinctSize(actual, actualDistinctSize, expected));
    });
  }

  public BagAssert<ELEMENT> hasDistinctSizeBetween(int lowerBoundary, int higherBoundary) {
    return executeAssertion(() -> {
      isNotNull();

      if (!(higherBoundary >= lowerBoundary)) {
        throw new IllegalArgumentException("The higher boundary <%s> must be greater than the lower boundary <%s>.".formatted(
          higherBoundary,
          lowerBoundary));
      }

      int actualSize = actual.sizeDistinct();
      if (actualSize >= lowerBoundary && actualSize <= higherBoundary) {
        return;
      }

      throw assertionError(shouldHaveDistinctSizeBetween(actual, actualSize, lowerBoundary, higherBoundary));
    });
  }

  public BagAssert<ELEMENT> hasDistinctSizeGreaterThan(int boundary) {
    return executeAssertion(() -> {
      isNotNull();

      int actualDistinctSize = actual.sizeDistinct();
      if (actualDistinctSize > boundary) {
        return;
      }

      throw assertionError(shouldHaveDistinctSizeGreaterThan(actual, actualDistinctSize, boundary));
    });
  }

  public BagAssert<ELEMENT> hasDistinctSizeGreaterThanOrEqualTo(int boundary) {
    return executeAssertion(() -> {
      isNotNull();

      int actualDistinctSize = actual.sizeDistinct();
      if (actualDistinctSize >= boundary) {
        return;
      }

      throw assertionError(shouldHaveDistinctSizeGreaterThanOrEqualTo(actual, actualDistinctSize, boundary));
    });
  }

  public BagAssert<ELEMENT> hasDistinctSizeLessThan(int boundary) {
    return executeAssertion(() -> {
      isNotNull();

      int actualDistinctSize = actual.sizeDistinct();
      if (actualDistinctSize < boundary) {
        return;
      }

      throw assertionError(shouldHaveDistinctSizeLessThan(actual, actualDistinctSize, boundary));
    });
  }

  public BagAssert<ELEMENT> hasDistinctSizeLessThanOrEqualTo(int boundary) {
    return executeAssertion(() -> {
      isNotNull();

      int actualDistinctSize = actual.sizeDistinct();
      if (actualDistinctSize <= boundary) {
        return;
      }

      throw assertionError(shouldHaveDistinctSizeLessThanOrEqualTo(actual, actualDistinctSize, boundary));
    });
  }

  @Override
  protected ObjectAssert<ELEMENT> toAssert(ELEMENT value) {
    return new ObjectAssert<>(value);
  }

  @Override
  protected BagAssert<ELEMENT> newAbstractIterableAssert(Iterable<? extends ELEMENT> iterable) {
    ImmutableBag<? extends ELEMENT> elements = Bags.immutable.ofAll(iterable);
    return new BagAssert<>(elements);
  }
}

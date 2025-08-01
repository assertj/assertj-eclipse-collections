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
package org.assertj.eclipse.collections.api.multimap;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.error.ShouldBeEmpty.shouldBeEmpty;
import static org.assertj.core.error.ShouldBeNullOrEmpty.shouldBeNullOrEmpty;
import static org.assertj.core.error.ShouldContain.shouldContain;
import static org.assertj.core.error.ShouldContainKey.shouldContainKey;
import static org.assertj.core.error.ShouldContainKeys.shouldContainKeys;
import static org.assertj.core.error.ShouldContainOnly.shouldContainOnly;
import static org.assertj.core.error.ShouldContainValue.shouldContainValue;
import static org.assertj.core.error.ShouldContainValues.shouldContainValues;
import static org.assertj.core.error.ShouldHaveSize.shouldHaveSize;
import static org.assertj.core.error.ShouldHaveSizeBetween.shouldHaveSizeBetween;
import static org.assertj.core.error.ShouldHaveSizeGreaterThan.shouldHaveSizeGreaterThan;
import static org.assertj.core.error.ShouldHaveSizeGreaterThanOrEqualTo.shouldHaveSizeGreaterThanOrEqualTo;
import static org.assertj.core.error.ShouldHaveSizeLessThan.shouldHaveSizeLessThan;
import static org.assertj.core.error.ShouldHaveSizeLessThanOrEqualTo.shouldHaveSizeLessThanOrEqualTo;
import static org.assertj.core.error.ShouldNotBeEmpty.shouldNotBeEmpty;
import static org.assertj.eclipse.collections.error.ShouldHaveDistinctSize.shouldHaveDistinctSize;
import static org.assertj.eclipse.collections.error.ShouldHaveDistinctSizeGreaterThan.shouldHaveDistinctSizeGreaterThan;
import static org.assertj.eclipse.collections.error.ShouldHaveDistinctSizeGreaterThanOrEqualTo.shouldHaveDistinctSizeGreaterThanOrEqualTo;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Condition;
import org.assertj.core.error.GroupTypeDescription;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.tuple.Tuples;

/**
 * Base class for all implementations of assertions for {@link Multimap}.
 *
 * @param <KEY>   the type of keys in the Multimap.
 * @param <VALUE> the type of values in the Multimap.
 */
public class MultimapAssert<KEY, VALUE> extends AbstractAssert<MultimapAssert<KEY, VALUE>, Multimap<KEY, VALUE>> {

  /**
   * Creates a new {@link MultimapAssert}.
   *
   * @param actual The actual Multimap to assert against
   */
  public MultimapAssert(Multimap<KEY, VALUE> actual) {
    super(actual, MultimapAssert.class);
  }

  /**
   * Verifies that the actual {@link Multimap} contains the given entries. Entries are given in the form of {@link
   * Pair} objects.
   * <p>
   * Example:
   * <pre>{@code
   *     Multimap<String, String> multimap = Multimaps.mutable.list.with("Key1", "Value1", "Key2", "Value2");
   *
   *     // assertion will pass
   *     assertThat(multimap).contains(Tuples.pair("Key1", "Value1"), Tuples.pair("Key2", "Value2"));
   *
   *     // assertion will fail
   *     assertThat(multimap).contains(Tuples.pair("Key1", "Value3"), Tuples.pair("Key2", "Value1"));
   *     }</pre>
   *
   * @param entries the entries that are expected to be present in the {@link Multimap}.
   * @return this assertion object for method chaining.
   * @throws AssertionError if the actual {@link Multimap} does not contain the given entries
   */
  @SafeVarargs
  public final MultimapAssert<KEY, VALUE> contains(Pair<KEY, VALUE>... entries) {
    return this.containsForProxy(Lists.mutable.of(entries));
  }

  /**
   * Verifies that the actual {@code Multimap} contains the provided entries. Entries are provided as
   * an array of {@code Map.Entry} objects.
   *
   * @param entries the entries that are expected to be contained within the {@code Multimap}.
   * @return this assertion object for method chaining.
   * @throws AssertionError if the actual {@code Multimap} does not contain one or more of the specified entries.
   */
  @SafeVarargs
  public final MultimapAssert<KEY, VALUE> contains(Map.Entry<KEY, VALUE>... entries) {
    MutableList<Pair<KEY, VALUE>> pairs = Lists.mutable.of(entries).collect(Tuples::pairFrom);
    return this.containsForProxy(pairs);
  }

  /**
   * Verifies that the actual {@code Multimap} contains the provided entries.
   *
   * @param entries the list of entries that are expected to be contained within the {@code Multimap}.
   * @return this assertion object for method chaining.
   * @throws AssertionError if the actual {@code Multimap} does not contain one or more of the specified entries.
   */
  protected MultimapAssert<KEY, VALUE> containsForProxy(MutableList<Pair<KEY, VALUE>> entries) {
    this.isNotNull();
    MutableList<Pair<KEY, VALUE>> entriesNotFound = entries
      .reject(entry -> this.actual.containsKeyAndValue(entry.getOne(), entry.getTwo()));
    if (entriesNotFound.isEmpty()) {
      return this.myself;
    }
    throw this.assertionError(shouldContain(this.actual, entries, entriesNotFound));
  }

  /**
   * Verifies that the actual {@link Multimap} contains the given key-value entry.
   *
   * @param key   the key that is expected to be present in the {@link Multimap}.
   * @param value the value that is expected to be associated with the given key in the {@link Multimap}.
   * @return this assertion object for method chaining.
   * @throws AssertionError if the actual {@link Multimap} does not contain the given key-value entry.
   * @see #contains(Pair[])
   * @see #contains(Map.Entry[])
   */
  public MultimapAssert<KEY, VALUE> containsEntry(KEY key, VALUE value) {
    return this.contains(Tuples.pair(key, value));
  }

  /**
   * Verifies that the actual {@link Multimap} contains the given keys.
   * <p>
   * Example:
   * <pre>{@code
   * Multimap<String, String> multimap = Multimaps.mutable.list.with("Key1", "Value1", "Key2", "Value2");
   *
   * // assertion will pass
   * assertThat(multimap).containsKeys("Key1", "Key2");
   *
   * // assertion will fail
   * assertThat(multimap).containsKeys("Key3");
   * }</pre>
   *
   * @param keys the keys that are expected to be present in the {@link Multimap}.
   * @return this assertion object for method chaining.
   * @throws AssertionError if the actual {@link Multimap} does not contain the given keys.
   */
  public MultimapAssert<KEY, VALUE> containsKeys(KEY... keys) {
    this.isNotNull();
    MutableList<KEY> keysNotFound = Lists.mutable.of(keys).reject(this.actual::containsKey);
    if (keysNotFound.isEmpty()) {
      return this.myself;
    }
    throw this.assertionError(shouldContainKeys(this.actual, keysNotFound.toSet()));
  }

  /**
   * Verifies that the map contains only the given entries.
   *
   * @param entries the array of map entries to validate against the map
   * @return this assertion object for method chaining
   * @throws AssertionError if the actual {@link Multimap} does not contain only the given entries.
   */
  @SafeVarargs
  public final MultimapAssert<KEY, VALUE> containsOnly(Map.Entry<? extends KEY, ? extends VALUE>... entries) {
    MutableList<Pair<? extends KEY, ? extends VALUE>> pairs = Lists.mutable.of(entries).collect(Tuples::pairFrom);
    return this.containsOnlyForProxy(pairs);
  }

  /**
   * Verifies that the current object contains only the specified pairs of key-value entries.
   *
   * @param entries the pairs of key-value entries to check against
   * @return this assertion object for method chaining
   * @throws AssertionError if the actual {@link Multimap} does not contain only the given entries.
   */
  @SafeVarargs
  public final MultimapAssert<KEY, VALUE> containsOnly(Pair<? extends KEY, ? extends VALUE>... entries) {
    return this.containsOnlyForProxy(Lists.mutable.of(entries));
  }

  /**
   * Verifies that the current object contains only the specified list of pairs of key-value entries.
   *
   * @param entries the list of pairs of key-value entries to check against
   * @return this assertion object for method chaining
   * @throws AssertionError if the actual {@link Multimap} does not contain only the given entries.
   */
  protected MultimapAssert<KEY, VALUE> containsOnlyForProxy(MutableList<Pair<? extends KEY, ? extends VALUE>> entries) {
    this.isNotNull();
    PartitionMutableList<Pair<? extends KEY, ? extends VALUE>> partition = entries
      .partition(entry -> this.actual.containsKeyAndValue(entry.getOne(), entry.getTwo()));

    MutableList<Pair<? extends KEY, ? extends VALUE>> found = partition.getSelected();
    MutableList<Pair<? extends KEY, ? extends VALUE>> notFound = partition.getRejected();
    RichIterable<Pair<KEY, VALUE>> notExpected = this.actual.keyValuePairsView().reject(found::contains);

    if (notFound.isEmpty() && notExpected.isEmpty()) {
      return this.myself;
    }

    GroupTypeDescription groupTypeDescription = new GroupTypeDescription("multimap", "multimap entries");
    throw this.assertionError(shouldContainOnly(this.actual, entries, notFound, notExpected, groupTypeDescription));
  }

  /**
   * Verifies that the actual map contains the given values.
   *
   * @param values the values expected to be present in the actual map
   * @return the current assertion object for method chaining
   * @throws AssertionError if the actual map does not contain the given values
   */
  public MultimapAssert<KEY, VALUE> containsValues(VALUE... values) {
    this.isNotNull();
    MutableList<VALUE> valuesNotFound = Lists.mutable.of(values).reject(this.actual::containsValue);
    if (valuesNotFound.isEmpty()) {
      return this.myself;
    }
    throw this.assertionError(shouldContainValues(this.actual, valuesNotFound.toSet()));
  }

  /**
   * Verifies that the actual {@code Multimap} has the expected number of distinct keys.
   *
   * @param expected the expected number of distinct keys in the {@code Multimap}.
   * @return this assertion object for method chaining.
   * @throws AssertionError if the actual number of distinct keys in the {@code Multimap} does not match the expected size.
   */
  public MultimapAssert<KEY, VALUE> hasDistinctSize(int expected) {
    this.isNotNull();
    int actualSize = this.actual.sizeDistinct();
    if (actualSize == expected) {
      return this.myself;
    }
    throw this.assertionError(shouldHaveDistinctSize(this.actual, actualSize, expected));
  }

  /**
   * Verifies that the distinct size of the {@link Multimap} is greater than the specified boundary.
   *
   * @param boundary the size that the actual number of distinct keys should exceed.
   * @return this assertion object for method chaining.
   * @throws AssertionError if the actual distinct size of the {@link Multimap} is not greater than the specified boundary.
   */
  public MultimapAssert<KEY, VALUE> hasDistinctSizeGreaterThan(int boundary) {
    this.isNotNull();
    int actualSize = this.actual.sizeDistinct();
    if (actualSize > boundary) {
      return this.myself;
    }
    throw this.assertionError(shouldHaveDistinctSizeGreaterThan(this.actual, actualSize, boundary));
  }

  /**
   * Verifies that the distinct size of the {@link Multimap} is greater than or equal to the specified boundary.
   *
   * @param boundary the minimum distinct size to compare the Multimap against
   * @return this assertion for method chaining
   * @throws AssertionError if the distinct size of the collection is less than the specified boundary
   */
  public MultimapAssert<KEY, VALUE> hasDistinctSizeGreaterThanOrEqualTo(int boundary) {
    this.isNotNull();
    int actualSize = this.actual.sizeDistinct();
    if (actualSize >= boundary) {
      return this.myself;
    }
    throw this.assertionError(shouldHaveDistinctSizeGreaterThanOrEqualTo(this.actual, actualSize, boundary));
  }

  /**
   * Verifies that at least one key in the actual {@link Multimap} satisfies the given condition.
   *
   * @param keyCondition the condition to evaluate the keys against; must not be null.
   * @return this assertion object for method chaining.
   * @throws NullPointerException if the provided condition is null.
   * @throws AssertionError       if none of the keys in the {@link Multimap} satisfy the given condition.
   */
  public MultimapAssert<KEY, VALUE> hasKeySatisfying(Condition<? super KEY> keyCondition) {
    this.isNotNull();
    requireNonNull(keyCondition, "The condition to evaluate should not be null");

    if (this.actual.keysView().anySatisfy(keyCondition::matches)) {
      return this.myself;
    }

    throw this.assertionError(shouldContainKey(this.actual, keyCondition));
  }

  /**
   * Verifies that the number of key-value entry pairs in the {@link Multimap} is equal to the given one.
   * <p>
   * Example:
   * <pre>{@code
   * Multimap<String, String> multimap = Multimaps.mutable.list.with("Key", "Value1", "Key", "Value2");
   *
   * // assertion will pass
   * assertThat(multimap).hasSize(2);
   *
   * // assertions will fail
   * assertThat(multimap).hasSize(0);
   * assertThat(multimap).hasSize(1);
   * }</pre>
   *
   * @param expected the expected size of the {@link Multimap}.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual size of the {@link Multimap} is not equal to the expected size.
   */
  public MultimapAssert<KEY, VALUE> hasSize(int expected) {
    this.isNotNull();
    int actualSize = this.actual.size();
    if (actualSize == expected) {
      return this.myself;
    }
    throw this.assertionError(shouldHaveSize(this.actual, actualSize, expected));
  }

  /**
   * Verifies that the number of key-value entry pairs in the {@link Multimap} is between the given boundaries
   * (inclusive).
   * <p>
   * Example:
   * <pre>{@code
   * Multimap<String, String> multimap = Multimaps.mutable.list.with("Key1", "Value1", "Key1", "Value2", "Key2", "Value3");
   *
   * // assertion will pass
   * assertThat(multimap).hasSizeBetween(1, 4)
   *                     .hasSizeBetween(2, 3);
   *
   * // assertions will fail
   * assertThat(multimap).hasSizeBetween(4, 5);
   * }</pre>
   *
   * @param lowerBoundary  the lower boundary compared to which actual size should be greater than or equal to.
   * @param higherBoundary the higher boundary compared to which actual size should be less than or equal to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual size of the {@link Multimap} is not between the given boundaries.
   */
  public MultimapAssert<KEY, VALUE> hasSizeBetween(int lowerBoundary, int higherBoundary) {
    this.isNotNull();
    int actualSize = this.actual.size();
    if (actualSize >= lowerBoundary && actualSize <= higherBoundary) {
      return this.myself;
    }
    throw this.assertionError(shouldHaveSizeBetween(this.actual, actualSize, lowerBoundary, higherBoundary));
  }

  /**
   * Verifies that the number of key-value entry pairs in the {@link Multimap} is greater than the specified boundary.
   * <p>
   * Example:
   * <pre>{@code
   * Multimap<String, String> multimap = Multimaps.mutable.list.with("Key1", "Value1", "Key1", "Value2", "Key2", "Value3");
   *
   * // assertion will pass
   * assertThat(multimap).hasSizeGreaterThan(1);
   *
   * // assertions will fail
   * assertThat(multimap).hasSizeGreaterThan(3);
   * assertThat(multimap).hasSizeGreaterThan(4);
   * }</pre>
   *
   * @param boundary the size that the actual number of key-value pairs should exceed.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual size of the {@link Multimap} is not greater than the specified boundary.
   */
  public MultimapAssert<KEY, VALUE> hasSizeGreaterThan(int boundary) {
    this.isNotNull();
    int actualSize = this.actual.size();
    if (actualSize > boundary) {
      return this.myself;
    }
    throw this.assertionError(shouldHaveSizeGreaterThan(this.actual, actualSize, boundary));
  }

  /**
   * Verifies that the number of key-value entry pairs in the {@link Multimap} is greater than or equal to the
   * boundary.
   * <p>
   * Example:
   * <pre>{@code
   * Multimap<String, String> multimap = Multimaps.mutable.list.with("Key1", "Value1", "Key2", "Value2");
   *
   * // assertions will pass
   * assertThat(multimap).hasSizeGreaterThanOrEqualTo(1)
   *                     .hasSizeGreaterThanOrEqualTo(2);
   *
   * // assertions will fail
   * assertThat(multimap).hasSizeGreaterThanOrEqualTo(3);
   * assertThat(multimap).hasSizeGreaterThanOrEqualTo(5);
   * }</pre>
   *
   * @param boundary the minimum size (inclusive) the {@link Multimap} should have.
   * @return {@code this} assertion object for method chaining.
   * @throws AssertionError if the actual size of the {@link Multimap} is less than the expected size.
   */
  public MultimapAssert<KEY, VALUE> hasSizeGreaterThanOrEqualTo(int boundary) {
    this.isNotNull();
    int actualSize = this.actual.size();
    if (actualSize >= boundary) {
      return this.myself;
    }
    throw this.assertionError(shouldHaveSizeGreaterThanOrEqualTo(this.actual, actualSize, boundary));
  }

  /**
   * Verifies that the number of key-value entry pairs in the {@link Multimap} is less than the boundary.
   * <p>
   * Example:
   * <pre>{@code
   * Multimap<String, String> multimap = Multimaps.mutable.list.with("Key1", "Value1", "Key2", "Value2");
   *
   * // assertion will pass
   * assertThat(multimap).hasSizeLessThan(3);
   *
   * // assertions will fail
   * assertThat(multimap).hasSizeLessThan(1);
   * assertThat(multimap).hasSizeLessThan(2);
   * }</pre>
   *
   * @param boundary the maximum size (exclusive) the {@link Multimap} should have.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual size of the {@link Multimap} is not less than the expected size.
   */
  public MultimapAssert<KEY, VALUE> hasSizeLessThan(int boundary) {
    this.isNotNull();
    int actualSize = this.actual.size();
    if (actualSize < boundary) {
      return this.myself;
    }
    throw this.assertionError(shouldHaveSizeLessThan(this.actual, actualSize, boundary));
  }

  /**
   * Verifies that the number of key-value entry pairs in the {@link Multimap} is less than or equal to the boundary.
   * <p>
   * Example:
   * <pre>{@code
   * Multimap<String, String> multimap = Multimaps.mutable.list.with("Key1", "Value1", "Key2", "Value2");
   *
   * // assertion will pass
   * assertThat(multimap).hasSizeLessThanOrEqualTo(2)
   *                     .hasSizeLessThanOrEqualTo(3);
   *
   * // assertions will fail
   * assertThat(multimap).hasSizeLessThanOrEqualTo(0);
   * assertThat(multimap).hasSizeLessThanOrEqualTo(1);
   * }</pre>
   *
   * @param boundary the maximum expected size of the {@link Multimap}.
   * @return {@code this} assertion object for method chaining.
   * @throws AssertionError if the actual size of the {@link Multimap} is greater than the expected size.
   */
  public MultimapAssert<KEY, VALUE> hasSizeLessThanOrEqualTo(int boundary) {
    this.isNotNull();
    int actualSize = this.actual.size();
    if (actualSize <= boundary) {
      return this.myself;
    }
    throw this.assertionError(shouldHaveSizeLessThanOrEqualTo(this.actual, actualSize, boundary));
  }

  /**
   * Verifies that at least one value in the actual {@link Multimap} satisfies the given condition.
   *
   * @param valueCondition the condition to evaluate the values against; must not be null.
   * @return this assertion object for method chaining.
   * @throws NullPointerException if the provided condition is null.
   * @throws AssertionError       if none of the values in the {@link Multimap} satisfy the given condition.
   */
  public MultimapAssert<KEY, VALUE> hasValueSatisfying(Condition<? super VALUE> valueCondition) {
    this.isNotNull();
    requireNonNull(valueCondition, "The condition to evaluate should not be null");

    if (this.actual.valuesView().anySatisfy(valueCondition::matches)) {
      return this.myself;
    }
    throw this.assertionError(shouldContainValue(this.actual, valueCondition));
  }

  /**
   * Verifies that the {@link Multimap} is empty.
   * <p>
   * Example:
   * <pre>{@code
   * // assertion will pass
   * assertThat(Multimaps.mutable.list.empty()).isEmpty();
   *
   * // assertion will fail
   * Multimap<String, String> multimap = Multimaps.mutable.list.with("Key", "Value");
   * assertThat(multimap).isEmpty();
   * }</pre>
   *
   * @throws AssertionError if the {@link Multimap} of values is not empty.
   */
  public void isEmpty() {
    this.isNotNull();
    if (!this.actual.isEmpty()) {
      throw this.assertionError(shouldBeEmpty(this.actual));
    }
  }

  /**
   * Verifies that the {@link Multimap} is not empty.
   * <p>
   * Example:
   * <pre>{@code
   * // assertion will pass
   * Multimap<String, String> multimap = Multimaps.mutable.list.with("Key", "Value");
   * assertThat(multimap).isNotEmpty();
   *
   * // assertion will fail
   * assertThat(Multimaps.mutable.list.empty()).isNotEmpty();
   * }</pre>
   *
   * @return this assertion object for method chaining
   * @throws AssertionError if the {@link Multimap} of values is empty.
   */
  public MultimapAssert<KEY, VALUE> isNotEmpty() {
    this.isNotNull();
    if (!this.actual.isEmpty()) {
      return this.myself;
    }
    throw this.assertionError(shouldNotBeEmpty());
  }

  /**
   * Verifies that the {@link Multimap} is null or empty.
   * <p>
   * Example:
   * <pre>{@code
   * // assertions that will pass
   * Multimap<String, String> multimap = null;
   * assertThat(multimap).isNullOrEmpty();
   *
   * Multimap<String, String> emptyMultimap = Multimaps.mutable.list.empty();
   * assertThat(emptyMultimap).isNullOrEmpty();
   *
   * // assertion will fail
   * Multimap<String, String> multimapWithElements = Multimaps.mutable.list.with("Key", "Value");
   * assertThat(multimapWithElements).isNullOrEmpty();
   * }</pre>
   *
   * @throws AssertionError if the {@link Multimap} is either null or empty.
   */
  public void isNullOrEmpty() {
    if (this.actual == null || this.actual.isEmpty()) {
      return;
    }
    throw this.assertionError(shouldBeNullOrEmpty(this.actual));
  }
}

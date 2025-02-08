package org.assertj.eclipse.collections.api.multimap;

import static org.assertj.core.error.ShouldContain.shouldContain;
import static org.assertj.core.error.ShouldContainKeys.shouldContainKeys;

import java.util.Map;

import org.assertj.core.api.AbstractObjectAssert;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.tuple.Tuples;

/**
 * Base class for all implementations of assertions for {@link Multimap}s.
 *
 * @param <SELF>   the "self" type of this assertion class.
 * @param <ACTUAL> the type of the "actual" value.
 * @param <KEY>    the type of keys in the Multimap.
 * @param <VALUE>  the type of values in the Multimap.
 */
public class AbstractMultimapAssert<SELF extends AbstractMultimapAssert<SELF, ACTUAL, KEY, VALUE>, ACTUAL extends Multimap<KEY, VALUE>, KEY, VALUE>
  extends AbstractObjectAssert<SELF, ACTUAL> {

  protected AbstractMultimapAssert(ACTUAL actual, Class<?> selfType) {
    super(actual, selfType);
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
   * @return this assertion object.
   * @throws AssertionError if the actual {@link Multimap} does not contain the given entries
   */
  @SafeVarargs
  public final SELF contains(Pair<KEY, VALUE>... entries) {
    return this.containsForProxy(Lists.mutable.of(entries));
  }

  @SafeVarargs
  public final SELF contains(Map.Entry<KEY, VALUE>... entries) {
    MutableList<Pair<KEY, VALUE>> pairs = Lists.mutable.of(entries).collect(Tuples::pairFrom);
    return this.containsForProxy(pairs);
  }

  protected SELF containsForProxy(MutableList<Pair<KEY, VALUE>> entries) {
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
   * @param key the key that is expected to be present in the {@link Multimap}.
   * @param value the value that is expected to be associated with the given key in the {@link Multimap}.
   * @return this assertion object for method chaining.
   * @throws AssertionError if the actual {@link Multimap} does not contain the given key-value entry.
   */
  public SELF containsEntry(KEY key, VALUE value) {
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
   * @return this assertion object.
   * @throws AssertionError if the actual {@link Multimap} does not contain the given keys.
   */
  public SELF containsKeys(KEY... keys) {
    this.isNotNull();
    MutableList<KEY> keysNotFound = Lists.mutable.of(keys).reject(this.actual::containsKey);
    if (keysNotFound.isEmpty()) {
      return this.myself;
    }
    throw this.assertionError(shouldContainKeys(this.actual, keysNotFound.toSet()));
  }
}

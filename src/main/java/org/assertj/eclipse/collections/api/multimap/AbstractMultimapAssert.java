package org.assertj.eclipse.collections.api.multimap;

import static org.assertj.core.error.ShouldContain.shouldContain;

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
}

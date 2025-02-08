package org.assertj.eclipse.collections.api;

import org.assertj.core.api.SoftAssertionsProvider;
import org.assertj.core.util.CheckReturnValue;
import org.assertj.eclipse.collections.api.multimap.bag.BagMultimapAssert;
import org.assertj.eclipse.collections.api.multimap.list.ListMultimapAssert;
import org.assertj.eclipse.collections.api.multimap.set.SetMultimapAssert;
import org.eclipse.collections.api.multimap.bag.BagMultimap;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.multimap.set.SetMultimap;

@CheckReturnValue
public interface EclipseCollectionsSoftAssertionsProvider extends SoftAssertionsProvider {
  default <KEY, VALUE> BagMultimapAssert<KEY, VALUE> assertThat(BagMultimap<KEY, VALUE> actual) {
    return this.proxy(BagMultimapAssert.class, BagMultimap.class, actual);
  }

  default <KEY, VALUE> ListMultimapAssert<KEY, VALUE> assertThat(ListMultimap<KEY, VALUE> actual) {
    return this.proxy(ListMultimapAssert.class, ListMultimap.class, actual);
  }

  default <KEY, VALUE> SetMultimapAssert<KEY, VALUE> assertThat(SetMultimap<KEY, VALUE> actual) {
    return this.proxy(SetMultimapAssert.class, SetMultimap.class, actual);
  }
}

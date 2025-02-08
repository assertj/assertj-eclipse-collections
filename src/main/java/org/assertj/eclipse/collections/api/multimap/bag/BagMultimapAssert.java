package org.assertj.eclipse.collections.api.multimap.bag;

import org.assertj.eclipse.collections.api.multimap.AbstractMultimapAssert;
import org.eclipse.collections.api.multimap.bag.BagMultimap;

public class BagMultimapAssert<KEY, VALUE> extends AbstractMultimapAssert<BagMultimapAssert<KEY, VALUE>, BagMultimap<KEY, VALUE>, KEY, VALUE> {

  public static <KEY, VALUE> BagMultimapAssert<KEY, VALUE> assertThat(BagMultimap<KEY, VALUE> actual) {
    return new BagMultimapAssert<>(actual);
  }

  public BagMultimapAssert(BagMultimap<KEY, VALUE> keyvalueBagMultimap) {
    super(keyvalueBagMultimap, BagMultimapAssert.class);
  }
}

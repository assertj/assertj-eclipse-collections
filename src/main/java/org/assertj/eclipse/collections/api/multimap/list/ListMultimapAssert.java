package org.assertj.eclipse.collections.api.multimap.list;

import org.assertj.eclipse.collections.api.multimap.AbstractMultimapAssert;
import org.eclipse.collections.api.multimap.list.ListMultimap;

public class ListMultimapAssert<KEY, VALUE>
  extends AbstractMultimapAssert<ListMultimapAssert<KEY, VALUE>, ListMultimap<KEY, VALUE>, KEY, VALUE> {

  public static <KEY, VALUE> ListMultimapAssert<KEY, VALUE> assertThat(ListMultimap<KEY, VALUE> actual) {
    return new ListMultimapAssert<>(actual);
  }

  public ListMultimapAssert(ListMultimap<KEY, VALUE> actual) {
    super(actual, ListMultimapAssert.class);
  }
}

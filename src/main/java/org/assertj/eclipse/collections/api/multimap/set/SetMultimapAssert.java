package org.assertj.eclipse.collections.api.multimap.set;

import org.assertj.eclipse.collections.api.multimap.AbstractMultimapAssert;
import org.eclipse.collections.api.multimap.set.SetMultimap;

public class SetMultimapAssert<KEY, VALUE> extends AbstractMultimapAssert<SetMultimapAssert<KEY, VALUE>, SetMultimap<KEY, VALUE>, KEY, VALUE> {

  public static <KEY, VALUE> SetMultimapAssert<KEY, VALUE> assertThat(SetMultimap<KEY, VALUE> actual) {
    return new SetMultimapAssert<>(actual);
  }

  public SetMultimapAssert(SetMultimap<KEY, VALUE> actual) {
    super(actual, SetMultimapAssert.class);
  }
}

package org.assertj.eclipse.collections.api.multimap.list;

import static org.eclipse.collections.impl.tuple.Tuples.pair;

import java.util.Map;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.assertj.eclipse.collections.api.multimap.AbstractMultimapAssert_Contains_Contract;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Multimaps;

public class ListMultimapAssert_Contains_Test implements AbstractMultimapAssert_Contains_Contract<String, String, ListMultimap<String, String>, ListMultimapAssert<String, String>> {
  @Override
  public ListMultimap<String, String> testInput() {
    MutableListMultimap<String, String> multimap = Multimaps.mutable.list.of();
    multimap.putAll("TOS", Lists.immutable.of("Kirk", "Spock", "McCoy", "Scotty", "Uhura", "Sulu", "Chekov"));
    multimap.putAll("TNG", Lists.immutable.of("Picard", "Riker", "Data", "Geordi", "Troi", "Crusher", "Worf"));
    multimap.putAll("DS9", Lists.immutable.of("Sisko", "Kira", "Obrien", "Dax", "Odo", "Bashir", "Worf", "Quark", "Jake"));
    multimap.putAll("VOY", Lists.immutable.of("Janeway", "Chakotay", "Torres", "Paris", "The Doctor", "Tuvok", "Kim", "Seven"));
    multimap.putAll("ENT", Lists.immutable.of("Archer", "Trip", "Tpol", "Reed", "Hoshi", "Phlox", "Mayweather"));
    return multimap;
  }

  @Override
  public ListMultimap<String, String> emptyInput() {
    return Multimaps.immutable.list.empty();
  }

  @Override
  public ListMultimapAssert<String, String> assertion(ListMultimap<String, String> testInput) {
    return ListMultimapAssert.assertThat(testInput);
  }

  @Override
  public ListMultimapAssert<String, String> softAssertion(SoftAssertions softAssertions, ListMultimap<String, String> testInput) {
    return softAssertions.assertThat(testInput);
  }

  @Override
  public Pair<String, String>[] expectedPairs() {
    return new Pair[]{pair("TNG", "Riker"), pair("DS9", "Kira")};
  }

  @Override
  public Map.Entry<String, String>[] expectedEntries() {
    return new Map.Entry[]{pair("TNG", "Riker").toEntry(), pair("DS9", "Kira").toEntry()};
  }

  @Override
  public Pair<String, String> missingPair() {
    return pair("VOY", "Kes");
  }

  @Override
  public Map.Entry<String, String> missingEntry() {
    return missingPair().toEntry();
  }
}

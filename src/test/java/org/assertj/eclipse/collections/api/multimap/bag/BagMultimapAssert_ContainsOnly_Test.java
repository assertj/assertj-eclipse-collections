package org.assertj.eclipse.collections.api.multimap.bag;

import static org.eclipse.collections.impl.tuple.Tuples.pair;

import java.util.Map;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.assertj.eclipse.collections.api.multimap.AbstractMultimapAssert_ContainsOnly_Contract;
import org.eclipse.collections.api.multimap.bag.BagMultimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Multimaps;

public class BagMultimapAssert_ContainsOnly_Test implements AbstractMultimapAssert_ContainsOnly_Contract<String, String, BagMultimap<String, String>, BagMultimapAssert<String, String>> {

  @Override
  public BagMultimap<String, String> testInput() {
    MutableBagMultimap<String, String> multimap = Multimaps.mutable.bag.of();
    multimap.put("TNG", "Enterprise");
    multimap.put("DS9", "Deep Space Nine");
    multimap.put("DS9", "Defiant");
    multimap.put("VOY", "Voyager");
    return multimap;
  }

  @Override
  public BagMultimap<String, String> emptyInput() {
    return Multimaps.immutable.bag.empty();
  }

  @Override
  public BagMultimapAssert<String, String> assertion(BagMultimap<String, String> testInput) {
    return BagMultimapAssert.assertThat(testInput);
  }

  @Override
  public BagMultimapAssert<String, String> softAssertion(SoftAssertions softAssertions, BagMultimap<String, String> testInput) {
    return softAssertions.assertThat(testInput);
  }

  @Override
  public Pair<? extends String, ? extends String>[] exactMatchPairs() {
    return new Pair[]{
      pair("TNG", "Enterprise"),
      pair("DS9", "Deep Space Nine"),
      pair("DS9", "Defiant"),
      pair("VOY", "Voyager")
    };
  }

  @Override
  public Map.Entry<? extends String, ? extends String>[] exactMatchEntries() {
    return new Map.Entry[]{
      pair("TNG", "Enterprise").toEntry(),
      pair("DS9", "Deep Space Nine").toEntry(),
      pair("DS9", "Defiant").toEntry(),
      pair("VOY", "Voyager").toEntry()
    };
  }

  @Override
  public Pair<? extends String, ? extends String>[] partialMatchMissingPairs() {
    return new Pair[]{
      pair("TNG", "Enterprise"),
      pair("DS9", "Defiant"),
      pair("VOY", "Voyager")
    };
  }

  @Override
  public Map.Entry<? extends String, ? extends String>[] partialMatchMissingEntries() {
    return new Map.Entry[]{
      pair("TNG", "Enterprise").toEntry(),
      pair("DS9", "Defiant").toEntry(),
      pair("VOY", "Voyager").toEntry()
    };
  }

  @Override
  public Pair<? extends String, ? extends String>[] partialMatchExtraPairs() {
    return new Pair[]{
      pair("TOS", "Enterprise"),
      pair("TNG", "Enterprise"),
      pair("DS9", "Deep Space Nine"),
      pair("DS9", "Defiant"),
      pair("VOY", "Voyager")
    };
  }

  @Override
  public Map.Entry<? extends String, ? extends String>[] partialMatchExtraEntries() {
    return new Map.Entry[]{
      pair("TOS", "Enterprise").toEntry(),
      pair("TNG", "Enterprise").toEntry(),
      pair("DS9", "Deep Space Nine").toEntry(),
      pair("DS9", "Defiant").toEntry(),
      pair("VOY", "Voyager").toEntry()
    };
  }
}

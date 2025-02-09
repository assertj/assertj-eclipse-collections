package org.assertj.eclipse.collections.api.multimap.set;

import static org.eclipse.collections.impl.tuple.Tuples.pair;

import java.util.Map;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.assertj.eclipse.collections.api.multimap.AbstractMultimapAssert_ContainsOnly_Contract;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.multimap.set.SetMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Multimaps;

public class SetMultimapAssert_ContainsOnly_Test implements AbstractMultimapAssert_ContainsOnly_Contract<String, String, SetMultimap<String, String>, SetMultimapAssert<String, String>> {

  @Override
  public SetMultimap<String, String> testInput() {
    MutableSetMultimap<String, String> multimap = Multimaps.mutable.set.of();
    multimap.put("TNG", "Enterprise");
    multimap.put("DS9", "Deep Space Nine");
    multimap.put("DS9", "Defiant");
    multimap.put("VOY", "Voyager");
    return multimap;
  }

  @Override
  public SetMultimap<String, String> emptyInput() {
    return Multimaps.immutable.set.empty();
  }

  @Override
  public SetMultimapAssert<String, String> assertion(SetMultimap<String, String> testInput) {
    return SetMultimapAssert.assertThat(testInput);
  }

  @Override
  public SetMultimapAssert<String, String> softAssertion(SoftAssertions softAssertions, SetMultimap<String, String> testInput) {
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

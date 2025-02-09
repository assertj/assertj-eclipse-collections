package org.assertj.eclipse.collections.api.multimap.list;

import static org.eclipse.collections.impl.tuple.Tuples.pair;

import java.util.Map;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.assertj.eclipse.collections.api.multimap.AbstractMultimapAssert_ContainsOnly_Contract;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Multimaps;

public class ListMultimapAssert_ContainsOnly_Test implements AbstractMultimapAssert_ContainsOnly_Contract<String, String, ListMultimap<String, String>, ListMultimapAssert<String, String>> {

  @Override
  public ListMultimap<String, String> testInput() {
    MutableListMultimap<String, String> multimap = Multimaps.mutable.list.of();
    multimap.put("TNG", "Enterprise");
    multimap.put("DS9", "Deep Space Nine");
    multimap.put("DS9", "Defiant");
    multimap.put("VOY", "Voyager");
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

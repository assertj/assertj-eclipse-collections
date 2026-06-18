/*
 * Copyright 2025-2026 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.assertj.eclipse.collections.api.primitive;

import java.util.stream.Stream;

import org.assertj.eclipse.collections.api.BooleanIterableAssert;
import org.assertj.eclipse.collections.api.ByteIterableAssert;
import org.assertj.eclipse.collections.api.CharIterableAssert;
import org.assertj.eclipse.collections.api.DoubleIterableAssert;
import org.assertj.eclipse.collections.api.FloatIterableAssert;
import org.assertj.eclipse.collections.api.IntIterableAssert;
import org.assertj.eclipse.collections.api.LongIterableAssert;
import org.assertj.eclipse.collections.api.ShortIterableAssert;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.primitive.MutableBooleanBag;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.factory.primitive.BooleanBags;
import org.eclipse.collections.api.factory.primitive.BooleanLists;
import org.eclipse.collections.api.factory.primitive.BooleanSets;
import org.eclipse.collections.api.factory.primitive.BooleanStacks;
import org.eclipse.collections.api.factory.primitive.ByteBags;
import org.eclipse.collections.api.factory.primitive.ByteLists;
import org.eclipse.collections.api.factory.primitive.ByteSets;
import org.eclipse.collections.api.factory.primitive.ByteStacks;
import org.eclipse.collections.api.factory.primitive.CharBags;
import org.eclipse.collections.api.factory.primitive.CharLists;
import org.eclipse.collections.api.factory.primitive.CharSets;
import org.eclipse.collections.api.factory.primitive.CharStacks;
import org.eclipse.collections.api.factory.primitive.DoubleBags;
import org.eclipse.collections.api.factory.primitive.DoubleLists;
import org.eclipse.collections.api.factory.primitive.DoubleSets;
import org.eclipse.collections.api.factory.primitive.DoubleStacks;
import org.eclipse.collections.api.factory.primitive.FloatBags;
import org.eclipse.collections.api.factory.primitive.FloatLists;
import org.eclipse.collections.api.factory.primitive.FloatSets;
import org.eclipse.collections.api.factory.primitive.FloatStacks;
import org.eclipse.collections.api.factory.primitive.IntBags;
import org.eclipse.collections.api.factory.primitive.IntLists;
import org.eclipse.collections.api.factory.primitive.IntSets;
import org.eclipse.collections.api.factory.primitive.IntStacks;
import org.eclipse.collections.api.factory.primitive.LongBags;
import org.eclipse.collections.api.factory.primitive.LongLists;
import org.eclipse.collections.api.factory.primitive.LongSets;
import org.eclipse.collections.api.factory.primitive.LongStacks;
import org.eclipse.collections.api.factory.primitive.ShortBags;
import org.eclipse.collections.api.factory.primitive.ShortLists;
import org.eclipse.collections.api.factory.primitive.ShortSets;
import org.eclipse.collections.api.factory.primitive.ShortStacks;
import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.primitive.MutableBooleanSet;
import org.eclipse.collections.api.stack.primitive.MutableBooleanStack;
import org.eclipse.collections.impl.factory.Multimaps;
import org.eclipse.collections.impl.list.fixed.ArrayAdapter;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;
import org.junit.jupiter.params.support.ParameterDeclarations;

class PrimitiveIterableArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<PrimitiveIterableParameterizedTest> {

  private static final MutableMultimap<PrimitiveType, PrimitiveIterableAssertFactory<?>> FACTORIES_BY_TYPE;

  static {
    FACTORIES_BY_TYPE = Multimaps.mutable.list.empty();
    FACTORIES_BY_TYPE.withKeyMultiValues(PrimitiveType.BOOLEAN, booleanList(), booleanSet(), booleanBag(), booleanStack());
    FACTORIES_BY_TYPE.withKeyMultiValues(PrimitiveType.BYTE,    byteList(),    byteSet(),    byteBag(),    byteStack());
    FACTORIES_BY_TYPE.withKeyMultiValues(PrimitiveType.CHAR,    charList(),    charSet(),    charBag(),    charStack());
    FACTORIES_BY_TYPE.withKeyMultiValues(PrimitiveType.DOUBLE,  doubleList(),  doubleSet(),  doubleBag(),  doubleStack());
    FACTORIES_BY_TYPE.withKeyMultiValues(PrimitiveType.FLOAT,   floatList(),   floatSet(),   floatBag(),   floatStack());
    FACTORIES_BY_TYPE.withKeyMultiValues(PrimitiveType.INT,     intList(),     intSet(),     intBag(),     intStack());
    FACTORIES_BY_TYPE.withKeyMultiValues(PrimitiveType.LONG,    longList(),    longSet(),    longBag(),    longStack());
    FACTORIES_BY_TYPE.withKeyMultiValues(PrimitiveType.SHORT,   shortList(),   shortSet(),   shortBag(),   shortStack());
  }

  private ImmutableSet<PrimitiveType> types;

  @Override
  public void accept(PrimitiveIterableParameterizedTest annotation) {
    this.types = Sets.immutable.of(annotation.type());
  }

  @Override
  public Stream<? extends Arguments> provideArguments(ParameterDeclarations parameters, ExtensionContext context) {
    RichIterable<PrimitiveIterableAssertFactory<?>> primitiveIterableAssertFactories;
    if (types.isEmpty()) {
      primitiveIterableAssertFactories = FACTORIES_BY_TYPE.valuesView();
    } else {
      primitiveIterableAssertFactories = types.flatCollect(FACTORIES_BY_TYPE::get);
    }
    return primitiveIterableAssertFactories.collect(Arguments::arguments).toList().stream();
  }

  static PrimitiveIterableAssertFactory<BooleanIterableAssert> booleanList() {
    return new PrimitiveIterableAssertFactory<>("Boolean List",
      elements -> new BooleanIterableAssert(toBooleanList(elements)),
      size -> new BooleanIterableAssert(BooleanLists.immutable.of(new boolean[size])),
      () -> new BooleanIterableAssert(BooleanLists.immutable.empty()),
      () -> new BooleanIterableAssert(null),
      (softAssertions, size) -> softAssertions.assertThat(BooleanLists.immutable.of(new boolean[size])));
  }

  private static MutableBooleanList toBooleanList(Object[] elements) {
    return ArrayAdapter.adapt(elements).collectBoolean(Boolean.class::cast);
  }

  static PrimitiveIterableAssertFactory<BooleanIterableAssert> booleanSet() {
    return new PrimitiveIterableAssertFactory<>("Boolean Set",
      elements -> new BooleanIterableAssert(toBooleanSet(elements)),
      size -> new BooleanIterableAssert(BooleanSets.immutable.of(distinctBooleans(size))),
      () -> new BooleanIterableAssert(BooleanSets.immutable.empty()),
      () -> new BooleanIterableAssert(null),
      (softAssertions, size) -> softAssertions.assertThat(BooleanSets.immutable.of(distinctBooleans(size))));
  }

  private static MutableBooleanSet toBooleanSet(Object[] elements) {
    return toBooleanList(elements).toSet();
  }

  static PrimitiveIterableAssertFactory<BooleanIterableAssert> booleanBag() {
    return new PrimitiveIterableAssertFactory<>("Boolean Bag",
      elements -> new BooleanIterableAssert(toBooleanBag(elements)),
      n -> new BooleanIterableAssert(BooleanBags.immutable.of(new boolean[n])),
      () -> new BooleanIterableAssert(BooleanBags.immutable.empty()),
      () -> new BooleanIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(BooleanBags.immutable.of(new boolean[n])));
  }

  private static MutableBooleanBag toBooleanBag(Object[] elements) {
    return toBooleanList(elements).toBag();
  }

  static PrimitiveIterableAssertFactory<BooleanIterableAssert> booleanStack() {
    return new PrimitiveIterableAssertFactory<>("Boolean Stack",
      elements -> new BooleanIterableAssert(toBooleanStack(elements)),
      n -> new BooleanIterableAssert(BooleanStacks.immutable.of(new boolean[n])),
      () -> new BooleanIterableAssert(BooleanStacks.immutable.empty()),
      () -> new BooleanIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(BooleanStacks.immutable.of(new boolean[n])));
  }

  private static MutableBooleanStack toBooleanStack(Object[] elements) {
    return toBooleanList(elements).toStack();
  }

  private static boolean[] distinctBooleans(int n) {
    boolean[] arr = new boolean[n];
    for (int i = 0; i < n; i++) {
      arr[i] = (i % 2 != 0);
    }
    return arr;
  }

  static PrimitiveIterableAssertFactory<ByteIterableAssert> byteList() {
    return new PrimitiveIterableAssertFactory<>("Byte List",
      objects -> new  ByteIterableAssert(ArrayAdapter.adapt(objects).collectByte(Byte.class::cast)),
      n -> new ByteIterableAssert(ByteLists.immutable.of(new byte[n])),
      () -> new ByteIterableAssert(ByteLists.immutable.empty()),
      () -> new ByteIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(ByteLists.immutable.of(new byte[n])));
  }

  static PrimitiveIterableAssertFactory<ByteIterableAssert> byteSet() {
    return new PrimitiveIterableAssertFactory<>("Byte Set",
      objects -> new  ByteIterableAssert(ArrayAdapter.adapt(objects).collectByte(Byte.class::cast).toSet()),
      n -> new ByteIterableAssert(ByteSets.immutable.of(distinctBytes(n))),
      () -> new ByteIterableAssert(ByteSets.immutable.empty()),
      () -> new ByteIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(ByteSets.immutable.of(distinctBytes(n))));
  }

  static PrimitiveIterableAssertFactory<ByteIterableAssert> byteBag() {
    return new PrimitiveIterableAssertFactory<>("Byte Bag",
      objects -> new  ByteIterableAssert(ArrayAdapter.adapt(objects).collectByte(Byte.class::cast).toBag()),
      n -> new ByteIterableAssert(ByteBags.immutable.of(new byte[n])),
      () -> new ByteIterableAssert(ByteBags.immutable.empty()),
      () -> new ByteIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(ByteBags.immutable.of(new byte[n])));
  }

  static PrimitiveIterableAssertFactory<ByteIterableAssert> byteStack() {
    return new PrimitiveIterableAssertFactory<>("Byte Stack",
      objects -> new  ByteIterableAssert(ArrayAdapter.adapt(objects).collectByte(Byte.class::cast).toStack()),
      n -> new ByteIterableAssert(ByteStacks.immutable.of(new byte[n])),
      () -> new ByteIterableAssert(ByteStacks.immutable.empty()),
      () -> new ByteIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(ByteStacks.immutable.of(new byte[n])));
  }

  private static byte[] distinctBytes(int n) {
    byte[] arr = new byte[n];
    for (int i = 0; i < n; i++) arr[i] = (byte) i;
    return arr;
  }

  static PrimitiveIterableAssertFactory<CharIterableAssert> charList() {
    return new PrimitiveIterableAssertFactory<>("Char List",
      objects -> new  CharIterableAssert(ArrayAdapter.adapt(objects).collectChar(Character.class::cast)),
      n -> new CharIterableAssert(CharLists.immutable.of(new char[n])),
      () -> new CharIterableAssert(CharLists.immutable.empty()),
      () -> new CharIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(CharLists.immutable.of(new char[n])));
  }

  static PrimitiveIterableAssertFactory<CharIterableAssert> charSet() {
    return new PrimitiveIterableAssertFactory<>("Char Set",
      objects -> new  CharIterableAssert(ArrayAdapter.adapt(objects).collectChar(Character.class::cast).toSet()),
      n -> new CharIterableAssert(CharSets.immutable.of(distinctChars(n))),
      () -> new CharIterableAssert(CharSets.immutable.empty()),
      () -> new CharIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(CharSets.immutable.of(distinctChars(n))));
  }

  static PrimitiveIterableAssertFactory<CharIterableAssert> charBag() {
    return new PrimitiveIterableAssertFactory<>("Char Bag",
      objects -> new  CharIterableAssert(ArrayAdapter.adapt(objects).collectChar(Character.class::cast).toBag()),
      n -> new CharIterableAssert(CharBags.immutable.of(new char[n])),
      () -> new CharIterableAssert(CharBags.immutable.empty()),
      () -> new CharIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(CharBags.immutable.of(new char[n])));
  }

  static PrimitiveIterableAssertFactory<CharIterableAssert> charStack() {
    return new PrimitiveIterableAssertFactory<>("Char Stack",
      objects -> new  CharIterableAssert(ArrayAdapter.adapt(objects).collectChar(Character.class::cast).toStack()),
      n -> new CharIterableAssert(CharStacks.immutable.of(new char[n])),
      () -> new CharIterableAssert(CharStacks.immutable.empty()),
      () -> new CharIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(CharStacks.immutable.of(new char[n])));
  }

  private static char[] distinctChars(int n) {
    char[] arr = new char[n];
    for (int i = 0; i < n; i++) arr[i] = (char) ('a' + i);
    return arr;
  }

  static PrimitiveIterableAssertFactory<DoubleIterableAssert> doubleList() {
    return new PrimitiveIterableAssertFactory<>("Double List",
      objects -> new  DoubleIterableAssert(ArrayAdapter.adapt(objects).collectDouble(Double.class::cast)),
      n -> new DoubleIterableAssert(DoubleLists.immutable.of(new double[n])),
      () -> new DoubleIterableAssert(DoubleLists.immutable.empty()),
      () -> new DoubleIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(DoubleLists.immutable.of(new double[n])));
  }

  static PrimitiveIterableAssertFactory<DoubleIterableAssert> doubleSet() {
    return new PrimitiveIterableAssertFactory<>("Double Set",
      objects -> new  DoubleIterableAssert(ArrayAdapter.adapt(objects).collectDouble(Double.class::cast).toSet()),
      n -> new DoubleIterableAssert(DoubleSets.immutable.of(distinctDoubles(n))),
      () -> new DoubleIterableAssert(DoubleSets.immutable.empty()),
      () -> new DoubleIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(DoubleSets.immutable.of(distinctDoubles(n))));
  }

  static PrimitiveIterableAssertFactory<DoubleIterableAssert> doubleBag() {
    return new PrimitiveIterableAssertFactory<>("Double Bag",
      objects -> new  DoubleIterableAssert(ArrayAdapter.adapt(objects).collectDouble(Double.class::cast).toBag()),
      n -> new DoubleIterableAssert(DoubleBags.immutable.of(new double[n])),
      () -> new DoubleIterableAssert(DoubleBags.immutable.empty()),
      () -> new DoubleIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(DoubleBags.immutable.of(new double[n])));
  }

  static PrimitiveIterableAssertFactory<DoubleIterableAssert> doubleStack() {
    return new PrimitiveIterableAssertFactory<>("Double Stack",
      objects -> new  DoubleIterableAssert(ArrayAdapter.adapt(objects).collectDouble(Double.class::cast).toStack()),
      n -> new DoubleIterableAssert(DoubleStacks.immutable.of(new double[n])),
      () -> new DoubleIterableAssert(DoubleStacks.immutable.empty()),
      () -> new DoubleIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(DoubleStacks.immutable.of(new double[n])));
  }

  private static double[] distinctDoubles(int n) {
    double[] arr = new double[n];
    for (int i = 0; i < n; i++) arr[i] = i;
    return arr;
  }

  static PrimitiveIterableAssertFactory<FloatIterableAssert> floatList() {
    return new PrimitiveIterableAssertFactory<>("Float List",
      objects -> new  FloatIterableAssert(ArrayAdapter.adapt(objects).collectFloat(Float.class::cast)),
      n -> new FloatIterableAssert(FloatLists.immutable.of(new float[n])),
      () -> new FloatIterableAssert(FloatLists.immutable.empty()),
      () -> new FloatIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(FloatLists.immutable.of(new float[n])));
  }

  static PrimitiveIterableAssertFactory<FloatIterableAssert> floatSet() {
    return new PrimitiveIterableAssertFactory<>("Float Set",
      objects -> new  FloatIterableAssert(ArrayAdapter.adapt(objects).collectFloat(Float.class::cast).toSet()),
      n -> new FloatIterableAssert(FloatSets.immutable.of(distinctFloats(n))),
      () -> new FloatIterableAssert(FloatSets.immutable.empty()),
      () -> new FloatIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(FloatSets.immutable.of(distinctFloats(n))));
  }

  static PrimitiveIterableAssertFactory<FloatIterableAssert> floatBag() {
    return new PrimitiveIterableAssertFactory<>("Float Bag",
      objects -> new  FloatIterableAssert(ArrayAdapter.adapt(objects).collectFloat(Float.class::cast).toBag()),
      n -> new FloatIterableAssert(FloatBags.immutable.of(new float[n])),
      () -> new FloatIterableAssert(FloatBags.immutable.empty()),
      () -> new FloatIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(FloatBags.immutable.of(new float[n])));
  }

  static PrimitiveIterableAssertFactory<FloatIterableAssert> floatStack() {
    return new PrimitiveIterableAssertFactory<>("Float Stack",
      objects -> new  FloatIterableAssert(ArrayAdapter.adapt(objects).collectFloat(Float.class::cast).toStack()),
      n -> new FloatIterableAssert(FloatStacks.immutable.of(new float[n])),
      () -> new FloatIterableAssert(FloatStacks.immutable.empty()),
      () -> new FloatIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(FloatStacks.immutable.of(new float[n])));
  }

  private static float[] distinctFloats(int n) {
    float[] arr = new float[n];
    for (int i = 0; i < n; i++) arr[i] = i;
    return arr;
  }

  static PrimitiveIterableAssertFactory<IntIterableAssert> intList() {
    return new PrimitiveIterableAssertFactory<>("Int List",
      objects -> new  IntIterableAssert(ArrayAdapter.adapt(objects).collectInt(Integer.class::cast)),
      n -> new IntIterableAssert(IntLists.immutable.of(new int[n])),
      () -> new IntIterableAssert(IntLists.immutable.empty()),
      () -> new IntIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(IntLists.immutable.of(new int[n])));
  }

  static PrimitiveIterableAssertFactory<IntIterableAssert> intSet() {
    return new PrimitiveIterableAssertFactory<>("Int Set",
      objects -> new  IntIterableAssert(ArrayAdapter.adapt(objects).collectInt(Integer.class::cast).toSet()),
      n -> new IntIterableAssert(IntSets.immutable.of(distinctInts(n))),
      () -> new IntIterableAssert(IntSets.immutable.empty()),
      () -> new IntIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(IntSets.immutable.of(distinctInts(n))));
  }

  static PrimitiveIterableAssertFactory<IntIterableAssert> intBag() {
    return new PrimitiveIterableAssertFactory<>("Int Bag",
      objects -> new  IntIterableAssert(ArrayAdapter.adapt(objects).collectInt(Integer.class::cast).toBag()),
      n -> new IntIterableAssert(IntBags.immutable.of(new int[n])),
      () -> new IntIterableAssert(IntBags.immutable.empty()),
      () -> new IntIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(IntBags.immutable.of(new int[n])));
  }

  static PrimitiveIterableAssertFactory<IntIterableAssert> intStack() {
    return new PrimitiveIterableAssertFactory<>("Int Stack",
      objects -> new  IntIterableAssert(ArrayAdapter.adapt(objects).collectInt(Integer.class::cast).toStack()),
      n -> new IntIterableAssert(IntStacks.immutable.of(new int[n])),
      () -> new IntIterableAssert(IntStacks.immutable.empty()),
      () -> new IntIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(IntStacks.immutable.of(new int[n])));
  }

  private static int[] distinctInts(int n) {
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) arr[i] = i;
    return arr;
  }

  static PrimitiveIterableAssertFactory<LongIterableAssert> longList() {
    return new PrimitiveIterableAssertFactory<>("Long List",
      objects -> new  LongIterableAssert(ArrayAdapter.adapt(objects).collectLong(Long.class::cast)),
      n -> new LongIterableAssert(LongLists.immutable.of(new long[n])),
      () -> new LongIterableAssert(LongLists.immutable.empty()),
      () -> new LongIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(LongLists.immutable.of(new long[n])));
  }

  static PrimitiveIterableAssertFactory<LongIterableAssert> longSet() {
    return new PrimitiveIterableAssertFactory<>("Long Set",
      objects -> new  LongIterableAssert(ArrayAdapter.adapt(objects).collectLong(Long.class::cast).toSet()),
      n -> new LongIterableAssert(LongSets.immutable.of(distinctLongs(n))),
      () -> new LongIterableAssert(LongSets.immutable.empty()),
      () -> new LongIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(LongSets.immutable.of(distinctLongs(n))));
  }

  static PrimitiveIterableAssertFactory<LongIterableAssert> longBag() {
    return new PrimitiveIterableAssertFactory<>("Long Bag",
      objects -> new  LongIterableAssert(ArrayAdapter.adapt(objects).collectLong(Long.class::cast).toBag()),
      n -> new LongIterableAssert(LongBags.immutable.of(new long[n])),
      () -> new LongIterableAssert(LongBags.immutable.empty()),
      () -> new LongIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(LongBags.immutable.of(new long[n])));
  }

  static PrimitiveIterableAssertFactory<LongIterableAssert> longStack() {
    return new PrimitiveIterableAssertFactory<>("Long Stack",
      objects -> new  LongIterableAssert(ArrayAdapter.adapt(objects).collectLong(Long.class::cast).toStack()),
      n -> new LongIterableAssert(LongStacks.immutable.of(new long[n])),
      () -> new LongIterableAssert(LongStacks.immutable.empty()),
      () -> new LongIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(LongStacks.immutable.of(new long[n])));
  }

  private static long[] distinctLongs(int n) {
    long[] arr = new long[n];
    for (int i = 0; i < n; i++) arr[i] = i;
    return arr;
  }

  static PrimitiveIterableAssertFactory<ShortIterableAssert> shortList() {
    return new PrimitiveIterableAssertFactory<>("Short List",
      objects -> new  ShortIterableAssert(ArrayAdapter.adapt(objects).collectShort(Short.class::cast)),
      n -> new ShortIterableAssert(ShortLists.immutable.of(new short[n])),
      () -> new ShortIterableAssert(ShortLists.immutable.empty()),
      () -> new ShortIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(ShortLists.immutable.of(new short[n])));
  }

  static PrimitiveIterableAssertFactory<ShortIterableAssert> shortSet() {
    return new PrimitiveIterableAssertFactory<>("Short Set",
      objects -> new  ShortIterableAssert(ArrayAdapter.adapt(objects).collectShort(Short.class::cast).toSet()),
      n -> new ShortIterableAssert(ShortSets.immutable.of(distinctShorts(n))),
      () -> new ShortIterableAssert(ShortSets.immutable.empty()),
      () -> new ShortIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(ShortSets.immutable.of(distinctShorts(n))));
  }

  static PrimitiveIterableAssertFactory<ShortIterableAssert> shortBag() {
    return new PrimitiveIterableAssertFactory<>("Short Bag",
      objects -> new  ShortIterableAssert(ArrayAdapter.adapt(objects).collectShort(Short.class::cast).toBag()),
      n -> new ShortIterableAssert(ShortBags.immutable.of(new short[n])),
      () -> new ShortIterableAssert(ShortBags.immutable.empty()),
      () -> new ShortIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(ShortBags.immutable.of(new short[n])));
  }

  static PrimitiveIterableAssertFactory<ShortIterableAssert> shortStack() {
    return new PrimitiveIterableAssertFactory<>("Short Stack",
      objects -> new  ShortIterableAssert(ArrayAdapter.adapt(objects).collectShort(Short.class::cast).toStack()),
      n -> new ShortIterableAssert(ShortStacks.immutable.of(new short[n])),
      () -> new ShortIterableAssert(ShortStacks.immutable.empty()),
      () -> new ShortIterableAssert(null),
      (softAssertions, n) -> softAssertions.assertThat(ShortStacks.immutable.of(new short[n])));
  }

  private static short[] distinctShorts(int n) {
    short[] arr = new short[n];
    for (int i = 0; i < n; i++) arr[i] = (short) i;
    return arr;
  }
}

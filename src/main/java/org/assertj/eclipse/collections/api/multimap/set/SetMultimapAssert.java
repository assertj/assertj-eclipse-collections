/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2025-2025 the original author or authors.
 */
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

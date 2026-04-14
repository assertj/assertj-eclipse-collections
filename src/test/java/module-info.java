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
/**
 * Test module for AssertJ Eclipse Collections
 */
open module org.assertj.eclipse.collections.test {
  exports org.assertj.eclipse.collections.api.multimap;

  requires org.assertj.eclipse.collections;
  requires org.assertj.core;
  requires org.eclipse.collections.api;
  requires org.eclipse.collections.impl;
  requires org.junit.jupiter.api;
  requires org.junit.jupiter.params;
}

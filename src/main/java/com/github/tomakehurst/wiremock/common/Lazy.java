/*
 * Copyright (C) 2023 Thomas Akehurst
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.tomakehurst.wiremock.common;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class Lazy<T> {

  public static <T> Lazy<T> lazy(Supplier<T> supplier) {
    return new Lazy<>(supplier);
  }

  private final Supplier<T> supplier;
  private final AtomicReference<T> ref = new AtomicReference<>();

  private Lazy(Supplier<T> supplier) {
    this.supplier = supplier;
  }

  public T get() {
    return ref.updateAndGet(existing -> existing == null ? supplier.get() : existing);
  }
}

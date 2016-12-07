/*
 * Copyright 2016 Max Balushkin.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.noxchimaera.melchior.core.components;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Creates widgets for specified class or id.
 *
 * @author Max Balushkin
 */
public class WidgetFactory<T> {

    private Map<String, Supplier<MelchiorWidget<T>>> factories;

    /**
     * Creates empty factory.
     */
    public WidgetFactory() {
        factories = new HashMap<>();
    }

    /**
     * Registers factory for specified id.
     *
     * @param id      factory identifier (e.g. class name)
     * @param factory factory
     */
    public void register(String id, Supplier<MelchiorWidget<T>> factory) {
        factories.put(id, factory);
    }

    /**
     * Creates widget if appropriate factory exists or else returns null.
     *
     * @param cl factory id-class
     *
     * @return widget
     */
    public MelchiorWidget<T> get(Class cl) {
        return factories.getOrDefault(cl.getName(), () -> null).get();
    }

    /**
     * Creates widget if appropriate factory exists or else returns null.
     *
     * @param id factory id
     *
     * @return widget
     */
    public MelchiorWidget<T> get(String id) {
        return factories.getOrDefault(id, () -> null).get();
    }

}

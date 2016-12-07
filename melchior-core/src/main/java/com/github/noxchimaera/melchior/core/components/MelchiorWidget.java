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

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Wrapper for Java GUI component.
 *
 * @author Max Balushkin
 */
public class MelchiorWidget<T> {

    /**
     * Wrapped GUI component.
     */
    private T javaComponent;
    /**
     * Returns value from component.
     */
    private Function<T, Object> getter;
    /**
     * Sets component value.
     */
    private BiConsumer<T, Object> setter;

    /**
     * Creates GUI component wrapper.
     *
     * @param javaComponent Java GUI component
     * @param getter        the operation to get value from component
     * @param setter        the operation to set component value
     */
    public MelchiorWidget(T javaComponent, Function<T, Object> getter, BiConsumer<T, Object> setter) {
        this.javaComponent = javaComponent;
        this.getter = getter;
        this.setter = setter;
    }

    /**
     * Returns component value.
     *
     * @return value
     */
    public Object get() {
        return getter.apply(javaComponent);
    }

    /**
     * Sets component value.
     *
     * @param object new value
     */
    public void set(Object object) {
        setter.accept(javaComponent, object);
    }

    /**
     * Returns wrapped GUI component.
     *
     * @return GUI component
     */
    public T javaComponent() {
        return javaComponent;
    }

}

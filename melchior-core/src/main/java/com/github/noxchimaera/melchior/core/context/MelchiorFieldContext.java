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

package com.github.noxchimaera.melchior.core.context;

import java.lang.reflect.Method;

/**
 * Class field context.
 *
 * @author Max Balushkin
 */
public class MelchiorFieldContext {

    /**
     * Field type;
     */
    private Class fieldType;

    /**
     * Name of the field.
     */
    private String name;
    /**
     * Label of the field.
     */
    private String label;

    /**
     * Field getter.
     */
    private Method getter;
    /**
     * Field setter.
     */
    private Method setter;

    /**
     * Whether field is read only.
     */
    private boolean readonly;
    /**
     * Order.
     */
    private int order;

    /**
     * Creates new field context.
     *
     * @param name     field name
     * @param label    field label
     * @param getter   field getter
     * @param setter   field setter
     * @param readonly whether field is read only
     * @param order    field order
     */
    public MelchiorFieldContext(Class fieldType, String name, String label, Method getter, Method setter, boolean readonly, int order) {
        this.fieldType = fieldType;
        this.name = name;
        this.label = label;
        this.getter = getter;
        this.setter = setter;
        this.readonly = readonly;
        this.order = order;
    }

    /**
     * Returns field type.
     *
     * @return field type
     */
    public Class getFieldType() {
        return fieldType;
    }

    /**
     * Returns field name.
     *
     * @return field name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns field label.
     *
     * @return field label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns field getter.
     *
     * @return getter
     */
    public Method getGetter() {
        return getter;
    }

    /**
     * Returns field setter.
     *
     * @return setter
     */
    public Method getSetter() {
        return setter;
    }

    /**
     * Whether field is read only.
     *
     * @return {@code true} if field is read only, else {@code else}
     */
    public boolean isReadonly() {
        return readonly;
    }

    /**
     * Returns field order.
     *
     * @return order
     */
    public int getOrder() {
        return order;
    }

    @Override public String toString() {
        return "MelchiorFieldContext{" +
            "name='" + name + '\'' +
            ", label='" + label + '\'' +
            ", getter=" + getter +
            ", setter=" + setter +
            ", readonly=" + readonly +
            ", order=" + order +
            '}';
    }

}

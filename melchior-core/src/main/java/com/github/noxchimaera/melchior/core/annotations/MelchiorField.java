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

package com.github.noxchimaera.melchior.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Show field in GUI.
 *
 * @author Max Balushkin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MelchiorField {

    /**
     * Field label. <br>
     * If empty transforms field name in human-readable format. <br>
     * e.g.: {@code firstName} will be transformed to {@code First name}.
     *
     * @return field label
     */
    String label() default "";

    /**
     * Name of getter method. <br>
     * If empty Melchior will use method with name specified by JavaBeans Conventions. <br>
     * e.g. getter for {@code String firstName} is {@code String getFirstName()}.
     *
     * @return getter name
     */
    String readMethod() default "";

    /**
     * Name of setter method. <br>
     * If empty Melchior will use method with name specified by JavaBeans Conventions. <br>
     * e.g. setter for {@code String firstName} is {@code void setFirstName(String)}
     *
     * @return setter name
     */
    String writeMethod() default "";

    /**
     * If {@code true} field will be read only (not editable).
     *
     * @return whether field is read only (not editable)
     */
    boolean readOnly() default false;

    /**
     * Field order.
     *
     * @return order
     */
    int order() default Integer.MAX_VALUE;

}

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

import com.github.noxchimaera.melchior.core.annotations.MelchiorField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.github.noxchimaera.melchior.core.commons.StringUtils.*;

/**
 * Class context.
 *
 * @author Max Balushkin
 */
public class MelchiorClassContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(MelchiorClassContext.class);

    /**
     * Creates Melchior context of specified class.
     *
     * @param ofClass class
     *
     * @return class context
     */
    public static MelchiorClassContext of(Class ofClass) {
        Field[] fields = ofClass.getDeclaredFields();
        List<MelchiorFieldContext> fieldContext = new ArrayList<>(fields.length);
        for (Field field : fields) {
            MelchiorField f = field.getAnnotation(MelchiorField.class);
            if (f == null) {
                continue;
            }
            String getterName = f.readMethod();
            Method getter = null;
            if (getterName.trim().isEmpty()) {
                getterName = "get" + capitalize(field.getName());
            }
            try {
                getter = ofClass.getMethod(getterName);
            } catch (ReflectiveOperationException ex) {
                String msg = String.format("Can't find getter for `%s` in `%s`", field.getName(), ofClass.getName());
                LOGGER.error(msg);
                throw new MelchiorContextException(msg, ex);
            }

            String setterName = f.writeMethod();
            Method setter = null;
            if (!f.readOnly()) {
                if (setterName.trim().isEmpty()) {
                    setterName = "set" + capitalize(field.getName());
                }
                try {
                    setter = ofClass.getMethod(setterName, field.getType());
                } catch (ReflectiveOperationException ex) {
                    String msg = String.format("Can't find setter for `%s` in `%s`", field.getName(), ofClass.getName());
                    LOGGER.error(msg);
                    throw new MelchiorContextException(msg, ex);
                }
            }

            String label = f.label().trim().isEmpty() ? toReadable(field.getName()) : f.label();
            MelchiorFieldContext ctx = new MelchiorFieldContext(field.getName(), label, getter, setter, f.readOnly(), f.order());
        }
        return new MelchiorClassContext(ofClass, fieldContext);
    }

    private Class ofClass;
    private List<MelchiorFieldContext> fields;

    /**
     * Creates class context.
     *
     * @param ofClass class
     * @param fields  fields context
     */
    public MelchiorClassContext(Class ofClass, List<MelchiorFieldContext> fields) {
        this.ofClass = ofClass;
        this.fields = fields;
    }

    /**
     * Returns class.
     *
     * @return class
     */
    public Class getOfClass() {
        return ofClass;
    }

    /**
     * Returns fields context. <br>
     * Any changes of returned list (adding or deleting items) will not affect original list.
     *
     * @return fields context
     */
    public List<MelchiorFieldContext> getFields() {
        return new ArrayList<>(fields);
    }

    @Override public String toString() {
        return "MelchiorClassContext{" +
            "ofClass=" + ofClass +
            ", fields=" + fields +
            '}';
    }

}

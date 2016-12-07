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

package com.github.noxchimaera.melchior.core.commons;

/**
 * String utilities.
 *
 * @author Max Balushkin
 */
public class StringUtils {

    /**
     * Capitalizes string.
     *
     * @param src source string
     *
     * @return capitalized string
     */
    public static String capitalize(String src) {
        return src.substring(0, 1).toUpperCase() + src.substring(1);
    }

    /**
     * Transforms string to human-readable format. <br>
     * e.g. {@code someClassField} transforms to {@code Some class field}.
     *
     * @param src source string
     *
     * @return human-readable string
     */
    public static String toReadable(String src) {
        StringBuilder b = new StringBuilder();
        final int n = src.length();
        for (int i = 0; i < n; ++i) {
            char ch = src.charAt(i);
            if (i == 0) {
                b.append(Character.toUpperCase(ch));
            } else if (Character.isUpperCase(ch)) {
                b.append(' ');
                b.append(Character.toLowerCase(ch));
            } else {
                b.append(ch);
            }
        }
        return b.toString();
    }

}

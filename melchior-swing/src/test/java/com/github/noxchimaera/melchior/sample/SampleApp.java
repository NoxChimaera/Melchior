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

package com.github.noxchimaera.melchior.sample;

import com.github.noxchimaera.melchior.core.annotations.MelchiorField;
import com.github.noxchimaera.melchior.core.components.MelchiorWidget;
import com.github.noxchimaera.melchior.core.components.WidgetFactory;
import com.github.noxchimaera.melchior.core.context.MelchiorClassContext;
import com.github.noxchimaera.melchior.swing.builders.BasicSwingBuilder;

import javax.swing.*;

/**
 * @author Max Balushkin
 */
public class SampleApp {

    private static void prepareFactory(WidgetFactory<JComponent> fct) {
        fct.register(String.class.getName(), () -> new MelchiorWidget<JComponent>(
            new JTextField(),
            cmpt -> ((JTextField)cmpt).getText(),
            (cmpt, o) -> ((JTextField)cmpt).setText(o.toString())
        ));
        fct.register(int.class.getName(), () -> new MelchiorWidget<JComponent>(
            new JSpinner(),
            cmpt -> ((JSpinner)cmpt).getValue(),
            (cmpt, o) -> ((JSpinner)cmpt).setValue(o)
        ));
    }

    public static void main(String[] args) throws Exception {
        MelchiorClassContext ctx = MelchiorClassContext.of(Person.class);
        WidgetFactory<JComponent> fct = new WidgetFactory<>();
        prepareFactory(fct);

        BasicSwingBuilder b = new BasicSwingBuilder(ctx, fct);
        JFrame frame = b.create(
            data -> System.out.println(data),
            () -> { }
        );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}

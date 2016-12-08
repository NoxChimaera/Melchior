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

package com.github.noxchimaera.melchior.swing.builders;

import com.github.noxchimaera.melchior.core.commons.StringUtils;
import com.github.noxchimaera.melchior.core.commons.functional.Proc;
import com.github.noxchimaera.melchior.core.components.MelchiorWidget;
import com.github.noxchimaera.melchior.core.components.WidgetFactory;
import com.github.noxchimaera.melchior.core.context.MelchiorClassContext;
import com.github.noxchimaera.melchior.core.context.MelchiorFieldContext;
import com.github.noxchimaera.melchior.swing.utils.GridConstraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.github.noxchimaera.melchior.swing.utils.GridConstraint.*;

/**
 * @param <T> data type
 * @param <J> UI component type
 *
 * @author Max Balushkin
 */
public class BasicSwingBuilder<T, J extends JComponent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicSwingBuilder.class);

    private MelchiorClassContext context;
    private WidgetFactory<J> factory;

    private Map<String, MelchiorWidget<J>> fieldMap;

    private JPanel controlPanel;
    private JButton cancelButton;
    private JButton okButton;

    private T data;

    public BasicSwingBuilder(MelchiorClassContext context, WidgetFactory<J> factory) {
        this.context = context;
        this.factory = factory;
        fieldMap = new HashMap<>();
    }

    public MelchiorWidget<J> getWidget(String field) {
        return fieldMap.get(field);
    }

    public JFrame create(Consumer<T> onOk, Proc onCancel) throws ReflectiveOperationException {
        JFrame f = init();
        okButton.addActionListener(e -> { save(); onOk.accept(data); f.setVisible(false); });
        cancelButton.addActionListener(e -> { f.setVisible(false); onCancel.exec(); });
        return f;
    }

    private JFrame init() throws ReflectiveOperationException {
        data = (T)context.getOfClass().newInstance();
        JFrame frame = new JFrame();

        Box box = Box.createVerticalBox();
        frame.setContentPane(box);

        JLabel header = new JLabel(StringUtils.toReadable(context.getOfClass().getSimpleName()));
        header.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        box.add(header);

        Container root = new JPanel(new GridBagLayout());
        box.add(root);

        int i = 0;
        List<MelchiorFieldContext> fs = context.getFields();
        fs.sort(Comparator.comparingInt(MelchiorFieldContext::getOrder));
        for (MelchiorFieldContext f : fs) {
            String label = f.getLabel().isEmpty()
                ? StringUtils.toReadable(f.getName())
                : f.getLabel();
            root.add(new JLabel(label), at(0, i)
                .anchor(AnchorAbsolute.WEST).insets(5, 5, 5, 5).build());

            MelchiorWidget<J> widget = factory.get(f.getFieldType());
            JComponent cmpt = widget.javaComponent();

            widget.set(f.getGetter().invoke(data));

            root.add(cmpt, GridConstraint
                .at(1, i).insets(5, 5, 5, 5).weight(1, 0)
                .fill(Fill.HORIZONTAL).width(GridBagConstraints.REMAINDER).build());
            if (f.isReadonly()) {
                cmpt.setEnabled(false);
            }
            fieldMap.put(f.getName(), widget);

            ++i;
        }

        box.add(Box.createVerticalStrut(8));

        controlPanel = new JPanel(new FlowLayout());
        okButton = new JButton("Ok");
        controlPanel.add(okButton);

        cancelButton = new JButton("Cancel");
        controlPanel.add(cancelButton);

        controlPanel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        box.add(controlPanel);

        frame.pack();
        return frame;
    }

    private void save() {
        try {
            Object obj = context.getOfClass().newInstance();

            for (MelchiorFieldContext fieldContext : context.getFields()) {
                if (fieldContext.isReadonly()) {
                    continue;
                }
                Method setter = fieldContext.getSetter();
                MelchiorWidget<J> cmpt = fieldMap.get(fieldContext.getName());
                Object value =  cmpt.get();
                setter.invoke(data, value);
            }

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("Can't create object", e);
        }

    }

    public T getData() {
        return data;
    }

}

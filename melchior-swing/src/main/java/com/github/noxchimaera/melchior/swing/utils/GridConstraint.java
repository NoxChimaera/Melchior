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

package com.github.noxchimaera.melchior.swing.utils;

import java.awt.*;

/**
 * GraiBagLayout constraint builder.
 *
 * @author Max Balushkin
 */
public class GridConstraint {

    /**
     * Enum-wrapper for AnchorAbsolute property.
     */
    public enum AnchorAbsolute {
        /**
         * Centre.
         */
        CENTER(GridBagConstraints.CENTER),
        /**
         * North, Top.
         */
        NORTH(GridBagConstraints.NORTH),
        /**
         * North-east, Top-right.
         */
        NORTHEAST(GridBagConstraints.NORTHEAST),
        /**
         * East, right.
         */
        EAST(GridBagConstraints.EAST),
        /**
         * South-east, Bottom-right.
         */
        SOUTHEAST(GridBagConstraints.SOUTHEAST),
        /**
         * South, Bottom.
         */
        SOUTH(GridBagConstraints.SOUTH),
        /**
         * South-west, Bottom-left.
         */
        SOUTHWEST(GridBagConstraints.SOUTHWEST),
        /**
         * West, Left.
         */
        WEST(GridBagConstraints.WEST),
        /**
         * North-west, Top-left.
         */
        NORTHWEST(GridBagConstraints.NORTHWEST);

        private final int swingCnst;

        private AnchorAbsolute(int aSwingCnst) {
            swingCnst = aSwingCnst;
        }

        /**
         * Returns Swing constant value.
         *
         * @return constant value
         */
        public int asSwingConstant() {
            return swingCnst;
        }
    }

    /**
     * Enum-wrapper for Fill mode.
     */
    public enum Fill {
        /**
         * Do not resize the component.
         */
        NONE(GridBagConstraints.NONE),
        /**
         * Resize the component horizontally but not vertically.
         */
        HORIZONTAL(GridBagConstraints.HORIZONTAL),
        /**
         * Resize the component vertically but not horizontally.
         */
        VERTICAL(GridBagConstraints.VERTICAL),
        /**
         * Resize the component both horizontally and vertically.
         */
        BOTH(GridBagConstraints.BOTH);

        private final int swingCnst;

        private Fill(int aSwingCnst) {
            swingCnst = aSwingCnst;
        }

        /**
         * Returns wrapped value.
         *
         * @return constant value
         */
        public int asSwingConstant() {
            return swingCnst;
        }
    }

    /**
     * Creates new grid cell at specified position.
     *
     * @param aX horizontal position
     * @param aY vertical position
     *
     * @return grid
     */
    public static GridConstraint at(int aX, int aY) {
        return new GridConstraint(aX, aY);
    }

    private int gridX = 0;
    private int gridY = 0;
    private int gridWidth = 1;
    private int gridHeight = 1;
    private double weightX = 0;
    private double weightY = 0;
    private int anchor = GridBagConstraints.CENTER;
    private int fill = GridBagConstraints.NONE;
    private Insets insets = new Insets(0, 0, 0, 0);
    private int padX = 0;
    private int padY = 0;

    /**
     * Creates grid cell at specified position.
     *
     * @param aX horizontal position
     * @param aY vertical position
     */
    public GridConstraint(int aX, int aY) {
        gridX = aX;
        gridY = aY;
    }

    /**
     * Sets cell width.
     *
     * @param aWidth cell width
     *
     * @return self
     */
    public GridConstraint width(int aWidth) {
        gridWidth = aWidth;
        return this;
    }

    /**
     * Sets cell height.
     *
     * @param aHeight cell height
     *
     * @return self
     */
    public GridConstraint height(int aHeight) {
        gridHeight = aHeight;
        return this;
    }

    /**
     * Sets cell size.
     *
     * @param aWidth  cell width
     * @param aHeight cell height
     *
     * @return self
     */
    public GridConstraint size(int aWidth, int aHeight) {
        gridWidth = aWidth;
        gridHeight = aHeight;
        return this;
    }

    public GridConstraint weightHorizontal(double aWeightX) {
        weightX = aWeightX;
        return this;
    }

    public GridConstraint weightVertical(double aWeightY) {
        weightY = aWeightY;
        return this;
    }

    public GridConstraint weight(double aHorizontal, double aVertical) {
        weightX = aHorizontal;
        weightY = aVertical;
        return this;
    }

    public GridConstraint anchor(AnchorAbsolute aAnchor) {
        anchor = aAnchor.asSwingConstant();
        return this;
    }

    public GridConstraint fill(Fill aFill) {
        fill = aFill.asSwingConstant();
        return this;
    }

    public GridConstraint insets(int aTop, int aLeft, int aBottom, int aRight) {
        insets = new Insets(aTop, aLeft, aBottom, aRight);
        return this;
    }

    public GridConstraint internalPaddings(int aHorizontal, int aVertical) {
        padX = aHorizontal;
        padY = aVertical;
        return this;
    }

    /**
     * Creates GridBagConstrains.
     *
     * @return GridBagLayout constraint
     */
    public GridBagConstraints build() {
        return new GridBagConstraints(
            gridX, gridY, gridWidth, gridHeight, weightX, weightY, anchor, fill, insets, padX, padY);
    }

}

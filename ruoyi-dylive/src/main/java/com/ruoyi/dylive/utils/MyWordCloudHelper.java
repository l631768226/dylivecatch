package com.ruoyi.dylive.utils;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.word.cloud.support.background.Backgrounds;
import com.github.houbb.word.cloud.support.background.IBackground;
import com.github.houbb.word.cloud.support.color.ColorPalettes;
import com.github.houbb.word.cloud.support.color.IColorPalette;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.palette.ColorPalette;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public final class MyWordCloudHelper {
    private MyWordCloudHelper() {
    }

    public static BufferedImage wordCloud(String text, String backgroundImagePath) throws IOException {
        IBackground background = Backgrounds.none();
        if (StringUtil.isNotEmptyTrim(backgroundImagePath)) {
            background = Backgrounds.image(backgroundImagePath);
        }

        ColorPalette colorPalette = new ColorPalette(getColors());
        IColorPalette iColorPalette = ColorPalettes.arrayColor(getColors());

        return MyWordCloudBs.newInstance()
                .text(text)
                .padding(10)
                .limit(20)
                .colorPalette(iColorPalette)
                .collisionMode(CollisionMode.PIXEL_PERFECT)
                .wordCloud();
    }

    public static BufferedImage wordCloud(String text) throws IOException {
        return wordCloud(text, null);
    }


    private static Color[] getColors() {
        int[][] colors = {
                {12, 57, 176},
                {16, 83, 190},
                {18, 102, 201},
                {21, 122, 214},
                {25, 140, 220},
                {29, 161, 223},
                {32, 180, 226},
                {37, 205, 231}
        };

        Color[] colorArray = new Color[colors.length];
        for (int i = 0; i < colors.length; i++) {
            int r = colors[i][0];
            int g = colors[i][1];
            int b = colors[i][2];

            Color color = new Color(r, g, b);
            colorArray[i] = color;
        }
        return colorArray;
    }

}


package com.ruoyi.dylive.utils;

import com.github.houbb.word.cloud.support.background.Backgrounds;
import com.github.houbb.word.cloud.support.background.IBackground;
import com.github.houbb.word.cloud.support.color.ColorPalettes;
import com.github.houbb.word.cloud.support.color.IColorPalette;
import com.github.houbb.word.cloud.support.font.IWordKumoFont;
import com.github.houbb.word.cloud.support.font.WordKumoFonts;
import com.github.houbb.word.cloud.support.fontscalar.IWordFontScalar;
import com.github.houbb.word.cloud.support.fontscalar.WordFontScalars;
import com.github.houbb.word.cloud.support.freq.IWordFrequency;
import com.github.houbb.word.cloud.support.freq.WordFrequencies;
import com.github.houbb.word.cloud.support.freq.WordFrequencyContext;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.Background;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.image.AngleGenerator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class MyWordCloudBs {
    private String outPath = "out.png";
    private String text = "我爱云图，云图爱我";
    private int width = 500;
    private int height = 500;
    private int padding = 0;
    private CollisionMode collisionMode;
    private Color backgroundColor;
    private IWordFrequency wordFrequency;
    private IBackground background;
    private IColorPalette colorPalette;
    private IWordFontScalar fontScalar;
    private IWordKumoFont kumoFont;
    private int limit;

    private MyWordCloudBs() {
        this.collisionMode = CollisionMode.PIXEL_PERFECT;
        this.backgroundColor = Color.WHITE;
        this.wordFrequency = WordFrequencies.defaults();
        this.background = Backgrounds.none();
        this.colorPalette = ColorPalettes.random();
        this.fontScalar = WordFontScalars.linear();
        this.kumoFont = WordKumoFonts.kumo();
        this.limit = Integer.MAX_VALUE;
    }

    public static MyWordCloudBs newInstance() {
        return new MyWordCloudBs();
    }

    public MyWordCloudBs outPath(String outPath) {
        this.outPath = outPath;
        return this;
    }

    public MyWordCloudBs text(String text) {
        this.text = text;
        return this;
    }

    public MyWordCloudBs width(int width) {
        this.width = width;
        return this;
    }

    public MyWordCloudBs height(int height) {
        this.height = height;
        return this;
    }

    public MyWordCloudBs padding(int padding) {
        this.padding = padding;
        return this;
    }

    public MyWordCloudBs collisionMode(CollisionMode collisionMode) {
        this.collisionMode = collisionMode;
        return this;
    }

    public MyWordCloudBs backgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public MyWordCloudBs wordFrequency(IWordFrequency wordFrequency) {
        this.wordFrequency = wordFrequency;
        return this;
    }

    public MyWordCloudBs background(IBackground background) {
        this.background = background;
        return this;
    }

    public MyWordCloudBs colorPalette(IColorPalette colorPalette) {
        this.colorPalette = colorPalette;
        return this;
    }

    public MyWordCloudBs fontScalar(IWordFontScalar fontScalar) {
        this.fontScalar = fontScalar;
        return this;
    }

    public MyWordCloudBs kumoFont(IWordKumoFont kumoFont) {
        this.kumoFont = kumoFont;
        return this;
    }

    public MyWordCloudBs limit(int limit) {
        this.limit = limit;
        return this;
    }

    public BufferedImage wordCloud() {
        WordFrequencyContext frequencyContext = new WordFrequencyContext();
        frequencyContext.text(this.text).limit(this.limit);
        List<WordFrequency> wordFrequencies = this.wordFrequency.freq(frequencyContext);
        Dimension dimension = new Dimension(this.width, this.height);
        WordCloud wordCloud = new WordCloud(dimension, this.collisionMode);
        wordCloud.setPadding(this.padding);
        wordCloud.setBackgroundColor(this.backgroundColor);
        wordCloud.setColorPalette(this.colorPalette.color());
        wordCloud.setAngleGenerator(new AngleGenerator(0));
        Background backgroundVal = this.background.background();
        if (backgroundVal != null) {
            wordCloud.setBackground(backgroundVal);
        }else{
            wordCloud.setBackground(new CircleBackground(165));
        }

        wordCloud.setFontScalar(this.fontScalar.fontScalar());
        wordCloud.setKumoFont(this.kumoFont.font());
        wordCloud.build(wordFrequencies);
        return wordCloud.getBufferedImage();
    }
}


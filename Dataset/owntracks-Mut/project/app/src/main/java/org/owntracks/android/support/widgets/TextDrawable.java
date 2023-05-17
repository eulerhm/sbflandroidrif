package org.owntracks.android.support.widgets;

import android.graphics.*;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TextDrawable extends ShapeDrawable {

    private final Paint textPaint;

    private final Paint borderPaint;

    private static final float SHADE_FACTOR = 0.9f;

    private final String text;

    private final RectShape shape;

    private final int height;

    private final int width;

    private final int fontSize;

    private final float radius;

    private final int borderThickness;

    private static final Typeface typeface = Typeface.create("sans-serif-light", Typeface.NORMAL);

    private TextDrawable(Builder builder) {
        super(builder.shape);
        int color;
        // shape properties
        shape = builder.shape;
        height = builder.height;
        width = builder.width;
        radius = builder.radius;
        // text and color
        text = builder.toUpperCase ? builder.text.toUpperCase(Locale.getDefault()) : builder.text;
        color = builder.color;
        // text paint settings
        fontSize = builder.fontSize;
        textPaint = new Paint();
        if (!ListenerUtil.mutListener.listen(1063)) {
            textPaint.setColor(builder.textColor);
        }
        if (!ListenerUtil.mutListener.listen(1064)) {
            textPaint.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(1065)) {
            textPaint.setFakeBoldText(builder.isBold);
        }
        if (!ListenerUtil.mutListener.listen(1066)) {
            textPaint.setStyle(Paint.Style.FILL);
        }
        if (!ListenerUtil.mutListener.listen(1067)) {
            textPaint.setTypeface(typeface);
        }
        if (!ListenerUtil.mutListener.listen(1068)) {
            textPaint.setTextAlign(Paint.Align.CENTER);
        }
        if (!ListenerUtil.mutListener.listen(1069)) {
            textPaint.setStrokeWidth(builder.borderThickness);
        }
        // border paint settings
        borderThickness = builder.borderThickness;
        borderPaint = new Paint();
        if (!ListenerUtil.mutListener.listen(1070)) {
            borderPaint.setColor(getDarkerShade(color));
        }
        if (!ListenerUtil.mutListener.listen(1071)) {
            borderPaint.setStyle(Paint.Style.STROKE);
        }
        if (!ListenerUtil.mutListener.listen(1072)) {
            borderPaint.setStrokeWidth(borderThickness);
        }
        // drawable paint color
        Paint paint = getPaint();
        if (!ListenerUtil.mutListener.listen(1073)) {
            paint.setColor(color);
        }
    }

    private int getDarkerShade(int color) {
        return Color.rgb((int) ((ListenerUtil.mutListener.listen(1077) ? (SHADE_FACTOR % Color.red(color)) : (ListenerUtil.mutListener.listen(1076) ? (SHADE_FACTOR / Color.red(color)) : (ListenerUtil.mutListener.listen(1075) ? (SHADE_FACTOR - Color.red(color)) : (ListenerUtil.mutListener.listen(1074) ? (SHADE_FACTOR + Color.red(color)) : (SHADE_FACTOR * Color.red(color))))))), (int) ((ListenerUtil.mutListener.listen(1081) ? (SHADE_FACTOR % Color.green(color)) : (ListenerUtil.mutListener.listen(1080) ? (SHADE_FACTOR / Color.green(color)) : (ListenerUtil.mutListener.listen(1079) ? (SHADE_FACTOR - Color.green(color)) : (ListenerUtil.mutListener.listen(1078) ? (SHADE_FACTOR + Color.green(color)) : (SHADE_FACTOR * Color.green(color))))))), (int) ((ListenerUtil.mutListener.listen(1085) ? (SHADE_FACTOR % Color.blue(color)) : (ListenerUtil.mutListener.listen(1084) ? (SHADE_FACTOR / Color.blue(color)) : (ListenerUtil.mutListener.listen(1083) ? (SHADE_FACTOR - Color.blue(color)) : (ListenerUtil.mutListener.listen(1082) ? (SHADE_FACTOR + Color.blue(color)) : (SHADE_FACTOR * Color.blue(color))))))));
    }

    @Override
    public void draw(Canvas canvas) {
        if (!ListenerUtil.mutListener.listen(1086)) {
            super.draw(canvas);
        }
        Rect r = getBounds();
        if (!ListenerUtil.mutListener.listen(1093)) {
            // draw border
            if ((ListenerUtil.mutListener.listen(1091) ? (borderThickness >= 0) : (ListenerUtil.mutListener.listen(1090) ? (borderThickness <= 0) : (ListenerUtil.mutListener.listen(1089) ? (borderThickness < 0) : (ListenerUtil.mutListener.listen(1088) ? (borderThickness != 0) : (ListenerUtil.mutListener.listen(1087) ? (borderThickness == 0) : (borderThickness > 0))))))) {
                if (!ListenerUtil.mutListener.listen(1092)) {
                    drawBorder(canvas);
                }
            }
        }
        int count = canvas.save();
        if (!ListenerUtil.mutListener.listen(1094)) {
            canvas.translate(r.left, r.top);
        }
        // draw text
        int width = (ListenerUtil.mutListener.listen(1099) ? (this.width >= 0) : (ListenerUtil.mutListener.listen(1098) ? (this.width <= 0) : (ListenerUtil.mutListener.listen(1097) ? (this.width > 0) : (ListenerUtil.mutListener.listen(1096) ? (this.width != 0) : (ListenerUtil.mutListener.listen(1095) ? (this.width == 0) : (this.width < 0)))))) ? r.width() : this.width;
        int height = (ListenerUtil.mutListener.listen(1104) ? (this.height >= 0) : (ListenerUtil.mutListener.listen(1103) ? (this.height <= 0) : (ListenerUtil.mutListener.listen(1102) ? (this.height > 0) : (ListenerUtil.mutListener.listen(1101) ? (this.height != 0) : (ListenerUtil.mutListener.listen(1100) ? (this.height == 0) : (this.height < 0)))))) ? r.height() : this.height;
        int fontSize = (ListenerUtil.mutListener.listen(1109) ? (this.fontSize >= 0) : (ListenerUtil.mutListener.listen(1108) ? (this.fontSize <= 0) : (ListenerUtil.mutListener.listen(1107) ? (this.fontSize > 0) : (ListenerUtil.mutListener.listen(1106) ? (this.fontSize != 0) : (ListenerUtil.mutListener.listen(1105) ? (this.fontSize == 0) : (this.fontSize < 0)))))) ? ((ListenerUtil.mutListener.listen(1113) ? (Math.min(width, height) % 2) : (ListenerUtil.mutListener.listen(1112) ? (Math.min(width, height) * 2) : (ListenerUtil.mutListener.listen(1111) ? (Math.min(width, height) - 2) : (ListenerUtil.mutListener.listen(1110) ? (Math.min(width, height) + 2) : (Math.min(width, height) / 2)))))) : this.fontSize;
        if (!ListenerUtil.mutListener.listen(1114)) {
            textPaint.setTextSize(fontSize);
        }
        if (!ListenerUtil.mutListener.listen(1131)) {
            canvas.drawText(text, (ListenerUtil.mutListener.listen(1118) ? (width % 2) : (ListenerUtil.mutListener.listen(1117) ? (width * 2) : (ListenerUtil.mutListener.listen(1116) ? (width - 2) : (ListenerUtil.mutListener.listen(1115) ? (width + 2) : (width / 2))))), (ListenerUtil.mutListener.listen(1130) ? ((ListenerUtil.mutListener.listen(1122) ? ((float) height % 2) : (ListenerUtil.mutListener.listen(1121) ? ((float) height * 2) : (ListenerUtil.mutListener.listen(1120) ? ((float) height - 2) : (ListenerUtil.mutListener.listen(1119) ? ((float) height + 2) : ((float) height / 2))))) % ((ListenerUtil.mutListener.listen(1126) ? ((textPaint.descent() + textPaint.ascent()) % 2) : (ListenerUtil.mutListener.listen(1125) ? ((textPaint.descent() + textPaint.ascent()) * 2) : (ListenerUtil.mutListener.listen(1124) ? ((textPaint.descent() + textPaint.ascent()) - 2) : (ListenerUtil.mutListener.listen(1123) ? ((textPaint.descent() + textPaint.ascent()) + 2) : ((textPaint.descent() + textPaint.ascent()) / 2))))))) : (ListenerUtil.mutListener.listen(1129) ? ((ListenerUtil.mutListener.listen(1122) ? ((float) height % 2) : (ListenerUtil.mutListener.listen(1121) ? ((float) height * 2) : (ListenerUtil.mutListener.listen(1120) ? ((float) height - 2) : (ListenerUtil.mutListener.listen(1119) ? ((float) height + 2) : ((float) height / 2))))) / ((ListenerUtil.mutListener.listen(1126) ? ((textPaint.descent() + textPaint.ascent()) % 2) : (ListenerUtil.mutListener.listen(1125) ? ((textPaint.descent() + textPaint.ascent()) * 2) : (ListenerUtil.mutListener.listen(1124) ? ((textPaint.descent() + textPaint.ascent()) - 2) : (ListenerUtil.mutListener.listen(1123) ? ((textPaint.descent() + textPaint.ascent()) + 2) : ((textPaint.descent() + textPaint.ascent()) / 2))))))) : (ListenerUtil.mutListener.listen(1128) ? ((ListenerUtil.mutListener.listen(1122) ? ((float) height % 2) : (ListenerUtil.mutListener.listen(1121) ? ((float) height * 2) : (ListenerUtil.mutListener.listen(1120) ? ((float) height - 2) : (ListenerUtil.mutListener.listen(1119) ? ((float) height + 2) : ((float) height / 2))))) * ((ListenerUtil.mutListener.listen(1126) ? ((textPaint.descent() + textPaint.ascent()) % 2) : (ListenerUtil.mutListener.listen(1125) ? ((textPaint.descent() + textPaint.ascent()) * 2) : (ListenerUtil.mutListener.listen(1124) ? ((textPaint.descent() + textPaint.ascent()) - 2) : (ListenerUtil.mutListener.listen(1123) ? ((textPaint.descent() + textPaint.ascent()) + 2) : ((textPaint.descent() + textPaint.ascent()) / 2))))))) : (ListenerUtil.mutListener.listen(1127) ? ((ListenerUtil.mutListener.listen(1122) ? ((float) height % 2) : (ListenerUtil.mutListener.listen(1121) ? ((float) height * 2) : (ListenerUtil.mutListener.listen(1120) ? ((float) height - 2) : (ListenerUtil.mutListener.listen(1119) ? ((float) height + 2) : ((float) height / 2))))) + ((ListenerUtil.mutListener.listen(1126) ? ((textPaint.descent() + textPaint.ascent()) % 2) : (ListenerUtil.mutListener.listen(1125) ? ((textPaint.descent() + textPaint.ascent()) * 2) : (ListenerUtil.mutListener.listen(1124) ? ((textPaint.descent() + textPaint.ascent()) - 2) : (ListenerUtil.mutListener.listen(1123) ? ((textPaint.descent() + textPaint.ascent()) + 2) : ((textPaint.descent() + textPaint.ascent()) / 2))))))) : ((ListenerUtil.mutListener.listen(1122) ? ((float) height % 2) : (ListenerUtil.mutListener.listen(1121) ? ((float) height * 2) : (ListenerUtil.mutListener.listen(1120) ? ((float) height - 2) : (ListenerUtil.mutListener.listen(1119) ? ((float) height + 2) : ((float) height / 2))))) - ((ListenerUtil.mutListener.listen(1126) ? ((textPaint.descent() + textPaint.ascent()) % 2) : (ListenerUtil.mutListener.listen(1125) ? ((textPaint.descent() + textPaint.ascent()) * 2) : (ListenerUtil.mutListener.listen(1124) ? ((textPaint.descent() + textPaint.ascent()) - 2) : (ListenerUtil.mutListener.listen(1123) ? ((textPaint.descent() + textPaint.ascent()) + 2) : ((textPaint.descent() + textPaint.ascent()) / 2))))))))))), textPaint);
        }
        if (!ListenerUtil.mutListener.listen(1132)) {
            canvas.restoreToCount(count);
        }
    }

    private void drawBorder(Canvas canvas) {
        RectF rect = new RectF(getBounds());
        if (!ListenerUtil.mutListener.listen(1141)) {
            rect.inset((ListenerUtil.mutListener.listen(1136) ? (borderThickness % 2) : (ListenerUtil.mutListener.listen(1135) ? (borderThickness * 2) : (ListenerUtil.mutListener.listen(1134) ? (borderThickness - 2) : (ListenerUtil.mutListener.listen(1133) ? (borderThickness + 2) : (borderThickness / 2))))), (ListenerUtil.mutListener.listen(1140) ? (borderThickness % 2) : (ListenerUtil.mutListener.listen(1139) ? (borderThickness * 2) : (ListenerUtil.mutListener.listen(1138) ? (borderThickness - 2) : (ListenerUtil.mutListener.listen(1137) ? (borderThickness + 2) : (borderThickness / 2))))));
        }
        if (!ListenerUtil.mutListener.listen(1145)) {
            if (shape instanceof OvalShape) {
                if (!ListenerUtil.mutListener.listen(1144)) {
                    canvas.drawOval(rect, borderPaint);
                }
            } else if (shape instanceof RoundRectShape) {
                if (!ListenerUtil.mutListener.listen(1143)) {
                    canvas.drawRoundRect(rect, radius, radius, borderPaint);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1142)) {
                    canvas.drawRect(rect, borderPaint);
                }
            }
        }
    }

    @Override
    public void setAlpha(int alpha) {
        if (!ListenerUtil.mutListener.listen(1146)) {
            textPaint.setAlpha(alpha);
        }
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        if (!ListenerUtil.mutListener.listen(1147)) {
            textPaint.setColorFilter(cf);
        }
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return width;
    }

    @Override
    public int getIntrinsicHeight() {
        return height;
    }

    public static IShapeBuilder builder() {
        return new Builder();
    }

    public static class Builder implements IConfigBuilder, IShapeBuilder, IBuilder {

        private String text;

        private int color;

        private int borderThickness;

        private int width;

        private int height;

        private RectShape shape;

        int textColor;

        private int fontSize;

        private boolean isBold;

        private boolean toUpperCase;

        float radius;

        private Builder() {
            if (!ListenerUtil.mutListener.listen(1148)) {
                text = "";
            }
            if (!ListenerUtil.mutListener.listen(1149)) {
                color = Color.GRAY;
            }
            if (!ListenerUtil.mutListener.listen(1150)) {
                textColor = Color.WHITE;
            }
            if (!ListenerUtil.mutListener.listen(1151)) {
                borderThickness = 0;
            }
            if (!ListenerUtil.mutListener.listen(1152)) {
                width = -1;
            }
            if (!ListenerUtil.mutListener.listen(1153)) {
                height = -1;
            }
            if (!ListenerUtil.mutListener.listen(1154)) {
                shape = new RectShape();
            }
            if (!ListenerUtil.mutListener.listen(1155)) {
                fontSize = -1;
            }
            if (!ListenerUtil.mutListener.listen(1156)) {
                isBold = false;
            }
            if (!ListenerUtil.mutListener.listen(1157)) {
                toUpperCase = false;
            }
        }

        public IConfigBuilder width(int width) {
            if (!ListenerUtil.mutListener.listen(1158)) {
                this.width = width;
            }
            return this;
        }

        public IConfigBuilder height(int height) {
            if (!ListenerUtil.mutListener.listen(1159)) {
                this.height = height;
            }
            return this;
        }

        public IConfigBuilder textColor(int color) {
            if (!ListenerUtil.mutListener.listen(1160)) {
                this.textColor = color;
            }
            return this;
        }

        public IConfigBuilder withBorder(int thickness) {
            if (!ListenerUtil.mutListener.listen(1161)) {
                this.borderThickness = thickness;
            }
            return this;
        }

        public IConfigBuilder fontSize(int size) {
            if (!ListenerUtil.mutListener.listen(1162)) {
                this.fontSize = size;
            }
            return this;
        }

        public IConfigBuilder bold() {
            if (!ListenerUtil.mutListener.listen(1163)) {
                this.isBold = true;
            }
            return this;
        }

        public IConfigBuilder toUpperCase() {
            if (!ListenerUtil.mutListener.listen(1164)) {
                this.toUpperCase = true;
            }
            return this;
        }

        @Override
        public IConfigBuilder beginConfig() {
            return this;
        }

        @Override
        public IShapeBuilder endConfig() {
            return this;
        }

        @Override
        public IBuilder rect() {
            if (!ListenerUtil.mutListener.listen(1165)) {
                this.shape = new RectShape();
            }
            return this;
        }

        @Override
        public IBuilder round() {
            if (!ListenerUtil.mutListener.listen(1166)) {
                this.shape = new OvalShape();
            }
            return this;
        }

        @Override
        public IBuilder roundRect(int radius) {
            if (!ListenerUtil.mutListener.listen(1167)) {
                this.radius = radius;
            }
            float[] radii = { radius, radius, radius, radius, radius, radius, radius, radius };
            if (!ListenerUtil.mutListener.listen(1168)) {
                this.shape = new RoundRectShape(radii, null, null);
            }
            return this;
        }

        @Override
        public TextDrawable buildRect(String text, int color) {
            if (!ListenerUtil.mutListener.listen(1169)) {
                rect();
            }
            return build(text, color);
        }

        @Override
        public TextDrawable buildRoundRect(String text, int color, int radius) {
            if (!ListenerUtil.mutListener.listen(1170)) {
                roundRect(radius);
            }
            return build(text, color);
        }

        @Override
        public TextDrawable buildRound(String text, int color) {
            if (!ListenerUtil.mutListener.listen(1171)) {
                round();
            }
            return build(text, color);
        }

        @Override
        public TextDrawable build(String text, int color) {
            if (!ListenerUtil.mutListener.listen(1172)) {
                this.color = color;
            }
            if (!ListenerUtil.mutListener.listen(1173)) {
                this.text = text;
            }
            return new TextDrawable(this);
        }
    }

    public interface IConfigBuilder {

        IConfigBuilder width(int width);

        IConfigBuilder height(int height);

        IConfigBuilder textColor(int color);

        IConfigBuilder withBorder(int thickness);

        IConfigBuilder fontSize(int size);

        IConfigBuilder bold();

        IConfigBuilder toUpperCase();

        IShapeBuilder endConfig();
    }

    public interface IBuilder {

        TextDrawable build(String text, int color);
    }

    public interface IShapeBuilder {

        IConfigBuilder beginConfig();

        IBuilder rect();

        IBuilder round();

        IBuilder roundRect(int radius);

        TextDrawable buildRect(String text, int color);

        TextDrawable buildRoundRect(String text, int color, int radius);

        TextDrawable buildRound(String text, int color);
    }

    public static class ColorGenerator {

        public static ColorGenerator MATERIAL;

        static {
            if (!ListenerUtil.mutListener.listen(1174)) {
                MATERIAL = create(Arrays.asList(0xffe57373, 0xfff06292, 0xffba68c8, 0xff9575cd, 0xff7986cb, 0xff64b5f6, 0xff4fc3f7, 0xff4dd0e1, 0xff4db6ac, 0xff81c784, 0xffaed581, 0xffff8a65, 0xffd4e157, 0xffffd54f, 0xffffb74d, 0xffa1887f, 0xff90a4ae));
            }
        }

        private final List<Integer> mColors;

        private final Random mRandom;

        static ColorGenerator create(List<Integer> colorList) {
            return new ColorGenerator(colorList);
        }

        private ColorGenerator(List<Integer> colorList) {
            mColors = colorList;
            mRandom = new Random(System.currentTimeMillis());
        }

        public int getRandomColor() {
            return mColors.get(mRandom.nextInt(mColors.size()));
        }

        public int getColor(Object key) {
            return mColors.get((ListenerUtil.mutListener.listen(1178) ? (Math.abs(key.hashCode()) / mColors.size()) : (ListenerUtil.mutListener.listen(1177) ? (Math.abs(key.hashCode()) * mColors.size()) : (ListenerUtil.mutListener.listen(1176) ? (Math.abs(key.hashCode()) - mColors.size()) : (ListenerUtil.mutListener.listen(1175) ? (Math.abs(key.hashCode()) + mColors.size()) : (Math.abs(key.hashCode()) % mColors.size()))))));
        }
    }
}

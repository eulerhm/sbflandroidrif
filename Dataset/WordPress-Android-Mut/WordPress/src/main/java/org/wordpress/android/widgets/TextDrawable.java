package org.wordpress.android.widgets;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.TypedValue;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A Drawable object that draws text.
 * A TextDrawable accepts most of the same parameters that can be applied to
 * {@link android.widget.TextView} for displaying and formatting text.
 *
 * Optionally, a {@link Path} may be supplied on which to draw the text.
 *
 * A TextDrawable has an intrinsic size equal to that required to draw all
 * the text it has been supplied, when possible. In cases where a {@link Path}
 * has been supplied, the caller must explicitly call
 * {@link #setBounds(android.graphics.Rect) setBounds()} to provide the Drawable
 * size based on the Path constraints.
 */
public class TextDrawable extends Drawable {

    /* Platform XML constants for typeface */
    private static final int SANS = 1;

    private static final int SERIF = 2;

    private static final int MONOSPACE = 3;

    /* Resources for scaling values to the given device */
    private Resources mResources;

    /* Paint to hold most drawing primitives for the text */
    private TextPaint mTextPaint;

    /* Layout is used to measure and draw the text */
    private StaticLayout mTextLayout;

    /* Alignment of the text inside its bounds */
    private Layout.Alignment mTextAlignment = Layout.Alignment.ALIGN_NORMAL;

    /* Optional path on which to draw the text */
    private Path mTextPath;

    /* Stateful text color list */
    private ColorStateList mTextColors;

    /* Container for the bounds to be reported to widgets */
    private Rect mTextBounds;

    /* Text string to draw */
    private CharSequence mText = "";

    /* Attribute lists to pull default values from the current theme */
    private static final int[] THEME_ATTRIBUTES = { android.R.attr.textAppearance };

    private static final int[] APPEARANCE_ATTRIBUTES = { android.R.attr.textSize, android.R.attr.typeface, android.R.attr.textStyle, android.R.attr.textColor };

    public TextDrawable(Context context) {
        super();
        if (!ListenerUtil.mutListener.listen(28869)) {
            // Used to load and scale resource items
            mResources = context.getResources();
        }
        if (!ListenerUtil.mutListener.listen(28870)) {
            // Definition of this drawables size
            mTextBounds = new Rect();
        }
        if (!ListenerUtil.mutListener.listen(28871)) {
            // Paint to use for the text
            mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        }
        if (!ListenerUtil.mutListener.listen(28872)) {
            mTextPaint.density = mResources.getDisplayMetrics().density;
        }
        if (!ListenerUtil.mutListener.listen(28873)) {
            mTextPaint.setDither(true);
        }
        int textSize = 15;
        ColorStateList textColor = null;
        int styleIndex = -1;
        int typefaceIndex = -1;
        // Set default parameters from the current theme
        TypedArray a = context.getTheme().obtainStyledAttributes(THEME_ATTRIBUTES);
        int appearanceId = a.getResourceId(0, -1);
        if (!ListenerUtil.mutListener.listen(28874)) {
            a.recycle();
        }
        TypedArray ap = null;
        if (!ListenerUtil.mutListener.listen(28881)) {
            if ((ListenerUtil.mutListener.listen(28879) ? (appearanceId >= -1) : (ListenerUtil.mutListener.listen(28878) ? (appearanceId <= -1) : (ListenerUtil.mutListener.listen(28877) ? (appearanceId > -1) : (ListenerUtil.mutListener.listen(28876) ? (appearanceId < -1) : (ListenerUtil.mutListener.listen(28875) ? (appearanceId == -1) : (appearanceId != -1))))))) {
                if (!ListenerUtil.mutListener.listen(28880)) {
                    ap = context.obtainStyledAttributes(appearanceId, APPEARANCE_ATTRIBUTES);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28894)) {
            if (ap != null) {
                if (!ListenerUtil.mutListener.listen(28892)) {
                    {
                        long _loopCounter434 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(28891) ? (i >= ap.getIndexCount()) : (ListenerUtil.mutListener.listen(28890) ? (i <= ap.getIndexCount()) : (ListenerUtil.mutListener.listen(28889) ? (i > ap.getIndexCount()) : (ListenerUtil.mutListener.listen(28888) ? (i != ap.getIndexCount()) : (ListenerUtil.mutListener.listen(28887) ? (i == ap.getIndexCount()) : (i < ap.getIndexCount())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter434", ++_loopCounter434);
                            int attr = ap.getIndex(i);
                            if (!ListenerUtil.mutListener.listen(28886)) {
                                switch(attr) {
                                    case // Text Size
                                    0:
                                        if (!ListenerUtil.mutListener.listen(28882)) {
                                            textSize = a.getDimensionPixelSize(attr, textSize);
                                        }
                                        break;
                                    case // Typeface
                                    1:
                                        if (!ListenerUtil.mutListener.listen(28883)) {
                                            typefaceIndex = a.getInt(attr, typefaceIndex);
                                        }
                                        break;
                                    case // Text Style
                                    2:
                                        if (!ListenerUtil.mutListener.listen(28884)) {
                                            styleIndex = a.getInt(attr, styleIndex);
                                        }
                                        break;
                                    case // Text Color
                                    3:
                                        if (!ListenerUtil.mutListener.listen(28885)) {
                                            textColor = a.getColorStateList(attr);
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(28893)) {
                    ap.recycle();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28895)) {
            setTextColor(textColor != null ? textColor : ColorStateList.valueOf(0xFF000000));
        }
        if (!ListenerUtil.mutListener.listen(28896)) {
            setRawTextSize(textSize);
        }
        Typeface tf = null;
        if (!ListenerUtil.mutListener.listen(28900)) {
            switch(typefaceIndex) {
                case SANS:
                    if (!ListenerUtil.mutListener.listen(28897)) {
                        tf = Typeface.SANS_SERIF;
                    }
                    break;
                case SERIF:
                    if (!ListenerUtil.mutListener.listen(28898)) {
                        tf = Typeface.SERIF;
                    }
                    break;
                case MONOSPACE:
                    if (!ListenerUtil.mutListener.listen(28899)) {
                        tf = Typeface.MONOSPACE;
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(28901)) {
            setTypeface(tf, styleIndex);
        }
    }

    public void setText(int text) {
        if (!ListenerUtil.mutListener.listen(28902)) {
            this.setText(String.valueOf(text));
        }
    }

    /**
     * Set the text that will be displayed
     * @param text Text to display
     */
    public void setText(CharSequence text) {
        if (!ListenerUtil.mutListener.listen(28904)) {
            if (text == null) {
                if (!ListenerUtil.mutListener.listen(28903)) {
                    text = "";
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28905)) {
            mText = text;
        }
        if (!ListenerUtil.mutListener.listen(28906)) {
            measureContent();
        }
    }

    /**
     * Return the text currently being displayed
     */
    public CharSequence getText() {
        return mText;
    }

    /**
     * Return the current text size, in pixels
     */
    public float getTextSize() {
        return mTextPaint.getTextSize();
    }

    /**
     * Set the text size. The value will be interpreted in "sp" units
     * @param size Text size value, in sp
     */
    public void setTextSize(float size) {
        if (!ListenerUtil.mutListener.listen(28907)) {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        }
    }

    /**
     * Set the text size, using the supplied complex units
     * @param unit Units for the text size, such as dp or sp
     * @param size Text size value
     */
    public void setTextSize(int unit, float size) {
        float dimension = TypedValue.applyDimension(unit, size, mResources.getDisplayMetrics());
        if (!ListenerUtil.mutListener.listen(28908)) {
            setRawTextSize(dimension);
        }
    }

    /*
     * Set the text size, in raw pixels
     */
    private void setRawTextSize(float size) {
        if (!ListenerUtil.mutListener.listen(28911)) {
            if (size != mTextPaint.getTextSize()) {
                if (!ListenerUtil.mutListener.listen(28909)) {
                    mTextPaint.setTextSize(size);
                }
                if (!ListenerUtil.mutListener.listen(28910)) {
                    measureContent();
                }
            }
        }
    }

    /**
     * Return the horizontal stretch factor of the text
     */
    public float getTextScaleX() {
        return mTextPaint.getTextScaleX();
    }

    /**
     * Set the horizontal stretch factor of the text
     * @param size Text scale factor
     */
    public void setTextScaleX(float size) {
        if (!ListenerUtil.mutListener.listen(28914)) {
            if (size != mTextPaint.getTextScaleX()) {
                if (!ListenerUtil.mutListener.listen(28912)) {
                    mTextPaint.setTextScaleX(size);
                }
                if (!ListenerUtil.mutListener.listen(28913)) {
                    measureContent();
                }
            }
        }
    }

    /**
     * Return the current text alignment setting
     */
    public Layout.Alignment getTextAlign() {
        return mTextAlignment;
    }

    /**
     * Set the text alignment. The alignment itself is based on the text layout direction.
     * For LTR text NORMAL is left aligned and OPPOSITE is right aligned.
     * For RTL text, those alignments are reversed.
     * @param align Text alignment value. Should be set to one of:
     *
     * {@link Layout.Alignment#ALIGN_NORMAL},
     * {@link Layout.Alignment#ALIGN_NORMAL},
     * {@link Layout.Alignment#ALIGN_OPPOSITE}.
     */
    public void setTextAlign(Layout.Alignment align) {
        if (!ListenerUtil.mutListener.listen(28917)) {
            if (mTextAlignment != align) {
                if (!ListenerUtil.mutListener.listen(28915)) {
                    mTextAlignment = align;
                }
                if (!ListenerUtil.mutListener.listen(28916)) {
                    measureContent();
                }
            }
        }
    }

    /**
     * Sets the typeface and style in which the text should be displayed.
     * Note that not all Typeface families actually have bold and italic
     * variants, so you may need to use
     * {@link #setTypeface(Typeface, int)} to get the appearance
     * that you actually want.
     */
    public void setTypeface(Typeface tf) {
        if (!ListenerUtil.mutListener.listen(28920)) {
            if (mTextPaint.getTypeface() != tf) {
                if (!ListenerUtil.mutListener.listen(28918)) {
                    mTextPaint.setTypeface(tf);
                }
                if (!ListenerUtil.mutListener.listen(28919)) {
                    measureContent();
                }
            }
        }
    }

    /**
     * Sets the typeface and style in which the text should be displayed,
     * and turns on the fake bold and italic bits in the Paint if the
     * Typeface that you provided does not have all the bits in the
     * style that you specified.
     */
    public void setTypeface(Typeface tf, int style) {
        if (!ListenerUtil.mutListener.listen(28945)) {
            if ((ListenerUtil.mutListener.listen(28925) ? (style >= 0) : (ListenerUtil.mutListener.listen(28924) ? (style <= 0) : (ListenerUtil.mutListener.listen(28923) ? (style < 0) : (ListenerUtil.mutListener.listen(28922) ? (style != 0) : (ListenerUtil.mutListener.listen(28921) ? (style == 0) : (style > 0))))))) {
                if (!ListenerUtil.mutListener.listen(28931)) {
                    if (tf == null) {
                        if (!ListenerUtil.mutListener.listen(28930)) {
                            tf = Typeface.defaultFromStyle(style);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(28929)) {
                            tf = Typeface.create(tf, style);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(28932)) {
                    setTypeface(tf);
                }
                // now compute what (if any) algorithmic styling is needed
                int typefaceStyle = tf != null ? tf.getStyle() : 0;
                int need = style & ~typefaceStyle;
                if (!ListenerUtil.mutListener.listen(28938)) {
                    mTextPaint.setFakeBoldText((ListenerUtil.mutListener.listen(28937) ? ((need & Typeface.BOLD) >= 0) : (ListenerUtil.mutListener.listen(28936) ? ((need & Typeface.BOLD) <= 0) : (ListenerUtil.mutListener.listen(28935) ? ((need & Typeface.BOLD) > 0) : (ListenerUtil.mutListener.listen(28934) ? ((need & Typeface.BOLD) < 0) : (ListenerUtil.mutListener.listen(28933) ? ((need & Typeface.BOLD) == 0) : ((need & Typeface.BOLD) != 0)))))));
                }
                if (!ListenerUtil.mutListener.listen(28944)) {
                    mTextPaint.setTextSkewX((ListenerUtil.mutListener.listen(28943) ? ((need & Typeface.ITALIC) >= 0) : (ListenerUtil.mutListener.listen(28942) ? ((need & Typeface.ITALIC) <= 0) : (ListenerUtil.mutListener.listen(28941) ? ((need & Typeface.ITALIC) > 0) : (ListenerUtil.mutListener.listen(28940) ? ((need & Typeface.ITALIC) < 0) : (ListenerUtil.mutListener.listen(28939) ? ((need & Typeface.ITALIC) == 0) : ((need & Typeface.ITALIC) != 0)))))) ? -0.25f : 0);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28926)) {
                    mTextPaint.setFakeBoldText(false);
                }
                if (!ListenerUtil.mutListener.listen(28927)) {
                    mTextPaint.setTextSkewX(0);
                }
                if (!ListenerUtil.mutListener.listen(28928)) {
                    setTypeface(tf);
                }
            }
        }
    }

    /**
     * Return the current typeface and style that the Paint
     * using for display.
     */
    public Typeface getTypeface() {
        return mTextPaint.getTypeface();
    }

    /**
     * Set a single text color for all states
     * @param color Color value such as {@link Color#WHITE} or {@link Color#argb(int, int, int, int)}
     */
    public void setTextColor(int color) {
        if (!ListenerUtil.mutListener.listen(28946)) {
            setTextColor(ColorStateList.valueOf(color));
        }
    }

    /**
     * Set the text color as a state list
     * @param colorStateList ColorStateList of text colors, such as inflated from an R.color resource
     */
    public void setTextColor(ColorStateList colorStateList) {
        if (!ListenerUtil.mutListener.listen(28947)) {
            mTextColors = colorStateList;
        }
        if (!ListenerUtil.mutListener.listen(28948)) {
            updateTextColors(getState());
        }
    }

    /**
     * Optional Path object on which to draw the text. If this is set,
     * TextDrawable cannot properly measure the bounds this drawable will need.
     * You must call {@link #setBounds(int, int, int, int) setBounds()} before
     * applying this TextDrawable to any View.
     *
     * Calling this method with <code>null</code> will remove any Path currently attached.
     */
    public void setTextPath(Path path) {
        if (!ListenerUtil.mutListener.listen(28951)) {
            if (mTextPath != path) {
                if (!ListenerUtil.mutListener.listen(28949)) {
                    mTextPath = path;
                }
                if (!ListenerUtil.mutListener.listen(28950)) {
                    measureContent();
                }
            }
        }
    }

    /**
     * Internal method to take measurements of the current contents and apply
     * the correct bounds when possible.
     */
    private void measureContent() {
        if (!ListenerUtil.mutListener.listen(28956)) {
            // We must resly on setBounds being called externally
            if (mTextPath != null) {
                if (!ListenerUtil.mutListener.listen(28954)) {
                    // Clear any previous measurement
                    mTextLayout = null;
                }
                if (!ListenerUtil.mutListener.listen(28955)) {
                    mTextBounds.setEmpty();
                }
            } else {
                // Measure text bounds
                double desired = Math.ceil(Layout.getDesiredWidth(mText, mTextPaint));
                if (!ListenerUtil.mutListener.listen(28952)) {
                    mTextLayout = new StaticLayout(mText, mTextPaint, (int) desired, mTextAlignment, 1.0f, 0.0f, false);
                }
                if (!ListenerUtil.mutListener.listen(28953)) {
                    mTextBounds.set(0, 0, mTextLayout.getWidth(), mTextLayout.getHeight());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28957)) {
            // We may need to be redrawn
            invalidateSelf();
        }
    }

    /**
     * Internal method to apply the correct text color based on the drawable's state
     */
    private boolean updateTextColors(int[] stateSet) {
        int newColor = mTextColors.getColorForState(stateSet, Color.WHITE);
        if (!ListenerUtil.mutListener.listen(28959)) {
            if (mTextPaint.getColor() != newColor) {
                if (!ListenerUtil.mutListener.listen(28958)) {
                    mTextPaint.setColor(newColor);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        if (!ListenerUtil.mutListener.listen(28960)) {
            // Update the internal bounds in response to any external requests
            mTextBounds.set(bounds);
        }
    }

    @Override
    public boolean isStateful() {
        /*
         * The drawable's ability to represent state is based on
         * the text color list set
         */
        return mTextColors.isStateful();
    }

    @Override
    protected boolean onStateChange(int[] state) {
        // Upon state changes, grab the correct text color
        return updateTextColors(state);
    }

    @Override
    public int getIntrinsicHeight() {
        // Return the vertical bounds measured, or -1 if none
        if (mTextBounds.isEmpty()) {
            return -1;
        } else {
            return ((ListenerUtil.mutListener.listen(28964) ? (mTextBounds.bottom % mTextBounds.top) : (ListenerUtil.mutListener.listen(28963) ? (mTextBounds.bottom / mTextBounds.top) : (ListenerUtil.mutListener.listen(28962) ? (mTextBounds.bottom * mTextBounds.top) : (ListenerUtil.mutListener.listen(28961) ? (mTextBounds.bottom + mTextBounds.top) : (mTextBounds.bottom - mTextBounds.top))))));
        }
    }

    @Override
    public int getIntrinsicWidth() {
        // Return the horizontal bounds measured, or -1 if none
        if (mTextBounds.isEmpty()) {
            return -1;
        } else {
            return ((ListenerUtil.mutListener.listen(28968) ? (mTextBounds.right % mTextBounds.left) : (ListenerUtil.mutListener.listen(28967) ? (mTextBounds.right / mTextBounds.left) : (ListenerUtil.mutListener.listen(28966) ? (mTextBounds.right * mTextBounds.left) : (ListenerUtil.mutListener.listen(28965) ? (mTextBounds.right + mTextBounds.left) : (mTextBounds.right - mTextBounds.left))))));
        }
    }

    @Override
    public void draw(Canvas canvas) {
        final Rect bounds = getBounds();
        final int count = canvas.save();
        if (!ListenerUtil.mutListener.listen(28969)) {
            canvas.translate(bounds.left, bounds.top);
        }
        if (!ListenerUtil.mutListener.listen(28972)) {
            if (mTextPath == null) {
                if (!ListenerUtil.mutListener.listen(28971)) {
                    // Allow the layout to draw the text
                    mTextLayout.draw(canvas);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28970)) {
                    // Draw directly on the canvas using the supplied path
                    canvas.drawTextOnPath(mText.toString(), mTextPath, 0, 0, mTextPaint);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28973)) {
            canvas.restoreToCount(count);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        if (!ListenerUtil.mutListener.listen(28975)) {
            if (mTextPaint.getAlpha() != alpha) {
                if (!ListenerUtil.mutListener.listen(28974)) {
                    mTextPaint.setAlpha(alpha);
                }
            }
        }
    }

    @Override
    public int getOpacity() {
        return mTextPaint.getAlpha();
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        if (!ListenerUtil.mutListener.listen(28977)) {
            if (mTextPaint.getColorFilter() != cf) {
                if (!ListenerUtil.mutListener.listen(28976)) {
                    mTextPaint.setColorFilter(cf);
                }
            }
        }
    }
}

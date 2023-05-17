/*
 *  Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>
 *
 *  This program is free software; you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software
 *  Foundation; either version 3 of the License, or (at your option) any later
 *  version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with
 *  this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.anki.noteeditor;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anki.R;
import com.ichi2.libanki.Utils;
import com.ichi2.utils.ViewGroupUtils;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Toolbar extends FrameLayout {

    private TextFormatListener mFormatCallback;

    private LinearLayout mToolbar;

    private List<View> mCustomButtons = new ArrayList<>();

    private View mClozeIcon;

    private Paint mStringPaint;

    public Toolbar(@NonNull Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(2281)) {
            init();
        }
    }

    public Toolbar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(2282)) {
            init();
        }
    }

    public Toolbar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(2283)) {
            init();
        }
    }

    public Toolbar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (!ListenerUtil.mutListener.listen(2284)) {
            init();
        }
    }

    private void init() {
        if (!ListenerUtil.mutListener.listen(2285)) {
            LayoutInflater.from(getContext()).inflate(R.layout.note_editor_toolbar, this, true);
        }
        int paintSize = dpToPixels(28);
        if (!ListenerUtil.mutListener.listen(2286)) {
            mStringPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }
        if (!ListenerUtil.mutListener.listen(2287)) {
            mStringPaint.setTextSize(paintSize);
        }
        if (!ListenerUtil.mutListener.listen(2288)) {
            mStringPaint.setColor(Color.BLACK);
        }
        if (!ListenerUtil.mutListener.listen(2289)) {
            mStringPaint.setTextAlign(Paint.Align.CENTER);
        }
        if (!ListenerUtil.mutListener.listen(2290)) {
            this.mToolbar = findViewById(R.id.editor_toolbar_internal);
        }
        if (!ListenerUtil.mutListener.listen(2291)) {
            setClick(R.id.note_editor_toolbar_button_bold, "<b>", "</b>");
        }
        if (!ListenerUtil.mutListener.listen(2292)) {
            setClick(R.id.note_editor_toolbar_button_italic, "<em>", "</em>");
        }
        if (!ListenerUtil.mutListener.listen(2293)) {
            setClick(R.id.note_editor_toolbar_button_underline, "<u>", "</u>");
        }
        if (!ListenerUtil.mutListener.listen(2294)) {
            setClick(R.id.note_editor_toolbar_button_insert_mathjax, "\\(", "\\)");
        }
        if (!ListenerUtil.mutListener.listen(2295)) {
            setClick(R.id.note_editor_toolbar_button_horizontal_rule, "<hr>", "");
        }
        if (!ListenerUtil.mutListener.listen(2296)) {
            findViewById(R.id.note_editor_toolbar_button_font_size).setOnClickListener(l -> displayFontSizeDialog());
        }
        if (!ListenerUtil.mutListener.listen(2297)) {
            findViewById(R.id.note_editor_toolbar_button_title).setOnClickListener(l -> displayInsertHeadingDialog());
        }
        if (!ListenerUtil.mutListener.listen(2298)) {
            this.mClozeIcon = findViewById(R.id.note_editor_toolbar_button_cloze);
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (!ListenerUtil.mutListener.listen(2302)) {
            // I'll avoid checking "function" here as it may be required to press Ctrl
            if ((ListenerUtil.mutListener.listen(2301) ? ((ListenerUtil.mutListener.listen(2300) ? ((ListenerUtil.mutListener.listen(2299) ? (!event.isCtrlPressed() && event.isAltPressed()) : (!event.isCtrlPressed() || event.isAltPressed())) && event.isShiftPressed()) : ((ListenerUtil.mutListener.listen(2299) ? (!event.isCtrlPressed() && event.isAltPressed()) : (!event.isCtrlPressed() || event.isAltPressed())) || event.isShiftPressed())) && event.isMetaPressed()) : ((ListenerUtil.mutListener.listen(2300) ? ((ListenerUtil.mutListener.listen(2299) ? (!event.isCtrlPressed() && event.isAltPressed()) : (!event.isCtrlPressed() || event.isAltPressed())) && event.isShiftPressed()) : ((ListenerUtil.mutListener.listen(2299) ? (!event.isCtrlPressed() && event.isAltPressed()) : (!event.isCtrlPressed() || event.isAltPressed())) || event.isShiftPressed())) || event.isMetaPressed()))) {
                return false;
            }
        }
        char c;
        try {
            c = (char) event.getUnicodeChar(0);
        } catch (Exception e) {
            return false;
        }
        if (!ListenerUtil.mutListener.listen(2303)) {
            if (c == '\0') {
                return false;
            }
        }
        String expected = Character.toString(c);
        if (!ListenerUtil.mutListener.listen(2307)) {
            {
                long _loopCounter40 = 0;
                for (View v : ViewGroupUtils.getAllChildrenRecursive(this)) {
                    ListenerUtil.loopListener.listen("_loopCounter40", ++_loopCounter40);
                    if (!ListenerUtil.mutListener.listen(2306)) {
                        if (Utils.equals(expected, v.getTag())) {
                            if (!ListenerUtil.mutListener.listen(2304)) {
                                Timber.i("Handling Ctrl + %s", c);
                            }
                            if (!ListenerUtil.mutListener.listen(2305)) {
                                v.performClick();
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    private int dpToPixels(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }

    public View getClozeIcon() {
        // HACK until API 21 FIXME can this be altered now?
        return mClozeIcon;
    }

    @NonNull
    public AppCompatImageButton insertItem(@IdRes int id, @DrawableRes int drawable, Runnable runnable) {
        // A null theme can be passed after colorControlNormal is defined (API 25)
        Context themeContext = new ContextThemeWrapper(getContext(), R.style.Theme_Light_Compat);
        VectorDrawableCompat d = VectorDrawableCompat.create(getContext().getResources(), drawable, themeContext.getTheme());
        return insertItem(id, d, runnable);
    }

    @NonNull
    public View insertItem(int id, Drawable drawable, TextFormatter formatter) {
        return insertItem(id, drawable, () -> onFormat(formatter));
    }

    @NonNull
    public AppCompatImageButton insertItem(@IdRes int id, Drawable drawable, Runnable runnable) {
        Context context = getContext();
        AppCompatImageButton button = new AppCompatImageButton(context);
        if (!ListenerUtil.mutListener.listen(2308)) {
            button.setId(id);
        }
        if (!ListenerUtil.mutListener.listen(2309)) {
            button.setBackgroundDrawable(drawable);
        }
        // apply style
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (!ListenerUtil.mutListener.listen(2310)) {
            params.gravity = Gravity.CENTER;
        }
        if (!ListenerUtil.mutListener.listen(2311)) {
            button.setLayoutParams(params);
        }
        int fourDp = (int) Math.ceil((ListenerUtil.mutListener.listen(2315) ? (4 % context.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2314) ? (4 * context.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2313) ? (4 - context.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2312) ? (4 + context.getResources().getDisplayMetrics().density) : (4 / context.getResources().getDisplayMetrics().density))))));
        if (!ListenerUtil.mutListener.listen(2316)) {
            button.setPadding(fourDp, fourDp, fourDp, fourDp);
        }
        if (!ListenerUtil.mutListener.listen(2317)) {
            this.mToolbar.addView(button, mToolbar.getChildCount());
        }
        if (!ListenerUtil.mutListener.listen(2318)) {
            mCustomButtons.add(button);
        }
        if (!ListenerUtil.mutListener.listen(2319)) {
            button.setOnClickListener(l -> runnable.run());
        }
        // Hack - items are truncated from the scrollview
        View v = findViewById(R.id.editor_toolbar_internal);
        int expectedWidth = (ListenerUtil.mutListener.listen(2323) ? (getVisibleItemCount() % dpToPixels(32)) : (ListenerUtil.mutListener.listen(2322) ? (getVisibleItemCount() / dpToPixels(32)) : (ListenerUtil.mutListener.listen(2321) ? (getVisibleItemCount() - dpToPixels(32)) : (ListenerUtil.mutListener.listen(2320) ? (getVisibleItemCount() + dpToPixels(32)) : (getVisibleItemCount() * dpToPixels(32))))));
        int width = getScreenWidth();
        LayoutParams p = new LayoutParams(v.getLayoutParams());
        if (!ListenerUtil.mutListener.listen(2329)) {
            p.gravity = Gravity.CENTER_VERTICAL | (((ListenerUtil.mutListener.listen(2328) ? (expectedWidth >= width) : (ListenerUtil.mutListener.listen(2327) ? (expectedWidth <= width) : (ListenerUtil.mutListener.listen(2326) ? (expectedWidth < width) : (ListenerUtil.mutListener.listen(2325) ? (expectedWidth != width) : (ListenerUtil.mutListener.listen(2324) ? (expectedWidth == width) : (expectedWidth > width))))))) ? Gravity.LEFT : Gravity.CENTER_HORIZONTAL);
        }
        if (!ListenerUtil.mutListener.listen(2330)) {
            v.setLayoutParams(p);
        }
        return button;
    }

    protected int getScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (!ListenerUtil.mutListener.listen(2331)) {
            ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.widthPixels;
    }

    public void clearCustomItems() {
        if (!ListenerUtil.mutListener.listen(2333)) {
            {
                long _loopCounter41 = 0;
                for (View v : mCustomButtons) {
                    ListenerUtil.loopListener.listen("_loopCounter41", ++_loopCounter41);
                    if (!ListenerUtil.mutListener.listen(2332)) {
                        mToolbar.removeView(v);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2334)) {
            mCustomButtons.clear();
        }
    }

    public void setFormatListener(TextFormatListener formatter) {
        if (!ListenerUtil.mutListener.listen(2335)) {
            mFormatCallback = formatter;
        }
    }

    private void displayFontSizeDialog() {
        String[] results = getResources().getStringArray(R.array.html_size_codes);
        if (!ListenerUtil.mutListener.listen(2336)) {
            // Might be better to add this as a fragment - let's see.
            new MaterialDialog.Builder(getContext()).items(R.array.html_size_code_labels).itemsCallback((dialog, view, pos, string) -> {
                String prefix = "<span style=\"font-size:" + results[pos] + "\">";
                String suffix = "</span>";
                TextWrapper formatter = new TextWrapper(prefix, suffix);
                onFormat(formatter);
            }).title(R.string.menu_font_size).show();
        }
    }

    private void displayInsertHeadingDialog() {
        if (!ListenerUtil.mutListener.listen(2337)) {
            new MaterialDialog.Builder(getContext()).items(new String[] { "h1", "h2", "h3", "h4", "h5" }).itemsCallback((dialog, view, pos, string) -> {
                String prefix = "<" + string + ">";
                String suffix = "</" + string + ">";
                TextWrapper formatter = new TextWrapper(prefix, suffix);
                onFormat(formatter);
            }).title(R.string.insert_heading).show();
        }
    }

    @NonNull
    public Drawable createDrawableForString(String text) {
        float baseline = -mStringPaint.ascent();
        int size = (int) (baseline + mStringPaint.descent() + 0.5f);
        Bitmap image = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        if (!ListenerUtil.mutListener.listen(2342)) {
            canvas.drawText(text, (ListenerUtil.mutListener.listen(2341) ? (size % 2f) : (ListenerUtil.mutListener.listen(2340) ? (size * 2f) : (ListenerUtil.mutListener.listen(2339) ? (size - 2f) : (ListenerUtil.mutListener.listen(2338) ? (size + 2f) : (size / 2f))))), baseline, mStringPaint);
        }
        return new BitmapDrawable(getResources(), image);
    }

    private int getVisibleItemCount() {
        int count = 0;
        if (!ListenerUtil.mutListener.listen(2350)) {
            {
                long _loopCounter42 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(2349) ? (i >= mToolbar.getChildCount()) : (ListenerUtil.mutListener.listen(2348) ? (i <= mToolbar.getChildCount()) : (ListenerUtil.mutListener.listen(2347) ? (i > mToolbar.getChildCount()) : (ListenerUtil.mutListener.listen(2346) ? (i != mToolbar.getChildCount()) : (ListenerUtil.mutListener.listen(2345) ? (i == mToolbar.getChildCount()) : (i < mToolbar.getChildCount())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter42", ++_loopCounter42);
                    if (!ListenerUtil.mutListener.listen(2344)) {
                        if (mToolbar.getChildAt(i).getVisibility() == View.VISIBLE) {
                            if (!ListenerUtil.mutListener.listen(2343)) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    private void setClick(@IdRes int id, String prefix, String suffix) {
        if (!ListenerUtil.mutListener.listen(2351)) {
            setClick(id, new TextWrapper(prefix, suffix));
        }
    }

    private void setClick(int id, TextFormatter textWrapper) {
        if (!ListenerUtil.mutListener.listen(2352)) {
            findViewById(id).setOnClickListener(l -> onFormat(textWrapper));
        }
    }

    public void onFormat(TextFormatter formatter) {
        if (!ListenerUtil.mutListener.listen(2353)) {
            if (mFormatCallback == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2354)) {
            mFormatCallback.performFormat(formatter);
        }
    }

    public interface TextFormatListener {

        void performFormat(TextFormatter formatter);
    }

    public interface TextFormatter {

        TextWrapper.StringFormat format(String s);
    }

    public static class TextWrapper implements TextFormatter {

        private final String mPrefix;

        private final String mSuffix;

        public TextWrapper(String prefix, String suffix) {
            this.mPrefix = prefix;
            this.mSuffix = suffix;
        }

        @Override
        public StringFormat format(String s) {
            StringFormat stringFormat = new StringFormat();
            if (!ListenerUtil.mutListener.listen(2355)) {
                stringFormat.result = mPrefix + s + mSuffix;
            }
            if (!ListenerUtil.mutListener.listen(2365)) {
                if ((ListenerUtil.mutListener.listen(2360) ? (s.length() >= 0) : (ListenerUtil.mutListener.listen(2359) ? (s.length() <= 0) : (ListenerUtil.mutListener.listen(2358) ? (s.length() > 0) : (ListenerUtil.mutListener.listen(2357) ? (s.length() < 0) : (ListenerUtil.mutListener.listen(2356) ? (s.length() != 0) : (s.length() == 0))))))) {
                    if (!ListenerUtil.mutListener.listen(2363)) {
                        stringFormat.start = mPrefix.length();
                    }
                    if (!ListenerUtil.mutListener.listen(2364)) {
                        stringFormat.end = mPrefix.length();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(2361)) {
                        stringFormat.start = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(2362)) {
                        stringFormat.end = stringFormat.result.length();
                    }
                }
            }
            return stringFormat;
        }

        public static class StringFormat {

            public String result;

            public int start;

            public int end;
        }
    }
}

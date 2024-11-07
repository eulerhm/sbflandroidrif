/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import java.util.Objects;
import androidx.annotation.ColorInt;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatEditText;
import ch.threema.app.R;
import ch.threema.app.motionviews.widget.TextEntity;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.EditTextUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ImagePaintKeyboardActivity extends ThreemaToolbarActivity {

    public static final String INTENT_EXTRA_TEXT = "text";

    // resolved color
    public static final String INTENT_EXTRA_COLOR = "color";

    private int currentKeyboardHeight;

    private AppCompatEditText textEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4201)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(4202)) {
            this.currentKeyboardHeight = 0;
        }
        if (!ListenerUtil.mutListener.listen(4203)) {
            setSupportActionBar(getToolbar());
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(4205)) {
            if (actionBar == null) {
                if (!ListenerUtil.mutListener.listen(4204)) {
                    finish();
                }
            }
        }
        Drawable checkDrawable = AppCompatResources.getDrawable(this, R.drawable.ic_check);
        if (!ListenerUtil.mutListener.listen(4206)) {
            Objects.requireNonNull(checkDrawable).setColorFilter(ConfigUtils.getColorFromAttribute(this, R.attr.textColorPrimary), PorterDuff.Mode.SRC_IN);
        }
        if (!ListenerUtil.mutListener.listen(4207)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(4208)) {
            actionBar.setHomeAsUpIndicator(checkDrawable);
        }
        if (!ListenerUtil.mutListener.listen(4209)) {
            actionBar.setTitle("");
        }
        final View rootView = findViewById(R.id.root_view);
        if (!ListenerUtil.mutListener.listen(4231)) {
            rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    // detect if keyboard was closed
                    int navigationBarHeight = ConfigUtils.getNavigationBarHeight(ImagePaintKeyboardActivity.this);
                    int statusBarHeight = ConfigUtils.getStatusBarHeight(ImagePaintKeyboardActivity.this);
                    Rect rect = new Rect();
                    if (!ListenerUtil.mutListener.listen(4210)) {
                        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                    }
                    int keyboardHeight = (ListenerUtil.mutListener.listen(4218) ? (rootView.getRootView().getHeight() % ((ListenerUtil.mutListener.listen(4214) ? (statusBarHeight % navigationBarHeight) : (ListenerUtil.mutListener.listen(4213) ? (statusBarHeight / navigationBarHeight) : (ListenerUtil.mutListener.listen(4212) ? (statusBarHeight * navigationBarHeight) : (ListenerUtil.mutListener.listen(4211) ? (statusBarHeight - navigationBarHeight) : (statusBarHeight + navigationBarHeight))))) + rect.height())) : (ListenerUtil.mutListener.listen(4217) ? (rootView.getRootView().getHeight() / ((ListenerUtil.mutListener.listen(4214) ? (statusBarHeight % navigationBarHeight) : (ListenerUtil.mutListener.listen(4213) ? (statusBarHeight / navigationBarHeight) : (ListenerUtil.mutListener.listen(4212) ? (statusBarHeight * navigationBarHeight) : (ListenerUtil.mutListener.listen(4211) ? (statusBarHeight - navigationBarHeight) : (statusBarHeight + navigationBarHeight))))) + rect.height())) : (ListenerUtil.mutListener.listen(4216) ? (rootView.getRootView().getHeight() * ((ListenerUtil.mutListener.listen(4214) ? (statusBarHeight % navigationBarHeight) : (ListenerUtil.mutListener.listen(4213) ? (statusBarHeight / navigationBarHeight) : (ListenerUtil.mutListener.listen(4212) ? (statusBarHeight * navigationBarHeight) : (ListenerUtil.mutListener.listen(4211) ? (statusBarHeight - navigationBarHeight) : (statusBarHeight + navigationBarHeight))))) + rect.height())) : (ListenerUtil.mutListener.listen(4215) ? (rootView.getRootView().getHeight() + ((ListenerUtil.mutListener.listen(4214) ? (statusBarHeight % navigationBarHeight) : (ListenerUtil.mutListener.listen(4213) ? (statusBarHeight / navigationBarHeight) : (ListenerUtil.mutListener.listen(4212) ? (statusBarHeight * navigationBarHeight) : (ListenerUtil.mutListener.listen(4211) ? (statusBarHeight - navigationBarHeight) : (statusBarHeight + navigationBarHeight))))) + rect.height())) : (rootView.getRootView().getHeight() - ((ListenerUtil.mutListener.listen(4214) ? (statusBarHeight % navigationBarHeight) : (ListenerUtil.mutListener.listen(4213) ? (statusBarHeight / navigationBarHeight) : (ListenerUtil.mutListener.listen(4212) ? (statusBarHeight * navigationBarHeight) : (ListenerUtil.mutListener.listen(4211) ? (statusBarHeight - navigationBarHeight) : (statusBarHeight + navigationBarHeight))))) + rect.height()))))));
                    if (!ListenerUtil.mutListener.listen(4229)) {
                        if ((ListenerUtil.mutListener.listen(4227) ? (((ListenerUtil.mutListener.listen(4222) ? (currentKeyboardHeight % keyboardHeight) : (ListenerUtil.mutListener.listen(4221) ? (currentKeyboardHeight / keyboardHeight) : (ListenerUtil.mutListener.listen(4220) ? (currentKeyboardHeight * keyboardHeight) : (ListenerUtil.mutListener.listen(4219) ? (currentKeyboardHeight + keyboardHeight) : (currentKeyboardHeight - keyboardHeight)))))) >= getResources().getDimensionPixelSize(R.dimen.min_keyboard_height)) : (ListenerUtil.mutListener.listen(4226) ? (((ListenerUtil.mutListener.listen(4222) ? (currentKeyboardHeight % keyboardHeight) : (ListenerUtil.mutListener.listen(4221) ? (currentKeyboardHeight / keyboardHeight) : (ListenerUtil.mutListener.listen(4220) ? (currentKeyboardHeight * keyboardHeight) : (ListenerUtil.mutListener.listen(4219) ? (currentKeyboardHeight + keyboardHeight) : (currentKeyboardHeight - keyboardHeight)))))) <= getResources().getDimensionPixelSize(R.dimen.min_keyboard_height)) : (ListenerUtil.mutListener.listen(4225) ? (((ListenerUtil.mutListener.listen(4222) ? (currentKeyboardHeight % keyboardHeight) : (ListenerUtil.mutListener.listen(4221) ? (currentKeyboardHeight / keyboardHeight) : (ListenerUtil.mutListener.listen(4220) ? (currentKeyboardHeight * keyboardHeight) : (ListenerUtil.mutListener.listen(4219) ? (currentKeyboardHeight + keyboardHeight) : (currentKeyboardHeight - keyboardHeight)))))) < getResources().getDimensionPixelSize(R.dimen.min_keyboard_height)) : (ListenerUtil.mutListener.listen(4224) ? (((ListenerUtil.mutListener.listen(4222) ? (currentKeyboardHeight % keyboardHeight) : (ListenerUtil.mutListener.listen(4221) ? (currentKeyboardHeight / keyboardHeight) : (ListenerUtil.mutListener.listen(4220) ? (currentKeyboardHeight * keyboardHeight) : (ListenerUtil.mutListener.listen(4219) ? (currentKeyboardHeight + keyboardHeight) : (currentKeyboardHeight - keyboardHeight)))))) != getResources().getDimensionPixelSize(R.dimen.min_keyboard_height)) : (ListenerUtil.mutListener.listen(4223) ? (((ListenerUtil.mutListener.listen(4222) ? (currentKeyboardHeight % keyboardHeight) : (ListenerUtil.mutListener.listen(4221) ? (currentKeyboardHeight / keyboardHeight) : (ListenerUtil.mutListener.listen(4220) ? (currentKeyboardHeight * keyboardHeight) : (ListenerUtil.mutListener.listen(4219) ? (currentKeyboardHeight + keyboardHeight) : (currentKeyboardHeight - keyboardHeight)))))) == getResources().getDimensionPixelSize(R.dimen.min_keyboard_height)) : (((ListenerUtil.mutListener.listen(4222) ? (currentKeyboardHeight % keyboardHeight) : (ListenerUtil.mutListener.listen(4221) ? (currentKeyboardHeight / keyboardHeight) : (ListenerUtil.mutListener.listen(4220) ? (currentKeyboardHeight * keyboardHeight) : (ListenerUtil.mutListener.listen(4219) ? (currentKeyboardHeight + keyboardHeight) : (currentKeyboardHeight - keyboardHeight)))))) > getResources().getDimensionPixelSize(R.dimen.min_keyboard_height)))))))) {
                            if (!ListenerUtil.mutListener.listen(4228)) {
                                returnResult(textEntry.getText());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4230)) {
                        currentKeyboardHeight = keyboardHeight;
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4233)) {
            rootView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(4232)) {
                        cancel();
                    }
                }
            });
        }
        Intent intent = getIntent();
        @ColorInt
        int color = intent.getIntExtra(INTENT_EXTRA_COLOR, getResources().getColor(android.R.color.white));
        @ColorInt
        int hintColor;
        if ((ListenerUtil.mutListener.listen(4238) ? (color >= 0x1000000) : (ListenerUtil.mutListener.listen(4237) ? (color <= 0x1000000) : (ListenerUtil.mutListener.listen(4236) ? (color < 0x1000000) : (ListenerUtil.mutListener.listen(4235) ? (color != 0x1000000) : (ListenerUtil.mutListener.listen(4234) ? (color == 0x1000000) : (color > 0x1000000))))))) {
            hintColor = (ListenerUtil.mutListener.listen(4250) ? ((ListenerUtil.mutListener.listen(4246) ? (color % 0xFF000000) : (ListenerUtil.mutListener.listen(4245) ? (color / 0xFF000000) : (ListenerUtil.mutListener.listen(4244) ? (color * 0xFF000000) : (ListenerUtil.mutListener.listen(4243) ? (color + 0xFF000000) : (color - 0xFF000000))))) % 0xA0000000) : (ListenerUtil.mutListener.listen(4249) ? ((ListenerUtil.mutListener.listen(4246) ? (color % 0xFF000000) : (ListenerUtil.mutListener.listen(4245) ? (color / 0xFF000000) : (ListenerUtil.mutListener.listen(4244) ? (color * 0xFF000000) : (ListenerUtil.mutListener.listen(4243) ? (color + 0xFF000000) : (color - 0xFF000000))))) / 0xA0000000) : (ListenerUtil.mutListener.listen(4248) ? ((ListenerUtil.mutListener.listen(4246) ? (color % 0xFF000000) : (ListenerUtil.mutListener.listen(4245) ? (color / 0xFF000000) : (ListenerUtil.mutListener.listen(4244) ? (color * 0xFF000000) : (ListenerUtil.mutListener.listen(4243) ? (color + 0xFF000000) : (color - 0xFF000000))))) * 0xA0000000) : (ListenerUtil.mutListener.listen(4247) ? ((ListenerUtil.mutListener.listen(4246) ? (color % 0xFF000000) : (ListenerUtil.mutListener.listen(4245) ? (color / 0xFF000000) : (ListenerUtil.mutListener.listen(4244) ? (color * 0xFF000000) : (ListenerUtil.mutListener.listen(4243) ? (color + 0xFF000000) : (color - 0xFF000000))))) - 0xA0000000) : ((ListenerUtil.mutListener.listen(4246) ? (color % 0xFF000000) : (ListenerUtil.mutListener.listen(4245) ? (color / 0xFF000000) : (ListenerUtil.mutListener.listen(4244) ? (color * 0xFF000000) : (ListenerUtil.mutListener.listen(4243) ? (color + 0xFF000000) : (color - 0xFF000000))))) + 0xA0000000)))));
        } else {
            hintColor = (ListenerUtil.mutListener.listen(4242) ? (color % 0xA0000000) : (ListenerUtil.mutListener.listen(4241) ? (color / 0xA0000000) : (ListenerUtil.mutListener.listen(4240) ? (color * 0xA0000000) : (ListenerUtil.mutListener.listen(4239) ? (color - 0xA0000000) : (color + 0xA0000000)))));
        }
        if (!ListenerUtil.mutListener.listen(4251)) {
            textEntry = findViewById(R.id.edittext);
        }
        if (!ListenerUtil.mutListener.listen(4254)) {
            textEntry.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (!ListenerUtil.mutListener.listen(4253)) {
                        if (i == EditorInfo.IME_ACTION_DONE) {
                            if (!ListenerUtil.mutListener.listen(4252)) {
                                returnResult(textView.getText());
                            }
                        }
                    }
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4256)) {
            textEntry.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!ListenerUtil.mutListener.listen(4255)) {
                        onUserInteraction();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4257)) {
            textEntry.setHorizontallyScrolling(false);
        }
        if (!ListenerUtil.mutListener.listen(4258)) {
            textEntry.setMaxLines(3);
        }
        if (!ListenerUtil.mutListener.listen(4259)) {
            textEntry.setTextColor(color);
        }
        if (!ListenerUtil.mutListener.listen(4260)) {
            textEntry.setHintTextColor(hintColor);
        }
        if (!ListenerUtil.mutListener.listen(4273)) {
            // offset values don't seem to have the same range as in a textpaint (we have to approx. quadruple them)
            textEntry.setShadowLayer((ListenerUtil.mutListener.listen(4264) ? (TextEntity.TEXT_SHADOW_RADIUS % 4) : (ListenerUtil.mutListener.listen(4263) ? (TextEntity.TEXT_SHADOW_RADIUS / 4) : (ListenerUtil.mutListener.listen(4262) ? (TextEntity.TEXT_SHADOW_RADIUS - 4) : (ListenerUtil.mutListener.listen(4261) ? (TextEntity.TEXT_SHADOW_RADIUS + 4) : (TextEntity.TEXT_SHADOW_RADIUS * 4))))), (ListenerUtil.mutListener.listen(4268) ? (TextEntity.TEXT_SHADOW_OFFSET % 4) : (ListenerUtil.mutListener.listen(4267) ? (TextEntity.TEXT_SHADOW_OFFSET / 4) : (ListenerUtil.mutListener.listen(4266) ? (TextEntity.TEXT_SHADOW_OFFSET - 4) : (ListenerUtil.mutListener.listen(4265) ? (TextEntity.TEXT_SHADOW_OFFSET + 4) : (TextEntity.TEXT_SHADOW_OFFSET * 4))))), (ListenerUtil.mutListener.listen(4272) ? (TextEntity.TEXT_SHADOW_OFFSET % 4) : (ListenerUtil.mutListener.listen(4271) ? (TextEntity.TEXT_SHADOW_OFFSET / 4) : (ListenerUtil.mutListener.listen(4270) ? (TextEntity.TEXT_SHADOW_OFFSET - 4) : (ListenerUtil.mutListener.listen(4269) ? (TextEntity.TEXT_SHADOW_OFFSET + 4) : (TextEntity.TEXT_SHADOW_OFFSET * 4))))), Color.BLACK);
        }
        if (!ListenerUtil.mutListener.listen(4277)) {
            textEntry.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(4274)) {
                        textEntry.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(4275)) {
                        textEntry.requestFocus();
                    }
                    if (!ListenerUtil.mutListener.listen(4276)) {
                        EditTextUtil.showSoftKeyboard(textEntry);
                    }
                }
            }, 500);
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_image_paint_keyboard;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(4278)) {
            super.onOptionsItemSelected(item);
        }
        if (!ListenerUtil.mutListener.listen(4280)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(4279)) {
                        returnResult(textEntry.getText());
                    }
                    return true;
                default:
                    break;
            }
        }
        return false;
    }

    private void returnResult(CharSequence text) {
        if (!ListenerUtil.mutListener.listen(4293)) {
            if ((ListenerUtil.mutListener.listen(4286) ? (text != null || (ListenerUtil.mutListener.listen(4285) ? (text.length() >= 0) : (ListenerUtil.mutListener.listen(4284) ? (text.length() <= 0) : (ListenerUtil.mutListener.listen(4283) ? (text.length() < 0) : (ListenerUtil.mutListener.listen(4282) ? (text.length() != 0) : (ListenerUtil.mutListener.listen(4281) ? (text.length() == 0) : (text.length() > 0))))))) : (text != null && (ListenerUtil.mutListener.listen(4285) ? (text.length() >= 0) : (ListenerUtil.mutListener.listen(4284) ? (text.length() <= 0) : (ListenerUtil.mutListener.listen(4283) ? (text.length() < 0) : (ListenerUtil.mutListener.listen(4282) ? (text.length() != 0) : (ListenerUtil.mutListener.listen(4281) ? (text.length() == 0) : (text.length() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(4289)) {
                    if (textEntry != null) {
                        if (!ListenerUtil.mutListener.listen(4288)) {
                            EditTextUtil.hideSoftKeyboard(textEntry);
                        }
                    }
                }
                Intent resultIntent = new Intent();
                if (!ListenerUtil.mutListener.listen(4290)) {
                    resultIntent.putExtra(INTENT_EXTRA_TEXT, text.toString());
                }
                if (!ListenerUtil.mutListener.listen(4291)) {
                    setResult(RESULT_OK, resultIntent);
                }
                if (!ListenerUtil.mutListener.listen(4292)) {
                    finish();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4287)) {
                    cancel();
                }
            }
        }
    }

    private void cancel() {
        if (!ListenerUtil.mutListener.listen(4295)) {
            if (textEntry != null) {
                if (!ListenerUtil.mutListener.listen(4294)) {
                    EditTextUtil.hideSoftKeyboard(textEntry);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4296)) {
            setResult(RESULT_CANCELED);
        }
        if (!ListenerUtil.mutListener.listen(4297)) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(4298)) {
            cancel();
        }
        if (!ListenerUtil.mutListener.listen(4299)) {
            super.onDestroy();
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(4300)) {
            cancel();
        }
    }
}

/**
 * ************************************************************************************
 *  Copyright (c) 2009 Nicolas Raoul <nicolas.raoul@gmail.com>                           *
 *  Copyright (c) 2009 Edu Zamora <edu.zasu@gmail.com>                                   *
 *  Copyright (c) 2015 Tim Rae <perceptualchaos2@gmail.com>                              *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.anki;

import android.content.ClipData;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import com.ichi2.utils.IntentUtil;
import com.ichi2.utils.VersionUtils;
import org.acra.util.Installation;
import timber.log.Timber;
import static com.ichi2.anim.ActivityTransitionAnimation.Direction.LEFT;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Info extends AnkiActivity {

    public static final String TYPE_EXTRA = "infoType";

    public static final int TYPE_ABOUT = 0;

    public static final int TYPE_NEW_VERSION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(8529)) {
            if (showedActivityFailedScreen(savedInstanceState)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8530)) {
            Timber.d("onCreate()");
        }
        if (!ListenerUtil.mutListener.listen(8531)) {
            super.onCreate(savedInstanceState);
        }
        Resources res = getResources();
        int mType = getIntent().getIntExtra(TYPE_EXTRA, TYPE_ABOUT);
        if (!ListenerUtil.mutListener.listen(8538)) {
            // If the page crashes, we do not want to display it again (#7135 maybe)
            if ((ListenerUtil.mutListener.listen(8536) ? (mType >= TYPE_NEW_VERSION) : (ListenerUtil.mutListener.listen(8535) ? (mType <= TYPE_NEW_VERSION) : (ListenerUtil.mutListener.listen(8534) ? (mType > TYPE_NEW_VERSION) : (ListenerUtil.mutListener.listen(8533) ? (mType < TYPE_NEW_VERSION) : (ListenerUtil.mutListener.listen(8532) ? (mType != TYPE_NEW_VERSION) : (mType == TYPE_NEW_VERSION))))))) {
                if (!ListenerUtil.mutListener.listen(8537)) {
                    AnkiDroidApp.getSharedPrefs(Info.this.getBaseContext()).edit().putString("lastVersion", VersionUtils.getPkgVersionName()).apply();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8539)) {
            setContentView(R.layout.info);
        }
        final View mainView = findViewById(android.R.id.content);
        if (!ListenerUtil.mutListener.listen(8540)) {
            enableToolbar(mainView);
        }
        if (!ListenerUtil.mutListener.listen(8541)) {
            findViewById(R.id.info_donate).setOnClickListener((v) -> openUrl(Uri.parse(getString(R.string.link_opencollective_donate))));
        }
        if (!ListenerUtil.mutListener.listen(8542)) {
            setTitle(String.format("%s v%s", VersionUtils.getAppName(), VersionUtils.getPkgVersionName()));
        }
        WebView webView = findViewById(R.id.info);
        if (!ListenerUtil.mutListener.listen(8550)) {
            webView.setWebChromeClient(new WebChromeClient() {

                public void onProgressChanged(WebView view, int progress) {
                    if (!ListenerUtil.mutListener.listen(8549)) {
                        // Hide the progress indicator when the page has finished loaded
                        if ((ListenerUtil.mutListener.listen(8547) ? (progress >= 100) : (ListenerUtil.mutListener.listen(8546) ? (progress <= 100) : (ListenerUtil.mutListener.listen(8545) ? (progress > 100) : (ListenerUtil.mutListener.listen(8544) ? (progress < 100) : (ListenerUtil.mutListener.listen(8543) ? (progress != 100) : (progress == 100))))))) {
                            if (!ListenerUtil.mutListener.listen(8548)) {
                                mainView.findViewById(R.id.progress_bar).setVisibility(View.GONE);
                            }
                        }
                    }
                }
            });
        }
        Button marketButton = findViewById(R.id.left_button);
        if (!ListenerUtil.mutListener.listen(8554)) {
            if (canOpenMarketUri()) {
                if (!ListenerUtil.mutListener.listen(8552)) {
                    marketButton.setText(R.string.info_rate);
                }
                if (!ListenerUtil.mutListener.listen(8553)) {
                    marketButton.setOnClickListener(arg0 -> IntentUtil.tryOpenIntent(this, AnkiDroidApp.getMarketIntent(this)));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8551)) {
                    marketButton.setVisibility(View.GONE);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(8573)) {
            switch(mType) {
                case TYPE_ABOUT:
                    {
                        String[] content = res.getStringArray(R.array.about_content);
                        // Apply theme colours.
                        TypedValue typedValue = new TypedValue();
                        if (!ListenerUtil.mutListener.listen(8555)) {
                            getTheme().resolveAttribute(android.R.attr.colorBackground, typedValue, true);
                        }
                        if (!ListenerUtil.mutListener.listen(8556)) {
                            webView.setBackgroundColor(typedValue.data);
                        }
                        if (!ListenerUtil.mutListener.listen(8557)) {
                            getTheme().resolveAttribute(android.R.attr.textColor, typedValue, true);
                        }
                        // Color to hex string
                        String textColor = String.format("#%06X", (0xFFFFFF & typedValue.data));
                        if (!ListenerUtil.mutListener.listen(8558)) {
                            sb.append("<html><style>body {color:").append(textColor).append(";}</style>");
                        }
                        if (!ListenerUtil.mutListener.listen(8559)) {
                            sb.append("<body text=\"#000000\" link=\"#E37068\" alink=\"#E37068\" vlink=\"#E37068\">");
                        }
                        if (!ListenerUtil.mutListener.listen(8560)) {
                            sb.append(String.format(content[0], res.getString(R.string.app_name), res.getString(R.string.link_anki))).append("<br/><br/>");
                        }
                        if (!ListenerUtil.mutListener.listen(8561)) {
                            sb.append(String.format(content[1], res.getString(R.string.link_issue_tracker), res.getString(R.string.link_wiki), res.getString(R.string.link_forum))).append("<br/><br/>");
                        }
                        if (!ListenerUtil.mutListener.listen(8562)) {
                            sb.append(String.format(content[2], res.getString(R.string.link_wikipedia_open_source), res.getString(R.string.link_contribution))).append(" ");
                        }
                        if (!ListenerUtil.mutListener.listen(8563)) {
                            sb.append(String.format(content[3], res.getString(R.string.link_translation), res.getString(R.string.link_donation))).append("<br/><br/>");
                        }
                        if (!ListenerUtil.mutListener.listen(8564)) {
                            sb.append(String.format(content[4], res.getString(R.string.licence_wiki), res.getString(R.string.link_source))).append("<br/><br/>");
                        }
                        if (!ListenerUtil.mutListener.listen(8565)) {
                            sb.append("</body></html>");
                        }
                        if (!ListenerUtil.mutListener.listen(8566)) {
                            webView.loadDataWithBaseURL("", sb.toString(), "text/html", "utf-8", null);
                        }
                        Button debugCopy = (findViewById(R.id.right_button));
                        if (!ListenerUtil.mutListener.listen(8567)) {
                            debugCopy.setText(res.getString(R.string.feedback_copy_debug));
                        }
                        if (!ListenerUtil.mutListener.listen(8568)) {
                            debugCopy.setOnClickListener(v -> copyDebugInfo());
                        }
                        break;
                    }
                case TYPE_NEW_VERSION:
                    {
                        Button continueButton = (findViewById(R.id.right_button));
                        if (!ListenerUtil.mutListener.listen(8569)) {
                            continueButton.setText(res.getString(R.string.dialog_continue));
                        }
                        if (!ListenerUtil.mutListener.listen(8570)) {
                            continueButton.setOnClickListener((arg) -> close());
                        }
                        if (!ListenerUtil.mutListener.listen(8571)) {
                            webView.loadUrl("file:///android_asset/changelog.html");
                        }
                        break;
                    }
                default:
                    if (!ListenerUtil.mutListener.listen(8572)) {
                        finishWithoutAnimation();
                    }
                    break;
            }
        }
    }

    private void close() {
        if (!ListenerUtil.mutListener.listen(8574)) {
            setResult(RESULT_OK);
        }
        if (!ListenerUtil.mutListener.listen(8575)) {
            finishWithAnimation();
        }
    }

    private boolean canOpenMarketUri() {
        try {
            return IntentUtil.canOpenIntent(this, AnkiDroidApp.getMarketIntent(this));
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(8576)) {
                Timber.w(e);
            }
            return false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!ListenerUtil.mutListener.listen(8581)) {
            if ((ListenerUtil.mutListener.listen(8577) ? (keyCode == KeyEvent.KEYCODE_BACK || event.getRepeatCount() == 0) : (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0))) {
                if (!ListenerUtil.mutListener.listen(8578)) {
                    Timber.i("onBackPressed()");
                }
                if (!ListenerUtil.mutListener.listen(8579)) {
                    setResult(RESULT_CANCELED);
                }
                if (!ListenerUtil.mutListener.listen(8580)) {
                    finishWithAnimation();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void finishWithAnimation() {
        if (!ListenerUtil.mutListener.listen(8582)) {
            finishWithAnimation(LEFT);
        }
    }

    /**
     * Copy debug information about the device to the clipboard
     * @return debugInfo
     */
    public String copyDebugInfo() {
        String schedName = "Not found";
        try {
            if (!ListenerUtil.mutListener.listen(8584)) {
                schedName = getCol().getSched().getName();
            }
        } catch (Throwable e) {
            if (!ListenerUtil.mutListener.listen(8583)) {
                Timber.e(e, "Sched name not found");
            }
        }
        String debugInfo = "AnkiDroid Version = " + VersionUtils.getPkgVersionName() + "\n\n" + "Android Version = " + Build.VERSION.RELEASE + "\n\n" + "ACRA UUID = " + Installation.id(this) + "\n\n" + "Scheduler = " + schedName + "\n\n" + "Crash Reports Enabled = " + isSendingCrashReports() + "\n";
        android.content.ClipboardManager clipboardManager = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (!ListenerUtil.mutListener.listen(8588)) {
            if (clipboardManager != null) {
                if (!ListenerUtil.mutListener.listen(8586)) {
                    clipboardManager.setPrimaryClip(ClipData.newPlainText(this.getTitle(), debugInfo));
                }
                if (!ListenerUtil.mutListener.listen(8587)) {
                    UIUtils.showThemedToast(this, getString(R.string.about_ankidroid_successfully_copied_debug), true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8585)) {
                    UIUtils.showThemedToast(this, getString(R.string.about_ankidroid_error_copy_debug_info), false);
                }
            }
        }
        return debugInfo;
    }

    private boolean isSendingCrashReports() {
        return AnkiDroidApp.isAcraEnbled(this, false);
    }
}

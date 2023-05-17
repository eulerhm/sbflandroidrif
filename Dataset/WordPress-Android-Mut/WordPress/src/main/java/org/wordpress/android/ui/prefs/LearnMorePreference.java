package org.wordpress.android.ui.prefs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.Preference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import org.wordpress.android.R;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.util.ToastUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LearnMorePreference extends Preference implements View.OnClickListener {

    private static final String SUPPORT_CONTENT_JS = "javascript:(function(){" + "var mobileSupport = document.getElementById('mobile-only-usage');" + "mobileSupport.style.display = 'inline';" + "var newHtml = '<' + mobileSupport.tagName + '>'" + " + mobileSupport.innerHTML + '</' + mobileSupport.tagName + '>';" + "document.body.innerHTML = newHtml;" + "document.body.setAttribute('style', 'padding:24px 24px 0px 24px !important');" + "}) ();";

    private static final String CONTENT_PADDING_JS = "javascript:(function(){" + "document.body.setAttribute('style', 'padding:24px 24px 0px 24px !important');" + "document.getElementById('mobilenav-toggle').style.display = 'none';" + "document.getElementById('actionbar').style.display = 'none';" + "}) ();";

    private Dialog mDialog;

    private String mUrl;

    private String mCaption;

    private String mButtonText;

    private int mIcon = -1;

    private int mLayout = R.layout.learn_more_pref;

    private boolean mUseCustomJsFormatting;

    private boolean mOpenInDialog;

    public LearnMorePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LearnMorePreference);
        if (!ListenerUtil.mutListener.listen(14785)) {
            {
                long _loopCounter248 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(14784) ? (i >= array.getIndexCount()) : (ListenerUtil.mutListener.listen(14783) ? (i <= array.getIndexCount()) : (ListenerUtil.mutListener.listen(14782) ? (i > array.getIndexCount()) : (ListenerUtil.mutListener.listen(14781) ? (i != array.getIndexCount()) : (ListenerUtil.mutListener.listen(14780) ? (i == array.getIndexCount()) : (i < array.getIndexCount())))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter248", ++_loopCounter248);
                    int index = array.getIndex(i);
                    if (!ListenerUtil.mutListener.listen(14779)) {
                        if (index == R.styleable.LearnMorePreference_url) {
                            if (!ListenerUtil.mutListener.listen(14778)) {
                                mUrl = array.getString(index);
                            }
                        } else if (index == R.styleable.LearnMorePreference_useCustomJsFormatting) {
                            if (!ListenerUtil.mutListener.listen(14777)) {
                                mUseCustomJsFormatting = array.getBoolean(index, false);
                            }
                        } else if (index == R.styleable.LearnMorePreference_caption) {
                            int id = array.getResourceId(index, -1);
                            if (!ListenerUtil.mutListener.listen(14776)) {
                                if ((ListenerUtil.mutListener.listen(14774) ? (id >= -1) : (ListenerUtil.mutListener.listen(14773) ? (id <= -1) : (ListenerUtil.mutListener.listen(14772) ? (id > -1) : (ListenerUtil.mutListener.listen(14771) ? (id < -1) : (ListenerUtil.mutListener.listen(14770) ? (id == -1) : (id != -1))))))) {
                                    if (!ListenerUtil.mutListener.listen(14775)) {
                                        mCaption = array.getResources().getString(id);
                                    }
                                }
                            }
                        } else if (index == R.styleable.LearnMorePreference_button) {
                            int id = array.getResourceId(index, -1);
                            if (!ListenerUtil.mutListener.listen(14769)) {
                                if ((ListenerUtil.mutListener.listen(14767) ? (id >= -1) : (ListenerUtil.mutListener.listen(14766) ? (id <= -1) : (ListenerUtil.mutListener.listen(14765) ? (id > -1) : (ListenerUtil.mutListener.listen(14764) ? (id < -1) : (ListenerUtil.mutListener.listen(14763) ? (id == -1) : (id != -1))))))) {
                                    if (!ListenerUtil.mutListener.listen(14768)) {
                                        mButtonText = array.getResources().getString(id);
                                    }
                                }
                            }
                        } else if (index == R.styleable.LearnMorePreference_icon) {
                            if (!ListenerUtil.mutListener.listen(14762)) {
                                mIcon = array.getResourceId(index, -1);
                            }
                        } else if (index == R.styleable.LearnMorePreference_layout) {
                            if (!ListenerUtil.mutListener.listen(14761)) {
                                mLayout = array.getResourceId(index, -1);
                            }
                        } else if (index == R.styleable.LearnMorePreference_openInDialog) {
                            if (!ListenerUtil.mutListener.listen(14760)) {
                                mOpenInDialog = array.getBoolean(index, false);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14786)) {
            array.recycle();
        }
    }

    @Override
    protected View onCreateView(@NonNull ViewGroup parent) {
        if (!ListenerUtil.mutListener.listen(14787)) {
            super.onCreateView(parent);
        }
        View view = View.inflate(getContext(), mLayout, null);
        Button learnMoreButton = view.findViewById(R.id.learn_more_button);
        if (!ListenerUtil.mutListener.listen(14788)) {
            learnMoreButton.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14791)) {
            if (!TextUtils.isEmpty(mCaption)) {
                TextView caption = view.findViewById(R.id.learn_more_caption);
                if (!ListenerUtil.mutListener.listen(14789)) {
                    caption.setText(mCaption);
                }
                if (!ListenerUtil.mutListener.listen(14790)) {
                    caption.setVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14795)) {
            if (!TextUtils.isEmpty(mButtonText)) {
                if (!ListenerUtil.mutListener.listen(14792)) {
                    learnMoreButton.setText(mButtonText);
                }
                if (!ListenerUtil.mutListener.listen(14793)) {
                    learnMoreButton.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(14794)) {
                    view.findViewById(R.id.bottom_padding).setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14804)) {
            if ((ListenerUtil.mutListener.listen(14800) ? (mIcon >= -1) : (ListenerUtil.mutListener.listen(14799) ? (mIcon <= -1) : (ListenerUtil.mutListener.listen(14798) ? (mIcon > -1) : (ListenerUtil.mutListener.listen(14797) ? (mIcon < -1) : (ListenerUtil.mutListener.listen(14796) ? (mIcon == -1) : (mIcon != -1))))))) {
                ImageView icon = view.findViewById(R.id.learn_more_icon);
                if (!ListenerUtil.mutListener.listen(14803)) {
                    if (icon != null) {
                        if (!ListenerUtil.mutListener.listen(14801)) {
                            icon.setImageResource(mIcon);
                        }
                        if (!ListenerUtil.mutListener.listen(14802)) {
                            icon.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        }
        return view;
    }

    @Override
    public Parcelable onSaveInstanceState() {
        if (!ListenerUtil.mutListener.listen(14807)) {
            if ((ListenerUtil.mutListener.listen(14805) ? (mDialog != null || mDialog.isShowing()) : (mDialog != null && mDialog.isShowing()))) {
                if (!ListenerUtil.mutListener.listen(14806)) {
                    mDialog.dismiss();
                }
                return new SavedState(super.onSaveInstanceState());
            }
        }
        return super.onSaveInstanceState();
    }

    @Override
    public void onRestoreInstanceState(@NonNull Parcelable state) {
        if (!ListenerUtil.mutListener.listen(14812)) {
            if (!(state instanceof SavedState)) {
                if (!ListenerUtil.mutListener.listen(14811)) {
                    super.onRestoreInstanceState(state);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14808)) {
                    super.onRestoreInstanceState(((SavedState) state).getSuperState());
                }
                if (!ListenerUtil.mutListener.listen(14810)) {
                    if (mOpenInDialog) {
                        if (!ListenerUtil.mutListener.listen(14809)) {
                            showDialog();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (!ListenerUtil.mutListener.listen(14813)) {
            if (mDialog != null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(14814)) {
            AnalyticsTracker.track(Stat.SITE_SETTINGS_LEARN_MORE_CLICKED);
        }
        if (!ListenerUtil.mutListener.listen(14818)) {
            if (mOpenInDialog) {
                if (!ListenerUtil.mutListener.listen(14817)) {
                    showDialog();
                }
            } else {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrl));
                if (!ListenerUtil.mutListener.listen(14816)) {
                    if (browserIntent.resolveActivity(v.getContext().getPackageManager()) != null) {
                        if (!ListenerUtil.mutListener.listen(14815)) {
                            v.getContext().startActivity(browserIntent);
                        }
                    }
                }
            }
        }
    }

    private void showDialog() {
        final WebView webView = loadSupportWebView();
        if (!ListenerUtil.mutListener.listen(14819)) {
            mDialog = new Dialog(getContext());
        }
        if (!ListenerUtil.mutListener.listen(14822)) {
            mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

                @Override
                public void onDismiss(DialogInterface dialog) {
                    if (!ListenerUtil.mutListener.listen(14820)) {
                        webView.stopLoading();
                    }
                    if (!ListenerUtil.mutListener.listen(14821)) {
                        mDialog = null;
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(14823)) {
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if (!ListenerUtil.mutListener.listen(14824)) {
            mDialog.setContentView(R.layout.learn_more_pref_screen);
        }
        WindowManager.LayoutParams params = mDialog.getWindow().getAttributes();
        if (!ListenerUtil.mutListener.listen(14825)) {
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
        }
        if (!ListenerUtil.mutListener.listen(14826)) {
            params.height = WindowManager.LayoutParams.MATCH_PARENT;
        }
        if (!ListenerUtil.mutListener.listen(14827)) {
            params.gravity = Gravity.CENTER;
        }
        if (!ListenerUtil.mutListener.listen(14828)) {
            params.x = 12;
        }
        if (!ListenerUtil.mutListener.listen(14829)) {
            params.y = 12;
        }
        if (!ListenerUtil.mutListener.listen(14830)) {
            mDialog.getWindow().setAttributes(params);
        }
        if (!ListenerUtil.mutListener.listen(14831)) {
            mDialog.show();
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private WebView loadSupportWebView() {
        WebView webView = new WebView(getContext());
        WebSettings webSettings = webView.getSettings();
        if (!ListenerUtil.mutListener.listen(14832)) {
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        }
        if (!ListenerUtil.mutListener.listen(14833)) {
            webSettings.setJavaScriptEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(14834)) {
            webView.setWebViewClient(new LearnMoreClient());
        }
        if (!ListenerUtil.mutListener.listen(14835)) {
            webView.loadUrl(mUrl);
        }
        return webView;
    }

    private static class SavedState extends BaseSavedState {

        SavedState(Parcel source) {
            super(source);
        }

        SavedState(Parcelable superState) {
            super(superState);
        }

        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    private class LearnMoreClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            // prevent loading clicked links
            return (ListenerUtil.mutListener.listen(14837) ? ((ListenerUtil.mutListener.listen(14836) ? (!mUrl.equals(url) || !SUPPORT_CONTENT_JS.equals(url)) : (!mUrl.equals(url) && !SUPPORT_CONTENT_JS.equals(url))) || !CONTENT_PADDING_JS.equals(url)) : ((ListenerUtil.mutListener.listen(14836) ? (!mUrl.equals(url) || !SUPPORT_CONTENT_JS.equals(url)) : (!mUrl.equals(url) && !SUPPORT_CONTENT_JS.equals(url))) && !CONTENT_PADDING_JS.equals(url)));
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            if (!ListenerUtil.mutListener.listen(14838)) {
                super.onReceivedError(view, request, error);
            }
            if (!ListenerUtil.mutListener.listen(14842)) {
                if ((ListenerUtil.mutListener.listen(14839) ? (mDialog != null || mDialog.isShowing()) : (mDialog != null && mDialog.isShowing()))) {
                    if (!ListenerUtil.mutListener.listen(14840)) {
                        ToastUtils.showToast(getContext(), R.string.could_not_load_page);
                    }
                    if (!ListenerUtil.mutListener.listen(14841)) {
                        mDialog.dismiss();
                    }
                }
            }
        }

        @Override
        public void onPageFinished(WebView webView, String url) {
            if (!ListenerUtil.mutListener.listen(14843)) {
                super.onPageFinished(webView, url);
            }
            if (!ListenerUtil.mutListener.listen(14850)) {
                if (mDialog != null) {
                    if (!ListenerUtil.mutListener.listen(14844)) {
                        AnalyticsTracker.track(Stat.SITE_SETTINGS_LEARN_MORE_LOADED);
                    }
                    if (!ListenerUtil.mutListener.listen(14847)) {
                        if (mUseCustomJsFormatting) {
                            if (!ListenerUtil.mutListener.listen(14846)) {
                                webView.loadUrl(SUPPORT_CONTENT_JS);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(14845)) {
                                webView.loadUrl(CONTENT_PADDING_JS);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(14848)) {
                        mDialog.setContentView(webView);
                    }
                    if (!ListenerUtil.mutListener.listen(14849)) {
                        webView.scrollTo(0, 0);
                    }
                }
            }
        }
    }
}

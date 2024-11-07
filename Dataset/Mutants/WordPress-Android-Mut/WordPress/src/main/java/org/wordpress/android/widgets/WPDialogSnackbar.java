package org.wordpress.android.widgets;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.Snackbar.SnackbarLayout;
import org.wordpress.android.R;
import org.wordpress.android.util.AccessibilityUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * {@link Snackbar} with {@link android.app.Dialog}-like layout mimicking the updated design pattern defined in the
 * Material Design guidelines <a href="https://material.io/design/components/snackbars.html#spec">specifications</a>.
 * The view include title, message, positive button, negative button, and neutral button.  Any empty or null view is
 * hidden.  The only required view is message.
 */
public class WPDialogSnackbar {

    private Snackbar mSnackbar;

    private View mContentView;

    private WPDialogSnackbar(@NonNull View view, @NonNull CharSequence text, int duration) {
        if (!ListenerUtil.mutListener.listen(28981)) {
            mSnackbar = // CHECKSTYLE IGNORE
            Snackbar.make(// CHECKSTYLE IGNORE
            view, // CHECKSTYLE IGNORE
            "", AccessibilityUtils.getSnackbarDuration(view.getContext(), duration));
        }
        // Set underlying snackbar layout.
        SnackbarLayout snackbarLayout = (SnackbarLayout) mSnackbar.getView();
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) snackbarLayout.getLayoutParams();
        Context context = view.getContext();
        int margin = (int) context.getResources().getDimension(R.dimen.margin_medium);
        if (!ListenerUtil.mutListener.listen(28982)) {
            params.setMargins(margin, margin, margin, margin);
        }
        if (!ListenerUtil.mutListener.listen(28983)) {
            snackbarLayout.setLayoutParams(params);
        }
        if (!ListenerUtil.mutListener.listen(28984)) {
            snackbarLayout.setPadding(0, 0, 0, 0);
        }
        if (!ListenerUtil.mutListener.listen(28985)) {
            snackbarLayout.setBackgroundResource(R.drawable.bg_snackbar);
        }
        // Hide underlying snackbar text and action.
        TextView snackbarText = snackbarLayout.findViewById(com.google.android.material.R.id.snackbar_text);
        if (!ListenerUtil.mutListener.listen(28986)) {
            snackbarText.setVisibility(View.INVISIBLE);
        }
        TextView snackbarAction = snackbarLayout.findViewById(com.google.android.material.R.id.snackbar_action);
        if (!ListenerUtil.mutListener.listen(28987)) {
            snackbarAction.setVisibility(View.INVISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(28988)) {
            mContentView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_snackbar, null);
        }
        TextView message = mContentView.findViewById(R.id.message);
        if (!ListenerUtil.mutListener.listen(28992)) {
            // Hide message view when text is empty.
            if (TextUtils.isEmpty(text)) {
                if (!ListenerUtil.mutListener.listen(28991)) {
                    message.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28989)) {
                    message.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(28990)) {
                    message.setText(text);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28993)) {
            snackbarLayout.addView(mContentView, 0);
        }
    }

    public void dismiss() {
        if (!ListenerUtil.mutListener.listen(28995)) {
            if (mSnackbar != null) {
                if (!ListenerUtil.mutListener.listen(28994)) {
                    mSnackbar.dismiss();
                }
            }
        }
    }

    public boolean isShowing() {
        return (ListenerUtil.mutListener.listen(28996) ? (mSnackbar != null || mSnackbar.isShown()) : (mSnackbar != null && mSnackbar.isShown()));
    }

    public static WPDialogSnackbar make(@NonNull View view, @NonNull CharSequence text, int duration) {
        return new WPDialogSnackbar(view, text, duration);
    }

    private void setButtonTextAndVisibility(Button button, CharSequence text, final View.OnClickListener listener) {
        if (!ListenerUtil.mutListener.listen(29005)) {
            // Hide button when text is empty or listener is null.
            if ((ListenerUtil.mutListener.listen(28997) ? (TextUtils.isEmpty(text) && listener == null) : (TextUtils.isEmpty(text) || listener == null))) {
                if (!ListenerUtil.mutListener.listen(29003)) {
                    button.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(29004)) {
                    button.setOnClickListener(null);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28998)) {
                    button.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(28999)) {
                    button.setText(text);
                }
                if (!ListenerUtil.mutListener.listen(29002)) {
                    button.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            if (!ListenerUtil.mutListener.listen(29000)) {
                                listener.onClick(view);
                            }
                            if (!ListenerUtil.mutListener.listen(29001)) {
                                dismiss();
                            }
                        }
                    });
                }
            }
        }
    }

    public WPDialogSnackbar setNegativeButton(CharSequence text, View.OnClickListener listener) {
        if (!ListenerUtil.mutListener.listen(29006)) {
            setButtonTextAndVisibility((Button) mContentView.findViewById(R.id.button_negative), text, listener);
        }
        return this;
    }

    public WPDialogSnackbar setNeutralButton(CharSequence text, View.OnClickListener listener) {
        if (!ListenerUtil.mutListener.listen(29007)) {
            setButtonTextAndVisibility((Button) mContentView.findViewById(R.id.button_neutral), text, listener);
        }
        return this;
    }

    public WPDialogSnackbar setPositiveButton(CharSequence text, View.OnClickListener listener) {
        if (!ListenerUtil.mutListener.listen(29008)) {
            setButtonTextAndVisibility((Button) mContentView.findViewById(R.id.button_positive), text, listener);
        }
        return this;
    }

    public WPDialogSnackbar setTitle(@NonNull CharSequence text) {
        TextView title = mContentView.findViewById(R.id.title);
        if (!ListenerUtil.mutListener.listen(29012)) {
            // Hide title view when text is empty.
            if (TextUtils.isEmpty(text)) {
                if (!ListenerUtil.mutListener.listen(29011)) {
                    title.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(29009)) {
                    title.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(29010)) {
                    title.setText(text);
                }
            }
        }
        return this;
    }

    public void show() {
        if (!ListenerUtil.mutListener.listen(29013)) {
            mSnackbar.show();
        }
    }
}

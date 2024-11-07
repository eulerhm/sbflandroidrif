package fr.free.nrw.commons.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.annotation.StringRes;
import com.google.android.material.snackbar.Snackbar;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ViewUtil {

    /**
     * Utility function to show short snack bar
     * @param view
     * @param messageResourceId
     */
    public static void showShortSnackbar(View view, int messageResourceId) {
        if (!ListenerUtil.mutListener.listen(2596)) {
            if (view.getContext() == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2597)) {
            ExecutorUtils.uiExecutor().execute(() -> {
                try {
                    Snackbar.make(view, messageResourceId, Snackbar.LENGTH_SHORT).show();
                } catch (IllegalStateException e) {
                    Timber.e(e.getMessage());
                }
            });
        }
    }

    public static void showLongToast(Context context, String text) {
        if (!ListenerUtil.mutListener.listen(2598)) {
            if (context == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2599)) {
            ExecutorUtils.uiExecutor().execute(() -> Toast.makeText(context, text, Toast.LENGTH_LONG).show());
        }
    }

    public static void showLongToast(Context context, @StringRes int stringResourceId) {
        if (!ListenerUtil.mutListener.listen(2600)) {
            if (context == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2601)) {
            ExecutorUtils.uiExecutor().execute(() -> Toast.makeText(context, context.getString(stringResourceId), Toast.LENGTH_LONG).show());
        }
    }

    public static void showShortToast(Context context, String text) {
        if (!ListenerUtil.mutListener.listen(2602)) {
            if (context == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2603)) {
            ExecutorUtils.uiExecutor().execute(() -> Toast.makeText(context, text, Toast.LENGTH_SHORT).show());
        }
    }

    public static void showShortToast(Context context, @StringRes int stringResourceId) {
        if (!ListenerUtil.mutListener.listen(2604)) {
            if (context == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2605)) {
            ExecutorUtils.uiExecutor().execute(() -> Toast.makeText(context, context.getString(stringResourceId), Toast.LENGTH_SHORT).show());
        }
    }

    public static boolean isPortrait(Context context) {
        Display orientation = ((Activity) context).getWindowManager().getDefaultDisplay();
        if ((ListenerUtil.mutListener.listen(2610) ? (orientation.getWidth() >= orientation.getHeight()) : (ListenerUtil.mutListener.listen(2609) ? (orientation.getWidth() <= orientation.getHeight()) : (ListenerUtil.mutListener.listen(2608) ? (orientation.getWidth() > orientation.getHeight()) : (ListenerUtil.mutListener.listen(2607) ? (orientation.getWidth() != orientation.getHeight()) : (ListenerUtil.mutListener.listen(2606) ? (orientation.getWidth() == orientation.getHeight()) : (orientation.getWidth() < orientation.getHeight()))))))) {
            return true;
        } else {
            return false;
        }
    }

    public static void hideKeyboard(View view) {
        if (!ListenerUtil.mutListener.listen(2614)) {
            if (view != null) {
                InputMethodManager manager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (!ListenerUtil.mutListener.listen(2611)) {
                    view.clearFocus();
                }
                if (!ListenerUtil.mutListener.listen(2613)) {
                    if (manager != null) {
                        if (!ListenerUtil.mutListener.listen(2612)) {
                            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    }
                }
            }
        }
    }

    /**
     * A snack bar which has an action button which on click dismisses the snackbar and invokes the
     * listener passed
     */
    public static void showDismissibleSnackBar(View view, int messageResourceId, int actionButtonResourceId, View.OnClickListener onClickListener) {
        if (!ListenerUtil.mutListener.listen(2615)) {
            if (view.getContext() == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2616)) {
            ExecutorUtils.uiExecutor().execute(() -> {
                Snackbar snackbar = Snackbar.make(view, view.getContext().getString(messageResourceId), Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction(view.getContext().getString(actionButtonResourceId), v -> {
                    snackbar.dismiss();
                    onClickListener.onClick(v);
                });
                snackbar.show();
            });
        }
    }
}

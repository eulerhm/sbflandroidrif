package fr.free.nrw.commons.ui.widget;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * a formatted dialog fragment
 * This class is used by NearbyInfoDialog
 */
public abstract class OverlayDialog extends DialogFragment {

    /**
     * creates a DialogFragment with the correct style and theme
     * @param savedInstanceState bundle re-constructed from a previous saved state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(0)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1)) {
            setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
        }
    }

    /**
     * When the view is created, sets the dialog layout to full screen
     *
     * @param view the view being used
     * @param savedInstanceState bundle re-constructed from a previous saved state
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2)) {
            setDialogLayoutToFullScreen();
        }
        if (!ListenerUtil.mutListener.listen(3)) {
            super.onViewCreated(view, savedInstanceState);
        }
    }

    /**
     * sets the dialog layout to fullscreen
     */
    private void setDialogLayoutToFullScreen() {
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        if (!ListenerUtil.mutListener.listen(4)) {
            window.requestFeature(Window.FEATURE_NO_TITLE);
        }
        if (!ListenerUtil.mutListener.listen(5)) {
            wlp.gravity = Gravity.BOTTOM;
        }
        if (!ListenerUtil.mutListener.listen(6)) {
            wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        }
        if (!ListenerUtil.mutListener.listen(7)) {
            wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        }
        if (!ListenerUtil.mutListener.listen(8)) {
            window.setAttributes(wlp);
        }
    }

    /**
     * builds custom dialog container
     *
     * @param savedInstanceState the previously saved state
     * @return the dialog
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        if (!ListenerUtil.mutListener.listen(9)) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        return dialog;
    }
}

package org.wordpress.android.ui;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import org.wordpress.android.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WPBottomSheetDialogFragment extends BottomSheetDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(requireContext(), getTheme());
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(26661)) {
            super.onResume();
        }
        Dialog dialog = getDialog();
        if (!ListenerUtil.mutListener.listen(26663)) {
            if (dialog != null) {
                if (!ListenerUtil.mutListener.listen(26662)) {
                    restrictMaxWidthForDialog(dialog);
                }
            }
        }
    }

    private void restrictMaxWidthForDialog(@NonNull Dialog dialog) {
        Resources resources = dialog.getContext().getResources();
        int dp = (int) resources.getDimension(R.dimen.bottom_sheet_dialog_width);
        if (!ListenerUtil.mutListener.listen(26675)) {
            // Limit width of bottom sheet on wide screens; non-zero width defined only for sw600dp qualifier.
            if ((ListenerUtil.mutListener.listen(26668) ? (dp >= 0) : (ListenerUtil.mutListener.listen(26667) ? (dp <= 0) : (ListenerUtil.mutListener.listen(26666) ? (dp < 0) : (ListenerUtil.mutListener.listen(26665) ? (dp != 0) : (ListenerUtil.mutListener.listen(26664) ? (dp == 0) : (dp > 0))))))) {
                FrameLayout bottomSheetLayout = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
                if (!ListenerUtil.mutListener.listen(26674)) {
                    if (bottomSheetLayout != null) {
                        ViewParent bottomSheetParent = bottomSheetLayout.getParent();
                        if (!ListenerUtil.mutListener.listen(26673)) {
                            if (bottomSheetParent instanceof CoordinatorLayout) {
                                CoordinatorLayout.LayoutParams coordinatorLayoutParams = (CoordinatorLayout.LayoutParams) bottomSheetLayout.getLayoutParams();
                                if (!ListenerUtil.mutListener.listen(26669)) {
                                    coordinatorLayoutParams.width = dp;
                                }
                                if (!ListenerUtil.mutListener.listen(26670)) {
                                    bottomSheetLayout.setLayoutParams(coordinatorLayoutParams);
                                }
                                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) bottomSheetParent;
                                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) coordinatorLayout.getLayoutParams();
                                if (!ListenerUtil.mutListener.listen(26671)) {
                                    layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
                                }
                                if (!ListenerUtil.mutListener.listen(26672)) {
                                    coordinatorLayout.setLayoutParams(layoutParams);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

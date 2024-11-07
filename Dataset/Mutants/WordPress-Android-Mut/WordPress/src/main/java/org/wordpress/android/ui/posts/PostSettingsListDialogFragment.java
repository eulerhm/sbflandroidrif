package org.wordpress.android.ui.posts;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.wordpress.android.R;
import org.wordpress.android.util.AppLog;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PostSettingsListDialogFragment extends DialogFragment {

    private static final String ARG_DIALOG_TYPE = "dialog_type";

    private static final String ARG_CHECKED_INDEX = "checked_index";

    public static final String TAG = "post_list_settings_dialog_fragment";

    enum DialogType {

        HOMEPAGE_STATUS, POST_STATUS, POST_FORMAT
    }

    interface OnPostSettingsDialogFragmentListener {

        void onPostSettingsFragmentPositiveButtonClicked(@NonNull PostSettingsListDialogFragment fragment);
    }

    private DialogType mDialogType;

    private int mCheckedIndex;

    private OnPostSettingsDialogFragmentListener mListener;

    public static PostSettingsListDialogFragment newInstance(@NonNull DialogType dialogType, int index) {
        PostSettingsListDialogFragment fragment = new PostSettingsListDialogFragment();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(12840)) {
            args.putSerializable(ARG_DIALOG_TYPE, dialogType);
        }
        if (!ListenerUtil.mutListener.listen(12841)) {
            args.putInt(ARG_CHECKED_INDEX, index);
        }
        if (!ListenerUtil.mutListener.listen(12842)) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(12843)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(12844)) {
            setCancelable(true);
        }
    }

    @Override
    public void setArguments(Bundle args) {
        if (!ListenerUtil.mutListener.listen(12845)) {
            super.setArguments(args);
        }
        if (!ListenerUtil.mutListener.listen(12846)) {
            mDialogType = (DialogType) args.getSerializable(ARG_DIALOG_TYPE);
        }
        if (!ListenerUtil.mutListener.listen(12847)) {
            mCheckedIndex = args.getInt(ARG_CHECKED_INDEX);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(12848)) {
            super.onAttach(activity);
        }
        try {
            if (!ListenerUtil.mutListener.listen(12849)) {
                mListener = (OnPostSettingsDialogFragmentListener) activity;
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnPostSettingsDialogFragmentListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(getActivity());
        DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!ListenerUtil.mutListener.listen(12850)) {
                    mCheckedIndex = which;
                }
                if (!ListenerUtil.mutListener.listen(12851)) {
                    getArguments().putInt(ARG_CHECKED_INDEX, mCheckedIndex);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(12858)) {
            switch(mDialogType) {
                case HOMEPAGE_STATUS:
                    if (!ListenerUtil.mutListener.listen(12852)) {
                        builder.setTitle(R.string.post_settings_status);
                    }
                    if (!ListenerUtil.mutListener.listen(12853)) {
                        builder.setSingleChoiceItems(R.array.post_settings_homepage_statuses, mCheckedIndex, clickListener);
                    }
                    break;
                case POST_STATUS:
                    if (!ListenerUtil.mutListener.listen(12854)) {
                        builder.setTitle(R.string.post_settings_status);
                    }
                    if (!ListenerUtil.mutListener.listen(12855)) {
                        builder.setSingleChoiceItems(R.array.post_settings_statuses, mCheckedIndex, clickListener);
                    }
                    break;
                case POST_FORMAT:
                    if (!ListenerUtil.mutListener.listen(12856)) {
                        builder.setTitle(R.string.post_settings_post_format);
                    }
                    if (!ListenerUtil.mutListener.listen(12857)) {
                        builder.setSingleChoiceItems(R.array.post_format_display_names, mCheckedIndex, clickListener);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(12860)) {
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(12859)) {
                        mListener.onPostSettingsFragmentPositiveButtonClicked(PostSettingsListDialogFragment.this);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(12861)) {
            builder.setNegativeButton(R.string.cancel, null);
        }
        return builder.create();
    }

    public DialogType getDialogType() {
        return mDialogType;
    }

    public int getCheckedIndex() {
        return mCheckedIndex;
    }

    @Nullable
    public String getSelectedItem() {
        ListView listView = ((AlertDialog) getDialog()).getListView();
        if (!ListenerUtil.mutListener.listen(12863)) {
            if (listView != null) {
                try {
                    return (String) listView.getItemAtPosition(mCheckedIndex);
                } catch (IndexOutOfBoundsException e) {
                    if (!ListenerUtil.mutListener.listen(12862)) {
                        AppLog.e(AppLog.T.POSTS, e);
                    }
                }
            }
        }
        return null;
    }
}

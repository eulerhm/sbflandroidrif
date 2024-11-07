package org.wordpress.android.widgets;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.wordpress.android.R;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.ui.ActivityLauncher;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * An alert dialog fragment for XML-RPC authentication failures
 */
public class AuthErrorDialogFragment extends DialogFragment {

    public static final int DEFAULT_RESOURCE_ID = -1;

    private int mMessageId = R.string.incorrect_credentials;

    private int mTitleId = R.string.connection_error;

    private SiteModel mSite;

    public void setArgs(int titleResourceId, int messageResourceId, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(28376)) {
            mSite = site;
        }
        if (!ListenerUtil.mutListener.listen(28384)) {
            if ((ListenerUtil.mutListener.listen(28381) ? (titleResourceId >= DEFAULT_RESOURCE_ID) : (ListenerUtil.mutListener.listen(28380) ? (titleResourceId <= DEFAULT_RESOURCE_ID) : (ListenerUtil.mutListener.listen(28379) ? (titleResourceId > DEFAULT_RESOURCE_ID) : (ListenerUtil.mutListener.listen(28378) ? (titleResourceId < DEFAULT_RESOURCE_ID) : (ListenerUtil.mutListener.listen(28377) ? (titleResourceId == DEFAULT_RESOURCE_ID) : (titleResourceId != DEFAULT_RESOURCE_ID))))))) {
                if (!ListenerUtil.mutListener.listen(28383)) {
                    mTitleId = titleResourceId;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28382)) {
                    mTitleId = R.string.connection_error;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28392)) {
            if ((ListenerUtil.mutListener.listen(28389) ? (messageResourceId >= DEFAULT_RESOURCE_ID) : (ListenerUtil.mutListener.listen(28388) ? (messageResourceId <= DEFAULT_RESOURCE_ID) : (ListenerUtil.mutListener.listen(28387) ? (messageResourceId > DEFAULT_RESOURCE_ID) : (ListenerUtil.mutListener.listen(28386) ? (messageResourceId < DEFAULT_RESOURCE_ID) : (ListenerUtil.mutListener.listen(28385) ? (messageResourceId == DEFAULT_RESOURCE_ID) : (messageResourceId != DEFAULT_RESOURCE_ID))))))) {
                if (!ListenerUtil.mutListener.listen(28391)) {
                    mMessageId = messageResourceId;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28390)) {
                    mMessageId = R.string.incorrect_credentials;
                }
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(28393)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(28394)) {
            this.setCancelable(true);
        }
        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        if (!ListenerUtil.mutListener.listen(28395)) {
            setStyle(style, theme);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b = new MaterialAlertDialogBuilder(getActivity());
        if (!ListenerUtil.mutListener.listen(28396)) {
            b.setTitle(mTitleId);
        }
        if (!ListenerUtil.mutListener.listen(28397)) {
            b.setMessage(mMessageId);
        }
        if (!ListenerUtil.mutListener.listen(28398)) {
            b.setCancelable(true);
        }
        if (!ListenerUtil.mutListener.listen(28400)) {
            b.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(28399)) {
                        ActivityLauncher.viewBlogSettingsForResult(getActivity(), mSite);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(28401)) {
            b.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        }
        return b.create();
    }
}

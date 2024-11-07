package org.wordpress.android.ui.prefs;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.wordpress.android.R;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RelatedPostsDialog extends DialogFragment implements DialogInterface.OnClickListener, CompoundButton.OnCheckedChangeListener {

    /**
     * boolean
     * <p>
     * Sets the default state of the Show Related Posts switch. The switch is off by default.
     */
    public static final String SHOW_RELATED_POSTS_KEY = "related-posts";

    /**
     * boolean
     * <p>
     * Sets the default state of the Show Headers checkbox. The checkbox is off by default.
     */
    public static final String SHOW_HEADER_KEY = "show-header";

    /**
     * boolean
     * <p>
     * Sets the default state of the Show Images checkbox. The checkbox is off by default.
     */
    public static final String SHOW_IMAGES_KEY = "show-images";

    private SwitchCompat mShowRelatedPosts;

    private CheckBox mShowHeader;

    private CheckBox mShowImages;

    private TextView mPreviewHeader;

    private TextView mRelatedPostsListHeader;

    private LinearLayout mRelatedPostsList;

    private List<ImageView> mPreviewImages;

    private boolean mConfirmed;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // noinspection InflateParams
        View v = inflater.inflate(R.layout.related_posts_dialog, null);
        if (!ListenerUtil.mutListener.listen(14970)) {
            mShowRelatedPosts = v.findViewById(R.id.toggle_related_posts_switch);
        }
        if (!ListenerUtil.mutListener.listen(14971)) {
            mShowHeader = v.findViewById(R.id.show_header_checkbox);
        }
        if (!ListenerUtil.mutListener.listen(14972)) {
            mShowImages = v.findViewById(R.id.show_images_checkbox);
        }
        if (!ListenerUtil.mutListener.listen(14973)) {
            mPreviewHeader = v.findViewById(R.id.preview_header);
        }
        if (!ListenerUtil.mutListener.listen(14974)) {
            mRelatedPostsListHeader = v.findViewById(R.id.related_posts_list_header);
        }
        if (!ListenerUtil.mutListener.listen(14975)) {
            mRelatedPostsList = v.findViewById(R.id.related_posts_list);
        }
        if (!ListenerUtil.mutListener.listen(14976)) {
            mPreviewImages = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(14977)) {
            mPreviewImages.add(v.findViewById(R.id.related_post_image1));
        }
        if (!ListenerUtil.mutListener.listen(14978)) {
            mPreviewImages.add(v.findViewById(R.id.related_post_image2));
        }
        if (!ListenerUtil.mutListener.listen(14979)) {
            mPreviewImages.add(v.findViewById(R.id.related_post_image3));
        }
        Bundle args = getArguments();
        if (!ListenerUtil.mutListener.listen(14983)) {
            if (args != null) {
                if (!ListenerUtil.mutListener.listen(14980)) {
                    mShowRelatedPosts.setChecked(args.getBoolean(SHOW_RELATED_POSTS_KEY));
                }
                if (!ListenerUtil.mutListener.listen(14981)) {
                    mShowHeader.setChecked(args.getBoolean(SHOW_HEADER_KEY));
                }
                if (!ListenerUtil.mutListener.listen(14982)) {
                    mShowImages.setChecked(args.getBoolean(SHOW_IMAGES_KEY));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14984)) {
            toggleShowHeader(mShowHeader.isChecked());
        }
        if (!ListenerUtil.mutListener.listen(14985)) {
            toggleShowImages(mShowImages.isChecked());
        }
        if (!ListenerUtil.mutListener.listen(14986)) {
            mShowRelatedPosts.setOnCheckedChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14987)) {
            mShowHeader.setOnCheckedChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14988)) {
            mShowImages.setOnCheckedChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14989)) {
            toggleViews(mShowRelatedPosts.isChecked());
        }
        int topOffset = getResources().getDimensionPixelOffset(R.dimen.settings_fragment_dialog_vertical_inset);
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(getActivity()).setBackgroundInsetTop(topOffset).setBackgroundInsetBottom(topOffset);
        // noinspection InflateParams
        View titleView = inflater.inflate(R.layout.detail_list_preference_title, null);
        TextView titleText = titleView.findViewById(R.id.title);
        if (!ListenerUtil.mutListener.listen(14990)) {
            titleText.setText(R.string.site_settings_related_posts_title);
        }
        if (!ListenerUtil.mutListener.listen(14991)) {
            titleText.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        }
        if (!ListenerUtil.mutListener.listen(14992)) {
            builder.setCustomTitle(titleView);
        }
        if (!ListenerUtil.mutListener.listen(14993)) {
            builder.setPositiveButton(android.R.string.ok, this);
        }
        if (!ListenerUtil.mutListener.listen(14994)) {
            builder.setNegativeButton(R.string.cancel, this);
        }
        if (!ListenerUtil.mutListener.listen(14995)) {
            builder.setView(v);
        }
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (!ListenerUtil.mutListener.listen(14996)) {
            mConfirmed = which == DialogInterface.BUTTON_POSITIVE;
        }
        if (!ListenerUtil.mutListener.listen(14997)) {
            dismiss();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!ListenerUtil.mutListener.listen(15001)) {
            if (buttonView == mShowRelatedPosts) {
                if (!ListenerUtil.mutListener.listen(15000)) {
                    toggleViews(isChecked);
                }
            } else if (buttonView == mShowHeader) {
                if (!ListenerUtil.mutListener.listen(14999)) {
                    toggleShowHeader(isChecked);
                }
            } else if (buttonView == mShowImages) {
                if (!ListenerUtil.mutListener.listen(14998)) {
                    toggleShowImages(isChecked);
                }
            }
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        // See https://developer.android.com/reference/android/app/Fragment
        android.app.Fragment target = getTargetFragment();
        if (!ListenerUtil.mutListener.listen(15003)) {
            if (target != null) {
                if (!ListenerUtil.mutListener.listen(15002)) {
                    target.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, getResultIntent());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15004)) {
            super.onDismiss(dialog);
        }
    }

    private void toggleShowHeader(boolean show) {
        if (!ListenerUtil.mutListener.listen(15007)) {
            if (show) {
                if (!ListenerUtil.mutListener.listen(15006)) {
                    mRelatedPostsListHeader.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(15005)) {
                    mRelatedPostsListHeader.setVisibility(View.GONE);
                }
            }
        }
    }

    private void toggleShowImages(boolean show) {
        int visibility = show ? View.VISIBLE : View.GONE;
        if (!ListenerUtil.mutListener.listen(15009)) {
            {
                long _loopCounter249 = 0;
                for (ImageView view : mPreviewImages) {
                    ListenerUtil.loopListener.listen("_loopCounter249", ++_loopCounter249);
                    if (!ListenerUtil.mutListener.listen(15008)) {
                        view.setVisibility(visibility);
                    }
                }
            }
        }
    }

    private Intent getResultIntent() {
        if (!ListenerUtil.mutListener.listen(15010)) {
            if (mConfirmed) {
                return new Intent().putExtra(SHOW_RELATED_POSTS_KEY, mShowRelatedPosts.isChecked()).putExtra(SHOW_HEADER_KEY, mShowHeader.isChecked()).putExtra(SHOW_IMAGES_KEY, mShowImages.isChecked());
            }
        }
        return null;
    }

    private void toggleViews(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(15011)) {
            mShowHeader.setEnabled(enabled);
        }
        if (!ListenerUtil.mutListener.listen(15012)) {
            mShowImages.setEnabled(enabled);
        }
        if (!ListenerUtil.mutListener.listen(15013)) {
            mPreviewHeader.setEnabled(enabled);
        }
        if (!ListenerUtil.mutListener.listen(15014)) {
            mRelatedPostsListHeader.setEnabled(enabled);
        }
        if (!ListenerUtil.mutListener.listen(15017)) {
            if (enabled) {
                if (!ListenerUtil.mutListener.listen(15016)) {
                    mRelatedPostsList.setAlpha(1.0f);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(15015)) {
                    mRelatedPostsList.setAlpha(0.5f);
                }
            }
        }
    }
}

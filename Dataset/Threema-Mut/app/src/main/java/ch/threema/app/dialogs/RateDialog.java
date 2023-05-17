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
package ch.threema.app.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.RatingService;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RateDialog extends ThreemaDialogFragment {

    private static final String BUNDLE_RATE_STAR = "rs";

    private RateDialogClickListener callback;

    private Activity activity;

    private AlertDialog alertDialog;

    private int rating;

    private TextInputEditText editText = null;

    private String tag = null;

    private PreferenceService preferenceService;

    private Integer[] starMap = { R.id.star_one, R.id.star_two, R.id.star_three, R.id.star_four, R.id.star_five };

    public static RateDialog newInstance(String title) {
        RateDialog dialog = new RateDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13908)) {
            args.putString("title", title);
        }
        if (!ListenerUtil.mutListener.listen(13909)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public interface RateDialogClickListener {

        void onYes(String tag, int rating, String text);

        void onCancel(String tag);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13910)) {
            super.onCreate(savedInstanceState);
        }
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(13912)) {
            if (serviceManager == null) {
                if (!ListenerUtil.mutListener.listen(13911)) {
                    dismiss();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13913)) {
            preferenceService = serviceManager.getPreferenceService();
        }
        if (!ListenerUtil.mutListener.listen(13915)) {
            if (preferenceService == null) {
                if (!ListenerUtil.mutListener.listen(13914)) {
                    dismiss();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(13920)) {
            if (callback == null) {
                try {
                    if (!ListenerUtil.mutListener.listen(13916)) {
                        callback = (RateDialogClickListener) getTargetFragment();
                    }
                } catch (ClassCastException e) {
                }
                if (!ListenerUtil.mutListener.listen(13919)) {
                    // called from an activity rather than a fragment
                    if (callback == null) {
                        if (!ListenerUtil.mutListener.listen(13918)) {
                            if (activity instanceof RateDialogClickListener) {
                                if (!ListenerUtil.mutListener.listen(13917)) {
                                    callback = (RateDialogClickListener) activity;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(13921)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(13922)) {
            this.activity = activity;
        }
    }

    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        String positive = getString(R.string.rate_positive);
        String negative = getString(R.string.cancel);
        if (!ListenerUtil.mutListener.listen(13923)) {
            tag = this.getTag();
        }
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.dialog_rate, null);
        if (!ListenerUtil.mutListener.listen(13924)) {
            editText = dialogView.findViewById(R.id.feedback_edittext);
        }
        final LinearLayout feedbackLayout = dialogView.findViewById(R.id.feedback_layout);
        if (!ListenerUtil.mutListener.listen(13927)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(13925)) {
                    rating = savedInstanceState.getInt(BUNDLE_RATE_STAR, 0);
                }
                if (!ListenerUtil.mutListener.listen(13926)) {
                    onStarClick(rating, feedbackLayout, dialogView);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13941)) {
            {
                long _loopCounter131 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(13940) ? (i >= starMap.length) : (ListenerUtil.mutListener.listen(13939) ? (i <= starMap.length) : (ListenerUtil.mutListener.listen(13938) ? (i > starMap.length) : (ListenerUtil.mutListener.listen(13937) ? (i != starMap.length) : (ListenerUtil.mutListener.listen(13936) ? (i == starMap.length) : (i < starMap.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter131", ++_loopCounter131);
                    ImageView starView = dialogView.findViewById(starMap[i]);
                    if (!ListenerUtil.mutListener.listen(13932)) {
                        starView.setTag((ListenerUtil.mutListener.listen(13931) ? (i % 1) : (ListenerUtil.mutListener.listen(13930) ? (i / 1) : (ListenerUtil.mutListener.listen(13929) ? (i * 1) : (ListenerUtil.mutListener.listen(13928) ? (i - 1) : (i + 1))))));
                    }
                    if (!ListenerUtil.mutListener.listen(13933)) {
                        ConfigUtils.themeImageView(activity, starView);
                    }
                    if (!ListenerUtil.mutListener.listen(13935)) {
                        starView.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                if (!ListenerUtil.mutListener.listen(13934)) {
                                    onStarClick((int) v.getTag(), feedbackLayout, dialogView);
                                }
                            }
                        });
                    }
                }
            }
        }
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), getTheme());
        if (!ListenerUtil.mutListener.listen(13942)) {
            builder.setView(dialogView);
        }
        if (!ListenerUtil.mutListener.listen(13944)) {
            if (!TestUtil.empty(title)) {
                if (!ListenerUtil.mutListener.listen(13943)) {
                    builder.setTitle(title);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13947)) {
            if (preferenceService != null) {
                String review = preferenceService.getRatingReviewText();
                if (!ListenerUtil.mutListener.listen(13946)) {
                    if (!TestUtil.empty(review)) {
                        if (!ListenerUtil.mutListener.listen(13945)) {
                            editText.append(review);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13948)) {
            builder.setPositiveButton(positive, null);
        }
        if (!ListenerUtil.mutListener.listen(13950)) {
            builder.setNegativeButton(negative, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    if (!ListenerUtil.mutListener.listen(13949)) {
                        callback.onCancel(tag);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(13951)) {
            alertDialog = builder.create();
        }
        if (!ListenerUtil.mutListener.listen(13952)) {
            setCancelable(false);
        }
        return alertDialog;
    }

    private void sendReview(final String tag, final int rating, final String text) {
        if (!ListenerUtil.mutListener.listen(13969)) {
            new AsyncTask<Void, Void, Boolean>() {

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(13953)) {
                        alertDialog.findViewById(R.id.text_input_layout).setVisibility(View.INVISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(13954)) {
                        alertDialog.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(13955)) {
                        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setVisibility(View.INVISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(13956)) {
                        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                protected Boolean doInBackground(Void... params) {
                    if (!ListenerUtil.mutListener.listen(13958)) {
                        if (!TestUtil.empty(text)) {
                            if (!ListenerUtil.mutListener.listen(13957)) {
                                preferenceService.setRatingReviewText(text);
                            }
                        }
                    }
                    // Create the rating service to send the rating
                    RatingService ratingService = new RatingService(preferenceService);
                    if (!ListenerUtil.mutListener.listen(13959)) {
                        // simulate some activity to show progress bar
                        SystemClock.sleep(1500);
                    }
                    return ratingService.sendRating(rating, text);
                }

                @Override
                protected void onPostExecute(Boolean success) {
                    if (!ListenerUtil.mutListener.listen(13968)) {
                        if (isAdded()) {
                            if (!ListenerUtil.mutListener.listen(13967)) {
                                if (success) {
                                    if (!ListenerUtil.mutListener.listen(13965)) {
                                        callback.onYes(tag, rating, text);
                                    }
                                    if (!ListenerUtil.mutListener.listen(13966)) {
                                        dismiss();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(13960)) {
                                        Toast.makeText(ThreemaApplication.getAppContext(), getString(R.string.rate_error), Toast.LENGTH_LONG).show();
                                    }
                                    if (!ListenerUtil.mutListener.listen(13961)) {
                                        alertDialog.findViewById(R.id.text_input_layout).setVisibility(View.VISIBLE);
                                    }
                                    if (!ListenerUtil.mutListener.listen(13962)) {
                                        alertDialog.findViewById(R.id.progress_bar).setVisibility(View.GONE);
                                    }
                                    if (!ListenerUtil.mutListener.listen(13963)) {
                                        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setVisibility(View.VISIBLE);
                                    }
                                    if (!ListenerUtil.mutListener.listen(13964)) {
                                        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    private void onStarClick(int currentRating, View feedbackLayout, View dialogView) {
        if (!ListenerUtil.mutListener.listen(13970)) {
            rating = currentRating;
        }
        if (!ListenerUtil.mutListener.listen(13971)) {
            updateStarDisplay(dialogView);
        }
        if (!ListenerUtil.mutListener.listen(13981)) {
            if ((ListenerUtil.mutListener.listen(13976) ? (rating >= 0) : (ListenerUtil.mutListener.listen(13975) ? (rating <= 0) : (ListenerUtil.mutListener.listen(13974) ? (rating < 0) : (ListenerUtil.mutListener.listen(13973) ? (rating != 0) : (ListenerUtil.mutListener.listen(13972) ? (rating == 0) : (rating > 0))))))) {
                if (!ListenerUtil.mutListener.listen(13980)) {
                    if (!feedbackLayout.isShown()) {
                        if (!ListenerUtil.mutListener.listen(13979)) {
                            toggleLayout(feedbackLayout, true);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13978)) {
                    if (feedbackLayout.isShown()) {
                        if (!ListenerUtil.mutListener.listen(13977)) {
                            toggleLayout(feedbackLayout, false);
                        }
                    }
                }
            }
        }
    }

    private void updateStarDisplay(View dialogView) {
        if (!ListenerUtil.mutListener.listen(13993)) {
            {
                long _loopCounter132 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(13992) ? (i >= starMap.length) : (ListenerUtil.mutListener.listen(13991) ? (i <= starMap.length) : (ListenerUtil.mutListener.listen(13990) ? (i > starMap.length) : (ListenerUtil.mutListener.listen(13989) ? (i != starMap.length) : (ListenerUtil.mutListener.listen(13988) ? (i == starMap.length) : (i < starMap.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter132", ++_loopCounter132);
                    ImageView v = dialogView.findViewById(starMap[i]);
                    if (!ListenerUtil.mutListener.listen(13987)) {
                        v.setImageResource((ListenerUtil.mutListener.listen(13986) ? (i >= rating) : (ListenerUtil.mutListener.listen(13985) ? (i <= rating) : (ListenerUtil.mutListener.listen(13984) ? (i > rating) : (ListenerUtil.mutListener.listen(13983) ? (i != rating) : (ListenerUtil.mutListener.listen(13982) ? (i == rating) : (i < rating)))))) ? R.drawable.ic_star_golden_24dp : R.drawable.ic_star_outline_24dp);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13995)) {
            if (alertDialog != null) {
                if (!ListenerUtil.mutListener.listen(13994)) {
                    alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(true);
                }
            }
        }
    }

    private void toggleLayout(View v, boolean show) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!ListenerUtil.mutListener.listen(14005)) {
            if (!show) {
                if (!ListenerUtil.mutListener.listen(14001)) {
                    slide_up(v);
                }
                if (!ListenerUtil.mutListener.listen(14002)) {
                    v.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(14004)) {
                    if (imm != null) {
                        if (!ListenerUtil.mutListener.listen(14003)) {
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13996)) {
                    v.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(13997)) {
                    slide_down(v);
                }
                if (!ListenerUtil.mutListener.listen(13998)) {
                    v.requestFocus();
                }
                if (!ListenerUtil.mutListener.listen(14000)) {
                    if (imm != null) {
                        if (!ListenerUtil.mutListener.listen(13999)) {
                            imm.showSoftInput(v, 0);
                        }
                    }
                }
            }
        }
    }

    public void slide_down(View v) {
        Animation a = AnimationUtils.loadAnimation(activity, R.anim.slide_down);
        if (!ListenerUtil.mutListener.listen(14010)) {
            if (a != null) {
                if (!ListenerUtil.mutListener.listen(14006)) {
                    a.reset();
                }
                if (!ListenerUtil.mutListener.listen(14009)) {
                    if (v != null) {
                        if (!ListenerUtil.mutListener.listen(14007)) {
                            v.clearAnimation();
                        }
                        if (!ListenerUtil.mutListener.listen(14008)) {
                            v.startAnimation(a);
                        }
                    }
                }
            }
        }
    }

    public void slide_up(View v) {
        Animation a = AnimationUtils.loadAnimation(activity, R.anim.slide_up);
        if (!ListenerUtil.mutListener.listen(14015)) {
            if (a != null) {
                if (!ListenerUtil.mutListener.listen(14011)) {
                    a.reset();
                }
                if (!ListenerUtil.mutListener.listen(14014)) {
                    if (v != null) {
                        if (!ListenerUtil.mutListener.listen(14012)) {
                            v.clearAnimation();
                        }
                        if (!ListenerUtil.mutListener.listen(14013)) {
                            v.startAnimation(a);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(14016)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(14027)) {
            if (alertDialog != null) {
                Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                if (!ListenerUtil.mutListener.listen(14022)) {
                    positiveButton.setEnabled((ListenerUtil.mutListener.listen(14021) ? (rating >= 0) : (ListenerUtil.mutListener.listen(14020) ? (rating <= 0) : (ListenerUtil.mutListener.listen(14019) ? (rating < 0) : (ListenerUtil.mutListener.listen(14018) ? (rating != 0) : (ListenerUtil.mutListener.listen(14017) ? (rating == 0) : (rating > 0)))))));
                }
                ColorStateList colorStateList = DialogUtil.getButtonColorStateList(activity);
                if (!ListenerUtil.mutListener.listen(14023)) {
                    positiveButton.setTextColor(colorStateList);
                }
                if (!ListenerUtil.mutListener.listen(14024)) {
                    negativeButton.setTextColor(colorStateList);
                }
                if (!ListenerUtil.mutListener.listen(14026)) {
                    positiveButton.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(14025)) {
                                sendReview(tag, rating, editText.getText().toString());
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(14028)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(14029)) {
            outState.putInt(BUNDLE_RATE_STAR, rating);
        }
    }
}

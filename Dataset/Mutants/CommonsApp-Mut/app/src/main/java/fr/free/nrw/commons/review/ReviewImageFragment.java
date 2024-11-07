package fr.free.nrw.commons.review;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.di.CommonsDaggerSupportFragment;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReviewImageFragment extends CommonsDaggerSupportFragment {

    static final int CATEGORY = 2;

    private static final int SPAM = 0;

    private static final int COPYRIGHT = 1;

    private static final int THANKS = 3;

    private int position;

    public ProgressBar progressBar;

    @BindView(R.id.tv_review_question)
    TextView textViewQuestion;

    @BindView(R.id.tv_review_question_context)
    TextView textViewQuestionContext;

    @BindView(R.id.button_yes)
    Button yesButton;

    @BindView(R.id.button_no)
    Button noButton;

    // Constant variable used to store user's key name for onSaveInstanceState method
    private final String SAVED_USER = "saved_user";

    // Variable that stores the value of user
    private String user;

    public void update(int position) {
        if (!ListenerUtil.mutListener.listen(5853)) {
            this.position = position;
        }
    }

    private String updateCategoriesQuestion() {
        Media media = getReviewActivity().getMedia();
        if (!ListenerUtil.mutListener.listen(5869)) {
            if ((ListenerUtil.mutListener.listen(5855) ? ((ListenerUtil.mutListener.listen(5854) ? (media != null || media.getCategoriesHiddenStatus() != null) : (media != null && media.getCategoriesHiddenStatus() != null)) || isAdded()) : ((ListenerUtil.mutListener.listen(5854) ? (media != null || media.getCategoriesHiddenStatus() != null) : (media != null && media.getCategoriesHiddenStatus() != null)) && isAdded()))) {
                // Filter category name attribute from all categories
                List<String> categories = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(5864)) {
                    {
                        long _loopCounter83 = 0;
                        for (String key : media.getCategoriesHiddenStatus().keySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter83", ++_loopCounter83);
                            String value = String.valueOf(key);
                            // so remove the prefix "Category:"
                            int index = key.indexOf("Category:");
                            if (!ListenerUtil.mutListener.listen(5862)) {
                                if ((ListenerUtil.mutListener.listen(5860) ? (index >= 0) : (ListenerUtil.mutListener.listen(5859) ? (index <= 0) : (ListenerUtil.mutListener.listen(5858) ? (index > 0) : (ListenerUtil.mutListener.listen(5857) ? (index < 0) : (ListenerUtil.mutListener.listen(5856) ? (index != 0) : (index == 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(5861)) {
                                        value = key.substring(9);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(5863)) {
                                categories.add(value);
                            }
                        }
                    }
                }
                String catString = TextUtils.join(", ", categories);
                if (!ListenerUtil.mutListener.listen(5868)) {
                    if ((ListenerUtil.mutListener.listen(5866) ? ((ListenerUtil.mutListener.listen(5865) ? (catString != null || !catString.equals("")) : (catString != null && !catString.equals(""))) || textViewQuestionContext != null) : ((ListenerUtil.mutListener.listen(5865) ? (catString != null || !catString.equals("")) : (catString != null && !catString.equals(""))) && textViewQuestionContext != null))) {
                        if (!ListenerUtil.mutListener.listen(5867)) {
                            catString = "<b>" + catString + "</b>";
                        }
                        String stringToConvertHtml = String.format(getResources().getString(R.string.review_category_explanation), catString);
                        return Html.fromHtml(stringToConvertHtml).toString();
                    }
                }
            }
        }
        return getResources().getString(R.string.review_no_category);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5870)) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5871)) {
            position = getArguments().getInt("position");
        }
        View layoutView = inflater.inflate(R.layout.fragment_review_image, container, false);
        if (!ListenerUtil.mutListener.listen(5872)) {
            ButterKnife.bind(this, layoutView);
        }
        String question, explanation = null, yesButtonText, noButtonText;
        switch(position) {
            case SPAM:
                question = getString(R.string.review_spam);
                if (!ListenerUtil.mutListener.listen(5873)) {
                    explanation = getString(R.string.review_spam_explanation);
                }
                yesButtonText = getString(R.string.yes);
                noButtonText = getString(R.string.no);
                if (!ListenerUtil.mutListener.listen(5874)) {
                    noButton.setOnClickListener(view -> getReviewActivity().reviewController.reportSpam(requireActivity(), getReviewCallback()));
                }
                break;
            case COPYRIGHT:
                if (!ListenerUtil.mutListener.listen(5875)) {
                    enableButtons();
                }
                question = getString(R.string.review_copyright);
                if (!ListenerUtil.mutListener.listen(5876)) {
                    explanation = getString(R.string.review_copyright_explanation);
                }
                yesButtonText = getString(R.string.yes);
                noButtonText = getString(R.string.no);
                if (!ListenerUtil.mutListener.listen(5877)) {
                    noButton.setOnClickListener(view -> getReviewActivity().reviewController.reportPossibleCopyRightViolation(requireActivity(), getReviewCallback()));
                }
                break;
            case CATEGORY:
                if (!ListenerUtil.mutListener.listen(5878)) {
                    enableButtons();
                }
                question = getString(R.string.review_category);
                if (!ListenerUtil.mutListener.listen(5879)) {
                    explanation = updateCategoriesQuestion();
                }
                yesButtonText = getString(R.string.yes);
                noButtonText = getString(R.string.no);
                if (!ListenerUtil.mutListener.listen(5880)) {
                    noButton.setOnClickListener(view -> {
                        getReviewActivity().reviewController.reportWrongCategory(requireActivity(), getReviewCallback());
                        getReviewActivity().swipeToNext();
                    });
                }
                break;
            case THANKS:
                if (!ListenerUtil.mutListener.listen(5881)) {
                    enableButtons();
                }
                question = getString(R.string.review_thanks);
                if (!ListenerUtil.mutListener.listen(5885)) {
                    if (getReviewActivity().reviewController.firstRevision != null) {
                        if (!ListenerUtil.mutListener.listen(5884)) {
                            user = getReviewActivity().reviewController.firstRevision.getUser();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5883)) {
                            if (savedInstanceState != null) {
                                if (!ListenerUtil.mutListener.listen(5882)) {
                                    user = savedInstanceState.getString(SAVED_USER);
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5887)) {
                    // if the user is null because of whatsoever reason, review will not be sent anyways
                    if (!TextUtils.isEmpty(user)) {
                        if (!ListenerUtil.mutListener.listen(5886)) {
                            explanation = getString(R.string.review_thanks_explanation, user);
                        }
                    }
                }
                // Note that the yes and no buttons are swapped in this section
                yesButtonText = getString(R.string.review_thanks_yes_button_text);
                noButtonText = getString(R.string.review_thanks_no_button_text);
                if (!ListenerUtil.mutListener.listen(5888)) {
                    yesButton.setTextColor(Color.parseColor("#116aaa"));
                }
                if (!ListenerUtil.mutListener.listen(5889)) {
                    noButton.setTextColor(Color.parseColor("#228b22"));
                }
                if (!ListenerUtil.mutListener.listen(5890)) {
                    noButton.setOnClickListener(view -> {
                        getReviewActivity().reviewController.sendThanks(getReviewActivity());
                        getReviewActivity().swipeToNext();
                    });
                }
                break;
            default:
                if (!ListenerUtil.mutListener.listen(5891)) {
                    enableButtons();
                }
                question = "How did we get here?";
                if (!ListenerUtil.mutListener.listen(5892)) {
                    explanation = "No idea.";
                }
                yesButtonText = "yes";
                noButtonText = "no";
        }
        if (!ListenerUtil.mutListener.listen(5893)) {
            textViewQuestion.setText(question);
        }
        if (!ListenerUtil.mutListener.listen(5894)) {
            textViewQuestionContext.setText(explanation);
        }
        if (!ListenerUtil.mutListener.listen(5895)) {
            yesButton.setText(yesButtonText);
        }
        if (!ListenerUtil.mutListener.listen(5896)) {
            noButton.setText(noButtonText);
        }
        return layoutView;
    }

    /**
     * This method will be called when configuration changes happen
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(5897)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(5898)) {
            // Save user name when configuration changes happen
            outState.putString(SAVED_USER, user);
        }
    }

    private ReviewController.ReviewCallback getReviewCallback() {
        return new ReviewController.ReviewCallback() {

            @Override
            public void onSuccess() {
                if (!ListenerUtil.mutListener.listen(5899)) {
                    getReviewActivity().runRandomizer();
                }
            }

            @Override
            public void onFailure() {
            }
        };
    }

    /**
     * This function is called when an image has
     * been loaded to enable the review buttons.
     */
    public void enableButtons() {
        if (!ListenerUtil.mutListener.listen(5900)) {
            yesButton.setEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(5901)) {
            yesButton.setAlpha(1);
        }
        if (!ListenerUtil.mutListener.listen(5902)) {
            noButton.setEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(5903)) {
            noButton.setAlpha(1);
        }
    }

    /**
     * This function is called when an image is being loaded
     * to disable the review buttons
     */
    public void disableButtons() {
        if (!ListenerUtil.mutListener.listen(5904)) {
            yesButton.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(5905)) {
            yesButton.setAlpha(0.5f);
        }
        if (!ListenerUtil.mutListener.listen(5906)) {
            noButton.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(5907)) {
            noButton.setAlpha(0.5f);
        }
    }

    @OnClick(R.id.button_yes)
    void onYesButtonClicked() {
        if (!ListenerUtil.mutListener.listen(5908)) {
            getReviewActivity().swipeToNext();
        }
    }

    private ReviewActivity getReviewActivity() {
        return (ReviewActivity) requireActivity();
    }
}

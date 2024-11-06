package fr.free.nrw.commons.quiz;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import fr.free.nrw.commons.databinding.ActivityQuizBinding;
import java.util.ArrayList;
import fr.free.nrw.commons.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class QuizActivity extends AppCompatActivity {

    private ActivityQuizBinding binding;

    private final QuizController quizController = new QuizController();

    private ArrayList<QuizQuestion> quiz = new ArrayList<>();

    private int questionIndex = 0;

    private int score;

    /**
     * isPositiveAnswerChecked : represents yes click event
     */
    private boolean isPositiveAnswerChecked;

    /**
     * isNegativeAnswerChecked : represents no click event
     */
    private boolean isNegativeAnswerChecked;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1910)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1911)) {
            binding = ActivityQuizBinding.inflate(getLayoutInflater());
        }
        if (!ListenerUtil.mutListener.listen(1912)) {
            setContentView(binding.getRoot());
        }
        if (!ListenerUtil.mutListener.listen(1913)) {
            quizController.initialize(this);
        }
        if (!ListenerUtil.mutListener.listen(1914)) {
            setSupportActionBar(binding.toolbar.toolbar);
        }
        if (!ListenerUtil.mutListener.listen(1915)) {
            binding.nextButton.setOnClickListener(view -> notKnowAnswer());
        }
        if (!ListenerUtil.mutListener.listen(1916)) {
            displayQuestion();
        }
    }

    /**
     * to move to next question and check whether answer is selected or not
     */
    public void setNextQuestion() {
        if (!ListenerUtil.mutListener.listen(1925)) {
            if ((ListenerUtil.mutListener.listen(1923) ? ((ListenerUtil.mutListener.listen(1921) ? (questionIndex >= quiz.size()) : (ListenerUtil.mutListener.listen(1920) ? (questionIndex > quiz.size()) : (ListenerUtil.mutListener.listen(1919) ? (questionIndex < quiz.size()) : (ListenerUtil.mutListener.listen(1918) ? (questionIndex != quiz.size()) : (ListenerUtil.mutListener.listen(1917) ? (questionIndex == quiz.size()) : (questionIndex <= quiz.size())))))) || ((ListenerUtil.mutListener.listen(1922) ? (isPositiveAnswerChecked && isNegativeAnswerChecked) : (isPositiveAnswerChecked || isNegativeAnswerChecked)))) : ((ListenerUtil.mutListener.listen(1921) ? (questionIndex >= quiz.size()) : (ListenerUtil.mutListener.listen(1920) ? (questionIndex > quiz.size()) : (ListenerUtil.mutListener.listen(1919) ? (questionIndex < quiz.size()) : (ListenerUtil.mutListener.listen(1918) ? (questionIndex != quiz.size()) : (ListenerUtil.mutListener.listen(1917) ? (questionIndex == quiz.size()) : (questionIndex <= quiz.size())))))) && ((ListenerUtil.mutListener.listen(1922) ? (isPositiveAnswerChecked && isNegativeAnswerChecked) : (isPositiveAnswerChecked || isNegativeAnswerChecked)))))) {
                if (!ListenerUtil.mutListener.listen(1924)) {
                    evaluateScore();
                }
            }
        }
    }

    public void notKnowAnswer() {
        if (!ListenerUtil.mutListener.listen(1926)) {
            customAlert("Information", quiz.get(questionIndex).getAnswerMessage());
        }
    }

    /**
     * to give warning before ending quiz
     */
    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(1927)) {
            new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.warning)).setMessage(getResources().getString(R.string.quiz_back_button)).setPositiveButton(R.string.continue_message, (dialog, which) -> {
                final Intent intent = new Intent(this, QuizResultActivity.class);
                dialog.dismiss();
                intent.putExtra("QuizResult", score);
                startActivity(intent);
            }).setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss()).create().show();
        }
    }

    /**
     * to display the question
     */
    public void displayQuestion() {
        if (!ListenerUtil.mutListener.listen(1928)) {
            quiz = quizController.getQuiz();
        }
        if (!ListenerUtil.mutListener.listen(1929)) {
            binding.question.questionText.setText(quiz.get(questionIndex).getQuestion());
        }
        if (!ListenerUtil.mutListener.listen(1930)) {
            binding.questionTitle.setText(getResources().getString(R.string.question) + quiz.get(questionIndex).getQuestionNumber());
        }
        if (!ListenerUtil.mutListener.listen(1931)) {
            binding.question.questionImage.setHierarchy(GenericDraweeHierarchyBuilder.newInstance(getResources()).setFailureImage(VectorDrawableCompat.create(getResources(), R.drawable.ic_error_outline_black_24dp, getTheme())).setProgressBarImage(new ProgressBarDrawable()).build());
        }
        if (!ListenerUtil.mutListener.listen(1932)) {
            binding.question.questionImage.setImageURI(quiz.get(questionIndex).getUrl());
        }
        if (!ListenerUtil.mutListener.listen(1933)) {
            isPositiveAnswerChecked = false;
        }
        if (!ListenerUtil.mutListener.listen(1934)) {
            isNegativeAnswerChecked = false;
        }
        if (!ListenerUtil.mutListener.listen(1935)) {
            binding.answer.quizPositiveAnswer.setOnClickListener(view -> {
                isPositiveAnswerChecked = true;
                setNextQuestion();
            });
        }
        if (!ListenerUtil.mutListener.listen(1936)) {
            binding.answer.quizNegativeAnswer.setOnClickListener(view -> {
                isNegativeAnswerChecked = true;
                setNextQuestion();
            });
        }
    }

    /**
     * to evaluate score and check whether answer is correct or wrong
     */
    public void evaluateScore() {
        if (!ListenerUtil.mutListener.listen(1943)) {
            if ((ListenerUtil.mutListener.listen(1939) ? (((ListenerUtil.mutListener.listen(1937) ? (quiz.get(questionIndex).isAnswer() || isPositiveAnswerChecked) : (quiz.get(questionIndex).isAnswer() && isPositiveAnswerChecked))) && ((ListenerUtil.mutListener.listen(1938) ? (!quiz.get(questionIndex).isAnswer() || isNegativeAnswerChecked) : (!quiz.get(questionIndex).isAnswer() && isNegativeAnswerChecked)))) : (((ListenerUtil.mutListener.listen(1937) ? (quiz.get(questionIndex).isAnswer() || isPositiveAnswerChecked) : (quiz.get(questionIndex).isAnswer() && isPositiveAnswerChecked))) || ((ListenerUtil.mutListener.listen(1938) ? (!quiz.get(questionIndex).isAnswer() || isNegativeAnswerChecked) : (!quiz.get(questionIndex).isAnswer() && isNegativeAnswerChecked)))))) {
                if (!ListenerUtil.mutListener.listen(1941)) {
                    customAlert(getResources().getString(R.string.correct), quiz.get(questionIndex).getAnswerMessage());
                }
                if (!ListenerUtil.mutListener.listen(1942)) {
                    score++;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1940)) {
                    customAlert(getResources().getString(R.string.wrong), quiz.get(questionIndex).getAnswerMessage());
                }
            }
        }
    }

    /**
     * to display explanation after each answer, update questionIndex and move to next question
     * @param title the alert title
     * @param Message the alert message
     */
    public void customAlert(final String title, final String Message) {
        if (!ListenerUtil.mutListener.listen(1944)) {
            new AlertDialog.Builder(this).setTitle(title).setMessage(Message).setPositiveButton(R.string.continue_message, (dialog, which) -> {
                questionIndex++;
                if (questionIndex == quiz.size()) {
                    final Intent intent = new Intent(this, QuizResultActivity.class);
                    dialog.dismiss();
                    intent.putExtra("QuizResult", score);
                    startActivity(intent);
                } else {
                    displayQuestion();
                }
            }).create().show();
        }
    }
}

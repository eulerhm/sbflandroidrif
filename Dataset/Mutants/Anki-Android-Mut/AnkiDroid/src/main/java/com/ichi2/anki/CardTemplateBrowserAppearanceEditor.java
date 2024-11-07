/*
 Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>

 This program is free software; you can redistribute it and/or modify it under
 the terms of the GNU General Public License as published by the Free Software
 Foundation; either version 3 of the License, or (at your option) any later
 version.

 This program is distributed in the hope that it will be useful, but WITHOUT ANY
 WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 PARTICULAR PURPOSE. See the GNU General Public License for more details.

 You should have received a copy of the GNU General Public License along with
 this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.anki;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anki.dialogs.DiscardChangesDialog;
import com.ichi2.utils.JSONObject;
import org.jetbrains.annotations.Contract;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Allows specification of the Question and Answer format of a card template in the Card Browser
 * This is known as "Browser Appearance" in Anki
 * We do not allow the user to change fonts as Android only has a handful
 * We do not allow the user to change the font size as this can be done in the Appearance settings.
 */
public class CardTemplateBrowserAppearanceEditor extends AnkiActivity {

    public static final String INTENT_QUESTION_FORMAT = "bqfmt";

    public static final String INTENT_ANSWER_FORMAT = "bafmt";

    /**
     * Specified the card browser should use the default template formatter
     */
    public static final String VALUE_USE_DEFAULT = "";

    private EditText mQuestionEditText;

    private EditText mAnswerEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6237)) {
            if (showedActivityFailedScreen(savedInstanceState)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6238)) {
            super.onCreate(savedInstanceState);
        }
        Bundle bundle = savedInstanceState;
        if (!ListenerUtil.mutListener.listen(6240)) {
            if (bundle == null) {
                if (!ListenerUtil.mutListener.listen(6239)) {
                    bundle = getIntent().getExtras();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6243)) {
            if (bundle == null) {
                if (!ListenerUtil.mutListener.listen(6241)) {
                    UIUtils.showThemedToast(this, getString(R.string.card_template_editor_card_browser_appearance_failed), true);
                }
                if (!ListenerUtil.mutListener.listen(6242)) {
                    finishActivityWithFade(this);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6244)) {
            initializeUiFromBundle(bundle);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        if (!ListenerUtil.mutListener.listen(6245)) {
            getMenuInflater().inflate(R.menu.card_template_browser_appearance_editor, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (!ListenerUtil.mutListener.listen(6252)) {
            if (item.getItemId() == R.id.action_confirm) {
                if (!ListenerUtil.mutListener.listen(6250)) {
                    Timber.i("Save pressed");
                }
                if (!ListenerUtil.mutListener.listen(6251)) {
                    saveAndExit();
                }
                return true;
            } else if (item.getItemId() == R.id.action_restore_default) {
                if (!ListenerUtil.mutListener.listen(6248)) {
                    Timber.i("Restore Default pressed");
                }
                if (!ListenerUtil.mutListener.listen(6249)) {
                    showRestoreDefaultDialog();
                }
                return true;
            } else if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(6246)) {
                    Timber.i("Back Pressed");
                }
                if (!ListenerUtil.mutListener.listen(6247)) {
                    closeWithDiscardWarning();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(6253)) {
            Timber.i("Back Button Pressed");
        }
        if (!ListenerUtil.mutListener.listen(6254)) {
            closeWithDiscardWarning();
        }
    }

    private void closeWithDiscardWarning() {
        if (!ListenerUtil.mutListener.listen(6258)) {
            if (hasChanges()) {
                if (!ListenerUtil.mutListener.listen(6256)) {
                    Timber.i("Changes detected - displaying discard warning dialog");
                }
                if (!ListenerUtil.mutListener.listen(6257)) {
                    showDiscardChangesDialog();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6255)) {
                    discardChangesAndClose();
                }
            }
        }
    }

    private void showDiscardChangesDialog() {
        if (!ListenerUtil.mutListener.listen(6259)) {
            DiscardChangesDialog.getDefault(this).onPositive((dialog, which) -> discardChangesAndClose()).show();
        }
    }

    private void showRestoreDefaultDialog() {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this).positiveText(R.string.dialog_ok).negativeText(R.string.dialog_cancel).content(R.string.card_template_browser_appearance_restore_default_dialog).onPositive((dialog, which) -> restoreDefaultAndClose());
        if (!ListenerUtil.mutListener.listen(6260)) {
            builder.show();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(6261)) {
            outState.putString(INTENT_QUESTION_FORMAT, getQuestionFormat());
        }
        if (!ListenerUtil.mutListener.listen(6262)) {
            outState.putString(INTENT_ANSWER_FORMAT, getAnswerFormat());
        }
        if (!ListenerUtil.mutListener.listen(6263)) {
            super.onSaveInstanceState(outState);
        }
    }

    private void initializeUiFromBundle(@NonNull Bundle bundle) {
        if (!ListenerUtil.mutListener.listen(6264)) {
            setContentView(R.layout.card_browser_appearance);
        }
        if (!ListenerUtil.mutListener.listen(6265)) {
            mQuestionEditText = findViewById(R.id.question_format);
        }
        if (!ListenerUtil.mutListener.listen(6266)) {
            mQuestionEditText.setText(bundle.getString(INTENT_QUESTION_FORMAT));
        }
        if (!ListenerUtil.mutListener.listen(6267)) {
            mAnswerEditText = findViewById(R.id.answer_format);
        }
        if (!ListenerUtil.mutListener.listen(6268)) {
            mAnswerEditText.setText(bundle.getString(INTENT_ANSWER_FORMAT));
        }
        if (!ListenerUtil.mutListener.listen(6269)) {
            enableToolbar();
        }
    }

    private boolean answerHasChanged(Intent intent) {
        return !intent.getStringExtra(INTENT_ANSWER_FORMAT).equals(getAnswerFormat());
    }

    private boolean questionHasChanged(Intent intent) {
        return !intent.getStringExtra(INTENT_QUESTION_FORMAT).equals(getQuestionFormat());
    }

    private String getQuestionFormat() {
        return getTextValue(mQuestionEditText);
    }

    private String getAnswerFormat() {
        return getTextValue(mAnswerEditText);
    }

    private String getTextValue(EditText editText) {
        return editText.getText().toString();
    }

    private void restoreDefaultAndClose() {
        if (!ListenerUtil.mutListener.listen(6270)) {
            Timber.i("Restoring Default and Closing");
        }
        if (!ListenerUtil.mutListener.listen(6271)) {
            mQuestionEditText.setText(VALUE_USE_DEFAULT);
        }
        if (!ListenerUtil.mutListener.listen(6272)) {
            mAnswerEditText.setText(VALUE_USE_DEFAULT);
        }
        if (!ListenerUtil.mutListener.listen(6273)) {
            saveAndExit();
        }
    }

    private void discardChangesAndClose() {
        if (!ListenerUtil.mutListener.listen(6274)) {
            Timber.i("Closing and discarding changes");
        }
        if (!ListenerUtil.mutListener.listen(6275)) {
            setResult(RESULT_CANCELED);
        }
        if (!ListenerUtil.mutListener.listen(6276)) {
            finishActivityWithFade(this);
        }
    }

    private void saveAndExit() {
        if (!ListenerUtil.mutListener.listen(6277)) {
            Timber.i("Save and Exit");
        }
        Intent data = new Intent();
        if (!ListenerUtil.mutListener.listen(6278)) {
            data.putExtra(INTENT_QUESTION_FORMAT, getQuestionFormat());
        }
        if (!ListenerUtil.mutListener.listen(6279)) {
            data.putExtra(INTENT_ANSWER_FORMAT, getAnswerFormat());
        }
        if (!ListenerUtil.mutListener.listen(6280)) {
            setResult(RESULT_OK, data);
        }
        if (!ListenerUtil.mutListener.listen(6281)) {
            finishActivityWithFade(this);
        }
    }

    public boolean hasChanges() {
        try {
            Intent intent = getIntent();
            return (ListenerUtil.mutListener.listen(6283) ? (questionHasChanged(intent) && answerHasChanged(intent)) : (questionHasChanged(intent) || answerHasChanged(intent)));
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(6282)) {
                Timber.w(e, "Failed to detect changes. Assuming true");
            }
            return true;
        }
    }

    @NonNull
    @CheckResult
    public static Intent getIntentFromTemplate(@NonNull Context context, @NonNull JSONObject template) {
        String browserQuestionTemplate = template.getString("bqfmt");
        String browserAnswerTemplate = template.getString("bafmt");
        return CardTemplateBrowserAppearanceEditor.getIntent(context, browserQuestionTemplate, browserAnswerTemplate);
    }

    @NonNull
    @CheckResult
    public static Intent getIntent(@NonNull Context context, @NonNull String questionFormat, @NonNull String answerFormat) {
        Intent intent = new Intent(context, CardTemplateBrowserAppearanceEditor.class);
        if (!ListenerUtil.mutListener.listen(6284)) {
            intent.putExtra(INTENT_QUESTION_FORMAT, questionFormat);
        }
        if (!ListenerUtil.mutListener.listen(6285)) {
            intent.putExtra(INTENT_ANSWER_FORMAT, answerFormat);
        }
        return intent;
    }

    public static class Result {

        @NonNull
        private final String mQuestion;

        @NonNull
        private final String mAnswer;

        private Result(String question, String answer) {
            this.mQuestion = question == null ? VALUE_USE_DEFAULT : question;
            this.mAnswer = answer == null ? VALUE_USE_DEFAULT : answer;
        }

        @Nullable
        @Contract("null -> null")
        @SuppressWarnings("WeakerAccess")
        public static Result fromIntent(@Nullable Intent intent) {
            if (intent == null) {
                return null;
            }
            try {
                String question = intent.getStringExtra(INTENT_QUESTION_FORMAT);
                String answer = intent.getStringExtra(INTENT_ANSWER_FORMAT);
                return new Result(question, answer);
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(6286)) {
                    Timber.w(e, "Could not read result from intent");
                }
                return null;
            }
        }

        @NonNull
        public String getQuestion() {
            return mQuestion;
        }

        @NonNull
        public String getAnswer() {
            return mAnswer;
        }

        @SuppressWarnings("WeakerAccess")
        public void applyTo(@NonNull JSONObject template) {
            if (!ListenerUtil.mutListener.listen(6287)) {
                template.put("bqfmt", getQuestion());
            }
            if (!ListenerUtil.mutListener.listen(6288)) {
                template.put("bafmt", getAnswer());
            }
        }
    }
}

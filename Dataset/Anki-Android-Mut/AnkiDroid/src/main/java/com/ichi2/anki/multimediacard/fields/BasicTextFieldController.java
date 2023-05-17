/**
 * *************************************************************************************
 *  Copyright (c) 2013 Bibek Shrestha <bibekshrestha@gmail.com>                          *
 *  Copyright (c) 2013 Zaur Molotnikov <qutorial@gmail.com>                              *
 *  Copyright (c) 2013 Nicolas Raoul <nicolas.raoul@gmail.com>                           *
 *  Copyright (c) 2013 Flavio Lerda <flerda@gmail.com>                                   *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.anki.multimediacard.fields;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.ichi2.anki.R;
import com.ichi2.anki.multimediacard.activity.LoadPronounciationActivity;
import com.ichi2.anki.multimediacard.activity.PickStringDialogFragment;
import com.ichi2.anki.multimediacard.activity.TranslationActivity;
import com.ichi2.compat.CompatHelper;
import com.ichi2.ui.FixedEditText;
import com.ichi2.ui.FixedTextView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * One of the most powerful controllers - creates UI and works with the field of textual type.
 * <p>
 * Controllers work with the edit field activity and create UI on it to edit a field.
 */
public class BasicTextFieldController extends FieldControllerBase implements IFieldController, DialogInterface.OnClickListener {

    // so on, here are their request codes, to differentiate, when they return.
    private static final int REQUEST_CODE_TRANSLATE_GLOSBE = 101;

    private static final int REQUEST_CODE_PRONOUNCIATION = 102;

    private static final int REQUEST_CODE_TRANSLATE_COLORDICT = 103;

    private EditText mEditText;

    // This is used to copy from another field value to this field
    private ArrayList<String> mPossibleClones;

    @Override
    public void createUI(Context context, LinearLayout layout) {
        if (!ListenerUtil.mutListener.listen(1878)) {
            mEditText = new FixedEditText(mActivity);
        }
        if (!ListenerUtil.mutListener.listen(1879)) {
            mEditText.setMinLines(3);
        }
        if (!ListenerUtil.mutListener.listen(1880)) {
            mEditText.setText(mField.getText());
        }
        if (!ListenerUtil.mutListener.listen(1881)) {
            layout.addView(mEditText, LayoutParams.MATCH_PARENT);
        }
        LinearLayout layoutTools = new LinearLayout(mActivity);
        if (!ListenerUtil.mutListener.listen(1882)) {
            layoutTools.setOrientation(LinearLayout.HORIZONTAL);
        }
        if (!ListenerUtil.mutListener.listen(1883)) {
            layout.addView(layoutTools);
        }
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
        if (!ListenerUtil.mutListener.listen(1884)) {
            createCloneButton(layoutTools, p);
        }
        if (!ListenerUtil.mutListener.listen(1885)) {
            createClearButton(layoutTools, p);
        }
        // search label
        TextView mSearchLabel = new FixedTextView(mActivity);
        if (!ListenerUtil.mutListener.listen(1886)) {
            mSearchLabel.setText(R.string.multimedia_editor_text_field_editing_search_label);
        }
        if (!ListenerUtil.mutListener.listen(1887)) {
            layout.addView(mSearchLabel);
        }
        // search buttons
        LinearLayout layoutTools2 = new LinearLayout(mActivity);
        if (!ListenerUtil.mutListener.listen(1888)) {
            layoutTools2.setOrientation(LinearLayout.HORIZONTAL);
        }
        if (!ListenerUtil.mutListener.listen(1889)) {
            layout.addView(layoutTools2);
        }
        if (!ListenerUtil.mutListener.listen(1890)) {
            createTranslateButton(layoutTools2, p);
        }
        if (!ListenerUtil.mutListener.listen(1891)) {
            createPronounceButton(layoutTools2, p);
        }
    }

    private String gtxt(int id) {
        return mActivity.getText(id).toString();
    }

    private void createClearButton(LinearLayout layoutTools, LayoutParams p) {
        Button clearButton = new Button(mActivity);
        if (!ListenerUtil.mutListener.listen(1892)) {
            clearButton.setText(gtxt(R.string.multimedia_editor_text_field_editing_clear));
        }
        if (!ListenerUtil.mutListener.listen(1893)) {
            layoutTools.addView(clearButton, p);
        }
        if (!ListenerUtil.mutListener.listen(1894)) {
            clearButton.setOnClickListener(v -> mEditText.setText(""));
        }
    }

    /**
     * @param layoutTools to create the button
     * @param p Button to load pronunciation from Beolingus
     */
    private void createPronounceButton(LinearLayout layoutTools, LayoutParams p) {
        Button btnPronounce = new Button(mActivity);
        if (!ListenerUtil.mutListener.listen(1895)) {
            btnPronounce.setText(gtxt(R.string.multimedia_editor_text_field_editing_say));
        }
        if (!ListenerUtil.mutListener.listen(1896)) {
            btnPronounce.setOnClickListener(v -> {
                String source = mEditText.getText().toString();
                if (source.length() == 0) {
                    showToast(gtxt(R.string.multimedia_editor_text_field_editing_no_text));
                    return;
                }
                Intent intent = new Intent(mActivity, LoadPronounciationActivity.class);
                intent.putExtra(LoadPronounciationActivity.EXTRA_SOURCE, source);
                mActivity.startActivityForResultWithoutAnimation(intent, REQUEST_CODE_PRONOUNCIATION);
            });
        }
        if (!ListenerUtil.mutListener.listen(1897)) {
            layoutTools.addView(btnPronounce, p);
        }
    }

    // Here is all the functionality to provide translations
    private void createTranslateButton(LinearLayout layoutTool, LayoutParams ps) {
        Button btnTranslate = new Button(mActivity);
        if (!ListenerUtil.mutListener.listen(1898)) {
            btnTranslate.setText(gtxt(R.string.multimedia_editor_text_field_editing_translate));
        }
        if (!ListenerUtil.mutListener.listen(1899)) {
            btnTranslate.setOnClickListener(v -> {
                String source = mEditText.getText().toString();
                // Checks and warnings
                if (source.length() == 0) {
                    showToast(gtxt(R.string.multimedia_editor_text_field_editing_no_text));
                    return;
                }
                if (source.contains(" ")) {
                    showToast(gtxt(R.string.multimedia_editor_text_field_editing_many_words));
                }
                // Pick from two translation sources
                PickStringDialogFragment fragment = new PickStringDialogFragment();
                final ArrayList<String> translationSources = new ArrayList<>(2);
                translationSources.add("Glosbe.com");
                // Chromebooks do not support dependent apps yet.
                if (!CompatHelper.isChromebook()) {
                    translationSources.add("ColorDict");
                }
                fragment.setChoices(translationSources);
                fragment.setOnclickListener((dialog, which) -> {
                    String translationSource = translationSources.get(which);
                    if ("Glosbe.com".equals(translationSource)) {
                        startTranslationWithGlosbe();
                    } else if ("ColorDict".equals(translationSource)) {
                        startTranslationWithColorDict();
                    }
                });
                fragment.setTitle(gtxt(R.string.multimedia_editor_trans_pick_translation_source));
                fragment.show(mActivity.getSupportFragmentManager(), "pick.translation.source");
            });
        }
        if (!ListenerUtil.mutListener.listen(1900)) {
            layoutTool.addView(btnTranslate, ps);
        }
    }

    /**
     * @param layoutTools This creates a button, which will call a dialog, allowing to pick from another note's fields
     *            one, and use it's value in the current one.
     * @param p layout params
     */
    private void createCloneButton(LinearLayout layoutTools, LayoutParams p) {
        if (!ListenerUtil.mutListener.listen(1934)) {
            // Makes sense only for two and more fields
            if ((ListenerUtil.mutListener.listen(1905) ? (mNote.getNumberOfFields() >= 1) : (ListenerUtil.mutListener.listen(1904) ? (mNote.getNumberOfFields() <= 1) : (ListenerUtil.mutListener.listen(1903) ? (mNote.getNumberOfFields() < 1) : (ListenerUtil.mutListener.listen(1902) ? (mNote.getNumberOfFields() != 1) : (ListenerUtil.mutListener.listen(1901) ? (mNote.getNumberOfFields() == 1) : (mNote.getNumberOfFields() > 1))))))) {
                if (!ListenerUtil.mutListener.listen(1906)) {
                    mPossibleClones = new ArrayList<>(mNote.getNumberOfFields());
                }
                int numTextFields = 0;
                if (!ListenerUtil.mutListener.listen(1924)) {
                    {
                        long _loopCounter25 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(1923) ? (i >= mNote.getNumberOfFields()) : (ListenerUtil.mutListener.listen(1922) ? (i <= mNote.getNumberOfFields()) : (ListenerUtil.mutListener.listen(1921) ? (i > mNote.getNumberOfFields()) : (ListenerUtil.mutListener.listen(1920) ? (i != mNote.getNumberOfFields()) : (ListenerUtil.mutListener.listen(1919) ? (i == mNote.getNumberOfFields()) : (i < mNote.getNumberOfFields())))))); ++i) {
                            ListenerUtil.loopListener.listen("_loopCounter25", ++_loopCounter25);
                            // Sort out non text and empty fields
                            IField curField = mNote.getField(i);
                            if (!ListenerUtil.mutListener.listen(1907)) {
                                if (curField == null) {
                                    continue;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(1908)) {
                                if (curField.getType() != EFieldType.TEXT) {
                                    continue;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(1909)) {
                                if (curField.getText() == null) {
                                    continue;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(1915)) {
                                if ((ListenerUtil.mutListener.listen(1914) ? (curField.getText().length() >= 0) : (ListenerUtil.mutListener.listen(1913) ? (curField.getText().length() <= 0) : (ListenerUtil.mutListener.listen(1912) ? (curField.getText().length() > 0) : (ListenerUtil.mutListener.listen(1911) ? (curField.getText().length() < 0) : (ListenerUtil.mutListener.listen(1910) ? (curField.getText().length() != 0) : (curField.getText().length() == 0))))))) {
                                    continue;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(1916)) {
                                // as well as the same field
                                if (curField.getText().contentEquals(mField.getText())) {
                                    continue;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(1917)) {
                                // collect clone sources
                                mPossibleClones.add(curField.getText());
                            }
                            if (!ListenerUtil.mutListener.listen(1918)) {
                                ++numTextFields;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1930)) {
                    // Nothing to clone from
                    if ((ListenerUtil.mutListener.listen(1929) ? (numTextFields >= 1) : (ListenerUtil.mutListener.listen(1928) ? (numTextFields <= 1) : (ListenerUtil.mutListener.listen(1927) ? (numTextFields > 1) : (ListenerUtil.mutListener.listen(1926) ? (numTextFields != 1) : (ListenerUtil.mutListener.listen(1925) ? (numTextFields == 1) : (numTextFields < 1))))))) {
                        return;
                    }
                }
                Button btnOtherField = new Button(mActivity);
                if (!ListenerUtil.mutListener.listen(1931)) {
                    btnOtherField.setText(gtxt(R.string.multimedia_editor_text_field_editing_clone));
                }
                if (!ListenerUtil.mutListener.listen(1932)) {
                    layoutTools.addView(btnOtherField, p);
                }
                final BasicTextFieldController controller = this;
                if (!ListenerUtil.mutListener.listen(1933)) {
                    btnOtherField.setOnClickListener(v -> {
                        PickStringDialogFragment fragment = new PickStringDialogFragment();
                        fragment.setChoices(mPossibleClones);
                        fragment.setOnclickListener(controller);
                        fragment.setTitle(gtxt(R.string.multimedia_editor_text_field_editing_clone_source));
                        fragment.show(mActivity.getSupportFragmentManager(), "pick.clone");
                    });
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see com.ichi2.anki.IFieldController#onActivityResult(int, int, android.content.Intent) When activity started
     * from here returns, the MultimediaEditFieldActivity passes control here back. And the results from the started before
     * activity are received.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(1962)) {
            if ((ListenerUtil.mutListener.listen(1940) ? ((ListenerUtil.mutListener.listen(1939) ? (requestCode >= REQUEST_CODE_TRANSLATE_GLOSBE) : (ListenerUtil.mutListener.listen(1938) ? (requestCode <= REQUEST_CODE_TRANSLATE_GLOSBE) : (ListenerUtil.mutListener.listen(1937) ? (requestCode > REQUEST_CODE_TRANSLATE_GLOSBE) : (ListenerUtil.mutListener.listen(1936) ? (requestCode < REQUEST_CODE_TRANSLATE_GLOSBE) : (ListenerUtil.mutListener.listen(1935) ? (requestCode != REQUEST_CODE_TRANSLATE_GLOSBE) : (requestCode == REQUEST_CODE_TRANSLATE_GLOSBE)))))) || resultCode == Activity.RESULT_OK) : ((ListenerUtil.mutListener.listen(1939) ? (requestCode >= REQUEST_CODE_TRANSLATE_GLOSBE) : (ListenerUtil.mutListener.listen(1938) ? (requestCode <= REQUEST_CODE_TRANSLATE_GLOSBE) : (ListenerUtil.mutListener.listen(1937) ? (requestCode > REQUEST_CODE_TRANSLATE_GLOSBE) : (ListenerUtil.mutListener.listen(1936) ? (requestCode < REQUEST_CODE_TRANSLATE_GLOSBE) : (ListenerUtil.mutListener.listen(1935) ? (requestCode != REQUEST_CODE_TRANSLATE_GLOSBE) : (requestCode == REQUEST_CODE_TRANSLATE_GLOSBE)))))) && resultCode == Activity.RESULT_OK))) {
                // Translation returned.
                try {
                    String translation = data.getExtras().get(TranslationActivity.EXTRA_TRANSLATION).toString();
                    if (!ListenerUtil.mutListener.listen(1961)) {
                        mEditText.setText(translation);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(1960)) {
                        showToast(gtxt(R.string.multimedia_editor_something_wrong));
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(1946) ? ((ListenerUtil.mutListener.listen(1945) ? (requestCode >= REQUEST_CODE_PRONOUNCIATION) : (ListenerUtil.mutListener.listen(1944) ? (requestCode <= REQUEST_CODE_PRONOUNCIATION) : (ListenerUtil.mutListener.listen(1943) ? (requestCode > REQUEST_CODE_PRONOUNCIATION) : (ListenerUtil.mutListener.listen(1942) ? (requestCode < REQUEST_CODE_PRONOUNCIATION) : (ListenerUtil.mutListener.listen(1941) ? (requestCode != REQUEST_CODE_PRONOUNCIATION) : (requestCode == REQUEST_CODE_PRONOUNCIATION)))))) || resultCode == Activity.RESULT_OK) : ((ListenerUtil.mutListener.listen(1945) ? (requestCode >= REQUEST_CODE_PRONOUNCIATION) : (ListenerUtil.mutListener.listen(1944) ? (requestCode <= REQUEST_CODE_PRONOUNCIATION) : (ListenerUtil.mutListener.listen(1943) ? (requestCode > REQUEST_CODE_PRONOUNCIATION) : (ListenerUtil.mutListener.listen(1942) ? (requestCode < REQUEST_CODE_PRONOUNCIATION) : (ListenerUtil.mutListener.listen(1941) ? (requestCode != REQUEST_CODE_PRONOUNCIATION) : (requestCode == REQUEST_CODE_PRONOUNCIATION)))))) && resultCode == Activity.RESULT_OK))) {
                try {
                    String pronuncPath = data.getExtras().get(LoadPronounciationActivity.EXTRA_PRONUNCIATION_FILE_PATH).toString();
                    File f = new File(pronuncPath);
                    if (!ListenerUtil.mutListener.listen(1956)) {
                        if (!f.exists()) {
                            if (!ListenerUtil.mutListener.listen(1955)) {
                                showToast(gtxt(R.string.multimedia_editor_pron_pronunciation_failed));
                            }
                        }
                    }
                    AudioField af = new AudioRecordingField();
                    if (!ListenerUtil.mutListener.listen(1957)) {
                        af.setAudioPath(pronuncPath);
                    }
                    if (!ListenerUtil.mutListener.listen(1958)) {
                        // This is done to delete the file later.
                        af.setHasTemporaryMedia(true);
                    }
                    if (!ListenerUtil.mutListener.listen(1959)) {
                        mActivity.handleFieldChanged(af);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(1954)) {
                        showToast(gtxt(R.string.multimedia_editor_pron_pronunciation_failed));
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(1952) ? ((ListenerUtil.mutListener.listen(1951) ? (requestCode >= REQUEST_CODE_TRANSLATE_COLORDICT) : (ListenerUtil.mutListener.listen(1950) ? (requestCode <= REQUEST_CODE_TRANSLATE_COLORDICT) : (ListenerUtil.mutListener.listen(1949) ? (requestCode > REQUEST_CODE_TRANSLATE_COLORDICT) : (ListenerUtil.mutListener.listen(1948) ? (requestCode < REQUEST_CODE_TRANSLATE_COLORDICT) : (ListenerUtil.mutListener.listen(1947) ? (requestCode != REQUEST_CODE_TRANSLATE_COLORDICT) : (requestCode == REQUEST_CODE_TRANSLATE_COLORDICT)))))) || resultCode == Activity.RESULT_OK) : ((ListenerUtil.mutListener.listen(1951) ? (requestCode >= REQUEST_CODE_TRANSLATE_COLORDICT) : (ListenerUtil.mutListener.listen(1950) ? (requestCode <= REQUEST_CODE_TRANSLATE_COLORDICT) : (ListenerUtil.mutListener.listen(1949) ? (requestCode > REQUEST_CODE_TRANSLATE_COLORDICT) : (ListenerUtil.mutListener.listen(1948) ? (requestCode < REQUEST_CODE_TRANSLATE_COLORDICT) : (ListenerUtil.mutListener.listen(1947) ? (requestCode != REQUEST_CODE_TRANSLATE_COLORDICT) : (requestCode == REQUEST_CODE_TRANSLATE_COLORDICT)))))) && resultCode == Activity.RESULT_OK))) {
                // String subject = data.getStringExtra(Intent.EXTRA_SUBJECT);
                String text = data.getStringExtra(Intent.EXTRA_TEXT);
                if (!ListenerUtil.mutListener.listen(1953)) {
                    mEditText.setText(text);
                }
            }
        }
    }

    @Override
    public void onFocusLost() {
    }

    /**
     * @param context context with the PackageManager
     * @param intent intent for state data
     * @return Needed to check, if the Color Dict is installed
     */
    private static boolean isIntentAvailable(Context context, Intent intent) {
        final PackageManager packageManager = context.getPackageManager();
        List<?> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return (ListenerUtil.mutListener.listen(1967) ? (list.size() >= 0) : (ListenerUtil.mutListener.listen(1966) ? (list.size() <= 0) : (ListenerUtil.mutListener.listen(1965) ? (list.size() < 0) : (ListenerUtil.mutListener.listen(1964) ? (list.size() != 0) : (ListenerUtil.mutListener.listen(1963) ? (list.size() == 0) : (list.size() > 0))))));
    }

    // When Done button is clicked
    @Override
    public void onDone() {
        if (!ListenerUtil.mutListener.listen(1968)) {
            mField.setText(mEditText.getText().toString());
        }
    }

    // This is when the dialog for clone ends
    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (!ListenerUtil.mutListener.listen(1969)) {
            mEditText.setText(mPossibleClones.get(which));
        }
    }

    /**
     * @param text A short cut to show a toast
     */
    private void showToast(CharSequence text) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(mActivity, text, duration);
        if (!ListenerUtil.mutListener.listen(1970)) {
            toast.show();
        }
    }

    // Only now not all APIs are used, may be later, they will be.
    @SuppressWarnings("unused")
    private void startTranslationWithColorDict() {
        final String PICK_RESULT_ACTION = "colordict.intent.action.PICK_RESULT";
        final String SEARCH_ACTION = "colordict.intent.action.SEARCH";
        final String EXTRA_QUERY = "EXTRA_QUERY";
        final String EXTRA_FULLSCREEN = "EXTRA_FULLSCREEN";
        final String EXTRA_HEIGHT = "EXTRA_HEIGHT";
        final String EXTRA_WIDTH = "EXTRA_WIDTH";
        final String EXTRA_GRAVITY = "EXTRA_GRAVITY";
        final String EXTRA_MARGIN_LEFT = "EXTRA_MARGIN_LEFT";
        final String EXTRA_MARGIN_TOP = "EXTRA_MARGIN_TOP";
        final String EXTRA_MARGIN_BOTTOM = "EXTRA_MARGIN_BOTTOM";
        final String EXTRA_MARGIN_RIGHT = "EXTRA_MARGIN_RIGHT";
        Intent intent = new Intent(PICK_RESULT_ACTION);
        if (!ListenerUtil.mutListener.listen(1971)) {
            // Search
            intent.putExtra(EXTRA_QUERY, mEditText.getText().toString());
        }
        if (!ListenerUtil.mutListener.listen(1972)) {
            // 
            intent.putExtra(EXTRA_FULLSCREEN, false);
        }
        if (!ListenerUtil.mutListener.listen(1973)) {
            // fill_parent"
            intent.putExtra(EXTRA_GRAVITY, Gravity.BOTTOM);
        }
        if (!ListenerUtil.mutListener.listen(1975)) {
            // intent.putExtra(EXTRA_MARGIN_LEFT, 100);
            if (!isIntentAvailable(mActivity, intent)) {
                if (!ListenerUtil.mutListener.listen(1974)) {
                    showToast(gtxt(R.string.multimedia_editor_trans_install_color_dict));
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1976)) {
            mActivity.startActivityForResultWithoutAnimation(intent, REQUEST_CODE_TRANSLATE_COLORDICT);
        }
    }

    private void startTranslationWithGlosbe() {
        String source = mEditText.getText().toString();
        Intent intent = new Intent(mActivity, TranslationActivity.class);
        if (!ListenerUtil.mutListener.listen(1977)) {
            intent.putExtra(TranslationActivity.EXTRA_SOURCE, source);
        }
        if (!ListenerUtil.mutListener.listen(1978)) {
            mActivity.startActivityForResultWithoutAnimation(intent, REQUEST_CODE_TRANSLATE_GLOSBE);
        }
    }

    @Override
    public void onDestroy() {
    }
}

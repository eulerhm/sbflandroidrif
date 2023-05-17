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
package com.ichi2.anki.multimediacard.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.R;
import com.ichi2.anki.multimediacard.glosbe.json.Meaning;
import com.ichi2.anki.multimediacard.glosbe.json.Phrase;
import com.ichi2.anki.multimediacard.glosbe.json.Response;
import com.ichi2.anki.multimediacard.glosbe.json.Tuc;
import com.ichi2.anki.multimediacard.language.LanguagesListerGlosbe;
import com.ichi2.anki.runtimetools.TaskOperations;
import com.ichi2.anki.web.HttpFetcher;
import com.ichi2.async.Connection;
import com.ichi2.libanki.Utils;
import com.ichi2.ui.FixedTextView;
import com.ichi2.utils.AdaptionUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Activity used now with Glosbe.com to enable translation of words.
 * FIXME why isn't this extending from our base classes?
 */
public class TranslationActivity extends FragmentActivity implements DialogInterface.OnClickListener, OnCancelListener {

    private static final String BUNDLE_KEY_SHUT_OFF = "key.multimedia.shut.off";

    // Something to translate
    public static final String EXTRA_SOURCE = "translation.activity.extra.source";

    // Translated result
    public static final String EXTRA_TRANSLATION = "translation.activity.extra.translation";

    private String mSource;

    private String mTranslation;

    private LanguagesListerGlosbe mLanguageLister;

    private Spinner mSpinnerFrom;

    private Spinner mSpinnerTo;

    // tracked in github as #5020
    @SuppressWarnings("deprecation")
    private android.app.ProgressDialog progressDialog = null;

    private String mWebServiceAddress;

    private ArrayList<String> mPossibleTranslations;

    private String mLangCodeTo;

    private BackgroundPost mTranslationLoadPost = null;

    private void finishCancel() {
        Intent resultData = new Intent();
        if (!ListenerUtil.mutListener.listen(1384)) {
            setResult(RESULT_CANCELED, resultData);
        }
        if (!ListenerUtil.mutListener.listen(1385)) {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1386)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1388)) {
            if (AdaptionUtil.isUserATestClient()) {
                if (!ListenerUtil.mutListener.listen(1387)) {
                    finishCancel();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1391)) {
            if (savedInstanceState != null) {
                boolean b = savedInstanceState.getBoolean(BUNDLE_KEY_SHUT_OFF, false);
                if (!ListenerUtil.mutListener.listen(1390)) {
                    if (b) {
                        if (!ListenerUtil.mutListener.listen(1389)) {
                            finishCancel();
                        }
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1392)) {
            setContentView(R.layout.activity_translation);
        }
        try {
            if (!ListenerUtil.mutListener.listen(1394)) {
                mSource = getIntent().getExtras().getString(EXTRA_SOURCE);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(1393)) {
                mSource = "";
            }
        }
        if (!ListenerUtil.mutListener.listen(1395)) {
            // If translation fails this is a default - source will be returned.
            mTranslation = mSource;
        }
        LinearLayout linearLayout = findViewById(R.id.MainLayoutInTranslationActivity);
        TextView tv = new FixedTextView(this);
        if (!ListenerUtil.mutListener.listen(1396)) {
            tv.setText(getText(R.string.multimedia_editor_trans_poweredglosbe));
        }
        if (!ListenerUtil.mutListener.listen(1397)) {
            linearLayout.addView(tv);
        }
        TextView tvFrom = new FixedTextView(this);
        if (!ListenerUtil.mutListener.listen(1398)) {
            tvFrom.setText(getText(R.string.multimedia_editor_trans_from));
        }
        if (!ListenerUtil.mutListener.listen(1399)) {
            linearLayout.addView(tvFrom);
        }
        if (!ListenerUtil.mutListener.listen(1400)) {
            mLanguageLister = new LanguagesListerGlosbe();
        }
        if (!ListenerUtil.mutListener.listen(1401)) {
            mSpinnerFrom = new Spinner(this);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mLanguageLister.getLanguages());
        if (!ListenerUtil.mutListener.listen(1402)) {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        if (!ListenerUtil.mutListener.listen(1403)) {
            mSpinnerFrom.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(1404)) {
            linearLayout.addView(mSpinnerFrom);
        }
        TextView tvTo = new FixedTextView(this);
        if (!ListenerUtil.mutListener.listen(1405)) {
            tvTo.setText(getText(R.string.multimedia_editor_trans_to));
        }
        if (!ListenerUtil.mutListener.listen(1406)) {
            linearLayout.addView(tvTo);
        }
        if (!ListenerUtil.mutListener.listen(1407)) {
            mSpinnerTo = new Spinner(this);
        }
        ArrayAdapter<String> adapterTo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mLanguageLister.getLanguages());
        if (!ListenerUtil.mutListener.listen(1408)) {
            adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        if (!ListenerUtil.mutListener.listen(1409)) {
            mSpinnerTo.setAdapter(adapterTo);
        }
        if (!ListenerUtil.mutListener.listen(1410)) {
            linearLayout.addView(mSpinnerTo);
        }
        final SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(getBaseContext());
        // Try to set spinner value to last selected position
        String fromLang = preferences.getString("translatorLastLanguageFrom", "");
        String toLang = preferences.getString("translatorLastLanguageTo", "");
        if (!ListenerUtil.mutListener.listen(1411)) {
            mSpinnerFrom.setSelection(getSpinnerIndex(mSpinnerFrom, fromLang));
        }
        if (!ListenerUtil.mutListener.listen(1412)) {
            mSpinnerTo.setSelection(getSpinnerIndex(mSpinnerTo, toLang));
        }
        // Setup button
        Button btnDone = new Button(this);
        if (!ListenerUtil.mutListener.listen(1413)) {
            btnDone.setText(getText(R.string.multimedia_editor_trans_translate));
        }
        if (!ListenerUtil.mutListener.listen(1414)) {
            btnDone.setOnClickListener(v -> {
                // Remember currently selected language
                String fromLang1 = mSpinnerFrom.getSelectedItem().toString();
                String toLang1 = mSpinnerTo.getSelectedItem().toString();
                preferences.edit().putString("translatorLastLanguageFrom", fromLang1).apply();
                preferences.edit().putString("translatorLastLanguageTo", toLang1).apply();
                // Get translation
                translate();
            });
        }
        if (!ListenerUtil.mutListener.listen(1415)) {
            linearLayout.addView(btnDone);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(1416)) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.activity_translation, menu);
        }
        return true;
    }

    private class BackgroundPost extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            return HttpFetcher.fetchThroughHttp(mWebServiceAddress);
        }

        @Override
        protected void onPostExecute(String result) {
            if (!ListenerUtil.mutListener.listen(1417)) {
                progressDialog.dismiss();
            }
            if (!ListenerUtil.mutListener.listen(1418)) {
                mTranslation = result;
            }
            if (!ListenerUtil.mutListener.listen(1419)) {
                showPickTranslationDialog();
            }
        }
    }

    // ProgressDialog change tracked in github as #5020
    @SuppressWarnings("deprecation")
    protected void translate() {
        if (!ListenerUtil.mutListener.listen(1421)) {
            if (!Connection.isOnline()) {
                if (!ListenerUtil.mutListener.listen(1420)) {
                    showToast(gtxt(R.string.network_no_connection));
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1422)) {
            progressDialog = android.app.ProgressDialog.show(this, getText(R.string.multimedia_editor_progress_wait_title), getText(R.string.multimedia_editor_trans_translating_online), true, false);
        }
        if (!ListenerUtil.mutListener.listen(1423)) {
            progressDialog.setCancelable(true);
        }
        if (!ListenerUtil.mutListener.listen(1424)) {
            progressDialog.setOnCancelListener(this);
        }
        if (!ListenerUtil.mutListener.listen(1425)) {
            mWebServiceAddress = computeAddress();
        }
        try {
            if (!ListenerUtil.mutListener.listen(1428)) {
                mTranslationLoadPost = new BackgroundPost();
            }
            if (!ListenerUtil.mutListener.listen(1429)) {
                mTranslationLoadPost.execute();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(1426)) {
                progressDialog.dismiss();
            }
            if (!ListenerUtil.mutListener.listen(1427)) {
                showToast(getText(R.string.multimedia_editor_something_wrong));
            }
        }
    }

    private String computeAddress() {
        String address = "https://glosbe.com/gapi/translate?from=FROMLANG&dest=TOLANG&format=json&phrase=SOURCE&pretty=true";
        String strFrom = mSpinnerFrom.getSelectedItem().toString();
        // Conversion to iso, lister created before.
        String langCodeFrom = mLanguageLister.getCodeFor(strFrom);
        String strTo = mSpinnerTo.getSelectedItem().toString();
        if (!ListenerUtil.mutListener.listen(1430)) {
            mLangCodeTo = mLanguageLister.getCodeFor(strTo);
        }
        String query;
        try {
            query = URLEncoder.encode(mSource, "utf-8");
        } catch (UnsupportedEncodingException e) {
            query = mSource.replace(" ", "%20");
        }
        if (!ListenerUtil.mutListener.listen(1431)) {
            address = address.replaceAll("FROMLANG", langCodeFrom).replaceAll("TOLANG", mLangCodeTo).replaceAll("SOURCE", query);
        }
        return address;
    }

    private String gtxt(int id) {
        return getText(id).toString();
    }

    private void showToastLong(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, text, duration);
        if (!ListenerUtil.mutListener.listen(1432)) {
            toast.show();
        }
    }

    private void showPickTranslationDialog() {
        if (!ListenerUtil.mutListener.listen(1434)) {
            if (mTranslation.startsWith("FAILED")) {
                if (!ListenerUtil.mutListener.listen(1433)) {
                    returnFailure(getText(R.string.multimedia_editor_trans_getting_failure).toString());
                }
                return;
            }
        }
        Gson gson = new Gson();
        Response resp = gson.fromJson(mTranslation, Response.class);
        if (!ListenerUtil.mutListener.listen(1436)) {
            if (resp == null) {
                if (!ListenerUtil.mutListener.listen(1435)) {
                    returnFailure(getText(R.string.multimedia_editor_trans_getting_failure).toString());
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1440)) {
            if (!resp.getResult().contentEquals("ok")) {
                if (!ListenerUtil.mutListener.listen(1438)) {
                    if (!mSource.toLowerCase(Locale.getDefault()).contentEquals(mSource)) {
                        if (!ListenerUtil.mutListener.listen(1437)) {
                            showToastLong(gtxt(R.string.multimedia_editor_word_search_try_lower_case));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1439)) {
                    returnFailure(getText(R.string.multimedia_editor_trans_getting_failure).toString());
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1441)) {
            mPossibleTranslations = parseJson(resp, mLangCodeTo);
        }
        if (!ListenerUtil.mutListener.listen(1450)) {
            if ((ListenerUtil.mutListener.listen(1446) ? (mPossibleTranslations.size() >= 0) : (ListenerUtil.mutListener.listen(1445) ? (mPossibleTranslations.size() <= 0) : (ListenerUtil.mutListener.listen(1444) ? (mPossibleTranslations.size() > 0) : (ListenerUtil.mutListener.listen(1443) ? (mPossibleTranslations.size() < 0) : (ListenerUtil.mutListener.listen(1442) ? (mPossibleTranslations.size() != 0) : (mPossibleTranslations.size() == 0))))))) {
                if (!ListenerUtil.mutListener.listen(1448)) {
                    if (!mSource.toLowerCase(Locale.getDefault()).contentEquals(mSource)) {
                        if (!ListenerUtil.mutListener.listen(1447)) {
                            showToastLong(gtxt(R.string.multimedia_editor_word_search_try_lower_case));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1449)) {
                    returnFailure(getText(R.string.multimedia_editor_error_word_not_found).toString());
                }
                return;
            }
        }
        PickStringDialogFragment fragment = new PickStringDialogFragment();
        if (!ListenerUtil.mutListener.listen(1451)) {
            fragment.setChoices(mPossibleTranslations);
        }
        if (!ListenerUtil.mutListener.listen(1452)) {
            fragment.setOnclickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(1453)) {
            fragment.setTitle(getText(R.string.multimedia_editor_trans_pick_translation).toString());
        }
        if (!ListenerUtil.mutListener.listen(1454)) {
            fragment.show(this.getSupportFragmentManager(), "pick.translation");
        }
    }

    private static ArrayList<String> parseJson(Response resp, String languageCodeTo) {
        List<Tuc> tucs = resp.getTuc();
        if (!ListenerUtil.mutListener.listen(1455)) {
            if (tucs == null) {
                return new ArrayList<>(0);
            }
        }
        ArrayList<String> res = new ArrayList<>(tucs.size());
        String desiredLang = LanguagesListerGlosbe.requestToResponseLangCode(languageCodeTo);
        if (!ListenerUtil.mutListener.listen(1467)) {
            {
                long _loopCounter22 = 0;
                for (Tuc tuc : tucs) {
                    ListenerUtil.loopListener.listen("_loopCounter22", ++_loopCounter22);
                    if (!ListenerUtil.mutListener.listen(1456)) {
                        if (tuc == null) {
                            continue;
                        }
                    }
                    List<Meaning> meanings = tuc.getMeanings();
                    if (!ListenerUtil.mutListener.listen(1462)) {
                        if (meanings != null) {
                            if (!ListenerUtil.mutListener.listen(1461)) {
                                {
                                    long _loopCounter21 = 0;
                                    for (Meaning meaning : meanings) {
                                        ListenerUtil.loopListener.listen("_loopCounter21", ++_loopCounter21);
                                        if (!ListenerUtil.mutListener.listen(1457)) {
                                            if (meaning == null) {
                                                continue;
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(1458)) {
                                            if (meaning.getLanguage() == null) {
                                                continue;
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(1460)) {
                                            if (meaning.getLanguage().contentEquals(desiredLang)) {
                                                String unescappedString = Utils.unescape(meaning.getText());
                                                if (!ListenerUtil.mutListener.listen(1459)) {
                                                    res.add(unescappedString);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Phrase phrase = tuc.getPhrase();
                    if (!ListenerUtil.mutListener.listen(1466)) {
                        if (phrase != null) {
                            if (!ListenerUtil.mutListener.listen(1463)) {
                                if (phrase.getLanguage() == null) {
                                    continue;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(1465)) {
                                if (phrase.getLanguage().contentEquals(desiredLang)) {
                                    String unescappedString = Utils.unescape(phrase.getText());
                                    if (!ListenerUtil.mutListener.listen(1464)) {
                                        res.add(unescappedString);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    private void returnTheTranslation() {
        Intent resultData = new Intent();
        if (!ListenerUtil.mutListener.listen(1468)) {
            resultData.putExtra(EXTRA_TRANSLATION, mTranslation);
        }
        if (!ListenerUtil.mutListener.listen(1469)) {
            setResult(RESULT_OK, resultData);
        }
        if (!ListenerUtil.mutListener.listen(1470)) {
            finish();
        }
    }

    private void returnFailure(String explanation) {
        if (!ListenerUtil.mutListener.listen(1471)) {
            showToast(explanation);
        }
        if (!ListenerUtil.mutListener.listen(1472)) {
            setResult(RESULT_CANCELED);
        }
        if (!ListenerUtil.mutListener.listen(1473)) {
            dismissCarefullyProgressDialog();
        }
        if (!ListenerUtil.mutListener.listen(1474)) {
            finish();
        }
    }

    private void showToast(CharSequence text) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        if (!ListenerUtil.mutListener.listen(1475)) {
            toast.show();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(1476)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(1477)) {
            outState.putBoolean(BUNDLE_KEY_SHUT_OFF, true);
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (!ListenerUtil.mutListener.listen(1478)) {
            mTranslation = mPossibleTranslations.get(which);
        }
        if (!ListenerUtil.mutListener.listen(1479)) {
            returnTheTranslation();
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        if (!ListenerUtil.mutListener.listen(1480)) {
            stopWorking();
        }
    }

    private void stopWorking() {
        if (!ListenerUtil.mutListener.listen(1481)) {
            TaskOperations.stopTaskGracefully(mTranslationLoadPost);
        }
        if (!ListenerUtil.mutListener.listen(1482)) {
            dismissCarefullyProgressDialog();
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(1483)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(1484)) {
            stopWorking();
        }
    }

    private void dismissCarefullyProgressDialog() {
        try {
            if (!ListenerUtil.mutListener.listen(1487)) {
                if ((ListenerUtil.mutListener.listen(1485) ? ((progressDialog != null) || progressDialog.isShowing()) : ((progressDialog != null) && progressDialog.isShowing()))) {
                    if (!ListenerUtil.mutListener.listen(1486)) {
                        progressDialog.dismiss();
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private int getSpinnerIndex(Spinner spinner, String myString) {
        int index = 0;
        if (!ListenerUtil.mutListener.listen(1495)) {
            {
                long _loopCounter23 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(1494) ? (i >= spinner.getCount()) : (ListenerUtil.mutListener.listen(1493) ? (i <= spinner.getCount()) : (ListenerUtil.mutListener.listen(1492) ? (i > spinner.getCount()) : (ListenerUtil.mutListener.listen(1491) ? (i != spinner.getCount()) : (ListenerUtil.mutListener.listen(1490) ? (i == spinner.getCount()) : (i < spinner.getCount())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter23", ++_loopCounter23);
                    if (!ListenerUtil.mutListener.listen(1489)) {
                        if (spinner.getItemAtPosition(i).equals(myString)) {
                            if (!ListenerUtil.mutListener.listen(1488)) {
                                index = i;
                            }
                        }
                    }
                }
            }
        }
        return index;
    }
}

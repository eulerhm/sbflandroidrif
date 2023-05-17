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

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import com.ichi2.anki.R;
import com.ichi2.anki.multimediacard.beolingus.parsing.BeolingusParser;
import com.ichi2.anki.multimediacard.language.LanguageListerBeolingus;
import com.ichi2.anki.runtimetools.TaskOperations;
import com.ichi2.anki.web.HttpFetcher;
import com.ichi2.async.Connection;
import com.ichi2.utils.AdaptionUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Activity to load pronunciation files from Beolingus.
 * <p>
 * User picks a source language and the source is passed as extra.
 * <p>
 * When activity finished, it passes the filepath as another extra to the caller.
 * FIXME why isn't this extending AnkiActivity?
 */
public class LoadPronounciationActivity extends Activity implements OnCancelListener {

    private static final String BUNDLE_KEY_SHUT_OFF = "key.multimedia.shut.off";

    // Must be passed in
    public static final String EXTRA_SOURCE = "com.ichi2.anki.LoadPronounciationActivity.extra.source";

    // Passed out as a result
    public static final String EXTRA_PRONUNCIATION_FILE_PATH = "com.ichi2.anki.LoadPronounciationActivity.extra.pronun.file.path";

    String mSource;

    private String mTranslationAddress;

    // tracked in github as #5020
    @SuppressWarnings("deprecation")
    private android.app.ProgressDialog progressDialog = null;

    private String mPronunciationAddress;

    private String mMp3Address;

    private LoadPronounciationActivity mActivity;

    private LanguageListerBeolingus mLanguageLister;

    private Spinner mSpinnerFrom;

    private BackgroundPost mPostTranslation = null;

    private BackgroundPost mPostPronunciation = null;

    private DownloadFileTask mDownloadMp3Task = null;

    private boolean mStopped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1094)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1096)) {
            if (AdaptionUtil.isUserATestClient()) {
                if (!ListenerUtil.mutListener.listen(1095)) {
                    finishCancel();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1099)) {
            if (savedInstanceState != null) {
                boolean b = savedInstanceState.getBoolean(BUNDLE_KEY_SHUT_OFF, false);
                if (!ListenerUtil.mutListener.listen(1098)) {
                    if (b) {
                        if (!ListenerUtil.mutListener.listen(1097)) {
                            finishCancel();
                        }
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1100)) {
            setContentView(R.layout.activity_load_pronounciation);
        }
        if (!ListenerUtil.mutListener.listen(1101)) {
            mSource = getIntent().getExtras().getString(EXTRA_SOURCE);
        }
        LinearLayout linearLayout = findViewById(R.id.layoutInLoadPronActivity);
        if (!ListenerUtil.mutListener.listen(1102)) {
            mLanguageLister = new LanguageListerBeolingus();
        }
        if (!ListenerUtil.mutListener.listen(1103)) {
            mSpinnerFrom = new Spinner(this);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mLanguageLister.getLanguages());
        if (!ListenerUtil.mutListener.listen(1104)) {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        if (!ListenerUtil.mutListener.listen(1105)) {
            mSpinnerFrom.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(1106)) {
            linearLayout.addView(mSpinnerFrom);
        }
        Button buttonLoadPronunciation = new Button(this);
        if (!ListenerUtil.mutListener.listen(1107)) {
            buttonLoadPronunciation.setText(gtxt(R.string.multimedia_editor_pron_load));
        }
        if (!ListenerUtil.mutListener.listen(1108)) {
            linearLayout.addView(buttonLoadPronunciation);
        }
        if (!ListenerUtil.mutListener.listen(1109)) {
            buttonLoadPronunciation.setOnClickListener(this::onLoadPronunciation);
        }
        Button mSaveButton = new Button(this);
        if (!ListenerUtil.mutListener.listen(1110)) {
            mSaveButton.setText("Save");
        }
        if (!ListenerUtil.mutListener.listen(1111)) {
            mSaveButton.setOnClickListener(v -> {
            });
        }
        if (!ListenerUtil.mutListener.listen(1112)) {
            mActivity = this;
        }
        if (!ListenerUtil.mutListener.listen(1113)) {
            mStopped = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(1114)) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.activity_load_pronounciation, menu);
        }
        return true;
    }

    /**
     * @param v Start of the story.
     */
    protected void onLoadPronunciation(View v) {
        if (!ListenerUtil.mutListener.listen(1116)) {
            if (!Connection.isOnline()) {
                if (!ListenerUtil.mutListener.listen(1115)) {
                    showToast(gtxt(R.string.network_no_connection));
                }
                return;
            }
        }
        String message = gtxt(R.string.multimedia_editor_searching_word);
        if (!ListenerUtil.mutListener.listen(1117)) {
            showProgressDialog(message);
        }
        if (!ListenerUtil.mutListener.listen(1118)) {
            mTranslationAddress = computeAddressOfTranslationPage();
        }
        try {
            if (!ListenerUtil.mutListener.listen(1121)) {
                mPostTranslation = new BackgroundPost();
            }
            if (!ListenerUtil.mutListener.listen(1122)) {
                mPostTranslation.setAddress(mTranslationAddress);
            }
            if (!ListenerUtil.mutListener.listen(1123)) {
                // post.setStopper(PRONUNC_STOPPER);
                mPostTranslation.execute();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(1119)) {
                progressDialog.dismiss();
            }
            if (!ListenerUtil.mutListener.listen(1120)) {
                showToast(gtxt(R.string.multimedia_editor_something_wrong));
            }
        }
    }

    // ProgressDialog change tracked in github as #5020
    @SuppressWarnings("deprecation")
    private void showProgressDialog(String message) {
        if (!ListenerUtil.mutListener.listen(1124)) {
            dismissCarefullyProgressDialog();
        }
        if (!ListenerUtil.mutListener.listen(1125)) {
            progressDialog = android.app.ProgressDialog.show(this, gtxt(R.string.multimedia_editor_progress_wait_title), message, true, false);
        }
        if (!ListenerUtil.mutListener.listen(1126)) {
            progressDialog.setCancelable(true);
        }
        if (!ListenerUtil.mutListener.listen(1127)) {
            progressDialog.setOnCancelListener(this);
        }
    }

    /**
     * @author zaur This class is used two times. First time from Beolingus it requests a page with the word
     *         translation. Second time it loads a page with the link to mp3 pronunciation file.
     */
    private class BackgroundPost extends AsyncTask<Void, Void, String> {

        private String mAddress;

        @Override
        protected String doInBackground(Void... params) {
            // Should be just this
            return HttpFetcher.fetchThroughHttp(getAddress(), "ISO-8859-1");
        }

        /**
         * @param address Used to set the download address
         */
        public void setAddress(String address) {
            if (!ListenerUtil.mutListener.listen(1128)) {
                mAddress = address;
            }
        }

        /**
         * @return Used to know, which of the posts finished, to differentiate.
         */
        public String getAddress() {
            return mAddress;
        }

        @Override
        protected void onPostExecute(String result) {
            if (!ListenerUtil.mutListener.listen(1129)) {
                // post has finished.
                processPostFinished(this, result);
            }
        }
    }

    /**
     * @author zaur This is to load finally the MP3 file with pronunciation.
     */
    private class DownloadFileTask extends AsyncTask<Void, Void, String> {

        private String mAddress;

        @Override
        protected String doInBackground(Void... params) {
            return HttpFetcher.downloadFileToSdCard(mAddress, mActivity, "pronunc");
        }

        public void setAddress(String address) {
            if (!ListenerUtil.mutListener.listen(1130)) {
                mAddress = address;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (!ListenerUtil.mutListener.listen(1131)) {
                receiveMp3File(result);
            }
        }
    }

    protected void processPostFinished(BackgroundPost post, String result) {
        if (!ListenerUtil.mutListener.listen(1132)) {
            if (mStopped) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1146)) {
            // And we have to start fetching the page with pronunciation
            if (post.getAddress().contentEquals(mTranslationAddress)) {
                if (!ListenerUtil.mutListener.listen(1134)) {
                    if (result.startsWith("FAILED")) {
                        if (!ListenerUtil.mutListener.listen(1133)) {
                            failNoPronunciation();
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(1135)) {
                    mPronunciationAddress = BeolingusParser.getPronunciationAddressFromTranslation(result, mSource);
                }
                if (!ListenerUtil.mutListener.listen(1139)) {
                    if (mPronunciationAddress.contentEquals("no")) {
                        if (!ListenerUtil.mutListener.listen(1136)) {
                            failNoPronunciation();
                        }
                        if (!ListenerUtil.mutListener.listen(1138)) {
                            if (!mSource.toLowerCase(Locale.getDefault()).contentEquals(mSource)) {
                                if (!ListenerUtil.mutListener.listen(1137)) {
                                    showToastLong(gtxt(R.string.multimedia_editor_word_search_try_lower_case));
                                }
                            }
                        }
                        return;
                    }
                }
                try {
                    if (!ListenerUtil.mutListener.listen(1142)) {
                        showProgressDialog(gtxt(R.string.multimedia_editor_pron_looking_up));
                    }
                    if (!ListenerUtil.mutListener.listen(1143)) {
                        mPostPronunciation = new BackgroundPost();
                    }
                    if (!ListenerUtil.mutListener.listen(1144)) {
                        mPostPronunciation.setAddress(mPronunciationAddress);
                    }
                    if (!ListenerUtil.mutListener.listen(1145)) {
                        mPostPronunciation.execute();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(1140)) {
                        progressDialog.dismiss();
                    }
                    if (!ListenerUtil.mutListener.listen(1141)) {
                        showToast(gtxt(R.string.multimedia_editor_something_wrong));
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1156)) {
            // We chekc if mp3 file could be downloaded and download it.
            if (post.getAddress().contentEquals(mPronunciationAddress)) {
                if (!ListenerUtil.mutListener.listen(1147)) {
                    mMp3Address = BeolingusParser.getMp3AddressFromPronounciation(result);
                }
                if (!ListenerUtil.mutListener.listen(1149)) {
                    if (mMp3Address.contentEquals("no")) {
                        if (!ListenerUtil.mutListener.listen(1148)) {
                            failNoPronunciation();
                        }
                        return;
                    }
                }
                // Download MP3 file
                try {
                    if (!ListenerUtil.mutListener.listen(1152)) {
                        showProgressDialog(gtxt(R.string.multimedia_editor_general_downloading));
                    }
                    if (!ListenerUtil.mutListener.listen(1153)) {
                        mDownloadMp3Task = new DownloadFileTask();
                    }
                    if (!ListenerUtil.mutListener.listen(1154)) {
                        mDownloadMp3Task.setAddress(mMp3Address);
                    }
                    if (!ListenerUtil.mutListener.listen(1155)) {
                        mDownloadMp3Task.execute();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(1150)) {
                        progressDialog.dismiss();
                    }
                    if (!ListenerUtil.mutListener.listen(1151)) {
                        showToast(gtxt(R.string.multimedia_editor_something_wrong));
                    }
                }
            }
        }
    }

    // This is called when MP3 Download is finished.
    public void receiveMp3File(String result) {
        if (!ListenerUtil.mutListener.listen(1157)) {
            if (mStopped) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1159)) {
            if (result == null) {
                if (!ListenerUtil.mutListener.listen(1158)) {
                    failNoPronunciation();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1161)) {
            if (result.startsWith("FAIL")) {
                if (!ListenerUtil.mutListener.listen(1160)) {
                    failNoPronunciation();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1162)) {
            progressDialog.dismiss();
        }
        if (!ListenerUtil.mutListener.listen(1163)) {
            showToast(gtxt(R.string.multimedia_editor_general_done));
        }
        Intent resultData = new Intent();
        if (!ListenerUtil.mutListener.listen(1164)) {
            resultData.putExtra(EXTRA_PRONUNCIATION_FILE_PATH, result);
        }
        if (!ListenerUtil.mutListener.listen(1165)) {
            setResult(RESULT_OK, resultData);
        }
        if (!ListenerUtil.mutListener.listen(1166)) {
            finish();
        }
    }

    private void finishCancel() {
        Intent resultData = new Intent();
        if (!ListenerUtil.mutListener.listen(1167)) {
            setResult(RESULT_CANCELED, resultData);
        }
        if (!ListenerUtil.mutListener.listen(1168)) {
            finish();
        }
    }

    private void failNoPronunciation() {
        if (!ListenerUtil.mutListener.listen(1169)) {
            stop(gtxt(R.string.multimedia_editor_error_word_not_found));
        }
        if (!ListenerUtil.mutListener.listen(1170)) {
            mPronunciationAddress = "no";
        }
        if (!ListenerUtil.mutListener.listen(1171)) {
            mMp3Address = "no";
        }
    }

    private void stop(String string) {
        if (!ListenerUtil.mutListener.listen(1172)) {
            progressDialog.dismiss();
        }
        if (!ListenerUtil.mutListener.listen(1173)) {
            showToast(string);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(1174)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(1175)) {
            outState.putBoolean(BUNDLE_KEY_SHUT_OFF, true);
        }
    }

    private String computeAddressOfTranslationPage() {
        // Service name has to be replaced from the language lister.
        String address = "https://dict.tu-chemnitz.de/dings.cgi?lang=en&service=SERVICE&opterrors=0&optpro=0&query=Welt";
        String strFrom = mSpinnerFrom.getSelectedItem().toString();
        String langCodeFrom = mLanguageLister.getCodeFor(strFrom);
        String query;
        try {
            query = URLEncoder.encode(mSource, "utf-8");
        } catch (UnsupportedEncodingException e) {
            query = mSource.replace(" ", "%20");
        }
        if (!ListenerUtil.mutListener.listen(1176)) {
            address = address.replaceAll("SERVICE", langCodeFrom).replaceAll("Welt", query);
        }
        return address;
    }

    private void showToast(CharSequence text) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        if (!ListenerUtil.mutListener.listen(1177)) {
            toast.show();
        }
    }

    private void showToastLong(CharSequence text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, text, duration);
        if (!ListenerUtil.mutListener.listen(1178)) {
            toast.show();
        }
    }

    // If the loading and dialog are cancelled
    @Override
    public void onCancel(DialogInterface dialog) {
        if (!ListenerUtil.mutListener.listen(1179)) {
            mStopped = true;
        }
        if (!ListenerUtil.mutListener.listen(1180)) {
            dismissCarefullyProgressDialog();
        }
        if (!ListenerUtil.mutListener.listen(1181)) {
            stopAllTasks();
        }
        Intent resultData = new Intent();
        if (!ListenerUtil.mutListener.listen(1182)) {
            setResult(RESULT_CANCELED, resultData);
        }
        if (!ListenerUtil.mutListener.listen(1183)) {
            finish();
        }
    }

    private void dismissCarefullyProgressDialog() {
        try {
            if (!ListenerUtil.mutListener.listen(1186)) {
                if ((ListenerUtil.mutListener.listen(1184) ? ((progressDialog != null) || progressDialog.isShowing()) : ((progressDialog != null) && progressDialog.isShowing()))) {
                    if (!ListenerUtil.mutListener.listen(1185)) {
                        progressDialog.dismiss();
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private void stopAllTasks() {
        AsyncTask<?, ?, ?> t = mPostTranslation;
        if (!ListenerUtil.mutListener.listen(1187)) {
            TaskOperations.stopTaskGracefully(t);
        }
        if (!ListenerUtil.mutListener.listen(1188)) {
            t = mPostPronunciation;
        }
        if (!ListenerUtil.mutListener.listen(1189)) {
            TaskOperations.stopTaskGracefully(t);
        }
        if (!ListenerUtil.mutListener.listen(1190)) {
            t = mDownloadMp3Task;
        }
        if (!ListenerUtil.mutListener.listen(1191)) {
            TaskOperations.stopTaskGracefully(t);
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(1192)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(1193)) {
            dismissCarefullyProgressDialog();
        }
        if (!ListenerUtil.mutListener.listen(1194)) {
            stopAllTasks();
        }
    }

    private String gtxt(int id) {
        return getText(id).toString();
    }
}

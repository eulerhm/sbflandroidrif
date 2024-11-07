/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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
package ch.threema.app.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.threema.app.BuildConfig;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.push.PushService;
import ch.threema.app.services.AppRestrictionService;
import ch.threema.app.services.license.LicenseService;
import ch.threema.app.services.license.LicenseServiceUser;
import ch.threema.app.services.license.SerialCredentials;
import ch.threema.app.services.license.UserCredentials;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.EditTextUtil;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// this should NOT extend ThreemaToolbarActivity
public class EnterSerialActivity extends ThreemaActivity {

    private static final Logger logger = LoggerFactory.getLogger(EnterSerialActivity.class);

    private static final String BUNDLE_PASSWORD = "bupw";

    private static final String BUNDLE_LICENSE_KEY = "bulk";

    private static final String DIALOG_TAG_CHECKING = "check";

    private TextView stateTextView, privateExplainText = null;

    private EditText licenseKeyText, passwordText;

    private ImageView unlockButton;

    private Button loginButton;

    private LicenseService licenseService;

    @SuppressLint("StringFormatInvalid")
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1361)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1363)) {
            if (!ConfigUtils.isSerialLicensed()) {
                if (!ListenerUtil.mutListener.listen(1362)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1364)) {
            setContentView(R.layout.activity_enter_serial);
        }
        try {
            if (!ListenerUtil.mutListener.listen(1368)) {
                licenseService = ThreemaApplication.getServiceManager().getLicenseService();
            }
        } catch (NullPointerException | FileSystemNotPresentException e) {
            if (!ListenerUtil.mutListener.listen(1365)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(1366)) {
                Toast.makeText(this, "Service Manager not available", Toast.LENGTH_LONG).show();
            }
            if (!ListenerUtil.mutListener.listen(1367)) {
                finish();
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(1370)) {
            if (licenseService == null) {
                if (!ListenerUtil.mutListener.listen(1369)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1371)) {
            stateTextView = findViewById(R.id.unlock_state);
        }
        if (!ListenerUtil.mutListener.listen(1372)) {
            licenseKeyText = findViewById(R.id.passphrase);
        }
        if (!ListenerUtil.mutListener.listen(1373)) {
            passwordText = findViewById(R.id.password);
        }
        if (!ListenerUtil.mutListener.listen(1399)) {
            if (!ConfigUtils.isWorkBuild()) {
                if (!ListenerUtil.mutListener.listen(1387)) {
                    licenseKeyText.addTextChangedListener(new PasswordWatcher());
                }
                if (!ListenerUtil.mutListener.listen(1388)) {
                    licenseKeyText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                }
                if (!ListenerUtil.mutListener.listen(1389)) {
                    licenseKeyText.setFilters(new InputFilter[] { new InputFilter.AllCaps(), new InputFilter.LengthFilter(11) });
                }
                if (!ListenerUtil.mutListener.listen(1394)) {
                    licenseKeyText.setOnKeyListener(new View.OnKeyListener() {

                        @Override
                        public boolean onKey(View v, int keyCode, KeyEvent event) {
                            if (!ListenerUtil.mutListener.listen(1393)) {
                                if ((ListenerUtil.mutListener.listen(1390) ? (event.getAction() == KeyEvent.ACTION_DOWN || keyCode == KeyEvent.KEYCODE_ENTER) : (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER))) {
                                    if (!ListenerUtil.mutListener.listen(1392)) {
                                        if (licenseKeyText.getText().length() == 11) {
                                            if (!ListenerUtil.mutListener.listen(1391)) {
                                                doUnlock();
                                            }
                                        }
                                    }
                                    return true;
                                }
                            }
                            return false;
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(1395)) {
                    unlockButton = findViewById(R.id.unlock_button);
                }
                if (!ListenerUtil.mutListener.listen(1397)) {
                    unlockButton.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(1396)) {
                                doUnlock();
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(1398)) {
                    this.enableLogin(false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1374)) {
                    privateExplainText = findViewById(R.id.private_explain);
                }
                if (!ListenerUtil.mutListener.listen(1380)) {
                    if (privateExplainText != null) {
                        if (!ListenerUtil.mutListener.listen(1377)) {
                            if (PushService.hmsServicesInstalled(this)) {
                                if (!ListenerUtil.mutListener.listen(1376)) {
                                    privateExplainText.setText(Html.fromHtml(String.format(getString(R.string.private_threema_download), getString(R.string.private_download_url_hms))));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(1375)) {
                                    privateExplainText.setText(Html.fromHtml(String.format(getString(R.string.private_threema_download), getString(R.string.private_download_url))));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(1378)) {
                            privateExplainText.setClickable(true);
                        }
                        if (!ListenerUtil.mutListener.listen(1379)) {
                            privateExplainText.setMovementMethod(LinkMovementMethod.getInstance());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1381)) {
                    licenseKeyText.addTextChangedListener(new TextChangeWatcher());
                }
                if (!ListenerUtil.mutListener.listen(1382)) {
                    passwordText.addTextChangedListener(new TextChangeWatcher());
                }
                if (!ListenerUtil.mutListener.listen(1383)) {
                    loginButton = findViewById(R.id.unlock_button_work);
                }
                if (!ListenerUtil.mutListener.listen(1385)) {
                    loginButton.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(1384)) {
                                doUnlock();
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(1386)) {
                    // always enable login button
                    this.enableLogin(true);
                }
            }
        }
        String scheme = null;
        Uri data = null;
        Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(1403)) {
            if (intent != null) {
                if (!ListenerUtil.mutListener.listen(1400)) {
                    data = intent.getData();
                }
                if (!ListenerUtil.mutListener.listen(1402)) {
                    if (data != null) {
                        if (!ListenerUtil.mutListener.listen(1401)) {
                            scheme = data.getScheme();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1424)) {
            if (!ConfigUtils.isSerialLicenseValid()) {
                if (!ListenerUtil.mutListener.listen(1419)) {
                    if (scheme != null) {
                        if (!ListenerUtil.mutListener.listen(1418)) {
                            if (scheme.startsWith(BuildConfig.uriScheme)) {
                                if (!ListenerUtil.mutListener.listen(1417)) {
                                    parseUrlAndCheck(data);
                                }
                            } else if (scheme.startsWith("https")) {
                                String path = data.getPath();
                                if (!ListenerUtil.mutListener.listen(1416)) {
                                    if ((ListenerUtil.mutListener.listen(1412) ? (path != null || (ListenerUtil.mutListener.listen(1411) ? (path.length() >= 1) : (ListenerUtil.mutListener.listen(1410) ? (path.length() <= 1) : (ListenerUtil.mutListener.listen(1409) ? (path.length() < 1) : (ListenerUtil.mutListener.listen(1408) ? (path.length() != 1) : (ListenerUtil.mutListener.listen(1407) ? (path.length() == 1) : (path.length() > 1))))))) : (path != null && (ListenerUtil.mutListener.listen(1411) ? (path.length() >= 1) : (ListenerUtil.mutListener.listen(1410) ? (path.length() <= 1) : (ListenerUtil.mutListener.listen(1409) ? (path.length() < 1) : (ListenerUtil.mutListener.listen(1408) ? (path.length() != 1) : (ListenerUtil.mutListener.listen(1407) ? (path.length() == 1) : (path.length() > 1))))))))) {
                                        if (!ListenerUtil.mutListener.listen(1413)) {
                                            path = path.substring(1);
                                        }
                                        if (!ListenerUtil.mutListener.listen(1415)) {
                                            if (path.startsWith("license")) {
                                                if (!ListenerUtil.mutListener.listen(1414)) {
                                                    parseUrlAndCheck(data);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1423)) {
                    if (ConfigUtils.isWorkRestricted()) {
                        String username = AppRestrictionUtil.getStringRestriction(getString(R.string.restriction__license_username));
                        String password = AppRestrictionUtil.getStringRestriction(getString(R.string.restriction__license_password));
                        if (!ListenerUtil.mutListener.listen(1422)) {
                            if ((ListenerUtil.mutListener.listen(1420) ? (!TestUtil.empty(username) || !TestUtil.empty(password)) : (!TestUtil.empty(username) && !TestUtil.empty(password)))) {
                                if (!ListenerUtil.mutListener.listen(1421)) {
                                    check(new UserCredentials(username, password));
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1406)) {
                    // we get here if called from url intent and we're already licensed
                    if (scheme != null) {
                        if (!ListenerUtil.mutListener.listen(1404)) {
                            Toast.makeText(this, R.string.already_licensed, Toast.LENGTH_LONG).show();
                        }
                        if (!ListenerUtil.mutListener.listen(1405)) {
                            finish();
                        }
                    }
                }
            }
        }
    }

    private void enableLogin(boolean enable) {
        if (!ListenerUtil.mutListener.listen(1431)) {
            if (!ConfigUtils.isWorkBuild()) {
                if (!ListenerUtil.mutListener.listen(1430)) {
                    if (this.unlockButton != null) {
                        if (!ListenerUtil.mutListener.listen(1428)) {
                            unlockButton.setClickable(enable);
                        }
                        if (!ListenerUtil.mutListener.listen(1429)) {
                            unlockButton.setEnabled(enable);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1427)) {
                    if (this.loginButton != null) {
                        if (!ListenerUtil.mutListener.listen(1425)) {
                            this.loginButton.setClickable(true);
                        }
                        if (!ListenerUtil.mutListener.listen(1426)) {
                            this.loginButton.setEnabled(true);
                        }
                    }
                }
            }
        }
    }

    private void parseUrlAndCheck(Uri data) {
        String query = data.getQuery();
        if (!ListenerUtil.mutListener.listen(1438)) {
            if (!TestUtil.empty(query)) {
                if (!ListenerUtil.mutListener.listen(1437)) {
                    if (licenseService instanceof LicenseServiceUser) {
                        final String username = data.getQueryParameter("username");
                        final String password = data.getQueryParameter("password");
                        if (!ListenerUtil.mutListener.listen(1436)) {
                            if ((ListenerUtil.mutListener.listen(1434) ? (!TestUtil.empty(username) || !TestUtil.empty(password)) : (!TestUtil.empty(username) && !TestUtil.empty(password)))) {
                                if (!ListenerUtil.mutListener.listen(1435)) {
                                    check(new UserCredentials(username, password));
                                }
                                return;
                            }
                        }
                    } else {
                        final String key = data.getQueryParameter("key");
                        if (!ListenerUtil.mutListener.listen(1433)) {
                            if (!TestUtil.empty(key)) {
                                if (!ListenerUtil.mutListener.listen(1432)) {
                                    check(new SerialCredentials(key));
                                }
                                return;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1439)) {
            Toast.makeText(this, R.string.invalid_input, Toast.LENGTH_LONG).show();
        }
    }

    private void doUnlock() {
        if (!ListenerUtil.mutListener.listen(1440)) {
            // hide keyboard to make error message visible on low resolution displays
            EditTextUtil.hideSoftKeyboard(this.licenseKeyText);
        }
        if (!ListenerUtil.mutListener.listen(1441)) {
            this.enableLogin(false);
        }
        if (!ListenerUtil.mutListener.listen(1448)) {
            if (ConfigUtils.isWorkBuild()) {
                if (!ListenerUtil.mutListener.listen(1447)) {
                    if ((ListenerUtil.mutListener.listen(1443) ? (!TestUtil.empty(this.licenseKeyText.getText().toString()) || !TestUtil.empty(this.passwordText.getText().toString())) : (!TestUtil.empty(this.licenseKeyText.getText().toString()) && !TestUtil.empty(this.passwordText.getText().toString())))) {
                        if (!ListenerUtil.mutListener.listen(1446)) {
                            this.check(new UserCredentials(this.licenseKeyText.getText().toString(), this.passwordText.getText().toString()));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1444)) {
                            this.enableLogin(true);
                        }
                        if (!ListenerUtil.mutListener.listen(1445)) {
                            this.stateTextView.setText(getString(R.string.invalid_input));
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1442)) {
                    this.check(new SerialCredentials(this.licenseKeyText.getText().toString()));
                }
            }
        }
    }

    private class PasswordWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            String initial = s.toString();
            String processed = initial.replaceAll("[^a-zA-Z0-9]", "");
            if (!ListenerUtil.mutListener.listen(1449)) {
                processed = processed.replaceAll("([a-zA-Z0-9]{5})(?=[a-zA-Z0-9])", "$1-");
            }
            if (!ListenerUtil.mutListener.listen(1451)) {
                if (!initial.equals(processed)) {
                    if (!ListenerUtil.mutListener.listen(1450)) {
                        s.replace(0, initial.length(), processed);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(1452)) {
                // enable login only if the length of the key is 11 chars
                enableLogin(s.length() == 11);
            }
        }
    }

    public class TextChangeWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!ListenerUtil.mutListener.listen(1454)) {
                if (stateTextView != null) {
                    if (!ListenerUtil.mutListener.listen(1453)) {
                        stateTextView.setText("");
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(1456)) {
                if (privateExplainText != null) {
                    if (!ListenerUtil.mutListener.listen(1455)) {
                        privateExplainText.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(1457)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(1459)) {
            if (!TestUtil.empty(licenseKeyText.getText())) {
                if (!ListenerUtil.mutListener.listen(1458)) {
                    outState.putString(BUNDLE_LICENSE_KEY, licenseKeyText.getText().toString());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1461)) {
            if (!TestUtil.empty(passwordText.getText())) {
                if (!ListenerUtil.mutListener.listen(1460)) {
                    outState.putString(BUNDLE_PASSWORD, passwordText.getText().toString());
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void check(final LicenseService.Credentials credentials) {
        if (!ListenerUtil.mutListener.listen(1473)) {
            new AsyncTask<Void, Void, String>() {

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(1462)) {
                        GenericProgressDialog.newInstance(R.string.checking_serial, R.string.please_wait).show(getSupportFragmentManager(), DIALOG_TAG_CHECKING);
                    }
                }

                @Override
                protected String doInBackground(Void... voids) {
                    String error = getString(R.string.error);
                    try {
                        if (!ListenerUtil.mutListener.listen(1464)) {
                            error = licenseService.validate(credentials);
                        }
                        if (!ListenerUtil.mutListener.listen(1467)) {
                            if (error == null) {
                                if (!ListenerUtil.mutListener.listen(1466)) {
                                    // validated
                                    if (ConfigUtils.isWorkBuild()) {
                                        if (!ListenerUtil.mutListener.listen(1465)) {
                                            AppRestrictionService.getInstance().fetchAndStoreWorkMDMSettings(ThreemaApplication.getServiceManager().getAPIConnector(), (UserCredentials) credentials);
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(1463)) {
                            logger.error("Exception", e);
                        }
                    }
                    return error;
                }

                @Override
                protected void onPostExecute(String error) {
                    if (!ListenerUtil.mutListener.listen(1468)) {
                        DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_CHECKING, true);
                    }
                    if (!ListenerUtil.mutListener.listen(1469)) {
                        enableLogin(true);
                    }
                    if (!ListenerUtil.mutListener.listen(1472)) {
                        if (error == null) {
                            if (!ListenerUtil.mutListener.listen(1471)) {
                                ConfigUtils.recreateActivity(EnterSerialActivity.this);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1470)) {
                                changeState(error);
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    private void changeState(String state) {
        if (!ListenerUtil.mutListener.listen(1474)) {
            this.stateTextView.setText(state);
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(1475)) {
            // finish application
            moveTaskToBack(true);
        }
        if (!ListenerUtil.mutListener.listen(1476)) {
            finish();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(1477)) {
            // activity when the keyboard is opened or orientation changes
            super.onConfigurationChanged(newConfig);
        }
    }
}

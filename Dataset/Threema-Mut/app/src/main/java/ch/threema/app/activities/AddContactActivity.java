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

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import java.io.IOException;
import java.util.Date;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import ch.threema.app.BuildConfig;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.dialogs.NewContactDialog;
import ch.threema.app.exceptions.EntryAlreadyExistsException;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.exceptions.InvalidEntryException;
import ch.threema.app.exceptions.PolicyViolationException;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.LockAppService;
import ch.threema.app.services.QRCodeService;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.LogUtil;
import ch.threema.app.utils.QRScannerUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.webclient.services.QRCodeParser;
import ch.threema.app.webclient.services.QRCodeParserImpl;
import ch.threema.client.Base64;
import ch.threema.localcrypto.MasterKeyLockedException;
import ch.threema.storage.models.ContactModel;
import static ch.threema.client.ProtocolDefines.IDENTITY_LEN;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AddContactActivity extends ThreemaActivity implements GenericAlertDialog.DialogClickListener, NewContactDialog.NewContactDialogClickListener {

    private static final String DIALOG_TAG_ADD_PROGRESS = "ap";

    private static final String DIALOG_TAG_ADD_ERROR = "ae";

    private static final String DIALOG_TAG_ADD_USER = "au";

    private static final String DIALOG_TAG_ADD_BY_ID = "abi";

    public static final String EXTRA_ADD_BY_ID = "add_by_id";

    public static final String EXTRA_ADD_BY_QR = "add_by_qr";

    private static final int PERMISSION_REQUEST_CAMERA = 1;

    private ContactService contactService;

    private QRCodeService qrCodeService;

    private LockAppService lockAppService;

    private ServiceManager serviceManager;

    private AsyncTask<Void, Void, Exception> addContactTask;

    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1660)) {
            serviceManager = ThreemaApplication.getServiceManager();
        }
        if (!ListenerUtil.mutListener.listen(1662)) {
            if (serviceManager == null) {
                if (!ListenerUtil.mutListener.listen(1661)) {
                    finish();
                }
                return;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(1665)) {
                this.qrCodeService = this.serviceManager.getQRCodeService();
            }
            if (!ListenerUtil.mutListener.listen(1666)) {
                this.contactService = this.serviceManager.getContactService();
            }
            if (!ListenerUtil.mutListener.listen(1667)) {
                this.lockAppService = this.serviceManager.getLockAppService();
            }
        } catch (MasterKeyLockedException | FileSystemNotPresentException e) {
            if (!ListenerUtil.mutListener.listen(1663)) {
                LogUtil.exception(e, this);
            }
            if (!ListenerUtil.mutListener.listen(1664)) {
                finish();
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(1669)) {
            if (ConfigUtils.getAppTheme(this) == ConfigUtils.THEME_DARK) {
                if (!ListenerUtil.mutListener.listen(1668)) {
                    setTheme(R.style.Theme_Threema_Translucent_Dark);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1670)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1671)) {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if (!ListenerUtil.mutListener.listen(1672)) {
            onNewIntent(getIntent());
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(1673)) {
            super.onNewIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(1690)) {
            if (intent != null) {
                String action = intent.getAction();
                if (!ListenerUtil.mutListener.listen(1685)) {
                    if (action != null) {
                        if (!ListenerUtil.mutListener.listen(1684)) {
                            if (action.equals(Intent.ACTION_VIEW)) {
                                Uri dataUri = intent.getData();
                                if (!ListenerUtil.mutListener.listen(1683)) {
                                    if (TestUtil.required(dataUri)) {
                                        String scheme = dataUri.getScheme();
                                        String host = dataUri.getHost();
                                        if (!ListenerUtil.mutListener.listen(1682)) {
                                            if ((ListenerUtil.mutListener.listen(1674) ? (scheme != null || host != null) : (scheme != null && host != null))) {
                                                if (!ListenerUtil.mutListener.listen(1681)) {
                                                    if ((ListenerUtil.mutListener.listen(1678) ? (((ListenerUtil.mutListener.listen(1675) ? (BuildConfig.uriScheme.equals(scheme) || "add".equals(host)) : (BuildConfig.uriScheme.equals(scheme) && "add".equals(host)))) && ((ListenerUtil.mutListener.listen(1677) ? ((ListenerUtil.mutListener.listen(1676) ? ("https".equals(scheme) || BuildConfig.actionUrl.equals(host)) : ("https".equals(scheme) && BuildConfig.actionUrl.equals(host))) || "/add".equals(dataUri.getPath())) : ((ListenerUtil.mutListener.listen(1676) ? ("https".equals(scheme) || BuildConfig.actionUrl.equals(host)) : ("https".equals(scheme) && BuildConfig.actionUrl.equals(host))) && "/add".equals(dataUri.getPath()))))) : (((ListenerUtil.mutListener.listen(1675) ? (BuildConfig.uriScheme.equals(scheme) || "add".equals(host)) : (BuildConfig.uriScheme.equals(scheme) && "add".equals(host)))) || ((ListenerUtil.mutListener.listen(1677) ? ((ListenerUtil.mutListener.listen(1676) ? ("https".equals(scheme) || BuildConfig.actionUrl.equals(host)) : ("https".equals(scheme) && BuildConfig.actionUrl.equals(host))) || "/add".equals(dataUri.getPath())) : ((ListenerUtil.mutListener.listen(1676) ? ("https".equals(scheme) || BuildConfig.actionUrl.equals(host)) : ("https".equals(scheme) && BuildConfig.actionUrl.equals(host))) && "/add".equals(dataUri.getPath()))))))) {
                                                        String id = dataUri.getQueryParameter("id");
                                                        if (!ListenerUtil.mutListener.listen(1680)) {
                                                            if (TestUtil.required(id)) {
                                                                if (!ListenerUtil.mutListener.listen(1679)) {
                                                                    addContactByIdentity(id);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1687)) {
                    if (intent.getBooleanExtra(EXTRA_ADD_BY_QR, false)) {
                        if (!ListenerUtil.mutListener.listen(1686)) {
                            scanQR();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1689)) {
                    if (intent.getBooleanExtra(EXTRA_ADD_BY_ID, false)) {
                        if (!ListenerUtil.mutListener.listen(1688)) {
                            requestID();
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void addContactByIdentity(final String identity) {
        if (!ListenerUtil.mutListener.listen(1692)) {
            if (lockAppService.isLocked()) {
                if (!ListenerUtil.mutListener.listen(1691)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1705)) {
            addContactTask = new AsyncTask<Void, Void, Exception>() {

                ContactModel newContactModel;

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(1693)) {
                        GenericProgressDialog.newInstance(R.string.creating_contact, R.string.please_wait).show(getSupportFragmentManager(), DIALOG_TAG_ADD_PROGRESS);
                    }
                }

                @Override
                protected Exception doInBackground(Void... params) {
                    try {
                        if (!ListenerUtil.mutListener.listen(1694)) {
                            newContactModel = contactService.createContactByIdentity(identity, false);
                        }
                    } catch (Exception e) {
                        return e;
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Exception exception) {
                    if (!ListenerUtil.mutListener.listen(1695)) {
                        if (isDestroyed()) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1696)) {
                        DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_ADD_PROGRESS, true);
                    }
                    if (!ListenerUtil.mutListener.listen(1704)) {
                        if (exception == null) {
                            if (!ListenerUtil.mutListener.listen(1703)) {
                                newContactAdded(newContactModel);
                            }
                        } else if (exception instanceof EntryAlreadyExistsException) {
                            if (!ListenerUtil.mutListener.listen(1700)) {
                                Toast.makeText(AddContactActivity.this, ((EntryAlreadyExistsException) exception).getTextId(), Toast.LENGTH_SHORT).show();
                            }
                            if (!ListenerUtil.mutListener.listen(1701)) {
                                showContactDetail(identity);
                            }
                            if (!ListenerUtil.mutListener.listen(1702)) {
                                finish();
                            }
                        } else if (exception instanceof InvalidEntryException) {
                            if (!ListenerUtil.mutListener.listen(1699)) {
                                GenericAlertDialog.newInstance(R.string.title_adduser, ((InvalidEntryException) exception).getTextId(), R.string.close, 0).show(getSupportFragmentManager(), DIALOG_TAG_ADD_ERROR);
                            }
                        } else if (exception instanceof PolicyViolationException) {
                            if (!ListenerUtil.mutListener.listen(1697)) {
                                Toast.makeText(AddContactActivity.this, R.string.disabled_by_policy_short, Toast.LENGTH_SHORT).show();
                            }
                            if (!ListenerUtil.mutListener.listen(1698)) {
                                finish();
                            }
                        }
                    }
                }
            };
        }
        if (!ListenerUtil.mutListener.listen(1706)) {
            addContactTask.execute();
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(1708)) {
            if (addContactTask != null) {
                if (!ListenerUtil.mutListener.listen(1707)) {
                    addContactTask.cancel(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1709)) {
            super.onDestroy();
        }
    }

    /**
     *  start a web client session (payload must be validated before
     *  the method is called)
     *
     *  fix #ANDR-570
     *  @param payload a valid payload
     */
    private void startWebClientByQRResult(final byte[] payload) {
        if (!ListenerUtil.mutListener.listen(1713)) {
            if (payload != null) {
                // start web client session screen with payload data and finish my screen
                Intent webClientIntent = new Intent(this, ch.threema.app.webclient.activities.SessionsActivity.class);
                if (!ListenerUtil.mutListener.listen(1710)) {
                    IntentDataUtil.append(payload, webClientIntent);
                }
                if (!ListenerUtil.mutListener.listen(1711)) {
                    this.finish();
                }
                if (!ListenerUtil.mutListener.listen(1712)) {
                    startActivity(webClientIntent);
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void addContactByQRResult(final QRCodeService.QRCodeContentResult qrResult) {
        ContactModel contactModel = contactService.getByPublicKey(qrResult.getPublicKey());
        if (!ListenerUtil.mutListener.listen(1731)) {
            if (contactModel != null) {
                // contact already exists - update it
                boolean c = true;
                int contactVerification = this.contactService.updateContactVerification(contactModel.getIdentity(), qrResult.getPublicKey());
                int textResId;
                switch(contactVerification) {
                    case ContactService.ContactVerificationResult_ALREADY_VERIFIED:
                        textResId = R.string.scan_duplicate;
                        break;
                    case ContactService.ContactVerificationResult_VERIFIED:
                        textResId = R.string.scan_successful;
                        break;
                    default:
                        textResId = R.string.id_mismatch;
                        if (!ListenerUtil.mutListener.listen(1722)) {
                            c = false;
                        }
                }
                if (!ListenerUtil.mutListener.listen(1730)) {
                    if (!c) {
                        if (!ListenerUtil.mutListener.listen(1729)) {
                            GenericAlertDialog.newInstance(R.string.title_adduser, getString(textResId), R.string.ok, 0).show(getSupportFragmentManager(), DIALOG_TAG_ADD_USER);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1728)) {
                            if (contactService.getIsHidden(contactModel.getIdentity())) {
                                if (!ListenerUtil.mutListener.listen(1726)) {
                                    contactService.setIsHidden(contactModel.getIdentity(), false);
                                }
                                if (!ListenerUtil.mutListener.listen(1727)) {
                                    newContactAdded(contactModel);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(1723)) {
                                    Toast.makeText(this.getApplicationContext(), textResId, Toast.LENGTH_SHORT).show();
                                }
                                if (!ListenerUtil.mutListener.listen(1724)) {
                                    showContactDetail(contactModel.getIdentity());
                                }
                                if (!ListenerUtil.mutListener.listen(1725)) {
                                    this.finish();
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1714)) {
                    if (AppRestrictionUtil.isAddContactDisabled(this)) {
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(1721)) {
                    // add new contact
                    new AsyncTask<Void, Void, String>() {

                        ContactModel newContactModel;

                        @Override
                        protected void onPreExecute() {
                            if (!ListenerUtil.mutListener.listen(1715)) {
                                GenericProgressDialog.newInstance(R.string.creating_contact, R.string.please_wait).show(getSupportFragmentManager(), DIALOG_TAG_ADD_PROGRESS);
                            }
                        }

                        @Override
                        protected String doInBackground(Void... params) {
                            try {
                                if (!ListenerUtil.mutListener.listen(1716)) {
                                    newContactModel = contactService.createContactByQRResult(qrResult);
                                }
                            } catch (final InvalidEntryException e) {
                                return getString(e.getTextId());
                            } catch (final EntryAlreadyExistsException e) {
                                return getString(e.getTextId());
                            } catch (final PolicyViolationException e) {
                                return getString(R.string.disabled_by_policy_short);
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(String message) {
                            if (!ListenerUtil.mutListener.listen(1717)) {
                                DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_ADD_PROGRESS, true);
                            }
                            if (!ListenerUtil.mutListener.listen(1720)) {
                                if (TestUtil.empty(message)) {
                                    if (!ListenerUtil.mutListener.listen(1719)) {
                                        newContactAdded(newContactModel);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(1718)) {
                                        GenericAlertDialog.newInstance(R.string.title_adduser, message, R.string.ok, 0).show(getSupportFragmentManager(), DIALOG_TAG_ADD_USER);
                                    }
                                }
                            }
                        }
                    }.execute();
                }
            }
        }
    }

    private void showContactDetail(String id) {
        Intent intent = new Intent(this, ContactDetailActivity.class);
        if (!ListenerUtil.mutListener.listen(1732)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, id);
        }
        if (!ListenerUtil.mutListener.listen(1733)) {
            this.startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(1734)) {
            finish();
        }
    }

    private void newContactAdded(ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(1738)) {
            if (contactModel != null) {
                if (!ListenerUtil.mutListener.listen(1735)) {
                    Toast.makeText(this.getApplicationContext(), R.string.creating_contact_successful, Toast.LENGTH_SHORT).show();
                }
                if (!ListenerUtil.mutListener.listen(1736)) {
                    showContactDetail(contactModel.getIdentity());
                }
                if (!ListenerUtil.mutListener.listen(1737)) {
                    finish();
                }
            }
        }
    }

    private void scanQR() {
        if (!ListenerUtil.mutListener.listen(1740)) {
            if (ConfigUtils.requestCameraPermissions(this, null, PERMISSION_REQUEST_CAMERA)) {
                if (!ListenerUtil.mutListener.listen(1739)) {
                    QRScannerUtil.getInstance().initiateScan(this, false, null);
                }
            }
        }
    }

    private void requestID() {
        DialogFragment dialogFragment = NewContactDialog.newInstance(R.string.menu_add_contact, R.string.enter_id_hint, R.string.ok, R.string.cancel);
        if (!ListenerUtil.mutListener.listen(1741)) {
            dialogFragment.show(getSupportFragmentManager(), DIALOG_TAG_ADD_BY_ID);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (!ListenerUtil.mutListener.listen(1742)) {
            super.onActivityResult(requestCode, resultCode, intent);
        }
        if (!ListenerUtil.mutListener.listen(1743)) {
            ConfigUtils.setLocaleOverride(this, serviceManager.getPreferenceService());
        }
        if (!ListenerUtil.mutListener.listen(1764)) {
            if (resultCode == RESULT_OK) {
                String payload = QRScannerUtil.getInstance().parseActivityResult(this, requestCode, resultCode, intent);
                if (!ListenerUtil.mutListener.listen(1762)) {
                    if (!TestUtil.empty(payload)) {
                        // first: try to parse as content result (contact scan)
                        QRCodeService.QRCodeContentResult contactQRCode = this.qrCodeService.getResult(payload);
                        if (!ListenerUtil.mutListener.listen(1749)) {
                            if (contactQRCode != null) {
                                if (!ListenerUtil.mutListener.listen(1747)) {
                                    // ok, try to add contact
                                    if ((ListenerUtil.mutListener.listen(1744) ? (contactQRCode.getExpirationDate() != null || contactQRCode.getExpirationDate().before(new Date())) : (contactQRCode.getExpirationDate() != null && contactQRCode.getExpirationDate().before(new Date())))) {
                                        if (!ListenerUtil.mutListener.listen(1746)) {
                                            GenericAlertDialog.newInstance(R.string.title_adduser, getString(R.string.expired_barcode), R.string.ok, 0).show(getSupportFragmentManager(), "ex");
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(1745)) {
                                            addContactByQRResult(contactQRCode);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(1748)) {
                                    // return, qr code valid and exit method
                                    DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_ADD_BY_ID, true);
                                }
                                return;
                            }
                        }
                        // second: try uri scheme
                        String scannedIdentity = null;
                        Uri uri = Uri.parse(payload);
                        if (!ListenerUtil.mutListener.listen(1758)) {
                            if (uri != null) {
                                String scheme = uri.getScheme();
                                if (!ListenerUtil.mutListener.listen(1754)) {
                                    if ((ListenerUtil.mutListener.listen(1750) ? (BuildConfig.uriScheme.equals(scheme) || "add".equals(uri.getAuthority())) : (BuildConfig.uriScheme.equals(scheme) && "add".equals(uri.getAuthority())))) {
                                        if (!ListenerUtil.mutListener.listen(1753)) {
                                            scannedIdentity = uri.getQueryParameter("id");
                                        }
                                    } else if ((ListenerUtil.mutListener.listen(1751) ? ("https".equals(scheme) || BuildConfig.contactActionUrl.equals(uri.getHost())) : ("https".equals(scheme) && BuildConfig.contactActionUrl.equals(uri.getHost())))) {
                                        if (!ListenerUtil.mutListener.listen(1752)) {
                                            scannedIdentity = uri.getLastPathSegment();
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(1757)) {
                                    if ((ListenerUtil.mutListener.listen(1755) ? (scannedIdentity != null || scannedIdentity.length() == IDENTITY_LEN) : (scannedIdentity != null && scannedIdentity.length() == IDENTITY_LEN))) {
                                        if (!ListenerUtil.mutListener.listen(1756)) {
                                            addContactByIdentity(scannedIdentity);
                                        }
                                        return;
                                    }
                                }
                            }
                        }
                        // third: try to parse as web client qr
                        try {
                            byte[] base64Payload = Base64.decode(payload);
                            if (!ListenerUtil.mutListener.listen(1761)) {
                                if (base64Payload != null) {
                                    final QRCodeParser webClientQRCodeParser = new QRCodeParserImpl();
                                    if (!ListenerUtil.mutListener.listen(1759)) {
                                        // throws if QR is not valid
                                        webClientQRCodeParser.parse(base64Payload);
                                    }
                                    if (!ListenerUtil.mutListener.listen(1760)) {
                                        // it was a valid web client qr code, exit method
                                        startWebClientByQRResult(base64Payload);
                                    }
                                    return;
                                }
                            }
                        } catch (IOException | QRCodeParser.InvalidQrCodeException x) {
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1763)) {
                    Toast.makeText(this, R.string.invalid_barcode, Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1765)) {
            finish();
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(1766)) {
            finish();
        }
    }

    @Override
    public void onNo(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(1767)) {
            finish();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(1780)) {
            switch(requestCode) {
                case PERMISSION_REQUEST_CAMERA:
                    if (!ListenerUtil.mutListener.listen(1779)) {
                        if ((ListenerUtil.mutListener.listen(1773) ? ((ListenerUtil.mutListener.listen(1772) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(1771) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(1770) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(1769) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(1768) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(1772) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(1771) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(1770) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(1769) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(1768) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                            if (!ListenerUtil.mutListener.listen(1778)) {
                                scanQR();
                            }
                        } else if (!shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                            if (!ListenerUtil.mutListener.listen(1777)) {
                                ConfigUtils.showPermissionRationale(this, getWindow().getDecorView().findViewById(android.R.id.content), R.string.permission_camera_qr_required, new BaseTransientBottomBar.BaseCallback<Snackbar>() {

                                    @Override
                                    public void onDismissed(Snackbar transientBottomBar, int event) {
                                        if (!ListenerUtil.mutListener.listen(1775)) {
                                            super.onDismissed(transientBottomBar, event);
                                        }
                                        if (!ListenerUtil.mutListener.listen(1776)) {
                                            finish();
                                        }
                                    }
                                });
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1774)) {
                                finish();
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(1781)) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onContactEnter(String tag, String text) {
        if (!ListenerUtil.mutListener.listen(1783)) {
            if (TestUtil.required(text)) {
                if (!ListenerUtil.mutListener.listen(1782)) {
                    addContactByIdentity(text);
                }
            }
        }
    }

    @Override
    public void onCancel(String tag) {
        if (!ListenerUtil.mutListener.listen(1784)) {
            finish();
        }
    }

    @Override
    public void onScanButtonClick(String tag) {
        if (!ListenerUtil.mutListener.listen(1785)) {
            scanQR();
        }
    }
}

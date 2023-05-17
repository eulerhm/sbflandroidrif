/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
package ch.threema.app.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.appcompat.app.AppCompatActivity;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.SimpleStringAlertDialog;
import ch.threema.app.qrscanner.activity.CaptureActivity;
import ch.threema.app.services.QRCodeService;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class QRScannerUtil {

    private static final Logger logger = LoggerFactory.getLogger(QRScannerUtil.class);

    private static boolean scanAnyCode;

    public static final int REQUEST_CODE_QR_SCANNER = 26657;

    // Singleton stuff
    private static QRScannerUtil sInstance = null;

    public static synchronized QRScannerUtil getInstance() {
        if (!ListenerUtil.mutListener.listen(55245)) {
            if (sInstance == null) {
                if (!ListenerUtil.mutListener.listen(55244)) {
                    sInstance = new QRScannerUtil();
                }
            }
        }
        return sInstance;
    }

    public void initiateScan(AppCompatActivity activity, boolean anyCode, String hint) {
        if (!ListenerUtil.mutListener.listen(55246)) {
            logger.info("initiateScan");
        }
        if (!ListenerUtil.mutListener.listen(55247)) {
            scanAnyCode = anyCode;
        }
        Intent intent = new Intent(activity, CaptureActivity.class);
        if (!ListenerUtil.mutListener.listen(55249)) {
            if (!TestUtil.empty(hint)) {
                if (!ListenerUtil.mutListener.listen(55248)) {
                    intent.putExtra("PROMPT_MESSAGE", hint);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(55250)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        if (!ListenerUtil.mutListener.listen(55251)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        }
        if (!ListenerUtil.mutListener.listen(55252)) {
            // lock orientation before launching scanner
            ConfigUtils.setRequestedOrientation(activity, activity.getResources().getConfiguration().orientation);
        }
        if (!ListenerUtil.mutListener.listen(55253)) {
            activity.startActivityForResult(intent, REQUEST_CODE_QR_SCANNER);
        }
    }

    private void invalidCodeDialog(AppCompatActivity activity) {
        if (!ListenerUtil.mutListener.listen(55254)) {
            SimpleStringAlertDialog.newInstance(R.string.scan_id, R.string.invalid_barcode).show(activity.getSupportFragmentManager(), "");
        }
    }

    public String parseActivityResult(AppCompatActivity activity, int requestCode, int resultCode, Intent intent) {
        if (!ListenerUtil.mutListener.listen(55266)) {
            if ((ListenerUtil.mutListener.listen(55259) ? (requestCode >= REQUEST_CODE_QR_SCANNER) : (ListenerUtil.mutListener.listen(55258) ? (requestCode <= REQUEST_CODE_QR_SCANNER) : (ListenerUtil.mutListener.listen(55257) ? (requestCode > REQUEST_CODE_QR_SCANNER) : (ListenerUtil.mutListener.listen(55256) ? (requestCode < REQUEST_CODE_QR_SCANNER) : (ListenerUtil.mutListener.listen(55255) ? (requestCode != REQUEST_CODE_QR_SCANNER) : (requestCode == REQUEST_CODE_QR_SCANNER))))))) {
                if (!ListenerUtil.mutListener.listen(55265)) {
                    if (activity != null) {
                        if (!ListenerUtil.mutListener.listen(55260)) {
                            ConfigUtils.setRequestedOrientation(activity, ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                        }
                        if (!ListenerUtil.mutListener.listen(55264)) {
                            if (resultCode == Activity.RESULT_OK) {
                                if (!ListenerUtil.mutListener.listen(55263)) {
                                    if ((ListenerUtil.mutListener.listen(55261) ? (scanAnyCode && intent.getBooleanExtra(ThreemaApplication.INTENT_DATA_QRCODE_TYPE_OK, false)) : (scanAnyCode || intent.getBooleanExtra(ThreemaApplication.INTENT_DATA_QRCODE_TYPE_OK, false)))) {
                                        return intent.getStringExtra(ThreemaApplication.INTENT_DATA_QRCODE);
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(55262)) {
                                            invalidCodeDialog(activity);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public QRCodeService.QRCodeContentResult parseActivityResult(AppCompatActivity activity, int requestCode, int resultCode, Intent intent, QRCodeService qrCodeService) {
        if (!ListenerUtil.mutListener.listen(55267)) {
            ConfigUtils.setRequestedOrientation(activity, ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        }
        if (!ListenerUtil.mutListener.listen(55271)) {
            if (qrCodeService != null) {
                String scanResult = parseActivityResult(activity, requestCode, resultCode, intent);
                if (!ListenerUtil.mutListener.listen(55270)) {
                    if (scanResult != null) {
                        QRCodeService.QRCodeContentResult qrRes = qrCodeService.getResult(scanResult);
                        if (!ListenerUtil.mutListener.listen(55268)) {
                            if (qrRes != null) {
                                return qrRes;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(55269)) {
                            invalidCodeDialog(activity);
                        }
                    }
                }
            }
        }
        return null;
    }
}

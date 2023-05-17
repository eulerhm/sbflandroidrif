/*
 * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 *http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.DrmSDK;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * 弹出对话框和处理拉起Activity的空Activity。
 * A dialog box is displayed and the empty activity that starts the activity is processed.
 *
 * @since 2020/07/01
 */
public class DrmDialogActivity extends Activity implements DialogObserver {

    private final Logger logger = LoggerFactory.getLogger(DrmDialogActivity.class);

    /**
     * 透明状态栏属性
     * Transparent Status Bar Properties
     */
    public static final int FLAG_TRANSLUCENT_STATUS = 0x04000000;

    /**
     * 透明导航栏属性
     * Transparent Navigation Bar Properties
     */
    public static final int FLAG_TRANS_NAVIGATION_BAR = 0x08000000;

    private AlertDialog waitingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(72006)) {
            logger.info("DrmDialogActivity onCreate");
        }
        if (!ListenerUtil.mutListener.listen(72007)) {
            setTransparency(this);
        }
        if (!ListenerUtil.mutListener.listen(72008)) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if (!ListenerUtil.mutListener.listen(72009)) {
            super.onCreate(savedInstanceState);
        }
        View view = new View(this);
        if (!ListenerUtil.mutListener.listen(72010)) {
            view.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }
        if (!ListenerUtil.mutListener.listen(72011)) {
            setContentView(view);
        }
        // Initiate an activity based on the transferred action.
        Intent dataIntent = getIntent();
        if (!ListenerUtil.mutListener.listen(72014)) {
            if (dataIntent == null) {
                if (!ListenerUtil.mutListener.listen(72012)) {
                    logger.error("DrmDialogActivity dataIntent null!");
                }
                if (!ListenerUtil.mutListener.listen(72013)) {
                    DrmKernel.handlerCodeException(this);
                }
                return;
            }
        }
        Bundle data = null;
        try {
            if (!ListenerUtil.mutListener.listen(72016)) {
                data = dataIntent.getExtras();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(72015)) {
                logger.error("data getExtras Exception");
            }
        }
        if (!ListenerUtil.mutListener.listen(72019)) {
            if (data == null) {
                if (!ListenerUtil.mutListener.listen(72017)) {
                    logger.error("DrmDialogActivity dataIntent getExtras null!");
                }
                if (!ListenerUtil.mutListener.listen(72018)) {
                    DrmKernel.handlerCodeException(this);
                }
                return;
            }
        }
        int dialog = data.getInt(Constants.KEY_EXTRA_DIALOG, ViewHelper.NO_DIALOG_INFO);
        if (!ListenerUtil.mutListener.listen(72020)) {
            logger.info("DrmDialogActivity dialog" + dialog);
        }
        String extra = data.getString(Constants.KEY_EXTRA_EXTRA);
        if (!ListenerUtil.mutListener.listen(72039)) {
            switch(dialog) {
                // Do not display the dialog box and pull out the activity.
                case ViewHelper.DIALOG_WAITING:
                    if (!ListenerUtil.mutListener.listen(72026)) {
                        if (!DialogTrigger.getInstance().hasObserver()) {
                            if (!ListenerUtil.mutListener.listen(72023)) {
                                logger.error("DrmDialogActivity no hasObserver");
                            }
                            if (!ListenerUtil.mutListener.listen(72024)) {
                                DialogTrigger.getInstance().registerObserver(this);
                            }
                            if (!ListenerUtil.mutListener.listen(72025)) {
                                waitingDialog = ViewHelper.showWaitingDialog(this);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(72021)) {
                                logger.error("DrmDialogActivity hasObserver finish");
                            }
                            if (!ListenerUtil.mutListener.listen(72022)) {
                                finish();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(72027)) {
                        DrmKernel.initBinding();
                    }
                    break;
                case ViewHelper.NO_DIALOG_INFO:
                    String action = "";
                    String pkg = "";
                    try {
                        if (!ListenerUtil.mutListener.listen(72029)) {
                            action = getIntent().getStringExtra(Constants.KEY_EXTRA_ACTION);
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(72028)) {
                            logger.error("action getStringExtra Exception");
                        }
                    }
                    try {
                        if (!ListenerUtil.mutListener.listen(72031)) {
                            pkg = getIntent().getStringExtra(Constants.KEY_EXTRA_PACKAGE);
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(72030)) {
                            logger.error("pkg getStringExtra Exception");
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(72034)) {
                        if ((ListenerUtil.mutListener.listen(72032) ? (TextUtils.isEmpty(action) && TextUtils.isEmpty(pkg)) : (TextUtils.isEmpty(action) || TextUtils.isEmpty(pkg)))) {
                            if (!ListenerUtil.mutListener.listen(72033)) {
                                logger.error("DrmDialogActivity NO_DIALOG_INFO null");
                            }
                            return;
                        }
                    }
                    Intent intent = new Intent(action);
                    if (!ListenerUtil.mutListener.listen(72035)) {
                        intent.putExtra(Constants.KEY_JSON_EXTRA, extra);
                    }
                    if (!ListenerUtil.mutListener.listen(72036)) {
                        intent.setPackage(pkg);
                    }
                    if (!ListenerUtil.mutListener.listen(72037)) {
                        startActivityForResult(intent, 0);
                    }
                    break;
                default:
                    int errorCode = data.getInt(Constants.KEY_EXTRA_CODE, DrmStatusCodes.CODE_DEFAULT);
                    if (!ListenerUtil.mutListener.listen(72038)) {
                        ViewHelper.showDailog(this, dialog, extra, errorCode);
                    }
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(72040)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(72041)) {
            logger.info("DRM_SDK DrmDialogActivity onActivityResult resultCode {}", resultCode);
        }
        if (!ListenerUtil.mutListener.listen(72042)) {
            // Directly returned DRM processing
            DrmKernel.onActivityResult(resultCode);
        }
        if (!ListenerUtil.mutListener.listen(72043)) {
            finish();
        }
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        if (!ListenerUtil.mutListener.listen(72044)) {
            // The activity switching animation is deleted.
            super.overridePendingTransition(0, 0);
        }
    }

    @Override
    public void closeDlg() {
        if (!ListenerUtil.mutListener.listen(72045)) {
            finish();
        }
    }

    @Override
    public void finish() {
        if (!ListenerUtil.mutListener.listen(72046)) {
            super.finish();
        }
        if (!ListenerUtil.mutListener.listen(72047)) {
            dismissDialog(waitingDialog);
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(72048)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(72049)) {
            dismissDialog(waitingDialog);
        }
    }

    private void dismissDialog(AlertDialog dialog) {
        try {
            if (!ListenerUtil.mutListener.listen(72055)) {
                if ((ListenerUtil.mutListener.listen(72052) ? (dialog != null || dialog.isShowing()) : (dialog != null && dialog.isShowing()))) {
                    if (!ListenerUtil.mutListener.listen(72053)) {
                        dialog.dismiss();
                    }
                    if (!ListenerUtil.mutListener.listen(72054)) {
                        DialogTrigger.getInstance().registerObserver(null);
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(72050)) {
                logger.error("DrmDialogActivity dismissDialog {}", e.getMessage());
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(72051)) {
                dialog = null;
            }
        }
    }

    public static void setTransparency(Activity activity) {
        // Make the notification bar transparent
        Window window = activity.getWindow();
        if (!ListenerUtil.mutListener.listen(72056)) {
            window.setFlags(FLAG_TRANSLUCENT_STATUS, FLAG_TRANSLUCENT_STATUS);
        }
        if (!ListenerUtil.mutListener.listen(72057)) {
            setHwFloating(activity, true);
        }
    }

    public static boolean setHwFloating(Activity activity, Boolean boolHwFloating) {
        try {
            Window w = activity.getWindow();
            if (!ListenerUtil.mutListener.listen(72059)) {
                HwInvoke.invokeFun(w.getClass(), w, "setHwFloating", new Class[] { boolean.class }, new Object[] { boolHwFloating });
            }
            return true;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(72058)) {
                Log.e("DrmDialogActivity", "Exception");
            }
        }
        return false;
    }
}

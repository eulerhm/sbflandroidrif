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

import java.util.concurrent.atomic.AtomicInteger;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * View辅助类
 * View auxiliary class
 *
 * @since 2020/07/01
 */
public class ViewHelper {

    /**
     * Dialog类型：提示等待鉴权
     * Dialog type: prompt for authentication
     */
    public static final int DIALOG_WAITING = 0;

    /**
     * Dialog类型：获取签名失败
     * Dialog type: Failed to obtain the signature.
     */
    public static final int DIALOG_GET_DRM_SIGN_FAILED = 1;

    /**
     * Dialog类型：无网络连接
     * Dialog type: no network connection
     */
    public static final int DIALOG_NO_NETWORK = 2;

    /**
     * Dialog类型：拒绝应用市场使用协议
     * Dialog type: Deny the application of the AppGallery protocol.
     */
    public static final int DIALOG_HIAPP_AGREEMENT_DECLINED = 3;

    /**
     * Dialog类型：鉴权失败
     * Dialog type: authentication failure
     */
    public static final int DIALOG_CHECK_FAILED = 5;

    /**
     * Dialog类型：用户按返回键取消鉴权
     * Dialog type: The user presses the return key to cancel authentication.
     */
    public static final int DIALOG_USER_INTERRUPT = 6;

    /**
     * Dialog类型：没有登陆华为账号
     * Dialog type: No Huawei ID is logged in.
     */
    public static final int DIALOG_NOT_LOGGED = 7;

    /**
     * Dialog类型：市场不支持鉴权
     * Dialog type: The market does not support authentication.
     */
    public static final int DIALOG_STORE_NOT_AVAILABLE = 8;

    /**
     * Dialog类型：超过使用设备数量
     * Dialog type: Exceeded the number of used devices.
     */
    public static final int DIALOG_OVER_LIMIT = 9;

    /**
     * Dialog类型：未知错误码
     * Dialog type: unknown error code
     */
    public static final int DIALOG_UNKNOW_ERROR = 10;

    /**
     * 没有传递弹出Dialog的类型信息
     * Dialog type information is not transferred.
     */
    public static final int NO_DIALOG_INFO = -1;

    /**
     * Dialog按键处理：什么也不做
     * Dialog key processing: nothing
     */
    public static final int OPERATION_NONE = 0;

    /**
     * Dialog按键处理：安装
     * Dialog Key Processing:Installing
     */
    public static final int OPERATION_INSTALL = 1;

    /**
     * Dialog按键处理：重试
     * Dialog key processing: retry
     */
    public static final int OPERATION_RETRY = 2;

    /**
     * Dialog按键处理：购买
     * Dialog key processing: purchase
     */
    public static final int OPERATION_BUY = 3;

    /**
     * Dialog按键处理：登录
     * Dialog key processing: login
     */
    public static final int OPERATION_LOGIN = 4;

    /**
     * Dialog按键处理：拉起协议
     * Dialog key processing: starting the protocol
     */
    public static final int OPERATION_AGREEMENT = 5;

    /**
     * Dialog按键处理：用户按返回键
     * Dialog key processing: A user presses the return key.
     */
    public static final int OPERATION_USER_INTERRUPT = 6;

    /**
     * Dialog按键处理：切换账号登录
     * Dialog key processing: switching account login
     */
    public static final int OPERATION_LOGIN_CHANGE = 7;

    private static final AtomicInteger S_NEXT_GENERATED_ID = new AtomicInteger(1);

    /**
     * 为一个View生成一个id。
     * Generate an ID for a view.
     */
    @SuppressLint("NewApi")
    public static int generateViewId() {
        if ((ListenerUtil.mutListener.listen(72443) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(72442) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(72441) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(72440) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(72439) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1))))))) {
            {
                long _loopCounter939 = 0;
                for (; ; ) {
                    ListenerUtil.loopListener.listen("_loopCounter939", ++_loopCounter939);
                    final int result = S_NEXT_GENERATED_ID.get();
                    // range under that.
                    int newValue = (ListenerUtil.mutListener.listen(72447) ? (result % 1) : (ListenerUtil.mutListener.listen(72446) ? (result / 1) : (ListenerUtil.mutListener.listen(72445) ? (result * 1) : (ListenerUtil.mutListener.listen(72444) ? (result - 1) : (result + 1)))));
                    if (!ListenerUtil.mutListener.listen(72454)) {
                        if ((ListenerUtil.mutListener.listen(72452) ? (newValue >= 0x00FFFFFF) : (ListenerUtil.mutListener.listen(72451) ? (newValue <= 0x00FFFFFF) : (ListenerUtil.mutListener.listen(72450) ? (newValue < 0x00FFFFFF) : (ListenerUtil.mutListener.listen(72449) ? (newValue != 0x00FFFFFF) : (ListenerUtil.mutListener.listen(72448) ? (newValue == 0x00FFFFFF) : (newValue > 0x00FFFFFF))))))) {
                            if (!ListenerUtil.mutListener.listen(72453)) {
                                // Roll over to 1, not 0.
                                newValue = 1;
                            }
                        }
                    }
                    if (S_NEXT_GENERATED_ID.compareAndSet(result, newValue)) {
                        return result;
                    }
                }
            }
        } else {
            return View.generateViewId();
        }
    }

    /**
     * 弹出提示等待Dialog
     * A dialog box is displayed, prompting you to wait for the Dialog.
     *
     * @param activity Dialog Context
     * @return Dialog box instance
     */
    public static AlertDialog showWaitingDialog(final Activity activity) {
        if (!ListenerUtil.mutListener.listen(72455)) {
            Log.i("DRM_SDK", "showWaitingDialog");
        }
        RelativeLayout layout = new RelativeLayout(activity);
        LayoutParams msgLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        DisplayMetrics metrics = new DisplayMetrics();
        if (!ListenerUtil.mutListener.listen(72456)) {
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        }
        EMUISupportUtil util = EMUISupportUtil.getInstance();
        int marginRight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, metrics);
        int marginLR = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, metrics);
        int marginTB = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, metrics);
        if (!ListenerUtil.mutListener.listen(72459)) {
            // EMUI3 Dialog has a 16-dip padding in the horizontal direction.
            if ((ListenerUtil.mutListener.listen(72457) ? (util.isSupportEMUI() || util.isEMUI3()) : (util.isSupportEMUI() && util.isEMUI3()))) {
                if (!ListenerUtil.mutListener.listen(72458)) {
                    marginLR = 0;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(72460)) {
            layout.setPadding(marginLR, marginTB, marginLR, marginTB);
        }
        ProgressBar progress = new ProgressBar(activity);
        LayoutParams loadLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        if (!ListenerUtil.mutListener.listen(72461)) {
            loadLayout.addRule(RelativeLayout.CENTER_VERTICAL);
        }
        if (!ListenerUtil.mutListener.listen(72462)) {
            loadLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        }
        if (!ListenerUtil.mutListener.listen(72463)) {
            loadLayout.addRule(RelativeLayout.ALIGN_PARENT_END);
        }
        if (!ListenerUtil.mutListener.listen(72464)) {
            layout.addView(progress, loadLayout);
        }
        if (!ListenerUtil.mutListener.listen(72465)) {
            progress.setId(generateViewId());
        }
        TextView message = new TextView(activity);
        if (!ListenerUtil.mutListener.listen(72466)) {
            message.setText(activity.getString(DrmResource.string(activity, "drm_dialog_message_waiting")));
        }
        if (!ListenerUtil.mutListener.listen(72467)) {
            message.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        }
        if (!ListenerUtil.mutListener.listen(72468)) {
            msgLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        }
        if (!ListenerUtil.mutListener.listen(72469)) {
            msgLayout.addRule(RelativeLayout.ALIGN_PARENT_START);
        }
        if (!ListenerUtil.mutListener.listen(72470)) {
            msgLayout.addRule(RelativeLayout.CENTER_VERTICAL);
        }
        if (!ListenerUtil.mutListener.listen(72471)) {
            msgLayout.addRule(RelativeLayout.LEFT_OF, progress.getId());
        }
        if (!ListenerUtil.mutListener.listen(72472)) {
            msgLayout.addRule(RelativeLayout.START_OF, progress.getId());
        }
        if (!ListenerUtil.mutListener.listen(72473)) {
            msgLayout.rightMargin = marginRight;
        }
        if (!ListenerUtil.mutListener.listen(72474)) {
            msgLayout.setMarginEnd(marginRight);
        }
        if (!ListenerUtil.mutListener.listen(72475)) {
            layout.addView(message, msgLayout);
        }
        AlertDialog dialog = new AlertDialog.Builder(activity).setView(layout).create();
        if (!ListenerUtil.mutListener.listen(72476)) {
            dialog.setCanceledOnTouchOutside(false);
        }
        if (!ListenerUtil.mutListener.listen(72479)) {
            dialog.setOnCancelListener(new OnCancelListener() {

                @Override
                public void onCancel(DialogInterface dialog) {
                    if (!ListenerUtil.mutListener.listen(72477)) {
                        activity.finish();
                    }
                    if (!ListenerUtil.mutListener.listen(72478)) {
                        DrmKernel.onDialogClicked(OPERATION_USER_INTERRUPT, DrmStatusCodes.CODE_CANCEL);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(72481)) {
            dialog.setOnDismissListener(new OnDismissListener() {

                @Override
                public void onDismiss(DialogInterface dialog) {
                    if (!ListenerUtil.mutListener.listen(72480)) {
                        activity.finish();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(72482)) {
            dialog.show();
        }
        if (!ListenerUtil.mutListener.listen(72483)) {
            Log.i("ViewHelper", "showWaitingDialog");
        }
        return dialog;
    }

    /**
     * 弹出Dialog
     * A basic dialog box is displayed.
     *
     * @param activity   Dialog pops up the required context.
     * @param dialogType Dialog type
     * @param errorCode  Error Code
     */
    private static AlertDialog showDailogPro(final Activity activity, int dialogType, String extra, final int errorCode, int operation, String resourceNameMessage, String resourceNameText) {
        final int operationId = operation;
        int msgId = DrmResource.string(activity, resourceNameMessage);
        int textId = DrmResource.string(activity, resourceNameText);
        int quitId = DrmResource.string(activity, "drm_dialog_text_quit");
        String msg = activity.getString(msgId);
        if (!ListenerUtil.mutListener.listen(72503)) {
            if ((ListenerUtil.mutListener.listen(72500) ? ((ListenerUtil.mutListener.listen(72494) ? ((ListenerUtil.mutListener.listen(72488) ? (dialogType >= DIALOG_CHECK_FAILED) : (ListenerUtil.mutListener.listen(72487) ? (dialogType <= DIALOG_CHECK_FAILED) : (ListenerUtil.mutListener.listen(72486) ? (dialogType > DIALOG_CHECK_FAILED) : (ListenerUtil.mutListener.listen(72485) ? (dialogType < DIALOG_CHECK_FAILED) : (ListenerUtil.mutListener.listen(72484) ? (dialogType != DIALOG_CHECK_FAILED) : (dialogType == DIALOG_CHECK_FAILED)))))) && (ListenerUtil.mutListener.listen(72493) ? (dialogType >= DIALOG_OVER_LIMIT) : (ListenerUtil.mutListener.listen(72492) ? (dialogType <= DIALOG_OVER_LIMIT) : (ListenerUtil.mutListener.listen(72491) ? (dialogType > DIALOG_OVER_LIMIT) : (ListenerUtil.mutListener.listen(72490) ? (dialogType < DIALOG_OVER_LIMIT) : (ListenerUtil.mutListener.listen(72489) ? (dialogType != DIALOG_OVER_LIMIT) : (dialogType == DIALOG_OVER_LIMIT))))))) : ((ListenerUtil.mutListener.listen(72488) ? (dialogType >= DIALOG_CHECK_FAILED) : (ListenerUtil.mutListener.listen(72487) ? (dialogType <= DIALOG_CHECK_FAILED) : (ListenerUtil.mutListener.listen(72486) ? (dialogType > DIALOG_CHECK_FAILED) : (ListenerUtil.mutListener.listen(72485) ? (dialogType < DIALOG_CHECK_FAILED) : (ListenerUtil.mutListener.listen(72484) ? (dialogType != DIALOG_CHECK_FAILED) : (dialogType == DIALOG_CHECK_FAILED)))))) || (ListenerUtil.mutListener.listen(72493) ? (dialogType >= DIALOG_OVER_LIMIT) : (ListenerUtil.mutListener.listen(72492) ? (dialogType <= DIALOG_OVER_LIMIT) : (ListenerUtil.mutListener.listen(72491) ? (dialogType > DIALOG_OVER_LIMIT) : (ListenerUtil.mutListener.listen(72490) ? (dialogType < DIALOG_OVER_LIMIT) : (ListenerUtil.mutListener.listen(72489) ? (dialogType != DIALOG_OVER_LIMIT) : (dialogType == DIALOG_OVER_LIMIT)))))))) && (ListenerUtil.mutListener.listen(72499) ? (dialogType >= DIALOG_UNKNOW_ERROR) : (ListenerUtil.mutListener.listen(72498) ? (dialogType <= DIALOG_UNKNOW_ERROR) : (ListenerUtil.mutListener.listen(72497) ? (dialogType > DIALOG_UNKNOW_ERROR) : (ListenerUtil.mutListener.listen(72496) ? (dialogType < DIALOG_UNKNOW_ERROR) : (ListenerUtil.mutListener.listen(72495) ? (dialogType != DIALOG_UNKNOW_ERROR) : (dialogType == DIALOG_UNKNOW_ERROR))))))) : ((ListenerUtil.mutListener.listen(72494) ? ((ListenerUtil.mutListener.listen(72488) ? (dialogType >= DIALOG_CHECK_FAILED) : (ListenerUtil.mutListener.listen(72487) ? (dialogType <= DIALOG_CHECK_FAILED) : (ListenerUtil.mutListener.listen(72486) ? (dialogType > DIALOG_CHECK_FAILED) : (ListenerUtil.mutListener.listen(72485) ? (dialogType < DIALOG_CHECK_FAILED) : (ListenerUtil.mutListener.listen(72484) ? (dialogType != DIALOG_CHECK_FAILED) : (dialogType == DIALOG_CHECK_FAILED)))))) && (ListenerUtil.mutListener.listen(72493) ? (dialogType >= DIALOG_OVER_LIMIT) : (ListenerUtil.mutListener.listen(72492) ? (dialogType <= DIALOG_OVER_LIMIT) : (ListenerUtil.mutListener.listen(72491) ? (dialogType > DIALOG_OVER_LIMIT) : (ListenerUtil.mutListener.listen(72490) ? (dialogType < DIALOG_OVER_LIMIT) : (ListenerUtil.mutListener.listen(72489) ? (dialogType != DIALOG_OVER_LIMIT) : (dialogType == DIALOG_OVER_LIMIT))))))) : ((ListenerUtil.mutListener.listen(72488) ? (dialogType >= DIALOG_CHECK_FAILED) : (ListenerUtil.mutListener.listen(72487) ? (dialogType <= DIALOG_CHECK_FAILED) : (ListenerUtil.mutListener.listen(72486) ? (dialogType > DIALOG_CHECK_FAILED) : (ListenerUtil.mutListener.listen(72485) ? (dialogType < DIALOG_CHECK_FAILED) : (ListenerUtil.mutListener.listen(72484) ? (dialogType != DIALOG_CHECK_FAILED) : (dialogType == DIALOG_CHECK_FAILED)))))) || (ListenerUtil.mutListener.listen(72493) ? (dialogType >= DIALOG_OVER_LIMIT) : (ListenerUtil.mutListener.listen(72492) ? (dialogType <= DIALOG_OVER_LIMIT) : (ListenerUtil.mutListener.listen(72491) ? (dialogType > DIALOG_OVER_LIMIT) : (ListenerUtil.mutListener.listen(72490) ? (dialogType < DIALOG_OVER_LIMIT) : (ListenerUtil.mutListener.listen(72489) ? (dialogType != DIALOG_OVER_LIMIT) : (dialogType == DIALOG_OVER_LIMIT)))))))) || (ListenerUtil.mutListener.listen(72499) ? (dialogType >= DIALOG_UNKNOW_ERROR) : (ListenerUtil.mutListener.listen(72498) ? (dialogType <= DIALOG_UNKNOW_ERROR) : (ListenerUtil.mutListener.listen(72497) ? (dialogType > DIALOG_UNKNOW_ERROR) : (ListenerUtil.mutListener.listen(72496) ? (dialogType < DIALOG_UNKNOW_ERROR) : (ListenerUtil.mutListener.listen(72495) ? (dialogType != DIALOG_UNKNOW_ERROR) : (dialogType == DIALOG_UNKNOW_ERROR))))))))) {
                if (!ListenerUtil.mutListener.listen(72502)) {
                    if (!isEmpty(extra)) {
                        if (!ListenerUtil.mutListener.listen(72501)) {
                            msg = activity.getString(msgId, extra);
                        }
                    }
                }
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity).setMessage(msg).setOnCancelListener(new OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                if (!ListenerUtil.mutListener.listen(72506)) {
                    activity.finish();
                }
                if (!ListenerUtil.mutListener.listen(72507)) {
                    DrmKernel.onDialogClicked(OPERATION_NONE, errorCode);
                }
            }
        }).setPositiveButton(textId, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!ListenerUtil.mutListener.listen(72504)) {
                    activity.finish();
                }
                if (!ListenerUtil.mutListener.listen(72505)) {
                    DrmKernel.onDialogClicked(operationId, errorCode);
                }
            }
        });
        if (!ListenerUtil.mutListener.listen(72516)) {
            if ((ListenerUtil.mutListener.listen(72512) ? (dialogType >= DIALOG_UNKNOW_ERROR) : (ListenerUtil.mutListener.listen(72511) ? (dialogType <= DIALOG_UNKNOW_ERROR) : (ListenerUtil.mutListener.listen(72510) ? (dialogType > DIALOG_UNKNOW_ERROR) : (ListenerUtil.mutListener.listen(72509) ? (dialogType < DIALOG_UNKNOW_ERROR) : (ListenerUtil.mutListener.listen(72508) ? (dialogType == DIALOG_UNKNOW_ERROR) : (dialogType != DIALOG_UNKNOW_ERROR))))))) {
                if (!ListenerUtil.mutListener.listen(72515)) {
                    builder.setNegativeButton(quitId, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (!ListenerUtil.mutListener.listen(72513)) {
                                activity.finish();
                            }
                            if (!ListenerUtil.mutListener.listen(72514)) {
                                DrmKernel.onDialogClicked(OPERATION_NONE, errorCode);
                            }
                        }
                    });
                }
            }
        }
        AlertDialog dialog = builder.create();
        if (!ListenerUtil.mutListener.listen(72517)) {
            dialog.show();
        }
        return dialog;
    }

    /**
     * activity 有效性判断
     *
     * @param activity   Dialog pops up the required context.
     */
    private static boolean activityIsValid(final Activity activity) {
        if (!ListenerUtil.mutListener.listen(72519)) {
            if ((ListenerUtil.mutListener.listen(72518) ? (activity == null && activity.isFinishing()) : (activity == null || activity.isFinishing()))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 弹出一个基本的Dialog
     * A basic dialog box is displayed.
     *
     * @param activity   Dialog pops up the required context.
     * @param dialogType Dialog type
     * @param errorCode  Error Code
     */
    public static AlertDialog showDailog(final Activity activity, int dialogType, String extra, final int errorCode) {
        if (!ListenerUtil.mutListener.listen(72520)) {
            if (!activityIsValid(activity)) {
                return null;
            }
        }
        boolean stringIsEmpty = isEmpty(extra);
        String resourceNameMessage = null;
        String resourceNameText = null;
        int operation = OPERATION_NONE;
        if (!ListenerUtil.mutListener.listen(72548)) {
            switch(dialogType) {
                case DIALOG_WAITING:
                    return showWaitingDialog(activity);
                case DIALOG_CHECK_FAILED:
                    if (!ListenerUtil.mutListener.listen(72521)) {
                        resourceNameMessage = (stringIsEmpty) ? "drm_dialog_message_check_failed_without_name" : "drm_dialog_message_check_failed";
                    }
                    if (!ListenerUtil.mutListener.listen(72522)) {
                        resourceNameText = "drm_dialog_text_buy";
                    }
                    if (!ListenerUtil.mutListener.listen(72523)) {
                        operation = OPERATION_BUY;
                    }
                    break;
                case DIALOG_GET_DRM_SIGN_FAILED:
                    if (!ListenerUtil.mutListener.listen(72524)) {
                        resourceNameMessage = "drm_dialog_message_get_sign_failed";
                    }
                    if (!ListenerUtil.mutListener.listen(72525)) {
                        resourceNameText = "drm_dialog_text_retry";
                    }
                    if (!ListenerUtil.mutListener.listen(72526)) {
                        operation = OPERATION_RETRY;
                    }
                    break;
                case DIALOG_NO_NETWORK:
                    if (!ListenerUtil.mutListener.listen(72527)) {
                        resourceNameMessage = "drm_dialog_message_no_internet";
                    }
                    if (!ListenerUtil.mutListener.listen(72528)) {
                        resourceNameText = "drm_dialog_text_retry";
                    }
                    if (!ListenerUtil.mutListener.listen(72529)) {
                        operation = OPERATION_RETRY;
                    }
                    break;
                case DIALOG_NOT_LOGGED:
                    if (!ListenerUtil.mutListener.listen(72530)) {
                        resourceNameMessage = "drm_dialog_message_not_logged";
                    }
                    if (!ListenerUtil.mutListener.listen(72531)) {
                        resourceNameText = "drm_dialog_text_login";
                    }
                    if (!ListenerUtil.mutListener.listen(72532)) {
                        operation = OPERATION_LOGIN;
                    }
                    break;
                case DIALOG_USER_INTERRUPT:
                    if (!ListenerUtil.mutListener.listen(72533)) {
                        resourceNameMessage = "drm_dialog_message_user_interrupt";
                    }
                    if (!ListenerUtil.mutListener.listen(72534)) {
                        resourceNameText = "drm_dialog_text_retry";
                    }
                    if (!ListenerUtil.mutListener.listen(72535)) {
                        operation = OPERATION_RETRY;
                    }
                    break;
                case DIALOG_HIAPP_AGREEMENT_DECLINED:
                    if (!ListenerUtil.mutListener.listen(72536)) {
                        resourceNameMessage = "drm_dialog_message_hiapp_agreement";
                    }
                    if (!ListenerUtil.mutListener.listen(72537)) {
                        resourceNameText = "drm_dialog_text_ok";
                    }
                    if (!ListenerUtil.mutListener.listen(72538)) {
                        operation = OPERATION_AGREEMENT;
                    }
                    break;
                case DIALOG_STORE_NOT_AVAILABLE:
                    if (!ListenerUtil.mutListener.listen(72539)) {
                        resourceNameMessage = "drm_dialog_message_hiapp_not_installed";
                    }
                    if (!ListenerUtil.mutListener.listen(72540)) {
                        resourceNameText = "drm_dialog_text_install";
                    }
                    if (!ListenerUtil.mutListener.listen(72541)) {
                        operation = OPERATION_INSTALL;
                    }
                    break;
                case DIALOG_OVER_LIMIT:
                    if (!ListenerUtil.mutListener.listen(72542)) {
                        resourceNameMessage = (stringIsEmpty) ? "drm_dialog_message_over_limit_without_name" : "drm_dialog_message_over_limit";
                    }
                    if (!ListenerUtil.mutListener.listen(72543)) {
                        resourceNameText = "drm_dialog_text_ok";
                    }
                    if (!ListenerUtil.mutListener.listen(72544)) {
                        operation = OPERATION_LOGIN_CHANGE;
                    }
                    break;
                case DIALOG_UNKNOW_ERROR:
                    if (!ListenerUtil.mutListener.listen(72545)) {
                        resourceNameMessage = "drm_dialog_message_other_errorcode";
                    }
                    if (!ListenerUtil.mutListener.listen(72546)) {
                        resourceNameText = "drm_dialog_text_hasknow";
                    }
                    if (!ListenerUtil.mutListener.listen(72547)) {
                        operation = OPERATION_NONE;
                    }
                    break;
                default:
                    return null;
            }
        }
        return showDailogPro(activity, dialogType, extra, errorCode, operation, resourceNameMessage, resourceNameText);
    }

    /**
     * 判断字符串是否为空。
     * Check whether the character string is empty.
     *
     * @param str 字符串(String)
     * @return true:空, false:非空(true: empty; false: not empty)
     */
    public static boolean isEmpty(String str) {
        return ((ListenerUtil.mutListener.listen(72554) ? (str == null && (ListenerUtil.mutListener.listen(72553) ? (str.length() >= 0) : (ListenerUtil.mutListener.listen(72552) ? (str.length() <= 0) : (ListenerUtil.mutListener.listen(72551) ? (str.length() > 0) : (ListenerUtil.mutListener.listen(72550) ? (str.length() < 0) : (ListenerUtil.mutListener.listen(72549) ? (str.length() != 0) : (str.length() == 0))))))) : (str == null || (ListenerUtil.mutListener.listen(72553) ? (str.length() >= 0) : (ListenerUtil.mutListener.listen(72552) ? (str.length() <= 0) : (ListenerUtil.mutListener.listen(72551) ? (str.length() > 0) : (ListenerUtil.mutListener.listen(72550) ? (str.length() < 0) : (ListenerUtil.mutListener.listen(72549) ? (str.length() != 0) : (str.length() == 0)))))))));
    }
}

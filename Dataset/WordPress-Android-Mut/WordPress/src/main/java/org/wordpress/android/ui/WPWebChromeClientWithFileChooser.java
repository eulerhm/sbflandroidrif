package org.wordpress.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import org.wordpress.android.fluxc.utils.MediaUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.helpers.WPWebChromeClient;
import static android.app.Activity.RESULT_OK;
import static org.wordpress.android.ui.RequestCodes.WEB_CHROME_CLIENT_FILE_PICKER;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WPWebChromeClientWithFileChooser extends WPWebChromeClient {

    private ValueCallback<Uri[]> mFilePathCallback;

    private final OnShowFileChooserListener mOnShowFileChooserListener;

    public WPWebChromeClientWithFileChooser(Activity activity, View view, int defaultPoster, ProgressBar progressBar, OnShowFileChooserListener onShowFileChooserListener) {
        super(activity, view, defaultPoster, progressBar);
        this.mOnShowFileChooserListener = onShowFileChooserListener;
    }

    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        if (!ListenerUtil.mutListener.listen(26687)) {
            this.mFilePathCallback = filePathCallback;
        }
        // Check if MODE_OPEN_MULTIPLE is specified
        Boolean canMultiselect = false;
        if (!ListenerUtil.mutListener.listen(26689)) {
            if (fileChooserParams.getMode() == WebChromeClient.FileChooserParams.MODE_OPEN_MULTIPLE) {
                if (!ListenerUtil.mutListener.listen(26688)) {
                    canMultiselect = true;
                }
            }
        }
        Intent intent = fileChooserParams.createIntent();
        if (!ListenerUtil.mutListener.listen(26690)) {
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, canMultiselect);
        }
        String[] acceptableMimeTypes = fileChooserParams.getAcceptTypes();
        int acceptableMimeTypesLength = acceptableMimeTypes.length;
        if (!ListenerUtil.mutListener.listen(26691)) {
            // Sets an explicit MIME data type that states that all MIME types are supported.
            intent.setType("*/*");
        }
        // Creates values for Intent.EXTRA_MIME_TYPES with the MIME types that are currently acceptable.
        String[] resolvedMimeTypes = new String[acceptableMimeTypesLength];
        String resolvedMimeType = null;
        if (!ListenerUtil.mutListener.listen(26704)) {
            {
                long _loopCounter399 = 0;
                for (int index = 0; (ListenerUtil.mutListener.listen(26703) ? (index >= acceptableMimeTypesLength) : (ListenerUtil.mutListener.listen(26702) ? (index <= acceptableMimeTypesLength) : (ListenerUtil.mutListener.listen(26701) ? (index > acceptableMimeTypesLength) : (ListenerUtil.mutListener.listen(26700) ? (index != acceptableMimeTypesLength) : (ListenerUtil.mutListener.listen(26699) ? (index == acceptableMimeTypesLength) : (index < acceptableMimeTypesLength)))))); index++) {
                    ListenerUtil.loopListener.listen("_loopCounter399", ++_loopCounter399);
                    String acceptableMimeType = acceptableMimeTypes[index];
                    if (!ListenerUtil.mutListener.listen(26695)) {
                        /**
                         * The fileChooserParams.getAcceptTypes() API states that the it returns an array of acceptable MIME
                         * types. The returned MIME type could be partial such as audio/* . Currently, there are plugins that
                         * return extensions when the form input type is utilized instead of
                         *  MIME types. The logic below is to accommodate the use cases by utilizing the extension to resolve
                         *  the appropriate Mime type with MediaUtils.getMimeTypeForExtension().
                         *
                         *  N.B The condition below ensures that mime-types that have dots in them (eg. application/vnd.ms-excel)
                         *  are not accepted.
                         */
                        if ((ListenerUtil.mutListener.listen(26692) ? (acceptableMimeType.contains(".") || !acceptableMimeType.contains("/")) : (acceptableMimeType.contains(".") && !acceptableMimeType.contains("/")))) {
                            String extension = acceptableMimeType.replace(".", "");
                            if (!ListenerUtil.mutListener.listen(26694)) {
                                resolvedMimeType = MediaUtils.getMimeTypeForExtension(extension);
                            }
                        } else if (acceptableMimeType.contains("/")) {
                            if (!ListenerUtil.mutListener.listen(26693)) {
                                resolvedMimeType = acceptableMimeType;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(26698)) {
                        if (resolvedMimeType != null) {
                            if (!ListenerUtil.mutListener.listen(26697)) {
                                resolvedMimeTypes[index] = resolvedMimeType;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(26696)) {
                                AppLog.w(T.EDITOR, "MediaUtils.getMimeTypeForExtension failed to resolve the ${acceptableMimeType} MIME type");
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26705)) {
            // Uses Intent.EXTRA_MIME_TYPES to the MIME types that should be acceptable.
            intent.putExtra(Intent.EXTRA_MIME_TYPES, resolvedMimeTypes);
        }
        if (!ListenerUtil.mutListener.listen(26707)) {
            if (mOnShowFileChooserListener != null) {
                if (!ListenerUtil.mutListener.listen(26706)) {
                    mOnShowFileChooserListener.startActivityForFileChooserResult(intent, WEB_CHROME_CLIENT_FILE_PICKER);
                }
            }
        }
        return true;
    }

    void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Uri[] selectedUris = null;
        if (!ListenerUtil.mutListener.listen(26720)) {
            if ((ListenerUtil.mutListener.listen(26709) ? ((ListenerUtil.mutListener.listen(26708) ? (intent != null || resultCode == RESULT_OK) : (intent != null && resultCode == RESULT_OK)) || requestCode == WEB_CHROME_CLIENT_FILE_PICKER) : ((ListenerUtil.mutListener.listen(26708) ? (intent != null || resultCode == RESULT_OK) : (intent != null && resultCode == RESULT_OK)) && requestCode == WEB_CHROME_CLIENT_FILE_PICKER))) {
                if (!ListenerUtil.mutListener.listen(26719)) {
                    // if ClipData is not empty that means there are multiple files.
                    if (intent.getClipData() != null) {
                        // process multiple files
                        int clipDataItemCount = intent.getClipData().getItemCount();
                        if (!ListenerUtil.mutListener.listen(26711)) {
                            selectedUris = new Uri[clipDataItemCount];
                        }
                        if (!ListenerUtil.mutListener.listen(26718)) {
                            {
                                long _loopCounter400 = 0;
                                for (int index = 0; (ListenerUtil.mutListener.listen(26717) ? (index >= clipDataItemCount) : (ListenerUtil.mutListener.listen(26716) ? (index <= clipDataItemCount) : (ListenerUtil.mutListener.listen(26715) ? (index > clipDataItemCount) : (ListenerUtil.mutListener.listen(26714) ? (index != clipDataItemCount) : (ListenerUtil.mutListener.listen(26713) ? (index == clipDataItemCount) : (index < clipDataItemCount)))))); index++) {
                                    ListenerUtil.loopListener.listen("_loopCounter400", ++_loopCounter400);
                                    if (!ListenerUtil.mutListener.listen(26712)) {
                                        selectedUris[index] = intent.getClipData().getItemAt(index).getUri();
                                    }
                                }
                            }
                        }
                    } else if (intent.getData() != null) {
                        if (!ListenerUtil.mutListener.listen(26710)) {
                            // process the single file
                            selectedUris = WebChromeClient.FileChooserParams.parseResult(resultCode, intent);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26722)) {
            // They will not trigger the File Picker since it is waiting on the result of the previous file request.
            if (mFilePathCallback != null) {
                if (!ListenerUtil.mutListener.listen(26721)) {
                    mFilePathCallback.onReceiveValue(selectedUris);
                }
            }
        }
    }

    interface OnShowFileChooserListener {

        void startActivityForFileChooserResult(Intent intent, int requestCode);
    }
}

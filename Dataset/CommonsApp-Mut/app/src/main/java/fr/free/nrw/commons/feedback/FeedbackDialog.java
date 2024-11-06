package fr.free.nrw.commons.feedback;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.databinding.DialogFeedbackBinding;
import fr.free.nrw.commons.feedback.model.Feedback;
import fr.free.nrw.commons.utils.ConfigUtils;
import fr.free.nrw.commons.utils.DeviceInfoUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Feedback dialog that asks user for message and
 * other device specifications
 */
public class FeedbackDialog extends Dialog {

    DialogFeedbackBinding dialogFeedbackBinding;

    private OnFeedbackSubmitCallback onFeedbackSubmitCallback;

    public FeedbackDialog(Context context, OnFeedbackSubmitCallback onFeedbackSubmitCallback) {
        super(context);
        if (!ListenerUtil.mutListener.listen(3840)) {
            this.onFeedbackSubmitCallback = onFeedbackSubmitCallback;
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3841)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3842)) {
            dialogFeedbackBinding = DialogFeedbackBinding.inflate(getLayoutInflater());
        }
        final View view = dialogFeedbackBinding.getRoot();
        if (!ListenerUtil.mutListener.listen(3843)) {
            setContentView(view);
        }
        if (!ListenerUtil.mutListener.listen(3845)) {
            dialogFeedbackBinding.btnSubmitFeedback.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(3844)) {
                        submitFeedback();
                    }
                }
            });
        }
    }

    /**
     * When the button is clicked, it will create a feedback object
     * and give a callback to calling activity/fragment
     */
    void submitFeedback() {
        if (!ListenerUtil.mutListener.listen(3847)) {
            if (dialogFeedbackBinding.feedbackItemEditText.getText().toString().equals("")) {
                if (!ListenerUtil.mutListener.listen(3846)) {
                    dialogFeedbackBinding.feedbackItemEditText.setError(getContext().getString(R.string.enter_description));
                }
                return;
            }
        }
        String appVersion = ConfigUtils.getVersionNameWithSha(getContext());
        String androidVersion = dialogFeedbackBinding.androidVersionCheckbox.isChecked() ? DeviceInfoUtil.getAndroidVersion() : null;
        String apiLevel = dialogFeedbackBinding.apiLevelCheckbox.isChecked() ? DeviceInfoUtil.getAPILevel() : null;
        String deviceManufacturer = dialogFeedbackBinding.deviceManufacturerCheckbox.isChecked() ? DeviceInfoUtil.getDeviceManufacturer() : null;
        String deviceModel = dialogFeedbackBinding.deviceModelCheckbox.isChecked() ? DeviceInfoUtil.getDeviceModel() : null;
        String deviceName = dialogFeedbackBinding.deviceNameCheckbox.isChecked() ? DeviceInfoUtil.getDevice() : null;
        String networkType = dialogFeedbackBinding.networkTypeCheckbox.isChecked() ? DeviceInfoUtil.getConnectionType(getContext()).toString() : null;
        Feedback feedback = new Feedback(appVersion, apiLevel, dialogFeedbackBinding.feedbackItemEditText.getText().toString(), androidVersion, deviceModel, deviceManufacturer, deviceName, networkType);
        if (!ListenerUtil.mutListener.listen(3848)) {
            onFeedbackSubmitCallback.onFeedbackSubmit(feedback);
        }
        if (!ListenerUtil.mutListener.listen(3849)) {
            dismiss();
        }
    }
}

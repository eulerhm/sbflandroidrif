package fr.free.nrw.commons.upload;

import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import fr.free.nrw.commons.R;
import java.io.File;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SimilarImageDialogFragment extends DialogFragment {

    @BindView(R.id.orginalImage)
    SimpleDraweeView originalImage;

    @BindView(R.id.possibleImage)
    SimpleDraweeView possibleImage;

    @BindView(R.id.postive_button)
    Button positiveButton;

    @BindView(R.id.negative_button)
    Button negativeButton;

    // Implemented interface from shareActivity
    Callback callback;

    Boolean gotResponse = false;

    public SimilarImageDialogFragment() {
    }

    public interface Callback {

        void onPositiveResponse();

        void onNegativeResponse();
    }

    public void setCallback(Callback callback) {
        if (!ListenerUtil.mutListener.listen(7697)) {
            this.callback = callback;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_similar_image_dialog, container, false);
        if (!ListenerUtil.mutListener.listen(7698)) {
            ButterKnife.bind(this, view);
        }
        if (!ListenerUtil.mutListener.listen(7699)) {
            originalImage.setHierarchy(GenericDraweeHierarchyBuilder.newInstance(getResources()).setPlaceholderImage(VectorDrawableCompat.create(getResources(), R.drawable.ic_image_black_24dp, getContext().getTheme())).setFailureImage(VectorDrawableCompat.create(getResources(), R.drawable.ic_error_outline_black_24dp, getContext().getTheme())).build());
        }
        if (!ListenerUtil.mutListener.listen(7700)) {
            possibleImage.setHierarchy(GenericDraweeHierarchyBuilder.newInstance(getResources()).setPlaceholderImage(VectorDrawableCompat.create(getResources(), R.drawable.ic_image_black_24dp, getContext().getTheme())).setFailureImage(VectorDrawableCompat.create(getResources(), R.drawable.ic_error_outline_black_24dp, getContext().getTheme())).build());
        }
        if (!ListenerUtil.mutListener.listen(7701)) {
            originalImage.setImageURI(Uri.fromFile(new File(getArguments().getString("originalImagePath"))));
        }
        if (!ListenerUtil.mutListener.listen(7702)) {
            possibleImage.setImageURI(Uri.fromFile(new File(getArguments().getString("possibleImagePath"))));
        }
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(7703)) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if (!ListenerUtil.mutListener.listen(7704)) {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (!ListenerUtil.mutListener.listen(7706)) {
            // I user dismisses dialog by pressing outside the dialog.
            if (!gotResponse) {
                if (!ListenerUtil.mutListener.listen(7705)) {
                    callback.onNegativeResponse();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7707)) {
            super.onDismiss(dialog);
        }
    }

    @OnClick(R.id.negative_button)
    public void onNegativeButtonClicked() {
        if (!ListenerUtil.mutListener.listen(7708)) {
            callback.onNegativeResponse();
        }
        if (!ListenerUtil.mutListener.listen(7709)) {
            gotResponse = true;
        }
        if (!ListenerUtil.mutListener.listen(7710)) {
            dismiss();
        }
    }

    @OnClick(R.id.postive_button)
    public void onPositiveButtonClicked() {
        if (!ListenerUtil.mutListener.listen(7711)) {
            callback.onPositiveResponse();
        }
        if (!ListenerUtil.mutListener.listen(7712)) {
            gotResponse = true;
        }
        if (!ListenerUtil.mutListener.listen(7713)) {
            dismiss();
        }
    }
}

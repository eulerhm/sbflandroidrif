package fr.free.nrw.commons.upload;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import fr.free.nrw.commons.di.CommonsDaggerSupportFragment;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The base fragment of the fragments in upload
 */
public class UploadBaseFragment extends CommonsDaggerSupportFragment {

    public Callback callback;

    public static final String CALLBACK = "callback";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(7761)) {
            super.onCreate(savedInstanceState);
        }
    }

    public void setCallback(Callback callback) {
        if (!ListenerUtil.mutListener.listen(7762)) {
            this.callback = callback;
        }
    }

    protected void onBecameVisible() {
    }

    public interface Callback {

        void onNextButtonClicked(int index);

        void onPreviousButtonClicked(int index);

        void showProgress(boolean shouldShow);

        int getIndexInViewFlipper(UploadBaseFragment fragment);

        int getTotalNumberOfSteps();

        boolean isWLMUpload();
    }
}

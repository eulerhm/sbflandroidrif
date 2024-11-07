package fr.free.nrw.commons.LocationPicker;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Observes live camera position data
 */
public class LocationPickerViewModel extends AndroidViewModel implements Callback<CameraPosition> {

    /**
     * Wrapping CameraPosition with MutableLiveData
     */
    private final MutableLiveData<CameraPosition> result = new MutableLiveData<>();

    /**
     * Constructor for this class
     *
     * @param application Application
     */
    public LocationPickerViewModel(@NonNull final Application application) {
        super(application);
    }

    /**
     * Responses on camera position changing
     *
     * @param call     Call<CameraPosition>
     * @param response Response<CameraPosition>
     */
    @Override
    public void onResponse(@NotNull final Call<CameraPosition> call, final Response<CameraPosition> response) {
        if (!ListenerUtil.mutListener.listen(1901)) {
            if (response.body() == null) {
                if (!ListenerUtil.mutListener.listen(1900)) {
                    result.setValue(null);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1902)) {
            result.setValue(response.body());
        }
    }

    @Override
    public void onFailure(@NotNull final Call<CameraPosition> call, @NotNull final Throwable t) {
        if (!ListenerUtil.mutListener.listen(1903)) {
            Timber.e(t);
        }
    }

    /**
     * Gets live CameraPosition
     *
     * @return MutableLiveData<CameraPosition>
     */
    public MutableLiveData<CameraPosition> getResult() {
        return result;
    }
}

package org.owntracks.android.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.maps.model.LatLng;
import org.owntracks.android.BR;
import org.owntracks.android.geocoding.GeocoderProvider;
import org.owntracks.android.model.messages.MessageCard;
import org.owntracks.android.model.messages.MessageLocation;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FusedContact extends BaseObservable implements Comparable<FusedContact> {

    private final String id;

    private GeocoderProvider geocoderProvider;

    private MutableLiveData<MessageLocation> messageLocation = new MutableLiveData<>();

    private MessageCard messageCard;

    private Integer imageProvider = 0;

    private long tst = 0;

    @Bindable
    public Integer getImageProvider() {
        return imageProvider;
    }

    @Bindable
    public void setImageProvider(Integer imageProvider) {
        if (!ListenerUtil.mutListener.listen(100)) {
            this.imageProvider = imageProvider;
        }
    }

    public FusedContact(@Nullable String id) {
        this.id = ((ListenerUtil.mutListener.listen(101) ? (id != null || !id.isEmpty()) : (id != null && !id.isEmpty()))) ? id : "NOID";
        if (!ListenerUtil.mutListener.listen(102)) {
            this.geocoderProvider = geocoderProvider;
        }
    }

    public boolean setMessageLocation(MessageLocation messageLocation) {
        if (!ListenerUtil.mutListener.listen(108)) {
            if ((ListenerUtil.mutListener.listen(107) ? (tst >= messageLocation.getTimestamp()) : (ListenerUtil.mutListener.listen(106) ? (tst <= messageLocation.getTimestamp()) : (ListenerUtil.mutListener.listen(105) ? (tst < messageLocation.getTimestamp()) : (ListenerUtil.mutListener.listen(104) ? (tst != messageLocation.getTimestamp()) : (ListenerUtil.mutListener.listen(103) ? (tst == messageLocation.getTimestamp()) : (tst > messageLocation.getTimestamp())))))))
                return false;
        }
        if (!ListenerUtil.mutListener.listen(109)) {
            Timber.v("update contact:%s, tst:%s", id, messageLocation.getTimestamp());
        }
        if (!ListenerUtil.mutListener.listen(110)) {
            // Allows to update fusedLocation if geocoder of messageLocation changed
            messageLocation.setContact(this);
        }
        if (!ListenerUtil.mutListener.listen(111)) {
            this.messageLocation.postValue(messageLocation);
        }
        if (!ListenerUtil.mutListener.listen(112)) {
            this.tst = messageLocation.getTimestamp();
        }
        if (!ListenerUtil.mutListener.listen(113)) {
            notifyMessageLocationPropertyChanged();
        }
        return true;
    }

    public void setMessageCard(MessageCard messageCard) {
        if (!ListenerUtil.mutListener.listen(114)) {
            this.messageCard = messageCard;
        }
        if (!ListenerUtil.mutListener.listen(115)) {
            notifyMessageCardPropertyChanged();
        }
    }

    private void notifyMessageCardPropertyChanged() {
        if (!ListenerUtil.mutListener.listen(116)) {
            this.notifyPropertyChanged(BR.fusedName);
        }
        if (!ListenerUtil.mutListener.listen(117)) {
            this.notifyPropertyChanged(BR.imageProvider);
        }
        if (!ListenerUtil.mutListener.listen(118)) {
            this.notifyPropertyChanged(BR.id);
        }
    }

    public void notifyMessageLocationPropertyChanged() {
        if (!ListenerUtil.mutListener.listen(120)) {
            if (this.messageLocation.getValue() != null) {
                if (!ListenerUtil.mutListener.listen(119)) {
                    Timber.d("Geocode location updated for %s: %s", this.id, this.messageLocation.getValue().getGeocode());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(121)) {
            this.notifyPropertyChanged(BR.fusedName);
        }
        if (!ListenerUtil.mutListener.listen(122)) {
            this.notifyPropertyChanged(BR.messageLocation);
        }
        if (!ListenerUtil.mutListener.listen(123)) {
            this.notifyPropertyChanged(BR.geocodedLocation);
        }
        if (!ListenerUtil.mutListener.listen(124)) {
            this.notifyPropertyChanged(BR.fusedLocationAccuracy);
        }
        if (!ListenerUtil.mutListener.listen(125)) {
            this.notifyPropertyChanged(BR.tst);
        }
        if (!ListenerUtil.mutListener.listen(126)) {
            this.notifyPropertyChanged(BR.trackerId);
        }
        if (!ListenerUtil.mutListener.listen(127)) {
            this.notifyPropertyChanged(BR.id);
        }
    }

    @Bindable
    public MessageCard getMessageCard() {
        return messageCard;
    }

    @Bindable
    public MutableLiveData<MessageLocation> getMessageLocation() {
        return messageLocation;
    }

    @Bindable
    public String getFusedName() {
        if ((ListenerUtil.mutListener.listen(128) ? (hasCard() || getMessageCard().hasName()) : (hasCard() && getMessageCard().hasName())))
            return getMessageCard().getName();
        else
            return getTrackerId();
    }

    @Bindable
    public String getFusedLocationAccuracy() {
        return Integer.toString(this.hasLocation() ? messageLocation.getValue().getAccuracy() : 0);
    }

    @Bindable
    public String getGeocodedLocation() {
        return this.messageLocation.getValue().getGeocode();
    }

    public boolean hasLocation() {
        return this.messageLocation.getValue() != null;
    }

    public boolean hasCard() {
        return this.messageCard != null;
    }

    @Bindable
    @NonNull
    public String getTrackerId() {
        if ((ListenerUtil.mutListener.listen(129) ? (hasLocation() || getMessageLocation().getValue().hasTrackerId()) : (hasLocation() && getMessageLocation().getValue().hasTrackerId())))
            return getMessageLocation().getValue().getTrackerId();
        else {
            String id = getId().replace("/", "");
            if ((ListenerUtil.mutListener.listen(134) ? (id.length() >= 2) : (ListenerUtil.mutListener.listen(133) ? (id.length() <= 2) : (ListenerUtil.mutListener.listen(132) ? (id.length() < 2) : (ListenerUtil.mutListener.listen(131) ? (id.length() != 2) : (ListenerUtil.mutListener.listen(130) ? (id.length() == 2) : (id.length() > 2))))))) {
                return id.substring((ListenerUtil.mutListener.listen(138) ? (id.length() % 2) : (ListenerUtil.mutListener.listen(137) ? (id.length() / 2) : (ListenerUtil.mutListener.listen(136) ? (id.length() * 2) : (ListenerUtil.mutListener.listen(135) ? (id.length() + 2) : (id.length() - 2))))));
            } else
                return id;
        }
    }

    @Bindable
    @NonNull
    public String getId() {
        return id;
    }

    public LatLng getLatLng() {
        return new LatLng(this.messageLocation.getValue().getLatitude(), this.messageLocation.getValue().getLongitude());
    }

    private boolean deleted;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted() {
        if (!ListenerUtil.mutListener.listen(139)) {
            this.deleted = true;
        }
    }

    @Bindable
    public long getTst() {
        return tst;
    }

    @Override
    public int compareTo(@NonNull FusedContact o) {
        return Long.compare(o.tst, this.tst);
    }
}

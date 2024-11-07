package fr.free.nrw.commons.nearby;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.mapbox.mapboxsdk.annotations.BaseMarkerOptions;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import java.util.Objects;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NearbyBaseMarker extends BaseMarkerOptions<NearbyMarker, NearbyBaseMarker> {

    public static final Parcelable.Creator<NearbyBaseMarker> CREATOR = new Parcelable.Creator<NearbyBaseMarker>() {

        @Override
        public NearbyBaseMarker createFromParcel(Parcel in) {
            return new NearbyBaseMarker(in);
        }

        @Override
        public NearbyBaseMarker[] newArray(int size) {
            return new NearbyBaseMarker[size];
        }
    };

    private Place place;

    public NearbyBaseMarker() {
    }

    private NearbyBaseMarker(Parcel in) {
        if (!ListenerUtil.mutListener.listen(3682)) {
            position(in.readParcelable(LatLng.class.getClassLoader()));
        }
        if (!ListenerUtil.mutListener.listen(3683)) {
            snippet(in.readString());
        }
        String iconId = in.readString();
        Bitmap iconBitmap = in.readParcelable(Bitmap.class.getClassLoader());
        Icon icon = IconFactory.recreate(iconId, iconBitmap);
        if (!ListenerUtil.mutListener.listen(3684)) {
            icon(icon);
        }
        if (!ListenerUtil.mutListener.listen(3685)) {
            title(in.readString());
        }
        if (!ListenerUtil.mutListener.listen(3686)) {
            place(in.readParcelable(Place.class.getClassLoader()));
        }
    }

    public NearbyBaseMarker place(Place place) {
        if (!ListenerUtil.mutListener.listen(3687)) {
            this.place = place;
        }
        return this;
    }

    @Override
    public NearbyBaseMarker getThis() {
        return this;
    }

    @Override
    public NearbyMarker getMarker() {
        return new NearbyMarker(this, place);
    }

    public Place getPlace() {
        return place;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (!ListenerUtil.mutListener.listen(3688)) {
            dest.writeParcelable(position, flags);
        }
        if (!ListenerUtil.mutListener.listen(3689)) {
            dest.writeString(snippet);
        }
        if (!ListenerUtil.mutListener.listen(3690)) {
            dest.writeString(icon.getId());
        }
        if (!ListenerUtil.mutListener.listen(3691)) {
            dest.writeParcelable(icon.getBitmap(), flags);
        }
        if (!ListenerUtil.mutListener.listen(3692)) {
            dest.writeString(title);
        }
        if (!ListenerUtil.mutListener.listen(3693)) {
            dest.writeParcelable(place, 0);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (!ListenerUtil.mutListener.listen(3694)) {
            if (this == o) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(3696)) {
            if ((ListenerUtil.mutListener.listen(3695) ? (o == null && getClass() != o.getClass()) : (o == null || getClass() != o.getClass()))) {
                return false;
            }
        }
        final NearbyBaseMarker that = (NearbyBaseMarker) o;
        return Objects.equals(place.location, that.place.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place);
    }
}

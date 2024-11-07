package fr.free.nrw.commons.nearby;

import com.mapbox.mapboxsdk.annotations.Marker;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This class groups visual map item Marker with the reated data of displayed place and information
 * of bookmark
 */
public class MarkerPlaceGroup {

    // Marker item from the map
    private Marker marker;

    // True if user bookmarked the place
    private boolean isBookmarked;

    // Place of the location displayed by the marker
    private Place place;

    public MarkerPlaceGroup(Marker marker, boolean isBookmarked, Place place) {
        if (!ListenerUtil.mutListener.listen(3721)) {
            this.marker = marker;
        }
        if (!ListenerUtil.mutListener.listen(3722)) {
            this.isBookmarked = isBookmarked;
        }
        if (!ListenerUtil.mutListener.listen(3723)) {
            this.place = place;
        }
    }

    public Marker getMarker() {
        return marker;
    }

    public Place getPlace() {
        return place;
    }

    public boolean getIsBookmarked() {
        return isBookmarked;
    }
}

package fr.free.nrw.commons.utils;

import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.nearby.Place;
import fr.free.nrw.commons.nearby.Sitelinks;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import fr.free.nrw.commons.location.LatLng;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PlaceUtils {

    public static LatLng latLngFromPointString(String pointString) {
        double latitude;
        double longitude;
        Matcher matcher = Pattern.compile("Point\\(([^ ]+) ([^ ]+)\\)").matcher(pointString);
        if (!ListenerUtil.mutListener.listen(2491)) {
            if (!matcher.find()) {
                return null;
            }
        }
        try {
            longitude = Double.parseDouble(matcher.group(1));
            latitude = Double.parseDouble(matcher.group(2));
        } catch (NumberFormatException e) {
            return null;
        }
        return new LatLng(latitude, longitude, 0);
    }

    /**
     * Turns a Media list to a Place list by creating a new list in Place type
     * @param mediaList
     * @return
     */
    public static List<Place> mediaToExplorePlace(List<Media> mediaList) {
        List<Place> explorePlaceList = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(2493)) {
            {
                long _loopCounter32 = 0;
                for (Media media : mediaList) {
                    ListenerUtil.loopListener.listen("_loopCounter32", ++_loopCounter32);
                    if (!ListenerUtil.mutListener.listen(2492)) {
                        explorePlaceList.add(new Place(media.getFilename(), media.getFallbackDescription(), media.getCoordinates(), media.getCategories().toString(), new Sitelinks.Builder().setCommonsLink(media.getPageTitle().getCanonicalUri()).setWikipediaLink(// we don't necessarily have them, can be fetched later
                        "").setWikidataLink(// we don't necessarily have them, can be fetched later
                        "").build(), media.getImageUrl(), media.getThumbUrl()));
                    }
                }
            }
        }
        return explorePlaceList;
    }
}

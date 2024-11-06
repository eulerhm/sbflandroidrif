package fr.free.nrw.commons.nearby;

import androidx.annotation.Nullable;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import fr.free.nrw.commons.location.LatLng;
import fr.free.nrw.commons.mwapi.OkHttpJsonApiClient;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Handles the Wikidata query to obtain Places around search location
 */
@Singleton
public class NearbyPlaces {

    // in kilometers
    private static final double INITIAL_RADIUS = 0.3;

    private static final double RADIUS_MULTIPLIER = 2.0;

    public double radius = INITIAL_RADIUS;

    private final OkHttpJsonApiClient okHttpJsonApiClient;

    /**
     * Reads Wikidata query to check nearby wikidata items which needs picture, with a circular
     * search. As a point is center of a circle with a radius will be set later.
     * @param okHttpJsonApiClient
     */
    @Inject
    public NearbyPlaces(OkHttpJsonApiClient okHttpJsonApiClient) {
        this.okHttpJsonApiClient = okHttpJsonApiClient;
    }

    /**
     * Expands the radius as needed for the Wikidata query
     * @param curLatLng coordinates of search location
     * @param lang user's language
     * @param returnClosestResult true if only the nearest point is desired
     * @param customQuery
     * @return list of places obtained
     */
    List<Place> radiusExpander(final LatLng curLatLng, final String lang, final boolean returnClosestResult, final boolean shouldQueryForMonuments, @Nullable final String customQuery) throws Exception {
        final int minResults;
        final double maxRadius;
        List<Place> places = Collections.emptyList();
        // to use in cardView in Contributions fragment
        if (returnClosestResult) {
            // Return closest nearby place
            minResults = 1;
            // Return places only in 5 km area
            maxRadius = 5;
            if (!ListenerUtil.mutListener.listen(3698)) {
                // refresh radius again, otherwise increased radius is grater than MAX_RADIUS, thus returns null
                radius = INITIAL_RADIUS;
            }
        } else {
            minResults = 20;
            // in kilometers
            maxRadius = 300.0;
            if (!ListenerUtil.mutListener.listen(3697)) {
                radius = INITIAL_RADIUS;
            }
        }
        if (!ListenerUtil.mutListener.listen(3713)) {
            {
                long _loopCounter53 = 0;
                // Increase the radius gradually to find a satisfactory number of nearby places
                while ((ListenerUtil.mutListener.listen(3712) ? (radius >= maxRadius) : (ListenerUtil.mutListener.listen(3711) ? (radius > maxRadius) : (ListenerUtil.mutListener.listen(3710) ? (radius < maxRadius) : (ListenerUtil.mutListener.listen(3709) ? (radius != maxRadius) : (ListenerUtil.mutListener.listen(3708) ? (radius == maxRadius) : (radius <= maxRadius))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter53", ++_loopCounter53);
                    if (!ListenerUtil.mutListener.listen(3699)) {
                        places = getFromWikidataQuery(curLatLng, lang, radius, shouldQueryForMonuments, customQuery);
                    }
                    if (!ListenerUtil.mutListener.listen(3700)) {
                        Timber.d("%d results at radius: %f", places.size(), radius);
                    }
                    if (!ListenerUtil.mutListener.listen(3706)) {
                        if ((ListenerUtil.mutListener.listen(3705) ? (places.size() <= minResults) : (ListenerUtil.mutListener.listen(3704) ? (places.size() > minResults) : (ListenerUtil.mutListener.listen(3703) ? (places.size() < minResults) : (ListenerUtil.mutListener.listen(3702) ? (places.size() != minResults) : (ListenerUtil.mutListener.listen(3701) ? (places.size() == minResults) : (places.size() >= minResults))))))) {
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3707)) {
                        radius *= RADIUS_MULTIPLIER;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3720)) {
            // make sure we will be able to send at least one request next time
            if ((ListenerUtil.mutListener.listen(3718) ? (radius >= maxRadius) : (ListenerUtil.mutListener.listen(3717) ? (radius <= maxRadius) : (ListenerUtil.mutListener.listen(3716) ? (radius < maxRadius) : (ListenerUtil.mutListener.listen(3715) ? (radius != maxRadius) : (ListenerUtil.mutListener.listen(3714) ? (radius == maxRadius) : (radius > maxRadius))))))) {
                if (!ListenerUtil.mutListener.listen(3719)) {
                    radius = maxRadius;
                }
            }
        }
        return places;
    }

    /**
     * Runs the Wikidata query to populate the Places around search location
     * @param cur coordinates of search location
     * @param lang user's language
     * @param radius radius for search, as determined by radiusExpander()
     * @param shouldQueryForMonuments should the query include properites for monuments
     * @param customQuery
     * @return list of places obtained
     * @throws IOException if query fails
     */
    public List<Place> getFromWikidataQuery(final LatLng cur, final String lang, final double radius, final boolean shouldQueryForMonuments, @Nullable final String customQuery) throws Exception {
        return okHttpJsonApiClient.getNearbyPlaces(cur, lang, radius, shouldQueryForMonuments, customQuery);
    }
}

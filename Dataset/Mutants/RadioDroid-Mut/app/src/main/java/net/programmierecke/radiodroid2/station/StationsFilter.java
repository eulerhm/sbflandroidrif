package net.programmierecke.radiodroid2.station;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import net.programmierecke.radiodroid2.RadioDroidApp;
import net.programmierecke.radiodroid2.Utils;
import net.programmierecke.radiodroid2.utils.CustomFilter;
import org.jetbrains.annotations.NotNull;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import okhttp3.OkHttpClient;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class StationsFilter extends CustomFilter {

    public enum FilterType {

        /**
         * Will perform search only in initially set list
         */
        LOCAL,
        /**
         * Doesn't care about what is already in the list will search in ALL stations
         */
        GLOBAL
    }

    public enum SearchStatus {

        SUCCESS, ERROR
    }

    public enum SearchStyle {

        ByName, ByLanguageExact, ByCountryCodeExact, ByTagExact
    }

    public interface DataProvider {

        List<DataRadioStation> getOriginalStationList();

        void notifyFilteredStationsChanged(SearchStatus status, List<DataRadioStation> filteredStations);
    }

    private final String TAG = "StationsFilter";

    private final int FUZZY_SEARCH_THRESHOLD = 55;

    private FilterType filterType;

    private Context context;

    private DataProvider dataProvider;

    private String lastRemoteQuery = "";

    private List<DataRadioStation> filteredStationsList;

    private SearchStatus lastRemoteSearchStatus = SearchStatus.SUCCESS;

    private SearchStyle searchStyle = SearchStyle.ByName;

    private class WeightedStation {

        DataRadioStation station;

        int weight;

        WeightedStation(DataRadioStation station, int weight) {
            if (!ListenerUtil.mutListener.listen(3180)) {
                this.station = station;
            }
            if (!ListenerUtil.mutListener.listen(3181)) {
                this.weight = weight;
            }
        }
    }

    public StationsFilter(@NonNull Context context, FilterType filterType, @NonNull DataProvider dataProvider) {
        if (!ListenerUtil.mutListener.listen(3182)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(3183)) {
            this.filterType = filterType;
        }
        if (!ListenerUtil.mutListener.listen(3184)) {
            this.dataProvider = dataProvider;
        }
    }

    public void setSearchStyle(SearchStyle searchStyle) {
        if (!ListenerUtil.mutListener.listen(3185)) {
            Log.d("FILTER", "Changed search style:" + searchStyle);
        }
        if (!ListenerUtil.mutListener.listen(3186)) {
            this.searchStyle = searchStyle;
        }
    }

    @NonNull
    private List<DataRadioStation> searchGlobal(@NotNull final String query) {
        if (!ListenerUtil.mutListener.listen(3187)) {
            Log.d("FILTER", "searchGlobal 1:" + query);
        }
        RadioDroidApp radioDroidApp = (RadioDroidApp) context.getApplicationContext();
        // TODO: use http client with custom timeouts
        OkHttpClient httpClient = radioDroidApp.getHttpClient();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.context);
        final boolean show_broken = sharedPref.getBoolean("show_broken", false);
        HashMap<String, String> p = new HashMap<String, String>();
        if (!ListenerUtil.mutListener.listen(3188)) {
            p.put("order", "clickcount");
        }
        if (!ListenerUtil.mutListener.listen(3189)) {
            p.put("reverse", "true");
        }
        if (!ListenerUtil.mutListener.listen(3190)) {
            p.put("hidebroken", "" + (!show_broken));
        }
        try {
            String queryEncoded = URLEncoder.encode(query, "utf-8");
            if (!ListenerUtil.mutListener.listen(3193)) {
                queryEncoded = queryEncoded.replace("+", "%20");
            }
            String searchUrl = null;
            switch(searchStyle) {
                case ByName:
                    if (!ListenerUtil.mutListener.listen(3194)) {
                        searchUrl = "json/stations/byname/" + queryEncoded;
                    }
                    break;
                case ByCountryCodeExact:
                    if (!ListenerUtil.mutListener.listen(3195)) {
                        searchUrl = "json/stations/bycountrycodeexact/" + queryEncoded;
                    }
                    break;
                case ByLanguageExact:
                    if (!ListenerUtil.mutListener.listen(3196)) {
                        searchUrl = "json/stations/bylanguageexact/" + queryEncoded;
                    }
                    break;
                case ByTagExact:
                    if (!ListenerUtil.mutListener.listen(3197)) {
                        searchUrl = "json/stations/bytagexact/" + queryEncoded;
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(3198)) {
                        Log.d("FILTER", "unknown search style: " + searchStyle);
                    }
                    if (!ListenerUtil.mutListener.listen(3199)) {
                        lastRemoteSearchStatus = SearchStatus.ERROR;
                    }
                    return new ArrayList<>();
            }
            if (!ListenerUtil.mutListener.listen(3200)) {
                Log.d("FILTER", "searchGlobal 2:" + query);
            }
            String resultString = Utils.downloadFeedRelative(httpClient, radioDroidApp, searchUrl, false, p);
            if (resultString != null) {
                if (!ListenerUtil.mutListener.listen(3203)) {
                    Log.d("FILTER", "searchGlobal 3a:" + query);
                }
                List<DataRadioStation> result = DataRadioStation.DecodeJson(resultString);
                if (!ListenerUtil.mutListener.listen(3204)) {
                    lastRemoteSearchStatus = SearchStatus.SUCCESS;
                }
                return result;
            } else {
                if (!ListenerUtil.mutListener.listen(3201)) {
                    Log.d("FILTER", "searchGlobal 3b:" + query);
                }
                if (!ListenerUtil.mutListener.listen(3202)) {
                    lastRemoteSearchStatus = SearchStatus.ERROR;
                }
                return new ArrayList<>();
            }
        } catch (UnsupportedEncodingException e) {
            if (!ListenerUtil.mutListener.listen(3191)) {
                e.printStackTrace();
            }
            if (!ListenerUtil.mutListener.listen(3192)) {
                lastRemoteSearchStatus = SearchStatus.ERROR;
            }
            return new ArrayList<>();
        }
    }

    public void clearList() {
        if (!ListenerUtil.mutListener.listen(3205)) {
            Log.d("FILTER", "forced refetch");
        }
        if (!ListenerUtil.mutListener.listen(3206)) {
            lastRemoteQuery = "";
        }
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        final String query = constraint.toString().toLowerCase();
        if (!ListenerUtil.mutListener.listen(3207)) {
            Log.d("FILTER", "performFiltering() " + query);
        }
        if (!ListenerUtil.mutListener.listen(3248)) {
            if ((ListenerUtil.mutListener.listen(3215) ? (searchStyle == SearchStyle.ByName || ((ListenerUtil.mutListener.listen(3214) ? (query.isEmpty() && ((ListenerUtil.mutListener.listen(3213) ? ((ListenerUtil.mutListener.listen(3212) ? (query.length() >= 3) : (ListenerUtil.mutListener.listen(3211) ? (query.length() <= 3) : (ListenerUtil.mutListener.listen(3210) ? (query.length() > 3) : (ListenerUtil.mutListener.listen(3209) ? (query.length() != 3) : (ListenerUtil.mutListener.listen(3208) ? (query.length() == 3) : (query.length() < 3)))))) || filterType == FilterType.GLOBAL) : ((ListenerUtil.mutListener.listen(3212) ? (query.length() >= 3) : (ListenerUtil.mutListener.listen(3211) ? (query.length() <= 3) : (ListenerUtil.mutListener.listen(3210) ? (query.length() > 3) : (ListenerUtil.mutListener.listen(3209) ? (query.length() != 3) : (ListenerUtil.mutListener.listen(3208) ? (query.length() == 3) : (query.length() < 3)))))) && filterType == FilterType.GLOBAL)))) : (query.isEmpty() || ((ListenerUtil.mutListener.listen(3213) ? ((ListenerUtil.mutListener.listen(3212) ? (query.length() >= 3) : (ListenerUtil.mutListener.listen(3211) ? (query.length() <= 3) : (ListenerUtil.mutListener.listen(3210) ? (query.length() > 3) : (ListenerUtil.mutListener.listen(3209) ? (query.length() != 3) : (ListenerUtil.mutListener.listen(3208) ? (query.length() == 3) : (query.length() < 3)))))) || filterType == FilterType.GLOBAL) : ((ListenerUtil.mutListener.listen(3212) ? (query.length() >= 3) : (ListenerUtil.mutListener.listen(3211) ? (query.length() <= 3) : (ListenerUtil.mutListener.listen(3210) ? (query.length() > 3) : (ListenerUtil.mutListener.listen(3209) ? (query.length() != 3) : (ListenerUtil.mutListener.listen(3208) ? (query.length() == 3) : (query.length() < 3)))))) && filterType == FilterType.GLOBAL))))))) : (searchStyle == SearchStyle.ByName && ((ListenerUtil.mutListener.listen(3214) ? (query.isEmpty() && ((ListenerUtil.mutListener.listen(3213) ? ((ListenerUtil.mutListener.listen(3212) ? (query.length() >= 3) : (ListenerUtil.mutListener.listen(3211) ? (query.length() <= 3) : (ListenerUtil.mutListener.listen(3210) ? (query.length() > 3) : (ListenerUtil.mutListener.listen(3209) ? (query.length() != 3) : (ListenerUtil.mutListener.listen(3208) ? (query.length() == 3) : (query.length() < 3)))))) || filterType == FilterType.GLOBAL) : ((ListenerUtil.mutListener.listen(3212) ? (query.length() >= 3) : (ListenerUtil.mutListener.listen(3211) ? (query.length() <= 3) : (ListenerUtil.mutListener.listen(3210) ? (query.length() > 3) : (ListenerUtil.mutListener.listen(3209) ? (query.length() != 3) : (ListenerUtil.mutListener.listen(3208) ? (query.length() == 3) : (query.length() < 3)))))) && filterType == FilterType.GLOBAL)))) : (query.isEmpty() || ((ListenerUtil.mutListener.listen(3213) ? ((ListenerUtil.mutListener.listen(3212) ? (query.length() >= 3) : (ListenerUtil.mutListener.listen(3211) ? (query.length() <= 3) : (ListenerUtil.mutListener.listen(3210) ? (query.length() > 3) : (ListenerUtil.mutListener.listen(3209) ? (query.length() != 3) : (ListenerUtil.mutListener.listen(3208) ? (query.length() == 3) : (query.length() < 3)))))) || filterType == FilterType.GLOBAL) : ((ListenerUtil.mutListener.listen(3212) ? (query.length() >= 3) : (ListenerUtil.mutListener.listen(3211) ? (query.length() <= 3) : (ListenerUtil.mutListener.listen(3210) ? (query.length() > 3) : (ListenerUtil.mutListener.listen(3209) ? (query.length() != 3) : (ListenerUtil.mutListener.listen(3208) ? (query.length() == 3) : (query.length() < 3)))))) && filterType == FilterType.GLOBAL))))))))) {
                if (!ListenerUtil.mutListener.listen(3245)) {
                    Log.d("FILTER", "performFiltering() 2 " + query);
                }
                if (!ListenerUtil.mutListener.listen(3246)) {
                    filteredStationsList = dataProvider.getOriginalStationList();
                }
                if (!ListenerUtil.mutListener.listen(3247)) {
                    lastRemoteQuery = "";
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3216)) {
                    Log.d("FILTER", "performFiltering() 3 " + query);
                }
                List<DataRadioStation> stationsToFilter;
                boolean needsFiltering = false;
                if ((ListenerUtil.mutListener.listen(3218) ? ((ListenerUtil.mutListener.listen(3217) ? (!lastRemoteQuery.isEmpty() || query.startsWith(lastRemoteQuery)) : (!lastRemoteQuery.isEmpty() && query.startsWith(lastRemoteQuery))) || lastRemoteSearchStatus != SearchStatus.ERROR) : ((ListenerUtil.mutListener.listen(3217) ? (!lastRemoteQuery.isEmpty() || query.startsWith(lastRemoteQuery)) : (!lastRemoteQuery.isEmpty() && query.startsWith(lastRemoteQuery))) && lastRemoteSearchStatus != SearchStatus.ERROR))) {
                    if (!ListenerUtil.mutListener.listen(3223)) {
                        Log.d("FILTER", "performFiltering() 3a " + query + " lastRemoteQuery=" + lastRemoteQuery);
                    }
                    // We can filter already existing list without making costly http call.
                    stationsToFilter = filteredStationsList;
                    if (!ListenerUtil.mutListener.listen(3224)) {
                        needsFiltering = true;
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(3219)) {
                        Log.d("FILTER", "performFiltering() 3b " + query);
                    }
                    switch(filterType) {
                        case LOCAL:
                            stationsToFilter = dataProvider.getOriginalStationList();
                            if (!ListenerUtil.mutListener.listen(3220)) {
                                needsFiltering = true;
                            }
                            break;
                        case GLOBAL:
                            stationsToFilter = searchGlobal(query);
                            if (!ListenerUtil.mutListener.listen(3221)) {
                                needsFiltering = false;
                            }
                            if (!ListenerUtil.mutListener.listen(3222)) {
                                lastRemoteQuery = query;
                            }
                            break;
                        default:
                            throw new RuntimeException("performFiltering: Unknown filterType!");
                    }
                }
                if (!ListenerUtil.mutListener.listen(3244)) {
                    if (needsFiltering) {
                        if (!ListenerUtil.mutListener.listen(3227)) {
                            Log.d("FILTER", "performFiltering() 4a " + query);
                        }
                        ArrayList<WeightedStation> filteredStations = new ArrayList<>();
                        if (!ListenerUtil.mutListener.listen(3239)) {
                            {
                                long _loopCounter43 = 0;
                                for (DataRadioStation station : stationsToFilter) {
                                    ListenerUtil.loopListener.listen("_loopCounter43", ++_loopCounter43);
                                    int weight = FuzzySearch.partialRatio(query, station.Name.toLowerCase());
                                    if (!ListenerUtil.mutListener.listen(3238)) {
                                        if ((ListenerUtil.mutListener.listen(3232) ? (weight >= FUZZY_SEARCH_THRESHOLD) : (ListenerUtil.mutListener.listen(3231) ? (weight <= FUZZY_SEARCH_THRESHOLD) : (ListenerUtil.mutListener.listen(3230) ? (weight < FUZZY_SEARCH_THRESHOLD) : (ListenerUtil.mutListener.listen(3229) ? (weight != FUZZY_SEARCH_THRESHOLD) : (ListenerUtil.mutListener.listen(3228) ? (weight == FUZZY_SEARCH_THRESHOLD) : (weight > FUZZY_SEARCH_THRESHOLD))))))) {
                                            // We will sort stations with similar weight by other metric
                                            int compressedWeight = (ListenerUtil.mutListener.listen(3236) ? (weight % 4) : (ListenerUtil.mutListener.listen(3235) ? (weight * 4) : (ListenerUtil.mutListener.listen(3234) ? (weight - 4) : (ListenerUtil.mutListener.listen(3233) ? (weight + 4) : (weight / 4)))));
                                            if (!ListenerUtil.mutListener.listen(3237)) {
                                                filteredStations.add(new WeightedStation(station, compressedWeight));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(3240)) {
                            Collections.sort(filteredStations, (x, y) -> {
                                if (x.weight == y.weight) {
                                    return -Integer.compare(x.station.ClickCount, y.station.ClickCount);
                                }
                                return -Integer.compare(x.weight, y.weight);
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(3241)) {
                            filteredStationsList = new ArrayList<>();
                        }
                        if (!ListenerUtil.mutListener.listen(3243)) {
                            {
                                long _loopCounter44 = 0;
                                for (WeightedStation weightedStation : filteredStations) {
                                    ListenerUtil.loopListener.listen("_loopCounter44", ++_loopCounter44);
                                    if (!ListenerUtil.mutListener.listen(3242)) {
                                        filteredStationsList.add(weightedStation.station);
                                    }
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3225)) {
                            Log.d("FILTER", "performFiltering() 4b " + query);
                        }
                        if (!ListenerUtil.mutListener.listen(3226)) {
                            filteredStationsList = stationsToFilter;
                        }
                    }
                }
            }
        }
        FilterResults filterResults = new FilterResults();
        if (!ListenerUtil.mutListener.listen(3249)) {
            filterResults.values = filteredStationsList;
        }
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        if (!ListenerUtil.mutListener.listen(3250)) {
            dataProvider.notifyFilteredStationsChanged(lastRemoteSearchStatus, (List<DataRadioStation>) results.values);
        }
    }
}

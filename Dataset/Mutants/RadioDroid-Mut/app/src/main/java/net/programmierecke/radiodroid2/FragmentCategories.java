package net.programmierecke.radiodroid2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import net.programmierecke.radiodroid2.adapters.ItemAdapterCategory;
import net.programmierecke.radiodroid2.data.DataCategory;
import net.programmierecke.radiodroid2.station.StationsFilter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FragmentCategories extends FragmentBase {

    private static final String TAG = "FragmentCategories";

    private RecyclerView rvCategories;

    private StationsFilter.SearchStyle searchStyle = StationsFilter.SearchStyle.ByName;

    private SwipeRefreshLayout swipeRefreshLayout;

    private boolean singleUseFilter = false;

    private SharedPreferences sharedPref;

    public FragmentCategories() {
    }

    public void SetBaseSearchLink(StationsFilter.SearchStyle searchStyle) {
        if (!ListenerUtil.mutListener.listen(4285)) {
            this.searchStyle = searchStyle;
        }
    }

    void ClickOnItem(DataCategory theData) {
        ActivityMain m = (ActivityMain) getActivity();
        if (!ListenerUtil.mutListener.listen(4286)) {
            m.Search(this.searchStyle, theData.Name);
        }
    }

    @Override
    protected void RefreshListGui() {
        if (!ListenerUtil.mutListener.listen(4287)) {
            if (rvCategories == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4289)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(4288)) {
                    Log.d(TAG, "refreshing the categories list.");
                }
        }
        Context ctx = getContext();
        if (!ListenerUtil.mutListener.listen(4291)) {
            if (sharedPref == null) {
                if (!ListenerUtil.mutListener.listen(4290)) {
                    sharedPref = PreferenceManager.getDefaultSharedPreferences(ctx);
                }
            }
        }
        boolean show_single_use_tags = sharedPref.getBoolean("single_use_tags", false);
        ArrayList<DataCategory> filteredCategoriesList = new ArrayList<>();
        DataCategory[] data = DataCategory.DecodeJson(getUrlResult());
        if (!ListenerUtil.mutListener.listen(4293)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(4292)) {
                    Log.d(TAG, "categories count:" + data.length);
                }
        }
        CountryCodeDictionary countryDict = CountryCodeDictionary.getInstance();
        CountryFlagsLoader flagsDict = CountryFlagsLoader.getInstance();
        if (!ListenerUtil.mutListener.listen(4306)) {
            {
                long _loopCounter51 = 0;
                for (DataCategory aData : data) {
                    ListenerUtil.loopListener.listen("_loopCounter51", ++_loopCounter51);
                    if (!ListenerUtil.mutListener.listen(4305)) {
                        if ((ListenerUtil.mutListener.listen(4300) ? ((ListenerUtil.mutListener.listen(4294) ? (!singleUseFilter && show_single_use_tags) : (!singleUseFilter || show_single_use_tags)) && ((ListenerUtil.mutListener.listen(4299) ? (aData.UsedCount >= 1) : (ListenerUtil.mutListener.listen(4298) ? (aData.UsedCount <= 1) : (ListenerUtil.mutListener.listen(4297) ? (aData.UsedCount < 1) : (ListenerUtil.mutListener.listen(4296) ? (aData.UsedCount != 1) : (ListenerUtil.mutListener.listen(4295) ? (aData.UsedCount == 1) : (aData.UsedCount > 1)))))))) : ((ListenerUtil.mutListener.listen(4294) ? (!singleUseFilter && show_single_use_tags) : (!singleUseFilter || show_single_use_tags)) || ((ListenerUtil.mutListener.listen(4299) ? (aData.UsedCount >= 1) : (ListenerUtil.mutListener.listen(4298) ? (aData.UsedCount <= 1) : (ListenerUtil.mutListener.listen(4297) ? (aData.UsedCount < 1) : (ListenerUtil.mutListener.listen(4296) ? (aData.UsedCount != 1) : (ListenerUtil.mutListener.listen(4295) ? (aData.UsedCount == 1) : (aData.UsedCount > 1)))))))))) {
                            if (!ListenerUtil.mutListener.listen(4303)) {
                                if (searchStyle == StationsFilter.SearchStyle.ByCountryCodeExact) {
                                    if (!ListenerUtil.mutListener.listen(4301)) {
                                        aData.Label = countryDict.getCountryByCode(aData.Name);
                                    }
                                    if (!ListenerUtil.mutListener.listen(4302)) {
                                        aData.Icon = flagsDict.getFlag(requireContext(), aData.Name);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(4304)) {
                                filteredCategoriesList.add(aData);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4308)) {
            if (searchStyle == StationsFilter.SearchStyle.ByCountryCodeExact) {
                if (!ListenerUtil.mutListener.listen(4307)) {
                    Collections.sort(filteredCategoriesList);
                }
            }
        }
        ItemAdapterCategory adapter = (ItemAdapterCategory) rvCategories.getAdapter();
        if (!ListenerUtil.mutListener.listen(4309)) {
            adapter.updateList(filteredCategoriesList);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ItemAdapterCategory adapterCategory = new ItemAdapterCategory(R.layout.list_item_category);
        if (!ListenerUtil.mutListener.listen(4311)) {
            adapterCategory.setCategoryClickListener(new ItemAdapterCategory.CategoryClickListener() {

                @Override
                public void onCategoryClick(DataCategory category) {
                    if (!ListenerUtil.mutListener.listen(4310)) {
                        ClickOnItem(category);
                    }
                }
            });
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stations_remote, container, false);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        if (!ListenerUtil.mutListener.listen(4312)) {
            llm.setOrientation(LinearLayoutManager.VERTICAL);
        }
        if (!ListenerUtil.mutListener.listen(4313)) {
            rvCategories = (RecyclerView) view.findViewById(R.id.recyclerViewStations);
        }
        if (!ListenerUtil.mutListener.listen(4314)) {
            rvCategories.setAdapter(adapterCategory);
        }
        if (!ListenerUtil.mutListener.listen(4315)) {
            rvCategories.setLayoutManager(llm);
        }
        if (!ListenerUtil.mutListener.listen(4316)) {
            swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        }
        if (!ListenerUtil.mutListener.listen(4321)) {
            if (swipeRefreshLayout != null) {
                if (!ListenerUtil.mutListener.listen(4320)) {
                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

                        @Override
                        public void onRefresh() {
                            if (!ListenerUtil.mutListener.listen(4318)) {
                                if (BuildConfig.DEBUG) {
                                    if (!ListenerUtil.mutListener.listen(4317)) {
                                        Log.d(TAG, "onRefresh called from SwipeRefreshLayout");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(4319)) {
                                // RefreshListGui();
                                DownloadUrl(true, false);
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4322)) {
            RefreshListGui();
        }
        return view;
    }

    public void EnableSingleUseFilter(boolean b) {
        if (!ListenerUtil.mutListener.listen(4323)) {
            this.singleUseFilter = b;
        }
    }

    @Override
    protected void DownloadFinished() {
        if (!ListenerUtil.mutListener.listen(4325)) {
            if (swipeRefreshLayout != null) {
                if (!ListenerUtil.mutListener.listen(4324)) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        }
    }
}

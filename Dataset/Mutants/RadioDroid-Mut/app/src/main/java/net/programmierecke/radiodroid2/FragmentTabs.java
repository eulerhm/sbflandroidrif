package net.programmierecke.radiodroid2;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import net.programmierecke.radiodroid2.interfaces.IFragmentRefreshable;
import net.programmierecke.radiodroid2.interfaces.IFragmentSearchable;
import net.programmierecke.radiodroid2.station.FragmentStations;
import net.programmierecke.radiodroid2.station.StationsFilter;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FragmentTabs extends Fragment implements IFragmentRefreshable, IFragmentSearchable {

    private String itsAdressWWWLocal = "json/stations/bycountryexact/internet?order=clickcount&reverse=true";

    private String itsAdressWWWTopClick = "json/stations/topclick/100";

    private String itsAdressWWWTopVote = "json/stations/topvote/100";

    private String itsAdressWWWChangedLately = "json/stations/lastchange/100";

    private String itsAdressWWWCurrentlyHeard = "json/stations/lastclick/100";

    private String itsAdressWWWTags = "json/tags";

    private String itsAdressWWWCountries = "json/countrycodes";

    private String itsAdressWWWLanguages = "json/languages";

    // further down when populating the ViewPagerAdapter
    private static final int IDX_LOCAL = 0;

    private static final int IDX_TOP_CLICK = 1;

    private static final int IDX_TOP_VOTE = 2;

    private static final int IDX_CHANGED_LATELY = 3;

    private static final int IDX_CURRENTLY_HEARD = 4;

    private static final int IDX_TAGS = 5;

    private static final int IDX_COUNTRIES = 6;

    private static final int IDX_LANGUAGES = 7;

    private static final int IDX_SEARCH = 8;

    public static ViewPager viewPager;

    // Search may be requested before onCreateView so we should wait
    private String queuedSearchQuery;

    private StationsFilter.SearchStyle queuedSearchStyle;

    private Fragment[] fragments = new Fragment[9];

    private String[] addresses = new String[] { itsAdressWWWLocal, itsAdressWWWTopClick, itsAdressWWWTopVote, itsAdressWWWChangedLately, itsAdressWWWCurrentlyHeard, itsAdressWWWTags, itsAdressWWWCountries, itsAdressWWWLanguages, "" };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View x = inflater.inflate(R.layout.layout_tabs, null);
        final TabLayout tabLayout = getActivity().findViewById(R.id.tabs);
        if (!ListenerUtil.mutListener.listen(4873)) {
            viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        }
        if (!ListenerUtil.mutListener.listen(4874)) {
            setupViewPager(viewPager);
        }
        if (!ListenerUtil.mutListener.listen(4879)) {
            if (queuedSearchQuery != null) {
                if (!ListenerUtil.mutListener.listen(4875)) {
                    Log.d("TABS", "do queued search by name:" + queuedSearchQuery);
                }
                if (!ListenerUtil.mutListener.listen(4876)) {
                    Search(queuedSearchStyle, queuedSearchQuery);
                }
                if (!ListenerUtil.mutListener.listen(4877)) {
                    queuedSearchQuery = null;
                }
                if (!ListenerUtil.mutListener.listen(4878)) {
                    queuedSearchStyle = StationsFilter.SearchStyle.ByName;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4882)) {
            tabLayout.post(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(4881)) {
                        if (getContext() != null)
                            if (!ListenerUtil.mutListener.listen(4880)) {
                                tabLayout.setupWithViewPager(viewPager);
                            }
                    }
                }
            });
        }
        return x;
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(4883)) {
            super.onResume();
        }
        final TabLayout tabLayout = getActivity().findViewById(R.id.tabs);
        if (!ListenerUtil.mutListener.listen(4884)) {
            tabLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(4885)) {
            super.onPause();
        }
        final TabLayout tabLayout = getActivity().findViewById(R.id.tabs);
        if (!ListenerUtil.mutListener.listen(4886)) {
            tabLayout.setVisibility(View.GONE);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        Context ctx = getContext();
        String countryCode = null;
        if (!ListenerUtil.mutListener.listen(4901)) {
            if (ctx != null) {
                TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
                if (!ListenerUtil.mutListener.listen(4887)) {
                    countryCode = tm.getNetworkCountryIso();
                }
                if (!ListenerUtil.mutListener.listen(4889)) {
                    if (countryCode == null) {
                        if (!ListenerUtil.mutListener.listen(4888)) {
                            countryCode = tm.getSimCountryIso();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4900)) {
                    if (countryCode != null) {
                        if (!ListenerUtil.mutListener.listen(4899)) {
                            if ((ListenerUtil.mutListener.listen(4895) ? (countryCode.length() >= 2) : (ListenerUtil.mutListener.listen(4894) ? (countryCode.length() <= 2) : (ListenerUtil.mutListener.listen(4893) ? (countryCode.length() > 2) : (ListenerUtil.mutListener.listen(4892) ? (countryCode.length() < 2) : (ListenerUtil.mutListener.listen(4891) ? (countryCode.length() != 2) : (countryCode.length() == 2))))))) {
                                if (!ListenerUtil.mutListener.listen(4897)) {
                                    Log.d("MAIN", "Found countrycode " + countryCode);
                                }
                                if (!ListenerUtil.mutListener.listen(4898)) {
                                    addresses[IDX_LOCAL] = "json/stations/bycountrycodeexact/" + countryCode + "?order=clickcount&reverse=true";
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(4896)) {
                                    Log.e("MAIN", "countrycode length != 2");
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4890)) {
                            Log.e("MAIN", "device countrycode and sim countrycode are null");
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4902)) {
            fragments[IDX_LOCAL] = new FragmentStations();
        }
        if (!ListenerUtil.mutListener.listen(4903)) {
            fragments[IDX_TOP_CLICK] = new FragmentStations();
        }
        if (!ListenerUtil.mutListener.listen(4904)) {
            fragments[IDX_TOP_VOTE] = new FragmentStations();
        }
        if (!ListenerUtil.mutListener.listen(4905)) {
            fragments[IDX_CHANGED_LATELY] = new FragmentStations();
        }
        if (!ListenerUtil.mutListener.listen(4906)) {
            fragments[IDX_CURRENTLY_HEARD] = new FragmentStations();
        }
        if (!ListenerUtil.mutListener.listen(4907)) {
            fragments[IDX_TAGS] = new FragmentCategories();
        }
        if (!ListenerUtil.mutListener.listen(4908)) {
            fragments[IDX_COUNTRIES] = new FragmentCategories();
        }
        if (!ListenerUtil.mutListener.listen(4909)) {
            fragments[IDX_LANGUAGES] = new FragmentCategories();
        }
        if (!ListenerUtil.mutListener.listen(4910)) {
            fragments[IDX_SEARCH] = new FragmentStations();
        }
        if (!ListenerUtil.mutListener.listen(4925)) {
            {
                long _loopCounter59 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(4924) ? (i >= fragments.length) : (ListenerUtil.mutListener.listen(4923) ? (i <= fragments.length) : (ListenerUtil.mutListener.listen(4922) ? (i > fragments.length) : (ListenerUtil.mutListener.listen(4921) ? (i != fragments.length) : (ListenerUtil.mutListener.listen(4920) ? (i == fragments.length) : (i < fragments.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter59", ++_loopCounter59);
                    Bundle bundle = new Bundle();
                    if (!ListenerUtil.mutListener.listen(4911)) {
                        bundle.putString("url", addresses[i]);
                    }
                    if (!ListenerUtil.mutListener.listen(4918)) {
                        if ((ListenerUtil.mutListener.listen(4916) ? (i >= IDX_SEARCH) : (ListenerUtil.mutListener.listen(4915) ? (i <= IDX_SEARCH) : (ListenerUtil.mutListener.listen(4914) ? (i > IDX_SEARCH) : (ListenerUtil.mutListener.listen(4913) ? (i < IDX_SEARCH) : (ListenerUtil.mutListener.listen(4912) ? (i != IDX_SEARCH) : (i == IDX_SEARCH))))))) {
                            if (!ListenerUtil.mutListener.listen(4917)) {
                                bundle.putBoolean(FragmentStations.KEY_SEARCH_ENABLED, true);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4919)) {
                        fragments[i].setArguments(bundle);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4926)) {
            ((FragmentCategories) fragments[IDX_TAGS]).EnableSingleUseFilter(true);
        }
        if (!ListenerUtil.mutListener.listen(4927)) {
            ((FragmentCategories) fragments[IDX_TAGS]).SetBaseSearchLink(StationsFilter.SearchStyle.ByTagExact);
        }
        if (!ListenerUtil.mutListener.listen(4928)) {
            ((FragmentCategories) fragments[IDX_COUNTRIES]).SetBaseSearchLink(StationsFilter.SearchStyle.ByCountryCodeExact);
        }
        if (!ListenerUtil.mutListener.listen(4929)) {
            ((FragmentCategories) fragments[IDX_LANGUAGES]).SetBaseSearchLink(StationsFilter.SearchStyle.ByLanguageExact);
        }
        FragmentManager m = getChildFragmentManager();
        ViewPagerAdapter adapter = new ViewPagerAdapter(m);
        if (!ListenerUtil.mutListener.listen(4931)) {
            if (countryCode != null) {
                if (!ListenerUtil.mutListener.listen(4930)) {
                    adapter.addFragment(fragments[IDX_LOCAL], R.string.action_local);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4932)) {
            adapter.addFragment(fragments[IDX_TOP_CLICK], R.string.action_top_click);
        }
        if (!ListenerUtil.mutListener.listen(4933)) {
            adapter.addFragment(fragments[IDX_TOP_VOTE], R.string.action_top_vote);
        }
        if (!ListenerUtil.mutListener.listen(4934)) {
            adapter.addFragment(fragments[IDX_CHANGED_LATELY], R.string.action_changed_lately);
        }
        if (!ListenerUtil.mutListener.listen(4935)) {
            adapter.addFragment(fragments[IDX_CURRENTLY_HEARD], R.string.action_currently_playing);
        }
        if (!ListenerUtil.mutListener.listen(4936)) {
            adapter.addFragment(fragments[IDX_TAGS], R.string.action_tags);
        }
        if (!ListenerUtil.mutListener.listen(4937)) {
            adapter.addFragment(fragments[IDX_COUNTRIES], R.string.action_countries);
        }
        if (!ListenerUtil.mutListener.listen(4938)) {
            adapter.addFragment(fragments[IDX_LANGUAGES], R.string.action_languages);
        }
        if (!ListenerUtil.mutListener.listen(4939)) {
            adapter.addFragment(fragments[IDX_SEARCH], R.string.action_search);
        }
        if (!ListenerUtil.mutListener.listen(4940)) {
            viewPager.setAdapter(adapter);
        }
    }

    public void Search(StationsFilter.SearchStyle searchStyle, final String query) {
        if (!ListenerUtil.mutListener.listen(4941)) {
            Log.d("TABS", "Search = " + query + " searchStyle=" + searchStyle);
        }
        if (!ListenerUtil.mutListener.listen(4948)) {
            if (viewPager != null) {
                if (!ListenerUtil.mutListener.listen(4945)) {
                    Log.d("TABS", "a Search = " + query);
                }
                if (!ListenerUtil.mutListener.listen(4946)) {
                    viewPager.setCurrentItem(IDX_SEARCH, false);
                }
                if (!ListenerUtil.mutListener.listen(4947)) {
                    ((IFragmentSearchable) fragments[IDX_SEARCH]).Search(searchStyle, query);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4942)) {
                    Log.d("TABS", "b Search = " + query);
                }
                if (!ListenerUtil.mutListener.listen(4943)) {
                    queuedSearchQuery = query;
                }
                if (!ListenerUtil.mutListener.listen(4944)) {
                    queuedSearchStyle = searchStyle;
                }
            }
        }
    }

    @Override
    public void Refresh() {
        Fragment fragment = fragments[viewPager.getCurrentItem()];
        if (!ListenerUtil.mutListener.listen(4950)) {
            if (fragment instanceof FragmentBase) {
                if (!ListenerUtil.mutListener.listen(4949)) {
                    ((FragmentBase) fragment).DownloadUrl(true);
                }
            }
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();

        private final List<Integer> mFragmentTitleList = new ArrayList<Integer>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, int title) {
            if (!ListenerUtil.mutListener.listen(4951)) {
                mFragmentList.add(fragment);
            }
            if (!ListenerUtil.mutListener.listen(4952)) {
                mFragmentTitleList.add(title);
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Resources res = getResources();
            return res.getString(mFragmentTitleList.get(position));
        }
    }
}

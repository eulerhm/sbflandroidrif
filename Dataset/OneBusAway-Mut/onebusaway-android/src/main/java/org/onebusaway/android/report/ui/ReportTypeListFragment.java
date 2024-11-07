/*
* Copyright (C) 2014 University of South Florida (sjbarbeau@gmail.com)
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.onebusaway.android.report.ui;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.io.elements.ObaRegion;
import org.onebusaway.android.ui.MaterialListAdapter;
import org.onebusaway.android.ui.MaterialListItem;
import org.onebusaway.android.util.UIUtils;
import java.util.ArrayList;
import java.util.List;
import androidx.fragment.app.ListFragment;
import edu.usf.cutr.open311client.Open311Manager;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * @author Cagri Cetin
 */
public class ReportTypeListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.report_type_fragment, null, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11985)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(11986)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
        }
        String[] reportTypes;
        String[] reportDesc;
        TypedArray reportIcons;
        if (isOpen311Active()) {
            reportTypes = getResources().getStringArray(R.array.report_types);
            reportDesc = getResources().getStringArray(R.array.report_types_desc);
            reportIcons = getResources().obtainTypedArray(R.array.report_types_icons);
        } else {
            reportTypes = getResources().getStringArray(R.array.report_types_without_open311);
            reportDesc = getResources().getStringArray(R.array.report_types_desc_without_open311);
            reportIcons = getResources().obtainTypedArray(R.array.report_types_icons_without_open311);
        }
        Boolean isEmailDefined = isEmailDefined();
        List<MaterialListItem> materialListItems = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(11995)) {
            {
                long _loopCounter169 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(11994) ? (i >= reportTypes.length) : (ListenerUtil.mutListener.listen(11993) ? (i <= reportTypes.length) : (ListenerUtil.mutListener.listen(11992) ? (i > reportTypes.length) : (ListenerUtil.mutListener.listen(11991) ? (i != reportTypes.length) : (ListenerUtil.mutListener.listen(11990) ? (i == reportTypes.length) : (i < reportTypes.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter169", ++_loopCounter169);
                    if (!ListenerUtil.mutListener.listen(11988)) {
                        // Don't show the send app feedback section if email is not defined for region
                        if ((ListenerUtil.mutListener.listen(11987) ? (!isEmailDefined || getString(R.string.rt_app_feedback).equals(reportTypes[i])) : (!isEmailDefined && getString(R.string.rt_app_feedback).equals(reportTypes[i]))))
                            continue;
                    }
                    MaterialListItem item = new MaterialListItem(reportTypes[i], reportDesc[i], reportIcons.getResourceId(i, -1));
                    if (!ListenerUtil.mutListener.listen(11989)) {
                        materialListItems.add(item);
                    }
                }
            }
        }
        MaterialListAdapter adapter = new MaterialListAdapter(getActivity(), materialListItems);
        if (!ListenerUtil.mutListener.listen(11996)) {
            setListAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(11997)) {
            getListView().setOnItemClickListener(this);
        }
    }

    private Boolean isEmailDefined() {
        ObaRegion region = Application.get().getCurrentRegion();
        return !((ListenerUtil.mutListener.listen(11998) ? (region == null && region.getContactEmail() == null) : (region == null || region.getContactEmail() == null)));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MaterialListItem rti = (MaterialListItem) getListView().getItemAtPosition(i);
        if (!ListenerUtil.mutListener.listen(12013)) {
            if (getString(R.string.rt_customer_service).equals(rti.getTitle())) {
                if (!ListenerUtil.mutListener.listen(12011)) {
                    goToCustomerServices();
                }
                if (!ListenerUtil.mutListener.listen(12012)) {
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_problem), getString(R.string.analytics_label_customer_service));
                }
            } else if (getString(R.string.rt_infrastructure_problem).equals(rti.getTitle())) {
                if (!ListenerUtil.mutListener.listen(12009)) {
                    ((ReportActivity) getActivity()).createInfrastructureIssueActivity(getString(R.string.ri_selected_service_stop));
                }
                if (!ListenerUtil.mutListener.listen(12010)) {
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_problem), getString(R.string.analytics_label_stop_problem));
                }
            } else if (getString(R.string.rt_stop_problem).equals(rti.getTitle())) {
                if (!ListenerUtil.mutListener.listen(12007)) {
                    ((ReportActivity) getActivity()).createInfrastructureIssueActivity(getString(R.string.ri_selected_service_stop));
                }
                if (!ListenerUtil.mutListener.listen(12008)) {
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_problem), getString(R.string.analytics_label_stop_problem));
                }
            } else if (getString(R.string.rt_arrival_problem).equals(rti.getTitle())) {
                if (!ListenerUtil.mutListener.listen(12005)) {
                    // Report bus stop issue
                    ((ReportActivity) getActivity()).createInfrastructureIssueActivity(getString(R.string.ri_selected_service_trip));
                }
                if (!ListenerUtil.mutListener.listen(12006)) {
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_problem), getString(R.string.analytics_label_trip_problem));
                }
            } else if (getString(R.string.rt_app_feedback).equals(rti.getTitle())) {
                // Send App feedback
                String email = getString(R.string.ri_app_feedback_email);
                String locationString = getActivity().getIntent().getStringExtra(BaseReportActivity.LOCATION_STRING);
                if (!ListenerUtil.mutListener.listen(12001)) {
                    UIUtils.sendEmail(getActivity(), email, locationString);
                }
                if (!ListenerUtil.mutListener.listen(12002)) {
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_problem), getString(R.string.analytics_label_app_feedback));
                }
                if (!ListenerUtil.mutListener.listen(12004)) {
                    if (locationString == null) {
                        if (!ListenerUtil.mutListener.listen(12003)) {
                            ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_problem), getString(R.string.analytics_label_app_feedback_without_location));
                        }
                    }
                }
            } else if (getString(R.string.rt_ideas).equals(rti.getTitle())) {
                if (!ListenerUtil.mutListener.listen(11999)) {
                    // Direct to ideascale website
                    goToIdeaScale();
                }
                if (!ListenerUtil.mutListener.listen(12000)) {
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_problem), getString(R.string.analytics_label_idea_scale));
                }
            }
        }
    }

    private void goToCustomerServices() {
        if (!ListenerUtil.mutListener.listen(12014)) {
            ((ReportActivity) getActivity()).createCustomerServiceFragment();
        }
    }

    private void goToIdeaScale() {
        if (!ListenerUtil.mutListener.listen(12015)) {
            UIUtils.goToUrl(getActivity(), getString(R.string.ideascale_url));
        }
    }

    private Boolean isOpen311Active() {
        ObaRegion currentRegion = Application.get().getCurrentRegion();
        Boolean isOpen311Active = Boolean.FALSE;
        if (!ListenerUtil.mutListener.listen(12017)) {
            if (currentRegion != null) {
                if (!ListenerUtil.mutListener.listen(12016)) {
                    isOpen311Active = Open311Manager.isOpen311Exist();
                }
            }
        }
        return isOpen311Active;
    }
}

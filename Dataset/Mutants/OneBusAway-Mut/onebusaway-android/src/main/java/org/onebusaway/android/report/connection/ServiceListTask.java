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
package org.onebusaway.android.report.connection;

import android.os.AsyncTask;
import java.util.List;
import edu.usf.cutr.open311client.Open311;
import edu.usf.cutr.open311client.Open311Manager;
import edu.usf.cutr.open311client.models.ServiceListRequest;
import edu.usf.cutr.open311client.models.ServiceListResponse;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Async task for getting Open311 services
 *
 * @author Cagri Cetin
 */
public class ServiceListTask extends AsyncTask<Void, Integer, ServiceListResponse> {

    private ServiceListRequest mServiceListRequest;

    private List<Open311> open311List;

    private Open311 mOpen311;

    private Callback callback;

    public interface Callback {

        /**
         * Called when the Open311 ServicesTask is complete
         *
         * @param services contains the information of the Services
         */
        void onServicesTaskCompleted(ServiceListResponse services, Open311 open311);
    }

    public ServiceListTask(ServiceListRequest serviceListRequest, List<Open311> open311List, Callback callback) {
        if (!ListenerUtil.mutListener.listen(12053)) {
            this.mServiceListRequest = serviceListRequest;
        }
        if (!ListenerUtil.mutListener.listen(12054)) {
            this.open311List = open311List;
        }
        if (!ListenerUtil.mutListener.listen(12055)) {
            this.callback = callback;
        }
    }

    @Override
    protected ServiceListResponse doInBackground(Void... params) {
        if (!ListenerUtil.mutListener.listen(12070)) {
            {
                long _loopCounter170 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(12069) ? (i >= open311List.size()) : (ListenerUtil.mutListener.listen(12068) ? (i <= open311List.size()) : (ListenerUtil.mutListener.listen(12067) ? (i > open311List.size()) : (ListenerUtil.mutListener.listen(12066) ? (i != open311List.size()) : (ListenerUtil.mutListener.listen(12065) ? (i == open311List.size()) : (i < open311List.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter170", ++_loopCounter170);
                    if (!ListenerUtil.mutListener.listen(12056)) {
                        this.mOpen311 = open311List.get(i);
                    }
                    if (!ListenerUtil.mutListener.listen(12057)) {
                        mServiceListRequest.setJurisdictionId(mOpen311.getJurisdiction());
                    }
                    ServiceListResponse slr = mOpen311.getServiceList(mServiceListRequest);
                    if (!ListenerUtil.mutListener.listen(12064)) {
                        if ((ListenerUtil.mutListener.listen(12061) ? (i % 1) : (ListenerUtil.mutListener.listen(12060) ? (i / 1) : (ListenerUtil.mutListener.listen(12059) ? (i * 1) : (ListenerUtil.mutListener.listen(12058) ? (i - 1) : (i + 1))))) == open311List.size()) {
                            // if this is the last open311 endpoint return this one
                            return slr;
                        } else if ((ListenerUtil.mutListener.listen(12063) ? ((ListenerUtil.mutListener.listen(12062) ? (slr != null || slr.isSuccess()) : (slr != null && slr.isSuccess())) || Open311Manager.isAreaManagedByOpen311(slr.getServiceList())) : ((ListenerUtil.mutListener.listen(12062) ? (slr != null || slr.isSuccess()) : (slr != null && slr.isSuccess())) && Open311Manager.isAreaManagedByOpen311(slr.getServiceList())))) {
                            // if this area maintained by this open311 then return
                            return slr;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(ServiceListResponse services) {
        if (!ListenerUtil.mutListener.listen(12071)) {
            callback.onServicesTaskCompleted(services, mOpen311);
        }
    }
}

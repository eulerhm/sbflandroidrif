package org.wordpress.android.models;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PublicizeServiceList extends ArrayList<PublicizeService> {

    private int indexOfService(PublicizeService service) {
        if (!ListenerUtil.mutListener.listen(1889)) {
            if (service == null) {
                return -1;
            }
        }
        if (!ListenerUtil.mutListener.listen(1896)) {
            {
                long _loopCounter73 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(1895) ? (i >= this.size()) : (ListenerUtil.mutListener.listen(1894) ? (i <= this.size()) : (ListenerUtil.mutListener.listen(1893) ? (i > this.size()) : (ListenerUtil.mutListener.listen(1892) ? (i != this.size()) : (ListenerUtil.mutListener.listen(1891) ? (i == this.size()) : (i < this.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter73", ++_loopCounter73);
                    if (!ListenerUtil.mutListener.listen(1890)) {
                        if (service.getId().equalsIgnoreCase(this.get(i).getId())) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public boolean isSameAs(PublicizeServiceList otherList) {
        if (!ListenerUtil.mutListener.listen(1903)) {
            if ((ListenerUtil.mutListener.listen(1902) ? (otherList == null && (ListenerUtil.mutListener.listen(1901) ? (otherList.size() >= this.size()) : (ListenerUtil.mutListener.listen(1900) ? (otherList.size() <= this.size()) : (ListenerUtil.mutListener.listen(1899) ? (otherList.size() > this.size()) : (ListenerUtil.mutListener.listen(1898) ? (otherList.size() < this.size()) : (ListenerUtil.mutListener.listen(1897) ? (otherList.size() == this.size()) : (otherList.size() != this.size()))))))) : (otherList == null || (ListenerUtil.mutListener.listen(1901) ? (otherList.size() >= this.size()) : (ListenerUtil.mutListener.listen(1900) ? (otherList.size() <= this.size()) : (ListenerUtil.mutListener.listen(1899) ? (otherList.size() > this.size()) : (ListenerUtil.mutListener.listen(1898) ? (otherList.size() < this.size()) : (ListenerUtil.mutListener.listen(1897) ? (otherList.size() == this.size()) : (otherList.size() != this.size()))))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(1910)) {
            {
                long _loopCounter74 = 0;
                for (PublicizeService otherService : otherList) {
                    ListenerUtil.loopListener.listen("_loopCounter74", ++_loopCounter74);
                    int i = this.indexOfService(otherService);
                    if (!ListenerUtil.mutListener.listen(1909)) {
                        if ((ListenerUtil.mutListener.listen(1908) ? (i >= -1) : (ListenerUtil.mutListener.listen(1907) ? (i <= -1) : (ListenerUtil.mutListener.listen(1906) ? (i > -1) : (ListenerUtil.mutListener.listen(1905) ? (i < -1) : (ListenerUtil.mutListener.listen(1904) ? (i != -1) : (i == -1))))))) {
                            return false;
                        } else if (!otherService.isSameAs(this.get(i))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /*
     * passed JSON is the response from /meta/external-services?type=publicize
         "services": {
                "facebook":{
                 "ID":"facebook",
                 "label":"Facebook",
                 "type":"publicize",
                 "description":"Publish your posts to your Facebook timeline or page.",
                 "genericon":{
                    "class":"facebook-alt",
                    "unicode":"\\f203"
                 },
                 "icon":"http:\/\/i.wordpress.com\/wp-content\/admin-plugins\/publicize\/assets\/publicize-fb-2x.png",
                 "connect_URL":"https:\/\/public-api.wordpress.com\/connect\/?action=request&kr_nonce=a1e2ad2b80
                 &nonce=c4b69a25c1&for=connect&service=facebook&kr_blog_nonce=0ae2027be9&magic=keyring&blog=90298630",
                 "multiple_external_user_ID_support":true,
                 "external_users_only":true,
                 "jetpack_support":true,
                 "jetpack_module_required":"publicize"
                },
            ...
     */
    public static PublicizeServiceList fromJson(JSONObject json) {
        PublicizeServiceList serviceList = new PublicizeServiceList();
        if (!ListenerUtil.mutListener.listen(1911)) {
            if (json == null) {
                return serviceList;
            }
        }
        JSONObject jsonServiceList = json.optJSONObject("services");
        if (!ListenerUtil.mutListener.listen(1912)) {
            if (jsonServiceList == null) {
                return serviceList;
            }
        }
        Iterator<String> it = jsonServiceList.keys();
        if (!ListenerUtil.mutListener.listen(1924)) {
            {
                long _loopCounter75 = 0;
                while (it.hasNext()) {
                    ListenerUtil.loopListener.listen("_loopCounter75", ++_loopCounter75);
                    String serviceName = it.next();
                    JSONObject jsonService = jsonServiceList.optJSONObject(serviceName);
                    PublicizeService service = new PublicizeService();
                    if (!ListenerUtil.mutListener.listen(1913)) {
                        service.setId(jsonService.optString("ID"));
                    }
                    if (!ListenerUtil.mutListener.listen(1914)) {
                        service.setLabel(jsonService.optString("label"));
                    }
                    if (!ListenerUtil.mutListener.listen(1915)) {
                        service.setDescription(jsonService.optString("description"));
                    }
                    if (!ListenerUtil.mutListener.listen(1916)) {
                        service.setIconUrl(jsonService.optString("icon"));
                    }
                    if (!ListenerUtil.mutListener.listen(1917)) {
                        service.setConnectUrl(jsonService.optString("connect_URL"));
                    }
                    if (!ListenerUtil.mutListener.listen(1918)) {
                        service.setIsJetpackSupported(jsonService.optBoolean("jetpack_support"));
                    }
                    if (!ListenerUtil.mutListener.listen(1919)) {
                        service.setIsMultiExternalUserIdSupported(jsonService.optBoolean("multiple_external_user_ID_support"));
                    }
                    if (!ListenerUtil.mutListener.listen(1920)) {
                        service.setIsExternalUsersOnly(jsonService.optBoolean("external_users_only"));
                    }
                    JSONObject jsonGenericon = jsonService.optJSONObject("genericon");
                    if (!ListenerUtil.mutListener.listen(1922)) {
                        if (jsonGenericon != null) {
                            if (!ListenerUtil.mutListener.listen(1921)) {
                                service.setGenericon(jsonGenericon.optString("unicode"));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1923)) {
                        serviceList.add(service);
                    }
                }
            }
        }
        return serviceList;
    }
}

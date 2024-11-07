package org.wordpress.android.ui.people.utils;

import com.android.volley.VolleyError;
import com.wordpress.rest.RestRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.models.Person;
import org.wordpress.android.ui.people.utils.PeopleUtils.ValidateUsernameCallback.ValidationResult;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PeopleUtils {

    // We limit followers we display to 1000 to avoid API performance issues
    public static final int FOLLOWER_PAGE_LIMIT = 50;

    public static final int FETCH_LIMIT = 20;

    public static void fetchUsers(final SiteModel site, final int offset, final FetchUsersCallback callback) {
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(9308)) {
                    if ((ListenerUtil.mutListener.listen(9299) ? (jsonObject != null || callback != null) : (jsonObject != null && callback != null))) {
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("users");
                            List<Person> people = peopleListFromJSON(jsonArray, site.getId(), Person.PersonType.USER);
                            int numberOfUsers = jsonObject.optInt("found");
                            boolean isEndOfList = (ListenerUtil.mutListener.listen(9306) ? ((people.size() + offset) <= numberOfUsers) : (ListenerUtil.mutListener.listen(9305) ? ((people.size() + offset) > numberOfUsers) : (ListenerUtil.mutListener.listen(9304) ? ((people.size() + offset) < numberOfUsers) : (ListenerUtil.mutListener.listen(9303) ? ((people.size() + offset) != numberOfUsers) : (ListenerUtil.mutListener.listen(9302) ? ((people.size() + offset) == numberOfUsers) : ((people.size() + offset) >= numberOfUsers))))));
                            if (!ListenerUtil.mutListener.listen(9307)) {
                                callback.onSuccess(people, isEndOfList);
                            }
                        } catch (JSONException e) {
                            if (!ListenerUtil.mutListener.listen(9300)) {
                                AppLog.e(T.API, "JSON exception occurred while parsing the response for sites/%s/users: " + e);
                            }
                            if (!ListenerUtil.mutListener.listen(9301)) {
                                callback.onError();
                            }
                        }
                    }
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(9309)) {
                    AppLog.e(T.API, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(9311)) {
                    if (callback != null) {
                        if (!ListenerUtil.mutListener.listen(9310)) {
                            callback.onError();
                        }
                    }
                }
            }
        };
        Map<String, String> params = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(9312)) {
            params.put("number", Integer.toString(PeopleUtils.FETCH_LIMIT));
        }
        if (!ListenerUtil.mutListener.listen(9313)) {
            params.put("offset", Integer.toString(offset));
        }
        if (!ListenerUtil.mutListener.listen(9314)) {
            params.put("order_by", "display_name");
        }
        if (!ListenerUtil.mutListener.listen(9315)) {
            params.put("order", "ASC");
        }
        String path = String.format(Locale.US, "sites/%d/users", site.getSiteId());
        if (!ListenerUtil.mutListener.listen(9316)) {
            WordPress.getRestClientUtilsV1_1().get(path, params, null, listener, errorListener);
        }
    }

    public static void fetchRevisionAuthorsDetails(final SiteModel site, List<String> authors, final FetchUsersCallback callback) {
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(9324)) {
                    if ((ListenerUtil.mutListener.listen(9317) ? (jsonObject != null || callback != null) : (jsonObject != null && callback != null))) {
                        try {
                            List<Person> people = new ArrayList<>();
                            Iterator<String> keys = jsonObject.keys();
                            if (!ListenerUtil.mutListener.listen(9322)) {
                                {
                                    long _loopCounter180 = 0;
                                    while (keys.hasNext()) {
                                        ListenerUtil.loopListener.listen("_loopCounter180", ++_loopCounter180);
                                        String key = keys.next();
                                        if (!ListenerUtil.mutListener.listen(9321)) {
                                            if (jsonObject.get(key) instanceof JSONObject) {
                                                JSONArray jsonArray = ((JSONObject) jsonObject.get(key)).getJSONArray("users");
                                                if (!ListenerUtil.mutListener.listen(9320)) {
                                                    people.addAll(peopleListFromJSON(jsonArray, site.getId(), Person.PersonType.USER));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(9323)) {
                                callback.onSuccess(people, true);
                            }
                        } catch (JSONException e) {
                            if (!ListenerUtil.mutListener.listen(9318)) {
                                AppLog.e(T.API, "JSON exception occurred while parsing the revision author details" + " from batch response for sites/%s/users: " + e);
                            }
                            if (!ListenerUtil.mutListener.listen(9319)) {
                                callback.onError();
                            }
                        }
                    }
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(9325)) {
                    AppLog.e(T.API, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(9327)) {
                    if (callback != null) {
                        if (!ListenerUtil.mutListener.listen(9326)) {
                            callback.onError();
                        }
                    }
                }
            }
        };
        Map<String, String> batchParams = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(9334)) {
            {
                long _loopCounter181 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(9333) ? (i >= authors.size()) : (ListenerUtil.mutListener.listen(9332) ? (i <= authors.size()) : (ListenerUtil.mutListener.listen(9331) ? (i > authors.size()) : (ListenerUtil.mutListener.listen(9330) ? (i != authors.size()) : (ListenerUtil.mutListener.listen(9329) ? (i == authors.size()) : (i < authors.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter181", ++_loopCounter181);
                    if (!ListenerUtil.mutListener.listen(9328)) {
                        batchParams.put(String.format(Locale.US, "urls[%d]", i), String.format(Locale.US, "/sites/%d/users?search=%s&search_columns=ID", site.getSiteId(), authors.get(i)));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9335)) {
            WordPress.getRestClientUtilsV1_1().get("batch/", batchParams, null, listener, errorListener);
        }
    }

    public static void fetchFollowers(final SiteModel site, final int page, final FetchFollowersCallback callback) {
        if (!ListenerUtil.mutListener.listen(9336)) {
            fetchFollowers(site, page, callback, false);
        }
    }

    public static void fetchEmailFollowers(final SiteModel site, final int page, final FetchFollowersCallback callback) {
        if (!ListenerUtil.mutListener.listen(9337)) {
            fetchFollowers(site, page, callback, true);
        }
    }

    private static void fetchFollowers(final SiteModel site, final int page, final FetchFollowersCallback callback, final boolean isEmailFollower) {
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(9353)) {
                    if ((ListenerUtil.mutListener.listen(9338) ? (jsonObject != null || callback != null) : (jsonObject != null && callback != null))) {
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("subscribers");
                            Person.PersonType personType = isEmailFollower ? Person.PersonType.EMAIL_FOLLOWER : Person.PersonType.FOLLOWER;
                            List<Person> people = peopleListFromJSON(jsonArray, site.getId(), personType);
                            int pageFetched = jsonObject.optInt("page");
                            int numberOfPages = jsonObject.optInt("pages");
                            boolean isEndOfList = (ListenerUtil.mutListener.listen(9351) ? ((ListenerUtil.mutListener.listen(9345) ? (page <= numberOfPages) : (ListenerUtil.mutListener.listen(9344) ? (page > numberOfPages) : (ListenerUtil.mutListener.listen(9343) ? (page < numberOfPages) : (ListenerUtil.mutListener.listen(9342) ? (page != numberOfPages) : (ListenerUtil.mutListener.listen(9341) ? (page == numberOfPages) : (page >= numberOfPages)))))) && (ListenerUtil.mutListener.listen(9350) ? (page <= FOLLOWER_PAGE_LIMIT) : (ListenerUtil.mutListener.listen(9349) ? (page > FOLLOWER_PAGE_LIMIT) : (ListenerUtil.mutListener.listen(9348) ? (page < FOLLOWER_PAGE_LIMIT) : (ListenerUtil.mutListener.listen(9347) ? (page != FOLLOWER_PAGE_LIMIT) : (ListenerUtil.mutListener.listen(9346) ? (page == FOLLOWER_PAGE_LIMIT) : (page >= FOLLOWER_PAGE_LIMIT))))))) : ((ListenerUtil.mutListener.listen(9345) ? (page <= numberOfPages) : (ListenerUtil.mutListener.listen(9344) ? (page > numberOfPages) : (ListenerUtil.mutListener.listen(9343) ? (page < numberOfPages) : (ListenerUtil.mutListener.listen(9342) ? (page != numberOfPages) : (ListenerUtil.mutListener.listen(9341) ? (page == numberOfPages) : (page >= numberOfPages)))))) || (ListenerUtil.mutListener.listen(9350) ? (page <= FOLLOWER_PAGE_LIMIT) : (ListenerUtil.mutListener.listen(9349) ? (page > FOLLOWER_PAGE_LIMIT) : (ListenerUtil.mutListener.listen(9348) ? (page < FOLLOWER_PAGE_LIMIT) : (ListenerUtil.mutListener.listen(9347) ? (page != FOLLOWER_PAGE_LIMIT) : (ListenerUtil.mutListener.listen(9346) ? (page == FOLLOWER_PAGE_LIMIT) : (page >= FOLLOWER_PAGE_LIMIT))))))));
                            if (!ListenerUtil.mutListener.listen(9352)) {
                                callback.onSuccess(people, pageFetched, isEndOfList);
                            }
                        } catch (JSONException e) {
                            if (!ListenerUtil.mutListener.listen(9339)) {
                                AppLog.e(T.API, "JSON exception occurred while parsing the response for " + "sites/%s/stats/followers: " + e);
                            }
                            if (!ListenerUtil.mutListener.listen(9340)) {
                                callback.onError();
                            }
                        }
                    }
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(9354)) {
                    AppLog.e(T.API, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(9356)) {
                    if (callback != null) {
                        if (!ListenerUtil.mutListener.listen(9355)) {
                            callback.onError();
                        }
                    }
                }
            }
        };
        Map<String, String> params = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(9357)) {
            params.put("max", Integer.toString(FETCH_LIMIT));
        }
        if (!ListenerUtil.mutListener.listen(9358)) {
            params.put("page", Integer.toString(page));
        }
        if (!ListenerUtil.mutListener.listen(9359)) {
            params.put("type", isEmailFollower ? "email" : "wp_com");
        }
        String path = String.format(Locale.US, "sites/%d/stats/followers", site.getSiteId());
        if (!ListenerUtil.mutListener.listen(9360)) {
            WordPress.getRestClientUtilsV1_1().get(path, params, null, listener, errorListener);
        }
    }

    public static void fetchViewers(final SiteModel site, final int offset, final FetchViewersCallback callback) {
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(9370)) {
                    if ((ListenerUtil.mutListener.listen(9361) ? (jsonObject != null || callback != null) : (jsonObject != null && callback != null))) {
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("viewers");
                            List<Person> people = peopleListFromJSON(jsonArray, site.getId(), Person.PersonType.VIEWER);
                            int numberOfUsers = jsonObject.optInt("found");
                            boolean isEndOfList = (ListenerUtil.mutListener.listen(9368) ? ((people.size() + offset) <= numberOfUsers) : (ListenerUtil.mutListener.listen(9367) ? ((people.size() + offset) > numberOfUsers) : (ListenerUtil.mutListener.listen(9366) ? ((people.size() + offset) < numberOfUsers) : (ListenerUtil.mutListener.listen(9365) ? ((people.size() + offset) != numberOfUsers) : (ListenerUtil.mutListener.listen(9364) ? ((people.size() + offset) == numberOfUsers) : ((people.size() + offset) >= numberOfUsers))))));
                            if (!ListenerUtil.mutListener.listen(9369)) {
                                callback.onSuccess(people, isEndOfList);
                            }
                        } catch (JSONException e) {
                            if (!ListenerUtil.mutListener.listen(9362)) {
                                AppLog.e(T.API, "JSON exception occurred while parsing the response for " + "sites/%s/viewers: " + e);
                            }
                            if (!ListenerUtil.mutListener.listen(9363)) {
                                callback.onError();
                            }
                        }
                    }
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(9371)) {
                    AppLog.e(T.API, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(9373)) {
                    if (callback != null) {
                        if (!ListenerUtil.mutListener.listen(9372)) {
                            callback.onError();
                        }
                    }
                }
            }
        };
        int page = (ListenerUtil.mutListener.listen(9381) ? (((ListenerUtil.mutListener.listen(9377) ? (offset % FETCH_LIMIT) : (ListenerUtil.mutListener.listen(9376) ? (offset * FETCH_LIMIT) : (ListenerUtil.mutListener.listen(9375) ? (offset - FETCH_LIMIT) : (ListenerUtil.mutListener.listen(9374) ? (offset + FETCH_LIMIT) : (offset / FETCH_LIMIT)))))) % 1) : (ListenerUtil.mutListener.listen(9380) ? (((ListenerUtil.mutListener.listen(9377) ? (offset % FETCH_LIMIT) : (ListenerUtil.mutListener.listen(9376) ? (offset * FETCH_LIMIT) : (ListenerUtil.mutListener.listen(9375) ? (offset - FETCH_LIMIT) : (ListenerUtil.mutListener.listen(9374) ? (offset + FETCH_LIMIT) : (offset / FETCH_LIMIT)))))) / 1) : (ListenerUtil.mutListener.listen(9379) ? (((ListenerUtil.mutListener.listen(9377) ? (offset % FETCH_LIMIT) : (ListenerUtil.mutListener.listen(9376) ? (offset * FETCH_LIMIT) : (ListenerUtil.mutListener.listen(9375) ? (offset - FETCH_LIMIT) : (ListenerUtil.mutListener.listen(9374) ? (offset + FETCH_LIMIT) : (offset / FETCH_LIMIT)))))) * 1) : (ListenerUtil.mutListener.listen(9378) ? (((ListenerUtil.mutListener.listen(9377) ? (offset % FETCH_LIMIT) : (ListenerUtil.mutListener.listen(9376) ? (offset * FETCH_LIMIT) : (ListenerUtil.mutListener.listen(9375) ? (offset - FETCH_LIMIT) : (ListenerUtil.mutListener.listen(9374) ? (offset + FETCH_LIMIT) : (offset / FETCH_LIMIT)))))) - 1) : (((ListenerUtil.mutListener.listen(9377) ? (offset % FETCH_LIMIT) : (ListenerUtil.mutListener.listen(9376) ? (offset * FETCH_LIMIT) : (ListenerUtil.mutListener.listen(9375) ? (offset - FETCH_LIMIT) : (ListenerUtil.mutListener.listen(9374) ? (offset + FETCH_LIMIT) : (offset / FETCH_LIMIT)))))) + 1)))));
        Map<String, String> params = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(9382)) {
            params.put("number", Integer.toString(FETCH_LIMIT));
        }
        if (!ListenerUtil.mutListener.listen(9383)) {
            params.put("page", Integer.toString(page));
        }
        String path = String.format(Locale.US, "sites/%d/viewers", site.getSiteId());
        if (!ListenerUtil.mutListener.listen(9384)) {
            WordPress.getRestClientUtilsV1_1().get(path, params, null, listener, errorListener);
        }
    }

    public static void updateRole(final SiteModel site, long personID, String newRole, final int localTableBlogId, final UpdateUserCallback callback) {
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(9391)) {
                    if ((ListenerUtil.mutListener.listen(9385) ? (jsonObject != null || callback != null) : (jsonObject != null && callback != null))) {
                        try {
                            Person person = Person.userFromJSON(jsonObject, localTableBlogId);
                            if (!ListenerUtil.mutListener.listen(9390)) {
                                if (person != null) {
                                    if (!ListenerUtil.mutListener.listen(9389)) {
                                        callback.onSuccess(person);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(9387)) {
                                        AppLog.e(T.API, "Couldn't map jsonObject + " + jsonObject + " to person model.");
                                    }
                                    if (!ListenerUtil.mutListener.listen(9388)) {
                                        callback.onError();
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            if (!ListenerUtil.mutListener.listen(9386)) {
                                callback.onError();
                            }
                        }
                    }
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(9392)) {
                    AppLog.e(T.API, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(9394)) {
                    if (callback != null) {
                        if (!ListenerUtil.mutListener.listen(9393)) {
                            callback.onError();
                        }
                    }
                }
            }
        };
        Map<String, String> params = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(9395)) {
            params.put("roles", newRole);
        }
        String path = String.format(Locale.US, "sites/%d/users/%d", site.getSiteId(), personID);
        if (!ListenerUtil.mutListener.listen(9396)) {
            WordPress.getRestClientUtilsV1_1().post(path, params, null, listener, errorListener);
        }
    }

    public static void removeUser(final SiteModel site, final long personID, final RemovePersonCallback callback) {
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(9401)) {
                    if ((ListenerUtil.mutListener.listen(9397) ? (jsonObject != null || callback != null) : (jsonObject != null && callback != null))) {
                        // check if the call was successful
                        boolean success = jsonObject.optBoolean("success");
                        if (!ListenerUtil.mutListener.listen(9400)) {
                            if (success) {
                                if (!ListenerUtil.mutListener.listen(9399)) {
                                    callback.onSuccess(personID, site.getId());
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(9398)) {
                                    callback.onError();
                                }
                            }
                        }
                    }
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(9402)) {
                    AppLog.e(T.API, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(9404)) {
                    if (callback != null) {
                        if (!ListenerUtil.mutListener.listen(9403)) {
                            callback.onError();
                        }
                    }
                }
            }
        };
        String path = String.format(Locale.US, "sites/%d/users/%d/delete", site.getSiteId(), personID);
        if (!ListenerUtil.mutListener.listen(9405)) {
            WordPress.getRestClientUtilsV1_1().post(path, listener, errorListener);
        }
    }

    public static void removeFollower(final SiteModel site, final long personID, Person.PersonType personType, final RemovePersonCallback callback) {
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(9410)) {
                    if ((ListenerUtil.mutListener.listen(9406) ? (jsonObject != null || callback != null) : (jsonObject != null && callback != null))) {
                        // check if the call was successful
                        boolean success = jsonObject.optBoolean("deleted");
                        if (!ListenerUtil.mutListener.listen(9409)) {
                            if (success) {
                                if (!ListenerUtil.mutListener.listen(9408)) {
                                    callback.onSuccess(personID, site.getId());
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(9407)) {
                                    callback.onError();
                                }
                            }
                        }
                    }
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(9411)) {
                    AppLog.e(T.API, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(9413)) {
                    if (callback != null) {
                        if (!ListenerUtil.mutListener.listen(9412)) {
                            callback.onError();
                        }
                    }
                }
            }
        };
        String path;
        if (personType == Person.PersonType.EMAIL_FOLLOWER) {
            path = String.format(Locale.US, "sites/%d/email-followers/%d/delete", site.getSiteId(), personID);
        } else {
            path = String.format(Locale.US, "sites/%d/followers/%d/delete", site.getSiteId(), personID);
        }
        if (!ListenerUtil.mutListener.listen(9414)) {
            WordPress.getRestClientUtilsV1_1().post(path, listener, errorListener);
        }
    }

    public static void removeViewer(final SiteModel site, final long personID, final RemovePersonCallback callback) {
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(9419)) {
                    if ((ListenerUtil.mutListener.listen(9415) ? (jsonObject != null || callback != null) : (jsonObject != null && callback != null))) {
                        // check if the call was successful
                        boolean success = jsonObject.optBoolean("deleted");
                        if (!ListenerUtil.mutListener.listen(9418)) {
                            if (success) {
                                if (!ListenerUtil.mutListener.listen(9417)) {
                                    callback.onSuccess(personID, site.getId());
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(9416)) {
                                    callback.onError();
                                }
                            }
                        }
                    }
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(9420)) {
                    AppLog.e(T.API, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(9422)) {
                    if (callback != null) {
                        if (!ListenerUtil.mutListener.listen(9421)) {
                            callback.onError();
                        }
                    }
                }
            }
        };
        String path = String.format(Locale.US, "sites/%d/viewers/%d/delete", site.getSiteId(), personID);
        if (!ListenerUtil.mutListener.listen(9423)) {
            WordPress.getRestClientUtilsV1_1().post(path, listener, errorListener);
        }
    }

    private static List<Person> peopleListFromJSON(JSONArray jsonArray, int localTableBlogId, Person.PersonType personType) throws JSONException {
        if (!ListenerUtil.mutListener.listen(9424)) {
            if (jsonArray == null) {
                return null;
            }
        }
        ArrayList<Person> peopleList = new ArrayList<>(jsonArray.length());
        if (!ListenerUtil.mutListener.listen(9432)) {
            {
                long _loopCounter182 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(9431) ? (i >= jsonArray.length()) : (ListenerUtil.mutListener.listen(9430) ? (i <= jsonArray.length()) : (ListenerUtil.mutListener.listen(9429) ? (i > jsonArray.length()) : (ListenerUtil.mutListener.listen(9428) ? (i != jsonArray.length()) : (ListenerUtil.mutListener.listen(9427) ? (i == jsonArray.length()) : (i < jsonArray.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter182", ++_loopCounter182);
                    Person person;
                    if (personType == Person.PersonType.USER) {
                        person = Person.userFromJSON(jsonArray.optJSONObject(i), localTableBlogId);
                    } else if (personType == Person.PersonType.VIEWER) {
                        person = Person.viewerFromJSON(jsonArray.optJSONObject(i), localTableBlogId);
                    } else {
                        boolean isEmailFollower = (personType == Person.PersonType.EMAIL_FOLLOWER);
                        person = Person.followerFromJSON(jsonArray.optJSONObject(i), localTableBlogId, isEmailFollower);
                    }
                    if (!ListenerUtil.mutListener.listen(9426)) {
                        if (person != null) {
                            if (!ListenerUtil.mutListener.listen(9425)) {
                                peopleList.add(person);
                            }
                        }
                    }
                }
            }
        }
        return peopleList;
    }

    public interface FetchUsersCallback extends Callback {

        void onSuccess(List<Person> peopleList, boolean isEndOfList);
    }

    public interface FetchFollowersCallback extends Callback {

        void onSuccess(List<Person> peopleList, int pageFetched, boolean isEndOfList);
    }

    public interface FetchViewersCallback extends Callback {

        void onSuccess(List<Person> peopleList, boolean isEndOfList);
    }

    public interface RemovePersonCallback extends Callback {

        void onSuccess(long personID, int localTableBlogId);
    }

    public interface UpdateUserCallback extends Callback {

        void onSuccess(Person person);
    }

    public interface Callback {

        void onError();
    }

    public static void validateUsernames(final List<String> usernames, String role, long wpComBlogId, final ValidateUsernameCallback callback) {
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(9467)) {
                    if ((ListenerUtil.mutListener.listen(9433) ? (jsonObject != null || callback != null) : (jsonObject != null && callback != null))) {
                        JSONObject errors = jsonObject.optJSONObject("errors");
                        int errorredUsernameCount = 0;
                        if (!ListenerUtil.mutListener.listen(9446)) {
                            if (errors != null) {
                                if (!ListenerUtil.mutListener.listen(9445)) {
                                    {
                                        long _loopCounter183 = 0;
                                        for (String username : usernames) {
                                            ListenerUtil.loopListener.listen("_loopCounter183", ++_loopCounter183);
                                            JSONObject userError = errors.optJSONObject(username);
                                            if (!ListenerUtil.mutListener.listen(9434)) {
                                                if (userError == null) {
                                                    continue;
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(9435)) {
                                                errorredUsernameCount++;
                                            }
                                            if (!ListenerUtil.mutListener.listen(9442)) {
                                                switch(userError.optString("code")) {
                                                    case "invalid_input":
                                                        if (!ListenerUtil.mutListener.listen(9438)) {
                                                            switch(userError.optString("message")) {
                                                                case "Invalid email":
                                                                    if (!ListenerUtil.mutListener.listen(9436)) {
                                                                        callback.onUsernameValidation(username, ValidationResult.INVALID_EMAIL);
                                                                    }
                                                                    continue;
                                                                case "User not found":
                                                                // fall through to the default case
                                                                default:
                                                                    if (!ListenerUtil.mutListener.listen(9437)) {
                                                                        callback.onUsernameValidation(username, ValidationResult.USER_NOT_FOUND);
                                                                    }
                                                                    continue;
                                                            }
                                                        }
                                                    case "invalid_input_has_role":
                                                        if (!ListenerUtil.mutListener.listen(9439)) {
                                                            callback.onUsernameValidation(username, ValidationResult.ALREADY_MEMBER);
                                                        }
                                                        continue;
                                                    case "invalid_input_following":
                                                        if (!ListenerUtil.mutListener.listen(9440)) {
                                                            callback.onUsernameValidation(username, ValidationResult.ALREADY_FOLLOWING);
                                                        }
                                                        continue;
                                                    case "invalid_user_blocked_invites":
                                                        if (!ListenerUtil.mutListener.listen(9441)) {
                                                            callback.onUsernameValidation(username, ValidationResult.BLOCKED_INVITES);
                                                        }
                                                        continue;
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(9443)) {
                                                callback.onError();
                                            }
                                            if (!ListenerUtil.mutListener.listen(9444)) {
                                                callback.onValidationFinished();
                                            }
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                        JSONArray succeededUsernames = jsonObject.optJSONArray("success");
                        if (!ListenerUtil.mutListener.listen(9449)) {
                            if (succeededUsernames == null) {
                                if (!ListenerUtil.mutListener.listen(9447)) {
                                    callback.onError();
                                }
                                if (!ListenerUtil.mutListener.listen(9448)) {
                                    callback.onValidationFinished();
                                }
                                return;
                            }
                        }
                        int succeededUsernameCount = 0;
                        if (!ListenerUtil.mutListener.listen(9458)) {
                            {
                                long _loopCounter184 = 0;
                                for (int i = 0; (ListenerUtil.mutListener.listen(9457) ? (i >= succeededUsernames.length()) : (ListenerUtil.mutListener.listen(9456) ? (i <= succeededUsernames.length()) : (ListenerUtil.mutListener.listen(9455) ? (i > succeededUsernames.length()) : (ListenerUtil.mutListener.listen(9454) ? (i != succeededUsernames.length()) : (ListenerUtil.mutListener.listen(9453) ? (i == succeededUsernames.length()) : (i < succeededUsernames.length())))))); i++) {
                                    ListenerUtil.loopListener.listen("_loopCounter184", ++_loopCounter184);
                                    String username = succeededUsernames.optString(i);
                                    if (!ListenerUtil.mutListener.listen(9452)) {
                                        if (usernames.contains(username)) {
                                            if (!ListenerUtil.mutListener.listen(9450)) {
                                                succeededUsernameCount++;
                                            }
                                            if (!ListenerUtil.mutListener.listen(9451)) {
                                                callback.onUsernameValidation(username, ValidationResult.USER_FOUND);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(9465)) {
                            if ((ListenerUtil.mutListener.listen(9462) ? (errorredUsernameCount % succeededUsernameCount) : (ListenerUtil.mutListener.listen(9461) ? (errorredUsernameCount / succeededUsernameCount) : (ListenerUtil.mutListener.listen(9460) ? (errorredUsernameCount * succeededUsernameCount) : (ListenerUtil.mutListener.listen(9459) ? (errorredUsernameCount - succeededUsernameCount) : (errorredUsernameCount + succeededUsernameCount))))) != usernames.size()) {
                                if (!ListenerUtil.mutListener.listen(9463)) {
                                    callback.onError();
                                }
                                if (!ListenerUtil.mutListener.listen(9464)) {
                                    callback.onValidationFinished();
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(9466)) {
                            callback.onValidationFinished();
                        }
                    }
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(9468)) {
                    AppLog.e(AppLog.T.API, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(9470)) {
                    if (callback != null) {
                        if (!ListenerUtil.mutListener.listen(9469)) {
                            callback.onError();
                        }
                    }
                }
            }
        };
        String path = String.format(Locale.US, "sites/%d/invites/validate", wpComBlogId);
        Map<String, String> params = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(9472)) {
            {
                long _loopCounter185 = 0;
                for (String username : usernames) {
                    ListenerUtil.loopListener.listen("_loopCounter185", ++_loopCounter185);
                    if (!ListenerUtil.mutListener.listen(9471)) {
                        // specify an array key so to make the map key unique
                        params.put("invitees[" + username + "]", username);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9473)) {
            params.put("role", role);
        }
        if (!ListenerUtil.mutListener.listen(9474)) {
            WordPress.getRestClientUtilsV1_1().post(path, params, null, listener, errorListener);
        }
    }

    public interface ValidateUsernameCallback {

        enum ValidationResult {

            USER_NOT_FOUND,
            ALREADY_MEMBER,
            ALREADY_FOLLOWING,
            BLOCKED_INVITES,
            INVALID_EMAIL,
            USER_FOUND
        }

        void onUsernameValidation(String username, ValidationResult validationResult);

        void onValidationFinished();

        void onError();
    }

    public static void sendInvitations(final List<String> usernames, String role, String message, long wpComBlogId, final InvitationsSendCallback callback) {
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(9475)) {
                    if (callback == null) {
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(9477)) {
                    if (jsonObject == null) {
                        if (!ListenerUtil.mutListener.listen(9476)) {
                            callback.onError();
                        }
                        return;
                    }
                }
                Map<String, String> failedUsernames = new LinkedHashMap<>();
                JSONObject errors = jsonObject.optJSONObject("errors");
                if (!ListenerUtil.mutListener.listen(9481)) {
                    if (errors != null) {
                        if (!ListenerUtil.mutListener.listen(9480)) {
                            {
                                long _loopCounter186 = 0;
                                for (String username : usernames) {
                                    ListenerUtil.loopListener.listen("_loopCounter186", ++_loopCounter186);
                                    JSONObject userError = errors.optJSONObject(username);
                                    if (!ListenerUtil.mutListener.listen(9479)) {
                                        if (userError != null) {
                                            if (!ListenerUtil.mutListener.listen(9478)) {
                                                failedUsernames.put(username, userError.optString("message"));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                List<String> succeededUsernames = new ArrayList<>();
                JSONArray succeededUsernamesJson = jsonObject.optJSONArray("sent");
                if (!ListenerUtil.mutListener.listen(9483)) {
                    if (succeededUsernamesJson == null) {
                        if (!ListenerUtil.mutListener.listen(9482)) {
                            callback.onError();
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(9491)) {
                    {
                        long _loopCounter187 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(9490) ? (i >= succeededUsernamesJson.length()) : (ListenerUtil.mutListener.listen(9489) ? (i <= succeededUsernamesJson.length()) : (ListenerUtil.mutListener.listen(9488) ? (i > succeededUsernamesJson.length()) : (ListenerUtil.mutListener.listen(9487) ? (i != succeededUsernamesJson.length()) : (ListenerUtil.mutListener.listen(9486) ? (i == succeededUsernamesJson.length()) : (i < succeededUsernamesJson.length())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter187", ++_loopCounter187);
                            String username = succeededUsernamesJson.optString(i);
                            if (!ListenerUtil.mutListener.listen(9485)) {
                                if (usernames.contains(username)) {
                                    if (!ListenerUtil.mutListener.listen(9484)) {
                                        succeededUsernames.add(username);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9493)) {
                    if (failedUsernames.size() + succeededUsernames.size() != usernames.size()) {
                        if (!ListenerUtil.mutListener.listen(9492)) {
                            callback.onError();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9494)) {
                    callback.onSent(succeededUsernames, failedUsernames);
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(9495)) {
                    AppLog.e(AppLog.T.API, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(9497)) {
                    if (callback != null) {
                        if (!ListenerUtil.mutListener.listen(9496)) {
                            callback.onError();
                        }
                    }
                }
            }
        };
        String path = String.format(Locale.US, "sites/%s/invites/new", wpComBlogId);
        Map<String, String> params = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(9499)) {
            {
                long _loopCounter188 = 0;
                for (String username : usernames) {
                    ListenerUtil.loopListener.listen("_loopCounter188", ++_loopCounter188);
                    if (!ListenerUtil.mutListener.listen(9498)) {
                        // specify an array key so to make the map key unique
                        params.put("invitees[" + username + "]", username);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9500)) {
            params.put("role", role);
        }
        if (!ListenerUtil.mutListener.listen(9501)) {
            params.put("message", message);
        }
        if (!ListenerUtil.mutListener.listen(9502)) {
            WordPress.getRestClientUtilsV1_1().post(path, params, null, listener, errorListener);
        }
    }

    public interface InvitationsSendCallback {

        void onSent(List<String> succeededUsernames, Map<String, String> failedUsernameErrors);

        void onError();
    }
}

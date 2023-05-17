package org.wordpress.android.ui.notifications.services;

import android.text.TextUtils;
import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.wordpress.rest.RestRequest;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.NotificationsTable;
import org.wordpress.android.models.Note;
import org.wordpress.android.networking.RestClientUtils;
import org.wordpress.android.ui.notifications.NotificationEvents;
import org.wordpress.android.ui.notifications.utils.NotificationsActions;
import org.wordpress.android.ui.notifications.utils.NotificationsUtils;
import org.wordpress.android.util.AppLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NotificationsUpdateLogic {

    private ServiceCompletionListener mCompletionListener;

    private Object mListenerCompanion;

    private boolean mRunning = false;

    private String mNoteId;

    private boolean mIsStartedByTappingOnNotification = false;

    private String mLocale;

    public NotificationsUpdateLogic(String locale, ServiceCompletionListener listener) {
        if (!ListenerUtil.mutListener.listen(8745)) {
            mLocale = locale;
        }
        if (!ListenerUtil.mutListener.listen(8746)) {
            mCompletionListener = listener;
        }
    }

    public void performRefresh(String noteId, boolean isStartedByTappingOnNotification, Object companion) {
        if (!ListenerUtil.mutListener.listen(8747)) {
            if (mRunning) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8748)) {
            mListenerCompanion = companion;
        }
        if (!ListenerUtil.mutListener.listen(8749)) {
            mRunning = true;
        }
        if (!ListenerUtil.mutListener.listen(8750)) {
            mNoteId = noteId;
        }
        if (!ListenerUtil.mutListener.listen(8751)) {
            mIsStartedByTappingOnNotification = isStartedByTappingOnNotification;
        }
        Map<String, String> params = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(8752)) {
            params.put("number", "200");
        }
        if (!ListenerUtil.mutListener.listen(8753)) {
            params.put("num_note_items", "20");
        }
        if (!ListenerUtil.mutListener.listen(8754)) {
            params.put("fields", RestClientUtils.NOTIFICATION_FIELDS);
        }
        if (!ListenerUtil.mutListener.listen(8756)) {
            if (!TextUtils.isEmpty(mLocale)) {
                if (!ListenerUtil.mutListener.listen(8755)) {
                    params.put("locale", mLocale.toLowerCase(Locale.ENGLISH));
                }
            }
        }
        RestListener listener = new RestListener();
        if (!ListenerUtil.mutListener.listen(8757)) {
            WordPress.getRestClientUtilsV1_1().getNotifications(params, listener, listener);
        }
    }

    private class RestListener implements RestRequest.Listener, RestRequest.ErrorListener {

        @Override
        public void onResponse(final JSONObject response) {
            List<Note> notes;
            if (response == null) {
                if (!ListenerUtil.mutListener.listen(8765)) {
                    // Not sure this could ever happen, but make sure we're catching all response types
                    AppLog.w(AppLog.T.NOTIFS, "Success, but did not receive any notes");
                }
                if (!ListenerUtil.mutListener.listen(8766)) {
                    EventBus.getDefault().post(new NotificationEvents.NotificationsRefreshCompleted(new ArrayList<Note>(0)));
                }
            } else {
                try {
                    notes = NotificationsActions.parseNotes(response);
                    if (!ListenerUtil.mutListener.listen(8762)) {
                        // That means we need to re-set the *read* flag on this note.
                        if ((ListenerUtil.mutListener.listen(8760) ? (mIsStartedByTappingOnNotification || mNoteId != null) : (mIsStartedByTappingOnNotification && mNoteId != null))) {
                            if (!ListenerUtil.mutListener.listen(8761)) {
                                setNoteRead(mNoteId, notes);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(8763)) {
                        NotificationsTable.saveNotes(notes, true);
                    }
                    if (!ListenerUtil.mutListener.listen(8764)) {
                        EventBus.getDefault().post(new NotificationEvents.NotificationsRefreshCompleted(notes));
                    }
                } catch (JSONException e) {
                    if (!ListenerUtil.mutListener.listen(8758)) {
                        AppLog.e(AppLog.T.NOTIFS, "Success, but can't parse the response", e);
                    }
                    if (!ListenerUtil.mutListener.listen(8759)) {
                        EventBus.getDefault().post(new NotificationEvents.NotificationsRefreshError());
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8767)) {
                completed();
            }
        }

        @Override
        public void onErrorResponse(final VolleyError volleyError) {
            if (!ListenerUtil.mutListener.listen(8768)) {
                logVolleyErrorDetails(volleyError);
            }
            if (!ListenerUtil.mutListener.listen(8769)) {
                EventBus.getDefault().post(new NotificationEvents.NotificationsRefreshError(volleyError));
            }
            if (!ListenerUtil.mutListener.listen(8770)) {
                completed();
            }
        }
    }

    private void setNoteRead(String noteId, List<Note> notes) {
        int notePos = NotificationsUtils.findNoteInNoteArray(notes, noteId);
        if (!ListenerUtil.mutListener.listen(8777)) {
            if ((ListenerUtil.mutListener.listen(8775) ? (notePos >= -1) : (ListenerUtil.mutListener.listen(8774) ? (notePos <= -1) : (ListenerUtil.mutListener.listen(8773) ? (notePos > -1) : (ListenerUtil.mutListener.listen(8772) ? (notePos < -1) : (ListenerUtil.mutListener.listen(8771) ? (notePos == -1) : (notePos != -1))))))) {
                if (!ListenerUtil.mutListener.listen(8776)) {
                    notes.get(notePos).setRead();
                }
            }
        }
    }

    private static void logVolleyErrorDetails(final VolleyError volleyError) {
        if (!ListenerUtil.mutListener.listen(8779)) {
            if (volleyError == null) {
                if (!ListenerUtil.mutListener.listen(8778)) {
                    AppLog.e(AppLog.T.NOTIFS, "Tried to log a VolleyError, but the error obj was null!");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8783)) {
            if (volleyError.networkResponse != null) {
                NetworkResponse networkResponse = volleyError.networkResponse;
                if (!ListenerUtil.mutListener.listen(8780)) {
                    AppLog.e(AppLog.T.NOTIFS, "Network status code: " + networkResponse.statusCode);
                }
                if (!ListenerUtil.mutListener.listen(8782)) {
                    if (networkResponse.data != null) {
                        if (!ListenerUtil.mutListener.listen(8781)) {
                            AppLog.e(AppLog.T.NOTIFS, "Network data: " + new String(networkResponse.data));
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8784)) {
            AppLog.e(AppLog.T.NOTIFS, "Volley Error Message: " + volleyError.getMessage(), volleyError);
        }
    }

    private void completed() {
        if (!ListenerUtil.mutListener.listen(8785)) {
            AppLog.i(AppLog.T.NOTIFS, "notifications update service > completed");
        }
        if (!ListenerUtil.mutListener.listen(8786)) {
            mRunning = false;
        }
        if (!ListenerUtil.mutListener.listen(8787)) {
            mCompletionListener.onCompleted(mListenerCompanion);
        }
    }

    interface ServiceCompletionListener {

        void onCompleted(Object companion);
    }
}

package org.wordpress.android.ui.notifications.utils;

import com.android.volley.VolleyError;
import com.wordpress.rest.RestRequest;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.NotificationsTable;
import org.wordpress.android.models.Note;
import org.wordpress.android.ui.notifications.NotificationEvents;
import org.wordpress.android.util.AppLog;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NotificationsActions {

    // The server will discard the value if we've already seen a most recent note elsewhere or on this device.
    public static void updateNotesSeenTimestamp() {
        ArrayList<Note> latestNotes = NotificationsTable.getLatestNotes(1);
        if (!ListenerUtil.mutListener.listen(8817)) {
            if (latestNotes.size() == 0) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8818)) {
            updateSeenTimestamp(latestNotes.get(0));
        }
    }

    // If `note.getTimestamp()` is not the most recent seen note, the server will discard the value.
    public static void updateSeenTimestamp(Note note) {
        if (!ListenerUtil.mutListener.listen(8821)) {
            WordPress.getRestClientUtilsV1_1().markNotificationsSeen(String.valueOf(note.getTimestamp()), new RestRequest.Listener() {

                @Override
                public void onResponse(JSONObject response) {
                    if (!ListenerUtil.mutListener.listen(8819)) {
                        // Assuming that we've marked the most recent notification as seen. (Beware, seen != read).
                        EventBus.getDefault().post(new NotificationEvents.NotificationsUnseenStatus(false));
                    }
                }
            }, new RestRequest.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    if (!ListenerUtil.mutListener.listen(8820)) {
                        AppLog.e(AppLog.T.NOTIFS, "Could not mark notifications/seen' value via API.", error);
                    }
                }
            });
        }
    }

    public static List<Note> parseNotes(JSONObject response) throws JSONException {
        List<Note> notes;
        JSONArray notesJSON = response.getJSONArray("notes");
        notes = new ArrayList<>(notesJSON.length());
        if (!ListenerUtil.mutListener.listen(8828)) {
            {
                long _loopCounter175 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8827) ? (i >= notesJSON.length()) : (ListenerUtil.mutListener.listen(8826) ? (i <= notesJSON.length()) : (ListenerUtil.mutListener.listen(8825) ? (i > notesJSON.length()) : (ListenerUtil.mutListener.listen(8824) ? (i != notesJSON.length()) : (ListenerUtil.mutListener.listen(8823) ? (i == notesJSON.length()) : (i < notesJSON.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter175", ++_loopCounter175);
                    Note n = new Note(notesJSON.getJSONObject(i));
                    if (!ListenerUtil.mutListener.listen(8822)) {
                        notes.add(n);
                    }
                }
            }
        }
        return notes;
    }

    public static void markNoteAsRead(final Note note) {
        if (!ListenerUtil.mutListener.listen(8829)) {
            if (note == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8834)) {
            // mark the note as read if it's unread
            if (note.isUnread()) {
                if (!ListenerUtil.mutListener.listen(8833)) {
                    WordPress.getRestClientUtilsV1_1().decrementUnreadCount(note.getId(), "9999", new RestRequest.Listener() {

                        @Override
                        public void onResponse(JSONObject response) {
                            if (!ListenerUtil.mutListener.listen(8830)) {
                                note.setRead();
                            }
                            if (!ListenerUtil.mutListener.listen(8831)) {
                                NotificationsTable.saveNote(note);
                            }
                        }
                    }, new RestRequest.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (!ListenerUtil.mutListener.listen(8832)) {
                                AppLog.e(AppLog.T.NOTIFS, "Could not mark note as read via API.");
                            }
                        }
                    });
                }
            }
        }
    }

    public static void downloadNoteAndUpdateDB(final String noteID, final RestRequest.Listener requestListener, final RestRequest.ErrorListener errorListener) {
        if (!ListenerUtil.mutListener.listen(8852)) {
            WordPress.getRestClientUtilsV1_1().getNotification(noteID, new RestRequest.Listener() {

                @Override
                public void onResponse(JSONObject response) {
                    if (!ListenerUtil.mutListener.listen(8836)) {
                        if (response == null) {
                            if (!ListenerUtil.mutListener.listen(8835)) {
                                // Not sure this could ever happen, but make sure we're catching all response types
                                AppLog.w(AppLog.T.NOTIFS, "Success, but did not receive any notes");
                            }
                        }
                    }
                    try {
                        List<Note> notes = NotificationsActions.parseNotes(response);
                        if (!ListenerUtil.mutListener.listen(8846)) {
                            if ((ListenerUtil.mutListener.listen(8842) ? (notes.size() >= 0) : (ListenerUtil.mutListener.listen(8841) ? (notes.size() <= 0) : (ListenerUtil.mutListener.listen(8840) ? (notes.size() < 0) : (ListenerUtil.mutListener.listen(8839) ? (notes.size() != 0) : (ListenerUtil.mutListener.listen(8838) ? (notes.size() == 0) : (notes.size() > 0))))))) {
                                if (!ListenerUtil.mutListener.listen(8844)) {
                                    NotificationsTable.saveNote(notes.get(0));
                                }
                                if (!ListenerUtil.mutListener.listen(8845)) {
                                    EventBus.getDefault().post(new NotificationEvents.NotificationsChanged(notes.get(0).isUnread()));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(8843)) {
                                    AppLog.e(AppLog.T.NOTIFS, "Success, but no note!!!???");
                                }
                            }
                        }
                    } catch (JSONException e) {
                        if (!ListenerUtil.mutListener.listen(8837)) {
                            AppLog.e(AppLog.T.NOTIFS, "Success, but can't parse the response for the note_id " + noteID, e);
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(8848)) {
                        if (requestListener != null) {
                            if (!ListenerUtil.mutListener.listen(8847)) {
                                requestListener.onResponse(response);
                            }
                        }
                    }
                }
            }, new RestRequest.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    if (!ListenerUtil.mutListener.listen(8849)) {
                        AppLog.e(AppLog.T.NOTIFS, "Error retrieving note with ID " + noteID, error);
                    }
                    if (!ListenerUtil.mutListener.listen(8851)) {
                        if (errorListener != null) {
                            if (!ListenerUtil.mutListener.listen(8850)) {
                                errorListener.onErrorResponse(error);
                            }
                        }
                    }
                }
            });
        }
    }
}

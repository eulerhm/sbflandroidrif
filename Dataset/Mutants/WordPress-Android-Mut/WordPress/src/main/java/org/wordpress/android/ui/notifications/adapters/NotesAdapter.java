package org.wordpress.android.ui.notifications.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.core.text.BidiFormatter;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.NotificationsTable;
import org.wordpress.android.fluxc.model.CommentStatus;
import org.wordpress.android.models.Note;
import org.wordpress.android.models.NoticonUtils;
import org.wordpress.android.ui.comments.CommentUtils;
import org.wordpress.android.ui.notifications.NotificationsListFragmentPage.OnNoteClickListener;
import org.wordpress.android.ui.notifications.blocks.NoteBlockClickableSpan;
import org.wordpress.android.ui.notifications.utils.NotificationsUtilsWrapper;
import org.wordpress.android.util.extensions.ContextExtensionsKt;
import org.wordpress.android.util.GravatarUtils;
import org.wordpress.android.util.RtlUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import org.wordpress.android.widgets.BadgedImageView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private final int mAvatarSz;

    private final int mColorUnread;

    private final int mTextIndentSize;

    private final DataLoadedListener mDataLoadedListener;

    private final OnLoadMoreListener mOnLoadMoreListener;

    private final ArrayList<Note> mNotes = new ArrayList<>();

    private final ArrayList<Note> mFilteredNotes = new ArrayList<>();

    @Inject
    protected ImageManager mImageManager;

    @Inject
    protected NotificationsUtilsWrapper mNotificationsUtilsWrapper;

    @Inject
    protected NoticonUtils mNoticonUtils;

    public enum FILTERS {

        FILTER_ALL, FILTER_COMMENT, FILTER_FOLLOW, FILTER_LIKE, FILTER_UNREAD;

        public String toString() {
            switch(this) {
                case FILTER_ALL:
                    return "all";
                case FILTER_COMMENT:
                    return "comment";
                case FILTER_FOLLOW:
                    return "follow";
                case FILTER_LIKE:
                    return "like";
                case FILTER_UNREAD:
                    return "unread";
                default:
                    return "all";
            }
        }
    }

    private FILTERS mCurrentFilter = FILTERS.FILTER_ALL;

    private ReloadNotesFromDBTask mReloadNotesFromDBTask;

    public interface DataLoadedListener {

        void onDataLoaded(int itemsCount);
    }

    public interface OnLoadMoreListener {

        void onLoadMore(long timestamp);
    }

    private OnNoteClickListener mOnNoteClickListener;

    public NotesAdapter(Context context, DataLoadedListener dataLoadedListener, OnLoadMoreListener onLoadMoreListener) {
        super();
        if (!ListenerUtil.mutListener.listen(8103)) {
            ((WordPress) context.getApplicationContext()).component().inject(this);
        }
        mDataLoadedListener = dataLoadedListener;
        mOnLoadMoreListener = onLoadMoreListener;
        if (!ListenerUtil.mutListener.listen(8104)) {
            // will make things go south as in https://github.com/wordpress-mobile/WordPress-Android/issues/8741
            setHasStableIds(false);
        }
        mAvatarSz = (int) context.getResources().getDimension(R.dimen.notifications_avatar_sz);
        mColorUnread = ColorUtils.setAlphaComponent(ContextExtensionsKt.getColorFromAttribute(context, R.attr.colorOnSurface), context.getResources().getInteger(R.integer.selected_list_item_opacity));
        mTextIndentSize = context.getResources().getDimensionPixelSize(R.dimen.notifications_text_indent_sz);
    }

    public void setFilter(FILTERS newFilter) {
        if (!ListenerUtil.mutListener.listen(8105)) {
            mCurrentFilter = newFilter;
        }
    }

    public FILTERS getCurrentFilter() {
        return mCurrentFilter;
    }

    public void addAll(List<Note> notes, boolean clearBeforeAdding) {
        if (!ListenerUtil.mutListener.listen(8106)) {
            Collections.sort(notes, new Note.TimeStampComparator());
        }
        try {
            if (!ListenerUtil.mutListener.listen(8109)) {
                if (clearBeforeAdding) {
                    if (!ListenerUtil.mutListener.listen(8108)) {
                        mNotes.clear();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8110)) {
                mNotes.addAll(notes);
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(8107)) {
                myNotifyDatasetChanged();
            }
        }
    }

    private void myNotifyDatasetChanged() {
        if (!ListenerUtil.mutListener.listen(8111)) {
            buildFilteredNotesList(mFilteredNotes, mNotes, mCurrentFilter);
        }
        if (!ListenerUtil.mutListener.listen(8112)) {
            notifyDataSetChanged();
        }
        if (!ListenerUtil.mutListener.listen(8114)) {
            if (mDataLoadedListener != null) {
                if (!ListenerUtil.mutListener.listen(8113)) {
                    mDataLoadedListener.onDataLoaded(getItemCount());
                }
            }
        }
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notifications_list_item, parent, false);
        return new NoteViewHolder(view);
    }

    // Otherwise it's re-created so many times during layout.
    public static void buildFilteredNotesList(ArrayList<Note> filteredNotes, ArrayList<Note> notes, FILTERS filter) {
        if (!ListenerUtil.mutListener.listen(8115)) {
            filteredNotes.clear();
        }
        if (!ListenerUtil.mutListener.listen(8118)) {
            if ((ListenerUtil.mutListener.listen(8116) ? (notes.isEmpty() && filter == FILTERS.FILTER_ALL) : (notes.isEmpty() || filter == FILTERS.FILTER_ALL))) {
                if (!ListenerUtil.mutListener.listen(8117)) {
                    filteredNotes.addAll(notes);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8128)) {
            {
                long _loopCounter165 = 0;
                for (Note currentNote : notes) {
                    ListenerUtil.loopListener.listen("_loopCounter165", ++_loopCounter165);
                    if (!ListenerUtil.mutListener.listen(8127)) {
                        switch(filter) {
                            case FILTER_COMMENT:
                                if (!ListenerUtil.mutListener.listen(8120)) {
                                    if (currentNote.isCommentType()) {
                                        if (!ListenerUtil.mutListener.listen(8119)) {
                                            filteredNotes.add(currentNote);
                                        }
                                    }
                                }
                                break;
                            case FILTER_FOLLOW:
                                if (!ListenerUtil.mutListener.listen(8122)) {
                                    if (currentNote.isFollowType()) {
                                        if (!ListenerUtil.mutListener.listen(8121)) {
                                            filteredNotes.add(currentNote);
                                        }
                                    }
                                }
                                break;
                            case FILTER_UNREAD:
                                if (!ListenerUtil.mutListener.listen(8124)) {
                                    if (currentNote.isUnread()) {
                                        if (!ListenerUtil.mutListener.listen(8123)) {
                                            filteredNotes.add(currentNote);
                                        }
                                    }
                                }
                                break;
                            case FILTER_LIKE:
                                if (!ListenerUtil.mutListener.listen(8126)) {
                                    if (currentNote.isLikeType()) {
                                        if (!ListenerUtil.mutListener.listen(8125)) {
                                            filteredNotes.add(currentNote);
                                        }
                                    }
                                }
                                break;
                        }
                    }
                }
            }
        }
    }

    private Note getNoteAtPosition(int position) {
        if (!ListenerUtil.mutListener.listen(8129)) {
            if (isValidPosition(position)) {
                return mFilteredNotes.get(position);
            }
        }
        return null;
    }

    public void replaceNote(Note newNote) {
        if (!ListenerUtil.mutListener.listen(8138)) {
            if (newNote != null) {
                int position = getPositionForNoteUnfiltered(newNote.getId());
                if (!ListenerUtil.mutListener.listen(8137)) {
                    if ((ListenerUtil.mutListener.listen(8135) ? (position != RecyclerView.NO_POSITION || (ListenerUtil.mutListener.listen(8134) ? (position >= mNotes.size()) : (ListenerUtil.mutListener.listen(8133) ? (position <= mNotes.size()) : (ListenerUtil.mutListener.listen(8132) ? (position > mNotes.size()) : (ListenerUtil.mutListener.listen(8131) ? (position != mNotes.size()) : (ListenerUtil.mutListener.listen(8130) ? (position == mNotes.size()) : (position < mNotes.size()))))))) : (position != RecyclerView.NO_POSITION && (ListenerUtil.mutListener.listen(8134) ? (position >= mNotes.size()) : (ListenerUtil.mutListener.listen(8133) ? (position <= mNotes.size()) : (ListenerUtil.mutListener.listen(8132) ? (position > mNotes.size()) : (ListenerUtil.mutListener.listen(8131) ? (position != mNotes.size()) : (ListenerUtil.mutListener.listen(8130) ? (position == mNotes.size()) : (position < mNotes.size()))))))))) {
                        if (!ListenerUtil.mutListener.listen(8136)) {
                            mNotes.set(position, newNote);
                        }
                    }
                }
            }
        }
    }

    private boolean isValidPosition(int position) {
        return ((ListenerUtil.mutListener.listen(8149) ? ((ListenerUtil.mutListener.listen(8143) ? (position <= 0) : (ListenerUtil.mutListener.listen(8142) ? (position > 0) : (ListenerUtil.mutListener.listen(8141) ? (position < 0) : (ListenerUtil.mutListener.listen(8140) ? (position != 0) : (ListenerUtil.mutListener.listen(8139) ? (position == 0) : (position >= 0)))))) || (ListenerUtil.mutListener.listen(8148) ? (position >= mFilteredNotes.size()) : (ListenerUtil.mutListener.listen(8147) ? (position <= mFilteredNotes.size()) : (ListenerUtil.mutListener.listen(8146) ? (position > mFilteredNotes.size()) : (ListenerUtil.mutListener.listen(8145) ? (position != mFilteredNotes.size()) : (ListenerUtil.mutListener.listen(8144) ? (position == mFilteredNotes.size()) : (position < mFilteredNotes.size()))))))) : ((ListenerUtil.mutListener.listen(8143) ? (position <= 0) : (ListenerUtil.mutListener.listen(8142) ? (position > 0) : (ListenerUtil.mutListener.listen(8141) ? (position < 0) : (ListenerUtil.mutListener.listen(8140) ? (position != 0) : (ListenerUtil.mutListener.listen(8139) ? (position == 0) : (position >= 0)))))) && (ListenerUtil.mutListener.listen(8148) ? (position >= mFilteredNotes.size()) : (ListenerUtil.mutListener.listen(8147) ? (position <= mFilteredNotes.size()) : (ListenerUtil.mutListener.listen(8146) ? (position > mFilteredNotes.size()) : (ListenerUtil.mutListener.listen(8145) ? (position != mFilteredNotes.size()) : (ListenerUtil.mutListener.listen(8144) ? (position == mFilteredNotes.size()) : (position < mFilteredNotes.size())))))))));
    }

    @Override
    public int getItemCount() {
        return mFilteredNotes.size();
    }

    @Override
    public void onBindViewHolder(NoteViewHolder noteViewHolder, int position) {
        final Note note = getNoteAtPosition(position);
        if (!ListenerUtil.mutListener.listen(8150)) {
            if (note == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8151)) {
            noteViewHolder.mContentView.setTag(note.getId());
        }
        // Display group header
        Note.NoteTimeGroup timeGroup = Note.getTimeGroupForTimestamp(note.getTimestamp());
        Note.NoteTimeGroup previousTimeGroup = null;
        if (!ListenerUtil.mutListener.listen(8162)) {
            if ((ListenerUtil.mutListener.listen(8156) ? (position >= 0) : (ListenerUtil.mutListener.listen(8155) ? (position <= 0) : (ListenerUtil.mutListener.listen(8154) ? (position < 0) : (ListenerUtil.mutListener.listen(8153) ? (position != 0) : (ListenerUtil.mutListener.listen(8152) ? (position == 0) : (position > 0))))))) {
                Note previousNote = getNoteAtPosition((ListenerUtil.mutListener.listen(8160) ? (position % 1) : (ListenerUtil.mutListener.listen(8159) ? (position / 1) : (ListenerUtil.mutListener.listen(8158) ? (position * 1) : (ListenerUtil.mutListener.listen(8157) ? (position + 1) : (position - 1))))));
                if (!ListenerUtil.mutListener.listen(8161)) {
                    previousTimeGroup = Note.getTimeGroupForTimestamp(previousNote.getTimestamp());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8174)) {
            if ((ListenerUtil.mutListener.listen(8163) ? (previousTimeGroup != null || previousTimeGroup == timeGroup) : (previousTimeGroup != null && previousTimeGroup == timeGroup))) {
                if (!ListenerUtil.mutListener.listen(8172)) {
                    noteViewHolder.mHeaderText.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(8173)) {
                    noteViewHolder.mHeaderDivider.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8164)) {
                    noteViewHolder.mHeaderText.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(8165)) {
                    noteViewHolder.mHeaderDivider.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(8171)) {
                    if (timeGroup == Note.NoteTimeGroup.GROUP_TODAY) {
                        if (!ListenerUtil.mutListener.listen(8170)) {
                            noteViewHolder.mHeaderText.setText(R.string.stats_timeframe_today);
                        }
                    } else if (timeGroup == Note.NoteTimeGroup.GROUP_YESTERDAY) {
                        if (!ListenerUtil.mutListener.listen(8169)) {
                            noteViewHolder.mHeaderText.setText(R.string.stats_timeframe_yesterday);
                        }
                    } else if (timeGroup == Note.NoteTimeGroup.GROUP_OLDER_TWO_DAYS) {
                        if (!ListenerUtil.mutListener.listen(8168)) {
                            noteViewHolder.mHeaderText.setText(R.string.older_two_days);
                        }
                    } else if (timeGroup == Note.NoteTimeGroup.GROUP_OLDER_WEEK) {
                        if (!ListenerUtil.mutListener.listen(8167)) {
                            noteViewHolder.mHeaderText.setText(R.string.older_last_week);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8166)) {
                            noteViewHolder.mHeaderText.setText(R.string.older_month);
                        }
                    }
                }
            }
        }
        CommentStatus commentStatus = CommentStatus.ALL;
        if (!ListenerUtil.mutListener.listen(8176)) {
            if (note.getCommentStatus() == CommentStatus.UNAPPROVED) {
                if (!ListenerUtil.mutListener.listen(8175)) {
                    commentStatus = CommentStatus.UNAPPROVED;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8178)) {
            if (!TextUtils.isEmpty(note.getLocalStatus())) {
                if (!ListenerUtil.mutListener.listen(8177)) {
                    commentStatus = CommentStatus.fromString(note.getLocalStatus());
                }
            }
        }
        // Subject is stored in db as html to preserve text formatting
        Spanned noteSubjectSpanned = note.getFormattedSubject(mNotificationsUtilsWrapper);
        if (!ListenerUtil.mutListener.listen(8179)) {
            // Trim the '\n\n' added by Html.fromHtml()
            noteSubjectSpanned = (Spanned) noteSubjectSpanned.subSequence(0, TextUtils.getTrimmedLength(noteSubjectSpanned));
        }
        NoteBlockClickableSpan[] spans = noteSubjectSpanned.getSpans(0, noteSubjectSpanned.length(), NoteBlockClickableSpan.class);
        if (!ListenerUtil.mutListener.listen(8181)) {
            {
                long _loopCounter166 = 0;
                for (NoteBlockClickableSpan span : spans) {
                    ListenerUtil.loopListener.listen("_loopCounter166", ++_loopCounter166);
                    if (!ListenerUtil.mutListener.listen(8180)) {
                        span.enableColors(noteViewHolder.mContentView.getContext());
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8182)) {
            noteViewHolder.mTxtSubject.setText(noteSubjectSpanned);
        }
        String noteSubjectNoticon = note.getCommentSubjectNoticon();
        if (!ListenerUtil.mutListener.listen(8191)) {
            if (!TextUtils.isEmpty(noteSubjectNoticon)) {
                ViewParent parent = noteViewHolder.mTxtSubject.getParent();
                if (!ListenerUtil.mutListener.listen(8185)) {
                    // Fix position of the subject noticon in the RtL mode
                    if (parent instanceof ViewGroup) {
                        int textDirection = BidiFormatter.getInstance().isRtl(noteViewHolder.mTxtSubject.getText()) ? ViewCompat.LAYOUT_DIRECTION_RTL : ViewCompat.LAYOUT_DIRECTION_LTR;
                        if (!ListenerUtil.mutListener.listen(8184)) {
                            ViewCompat.setLayoutDirection((ViewGroup) parent, textDirection);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8187)) {
                    // mirror noticon in the rtl mode
                    if (RtlUtils.isRtl(noteViewHolder.itemView.getContext())) {
                        if (!ListenerUtil.mutListener.listen(8186)) {
                            noteViewHolder.mTxtSubjectNoticon.setScaleX(-1);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8188)) {
                    CommentUtils.indentTextViewFirstLine(noteViewHolder.mTxtSubject, mTextIndentSize);
                }
                if (!ListenerUtil.mutListener.listen(8189)) {
                    noteViewHolder.mTxtSubjectNoticon.setText(noteSubjectNoticon);
                }
                if (!ListenerUtil.mutListener.listen(8190)) {
                    noteViewHolder.mTxtSubjectNoticon.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8183)) {
                    noteViewHolder.mTxtSubjectNoticon.setVisibility(View.GONE);
                }
            }
        }
        String noteSnippet = note.getCommentSubject();
        if (!ListenerUtil.mutListener.listen(8197)) {
            if (!TextUtils.isEmpty(noteSnippet)) {
                if (!ListenerUtil.mutListener.listen(8194)) {
                    noteViewHolder.mTxtSubject.setMaxLines(2);
                }
                if (!ListenerUtil.mutListener.listen(8195)) {
                    noteViewHolder.mTxtDetail.setText(noteSnippet);
                }
                if (!ListenerUtil.mutListener.listen(8196)) {
                    noteViewHolder.mTxtDetail.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8192)) {
                    noteViewHolder.mTxtSubject.setMaxLines(3);
                }
                if (!ListenerUtil.mutListener.listen(8193)) {
                    noteViewHolder.mTxtDetail.setVisibility(View.GONE);
                }
            }
        }
        String avatarUrl = GravatarUtils.fixGravatarUrl(note.getIconURL(), mAvatarSz);
        if (!ListenerUtil.mutListener.listen(8198)) {
            mImageManager.loadIntoCircle(noteViewHolder.mImgAvatar, ImageType.AVATAR_WITH_BACKGROUND, avatarUrl);
        }
        boolean isUnread = note.isUnread();
        int gridicon = mNoticonUtils.noticonToGridicon(note.getNoticonCharacter());
        if (!ListenerUtil.mutListener.listen(8199)) {
            noteViewHolder.mImgAvatar.setBadgeIcon(gridicon);
        }
        if (!ListenerUtil.mutListener.listen(8203)) {
            if (commentStatus == CommentStatus.UNAPPROVED) {
                if (!ListenerUtil.mutListener.listen(8202)) {
                    noteViewHolder.mImgAvatar.setBadgeBackground(R.drawable.bg_oval_warning_dark);
                }
            } else if (isUnread) {
                if (!ListenerUtil.mutListener.listen(8201)) {
                    noteViewHolder.mImgAvatar.setBadgeBackground(R.drawable.bg_note_avatar_badge);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8200)) {
                    noteViewHolder.mImgAvatar.setBadgeBackground(R.drawable.bg_oval_neutral_20);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8206)) {
            if (isUnread) {
                if (!ListenerUtil.mutListener.listen(8205)) {
                    noteViewHolder.mContentView.setBackgroundColor(mColorUnread);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8204)) {
                    noteViewHolder.mContentView.setBackgroundColor(0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8218)) {
            // request to load more comments when we near the end
            if ((ListenerUtil.mutListener.listen(8216) ? (mOnLoadMoreListener != null || (ListenerUtil.mutListener.listen(8215) ? (position <= (ListenerUtil.mutListener.listen(8210) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(8209) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(8208) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(8207) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(8214) ? (position > (ListenerUtil.mutListener.listen(8210) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(8209) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(8208) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(8207) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(8213) ? (position < (ListenerUtil.mutListener.listen(8210) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(8209) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(8208) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(8207) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(8212) ? (position != (ListenerUtil.mutListener.listen(8210) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(8209) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(8208) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(8207) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(8211) ? (position == (ListenerUtil.mutListener.listen(8210) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(8209) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(8208) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(8207) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position >= (ListenerUtil.mutListener.listen(8210) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(8209) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(8208) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(8207) ? (getItemCount() + 1) : (getItemCount() - 1)))))))))))) : (mOnLoadMoreListener != null && (ListenerUtil.mutListener.listen(8215) ? (position <= (ListenerUtil.mutListener.listen(8210) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(8209) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(8208) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(8207) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(8214) ? (position > (ListenerUtil.mutListener.listen(8210) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(8209) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(8208) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(8207) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(8213) ? (position < (ListenerUtil.mutListener.listen(8210) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(8209) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(8208) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(8207) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(8212) ? (position != (ListenerUtil.mutListener.listen(8210) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(8209) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(8208) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(8207) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(8211) ? (position == (ListenerUtil.mutListener.listen(8210) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(8209) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(8208) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(8207) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position >= (ListenerUtil.mutListener.listen(8210) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(8209) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(8208) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(8207) ? (getItemCount() + 1) : (getItemCount() - 1)))))))))))))) {
                if (!ListenerUtil.mutListener.listen(8217)) {
                    mOnLoadMoreListener.onLoadMore(note.getTimestamp());
                }
            }
        }
    }

    private int getPositionForNoteUnfiltered(String noteId) {
        return getPositionForNoteInArray(noteId, mNotes);
    }

    private int getPositionForNoteInArray(String noteId, ArrayList<Note> notes) {
        if (!ListenerUtil.mutListener.listen(8228)) {
            if ((ListenerUtil.mutListener.listen(8219) ? (notes != null || noteId != null) : (notes != null && noteId != null))) {
                if (!ListenerUtil.mutListener.listen(8227)) {
                    {
                        long _loopCounter167 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(8226) ? (i >= notes.size()) : (ListenerUtil.mutListener.listen(8225) ? (i <= notes.size()) : (ListenerUtil.mutListener.listen(8224) ? (i > notes.size()) : (ListenerUtil.mutListener.listen(8223) ? (i != notes.size()) : (ListenerUtil.mutListener.listen(8222) ? (i == notes.size()) : (i < notes.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter167", ++_loopCounter167);
                            String noteKey = notes.get(i).getId();
                            if (!ListenerUtil.mutListener.listen(8221)) {
                                if ((ListenerUtil.mutListener.listen(8220) ? (noteKey != null || noteKey.equals(noteId)) : (noteKey != null && noteKey.equals(noteId)))) {
                                    return i;
                                }
                            }
                        }
                    }
                }
            }
        }
        return RecyclerView.NO_POSITION;
    }

    public void setOnNoteClickListener(OnNoteClickListener mNoteClickListener) {
        if (!ListenerUtil.mutListener.listen(8229)) {
            mOnNoteClickListener = mNoteClickListener;
        }
    }

    public void cancelReloadNotesTask() {
        if (!ListenerUtil.mutListener.listen(8233)) {
            if ((ListenerUtil.mutListener.listen(8230) ? (mReloadNotesFromDBTask != null || mReloadNotesFromDBTask.getStatus() != Status.FINISHED) : (mReloadNotesFromDBTask != null && mReloadNotesFromDBTask.getStatus() != Status.FINISHED))) {
                if (!ListenerUtil.mutListener.listen(8231)) {
                    mReloadNotesFromDBTask.cancel(true);
                }
                if (!ListenerUtil.mutListener.listen(8232)) {
                    mReloadNotesFromDBTask = null;
                }
            }
        }
    }

    public void reloadNotesFromDBAsync() {
        if (!ListenerUtil.mutListener.listen(8234)) {
            cancelReloadNotesTask();
        }
        if (!ListenerUtil.mutListener.listen(8235)) {
            mReloadNotesFromDBTask = new ReloadNotesFromDBTask();
        }
        if (!ListenerUtil.mutListener.listen(8236)) {
            mReloadNotesFromDBTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class ReloadNotesFromDBTask extends AsyncTask<Void, Void, ArrayList<Note>> {

        @Override
        protected ArrayList<Note> doInBackground(Void... voids) {
            return NotificationsTable.getLatestNotes();
        }

        @Override
        protected void onPostExecute(ArrayList<Note> notes) {
            if (!ListenerUtil.mutListener.listen(8237)) {
                mNotes.clear();
            }
            if (!ListenerUtil.mutListener.listen(8238)) {
                mNotes.addAll(notes);
            }
            if (!ListenerUtil.mutListener.listen(8239)) {
                myNotifyDatasetChanged();
            }
        }
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private final View mContentView;

        private final TextView mHeaderText;

        private final View mHeaderDivider;

        private final TextView mTxtSubject;

        private final TextView mTxtSubjectNoticon;

        private final TextView mTxtDetail;

        private final BadgedImageView mImgAvatar;

        NoteViewHolder(View view) {
            super(view);
            mContentView = view.findViewById(R.id.note_content_container);
            mHeaderText = view.findViewById(R.id.header_text);
            mHeaderDivider = view.findViewById(R.id.header_divider);
            mTxtSubject = view.findViewById(R.id.note_subject);
            mTxtSubjectNoticon = view.findViewById(R.id.note_subject_noticon);
            mTxtDetail = view.findViewById(R.id.note_detail);
            mImgAvatar = view.findViewById(R.id.note_avatar);
            if (!ListenerUtil.mutListener.listen(8240)) {
                mContentView.setOnClickListener(mOnClickListener);
            }
        }
    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (!ListenerUtil.mutListener.listen(8243)) {
                if ((ListenerUtil.mutListener.listen(8241) ? (mOnNoteClickListener != null || v.getTag() instanceof String) : (mOnNoteClickListener != null && v.getTag() instanceof String))) {
                    if (!ListenerUtil.mutListener.listen(8242)) {
                        mOnNoteClickListener.onClickNote((String) v.getTag());
                    }
                }
            }
        }
    };
}

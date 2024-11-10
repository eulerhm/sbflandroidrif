package org.wordpress.android.ui.notifications;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 H2\u00020\u00012\u00020\u0002:\u0003HIJB\u0005\u00a2\u0006\u0002\u0010\u0003J\n\u0010+\u001a\u0004\u0018\u00010\u001dH\u0016J\u0012\u0010,\u001a\u00020\u00072\b\u0010-\u001a\u0004\u0018\u00010.H\u0002J\u0012\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u000102H\u0016J\u0012\u00103\u001a\u0002002\b\u00104\u001a\u0004\u0018\u000102H\u0016J&\u00105\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\t2\b\u00104\u001a\u0004\u0018\u000102H\u0016J\b\u0010:\u001a\u000200H\u0016J\b\u0010;\u001a\u000200H\u0016J\u0010\u0010<\u001a\u0002002\u0006\u0010=\u001a\u000202H\u0016J\u000e\u0010>\u001a\u0002002\u0006\u0010?\u001a\u00020@J\u000e\u0010A\u001a\u0002002\u0006\u0010B\u001a\u00020(J\b\u0010C\u001a\u000200H\u0002J\b\u0010D\u001a\u000200H\u0002J\u0010\u0010E\u001a\u0002002\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u0012\u0010F\u001a\u0002002\b\u0010B\u001a\u0004\u0018\u00010(H\u0016J\b\u0010G\u001a\u000200H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001e\u001a\u00020\u001f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\'\u001a\u0004\u0018\u00010(X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006K"}, d2 = {"Lorg/wordpress/android/ui/notifications/NotificationsDetailListFragment;", "Landroidx/fragment/app/ListFragment;", "Lorg/wordpress/android/ui/notifications/NotificationFragment;", "()V", "commentListPosition", "", "confettiShown", "", "footerView", "Landroid/view/ViewGroup;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "listScenarioUtils", "Lorg/wordpress/android/ui/engagement/ListScenarioUtils;", "getListScenarioUtils", "()Lorg/wordpress/android/ui/engagement/ListScenarioUtils;", "setListScenarioUtils", "(Lorg/wordpress/android/ui/engagement/ListScenarioUtils;)V", "mOnGravatarClickedListener", "Lorg/wordpress/android/ui/notifications/blocks/UserNoteBlock$OnGravatarClickedListener;", "mOnNoteBlockTextClickListener", "Lorg/wordpress/android/ui/notifications/blocks/NoteBlock$OnNoteBlockTextClickListener;", "noteBlockAdapter", "Lorg/wordpress/android/ui/notifications/adapters/NoteBlockAdapter;", "notification", "Lorg/wordpress/android/models/Note;", "notificationsUtilsWrapper", "Lorg/wordpress/android/ui/notifications/utils/NotificationsUtilsWrapper;", "getNotificationsUtilsWrapper", "()Lorg/wordpress/android/ui/notifications/utils/NotificationsUtilsWrapper;", "setNotificationsUtilsWrapper", "(Lorg/wordpress/android/ui/notifications/utils/NotificationsUtilsWrapper;)V", "onCommentStatusChangeListener", "Lorg/wordpress/android/ui/notifications/blocks/CommentUserNoteBlock$OnCommentStatusChangeListener;", "restoredListPosition", "restoredNoteId", "", "rootLayout", "Landroid/widget/LinearLayout;", "getNote", "isFooterBlock", "blockObject", "Lorg/wordpress/android/fluxc/tools/FormattableContent;", "onActivityCreated", "", "bundle", "Landroid/os/Bundle;", "onCreate", "savedInstanceState", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "onPause", "onResume", "onSaveInstanceState", "outState", "refreshBlocksForCommentStatus", "newStatus", "Lorg/wordpress/android/fluxc/model/CommentStatus;", "refreshBlocksForEditedComment", "noteId", "reloadNoteBlocks", "requestReaderContentForNote", "setFooterView", "setNote", "showErrorToastAndFinish", "Companion", "LoadNoteBlocksTask", "ManageUserBlockResults", "org.wordpress.android_wordpressJalapenoDebug"})
public final class NotificationsDetailListFragment extends androidx.fragment.app.ListFragment implements org.wordpress.android.ui.notifications.NotificationFragment {
    private int restoredListPosition = 0;
    private org.wordpress.android.models.Note notification;
    private android.widget.LinearLayout rootLayout;
    private android.view.ViewGroup footerView;
    private java.lang.String restoredNoteId;
    private int commentListPosition = -1;
    private org.wordpress.android.ui.notifications.blocks.CommentUserNoteBlock.OnCommentStatusChangeListener onCommentStatusChangeListener;
    private org.wordpress.android.ui.notifications.adapters.NoteBlockAdapter noteBlockAdapter;
    private boolean confettiShown = false;
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    @javax.inject.Inject()
    public org.wordpress.android.ui.notifications.utils.NotificationsUtilsWrapper notificationsUtilsWrapper;
    @javax.inject.Inject()
    public org.wordpress.android.ui.engagement.ListScenarioUtils listScenarioUtils;
    private final org.wordpress.android.ui.notifications.blocks.NoteBlock.OnNoteBlockTextClickListener mOnNoteBlockTextClickListener = null;
    private final org.wordpress.android.ui.notifications.blocks.UserNoteBlock.OnGravatarClickedListener mOnGravatarClickedListener = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.notifications.NotificationsDetailListFragment.Companion Companion = null;
    private static final java.lang.String KEY_NOTE_ID = "noteId";
    private static final java.lang.String KEY_LIST_POSITION = "listPosition";
    
    public NotificationsDetailListFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    public final void setImageManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.notifications.utils.NotificationsUtilsWrapper getNotificationsUtilsWrapper() {
        return null;
    }
    
    public final void setNotificationsUtilsWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.notifications.utils.NotificationsUtilsWrapper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.engagement.ListScenarioUtils getListScenarioUtils() {
        return null;
    }
    
    public final void setListScenarioUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.ListScenarioUtils p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onActivityCreated(@org.jetbrains.annotations.Nullable()
    android.os.Bundle bundle) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onPause() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public org.wordpress.android.models.Note getNote() {
        return null;
    }
    
    @java.lang.Override()
    public void setNote(@org.jetbrains.annotations.Nullable()
    java.lang.String noteId) {
    }
    
    private final void showErrorToastAndFinish() {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    private final void reloadNoteBlocks() {
    }
    
    public final void setFooterView(@org.jetbrains.annotations.Nullable()
    android.view.ViewGroup footerView) {
    }
    
    private final boolean isFooterBlock(org.wordpress.android.fluxc.tools.FormattableContent blockObject) {
        return false;
    }
    
    public final void refreshBlocksForCommentStatus(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.CommentStatus newStatus) {
    }
    
    public final void refreshBlocksForEditedComment(@org.jetbrains.annotations.NotNull()
    java.lang.String noteId) {
    }
    
    private final void requestReaderContentForNote() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.ui.notifications.NotificationsDetailListFragment newInstance(@org.jetbrains.annotations.Nullable()
    java.lang.String noteId) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J)\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0007H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/notifications/NotificationsDetailListFragment$ManageUserBlockResults;", "", "index", "", "noteBlock", "Lorg/wordpress/android/ui/notifications/blocks/NoteBlock;", "pingbackUrl", "", "(ILorg/wordpress/android/ui/notifications/blocks/NoteBlock;Ljava/lang/String;)V", "getIndex", "()I", "getNoteBlock", "()Lorg/wordpress/android/ui/notifications/blocks/NoteBlock;", "getPingbackUrl", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    static final class ManageUserBlockResults {
        private final int index = 0;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.notifications.blocks.NoteBlock noteBlock = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String pingbackUrl = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.notifications.NotificationsDetailListFragment.ManageUserBlockResults copy(int index, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.notifications.blocks.NoteBlock noteBlock, @org.jetbrains.annotations.Nullable()
        java.lang.String pingbackUrl) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public ManageUserBlockResults(int index, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.notifications.blocks.NoteBlock noteBlock, @org.jetbrains.annotations.Nullable()
        java.lang.String pingbackUrl) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getIndex() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.notifications.blocks.NoteBlock component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.notifications.blocks.NoteBlock getNoteBlock() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getPingbackUrl() {
            return null;
        }
    }
    
    @android.annotation.SuppressLint(value = {"StaticFieldLeak"})
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0083\u0004\u0018\u00002\u001c\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0005J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\rH\u0002J0\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0007H\u0002J\"\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0017\u001a\u00020\u000fH\u0002J)\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0012\u0010\u0019\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u001a\"\u00020\u0002H\u0014\u00a2\u0006\u0002\u0010\u001bJ\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0002J0\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\"H\u0002J\u0018\u0010#\u001a\u00020\t2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lorg/wordpress/android/ui/notifications/NotificationsDetailListFragment$LoadNoteBlocksTask;", "Landroid/os/AsyncTask;", "Ljava/lang/Void;", "", "Lorg/wordpress/android/ui/notifications/blocks/NoteBlock;", "(Lorg/wordpress/android/ui/notifications/NotificationsDetailListFragment;)V", "mIsBadgeView", "", "addHeaderNoteBlock", "", "note", "Lorg/wordpress/android/models/Note;", "noteList", "", "addNotesBlock", "", "bodyArray", "Lorg/json/JSONArray;", "isPingback", "buildGeneratedLinkBlock", "onNoteBlockTextClickListener", "Lorg/wordpress/android/ui/notifications/blocks/NoteBlock$OnNoteBlockTextClickListener;", "pingbackUrl", "message", "doInBackground", "params", "", "([Ljava/lang/Void;)Ljava/util/List;", "manageUserBlock", "Lorg/wordpress/android/ui/notifications/NotificationsDetailListFragment$ManageUserBlockResults;", "listSize", "", "initialIndex", "noteObject", "Lorg/wordpress/android/fluxc/tools/FormattableContent;", "onPostExecute", "org.wordpress.android_wordpressJalapenoDebug"})
    final class LoadNoteBlocksTask extends android.os.AsyncTask<java.lang.Void, java.lang.Void, java.util.List<? extends org.wordpress.android.ui.notifications.blocks.NoteBlock>> {
        private boolean mIsBadgeView = false;
        
        public LoadNoteBlocksTask() {
            super();
        }
        
        private final void addHeaderNoteBlock(org.wordpress.android.models.Note note, java.util.List<org.wordpress.android.ui.notifications.blocks.NoteBlock> noteList) {
        }
        
        private final org.wordpress.android.ui.notifications.NotificationsDetailListFragment.ManageUserBlockResults manageUserBlock(org.wordpress.android.models.Note note, org.json.JSONArray bodyArray, int listSize, int initialIndex, org.wordpress.android.fluxc.tools.FormattableContent noteObject) {
            return null;
        }
        
        private final java.lang.String addNotesBlock(org.wordpress.android.models.Note note, java.util.List<org.wordpress.android.ui.notifications.blocks.NoteBlock> noteList, org.json.JSONArray bodyArray, boolean isPingback) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        protected java.util.List<org.wordpress.android.ui.notifications.blocks.NoteBlock> doInBackground(@org.jetbrains.annotations.NotNull()
        java.lang.Void... params) {
            return null;
        }
        
        private final boolean isPingback(org.wordpress.android.models.Note note) {
            return false;
        }
        
        private final org.wordpress.android.ui.notifications.blocks.NoteBlock buildGeneratedLinkBlock(org.wordpress.android.ui.notifications.blocks.NoteBlock.OnNoteBlockTextClickListener onNoteBlockTextClickListener, java.lang.String pingbackUrl, java.lang.String message) {
            return null;
        }
        
        @java.lang.Override()
        protected void onPostExecute(@org.jetbrains.annotations.Nullable()
        java.util.List<? extends org.wordpress.android.ui.notifications.blocks.NoteBlock> noteList) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/notifications/NotificationsDetailListFragment$Companion;", "", "()V", "KEY_LIST_POSITION", "", "KEY_NOTE_ID", "newInstance", "Lorg/wordpress/android/ui/notifications/NotificationsDetailListFragment;", "noteId", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.ui.notifications.NotificationsDetailListFragment newInstance(@org.jetbrains.annotations.Nullable()
        java.lang.String noteId) {
            return null;
        }
    }
}
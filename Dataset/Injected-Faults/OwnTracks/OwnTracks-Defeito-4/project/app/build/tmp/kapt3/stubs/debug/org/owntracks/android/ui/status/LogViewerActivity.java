package org.owntracks.android.ui.status;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.*;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textview.MaterialTextView;
import kotlinx.coroutines.*;
import org.owntracks.android.BuildConfig;
import org.owntracks.android.R;
import org.owntracks.android.databinding.UiPreferencesLogsBinding;
import org.owntracks.android.ui.base.BaseActivity;
import org.owntracks.android.ui.base.view.MvvmView;
import org.owntracks.android.ui.base.viewmodel.NoOpViewModel;
import timber.log.Timber;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 :2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004:\u0004:;<=B\u0005\u00a2\u0006\u0002\u0010\u0005J\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J\b\u0010!\u001a\u00020\u0018H\u0002J\"\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0014J\u0012\u0010(\u001a\u00020\u001f2\b\u0010)\u001a\u0004\u0018\u00010*H\u0014J\u0012\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u00020\u001fH\u0014J\u0010\u00100\u001a\u00020,2\u0006\u00101\u001a\u00020\u0007H\u0016J\u0010\u00102\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0016J\u0012\u00103\u001a\u0004\u0018\u00010\u00102\u0006\u00104\u001a\u00020\u0018H\u0002J\u0012\u00105\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u00020\u0018H\u0002J\b\u00108\u001a\u00020\u001fH\u0002J\u0011\u00109\u001a\u00020\u001fH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00060\u000bR\u00020\u0000X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R#\u0010\u0017\u001a\n \u0019*\u0004\u0018\u00010\u00180\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006>"}, d2 = {"Lorg/owntracks/android/ui/status/LogViewerActivity;", "Lorg/owntracks/android/ui/base/BaseActivity;", "Lorg/owntracks/android/databinding/UiPreferencesLogsBinding;", "Lorg/owntracks/android/ui/base/viewmodel/NoOpViewModel;", "Lorg/owntracks/android/ui/base/view/MvvmView;", "()V", "clearButton", "Landroid/view/MenuItem;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "logAdapter", "Lorg/owntracks/android/ui/status/LogViewerActivity$LogEntryAdapter;", "logExportUri", "Landroid/net/Uri;", "logLines", "Ljava/util/ArrayList;", "Lorg/owntracks/android/ui/status/LogViewerActivity$LogLine;", "rawLogLines", "Ljava/lang/StringBuffer;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "streamingJob", "Lkotlinx/coroutines/Job;", "year", "", "kotlin.jvm.PlatformType", "getYear", "()Ljava/lang/String;", "year$delegate", "Lkotlin/Lazy;", "clearLog", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRandomHexString", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onDestroy", "onOptionsItemSelected", "item", "onPrepareOptionsMenu", "parseLine", "line", "parseTime", "Ljava/util/Date;", "timeStr", "revokeExportUriPermissions", "streamingLog", "Companion", "ExportedLogContentProvider", "LogEntryAdapter", "LogLine", "app_debug"})
public final class LogViewerActivity extends org.owntracks.android.ui.base.BaseActivity<org.owntracks.android.databinding.UiPreferencesLogsBinding, org.owntracks.android.ui.base.viewmodel.NoOpViewModel> implements org.owntracks.android.ui.base.view.MvvmView {
    private org.owntracks.android.ui.status.LogViewerActivity.LogEntryAdapter logAdapter;
    private kotlinx.coroutines.Job streamingJob;
    private android.net.Uri logExportUri;
    private java.util.ArrayList<org.owntracks.android.ui.status.LogViewerActivity.LogLine> logLines;
    private java.lang.StringBuffer rawLogLines;
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private final kotlinx.coroutines.CoroutineScope coroutineScope = null;
    private android.view.MenuItem clearButton;
    private final kotlin.Lazy year$delegate = null;
    private static final java.util.regex.Pattern THREADTIME_LINE = null;
    private static final java.util.Map<java.lang.String, byte[]> LOGS = null;
    private static final int SHARE_ACTIVITY_REQUEST = 45043;
    @org.jetbrains.annotations.NotNull()
    public static final org.owntracks.android.ui.status.LogViewerActivity.Companion Companion = null;
    
    private final java.lang.String getYear() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.Nullable()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onPrepareOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final java.lang.String getRandomHexString() {
        return null;
    }
    
    private final void revokeExportUriPermissions() {
    }
    
    private final java.util.Date parseTime(java.lang.String timeStr) {
        return null;
    }
    
    private final org.owntracks.android.ui.status.LogViewerActivity.LogLine parseLine(java.lang.String line) {
        return null;
    }
    
    public LogViewerActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001b\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001c\u001a\u00020\bH\u00c6\u0003JG\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010!\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\"\u001a\u00020\bH\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\n\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\r\"\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006#"}, d2 = {"Lorg/owntracks/android/ui/status/LogViewerActivity$LogLine;", "", "pid", "", "tid", "time", "Ljava/util/Date;", "level", "", "tag", "msg", "(IILjava/util/Date;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getLevel", "()Ljava/lang/String;", "getMsg", "setMsg", "(Ljava/lang/String;)V", "getPid", "()I", "getTag", "getTid", "getTime", "()Ljava/util/Date;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
    static final class LogLine {
        private final int pid = 0;
        private final int tid = 0;
        @org.jetbrains.annotations.Nullable()
        private final java.util.Date time = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String level = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String tag = null;
        @org.jetbrains.annotations.NotNull()
        private java.lang.String msg;
        
        public final int getPid() {
            return 0;
        }
        
        public final int getTid() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date getTime() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLevel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTag() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getMsg() {
            return null;
        }
        
        public final void setMsg(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        public LogLine(int pid, int tid, @org.jetbrains.annotations.Nullable()
        java.util.Date time, @org.jetbrains.annotations.NotNull()
        java.lang.String level, @org.jetbrains.annotations.NotNull()
        java.lang.String tag, @org.jetbrains.annotations.NotNull()
        java.lang.String msg) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int component2() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.owntracks.android.ui.status.LogViewerActivity.LogLine copy(int pid, int tid, @org.jetbrains.annotations.Nullable()
        java.util.Date time, @org.jetbrains.annotations.NotNull()
        java.lang.String level, @org.jetbrains.annotations.NotNull()
        java.lang.String tag, @org.jetbrains.annotations.NotNull()
        java.lang.String msg) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object p0) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u0010\u0012\f\u0012\n0\u0002R\u00060\u0000R\u00020\u00030\u0001:\u0001)B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u001d\u001a\u00020\u0006H\u0016J\u0010\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 H\u0002J \u0010!\u001a\u00020\"2\u000e\u0010#\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010$\u001a\u00020\u0006H\u0016J \u0010%\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\u0006H\u0016R!\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\f\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR!\u0010\r\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\u0012\n\u0004\b\u0010\u0010\f\u0012\u0004\b\u000e\u0010\b\u001a\u0004\b\u000f\u0010\nR!\u0010\u0011\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\f\u0012\u0004\b\u0012\u0010\b\u001a\u0004\b\u0013\u0010\nR!\u0010\u0015\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\u0012\n\u0004\b\u0018\u0010\f\u0012\u0004\b\u0016\u0010\b\u001a\u0004\b\u0017\u0010\nR!\u0010\u0019\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\u0012\n\u0004\b\u001c\u0010\f\u0012\u0004\b\u001a\u0010\b\u001a\u0004\b\u001b\u0010\n\u00a8\u0006*"}, d2 = {"Lorg/owntracks/android/ui/status/LogViewerActivity$LogEntryAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/owntracks/android/ui/status/LogViewerActivity$LogEntryAdapter$ViewHolder;", "Lorg/owntracks/android/ui/status/LogViewerActivity;", "(Lorg/owntracks/android/ui/status/LogViewerActivity;)V", "debugColor", "", "getDebugColor$annotations", "()V", "getDebugColor", "()I", "debugColor$delegate", "Lkotlin/Lazy;", "defaultColor", "getDefaultColor$annotations", "getDefaultColor", "defaultColor$delegate", "errorColor", "getErrorColor$annotations", "getErrorColor", "errorColor$delegate", "infoColor", "getInfoColor$annotations", "getInfoColor", "infoColor$delegate", "warningColor", "getWarningColor$annotations", "getWarningColor", "warningColor$delegate", "getItemCount", "levelToColor", "level", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "app_debug"})
    final class LogEntryAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<org.owntracks.android.ui.status.LogViewerActivity.LogEntryAdapter.ViewHolder> {
        private final kotlin.Lazy defaultColor$delegate = null;
        private final kotlin.Lazy debugColor$delegate = null;
        private final kotlin.Lazy errorColor$delegate = null;
        private final kotlin.Lazy infoColor$delegate = null;
        private final kotlin.Lazy warningColor$delegate = null;
        
        @kotlin.Suppress(names = {"Deprecation"})
        @java.lang.Deprecated()
        private static void getDefaultColor$annotations() {
        }
        
        private final int getDefaultColor() {
            return 0;
        }
        
        @kotlin.Suppress(names = {"Deprecation"})
        @java.lang.Deprecated()
        private static void getDebugColor$annotations() {
        }
        
        private final int getDebugColor() {
            return 0;
        }
        
        @kotlin.Suppress(names = {"Deprecation"})
        @java.lang.Deprecated()
        private static void getErrorColor$annotations() {
        }
        
        private final int getErrorColor() {
            return 0;
        }
        
        @kotlin.Suppress(names = {"Deprecation"})
        @java.lang.Deprecated()
        private static void getInfoColor$annotations() {
        }
        
        private final int getInfoColor() {
            return 0;
        }
        
        @kotlin.Suppress(names = {"Deprecation"})
        @java.lang.Deprecated()
        private static void getWarningColor$annotations() {
        }
        
        private final int getWarningColor() {
            return 0;
        }
        
        private final int levelToColor(java.lang.String level) {
            return 0;
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.owntracks.android.ui.status.LogViewerActivity.LogEntryAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        org.owntracks.android.ui.status.LogViewerActivity.LogEntryAdapter.ViewHolder holder, int position) {
        }
        
        public LogEntryAdapter() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lorg/owntracks/android/ui/status/LogViewerActivity$LogEntryAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "layout", "Landroid/view/View;", "isSingleLine", "", "(Lorg/owntracks/android/ui/status/LogViewerActivity$LogEntryAdapter;Landroid/view/View;Z)V", "()Z", "setSingleLine", "(Z)V", "getLayout", "()Landroid/view/View;", "app_debug"})
        final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            @org.jetbrains.annotations.NotNull()
            private final android.view.View layout = null;
            private boolean isSingleLine;
            
            @org.jetbrains.annotations.NotNull()
            public final android.view.View getLayout() {
                return null;
            }
            
            public final boolean isSingleLine() {
                return false;
            }
            
            public final void setSingleLine(boolean p0) {
            }
            
            public ViewHolder(@org.jetbrains.annotations.NotNull()
            android.view.View layout, boolean isSingleLine) {
                super(null);
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J1\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\nH\u0016\u00a2\u0006\u0002\u0010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001c\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\bH\u0016JO\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0005\u001a\u00020\u00062\u0010\u0010\u0019\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\bH\u0016\u00a2\u0006\u0002\u0010\u001bJ;\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\nH\u0016\u00a2\u0006\u0002\u0010\u001d\u00a8\u0006\u001e"}, d2 = {"Lorg/owntracks/android/ui/status/LogViewerActivity$ExportedLogContentProvider;", "Landroid/content/ContentProvider;", "()V", "delete", "", "uri", "Landroid/net/Uri;", "selection", "", "selectionArgs", "", "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", "getType", "insert", "values", "Landroid/content/ContentValues;", "logForUri", "", "onCreate", "", "openFile", "Landroid/os/ParcelFileDescriptor;", "mode", "query", "Landroid/database/Cursor;", "projection", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "update", "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "app_debug"})
    public static final class ExportedLogContentProvider extends android.content.ContentProvider {
        
        private final byte[] logForUri(android.net.Uri uri) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public android.net.Uri insert(@org.jetbrains.annotations.NotNull()
        android.net.Uri uri, @org.jetbrains.annotations.Nullable()
        android.content.ContentValues values) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public android.database.Cursor query(@org.jetbrains.annotations.NotNull()
        android.net.Uri uri, @org.jetbrains.annotations.Nullable()
        java.lang.String[] projection, @org.jetbrains.annotations.Nullable()
        java.lang.String selection, @org.jetbrains.annotations.Nullable()
        java.lang.String[] selectionArgs, @org.jetbrains.annotations.Nullable()
        java.lang.String sortOrder) {
            return null;
        }
        
        @java.lang.Override()
        public boolean onCreate() {
            return false;
        }
        
        @java.lang.Override()
        public int update(@org.jetbrains.annotations.NotNull()
        android.net.Uri uri, @org.jetbrains.annotations.Nullable()
        android.content.ContentValues values, @org.jetbrains.annotations.Nullable()
        java.lang.String selection, @org.jetbrains.annotations.Nullable()
        java.lang.String[] selectionArgs) {
            return 0;
        }
        
        @java.lang.Override()
        public int delete(@org.jetbrains.annotations.NotNull()
        android.net.Uri uri, @org.jetbrains.annotations.Nullable()
        java.lang.String selection, @org.jetbrains.annotations.Nullable()
        java.lang.String[] selectionArgs) {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.String getType(@org.jetbrains.annotations.NotNull()
        android.net.Uri uri) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public android.os.ParcelFileDescriptor openFile(@org.jetbrains.annotations.NotNull()
        android.net.Uri uri, @org.jetbrains.annotations.NotNull()
        java.lang.String mode) {
            return null;
        }
        
        public ExportedLogContentProvider() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lorg/owntracks/android/ui/status/LogViewerActivity$Companion;", "", "()V", "LOGS", "", "", "", "SHARE_ACTIVITY_REQUEST", "", "THREADTIME_LINE", "Ljava/util/regex/Pattern;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
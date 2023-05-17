package net.programmierecke.radiodroid2.recording;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;
import net.programmierecke.radiodroid2.BuildConfig;
import net.programmierecke.radiodroid2.R;
import java.io.File;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RecordingsAdapter extends RecyclerView.Adapter<RecordingsAdapter.RecordingItemViewHolder> {

    static final String TAG = "RecordingsAdapter";

    class RecordingItemViewHolder extends RecyclerView.ViewHolder {

        final ViewGroup viewRoot;

        final TextView textViewTitle;

        final TextView textViewTime;

        private RecordingItemViewHolder(View itemView) {
            super(itemView);
            viewRoot = (ViewGroup) itemView;
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewTime = itemView.findViewById(R.id.textViewTime);
        }
    }

    private Context context;

    private List<DataRecording> recordings;

    public RecordingsAdapter(@NonNull Context context) {
        if (!ListenerUtil.mutListener.listen(1583)) {
            this.context = context;
        }
    }

    @NonNull
    @Override
    public RecordingsAdapter.RecordingItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item_recording, parent, false);
        return new RecordingItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordingItemViewHolder holder, int position) {
        final DataRecording recording = recordings.get(position);
        if (!ListenerUtil.mutListener.listen(1584)) {
            holder.textViewTitle.setText(recording.Name);
        }
        if (!ListenerUtil.mutListener.listen(1586)) {
            holder.viewRoot.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(1585)) {
                        openRecording(recording);
                    }
                }
            });
        }
    }

    public void setRecordings(List<DataRecording> recordings) {
        if (!ListenerUtil.mutListener.listen(1597)) {
            if ((ListenerUtil.mutListener.listen(1587) ? (this.recordings != null || recordings.size() == this.recordings.size()) : (this.recordings != null && recordings.size() == this.recordings.size()))) {
                boolean same = true;
                if (!ListenerUtil.mutListener.listen(1595)) {
                    {
                        long _loopCounter29 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(1594) ? (i >= recordings.size()) : (ListenerUtil.mutListener.listen(1593) ? (i <= recordings.size()) : (ListenerUtil.mutListener.listen(1592) ? (i > recordings.size()) : (ListenerUtil.mutListener.listen(1591) ? (i != recordings.size()) : (ListenerUtil.mutListener.listen(1590) ? (i == recordings.size()) : (i < recordings.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter29", ++_loopCounter29);
                            if (!ListenerUtil.mutListener.listen(1589)) {
                                if (!recordings.get(i).equals(this.recordings.get(i))) {
                                    if (!ListenerUtil.mutListener.listen(1588)) {
                                        same = false;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1596)) {
                    if (same) {
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1598)) {
            this.recordings = recordings;
        }
        if (!ListenerUtil.mutListener.listen(1599)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return recordings != null ? recordings.size() : 0;
    }

    void openRecording(DataRecording theData) {
        String path = RecordingsManager.getRecordDir() + "/" + theData.Name;
        if (!ListenerUtil.mutListener.listen(1601)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(1600)) {
                    Log.d(TAG, "play: " + path);
                }
            }
        }
        Intent i = new Intent(path);
        if (!ListenerUtil.mutListener.listen(1602)) {
            i.setAction(android.content.Intent.ACTION_VIEW);
        }
        File file = new File(path);
        Uri fileUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
        if (!ListenerUtil.mutListener.listen(1603)) {
            i.setDataAndType(fileUri, "audio/*");
        }
        if (!ListenerUtil.mutListener.listen(1619)) {
            if ((ListenerUtil.mutListener.listen(1608) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1607) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1606) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1605) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1604) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(1618)) {
                    i.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
            } else if ((ListenerUtil.mutListener.listen(1613) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(1612) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(1611) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(1610) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(1609) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN))))))) {
                ClipData clip = ClipData.newUri(context.getContentResolver(), "Record", fileUri);
                if (!ListenerUtil.mutListener.listen(1616)) {
                    i.setClipData(clip);
                }
                if (!ListenerUtil.mutListener.listen(1617)) {
                    i.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
            } else {
                List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(i, PackageManager.MATCH_DEFAULT_ONLY);
                if (!ListenerUtil.mutListener.listen(1615)) {
                    {
                        long _loopCounter30 = 0;
                        for (ResolveInfo resolveInfo : resInfoList) {
                            ListenerUtil.loopListener.listen("_loopCounter30", ++_loopCounter30);
                            String packageName = resolveInfo.activityInfo.packageName;
                            if (!ListenerUtil.mutListener.listen(1614)) {
                                context.grantUriPermission(packageName, fileUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1620)) {
            context.startActivity(i);
        }
    }
}

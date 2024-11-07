package org.wordpress.android.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import org.wordpress.android.R;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.ToastUtils;
import java.util.ArrayList;
import java.util.Locale;
import static java.lang.String.format;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * views the activity log (see utils/AppLog.java)
 */
public class AppLogViewerActivity extends LocaleAwareActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(25916)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(25917)) {
            setContentView(R.layout.logviewer_activity);
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(25918)) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(25922)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(25919)) {
                    actionBar.setDisplayShowTitleEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(25920)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(25921)) {
                    actionBar.setTitle(R.string.reader_title_applog);
                }
            }
        }
        final ListView listView = (ListView) findViewById(android.R.id.list);
        if (!ListenerUtil.mutListener.listen(25923)) {
            listView.setAdapter(new LogAdapter(this));
        }
    }

    private class LogAdapter extends BaseAdapter {

        private final ArrayList<String> mEntries;

        private final LayoutInflater mInflater;

        private LogAdapter(Context context) {
            mEntries = AppLog.toHtmlList(context);
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mEntries.size();
        }

        @Override
        public Object getItem(int position) {
            return mEntries.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final LogViewHolder holder;
            if (convertView == null) {
                if (!ListenerUtil.mutListener.listen(25924)) {
                    convertView = mInflater.inflate(R.layout.logviewer_listitem, parent, false);
                }
                holder = new LogViewHolder(convertView);
                if (!ListenerUtil.mutListener.listen(25925)) {
                    convertView.setTag(holder);
                }
            } else {
                holder = (LogViewHolder) convertView.getTag();
            }
            // line numbers shown here won't match the line numbers when the log is shared
            int lineNum = (ListenerUtil.mutListener.listen(25929) ? (position % AppLog.HEADER_LINE_COUNT) : (ListenerUtil.mutListener.listen(25928) ? (position / AppLog.HEADER_LINE_COUNT) : (ListenerUtil.mutListener.listen(25927) ? (position * AppLog.HEADER_LINE_COUNT) : (ListenerUtil.mutListener.listen(25926) ? (position + AppLog.HEADER_LINE_COUNT) : (position - AppLog.HEADER_LINE_COUNT))))) + 1;
            if (!ListenerUtil.mutListener.listen(25938)) {
                if ((ListenerUtil.mutListener.listen(25934) ? (lineNum >= 0) : (ListenerUtil.mutListener.listen(25933) ? (lineNum <= 0) : (ListenerUtil.mutListener.listen(25932) ? (lineNum < 0) : (ListenerUtil.mutListener.listen(25931) ? (lineNum != 0) : (ListenerUtil.mutListener.listen(25930) ? (lineNum == 0) : (lineNum > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(25936)) {
                        holder.mTxtLineNumber.setText(format(Locale.US, "%02d", lineNum));
                    }
                    if (!ListenerUtil.mutListener.listen(25937)) {
                        holder.mTxtLineNumber.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(25935)) {
                        holder.mTxtLineNumber.setVisibility(View.GONE);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(25939)) {
                holder.mTxtLogEntry.setText(Html.fromHtml(mEntries.get(position)));
            }
            return convertView;
        }

        private class LogViewHolder {

            private final TextView mTxtLineNumber;

            private final TextView mTxtLogEntry;

            LogViewHolder(View view) {
                mTxtLineNumber = (TextView) view.findViewById(R.id.text_line);
                mTxtLogEntry = (TextView) view.findViewById(R.id.text_log);
            }
        }
    }

    private void shareAppLog() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (!ListenerUtil.mutListener.listen(25940)) {
            intent.setType("text/plain");
        }
        if (!ListenerUtil.mutListener.listen(25941)) {
            intent.putExtra(Intent.EXTRA_TEXT, AppLog.toPlainText(this));
        }
        if (!ListenerUtil.mutListener.listen(25942)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name) + " " + getTitle());
        }
        try {
            if (!ListenerUtil.mutListener.listen(25944)) {
                startActivity(Intent.createChooser(intent, getString(R.string.reader_btn_share)));
            }
        } catch (android.content.ActivityNotFoundException ex) {
            if (!ListenerUtil.mutListener.listen(25943)) {
                ToastUtils.showToast(this, R.string.reader_toast_err_share_intent);
            }
        }
    }

    private void copyAppLogToClipboard() {
        try {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            if (!ListenerUtil.mutListener.listen(25947)) {
                clipboard.setPrimaryClip(ClipData.newPlainText("AppLog", AppLog.toPlainText(this)));
            }
            if (!ListenerUtil.mutListener.listen(25948)) {
                ToastUtils.showToast(this, R.string.logs_copied_to_clipboard);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(25945)) {
                AppLog.e(T.UTILS, e);
            }
            if (!ListenerUtil.mutListener.listen(25946)) {
                ToastUtils.showToast(this, R.string.error_copy_to_clipboard);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(25949)) {
            super.onCreateOptionsMenu(menu);
        }
        MenuInflater inflater = getMenuInflater();
        if (!ListenerUtil.mutListener.listen(25950)) {
            inflater.inflate(R.menu.app_log_viewer_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                if (!ListenerUtil.mutListener.listen(25951)) {
                    finish();
                }
                return true;
            case R.id.app_log_share:
                if (!ListenerUtil.mutListener.listen(25952)) {
                    shareAppLog();
                }
                return true;
            case R.id.app_log_copy_to_clipboard:
                if (!ListenerUtil.mutListener.listen(25953)) {
                    copyAppLogToClipboard();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

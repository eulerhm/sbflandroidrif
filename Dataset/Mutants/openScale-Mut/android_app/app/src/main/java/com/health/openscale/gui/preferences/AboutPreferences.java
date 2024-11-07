/* Copyright (C) 2014  olie.xdev <olie.xdev@googlemail.com>
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>
*/
package com.health.openscale.gui.preferences;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.health.openscale.BuildConfig;
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import timber.log.Timber;
import static android.app.Activity.RESULT_OK;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AboutPreferences extends PreferenceFragmentCompat {

    private static final String KEY_APP_VERSION = "pref_app_version";

    private static final String KEY_DEBUG_LOG = "debug_log";

    private static final int DEBUG_LOG_REQUEST = 100;

    private CheckBoxPreference debugLog;

    class FileDebugTree extends Timber.DebugTree {

        PrintWriter writer;

        DateFormat format;

        FileDebugTree(OutputStream output) {
            if (!ListenerUtil.mutListener.listen(8110)) {
                writer = new PrintWriter(output, true);
            }
            if (!ListenerUtil.mutListener.listen(8111)) {
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            }
        }

        void close() {
            if (!ListenerUtil.mutListener.listen(8112)) {
                writer.close();
            }
        }

        private String priorityToString(int priority) {
            if (!ListenerUtil.mutListener.listen(8113)) {
                switch(priority) {
                    case Log.ASSERT:
                        return "Assert";
                    case Log.ERROR:
                        return "Error";
                    case Log.WARN:
                        return "Warning";
                    case Log.INFO:
                        return "Info";
                    case Log.DEBUG:
                        return "Debug";
                    case Log.VERBOSE:
                        return "Verbose";
                }
            }
            return String.format("Unknown (%d)", priority);
        }

        @Override
        protected synchronized void log(int priority, String tag, String message, Throwable t) {
            final long id = Thread.currentThread().getId();
            if (!ListenerUtil.mutListener.listen(8114)) {
                writer.printf("%s %s [%d] %s: %s\r\n", format.format(new Date()), priorityToString(priority), id, tag, message);
            }
        }
    }

    private FileDebugTree getEnabledFileDebugTree() {
        if (!ListenerUtil.mutListener.listen(8116)) {
            {
                long _loopCounter98 = 0;
                for (Timber.Tree tree : Timber.forest()) {
                    ListenerUtil.loopListener.listen("_loopCounter98", ++_loopCounter98);
                    if (!ListenerUtil.mutListener.listen(8115)) {
                        if (tree instanceof FileDebugTree) {
                            return (FileDebugTree) tree;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        if (!ListenerUtil.mutListener.listen(8117)) {
            setPreferencesFromResource(R.xml.about_preferences, rootKey);
        }
        if (!ListenerUtil.mutListener.listen(8118)) {
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(8119)) {
            findPreference(KEY_APP_VERSION).setSummary(String.format("v%s (%d)", BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));
        }
        if (!ListenerUtil.mutListener.listen(8120)) {
            debugLog = (CheckBoxPreference) findPreference(KEY_DEBUG_LOG);
        }
        if (!ListenerUtil.mutListener.listen(8121)) {
            debugLog.setChecked(getEnabledFileDebugTree() != null);
        }
        if (!ListenerUtil.mutListener.listen(8132)) {
            debugLog.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (!ListenerUtil.mutListener.listen(8131)) {
                        if (debugLog.isChecked()) {
                            DateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
                            String fileName = String.format("openScale_%s.txt", format.format(new Date()));
                            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                            if (!ListenerUtil.mutListener.listen(8127)) {
                                intent.addCategory(Intent.CATEGORY_OPENABLE);
                            }
                            if (!ListenerUtil.mutListener.listen(8128)) {
                                intent.setType("text/plain");
                            }
                            if (!ListenerUtil.mutListener.listen(8129)) {
                                intent.putExtra(Intent.EXTRA_TITLE, fileName);
                            }
                            if (!ListenerUtil.mutListener.listen(8130)) {
                                startActivityForResult(intent, DEBUG_LOG_REQUEST);
                            }
                        } else {
                            FileDebugTree tree = getEnabledFileDebugTree();
                            if (!ListenerUtil.mutListener.listen(8126)) {
                                if (tree != null) {
                                    if (!ListenerUtil.mutListener.listen(8122)) {
                                        Timber.d("Debug log disabled");
                                    }
                                    if (!ListenerUtil.mutListener.listen(8123)) {
                                        Timber.uproot(tree);
                                    }
                                    if (!ListenerUtil.mutListener.listen(8124)) {
                                        tree.close();
                                    }
                                    if (!ListenerUtil.mutListener.listen(8125)) {
                                        OpenScale.DEBUG_MODE = false;
                                    }
                                }
                            }
                        }
                    }
                    return true;
                }
            });
        }
    }

    private void startLogTo(Uri uri) {
        try {
            OutputStream output = getActivity().getContentResolver().openOutputStream(uri);
            if (!ListenerUtil.mutListener.listen(8134)) {
                Timber.plant(new FileDebugTree(output));
            }
            if (!ListenerUtil.mutListener.listen(8135)) {
                OpenScale.DEBUG_MODE = true;
            }
            if (!ListenerUtil.mutListener.listen(8136)) {
                Timber.d("Debug log enabled, %s v%s (%d), SDK %d, %s %s", getResources().getString(R.string.app_name), BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE, Build.VERSION.SDK_INT, Build.MANUFACTURER, Build.MODEL);
            }
            if (!ListenerUtil.mutListener.listen(8137)) {
                Timber.d("Selected user " + OpenScale.getInstance().getSelectedScaleUser());
            }
        } catch (IOException ex) {
            if (!ListenerUtil.mutListener.listen(8133)) {
                Timber.e(ex, "Failed to open debug log %s", uri.toString());
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(8138)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(8147)) {
            if ((ListenerUtil.mutListener.listen(8145) ? ((ListenerUtil.mutListener.listen(8144) ? ((ListenerUtil.mutListener.listen(8143) ? (requestCode >= DEBUG_LOG_REQUEST) : (ListenerUtil.mutListener.listen(8142) ? (requestCode <= DEBUG_LOG_REQUEST) : (ListenerUtil.mutListener.listen(8141) ? (requestCode > DEBUG_LOG_REQUEST) : (ListenerUtil.mutListener.listen(8140) ? (requestCode < DEBUG_LOG_REQUEST) : (ListenerUtil.mutListener.listen(8139) ? (requestCode != DEBUG_LOG_REQUEST) : (requestCode == DEBUG_LOG_REQUEST)))))) || resultCode == RESULT_OK) : ((ListenerUtil.mutListener.listen(8143) ? (requestCode >= DEBUG_LOG_REQUEST) : (ListenerUtil.mutListener.listen(8142) ? (requestCode <= DEBUG_LOG_REQUEST) : (ListenerUtil.mutListener.listen(8141) ? (requestCode > DEBUG_LOG_REQUEST) : (ListenerUtil.mutListener.listen(8140) ? (requestCode < DEBUG_LOG_REQUEST) : (ListenerUtil.mutListener.listen(8139) ? (requestCode != DEBUG_LOG_REQUEST) : (requestCode == DEBUG_LOG_REQUEST)))))) && resultCode == RESULT_OK)) || data != null) : ((ListenerUtil.mutListener.listen(8144) ? ((ListenerUtil.mutListener.listen(8143) ? (requestCode >= DEBUG_LOG_REQUEST) : (ListenerUtil.mutListener.listen(8142) ? (requestCode <= DEBUG_LOG_REQUEST) : (ListenerUtil.mutListener.listen(8141) ? (requestCode > DEBUG_LOG_REQUEST) : (ListenerUtil.mutListener.listen(8140) ? (requestCode < DEBUG_LOG_REQUEST) : (ListenerUtil.mutListener.listen(8139) ? (requestCode != DEBUG_LOG_REQUEST) : (requestCode == DEBUG_LOG_REQUEST)))))) || resultCode == RESULT_OK) : ((ListenerUtil.mutListener.listen(8143) ? (requestCode >= DEBUG_LOG_REQUEST) : (ListenerUtil.mutListener.listen(8142) ? (requestCode <= DEBUG_LOG_REQUEST) : (ListenerUtil.mutListener.listen(8141) ? (requestCode > DEBUG_LOG_REQUEST) : (ListenerUtil.mutListener.listen(8140) ? (requestCode < DEBUG_LOG_REQUEST) : (ListenerUtil.mutListener.listen(8139) ? (requestCode != DEBUG_LOG_REQUEST) : (requestCode == DEBUG_LOG_REQUEST)))))) && resultCode == RESULT_OK)) && data != null))) {
                if (!ListenerUtil.mutListener.listen(8146)) {
                    startLogTo(data.getData());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8148)) {
            debugLog.setChecked(getEnabledFileDebugTree() != null);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(8149)) {
            menu.clear();
        }
    }
}

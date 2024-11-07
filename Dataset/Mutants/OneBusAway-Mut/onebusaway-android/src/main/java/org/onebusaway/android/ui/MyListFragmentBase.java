/*
 * Copyright (C) 2011 Paul Watts (paulcwatts@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.ui;

import org.onebusaway.android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.DialogFragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Base class for the stop/route list fragments.
 * Immediate base class for MyStopListFragmentBase/MyRouteListFragmentBase
 * Ancestor of:
 * MyRecentRoutesFragment
 * MyRecentStopsFragment
 * MyStarredStopsFragment
 *
 * @author paulw
 */
abstract class MyListFragmentBase extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>, MyListConstants {

    private static final Handler mHandler = new Handler();

    private class Observer extends ContentObserver {

        Observer() {
            super(mHandler);
        }

        @Override
        public boolean deliverSelfNotifications() {
            return false;
        }

        public void onChange(boolean selfChange) {
            if (!ListenerUtil.mutListener.listen(1291)) {
                if (isAdded()) {
                    if (!ListenerUtil.mutListener.listen(1290)) {
                        getLoaderManager().restartLoader(0, null, MyListFragmentBase.this);
                    }
                }
            }
        }
    }

    protected SimpleCursorAdapter mAdapter;

    private Observer mObserver;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1292)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1293)) {
            // Set empty text
            setEmptyText(getString(getEmptyText()));
        }
        if (!ListenerUtil.mutListener.listen(1294)) {
            registerForContextMenu(getListView());
        }
        if (!ListenerUtil.mutListener.listen(1295)) {
            // Create our generic adapter
            mAdapter = newAdapter();
        }
        if (!ListenerUtil.mutListener.listen(1296)) {
            setListAdapter(mAdapter);
        }
        ContentResolver cr = getActivity().getContentResolver();
        if (!ListenerUtil.mutListener.listen(1297)) {
            mObserver = new Observer();
        }
        if (!ListenerUtil.mutListener.listen(1298)) {
            cr.registerContentObserver(getContentUri(), true, mObserver);
        }
        if (!ListenerUtil.mutListener.listen(1299)) {
            // Prepare the loader
            getLoaderManager().initLoader(0, null, this);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1300)) {
            super.onViewCreated(view, savedInstanceState);
        }
        ListView listView = getListView();
        if (!ListenerUtil.mutListener.listen(1301)) {
            listView.setBackgroundColor(getResources().getColor(R.color.listview_background));
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(1304)) {
            if (mObserver != null) {
                ContentResolver cr = getActivity().getContentResolver();
                if (!ListenerUtil.mutListener.listen(1302)) {
                    cr.unregisterContentObserver(mObserver);
                }
                if (!ListenerUtil.mutListener.listen(1303)) {
                    mObserver = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1305)) {
            super.onDestroy();
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (!ListenerUtil.mutListener.listen(1306)) {
            mAdapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (!ListenerUtil.mutListener.listen(1307)) {
            mAdapter.swapCursor(null);
        }
    }

    protected boolean isShortcutMode() {
        Activity act = getActivity();
        if (!ListenerUtil.mutListener.listen(1308)) {
            if (act instanceof MyTabActivityBase) {
                MyTabActivityBase base = (MyTabActivityBase) act;
                return base.isShortcutMode();
            }
        }
        return false;
    }

    protected abstract static class ClearConfirmDialog extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity()).setMessage(R.string.my_option_clear_confirm).setTitle(R.string.my_option_clear_confirm_title).setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(1310)) {
                        doClear();
                    }
                }
            }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(1309)) {
                        dialog.dismiss();
                    }
                }
            }).create();
        }

        protected abstract void doClear();
    }

    // 
    protected abstract SimpleCursorAdapter newAdapter();

    // 
    protected abstract Uri getContentUri();

    // 
    protected abstract int getEmptyText();
}

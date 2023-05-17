/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.activities;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import ch.threema.app.R;
import ch.threema.app.adapters.StickerSelectorAdapter;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class StickerSelectorActivity extends ThreemaToolbarActivity implements LoaderManager.LoaderCallbacks<String[]> {

    private static final Logger logger = LoggerFactory.getLogger(StickerSelectorActivity.class);

    private static final String STICKER_DIRECTORY = "emojione";

    private static final String STICKER_INDEX = STICKER_DIRECTORY + "/contents.txt";

    public static final String EXTRA_STICKER_PATH = "spath";

    private GridView gridView;

    private StickerSelectorAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6681)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(6682)) {
            gridView = findViewById(R.id.grid_view);
        }
        if (!ListenerUtil.mutListener.listen(6687)) {
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (!ListenerUtil.mutListener.listen(6686)) {
                        if (adapter != null) {
                            Intent intent = new Intent();
                            if (!ListenerUtil.mutListener.listen(6683)) {
                                intent.putExtra(EXTRA_STICKER_PATH, adapter.getItem(i));
                            }
                            if (!ListenerUtil.mutListener.listen(6684)) {
                                setResult(RESULT_OK, intent);
                            }
                            if (!ListenerUtil.mutListener.listen(6685)) {
                                finish();
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6688)) {
            getLoaderManager().initLoader(0, null, this).forceLoad();
        }
    }

    private static class StickerLoader extends AsyncTaskLoader<String[]> {

        StickerLoader(Context context) {
            super(context);
        }

        @Override
        public String[] loadInBackground() {
            // AssetManager.getAssets().list is notoriously slow on some phones, so we use a list file to get the filenames quickly
            try {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader((getContext().getAssets().open(STICKER_INDEX))))) {
                    List<String> files = new ArrayList<>();
                    String line;
                    if (!ListenerUtil.mutListener.listen(6691)) {
                        {
                            long _loopCounter55 = 0;
                            while ((line = reader.readLine()) != null) {
                                ListenerUtil.loopListener.listen("_loopCounter55", ++_loopCounter55);
                                if (!ListenerUtil.mutListener.listen(6690)) {
                                    files.add(STICKER_DIRECTORY + "/" + line);
                                }
                            }
                        }
                    }
                    return files.toArray(new String[0]);
                }
            } catch (IOException e) {
                if (!ListenerUtil.mutListener.listen(6689)) {
                    logger.error("Exception", e);
                }
            }
            return new String[0];
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_sticker_selector;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(6693)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(6692)) {
                        finish();
                    }
                    break;
            }
        }
        return false;
    }

    @Override
    public Loader<String[]> onCreateLoader(int id, Bundle args) {
        return new StickerLoader(getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<String[]> loader, String[] data) {
        if (!ListenerUtil.mutListener.listen(6694)) {
            adapter = new StickerSelectorAdapter(this, data);
        }
        if (!ListenerUtil.mutListener.listen(6695)) {
            gridView.setAdapter(adapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<String[]> loader) {
        if (!ListenerUtil.mutListener.listen(6696)) {
            gridView.setAdapter(null);
        }
    }
}

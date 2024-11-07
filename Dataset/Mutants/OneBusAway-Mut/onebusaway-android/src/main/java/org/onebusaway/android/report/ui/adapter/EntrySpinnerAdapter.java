/*
* Copyright (C) 2015 University of South Florida (sjbarbeau@gmail.com)
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
package org.onebusaway.android.report.ui.adapter;

import org.onebusaway.android.R;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EntrySpinnerAdapter implements SpinnerAdapter {

    private ArrayList<SpinnerItem> mSpinnerItems;

    private LayoutInflater vi;

    public EntrySpinnerAdapter(Context ctx, ArrayList<SpinnerItem> spinnerItems) {
        if (!ListenerUtil.mutListener.listen(10907)) {
            this.mSpinnerItems = spinnerItems;
        }
        if (!ListenerUtil.mutListener.listen(10908)) {
            vi = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
    }

    @Override
    public int getCount() {
        return mSpinnerItems.size();
    }

    @Override
    public SpinnerItem getItem(int pos) {
        return mSpinnerItems.get(pos);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final SpinnerItem i = mSpinnerItems.get(position);
        if (!ListenerUtil.mutListener.listen(10918)) {
            if (i != null) {
                if (!ListenerUtil.mutListener.listen(10917)) {
                    if (i.isSection()) {
                        // Section Header
                        SectionItem si = (SectionItem) i;
                        if (!ListenerUtil.mutListener.listen(10912)) {
                            v = vi.inflate(R.layout.list_item_section, null);
                        }
                        if (!ListenerUtil.mutListener.listen(10913)) {
                            v.setOnClickListener(null);
                        }
                        if (!ListenerUtil.mutListener.listen(10914)) {
                            v.setOnLongClickListener(null);
                        }
                        if (!ListenerUtil.mutListener.listen(10915)) {
                            v.setLongClickable(false);
                        }
                        final TextView sectionView = (TextView) v.findViewById(R.id.list_item_section_text);
                        if (!ListenerUtil.mutListener.listen(10916)) {
                            sectionView.setText(si.getTitle());
                        }
                    } else {
                        ServiceSpinnerItem ei = (ServiceSpinnerItem) i;
                        if (!ListenerUtil.mutListener.listen(10909)) {
                            v = vi.inflate(R.layout.list_item_entry, null);
                        }
                        final TextView title = (TextView) v.findViewById(R.id.list_item_entry_title);
                        if (!ListenerUtil.mutListener.listen(10911)) {
                            if (title != null)
                                if (!ListenerUtil.mutListener.listen(10910)) {
                                    title.setText(ei.getService().getService_name());
                                }
                        }
                    }
                }
            }
        }
        return v;
    }

    @Override
    public int getItemViewType(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return this.getView(position, convertView, parent);
    }
}

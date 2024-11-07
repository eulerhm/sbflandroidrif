/*
 * Copyright (C) 2012 Paul Watts (paulcwatts@gmail.com)
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
package org.onebusaway.android.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A helper that provides a bit more common functionality than the base ArrayAdapter,
 * and provides an addAll() on non-Honeycomb.
 *
 * @author paulw
 */
public abstract class ArrayAdapter<T> extends android.widget.ArrayAdapter<T> {

    private final LayoutInflater mInflater;

    private final int mLayoutId;

    public ArrayAdapter(Context context, int layout) {
        super(context, layout);
        mLayoutId = layout;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<T> data) {
        if (!ListenerUtil.mutListener.listen(7552)) {
            // This prevents list from scrolling back to top on clear()
            setNotifyOnChange(false);
        }
        if (!ListenerUtil.mutListener.listen(7553)) {
            clear();
        }
        if (!ListenerUtil.mutListener.listen(7555)) {
            if (data != null) {
                if (!ListenerUtil.mutListener.listen(7554)) {
                    addAll(data);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7556)) {
            // Since we're calling setNotifyOnChange(false), we need to call notifyDataSetChanged() ourselves
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = mInflater.inflate(mLayoutId, parent, false);
        } else {
            view = convertView;
        }
        T item = getItem(position);
        if (!ListenerUtil.mutListener.listen(7557)) {
            initView(view, item);
        }
        return view;
    }

    protected LayoutInflater getLayoutInflater() {
        return mInflater;
    }

    protected abstract void initView(View view, T t);
}

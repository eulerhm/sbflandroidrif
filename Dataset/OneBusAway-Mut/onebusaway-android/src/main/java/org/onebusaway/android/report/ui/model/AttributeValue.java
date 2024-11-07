/*
 * Copyright (C) 2015-2017 University of South Florida (sjbarbeau@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.report.ui.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AttributeValue implements Parcelable {

    private Integer code;

    private ArrayList<String> values = new ArrayList<>();

    public AttributeValue(Integer code) {
        if (!ListenerUtil.mutListener.listen(10963)) {
            this.code = code;
        }
    }

    protected AttributeValue(Parcel in) {
        if (!ListenerUtil.mutListener.listen(10964)) {
            code = in.readInt();
        }
    }

    public static final Creator<AttributeValue> CREATOR = new Creator<AttributeValue>() {

        @Override
        public AttributeValue createFromParcel(Parcel in) {
            return new AttributeValue(in);
        }

        @Override
        public AttributeValue[] newArray(int size) {
            return new AttributeValue[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (!ListenerUtil.mutListener.listen(10965)) {
            parcel.writeInt(code);
        }
        try {
            if (!ListenerUtil.mutListener.listen(10967)) {
                parcel.writeStringArray((String[]) values.toArray());
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(10966)) {
                e.printStackTrace();
            }
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        if (!ListenerUtil.mutListener.listen(10968)) {
            this.code = code;
        }
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void addValue(String value) {
        if (!ListenerUtil.mutListener.listen(10970)) {
            if (!TextUtils.isEmpty(value)) {
                if (!ListenerUtil.mutListener.listen(10969)) {
                    this.values.add(value);
                }
            }
        }
    }

    public String getSingleValue() {
        if ((ListenerUtil.mutListener.listen(10975) ? (values.size() >= 0) : (ListenerUtil.mutListener.listen(10974) ? (values.size() <= 0) : (ListenerUtil.mutListener.listen(10973) ? (values.size() > 0) : (ListenerUtil.mutListener.listen(10972) ? (values.size() < 0) : (ListenerUtil.mutListener.listen(10971) ? (values.size() != 0) : (values.size() == 0))))))) {
            return "";
        } else {
            return values.get(0);
        }
    }
}

/*
* Copyright (C) 2014-2016 University of South Florida (sjbarbeau@gmail.com)
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

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A model object for MaterialListAdapter
 */
public class MaterialListItem {

    private String title;

    private String desc;

    private String id;

    private int icon;

    public MaterialListItem(String title, String desc, int icon) {
        if (!ListenerUtil.mutListener.listen(2156)) {
            this.title = title;
        }
        if (!ListenerUtil.mutListener.listen(2157)) {
            this.desc = desc;
        }
        if (!ListenerUtil.mutListener.listen(2158)) {
            this.icon = icon;
        }
    }

    public MaterialListItem(String title, String desc, String id, int icon) {
        if (!ListenerUtil.mutListener.listen(2159)) {
            this.title = title;
        }
        if (!ListenerUtil.mutListener.listen(2160)) {
            this.desc = desc;
        }
        if (!ListenerUtil.mutListener.listen(2161)) {
            this.id = id;
        }
        if (!ListenerUtil.mutListener.listen(2162)) {
            this.icon = icon;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (!ListenerUtil.mutListener.listen(2163)) {
            this.title = title;
        }
    }

    public String getDesc() {
        return desc;
    }

    @SuppressWarnings("unused")
    public void setDesc(String desc) {
        if (!ListenerUtil.mutListener.listen(2164)) {
            this.desc = desc;
        }
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        if (!ListenerUtil.mutListener.listen(2165)) {
            this.icon = icon;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (!ListenerUtil.mutListener.listen(2166)) {
            this.id = id;
        }
    }
}

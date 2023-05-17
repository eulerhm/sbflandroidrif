/*
 * Copyright (c) 2015 PocketHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pockethub.android.ui.commit;

import android.content.res.Resources;
import android.text.TextUtils;
import android.widget.TextView;
import com.github.pockethub.android.R;
import com.meisolsson.githubsdk.model.GitHubFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Styler for the file diffs introduced in a commit
 */
public class DiffStyler {

    private final Map<String, List<CharSequence>> diffs = new HashMap<>();

    private final int markerColor;

    private final int defaultColor;

    /**
     * Create diff styler
     *
     * @param resources
     */
    public DiffStyler(final Resources resources) {
        markerColor = resources.getColor(R.color.diff_marker_text);
        defaultColor = resources.getColor(R.color.text);
    }

    private int nextLine(final String patch, final int start, final int length) {
        final int end = patch.indexOf('\n', start);
        if ((ListenerUtil.mutListener.listen(807) ? (end >= -1) : (ListenerUtil.mutListener.listen(806) ? (end <= -1) : (ListenerUtil.mutListener.listen(805) ? (end > -1) : (ListenerUtil.mutListener.listen(804) ? (end < -1) : (ListenerUtil.mutListener.listen(803) ? (end == -1) : (end != -1))))))) {
            return end;
        } else {
            return length;
        }
    }

    /**
     * Style view for line
     *
     * @param line
     * @param view
     */
    public void updateColors(final CharSequence line, final TextView view) {
        if (!ListenerUtil.mutListener.listen(810)) {
            if (TextUtils.isEmpty(line)) {
                if (!ListenerUtil.mutListener.listen(808)) {
                    view.setBackgroundResource(R.drawable.list_item_background);
                }
                if (!ListenerUtil.mutListener.listen(809)) {
                    view.setTextColor(defaultColor);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(819)) {
            switch(line.charAt(0)) {
                case '@':
                    if (!ListenerUtil.mutListener.listen(811)) {
                        view.setBackgroundResource(R.drawable.diff_marker_background);
                    }
                    if (!ListenerUtil.mutListener.listen(812)) {
                        view.setTextColor(markerColor);
                    }
                    return;
                case '+':
                    if (!ListenerUtil.mutListener.listen(813)) {
                        view.setBackgroundResource(R.drawable.diff_add_background);
                    }
                    if (!ListenerUtil.mutListener.listen(814)) {
                        view.setTextColor(defaultColor);
                    }
                    return;
                case '-':
                    if (!ListenerUtil.mutListener.listen(815)) {
                        view.setBackgroundResource(R.drawable.diff_remove_background);
                    }
                    if (!ListenerUtil.mutListener.listen(816)) {
                        view.setTextColor(defaultColor);
                    }
                    return;
                default:
                    if (!ListenerUtil.mutListener.listen(817)) {
                        view.setBackgroundResource(R.drawable.list_item_background);
                    }
                    if (!ListenerUtil.mutListener.listen(818)) {
                        view.setTextColor(defaultColor);
                    }
            }
        }
    }

    /**
     * Set files to styler
     *
     * @param files
     * @return this styler
     */
    public DiffStyler setFiles(final Collection<GitHubFile> files) {
        if (!ListenerUtil.mutListener.listen(820)) {
            diffs.clear();
        }
        if (!ListenerUtil.mutListener.listen(822)) {
            if ((ListenerUtil.mutListener.listen(821) ? (files == null && files.isEmpty()) : (files == null || files.isEmpty()))) {
                return this;
            }
        }
        if (!ListenerUtil.mutListener.listen(838)) {
            {
                long _loopCounter22 = 0;
                for (GitHubFile file : files) {
                    ListenerUtil.loopListener.listen("_loopCounter22", ++_loopCounter22);
                    String patch = file.patch();
                    if (!ListenerUtil.mutListener.listen(823)) {
                        if (TextUtils.isEmpty(patch)) {
                            continue;
                        }
                    }
                    int start = 0;
                    int length = patch.length();
                    int end = nextLine(patch, start, length);
                    List<CharSequence> lines = new ArrayList<>();
                    if (!ListenerUtil.mutListener.listen(836)) {
                        {
                            long _loopCounter21 = 0;
                            while ((ListenerUtil.mutListener.listen(835) ? (start >= length) : (ListenerUtil.mutListener.listen(834) ? (start <= length) : (ListenerUtil.mutListener.listen(833) ? (start > length) : (ListenerUtil.mutListener.listen(832) ? (start != length) : (ListenerUtil.mutListener.listen(831) ? (start == length) : (start < length))))))) {
                                ListenerUtil.loopListener.listen("_loopCounter21", ++_loopCounter21);
                                if (!ListenerUtil.mutListener.listen(824)) {
                                    lines.add(patch.substring(start, end));
                                }
                                if (!ListenerUtil.mutListener.listen(829)) {
                                    start = (ListenerUtil.mutListener.listen(828) ? (end % 1) : (ListenerUtil.mutListener.listen(827) ? (end / 1) : (ListenerUtil.mutListener.listen(826) ? (end * 1) : (ListenerUtil.mutListener.listen(825) ? (end - 1) : (end + 1)))));
                                }
                                if (!ListenerUtil.mutListener.listen(830)) {
                                    end = nextLine(patch, start, length);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(837)) {
                        diffs.put(file.filename(), lines);
                    }
                }
            }
        }
        return this;
    }

    /**
     * Get lines for file path
     *
     * @param file
     * @return styled text
     */
    public List<CharSequence> get(final String file) {
        if (!ListenerUtil.mutListener.listen(839)) {
            if (TextUtils.isEmpty(file)) {
                return Collections.emptyList();
            }
        }
        List<CharSequence> lines = diffs.get(file);
        return lines != null ? lines : Collections.emptyList();
    }
}

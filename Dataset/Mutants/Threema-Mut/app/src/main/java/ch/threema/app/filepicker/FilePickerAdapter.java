/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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
package ch.threema.app.filepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Date;
import java.util.List;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import ch.threema.app.R;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.IconUtil;
import ch.threema.app.utils.LocaleUtil;
import ch.threema.app.utils.MimeUtil;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FilePickerAdapter extends ArrayAdapter<FileInfo> {

    private Context context;

    private int resourceID;

    private List<FileInfo> items;

    private boolean isDirectoryMode;

    @ColorInt
    private int enabledColor = ConfigUtils.getColorFromAttribute(getContext(), R.attr.textColorSecondary);

    @ColorInt
    private int disabledColor = ConfigUtils.getColorFromAttribute(getContext(), R.attr.textColorTertiary);

    FilePickerAdapter(Context context, int textViewResourceId, List<FileInfo> objects, boolean directoryMode) {
        super(context, textViewResourceId, objects);
        if (!ListenerUtil.mutListener.listen(23425)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(23426)) {
            this.resourceID = textViewResourceId;
        }
        if (!ListenerUtil.mutListener.listen(23427)) {
            this.items = objects;
        }
        if (!ListenerUtil.mutListener.listen(23428)) {
            this.isDirectoryMode = directoryMode;
        }
    }

    public FileInfo getItem(int i) {
        return items.get(i);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (!ListenerUtil.mutListener.listen(23429)) {
                convertView = layoutInflater.inflate(resourceID, null);
            }
            viewHolder = new ViewHolder();
            if (!ListenerUtil.mutListener.listen(23430)) {
                viewHolder.item = convertView;
            }
            if (!ListenerUtil.mutListener.listen(23431)) {
                viewHolder.icon = convertView.findViewById(android.R.id.icon);
            }
            if (!ListenerUtil.mutListener.listen(23432)) {
                viewHolder.name = convertView.findViewById(R.id.name);
            }
            if (!ListenerUtil.mutListener.listen(23433)) {
                viewHolder.date = convertView.findViewById(R.id.date);
            }
            if (!ListenerUtil.mutListener.listen(23434)) {
                viewHolder.size = convertView.findViewById(R.id.size);
            }
            if (!ListenerUtil.mutListener.listen(23435)) {
                viewHolder.extra = convertView.findViewById(R.id.extra);
            }
            if (!ListenerUtil.mutListener.listen(23436)) {
                convertView.setTag(viewHolder);
            }
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        FileInfo fileInfo = items.get(position);
        if (!ListenerUtil.mutListener.listen(23466)) {
            if (fileInfo != null) {
                if (!ListenerUtil.mutListener.listen(23437)) {
                    viewHolder.name.setText(fileInfo.getName());
                }
                if (!ListenerUtil.mutListener.listen(23438)) {
                    viewHolder.size.setText("");
                }
                if (!ListenerUtil.mutListener.listen(23439)) {
                    viewHolder.date.setText("");
                }
                if (!ListenerUtil.mutListener.listen(23440)) {
                    viewHolder.extra.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(23458)) {
                    if (fileInfo.getData().equalsIgnoreCase(Constants.FOLDER)) {
                        if (!ListenerUtil.mutListener.listen(23455)) {
                            viewHolder.icon.setImageResource(R.drawable.ic_doc_folder);
                        }
                        if (!ListenerUtil.mutListener.listen(23456)) {
                            viewHolder.extra.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(23457)) {
                            tintItem(viewHolder, true);
                        }
                    } else if (fileInfo.getData().equalsIgnoreCase(Constants.PARENT_FOLDER)) {
                        if (!ListenerUtil.mutListener.listen(23450)) {
                            viewHolder.icon.setImageResource(R.drawable.ic_doc_parent);
                        }
                        if (!ListenerUtil.mutListener.listen(23451)) {
                            viewHolder.date.setText(R.string.parent_directory);
                        }
                        if (!ListenerUtil.mutListener.listen(23452)) {
                            viewHolder.size.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(23453)) {
                            viewHolder.extra.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(23454)) {
                            tintItem(viewHolder, true);
                        }
                    } else {
                        String mimeType = FileUtil.getMimeTypeFromPath(fileInfo.getPath());
                        if (!ListenerUtil.mutListener.listen(23441)) {
                            viewHolder.icon.setImageResource(IconUtil.getMimeIcon(mimeType));
                        }
                        if (!ListenerUtil.mutListener.listen(23442)) {
                            viewHolder.size.setText(fileInfo.getData());
                        }
                        if (!ListenerUtil.mutListener.listen(23443)) {
                            viewHolder.size.setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(23448)) {
                            if ((ListenerUtil.mutListener.listen(23444) ? (mimeType != null || mimeType.equals(MimeUtil.MIME_TYPE_ZIP)) : (mimeType != null && mimeType.equals(MimeUtil.MIME_TYPE_ZIP)))) {
                                String id = getBackupID(fileInfo.getName());
                                if (!ListenerUtil.mutListener.listen(23447)) {
                                    if (!TestUtil.empty(id)) {
                                        if (!ListenerUtil.mutListener.listen(23445)) {
                                            viewHolder.extra.setText(id);
                                        }
                                        if (!ListenerUtil.mutListener.listen(23446)) {
                                            viewHolder.extra.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(23449)) {
                            tintItem(viewHolder, !isDirectoryMode);
                        }
                    }
                }
                long date = fileInfo.getLastModified();
                if (!ListenerUtil.mutListener.listen(23465)) {
                    if ((ListenerUtil.mutListener.listen(23463) ? (date >= 0L) : (ListenerUtil.mutListener.listen(23462) ? (date <= 0L) : (ListenerUtil.mutListener.listen(23461) ? (date > 0L) : (ListenerUtil.mutListener.listen(23460) ? (date < 0L) : (ListenerUtil.mutListener.listen(23459) ? (date == 0L) : (date != 0L))))))) {
                        if (!ListenerUtil.mutListener.listen(23464)) {
                            viewHolder.date.setText(LocaleUtil.formatTimeStampString(context, date, false));
                        }
                    }
                }
            }
        }
        return convertView;
    }

    private String getBackupID(final String filename) {
        if (!ListenerUtil.mutListener.listen(23477)) {
            if (!TestUtil.empty(filename)) {
                String[] pieces = filename.split("_");
                if (!ListenerUtil.mutListener.listen(23476)) {
                    if ((ListenerUtil.mutListener.listen(23472) ? ((ListenerUtil.mutListener.listen(23471) ? (pieces.length >= 2) : (ListenerUtil.mutListener.listen(23470) ? (pieces.length <= 2) : (ListenerUtil.mutListener.listen(23469) ? (pieces.length < 2) : (ListenerUtil.mutListener.listen(23468) ? (pieces.length != 2) : (ListenerUtil.mutListener.listen(23467) ? (pieces.length == 2) : (pieces.length > 2)))))) || pieces[0].equals("threema-backup")) : ((ListenerUtil.mutListener.listen(23471) ? (pieces.length >= 2) : (ListenerUtil.mutListener.listen(23470) ? (pieces.length <= 2) : (ListenerUtil.mutListener.listen(23469) ? (pieces.length < 2) : (ListenerUtil.mutListener.listen(23468) ? (pieces.length != 2) : (ListenerUtil.mutListener.listen(23467) ? (pieces.length == 2) : (pieces.length > 2)))))) && pieces[0].equals("threema-backup")))) {
                        if (!ListenerUtil.mutListener.listen(23475)) {
                            if ((ListenerUtil.mutListener.listen(23473) ? (!TestUtil.empty(pieces[1]) || !TestUtil.empty(pieces[2])) : (!TestUtil.empty(pieces[1]) && !TestUtil.empty(pieces[2])))) {
                                final String identity = pieces[1];
                                final Date time = new Date();
                                try {
                                    if (!ListenerUtil.mutListener.listen(23474)) {
                                        time.setTime(Long.valueOf(pieces[2]));
                                    }
                                } catch (NumberFormatException e) {
                                    return null;
                                }
                                return identity;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private void tintItem(ViewHolder holder, boolean enabled) {
        int color = enabled ? enabledColor : disabledColor;
        if (!ListenerUtil.mutListener.listen(23478)) {
            holder.icon.setColorFilter(color);
        }
        if (!ListenerUtil.mutListener.listen(23479)) {
            holder.name.setTextColor(color);
        }
        if (!ListenerUtil.mutListener.listen(23480)) {
            holder.date.setTextColor(color);
        }
        if (!ListenerUtil.mutListener.listen(23481)) {
            holder.size.setTextColor(color);
        }
        if (!ListenerUtil.mutListener.listen(23482)) {
            holder.extra.setTextColor(color);
        }
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        if (!ListenerUtil.mutListener.listen(23485)) {
            if (isDirectoryMode) {
                FileInfo fileInfo = items.get(position);
                return (ListenerUtil.mutListener.listen(23484) ? ((ListenerUtil.mutListener.listen(23483) ? (fileInfo == null && fileInfo.isFolder()) : (fileInfo == null || fileInfo.isFolder())) && fileInfo.isParent()) : ((ListenerUtil.mutListener.listen(23483) ? (fileInfo == null && fileInfo.isFolder()) : (fileInfo == null || fileInfo.isFolder())) || fileInfo.isParent()));
            }
        }
        return true;
    }

    class ViewHolder {

        View item;

        ImageView icon;

        TextView name;

        TextView date;

        TextView size;

        TextView extra;
    }
}

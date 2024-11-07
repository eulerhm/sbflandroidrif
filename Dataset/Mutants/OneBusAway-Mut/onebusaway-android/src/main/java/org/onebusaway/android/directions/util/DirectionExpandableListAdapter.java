/*
 * Copyright 2012 University of South Florida
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
package org.onebusaway.android.directions.util;

import org.onebusaway.android.R;
import org.onebusaway.android.directions.model.Direction;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DirectionExpandableListAdapter extends BaseExpandableListAdapter {

    Context mContext;

    int mDirectionLayoutResourceId;

    int mSubDirectionLayoutResourceId;

    Direction[] mData = null;

    public DirectionExpandableListAdapter(Context context, int directionLayoutResourceId, int subDirectionLayoutResourceId, Direction[] data) {
        if (!ListenerUtil.mutListener.listen(6069)) {
            mDirectionLayoutResourceId = directionLayoutResourceId;
        }
        if (!ListenerUtil.mutListener.listen(6070)) {
            mSubDirectionLayoutResourceId = subDirectionLayoutResourceId;
        }
        if (!ListenerUtil.mutListener.listen(6071)) {
            mContext = context;
        }
        if (!ListenerUtil.mutListener.listen(6072)) {
            mData = data;
        }
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Direction> subDirections = mData[groupPosition].getSubDirections();
        if (!ListenerUtil.mutListener.listen(6074)) {
            if ((ListenerUtil.mutListener.listen(6073) ? (subDirections != null || !subDirections.isEmpty()) : (subDirections != null && !subDirections.isEmpty()))) {
                return subDirections.get(childPosition);
            }
        }
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Direction> subDirections = mData[groupPosition].getSubDirections();
        if (!ListenerUtil.mutListener.listen(6075)) {
            if (subDirections != null) {
                return subDirections.size();
            }
        }
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View row = convertView;
        DirectionHolder holder = null;
        if (!ListenerUtil.mutListener.listen(6082)) {
            if (row == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                if (!ListenerUtil.mutListener.listen(6077)) {
                    row = inflater.inflate(mSubDirectionLayoutResourceId, parent, false);
                }
                if (!ListenerUtil.mutListener.listen(6078)) {
                    holder = new DirectionHolder();
                }
                if (!ListenerUtil.mutListener.listen(6079)) {
                    holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
                }
                if (!ListenerUtil.mutListener.listen(6080)) {
                    holder.txtDirection = (TextView) row.findViewById(R.id.directionText);
                }
                if (!ListenerUtil.mutListener.listen(6081)) {
                    row.setTag(holder);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6076)) {
                    holder = (DirectionHolder) row.getTag();
                }
            }
        }
        Direction subDirection = (Direction) getChild(groupPosition, childPosition);
        CharSequence text = subDirection == null ? "null here" : subDirection.getDirectionText();
        if (!ListenerUtil.mutListener.listen(6083)) {
            holder.txtDirection.setText(text);
        }
        if (!ListenerUtil.mutListener.listen(6087)) {
            if (subDirection.getIcon() != -1) {
                if (!ListenerUtil.mutListener.listen(6085)) {
                    holder.imgIcon.setImageResource(subDirection.getIcon());
                }
                if (!ListenerUtil.mutListener.listen(6086)) {
                    holder.imgIcon.setColorFilter(Color.GRAY);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6084)) {
                    holder.imgIcon.setVisibility(View.INVISIBLE);
                }
            }
        }
        return row;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mData[groupPosition];
    }

    @Override
    public int getGroupCount() {
        return mData.length;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View row = convertView;
        DirectionHolder holder = null;
        if (!ListenerUtil.mutListener.listen(6095)) {
            if (row == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                if (!ListenerUtil.mutListener.listen(6089)) {
                    row = inflater.inflate(mDirectionLayoutResourceId, parent, false);
                }
                if (!ListenerUtil.mutListener.listen(6090)) {
                    holder = new DirectionHolder();
                }
                if (!ListenerUtil.mutListener.listen(6091)) {
                    holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
                }
                if (!ListenerUtil.mutListener.listen(6092)) {
                    holder.noIconText = (TextView) row.findViewById(R.id.noIconText);
                }
                if (!ListenerUtil.mutListener.listen(6093)) {
                    holder.txtDirection = (TextView) row.findViewById(R.id.directionText);
                }
                if (!ListenerUtil.mutListener.listen(6094)) {
                    row.setTag(holder);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6088)) {
                    holder = (DirectionHolder) row.getTag();
                }
            }
        }
        Direction dir = mData[groupPosition];
        if (!ListenerUtil.mutListener.listen(6114)) {
            if (!dir.isTransit()) {
                if (!ListenerUtil.mutListener.listen(6108)) {
                    holder.txtDirection.setText(dir.getDirectionIndex() + ". " + dir.getDirectionText());
                }
                if (!ListenerUtil.mutListener.listen(6109)) {
                    holder.imgIcon.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(6113)) {
                    if (dir.getIcon() != -1) {
                        if (!ListenerUtil.mutListener.listen(6111)) {
                            holder.imgIcon.setImageResource(dir.getIcon());
                        }
                        if (!ListenerUtil.mutListener.listen(6112)) {
                            holder.imgIcon.setColorFilter(Color.GRAY);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6110)) {
                            holder.imgIcon.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            } else {
                CharSequence textBeforeTime = dir.getDirectionIndex() + ". " + dir.getService();
                CharSequence text;
                CharSequence time = dir.getOldTime();
                text = new SpannableString(textBeforeTime);
                if (!ListenerUtil.mutListener.listen(6098)) {
                    if (dir.isRealTimeInfo()) {
                        if (!ListenerUtil.mutListener.listen(6097)) {
                            if (dir.getNewTime() != null) {
                                if (!ListenerUtil.mutListener.listen(6096)) {
                                    time = dir.getNewTime();
                                }
                            }
                        }
                    }
                }
                text = TextUtils.concat(text, " ", time, "\n", dir.getPlaceAndHeadsign());
                if (!TextUtils.isEmpty(dir.getAgency())) {
                    text = TextUtils.concat(text, "\n", dir.getAgency());
                }
                if (!TextUtils.isEmpty(dir.getExtra())) {
                    SpannableString extraSpannableString = new SpannableString(dir.getExtra());
                    if (!ListenerUtil.mutListener.listen(6099)) {
                        extraSpannableString.setSpan(new StyleSpan(Typeface.ITALIC), 0, extraSpannableString.length(), 0);
                    }
                    text = TextUtils.concat(text, "\n", extraSpannableString);
                }
                if (!ListenerUtil.mutListener.listen(6100)) {
                    holder.txtDirection.setText(text);
                }
                if (!ListenerUtil.mutListener.listen(6107)) {
                    if (dir.getIcon() == -1) {
                        if (!ListenerUtil.mutListener.listen(6105)) {
                            holder.imgIcon.setVisibility(View.INVISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(6106)) {
                            holder.noIconText.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6101)) {
                            holder.imgIcon.setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(6102)) {
                            holder.imgIcon.setImageResource(dir.getIcon());
                        }
                        if (!ListenerUtil.mutListener.listen(6103)) {
                            holder.imgIcon.setColorFilter(Color.GRAY);
                        }
                        if (!ListenerUtil.mutListener.listen(6104)) {
                            holder.noIconText.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            }
        }
        return row;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    static class DirectionHolder {

        ImageView imgIcon;

        TextView noIconText;

        TextView txtDirection;
    }
}

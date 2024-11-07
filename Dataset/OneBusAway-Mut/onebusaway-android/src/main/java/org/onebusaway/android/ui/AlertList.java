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
package org.onebusaway.android.ui;

import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.util.ArrayAdapter;
import org.onebusaway.android.util.UIUtils;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Collection;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class AlertList {

    interface Alert {

        public static final int TYPE_ERROR = 1;

        public static final int TYPE_WARNING = 2;

        public static final int TYPE_INFO = 3;

        public static final int TYPE_SHOW_HIDDEN_ALERTS = 4;

        // and you can see more..
        public static final int FLAG_HASMORE = 0x1;

        String getId();

        int getType();

        int getFlags();

        CharSequence getString();

        void onClick();
    }

    // 
    private static class Adapter extends ArrayAdapter<Alert> {

        public Adapter(Context context) {
            super(context, R.layout.alert_item);
        }

        /**
         * Initialize the views - if the alert is a service alert that should be shown, populate
         * that text.  If its a response error (i.e., couldn't update from server), show that.
         * If it's an alert showing that some alerts are filtered, show that.
         */
        @Override
        protected void initView(View view, final Alert alert) {
            TextView alertView = (TextView) view.findViewById(android.R.id.text1);
            View filterGroupView = view.findViewById(R.id.filter_alert_group);
            TextView filterTextView = (TextView) view.findViewById(R.id.filter_alert_text);
            TextView showAllView = (TextView) view.findViewById(R.id.show_all_alerts);
            if (!ListenerUtil.mutListener.listen(4704)) {
                alertView.setText(alert.getString());
            }
            boolean clickable = (ListenerUtil.mutListener.listen(4709) ? ((alert.getFlags() & Alert.FLAG_HASMORE) >= Alert.FLAG_HASMORE) : (ListenerUtil.mutListener.listen(4708) ? ((alert.getFlags() & Alert.FLAG_HASMORE) <= Alert.FLAG_HASMORE) : (ListenerUtil.mutListener.listen(4707) ? ((alert.getFlags() & Alert.FLAG_HASMORE) > Alert.FLAG_HASMORE) : (ListenerUtil.mutListener.listen(4706) ? ((alert.getFlags() & Alert.FLAG_HASMORE) < Alert.FLAG_HASMORE) : (ListenerUtil.mutListener.listen(4705) ? ((alert.getFlags() & Alert.FLAG_HASMORE) != Alert.FLAG_HASMORE) : ((alert.getFlags() & Alert.FLAG_HASMORE) == Alert.FLAG_HASMORE))))));
            int type = alert.getType();
            Resources r = Application.get().getResources();
            int bg = R.color.alert_text_background_info;
            int arrowColor = R.color.alert_text_color_info;
            int alertColor = R.color.alert_icon_info;
            int resourceIdAlert = 0;
            if (!ListenerUtil.mutListener.listen(4729)) {
                switch(type) {
                    case Alert.TYPE_ERROR:
                        if (!ListenerUtil.mutListener.listen(4710)) {
                            bg = R.color.alert_text_background_error;
                        }
                        if (!ListenerUtil.mutListener.listen(4711)) {
                            arrowColor = R.color.alert_text_color_error;
                        }
                        if (!ListenerUtil.mutListener.listen(4712)) {
                            resourceIdAlert = R.drawable.ic_alert_warning;
                        }
                        if (!ListenerUtil.mutListener.listen(4713)) {
                            alertColor = R.color.alert_icon_error;
                        }
                        if (!ListenerUtil.mutListener.listen(4714)) {
                            alertView.setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(4715)) {
                            filterGroupView.setVisibility(View.GONE);
                        }
                        break;
                    case Alert.TYPE_WARNING:
                        if (!ListenerUtil.mutListener.listen(4716)) {
                            bg = R.color.alert_text_background_warning;
                        }
                        if (!ListenerUtil.mutListener.listen(4717)) {
                            arrowColor = R.color.alert_text_color_warning;
                        }
                        if (!ListenerUtil.mutListener.listen(4718)) {
                            resourceIdAlert = R.drawable.ic_alert_warning;
                        }
                        if (!ListenerUtil.mutListener.listen(4719)) {
                            alertColor = R.color.alert_icon_warning;
                        }
                        if (!ListenerUtil.mutListener.listen(4720)) {
                            alertView.setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(4721)) {
                            filterGroupView.setVisibility(View.GONE);
                        }
                        break;
                    case Alert.TYPE_SHOW_HIDDEN_ALERTS:
                        if (!ListenerUtil.mutListener.listen(4722)) {
                            alertView.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(4723)) {
                            filterGroupView.setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(4724)) {
                            filterTextView.setText(alert.getString());
                        }
                        break;
                    case Alert.TYPE_INFO:
                    default:
                        if (!ListenerUtil.mutListener.listen(4725)) {
                            bg = R.color.alert_text_background_info;
                        }
                        if (!ListenerUtil.mutListener.listen(4726)) {
                            arrowColor = R.color.alert_text_color_info;
                        }
                        if (!ListenerUtil.mutListener.listen(4727)) {
                            alertView.setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(4728)) {
                            filterGroupView.setVisibility(View.GONE);
                        }
                        break;
                }
            }
            if (!ListenerUtil.mutListener.listen(4730)) {
                // Set text color to same as arrow color
                alertView.setTextColor(r.getColor(arrowColor));
            }
            Drawable dWarning;
            Drawable wdWarning = null;
            if ((ListenerUtil.mutListener.listen(4735) ? (resourceIdAlert >= 0) : (ListenerUtil.mutListener.listen(4734) ? (resourceIdAlert <= 0) : (ListenerUtil.mutListener.listen(4733) ? (resourceIdAlert > 0) : (ListenerUtil.mutListener.listen(4732) ? (resourceIdAlert < 0) : (ListenerUtil.mutListener.listen(4731) ? (resourceIdAlert == 0) : (resourceIdAlert != 0))))))) {
                dWarning = ContextCompat.getDrawable(Application.get().getApplicationContext(), resourceIdAlert);
                if (!ListenerUtil.mutListener.listen(4736)) {
                    wdWarning = DrawableCompat.wrap(dWarning);
                }
                if (!ListenerUtil.mutListener.listen(4737)) {
                    wdWarning = wdWarning.mutate();
                }
                if (!ListenerUtil.mutListener.listen(4738)) {
                    // Tint the icon
                    DrawableCompat.setTint(wdWarning, r.getColor(alertColor));
                }
            }
            Drawable dArrow;
            Drawable wdArrow = null;
            int resourceIdArrow = clickable ? R.drawable.ic_navigation_chevron_right : 0;
            if ((ListenerUtil.mutListener.listen(4743) ? (resourceIdArrow >= 0) : (ListenerUtil.mutListener.listen(4742) ? (resourceIdArrow <= 0) : (ListenerUtil.mutListener.listen(4741) ? (resourceIdArrow > 0) : (ListenerUtil.mutListener.listen(4740) ? (resourceIdArrow < 0) : (ListenerUtil.mutListener.listen(4739) ? (resourceIdArrow == 0) : (resourceIdArrow != 0))))))) {
                dArrow = ContextCompat.getDrawable(Application.get().getApplicationContext(), resourceIdArrow);
                if (!ListenerUtil.mutListener.listen(4744)) {
                    wdArrow = DrawableCompat.wrap(dArrow);
                }
                if (!ListenerUtil.mutListener.listen(4745)) {
                    wdArrow = wdArrow.mutate();
                }
                if (!ListenerUtil.mutListener.listen(4746)) {
                    // Tint the icon
                    DrawableCompat.setTint(wdArrow, r.getColor(arrowColor));
                }
            }
            if (!ListenerUtil.mutListener.listen(4747)) {
                alertView.setCompoundDrawablesWithIntrinsicBounds(wdWarning, null, wdArrow, null);
            }
            if (!ListenerUtil.mutListener.listen(4748)) {
                // Set the background color
                view.setBackgroundResource(bg);
            }
            // reset the onclick listener because we could be reusing this view.
            View.OnClickListener listener = new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(4749)) {
                        alert.onClick();
                    }
                }
            };
            if (!ListenerUtil.mutListener.listen(4750)) {
                view.setOnClickListener(listener);
            }
            if (!ListenerUtil.mutListener.listen(4751)) {
                // Remove any previous clickable spans - we're recycling views between fragments for efficiency
                UIUtils.removeAllClickableSpans(showAllView);
            }
            ClickableSpan showAllClick = new ClickableSpan() {

                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(4752)) {
                        alert.onClick();
                    }
                }
            };
            if (!ListenerUtil.mutListener.listen(4753)) {
                UIUtils.setClickableSpan(showAllView, showAllClick);
            }
        }
    }

    private Adapter mAdapter;

    private boolean mIsAlertHidden;

    private int mHiddenAlertCount;

    // 
    private ListView mView;

    AlertList(Context context) {
        if (!ListenerUtil.mutListener.listen(4754)) {
            mAdapter = new Adapter(context);
        }
    }

    void initView(View view) {
        if (!ListenerUtil.mutListener.listen(4755)) {
            mView = (ListView) view;
        }
        if (!ListenerUtil.mutListener.listen(4756)) {
            mView.setAdapter(mAdapter);
        }
    }

    // 
    void add(Alert alert) {
        if (!ListenerUtil.mutListener.listen(4757)) {
            mAdapter.add(alert);
        }
    }

    void addAll(Collection<? extends Alert> alerts) {
        if (!ListenerUtil.mutListener.listen(4766)) {
            if ((ListenerUtil.mutListener.listen(4762) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB) : (ListenerUtil.mutListener.listen(4761) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) : (ListenerUtil.mutListener.listen(4760) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) : (ListenerUtil.mutListener.listen(4759) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.HONEYCOMB) : (ListenerUtil.mutListener.listen(4758) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.HONEYCOMB) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB))))))) {
                if (!ListenerUtil.mutListener.listen(4765)) {
                    mAdapter.addAll(alerts);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4764)) {
                    {
                        long _loopCounter34 = 0;
                        for (Alert a : alerts) {
                            ListenerUtil.loopListener.listen("_loopCounter34", ++_loopCounter34);
                            if (!ListenerUtil.mutListener.listen(4763)) {
                                mAdapter.add(a);
                            }
                        }
                    }
                }
            }
        }
    }

    void insert(Alert alert, int index) {
        if (!ListenerUtil.mutListener.listen(4767)) {
            mAdapter.insert(alert, index);
        }
    }

    int getPosition(Alert alert) {
        return mAdapter.getPosition(alert);
    }

    void remove(Alert alert) {
        if (!ListenerUtil.mutListener.listen(4768)) {
            mAdapter.remove(alert);
        }
    }

    int getCount() {
        return mAdapter.getCount();
    }

    Alert getItem(int position) {
        return mAdapter.getItem(position);
    }

    /**
     * Returns true if some alerts have previously been hidden that would otherwise
     * appear in this list, false if all alerts are visible
     *
     * @return true if some alerts have previously been hidden that would otherwise
     * appear in this list, false if all alerts are visible
     */
    public boolean isAlertHidden() {
        return mIsAlertHidden;
    }

    /**
     * Set to true if there are some alerts that have been previously hidden that would
     * otherwise appear in this list, , false if all alerts are visible
     *
     * @param alertHidden true if there are some alerts that have been previously hidden that would
     *                    otherwise appear in this list, false if all alerts are visible
     */
    public void setAlertHidden(boolean alertHidden) {
        if (!ListenerUtil.mutListener.listen(4769)) {
            mIsAlertHidden = alertHidden;
        }
    }

    /**
     * Returns the number of active alerts hidden that would otherwise appear in this list
     *
     * @return the number of active alerts hidden that would otherwise appear in this list
     */
    public int getHiddenAlertCount() {
        return mHiddenAlertCount;
    }

    /**
     * Sets the number of active alerts hidden that would otherwise appear in this list
     *
     * @param hiddenAlertCount the number of active alerts hidden that would otherwise appear in
     *                         this list
     */
    public void setHiddenAlertCount(int hiddenAlertCount) {
        if (!ListenerUtil.mutListener.listen(4770)) {
            mHiddenAlertCount = hiddenAlertCount;
        }
    }
}

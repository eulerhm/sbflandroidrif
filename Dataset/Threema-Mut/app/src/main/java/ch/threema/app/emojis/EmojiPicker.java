/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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
package ch.threema.app.emojis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import com.google.android.material.tabs.TabLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import androidx.viewpager.widget.ViewPager;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.ui.LockableViewPager;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EmojiPicker extends LinearLayout {

    private static final Logger logger = LoggerFactory.getLogger(EmojiPicker.class);

    private Context context;

    private View emojiPickerView;

    private LockableViewPager viewPager;

    private EmojiRecent emojiRecent;

    private ArrayList<EmojiPickerListener> emojiPickerListeners = new ArrayList<>();

    private EmojiKeyListener emojiKeyListener;

    private PreferenceService preferenceService;

    private DiverseEmojiPopup diverseEmojiPopup;

    private EmojiDetailPopup emojiDetailPopup;

    private RecentEmojiRemovePopup recentRemovePopup;

    private HashMap<String, String> diversePrefs;

    public static final String RECENT_VIEW_TAG = "0";

    public EmojiPicker(Context context) {
        this(context, null);
    }

    public EmojiPicker(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmojiPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(19616)) {
            this.preferenceService = ThreemaApplication.getServiceManager().getPreferenceService();
        }
        if (!ListenerUtil.mutListener.listen(19617)) {
            this.diversePrefs = preferenceService.getDiverseEmojiPrefs2();
        }
    }

    public void addEmojiPickerListener(EmojiPickerListener listener) {
        if (!ListenerUtil.mutListener.listen(19618)) {
            this.emojiPickerListeners.add(listener);
        }
    }

    public void removeEmojiPickerListener(EmojiPickerListener listener) {
        if (!ListenerUtil.mutListener.listen(19619)) {
            this.emojiPickerListeners.remove(listener);
        }
    }

    public void setEmojiKeyListener(EmojiKeyListener listener) {
        if (!ListenerUtil.mutListener.listen(19620)) {
            this.emojiKeyListener = listener;
        }
    }

    public void init(Context context) {
        if (!ListenerUtil.mutListener.listen(19621)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(19622)) {
            this.emojiRecent = new EmojiRecent();
        }
        if (!ListenerUtil.mutListener.listen(19623)) {
            this.emojiPickerView = LayoutInflater.from(getContext()).inflate(R.layout.emoji_picker, this, true);
        }
        if (!ListenerUtil.mutListener.listen(19624)) {
            this.recentRemovePopup = new RecentEmojiRemovePopup(context, this.emojiPickerView);
        }
        if (!ListenerUtil.mutListener.listen(19625)) {
            this.recentRemovePopup.setListener(this::removeEmojiFromRecent);
        }
        if (!ListenerUtil.mutListener.listen(19626)) {
            this.emojiDetailPopup = new EmojiDetailPopup(context, this.emojiPickerView);
        }
        if (!ListenerUtil.mutListener.listen(19627)) {
            this.emojiDetailPopup.setListener(emojiSequence -> {
                if (emojiKeyListener != null) {
                    emojiKeyListener.onEmojiClick(emojiSequence);
                    addEmojiToRecent(emojiSequence);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(19628)) {
            this.diverseEmojiPopup = new DiverseEmojiPopup(context, this.emojiPickerView);
        }
        if (!ListenerUtil.mutListener.listen(19638)) {
            this.diverseEmojiPopup.setListener(new DiverseEmojiPopup.DiverseEmojiPopupListener() {

                @Override
                public void onDiverseEmojiClick(String parentEmojiSequence, String emojiSequence) {
                    if (!ListenerUtil.mutListener.listen(19629)) {
                        emojiKeyListener.onEmojiClick(emojiSequence);
                    }
                    if (!ListenerUtil.mutListener.listen(19630)) {
                        diversePrefs.remove(parentEmojiSequence);
                    }
                    if (!ListenerUtil.mutListener.listen(19631)) {
                        diversePrefs.put(parentEmojiSequence, emojiSequence);
                    }
                    if (!ListenerUtil.mutListener.listen(19632)) {
                        addEmojiToRecent(emojiSequence);
                    }
                    if (!ListenerUtil.mutListener.listen(19633)) {
                        preferenceService.setDiverseEmojiPrefs2(diversePrefs);
                    }
                }

                @Override
                public void onOpen() {
                    if (!ListenerUtil.mutListener.listen(19635)) {
                        if (viewPager != null) {
                            if (!ListenerUtil.mutListener.listen(19634)) {
                                viewPager.lock(true);
                            }
                        }
                    }
                }

                @Override
                public void onClose() {
                    if (!ListenerUtil.mutListener.listen(19637)) {
                        if (viewPager != null) {
                            if (!ListenerUtil.mutListener.listen(19636)) {
                                viewPager.lock(false);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(19639)) {
            initPagerAdapter();
        }
    }

    public boolean isShown() {
        return getVisibility() == VISIBLE;
    }

    public void show(int height) {
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        if (!ListenerUtil.mutListener.listen(19640)) {
            setLayoutParams(params);
        }
        if (!ListenerUtil.mutListener.listen(19641)) {
            logger.info("Show EmojiPicker. Height = " + height);
        }
        if (!ListenerUtil.mutListener.listen(19642)) {
            setVisibility(VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(19644)) {
            {
                long _loopCounter143 = 0;
                for (EmojiPickerListener listener : this.emojiPickerListeners) {
                    ListenerUtil.loopListener.listen("_loopCounter143", ++_loopCounter143);
                    if (!ListenerUtil.mutListener.listen(19643)) {
                        listener.onEmojiPickerOpen();
                    }
                }
            }
        }
    }

    public void hide() {
        if (!ListenerUtil.mutListener.listen(19647)) {
            if ((ListenerUtil.mutListener.listen(19645) ? (this.diverseEmojiPopup != null || this.diverseEmojiPopup.isShowing()) : (this.diverseEmojiPopup != null && this.diverseEmojiPopup.isShowing()))) {
                if (!ListenerUtil.mutListener.listen(19646)) {
                    this.diverseEmojiPopup.dismiss();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(19648)) {
            setVisibility(GONE);
        }
        if (!ListenerUtil.mutListener.listen(19650)) {
            {
                long _loopCounter144 = 0;
                for (EmojiPickerListener listener : this.emojiPickerListeners) {
                    ListenerUtil.loopListener.listen("_loopCounter144", ++_loopCounter144);
                    if (!ListenerUtil.mutListener.listen(19649)) {
                        listener.onEmojiPickerClose();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(19651)) {
            this.emojiRecent.saveToPrefs();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private EmojiPagerAdapter initPagerAdapter() {
        EmojiGridAdapter.KeyClickListener keyClickListener = new EmojiGridAdapter.KeyClickListener() {

            @Override
            public void onEmojiKeyClicked(String emojiCodeString) {
                if (!ListenerUtil.mutListener.listen(19652)) {
                    emojiKeyListener.onEmojiClick(emojiCodeString);
                }
                if (!ListenerUtil.mutListener.listen(19653)) {
                    addEmojiToRecent(emojiCodeString);
                }
            }

            @Override
            public void onEmojiKeyLongClicked(View view, String emojiCodeString) {
                if (!ListenerUtil.mutListener.listen(19654)) {
                    onEmojiLongClicked(view, emojiCodeString);
                }
            }

            @Override
            public void onRecentLongClicked(View view, String emojiCodeString) {
                if (!ListenerUtil.mutListener.listen(19655)) {
                    onRecentListLongClicked(view, emojiCodeString);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(19656)) {
            this.viewPager = emojiPickerView.findViewById(R.id.emoji_pager);
        }
        int currentItem = this.viewPager.getCurrentItem();
        EmojiPagerAdapter emojiPagerAdapter = new EmojiPagerAdapter(context, this, this.emojiRecent, this.diversePrefs, keyClickListener);
        if (!ListenerUtil.mutListener.listen(19657)) {
            this.viewPager.setAdapter(emojiPagerAdapter);
        }
        if (!ListenerUtil.mutListener.listen(19658)) {
            this.viewPager.setOffscreenPageLimit(1);
        }
        final TabLayout tabLayout = emojiPickerView.findViewById(R.id.sliding_tabs);
        if (!ListenerUtil.mutListener.listen(19659)) {
            tabLayout.removeAllTabs();
        }
        if (!ListenerUtil.mutListener.listen(19661)) {
            {
                long _loopCounter145 = 0;
                for (EmojiGroup emojiGroup : EmojiManager.getEmojiGroups()) {
                    ListenerUtil.loopListener.listen("_loopCounter145", ++_loopCounter145);
                    if (!ListenerUtil.mutListener.listen(19660)) {
                        tabLayout.addTab(tabLayout.newTab().setIcon(emojiGroup.getGroupIcon()).setContentDescription(emojiGroup.getGroupName()));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(19662)) {
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        }
        if (!ListenerUtil.mutListener.listen(19663)) {
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        }
        if (!ListenerUtil.mutListener.listen(19672)) {
            this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    if (!ListenerUtil.mutListener.listen(19671)) {
                        if ((ListenerUtil.mutListener.listen(19668) ? (position >= 0) : (ListenerUtil.mutListener.listen(19667) ? (position <= 0) : (ListenerUtil.mutListener.listen(19666) ? (position > 0) : (ListenerUtil.mutListener.listen(19665) ? (position < 0) : (ListenerUtil.mutListener.listen(19664) ? (position != 0) : (position == 0))))))) {
                            if (!ListenerUtil.mutListener.listen(19670)) {
                                if (emojiRecent.syncRecents()) {
                                    if (!ListenerUtil.mutListener.listen(19669)) {
                                        refreshRecentView();
                                    }
                                }
                            }
                        }
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(19680)) {
            // show first regular tab if there are no recent emojis
            if ((ListenerUtil.mutListener.listen(19678) ? ((ListenerUtil.mutListener.listen(19677) ? (currentItem >= 0) : (ListenerUtil.mutListener.listen(19676) ? (currentItem <= 0) : (ListenerUtil.mutListener.listen(19675) ? (currentItem > 0) : (ListenerUtil.mutListener.listen(19674) ? (currentItem < 0) : (ListenerUtil.mutListener.listen(19673) ? (currentItem != 0) : (currentItem == 0)))))) || emojiRecent.getNumberOfRecentEmojis() == 0) : ((ListenerUtil.mutListener.listen(19677) ? (currentItem >= 0) : (ListenerUtil.mutListener.listen(19676) ? (currentItem <= 0) : (ListenerUtil.mutListener.listen(19675) ? (currentItem > 0) : (ListenerUtil.mutListener.listen(19674) ? (currentItem < 0) : (ListenerUtil.mutListener.listen(19673) ? (currentItem != 0) : (currentItem == 0)))))) && emojiRecent.getNumberOfRecentEmojis() == 0))) {
                if (!ListenerUtil.mutListener.listen(19679)) {
                    this.viewPager.setCurrentItem(1);
                }
            }
        }
        LinearLayout backspaceButton = emojiPickerView.findViewById(R.id.backspace_button);
        if (!ListenerUtil.mutListener.listen(19692)) {
            if (backspaceButton != null) {
                if (!ListenerUtil.mutListener.listen(19691)) {
                    backspaceButton.setOnTouchListener(new OnTouchListener() {

                        private Handler handler;

                        final Runnable action = new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(19681)) {
                                    emojiKeyListener.onBackspaceClick();
                                }
                                if (!ListenerUtil.mutListener.listen(19682)) {
                                    handler.postDelayed(this, 100);
                                }
                            }
                        };

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (!ListenerUtil.mutListener.listen(19690)) {
                                switch(event.getAction()) {
                                    case MotionEvent.ACTION_DOWN:
                                        if (!ListenerUtil.mutListener.listen(19683)) {
                                            if (handler != null)
                                                return true;
                                        }
                                        if (!ListenerUtil.mutListener.listen(19684)) {
                                            handler = new Handler();
                                        }
                                        if (!ListenerUtil.mutListener.listen(19685)) {
                                            handler.postDelayed(action, 600);
                                        }
                                        break;
                                    case MotionEvent.ACTION_UP:
                                        if (!ListenerUtil.mutListener.listen(19686)) {
                                            if (handler == null)
                                                return true;
                                        }
                                        if (!ListenerUtil.mutListener.listen(19687)) {
                                            handler.removeCallbacks(action);
                                        }
                                        if (!ListenerUtil.mutListener.listen(19688)) {
                                            handler = null;
                                        }
                                        if (!ListenerUtil.mutListener.listen(19689)) {
                                            emojiKeyListener.onBackspaceClick();
                                        }
                                        break;
                                }
                            }
                            return false;
                        }
                    });
                }
            }
        }
        return emojiPagerAdapter;
    }

    private void onRecentListLongClicked(View view, String emojiSequence) {
        if (!ListenerUtil.mutListener.listen(19693)) {
            recentRemovePopup.show(view, emojiSequence);
        }
    }

    private void onEmojiLongClicked(View view, String emojiSequence) {
        EmojiInfo emojiInfo = EmojiUtil.getEmojiInfo(emojiSequence);
        if (!ListenerUtil.mutListener.listen(19697)) {
            if ((ListenerUtil.mutListener.listen(19694) ? (emojiInfo != null || emojiInfo.diversityFlag == EmojiSpritemap.DIVERSITY_PARENT) : (emojiInfo != null && emojiInfo.diversityFlag == EmojiSpritemap.DIVERSITY_PARENT))) {
                if (!ListenerUtil.mutListener.listen(19696)) {
                    diverseEmojiPopup.show(view, emojiSequence, diversePrefs);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(19695)) {
                    emojiDetailPopup.show(view, emojiSequence);
                }
            }
        }
    }

    public int getNumberOfPages() {
        return EmojiManager.getNumberOfEmojiGroups();
    }

    public String getGroupTitle(int id) {
        return context.getString(EmojiManager.getGroupName(id)).toUpperCase();
    }

    public void refreshRecentView() {
        // update recent gridview
        GridView view = emojiPickerView.findViewWithTag(RECENT_VIEW_TAG);
        if (!ListenerUtil.mutListener.listen(19699)) {
            if (view != null) {
                EmojiGridAdapter emojiGridAdapter = (EmojiGridAdapter) view.getAdapter();
                if (!ListenerUtil.mutListener.listen(19698)) {
                    emojiGridAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    public void addEmojiToRecent(String emojiSequence) {
        if (!ListenerUtil.mutListener.listen(19700)) {
            emojiRecent.add(emojiSequence);
        }
    }

    public void removeEmojiFromRecent(String emojiSequence) {
        if (!ListenerUtil.mutListener.listen(19701)) {
            emojiRecent.remove(emojiSequence);
        }
        if (!ListenerUtil.mutListener.listen(19702)) {
            refreshRecentView();
        }
    }

    public interface EmojiPickerListener {

        void onEmojiPickerOpen();

        void onEmojiPickerClose();
    }

    public interface EmojiKeyListener {

        void onBackspaceClick();

        void onEmojiClick(String emojiCodeString);
    }
}

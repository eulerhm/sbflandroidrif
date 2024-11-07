package org.wordpress.android.ui.prefs.notifications;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.preference.DialogPreference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordpress.android.R;
import org.wordpress.android.databinding.NotificationsSettingsSwitchBinding;
import org.wordpress.android.models.NotificationsSettings;
import org.wordpress.android.models.NotificationsSettings.Channel;
import org.wordpress.android.models.NotificationsSettings.Type;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.ui.prefs.notifications.PrefMainSwitchToolbarView.MainSwitchToolbarListener;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.JSONUtils;
import org.wordpress.android.util.extensions.ContextExtensionsKt;
import java.util.Iterator;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// A dialog preference that displays settings for a NotificationSettings Channel and Type
public class NotificationsSettingsDialogPreference extends DialogPreference implements MainSwitchToolbarListener {

    private static final String SETTING_VALUE_ACHIEVEMENT = "achievement";

    private NotificationsSettings.Channel mChannel;

    private NotificationsSettings.Type mType;

    private NotificationsSettings mSettings;

    private JSONObject mUpdatedJson = new JSONObject();

    private long mBlogId;

    private ViewGroup mTitleViewWithMainSwitch;

    // view to display when main switch is on
    private View mDisabledView;

    // view to display when main switch is off
    private LinearLayout mOptionsView;

    private PrefMainSwitchToolbarView mMainSwitchToolbarView;

    private boolean mShouldDisplayMainSwitch;

    private String[] mSettingsArray = new String[0], mSettingsValues = new String[0];

    private OnNotificationsSettingsChangedListener mOnNotificationsSettingsChangedListener;

    private final BloggingRemindersProvider mBloggingRemindersProvider;

    public interface OnNotificationsSettingsChangedListener {

        void onSettingsChanged(Channel channel, Type type, long siteId, JSONObject newValues);
    }

    public interface BloggingRemindersProvider {

        boolean isEnabled();

        String getSummary(long blogId);

        void onClick(long blogId);
    }

    public NotificationsSettingsDialogPreference(Context context, AttributeSet attrs, Channel channel, Type type, long blogId, NotificationsSettings settings, OnNotificationsSettingsChangedListener listener) {
        this(context, attrs, channel, type, blogId, settings, listener, null);
    }

    public NotificationsSettingsDialogPreference(Context context, AttributeSet attrs, Channel channel, Type type, long blogId, NotificationsSettings settings, OnNotificationsSettingsChangedListener listener, BloggingRemindersProvider bloggingRemindersProvider) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(13369)) {
            mChannel = channel;
        }
        if (!ListenerUtil.mutListener.listen(13370)) {
            mType = type;
        }
        if (!ListenerUtil.mutListener.listen(13371)) {
            mBlogId = blogId;
        }
        if (!ListenerUtil.mutListener.listen(13372)) {
            mSettings = settings;
        }
        if (!ListenerUtil.mutListener.listen(13373)) {
            mOnNotificationsSettingsChangedListener = listener;
        }
        mBloggingRemindersProvider = bloggingRemindersProvider;
        if (!ListenerUtil.mutListener.listen(13374)) {
            mShouldDisplayMainSwitch = mSettings.shouldDisplayMainSwitch(mChannel, mType);
        }
    }

    @Override
    protected void onBindDialogView(@NonNull View view) {
        if (!ListenerUtil.mutListener.listen(13375)) {
            super.onBindDialogView(view);
        }
        if (!ListenerUtil.mutListener.listen(13377)) {
            if (mShouldDisplayMainSwitch) {
                if (!ListenerUtil.mutListener.listen(13376)) {
                    setupTitleViewWithMainSwitch(view);
                }
            }
        }
    }

    @Override
    protected void onPrepareDialogBuilder(Builder builder) {
        if (!ListenerUtil.mutListener.listen(13378)) {
            super.onPrepareDialogBuilder(builder);
        }
        if (!ListenerUtil.mutListener.listen(13382)) {
            if (mShouldDisplayMainSwitch) {
                if (!ListenerUtil.mutListener.listen(13380)) {
                    if (mTitleViewWithMainSwitch == null) {
                        if (!ListenerUtil.mutListener.listen(13379)) {
                            AppLog.e(T.NOTIFS, "Main switch enabled but layout not set");
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(13381)) {
                    builder.setCustomTitle(mTitleViewWithMainSwitch);
                }
            }
        }
    }

    @Override
    protected View onCreateDialogView() {
        ScrollView outerView = new ScrollView(getContext());
        if (!ListenerUtil.mutListener.listen(13383)) {
            outerView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        }
        LinearLayout innerView = new LinearLayout(getContext());
        if (!ListenerUtil.mutListener.listen(13384)) {
            innerView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        }
        if (!ListenerUtil.mutListener.listen(13385)) {
            innerView.setOrientation(LinearLayout.VERTICAL);
        }
        if (!ListenerUtil.mutListener.listen(13391)) {
            if (mShouldDisplayMainSwitch) {
                View dividerView = new View(getContext());
                int dividerHeight = getContext().getResources().getDimensionPixelSize(R.dimen.notifications_settings_dialog_divider_height);
                if (!ListenerUtil.mutListener.listen(13388)) {
                    dividerView.setBackground(ContextExtensionsKt.getDrawableFromAttribute(getContext(), android.R.attr.listDivider));
                }
                if (!ListenerUtil.mutListener.listen(13389)) {
                    dividerView.setLayoutParams(new ViewGroup.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, dividerHeight));
                }
                if (!ListenerUtil.mutListener.listen(13390)) {
                    innerView.addView(dividerView);
                }
            } else {
                View spacerView = new View(getContext());
                int spacerHeight = getContext().getResources().getDimensionPixelSize(R.dimen.margin_medium);
                if (!ListenerUtil.mutListener.listen(13386)) {
                    spacerView.setLayoutParams(new ViewGroup.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, spacerHeight));
                }
                if (!ListenerUtil.mutListener.listen(13387)) {
                    innerView.addView(spacerView);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13392)) {
            mDisabledView = View.inflate(getContext(), R.layout.notifications_tab_disabled_text_layout, null);
        }
        if (!ListenerUtil.mutListener.listen(13393)) {
            mDisabledView.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        }
        if (!ListenerUtil.mutListener.listen(13394)) {
            mOptionsView = new LinearLayout(getContext());
        }
        if (!ListenerUtil.mutListener.listen(13395)) {
            mOptionsView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        }
        if (!ListenerUtil.mutListener.listen(13396)) {
            mOptionsView.setOrientation(LinearLayout.VERTICAL);
        }
        if (!ListenerUtil.mutListener.listen(13397)) {
            innerView.addView(mDisabledView);
        }
        if (!ListenerUtil.mutListener.listen(13398)) {
            innerView.addView(mOptionsView);
        }
        if (!ListenerUtil.mutListener.listen(13399)) {
            outerView.addView(innerView);
        }
        if (!ListenerUtil.mutListener.listen(13400)) {
            configureLayoutForView(mOptionsView);
        }
        return outerView;
    }

    private View configureLayoutForView(LinearLayout view) {
        JSONObject settingsJson = mSettings.getSettingsJsonForChannelAndType(mChannel, mType, mBlogId);
        String[] summaryArray = new String[0];
        if (!ListenerUtil.mutListener.listen(13408)) {
            switch(mChannel) {
                case BLOGS:
                    if (!ListenerUtil.mutListener.listen(13401)) {
                        mSettingsArray = getContext().getResources().getStringArray(R.array.notifications_blog_settings);
                    }
                    if (!ListenerUtil.mutListener.listen(13402)) {
                        mSettingsValues = getContext().getResources().getStringArray(R.array.notifications_blog_settings_values);
                    }
                    break;
                case OTHER:
                    if (!ListenerUtil.mutListener.listen(13403)) {
                        mSettingsArray = getContext().getResources().getStringArray(R.array.notifications_other_settings);
                    }
                    if (!ListenerUtil.mutListener.listen(13404)) {
                        mSettingsValues = getContext().getResources().getStringArray(R.array.notifications_other_settings_values);
                    }
                    break;
                case WPCOM:
                    if (!ListenerUtil.mutListener.listen(13405)) {
                        mSettingsArray = getContext().getResources().getStringArray(R.array.notifications_wpcom_settings);
                    }
                    if (!ListenerUtil.mutListener.listen(13406)) {
                        mSettingsValues = getContext().getResources().getStringArray(R.array.notifications_wpcom_settings_values);
                    }
                    if (!ListenerUtil.mutListener.listen(13407)) {
                        summaryArray = getContext().getResources().getStringArray(R.array.notifications_wpcom_settings_summaries);
                    }
                    break;
            }
        }
        boolean shouldShowLocalNotifications = (ListenerUtil.mutListener.listen(13409) ? (mChannel == Channel.BLOGS || mType == Type.DEVICE) : (mChannel == Channel.BLOGS && mType == Type.DEVICE));
        if (!ListenerUtil.mutListener.listen(13443)) {
            if ((ListenerUtil.mutListener.listen(13415) ? (settingsJson != null || (ListenerUtil.mutListener.listen(13414) ? (mSettingsArray.length >= mSettingsValues.length) : (ListenerUtil.mutListener.listen(13413) ? (mSettingsArray.length <= mSettingsValues.length) : (ListenerUtil.mutListener.listen(13412) ? (mSettingsArray.length > mSettingsValues.length) : (ListenerUtil.mutListener.listen(13411) ? (mSettingsArray.length < mSettingsValues.length) : (ListenerUtil.mutListener.listen(13410) ? (mSettingsArray.length != mSettingsValues.length) : (mSettingsArray.length == mSettingsValues.length))))))) : (settingsJson != null && (ListenerUtil.mutListener.listen(13414) ? (mSettingsArray.length >= mSettingsValues.length) : (ListenerUtil.mutListener.listen(13413) ? (mSettingsArray.length <= mSettingsValues.length) : (ListenerUtil.mutListener.listen(13412) ? (mSettingsArray.length > mSettingsValues.length) : (ListenerUtil.mutListener.listen(13411) ? (mSettingsArray.length < mSettingsValues.length) : (ListenerUtil.mutListener.listen(13410) ? (mSettingsArray.length != mSettingsValues.length) : (mSettingsArray.length == mSettingsValues.length))))))))) {
                if (!ListenerUtil.mutListener.listen(13442)) {
                    {
                        long _loopCounter229 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(13441) ? (i >= mSettingsArray.length) : (ListenerUtil.mutListener.listen(13440) ? (i <= mSettingsArray.length) : (ListenerUtil.mutListener.listen(13439) ? (i > mSettingsArray.length) : (ListenerUtil.mutListener.listen(13438) ? (i != mSettingsArray.length) : (ListenerUtil.mutListener.listen(13437) ? (i == mSettingsArray.length) : (i < mSettingsArray.length)))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter229", ++_loopCounter229);
                            String settingName = mSettingsArray[i];
                            String settingValue = mSettingsValues[i];
                            if (!ListenerUtil.mutListener.listen(13417)) {
                                // Skip a few settings for 'Email' section
                                if ((ListenerUtil.mutListener.listen(13416) ? (mType == Type.EMAIL || settingValue.equals(SETTING_VALUE_ACHIEVEMENT)) : (mType == Type.EMAIL && settingValue.equals(SETTING_VALUE_ACHIEVEMENT)))) {
                                    continue;
                                }
                            }
                            // Add special summary text for the WPCOM section
                            String settingSummary = null;
                            if (!ListenerUtil.mutListener.listen(13425)) {
                                if ((ListenerUtil.mutListener.listen(13423) ? (mChannel == Channel.WPCOM || (ListenerUtil.mutListener.listen(13422) ? (i >= summaryArray.length) : (ListenerUtil.mutListener.listen(13421) ? (i <= summaryArray.length) : (ListenerUtil.mutListener.listen(13420) ? (i > summaryArray.length) : (ListenerUtil.mutListener.listen(13419) ? (i != summaryArray.length) : (ListenerUtil.mutListener.listen(13418) ? (i == summaryArray.length) : (i < summaryArray.length))))))) : (mChannel == Channel.WPCOM && (ListenerUtil.mutListener.listen(13422) ? (i >= summaryArray.length) : (ListenerUtil.mutListener.listen(13421) ? (i <= summaryArray.length) : (ListenerUtil.mutListener.listen(13420) ? (i > summaryArray.length) : (ListenerUtil.mutListener.listen(13419) ? (i != summaryArray.length) : (ListenerUtil.mutListener.listen(13418) ? (i == summaryArray.length) : (i < summaryArray.length))))))))) {
                                    if (!ListenerUtil.mutListener.listen(13424)) {
                                        settingSummary = summaryArray[i];
                                    }
                                }
                            }
                            boolean isSettingChecked = JSONUtils.queryJSON(settingsJson, settingValue, true);
                            boolean isSettingLast = (ListenerUtil.mutListener.listen(13435) ? (!shouldShowLocalNotifications || (ListenerUtil.mutListener.listen(13434) ? (i >= (ListenerUtil.mutListener.listen(13429) ? (mSettingsArray.length % 1) : (ListenerUtil.mutListener.listen(13428) ? (mSettingsArray.length / 1) : (ListenerUtil.mutListener.listen(13427) ? (mSettingsArray.length * 1) : (ListenerUtil.mutListener.listen(13426) ? (mSettingsArray.length + 1) : (mSettingsArray.length - 1)))))) : (ListenerUtil.mutListener.listen(13433) ? (i <= (ListenerUtil.mutListener.listen(13429) ? (mSettingsArray.length % 1) : (ListenerUtil.mutListener.listen(13428) ? (mSettingsArray.length / 1) : (ListenerUtil.mutListener.listen(13427) ? (mSettingsArray.length * 1) : (ListenerUtil.mutListener.listen(13426) ? (mSettingsArray.length + 1) : (mSettingsArray.length - 1)))))) : (ListenerUtil.mutListener.listen(13432) ? (i > (ListenerUtil.mutListener.listen(13429) ? (mSettingsArray.length % 1) : (ListenerUtil.mutListener.listen(13428) ? (mSettingsArray.length / 1) : (ListenerUtil.mutListener.listen(13427) ? (mSettingsArray.length * 1) : (ListenerUtil.mutListener.listen(13426) ? (mSettingsArray.length + 1) : (mSettingsArray.length - 1)))))) : (ListenerUtil.mutListener.listen(13431) ? (i < (ListenerUtil.mutListener.listen(13429) ? (mSettingsArray.length % 1) : (ListenerUtil.mutListener.listen(13428) ? (mSettingsArray.length / 1) : (ListenerUtil.mutListener.listen(13427) ? (mSettingsArray.length * 1) : (ListenerUtil.mutListener.listen(13426) ? (mSettingsArray.length + 1) : (mSettingsArray.length - 1)))))) : (ListenerUtil.mutListener.listen(13430) ? (i != (ListenerUtil.mutListener.listen(13429) ? (mSettingsArray.length % 1) : (ListenerUtil.mutListener.listen(13428) ? (mSettingsArray.length / 1) : (ListenerUtil.mutListener.listen(13427) ? (mSettingsArray.length * 1) : (ListenerUtil.mutListener.listen(13426) ? (mSettingsArray.length + 1) : (mSettingsArray.length - 1)))))) : (i == (ListenerUtil.mutListener.listen(13429) ? (mSettingsArray.length % 1) : (ListenerUtil.mutListener.listen(13428) ? (mSettingsArray.length / 1) : (ListenerUtil.mutListener.listen(13427) ? (mSettingsArray.length * 1) : (ListenerUtil.mutListener.listen(13426) ? (mSettingsArray.length + 1) : (mSettingsArray.length - 1)))))))))))) : (!shouldShowLocalNotifications && (ListenerUtil.mutListener.listen(13434) ? (i >= (ListenerUtil.mutListener.listen(13429) ? (mSettingsArray.length % 1) : (ListenerUtil.mutListener.listen(13428) ? (mSettingsArray.length / 1) : (ListenerUtil.mutListener.listen(13427) ? (mSettingsArray.length * 1) : (ListenerUtil.mutListener.listen(13426) ? (mSettingsArray.length + 1) : (mSettingsArray.length - 1)))))) : (ListenerUtil.mutListener.listen(13433) ? (i <= (ListenerUtil.mutListener.listen(13429) ? (mSettingsArray.length % 1) : (ListenerUtil.mutListener.listen(13428) ? (mSettingsArray.length / 1) : (ListenerUtil.mutListener.listen(13427) ? (mSettingsArray.length * 1) : (ListenerUtil.mutListener.listen(13426) ? (mSettingsArray.length + 1) : (mSettingsArray.length - 1)))))) : (ListenerUtil.mutListener.listen(13432) ? (i > (ListenerUtil.mutListener.listen(13429) ? (mSettingsArray.length % 1) : (ListenerUtil.mutListener.listen(13428) ? (mSettingsArray.length / 1) : (ListenerUtil.mutListener.listen(13427) ? (mSettingsArray.length * 1) : (ListenerUtil.mutListener.listen(13426) ? (mSettingsArray.length + 1) : (mSettingsArray.length - 1)))))) : (ListenerUtil.mutListener.listen(13431) ? (i < (ListenerUtil.mutListener.listen(13429) ? (mSettingsArray.length % 1) : (ListenerUtil.mutListener.listen(13428) ? (mSettingsArray.length / 1) : (ListenerUtil.mutListener.listen(13427) ? (mSettingsArray.length * 1) : (ListenerUtil.mutListener.listen(13426) ? (mSettingsArray.length + 1) : (mSettingsArray.length - 1)))))) : (ListenerUtil.mutListener.listen(13430) ? (i != (ListenerUtil.mutListener.listen(13429) ? (mSettingsArray.length % 1) : (ListenerUtil.mutListener.listen(13428) ? (mSettingsArray.length / 1) : (ListenerUtil.mutListener.listen(13427) ? (mSettingsArray.length * 1) : (ListenerUtil.mutListener.listen(13426) ? (mSettingsArray.length + 1) : (mSettingsArray.length - 1)))))) : (i == (ListenerUtil.mutListener.listen(13429) ? (mSettingsArray.length % 1) : (ListenerUtil.mutListener.listen(13428) ? (mSettingsArray.length / 1) : (ListenerUtil.mutListener.listen(13427) ? (mSettingsArray.length * 1) : (ListenerUtil.mutListener.listen(13426) ? (mSettingsArray.length + 1) : (mSettingsArray.length - 1)))))))))))));
                            if (!ListenerUtil.mutListener.listen(13436)) {
                                view.addView(setupSwitchSettingView(settingName, settingValue, settingSummary, isSettingChecked, isSettingLast, mOnCheckedChangedListener));
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13448)) {
            if (shouldShowLocalNotifications) {
                boolean isBloggingRemindersEnabled = (ListenerUtil.mutListener.listen(13444) ? (mBloggingRemindersProvider != null || mBloggingRemindersProvider.isEnabled()) : (mBloggingRemindersProvider != null && mBloggingRemindersProvider.isEnabled()));
                if (!ListenerUtil.mutListener.listen(13445)) {
                    addWeeklyRoundupSetting(view, !isBloggingRemindersEnabled);
                }
                if (!ListenerUtil.mutListener.listen(13447)) {
                    if (isBloggingRemindersEnabled) {
                        if (!ListenerUtil.mutListener.listen(13446)) {
                            addBloggingReminderSetting(view);
                        }
                    }
                }
            }
        }
        return view;
    }

    private void addWeeklyRoundupSetting(LinearLayout view, boolean isLast) {
        if (!ListenerUtil.mutListener.listen(13449)) {
            view.addView(setupSwitchSettingView(getContext().getString(R.string.weekly_roundup), null, null, AppPrefs.shouldShowWeeklyRoundupNotification(mBlogId), isLast, (compoundButton, isChecked) -> AppPrefs.setShouldShowWeeklyRoundupNotification(mBlogId, isChecked)));
        }
    }

    private void addBloggingReminderSetting(LinearLayout view) {
        if (!ListenerUtil.mutListener.listen(13450)) {
            view.addView(setupClickSettingView(getContext().getString(R.string.site_settings_blogging_reminders_title), mBloggingRemindersProvider != null ? mBloggingRemindersProvider.getSummary(mBlogId) : null, true, (v -> {
                if (mBloggingRemindersProvider != null) {
                    mBloggingRemindersProvider.onClick(mBlogId);
                }
                getDialog().dismiss();
            })));
        }
    }

    private View setupSwitchSettingView(String settingName, @Nullable String settingValue, @Nullable String settingSummary, boolean isSettingChecked, boolean isSettingLast, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        return setupSettingView(settingName, settingValue, settingSummary, isSettingChecked, isSettingLast, onCheckedChangeListener, null);
    }

    private View setupClickSettingView(String settingName, String settingSummary, boolean isSettingLast, View.OnClickListener onClickListener) {
        return setupSettingView(settingName, null, settingSummary, false, isSettingLast, null, onClickListener);
    }

    private View setupSettingView(String settingName, @Nullable String settingValue, @Nullable String settingSummary, boolean isSettingChecked, boolean isSettingLast, @Nullable CompoundButton.OnCheckedChangeListener onCheckedChangeListener, @Nullable View.OnClickListener onClickListener) {
        NotificationsSettingsSwitchBinding binding = NotificationsSettingsSwitchBinding.inflate(LayoutInflater.from(getContext()));
        if (!ListenerUtil.mutListener.listen(13451)) {
            binding.notificationsSwitchTitle.setText(settingName);
        }
        if (!ListenerUtil.mutListener.listen(13454)) {
            if (!TextUtils.isEmpty(settingSummary)) {
                if (!ListenerUtil.mutListener.listen(13452)) {
                    binding.notificationsSwitchSummary.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(13453)) {
                    binding.notificationsSwitchSummary.setText(settingSummary);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13460)) {
            if (onCheckedChangeListener != null) {
                if (!ListenerUtil.mutListener.listen(13456)) {
                    binding.notificationsSwitch.setChecked(isSettingChecked);
                }
                if (!ListenerUtil.mutListener.listen(13457)) {
                    binding.notificationsSwitch.setTag(settingValue);
                }
                if (!ListenerUtil.mutListener.listen(13458)) {
                    binding.notificationsSwitch.setOnCheckedChangeListener(onCheckedChangeListener);
                }
                if (!ListenerUtil.mutListener.listen(13459)) {
                    binding.rowContainer.setOnClickListener(v -> binding.notificationsSwitch.toggle());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13455)) {
                    binding.notificationsSwitch.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13462)) {
            if (onClickListener != null) {
                if (!ListenerUtil.mutListener.listen(13461)) {
                    binding.rowContainer.setOnClickListener(onClickListener);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13467)) {
            if ((ListenerUtil.mutListener.listen(13463) ? (mShouldDisplayMainSwitch || isSettingLast) : (mShouldDisplayMainSwitch && isSettingLast))) {
                View divider = binding.notificationsListDivider;
                MarginLayoutParams mlp = (MarginLayoutParams) divider.getLayoutParams();
                if (!ListenerUtil.mutListener.listen(13464)) {
                    mlp.leftMargin = 0;
                }
                if (!ListenerUtil.mutListener.listen(13465)) {
                    mlp.rightMargin = 0;
                }
                if (!ListenerUtil.mutListener.listen(13466)) {
                    divider.setLayoutParams(mlp);
                }
            }
        }
        return binding.getRoot();
    }

    private final CompoundButton.OnCheckedChangeListener mOnCheckedChangedListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            try {
                if (!ListenerUtil.mutListener.listen(13469)) {
                    mUpdatedJson.put(compoundButton.getTag().toString(), isChecked);
                }
                if (!ListenerUtil.mutListener.listen(13473)) {
                    // Switch off main switch if all current settings switches are off
                    if ((ListenerUtil.mutListener.listen(13471) ? ((ListenerUtil.mutListener.listen(13470) ? (mMainSwitchToolbarView != null || !isChecked) : (mMainSwitchToolbarView != null && !isChecked)) || areAllSettingsSwitchesUnchecked()) : ((ListenerUtil.mutListener.listen(13470) ? (mMainSwitchToolbarView != null || !isChecked) : (mMainSwitchToolbarView != null && !isChecked)) && areAllSettingsSwitchesUnchecked()))) {
                        if (!ListenerUtil.mutListener.listen(13472)) {
                            mMainSwitchToolbarView.setChecked(false);
                        }
                    }
                }
            } catch (JSONException e) {
                if (!ListenerUtil.mutListener.listen(13468)) {
                    AppLog.e(AppLog.T.NOTIFS, "Could not add notification setting change to JSONObject");
                }
            }
        }
    };

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        if (!ListenerUtil.mutListener.listen(13484)) {
            if ((ListenerUtil.mutListener.listen(13480) ? ((ListenerUtil.mutListener.listen(13479) ? (positiveResult || (ListenerUtil.mutListener.listen(13478) ? (mUpdatedJson.length() >= 0) : (ListenerUtil.mutListener.listen(13477) ? (mUpdatedJson.length() <= 0) : (ListenerUtil.mutListener.listen(13476) ? (mUpdatedJson.length() < 0) : (ListenerUtil.mutListener.listen(13475) ? (mUpdatedJson.length() != 0) : (ListenerUtil.mutListener.listen(13474) ? (mUpdatedJson.length() == 0) : (mUpdatedJson.length() > 0))))))) : (positiveResult && (ListenerUtil.mutListener.listen(13478) ? (mUpdatedJson.length() >= 0) : (ListenerUtil.mutListener.listen(13477) ? (mUpdatedJson.length() <= 0) : (ListenerUtil.mutListener.listen(13476) ? (mUpdatedJson.length() < 0) : (ListenerUtil.mutListener.listen(13475) ? (mUpdatedJson.length() != 0) : (ListenerUtil.mutListener.listen(13474) ? (mUpdatedJson.length() == 0) : (mUpdatedJson.length() > 0)))))))) || mOnNotificationsSettingsChangedListener != null) : ((ListenerUtil.mutListener.listen(13479) ? (positiveResult || (ListenerUtil.mutListener.listen(13478) ? (mUpdatedJson.length() >= 0) : (ListenerUtil.mutListener.listen(13477) ? (mUpdatedJson.length() <= 0) : (ListenerUtil.mutListener.listen(13476) ? (mUpdatedJson.length() < 0) : (ListenerUtil.mutListener.listen(13475) ? (mUpdatedJson.length() != 0) : (ListenerUtil.mutListener.listen(13474) ? (mUpdatedJson.length() == 0) : (mUpdatedJson.length() > 0))))))) : (positiveResult && (ListenerUtil.mutListener.listen(13478) ? (mUpdatedJson.length() >= 0) : (ListenerUtil.mutListener.listen(13477) ? (mUpdatedJson.length() <= 0) : (ListenerUtil.mutListener.listen(13476) ? (mUpdatedJson.length() < 0) : (ListenerUtil.mutListener.listen(13475) ? (mUpdatedJson.length() != 0) : (ListenerUtil.mutListener.listen(13474) ? (mUpdatedJson.length() == 0) : (mUpdatedJson.length() > 0)))))))) && mOnNotificationsSettingsChangedListener != null))) {
                if (!ListenerUtil.mutListener.listen(13481)) {
                    mOnNotificationsSettingsChangedListener.onSettingsChanged(mChannel, mType, mBlogId, mUpdatedJson);
                }
                // Update the settings json
                Iterator<?> keys = mUpdatedJson.keys();
                if (!ListenerUtil.mutListener.listen(13483)) {
                    {
                        long _loopCounter230 = 0;
                        while (keys.hasNext()) {
                            ListenerUtil.loopListener.listen("_loopCounter230", ++_loopCounter230);
                            String settingName = (String) keys.next();
                            if (!ListenerUtil.mutListener.listen(13482)) {
                                mSettings.updateSettingForChannelAndType(mChannel, mType, settingName, mUpdatedJson.optBoolean(settingName), mBlogId);
                            }
                        }
                    }
                }
            }
        }
    }

    private void setupTitleViewWithMainSwitch(View view) {
        if (!ListenerUtil.mutListener.listen(13487)) {
            switch(mChannel) {
                case BLOGS:
                    if (!ListenerUtil.mutListener.listen(13486)) {
                        if (mType == Type.TIMELINE) {
                            if (!ListenerUtil.mutListener.listen(13485)) {
                                mTitleViewWithMainSwitch = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.notifications_tab_for_blog_title_layout, (ViewGroup) view, false);
                            }
                        }
                    }
                    break;
                case OTHER:
                case WPCOM:
                default:
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(13495)) {
            if (mTitleViewWithMainSwitch != null) {
                TextView titleView = mTitleViewWithMainSwitch.findViewById(R.id.title);
                CharSequence dialogTitle = getDialogTitle();
                if (!ListenerUtil.mutListener.listen(13489)) {
                    if (dialogTitle != null) {
                        if (!ListenerUtil.mutListener.listen(13488)) {
                            titleView.setText(dialogTitle);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(13490)) {
                    mMainSwitchToolbarView = mTitleViewWithMainSwitch.findViewById(R.id.main_switch);
                }
                if (!ListenerUtil.mutListener.listen(13491)) {
                    mMainSwitchToolbarView.setMainSwitchToolbarListener(this);
                }
                if (!ListenerUtil.mutListener.listen(13492)) {
                    mMainSwitchToolbarView.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
                }
                // Off: If all settings options are off
                JSONObject settingsJson = mSettings.getSettingsJsonForChannelAndType(mChannel, mType, mBlogId);
                boolean checkMainSwitch = mSettings.isAtLeastOneSettingsEnabled(settingsJson, mSettingsArray, mSettingsValues);
                if (!ListenerUtil.mutListener.listen(13493)) {
                    mMainSwitchToolbarView.loadInitialState(checkMainSwitch);
                }
                if (!ListenerUtil.mutListener.listen(13494)) {
                    hideDisabledView(mMainSwitchToolbarView.isMainChecked());
                }
            }
        }
    }

    @Override
    public void onMainSwitchCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!ListenerUtil.mutListener.listen(13496)) {
            setSettingsSwitchesChecked(isChecked);
        }
        if (!ListenerUtil.mutListener.listen(13497)) {
            hideDisabledView(isChecked);
        }
    }

    /**
     * Hide view when Notifications Tab Settings are disabled by toggling the main switch off.
     *
     * @param isMainChecked TRUE to hide disabled view, FALSE to show disabled view
     */
    private void hideDisabledView(boolean isMainChecked) {
        if (!ListenerUtil.mutListener.listen(13498)) {
            mDisabledView.setVisibility(isMainChecked ? View.GONE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(13499)) {
            mOptionsView.setVisibility(isMainChecked ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * Updates Notifications current settings switches state based on the main switch state
     *
     * @param isMainChecked TRUE to switch on the settings switches.
     *                      FALSE to switch off the settings switches.
     */
    private void setSettingsSwitchesChecked(boolean isMainChecked) {
        if (!ListenerUtil.mutListener.listen(13502)) {
            {
                long _loopCounter231 = 0;
                for (String settingValue : mSettingsValues) {
                    ListenerUtil.loopListener.listen("_loopCounter231", ++_loopCounter231);
                    final SwitchCompat toggleSwitch = mOptionsView.findViewWithTag(settingValue);
                    if (!ListenerUtil.mutListener.listen(13501)) {
                        if (toggleSwitch != null) {
                            if (!ListenerUtil.mutListener.listen(13500)) {
                                toggleSwitch.setChecked(isMainChecked);
                            }
                        }
                    }
                }
            }
        }
    }

    // returns true if all current settings switches on the dialog are unchecked
    private boolean areAllSettingsSwitchesUnchecked() {
        boolean settingsSwitchesUnchecked = true;
        if (!ListenerUtil.mutListener.listen(13506)) {
            {
                long _loopCounter232 = 0;
                for (String settingValue : mSettingsValues) {
                    ListenerUtil.loopListener.listen("_loopCounter232", ++_loopCounter232);
                    final SwitchCompat toggleSwitch = mOptionsView.findViewWithTag(settingValue);
                    if (!ListenerUtil.mutListener.listen(13505)) {
                        if (toggleSwitch != null) {
                            boolean isChecked = toggleSwitch.isChecked();
                            if (!ListenerUtil.mutListener.listen(13504)) {
                                if (isChecked) {
                                    if (!ListenerUtil.mutListener.listen(13503)) {
                                        settingsSwitchesUnchecked = false;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return settingsSwitchesUnchecked;
    }
}

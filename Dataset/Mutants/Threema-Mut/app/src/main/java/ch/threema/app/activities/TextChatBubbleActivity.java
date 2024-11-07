/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
package ch.threema.app.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.card.MaterialCardView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.ColorInt;
import androidx.annotation.LayoutRes;
import androidx.appcompat.widget.Toolbar;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.emojis.EmojiConversationTextView;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.LockAppService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.LinkifyUtil;
import ch.threema.app.utils.MessageUtil;
import ch.threema.app.utils.QuoteUtil;
import ch.threema.app.utils.StateBitmapUtil;
import ch.threema.base.ThreemaException;
import ch.threema.storage.models.AbstractMessageModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TextChatBubbleActivity extends ThreemaActivity implements GenericAlertDialog.DialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(TextChatBubbleActivity.class);

    private static final int CONTEXT_MENU_FORWARD = 600;

    private static final int CONTEXT_MENU_GROUP = 22200;

    private EmojiConversationTextView textView;

    private final ActionMode.Callback textSelectionCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            if (!ListenerUtil.mutListener.listen(6925)) {
                menu.removeGroup(CONTEXT_MENU_GROUP);
            }
            if (!ListenerUtil.mutListener.listen(6926)) {
                menu.add(CONTEXT_MENU_GROUP, CONTEXT_MENU_FORWARD, 200, R.string.forward_text);
            }
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (!ListenerUtil.mutListener.listen(6928)) {
                switch(item.getItemId()) {
                    case CONTEXT_MENU_FORWARD:
                        if (!ListenerUtil.mutListener.listen(6927)) {
                            forwardText();
                        }
                        break;
                    default:
                        return false;
                }
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
        }

        private void forwardText() {
            EmojiConversationTextView textView = findViewById(R.id.text_view);
            CharSequence text = textView.getText();
            if (!ListenerUtil.mutListener.listen(6940)) {
                if ((ListenerUtil.mutListener.listen(6933) ? (text.length() >= 0) : (ListenerUtil.mutListener.listen(6932) ? (text.length() <= 0) : (ListenerUtil.mutListener.listen(6931) ? (text.length() < 0) : (ListenerUtil.mutListener.listen(6930) ? (text.length() != 0) : (ListenerUtil.mutListener.listen(6929) ? (text.length() == 0) : (text.length() > 0))))))) {
                    int start = textView.getSelectionStart();
                    int end = textView.getSelectionEnd();
                    String body = text.subSequence(start, end).toString();
                    Intent intent = new Intent(TextChatBubbleActivity.this, RecipientListBaseActivity.class);
                    if (!ListenerUtil.mutListener.listen(6934)) {
                        intent.setType("text/plain");
                    }
                    if (!ListenerUtil.mutListener.listen(6935)) {
                        intent.setAction(Intent.ACTION_SEND);
                    }
                    if (!ListenerUtil.mutListener.listen(6936)) {
                        intent.putExtra(Intent.EXTRA_TEXT, body);
                    }
                    if (!ListenerUtil.mutListener.listen(6937)) {
                        intent.putExtra(ThreemaApplication.INTENT_DATA_IS_FORWARD, true);
                    }
                    if (!ListenerUtil.mutListener.listen(6938)) {
                        startActivity(intent);
                    }
                    if (!ListenerUtil.mutListener.listen(6939)) {
                        finish();
                    }
                }
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6941)) {
            logger.debug("onCreate");
        }
        MessageService messageService;
        PreferenceService preferenceService;
        LockAppService lockAppService;
        MessageReceiver<? extends AbstractMessageModel> messageReceiver;
        @LayoutRes
        int footerLayout;
        @ColorInt
        int color;
        String title;
        if (!ListenerUtil.mutListener.listen(6942)) {
            ConfigUtils.configureActivityTheme(this);
        }
        if (!ListenerUtil.mutListener.listen(6943)) {
            super.onCreate(savedInstanceState);
        }
        try {
            ServiceManager serviceManager = ThreemaApplication.getServiceManager();
            messageService = serviceManager.getMessageService();
            preferenceService = serviceManager.getPreferenceService();
            lockAppService = serviceManager.getLockAppService();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(6944)) {
                finish();
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(6945)) {
            // set font size according to user preferences
            getTheme().applyStyle(ThreemaApplication.getServiceManager().getPreferenceService().getFontStyle(), true);
        }
        if (!ListenerUtil.mutListener.listen(6946)) {
            // hide contents in app switcher and inhibit screenshots
            ConfigUtils.setScreenshotsAllowed(this, preferenceService, lockAppService);
        }
        if (!ListenerUtil.mutListener.listen(6947)) {
            ConfigUtils.setLocaleOverride(this, preferenceService);
        }
        if (!ListenerUtil.mutListener.listen(6948)) {
            setContentView(R.layout.activity_text_chat_bubble);
        }
        AbstractMessageModel messageModel = IntentDataUtil.getAbstractMessageModel(getIntent(), messageService);
        try {
            messageReceiver = messageService.getMessageReceiver(messageModel);
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(6949)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(6950)) {
                finish();
            }
            return;
        }
        if (messageModel.isOutbox()) {
            // send
            if (ConfigUtils.getAppTheme(this) == ConfigUtils.THEME_DARK) {
                color = getResources().getColor(R.color.dark_bubble_send);
            } else {
                color = getResources().getColor(R.color.light_bubble_send);
            }
            title = getString(R.string.threema_message_to, messageReceiver.getDisplayName());
            footerLayout = R.layout.conversation_bubble_footer_send;
        } else {
            // recv
            if (ConfigUtils.getAppTheme(this) == ConfigUtils.THEME_DARK) {
                color = getResources().getColor(R.color.dark_bubble_recv);
            } else {
                color = getResources().getColor(R.color.light_bubble_recv);
            }
            title = getString(R.string.threema_message_from, messageReceiver.getDisplayName());
            footerLayout = R.layout.conversation_bubble_footer_recv;
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (!ListenerUtil.mutListener.listen(6951)) {
            toolbar.setNavigationOnClickListener(view -> finish());
        }
        if (!ListenerUtil.mutListener.listen(6952)) {
            toolbar.setOnMenuItemClickListener(item -> {
                if (item.isChecked()) {
                    item.setChecked(false);
                    textView.setIgnoreMarkup(true);
                    setText(messageModel);
                } else {
                    item.setChecked(true);
                    textView.setIgnoreMarkup(false);
                    setText(messageModel);
                }
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(6953)) {
            toolbar.setTitle(title);
        }
        if (!ListenerUtil.mutListener.listen(6954)) {
            ConfigUtils.addIconsToOverflowMenu(this, toolbar.getMenu());
        }
        if (!ListenerUtil.mutListener.listen(6955)) {
            // TODO: replace with "toolbarNavigationButtonStyle" attribute in theme as soon as all Toolbars have been switched to Material Components
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(ConfigUtils.getAppTheme(this) == ConfigUtils.THEME_DARK ? R.color.dark_text_color_primary : R.color.text_color_secondary), PorterDuff.Mode.SRC_IN);
        }
        MaterialCardView cardView = findViewById(R.id.card_view);
        if (!ListenerUtil.mutListener.listen(6956)) {
            cardView.setCardBackgroundColor(color);
        }
        View footerView = LayoutInflater.from(this).inflate(footerLayout, null);
        if (!ListenerUtil.mutListener.listen(6957)) {
            ((ViewGroup) findViewById(R.id.footer)).addView(footerView);
        }
        if (!ListenerUtil.mutListener.listen(6958)) {
            textView = findViewById(R.id.text_view);
        }
        if (!ListenerUtil.mutListener.listen(6959)) {
            setText(messageModel);
        }
        // display date
        CharSequence s = MessageUtil.getDisplayDate(this, messageModel, true);
        if (!ListenerUtil.mutListener.listen(6960)) {
            ((TextView) footerView.findViewById(R.id.date_view)).setText(s != null ? s : "");
        }
        if (!ListenerUtil.mutListener.listen(6961)) {
            // display message status
            StateBitmapUtil.getInstance().setStateDrawable(messageModel, findViewById(R.id.delivered_indicator), true);
        }
        if (!ListenerUtil.mutListener.listen(6968)) {
            if ((ListenerUtil.mutListener.listen(6966) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(6965) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(6964) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(6963) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(6962) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                if (!ListenerUtil.mutListener.listen(6967)) {
                    // do not add on lollipop or lower due to this bug: https://issuetracker.google.com/issues/36937508
                    textView.setCustomSelectionActionModeCallback(textSelectionCallback);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6970)) {
            findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(6969)) {
                        finish();
                    }
                }
            });
        }
    }

    private void setText(AbstractMessageModel messageModel) {
        if (!ListenerUtil.mutListener.listen(6971)) {
            textView.setText(QuoteUtil.getMessageBody(messageModel, false));
        }
        if (!ListenerUtil.mutListener.listen(6977)) {
            LinkifyUtil.getInstance().linkify(null, this, textView, messageModel, (ListenerUtil.mutListener.listen(6976) ? (messageModel.getBody().length() >= 80) : (ListenerUtil.mutListener.listen(6975) ? (messageModel.getBody().length() <= 80) : (ListenerUtil.mutListener.listen(6974) ? (messageModel.getBody().length() > 80) : (ListenerUtil.mutListener.listen(6973) ? (messageModel.getBody().length() != 80) : (ListenerUtil.mutListener.listen(6972) ? (messageModel.getBody().length() == 80) : (messageModel.getBody().length() < 80)))))), false, null);
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(6979)) {
            if (LinkifyUtil.DIALOG_TAG_CONFIRM_LINK.equals(tag)) {
                if (!ListenerUtil.mutListener.listen(6978)) {
                    LinkifyUtil.getInstance().openLink(this, (Uri) data);
                }
            }
        }
    }

    @Override
    public void onNo(String tag, Object data) {
    }
}

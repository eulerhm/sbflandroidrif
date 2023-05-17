/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
package ch.threema.app.utils;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.IDN;
import java.util.regex.Pattern;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.util.LinkifyCompat;
import androidx.core.view.GestureDetectorCompat;
import ch.threema.app.BuildConfig;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.ContactDetailActivity;
import ch.threema.app.adapters.decorators.ChatAdapterDecorator;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.fragments.ComposeMessageFragment;
import ch.threema.app.services.ContactService;
import ch.threema.app.ui.MentionClickableSpan;
import ch.threema.storage.models.AbstractMessageModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LinkifyUtil {

    private static final Logger logger = LoggerFactory.getLogger(LinkifyUtil.class);

    public static final String DIALOG_TAG_CONFIRM_LINK = "cnfl";

    private final Pattern compose, add, license;

    private GestureDetectorCompat gestureDetector;

    private boolean isLongClick;

    // Singleton stuff
    private static LinkifyUtil sInstance = null;

    public static synchronized LinkifyUtil getInstance() {
        if (!ListenerUtil.mutListener.listen(54545)) {
            if (sInstance == null) {
                if (!ListenerUtil.mutListener.listen(54544)) {
                    sInstance = new LinkifyUtil();
                }
            }
        }
        return sInstance;
    }

    private LinkifyUtil() {
        this.add = Pattern.compile("\\b" + BuildConfig.uriScheme + "://add\\?id=\\S{8}\\b");
        this.compose = Pattern.compile("\\b" + BuildConfig.uriScheme + "://compose\\?\\S+\\b");
        this.license = Pattern.compile("\\b" + BuildConfig.uriScheme + "://license\\?key=\\S{11}\\b");
        if (!ListenerUtil.mutListener.listen(54558)) {
            this.gestureDetector = new GestureDetectorCompat(null, new GestureDetector.OnGestureListener() {

                @Override
                public boolean onDown(MotionEvent e) {
                    if (!ListenerUtil.mutListener.listen(54546)) {
                        logger.debug("onDown detected");
                    }
                    if (!ListenerUtil.mutListener.listen(54547)) {
                        isLongClick = false;
                    }
                    return false;
                }

                @Override
                public void onShowPress(MotionEvent e) {
                    if (!ListenerUtil.mutListener.listen(54548)) {
                        logger.debug("onShowPress detected");
                    }
                    if (!ListenerUtil.mutListener.listen(54549)) {
                        isLongClick = false;
                    }
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    if (!ListenerUtil.mutListener.listen(54550)) {
                        logger.debug("onSingleTapUp detected");
                    }
                    if (!ListenerUtil.mutListener.listen(54551)) {
                        isLongClick = false;
                    }
                    return false;
                }

                @Override
                public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                    if (!ListenerUtil.mutListener.listen(54552)) {
                        logger.debug("onScroll detected");
                    }
                    if (!ListenerUtil.mutListener.listen(54553)) {
                        isLongClick = false;
                    }
                    return false;
                }

                public void onLongPress(MotionEvent e) {
                    if (!ListenerUtil.mutListener.listen(54554)) {
                        isLongClick = true;
                    }
                    if (!ListenerUtil.mutListener.listen(54555)) {
                        logger.debug("Longpress detected");
                    }
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    if (!ListenerUtil.mutListener.listen(54556)) {
                        logger.debug("onFling detected");
                    }
                    if (!ListenerUtil.mutListener.listen(54557)) {
                        isLongClick = false;
                    }
                    return false;
                }
            });
        }
    }

    public void linkifyText(TextView bodyTextView, boolean includePhoneNumbers) {
        if (!ListenerUtil.mutListener.listen(54561)) {
            // which linkify every kind of number combination imaginable
            if (includePhoneNumbers) {
                if (!ListenerUtil.mutListener.listen(54560)) {
                    LinkifyCompat.addLinks(bodyTextView, Linkify.WEB_URLS | Linkify.EMAIL_ADDRESSES | Linkify.PHONE_NUMBERS);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(54559)) {
                    LinkifyCompat.addLinks(bodyTextView, Linkify.WEB_URLS | Linkify.EMAIL_ADDRESSES);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(54562)) {
            LinkifyCompat.addLinks(bodyTextView, this.add, null);
        }
        if (!ListenerUtil.mutListener.listen(54563)) {
            LinkifyCompat.addLinks(bodyTextView, this.compose, null);
        }
        if (!ListenerUtil.mutListener.listen(54564)) {
            LinkifyCompat.addLinks(bodyTextView, this.license, null);
        }
    }

    /**
     *  Linkify (Add links to) TextView hosted in a chat bubble
     *
     *  @param fragment The hosting Fragment
     *  @param bodyTextView TextView where linkify will be applied to
     *  @param messageModel MessageModel of the message
     *  @param includePhoneNumbers Whether to linkify number sequences that may represent phone number
     *  @param actionModeEnabled Whether action mode (item selection) is currently enabled
     *  @param onClickElement Callback to which unhandled clicks are forwarded
     */
    public void linkify(@NonNull ComposeMessageFragment fragment, @NonNull TextView bodyTextView, @NonNull AbstractMessageModel messageModel, boolean includePhoneNumbers, boolean actionModeEnabled, @Nullable ChatAdapterDecorator.OnClickElement onClickElement) {
        if (!ListenerUtil.mutListener.listen(54565)) {
            linkify(fragment, null, bodyTextView, messageModel, includePhoneNumbers, actionModeEnabled, onClickElement);
        }
    }

    /**
     *  Linkify (Add links to) TextView hosted in a chat bubble
     *
     *  @param fragment The hosting Fragment
     *  @param activity The hosting activity (fragment must be null)
     *  @param bodyTextView TextView where linkify will be applied to
     *  @param messageModel MessageModel of the message
     *  @param includePhoneNumbers Whether to linkify number sequences that may represent phone number
     *  @param actionModeEnabled Whether action mode (item selection) is currently enabled
     *  @param onClickElement Callback to which unhandled clicks are forwarded
     */
    @SuppressLint("ClickableViewAccessibility")
    public void linkify(@Nullable ComposeMessageFragment fragment, @Nullable AppCompatActivity activity, @NonNull TextView bodyTextView, @NonNull AbstractMessageModel messageModel, boolean includePhoneNumbers, boolean actionModeEnabled, @Nullable ChatAdapterDecorator.OnClickElement onClickElement) {
        Context context = fragment != null ? fragment.getContext() : activity;
        if (!ListenerUtil.mutListener.listen(54566)) {
            linkifyText(bodyTextView, includePhoneNumbers);
        }
        if (!ListenerUtil.mutListener.listen(54567)) {
            if (context == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(54569)) {
            if (fragment != null) {
                if (!ListenerUtil.mutListener.listen(54568)) {
                    // handle click on linkify here - otherwise it confuses the listview
                    bodyTextView.setMovementMethod(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(54570)) {
            isLongClick = false;
        }
        if (!ListenerUtil.mutListener.listen(54612)) {
            // handle taps on links
            bodyTextView.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    TextView widget = (TextView) v;
                    Object text = widget.getText();
                    if (!ListenerUtil.mutListener.listen(54571)) {
                        // we're only interested in spannables
                        if (!(text instanceof Spannable)) {
                            return false;
                        }
                    }
                    Spannable buffer = (Spannable) text;
                    int action = event.getAction();
                    int x = (ListenerUtil.mutListener.listen(54575) ? ((int) event.getX() % widget.getTotalPaddingLeft()) : (ListenerUtil.mutListener.listen(54574) ? ((int) event.getX() / widget.getTotalPaddingLeft()) : (ListenerUtil.mutListener.listen(54573) ? ((int) event.getX() * widget.getTotalPaddingLeft()) : (ListenerUtil.mutListener.listen(54572) ? ((int) event.getX() + widget.getTotalPaddingLeft()) : ((int) event.getX() - widget.getTotalPaddingLeft()))))) + widget.getScrollX();
                    int y = (ListenerUtil.mutListener.listen(54579) ? ((int) event.getY() % widget.getTotalPaddingTop()) : (ListenerUtil.mutListener.listen(54578) ? ((int) event.getY() / widget.getTotalPaddingTop()) : (ListenerUtil.mutListener.listen(54577) ? ((int) event.getY() * widget.getTotalPaddingTop()) : (ListenerUtil.mutListener.listen(54576) ? ((int) event.getY() + widget.getTotalPaddingTop()) : ((int) event.getY() - widget.getTotalPaddingTop()))))) + widget.getScrollY();
                    Layout layout = widget.getLayout();
                    if (!ListenerUtil.mutListener.listen(54580)) {
                        if (layout == null) {
                            return false;
                        }
                    }
                    int line = layout.getLineForVertical(y);
                    int off = layout.getOffsetForHorizontal(line, x);
                    ClickableSpan[] link = buffer.getSpans(off, off, ClickableSpan.class);
                    if (!ListenerUtil.mutListener.listen(54581)) {
                        if (link.length == 0) {
                            return false;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(54582)) {
                        gestureDetector.onTouchEvent(event);
                    }
                    if (!ListenerUtil.mutListener.listen(54611)) {
                        switch(action) {
                            case MotionEvent.ACTION_UP:
                                if (!ListenerUtil.mutListener.listen(54583)) {
                                    logger.debug("ACTION_UP");
                                }
                                if (!ListenerUtil.mutListener.listen(54607)) {
                                    if (!actionModeEnabled) {
                                        if (!ListenerUtil.mutListener.listen(54606)) {
                                            if (link[0] instanceof URLSpan) {
                                                if (!ListenerUtil.mutListener.listen(54591)) {
                                                    if (fragment == null) {
                                                        if (!ListenerUtil.mutListener.listen(54590)) {
                                                            Selection.removeSelection(buffer);
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(54594)) {
                                                    if (isLongClick) {
                                                        if (!ListenerUtil.mutListener.listen(54592)) {
                                                            logger.debug("Ignore link due to long click");
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(54593)) {
                                                            isLongClick = false;
                                                        }
                                                        return true;
                                                    }
                                                }
                                                URLSpan urlSpan = (URLSpan) link[0];
                                                Uri uri = Uri.parse(urlSpan.getURL());
                                                if (!ListenerUtil.mutListener.listen(54605)) {
                                                    if (UrlUtil.isLegalUri(uri)) {
                                                        if (!ListenerUtil.mutListener.listen(54604)) {
                                                            LinkifyUtil.this.openLink(context, uri);
                                                        }
                                                    } else {
                                                        String host = uri.getHost();
                                                        if (!ListenerUtil.mutListener.listen(54603)) {
                                                            if (!TestUtil.empty(host)) {
                                                                String idnUrl = null;
                                                                try {
                                                                    if (!ListenerUtil.mutListener.listen(54597)) {
                                                                        idnUrl = IDN.toASCII(host);
                                                                    }
                                                                } catch (IllegalArgumentException e) {
                                                                    if (!ListenerUtil.mutListener.listen(54596)) {
                                                                        logger.error("Exception", e);
                                                                    }
                                                                }
                                                                String warningMessage;
                                                                if (idnUrl != null) {
                                                                    warningMessage = String.format(context.getString(R.string.url_warning_body), host, idnUrl);
                                                                } else {
                                                                    warningMessage = context.getString(R.string.url_warning_body_alt);
                                                                }
                                                                GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.url_warning_title, warningMessage, R.string.ok, R.string.cancel);
                                                                if (!ListenerUtil.mutListener.listen(54598)) {
                                                                    dialog.setData(uri);
                                                                }
                                                                if (!ListenerUtil.mutListener.listen(54602)) {
                                                                    if (fragment != null) {
                                                                        if (!ListenerUtil.mutListener.listen(54600)) {
                                                                            dialog.setTargetFragment(fragment, 0);
                                                                        }
                                                                        if (!ListenerUtil.mutListener.listen(54601)) {
                                                                            dialog.show(fragment.getFragmentManager(), DIALOG_TAG_CONFIRM_LINK);
                                                                        }
                                                                    } else {
                                                                        if (!ListenerUtil.mutListener.listen(54599)) {
                                                                            dialog.show(activity.getSupportFragmentManager(), DIALOG_TAG_CONFIRM_LINK);
                                                                        }
                                                                    }
                                                                }
                                                            } else {
                                                                if (!ListenerUtil.mutListener.listen(54595)) {
                                                                    LinkifyUtil.this.openLink(context, uri);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else if (link[0] instanceof MentionClickableSpan) {
                                                MentionClickableSpan clickableSpan = (MentionClickableSpan) link[0];
                                                if (!ListenerUtil.mutListener.listen(54589)) {
                                                    if (!clickableSpan.getText().equals(ContactService.ALL_USERS_PLACEHOLDER_ID)) {
                                                        Intent intent = new Intent(context, ContactDetailActivity.class);
                                                        if (!ListenerUtil.mutListener.listen(54586)) {
                                                            intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, clickableSpan.getText());
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(54587)) {
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(54588)) {
                                                            context.startActivity(intent);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(54585)) {
                                            if (onClickElement != null) {
                                                if (!ListenerUtil.mutListener.listen(54584)) {
                                                    onClickElement.onClick(messageModel);
                                                }
                                            }
                                        }
                                    }
                                }
                                return true;
                            case MotionEvent.ACTION_DOWN:
                                if (!ListenerUtil.mutListener.listen(54608)) {
                                    logger.debug("ACTION_DOWN");
                                }
                                if (!ListenerUtil.mutListener.listen(54610)) {
                                    if (fragment == null) {
                                        if (!ListenerUtil.mutListener.listen(54609)) {
                                            Selection.setSelection(buffer, buffer.getSpanStart(link[0]), buffer.getSpanEnd(link[0]));
                                        }
                                    }
                                }
                                return true;
                        }
                    }
                    return false;
                }
            });
        }
    }

    public void openLink(@NonNull Context context, Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(54613)) {
            bundle.putBoolean("new_window", true);
        }
        if (!ListenerUtil.mutListener.listen(54614)) {
            intent.putExtras(bundle);
        }
        try {
            if (!ListenerUtil.mutListener.listen(54616)) {
                context.startActivity(intent);
            }
        } catch (ActivityNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(54615)) {
                Toast.makeText(context, R.string.no_activity_for_mime_type, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

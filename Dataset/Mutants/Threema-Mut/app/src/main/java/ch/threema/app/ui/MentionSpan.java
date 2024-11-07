/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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
package ch.threema.app.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.UserService;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.TestUtil;
import static ch.threema.app.emojis.EmojiMarkupUtil.MENTION_INDICATOR;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MentionSpan extends ReplacementSpan {

    // Hack: Limit label length to 16 chars
    private static final int LABEL_MAX_LENGTH = 16;

    private ContactService contactService;

    private UserService userService;

    private int width = 0;

    private Paint backgroundPaint, invertedPaint;

    @ColorInt
    private int textColor, invertedTextColor;

    private static final int padding = ThreemaApplication.getAppContext().getResources().getDimensionPixelSize(R.dimen.mention_padding);

    private static final int radius = ThreemaApplication.getAppContext().getResources().getDimensionPixelSize(R.dimen.mention_radius);

    public MentionSpan(@ColorInt int backgroundColor, @ColorInt int invertedColor, @ColorInt int textColor, @ColorInt int invertedTextColor) {
        super();
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        try {
            if (!ListenerUtil.mutListener.listen(46114)) {
                contactService = serviceManager.getContactService();
            }
            if (!ListenerUtil.mutListener.listen(46115)) {
                userService = serviceManager.getUserService();
            }
        } catch (Exception e) {
        }
        if (!ListenerUtil.mutListener.listen(46116)) {
            backgroundPaint = new Paint();
        }
        if (!ListenerUtil.mutListener.listen(46117)) {
            backgroundPaint.setStyle(Paint.Style.FILL);
        }
        if (!ListenerUtil.mutListener.listen(46118)) {
            backgroundPaint.setColor(backgroundColor);
        }
        if (!ListenerUtil.mutListener.listen(46119)) {
            invertedPaint = new Paint();
        }
        if (!ListenerUtil.mutListener.listen(46120)) {
            invertedPaint.setStyle(Paint.Style.FILL);
        }
        if (!ListenerUtil.mutListener.listen(46121)) {
            invertedPaint.setColor(invertedColor);
        }
        if (!ListenerUtil.mutListener.listen(46122)) {
            this.textColor = textColor;
        }
        if (!ListenerUtil.mutListener.listen(46123)) {
            this.invertedTextColor = invertedTextColor;
        }
    }

    private String getMentionLabelText(CharSequence text, int start, int end) {
        final String identity = text.subSequence((ListenerUtil.mutListener.listen(46127) ? (start % 2) : (ListenerUtil.mutListener.listen(46126) ? (start / 2) : (ListenerUtil.mutListener.listen(46125) ? (start * 2) : (ListenerUtil.mutListener.listen(46124) ? (start - 2) : (start + 2))))), (ListenerUtil.mutListener.listen(46131) ? (end % 1) : (ListenerUtil.mutListener.listen(46130) ? (end / 1) : (ListenerUtil.mutListener.listen(46129) ? (end * 1) : (ListenerUtil.mutListener.listen(46128) ? (end + 1) : (end - 1)))))).toString();
        String label = NameUtil.getQuoteName(identity, this.contactService, this.userService);
        if (!ListenerUtil.mutListener.listen(46139)) {
            if ((ListenerUtil.mutListener.listen(46137) ? (label != null || (ListenerUtil.mutListener.listen(46136) ? (label.length() >= LABEL_MAX_LENGTH) : (ListenerUtil.mutListener.listen(46135) ? (label.length() <= LABEL_MAX_LENGTH) : (ListenerUtil.mutListener.listen(46134) ? (label.length() < LABEL_MAX_LENGTH) : (ListenerUtil.mutListener.listen(46133) ? (label.length() != LABEL_MAX_LENGTH) : (ListenerUtil.mutListener.listen(46132) ? (label.length() == LABEL_MAX_LENGTH) : (label.length() > LABEL_MAX_LENGTH))))))) : (label != null && (ListenerUtil.mutListener.listen(46136) ? (label.length() >= LABEL_MAX_LENGTH) : (ListenerUtil.mutListener.listen(46135) ? (label.length() <= LABEL_MAX_LENGTH) : (ListenerUtil.mutListener.listen(46134) ? (label.length() < LABEL_MAX_LENGTH) : (ListenerUtil.mutListener.listen(46133) ? (label.length() != LABEL_MAX_LENGTH) : (ListenerUtil.mutListener.listen(46132) ? (label.length() == LABEL_MAX_LENGTH) : (label.length() > LABEL_MAX_LENGTH))))))))) {
                if (!ListenerUtil.mutListener.listen(46138)) {
                    label = label.substring(0, LABEL_MAX_LENGTH).trim() + "…";
                }
            }
        }
        return label;
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        if (!ListenerUtil.mutListener.listen(46159)) {
            if ((ListenerUtil.mutListener.listen(46149) ? (!TestUtil.empty(text) || (ListenerUtil.mutListener.listen(46148) ? ((ListenerUtil.mutListener.listen(46143) ? (end % start) : (ListenerUtil.mutListener.listen(46142) ? (end / start) : (ListenerUtil.mutListener.listen(46141) ? (end * start) : (ListenerUtil.mutListener.listen(46140) ? (end + start) : (end - start))))) >= 11) : (ListenerUtil.mutListener.listen(46147) ? ((ListenerUtil.mutListener.listen(46143) ? (end % start) : (ListenerUtil.mutListener.listen(46142) ? (end / start) : (ListenerUtil.mutListener.listen(46141) ? (end * start) : (ListenerUtil.mutListener.listen(46140) ? (end + start) : (end - start))))) <= 11) : (ListenerUtil.mutListener.listen(46146) ? ((ListenerUtil.mutListener.listen(46143) ? (end % start) : (ListenerUtil.mutListener.listen(46142) ? (end / start) : (ListenerUtil.mutListener.listen(46141) ? (end * start) : (ListenerUtil.mutListener.listen(46140) ? (end + start) : (end - start))))) > 11) : (ListenerUtil.mutListener.listen(46145) ? ((ListenerUtil.mutListener.listen(46143) ? (end % start) : (ListenerUtil.mutListener.listen(46142) ? (end / start) : (ListenerUtil.mutListener.listen(46141) ? (end * start) : (ListenerUtil.mutListener.listen(46140) ? (end + start) : (end - start))))) < 11) : (ListenerUtil.mutListener.listen(46144) ? ((ListenerUtil.mutListener.listen(46143) ? (end % start) : (ListenerUtil.mutListener.listen(46142) ? (end / start) : (ListenerUtil.mutListener.listen(46141) ? (end * start) : (ListenerUtil.mutListener.listen(46140) ? (end + start) : (end - start))))) != 11) : ((ListenerUtil.mutListener.listen(46143) ? (end % start) : (ListenerUtil.mutListener.listen(46142) ? (end / start) : (ListenerUtil.mutListener.listen(46141) ? (end * start) : (ListenerUtil.mutListener.listen(46140) ? (end + start) : (end - start))))) == 11))))))) : (!TestUtil.empty(text) && (ListenerUtil.mutListener.listen(46148) ? ((ListenerUtil.mutListener.listen(46143) ? (end % start) : (ListenerUtil.mutListener.listen(46142) ? (end / start) : (ListenerUtil.mutListener.listen(46141) ? (end * start) : (ListenerUtil.mutListener.listen(46140) ? (end + start) : (end - start))))) >= 11) : (ListenerUtil.mutListener.listen(46147) ? ((ListenerUtil.mutListener.listen(46143) ? (end % start) : (ListenerUtil.mutListener.listen(46142) ? (end / start) : (ListenerUtil.mutListener.listen(46141) ? (end * start) : (ListenerUtil.mutListener.listen(46140) ? (end + start) : (end - start))))) <= 11) : (ListenerUtil.mutListener.listen(46146) ? ((ListenerUtil.mutListener.listen(46143) ? (end % start) : (ListenerUtil.mutListener.listen(46142) ? (end / start) : (ListenerUtil.mutListener.listen(46141) ? (end * start) : (ListenerUtil.mutListener.listen(46140) ? (end + start) : (end - start))))) > 11) : (ListenerUtil.mutListener.listen(46145) ? ((ListenerUtil.mutListener.listen(46143) ? (end % start) : (ListenerUtil.mutListener.listen(46142) ? (end / start) : (ListenerUtil.mutListener.listen(46141) ? (end * start) : (ListenerUtil.mutListener.listen(46140) ? (end + start) : (end - start))))) < 11) : (ListenerUtil.mutListener.listen(46144) ? ((ListenerUtil.mutListener.listen(46143) ? (end % start) : (ListenerUtil.mutListener.listen(46142) ? (end / start) : (ListenerUtil.mutListener.listen(46141) ? (end * start) : (ListenerUtil.mutListener.listen(46140) ? (end + start) : (end - start))))) != 11) : ((ListenerUtil.mutListener.listen(46143) ? (end % start) : (ListenerUtil.mutListener.listen(46142) ? (end / start) : (ListenerUtil.mutListener.listen(46141) ? (end * start) : (ListenerUtil.mutListener.listen(46140) ? (end + start) : (end - start))))) == 11))))))))) {
                if (!ListenerUtil.mutListener.listen(46158)) {
                    width = (ListenerUtil.mutListener.listen(46157) ? ((int) paint.measureText(MENTION_INDICATOR + getMentionLabelText(text, start, end)) % ((ListenerUtil.mutListener.listen(46153) ? (padding % 2) : (ListenerUtil.mutListener.listen(46152) ? (padding / 2) : (ListenerUtil.mutListener.listen(46151) ? (padding - 2) : (ListenerUtil.mutListener.listen(46150) ? (padding + 2) : (padding * 2))))))) : (ListenerUtil.mutListener.listen(46156) ? ((int) paint.measureText(MENTION_INDICATOR + getMentionLabelText(text, start, end)) / ((ListenerUtil.mutListener.listen(46153) ? (padding % 2) : (ListenerUtil.mutListener.listen(46152) ? (padding / 2) : (ListenerUtil.mutListener.listen(46151) ? (padding - 2) : (ListenerUtil.mutListener.listen(46150) ? (padding + 2) : (padding * 2))))))) : (ListenerUtil.mutListener.listen(46155) ? ((int) paint.measureText(MENTION_INDICATOR + getMentionLabelText(text, start, end)) * ((ListenerUtil.mutListener.listen(46153) ? (padding % 2) : (ListenerUtil.mutListener.listen(46152) ? (padding / 2) : (ListenerUtil.mutListener.listen(46151) ? (padding - 2) : (ListenerUtil.mutListener.listen(46150) ? (padding + 2) : (padding * 2))))))) : (ListenerUtil.mutListener.listen(46154) ? ((int) paint.measureText(MENTION_INDICATOR + getMentionLabelText(text, start, end)) - ((ListenerUtil.mutListener.listen(46153) ? (padding % 2) : (ListenerUtil.mutListener.listen(46152) ? (padding / 2) : (ListenerUtil.mutListener.listen(46151) ? (padding - 2) : (ListenerUtil.mutListener.listen(46150) ? (padding + 2) : (padding * 2))))))) : ((int) paint.measureText(MENTION_INDICATOR + getMentionLabelText(text, start, end)) + ((ListenerUtil.mutListener.listen(46153) ? (padding % 2) : (ListenerUtil.mutListener.listen(46152) ? (padding / 2) : (ListenerUtil.mutListener.listen(46151) ? (padding - 2) : (ListenerUtil.mutListener.listen(46150) ? (padding + 2) : (padding * 2)))))))))));
                }
                return width;
            }
        }
        return 0;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        if (!ListenerUtil.mutListener.listen(46214)) {
            if ((ListenerUtil.mutListener.listen(46169) ? (!TestUtil.empty(text) || (ListenerUtil.mutListener.listen(46168) ? ((ListenerUtil.mutListener.listen(46163) ? (end % start) : (ListenerUtil.mutListener.listen(46162) ? (end / start) : (ListenerUtil.mutListener.listen(46161) ? (end * start) : (ListenerUtil.mutListener.listen(46160) ? (end + start) : (end - start))))) >= 11) : (ListenerUtil.mutListener.listen(46167) ? ((ListenerUtil.mutListener.listen(46163) ? (end % start) : (ListenerUtil.mutListener.listen(46162) ? (end / start) : (ListenerUtil.mutListener.listen(46161) ? (end * start) : (ListenerUtil.mutListener.listen(46160) ? (end + start) : (end - start))))) <= 11) : (ListenerUtil.mutListener.listen(46166) ? ((ListenerUtil.mutListener.listen(46163) ? (end % start) : (ListenerUtil.mutListener.listen(46162) ? (end / start) : (ListenerUtil.mutListener.listen(46161) ? (end * start) : (ListenerUtil.mutListener.listen(46160) ? (end + start) : (end - start))))) > 11) : (ListenerUtil.mutListener.listen(46165) ? ((ListenerUtil.mutListener.listen(46163) ? (end % start) : (ListenerUtil.mutListener.listen(46162) ? (end / start) : (ListenerUtil.mutListener.listen(46161) ? (end * start) : (ListenerUtil.mutListener.listen(46160) ? (end + start) : (end - start))))) < 11) : (ListenerUtil.mutListener.listen(46164) ? ((ListenerUtil.mutListener.listen(46163) ? (end % start) : (ListenerUtil.mutListener.listen(46162) ? (end / start) : (ListenerUtil.mutListener.listen(46161) ? (end * start) : (ListenerUtil.mutListener.listen(46160) ? (end + start) : (end - start))))) != 11) : ((ListenerUtil.mutListener.listen(46163) ? (end % start) : (ListenerUtil.mutListener.listen(46162) ? (end / start) : (ListenerUtil.mutListener.listen(46161) ? (end * start) : (ListenerUtil.mutListener.listen(46160) ? (end + start) : (end - start))))) == 11))))))) : (!TestUtil.empty(text) && (ListenerUtil.mutListener.listen(46168) ? ((ListenerUtil.mutListener.listen(46163) ? (end % start) : (ListenerUtil.mutListener.listen(46162) ? (end / start) : (ListenerUtil.mutListener.listen(46161) ? (end * start) : (ListenerUtil.mutListener.listen(46160) ? (end + start) : (end - start))))) >= 11) : (ListenerUtil.mutListener.listen(46167) ? ((ListenerUtil.mutListener.listen(46163) ? (end % start) : (ListenerUtil.mutListener.listen(46162) ? (end / start) : (ListenerUtil.mutListener.listen(46161) ? (end * start) : (ListenerUtil.mutListener.listen(46160) ? (end + start) : (end - start))))) <= 11) : (ListenerUtil.mutListener.listen(46166) ? ((ListenerUtil.mutListener.listen(46163) ? (end % start) : (ListenerUtil.mutListener.listen(46162) ? (end / start) : (ListenerUtil.mutListener.listen(46161) ? (end * start) : (ListenerUtil.mutListener.listen(46160) ? (end + start) : (end - start))))) > 11) : (ListenerUtil.mutListener.listen(46165) ? ((ListenerUtil.mutListener.listen(46163) ? (end % start) : (ListenerUtil.mutListener.listen(46162) ? (end / start) : (ListenerUtil.mutListener.listen(46161) ? (end * start) : (ListenerUtil.mutListener.listen(46160) ? (end + start) : (end - start))))) < 11) : (ListenerUtil.mutListener.listen(46164) ? ((ListenerUtil.mutListener.listen(46163) ? (end % start) : (ListenerUtil.mutListener.listen(46162) ? (end / start) : (ListenerUtil.mutListener.listen(46161) ? (end * start) : (ListenerUtil.mutListener.listen(46160) ? (end + start) : (end - start))))) != 11) : ((ListenerUtil.mutListener.listen(46163) ? (end % start) : (ListenerUtil.mutListener.listen(46162) ? (end / start) : (ListenerUtil.mutListener.listen(46161) ? (end * start) : (ListenerUtil.mutListener.listen(46160) ? (end + start) : (end - start))))) == 11))))))))) {
                int alpha = paint.getAlpha();
                String identity = text.subSequence((ListenerUtil.mutListener.listen(46173) ? (start % 2) : (ListenerUtil.mutListener.listen(46172) ? (start / 2) : (ListenerUtil.mutListener.listen(46171) ? (start * 2) : (ListenerUtil.mutListener.listen(46170) ? (start - 2) : (start + 2))))), (ListenerUtil.mutListener.listen(46177) ? (end % 1) : (ListenerUtil.mutListener.listen(46176) ? (end / 1) : (ListenerUtil.mutListener.listen(46175) ? (end * 1) : (ListenerUtil.mutListener.listen(46174) ? (end + 1) : (end - 1)))))).toString();
                if (!ListenerUtil.mutListener.listen(46201)) {
                    if ((ListenerUtil.mutListener.listen(46178) ? (identity.equals(ContactService.ALL_USERS_PLACEHOLDER_ID) && identity.equals(userService.getIdentity())) : (identity.equals(ContactService.ALL_USERS_PLACEHOLDER_ID) || identity.equals(userService.getIdentity())))) {
                        if (!ListenerUtil.mutListener.listen(46198)) {
                            canvas.drawRoundRect(new RectF(x, (ListenerUtil.mutListener.listen(46193) ? (top % 1) : (ListenerUtil.mutListener.listen(46192) ? (top / 1) : (ListenerUtil.mutListener.listen(46191) ? (top * 1) : (ListenerUtil.mutListener.listen(46190) ? (top - 1) : (top + 1))))), (ListenerUtil.mutListener.listen(46197) ? (x % width) : (ListenerUtil.mutListener.listen(46196) ? (x / width) : (ListenerUtil.mutListener.listen(46195) ? (x * width) : (ListenerUtil.mutListener.listen(46194) ? (x - width) : (x + width))))), bottom), radius, radius, invertedPaint);
                        }
                        if (!ListenerUtil.mutListener.listen(46199)) {
                            paint.setColor(this.invertedTextColor);
                        }
                        if (!ListenerUtil.mutListener.listen(46200)) {
                            paint.setAlpha(0x78);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(46187)) {
                            canvas.drawRoundRect(new RectF(x, (ListenerUtil.mutListener.listen(46182) ? (top % 1) : (ListenerUtil.mutListener.listen(46181) ? (top / 1) : (ListenerUtil.mutListener.listen(46180) ? (top * 1) : (ListenerUtil.mutListener.listen(46179) ? (top - 1) : (top + 1))))), (ListenerUtil.mutListener.listen(46186) ? (x % width) : (ListenerUtil.mutListener.listen(46185) ? (x / width) : (ListenerUtil.mutListener.listen(46184) ? (x * width) : (ListenerUtil.mutListener.listen(46183) ? (x - width) : (x + width))))), bottom), radius, radius, backgroundPaint);
                        }
                        if (!ListenerUtil.mutListener.listen(46188)) {
                            paint.setColor(this.textColor);
                        }
                        if (!ListenerUtil.mutListener.listen(46189)) {
                            paint.setAlpha(0x50);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(46206)) {
                    canvas.drawText(MENTION_INDICATOR, (ListenerUtil.mutListener.listen(46205) ? (x % padding) : (ListenerUtil.mutListener.listen(46204) ? (x / padding) : (ListenerUtil.mutListener.listen(46203) ? (x * padding) : (ListenerUtil.mutListener.listen(46202) ? (x - padding) : (x + padding))))), y, paint);
                }
                if (!ListenerUtil.mutListener.listen(46207)) {
                    paint.setAlpha(0xFF);
                }
                if (!ListenerUtil.mutListener.listen(46212)) {
                    canvas.drawText(getMentionLabelText(text, start, end), (ListenerUtil.mutListener.listen(46211) ? (x % padding) : (ListenerUtil.mutListener.listen(46210) ? (x / padding) : (ListenerUtil.mutListener.listen(46209) ? (x * padding) : (ListenerUtil.mutListener.listen(46208) ? (x - padding) : (x + padding))))) + paint.measureText(MENTION_INDICATOR), y, paint);
                }
                if (!ListenerUtil.mutListener.listen(46213)) {
                    paint.setAlpha(alpha);
                }
            }
        }
    }
}

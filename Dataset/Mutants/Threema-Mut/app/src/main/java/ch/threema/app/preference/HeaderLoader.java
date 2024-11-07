/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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
package ch.threema.app.preference;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.XmlRes;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 */
class HeaderLoader {

    static void loadFromResource(@NonNull final Context context, @XmlRes final int resId, @NonNull final List<Header> target) {
        XmlResourceParser parser = null;
        try {
            if (!ListenerUtil.mutListener.listen(31786)) {
                parser = context.getResources().getXml(resId);
            }
            if (!ListenerUtil.mutListener.listen(31787)) {
                loadFromResource(context, parser, target);
            }
        } catch (final XmlPullParserException e) {
            throw new RuntimeException("Error parsing headers", e);
        } catch (final IOException e) {
            throw new RuntimeException("Error parsing headers", e);
        } finally {
            if (!ListenerUtil.mutListener.listen(31785)) {
                if (parser != null) {
                    if (!ListenerUtil.mutListener.listen(31784)) {
                        parser.close();
                    }
                }
            }
        }
    }

    private static void loadFromResource(@NonNull final Context context, @NonNull final XmlResourceParser parser, @NonNull final List<Header> target) throws IOException, XmlPullParserException {
        final AttributeSet attrs = Xml.asAttributeSet(parser);
        if (!ListenerUtil.mutListener.listen(31790)) {
            {
                long _loopCounter217 = 0;
                while (true) {
                    ListenerUtil.loopListener.listen("_loopCounter217", ++_loopCounter217);
                    final int type = parser.next();
                    if (!ListenerUtil.mutListener.listen(31789)) {
                        if ((ListenerUtil.mutListener.listen(31788) ? (type == XmlPullParser.END_DOCUMENT && type == XmlPullParser.START_TAG) : (type == XmlPullParser.END_DOCUMENT || type == XmlPullParser.START_TAG))) {
                            break;
                        }
                    }
                }
            }
        }
        String nodeName = parser.getName();
        if (!ListenerUtil.mutListener.listen(31791)) {
            if (!"preference-headers".equals(nodeName)) {
                throw new RuntimeException("XML document must start with <preference-headers> tag; found" + nodeName + " at " + parser.getPositionDescription());
            }
        }
        final int startDepth = parser.getDepth();
        if (!ListenerUtil.mutListener.listen(31799)) {
            {
                long _loopCounter218 = 0;
                while (true) {
                    ListenerUtil.loopListener.listen("_loopCounter218", ++_loopCounter218);
                    final int type = parser.next();
                    if (!ListenerUtil.mutListener.listen(31792)) {
                        if (reachToEnd(type, parser.getDepth(), startDepth)) {
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(31794)) {
                        if ((ListenerUtil.mutListener.listen(31793) ? (type == XmlPullParser.END_TAG && type == XmlPullParser.TEXT) : (type == XmlPullParser.END_TAG || type == XmlPullParser.TEXT))) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(31795)) {
                        nodeName = parser.getName();
                    }
                    if (!ListenerUtil.mutListener.listen(31798)) {
                        if ("header".equals(nodeName)) {
                            if (!ListenerUtil.mutListener.listen(31797)) {
                                target.add(parseHeaderSection(context, parser, attrs));
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(31796)) {
                                skipCurrentTag(parser);
                            }
                        }
                    }
                }
            }
        }
    }

    private static Header parseHeaderSection(@NonNull final Context context, @NonNull final XmlResourceParser parser, @NonNull final AttributeSet attrs) throws IOException, XmlPullParserException {
        final Header header = new Header();
        final TypedArray sa = context.obtainStyledAttributes(attrs, R.styleable.PreferenceHeader);
        if (!ListenerUtil.mutListener.listen(31800)) {
            header.id = PreferenceActivityCompatDelegate.HEADER_ID_UNDEFINED;
        }
        if (!ListenerUtil.mutListener.listen(31801)) {
            setTitle(header, sa.peekValue(R.styleable.PreferenceHeader_title));
        }
        if (!ListenerUtil.mutListener.listen(31802)) {
            setSummary(header, sa.peekValue(R.styleable.PreferenceHeader_summary));
        }
        if (!ListenerUtil.mutListener.listen(31803)) {
            setBreadCrumbTitle(header, sa.peekValue(R.styleable.PreferenceHeader_breadCrumbTitle));
        }
        if (!ListenerUtil.mutListener.listen(31804)) {
            header.iconRes = sa.getResourceId(R.styleable.PreferenceHeader_icon, 0);
        }
        if (!ListenerUtil.mutListener.listen(31805)) {
            header.fragment = sa.getString(R.styleable.PreferenceHeader_fragment);
        }
        if (!ListenerUtil.mutListener.listen(31806)) {
            sa.recycle();
        }
        if (!ListenerUtil.mutListener.listen(31807)) {
            parseIntentSection(context, parser, attrs, header);
        }
        return header;
    }

    private static void setTitle(@NonNull final Header header, @Nullable final TypedValue tv) {
        if (!ListenerUtil.mutListener.listen(31809)) {
            if ((ListenerUtil.mutListener.listen(31808) ? (tv == null && tv.type != TypedValue.TYPE_STRING) : (tv == null || tv.type != TypedValue.TYPE_STRING))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(31812)) {
            if (tv.resourceId != 0) {
                if (!ListenerUtil.mutListener.listen(31811)) {
                    header.titleRes = tv.resourceId;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(31810)) {
                    header.title = tv.string;
                }
            }
        }
    }

    private static void setSummary(@NonNull final Header header, @Nullable final TypedValue tv) {
        if (!ListenerUtil.mutListener.listen(31814)) {
            if ((ListenerUtil.mutListener.listen(31813) ? (tv == null && tv.type != TypedValue.TYPE_STRING) : (tv == null || tv.type != TypedValue.TYPE_STRING))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(31817)) {
            if (tv.resourceId != 0) {
                if (!ListenerUtil.mutListener.listen(31816)) {
                    header.summaryRes = tv.resourceId;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(31815)) {
                    header.summary = tv.string;
                }
            }
        }
    }

    private static void setBreadCrumbTitle(@NonNull final Header header, @Nullable final TypedValue tv) {
        if (!ListenerUtil.mutListener.listen(31819)) {
            if ((ListenerUtil.mutListener.listen(31818) ? (tv == null && tv.type != TypedValue.TYPE_STRING) : (tv == null || tv.type != TypedValue.TYPE_STRING))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(31822)) {
            if (tv.resourceId != 0) {
                if (!ListenerUtil.mutListener.listen(31821)) {
                    header.breadCrumbTitleRes = tv.resourceId;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(31820)) {
                    header.breadCrumbTitle = tv.string;
                }
            }
        }
    }

    private static void parseIntentSection(@NonNull final Context context, @NonNull final XmlResourceParser parser, @NonNull final AttributeSet attrs, @NonNull final Header header) throws IOException, XmlPullParserException {
        final Bundle curBundle = new Bundle();
        final int startDepth = parser.getDepth();
        if (!ListenerUtil.mutListener.listen(31831)) {
            {
                long _loopCounter219 = 0;
                while (true) {
                    ListenerUtil.loopListener.listen("_loopCounter219", ++_loopCounter219);
                    final int type = parser.next();
                    if (!ListenerUtil.mutListener.listen(31823)) {
                        if (reachToEnd(type, parser.getDepth(), startDepth)) {
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(31825)) {
                        if ((ListenerUtil.mutListener.listen(31824) ? (type == XmlPullParser.END_TAG && type == XmlPullParser.TEXT) : (type == XmlPullParser.END_TAG || type == XmlPullParser.TEXT))) {
                            continue;
                        }
                    }
                    final String innerNodeName = parser.getName();
                    if (!ListenerUtil.mutListener.listen(31830)) {
                        switch(innerNodeName) {
                            case "extra":
                                if (!ListenerUtil.mutListener.listen(31826)) {
                                    context.getResources().parseBundleExtra("extra", attrs, curBundle);
                                }
                                if (!ListenerUtil.mutListener.listen(31827)) {
                                    skipCurrentTag(parser);
                                }
                                break;
                            case "intent":
                                if (!ListenerUtil.mutListener.listen(31828)) {
                                    header.intent = Intent.parseIntent(context.getResources(), parser, attrs);
                                }
                                break;
                            default:
                                if (!ListenerUtil.mutListener.listen(31829)) {
                                    skipCurrentTag(parser);
                                }
                                break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(31838)) {
            if ((ListenerUtil.mutListener.listen(31836) ? (curBundle.size() >= 0) : (ListenerUtil.mutListener.listen(31835) ? (curBundle.size() <= 0) : (ListenerUtil.mutListener.listen(31834) ? (curBundle.size() < 0) : (ListenerUtil.mutListener.listen(31833) ? (curBundle.size() != 0) : (ListenerUtil.mutListener.listen(31832) ? (curBundle.size() == 0) : (curBundle.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(31837)) {
                    header.fragmentArguments = curBundle;
                }
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    private static void skipCurrentTag(final XmlPullParser parser) throws IOException, XmlPullParserException {
        final int startDepth = parser.getDepth();
        if (!ListenerUtil.mutListener.listen(31839)) {
            {
                long _loopCounter220 = 0;
                while (!reachToEnd(parser.next(), parser.getDepth(), startDepth)) {
                    ListenerUtil.loopListener.listen("_loopCounter220", ++_loopCounter220);
                    ;
                }
            }
        }
    }

    @SuppressWarnings("RedundantIfStatement")
    private static boolean reachToEnd(final int type, final int currentDepth, final int startDepth) {
        if (!ListenerUtil.mutListener.listen(31840)) {
            if (type == XmlPullParser.END_DOCUMENT) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(31847)) {
            if ((ListenerUtil.mutListener.listen(31846) ? (type == XmlPullParser.END_TAG || (ListenerUtil.mutListener.listen(31845) ? (currentDepth >= startDepth) : (ListenerUtil.mutListener.listen(31844) ? (currentDepth > startDepth) : (ListenerUtil.mutListener.listen(31843) ? (currentDepth < startDepth) : (ListenerUtil.mutListener.listen(31842) ? (currentDepth != startDepth) : (ListenerUtil.mutListener.listen(31841) ? (currentDepth == startDepth) : (currentDepth <= startDepth))))))) : (type == XmlPullParser.END_TAG && (ListenerUtil.mutListener.listen(31845) ? (currentDepth >= startDepth) : (ListenerUtil.mutListener.listen(31844) ? (currentDepth > startDepth) : (ListenerUtil.mutListener.listen(31843) ? (currentDepth < startDepth) : (ListenerUtil.mutListener.listen(31842) ? (currentDepth != startDepth) : (ListenerUtil.mutListener.listen(31841) ? (currentDepth == startDepth) : (currentDepth <= startDepth))))))))) {
                return true;
            }
        }
        return false;
    }
}

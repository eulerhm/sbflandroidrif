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
package ch.threema.app.services;

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public final class BrowserDetectionServiceImpl implements BrowserDetectionService {

    @Override
    public Browser detectBrowser(String userAgent) {
        if (!ListenerUtil.mutListener.listen(36792)) {
            if ((ListenerUtil.mutListener.listen(36779) ? (userAgent != null || (ListenerUtil.mutListener.listen(36778) ? (userAgent.length() >= 0) : (ListenerUtil.mutListener.listen(36777) ? (userAgent.length() <= 0) : (ListenerUtil.mutListener.listen(36776) ? (userAgent.length() < 0) : (ListenerUtil.mutListener.listen(36775) ? (userAgent.length() != 0) : (ListenerUtil.mutListener.listen(36774) ? (userAgent.length() == 0) : (userAgent.length() > 0))))))) : (userAgent != null && (ListenerUtil.mutListener.listen(36778) ? (userAgent.length() >= 0) : (ListenerUtil.mutListener.listen(36777) ? (userAgent.length() <= 0) : (ListenerUtil.mutListener.listen(36776) ? (userAgent.length() < 0) : (ListenerUtil.mutListener.listen(36775) ? (userAgent.length() != 0) : (ListenerUtil.mutListener.listen(36774) ? (userAgent.length() == 0) : (userAgent.length() > 0))))))))) {
                final String desc = userAgent.toLowerCase().trim();
                if (!ListenerUtil.mutListener.listen(36791)) {
                    if ((ListenerUtil.mutListener.listen(36783) ? ((ListenerUtil.mutListener.listen(36782) ? ((ListenerUtil.mutListener.listen(36781) ? ((ListenerUtil.mutListener.listen(36780) ? (desc.contains("mozilla") || desc.contains("applewebkit")) : (desc.contains("mozilla") && desc.contains("applewebkit"))) || desc.contains("chrome")) : ((ListenerUtil.mutListener.listen(36780) ? (desc.contains("mozilla") || desc.contains("applewebkit")) : (desc.contains("mozilla") && desc.contains("applewebkit"))) && desc.contains("chrome"))) || desc.contains("safari")) : ((ListenerUtil.mutListener.listen(36781) ? ((ListenerUtil.mutListener.listen(36780) ? (desc.contains("mozilla") || desc.contains("applewebkit")) : (desc.contains("mozilla") && desc.contains("applewebkit"))) || desc.contains("chrome")) : ((ListenerUtil.mutListener.listen(36780) ? (desc.contains("mozilla") || desc.contains("applewebkit")) : (desc.contains("mozilla") && desc.contains("applewebkit"))) && desc.contains("chrome"))) && desc.contains("safari"))) || desc.contains("opr")) : ((ListenerUtil.mutListener.listen(36782) ? ((ListenerUtil.mutListener.listen(36781) ? ((ListenerUtil.mutListener.listen(36780) ? (desc.contains("mozilla") || desc.contains("applewebkit")) : (desc.contains("mozilla") && desc.contains("applewebkit"))) || desc.contains("chrome")) : ((ListenerUtil.mutListener.listen(36780) ? (desc.contains("mozilla") || desc.contains("applewebkit")) : (desc.contains("mozilla") && desc.contains("applewebkit"))) && desc.contains("chrome"))) || desc.contains("safari")) : ((ListenerUtil.mutListener.listen(36781) ? ((ListenerUtil.mutListener.listen(36780) ? (desc.contains("mozilla") || desc.contains("applewebkit")) : (desc.contains("mozilla") && desc.contains("applewebkit"))) || desc.contains("chrome")) : ((ListenerUtil.mutListener.listen(36780) ? (desc.contains("mozilla") || desc.contains("applewebkit")) : (desc.contains("mozilla") && desc.contains("applewebkit"))) && desc.contains("chrome"))) && desc.contains("safari"))) && desc.contains("opr")))) {
                        return Browser.OPERA;
                    } else if ((ListenerUtil.mutListener.listen(36786) ? ((ListenerUtil.mutListener.listen(36785) ? ((ListenerUtil.mutListener.listen(36784) ? (desc.contains("chrome") || desc.contains("webkit")) : (desc.contains("chrome") && desc.contains("webkit"))) || !desc.contains("edge")) : ((ListenerUtil.mutListener.listen(36784) ? (desc.contains("chrome") || desc.contains("webkit")) : (desc.contains("chrome") && desc.contains("webkit"))) && !desc.contains("edge"))) || !desc.contains("edg")) : ((ListenerUtil.mutListener.listen(36785) ? ((ListenerUtil.mutListener.listen(36784) ? (desc.contains("chrome") || desc.contains("webkit")) : (desc.contains("chrome") && desc.contains("webkit"))) || !desc.contains("edge")) : ((ListenerUtil.mutListener.listen(36784) ? (desc.contains("chrome") || desc.contains("webkit")) : (desc.contains("chrome") && desc.contains("webkit"))) && !desc.contains("edge"))) && !desc.contains("edg")))) {
                        return Browser.CHROME;
                    } else if ((ListenerUtil.mutListener.listen(36787) ? (desc.contains("mozilla") || desc.contains("firefox")) : (desc.contains("mozilla") && desc.contains("firefox")))) {
                        return Browser.FIREFOX;
                    } else if ((ListenerUtil.mutListener.listen(36789) ? ((ListenerUtil.mutListener.listen(36788) ? (desc.contains("safari") || desc.contains("applewebkit")) : (desc.contains("safari") && desc.contains("applewebkit"))) || !desc.contains("chrome")) : ((ListenerUtil.mutListener.listen(36788) ? (desc.contains("safari") || desc.contains("applewebkit")) : (desc.contains("safari") && desc.contains("applewebkit"))) && !desc.contains("chrome")))) {
                        return Browser.SAFARI;
                    } else if ((ListenerUtil.mutListener.listen(36790) ? (desc.contains("edge") && desc.contains("edg")) : (desc.contains("edge") || desc.contains("edg")))) {
                        return Browser.EDGE;
                    }
                }
            }
        }
        return Browser.UNKNOWN;
    }
}

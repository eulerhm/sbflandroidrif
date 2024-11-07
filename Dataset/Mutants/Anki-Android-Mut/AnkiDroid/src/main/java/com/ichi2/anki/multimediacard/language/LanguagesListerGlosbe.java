/**
 * *************************************************************************************
 *  Copyright (c) 2013 Bibek Shrestha <bibekshrestha@gmail.com>                          *
 *  Copyright (c) 2013 Zaur Molotnikov <qutorial@gmail.com>                              *
 *  Copyright (c) 2013 Nicolas Raoul <nicolas.raoul@gmail.com>                           *
 *  Copyright (c) 2013 Flavio Lerda <flerda@gmail.com>                                   *
 *  Copyright (c) 2014 Timothy Rae <perceptualchaos2@gmail.com>                          *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.anki.multimediacard.language;

import java.util.HashMap;
import java.util.Locale;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This language lister is used to call glosbe.com translation services.
 * <p>
 * Glosbe expects the languages to follow the ISO 639-3 codes.
 * <p>
 * It can be extended freely here, to support more languages.
 */
public class LanguagesListerGlosbe extends LanguageListerBase {

    private static HashMap<String, Locale> locale_map = null;

    public LanguagesListerGlosbe() {
        final String[] languages = { "eng", "deu", "jpn", "fra", "spa", "pol", "ita", "rus", "ces", "zho", "nld", "por", "swe", "hrv", "hin", "hun", "vie", "ara", "tur" };
        if (!ListenerUtil.mutListener.listen(2056)) {
            {
                long _loopCounter27 = 0;
                // is not supported, but "Chinese" ("zho") is.
                for (String l : languages) {
                    ListenerUtil.loopListener.listen("_loopCounter27", ++_loopCounter27);
                    Locale locale = new Locale(l);
                    if (!ListenerUtil.mutListener.listen(2055)) {
                        addLanguage(locale.getDisplayLanguage(), locale.getISO3Language());
                    }
                }
            }
        }
    }

    /**
     * Convert from 3 letter ISO 639-2 language code to ISO 639-1
     * @param req 3 letter language code
     * @return 2 letter language code
     */
    public static String requestToResponseLangCode(String req) {
        if (!ListenerUtil.mutListener.listen(2060)) {
            if (locale_map == null) {
                String[] languages = Locale.getISOLanguages();
                if (!ListenerUtil.mutListener.listen(2057)) {
                    locale_map = new HashMap<>(languages.length);
                }
                if (!ListenerUtil.mutListener.listen(2059)) {
                    {
                        long _loopCounter28 = 0;
                        for (String language : languages) {
                            ListenerUtil.loopListener.listen("_loopCounter28", ++_loopCounter28);
                            Locale locale = new Locale(language);
                            if (!ListenerUtil.mutListener.listen(2058)) {
                                locale_map.put(locale.getISO3Language(), locale);
                            }
                        }
                    }
                }
            }
        }
        return locale_map.get(req).getLanguage();
    }
}

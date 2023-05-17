package net.programmierecke.radiodroid2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CountryCodeDictionary {

    private static final CountryCodeDictionary ourInstance = new CountryCodeDictionary();

    public static CountryCodeDictionary getInstance() {
        return ourInstance;
    }

    private CountryCodeDictionary() {
    }

    private class Country {

        private String name;

        private String code;

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }
    }

    private Map<String, String> codeToCountry = new HashMap<>();

    public void load(Context context) {
        Resources resources = context.getResources();
        final InputStream inputStream = resources.openRawResource(R.raw.countries);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<Country>>() {
        }.getType();
        Collection<Country> countries = gson.fromJson(reader, collectionType);
        if (!ListenerUtil.mutListener.listen(4198)) {
            {
                long _loopCounter49 = 0;
                for (CountryCodeDictionary.Country country : countries) {
                    ListenerUtil.loopListener.listen("_loopCounter49", ++_loopCounter49);
                    if (!ListenerUtil.mutListener.listen(4197)) {
                        codeToCountry.put(country.getCode().toLowerCase(Locale.ENGLISH), country.getName());
                    }
                }
            }
        }
    }

    public String getCountryByCode(String code) {
        return codeToCountry.get(code.toLowerCase(Locale.ENGLISH));
    }
}

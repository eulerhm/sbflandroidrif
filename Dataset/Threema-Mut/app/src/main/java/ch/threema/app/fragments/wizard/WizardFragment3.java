/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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
package ch.threema.app.fragments.wizard;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.google.i18n.phonenumbers.AsYouTypeFormatter;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import ch.threema.app.R;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.EditTextUtil;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WizardFragment3 extends WizardFragment {

    private static final Logger logger = LoggerFactory.getLogger(WizardFragment3.class);

    private EditText prefixText, emailEditText, phoneText;

    private CountryListAdapter countryListAdapter;

    private Spinner countrySpinner;

    private AsYouTypeFormatter phoneNumberFormatter;

    private AsyncTask<Void, Void, ArrayList<Map<String, String>>> countryListTask;

    public static final int PAGE_ID = 3;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        if (!ListenerUtil.mutListener.listen(24054)) {
            // inflate content layout
            contentViewStub.setLayoutResource(R.layout.fragment_wizard3);
        }
        if (!ListenerUtil.mutListener.listen(24055)) {
            contentViewStub.inflate();
        }
        WizardFragment5.SettingsInterface callback = (WizardFragment5.SettingsInterface) getActivity();
        if (!ListenerUtil.mutListener.listen(24056)) {
            countrySpinner = rootView.findViewById(R.id.country_spinner);
        }
        if (!ListenerUtil.mutListener.listen(24057)) {
            emailEditText = rootView.findViewById(R.id.wizard_email);
        }
        if (!ListenerUtil.mutListener.listen(24058)) {
            prefixText = rootView.findViewById(R.id.wizard_prefix);
        }
        if (!ListenerUtil.mutListener.listen(24059)) {
            prefixText.setText("+");
        }
        if (!ListenerUtil.mutListener.listen(24060)) {
            phoneText = rootView.findViewById(R.id.wizard_phone);
        }
        if (!ListenerUtil.mutListener.listen(24063)) {
            if (!ConfigUtils.isWorkBuild()) {
                if (!ListenerUtil.mutListener.listen(24061)) {
                    rootView.findViewById(R.id.wizard_email_layout).setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(24062)) {
                    ((TextView) rootView.findViewById(R.id.scooter)).setText(getString(R.string.new_wizard_link_mobile_only));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24108)) {
            if (callback.isReadOnlyProfile()) {
                if (!ListenerUtil.mutListener.listen(24103)) {
                    emailEditText.setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(24104)) {
                    prefixText.setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(24105)) {
                    phoneText.setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(24106)) {
                    countrySpinner.setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(24107)) {
                    rootView.findViewById(R.id.disabled_by_policy).setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(24066)) {
                    emailEditText.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if (!ListenerUtil.mutListener.listen(24065)) {
                                if (getActivity() != null) {
                                    if (!ListenerUtil.mutListener.listen(24064)) {
                                        ((OnSettingsChangedListener) getActivity()).onEmailSet(s.toString());
                                    }
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(24086)) {
                    prefixText.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            String prefixString = s.toString();
                            if (!ListenerUtil.mutListener.listen(24085)) {
                                if (!prefixString.startsWith("+")) {
                                    if (!ListenerUtil.mutListener.listen(24083)) {
                                        prefixText.setText("+");
                                    }
                                    if (!ListenerUtil.mutListener.listen(24084)) {
                                        Selection.setSelection(prefixText.getText(), prefixText.getText().length());
                                    }
                                } else if ((ListenerUtil.mutListener.listen(24072) ? ((ListenerUtil.mutListener.listen(24071) ? (prefixString.length() >= 1) : (ListenerUtil.mutListener.listen(24070) ? (prefixString.length() <= 1) : (ListenerUtil.mutListener.listen(24069) ? (prefixString.length() < 1) : (ListenerUtil.mutListener.listen(24068) ? (prefixString.length() != 1) : (ListenerUtil.mutListener.listen(24067) ? (prefixString.length() == 1) : (prefixString.length() > 1)))))) || countryListAdapter != null) : ((ListenerUtil.mutListener.listen(24071) ? (prefixString.length() >= 1) : (ListenerUtil.mutListener.listen(24070) ? (prefixString.length() <= 1) : (ListenerUtil.mutListener.listen(24069) ? (prefixString.length() < 1) : (ListenerUtil.mutListener.listen(24068) ? (prefixString.length() != 1) : (ListenerUtil.mutListener.listen(24067) ? (prefixString.length() == 1) : (prefixString.length() > 1)))))) && countryListAdapter != null))) {
                                    try {
                                        int countryCode = Integer.parseInt(prefixString.substring(1));
                                        String region = PhoneNumberUtil.getInstance().getRegionCodeForCountryCode(countryCode);
                                        int position = countryListAdapter.getPosition(region);
                                        if (!ListenerUtil.mutListener.listen(24082)) {
                                            if ((ListenerUtil.mutListener.listen(24078) ? (position >= -1) : (ListenerUtil.mutListener.listen(24077) ? (position <= -1) : (ListenerUtil.mutListener.listen(24076) ? (position < -1) : (ListenerUtil.mutListener.listen(24075) ? (position != -1) : (ListenerUtil.mutListener.listen(24074) ? (position == -1) : (position > -1))))))) {
                                                if (!ListenerUtil.mutListener.listen(24079)) {
                                                    countrySpinner.setSelection(position);
                                                }
                                                if (!ListenerUtil.mutListener.listen(24080)) {
                                                    setPhoneNumberFormatter(countryCode);
                                                }
                                                if (!ListenerUtil.mutListener.listen(24081)) {
                                                    ((OnSettingsChangedListener) getActivity()).onPrefixSet(prefixText.getText().toString());
                                                }
                                            }
                                        }
                                    } catch (NumberFormatException e) {
                                        if (!ListenerUtil.mutListener.listen(24073)) {
                                            logger.error("Exception", e);
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(24102)) {
                    phoneText.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if (!ListenerUtil.mutListener.listen(24099)) {
                                if ((ListenerUtil.mutListener.listen(24087) ? (!TextUtils.isEmpty(s) || phoneNumberFormatter != null) : (!TextUtils.isEmpty(s) && phoneNumberFormatter != null))) {
                                    if (!ListenerUtil.mutListener.listen(24088)) {
                                        phoneNumberFormatter.clear();
                                    }
                                    String number = s.toString().replaceAll("[^\\d.]", "");
                                    String formattedNumber = null;
                                    if (!ListenerUtil.mutListener.listen(24095)) {
                                        {
                                            long _loopCounter159 = 0;
                                            for (int i = 0; (ListenerUtil.mutListener.listen(24094) ? (i >= number.length()) : (ListenerUtil.mutListener.listen(24093) ? (i <= number.length()) : (ListenerUtil.mutListener.listen(24092) ? (i > number.length()) : (ListenerUtil.mutListener.listen(24091) ? (i != number.length()) : (ListenerUtil.mutListener.listen(24090) ? (i == number.length()) : (i < number.length())))))); i++) {
                                                ListenerUtil.loopListener.listen("_loopCounter159", ++_loopCounter159);
                                                if (!ListenerUtil.mutListener.listen(24089)) {
                                                    formattedNumber = phoneNumberFormatter.inputDigit(number.charAt(i));
                                                }
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(24098)) {
                                        if ((ListenerUtil.mutListener.listen(24096) ? (formattedNumber != null || !s.toString().equals(formattedNumber)) : (formattedNumber != null && !s.toString().equals(formattedNumber)))) {
                                            if (!ListenerUtil.mutListener.listen(24097)) {
                                                s.replace(0, s.length(), formattedNumber);
                                            }
                                        }
                                    }
                                }
                            }
                            Activity activity = getActivity();
                            if (!ListenerUtil.mutListener.listen(24101)) {
                                if (activity != null) {
                                    if (!ListenerUtil.mutListener.listen(24100)) {
                                        ((OnSettingsChangedListener) activity).onPhoneSet(s.toString());
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
        TextView presetEmailText = rootView.findViewById(R.id.preset_email_text);
        TextView presetPhoneText = rootView.findViewById(R.id.preset_phone_text);
        if (!ListenerUtil.mutListener.listen(24112)) {
            if (!TestUtil.empty(callback.getPresetEmail())) {
                if (!ListenerUtil.mutListener.listen(24109)) {
                    emailEditText.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(24110)) {
                    presetEmailText.setText(R.string.linked);
                }
                if (!ListenerUtil.mutListener.listen(24111)) {
                    presetEmailText.setVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24163)) {
            if (!TestUtil.empty(callback.getPresetPhone())) {
                if (!ListenerUtil.mutListener.listen(24158)) {
                    phoneText.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(24159)) {
                    prefixText.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(24160)) {
                    countrySpinner.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(24161)) {
                    presetPhoneText.setText(R.string.linked);
                }
                if (!ListenerUtil.mutListener.listen(24162)) {
                    presetPhoneText.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(24156)) {
                    // load country list
                    countryListTask = new AsyncTask<Void, Void, ArrayList<Map<String, String>>>() {

                        final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

                        @Override
                        protected ArrayList<Map<String, String>> doInBackground(Void... params) {
                            Set<String> regions = phoneNumberUtil.getSupportedRegions();
                            ArrayList<Map<String, String>> results = new ArrayList<Map<String, String>>(regions.size());
                            if (!ListenerUtil.mutListener.listen(24116)) {
                                {
                                    long _loopCounter160 = 0;
                                    for (String region : regions) {
                                        ListenerUtil.loopListener.listen("_loopCounter160", ++_loopCounter160);
                                        Map<String, String> data = new HashMap<String, String>(2);
                                        if (!ListenerUtil.mutListener.listen(24113)) {
                                            data.put("name", getCountryName(region));
                                        }
                                        if (!ListenerUtil.mutListener.listen(24114)) {
                                            data.put("prefix", "+" + PhoneNumberUtil.getInstance().getCountryCodeForRegion(region));
                                        }
                                        if (!ListenerUtil.mutListener.listen(24115)) {
                                            results.add(data);
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(24117)) {
                                Collections.sort(results, new CountryNameComparator());
                            }
                            Map<String, String> data = new HashMap<String, String>(2);
                            if (!ListenerUtil.mutListener.listen(24118)) {
                                data.put("name", getString(R.string.new_wizard_select_country));
                            }
                            if (!ListenerUtil.mutListener.listen(24119)) {
                                data.put("prefix", "");
                            }
                            if (!ListenerUtil.mutListener.listen(24120)) {
                                results.add(data);
                            }
                            return results;
                        }

                        @Override
                        protected void onPostExecute(final ArrayList<Map<String, String>> result) {
                            if (!ListenerUtil.mutListener.listen(24121)) {
                                countryListAdapter = new CountryListAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, result);
                            }
                            if (!ListenerUtil.mutListener.listen(24122)) {
                                countrySpinner.setAdapter(countryListAdapter);
                            }
                            if (!ListenerUtil.mutListener.listen(24123)) {
                                countrySpinner.setSelection(countryListAdapter.getCount());
                            }
                            if (!ListenerUtil.mutListener.listen(24145)) {
                                countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if (!ListenerUtil.mutListener.listen(24143)) {
                                            if ((ListenerUtil.mutListener.listen(24132) ? (position >= (ListenerUtil.mutListener.listen(24127) ? (result.size() % 1) : (ListenerUtil.mutListener.listen(24126) ? (result.size() / 1) : (ListenerUtil.mutListener.listen(24125) ? (result.size() * 1) : (ListenerUtil.mutListener.listen(24124) ? (result.size() + 1) : (result.size() - 1)))))) : (ListenerUtil.mutListener.listen(24131) ? (position <= (ListenerUtil.mutListener.listen(24127) ? (result.size() % 1) : (ListenerUtil.mutListener.listen(24126) ? (result.size() / 1) : (ListenerUtil.mutListener.listen(24125) ? (result.size() * 1) : (ListenerUtil.mutListener.listen(24124) ? (result.size() + 1) : (result.size() - 1)))))) : (ListenerUtil.mutListener.listen(24130) ? (position > (ListenerUtil.mutListener.listen(24127) ? (result.size() % 1) : (ListenerUtil.mutListener.listen(24126) ? (result.size() / 1) : (ListenerUtil.mutListener.listen(24125) ? (result.size() * 1) : (ListenerUtil.mutListener.listen(24124) ? (result.size() + 1) : (result.size() - 1)))))) : (ListenerUtil.mutListener.listen(24129) ? (position != (ListenerUtil.mutListener.listen(24127) ? (result.size() % 1) : (ListenerUtil.mutListener.listen(24126) ? (result.size() / 1) : (ListenerUtil.mutListener.listen(24125) ? (result.size() * 1) : (ListenerUtil.mutListener.listen(24124) ? (result.size() + 1) : (result.size() - 1)))))) : (ListenerUtil.mutListener.listen(24128) ? (position == (ListenerUtil.mutListener.listen(24127) ? (result.size() % 1) : (ListenerUtil.mutListener.listen(24126) ? (result.size() / 1) : (ListenerUtil.mutListener.listen(24125) ? (result.size() * 1) : (ListenerUtil.mutListener.listen(24124) ? (result.size() + 1) : (result.size() - 1)))))) : (position < (ListenerUtil.mutListener.listen(24127) ? (result.size() % 1) : (ListenerUtil.mutListener.listen(24126) ? (result.size() / 1) : (ListenerUtil.mutListener.listen(24125) ? (result.size() * 1) : (ListenerUtil.mutListener.listen(24124) ? (result.size() + 1) : (result.size() - 1)))))))))))) {
                                                String prefixString = result.get(position).get("prefix");
                                                if (!ListenerUtil.mutListener.listen(24133)) {
                                                    prefixText.setText(prefixString);
                                                }
                                                if (!ListenerUtil.mutListener.listen(24141)) {
                                                    if ((ListenerUtil.mutListener.listen(24139) ? (!TestUtil.empty(prefixString) || (ListenerUtil.mutListener.listen(24138) ? (prefixString.length() >= 1) : (ListenerUtil.mutListener.listen(24137) ? (prefixString.length() <= 1) : (ListenerUtil.mutListener.listen(24136) ? (prefixString.length() < 1) : (ListenerUtil.mutListener.listen(24135) ? (prefixString.length() != 1) : (ListenerUtil.mutListener.listen(24134) ? (prefixString.length() == 1) : (prefixString.length() > 1))))))) : (!TestUtil.empty(prefixString) && (ListenerUtil.mutListener.listen(24138) ? (prefixString.length() >= 1) : (ListenerUtil.mutListener.listen(24137) ? (prefixString.length() <= 1) : (ListenerUtil.mutListener.listen(24136) ? (prefixString.length() < 1) : (ListenerUtil.mutListener.listen(24135) ? (prefixString.length() != 1) : (ListenerUtil.mutListener.listen(24134) ? (prefixString.length() == 1) : (prefixString.length() > 1))))))))) {
                                                        if (!ListenerUtil.mutListener.listen(24140)) {
                                                            setPhoneNumberFormatter(Integer.parseInt(prefixString.substring(1)));
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(24142)) {
                                                    phoneText.requestFocus();
                                                }
                                            }
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                        if (!ListenerUtil.mutListener.listen(24144)) {
                                            prefixText.setText("+");
                                        }
                                    }
                                });
                            }
                            if (!ListenerUtil.mutListener.listen(24155)) {
                                if ((ListenerUtil.mutListener.listen(24150) ? (prefixText.getText().length() >= 1) : (ListenerUtil.mutListener.listen(24149) ? (prefixText.getText().length() > 1) : (ListenerUtil.mutListener.listen(24148) ? (prefixText.getText().length() < 1) : (ListenerUtil.mutListener.listen(24147) ? (prefixText.getText().length() != 1) : (ListenerUtil.mutListener.listen(24146) ? (prefixText.getText().length() == 1) : (prefixText.getText().length() <= 1))))))) {
                                    String countryCode = localeService.getCountryCodePhonePrefix();
                                    if (!ListenerUtil.mutListener.listen(24154)) {
                                        if (!TestUtil.empty(countryCode)) {
                                            if (!ListenerUtil.mutListener.listen(24151)) {
                                                prefixText.setText(countryCode);
                                            }
                                            if (!ListenerUtil.mutListener.listen(24152)) {
                                                ((OnSettingsChangedListener) getActivity()).onPrefixSet(prefixText.getText().toString());
                                            }
                                            if (!ListenerUtil.mutListener.listen(24153)) {
                                                phoneText.requestFocus();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    };
                }
                if (!ListenerUtil.mutListener.listen(24157)) {
                    countryListTask.execute();
                }
            }
        }
        return rootView;
    }

    @Override
    protected int getAdditionalInfoText() {
        return ConfigUtils.isWorkBuild() ? R.string.new_wizard_info_link : R.string.new_wizard_info_link_phone_only;
    }

    private void showEditTextError(EditText editText, boolean show) {
        if (!ListenerUtil.mutListener.listen(24164)) {
            editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, show ? R.drawable.ic_error_red_24dp : 0, 0);
        }
    }

    private String getCountryName(String region) {
        if (!TestUtil.empty(region)) {
            return new Locale("", region).getDisplayCountry(Locale.getDefault());
        } else {
            return "";
        }
    }

    void setPhoneNumberFormatter(int countryCode) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        String regionCode = phoneNumberUtil.getRegionCodeForCountryCode(countryCode);
        if (!ListenerUtil.mutListener.listen(24167)) {
            if (!TestUtil.empty(regionCode)) {
                if (!ListenerUtil.mutListener.listen(24166)) {
                    this.phoneNumberFormatter = phoneNumberUtil.getAsYouTypeFormatter(regionCode);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(24165)) {
                    this.phoneNumberFormatter = null;
                }
            }
        }
    }

    private static class CountryNameComparator implements Comparator<Map<String, String>> {

        @Override
        public int compare(Map<String, String> lhs, Map<String, String> rhs) {
            // Compare two strings in the default locale
            Collator collator = Collator.getInstance();
            return collator.compare(lhs.get("name"), rhs.get("name"));
        }
    }

    private class CountryListAdapter extends BaseAdapter implements SpinnerAdapter {

        private ArrayList<Map<String, String>> list;

        private LayoutInflater inflater;

        private int resource;

        public CountryListAdapter(Context context, int resource, ArrayList<Map<String, String>> objects) {
            if (!ListenerUtil.mutListener.listen(24168)) {
                this.inflater = getActivity().getLayoutInflater();
            }
            if (!ListenerUtil.mutListener.listen(24169)) {
                this.list = objects;
            }
            if (!ListenerUtil.mutListener.listen(24170)) {
                this.resource = resource;
            }
        }

        private class ViewHolder {

            TextView country;
        }

        @Override
        public int getCount() {
            int count = list.size();
            return (ListenerUtil.mutListener.listen(24175) ? (count >= 0) : (ListenerUtil.mutListener.listen(24174) ? (count <= 0) : (ListenerUtil.mutListener.listen(24173) ? (count < 0) : (ListenerUtil.mutListener.listen(24172) ? (count != 0) : (ListenerUtil.mutListener.listen(24171) ? (count == 0) : (count > 0)))))) ? (ListenerUtil.mutListener.listen(24179) ? (count % 1) : (ListenerUtil.mutListener.listen(24178) ? (count / 1) : (ListenerUtil.mutListener.listen(24177) ? (count * 1) : (ListenerUtil.mutListener.listen(24176) ? (count + 1) : (count - 1))))) : count;
        }

        @Override
        public Map<String, String> getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                if (!ListenerUtil.mutListener.listen(24180)) {
                    convertView = inflater.inflate(this.resource, parent, false);
                }
                viewHolder = new ViewHolder();
                if (!ListenerUtil.mutListener.listen(24181)) {
                    viewHolder.country = convertView.findViewById(android.R.id.text1);
                }
                if (!ListenerUtil.mutListener.listen(24182)) {
                    convertView.setTag(viewHolder);
                }
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Map<String, String> map = list.get(position);
            if (!ListenerUtil.mutListener.listen(24183)) {
                viewHolder.country.setText(map.get("name"));
            }
            return convertView;
        }

        public int getPosition(String region) {
            String countryName = getCountryName(region);
            if (!ListenerUtil.mutListener.listen(24190)) {
                {
                    long _loopCounter161 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(24189) ? (i >= list.size()) : (ListenerUtil.mutListener.listen(24188) ? (i <= list.size()) : (ListenerUtil.mutListener.listen(24187) ? (i > list.size()) : (ListenerUtil.mutListener.listen(24186) ? (i != list.size()) : (ListenerUtil.mutListener.listen(24185) ? (i == list.size()) : (i < list.size())))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter161", ++_loopCounter161);
                        Map<String, String> map = list.get(i);
                        if (!ListenerUtil.mutListener.listen(24184)) {
                            if (map.get("name").equalsIgnoreCase(countryName)) {
                                return i;
                            }
                        }
                    }
                }
            }
            return -1;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(24191)) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onDetach() {
        if (!ListenerUtil.mutListener.listen(24192)) {
            super.onDetach();
        }
        if (!ListenerUtil.mutListener.listen(24194)) {
            // make sure asynctask is cancelled before detaching fragment
            if (countryListTask != null) {
                if (!ListenerUtil.mutListener.listen(24193)) {
                    countryListTask.cancel(true);
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(24195)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(24196)) {
            initValues();
        }
        if (!ListenerUtil.mutListener.listen(24199)) {
            if (this.phoneText != null) {
                if (!ListenerUtil.mutListener.listen(24197)) {
                    this.phoneText.requestFocus();
                }
                if (!ListenerUtil.mutListener.listen(24198)) {
                    EditTextUtil.showSoftKeyboard(this.phoneText);
                }
            }
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(24202)) {
            if (this.phoneText != null) {
                if (!ListenerUtil.mutListener.listen(24200)) {
                    this.phoneText.clearFocus();
                }
                if (!ListenerUtil.mutListener.listen(24201)) {
                    EditTextUtil.hideSoftKeyboard(this.phoneText);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24203)) {
            super.onPause();
        }
    }

    void initValues() {
        if (!ListenerUtil.mutListener.listen(24213)) {
            if (isResumed()) {
                WizardFragment5.SettingsInterface callback = (WizardFragment5.SettingsInterface) getActivity();
                if (!ListenerUtil.mutListener.listen(24204)) {
                    emailEditText.setText(callback.getEmail());
                }
                if (!ListenerUtil.mutListener.listen(24207)) {
                    if (TestUtil.empty(callback.getPresetEmail())) {
                        if (!ListenerUtil.mutListener.listen(24206)) {
                            showEditTextError(emailEditText, (ListenerUtil.mutListener.listen(24205) ? (!TestUtil.empty(callback.getEmail()) || !Patterns.EMAIL_ADDRESS.matcher(callback.getEmail()).matches()) : (!TestUtil.empty(callback.getEmail()) && !Patterns.EMAIL_ADDRESS.matcher(callback.getEmail()).matches())));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24208)) {
                    prefixText.setText(callback.getPrefix());
                }
                if (!ListenerUtil.mutListener.listen(24209)) {
                    phoneText.setText(callback.getNumber());
                }
                if (!ListenerUtil.mutListener.listen(24212)) {
                    if (TestUtil.empty(callback.getPresetPhone())) {
                        if (!ListenerUtil.mutListener.listen(24211)) {
                            showEditTextError(phoneText, (ListenerUtil.mutListener.listen(24210) ? (!TestUtil.empty(callback.getNumber()) || TestUtil.empty(callback.getPhone())) : (!TestUtil.empty(callback.getNumber()) && TestUtil.empty(callback.getPhone()))));
                        }
                    }
                }
            }
        }
    }

    public interface OnSettingsChangedListener {

        void onPrefixSet(String prefix);

        void onPhoneSet(String phoneNumber);

        void onEmailSet(String email);
    }
}

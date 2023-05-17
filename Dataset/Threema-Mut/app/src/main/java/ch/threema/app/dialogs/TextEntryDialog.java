/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
package ch.threema.app.dialogs;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.emojis.EmojiEditText;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.LocaleService;
import ch.threema.app.utils.DialogUtil;
import ch.threema.client.ProtocolDefines;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TextEntryDialog extends ThreemaDialogFragment {

    private TextEntryDialogClickListener callback;

    private Activity activity;

    private AlertDialog alertDialog;

    private LocaleService localeService;

    private int inputFilterType, minLength = 0;

    public static int INPUT_FILTER_TYPE_NONE = 0;

    public static int INPUT_FILTER_TYPE_IDENTITY = 1;

    public static int INPUT_FILTER_TYPE_PHONE = 2;

    public static TextEntryDialog newInstance(@StringRes int title, @StringRes int message, @StringRes int positive, @StringRes int neutral, @StringRes int negative, String text, int inputType, int inputFilterType) {
        TextEntryDialog dialog = new TextEntryDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(14303)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(14304)) {
            args.putInt("message", message);
        }
        if (!ListenerUtil.mutListener.listen(14305)) {
            args.putInt("positive", positive);
        }
        if (!ListenerUtil.mutListener.listen(14306)) {
            args.putInt("neutral", neutral);
        }
        if (!ListenerUtil.mutListener.listen(14307)) {
            args.putInt("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(14308)) {
            args.putString("text", text);
        }
        if (!ListenerUtil.mutListener.listen(14309)) {
            args.putInt("inputType", inputType);
        }
        if (!ListenerUtil.mutListener.listen(14310)) {
            args.putInt("inputFilterType", inputFilterType);
        }
        if (!ListenerUtil.mutListener.listen(14311)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public static TextEntryDialog newInstance(@StringRes int title, @StringRes int message, @StringRes int positive, @StringRes int negative, String text, int inputType, int inputFilterType, int maxLines) {
        TextEntryDialog dialog = new TextEntryDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(14312)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(14313)) {
            args.putInt("message", message);
        }
        if (!ListenerUtil.mutListener.listen(14314)) {
            args.putInt("positive", positive);
        }
        if (!ListenerUtil.mutListener.listen(14315)) {
            args.putInt("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(14316)) {
            args.putString("text", text);
        }
        if (!ListenerUtil.mutListener.listen(14317)) {
            args.putInt("inputType", inputType);
        }
        if (!ListenerUtil.mutListener.listen(14318)) {
            args.putInt("inputFilterType", inputFilterType);
        }
        if (!ListenerUtil.mutListener.listen(14319)) {
            args.putInt("maxLines", maxLines);
        }
        if (!ListenerUtil.mutListener.listen(14320)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public static TextEntryDialog newInstance(@StringRes int title, @StringRes int message, @StringRes int positive, @StringRes int neutral, @StringRes int negative, String text, int inputType, int inputFilterType, int maxLength) {
        TextEntryDialog dialog = new TextEntryDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(14321)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(14322)) {
            args.putInt("message", message);
        }
        if (!ListenerUtil.mutListener.listen(14323)) {
            args.putInt("positive", positive);
        }
        if (!ListenerUtil.mutListener.listen(14324)) {
            args.putInt("neutral", neutral);
        }
        if (!ListenerUtil.mutListener.listen(14325)) {
            args.putInt("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(14326)) {
            args.putString("text", text);
        }
        if (!ListenerUtil.mutListener.listen(14327)) {
            args.putInt("inputType", inputType);
        }
        if (!ListenerUtil.mutListener.listen(14328)) {
            args.putInt("inputFilterType", inputFilterType);
        }
        if (!ListenerUtil.mutListener.listen(14329)) {
            args.putInt("maxLength", maxLength);
        }
        if (!ListenerUtil.mutListener.listen(14330)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public static TextEntryDialog newInstance(@StringRes int title, @StringRes int message, @StringRes int positive, @StringRes int negative, String text, int inputType, int inputFilterType) {
        TextEntryDialog dialog = new TextEntryDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(14331)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(14332)) {
            args.putInt("message", message);
        }
        if (!ListenerUtil.mutListener.listen(14333)) {
            args.putInt("positive", positive);
        }
        if (!ListenerUtil.mutListener.listen(14334)) {
            args.putInt("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(14335)) {
            args.putString("text", text);
        }
        if (!ListenerUtil.mutListener.listen(14336)) {
            args.putInt("inputType", inputType);
        }
        if (!ListenerUtil.mutListener.listen(14337)) {
            args.putInt("inputFilterType", inputFilterType);
        }
        if (!ListenerUtil.mutListener.listen(14338)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public static TextEntryDialog newInstance(@StringRes int title, @StringRes int message, @StringRes int positive, @StringRes int negative, int maxLines, int maxLength) {
        TextEntryDialog dialog = new TextEntryDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(14339)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(14340)) {
            args.putInt("message", message);
        }
        if (!ListenerUtil.mutListener.listen(14341)) {
            args.putInt("positive", positive);
        }
        if (!ListenerUtil.mutListener.listen(14342)) {
            args.putInt("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(14343)) {
            args.putInt("maxLines", maxLines);
        }
        if (!ListenerUtil.mutListener.listen(14344)) {
            args.putInt("maxLength", maxLength);
        }
        if (!ListenerUtil.mutListener.listen(14345)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public static TextEntryDialog newInstance(@StringRes int title, @StringRes int message, @StringRes int positive, @StringRes int negative, int maxLines, int maxLength, int minLength) {
        TextEntryDialog dialog = new TextEntryDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(14346)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(14347)) {
            args.putInt("message", message);
        }
        if (!ListenerUtil.mutListener.listen(14348)) {
            args.putInt("positive", positive);
        }
        if (!ListenerUtil.mutListener.listen(14349)) {
            args.putInt("negative", negative);
        }
        if (!ListenerUtil.mutListener.listen(14350)) {
            args.putInt("maxLines", maxLines);
        }
        if (!ListenerUtil.mutListener.listen(14351)) {
            args.putInt("maxLength", maxLength);
        }
        if (!ListenerUtil.mutListener.listen(14352)) {
            args.putInt("minLength", minLength);
        }
        if (!ListenerUtil.mutListener.listen(14353)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public interface TextEntryDialogClickListener {

        void onYes(String tag, String text);

        void onNo(String tag);

        void onNeutral(String tag);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(14354)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(14359)) {
            if (callback == null) {
                try {
                    if (!ListenerUtil.mutListener.listen(14355)) {
                        callback = (TextEntryDialogClickListener) getTargetFragment();
                    }
                } catch (ClassCastException e) {
                }
                if (!ListenerUtil.mutListener.listen(14358)) {
                    // called from an activity rather than a fragment
                    if (callback == null) {
                        if (!ListenerUtil.mutListener.listen(14357)) {
                            if (activity instanceof TextEntryDialogClickListener) {
                                if (!ListenerUtil.mutListener.listen(14356)) {
                                    callback = (TextEntryDialogClickListener) activity;
                                }
                            }
                        }
                    }
                }
            }
        }
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(14361)) {
            if (serviceManager != null) {
                if (!ListenerUtil.mutListener.listen(14360)) {
                    localeService = ThreemaApplication.getServiceManager().getLocaleService();
                }
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(14362)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(14363)) {
            this.activity = activity;
        }
    }

    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        int message = getArguments().getInt("message");
        int positive = getArguments().getInt("positive");
        int neutral = getArguments().getInt("neutral");
        int negative = getArguments().getInt("negative");
        String text = getArguments().getString("text", "");
        int inputType = getArguments().getInt("inputType");
        if (!ListenerUtil.mutListener.listen(14364)) {
            inputFilterType = getArguments().getInt("inputFilterType", 0);
        }
        int maxLength = getArguments().getInt("maxLength", 0);
        int maxLines = getArguments().getInt("maxLines", 0);
        if (!ListenerUtil.mutListener.listen(14365)) {
            minLength = getArguments().getInt("minLength", 0);
        }
        final String tag = this.getTag();
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.dialog_text_entry, null);
        final EmojiEditText editText = dialogView.findViewById(R.id.edit_text);
        final TextInputLayout editTextLayout = dialogView.findViewById(R.id.text_input_layout);
        if (!ListenerUtil.mutListener.listen(14366)) {
            editText.setText(text);
        }
        if (!ListenerUtil.mutListener.listen(14374)) {
            if ((ListenerUtil.mutListener.listen(14372) ? (text != null || (ListenerUtil.mutListener.listen(14371) ? (text.length() >= 0) : (ListenerUtil.mutListener.listen(14370) ? (text.length() <= 0) : (ListenerUtil.mutListener.listen(14369) ? (text.length() < 0) : (ListenerUtil.mutListener.listen(14368) ? (text.length() != 0) : (ListenerUtil.mutListener.listen(14367) ? (text.length() == 0) : (text.length() > 0))))))) : (text != null && (ListenerUtil.mutListener.listen(14371) ? (text.length() >= 0) : (ListenerUtil.mutListener.listen(14370) ? (text.length() <= 0) : (ListenerUtil.mutListener.listen(14369) ? (text.length() < 0) : (ListenerUtil.mutListener.listen(14368) ? (text.length() != 0) : (ListenerUtil.mutListener.listen(14367) ? (text.length() == 0) : (text.length() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(14373)) {
                    editText.setSelection(text.length());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14381)) {
            if ((ListenerUtil.mutListener.listen(14379) ? (inputType >= 0) : (ListenerUtil.mutListener.listen(14378) ? (inputType <= 0) : (ListenerUtil.mutListener.listen(14377) ? (inputType > 0) : (ListenerUtil.mutListener.listen(14376) ? (inputType < 0) : (ListenerUtil.mutListener.listen(14375) ? (inputType == 0) : (inputType != 0))))))) {
                if (!ListenerUtil.mutListener.listen(14380)) {
                    editText.setInputType(inputType);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14388)) {
            if ((ListenerUtil.mutListener.listen(14386) ? (maxLength >= 0) : (ListenerUtil.mutListener.listen(14385) ? (maxLength <= 0) : (ListenerUtil.mutListener.listen(14384) ? (maxLength < 0) : (ListenerUtil.mutListener.listen(14383) ? (maxLength != 0) : (ListenerUtil.mutListener.listen(14382) ? (maxLength == 0) : (maxLength > 0))))))) {
                if (!ListenerUtil.mutListener.listen(14387)) {
                    editText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(maxLength) });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14396)) {
            if ((ListenerUtil.mutListener.listen(14393) ? (maxLines >= 1) : (ListenerUtil.mutListener.listen(14392) ? (maxLines <= 1) : (ListenerUtil.mutListener.listen(14391) ? (maxLines < 1) : (ListenerUtil.mutListener.listen(14390) ? (maxLines != 1) : (ListenerUtil.mutListener.listen(14389) ? (maxLines == 1) : (maxLines > 1))))))) {
                if (!ListenerUtil.mutListener.listen(14394)) {
                    editText.setSingleLine(false);
                }
                if (!ListenerUtil.mutListener.listen(14395)) {
                    editText.setMaxLines(maxLines);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14411)) {
            editText.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!ListenerUtil.mutListener.listen(14397)) {
                        ThreemaApplication.activityUserInteract(activity);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!ListenerUtil.mutListener.listen(14410)) {
                        if ((ListenerUtil.mutListener.listen(14402) ? (minLength >= 0) : (ListenerUtil.mutListener.listen(14401) ? (minLength <= 0) : (ListenerUtil.mutListener.listen(14400) ? (minLength < 0) : (ListenerUtil.mutListener.listen(14399) ? (minLength != 0) : (ListenerUtil.mutListener.listen(14398) ? (minLength == 0) : (minLength > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(14409)) {
                                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled((ListenerUtil.mutListener.listen(14408) ? (s != null || (ListenerUtil.mutListener.listen(14407) ? (s.length() <= minLength) : (ListenerUtil.mutListener.listen(14406) ? (s.length() > minLength) : (ListenerUtil.mutListener.listen(14405) ? (s.length() < minLength) : (ListenerUtil.mutListener.listen(14404) ? (s.length() != minLength) : (ListenerUtil.mutListener.listen(14403) ? (s.length() == minLength) : (s.length() >= minLength))))))) : (s != null && (ListenerUtil.mutListener.listen(14407) ? (s.length() <= minLength) : (ListenerUtil.mutListener.listen(14406) ? (s.length() > minLength) : (ListenerUtil.mutListener.listen(14405) ? (s.length() < minLength) : (ListenerUtil.mutListener.listen(14404) ? (s.length() != minLength) : (ListenerUtil.mutListener.listen(14403) ? (s.length() == minLength) : (s.length() >= minLength)))))))));
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(14434)) {
            if ((ListenerUtil.mutListener.listen(14416) ? (inputFilterType >= INPUT_FILTER_TYPE_IDENTITY) : (ListenerUtil.mutListener.listen(14415) ? (inputFilterType <= INPUT_FILTER_TYPE_IDENTITY) : (ListenerUtil.mutListener.listen(14414) ? (inputFilterType > INPUT_FILTER_TYPE_IDENTITY) : (ListenerUtil.mutListener.listen(14413) ? (inputFilterType < INPUT_FILTER_TYPE_IDENTITY) : (ListenerUtil.mutListener.listen(14412) ? (inputFilterType != INPUT_FILTER_TYPE_IDENTITY) : (inputFilterType == INPUT_FILTER_TYPE_IDENTITY))))))) {
                if (!ListenerUtil.mutListener.listen(14433)) {
                    editText.setFilters(new InputFilter[] { new InputFilter.AllCaps(), new InputFilter.LengthFilter(ProtocolDefines.IDENTITY_LEN) });
                }
            } else if ((ListenerUtil.mutListener.listen(14422) ? ((ListenerUtil.mutListener.listen(14421) ? (inputFilterType >= INPUT_FILTER_TYPE_PHONE) : (ListenerUtil.mutListener.listen(14420) ? (inputFilterType <= INPUT_FILTER_TYPE_PHONE) : (ListenerUtil.mutListener.listen(14419) ? (inputFilterType > INPUT_FILTER_TYPE_PHONE) : (ListenerUtil.mutListener.listen(14418) ? (inputFilterType < INPUT_FILTER_TYPE_PHONE) : (ListenerUtil.mutListener.listen(14417) ? (inputFilterType != INPUT_FILTER_TYPE_PHONE) : (inputFilterType == INPUT_FILTER_TYPE_PHONE)))))) || localeService != null) : ((ListenerUtil.mutListener.listen(14421) ? (inputFilterType >= INPUT_FILTER_TYPE_PHONE) : (ListenerUtil.mutListener.listen(14420) ? (inputFilterType <= INPUT_FILTER_TYPE_PHONE) : (ListenerUtil.mutListener.listen(14419) ? (inputFilterType > INPUT_FILTER_TYPE_PHONE) : (ListenerUtil.mutListener.listen(14418) ? (inputFilterType < INPUT_FILTER_TYPE_PHONE) : (ListenerUtil.mutListener.listen(14417) ? (inputFilterType != INPUT_FILTER_TYPE_PHONE) : (inputFilterType == INPUT_FILTER_TYPE_PHONE)))))) && localeService != null))) {
                if (!ListenerUtil.mutListener.listen(14430)) {
                    if ((ListenerUtil.mutListener.listen(14427) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(14426) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(14425) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(14424) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(14423) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                        if (!ListenerUtil.mutListener.listen(14429)) {
                            editText.addTextChangedListener(new PhoneNumberFormattingTextWatcher(localeService.getCountryIsoCode()));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(14428)) {
                            editText.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(14432)) {
                    editText.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if (!ListenerUtil.mutListener.listen(14431)) {
                                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(localeService.validatePhoneNumber(s.toString()));
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14441)) {
            if ((ListenerUtil.mutListener.listen(14439) ? (message >= 0) : (ListenerUtil.mutListener.listen(14438) ? (message <= 0) : (ListenerUtil.mutListener.listen(14437) ? (message > 0) : (ListenerUtil.mutListener.listen(14436) ? (message < 0) : (ListenerUtil.mutListener.listen(14435) ? (message == 0) : (message != 0))))))) {
                if (!ListenerUtil.mutListener.listen(14440)) {
                    editTextLayout.setHint(getString(message));
                }
            }
        }
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        if (!ListenerUtil.mutListener.listen(14442)) {
            builder.setView(dialogView);
        }
        if (!ListenerUtil.mutListener.listen(14449)) {
            if ((ListenerUtil.mutListener.listen(14447) ? (title >= 0) : (ListenerUtil.mutListener.listen(14446) ? (title <= 0) : (ListenerUtil.mutListener.listen(14445) ? (title > 0) : (ListenerUtil.mutListener.listen(14444) ? (title < 0) : (ListenerUtil.mutListener.listen(14443) ? (title == 0) : (title != 0))))))) {
                if (!ListenerUtil.mutListener.listen(14448)) {
                    builder.setTitle(title);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14451)) {
            builder.setPositiveButton(getString(positive), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    if (!ListenerUtil.mutListener.listen(14450)) {
                        callback.onYes(tag, editText.getText().toString());
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(14453)) {
            builder.setNegativeButton(getString(negative), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    if (!ListenerUtil.mutListener.listen(14452)) {
                        callback.onNo(tag);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(14461)) {
            if ((ListenerUtil.mutListener.listen(14458) ? (neutral >= 0) : (ListenerUtil.mutListener.listen(14457) ? (neutral <= 0) : (ListenerUtil.mutListener.listen(14456) ? (neutral > 0) : (ListenerUtil.mutListener.listen(14455) ? (neutral < 0) : (ListenerUtil.mutListener.listen(14454) ? (neutral == 0) : (neutral != 0))))))) {
                if (!ListenerUtil.mutListener.listen(14460)) {
                    builder.setNeutralButton(getString(neutral), new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            if (!ListenerUtil.mutListener.listen(14459)) {
                                callback.onNeutral(tag);
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14462)) {
            alertDialog = builder.create();
        }
        return alertDialog;
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(14463)) {
            super.onStart();
        }
        ColorStateList colorStateList = DialogUtil.getButtonColorStateList(activity);
        if (!ListenerUtil.mutListener.listen(14464)) {
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(colorStateList);
        }
        if (!ListenerUtil.mutListener.listen(14465)) {
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(colorStateList);
        }
        if (!ListenerUtil.mutListener.listen(14478)) {
            if ((ListenerUtil.mutListener.listen(14476) ? ((ListenerUtil.mutListener.listen(14470) ? (inputFilterType >= INPUT_FILTER_TYPE_PHONE) : (ListenerUtil.mutListener.listen(14469) ? (inputFilterType <= INPUT_FILTER_TYPE_PHONE) : (ListenerUtil.mutListener.listen(14468) ? (inputFilterType > INPUT_FILTER_TYPE_PHONE) : (ListenerUtil.mutListener.listen(14467) ? (inputFilterType < INPUT_FILTER_TYPE_PHONE) : (ListenerUtil.mutListener.listen(14466) ? (inputFilterType != INPUT_FILTER_TYPE_PHONE) : (inputFilterType == INPUT_FILTER_TYPE_PHONE)))))) && (ListenerUtil.mutListener.listen(14475) ? (minLength >= 0) : (ListenerUtil.mutListener.listen(14474) ? (minLength <= 0) : (ListenerUtil.mutListener.listen(14473) ? (minLength < 0) : (ListenerUtil.mutListener.listen(14472) ? (minLength != 0) : (ListenerUtil.mutListener.listen(14471) ? (minLength == 0) : (minLength > 0))))))) : ((ListenerUtil.mutListener.listen(14470) ? (inputFilterType >= INPUT_FILTER_TYPE_PHONE) : (ListenerUtil.mutListener.listen(14469) ? (inputFilterType <= INPUT_FILTER_TYPE_PHONE) : (ListenerUtil.mutListener.listen(14468) ? (inputFilterType > INPUT_FILTER_TYPE_PHONE) : (ListenerUtil.mutListener.listen(14467) ? (inputFilterType < INPUT_FILTER_TYPE_PHONE) : (ListenerUtil.mutListener.listen(14466) ? (inputFilterType != INPUT_FILTER_TYPE_PHONE) : (inputFilterType == INPUT_FILTER_TYPE_PHONE)))))) || (ListenerUtil.mutListener.listen(14475) ? (minLength >= 0) : (ListenerUtil.mutListener.listen(14474) ? (minLength <= 0) : (ListenerUtil.mutListener.listen(14473) ? (minLength < 0) : (ListenerUtil.mutListener.listen(14472) ? (minLength != 0) : (ListenerUtil.mutListener.listen(14471) ? (minLength == 0) : (minLength > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(14477)) {
                    alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
                }
            }
        }
        Button neutral = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
        if (!ListenerUtil.mutListener.listen(14480)) {
            if (neutral != null) {
                if (!ListenerUtil.mutListener.listen(14479)) {
                    neutral.setTextColor(colorStateList);
                }
            }
        }
    }
}

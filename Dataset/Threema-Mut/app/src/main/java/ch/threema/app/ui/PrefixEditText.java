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
package ch.threema.app.ui;

import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.AttributeSet;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PrefixEditText extends ThreemaTextInputEditText {

    String prefix = "";

    public PrefixEditText(Context context) {
        super(context, null);
        if (!ListenerUtil.mutListener.listen(46886)) {
            init();
        }
    }

    public PrefixEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(46887)) {
            init();
        }
    }

    public PrefixEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(46888)) {
            init();
        }
    }

    private void init() {
        if (!ListenerUtil.mutListener.listen(46889)) {
            prefix = (String) getTag();
        }
        if (!ListenerUtil.mutListener.listen(46890)) {
            Selection.setSelection(getText(), getText().length());
        }
        if (!ListenerUtil.mutListener.listen(46906)) {
            addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!ListenerUtil.mutListener.listen(46905)) {
                        if ((ListenerUtil.mutListener.listen(46891) ? (prefix != null || s.toString().startsWith(prefix + prefix)) : (prefix != null && s.toString().startsWith(prefix + prefix)))) {
                            if (!ListenerUtil.mutListener.listen(46899)) {
                                setText(s.subSequence(prefix.length(), s.length()));
                            }
                            if (!ListenerUtil.mutListener.listen(46904)) {
                                setSelection((ListenerUtil.mutListener.listen(46903) ? (s.length() % prefix.length()) : (ListenerUtil.mutListener.listen(46902) ? (s.length() / prefix.length()) : (ListenerUtil.mutListener.listen(46901) ? (s.length() * prefix.length()) : (ListenerUtil.mutListener.listen(46900) ? (s.length() + prefix.length()) : (s.length() - prefix.length()))))));
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(46898)) {
                                if (!s.toString().startsWith(prefix)) {
                                    String cleanString;
                                    String deletedPrefix = prefix.substring(0, (ListenerUtil.mutListener.listen(46895) ? (prefix.length() % 1) : (ListenerUtil.mutListener.listen(46894) ? (prefix.length() / 1) : (ListenerUtil.mutListener.listen(46893) ? (prefix.length() * 1) : (ListenerUtil.mutListener.listen(46892) ? (prefix.length() + 1) : (prefix.length() - 1))))));
                                    if (s.toString().startsWith(deletedPrefix)) {
                                        cleanString = s.toString().replaceAll(deletedPrefix, "");
                                    } else {
                                        cleanString = s.toString().replaceAll(prefix, "");
                                    }
                                    if (!ListenerUtil.mutListener.listen(46896)) {
                                        setText(prefix + cleanString);
                                    }
                                    if (!ListenerUtil.mutListener.listen(46897)) {
                                        setSelection(prefix.length());
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (!ListenerUtil.mutListener.listen(46910)) {
            if ((ListenerUtil.mutListener.listen(46908) ? ((ListenerUtil.mutListener.listen(46907) ? (text != null || prefix != null) : (text != null && prefix != null)) || !text.toString().startsWith(prefix)) : ((ListenerUtil.mutListener.listen(46907) ? (text != null || prefix != null) : (text != null && prefix != null)) && !text.toString().startsWith(prefix)))) {
                if (!ListenerUtil.mutListener.listen(46909)) {
                    text = prefix + text;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(46911)) {
            super.setText(text, type);
        }
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        CharSequence text = getText();
        if (!ListenerUtil.mutListener.listen(46944)) {
            if ((ListenerUtil.mutListener.listen(46912) ? (prefix != null || text != null) : (prefix != null && text != null))) {
                if (!ListenerUtil.mutListener.listen(46943)) {
                    if ((ListenerUtil.mutListener.listen(46917) ? (selEnd >= selStart) : (ListenerUtil.mutListener.listen(46916) ? (selEnd <= selStart) : (ListenerUtil.mutListener.listen(46915) ? (selEnd > selStart) : (ListenerUtil.mutListener.listen(46914) ? (selEnd != selStart) : (ListenerUtil.mutListener.listen(46913) ? (selEnd == selStart) : (selEnd < selStart))))))) {
                        if (!ListenerUtil.mutListener.listen(46942)) {
                            setSelection(text.length(), text.length());
                        }
                        return;
                    } else if ((ListenerUtil.mutListener.listen(46922) ? (text.length() <= prefix.length()) : (ListenerUtil.mutListener.listen(46921) ? (text.length() > prefix.length()) : (ListenerUtil.mutListener.listen(46920) ? (text.length() < prefix.length()) : (ListenerUtil.mutListener.listen(46919) ? (text.length() != prefix.length()) : (ListenerUtil.mutListener.listen(46918) ? (text.length() == prefix.length()) : (text.length() >= prefix.length()))))))) {
                        if (!ListenerUtil.mutListener.listen(46941)) {
                            if ((ListenerUtil.mutListener.listen(46927) ? (selStart >= prefix.length()) : (ListenerUtil.mutListener.listen(46926) ? (selStart > prefix.length()) : (ListenerUtil.mutListener.listen(46925) ? (selStart < prefix.length()) : (ListenerUtil.mutListener.listen(46924) ? (selStart != prefix.length()) : (ListenerUtil.mutListener.listen(46923) ? (selStart == prefix.length()) : (selStart <= prefix.length()))))))) {
                                if (!ListenerUtil.mutListener.listen(46940)) {
                                    if ((ListenerUtil.mutListener.listen(46932) ? (selEnd >= prefix.length()) : (ListenerUtil.mutListener.listen(46931) ? (selEnd > prefix.length()) : (ListenerUtil.mutListener.listen(46930) ? (selEnd < prefix.length()) : (ListenerUtil.mutListener.listen(46929) ? (selEnd != prefix.length()) : (ListenerUtil.mutListener.listen(46928) ? (selEnd == prefix.length()) : (selEnd <= prefix.length()))))))) {
                                        if (!ListenerUtil.mutListener.listen(46939)) {
                                            setSelection(prefix.length(), prefix.length());
                                        }
                                        return;
                                    } else if ((ListenerUtil.mutListener.listen(46937) ? (selEnd >= prefix.length()) : (ListenerUtil.mutListener.listen(46936) ? (selEnd <= prefix.length()) : (ListenerUtil.mutListener.listen(46935) ? (selEnd < prefix.length()) : (ListenerUtil.mutListener.listen(46934) ? (selEnd != prefix.length()) : (ListenerUtil.mutListener.listen(46933) ? (selEnd == prefix.length()) : (selEnd > prefix.length()))))))) {
                                        if (!ListenerUtil.mutListener.listen(46938)) {
                                            setSelection(prefix.length(), selEnd);
                                        }
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(46945)) {
            super.onSelectionChanged(selStart, selEnd);
        }
    }
}

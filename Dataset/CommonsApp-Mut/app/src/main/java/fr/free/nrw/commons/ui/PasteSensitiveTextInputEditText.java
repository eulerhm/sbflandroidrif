package fr.free.nrw.commons.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import com.google.android.material.textfield.TextInputEditText;
import fr.free.nrw.commons.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PasteSensitiveTextInputEditText extends TextInputEditText {

    private boolean formattingAllowed = true;

    public PasteSensitiveTextInputEditText(final Context context) {
        super(context);
    }

    public PasteSensitiveTextInputEditText(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(13)) {
            formattingAllowed = extractFormattingAttribute(context, attrs);
        }
    }

    @Override
    public boolean onTextContextMenuItem(int id) {
        if (!ListenerUtil.mutListener.listen(15)) {
            // if not paste command, or formatting is allowed, return default
            if ((ListenerUtil.mutListener.listen(14) ? (id != android.R.id.paste && formattingAllowed) : (id != android.R.id.paste || formattingAllowed))) {
                return super.onTextContextMenuItem(id);
            }
        }
        // if its paste and formatting not allowed
        boolean proceeded;
        if ((ListenerUtil.mutListener.listen(20) ? (VERSION.SDK_INT <= 23) : (ListenerUtil.mutListener.listen(19) ? (VERSION.SDK_INT > 23) : (ListenerUtil.mutListener.listen(18) ? (VERSION.SDK_INT < 23) : (ListenerUtil.mutListener.listen(17) ? (VERSION.SDK_INT != 23) : (ListenerUtil.mutListener.listen(16) ? (VERSION.SDK_INT == 23) : (VERSION.SDK_INT >= 23))))))) {
            proceeded = super.onTextContextMenuItem(android.R.id.pasteAsPlainText);
        } else {
            proceeded = super.onTextContextMenuItem(id);
            if (!ListenerUtil.mutListener.listen(24)) {
                if ((ListenerUtil.mutListener.listen(21) ? (proceeded || getText() != null) : (proceeded && getText() != null))) {
                    if (!ListenerUtil.mutListener.listen(22)) {
                        // rewrite with plain text so formatting is lost
                        setText(getText().toString());
                    }
                    if (!ListenerUtil.mutListener.listen(23)) {
                        setSelection(getText().length());
                    }
                }
            }
        }
        return proceeded;
    }

    private boolean extractFormattingAttribute(Context context, AttributeSet attrs) {
        boolean formatAllowed = true;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PasteSensitiveTextInputEditText, 0, 0);
        try {
            if (!ListenerUtil.mutListener.listen(26)) {
                formatAllowed = a.getBoolean(R.styleable.PasteSensitiveTextInputEditText_allowFormatting, true);
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(25)) {
                a.recycle();
            }
        }
        return formatAllowed;
    }

    public void setFormattingAllowed(boolean formattingAllowed) {
        if (!ListenerUtil.mutListener.listen(27)) {
            this.formattingAllowed = formattingAllowed;
        }
    }
}

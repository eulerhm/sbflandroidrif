package fr.free.nrw.commons.nearby;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import java.util.List;
import fr.free.nrw.commons.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Base on https://stackoverflow.com/a/40939367/3950497 answer.
 */
public class CheckBoxTriStates extends AppCompatCheckBox {

    public static final int UNKNOWN = -1;

    public static final int UNCHECKED = 0;

    public static final int CHECKED = 1;

    private int state = UNKNOWN;

    private Callback callback;

    public interface Callback {

        void filterByMarkerType(@Nullable List<Label> selectedLabels, int state, boolean b, boolean b1);
    }

    public void setCallback(Callback callback) {
        if (!ListenerUtil.mutListener.listen(3589)) {
            this.callback = callback;
        }
    }

    /**
     * This is the listener set to the super class which is going to be evoke each
     * time the check state has changed.
     */
    private final OnCheckedChangeListener privateListener = new CompoundButton.OnCheckedChangeListener() {

        // checkbox status is changed from uncheck to checked.
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (!ListenerUtil.mutListener.listen(3593)) {
                switch(state) {
                    case UNKNOWN:
                        if (!ListenerUtil.mutListener.listen(3590)) {
                            setState(UNCHECKED);
                        }
                        ;
                        break;
                    case UNCHECKED:
                        if (!ListenerUtil.mutListener.listen(3591)) {
                            setState(CHECKED);
                        }
                        break;
                    case CHECKED:
                        if (!ListenerUtil.mutListener.listen(3592)) {
                            setState(UNKNOWN);
                        }
                        break;
                }
            }
        }
    };

    /**
     * Holds a reference to the listener set by a client, if any.
     */
    private OnCheckedChangeListener clientListener;

    public CheckBoxTriStates(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(3594)) {
            init();
        }
    }

    public CheckBoxTriStates(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(3595)) {
            init();
        }
    }

    public CheckBoxTriStates(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(3596)) {
            init();
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        if (!ListenerUtil.mutListener.listen(3608)) {
            if ((ListenerUtil.mutListener.listen(3601) ? (this.state >= state) : (ListenerUtil.mutListener.listen(3600) ? (this.state <= state) : (ListenerUtil.mutListener.listen(3599) ? (this.state > state) : (ListenerUtil.mutListener.listen(3598) ? (this.state < state) : (ListenerUtil.mutListener.listen(3597) ? (this.state == state) : (this.state != state))))))) {
                if (!ListenerUtil.mutListener.listen(3602)) {
                    this.state = state;
                }
                if (!ListenerUtil.mutListener.listen(3604)) {
                    if (this.clientListener != null) {
                        if (!ListenerUtil.mutListener.listen(3603)) {
                            this.clientListener.onCheckedChanged(this, this.isChecked());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3606)) {
                    if (NearbyController.currentLocation != null) {
                        if (!ListenerUtil.mutListener.listen(3605)) {
                            callback.filterByMarkerType(null, state, false, true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3607)) {
                    updateBtn();
                }
            }
        }
    }

    @Override
    public void setOnCheckedChangeListener(@Nullable OnCheckedChangeListener listener) {
        if (!ListenerUtil.mutListener.listen(3610)) {
            // a reference to it and evoke it when needed.
            if (this.privateListener != listener) {
                if (!ListenerUtil.mutListener.listen(3609)) {
                    this.clientListener = listener;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3611)) {
            // always use our implementation
            super.setOnCheckedChangeListener(privateListener);
        }
    }

    private void init() {
        if (!ListenerUtil.mutListener.listen(3612)) {
            state = UNKNOWN;
        }
        if (!ListenerUtil.mutListener.listen(3613)) {
            updateBtn();
        }
    }

    public void addAction() {
        if (!ListenerUtil.mutListener.listen(3614)) {
            setOnCheckedChangeListener(this.privateListener);
        }
    }

    private void updateBtn() {
        int btnDrawable = R.drawable.ic_indeterminate_check_box_black_24dp;
        if (!ListenerUtil.mutListener.listen(3618)) {
            switch(state) {
                case UNKNOWN:
                    if (!ListenerUtil.mutListener.listen(3615)) {
                        btnDrawable = R.drawable.ic_indeterminate_check_box_black_24dp;
                    }
                    break;
                case UNCHECKED:
                    if (!ListenerUtil.mutListener.listen(3616)) {
                        btnDrawable = R.drawable.ic_check_box_outline_blank_black_24dp;
                    }
                    break;
                case CHECKED:
                    if (!ListenerUtil.mutListener.listen(3617)) {
                        btnDrawable = R.drawable.ic_check_box_black_24dp;
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(3619)) {
            setButtonDrawable(btnDrawable);
        }
    }
}

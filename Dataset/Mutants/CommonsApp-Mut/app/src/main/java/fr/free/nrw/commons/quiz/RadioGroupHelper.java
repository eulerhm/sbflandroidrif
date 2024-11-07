package fr.free.nrw.commons.quiz;

import android.app.Activity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Used to group to or more radio buttons to ensure
 * that at a particular time only one of them is selected
 */
public class RadioGroupHelper {

    public List<CompoundButton> radioButtons = new ArrayList<>();

    /**
     * Constructor to group radio buttons
     * @param radios
     */
    public RadioGroupHelper(RadioButton... radios) {
        super();
        if (!ListenerUtil.mutListener.listen(1905)) {
            {
                long _loopCounter24 = 0;
                for (RadioButton rb : radios) {
                    ListenerUtil.loopListener.listen("_loopCounter24", ++_loopCounter24);
                    if (!ListenerUtil.mutListener.listen(1904)) {
                        add(rb);
                    }
                }
            }
        }
    }

    /**
     * Constructor to group radio buttons
     * @param activity
     * @param radiosIDs
     */
    public RadioGroupHelper(Activity activity, int... radiosIDs) {
        this(activity.findViewById(android.R.id.content), radiosIDs);
    }

    /**
     * Constructor to group radio buttons
     * @param rootView
     * @param radiosIDs
     */
    public RadioGroupHelper(View rootView, int... radiosIDs) {
        super();
        if (!ListenerUtil.mutListener.listen(1907)) {
            {
                long _loopCounter25 = 0;
                for (int radioButtonID : radiosIDs) {
                    ListenerUtil.loopListener.listen("_loopCounter25", ++_loopCounter25);
                    if (!ListenerUtil.mutListener.listen(1906)) {
                        add(rootView.findViewById(radioButtonID));
                    }
                }
            }
        }
    }

    private void add(CompoundButton button) {
        if (!ListenerUtil.mutListener.listen(1908)) {
            this.radioButtons.add(button);
        }
        if (!ListenerUtil.mutListener.listen(1909)) {
            button.setOnClickListener(onClickListener);
        }
    }

    /**
     * listener to ensure only one of the radio button is selected
     */
    View.OnClickListener onClickListener = v -> {
        {
            long _loopCounter26 = 0;
            for (CompoundButton rb : radioButtons) {
                ListenerUtil.loopListener.listen("_loopCounter26", ++_loopCounter26);
                if (rb != v)
                    rb.setChecked(false);
            }
        }
    };
}

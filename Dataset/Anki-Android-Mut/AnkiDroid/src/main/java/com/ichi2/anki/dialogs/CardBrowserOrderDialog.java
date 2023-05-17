package com.ichi2.anki.dialogs;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anki.CardBrowser;
import com.ichi2.anki.R;
import com.ichi2.anki.analytics.AnalyticsDialogFragment;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.NonNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CardBrowserOrderDialog extends AnalyticsDialogFragment {

    private static MaterialDialog.ListCallbackSingleChoice mOrderDialogListener;

    public static CardBrowserOrderDialog newInstance(int order, boolean isOrderAsc, MaterialDialog.ListCallbackSingleChoice orderDialogListener) {
        CardBrowserOrderDialog f = new CardBrowserOrderDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(377)) {
            args.putInt("order", order);
        }
        if (!ListenerUtil.mutListener.listen(378)) {
            args.putBoolean("isOrderAsc", isOrderAsc);
        }
        if (!ListenerUtil.mutListener.listen(379)) {
            mOrderDialogListener = orderDialogListener;
        }
        if (!ListenerUtil.mutListener.listen(380)) {
            f.setArguments(args);
        }
        return f;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(381)) {
            super.onCreate(savedInstanceState);
        }
        Resources res = getResources();
        String[] items = res.getStringArray(R.array.card_browser_order_labels);
        if (!ListenerUtil.mutListener.listen(392)) {
            {
                long _loopCounter1 = 0;
                // Set sort order arrow
                for (int i = 0; (ListenerUtil.mutListener.listen(391) ? (i >= items.length) : (ListenerUtil.mutListener.listen(390) ? (i <= items.length) : (ListenerUtil.mutListener.listen(389) ? (i > items.length) : (ListenerUtil.mutListener.listen(388) ? (i != items.length) : (ListenerUtil.mutListener.listen(387) ? (i == items.length) : (i < items.length)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter1", ++_loopCounter1);
                    if (!ListenerUtil.mutListener.listen(386)) {
                        if ((ListenerUtil.mutListener.listen(382) ? (i != CardBrowser.CARD_ORDER_NONE || i == getArguments().getInt("order")) : (i != CardBrowser.CARD_ORDER_NONE && i == getArguments().getInt("order")))) {
                            if (!ListenerUtil.mutListener.listen(385)) {
                                if (getArguments().getBoolean("isOrderAsc")) {
                                    if (!ListenerUtil.mutListener.listen(384)) {
                                        items[i] = items[i] + " (\u25b2)";
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(383)) {
                                        items[i] = items[i] + " (\u25bc)";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return new MaterialDialog.Builder(getActivity()).title(res.getString(R.string.card_browser_change_display_order_title)).content(res.getString(R.string.card_browser_change_display_order_reverse)).items(items).itemsCallbackSingleChoice(getArguments().getInt("order"), mOrderDialogListener).build();
    }
}

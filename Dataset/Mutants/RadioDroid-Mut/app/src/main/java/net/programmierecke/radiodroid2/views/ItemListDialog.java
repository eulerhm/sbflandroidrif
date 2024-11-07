package net.programmierecke.radiodroid2.views;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import net.programmierecke.radiodroid2.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ItemListDialog {

    public interface Callback {

        void onItemSelected(int resourceId);
    }

    public static BottomSheetDialog create(@NonNull Activity activity, @NonNull int[] resourceIds, @NonNull final Callback callback) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        final LayoutInflater inflater = activity.getLayoutInflater();
        final View sheetView = inflater.inflate(R.layout.dialog_generic_item_list, null);
        final ViewGroup viewItemsList = sheetView.findViewById(R.id.layout_items_list);
        if (!ListenerUtil.mutListener.listen(3474)) {
            {
                long _loopCounter45 = 0;
                for (final int resourceId : resourceIds) {
                    ListenerUtil.loopListener.listen("_loopCounter45", ++_loopCounter45);
                    final View itemView = inflater.inflate(R.layout.dialog_generic_item, null);
                    TextView textView = itemView.findViewById(R.id.text);
                    if (!ListenerUtil.mutListener.listen(3470)) {
                        textView.setText(resourceId);
                    }
                    if (!ListenerUtil.mutListener.listen(3471)) {
                        textView.setClickable(true);
                    }
                    if (!ListenerUtil.mutListener.listen(3472)) {
                        textView.setOnClickListener(view -> {
                            callback.onItemSelected(resourceId);
                            bottomSheetDialog.hide();
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(3473)) {
                        viewItemsList.addView(textView);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3475)) {
            bottomSheetDialog.setContentView(sheetView);
        }
        return bottomSheetDialog;
    }
}

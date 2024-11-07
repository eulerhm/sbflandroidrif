/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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

import android.os.Bundle;
import java.util.ArrayList;
import androidx.annotation.StringRes;
import ch.threema.app.ui.BottomSheetItem;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BottomSheetListDialog extends BottomSheetAbstractDialog {

    public static BottomSheetListDialog newInstance(@StringRes int title, ArrayList<BottomSheetItem> items, int selected) {
        BottomSheetListDialog dialog = new BottomSheetListDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13236)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(13237)) {
            args.putInt("selected", selected);
        }
        if (!ListenerUtil.mutListener.listen(13238)) {
            args.putParcelableArrayList("items", items);
        }
        if (!ListenerUtil.mutListener.listen(13239)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public static BottomSheetListDialog newInstance(@StringRes int title, ArrayList<BottomSheetItem> items, int selected, BottomSheetDialogInlineClickListener listener) {
        // or fragments without setRetainInstance(true)
        BottomSheetListDialog dialog = new BottomSheetListDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13240)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(13241)) {
            args.putInt("selected", selected);
        }
        if (!ListenerUtil.mutListener.listen(13242)) {
            args.putParcelableArrayList("items", items);
        }
        if (!ListenerUtil.mutListener.listen(13243)) {
            args.putParcelable("listener", listener);
        }
        if (!ListenerUtil.mutListener.listen(13244)) {
            dialog.setArguments(args);
        }
        return dialog;
    }
}

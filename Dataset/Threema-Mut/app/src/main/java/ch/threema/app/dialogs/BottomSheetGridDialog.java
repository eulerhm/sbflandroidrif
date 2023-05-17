/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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

public class BottomSheetGridDialog extends BottomSheetAbstractDialog {

    public static BottomSheetGridDialog newInstance(@StringRes int title, ArrayList<BottomSheetItem> items) {
        BottomSheetGridDialog dialog = new BottomSheetGridDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(13231)) {
            args.putInt("title", title);
        }
        if (!ListenerUtil.mutListener.listen(13232)) {
            args.putParcelableArrayList("items", items);
        }
        if (!ListenerUtil.mutListener.listen(13233)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    /* Hack to prevent TransactionTooLargeException when hosting activity goes into the background */
    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(13234)) {
            dismiss();
        }
        if (!ListenerUtil.mutListener.listen(13235)) {
            super.onPause();
        }
    }
}

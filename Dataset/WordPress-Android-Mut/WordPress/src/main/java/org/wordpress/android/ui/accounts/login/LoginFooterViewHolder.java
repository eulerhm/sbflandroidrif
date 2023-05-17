package org.wordpress.android.ui.accounts.login;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import org.wordpress.android.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LoginFooterViewHolder extends RecyclerView.ViewHolder {

    private final TextView mFooterTextView;

    LoginFooterViewHolder(View view) {
        super(view);
        mFooterTextView = view.findViewById(R.id.footer_text_view);
    }

    public void bindText(String text) {
        if (!ListenerUtil.mutListener.listen(3475)) {
            mFooterTextView.setText(text);
        }
    }
}

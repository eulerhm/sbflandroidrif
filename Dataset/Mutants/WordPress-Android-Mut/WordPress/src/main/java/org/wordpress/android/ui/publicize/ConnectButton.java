package org.wordpress.android.ui.publicize;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.StringRes;
import com.google.android.material.button.MaterialButton;
import org.wordpress.android.R;
import org.wordpress.android.ui.publicize.PublicizeConstants.ConnectAction;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Publicize connect/disconnect/reconnect button
 */
public class ConnectButton extends MaterialButton {

    private ConnectAction mConnectAction = ConnectAction.CONNECT;

    public ConnectButton(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(17212)) {
            updateView();
        }
    }

    public ConnectButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(17213)) {
            updateView();
        }
    }

    public ConnectButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(17214)) {
            updateView();
        }
    }

    private void updateView() {
        @StringRes
        int captionResId;
        switch(mConnectAction) {
            case CONNECT:
                captionResId = R.string.share_btn_connect;
                break;
            case DISCONNECT:
                captionResId = R.string.share_btn_disconnect;
                break;
            case RECONNECT:
                captionResId = R.string.share_btn_reconnect;
                break;
            case CONNECT_ANOTHER_ACCOUNT:
                captionResId = R.string.share_btn_connect_another_account;
                break;
            default:
                return;
        }
        if (!ListenerUtil.mutListener.listen(17215)) {
            setText(captionResId);
        }
    }

    public ConnectAction getAction() {
        return mConnectAction;
    }

    public void setAction(ConnectAction newAction) {
        if (!ListenerUtil.mutListener.listen(17219)) {
            if ((ListenerUtil.mutListener.listen(17216) ? (newAction != null || !newAction.equals(mConnectAction)) : (newAction != null && !newAction.equals(mConnectAction)))) {
                if (!ListenerUtil.mutListener.listen(17217)) {
                    mConnectAction = newAction;
                }
                if (!ListenerUtil.mutListener.listen(17218)) {
                    updateView();
                }
            }
        }
    }
}

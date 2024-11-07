package org.wordpress.android.networking;

import org.wordpress.android.fluxc.network.rest.wpcom.auth.AccessToken;
import org.wordpress.android.util.StringUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// TODO: kill this when we don't need any other rest client than the one in FluxC
public class OAuthAuthenticator implements Authenticator {

    private AccessToken mAccessToken;

    public OAuthAuthenticator(AccessToken accessToken) {
        if (!ListenerUtil.mutListener.listen(2704)) {
            mAccessToken = accessToken;
        }
    }

    @Override
    public void authenticate(final AuthenticatorRequest request) {
        if (!ListenerUtil.mutListener.listen(2705)) {
            request.sendWithAccessToken(StringUtils.notNullStr(mAccessToken.get()));
        }
    }
}

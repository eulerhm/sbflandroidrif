package org.wordpress.android.models;

import android.content.Context;
import org.wordpress.android.R;
import org.wordpress.android.fluxc.model.RoleModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.util.StringUtils;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RoleUtils {

    public static String getDisplayName(String userRole, List<RoleModel> siteUserRoles) {
        if (!ListenerUtil.mutListener.listen(2645)) {
            if (siteUserRoles != null) {
                if (!ListenerUtil.mutListener.listen(2644)) {
                    {
                        long _loopCounter101 = 0;
                        for (RoleModel roleModel : siteUserRoles) {
                            ListenerUtil.loopListener.listen("_loopCounter101", ++_loopCounter101);
                            if (!ListenerUtil.mutListener.listen(2643)) {
                                if (roleModel.getName().equalsIgnoreCase(userRole)) {
                                    return roleModel.getDisplayName();
                                }
                            }
                        }
                    }
                }
            }
        }
        return StringUtils.capitalize(userRole);
    }

    public static List<RoleModel> getInviteRoles(SiteStore siteStore, SiteModel siteModel, Context context) {
        // Setup invite roles
        List<RoleModel> inviteRoles = siteStore.getUserRoles(siteModel);
        // The API doesn't return the follower/viewer role, so we need to manually add it for invites
        RoleModel viewerOrFollowerRole = new RoleModel();
        if (!ListenerUtil.mutListener.listen(2646)) {
            // the remote expects "follower" as the role parameter even if the role is "viewer"
            viewerOrFollowerRole.setName("follower");
        }
        int displayNameRes = siteModel.isPrivate() ? R.string.role_viewer : R.string.role_follower;
        if (!ListenerUtil.mutListener.listen(2647)) {
            viewerOrFollowerRole.setDisplayName(context.getString(displayNameRes));
        }
        if (!ListenerUtil.mutListener.listen(2648)) {
            inviteRoles.add(viewerOrFollowerRole);
        }
        return inviteRoles;
    }
}

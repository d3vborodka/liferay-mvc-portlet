package dev.b7w23z.mvcportlet.utils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;

public final class ResourceAccessUtils {

    private final static Log log = LogFactoryUtil.getLog(ResourceAccessUtils.class);

    public static boolean hasPermission(User user, String resource, String action) {
        try {
            if ( user == null ) {
                return false;
            }

            return ResourcePermissionLocalServiceUtil.hasResourcePermission(
                    user.getCompanyId(),
                    User.class.getName(),
                    1,
                    String.valueOf(user.getCompanyId()),
                    user.getRoleIds(),
                    "VIEW"
            );
        } catch ( PortalException e ) {
            log.error("Error while checking permissions: " + e.getMessage());
            return false;
        }
    }

}

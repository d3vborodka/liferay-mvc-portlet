package dev.b7w23z.mvcportlet.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.PhoneServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import dev.b7w23z.mvcportlet.dto.UserDto;

import javax.portlet.PortletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class UserUtils {

    private static final Log log = LogFactoryUtil.getLog(UserUtils.class);

    public static User getCurrent(PortletRequest request) {
        try {
            return PortalUtil.getUser(request);
        } catch ( PortalException e ) {
            log.error("Error while fetching current user: " + e.getMessage());
            return null;
        }
    }

    public static UserDto getUser(PortletRequest request, Long userId) {
        try {
            if ( userId == null || !checkAccess(request) ) {
                return null;
            }
            return toDto(UserLocalServiceUtil.getUser(userId));
        } catch ( Exception e ) {
            log.error("Error while fetching user: " + e.getMessage());
            return null;
        }
    }

    public static List<UserDto> getUserList(PortletRequest request) {
        try {
            if ( !checkAccess(request) ) {
                return new ArrayList<>();
            }

            return UserLocalServiceUtil.getUsers(-1, -1)
                    .stream()
                    .filter(user -> !"default@liferay.com".equals(user.getEmailAddress()))
                    .map(UserUtils::toDto)
                    .collect(Collectors.toList());
        } catch ( Exception e ) {
            log.error("Error while fetching users list: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static boolean checkAccess(PortletRequest request) {
        try {
            User currentUser = getCurrent(request);
            return ResourceAccessUtils.hasPermission(currentUser, User.class.getName(), "VIEW");
        } catch ( Exception e ) {
            log.error("Error while check access: " + e.getMessage());
            return false;
        }
    }

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getUserId());
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmailAddress());
        userDto.setPosition(user.getJobTitle());
        try {
            userDto.setBirthday(user.getBirthday());
            userDto.setPhones(PhoneServiceUtil.getPhones(Contact.class.getName(), user.getContactId()));
            userDto.setOrganizations(user.getOrganizations());
        } catch (PortalException e) {
            log.error("Error while converting to dto: " + e.getMessage());
        }
        return userDto;
    }

    public static String getPhones(UserDto userDto) {
        if ( userDto == null || userDto.getPhones() == null ) {
            return StringPool.BLANK;
        }
        return userDto.getPhones().stream()
                .map(Phone::getNumber)
                .collect(Collectors.joining(", "));
    }

    public static String getOrganizations(UserDto userDto) {
        if ( userDto == null || userDto.getOrganizations() == null ) {
            return StringPool.BLANK;
        }
        return userDto.getOrganizations().stream()
                .map(Organization::getName)
                .collect(Collectors.joining(", "));
    }

}

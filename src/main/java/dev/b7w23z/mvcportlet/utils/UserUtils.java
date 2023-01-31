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
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import dev.b7w23z.mvcportlet.dto.UserDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class UserUtils {

    private static final Log log = LogFactoryUtil.getLog(UserUtils.class);

    public static UserDto getUser(Long userId) {
        try {
            if ( userId == null ) {
                return null;
            }
            return toDto(UserLocalServiceUtil.getUser(userId));
        } catch ( Exception e ) {
            log.error("Error while fetching user: " + e.getMessage());
            return null;
        }
    }

    public static List<UserDto> getUserList() {
        try {
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

package dev.b7w23z.mvcportlet.dto;

import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Phone;
import dev.b7w23z.mvcportlet.utils.DateUtils;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {

    private long id;
    private String fullName;
    private String email;
    private String position;
    private Date birthday;
    private List<Phone> phones;
    private List<Organization> organizations;

    public String getBirthdayString() {
        return DateUtils.format(birthday);
    }

}

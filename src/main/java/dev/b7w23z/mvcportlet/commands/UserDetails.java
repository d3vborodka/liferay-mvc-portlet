package dev.b7w23z.mvcportlet.commands;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ParamUtil;
import dev.b7w23z.mvcportlet.constants.UsersMvcPortletKeys;
import dev.b7w23z.mvcportlet.dto.UserDto;
import dev.b7w23z.mvcportlet.utils.UserUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + UsersMvcPortletKeys.USERSMVC,
                "mvc.command.name=" + UsersMvcPortletKeys.USERS_USER_DETAIL_COMMAND
        },
        service = MVCRenderCommand.class
)
public class UserDetails implements MVCRenderCommand {

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        Long userId = ParamUtil.getLong(renderRequest, "userId");
        if ( userId != null ) {
            renderRequest.setAttribute("_user", UserUtils.getUser(userId));
        }

        return UsersMvcPortletKeys.USERS_USER_DETAIL_VIEW;
    }

}

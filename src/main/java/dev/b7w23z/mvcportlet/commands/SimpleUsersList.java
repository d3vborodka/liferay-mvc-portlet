package dev.b7w23z.mvcportlet.commands;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import dev.b7w23z.mvcportlet.constants.UsersMvcPortletKeys;
import dev.b7w23z.mvcportlet.utils.UserUtils;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + UsersMvcPortletKeys.USERSMVC,
                "mvc.command.name=" + UsersMvcPortletKeys.USERS_LIST_SIMPLE_COMMAND
        },
        service = MVCRenderCommand.class
)
public class SimpleUsersList implements MVCRenderCommand {

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        renderRequest.setAttribute("users", UserUtils.getUserList());

        return UsersMvcPortletKeys.USERS_LIST_SIMPLE_VIEW;
    }

}

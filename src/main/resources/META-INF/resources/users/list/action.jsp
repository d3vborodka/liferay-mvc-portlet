<%@ include file="/init.jsp" %>

<%@ page import="com.liferay.taglib.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="dev.b7w23z.mvcportlet.dto.UserDto" %>
<%@ page import="dev.b7w23z.mvcportlet.constants.UsersMvcPortletKeys" %>

<%
    ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
    UserDto userDto = (UserDto) row.getObject();
%>

<portlet:renderURL var="userDetailsUrl">
    <portlet:param name="mvcRenderCommandName" value="<%= UsersMvcPortletKeys.USERS_USER_DETAIL_COMMAND %>" />
    <portlet:param name="userId" value="<%= String.valueOf(userDto.getId()) %>"/>
</portlet:renderURL>

<a href="<%= userDetailsUrl %>"><c:out value="<%= userDto.getFullName()  %>"/></a>
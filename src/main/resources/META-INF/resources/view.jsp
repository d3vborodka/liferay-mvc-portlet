<%@ page import="dev.b7w23z.mvcportlet.constants.UsersMvcPortletKeys" %>
<%@ include file="/init.jsp" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<aui:button-row>
	<portlet:renderURL var="simpleUserListURL">
		<portlet:param name="mvcRenderCommandName" value="<%= UsersMvcPortletKeys.USERS_LIST_SIMPLE_COMMAND %>" />
	</portlet:renderURL>
	<portlet:renderURL var="advancedUserListURL">
		<portlet:param name="mvcRenderCommandName" value="<%= UsersMvcPortletKeys.USERS_LIST_ADVANCED_COMMAND %>" />
	</portlet:renderURL>
	<portlet:renderURL var="masterDetailUserListURL">
		<portlet:param name="mvcRenderCommandName" value="<%= UsersMvcPortletKeys.USERS_LIST_MASTER_DETAIL_COMMAND %>" />
	</portlet:renderURL>

	<aui:button href="<%= simpleUserListURL %>" value="Список" />
	<aui:button href="<%= advancedUserListURL %>" value="Расширенный список" />
	<aui:button href="<%= masterDetailUserListURL %>" value="Master-Detail" />
</aui:button-row>
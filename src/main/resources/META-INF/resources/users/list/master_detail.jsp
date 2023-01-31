<%@ include file="/init.jsp" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<liferay-ui:search-container total="${fn:length(users)}">
    <liferay-ui:search-container-results results="${users}"/>
    <liferay-ui:search-container-row
            className="dev.b7w23z.mvcportlet.dto.UserDto"
            escapedModel="true"
            modelVar="user"
            keyProperty="id"
    >
        <liferay-ui:search-container-column-jsp
                name="Пользователь"
                path="/users/list/action.jsp"
        />
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>
<%@ include file="/init.jsp" %>

<%@ page import="dev.b7w23z.mvcportlet.utils.UserUtils" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<liferay-ui:search-container total="${fn:length(users)}">
    <liferay-ui:search-container-results results="${users}"/>
    <liferay-ui:search-container-row
            className="dev.b7w23z.mvcportlet.dto.UserDto"
            escapedModel="true"
            modelVar="_user"
            keyProperty="id"
    >
        <liferay-ui:search-container-column-text name="ID" align="center" property="id"/>
        <liferay-ui:search-container-column-text name="ФИО" property="fullName"/>
        <liferay-ui:search-container-column-text name="Email" property="email"/>
        <liferay-ui:search-container-column-text name="Должность" property="position"/>
        <liferay-ui:search-container-column-text name="День рождения" property="birthdayString"/>
        <liferay-ui:search-container-column-text
                name="Список телефонов"
                value="<%= UserUtils.getPhones(_user) %>"
        />
        <liferay-ui:search-container-column-text
                name="Организации"
                value="<%= UserUtils.getOrganizations(_user) %>"
        />
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>
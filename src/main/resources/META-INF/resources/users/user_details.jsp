<%@ include file="/init.jsp" %>

<%@ page import="dev.b7w23z.mvcportlet.utils.UserUtils" %>
<%@ page import="dev.b7w23z.mvcportlet.dto.UserDto" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<aui:fieldset label="Информация о пользователе">
    <aui:row cssClass="user-info-row">
        <aui:col width="30">ID</aui:col>
        <aui:col width="70">
            <c:out value="${_user.fullName}"/>
        </aui:col>
    </aui:row>

    <aui:row cssClass="user-info-row">
        <aui:col width="30">ФИО</aui:col>
        <aui:col width="70">
            <c:out value="${_user.fullName}"/>
        </aui:col>
    </aui:row>

    <aui:row cssClass="user-info-row">
        <aui:col width="30">Email</aui:col>
        <aui:col width="70">
            <c:out value="${_user.email}"/>
        </aui:col>
    </aui:row>

    <aui:row cssClass="user-info-row">
        <aui:col width="30">Должность</aui:col>
        <aui:col width="70">
            <c:out value="${_user.position}"/>
        </aui:col>
    </aui:row>

    <aui:row cssClass="user-info-row">
        <aui:col width="30">День рождения</aui:col>
        <aui:col width="70">
            <c:out value="${_user.birthdayString}"/>
        </aui:col>
    </aui:row>

    <aui:row cssClass="user-info-row">
        <aui:col width="30">Список телефонов</aui:col>
        <aui:col width="70">
            <c:out value="<%= UserUtils.getPhones((UserDto) request.getAttribute("_user")) %>"/>
        </aui:col>
    </aui:row>

    <aui:row cssClass="user-info-row">
        <aui:col width="30">Организации</aui:col>
        <aui:col width="70">
            <c:out value="<%= UserUtils.getOrganizations((UserDto) request.getAttribute("_user")) %>"/>
        </aui:col>
    </aui:row>
</aui:fieldset>
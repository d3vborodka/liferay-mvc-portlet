<%@ include file="/init.jsp" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="table-responsive">
    <table class="table table-autofit table-heading-nowrap table-list">
        <thead>
        <tr>
            <th class="table-column-text-center">ID</th>
            <th class="table-column-text-center">ФИО</th>
            <th class="table-column-text-center">Email</th>
            <th class="table-column-text-center">Должность</th>
            <th class="table-column-text-center">День рождения</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="_user">
            <tr class="table-title">
                <td class="table-column-text-center" colspan="1">
                    <c:out value="${_user.id}"/>
                </td>
                <td class="table-column-text-center" colspan="1">
                    <c:out value="${_user.fullName}"/>
                </td>
                <td class="table-column-text-center" colspan="1">
                    <c:out value="${_user.email}"/>
                </td>
                <td class="table-column-text-center" colspan="1">
                    <c:out default="empty" value="${_user.position}"/>
                </td>
                <td class="table-column-text-center" colspan="1">
                    <fmt:formatDate pattern="dd-MM-yyyy г." value="${_user.birthday}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
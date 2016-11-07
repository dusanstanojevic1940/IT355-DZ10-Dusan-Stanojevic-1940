<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="header.jsp" %>

<h2>${title}</h2>

<form:form method="POST" action="contact">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <table>
        <tr>
            <td><form:label path="name"><spring:message code="name"></spring:message></form:label></td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td><form:label path="number"><spring:message code="number"></spring:message></form:label></td>
            <td><form:input path="number" /></td>
        </tr>
        <tr>
            <td>
                <input type="submit"/>
            </td>
        </tr>
    </table>


    <table>
        <thead>
            <td><spring:message code="name"></spring:message></td>
            <td><spring:message code="number"></spring:message></td>
        </thead>
        <tbody>
            <c:forEach items="${contactList}" var="contact">
                <tr>
                    <td>${contact.name}</td>
                    <td>${contact.number}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</form:form>

<%@ include file="footer.jsp" %>
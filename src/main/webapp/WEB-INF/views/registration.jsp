<%@ page contentType="text/html;charset=UTF-8"
         language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>account</title>
</head>
<body>
    <h1>account</h1>
    <h2>регистрация пользователя</h2>
    <%--@elvariable id="formUser" type="vdm.controllers.hespsOfControllers.FormUser"--%>
    <form:form  modelAttribute="formUser"  action="./new" method='POST'>
        <table>
            <tr>
                <td>name:</td>
                <td><form:input path="name"/></td>
                <td><form:errors path="name" /></td>
            </tr>
            <tr>
                <td>lastName:</td>
                <td><form:input path="lastName" /></td>
                <td><form:errors path="lastName" /></td>
            </tr>
            <tr>
                <td>email:</td>
                <td><form:input path="email"/></td>
                <td><form:errors path="email" /></td>
            </tr>
            <tr>
                <td>password:</td>
                <td><form:input path="password" /></td>
                <td><form:errors path="password" /></td>
            </tr>
            <tr>
                <td>role:</td>
                <td><form:radiobutton path="role" value="ROLE_DEVELOPER" checked="ROLE_DEVELOPER"/>developer</td>
                <td><form:radiobutton path="role" value="ROLE_MANAGER"/>manager</td>
                <td><form:radiobutton path="role" value="ROLE_ADMIN"/>admin</td>
            </tr>

            <tr>
                <td><input name="submit" type="submit" value="submit" /></td>

            </tr>
        </table>
       <h2>${activate}</h2>
    </form:form>
</body>
</html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head></head>
<body>
<h1>Login</h1>
    <div id="error">
        <c:if test="${not empty param.error}">
        <h3 style="font-size:20px; color:#FF1C19;">Неправильный логин или пароль</h3>
        </c:if>
    </div>
<h2></h2>
<form name='f' action="/taskTracker/j_spring_security_check" method='POST'>
    <table>
        <tr>
            <td>email of user:</td>
            <td><input type='text' name='j_username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='j_password' /></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>
    <br/><br/>
    <div id="account"><a href="/taskTracker/registration/">go to registration</a> </div>
</form>
</body>
</html>
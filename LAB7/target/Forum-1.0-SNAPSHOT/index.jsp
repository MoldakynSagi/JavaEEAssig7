<% // Use of PersonBean in a JSP. %>
<jsp:useBean id="person" class="kz.javaee.demo.PersonBean" scope="page"/>
<jsp:setProperty name="person" property="*"/>
<html>
<body>
Name: <jsp:getProperty name="person" property="name"/>
<br/> Deceased? <jsp:getProperty name="person" property="deceased"/><br/> <br/>
<form name="beanTest" method="POST" action="TestPersonBean"> Enter a name: <input type="text" name="name" size="50"><br/> Choose an option:
<select name="deceased">
    <option value="false">Alive</option>
    <option value="true">Dead</option> </select>
<input type="submit" value="Test the Bean">
</form>
</body> </html>
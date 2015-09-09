<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:query var="rs" dataSource="jdbc/postgres">
select id, foo, bar from testdata
</sql:query>


<html>
<body>
<h2>Hello World!</h2>
  fu fu fu 3
  <%= Class.forName("org.postgresql.Driver").toString()%>
  
  <c:forEach var="row" items="${rs.rows}">
    Foo ${row.foo}<br/>
    Bar ${row.bar}<br/>
</c:forEach>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

<script>
	var msg = "${msg}";
	if(msg) {
		alert(msg);
	}
</script>
	home 화면 
</body>
</html>

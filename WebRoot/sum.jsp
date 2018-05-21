<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html> 
	<head>
		<title>JSP for HelloForm form</title>
		<style type="text/css">body {font-size: 12px; }</style>
	</head>
	<body>
	
		您好，${ UserForm.name }.  <br/><br/>
		
		<%=request.getAttribute("name") %>

	</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>BBS后台管理系统</title>
		<style type="text/css">body {font-size: 12px; }</style>
		<link rel="stylesheet" href="css/style.css" />
	</head>
	<%
	String tip="";
	try{
	 tip = request.getAttribute("tip").toString();
	}catch(NullPointerException e){
	}
	
	
	 %>
	<body background="picture/body.jpg">
	<div class="name">
	<h1 align="center">BBS后台管理系统</h1>
	</div>
	<div class="ddd" align="center">
	<h3 align="center" style=" color: crimson;"><%=tip%></h3>
		<html:form action="/login?action=login">
			姓名: <html:text property="name"/><br/>
			密码: <html:text property="password" /><br/>	
			<div class="all">
			<html:submit value="登录"/>
			<input type="reset" value="取消"/>
            </div>
		</html:form>
		</div>
	</body>
</html>

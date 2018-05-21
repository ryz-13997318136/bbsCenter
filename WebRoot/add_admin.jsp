<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'a_check_my_info.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/style.css" />
	<%
	String tip = (String)request.getAttribute("tip");
	if(tip==null){
	tip="";
	}
	 %>
	
	<script>
	function check(){
	if(document.form1.password_one.value!=document.form1.password_two.value){
	alert("两次输入的密码不一致···");
	return;
	}
	if(document.form1.name.value==""||document.form1.sex.value==""||
	   document.form1.age.value==""||
	    document.form1.password_one.value==""||document.form1.password_two.value==""){
	alert("填写信息不完整,请仔细检查···");
	return;
	}
	
	//检查结束，跳转页面
	form1.action='login.do?action=add';
    form1.submit();
	}

	</script>

  </head>
  
  <body>
  
  
  
   <form name="form1" action="" method="post" align="center" style="margin-top: 135px;">
   
   &nbsp;&nbsp;登录名：<input type="text" name="name"/><br/>
   &nbsp;&nbsp;密&nbsp;&nbsp;码：<input type="password" name="password_one"/><br/>
   确认密码：<input type="password" name="password_two"/><br/>
   &nbsp;&nbsp;年&nbsp;&nbsp;龄：<input type="text" name="age"/><br/>
   性&nbsp;&nbsp;别：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" checked="checked" value="0"/>男
       &nbsp;<input type="radio" name="sex" value="1"/>女<br/>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="提交" onclick="check()"/>
       <input type="reset" value="重置"/>
       
       <%=tip %>
   
   
   </form>
  
  </body>
</html>

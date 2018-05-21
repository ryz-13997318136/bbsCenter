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
List list = (List)request.getAttribute("list");
 %>
	<script>
	function edit(){
	var obj1 = document.getElementById('user_name');
	var obj2 = document.getElementById('user_password');
	var obj3 = document.getElementById('user_age');
	var obj4 = document.getElementById('user_sex');
	 obj1.readOnly = false;
	 obj2.readOnly = false;
	 obj3.readOnly = false;

	 var b1 = document.getElementById('ok');
	 var b2 = document.getElementById('cancel');
	 b1.style.display="block";
	 b2.style.display="block";
	}
	function reset(){
	
	document.getElementById('user_name').value='';
	document.getElementById('user_password').value='';
	 document.getElementById('user_age').value='';
	document.getElementById('user_sex').value='';
	}
	function Setsex(){

	var obj = document.getElementById("sex");
	
		obj.value = <%=list.get(3)%>;
		
       
	}
	</script>

  </head>
  
  <body onload="Setsex()">
    <div class="aaa">
    <div class="bbb">
  <input type="button" name="edit" value="编辑" onclick="edit()">
  </div>
  
  <form name="form" action="login.do?action=save" method="post">

  <table class="info">
    <%
    
    out.println("<tr><td>姓名：</td><td><input type=text readonly= 'true' name='user_name' id='user_name' value="+list.get(0)+"></td></tr>"+
    "<tr><td>登录密码：</td><td><input type=text readonly= 'true' name='user_password' id='user_password' value="+list.get(1)+"></td></tr>"+
    "<tr><td>年龄：</td><td><input type=text readonly= 'true' name='user_age' id='user_age' value="+list.get(2)+"></td></tr>"+
    "<tr><td>性别 ：</td><td> <select name='user_sex' id='sex'><option value='0'>男</option><option value='1'>女</option></select></td></tr>");
     %>
    
     </table>
     <input type="submit" style="display: none" name="ok" id="ok" value="提交"/>
     
     </form>
     <div class="ccc">
     <input type="reset" style="display: none" name="cancel" id="cancel" value="重置" onclick="reset()"/>
     </div>
     </div>
  </body>
</html>

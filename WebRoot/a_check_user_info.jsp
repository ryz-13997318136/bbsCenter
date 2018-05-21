<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="bean.Person" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'a_user_info.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  
    
<table id="mainpage" width="100%" border="0" cellspacing="0" cellpadding="0">

  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="62" background="../images/nav04.gif">
          
		   <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
			<td width="21"><img src="picture/ico07.gif" width="20" height="18" /></td>
			<td width="550">查找用户：
			<form name="fom" id="fom" method="post" action="person.do?action=search">
			 姓名：<input name="name" type="text"  size="12" /><span class="newfont06">年龄:</span>
			 <input name="age" type="text" size="12" /><span class="newfont06">手机号码:</span>
			 <input name="mobile" type="text" size="12" /><span class="newfont06">性别:</span>	
			 <input name="sex" type="text" size="12" />
			 <input name="Submit" type="submit"  value="查 询" />
			 </form>
			 </td>
			 	
		  </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table id="subtree1" style="DISPLAY: " width="100%" border="0" cellspacing="0" cellpadding="0">

        <tr>
          <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">

              <tr>
                <td height="40" class="font42"><table width="100%" border="0" cellpadding="4" cellspacing="1"  class="newfont03">
                  <tr>
                    <td height="20" colspan="14" align="center" bgcolor="#EEEEEE"class="tablestyle_title">用户信息列表 </td>
                  </tr>
                  <tr>
                    <td width="7%" align="center" bgcolor="#EEEEEE">用户ID</td>
                    <td width="7%" align="center" bgcolor="#EEEEEE">登录密码</td>
                    <td width="9%" height="20" align="center" bgcolor="#EEEEEE">姓名</td>
                    <td width="5%" align="center" bgcolor="#EEEEEE">年龄</td>
                    <td width="4%" align="center" bgcolor="#EEEEEE">性别</td>
                    <td width="8%" align="center" bgcolor="#EEEEEE">电话号码</td>
                    <td width="50%" align="center" bgcolor="#EEEEEE">个性签名</td>                  
                    <td width="2%" align="center" bgcolor="#EEEEEE">头像</td>
                    <td width="5%" align="center" bgcolor="#EEEEEE">操作</td>
                  </tr>
                 
                  <%
                    ArrayList<Person> list = (ArrayList<Person>)request.getAttribute("person_list");
                     for(Person person : list){
                      out.println("<tr >"
                        +"<td>"+person.getUser_id()+"</td>"
                        +"<td >"+person.getUser_password()+"</td>"
                        +"<td >"+person.getUser_name()+"</td>"               
                        +"<td >"+person.getUser_age()+"</td>"
                        +"<td >"+person.getUser_sex()+"</td>"
                        +"<td >"+person.getUser_phonenumber()+"</td>"
                        +"<td >"+person.getUser_signature()+"</td>"
                        +"<td ><img src='http://localhost:8080/bbs/image/"+person.getUser_imageId()+"' height='40' width='40'/></td>"
                        +"<td align='center'><a href=person.do?action=delete&&person_id="+person.getUser_id()+">删除</a></td>"                                                                             
                        +"</tr>");
                  }                  
                 
                 
                 
                  %>
                 
                 
                 
                </table></td>
              </tr>
            </table></td>
        </tr>
      </table>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="6"><img src="../images/spacer.gif" width="1" height="1" /></td>
        </tr>
        <tr>
          <td height="33"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="right-font08">
              <tr>
                
                <td width="49%" align="right">[<a href="person.do?action=check" class="right-font08">首页</a> | <a href="person.do?action=shang" class="right-font08">上一页</a> | <a href="person.do?action=xia" class="right-font08">下一页</a> ] </td>
                <td width="1%"><table width="20" border="0" cellspacing="0" cellpadding="0">
                  
                </table></td>
              </tr>
          </table></td>
        </tr>
      </table></td>
  </tr>
</table>


<div id="loadingmsg" style="width:100px; height:18px; top:0px; display:none;">
	<img src="file:///F|/项目管理相关资料/项目管理系统页面/images/loadon.gif" />
</div>
  </body>
</html>

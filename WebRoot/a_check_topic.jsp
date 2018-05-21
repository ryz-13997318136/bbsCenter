<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="bean.Topic" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'a_check_topic.jsp' starting page</title>
    
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
			<td width="550">查找内容
			<form name="form3" action="topic.do?action=search" method="post">
			 发帖人:<input name="topic_user" type="text" size="12" /><span class="newfont06">帖子标题:</span>
			 <input name="topic_name" type="text" size="12"/>	
			 <input name="Submit" type="submit" class="right-button02" value="查 询" />
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
              
          	 </tr>
              <tr>
                <td height="40" class="font42"><table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#AEEEEA" class="newfont03">
                  <tr>
                    <td height="20" colspan="14" align="center" bgcolor="#EEEEEE"class="tablestyle_title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 接收信息列表 &nbsp;</td>
                  </tr>
                  <tr>
                    <td width="5%" align="center" bgcolor="#EEEEEE">帖子ID</td>
                    <td width="5%" height="20" align="center" bgcolor="#EEEEEE">帖子标题</td>
                    <td width="5%" align="center" bgcolor="#EEEEEE">发帖人</td>
                    <td width="5%" align="center" bgcolor="#EEEEEE">发帖人ID</td>
                    <td width="68%" align="center" bgcolor="#EEEEEE">帖子内容</td>
                      <td width="8%" align="center" bgcolor="#EEEEEE">发表时间</td>
                    <td width="4%" align="center" bgcolor="#EEEEEE">操作</td>
                  </tr>
                   <%
                    ArrayList<Topic> list = (ArrayList<Topic>)request.getAttribute("topic_list");
                     for(Topic topic : list){
                      out.println("<tr >"
                        +"<td>"+topic.getTopic_id()+"</td>"
                        +"<td >"+topic.getTopic_name()+"</td>"
                        +"<td >"+topic.getUser_name()+"</td>"               
                        +"<td >"+topic.getUser_id()+"</td>"
                        +"<td >"+topic.getTopic_content()+"</td>"
                        +"<td >"+topic.getTopic_date()+"</td>"
                        +"<td align='center'><a href=topic.do?action=delete&&topic_id="+topic.getTopic_id()+">删除</a></td>"                                                                             
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
                <td width="50%">共 <span class="right-text09"><%=session.getAttribute("totle")%></span> 页 | 第 <span class="right-text09"><%=session.getAttribute("n")%></span> 页</td>
                <td width="49%" align="right">[<a href="topic.do?action=check" class="right-font08">首页</a> | <a href="topic.do?action=shang" class="right-font08">上一页</a> | <a href="topic.do?action=xia" class="right-font08">下一页</a></td>
                <td width="1%"><table width="20" border="0" cellspacing="0" cellpadding="0">
                 
                </table></td>
              </tr>
          </table></td>
        </tr>
      </table></td>
  </tr>
</table>

  </body>
</html>

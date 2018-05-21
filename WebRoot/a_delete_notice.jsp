<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="bean.Notice"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'a_delete_notice.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script>

	function sure() {
		var isSure = confirm("你要删除该条公告吗？");
		if (isSure) {
			return true;
		} else {

			return false;
		}
	}
</script>
  </head>
  
  <body>
   
    <form name="fom" id="fom" method="post" action="">
<table id="mainpage" width="100%" border="0" cellspacing="0" cellpadding="0">

  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="62" background="../images/nav04.gif">
          
		   <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
		
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table id="subtree1" style="DISPLAY: " width="100%" border="0" cellspacing="0" cellpadding="0">

        <tr>
          <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">

          	 
              <tr>
                <td height="40" class="font42"><table width="100%" border="1" cellpadding="4" cellspacing="1" bgcolor="#EEEEEE" class="newfont03">
                  <tr>
                    <td height="20" colspan="14" align="center" bgcolor="#EEEEEE"class="tablestyle_title"> 通知列表 </td>
                  </tr>
                  <tr>
                    <td width="20%" height="20" align="center" bgcolor="#EEEEEE">标题</td>
                    <td width="60%" align="center" bgcolor="#EEEEEE">内容</td>
                    <td width="10%" align="center" bgcolor="#EEEEEE">发布时间</td>
                    <td width="10%" align="center" bgcolor="#EEEEEE">操作</td>
                  </tr>
                 <%
                    ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("notice_list");
                     for(Notice notice : list){
                      out.println("<tr >"
                       +"<td>"+notice.getNotice_name()+"</td>"
                       +"<td >"+notice.getNotice_content()+"</td>"
                      
                       +"<td >"+notice.getNotice_date()+"</td>"
                       +"<td align='center'><a href=notice.do?action=delete&&notice_id="+notice.getNotice_id()+" onclick='return sure();'>删除</a></td>"                                                                             
                       +"</tr>");
                  }                  
                 
                 
                 
                  %>
                </table>
                </td>
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
               
               
                <td width="1%"><table width="20" border="0" cellspacing="0" cellpadding="0">
                  
                </table></td>
              </tr>
          </table></td>
        </tr>
      </table></td>
  </tr>
</table>
</form>

<div id="loadingmsg" style="width:100px; height:18px; top:0px; display:none;">
	<img src="file:///F|/项目管理相关资料/项目管理系统页面/images/loadon.gif" />
</div>
  </body>
</html>

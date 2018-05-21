<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="bean.advertisement"%>
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
		var isSure = confirm("你要删除该条广告吗？");
		if (isSure) {
			return true;
		} else {

			return false;
		}
	}
</script>
<style type="text/css">
.tupian{
}
.chakan{
    float: left;
}

</style>
  </head>
  
  <body>
   
 

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
                    <td height="20" colspan="14" align="center" bgcolor="#EEEEEE"class="tablestyle_title"> 广告列表 </td>
                  </tr>
                  <tr>
                    <td width="20%" height="20" align="center" bgcolor="#EEEEEE">广告ID</td>
                    <td width="60%" align="center" bgcolor="#EEEEEE">广告图标</td>
                    <td width="10%" align="center" bgcolor="#EEEEEE">链接地址</td>
                    <td width="10%" align="center" bgcolor="#EEEEEE">操作</td>
                  </tr>
                 <%
                    ArrayList<advertisement> list = (ArrayList<advertisement>)request.getAttribute("adv_list");
                     for(advertisement adv : list){
                      out.println("<tr >"
                       +"<td>"+adv.getId()+"</td>"
                       +"<td ><div><div class='tupian'><img src='./image/"+adv.getImageUrl()+"' height='50' width='50'/></div><div style='float:right;'><a href='./image/"+adv.getImageUrl()+"'>查看大图</a></div></div></td>"
                       +"<td ><a href=//"+adv.getAddress()+">"+adv.getAddress()+"</a></td>"
                       +"<td align='center'><a href=advertisement.do?action=delete&&adv_id="+adv.getId()+" onclick='return sure();'>删除</a></td>"                                                                             
                       +"</tr>");
                  }                  
                 
                 
                 
                  %>
                </table>
                </td>
              </tr>
            </table></td>
        </tr>
      </table>
  注意：本系统严格限制广告数量，最好保持在5个左右，不能超过8个  
 




  </body>
</html>

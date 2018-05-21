<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    
    <title>My JSP 'a_notice.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script>
  function check(){
  var name = document.form1.notice_name.value;
  var content = document.form1.notice_content.value;
  if(name==''||name==null){
  alert("请填写公告的标题！");
  return false;
  }
   if(name.length>=10){
  alert("公告标题长度不能大于10！");
  return false;
  }
  if(content==''||content==null){
  alert("请填写公告的内容！");
  return false;
  }
  if(content.length>=100){
  alert("公告内容内容不能超过100！");
  return false;
  }
  }
  </script>
  </head>
  
  <body class="ContentBody">

  <form action="notice.do?action=public" method="post"  name="form1"  >
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >新建公告</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		
		<tr>
			<td width="100%">
				<fieldset style="height:100%;">
				<legend>新建公告</legend>
					<table border="0" cellpadding="2" cellspacing="1" style="width:100%">
 
					  <tr>
					    <td nowrap align="right" width="11%">公告标题:</td>
					    <td width="27%"><input name='notice_name' type="text" class="text" style="width:300px" value="" /></td>
				        	
					    <td align="right" width="25%">&nbsp;</td>
					    <td width="37%">&nbsp;</td>
					  </tr>
  
					  <tr>
					    <td  width="11%" nowrap align="right">信息内容:</td>
					    <td colspan="3"><textarea name="notice_content" cols="100" rows="20"></textarea></td>
					  </tr>
					</table>
			       <br>
				</fieldset>			
				</td>			
		</tr>	
		</table>

	 </td>
  </tr>

		<tr>
			<Td colspan="2" align="center" height="50px">
			<input type="submit"  value="发布" class="button" onclick="return check()"/>　
			
			<input type="button"  value="返回" class="button" onclick="window.history.go(-1);"/></TD>
		</tr>
	</table>
	

</div>
</form>
  </body>
</html>

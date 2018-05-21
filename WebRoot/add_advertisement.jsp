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
		String info="";
			try{
			 info = request.getAttribute("tip").toString();
			   }catch(Exception e){
				
				info="";
				}
		%>
	 <script>
    function edit(){  
    if(check()==true&&CheckFileType()==true){
    form1.action='advertisement.do?action=add';
    form1.submit();
    }
   
    }   
    </script>
    <script type="text/javascript">
    function check(){
    if(document.form1.name.address==""||document.form1.file.value==""){
	 alert("修改信息不完整,请仔细检查···");
	return false;
	}
	
	
	else{
	return true;
	}
    }
    function CheckFileType()
     {
       var obj=document.getElementById('file');
        var stuff=obj.value.match(/^(.*)(.)(.{1,8})$/)[0]; //这个文件类型正则很有用：）    
       var a = stuff.lastIndexOf('.');       
        var b = stuff.substring(a+1).toLowerCase();     
        if(b!='png'&& b!='jpg')
        {
           alert("上传的图片资源格式不正确！");
           return false;
        }
        return true;
     }
    </script>

  </head>
  
  <body>
  <div><%=info%></div>
  <form name="form1" action="" method="post" enctype="multipart/form-data">
      <table align="center" border="1">
        
       <tr>
           <td align="center">链接地址:</td>
           <td align="center"><textarea rows="2" cols="20" name="address"></textarea></td>
       </tr>
       <tr>
           <td align="center">广告图片:</td>
           <td align="center"><input type="file" id="file" size="20" name="file"/></td>
           
       </tr>
     <tr>
       <td align="center"><input type="button"  value="发布" onclick="edit()"/></td>
       <td align="center"><input type="reset"  value="重置" /></td>
    </tr>
      
      </table>
      </form>     
 <div>注意 ：上传的图片格式只能是png和JPG。
             广告URL最好不要包含中文字符。</div> 
  
  </body>
</html>

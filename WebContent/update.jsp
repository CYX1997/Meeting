<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<title>CoolMeeting会议管理系统</title>
<script type="text/javascript">
	function update(){
		var flag1=1;
		var pass = document.getElementById("pwd").value;
		var repass = document.getElementById("repwd").value;
		var reg = /^[u4E00-u9FA5]+$/;
		if (pass == null || pass == "") {
			flag1 = 2;
			document.getElementById("pwdmsg").innerHTML = "密码不能为空";
		}
		else if (pass.length>20) {
			flag1 = 2;
			document.getElementById("pwdmsg").innerHTML = "密码长度过长，请重新输入";
		}
		else if(pass!=repass){
			flag1 = 2;
			document.getElementById("repwdmsg").innerHTML = "两次输入的密码不相同";
		} 
		else if(!reg.test(pass)){
			flag1 = 2;
			document.getElementById("pwdmsg").innerHTML = "密码不能为中文";
		}
		if(flag1==1){
			form1.action="UpdateServlet?employeeid=${sessionScope.employeeid }";
			form1.submit();
		}
		
	}

</script>
<link rel="stylesheet" href="styles/common.css" />
</head>
<body>
	
	<div class="page-content">
		<div class="content-nav">修改</div>
		<form  method="post" id="form1" name="form1">
			<fieldset>
				<legend>修改信息</legend>
				<table class="formtable" style="width: 50%">
					<%
					String msg = (String) request.getAttribute("msg");
					if (msg != null) {
				%><tr>
						<td>提示信息:</td>
						
					<td><font color='red'> ${requestScope.msg}</font></td>
					</tr>
					<%
						}
					%>
					<tr>
						<td>输入要修改的密码:</td>
						<td><input id="pwd" name="pwd" type="password" maxlegth="20"/>
						<div id="pwdmsg"></div></td>
					</tr>
					<tr>
						<td>确认密码:</td>
						<td><input id="repwd" name="repwd" type="password"  maxlegth="20" />
						<div id="repwdmsg"></div></td>
					</tr>
					<tr>
						<td colspan="2" class="command"><input type="button" value="确认修改" class="clickbutton"
						 onclick="update()" /> 
						<a href="adminindex.jsp"><input type="button" value="返回" class="clickbutton" /></a> </td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>

</body>
</html>
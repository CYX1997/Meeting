<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>CoolMeeting会议管理系统</title>
<link rel="stylesheet" href="styles/common.css" />
<style type="text/css">
</style>
<script type="text/javascript">
	var xmlHttp;
	
	function createXMLHttpRequest() {
	    if (window.ActiveXObject) {
	        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	    } 
	    else if (window.XMLHttpRequest) {
	        xmlHttp = new XMLHttpRequest();                
	    }
	}
	
	function validate() {
	    createXMLHttpRequest(); 
	    var username = document.getElementById("username");
	    var url = "ValidateUsernameServlet?username=" + escape(username.value);           
	    xmlHttp.open("GET", url, true);
	    xmlHttp.onreadystatechange = callback;
	    xmlHttp.send(null);
	}
	
	function callback() {
	    if (xmlHttp.readyState == 4) {
	        if (xmlHttp.status == 200) {
	            var message = xmlHttp.responseXML.getElementsByTagName("message")[0].firstChild.data;
	            var passed = xmlHttp.responseXML.getElementsByTagName("passed")[0].firstChild.data;
	            setMessage(message, passed);
	        }
	    }
	}
	
	function setMessage(message, passed) {            
	    var validateMessage = document.getElementById("validateMessage");
	    var fontColor = "red";
	    if (passed == "true") {
	        fontColor = "green";                
	    }
	    validateMessage.innerHTML = "<font color=" + fontColor + ">" + message + " </font>";
	}
	
	function check() {
	if (form1.firstpassword.value!=form1.secondpassword.value) {
		confirminfo.innerHTML = "<font color=red>两次输入的密码不相符</font>";
	}else{
		confirminfo.innerHTML="<font color=green>两次输入的密码相符</font>";
	}
	}
	function show() {
		var flag1 = 1;
		var empname = document.getElementById("employeename").value;
		var name = document.getElementById("username").value;
		var pass = document.getElementById("pwd").value;
		var repass = document.getElementById("repwd").value;
		var phone = document.getElementById("phone").value;
		var email = document.getElementById("email").value;
		var deptid = document.getElementById("deptid").value;
		var reg = /^[u4E00-u9FA5]+$/;
		if (empname == "" || empname == null) {
			flag1 = 2;
			document.getElementById("empmsg").innerHTML = "用户名不能为空";
		}
		if(empname.length>20){
			flag1 = 2;
			document.getElementById("empmsg").innerHTML = "用户名过长，请重新输入";
		}
		if (name == "" || name == null) {
			flag1 = 2;
			document.getElementById("usermsg").innerHTML = "用户名不能为空";
		}
		if(name.length>20){
			flag1 = 2;
			document.getElementById("usermsg").innerHTML = "用户名过长，请重新输入";
		}
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
		if (phone == null || phone == "") {
			flag1 = 2;
			document.getElementById("phmsg").innerHTML = "电话不能为空";
		}
		if (phone.length>12 || phone.length<10) {
			flag1 = 2;
			document.getElementById("phmsg").innerHTML = "电话号码格式有误";
		}
	 	else if(!reg.test(phone)){
			flag1 = 2;
			document.getElementById("phmsg").innerHTML = "电话号码不能为中文";
		}		 
		if (email == null || email == "") {
			flag1 = 2;
			document.getElementById("emmsg").innerHTML = "邮箱不能为空";
		}
		if (email.length>20) {
			flag1 = 2;
			document.getElementById("emmsg").innerHTML = "邮箱格式错误";
		}
		
		if (flag1==1) {
			alert(11);
			form1.action ="RegistServlet";
			form1.submit();
		}
	
	}

	
</script>

</head>
<body>
	<div class="page-header">
		<div class="header-banner">
			<img src="images/header.png" alt="CoolMeeting" />
		</div>
		<div class="header-title">欢迎访问Cool-Meeting会议管理系统</div>
	</div>
	<div class="page-content">
		<div class="content-nav">人员管理 > 员工注册</div>
		<form name="form1" method="post">
			<fieldset>
				<legend>员工信息</legend>

				<tr>
					<td>提示信息:</td>
					<td><font color='red'>${requestScope.msg}</font></td>
				</tr>

				<table class="formtable" style="width: 50%">
					<tr>
						<td>姓名：</td>

						<td><input type="text" id="employeename" name="employeename" 
						maxlength="20" value="${param.employeename}" maxlength="10" >
							<div id="empmsg"></div></td>
					</tr>
					<tr>
						<td>账户名：</td>

						<td><input type="text" id="username" name="username" maxlength="20" value="${param.username}"
						 onchange="validate()"  maxlength="10" onchange="selectUser()">
							<div id="validateMessage"></div>
							<div id="usermsg"></div></td>
					</tr>
					<tr>
						<td>密码：</td>
						<td><input type="password" id="pwd" name="password" maxlength="20" 
						placeholder="请输入密码"  >
							<div id="pwdmsg"></div></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input type="password" id="repwd" name="password1" maxlength="20"
						 onchange="check()" />
							<div id="repwdmsg"></div></td>
					</tr>
					<tr>
						<td>联系电话：</td>

						<td><input type="text" id="phone" name="phone" maxlength="20"
						 value="${param.phone}" >
							<div id="phmsg"></div></td>
					</tr>
					<tr>
						<td>电子邮件：</td>

						<td><input type="text" id="email" name="email" maxlength="20" 
						value="${param.email}" >
							<div id="emmsg"></div></td>
					</tr>

					<tr>
						<td>所在部门：</td>
						<td><select name="deptid" id="deptid">
								<c:forEach var="department" items="${requestScope.departmentsList}">
									<c:if test="${department.departmentid== param.deptid}">
										<option value="${department.departmentid}" selected>${department.departmentname}</option>
									</c:if>
									<c:if test="${department.departmentid!= param.deptid}">
										<option value="${department.departmentid}">${department.departmentname}</option>
									</c:if>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td colspan="6" class="command"><input type="button" class="clickbutton" value="注册" onclick="show()" /> <input type="reset"
							class="clickbutton" value="重置" /></td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	</div>
	<div class="page-footer">
		<hr />
		更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a> <img src="images/footer.png" alt="CoolMeeting" />
	</div>
</body>
</html>
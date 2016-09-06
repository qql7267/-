<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
欢迎 <b>${sessionScope.user.name}</b> 登录成功<br><br>
<a href="showInfo.jsp">显示个人信息</a><br><br>
<a href="getAllAction">管理员工信息</a><br><br>
<a href="getAllDeptAction">添加员工</a>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" width="100%">
	<tr>
		<th>编号</th>
		<th>姓名</th>
		<th>职位</th>
		<th>管理者编号</th>
		<th>入职日期</th>
		<th>工资</th>
		<th>奖金</th>
		<th>部门名称</th>
		<th>操作</th>
	</tr>
	
	<c:forEach items="${requestScope.listEmp}" var="emp">
		<tr>
		<td>${emp.empno}</td>
		<td>${emp.ename}</td>
		<td>${emp.job}</td>
		<td>${emp.mgr}</td>
		<td>${emp.hireDate}</td>
		<td>${emp.sal}</td>
		<td>${emp.comm}</td>
		<td>${emp.dept.dname}</td>
		<td>
		<a href="goUpdateEmpAction?empno=${emp.empno}">编辑 </a>
		<a href="deleteEmpAction?empno=${emp.empno}">删除</a></td>
	</tr>
	</c:forEach>
	
</table>
</body>
</html>
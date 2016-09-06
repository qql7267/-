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
<form action="updateEmpAction" method = "post">
<table>
<tr>
	<td>编号：</td><td><input type="text" name="empno" value="${emp.empno}" readonly/></td>
</tr>
<tr>
	<td>姓名：</td><td><input type="text" name="ename" value="${emp.ename}"/></td>
</tr>
<tr>
	<td>职位：</td><td><input type="text" name="job" value="${emp.job}"/></td>
</tr>
<tr>
	<td>管理者：</td>

	<td>
	<select name="mgr">
		<c:forEach items="${requestScope.listEmp}" var="emp1">
		<option value="${emp1.mgr}" ${emp1.mgr==emp.mgr?"selected":""}>${emp1.mgr}</option>
		</c:forEach>
	</select>
	</td>
</tr>
<tr>
	<td>入职日期：</td><td><input type="text" name="hireDate" value="${emp.hireDate}"/>(1999-02-09)</td>
</tr>
<tr>
	<td>工资：</td><td><input type="text" name="sal" value="${emp.sal}"/></td>
</tr>
<tr>
	<td>奖金：</td><td><input type="text" name="comm" value="${emp.comm}"/></td>
</tr>
<tr>
	<td>部门：</td>
	<td>
	<select name="deptName">
		<c:forEach items="${requestScope.listDept}" var="dept">
		<option value="${dept.deptno}" ${dept.deptno==emp.dept.deptno?"selected":""}>${dept.dname}</option>
		</c:forEach>
	</select>
	</td>
</tr>
<tr>
	<td>&nbsp;</td><td><input type="submit" value="修改"/></td>
</tr>
</table>
</form>

</body>
</html>
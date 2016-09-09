<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>CoolMeeting会议管理系统</title>
		<link rel="stylesheet" href="styles/common.css" />
	</head>
	<body>
		<div class="page-header">
			<div class="header-banner"><img src="images/header.png" alt="CoolMeeting" /></div>
			<div class="header-title">欢迎访问Cool-Meeting会议管理系统</div>
			<div class="header-quicklink">请登录</div>
		</div>
		<div class="page-body">
			<div class="page-sidebar">
				<div class="sidebar-menugroup">
					<div class="sidebar-grouptitle">个人中心</div>
					<ul class="sidebar-menu">
						<li class="sidebar-menuitem"><a href="NotificationsAction">最新通知</a></li>
						<li class="sidebar-menuitem active"><a href="MyBookMeetingAction">我的预定</a></li>
						<li class="sidebar-menuitem"><a href="MyMeetingAction">我的会议</a></li>
					</ul>
				</div>
				<div class="sidebar-menugroup">
					<div class="sidebar-grouptitle">人员管理</div>
					<ul class="sidebar-menu">
						<li class="sidebar-menuitem"><a href="GetAllDeptAction">部门管理</a></li>
						<li class="sidebar-menuitem"><a href="RegisterDeptActoin">员工注册</a></li>
						<li class="sidebar-menuitem"><a href="ApproveListAction">注册审批</a></li>
						<li class="sidebar-menuitem"><a href="SearchAction">搜索员工</a></li>
					</ul>
				</div>
				<div class="sidebar-menugroup">
					<div class="sidebar-grouptitle">会议预定</div>
					<ul class="sidebar-menu">
						<li class="sidebar-menuitem"><a href="addmeetingroom.jsp">添加会议室</a></li>
						<li class="sidebar-menuitem"><a href="SelectRoomAction">查看会议室</a></li>
						<li class="sidebar-menuitem"><a href="SelectRoomDeptAction">预定会议</a></li>
						<li class="sidebar-menuitem"><a href="SearchMeetingAction">搜索会议</a></li>
					</ul>
				</div>
			</div>
			<div class="page-content">
				<div class="content-nav">登录</div>
				<form action="loginActoin">
					<fieldset>
						<legend>登录信息</legend> 
						<table class="formtable" style="width: 50%">
							<tr>
								<td>账号名:</td>
								<td><input id="accountname" name="uunm" type="text"/></td>
							</tr>
							<tr>
								<td>密码:</td>
								<td><input id="new" name="upwd" type="password" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red">${error}</font></td>
							</tr>
							<tr>
								<td colspan="2" class="command">
								<input type="submit" value="登录" class="clickbutton" />
								<input type="button" value="返回" class="clickbutton" onclick="window.history.back();" /></td>
							</tr>
						</table>
					</fieldset>
				</form>
			</div>
		</div>
		<div class="page-footer">
			<hr/>更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a> 
			<img src="images/footer.png" alt="CoolMeeting" />
		</div>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<script type="text/javascript">
		function chg_btn(edit_btn,cancel_btn,dname_div,dname){
			edit_btn = document.getElementById(edit_btn);
			cancel_btn = document.getElementById(cancel_btn);
			dname_div = document.getElementById(dname_div);
			div_str = "<input type=text id=chg_"+dname+" value="+dname+">";
			if(edit_btn.innerHTML == "编辑"){
				dname_div.innerHTML = div_str;
				cancel_btn.className = "clickbutton";
				edit_btn.innerHTML = "修改";
				cancel_btn.innerHTML = "取消";
			}
			else{
				chg = document.getElementById("chg_"+dname);
				dname_div.innerHTML = chg.value;
				document.location="UpdateDeptActoin?dname="+dname+"&newDname="+dname_div.innerHTML;
				cancel_btn.className = "";
				edit_btn.innerHTML = "编辑";
				cancel_btn.innerHTML = "";
			}
		}
		function can_btn(edit_btn,cancel_btn,dname_div,dname){
			edit_btn = document.getElementById(edit_btn);
			cancel_btn = document.getElementById(cancel_btn);
			dname_div = document.getElementById(dname_div);
			dname_div.innerHTML = dname;
			cancel_btn.className = "";
			edit_btn.innerHTML = "编辑";
			cancel_btn.innerHTML = "";
		}
	</script>
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="styles/common.css"/>
    </head>
    <body>
        <div class="page-header">
            <div class="header-banner"><img src="images/header.png" alt="CoolMeeting"/></div>
            <div class="header-title"> 欢迎访问Cool-Meeting会议管理系统 </div>
            <div class="header-quicklink">
            	欢迎您，<strong>${sessionScope.uname}</strong>
            	<a href="changepassword.html">[修改密码]</a>
            </div>
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
                <div class="content-nav">人员管理 > 部门管理</div>
                <form action="AddDeptAction">
                    <fieldset>
                        <legend>添加部门</legend>
                       	 部门名称:
                        <input type="text" name="dname" id="departmentname" maxlength="20"/>
                        <input type="submit" class="clickbutton" value="添加"/>
                        <font color="red">${result}</font>
                    </fieldset>
                </form>
                <table class="listtable">
                	<caption>所有部门:</caption>
                    <tr class="listheader">
                        <th>部门编号</th>
                        <th>部门名称</th>
                        <th>操作</th>
                    </tr>
	                <c:forEach items="${requestScope.deptList}" var="dept">
	                	<tr>
	                		<td>${dept.dno}</td>
	                		<td><div id="dname${dept.dno}">${dept.dname}</div></td>
	                		<td>
	                			<a class="clickbutton" id="edit_btn${dept.dno}" onclick="chg_btn('edit_btn${dept.dno}',
	                			'cancel_btn${dept.dno}','dname${dept.dno}','${dept.dname}')">编辑</a>
	                			<a class="" id="cancel_btn${dept.dno}" onclick="can_btn('edit_btn${dept.dno}',
	                			'cancel_btn${dept.dno}','dname${dept.dno}','${dept.dname}')"></a>
	                            <a class="clickbutton" href="DelDeptAction?dno=${dept.dno}">删除</a>
	                		</td>
	                	</tr>
	                </c:forEach>
                </table>
            </div>
        </div>
        <div class="page-footer">
            <hr/>更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
            <img src="images/footer.png" alt="CoolMeeting"/>
        </div>
    </body>
</html>
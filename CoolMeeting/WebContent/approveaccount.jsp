<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<script type="text/javascript">
		function passBTN(uunm){
			document.location="ApprovePassAction?uunm="+uunm;
		}
		function notpassBTN(uunm){
			document.location="ApproveNotPassAction?uunm="+uunm;
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
                <div class="content-nav">人员管理 > 注册审批</div>
                <table class="listtable">
                    <caption>所有待审批注册信息：</caption>
                    <tr class="listheader">
                        <th>姓名</th>
                        <th>账号名</th>
                        <th>联系电话</th>
                        <th>电子邮件</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${requestScope.approveList}" var="approve">
                    	<tr>
                    		<td>${approve.uname}</td>
                    		<td>${approve.uunm}</td>
                    		<td>${approve.uphone}</td>
                    		<td>${approve.umail}</td>
                    		<td>
                            	<input type="button" class="clickbutton" value="通过" onclick="passBTN('${approve.uunm}');" />
                            	<input type="button" class="clickbutton" value="删除" onclick="notpassBTN('${approve.uunm}');" />
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
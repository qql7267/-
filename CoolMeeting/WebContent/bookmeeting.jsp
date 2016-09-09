<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="styles/common.css"/>
        <style type="text/css">
            #divfrom{
                float:left;
                width:150px;
            }
            #divto{
                float:left;
                width:150px;
            }
            #divoperator{
                float:left;
                width:50px;
                padding:60px 5px;
            }
            #divoperator input[type="button"]{
                margin:10px 0;
            }
            #selDepartments{
                display:block;
                width:100%;
            }
            #selEmployees{
                display:block;
                width:100%;
                height:200px;
            }
            #selSelectedEmployees{
                display:block;
                width:100%;
                height:225px;
            }
        </style>
        <script type="text/javascript">  
	        var selDepartments;
	        var selEmployees;
	        var selSelectedEmployees;
	
	        
	        function change(){
	        	var mname = document.getElementById("mname");
	        	var mnum = document.getElementById("mnum");
	        	var mstart_date = document.getElementById("mstart_date");
	        	var mstart_time = document.getElementById("mstart_time");
	        	var mend_date = document.getElementById("mend_date");
	        	var mend_time = document.getElementById("mend_time");
	        	var mnote = document.getElementById("mnote");
	        	var mroom = document.getElementById("mroom");
	        	var dno = document.getElementById("selDepartments"); 
	        	var str="ChangeOptionAction?mname="+mname.value+"&mnum="+mnum.value
	        			+"&mstart_date="+mstart_date.value+"&mstart_time="+mstart_time.value
	        			+"&mend_date="+mend_date.value+"&mend_time="+mend_time.value
	        			+"&mnote="+mnote.innerHTML+"&mroom="+mroom.value+"&dno="+dno.value
	        			+"&selectedLength="+selSelectedEmployees.options.length;
	        	for(var i=0;i<selSelectedEmployees.options.length;i++){
	        		str = str+"&uno"+i+"="+selSelectedEmployees.options[i].value+"&uname"+i+"="+selSelectedEmployees.options[i].text;
	        	}
	        	document.location=str;
	        }
	        
	        function confirmRes(){
	        	var mname = document.getElementById("mname");
	        	var mnum = document.getElementById("mnum");
	        	var mstart_date = document.getElementById("mstart_date");
	        	var mstart_time = document.getElementById("mstart_time");
	        	var mend_date = document.getElementById("mend_date");
	        	var mend_time = document.getElementById("mend_time");
	        	var mnote = document.getElementById("mnote");
	        	var mroom = document.getElementById("mroom");
	        	var dno = document.getElementById("selDepartments");
	        	var str="AddMeetingAction?mname="+mname.value+"&mnum="+mnum.value
	        			+"&mstart_date="+mstart_date.value+"&mstart_time="+mstart_time.value
	        			+"&mend_date="+mend_date.value+"&mend_time="+mend_time.value
	        			+"&mnote="+mnote.innerHTML+"&mroom="+mroom.value+"&dno="+dno.value
	        			+"&selectedLength="+selSelectedEmployees.options.length;
	        	for(var i=0;i<selSelectedEmployees.options.length;i++){
	        		str = str+"&uno"+i+"="+selSelectedEmployees.options[i].value;
	        	}
	        	document.location=str;
	        }
	        
	        function body_load(){
	        	selDepartments = document.getElementById("selDepartments");
	        	selEmployees = document.getElementById("selEmployees");
	        	selSelectedEmployees = document.getElementById("selSelectedEmployees");    	
	        }
	        
	        function clearList(list){
	            while(list.childElementCount > 0){
	                list.removeChild(list.lastChild);
	            }
	        }
	        
	        function selectEmployees(){
	            for(var i=0;i<selEmployees.options.length;i++){
	                if (selEmployees.options[i].selected){
	                    addEmployee(selEmployees.options[i]);
	                    selEmployees.options[i].selected = false;
	                }
	            }
	        }
	        
	        function deSelectEmployees(){
	            var elementsToRemoved = new Array();
	            var options = selSelectedEmployees.options;
	            for(var i=0;i<options.length;i++){
	                if (options[i].selected){
	                    elementsToRemoved.push(options[i]);
	                }
	            }
	            for(i=0;i<elementsToRemoved.length;i++){
	                selSelectedEmployees.removeChild(elementsToRemoved[i]);
	            }
	        }
	        
	        function addEmployee(optEmployee){
	            var options = selSelectedEmployees.options;
	            var i = 0;
	            var insertIndex = -1;
	            while(i < options.length){
	                if (optEmployee.value == options[i].value){
	                    return;
	                } else if (optEmployee.value < options[i].value) {
	                    insertIndex = i;
	                    break;
	                }
	                i++;
	            }
	            var opt = document.createElement("option");
	            opt.value = optEmployee.value;
	            opt.text = optEmployee.text;
	            
	            if (insertIndex == -1){
	                selSelectedEmployees.appendChild(opt);
	            } else {
	                selSelectedEmployees.insertBefore(opt, options[insertIndex]);
	            }
	        }           
        </script>
    </head>
   <body onload="body_load()">
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
                <div class="content-nav">会议预定 > 预定会议</div>
                <form>
                    <fieldset>
                        <legend>会议信息</legend>
                        <table class="formtable">
                            <tr>
                                <td>会议名称：</td>
                                <td>
                                    <input type="text" name="mname" id="mname" maxlength="20" value="${requestScope.mname}"/>
                                    ${requestScope.result}
                                </td>
                            </tr>
                            <tr>
                                <td>预计参加人数：</td>
                                <td>
                                    <input type="text" name="mnum" id="mnum" value="${requestScope.mnum}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>预计开始时间：</td>
                                <td>
                                    <input type="date" name="mstart_date" id="mstart_date" value="${requestScope.mstart_date}"/>
                                    <input type="time" name="mstart_time" id="mstart_time" value="${requestScope.mstart_time}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>预计结束时间：</td>
                                <td>
                                    <input type="date" name="mend_date" id="mend_date" value="${requestScope.mend_date}" />
                                    <input type="time" name="mend_time" id="mend_time" value="${requestScope.mend_time}" />
                                </td>
                            </tr>
							<tr>
                                <td>会议室名称：</td>
                                <td>
                                    <select name="mroom" id="mroom">
                                    	<c:forEach items="${requestScope.roomList}" var="room">
                                    		<option value="${room.rno}"<c:if test="${room.rno==requestScope.mroom}">selected="selected"</c:if>>${room.rname}</option>
                                    	</c:forEach>   
                                     </select>
                                </td>
                            </tr>
                            <tr>
                                <td>会议说明：</td>
                                <td>
                                    <textarea id="mnote" name="mnote" rows="5">${requestScope.mnote}</textarea>
                                </td>
                            </tr>
                            <tr>
                                 <td>选择参会人员：</td>
                                <td>
                                    <div id="divfrom">
                                        <select id="selDepartments">
                                        	<c:forEach items="${requestScope.deptList}" var="dept">
                                        		<option value="${dept.dno}" onclick="change()"<c:if test="${dept.dno==requestScope.dno}">selected="selected"</c:if>>${dept.dname}</option>
                                        	</c:forEach>
                                        </select>
                                        <select id="selEmployees" multiple="multiple">
                                        <c:forEach items="${requestScope.userList}" var="user">
                                        		<option value="${user.uno}">${user.uname}</option>
                                        	</c:forEach>
                                        </select>
                                    </div>
                                    <div id="divoperator">
                                        <input type="button" class="clickbutton" value="&gt;" onclick="selectEmployees()"/>
                                        <input type="button" class="clickbutton" value="&lt;" onclick="deSelectEmployees()"/>
                                    </div>
                                    <div id="divto">
                                        <select id="selSelectedEmployees" name="userList" multiple="multiple">
                                        	<c:forEach items="${requestScope.userSelList}" var="user">
                                        		<option value="${user.uno}">${user.uname}</option>
                                        	</c:forEach>
                                        </select>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="command" colspan="2">
                                    <input type="button" class="clickbutton" onclick="confirmRes()" value="预定会议"/>
                                    <input type="reset" class="clickbutton" value="重置"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <div class="page-footer">
            <hr/>更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
            <img src="images/footer.png" alt="CoolMeeting"/>
        </div>
    </body>
</html>
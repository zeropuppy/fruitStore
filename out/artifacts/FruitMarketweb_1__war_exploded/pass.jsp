<%@ page import="edu.fruitmarket.web.beans.User" %><%--
  Created by IntelliJ IDEA.
  User: 18307
  Date: 2023/1/9
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong><span class="icon-key"></span> 修改会员密码</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="PassServlet">
            <%

                User user=(User) session.getAttribute("loginUser");
//                User user=new User();
//                user.setId(1);
//                user.setUsername("admin");
//                user.setPassword("123456");
            %>
            <div class="form-group">
                <div class="label">
                    <label >管理员帐号：</label>
                </div>
                <div class="field">
                    <label style="line-height:33px;">
                        admin
                    </label>
                </div>
            </div>

            <%--            <div class="form-group">--%>
            <%--                <div class="label">--%>
            <%--                    <label for="uid">用户编号：</label>--%>
            <%--                </div>--%>
            <%--                <div class="field">--%>
            <%--                    <input type="text" class="input w50" id="uid" name="uid" size="50" value="<%=user.getId()%>" readonly="readonly" />--%>
            <%--                </div>--%>
            <%--            </div>--%>
            <%--            <div class="form-group">--%>
            <%--                <div class="label">--%>
            <%--                    <label for="username">用户名：</label>--%>
            <%--                </div>--%>
            <%--                <div class="field">--%>
            <%--                    <input type="text" class="input w50" id="username" name="username" size="50" value="<%=user.getUsername()%>" readonly="readonly" />--%>
            <%--                </div>--%>
            <%--            </div>--%>

            <div class="form-group">
                <div class="label">
                    <label for="mpass">原始密码：</label>
                </div>
                <div class="field">
                    <input type="password" class="input w50" id="mpass" name="mpass" size="50" placeholder="请输入原始密码" data-validate="required:请输入原始密码" />
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label for="newpass">新密码：</label>
                </div>
                <div class="field">
                    <input type="password" class="input w50" id="newpass" name="newpass" size="50" placeholder="请输入新密码" data-validate="required:请输入新密码,length#>=5:新密码不能小于5位" />
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label for="renewpass">确认新密码：</label>
                </div>
                <div class="field">
                    <input type="password" class="input w50" id="renewpass" name="renewpass" size="50" placeholder="请再次输入新密码" data-validate="required:请再次输入新密码,repeat#newpass:两次输入的密码不一致" />
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
                    <button class="button bg-main icon-check-square-o" type="reset"> <a href="ListServlet" >取消</a></button>
                </div>
            </div>
        </form>
    </div>
</div>
</body></html>
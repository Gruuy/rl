<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/4
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>库存管理系统</title>
    <!--<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>-->
    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
    <!-- 引入样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap.min.js"></script>
    </head>
    <body style="background: url('views/images/background.jpg') no-repeat;background-size: 100% 100%;">
    <div style="padding: 100px 100px 10px;">
            <h3>库存管理系统</h3>
            <div class="input-group">
                <table>
                    <tr>
                        <td>Username:</td>
                        <td><input name="username" type="text" class="form-control" placeholder="username"></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input name="password" type="password" class="form-control" placeholder="password"></td>
                    </tr>
                    <tr>
                        <td colspan="1"><div style="width: 100px;margin-left: auto;margin-right: auto;">
                            <input id="btn1" class="btn btn-default" type="button" value="Login" onclick="login()">
                        </div></td>
                    </tr>
                </table>
            </div>
    </div>
    </body>
    <script type="text/javascript">
            function login() {
                uname = $("input[name='username']").val();
                upass = $("input[name='password']").val();
                alert(uname);
                alert(upass);
                $.ajax({
                    contentType:"application/x-www-form-urlencoded",
                    type: "post",
                    url: "login",
                    data: {"username": uname, "password": upass},
                    success: function (result) {
                        result=eval('('+result+')')
                        if (result.flag=="true") {
                            alert("登陆成功！");
                            //window.location.href("success.jsp");
                        } else if (result.flag=="false") {
                            alert("账号或密码错误");
                        }else{
                            alert("不能为空！");
                        }
                    },
                    error: function () {
                        alert("服务器错误，请重试");
                    }
                })
            };
    </script>
    </html>

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
<body>
<div style="padding: 100px 100px 10px;">
    <form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
        <h2>文件上传</h2>
        <input type="file" name="file">
        <input type="submit" value="提交">
    </form>
    <a href="${pageContext.request.contextPath}/download?filename=sbIDEA.txt">下载</a><br><br>
    <a href="${pageContext.request.contextPath}/bigfiledown?filename=sbIDEA.txt">大文件下载</a>
</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: wdf
  Date: 2019/11/21
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图片上传页面</title>
</head>
<style>
    .btn {
        /*font: 1px;*/
        font-size: 10px;
    }
</style>
<body>

<form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
    <div class="box">
        <input type="file" name="filename" class="select-file" multiple>

    </div>
    <input type="submit" value="上传" class="btn">
</form>
<p>${ msg}</p>
<p>${ url}</p>


</body>

</html>

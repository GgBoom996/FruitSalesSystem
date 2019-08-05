<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/hejie/common/header.jsp"%>
</head>
<body>
    <form action="/import/upload" method="post" enctype="multipart/form-data">
        <input class="easyui-filebox" name="file"   data-options="prompt:'选中文件',  buttonText: '选择'" style="width:50%">
        <button class="easyui-linkbutton">上传</button>
    </form>

</body>
</html>

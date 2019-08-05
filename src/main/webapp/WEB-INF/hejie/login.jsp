<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>智销系统登录页</title>
    <%@include file="/WEB-INF/hejie/common/header.jsp"%>
    <link rel="stylesheet" type="text/css" href="/plugins/login/css/style.css">
    <%-- <script type="text/javascript" src="/plugins/login/js/jquery.min.js"></script>--%>
    <script type="text/javascript" src="/plugins/login/js/vector.js"></script>
    <script type="text/javascript">
        function submitForm(){
            var jsonObj = $("#entry_form").serializeObject();
            $('#entry_form').form('submit', {
                url:"/login",
                onSubmit: function(){
                    return $("#entry_form").form("validate");
                },
                success:function(data){
                    var result = $.parseJSON(data);
                    if(result.success){//表示登录成功
                        //跳转到主界面
                        window.location.href = "/main";
                    }else{
                        $.messager.alert("错误", result.msg, "error");
                    }
                }
            });

        }

    </script>

</head>
<body>

<div id="container">
    <div id="output">
        <div class="containerT">
            <h1>用户登录</h1>
            <form id="entry_form" class="form" method="post">
                <input name="username" type="text" placeholder="用户名" id="username" value="admin" >
                <input name="password" type="password" placeholder="密码" id="password" value="admin">
                <button type="button" id="entry_btn" onclick="submitForm();">登录</button>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function(){
        Victor("container", "output");   //登录背景函数
        $("#entry_name").focus();
        $(document).keydown(function(event){
            if(event.keyCode==13){
                $("#entry_btn").click();
            }
        });
    });
</script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智销系统主页</title>
    <%@include file="/WEB-INF/hejie/common/header.jsp"%>
    <style type="text/css">
        #tt{
            background:url("/images/index_main.jpg");
            float: top;
        }
        .header{
            height: 120px;
        }
        .logo{
            margin-top: 40px;
            margin-left: 10px;
            float: left;

        }
        .logoText{
            color: white;
            float: left;
            margin-top: 55px;
            font-size: 20px;
            margin-left: 30px;
        }
        .loginUser{
            float: right;
            color: black;
            margin-right: 15px;
            margin-top: 55px;
            font-size: 20px;

        }
        .loginUser a{
            color: green;
        }
        .logoMaSha{
            float: left;
            margin-top: 40px;
            margin-left: 30px;
        }
        .southMain {
            
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("#ul").tree({
                url:"/menu/findMenuByLoginUser",
                animate:true,
                lines:true,
                onClick: function(node){
                    if(node.url){//只有子菜单才能添加选项卡
                        if($("#tt").tabs("exists",node.text)){//如果存在，则不添加，应该选中
                            $("#tt").tabs("select",node.text)
                        }else{//否则添加新的选项卡
                            $('#tt').tabs('add',{
                                title: node.text,
                                content: "<iframe frameborder='0' src='"+node.url+"' width='100%' height='100%'>",
                                closable: true
                            });
                        }
                    }
                }

            });

                /*为选项卡绑定右键*/
                $("#tt").tabs({
                    onContextMenu : function(e) {
                        /* 选中当前触发事件的选项卡 */
                        var subtitle = $(this).text();
                        $('#tt').tabs('select', subtitle);
                        //显示快捷菜单
                        e.preventDefault();
                        //阻止冒泡
                        $('#menu').menu('show', {
                            left : e.pageX,
                            top : e.pageY
                        });
                        return false;
                    }
                })
//----------------------------------------
            //刷新
            $("#m-refresh").click(function () {
                var currTab = $('#tt').tabs('getSelected');    //获取选中的标签项
                var url = $(currTab.panel('options').content).attr('src');    //获取该选项卡中内容标签（iframe）的 src 属性
                if (url == null) {
                    /* 重新设置该标签 */
                    $('#tt').tabs('update', {
                        tab: currTab,
                        options: {
                            content: '<iframe frameborder="0"  src="Tabs/Monitoring/MonitoringOfMapType.aspx" style="height: 100%; width: 100%;" ></iframe>'//如果用herf,容易导致样式与主页面重载,导致页面奔溃.
                        }
                    })
                }
                else {
                    /* 重新设置该标签 */
                    $('#tt').tabs('update', {
                        tab: currTab,
                        options: {
                            content: '<iframe frameborder="0"  src="' + url + '"  style="height: 100%; width: 100%;" ></iframe>'//如果用herf,容易导致样式与主页面重载,导致页面奔溃.
                        }
                    })
                }
            });
            //关闭所有
            $("#m-closeall").click(function () {
                $(".tabs li").each(function (i, n) {
                    var title = $(n).text();
                    if (title != '主页') {//非主页全部关闭
                        $('#tt').tabs('close', title);
                    }
                });
            });
            //关闭其他


//关闭其他
                $("#m-closeother").click(function () {
                    var currTab = $('#tt').tabs('getSelected');
                    currTitle = currTab.panel('options').title;
                    $(".tabs li").each(function (i, n) {
                        var title = $(n).text();
                        if (currTitle != title && title != '主页') {//除本页和主页以外全部关闭
                            $('#tt').tabs('close', title);
                        }
                    });
                });
//关闭当前
            $("#m-close").click(function () {
                var currTab = $('#tt').tabs('getSelected');
                currTitle = currTab.panel('options').title;
                $('#tt').tabs('close', currTitle);
            });

        });
    </script>
    <script type="text/javascript">
        $('#mm').menu({
            onClick:function(item){
                //...
            }
        });
        $('#mm').menu('show', {
            left: 200,
            top: 100
        });

    </script>

</head>
<body>



<div class="easyui-layout" data-options="fit:true" >
    <div data-options="region:'north'" class="header" style="background: url('/images/1.bmp')">
        <div class="logoMaSha">
            <img src="/images/mashaladi.jpg" width="60" height="60"/>
        </div>
        <div class="logoText">
            智能商贸系统
        </div>
        <div class="loginUser">
            欢迎<span style="color: #0052A3"><shiro:principal property="username"/></span>登录系统&emsp;<a href="/logout">注销</a>
        </div>
    </div>
    <div class="southMain" data-options="region:'south'"  style="height:50px;color: #0052A3;">
        <div id="southLogin" align="center">
            上网不上瘾，健康又文明<br/>
            Java智能商贸，让你感受到不一般的生活
        </div>
    </div>
    <div data-options="region:'west'" title="菜单" style="width:160px;">
        <%--菜单的展示--%>
        <ul id="ul"></ul>
    </div>
    <div data-options="region:'center'" class="center" >
        <div id="tt" class="easyui-tabs" data-options="fit:true,border:false" >
            <div title="主页" style="padding:10px">
                <img src="/images/index_main.jpg" style="width: 100%;height: 100%">
            </div>
        </div>
</div>
    <%--右键菜单--%>
    <div id="menu" class="easyui-menu" style="width: 150px;">
        <div id="m-refresh" data-options="iconCls:'icon-reload'">
            刷新</div>
        <div class="menu-sep" data-options="iconCls:''">
        </div>
        <div id="m-closeall" data-options="iconCls:''">
            全部关闭</div>
        <div id="m-closeother" data-options="iconCls:''">
            关闭其他</div>
        <div class="menu-sep">
        </div>
        <div id="m-close" data-options="iconCls:'icon-no'">
            关闭</div>
    </div>




</body>
</html>

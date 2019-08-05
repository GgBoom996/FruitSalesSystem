<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/hejie/common/header.jsp" %>
    <script type="text/javascript" src="/plugins/model/permission.js"></script>
</head>
<body>


<%--弹出框对应的按钮--%>
<div id="bb">
    <a href="javascript:void(0);" data-method="save" class="easyui-linkbutton c3" data-options="plain:true">保存</a>
    <a href="javascript:void(0);" onclick="$('#dlg').dialog('close')" class="easyui-linkbutton c4"
       data-options="plain:true">关闭</a>
</div>

<%--弹出框的定义--%>
<div id="dlg" class="easyui-dialog" data-options="buttons:'#bb'">
    <form id="ff" method="post">
        <input id="eid" type="hidden" name="id">
        <table cellpadding="5">
            <tr>
                <td>name:</td>
                <td><input class="easyui-textbox" type="text" name="name"></input></td>
            </tr>
            <tr>
                <td>url:</td>
                <td><input class="easyui-textbox" type="text" name="url"></input></td>
            </tr>
            <tr>
                <td>descs:</td>
                <td><input class="easyui-textbox" type="text" name="descs"></input></td>
            </tr>
            <tr>
                <td>sn:</td>
                <td><input class="easyui-textbox" type="text" name="sn"></input></td>
            </tr>
            <tr>
                <td>menuId:</td>
                <td><input class="easyui-textbox" type="text" name="menuId"></input></td>
            </tr>
        </table>
    </form>

</div>

<%--datagrid对应的toolbar--%>
<div id="tb" style="padding:15px;height:auto">
    <form id="searchForm">
        <div>
            名字: <input class="easyui-textbox" type="text" name="name"></input>
            <a href="javascript:void(0);" data-method="search" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </form>
    <div>
        <a href="javascript:void(0);" data-method="add" class="easyui-linkbutton"
           data-options="iconCls:'icon-add',plain:true"></a>
        <a href="javascript:void(0);" data-method="edit" class="easyui-linkbutton"
           data-options="iconCls:'icon-edit',plain:true"></a>
        <a href="javascript:void(0);" data-method="remove" class="easyui-linkbutton"
           data-options="iconCls:'icon-remove',plain:true"></a>
    </div>
</div>

<table id="dg" class="easyui-datagrid" title="权限管理"
       data-options="
           fit:true,
           striped:true,
           rownumbers:true,
           fitColumns:true,
           pagination:true,
           toolbar:'#tb',
           url:'/permission/datagrid'">
    <thead>
    <tr>
        <th data-options="field:'id',checkbox:true,width:'1%'">ID</th>
        <th data-options="field:'name',width:100,align:'center'">权限名</th>
        <th data-options="field:'url',width:100,align:'center'">url路径</th>
        <th data-options="field:'descs',width:100,align:'center'">描述</th>
        <th data-options="field:'sn',width:100,align:'center'">编号</th>
        <th data-options="field:'menu',width:100,align:'center',formatter:formatterPermission">菜单</th>
    </tr>
    </thead>
</table>
</body>
</html>
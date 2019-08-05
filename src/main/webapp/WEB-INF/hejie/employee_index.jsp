
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/hejie/common/header.jsp"%>
    <script type="text/javascript" src="/plugins/model/employee.js"></script>
    <style type="text/css">

    </style>
</head>
<body>
<%--弹出框对应的按钮--%>
<div id="bb">
    <a href="javascript:void(0);"  data-method="save" class="easyui-linkbutton c1" data-options="plain:true">保存</a>
    <a href="javascript:void(0);" onclick="$('#dlg').dialog('close')" class="easyui-linkbutton c4" data-options="plain:true">关闭</a>
</div>
<%--弹出框的定义--%>
<div id="dlg" class="easyui-dialog" data-options="buttons:'#bb'">
    <form id="ff" method="post">
        <input id="eid" type="hidden" name="id">
        <table cellpadding="5">
            <tr>
                <td>用户名:</td>
                <td><input class="easyui-textbox" type="text" name="username" data-options="required:true,validType:['length[5,10]','checkName']" ></input></td>
            </tr>
            <tr pwd>
                <td>密码:</td>
                <td><input class="easyui-textbox" id="password" type="password" name="password"  data-options="required:true,validType:'minLength[6]'" ></input></td>
            </tr>
            <tr pwd>
                <td>重复密码:</td>
                <td><input class="easyui-textbox" id="configPassword" type="password" name="configPassword"  data-options="required:true,validType:'equals[\'password\',\'id\']'" ></input></td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td><input class="easyui-textbox" name="email" data-options="validType:'email'"></input></td>
            </tr>
            <tr>
                <td>年龄:</td>
                <td><input class="easyui-textbox" name="age" data-options="validType:'integerRange[18,90]'"></input></td>
            </tr>
            <tr>
                <td>部门:</td>
                <td>
                    <input class="easyui-combobox" name="department.id"
                           data-options="
                        <%--防止下拉框写入--%>
                        editable:false,
                        url: '/util/findAllDepartment',
                        valueField:'id',  <%--给程序看的值--%>
                        textField:'name',  <%--给程序员看的值--%>
                        panelHeight:'auto'
                    ">
                </td>
            </tr>
        </table>
    </form>
</div>

<%--datagrid对应的toolbar--%>
<div id="tb" style="padding:5px;height:auto">
    <form id="searchForm">
        <div>
            <a href="javascript:void(0);" data-method="add" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
            <a href="javascript:void(0);" data-method="edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
            <a href="javascript:void(0);" data-method="remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
            用户名: <input class="easyui-textbox" type="text" name="username" ></input>
            部门:
            <input class="easyui-combobox" name="deptId"
                   data-options="
                        <%--防止下拉框写入--%>
                        editable:false,
                        url: '/util/findAllDepartment',
                        valueField:'id',  <%--给程序看的值--%>
                        textField:'name',  <%--给程序员看的值--%>
                        panelHeight:'auto'
                    ">
            <a href="javascript:void(0);" data-method="search" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </form>
</div>

<%--员工管理信息--%>
<table id="dg" class="easyui-datagrid" title="员工管理"
       data-options="
           fit:true,
           striped:true,
           rownumbers:true,
           fitColumns:true,
           pagination:true,
           toolbar:'#tb',
           url:'/employee/datagrid'">
    <thead>
    <tr>
        <th data-options="field:'id',checkbox:true,width:'1%'">ID</th>
        <th data-options="field:'headImage',width:'16%',align:'center',formatter:formatterHeadImage">头像</th>
        <th data-options="field:'username',width:'16%',align:'center'">用户名</th>
        <th data-options="field:'password',width:'19%',align:'center'">密码</th>
        <th data-options="field:'email',width:'16%',align:'center'">邮箱</th>
        <th data-options="field:'age',width:'16%',align:'center'">年龄</th>
        <th data-options="field:'department',width:'16%',align:'center',formatter:formatterDepartment">部门</th>
    </tr>
    </thead>
</table>
</body>
</html>

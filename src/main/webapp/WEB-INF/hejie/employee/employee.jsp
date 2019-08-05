<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/hejie/common/header.jsp"%>
    <script type="text/javascript" src="/plugins/model/employee.js"></script>
</head>
<body>
    <%--弹出框对应的按钮--%>
    <div id="bb">
        <a href="javascript:void(0);"  data-method="save" class="easyui-linkbutton c3" data-options="plain:true">保存</a>
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
                    <td><input class="easyui-textbox" name="age" data-options="validType:'integerRange[1,90]'"></input></td>
                </tr>
                <tr>
                    <td>部门:</td>
                    <td>
                        <input class="easyui-combobox" name="department.id"
                               data-options="
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
    <div id="tb" style="padding:15px;height:auto">
        <form id="searchForm" action="/employee/download" method="post">
            <div>
                用户名: <input class="easyui-textbox" type="text" name="username" ></input>
                部门:
                <input class="easyui-combobox" name="deptId"
                       data-options="
                        url: '/util/findAllDepartment',
                        valueField:'id',  <%--给程序看的值--%>
                        textField:'name',  <%--给程序员看的值--%>
                        panelHeight:'auto'
                    ">
                <a href="javascript:void(0);" data-method="search" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
                <%--button标签在form表单内部，默认提交就是submit--%>
                <button   class="easyui-linkbutton" iconCls="icon-redo">导出</button>
            </div>
        </form>
        <div>
            <shiro:hasPermission name="employee:save">
                <a href="javascript:void(0);" data-method="add" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"></a>
            </shiro:hasPermission>
            <shiro:hasPermission name="employee:update">
                <a href="javascript:void(0);" data-method="edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"></a>
            </shiro:hasPermission>
            <shiro:hasPermission name="employee:remove">
                <a href="javascript:void(0);" data-method="remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"></a>
            </shiro:hasPermission>
        </div>
    </div>

    <table id="dg" class="easyui-datagrid" title="员工管理"
           data-options="
           fit:true,
           striped:true,
           rownumbers:true,
           fitColumns:true,
           pagination:true,
           toolbar:'#tb',
           border:false,
           url:'/employee/datagrid'">
        <thead>
            <tr>
                <th data-options="field:'id',checkbox:true,width:'1%'">ID</th>
                <th data-options="field:'headImage',width:'20%',align:'center',formatter:formatterHeadImage">头像</th>
                <th data-options="field:'username',width:'19%',align:'center'">用户名</th>
                <th data-options="field:'email',width:'20%',align:'center'">邮箱</th>
                <th data-options="field:'age',width:'20%',align:'center'">年龄</th>
                <th data-options="field:'department',width:'20%',align:'center',formatter:formatterDepartment">部门</th>
            </tr>
        </thead>
    </table>
</body>
</html>

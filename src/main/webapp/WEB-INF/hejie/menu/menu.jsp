<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/hejie/common/header.jsp"%>
    <script type="text/javascript" src="/plugins/model/menu.js"></script>
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
                    <td>name:</td>
                    <td><input class="easyui-textbox" type="text" name="name"  ></input></td>
                </tr>
                             <tr>
                    <td>url:</td>
                    <td><input class="easyui-textbox" type="text" name="url"  ></input></td>
                </tr>
                             <tr>
                    <td>icon:</td>
                    <td><input class="easyui-textbox" type="text" name="icon"  ></input></td>
                </tr>
                             <tr>
                    <td>parentId:</td>
                    <td><input class="easyui-textbox" type="text" name="parentId"  ></input></td>
                </tr>
                         </table>
        </form>

    </div>

    <%--datagrid对应的toolbar--%>
    <div id="tb" style="padding:15px;height:auto">
        <form id="searchForm">
            <div>
                名字: <input class="easyui-textbox" type="text" name="name" ></input>
                <a href="javascript:void(0);" data-method="search" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
            </div>
        </form>
        <div>
            <a href="javascript:void(0);" data-method="add" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"></a>
            <a href="javascript:void(0);" data-method="edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"></a>
            <a href="javascript:void(0);" data-method="remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"></a>
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
           url:'/menu/datagrid'">
        <thead>
            <tr>
                 <th data-options="field:'id',checkbox:true,width:'1%'">ID</th>
                                <th data-options="field:'name',width:100,align:'center'">管理菜单</th>
                                <th data-options="field:'url',width:100,align:'center'">路径</th>
                                <th data-options="field:'icon',width:100,align:'center',formatter:formatterIcon">图标</th>
                            </tr>
        </thead>
    </table>
</body>
</html>
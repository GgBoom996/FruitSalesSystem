<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/hejie/common/header.jsp"%>
    <script type="text/javascript" src="/plugins/model/producttype.js"></script>
</head>
<body>

    <%--弹出框对应的按钮--%>
    <div id="bb">
        <a href="javascript:void(0);" data-method="saveData" class="easyui-linkbutton c3" data-options="plain:true">保存</a>
        <a href="javascript:void(0);" onclick="$('#dlg').dialog('close')" class="easyui-linkbutton c4"  data-options="plain:true">关闭</a>
    </div>

<%--
        easyui的时候，查看默认属性与事件
       $.fn.{plugin}.defaults
        弹出框html代码
    --%>
    <div id="dlg" class="easyui-dialog" data-options="width:300,height:310,buttons:'#bb'" >
        <form id="ff" method="post">
            <input id="eid" type="hidden" name="id">
            <table cellpadding="5">
                    <tr>
                        <td>name:</td>
                        <td><input class="easyui-textbox" type="text" name="name" ></input></td>
                    </tr>
                                    <tr>
                        <td>descs:</td>
                        <td><input class="easyui-textbox" type="text" name="descs" ></input></td>
                    </tr>
                                    <tr>
                        <td>parentId:</td>
                        <td><input class="easyui-textbox" type="text" name="parentId" ></input></td>
                    </tr>
                                
  
            </table>
        </form>
    </div>

    <%--查询工具栏--%>
    <div id="tb" style="padding:10px;height:auto">
        <div>
            <form id="searchForm">
                姓名: <input name="name" class="easyui-textbox" style="width:120px">
                <a href="javascript:void(0);" data-method="search" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
            </form>
        </div>
        <div style="margin-bottom:5px">
            <a href="javascript:void(0);" data-method="add" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
            <a href="javascript:void(0);" data-method="edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
            <a href="javascript:void(0);" data-method="delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
        </div>
    </div>

    <table id="dg" class="easyui-datagrid" title="员工管理"
           data-options="url:'/productType/datagrid',
           fit:true,
           pagination:true,
           rownumbers:true,
           toolbar:'#tb'
        ">
        <thead>
        <tr>
            <th data-options="field:'id',checkbox:true,width:'2%'">ID</th>
                            <th data-options="field:'name',width:'32%',align:'center'">产品类型</th>
                            <th data-options="field:'descs',width:'33%',align:'center'">描述</th>
                            <th data-options="field:'parent',width:'33%',align:'center',formatter:formatterProducttype">产品</th>
                    </tr>
        </thead>
    </table>
</body>
</html>
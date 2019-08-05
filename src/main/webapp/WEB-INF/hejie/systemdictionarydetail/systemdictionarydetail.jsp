<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/hejie/common/header.jsp"%>
    <script type="text/javascript" src="/plugins/model/systemdictionarydetail.js"></script>
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
                        <td>typesId:</td>
                        <td><input class="easyui-textbox" type="text" name="typesId" ></input></td>
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
           data-options="url:'/systemDictionaryDetail/datagrid',
           fit:true,
           pagination:true,
           rownumbers:true,
           toolbar:'#tb'
        ">
        <thead>
        <tr>
            <th data-options="field:'id',checkbox:true,width:'2%'">ID</th>
                                                                                                        
                                                                                                    
                                                                
                                        <th data-options="field:'name',width:'49%',align:'center'">字典</th>
                            <th data-options="field:'types',width:'49%',align:'center',formatter:formatterSystemdictionarydetail">类型</th>
                    </tr>
        </thead>
    </table>
</body>
</html>
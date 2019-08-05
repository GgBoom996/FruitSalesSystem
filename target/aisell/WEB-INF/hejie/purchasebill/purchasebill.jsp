<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/hejie/common/header.jsp" %>
    <script type="text/javascript" src="/plugins/easyui/plugins/jeasyui.extensions.datagrid.edit.cellEdit.js"></script>
    <script type="text/javascript" src="/plugins/easyui/plugins/jeasyui.extensions.datagrid.editors.js"></script>
    <script type="text/javascript" src="/plugins/easyui/plugins/jeasyui.extensions.datagrid.getColumnInfo.js"></script>
    <script type="text/javascript" src="/plugins/model/purchasebill.js"></script>
</head>
<body>

<%--弹出框对应的按钮--%>
<div id="bb">
    <a href="javascript:void(0);" data-method="save" class="easyui-linkbutton c3" data-options="plain:true">保存</a>
    <a href="javascript:void(0);" onclick="$('#dlg').dialog('close')" class="easyui-linkbutton c4"
       data-options="plain:true">关闭</a>
</div>

<%--采购订单明细对应的工具栏--%>
<div id="billItemsToolBar">
    <a id="btnInsert" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
    <a id="btnRemove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
</div>

<%--弹出框的定义--%>
<div id="dlg" class="easyui-dialog" data-options="buttons:'#bb'">
    <form id="ff" method="post">
        <input id="eid" type="hidden" name="id">
        <table cellpadding="5">
            <tr>
                <td>交易时间:</td>
                <td><input class="easyui-datebox" type="text"  name="vdate"></input></td>
            </tr>
            <tr>
                <td>供应商:</td>
                <td>
                    <input class="easyui-combobox" name="supplier.id"
                           data-options="
                            url:'/util/findSuppliers',
                            method:'get',
                            valueField:'id',
                            textField:'name',
                            panelHeight:'auto'
                        ">

                </td>
            </tr>
            <tr>
                <td>采购员:</td>
                <td>
                    <input class="easyui-combobox" name="buyer.id"
                           data-options="
                            url:'/util/findBuyers',
                            method:'get',
                            valueField:'id',
                            textField:'username',
                            panelHeight:'auto'
                        ">

                </td>
            </tr>

        </table>
        <table id="billItems"></table>
    </form>

</div>

<%--datagrid对应的toolbar--%>
<div id="tb" style="padding:15px;height:auto">
    <form id="searchForm">
        <div>
            时间: <input class="easyui-datebox" type="text" name="beginDate"></input>
            <input class="easyui-datebox" type="text" name="endDate"></input>
            状态:<select class="easyui-combobox" data-options="panelHeight:'auto'" name="status" style="width: 130px" >
            <option value="">请选中</option>
            <option value="0">待审</option>
            <option value="1">已审</option>
            <option value="-1">作废</option>
        </select>
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

<table id="dg" class="easyui-datagrid" title="员工管理"
       data-options="
           fit:true,
           striped:true,
           rownumbers:true,
           fitColumns:true,
           pagination:true,
           toolbar:'#tb',
           url:'/purchaseBill/datagrid'">
    <thead>
    <tr>
        <th data-options="field:'vdate',width:100,align:'center'">交易时间</th>
        <th data-options="field:'supplier',width:100,align:'center',formatter:formatterSupplier">供应商</th>
        <th data-options="field:'buyer',width:100,align:'center',formatter:formatterBuyer">采购员</th>
        <th data-options="field:'totalNum',width:100,align:'center'">总数量</th>
        <th data-options="field:'totalAmount',width:100,align:'center'">总金额</th>
        <th data-options="field:'status',width:100,align:'center',formatter:formatterStatus">状态</th>
    </tr>
    </thead>
</table>
</body>
</html>
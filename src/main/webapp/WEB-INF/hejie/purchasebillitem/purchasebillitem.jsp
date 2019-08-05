<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/hejie/common/header.jsp"%>
    <script type="text/javascript" src="/plugins/model/purchasebillitem.js"></script>
    <script type="text/javascript" src="/plugins/easyui/plugins/datagrid-groupview.js"></script>
    <%--highcharts核心js--%>
    <script src="/plugins/highcharts/code/highcharts.js"></script>
    <%--支持3d效果的js--%>
    <script src="/plugins/highcharts/code/highcharts-3d.js"></script>
    <%--支持导出图片相关的js--%>
    <script src="/plugins/highcharts/code/modules/exporting.js"></script>
    <script src="/plugins/highcharts/code/modules/export-data.js"></script>
</head>
<body>
<div id="dlg" class="easyui-dialog" data-options="width:500,height:480,title:'报表图'">
    <div id="container" style="height: 400px"></div>
</div>

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
            <select class="easyui-combobox" data-options="panelHeight:'auto'" name="groupType" style="width: 130px" >
                <option value="o.bill.supplier.name">供应商</option>
                <option value="o.bill.buyer.username">采购员</option>
                <option value="month(o.bill.vdate)">月份</option>
            </select>
            <a href="javascript:void(0);" data-method="search" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
            <a href="javascript:void(0);" data-method="3d" class="easyui-linkbutton" iconCls="icon-search">3D</a>
        </div>
    </form>
</div>
<table id="tt"></table>
</body>
</html>
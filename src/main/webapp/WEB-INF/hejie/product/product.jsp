    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/hejie/common/header.jsp" %>
    <script type="text/javascript" src="/plugins/layer/layer.js"></script>
    <script type="text/javascript" src="/plugins/model/product.js"></script>

</head>
<body>

<%--弹出框对应的按钮--%>
<div id="bb">
    <a href="javascript:void(0);" data-method="saveData" class="easyui-linkbutton c3" data-options="plain:true">保存</a>
    <a href="javascript:void(0);" onclick="$('#dlg').dialog('close')" class="easyui-linkbutton c4"
       data-options="plain:true">关闭</a>
</div>
<%--
        easyui的时候，查看默认属性与事件
       $.fn.{plugin}.defaults
        弹出框html代码
    --%>
<div id="dlg" class="easyui-dialog" data-options="width:300,height:310,buttons:'#bb'">
    <form id="ff" method="post" enctype="multipart/form-data">
        <input id="eid" type="hidden" name="id">
        <table cellpadding="5">
            <tr>
                <td>名称:</td>
                <td><input class="easyui-textbox" type="text" name="name"></input></td>
            </tr>
            <tr>
                <td>颜色:</td>
                <td>
                    <input class="easyui-textbox" type="color"   name="color"></input>
                </td>
            </tr>
            <tr>
                <td>成本价:</td>
                <td><input class="easyui-textbox" type="text" name="costPrice"></input></td>
            </tr>
            <tr>
                <td>销售价:</td>
                <td><input class="easyui-textbox" type="text" name="salePrice"></input></td>
            </tr>
            <tr>
                <td>单位:</td>
                <td>
                    <input class="easyui-combobox" name="unit.id"
                           data-options="
                        url: '/util/findUnits',
                        method: 'get',
                        valueField:'id',
                        textField:'name',
                        panelHeight:'auto'     <%--自适应高度--%>
                    ">
                </td>
            </tr>
            <tr>
                <td>品牌:</td>
                <td>
                    <input class="easyui-combobox" name="brand.id"
                           data-options="
                        url: '/util/findBrands',
                        method: 'get',
                        valueField:'id',
                        textField:'name',
                        panelHeight:'auto'     <%--自适应高度--%>
                    ">
                </td>
            </tr>
            <tr>
                <td>类型:</td>
                <td>
                    <input class="easyui-combobox" name="types.id"
                           data-options="
                        url: '/util/findTypes',
                        method: 'get',
                        valueField:'id',
                        textField:'name',
                        groupField:'group'
                    ">
                </td>
            </tr>
            <tr>
                <td>产品图片:</td>
                <td>
                    <input class="easyui-filebox" name="file" data-options="prompt:'图片', buttonText: '选择图片'">
                </td>
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
        <a href="javascript:void(0);" data-method="delete" class="easyui-linkbutton" iconCls="icon-remove"
           plain="true"></a>
    </div>
</div>

<table id="dg"  class="easyui-datagrid" title="员工管理"
           data-options="url:'/product/datagrid',
           fit:true,
           pagination:true,
           rownumbers:true,
           toolbar:'#tb',
           onLoadSuccess:loadSuccess  <%-- 当页面渲染完毕之后才执行onLoadSuccess对应的函数--%>
        ">
        <thead>
        <tr>
            <th data-options="field:'id',checkbox:true,width:'2%'">ID</th>
            <th data-options="field:'name',width:'12%',align:'center'">名称</th>
            <th data-options="field:'color',width:'12%',align:'center',formatter:formatterColor">颜色</th>
            <th data-options="field:'pic',width:'14%',align:'center',formatter:formatterImage">图片</th>
            <th data-options="field:'costPrice',width:'12%',align:'center'">成本价</th>
            <th data-options="field:'salePrice',width:'12%',align:'center'">销售价</th>
            <th data-options="field:'types',width:'12%',align:'center',formatter:formatterTypes">类型</th>
            <th data-options="field:'unit',width:'12%',align:'center',formatter:formatterUnit">单位</th>
            <th data-options="field:'brand',width:'12%',align:'center',formatter:formatterBrand">品牌</th>
        </tr>
        </thead>
    </table>
</body>
</html>
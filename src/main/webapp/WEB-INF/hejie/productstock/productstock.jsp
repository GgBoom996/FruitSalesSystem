<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/hejie/common/header.jsp"%>
    <script type="text/javascript" src="/plugins/model/productstock.js"></script>
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
                    <td>num:</td>
                    <td><input class="easyui-textbox" type="text" name="num"  ></input></td>
                </tr>
                             <tr>
                    <td>amount:</td>
                    <td><input class="easyui-textbox" type="text" name="amount"  ></input></td>
                </tr>
                             <tr>
                    <td>price:</td>
                    <td><input class="easyui-textbox" type="text" name="price"  ></input></td>
                </tr>
                             <tr>
                    <td>incomedate:</td>
                    <td><input class="easyui-textbox" type="text" name="incomedate"  ></input></td>
                </tr>
                             <tr>
                    <td>warning:</td>
                    <td><input class="easyui-textbox" type="text" name="warning"  ></input></td>
                </tr>
                             <tr>
                    <td>topnum:</td>
                    <td><input class="easyui-textbox" type="text" name="topnum"  ></input></td>
                </tr>
                             <tr>
                    <td>bottomnum:</td>
                    <td><input class="easyui-textbox" type="text" name="bottomnum"  ></input></td>
                </tr>
                             <tr>
                    <td>productId:</td>
                    <td><input class="easyui-textbox" type="text" name="productId"  ></input></td>
                </tr>
                             <tr>
                    <td>depotId:</td>
                    <td><input class="easyui-textbox" type="text" name="depotId"  ></input></td>
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
           url:'/productstock/datagrid'">
        <thead>
            <tr>
                 <th data-options="field:'id',checkbox:true,width:'1%'">ID</th>
                                <th data-options="field:'num',width:100,align:'center'">数量</th>
                                <th data-options="field:'amount',width:100,align:'center'">小计</th>
                                <th data-options="field:'price',width:100,align:'center'">单价</th>
                                <th data-options="field:'incomeDate',width:100,align:'center'">录入时间</th>
                                <th data-options="field:'warning',width:100,align:'center'">预警线</th>
                                <th data-options="field:'topNum',width:100,align:'center'">上限数量</th>
                                <th data-options="field:'bottomNum',width:100,align:'center'">下限数量</th>
                                <th data-options="field:'product',width:100,align:'center'">产品</th>
                                <th data-options="field:'depot',width:100,align:'center'">仓库</th>
                            </tr>
        </thead>
    </table>
</body>
</html>
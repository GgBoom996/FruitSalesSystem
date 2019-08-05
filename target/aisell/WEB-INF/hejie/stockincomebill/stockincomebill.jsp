<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/hejie/common/header.jsp"%>
    <script type="text/javascript" src="/plugins/model/stockincomebill.js"></script>
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
                    <td>vdate:</td>
                    <td><input class="easyui-textbox" type="text" name="vdate"  ></input></td>
                </tr>
                             <tr>
                    <td>totalamount:</td>
                    <td><input class="easyui-textbox" type="text" name="totalamount"  ></input></td>
                </tr>
                             <tr>
                    <td>totalnum:</td>
                    <td><input class="easyui-textbox" type="text" name="totalnum"  ></input></td>
                </tr>
                             <tr>
                    <td>inputtime:</td>
                    <td><input class="easyui-textbox" type="text" name="inputtime"  ></input></td>
                </tr>
                             <tr>
                    <td>auditortime:</td>
                    <td><input class="easyui-textbox" type="text" name="auditortime"  ></input></td>
                </tr>
                             <tr>
                    <td>status:</td>
                    <td><input class="easyui-textbox" type="text" name="status"  ></input></td>
                </tr>
                             <tr>
                    <td>supplierId:</td>
                    <td><input class="easyui-textbox" type="text" name="supplierId"  ></input></td>
                </tr>
                             <tr>
                    <td>auditorId:</td>
                    <td><input class="easyui-textbox" type="text" name="auditorId"  ></input></td>
                </tr>
                             <tr>
                    <td>inputuserId:</td>
                    <td><input class="easyui-textbox" type="text" name="inputuserId"  ></input></td>
                </tr>
                             <tr>
                    <td>keeperId:</td>
                    <td><input class="easyui-textbox" type="text" name="keeperId"  ></input></td>
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
           url:'/stockincomebill/datagrid'">
        <thead>
            <tr>
                 <th data-options="field:'id',checkbox:true,width:'1%'">ID</th>
                                <th data-options="field:'vdate',width:100,align:'center'">保存时间</th>
                                <th data-options="field:'totalAmount',width:100,align:'center'">总计</th>
                                <th data-options="field:'totalNum',width:100,align:'center'">总数</th>
                                <th data-options="field:'inputTime',width:100,align:'center'">录入时间</th>
                                <th data-options="field:'auditorTime',width:100,align:'center'">审核时间</th>
                                <th data-options="field:'status',width:100,align:'center'">状态</th>
                                <th data-options="field:'supplier',width:100,align:'center'">供应商</th>
                                <th data-options="field:'auditor',width:100,align:'center'">审核人</th>
                                <th data-options="field:'inputUser',width:100,align:'center'">录入人</th>
                                <th data-options="field:'keeper',width:100,align:'center'">管理者</th>
                                <th data-options="field:'depot',width:100,align:'center'">仓库</th>
                            </tr>
        </thead>
    </table>
</body>
</html>
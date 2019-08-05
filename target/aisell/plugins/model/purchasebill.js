function formatterSupplier(v,r){
    if(v){
        return v.name;
    }
}
function formatterBuyer(v,r){
    if(v){
        return v.username;
    }
}
function formatterStatus(v,r){
    switch (v){
        case 1:
            return "<span style='color:green;'>已审</span>";
            break;
        case 0:
            return "<span style='color:blue;'>待审</span>";
            break;
        case -1:
            return "<span style='color:grey'><s>作废</s></span>";
            break;
    }
}

$(function () {
    //给所有的a标签都注册事件一个事件
    $("a").on("click", function () {
        var method = $(this).data("method");
        if(method){
            itsource[method]();
        }
    });

    //获取datagrid的jquery对象
    var dg = $("#dg");
    //获取高级查询form表单的jquery对象
    var searchForm = $("#searchForm");
    //弹出框对应的jquery对象
    var dlg = $("#dlg");
    //弹出框中的form表单对应的jquery对象
    var ff = $("#ff");
    itsource = {
        //高级查询
        "search":function(){
            //把form表单中所有的元素标签打成json格式   name作为key，填写的值作为value
            var jsonObj = searchForm.serializeObject();
            //加载数据
            dg.datagrid('load',jsonObj);
        },
        //选中数据进行删除
        "remove":function(){
            //获取选中的数据
            var rows = dg.datagrid("getSelections");
            if(!rows.length){
                //如果没有选中数据，提示用户信息
                $.messager.alert("错误", "请选中数据进行删除!!", "error");
                return;
            }
            //定义一个数组，该数组只存储id
            var ids = [];
            //循环迭代选中的行，获取每个对象的id
            $.each(rows, function (i, o) {
                ids.push(o.id);
            });

            $.messager.confirm('提示', '您确认离我而去吗?', function(r){
                if (r){
                    $.get("/purchaseBill/remove", {"ids": ids.toString()}, function (result) {
                        if(result.success){
                            //刷新界面
                            dg.datagrid("reload");
                        }else{
                            $.messager.alert("错误", result.msg, "error");
                        }
                    });
                }
            });



        },
        //添加按钮，弹出窗体
        "add":function(){
            //清空form表单
            ff.form("clear");
            //弹出窗体，居中，并且设置标题
            dlg.dialog("open").dialog("setTitle","添加").dialog("resize",{
                width:820,
                height:550
            }).dialog("center");
            //清空明细
            billItems.datagrid("loadData", []);
        },
        //修改按钮，弹出窗体
        "edit":function(){
            //获取datagrid选中的数据
            var row = dg.datagrid("getSelected");
            if(!row){
                $.messager.alert("错误", "请选中数据进行修改!!", "error");
                return;
            }
            //先清空再回显
            ff.form("clear");
            if(row.buyer){
                row["buyer.id"] = row.buyer.id;
            }
            if(row.supplier){
                row["supplier.id"] = row.supplier.id;
            }
            //数据进行回显
            ff.form("load", row);
            //弹出窗体，居中，并且设置标题
            dlg.dialog("open").dialog("setTitle","修改").dialog("resize",{
                width:820,
                height:550
            }).dialog("center");
            var items = [];
            $.extend(items, row.items);
            billItems.datagrid("loadData", items);
        },
        //保存/修改数据
        "save":function(){
            var url = "/purchaseBill/save";
            if($("#eid").val()){
                url = "/purchaseBill/update";
            }
            $('#ff').form('submit', {
                url: url,
                onSubmit: function(param){
                    //获取当前列表所有的行
                    var rows = billItems.datagrid("getRows");
                    for(var i=0;i<rows.length;i++){
                        //结束编辑
                        billItems.datagrid("endEdit", i);
                        var row = rows[i];
                        param["items[" + i + "].product.id"] = row.product.id;//设置产品
                        param["items[" + i + "].price"] = row.price;//设置单价
                        param["items[" + i + "].num"] = row.num;//设置数量
                        param["items[" + i + "].descs"] = row.descs;//设置数量
                    }

                },
                success:function(data){
                    //把json字符串转为json对象
                    var result = $.parseJSON(data);
                    if(result.success){
                        //关闭窗体
                        dlg.dialog("close");
                        //刷新页面
                        dg.datagrid("reload");
                    }else{
                        $.messager.alert("错误", result.msg, "error");
                    }
                }
            });

        }
    }





    //采购订单明细对应的jquery对象
    var billItems = $("#billItems"),
        //默认行对应的值（都是空字符串）
        defaultRow = { product: "", color: "", img: "", num: "", price: "", amount: "",descs:"" },
        //插入的位置（在底部进行插入）
        insertPosition = "bottom";

    //datagrid的初始化方法
    var dgInit = function () {

        //获取列
        var getColumns = function () {
            var result = [];

            var normal = [
                {
                    field: 'product', title: '产品', width: 180,align:'center',
                    editor: {
                        type: "combobox", /*使用组件的类型*/
                        options: { /*该组件对应的属性*/
                            url:'/util/findProduts',
                            method:'get',
                            valueField:'id',
                            textField:'name',
                            panelHeight:'auto'
                        }
                    },
                    formatter:function(v,r){
                        return v.name;
                    }
                },
                {
                    field: 'color', title: '颜色', width: 180,align:'center',
                    formatter:function(v,r){
                        return "<div style='background: "+r.product.color+";width: 20px;height: 20px;'></div>"
                    }
                },
                {
                    field: 'img', title: '图片', width: 180,align:'center',
                    formatter:function(v,r){
                        if(r.product.smallPic){
                            return "<img src='"+r.product.smallPic+"' alt='无图片'/>"
                        }
                    }
                },
                {
                    field: 'num', title: '数量', width: 180,align:'center', editor: {
                        type: "numberbox"
                    }
                },
                {
                    field: 'price', title: '单价', width: 180,align:'center', editor: {
                        type: "numberbox"
                    }
                },
                {
                    field: 'amount', title: '小计', width: 180,align:'center',formatter:function(v,r){
                        if(r.num && r.price){
                            return (r.num * r.price).toFixed(2);
                        }
                    }
                },
                {
                    field: 'descs', title: '描述', width: 180,align:'center', editor: {
                        type: "text"
                    }
                }
            ];
            result.push(normal);

            return result;
        };

        //datagrid属性配置
        var options = {
            rownumbers: true,  /*是否显示行号*/
            fitColumns: true, /*自适应列*/
            fit: true, /*自适应窗口*/
            singleSelect: true,  /*单选*/
            columns:getColumns(),
            title:'明细编辑',
            toolbar:'#billItemsToolBar',
            //表示开启单元格编辑功能
            enableCellEdit: true
        };

        //js创建的核心代码
        billItems.datagrid(options);

    };


    dgInit();




    //获取插入的位置
    var getInsertRowIndex = function () {
        return insertPosition == "top" ? 0 : billItems.datagrid("getRows").length;
    }
    //明细添加注册事件
    $("#btnInsert").click(function () {
        var targetIndex = getInsertRowIndex(),
            targetRow = $.extend({}, defaultRow);
        billItems.datagrid("insertRow", { index: targetIndex, row: targetRow });
        billItems.datagrid("editCell", { index: targetIndex, field: "product" });
    });

    $("#btnRemove").click(function () {
        //获取即将要删除的对象
        var row = billItems.datagrid("getSelected");
        if(!row){
            $.messager.alert("错误", "亲!请选中进行删除!!", "error");
            return;
        }
        //根据对象获取对应的索引
        var index = billItems.datagrid("getRowIndex", row);
        billItems.datagrid("deleteRow", index);
    });
});
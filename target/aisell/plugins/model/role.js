function formatterPermissions(v,r,i){
    var permissions = [];
    $.each(v, function (i, o) {
        permissions.push(o.name);
    });
    return permissions;
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
    //所有权限对应的jquery对象
    var allPermissions = $("#allPermissions");
    //已有权限对应的jquery对象
    var myPermissions = $("#myPermissions");
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
                    $.get("/role/remove", {"ids": ids.toString()}, function (result) {
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
                width:850,
                height:510
            }).dialog("center");
            //清空已有权限列表
            myPermissions.datagrid("loadData", []);
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
            //数据进行回显
            ff.form("load", row);
            //弹出窗体，居中，并且设置标题
            dlg.dialog("open").dialog("center").dialog("setTitle","修改").dialog("resize",{
                width:850,
                height:510
            });
            //定义个空数组
            var permissions = [];
            $.extend(permissions, row.permissions);
            myPermissions.datagrid("loadData",  permissions);


        },
        //保存/修改数据
        "save":function(){
            var url = "/role/save";
            if($("#eid").val()){
                url = "/role/update";
            }
            $('#ff').form('submit', {
                url: url,
                onSubmit: function(param){
                    var rows = myPermissions.datagrid("getRows");
                    $.each(rows, function (i, o) {
                        //传递额外的数据
                        param["permissions["+i+"].id"] = o.id;
                    });
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

        },
        //添加一行
        "appendRow":function(index,row){
            //添加之前，应该判断一下myPermission中有没有即将要添加的对象，如果有，阻止添加，如果没有，正常添加
            var rows = myPermissions.datagrid("getRows");//获取已有权限所有的列表数据
            for(var i=0;i<rows.length;i++){
                if(rows[i].id === row.id){
                    $.messager.show({
                        title:'提示',
                        msg:'<h1 style="color: red">亲!重复数据不能添加2次!</h1>',
                        showType:'show'
                    });
                    return;
                }
            }
            myPermissions.datagrid("appendRow", row);
        },
        //删除一行
        "removeRow":function(index,row){
            myPermissions.datagrid("deleteRow", index);
        }
    }











    //创建所有的权限easyui组件
    allPermissions.datagrid({
        border: false,
        fit: true,
        striped: true,
        rownumbers: true,
        fitColumns: true,
        pagination: true,
        title: "权限列表",
        url: '/permission/datagrid',
        onDblClickRow:itsource.appendRow
    });

    //创建已有权限easyui组件
    myPermissions.datagrid({
        border: false,
        fit: true,
        striped: true,
        rownumbers: true,
        fitColumns: true,
        title: "已有权限",
        onDblClickRow:itsource.removeRow
    })

});
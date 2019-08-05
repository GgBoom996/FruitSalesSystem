$(function () {
    //给所有a标签都注册一个事件
    $("a").on("click", function () {
        //动态获取data-method属性对应的值
        var method = $(this).data("method");
        //method不能为空
        if(method){
            //动态触发事件

            itsource[method]();
        }
    });
    //datagrid对应的jquery对象
    var dg = $("#dg");
    //弹出框对应的jquery对象
    var dlg = $("#dlg");
    //form表单对应的jquery对象(弹出框)
    var ff = $("#ff");
    itsource = {
        //高级查询
        "search":function(){
            //把form表单元素，直接封装成一个json对象
            var jsonObj = $("#searchForm").serializeObject();
            //加载datagrid
            dg.datagrid('load',jsonObj);
        },
        //删除
        "delete":function(){
            //获取选中的行
            var rows = dg.datagrid("getSelections");
            //在js中认为false的值：0  false ""  null NaN  undefined
            if(!rows.length){
                $.messager.alert('操作错误','亲!请选中数据进行删除!','error');
                return;
            }
            //定义一个数组，该数组把所有的id都给装进来
            var ids = [];
            //循环数组中的所有数据
            $.each(rows, function (i, o) {
                //把id装进数组中
                ids.push(o.id);
            });
            $.messager.confirm('确认', '你确定要离我而去吗?', function(r){
                if (r){
                    $.get("/systemDictionaryType/delete",{"ids":ids.toString()},function(result){
                        if(result.success){
                            //刷新界面
                            dg.datagrid("reload");
                        }else{
                            $.messager.alert('失败',result.msg,'error');
                        }
                    });
                }
            });

        },
        //添加按钮，弹出窗体
        "add":function(){
            //清空form表单中所有的值
            ff.form("clear");
            //弹出窗体，居中，并且设置标题,动态修改高度
            dlg.dialog("open").dialog("center").dialog("setTitle","添加员工").dialog("resize",{
                height:350
            });
        },
        //修改按钮，弹出窗体
        "edit":function(){
            //获取即将要修改的数据（单个对象）
            var row = dg.datagrid("getSelected");
            //没有选中就会返回null
            if(!row){
                $.messager.alert('操作错误','亲!请选中数据进行修改!','error');
                return;
            }
            //清空form表单
            ff.form("clear");
            //回显操作
            ff.form('load',row);
            //弹出窗体，居中，设置标题  动态修改它的宽度和高度
            dlg.dialog("open").dialog("center").dialog("setTitle","修改员工").dialog("resize",{
                height:270
            });
        },
        //保存数据
        "saveData":function(){
            var url = "/systemDictionaryType/save";
            if($("#eid").val()){
                url = "/systemDictionaryType/update?cmd=update";
            }
            //提交form表单
            ff.form('submit', {
                url:url,
                onSubmit: function(){//提交之前先做验证
                    return ff.form("validate");//验证通过之后才返回true，否则返回false，false它会阻止你提交
                },
                success:function(data){
                    //把json字符串转为对象
                    var result = $.parseJSON(data);
                    if(result.success){
                        //关闭窗体
                        dlg.dialog("close");
                        //刷新界面
                        dg.datagrid("reload");
                    }else{
                        $.messager.alert('操作错误',result.msg,'error');
                    }
                }
            });
        }

    }
});
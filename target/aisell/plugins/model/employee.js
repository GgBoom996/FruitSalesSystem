/**
 * 格式化图片
 * @param v   当前属性对应的值
 * @param r   当前行对应的值
 * @param i   索引
 */
function formatterHeadImage(v,r,i){
    return "<img src='"+v+"' width=40 alt='无头像'/>"
}

/**
 * 格式化部门
 * @param v
 * @param r
 * @param i
 */
function formatterDepartment(v,r,i){
    if(v){// 在js中 0 false  ""  NaN  undefined null  为false，其他全部为true
        return v.name;
    }
}

$(function () {
    /*自定义验证规则*/
    $.extend($.fn.validatebox.defaults.rules, {
        checkName: {
            validator: function(value, param){
                var html = $.ajax({
                    url: "/employee/checkName",
                    data:{
                      username:value,
                      id:$("#eid").val()
                    },
                    async: false //同步请求
                }).responseText;//返回一个纯文本
                return html==="true";
            },
            message: '用户名已重复!'
        }
    });



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
                    $.get("/employee/remove", {"ids": ids.toString()}, function (result) {
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
            //显示密码
            $("tr[pwd]").show();

            //启用密码框的验证
            $("#password").textbox("enableValidation");
            $("#configPassword").textbox("enableValidation");

            //弹出窗体，居中，并且设置标题
            dlg.dialog("open").dialog("center").dialog("setTitle","添加员工").dialog("resize",{
                width:320,
                height:350
            });
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
            if(row.department){//动态添加department.id这个属性
                row["department.id"] = row.department.id;
            }
            //数据进行回显
            ff.form("load", row);
            //修改的时候应该影藏密码
            $("tr[pwd]").hide();
            //禁用密码框的验证
            $("#password").textbox("disableValidation");
            $("#configPassword").textbox("disableValidation");

            //弹出窗体，居中，并且设置标题
            dlg.dialog("open").dialog("center").dialog("setTitle","修改员工").dialog("resize",{
                width:320,
                height:270
            });
        },
        //保存/修改数据
        "save":function(){
            var url = "/employee/save";
            if($("#eid").val()){
                url = "/employee/update";
            }
            $('#ff').form('submit', {
                url: url,
                onSubmit: function(){
                    return ff.form("validate");//当验证全部通过直接返回true，否则返回false，阻止你提交
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
});
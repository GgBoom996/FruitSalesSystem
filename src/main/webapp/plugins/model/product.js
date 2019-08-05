/**
 * 格式化颜色
 * @param v
 * @param r
 */
function formatterColor(v,r){
    if(v){
        return "<div style='background-color: "+v+";width: 20px;height: 20px'></div>"
    }
}

/**
 * 格式化产品类型
 * @param v
 * @param r
 */
function formatterTypes(v,r){
    return v.name;
}
/**
 * 格式化单位
 * @param v
 * @param r
 */
function formatterUnit(v,r){
    return v.name;
}

/**
 * 格式化品牌
 * @param v
 * @param r
 */
function formatterBrand(v,r){
    return v.name;
}

/**
 * 格式化图片
 */
function formatterImage(v,r,i){
    return "<span id='layerPhotos"+i+"'><img  layer-src=" + v + " src=" + r.smallPic + " ></span>";
}
function loadSuccess(data){
    for(var i=0;i<data.rows.length;i++){

        layer.photos({
            photos: '#layerPhotos'+i
            ,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
        });
    }
}

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
                    $.get("/product/delete",{"ids":ids.toString()},function(result){
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
            dlg.dialog("open").dialog("center").dialog("setTitle","添加产品").dialog("resize",{
                height:450
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
            //单位回显
            if(row.unit){
                row["unit.id"] = row.unit.id;
            }
            //品牌回显
            if(row.brand){
                row["brand.id"] = row.brand.id;
            }
            //产品类型回显
            if(row.types){
                row["types.id"] = row.types.id;
            }

            //回显操作
            ff.form('load',row);
            //弹出窗体，居中，设置标题  动态修改它的宽度和高度
            dlg.dialog("open").dialog("center").dialog("setTitle","修改员工").dialog("resize",{
                height:450
            });
        },
        //保存数据
        "saveData":function(){
            var url = "/product/save";
            if($("#eid").val()){
                url = "/product/update?cmd=update";
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
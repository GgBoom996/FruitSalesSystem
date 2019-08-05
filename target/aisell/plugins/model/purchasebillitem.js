$(function () {
    //给所有的a标签都注册事件一个事件
    $("a").on("click", function () {
        var method = $(this).data("method");
        if(method){
            itsource[method]();
        }
    });

    var dg = $('#tt');
    itsource = {
        //高级查询
        "search":function(){
            //把form表单中所有的元素标签打成json格式   name作为key，填写的值作为value
            var jsonObj = $("#searchForm").serializeObject();
            //加载数据
            dg.datagrid('load',jsonObj);
        },
        "3d":function(){
            $("#dlg").dialog("open").dialog("center");
            //获取form表单中的数据
            var jsonObj = $("#searchForm").serializeObject();
            $.get("/purchaseBillItem/findGroups",jsonObj, function (result) {
                Highcharts.chart('container', {
                    chart: {
                        type: 'pie',
                        options3d: {
                            enabled: true,
                            alpha: 45,
                            beta: 0
                        }
                    },
                    title: {
                        text: ''
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    credits: {
                        enabled: false
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            depth: 35,
                            dataLabels: {
                                enabled: true,
                                format: '{point.name}'
                            }
                        }
                    },
                    series: [{
                        type: 'pie',
                        name: '市场占有比',
                        data: result
                    }]
                });
            });
        }
    }


    dg.datagrid({
        title:'采购订单报表',
        fit:true,
        rownumbers:true,
        fitColumns:true,
        toolbar:'#tb',
        url:'/purchaseBillItem/findItems',
        columns:[[
            {field:'id',title:'编号',width:100},
            {field:'supplierName',title:'供应商',width:100},
            {field:'buyerName',title:'采购员',width:100},
            {field:'productName',title:'产品',width:100},
            {field:'productTypeName',title:'产品类型',width:100},
            {field:'vdate',title:'日期',width:100},
            {field:'num',title:'数量',width:100},
            {field:'price',title:'单价',width:100},
            {field:'amount',title:'小计',width:100},
            {field:'status',title:'状态',width:100,formatter:function(v,r){
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
                }}
        ]],
        groupField:'groupName',
        view: groupview,
        groupFormatter:function(value, rows){
            //总数量
            var totalNum = 0;
            //总金额
            var totalPrice = 0;
            $.each(rows, function (i, o) {
                totalNum += o.num;
                totalPrice += o.amount;
            });
            return value + ' - ' + rows.length
                + '条数据<span style="color: green">共'+totalNum
                +'件商品</span><span style="color: red">总金额:'+totalPrice
                +'</span>';
        }
    });
});
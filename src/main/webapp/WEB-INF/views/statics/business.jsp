<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <%@ include file="../common/common_css.jsp" %>

    <title>愿景建筑OA系统</title>

    <%@ include file="../common/common_tag.jsp" %>

    <script src="${domain}/blue/assets/js/modernizr.min.js"></script>


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${domain}/blue/assets/html5shiv.js"></script>
    <script src="${domain}/blue/assets/respond.min.js"></script>
    <![endif]-->

</head>


<body class="fixed-left">

<!-- Begin page -->
<div id="wrapper">

    <!-- Top Bar Start -->
    <%@ include file="../common/header.jsp" %>
    <%@ include file="../common/sys_left.jsp" %>
</div>
<!-- Top Bar End -->
<div>
    <div>
        <div class="content-page">
            <!-- Start content -->
            <div class="content">
                <div class="container">

                    <!-- Page-Title -->
                    <div class="row">
                        <div class="col-sm-12">
                            <h4 class="pull-left page-title">统计管理</h4>
                            <ol class="breadcrumb pull-right">
                                <li><a href="#">公司利润招商维度</a></li>
                                <li class="active">统计管理</li>
                            </ol>
                        </div>
                    </div>


                    <div class="row">
                        <div class="col-lg-12 col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 id="panel-title" class="panel-title"></h3>
                                </div>
                                <div class="panel-body">
                                    <div class="row">

                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <ul id="checkul" class="nav nav-tabs navtab-bg" style="border-bottom: 2px solid #EED8AE;margin-left: 10px;margin-bottom:20px">
                                                <li onclick="changestatus('1')" id="type1" class="active "><a href="#jxz" data-toggle="tab" aria-expanded="true"> <span class="visible-xs"><i class="fa fa-home"></i></span> <span
                                                        class="hidden-xs">图表</span>
                                                </a></li>
                                                <li onclick="changestatus('2')" id="type2" class=""><a href="#wks" data-toggle="tab" aria-expanded="true"> <span class="visible-xs"><i class="fa fa-user"></i></span> <span
                                                        class="hidden-xs">表格</span>
                                                </a></li>
                                            </ul>
                                            <div class="panel panel-default">
                                                <form id="form">
                                                    <div class="form-group col-sm-3 col-md-3 col-xs-3 pull-left">
                                                        <input type="text" placeholder="请输入建档时间" class="form-control" name="projectCreationTime">
                                                    </div>

                                                    <div class="form-group col-sm-1 col-md-1 col-xs-1 pull-left">
                                                        <button id="query" type="button" style="position: absolute;right: -10px;"
                                                                class="btn btn-primary waves-effect waves-light m-b-5">
                                                            <i class="ion-ios7-search"></i> 查询
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="col-md-12 col-sm-12 col-xs-12" id = "datatable_div" style="display: none">
                                                <table id="datatable"
                                                       class="table table-hover mails dataTable no-footer ">
                                                    <thead>
                                                    <tr>
                                                        <th nowrap="nowrap">序号</th>
                                                        <th nowrap="nowrap">客户来源</th>
                                                        <th nowrap="nowrap">销售费用</th>
                                                        <th nowrap="nowrap">成本总额</th>
                                                        <th nowrap="nowrap">利润</th>
                                                        <th nowrap="nowrap">应收款</th>
                                                        <th nowrap="nowrap">已回款</th>
                                                    </tr>
                                                    </thead>
                                                </table>
                                            </div>
                                            <div class="col-md-12 col-sm-12 col-xs-12" id="echarts_imge_div">
                                                <div id="huizong" style="padding-top: 10px;padding-left: 60px;">
                                                    <table> <tr><td>总销售额：</td><td id="sale">0</td><td>总成本：</td><td id="cost">0</td><td>总利润：</td><td id="commission">0</td></tr></table>
                                                </div>
                                                <div id="echarts_imge" style="height:600px; margin: 0; "></div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Row -->

            </div>
            <!-- container -->

        </div>


        <!-- ========== Left Sidebar Start ========== -->
        <%@ include file="../common/footer.jsp" %>

    </div>
    <!-- ============================================================== -->
    <!-- End Right content here -->
    <!-- ============================================================== -->



</div>
<!-- Begin page -->
<div id="wrapper">

    <!-- Top Bar Start -->
    <%@ include file="../common/header.jsp" %>
    <%@ include file="../common/sys_left.jsp" %>
</div>
<!-- Top Bar End -->


<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-gl/dist/echarts-gl.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-stat/dist/ecStat.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/dataTool.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/map/js/china.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/map/js/world.js"></script>
<%--        <script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=xfhhaTThl11qYVrqLZii6w8qE5ggnhrY&__ec_v__=20190126"></script>--%>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/bmap.min.js"></script>

<!-- END wrapper -->
<script>
    var resizefunc = [];
</script>
<%@ include file="../common/common_js.jsp"%>
<%@ include file="../common/formajax_js.jsp"%>
<script type="text/javascript">

    function changestatus(status){
        if(status == '1'){
            $("#echarts_imge_div").show();
            $("#datatable_div").hide();
        }else{
            $("#datatable_div").show();
            $("#echarts_imge_div").hide();
        }
    }
    var functionlist = [{targets:[2],
        mRender:function(data) {
            var result = "";
            if(data){
                result = data/100;
            }else{
                result = "";
            }
            return result;
        }
    },{targets:[3],
        mRender:function(data) {
            var result = "";
            if(data){
                result = data/100;
            }else{
                result = "";
            }
            return result;
        }
    },{targets:[4],
        mRender:function(data) {
            var result = "";
            result = data.saleAmount - data.costAmount;
            if(result){
                result = result/100;
            }else{
                result = "";
            }
            return result;
        }
    },{targets:[5],
        mRender:function(data) {
            var result = "";
            if(data){
                result = data/100;
            }else{
                result = "";
            }
            return result;
        }
    },{targets:[6],
        mRender:function(data) {
            var result = "";
            if(data){
                result = data/100;
            }else{
                result = "";
            }
            return result;
        }
    }]
    function initTbale(){
        //参数  $("form").serialize() 获取form表单的输入参数并且序列化成json    datatable 需要渲染的table id  ${base}/admin/producelistinfo.html 其请求数据的路径
        //,produceName,price, 需要显示的列   functionlist显示的列的格式
        var par = $("form").serialize();
        initBaseTable(par,"datatable","${base}/data/settlement/getBusinessEchartInfoPage",",eventDate,saleAmount,costAmount,,amountReceivable,amountReturned",functionlist);
    }
    $(document).ready(function(){
        initImage();
        initTbale();
    });
    $("#query").click(function(){
        initImage();
        initTbale();
    });
    function initImage(){
        var par = $("form").serialize();
        baseCallBackAJAX("post","${base}/data/settlement/getBusinessEchartInfo",par,"json","getData(data)");
    }
    function getData(res){
        var app = {};
        var option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {
                // data: ['蒸发量', '降水量', '平均温度']
                data: ['设计成本', '销售额','应收款','已回款', '利润']
            },
            xAxis: [
                {
                    type: 'category',
                    data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
                    axisPointer: {
                        type: 'shadow'
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '销售成本额',
                    min: 0,
                    max: 250,
                    interval: 50,
                    axisLabel: {
                        formatter: '{value} ￥'
                    }
                },
                {
                    type: 'value',
                    name: '利润额',
                    min: 0,
                    max: 25,
                    interval: 5,
                    axisLabel: {
                        formatter: '{value} ￥'
                    }
                }
            ],
            series: [
                {
                    name: '设计成本',
                    type: 'bar',
                    data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
                },
                {
                    name: '销售额',
                    type: 'bar',
                    data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
                },
                {
                    name: '利润',
                    type: 'line',
                    yAxisIndex: 1,
                    data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
                }
            ]
        };
        if(res.status=="0"){
            option.series = res.value.series;
            option.yAxis[0].min = res.value.leftYMin;
            option.yAxis[0].max = res.value.leftMax;
            option.yAxis[0].interval = res.value.leftInterval;
            option.yAxis[1].min = res.value.rigthYMin;
            option.yAxis[1].max = res.value.rigthMax;
            option.yAxis[1].interval = res.value.rightInterval;
            option.xAxis[0].data = res.value.dataStr;
            console.info(option);
            initData(option);
            $("#sale").text(res.value.allSale);
            $("#cost").text(res.value.allCost);
            $("#commission").text((res.value.allSale - res.value.allCost));
        }else{
            initData(option);
        }
    }

    function initData(option){
        var dom = document.getElementById("echarts_imge");
        var myChart = echarts.init(dom);

        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
    }
</script>

</body>
</html>
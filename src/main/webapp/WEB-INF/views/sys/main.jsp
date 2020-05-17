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
        <div class="content-page">
            <!-- Start content -->
            <div class="content">
                <div class="container">
                    <div class="row">
                        <div class="pull-left col-md-6 col-sm-6 col-lg-3" style="margin-left: 80px">
                            <div class="mini-stat clearfix bx-shadow bg-info">
                                <span class="mini-stat-icon"><i class="ion-eye" id="chakan"></i></span>
<%--                                <div class="mini-stat-info text-right">--%>
<%--                                    <div style="background-color: #29b6f6 !important" class="">点击查看</div>--%>
<%--                                </div>--%>
                                <div class="tiles-progress">
                                    <div class="m-t-20">
                                        <h5 class="text-uppercase text-white m-0">今日待审核<span class="pull-right" id = "countVal">0</span></h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="pull-left col-md-12 col-sm-12 col-lg-12" id="echarts_imge" style="height:600px; margin: 0"></div>
<%--                    <div style="text-align: center; vertical-align:middle; margin-top: 120px;height: 380px;font-size: 38px; font-family: 'Arial Negreta', 'Arial Normal', 'Arial'; font-weight: 700; font-style: normal;">欢迎使用愿景建筑OA系统</div>--%>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-gl/dist/echarts-gl.min.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-stat/dist/ecStat.min.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/dataTool.min.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/map/js/china.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/map/js/world.js"></script>
        <%--        <script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=xfhhaTThl11qYVrqLZii6w8qE5ggnhrY&__ec_v__=20190126"></script>--%>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/bmap.min.js"></script>


		<div>
            <%@ include file="../common/footer.jsp" %>
        </div>
        <!-- END wrapper -->
        <script>
            var resizefunc = [];
        </script>
<%@ include file="../common/common_js.jsp"%>
        <%@ include file="../common/formajax_js.jsp"%>
        <script type="text/javascript">

            $("#chakan").click(function(){
                window.location.href="${base}/project/main-project/audit_project_list.html?chirld=${chirld}&parent=${parent}";
            });

            $(document).ready(function(){
                baseCallBackAJAX("post","${base}/data/settlement/getEchartInfo",null,"json","getData(data)");
                baseCallBackAJAX("post","${base}/project/main-project/counttodaySubDates",null,"json","countCallBack(data)");
            });
            function countCallBack(data){
                $("#countVal").text(data.value);
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
                        data: ['设计成本', '销售额', '利润']
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height: 100%">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>月销量</title>
</head>
<body style="height: 100%; margin: 0">
	<input type="hidden" id="cxt" value="${pageContext.request.contextPath }">
	<div id="container" style="width:1100px; height:700px;" ></div>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
       <script type="text/javascript" src="jquery-3.3.1.js"></script>
       <script type="text/javascript">
       
       $(function(){
   			aaa();
       });
       function aaa() {
    	   var cxt = $("#cxt").val();
  			var url = cxt+"/ShowTop10";
  			$.get(url,top10,"JSON");
  			function top10(res) {
				ddd(res.list);
			};
		};
		function ddd(list) {
			var dom = document.getElementById("container");
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
			var dataAxis = new Array();
			var data = new Array();
			for(var i=0;i<list.length;i++){
				dataAxis.push(list[i].goodsName+"      (月销量:"+list[i].monthSale+")");
				data.push(list[i].monthSale);
			}
			var yMax = 400;
			var dataShadow = [];
			for (var i = 0; i < data.length; i++) {
			    dataShadow.push(yMax);
			}
			
			option = {
			    title: {
			        text: '月销量前10的商品展示',
			    },
			    yAxis: {
			        data: dataAxis,
			        axisLabel: {
			            inside: true,
			            textStyle: {
			                color: '#fff'
			            }
			        },
			        axisTick: {
			            show: false
			        },
			        axisLine: {
			            show: true
			        },
			        z: 10
			    },
			    xAxis: {
			        axisLine: {
			            show: true
			        },
			        axisTick: {
			            show: false
			        },
			        axisLabel: {
			            textStyle: {
			                color: '#999'
			            }
			        }
			    },
			    dataZoom: [
			        {
			            type: 'inside',
			            yAxisIndex:0
			        }
			    ],
			    series: [
			        { // For shadow
			            type: 'bar',
			            itemStyle: {
			                normal: {color: 'rgba(0,0,0,0.05)'}
			            },
			            barGap:'-100%',
			            barCategoryGap:'40%',
			            data: dataShadow,
			            animation: false,
			            lobel:{
				        	show:true,
				        }
			        },
			        {
			            type: 'bar',
			            itemStyle: {
			                normal: {
			                    color: new echarts.graphic.LinearGradient(
			                        0, 0, 0, 1,
			                        [
			                            {offset: 0, color: '#83bff6'},
			                            {offset: 0.5, color: '#188df0'},
			                            {offset: 1, color: '#188df0'}
			                        ]
			                    )
			                },
			                emphasis: {
			                    color: new echarts.graphic.LinearGradient(
			                        0, 0, 0, 1,
			                        [
			                            {offset: 0, color: '#2378f7'},
			                            {offset: 0.7, color: '#2378f7'},
			                            {offset: 1, color: '#83bff6'}
			                        ]
			                    )
			                }
			            },
			            data: data
			        }
			    ]
			};
			
			// Enable data zoom when user click bar.
			var zoomSize = 6;
			myChart.on('click', function (params) {
			    console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
			    myChart.dispatchAction({
			        type: 'dataZoom',
			        startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
			        endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
			    });
			});;
			if (option && typeof option === "object") {
			    myChart.setOption(option, true);
			}
		};
			
	 </script>
</body>
</html>
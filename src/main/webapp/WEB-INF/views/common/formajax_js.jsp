<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<!--公用保存方法AJAX JS-->
	 <script type="text/javascript">
	<%--//type: 提交类型  ： POST,PUT,DELET,GET...
	//url：   ajax请求地址
	//formdata: 提交参数 form表单序列化参数
	//successurl： 操作成功后跳转地址
	//dataType ： 数据格式  ： json--%>
	 function baseAJAX(type,url,formdata,successurl,dataType){
		 $.ajax({
				type : type,
				url : url,
				data : formdata,
				dataType : dataType,
				success : function(data) {
				 if (data.status == 0) {
					 	successalertclosebyself(data,successurl);
					} else {
						erroralert(data);
					}  
				}
			});
	 }
	<%--//带回调函数的ajax方法
	//请求完成后 不进行跳转，进行拼接，或是做其他操作
	//callbackname：回调方法名称（以字符串形式 传入方法名称）--%>
	function baseCallBackAJAX(type,url,formdata,dataType,callbackname){
		 $.ajax({
				type : type,
				data : formdata,
				url : 	url,
				dataType : 'json',
				async : true,
				success : function(data) {
					eval(""+callbackname+""); 
				}
			});
	}

    function baseCallBackUploadAJAX(type,url,formdata,dataType,callbackname){
        $.ajax({
            type : type,
            data : formdata,
            url : 	url,
            dataType : 'json',
            cache: false,//上传文件无需缓存
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            success : function(data) {
                eval(""+callbackname+"");
            }
        });
    }

	 function baseAsyncCallBackAJAX(type,url,formdata,dataType,callbackname){
		 $.ajax({
				type : type,
				data : formdata,
				url : 	url,
				dataType : 'json',
				async: false,
				success : function(data) {
					eval(""+callbackname+""); 
				}
			});
	}
	<%--//带对话框回调函数的ajax方法--%>
	function baseQuestionCallBackAJAX(title,type,url,formdata,dataType,callbackname){ swal({
        title: title,
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        closeOnConfirm : true, //关闭按钮
        closeOnCancel : true
    },function(isConfirm) {
        if(isConfirm==true){
            baseCallBackAJAX(type,url,formdata,dataType,callbackname);
        }

    });

	 }
	<%--//基础跳转--%>
	 function baseWindowLoacation(url){
		 window.location.href = url;
	 }
	 function baseWindowOpenLoacation(url){
		 window.open(url);  
	 }
	<%--//基础对话框操作--%>
	 function baseQuestionAJAX(title,type,url,formdata,successurl,dataType){
		 swal({
	            title: title,
	            type: "warning",
	            showCancelButton: true,
	            confirmButtonColor: "#DD6B55",
	            confirmButtonText: "确定",
	            cancelButtonText: "取消",
	            closeOnConfirm : false, //关闭按钮
				closeOnCancel : true
	        },function(isConfirm) {
	        	if(isConfirm==true){
	        		baseAJAX(type,url,formdata,successurl,dataType)
	        	}
	        	
	        });
	 }
	<%--//基础对话框操作--%>
	 function baseQuestion(title,url){
		 swal({
	            title: title,
	            type: "warning",
	            showCancelButton: true,
	            confirmButtonColor: "#DD6B55",
	            confirmButtonText: "确定",
	            cancelButtonText: "取消",
	            closeOnConfirm : true, //关闭按钮
				closeOnCancel : true
	        },function(isConfirm) {
	        	if(isConfirm==true){
	        		baseWindowLoacation(url);
	        	}
	        	
	        });
	 }
	</script>
	 <script type="text/javascript">
	 <%--
	 checkid :最终存储隐藏域的id
	 checkboxbtnidarr　：所有checkbox  按钮 选项的id数组   例如：['bddg','wddg','yz']
	 valuearr:对应checkbox对应 值  例如：['1','2','3']
	 defultcheckboxbtnid:默认选中 checkbox  按钮 选项的id
	 defultvalue:默认选中对应的值
	 --%>
	 function initcheckbox(checkid,checkboxbtnidarr,valuearr,defultcheckboxbtnid,defultvalue){
		 
		 if(checkboxbtnidarr.length!=valuearr.length){
			 console.log("js代码错误:调用方法:initcheckbox  错误原因：checkboxidarr 与  valuearr 数组长度互不匹配！");
			 return;
		 }
		 if(defultcheckboxbtnid==null||defultcheckboxbtnid==''){
			 console.log("js代码错误:调用方法:initcheckbox  错误原因：defultcheckboxbtnid为空！");
			 return;
		 }
		 
		 if(defultvalue==null||defultvalue==''){
			 console.log("js代码错误:调用方法:initcheckbox  错误原因：defultvalue为空！");
			 return;
		 }
		 var checkidvalue = $("#"+checkid).val();
	 
		 if(checkidvalue==null||checkidvalue==''){
			 
			 $("#"+defultcheckboxbtnid).addClass("click");
			 $("#"+checkid).val(defultvalue);
		 }else{
			 for(var i=0;i<valuearr.length;i++){
				 if(checkidvalue==valuearr[i]){
					 $("#"+checkboxbtnidarr[i]).addClass("click");
				 }
			 }
		 }
		 
	 }
 	
	</script>
	<!--公用提示方法js-->
	<script type="text/javascript">
		<%--//操作成功提示方法 传：跳转url--%>
		function successalert(data,url,text){
			var message = '';
			if(data!=null&&data.message!=null&&data.message!=''){
				message = data.message;
			}else{
				message = "操作成功！";
			}
			swal({
				title : message,//操作提示   
				type : "success", //信息类型  success成功  error  失败    waring 警告
				text :text,
				showCancelButton : false, //撤销按钮
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "确定",
				closeOnConfirm : true, //关闭按钮
				closeOnCancel : true
				},function(isConfirm) {
						if (isConfirm==true) {
							if(url!=null&&url!=''){
								window.location.href = url;
							}
						}
				});
		}
		//自动消失
		function successalertclosebyself(data,url){
			var message = '';
			if(data!=null&&data.message!=null&&data.message!=''){
				message = data.message;
			}else{
				message = "操作成功！";
			}
			swal({   
	            title: message,   
	            type : "success",
	            timer: 1000,   
	            showConfirmButton: false 
	        });
			if(url!=null&&url!=''){
				window.location.href = url;
			} 
		 
		}
		function warningalertclosebyself(str){
			var message = '';
			if(str!=null&&str!=''){
				message = str;
			}else{
				message = "警告！";
			}
			swal({   
	            title: message,   
	            type : "warning",
	            timer: 2000,
	            showConfirmButton: false 
	        });
		 
		}
		<%--//操作失败提示方法--%>
		function erroralert(data){
			var message = '';
			if(data!=null&&data.message!=null&&data.message!=''){
				message = data.message;
			}else{
				message = "操作失败！";
			}
			swal({
				title : message,//操作提示   
				type : "error", //信息类型  success成功  error  失败    waring 警告
				showCancelButton : false, //撤销按钮
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "确定",
				closeOnConfirm : false, //关闭按钮
				closeOnCancel : true
			});
		}
		
		function  warningquestion (message){
			swal({
                title:message,//操作提示
                type: "warning", //信息类型  success成功  error  失败    waring 警告
                showCancelButton: false, //撤销按钮
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                closeOnConfirm: true, //关闭按钮
                closeOnCancel: true
            });
		}
		<%--
		//修改button样式
		//btnid 修改的buttonid
		//diabled button是否失效 true 为失效，false为正常
		//button  点击触发时修改的样式 (以字符串形式传入) 如果不需要修改样式 可为null值
		//text  button 的text值   传入 则修改，不修改 传null
		--%>
		function editButtonClass(btnid,diabled,btnclass,text){
			if(text!=null&&text!=""){
				$("#"+btnid).text(text);
			}
			$("#"+btnid).attr("disabled", true); 
			if(btnclass!=null&&btnclass!=""){
				$("#"+btnid).attr("class", "btn  waves-effect waves-light m-b-5 pull-right");
			}
		}
		
		
		
	 
		<%--
		 //queryformdata 查询 序列化 条件表单 数据
		 //datatableid   datatable id值
		 //getDataUrl    加载数据地址
		 //columnString  显示  字段名称 字符串    翻译字段逗号隔开    例如：'name,,age,mobile,'
		 //functionlist  翻译方法 targets:[1] ----->'name,,age,mobile,' name后面的空值需要翻译所调用方法
		 例：
		 var functionlist=[{targets:[1],
					   mRender:function(data) {
							return 1;
						}
					  },{targets:[2],
						 mRender:function(data) {
							 return 2;
							}
                      },{targets:[3],
                         mRender:function (data) {
                            return 3;
                      }
					}]
		--%>

		/**
		 * 基础
		 * @param queryformdata
		 * @param datatableid
		 * @param getDataUrl
		 * @param columnString
		 * @param functionlist
		 */
		function initBaseTable(queryformdata,datatableid,getDataUrl,columnString,functionlist) {
			var tableAutoWidth = $('#'+datatableid).width();
			// console.info("宽宽宽"+tableAutoWidth);
			$.fn.dataTable.ext.errMode = function(s, h, m) {
			};
			if(columnString==null){
				columnString="";
			}
			var columnList = [];      //显示列对应的json字段
            var  colArr = columnString.split(',');
            for (var i = 0; i < colArr.length; i++) {
                var obj = {};
                if(colArr[i]==''){
                	obj['mDataProp'] = null;
                }else{
                	obj['mDataProp'] = colArr[i];
                }
                if(colArr.length > 8){
					obj.sWidth = '130px';
				}else{
					obj.sWidth = 1300/colArr.length+'px';
				}
                columnList.push(obj);
            }
       
            
			var table = $("#" + datatableid)
					.DataTable(
							{
								"fnProcessingIndicator" : false,
								"bFilter" : false,//去掉搜索框
								"ordering" : false,//去掉排序
								"bAutoWidth" : false,
								"bLengthChange" : false,//去掉每页显示多少条
								"bAutoWidth": true, //自适应宽度
                                "scrollX": true,
                                "autoWidth":true,
								"sPaginationType" : "full_numbers",
								//"sAjaxDataProp" : "aData",
								"bDestroy" : true,
								"bProcessing" : true,
								"sAjaxSource" : getDataUrl+"?" + queryformdata,

								"bServerSide" : true,
								"aoColumns" :columnList ,
								"aoColumnDefs" :functionlist,
								"fnDrawCallback": function(table) { 
									 $("#"+datatableid+"_paginate .pagination").append("<li><a class='btn btn-default shiny' style='margin-bottom:5px' href='javascript:void(0);' id='dataTablebtn'>跳</a><span style='height: 35px;'>到&nbsp;<input class='btn btn-default shiny'  style='height:28px;line-height:28px;width:40px;margin-top: -5px;' class='margin text-center' id='changePage' type='text'>&nbsp;页&nbsp;</span></li>");
				                     var pageno = '${pageno}';
									 var oTable = $("#"+datatableid).dataTable();  
				                        $('#dataTablebtn').click(function(e) {  
				                            if($("#changePage").val() && $("#changePage").val() > 0) {  
				                                var redirectpage = $("#changePage").val() - 1;  
				                            } else {  
				                                var redirectpage = 0;  
				                            }  
				                            oTable.fnPageChange(redirectpage);
				                        });  
								}, 
								"oLanguage" : {
									"sProcessing" : "正在加载中......",
									"sLengthMenu" : "每页显示 _MENU_ 条记录",
									"sZeroRecords" : "没有数据！",
									"sEmptyTable" : "<center>没有查到数据</center>",
									"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
									"sInfoEmpty" : "显示0到0条记录",
									"sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
									//"sSearch" : "",
									"oPaginate" : {
										"sFirst" : "首页",
										"sPrevious" : "上一页",
										"sNext" : "下一页",
										"sLast" : "末页"
									}
								}
							});

			//显示序号
			table.on('draw.dt', function() {
				table.column(0, {
					search : 'applied',
							order : 'applied'
				}).nodes().each(function(cell, i) {
					var historypageno='${pageno}';

					//i 从0开始，所以这里先加1
					i = i + 1;
					//服务器模式下获取分页信息
					var page = table.page.info();
					//当前第几页，从0开始
					var pageno = page.page;
					$("#"+datatableid+"_paginate .pagination  #changePage").val(pageno+1);
					//每页数据
					var length = page.length;
					//行号等于 页数*每页数据长度+行号
					var columnIndex = (i + pageno * length);
					cell.innerHTML = columnIndex;
				});
			});

		}


        function initDYBaseTable(queryformdata,datatableid,getDataUrl,columnString,functionlist) {
            $.fn.dataTable.ext.errMode = function(s, h, m) {
            };
            if(columnString==null){
                columnString="";
            }
            var columnList = [];      //显示列对应的json字段
            var  colArr = columnString.split(',');
            for (var i = 0; i < colArr.length; i++) {
                var obj = {};
                if(colArr[i]==''){
                    obj['mDataProp'] = null;
                }else{
                    obj['mDataProp'] = colArr[i];
                }

                columnList.push(obj);
            }


            var table = $("#" + datatableid)
                .DataTable(
                    {
                        "fnProcessingIndicator" : false,
                        "bFilter" : false,//去掉搜索框
                        "ordering" : false,//去掉排序
                        "bAutoWidth" : false,
                        "bLengthChange" : false,//去掉每页显示多少条
                        "iDisplayLength": 30,
                        //"bAutoWidth": true, //自适应宽度
                        // "scrollX": true,
                        // "autoWidth":true,
                        "sPaginationType" : "full_numbers",
                        //"sAjaxDataProp" : "aData",
                        "bDestroy" : true,
                        "bProcessing" : true,
                        "sAjaxSource" : getDataUrl+"?" + queryformdata,
                        "bServerSide" : true,
                        "aoColumns" :columnList ,
                        "aoColumnDefs" :functionlist,
                        "fnDrawCallback": function(table) {
                            $("#"+datatableid+"_paginate .pagination").append("<li><a class='btn btn-default shiny' style='margin-bottom:5px' href='javascript:void(0);' id='dataTablebtn'>跳</a>到<input style='height:28px;line-height:28px;width:40px;' class='margin text-center' id='changePage' type='text'>页 </li>");
                            var pageno = '${pageno}';
                            var oTable = $("#"+datatableid).dataTable();
                            $('#dataTablebtn').click(function(e) {
                                if($("#changePage").val() && $("#changePage").val() > 0) {
                                    var redirectpage = $("#changePage").val() - 1;
                                } else {
                                    var redirectpage = 0;
                                }
                                oTable.fnPageChange(redirectpage);
                            });
                        },
                        "oLanguage" : {
                            "sProcessing" : "正在加载中......",
                            "sLengthMenu" : "每页显示 _MENU_ 条记录",
                            "sZeroRecords" : "没有数据！",
                            "sEmptyTable" : "<center>没有查到数据</center>",
                            "sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
                            "sInfoEmpty" : "显示0到0条记录",
                            "sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
                            //"sSearch" : "",
                            "oPaginate" : {
                                "sFirst" : "首页",
                                "sPrevious" : "上一页",
                                "sNext" : "下一页",
                                "sLast" : "末页"
                            }
                        }
                    });

            //显示序号
            table.on('draw.dt', function() {
                table.column(0, {
                    search : 'applied',
                    order : 'applied'
                }).nodes().each(function(cell, i) {
                    var historypageno='${pageno}';

                    //i 从0开始，所以这里先加1
                    i = i + 1;
                    //服务器模式下获取分页信息
                    var page = table.page.info();
                    //当前第几页，从0开始
                    var pageno = page.page;
                    $("#"+datatableid+"_paginate .pagination  #changePage").val(pageno+1);
                    //每页数据
                    var length = page.length;
                    $('.recordsTotal').val(page.recordsTotal);
                    //行号等于 页数*每页数据长度+行号
                    var columnIndex = (i + pageno * length);
                    cell.innerHTML = columnIndex;
                });
            });

        }

        function initDYALLBaseTable(queryformdata, datatableid, getDataUrl, columnString, functionlist, length) {
            $.fn.dataTable.ext.errMode = function(s, h, m) {
            };
            if(columnString==null){
                columnString="";
            }
            var columnList = [];      //显示列对应的json字段
            var  colArr = columnString.split(',');
            for (var i = 0; i < colArr.length; i++) {
                var obj = {};
                if(colArr[i]==''){
                    obj['mDataProp'] = null;
                }else{
                    obj['mDataProp'] = colArr[i];
                }
                columnList.push(obj);
            }
            var table = $("#" + datatableid).DataTable({
				"fnProcessingIndicator" : false,
				"bFilter" : false,//去掉搜索框
				"ordering" : false,//去掉排序
				"bAutoWidth" : false,
				"bLengthChange" : false,//去掉每页显示多少条
				"iDisplayLength": length,
				"sPaginationType" : "full_numbers",
				"bDestroy" : true,
				"bProcessing" : false,
				"sAjaxSource" : getDataUrl+"?" + queryformdata,
				"bServerSide" : true,
				"aoColumns" :columnList ,
				"aoColumnDefs" :functionlist,
                "fnInitComplete": function (oSettings, json) {
                    $('.isLoad').val('true');
                }
			});

            //显示序号
            table.on('draw.dt', function() {
                table.column(0, {
                    search : 'applied',
                    order : 'applied'
                }).nodes().each(function(cell, i) {
                    var historypageno='${pageno}';
                    //i 从0开始，所以这里先加1
                    i = i + 1;
                    //服务器模式下获取分页信息
                    var page = table.page.info();
                    //当前第几页，从0开始
                    var pageno = page.page;
                    $("#" + datatableid + "_paginate .pagination  #changePage").val(pageno + 1);
                    //每页数据
                    var length = page.length;
                    //行号等于 页数*每页数据长度+行号
                    var columnIndex = (i + pageno * length);
                    cell.innerHTML = columnIndex;
                });
            });
        }

        function initBaseTableSelect(queryformdata,datatableid,getDataUrl,columnString,functionlist) {
            $.fn.dataTable.ext.errMode = function(s, h, m) {
            };
            if(columnString==null){
                columnString="";
            }
            var columnList = [];      //显示列对应的json字段
            var  colArr = columnString.split(',');
            for (var i = 0; i < colArr.length; i++) {
                var obj = {};
                if(colArr[i]==''){
                    obj['mDataProp'] = null;
                }else{
                    obj['mDataProp'] = colArr[i];
                }

                columnList.push(obj);
            }


            var table = $("#"+datatableid)
                .DataTable(
                    {
                        "fnProcessingIndicator" : false,
                        "bFilter" : false,//去掉搜索框
                        "ordering" : false,//去掉排序
                        "bAutoWidth" : false,
                        "bLengthChange" : false,//去掉每页显示多少条
                        //"bAutoWidth": true, //自适应宽度
                        "scrollX": true,
                        "autoWidth":true,
                        "sPaginationType" : "full_numbers",
                        //"sAjaxDataProp" : "aData",
                        "bDestroy" : true,
                        "bProcessing" : true,
                        "sAjaxSource" : getDataUrl+"?" + queryformdata,

                        "bServerSide" : true,
                        "aoColumns" :columnList ,
                        "aoColumnDefs" :functionlist,
                        "fnDrawCallback": function(table) {
                            $("#"+datatableid+"_paginate .pagination").append("<li><a class='btn btn-default shiny' style='margin-bottom:5px' href='javascript:void(0);' id='dataTablebtn'>跳</a>到<input style='height:28px;line-height:28px;width:40px;' class='margin text-center' id='changePage' type='text'>页 </li>");
                            var pageno = '${pageno}';
                            var oTable = $("#"+datatableid).dataTable();
                            $('#dataTablebtn').click(function(e) {
                                if($("#changePage").val() && $("#changePage").val() > 0) {
                                    var redirectpage = $("#changePage").val() - 1;
                                } else {
                                    var redirectpage = 0;
                                }

                                oTable.fnPageChange(redirectpage);
                            });
                        },
                        "oLanguage" : {
                            "sProcessing" : "正在加载中......",
                            "sLengthMenu" : "每页显示 _MENU_ 条记录",
                            "sZeroRecords" : "没有数据！",
                            "sEmptyTable" : "<center>没有查到数据</center>",
                            "sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
                            "sInfoEmpty" : "显示0到0条记录",
                            "sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
                            //"sSearch" : "",
                            "oPaginate" : {
                                "sFirst" : "首页",
                                "sPrevious" : "上一页",
                                "sNext" : "下一页",
                                "sLast" : "末页"
                            }
                        }
                    });

            //显示序号
            table.on('draw.dt', function() {
                table.column(0, {
                    search : 'applied',
                    order : 'applied'
                }).nodes().each(function(cell, i) {
                    var historypageno='${pageno}';

                    //i 从0开始，所以这里先加1
                    i = i + 1;
                    //服务器模式下获取分页信息
                    var page = table.page.info();
                    //当前第几页，从0开始
                    var pageno = page.page;
                    $("#"+datatableid+"_paginate .pagination  #changePage").val(pageno+1);
                    //每页数据
                    var length = page.length;
                    //行号等于 页数*每页数据长度+行号
                    var columnIndex = (i + pageno * length);
                    cell.innerHTML = columnIndex;
                });
            });

        }


        function initBaseTableNotPage(queryformdata,datatableid,getDataUrl,columnString,functionlist) {
            $.fn.dataTable.ext.errMode = function(s, h, m) {
            };
            if(columnString==null){
                columnString="";
            }
            var columnList = [];      //显示列对应的json字段
            var  colArr = columnString.split(',');
            for (var i = 0; i < colArr.length; i++) {
                var obj = {};
                if(colArr[i]==''){
                    obj['mDataProp'] = null;
                }else{
                    obj['mDataProp'] = colArr[i];
                }

                columnList.push(obj);
            }


            var table = $("#"+datatableid)
                .DataTable(
                    {
                        "fnProcessingIndicator" : false,
                        "bFilter" : false,//去掉搜索框
                        "ordering" : false,//去掉排序
                        "bAutoWidth" : false,
                        "bLengthChange" : false,//去掉每页显示多少条
                        "bAutoWidth": true, //自适应宽度
        				"bPaginate": false, //设置是否分页
                        "sPaginationType" : "full_numbers",
                        //"sAjaxDataProp" : "aData",
                        "bDestroy" : true,
						"cache":true,
                        "bProcessing" : true,
                        "sAjaxSource" : getDataUrl+"?" + queryformdata,

                        "bServerSide" : true,
                        "aoColumns" :columnList ,
                        "aoColumnDefs" :functionlist,
                        "fnDrawCallback": function(table) {
                            $("#"+datatableid+"_paginate .pagination").append("<li><a class='btn btn-default shiny' style='margin-bottom:5px' href='javascript:void(0);' id='dataTablebtn'>跳</a>到<input style='height:28px;line-height:28px;width:40px;' class='margin text-center' id='changePage' type='text'>页 </li>");
                            var pageno = '${pageno}';
                            var oTable = $("#"+datatableid).dataTable();
                            $('#dataTablebtn').click(function(e) {
                                if($("#changePage").val() && $("#changePage").val() > 0) {
                                    var redirectpage = $("#changePage").val() - 1;
                                } else {
                                    var redirectpage = 0;
                                }

                                oTable.fnPageChange(redirectpage);
                            });
                        },
                        "oLanguage" : {
                            "sProcessing" : "正在加载中......",
                            "sLengthMenu" : "每页显示 _TOTAL_ 条记录",
                            "sZeroRecords" : "没有数据！",
                            "sEmptyTable" : "<center>没有查到数据</center>",
                            "sInfo" : "当前显示 1 到 _TOTAL_ 条，共 _TOTAL_ 条记录",
                            "sInfoEmpty" : "显示1到最后一条记录",
                            "sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
                            //"sSearch" : "",
                            "oPaginate" : {
                                "sFirst" : "首页",
                                "sPrevious" : "上一页",
                                "sNext" : "下一页",
                                "sLast" : "末页"
                            }
                        }
                    });

            //显示序号
            table.on('draw.dt', function() {
                table.column(0, {
                    search : 'applied',
                    order : 'applied'
                }).nodes().each(function(cell, i) {
                    var historypageno='${pageno}';

                    //i 从0开始，所以这里先加1
                    i = i + 1;
                    //服务器模式下获取分页信息
                    var page = table.page.info();
                    //当前第几页，从0开始
                    var pageno = page.page;
                    $("#"+datatableid+"_paginate .pagination  #changePage").val(pageno+1);
                    //每页数据
                    var length = page.length;
                    //行号等于 页数*每页数据长度+行号
                    var columnIndex = (i + pageno * length);
                    cell.innerHTML = columnIndex;
                });
            });

        }


		//带控件
		function initBaseTableInput(queryformdata,datatableid,getDataUrl,columnString,functionlist) {
			$.fn.dataTable.ext.errMode = function(s, h, m) {
			};
			if(columnString==null){
				columnString="";
			}
			var columnList = [];      //显示列对应的json字段
            var  colArr = columnString.split(',');
            for (var i = 0; i < colArr.length; i++) {
                var obj = {};
                if(colArr[i]==''){
                	obj['mDataProp'] = null;
                }else{
                	obj['mDataProp'] = colArr[i];
                }
                
                columnList.push(obj);
            }
       
            
			var table = $("#"+datatableid)
					.DataTable(
							{
								"bLengthChange": false, //关闭每页显示多少条数据
	                            "bFilter": false,//去掉搜索框
	                            "ordering": false,//去掉排序
	                            "bAutoWidth": true,
	                            "bAutoWidth": true, //自适应宽度
	                            "sPaginationType": "full_numbers",
	                            "bPaginate": true,//是否使用分页
	                            "bDestroy": true,
	                            "bProcessing": true,
								"sAjaxSource" : getDataUrl+"?" + queryformdata,

								"bServerSide": true,
								"aoColumns" :columnList ,
								"aoColumnDefs" :functionlist,
								"fnDrawCallback": function(table) {    
									 $("#"+datatableid+"_paginate .pagination").append("<li><a class='btn btn-default shiny' style='margin-bottom:5px' href='javascript:void(0);' id='"+datatableid+"_dataTablebtn1'>跳</a>到<input style='height:28px;line-height:28px;width:40px;' class='margin text-center' id='"+datatableid+"_changePage1' type='text'>页 </li>");  
				                        var oTable = $("#"+datatableid).dataTable();  
				                        $('#'+datatableid+'_dataTablebtn1').click(function(e) {  
				                            if($("#"+datatableid+"_changePage1").val() && $("#"+datatableid+"_changePage1").val() > 0) {  
				                                var redirectpage = $("#"+datatableid+"_changePage1").val() - 1;  
				                            } else {  
				                                var redirectpage = 0;  
				                            }  
				                            oTable.fnPageChange(redirectpage);  
				                        });  
								}, 
								"oLanguage": {
	                                "sProcessing": "正在加载中......",
	                                "sLengthMenu": "每页显示 _MENU_ 条记录",
	                                "sZeroRecords": "没有数据！",
	                                "sEmptyTable": "表中无数据存在！",
	                                "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
	                                "sInfoEmpty": "显示0到0条记录",
	                                "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
	                                //"sSearch" : "",
	                                "oPaginate": {
	                                    "sFirst": "首页",
	                                    "sPrevious": "上一页",
	                                    "sNext": "下一页",
	                                    "sLast": "末页"
	                                }
	                            }
							});

			 //显示序号
	        //var table = $('#datatable').DataTable();
	        table.on('draw.dt', function () {
	            table.column(1, {
	                search: 'applied',
	                order: 'applied'
	            }).nodes().each(function (cell, i) {
	                //i 从0开始，所以这里先加1
	                i = i + 1;
	                //服务器模式下获取分页信息
	                var page = table.page.info();
	               
	                //当前第几页，从0开始
	                var pageno = page.page;
	                $("#"+datatableid+"_paginate .pagination  #"+datatableid+"_changePage1").val(pageno+1);
	                //每页数据
	                var length = page.length;
	                //行号等于 页数*每页数据长度+行号
	                var columnIndex = (i + pageno * length);
	                cell.innerHTML = columnIndex;
	            });
	        }).draw();

		}
		<%--
		刷新选项卡列表 
		tabindex：当前选项卡id
		tabindexArr：所有选项卡的id数组    参数数组顺序 与  tabindexUrlDataArr  一致
		tabindexUrlDataArr：所有选项卡所调用的获取数据的url   参数数组顺序 与  tabindexArr 一致
		--%>
		function RefreshTables(tabindex,tabindexArr, tabindexUrlDataArr) {
			if(tabindexArr.length!=tabindexUrlDataArr.length){
				console.log("js代码错误:调用方法:RefreshTables  错误原因：tabindexArr 与  tabindexUrlDataArr 数组长度互不匹配！");
				return;
			}
			for(var i=0;i<tabindexArr.length;i++){
				if(tabindex==tabindexArr[i]){
					var datatable = $("#"+tabindex).dataTable();
					var data = {'iDisplayStart':datatable.fnSettings().oAjaxData.iDisplayStart,'iDisplayLength':datatable.fnSettings().oAjaxData.iDisplayLength};
					$.getJSON(tabindexUrlDataArr[i], data, function(json) {
						$("#"+tabindex).dataTable().fnDraw(false); 
						successalertclosebyself(null,null);
					});  
				}
			}
			
			
		}
		
		<%--
		刷新单表数据
		
		tableId： 刷新的table id
		urlData： 刷新table 数据调用 的url
		--%>
		function RefreshTable(tableId, urlData,type) {
		 
			var datatable = $("#"+tableId).dataTable();
			var iDisplayStart = 0;
            var iDisplayLength =10;


			if(datatable.fnSettings().oAjaxData!=null){
				iDisplayStart=datatable.fnSettings().oAjaxData.iDisplayStart;
				iDisplayLength=datatable.fnSettings().oAjaxData.iDisplayLength;
			}
		 
			var data = {'iDisplayStart':iDisplayStart,'iDisplayLength':iDisplayLength};
			$.getJSON(urlData, data, function(json) {
				$("#"+tableId).dataTable().fnDraw(false); 
				successalertclosebyself(null,null);
			});  
		}
		function initBaseDateTimePicker(minDateTimePickerId,maxDateTimePickerId){
			
			 $('#'+minDateTimePickerId).datetimepicker({
				  format:"YYYY-MM-DD HH:mm:ss"
             });
		     $('#'+maxDateTimePickerId).datetimepicker({
				  format:"YYYY-MM-DD HH:mm:ss"
             });
		        
		        $("#"+minDateTimePickerId).on("dp.change", function (e) {
		            $('#'+maxDateTimePickerId).data("DateTimePicker").minDate(e.date);
		        });
		        $("#"+maxDateTimePickerId).on("dp.change", function (e) {
		            $('#'+minDateTimePickerId).data("DateTimePicker").maxDate(e.date);
		        });
		}
		<%--选项卡抽象js方法 
		 注意 1、要使用该方法首先 更改li的点击事件   传参必须 与 列表页id一致   <div id="checking" style="display: none">  onclick="changeStatus('checking')"  
		 2、保证tabledividarr tabledividarr tabliarr tabinifuncarr 数组顺序一致  否则方法调用错乱 
		 ulid : 所有的li标签都被ul标签包裹 传入该 ul  的  id<ul id="checkul" class="nav nav-tabs navtab-bg"> 
		 tabindexid ：隐藏域的tableindx id   例如：<input type="hidden" id="tabindx" name="tabindx" value="${tabindx}"> 
		 tabledividarr : 列表id 数组  例如传入 ['checking','pass']  注意数组顺序与其他数组顺序一致 
		 tabliarr   : 切换选项li id 数组 例如传入 ['dshli','passli']  注意数组顺序与其他数组顺序一致  
		 tabinifuncarr：初始化table 的 方法数组  例如传入['initDSHTable','initYTGTable']注意数组顺序与其他数组顺序一致 
		 defaulttabid: 默认选择的选项卡  
		 案例
		 initTabs("checkul",
					"tabindx",
					['checking','pass','Invalid'],
					['checkingli','passli','Invalidli'],
					['initDSHTable()','initYTGTable()','initWTGTable()'],
					"checking");
		--%>
		function initXXKTabs(ulid,tabindxid,tabledividarr,tabliarr,tabinifuncarr,defaulttabid){
			if(tabledividarr.length!=tabliarr.length
					||tabliarr.length!=tabinifuncarr.length
					||tabledividarr.length!=tabinifuncarr.length){
				console.log("js代码错误:调用方法:initTabs  错误原因：数组长度互不匹配！");
				return;
			}
			var tabindx = $("#"+tabindxid).val();
			if(tabindx==null||tabindx==''){
				if(defaulttabid==null||defaulttabid==''){
					console.log("js代码错误:调用方法:initTabs  错误原因：defaulttabid为空");
					return;
				}else{
					tabindx=defaulttabid;
				}
			}  
			var funcioncount=null;
			for(var i=0;i<tabledividarr.length;i++){
				if (tabindx != tabledividarr[i]) {
					$("#"+tabledividarr[i]).hide();
				} else{
					$("#"+tabindxid).val(tabindx);
					$("#"+ulid+"  li").removeClass("active");
					$("#"+ulid+"  #"+tabliarr[i]).addClass(" active");
					$("#"+tabledividarr[i]).show();
					funcioncount=i;
				}
			}
			eval(""+tabinifuncarr[funcioncount]+""); 
		}

        function isNumber(str) {
            var reg = new RegExp('^\\+?(\\d+)(\\.\\d+)?$');
            return reg.test(str);
        }
	</script>
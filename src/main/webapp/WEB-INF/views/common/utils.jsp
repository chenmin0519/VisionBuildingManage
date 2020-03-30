<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script>

    /**
     * 处理ajax 请求的data,弹出提示框
     * @param data, url
     * @returns void
     */
    function handleResult(data, url) {
        if (data.status == 0) //成功
        {
            swal({
                title: data.message,//操作提示
                type: "success", //信息类型  success成功  error  失败    waring 警告
                showCancelButton: false, //撤销按钮
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                closeOnConfirm: true, //关闭按钮
                closeOnCancel: true
            }, function () {
                if (url) {
                    window.location.href = url;
                }
            });

        }
        else {
            swal({
                title: data.message,//操作提示
                type: "error", //信息类型  success成功  error  失败    waring 警告
                showCancelButton: false, //撤销按钮
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                closeOnConfirm: false, //关闭按钮
                closeOnCancel: false
            });
        }
    }

    /**
     * 将时间戳转换成  yyyy-MM-dd hh:mm:ss 的字符串
     * @param time
     * @returns {*}
     */
    function getDateStr(time) {
        if (time) {
            // 比如需要这样的格式 yyyy-MM-dd hh:mm:ss
            var date = new Date(time);
            Y = date.getFullYear() + '-';
            M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date
                            .getMonth() + 1)
                    + '-';
            D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate())
                    + ' ';
            h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
            m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
            s = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
            return Y + M + D + h + m + s;
        }
    }

    /**
     * 按钮开关样式取值
     * @param id
     */
    function buttonChecked(id) {
        if ($("#" + id).hasClass("btn btn-default waves-effect click")) {
            $("#" + id + "Ti").val("1");
        }
        else {
            $("#" + id + "Ti").val("0");
        }
    }
    
    
    
 // 对Date的扩展，将 Date 转化为指定格式的String
 // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
 // 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
 // 例子： 
 // (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
 // (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
 Date.prototype.Format = function (fmt) { //author: meizz 
     var o = {
         "M+": this.getMonth() + 1, //月份 
         "d+": this.getDate(), //日 
         "h+": this.getHours(), //小时 
         "m+": this.getMinutes(), //分 
         "s+": this.getSeconds(), //秒 
         "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
         "S": this.getMilliseconds() //毫秒 
     };
     if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
     for (var k in o)
     if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
     return fmt;
 }
</script>

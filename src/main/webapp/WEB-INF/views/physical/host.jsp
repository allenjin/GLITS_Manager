<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/head.jsp" %>
</head>
<body>
    <%@include file="../common/header.jsp" %>
    <div class="container">
        <div class="col-md-6" id="leftCol">

        </div>
        <div class="col-md-6" id="rightCol">

        </div>
    </div>
    <%@include file="../common/footer.jsp" %>
    <%@include file="../common/script.jsp" %>
    <script type="text/javascript">

        basicInfoBuild();
        cpuInfoBuild();
        fsInfoBuild();
        memInfoBuild();
        netInfoBuild();

        function basicInfoBuild(){
            var basicInfos = [];
            var dataArray = ${basicInfo};
            var basicInfo = dataArray[0];
            basicInfos.push(["操作系统", basicInfo.data.os]);
            basicInfos.push(["运行时间", uptimeParser(basicInfo.data.uptime)]);
            basicInfos.push(["系统时间", basicInfo.data.sever_date]);
            basicInfos.push(["最近1分钟负载",Number(basicInfo.data.load_avg_1).toFixed(2)]);
            basicInfos.push(["最近5分钟负载", Number(basicInfo.data.load_avg_5).toFixed(2)]);
            basicInfos.push(["最近15分钟负载", Number(basicInfo.data.load_avg_15).toFixed(2)]);
            var basicPanel = metricPanelBuild("基本信息", glTable(undefined, basicInfos));
            $('#leftCol').append(basicPanel);
        }

        function cpuInfoBuild(){
            var dataArray = ${cpuInfo};
            var times = [];
            var values = [];
            $.each(dataArray, function(i, cpuInfo){
                times.push(dateFormat(cpuInfo.time));
                values.push(cpuInfo.data.usage);
            });
            var id = "cpuChart";
            var cpuPanel = metricPanelBuild("CPU信息", chartContainer(id));
            $('#leftCol').append(cpuPanel);
            var series = [{name: "CPU使用率", data: values}];
            highChartBuilder($("#" + id), "line", times, series, "CPU使用情况", "百分比(%)");
        }

        function fsInfoBuild(){
            var dataArray = ${fsInfo};
            var partition = [];
            var mountPoints = [];
            var series = [{name:"可用", data: []}, {name: "已用", data: []}];

            $.each(dataArray[0].data, function(i, fsInfo){
                partition.push(fsInfo.partition);
                mountPoints.push(fsInfo.mount_point);
                series[0].data.push(fsInfo.free);
                series[1].data.push(fsInfo.used);
            });
            var id = "fsChart";
            var fsPanel = metricPanelBuild("磁盘信息", chartContainer(id));
            $('#leftCol').append(fsPanel);
            var options = {mounted: mountPoints};
            highChartBuilder($("#" + id), "column", partition, series, "硬盘使用情况", "百分比(%)", options);
        }

        function memInfoBuild(){
            var dataArray = ${memInfo};
            var times = [];
            var series = [
                {name:"free", data: []},
                {name:"used", data: []}
            ]
            $.each(dataArray, function(i, memInfo){
               times.push(dateFormat(memInfo.time));
                series[0].data.push(memInfo.data.free);
                series[1].data.push(memInfo.data.used);
            });
            var id = "memChart";
            var memPanel = metricPanelBuild("内存信息", chartContainer(id));
            $('#rightCol').append(memPanel);
            highChartBuilder($("#" + id), "area", times, series, "内存使用情况", "百分比(%)");
        }

        function netInfoBuild(){
            var dataArray = ${netInfo};
            var times = [];
            var series = [];
            var ifaces = [];
            var ips = [];
            $.each(dataArray, function(i, netInfo){
               times.push(dateFormat(netInfo.time));
                $.each(netInfo.data, function(j, iface){
                    if(!ifaces[iface.iface]){
                        ifaces[iface.iface] = [];
                        ips.push(iface.ip);
                    }
                    ifaces[iface.iface].push({b_in:iface.bytes_recv, b_out: iface.bytes_sent});
                });
            });
            var index = 0;
            for(var iface in ifaces){
                var b_ins = [];
                var b_outs = [];
                var ip = ips[index ++];
                $.each(ifaces[iface], function(i, obj){
                    var kb_in = (obj.b_in / 1024).toFixed(2);
                    var kb_out = (obj.b_out / 1024).toFixed(2);
                    b_ins.push(Number(kb_in));
                    b_outs.push(Number(kb_out));
                });
                series.push({name: iface + "-in", ip: ip, data: b_ins});
                series.push({name: iface + "-out", ip: ip, data: b_outs});
            }
            var id = "netChart";
            var netPanel = metricPanelBuild("网络信息", chartContainer(id));
            $('#rightCol').append(netPanel);
            var options = {type: "NET"};
            highChartBuilder($('#' + id), "line", times, series, "网络使用情况", "流量(Kb/s)", options);
        }

        function metricPanelBuild(title, content){
            var panel = '<div class="panel panel-info gl-panel">';
            panel += '<div class="panel-heading">' + title + '</div>';
            panel += '<div class="panel-body">' + content + '</div>';
            panel += '</div>';
            return panel;
        }

        function chartContainer(id){
            return '<div id="' + id +'"></div>';
        }
        function dateFormat(time){
            var date = new Date(time);
            return date.format("hh:mm");
        }
    </script>
</body>
</html>
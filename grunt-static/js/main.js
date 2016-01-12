//通用表格控件
function glTable(thRow, tbRows){
    var t_html = '';
    t_html += '<table class="table table-hover">';
    if(thRow !== undefined && thRow !== null){
        t_html += '<thead><tr>';
        $.each(thRow, function(index, item){
            t_html += '<th>' + item + '</th>';
        });
        t_html += '</tr></thead>';
    }
    t_html += '<tbody>';
    $.each(tbRows, function(i, row){
        t_html += '<tr>';
        $.each(row, function(j, item){
            t_html += '<td>' + item + '</td>';
        });
        t_html += '</tr>';
    });
    t_html += '</tbody></table>';
    return t_html;
}

function uptimeParser(second){
    var days = parseInt(second / (60 * 60 * 24));
    var hours = parseInt((second % (60 * 60 * 24)) / (60 * 60));
    var minutes = parseInt((second % (60 * 60) / 60));
    var seconds = parseInt(second % 60);
    var daysBuilder = (days === 0) ? "" : (days + "天");
    var hoursBuilder = (days === 0 && hours === 0) ? "" : (hours + "小时");
    var minutesBuilder = (days === 0 && hours === 0 && minutes === 0) ? "" : (minutes + "分钟");
    return daysBuilder + hoursBuilder + minutesBuilder + seconds + "秒";
}

//only use host metric chart
function highChartBuilder(container, type, xAxis, series, title, yTitle, options){
    var plotOptions = {};
    var tooltip = {};

    if(options && options.type == "NET"){
        tooltip.pointFormatter = function() {
            var ip = '';
            for (var i in series) {
                if (series[i].name == this.series.name) {
                    ip = series[i].ip;
                }
            }
            var ss = '<span style="color:' + this.series.color + '">' + this.series.name + '</span>: <b>' + this.y + 'Kb/s</b><br/>';
            ss += '<span style="color:#55ddbb">IP地址</span>: <b>' + ip + '</b><br/>';
            return ss;
        };
    }
    if(type === "column"){
        plotOptions.column = {
            stacking: 'percent'
        };
        tooltip.formatter = function() {
            var s = '';
            var index = 0;
            $.each(this.points, function() {
                index = this.point.index;
                s += '<span style="color:' + this.series.color + '">' + this.series.name + '</span>: <b>' +
                    (this.point.y / 1024 / 1024 / 1024).toFixed(3) + 'G</b> (' + this.point.percentage.toFixed(2) + '%)<br/>';
            });
            s = '<span style="color:#f7a35c">挂载位置<span>: <b>' + options.mounted[index] + '</b><br />' + s;
            return s;
        };
        tooltip.shared = true;
    }

    if(type === "area"){
        plotOptions.area = {
            stacking: 'percent',
            lineColor: '#ffffff',
            lineWidth: 1
        };
        tooltip.shared = true;
        tooltip.pointFormatter = function(){
            return '<span style="color:' + this.series.color + '">' + this.series.name + '</span>:<b>' + this.percentage.toFixed(3) + '%</b> (' + (this.y / 1024 / 1024 / 1024).toFixed(3) + ' G)<br/>';
        };
    }
    container.highcharts({
        chart: {
            type: type
        },
        title: {
            text: title
        },
        xAxis: {
            categories: xAxis
        },
        tooltip: tooltip,
        plotOptions: plotOptions,
        yAxis: {
            title: {
                text: yTitle
            },
            min: 0,
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        series: series
    });
}

Date.prototype.format = function(format){
    var o = {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(), //day
        "h+" : this.getHours(), //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3), //quarter
        "S" : this.getMilliseconds() //millisecond
    };

    if(/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }

    for(var k in o) {
        if(new RegExp("("+ k +")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
        }
    }
    return format;
};
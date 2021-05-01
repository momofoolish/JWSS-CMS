window.onload = function () {
    systemChartShow();
    websiteChartShow();
}

//网站访问量
function websiteChartShow() {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('websiteChart'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '网站'
        },
        tooltip: {},
        legend: {
            data: ['访问量']
        },
        xAxis: {
            data: ["12-01", "12-02", "12-03", "12-04", "12-06", "12-07"]
        },
        yAxis: {},
        series: [{
            name: '访问量',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

//系统使用情况
function systemChartShow() {
    // 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(document.getElementById('systemChart'));

    // 指定图表的配置项和数据
    let option = "";

    setSystemInfoPie(option, myChart);

    // 定时器
    setInterval(function () {
        setSystemInfoPie(option, myChart);
    }, 10 * 60 * 1000);
}

// 异步获取系统信息数据
function setSystemInfoPie(option, myChart) {
    // http获取数据
    $.ajax({
        url: "/api/admin/watch",
        method: 'get',
        success: function (resp) {
            if (resp.code === 1) {
                let content = resp.content;
                let usedMemory = content.usedMemory / content.totalMemory;
                let usedSpace = (content.totalSpace - content.usableSpace) / content.totalSpace;

                let cpu = 'CPU ' + (content.usedCpu * 100).toFixed() + '%';
                let memory = '内存 ' + (usedMemory * 100).toFixed() + '%';
                let space = '磁盘 ' + (usedSpace * 100).toFixed() + '%';
                option = {
                    title: {
                        text: '系统使用情况'
                    },
                    legend: {
                        data: ['使用率']
                    },
                    series: [{
                        name: '占比',
                        type: 'pie',
                        data: [
                            {value: usedMemory, name: memory},
                            {value: content.usedCpu, name: cpu},
                            {value: usedSpace, name: space},
                        ]
                    }]
                };
                //重新赋值文本展示
                content.usedCpu = cpu;
                content.usedMemory = content.usedMemory.toFixed(2) + " G";
                content.totalMemory = content.totalMemory.toFixed(2) + " G";
                content.totalSpace = content.totalSpace.toFixed(2) + " G";
                content.usableSpace = content.usableSpace.toFixed(2) + " G";
                setSystemInfoText(content);
            }
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });
}

// 文本展现系统信息
function setSystemInfoText(c) {
    let el = ['jHome', 'jVersion', 'uHome', 'OS', 'bootTime', 'cpuCount', 'usedCpu', 'usedMemory',
        'totalMemory', 'usableSpace', 'totalSpace'];
    // 通过key获取value
    for (let i = 0; i < el.length; i++) {
        $('#' + el[i]).text("" + c[el[i]]);
    }
}

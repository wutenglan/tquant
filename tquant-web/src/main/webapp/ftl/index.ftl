<#include "templ/global.ftl">
<@framework>

    <h3 class="page-title">
        <small>blank page layout</small>
    </h3>
	<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

	

</@framework>
<script type="text/javascript"> 
$.getJSON('http://localhost:9999/surgeLimit/sayHello1?1=1&callback=?', function (data) {

    Highcharts.chart('container', {
        chart: {
            zoomType: 'x'
        },
        title: {
            text: 'USD to EUR exchange rate over time'
        },
        subtitle: {
            text: document.ontouchstart === undefined ?
                    'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
        },
        xAxis: {
            type: 'datetime'
        },
        yAxis: {
            title: {
                text: 'Exchange rate'
            }
        },
        legend: {
            enabled: false
        },
        plotOptions: {
            area: {
                fillColor: {
                    linearGradient: {
                        x1: 0,
                        y1: 0,
                        x2: 0,
                        y2: 1
                    },
                    stops: [
                        [0, Highcharts.getOptions().colors[0]],
                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                    ]
                },
                marker: {
                    radius: 2
                },
                lineWidth: 1,
                states: {
                    hover: {
                        lineWidth: 1
                    }
                },
                threshold: null
            }
        },

        series: [{
            type: 'area',
            name: 'USD to EUR',
            data: data
        },
        {
        type: 'area',
        name: 'Winter 2014-2015',
        data:[[Date.UTC(2013,5,2),1.7695],
        	[Date.UTC(2013,5,3),1.7648]]
    	},
    	{
        type: 'area',
        name: 'Winter 2014-2015',
        data:[[Date.UTC(2013,5,6),1.7695],
        	[Date.UTC(2013,5,7),1.7648]]
    	}
        ]
    });
});

themeDark();


</script>

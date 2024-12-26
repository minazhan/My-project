<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>圖表示例</title>

    <!-- Resources -->
    <script src="https://cdn.amcharts.com/lib/5/index.js"></script>
    <script src="https://cdn.amcharts.com/lib/5/percent.js"></script>
    <script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Styles -->
    <style>
    
    	body{
    		background-color: #f0f2f5;
    	}
    
        #chartdiv, #chartdiv_form {
            width: 100%;
            height: 500px;
        }

        .chart-wrapper {
            display: flex;
            justify-content: space-between;
            align-items: center;
            gap: 30px;
        }

        .chart-container {
            flex: 1;
            max-width: 50%;
            height: auto;
            background-color: #f7f7f7;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-sizing: border-box;
        }

        .chart-title {
            text-align: center;
            margin-top: 10px;
            font-size: 24px;
            font-weight: bold;
        }
    </style>
</head>
<body>

	<!-- menu bar include -->
	<%@ include file="/WEB-INF/view/menu.jspf" %>

    <div class="container mt-5 mb-5">
        <div class="d-flex justify-content-between chart-wrapper">
            <!-- 第一個圖表 -->
            <div class="chart-container">
                <h3 class="chart-title">職業</h3>
                <div id="chartdiv"></div>
            </div>
            
            <!-- 第二個圖表 -->
            <div class="chart-container">
                <h3 class="chart-title">表單</h3>
                <div id="chartdiv_form"></div>
            </div>
        </div>
    </div>

    <!-- Chart code -->
    <script> 
    
    am5.ready(function() {
        // 第一個圖表
        var root = am5.Root.new("chartdiv");
        root._logo.dispose();
        var chart = root.container.children.push(am5percent.PieChart.new(root, {
            layout: root.verticalLayout,
            paddingTop: 50, paddingBottom: 50, paddingLeft: 50, paddingRight: 50
        }));

        var series = chart.series.push(am5percent.PieSeries.new(root, {
            valueField: "value", categoryField: "category"
        }));
        
     	// 將 JSP 傳遞的職業統計數據轉換為 JavaScript 陣列
     	// c:set 先處理對應的值，避免 c:choose 在 JavaScript 字串內直接出現
     	//一開始沒寫c:set就無法呈現
    var occupationData = [
        <c:forEach var="entry" items="${occupationStats}" varStatus="status">
            { category: "${entry.key}", value: ${entry.value} }<c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ];
        console.log("Occupation Data:", occupationData);
        series.data.setAll(occupationData);

        // 隱藏標籤與連接線
        series.labels.template.setAll({ forceHidden: true });
        series.ticks.template.setAll({ visible: false });

        //series.data.setAll([
        //    { value: 10, category: "One" },
        //    { value: 9, category: "Two" },
        //    { value: 6, category: "Three" },
        //    { value: 5, category: "Four" },
        //    { value: 4, category: "Five" },
        //    { value: 3, category: "Six" },
        //    { value: 1, category: "Seven" }
        //]);

        var legend = chart.children.push(am5.Legend.new(root, {
            centerX: am5.percent(50), x: am5.percent(50), marginTop: 15, marginBottom: 15
        }));

        legend.labels.template.setAll({ fontSize: 12 });
        legend.markerRectangles.template.setAll({ width: 12, height: 12 });
        legend.data.setAll(series.dataItems);

        series.appear(1000, 100);

        // 第二個圖表
        var rootForm = am5.Root.new("chartdiv_form");
        rootForm._logo.dispose();
        var chartForm = rootForm.container.children.push(am5percent.PieChart.new(rootForm, {
            layout: rootForm.verticalLayout,
            paddingTop: 50, paddingBottom: 50, paddingLeft: 50, paddingRight: 50
        }));

        var seriesForm = chartForm.series.push(am5percent.PieSeries.new(rootForm, {
            valueField: "value", categoryField: "category"
        }));
        
     	// 將 JSP 傳遞的風險類型數據轉換為 JavaScript 陣列
        var riskTypeData = [
            <c:forEach var="entry" items="${riskTypeStatistics}">
                { category: "${entry.key}", value: ${entry.value} },
            </c:forEach>
        ];
        seriesForm.data.setAll(riskTypeData);
        

        seriesForm.labels.template.setAll({ forceHidden: true });
        seriesForm.ticks.template.setAll({ visible: false });

        //seriesForm.data.setAll([
        //    { value: 10, category: "One" },
        //    { value: 9, category: "Two" },
        //    { value: 6, category: "Three" },
        //    { value: 5, category: "Four" },
        //    { value: 4, category: "Five" },
        //    { value: 3, category: "Six" },
        //    { value: 1, category: "Seven" }
        //]);

        var legendForm = chartForm.children.push(am5.Legend.new(rootForm, {
            centerX: am5.percent(50), x: am5.percent(50), marginTop: 15, marginBottom: 15
        }));

        legendForm.labels.template.setAll({ fontSize: 12 });
        legendForm.markerRectangles.template.setAll({ width: 12, height: 12 });
        legendForm.data.setAll(seriesForm.dataItems);

        seriesForm.appear(1000, 100);
    });
    
    
    </script>

</body>
</html>

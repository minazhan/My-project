<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>熱門股票清單</title>

    <!-- Bootstrap CSS-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
    
    	body{
    		background-color: #f0f2f5;
    	}
    	
        .header {
        	padding: 15px 20px;
        	text-align: center;
        	font-size: 1.5rem;
        	display: flex;
        	align-items: center;
        	justify-content: center;
        	font-weight:bold; 
        	color:#0000008c; 
        	text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
    	}
    
        .container-fluid {
            max-width: 800px;
            margin: 0 auto;
            flex: 1;
            background-color: #f0f2f5;
            padding: 10px;
        }

        .tab-content {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 20px;
        }

        .tab-pane {
            width: 100%;
            max-width: 800px;
        }

        .tab-pane ul {
            list-style-type: none;
            padding: 0;
        }

        .tab-pane li {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            border-bottom: 1px solid #ddd;
            background-color: #ffffff;
            margin-bottom: 5px;
            border-radius: 4px;
        }

        .tab-pane li span {
            flex: 1;
        }

        h5 {
            text-align: center;
        }
        
        .back-home {
	    	display: flex;      /* 使用 flex 將圖示與文字排列在同一行 */
	 	align-items: center;
		text-decoration: none; /* 移除底線 */
		color: #5f6368; /* 文字顏色 */
		font-size: 16px; /* 文字大小 */
		font-weight: bold; /* 加粗 */
	}
		
	.back-home svg {
		margin-right: 5px; /* SVG 與文字之間的間距 */
		height: 24px;
		width: 24px;

	}
		
	.back-home:hover {
		color: #5a84c3; /* 滑鼠懸停時的文字顏色 */
	}
		
	.back-home:hover svg {
		fill: #5a84c3; /* 滑鼠懸停時的 SVG 顏色 */
	}
		
	.card-body{
		background-color: #deeafc;
            	color: #1565c0; /* 深藍字體 */
    		border: 1px solid #90caf9; /* 藍色邊框 */
	}
		
	.card-text{
		color: #0000008c;
	}
		
	li{
		background-color: #deeafc;
            	color: #0000008c; /* 深藍字體 */
    		/*border: 1px solid #90caf9;  藍色邊框 */
	}
        
    </style>
</head>

<body>
    <div class="container-fluid">
    
    	    <a href="/" class="back-home">
	        	<svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#5f6368"><path d="M640-80 240-480l400-400 71 71-329 329 329 329-71 71Z"/></svg>
	        	<span>回首頁</span>
	        </a>
    
	        <div class="header">
		        熱門股票清單
	    	</div>
	    	
		<div class="tab-content" id="myTabContent">
		
            <!-- 股票清單 -->
            <div class="tab-pane fade show active" id="recommend" role="tabpanel" aria-labelledby="recommend-tab">
                <!--<h3 class="text-center mt-3">熱門股票清單</h3>-->
                <div class="row">
                    <!-- Top 3 股票 -->
                    <c:forEach var="stock" items="${topStocks}" varStatus="status">
                        <div class="col-md-4">
                            <h5 style="color: #f9483b">Top${status.index + 1}</h5>
                            <div class="card mb-4">
                                <div class="card-body">
                                    <h5 class="card-title">${stock.stockName}</h5>
                                    <p class="card-text">
                                        價格: ${stock.price}<br>
                                        成交量: ${stock.volume}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <!-- 其餘股票列表 -->
                <ul>
                    <c:forEach var="stock" items="${remainingStocks}">
                        <li><span>${stock.stockName} - 價格:${stock.price}, 成交量:${stock.volume}</span></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</body>

</html>

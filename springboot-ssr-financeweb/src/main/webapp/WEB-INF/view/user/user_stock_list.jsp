<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>熱門股票清單</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container-fluid {
            max-width: 800px;
            margin: 0 auto;
            flex: 1;
            background-color: #f0f2f5;
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
        
        .update_stock {
			display: flex;
			justify-content: end;
			margin-bottom: 5px;
        }
        
        .btn	{
        	background-color: #69c591;
        	border: 0; /* 清除其他邊框 */
        }
        
        .btn:hover {
			background-color: #5a84c3;
		}
        
    </style>
</head>

<body>

	<!-- menu bar include -->
	<%@ include file="/WEB-INF/view/menu.jspf" %>
			
    <div class="container-fluid">

        <div class="tab-content" id="myTabContent">
            <!-- 股票清單 -->
            <div class="tab-pane fade show active" id="recommend" role="tabpanel" aria-labelledby="recommend-tab">
                <h3 class="text-center mt-3">熱門股票清單</h3>
                <div class=update_stock>
                	<button class="btn btn-primary update-btn">更新</button>
				</div>
                <!-- 其餘股票列表 -->
                <ul>
                    <c:forEach var="stock" items="${stockDtos}">
                        <li><span>${stock.stockName} - 價格:${stock.price}, 成交量:${stock.volume}, 風險:${stock.riskLevel}</span></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</body>

</html>

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
    
        body{
    		background-color: #f0f2f5;
    	}
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
                <h3 class="text-center mt-3" style="font-weight:bold; color:#0000008c; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);">熱門股票清單</h3>
                <div class=update_stock style="display: flex; align-items: center; justify-content: space-between;">
                	 <!-- 最新更新日期 -->
            		<span style="margin-right: 20px; color: gray;">最近更新：${lastUpdated}</span>
					<button class="btn btn-primary update-btn">更新</button>
				</div>
                <!-- 其餘股票列表 -->
                <ul>
                    <c:forEach var="stock" items="${stockDtos}">
                       	<li style="display: flex; justify-content: space-between; margin-bottom: 10px;">
						     <!-- 左欄 -->
							<div>
							  	${stock.stockName} - 價格:${stock.price}, 成交量:${stock.volume}
							</div>
							 <!-- 右欄 -->
							<div>
							    風險:${stock.riskLevel}
							</div>
						</li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    
    <script>
	    document.querySelector(".update-btn").addEventListener("click", function () {
	        const updateButton = this;
	        updateButton.disabled = true; // 禁用按鈕避免多次點擊
	        updateButton.textContent = "更新中...";
	
	        // 發送更新請求
	        fetch("http://localhost:8081/stocks/update", {
	            method: "GET",
	        })
	            .then(response => response.text())
	            .then(message => {
	                console.log(message); // 打印後端返回消息
	                // 刷新整個頁面
	                location.reload();
	            })
	            .catch(error => {
	                console.error("更新失敗：", error);
	                updateButton.textContent = "更新失敗";
	                setTimeout(() => {
	                    updateButton.disabled = false; // 重新啟用按鈕
	                    updateButton.textContent = "更新";
	                }, 3000);
	            });
	    });
    
    </script>
    
    
    
</body>

</html>

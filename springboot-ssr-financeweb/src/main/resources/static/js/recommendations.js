function initializeRecommendationsPage() {
    const balanceContainer = document.getElementById("balance");
    const recommendationsContainer = document.getElementById("recommendations-cards");

    if (!balanceContainer || !recommendationsContainer) {
        console.error("Container 'balance' or 'recommendations-cards' not found");
        return;
    }

    fetch('/api/recommend', {
        method: 'GET',
        credentials: 'include', // 確保傳遞 Cookie
    })
        .then(response => response.json())
        .then(data => {
            console.log(data); // 確認資料是否正確

            // 插入餘額到 'balance'
            const balance = `
                <div class="alert alert-info" role="alert" 
				style="background-color: #deeafc; color: #1565c0; border: 1px solid #90caf9; ">
                    <h5 style="margin-bottom: 0px;">您的當月餘額：${data.balance}</h5>
                </div>
            `;
            balanceContainer.innerHTML = balance;

            // 插入推薦股票卡片到 'recommendations-cards'
            recommendationsContainer.innerHTML = ""; // 清空容器
            data.recommendations.forEach(stock => {
                const card = `
                    <div class="col-md-4">
                        <div class="card mb-4">
                            <div class="card-body">
                                <h5 class="card-title">${stock.stockName}</h5>
                                <p class="card-text">
                                    價格: ${stock.price}<br>
                                    成交量: ${stock.volume}<br>
                                </p>
                                <div class="detailBtn">
                                    <a href="https://tw.stock.yahoo.com/quote/${stock.stockSymbol}" target="_blank" class="btn btn-success" style="background-color:#469872;">詳細資料</a>
                                </div>
                            </div>
                        </div>
                    </div>
                `;
                recommendationsContainer.insertAdjacentHTML("beforeend", card);
            });
        })
        .catch(error => console.error("Error fetching recommendations:", error));
}

// 呼叫函數，確保頁面載入時執行
//initializeRecommendationsPage();

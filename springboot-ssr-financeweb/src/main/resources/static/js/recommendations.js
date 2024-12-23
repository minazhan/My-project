function initializeRecommendationsPage() {
    const container = document.getElementById("recommendations-cards");
    if (!container) {
        console.error("Container 'recommendations-cards' not found");
        return;
    }

    fetch('/rest/recommend', {
        method: 'GET',
        credentials: 'include', // 確保傳遞 Cookie
    })
        .then(response => response.json())
        .then(data => {
            console.log(data); // 確認資料是否正確
            container.innerHTML = ""; // 清空容器

            data.forEach(stock => {
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
                                    <a href="/product/details/${stock.stockId}" class="btn btn-primary">詳細資料</a>
                                </div>
                            </div>
                        </div>
                    </div>
                `;
                container.insertAdjacentHTML("beforeend", card);
            });
        })
        .catch(error => console.error("Error fetching recommendations:", error));
}

// 呼叫函數，確保函數被執行
//initializeRecommendationsPage();

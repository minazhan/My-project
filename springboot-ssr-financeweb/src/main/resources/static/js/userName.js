// 全域變數用於存儲使用者名稱
let userName = null;

// 請求使用者名稱的函數
function fetchUserName() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "/rest/transaction", true); // 從伺服器請求數據
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const response = JSON.parse(xhr.responseText);
            if (response.data && response.data.userName) { // 確認返回數據中有 userName
                userName = response.data.userName; // 提取並存儲使用者名稱
                console.log("使用者名稱：", userName);
            } else {
                console.error("無法獲取使用者名稱：", response.message);
            }
        } else if (xhr.readyState === 4) {
            console.error("HTTP 錯誤，狀態碼：", xhr.status);
        }
    };
    xhr.send();
}

// 在頁面載入時自動執行請求
document.addEventListener("DOMContentLoaded", fetchUserName);

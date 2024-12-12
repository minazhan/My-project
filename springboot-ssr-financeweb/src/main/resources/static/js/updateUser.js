//用於在個人頁面點擊按鈕時，觸發的函數
//把使用者填寫的資料除儲存到變數中
function updateUser() {
    // 收集表單中的使用者輸入資料
    const userData = {
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        gender: document.querySelector('input[name="gender"]:checked').value,
        occupation: document.getElementById('occupation').value,
        email: document.getElementById('email').value,
        birthDate: document.getElementById('birthDate').value,
        //identityNumber: document.getElementById('identityNumber').value // 如果需要傳送身份證字號
    };

    // 傳送 PUT 請求到後端
    fetch('/rest/user', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData), // 將資料轉換為 JSON 格式
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('更新失敗');
            }
            return response.json(); // 解析返回的 JSON 資料
        })
        .then(data => {
            alert('會員資料修改成功！');
            initializeUserInfoPage(); // 可選，重新更新頁面以顯示最新資料
        })
        .catch(error => {
            console.error('更新錯誤:', error);
            alert('更新失敗，請稍後再試');
        });
}

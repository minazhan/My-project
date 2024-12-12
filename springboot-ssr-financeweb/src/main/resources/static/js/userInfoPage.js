function initializeUserInfoPage(){
	
	//請求數據
	fetch('/rest/user')
		.then(response => response.json())
		.then(data => {
			if (data.status === 200 && data.data) {
				const user = data.data;//json資料
				// 填充表格
				document.getElementById('firstName').value = user.firstName;
				document.getElementById('lastName').value = user.lastName;
				document.getElementById('genderMale').checked = user.gender === '男生';
				document.getElementById('genderFemale').checked = user.gender === '女生'; 
				document.getElementById('occupation').value = user.occupation;
				//document.getElementById('identityNumber').value = user.identityNumber;
				document.getElementById('email').value = user.email;
				document.getElementById('birthDate').value = user.birthDate.split('T')[0]; // 格式化日期
			} else {
				alert('查詢用戶資料失敗');
			}
		})
		.catch(error => console.error('錯誤:', error));
}
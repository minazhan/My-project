// 載入頁面內容的通用函數
function loadPage(pageUrl) {
	const basePath = '/html/'; // 基礎路徑
	const xhr = new XMLHttpRequest(); // 用於向伺服器請求數據
	xhr.open("GET", basePath + pageUrl, true); // 初始化請求

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			// 確保獲取 HTML 元素
			const contentElement = document.getElementById('content');
			const sidebarElement = document.querySelector('.sidebar');

			if (!contentElement || !sidebarElement) {
				console.error('content 或 sidebar 元素未找到，請檢查 HTML 結構');
				return;
			}

			// 更新主要內容區域
			contentElement.innerHTML = xhr.responseText;

			// 插入歡迎語句到 sidebar 上方
			if (userName) {
				let welcomeElement = document.querySelector('.welcome-message');
				if (!welcomeElement) { // 防止重複插入
					welcomeElement = document.createElement('div');
					welcomeElement.style.fontSize = '1rem';  //修改字體大小
					welcomeElement.className = 'welcome-message';
					welcomeElement.innerHTML = `<h3>${userName} 您好</h3>`;
					sidebarElement.prepend(welcomeElement); // 在 sidebar 的頂部插入
				} else {
					welcomeElement.innerHTML = `<h3>${userName},您好!</h3>`; // 如果已存在，更新內容
					welcomeElement.style.fontSize = '1rem';
				}
			}

			// 檢查加載內容中是否有特定容器並執行對應邏輯
			if (contentElement.querySelector('.transaction-container')) {
				initializeTransactionPage();
			} else if (contentElement.querySelector('.recommendations-container')) {
				console.log("recommendations-container loaded");
				initializeRecommendationsPage();
			} else if (contentElement.querySelector('.user_info-container')) {
				initializeUserInfoPage();
			} else if (contentElement.querySelector('.summary-container')) {
				initializeSummaryPage();
			}
		}
	};

	xhr.send(); // 發送請求
}

// 首次載入時請求使用者名稱
fetchUserName();


// 初始化收入/支出頁面的函數
function initializeTransactionPage() {
	console.log('Initializing Expenses Page');
	// 執行只在收入/支出頁面需要的操作
	let detailsTable; // 全局變數，用於存儲 DataTable 的實例
	let mockData = [];

	$(document).ready(function() {

		//$ 作為變量前綴
		const $monthBtn = $('#monthBtn');
		const $sixMonthsBtn = $('#sixMonthsBtn');
		const $yearBtn = $('#yearBtn');
		const $startDateInput = $('#startDate');
		const $endDateInput = $('#endDate');

		//隱藏元素
		$startDateInput.hide();
		$endDateInput.hide();

		//初始化圖表 '#4CAF50', '#F44336'
		//Chart.js 需要原生 DOM 元素的 context，.get(0) 來獲取原生的 DOM 元素
		const ctx = $('#donutChart').get(0).getContext('2d');
		const myChart = new Chart(ctx, {
			type: 'doughnut',
			data: {
				labels: ['收入', '支出'],
				datasets: [{
					label: '總計',
					data: [0, 0],
					backgroundColor: ['#628acb ', '#ed9075']
				}]
			},
			options: {
				responsive: true,
				plugins: {
					legend: { display: true, position: 'top' }
				}
			}
		});

		// 初始化結束日期選擇器
		$endDateInput.datepicker({
			format: 'yyyy-mm',
			autoclose: true,
			startView: 1,
			minViewMode: 1,
			language: 'zh-TW',
		}).on('changeDate', function() {
			const selectedMonth = new Date($(this).datepicker('getDate'));
			const endDate = new Date(selectedMonth.getFullYear(), selectedMonth.getMonth() + 1, 0); // 設置為該月的最後一天
			const today = new Date();
			$(this).val(formatDate(endDate)); // 在輸入框顯示完整日期格式
			console.log('用戶選擇的結束日期:', endDate);

			// 使用 jQuery 來檢查按鈕的 active 樣式
			if ($sixMonthsBtn.hasClass('active')) {
				const startDate = new Date(endDate.getFullYear(), endDate.getMonth() - 5, 1); // 近6月
				$startDateInput.val(formatDate(startDate)); // 自動填入開始日期
				filterAndUpdate(startDate, endDate); // 篩選表格並更新圖表
			} else if ($yearBtn.hasClass('active')) {
				const startDate = new Date(endDate.getFullYear() - 1, endDate.getMonth(), 1); // 近1年
				$startDateInput.val(formatDate(startDate)); // 自動填入開始日期
				filterAndUpdate(startDate, endDate); // 篩選表格並更新圖表
			} else if ($monthBtn.hasClass('active')) {
				// const startDate = new Date(today.getFullYear(), today.getMonth(), 1);
				// const endDate = new Date(today.getFullYear(), today.getMonth() + 1, 0);
				filterAndUpdate(selectedMonth, endDate); // 篩選表格並更新圖表
			}
		});

		// 請求數據(和後端)
		fetch('/rest/transaction')
			.then(response => response.json())
			.then(data => {
				if (data.status === 200 && data.data.transactions.length > 0) {
					const { userName, transactions } = data.data;
					mockData = transactions; // 保存後端返回的數據到全局變數
					console.log("後台資料" + mockData);
					updateTransactionTable(mockData); // 初始化表格
					//updateChart(mockData, myChart);  初始化圖表
					filterAndUpdate(new Date('2024-01-01'), new Date('2024-12-31')); // 初始化篩選，展示全年的數據
				} else {
					console.log('查無資料');
				}
			})
			.catch(error => console.error('Error:', error));

		//點擊月按鈕
		$monthBtn.on('click', function() {
			toggleDatePicker($monthBtn, null, $endDateInput);
		});

		//點擊近六月按鈕
		$sixMonthsBtn.on('click', function() {
			toggleDatePicker($sixMonthsBtn, $startDateInput, $endDateInput);
		});

		//點擊年按鈕
		$yearBtn.on('click', function() {
			toggleDatePicker($yearBtn, $startDateInput, $endDateInput);
		});

		// 切換日期選擇器的顯示與隱藏狀態(函數)
		function toggleDatePicker($button, $startDatePicker, $endDatePicker) {
			// 如果按鈕已經是 active，則移除 active 樣式並隱藏日期選擇器
			if ($button.hasClass('active')) {
				$button.removeClass('active');
				if ($startDatePicker) $startDatePicker.hide();
				if ($endDatePicker) $endDatePicker.hide();
			} else {
				// 否則，移除其他按鈕的 active 樣式，顯示當前按鈕的日期選擇器
				$monthBtn.add($sixMonthsBtn).add($yearBtn).removeClass('active');
				$button.addClass('active');

				// 顯示日期選擇器
				if ($startDatePicker) $startDatePicker.show();
				if ($endDatePicker) $endDatePicker.show();
			}
		}



		// 初始化 DataTable 並存儲在變數中
		const detailsTable = $('#detailsTable').DataTable({ //
			"lengthChange": false, // 隱藏 "entries per page" 選項
			"searching": false,     // 隱藏搜尋框
			"info": false,           // 隱藏 "Showing X to Y of Z entries"
			"language": {
				"emptyTable": "請輸入資料"
			},
			"pageLength": 5, // 設定每頁顯示的筆數
			"columnDefs": [
				{
					"targets": -1, // 最後一欄
					"orderable": false, // 禁用排序
					"data": null,
					"defaultContent": `<button class="delete-btn btn btn-danger btn-sm" 
										style="color: #f05757; border: 1px solid #f05757; background-color: transparent;">刪除</button>`
				}
			]
		});

		// ===== 加號按鈕功能 =====
		const $addEntryBtn = $('#addEntryBtn');
		const $content1 = $('#content1');
		//let mockData = {};

		if ($addEntryBtn.length) {
			$addEntryBtn.on('click', function() {
				console.log('觸發的元素：', $(this));

				// 檢查表單是否已經存在
				const formContainer = $content1.find('.form-container');
				if (formContainer.length) {
					// 如果表單已經存在，切換顯示狀態
					console.log('表單已存在，切換顯示狀態');
					formContainer.toggle(); // 顯示或隱藏
					$content1.attr('data-expanded', formContainer.is(':visible') ? 'true' : 'false');
				} else {
					// 動態插入新增收入/支出的表單
					//轉換成ajax時，原本html()，清空頁面內容
					$content1.append(`
                    <h3 style="text-align: center; margin-bottom: 20px; margin-top: 10px; border-bottom: 1px solid gray; display: inline-block; ">新增</h3>
                    <div class="form-container" style="display: flex; justify-content: center; align-items: center; gap: 10px;">
                        <div class="form-group" style="width: auto; text-align: center;">
                            <label for="type">類型：</label>
                            <select id="type" style="padding: 5px;">
                                <option value="income">收入</option>
                                <option value="expense">支出</option>
                            </select>
                        </div>
                        <div class="form-group" style="text-align: center;">
                            <label for="classify">分類：</label>
                            <select id="classify" style="padding: 5px;">
                                <option>薪水</option>
                                <option>獎金</option>
                                <option>交易</option>
                                <option>股息</option>
                                <option>租金</option>
                                <option>投資</option>
                                <option>其他</option>
                            </select>
                        </div>
                        <div class="form-group" style="text-align: center;">
                            <label for="amount">金額：</label>
                            <input type="number" id="amount" placeholder="輸入金額" style="padding: 5px; width: 100px;" />
                        </div>
                        <div class="form-group" style="text-align: center;">
                            <label for="date">日期：</label>
                            <input type="date" id="date" value="${new Date().toISOString().split('T')[0]}" style="padding: 5px;" />
                        </div>
                        <button id="submitEntryBtn" style="margin-left: auto; height: 40px;">OK</button>
                    </div>
                    `);

					// $('#submitEntryBtn').css({
					//     'background-color': '#007bff',
					//     'color': 'white',
					//     'padding': '10px 20px',
					//     'font-size': '14px',
					//     'border-radius': '5px',
					//     'border': 'none'
					// });

					$content1.attr('data-expanded', 'true');

					// ======更新分類選項======
					// 定義收入與支出的分類選項
					const incomeOptions = ['薪水', '獎金', '交易', '股息', '租金', '投資', '其他'];
					const expenseOptions = ['早餐', '午餐', '晚餐', '飲品', '點心', '交通', '購物', '娛樂', '日用品', '房租', '醫療', '其他'];

					// 獲取類型和分類的下拉框
					const $typeSelect = $('#type');
					const $classifySelect = $('#classify');

					// 初始化分類選項
					updateClassifyOptions(incomeOptions);

					// 監聽類型選擇的變化，動態更新分類選項
					$typeSelect.on('change', function() {
						console.log('觸發的元素：', $(this));
						const selectedType = $(this).val(); // 獲取使用者選擇的類型
						const options = selectedType === 'income' ? incomeOptions : expenseOptions;
						updateClassifyOptions(options); // 更新分類選項
					});

					// 更新分類選項的函數
					function updateClassifyOptions(options) {
						$classifySelect.empty(); // 清空目前的分類選項
						options.forEach(option => {
							// 動態添加新分類選項
							$classifySelect.append($('<option>').val(option).text(option));
						});
					}

					// ======提交資料功能======
					// 綁定 OK 按鈕事件
					const $submitEntryBtn = $('#submitEntryBtn');
					if ($submitEntryBtn.length) {
						$submitEntryBtn.on('click', function() {
							console.log('觸發的元素：', $(this));
							submitEntry();
						});
					}

					// 定義送出資料的函數
					function submitEntry() {
						const type = $('#type').val(); // 取得類型
						const classify = $('#classify').val(); // 取得分類
						const amount = parseFloat($('#amount').val()); // 取得金額並轉為數字
						const date = $('#date').val(); // 取得日期

						if (isNaN(amount) || amount <= 0) {
							alert('請輸入有效的金額');
							return;
						}

						// 準備要送到後端的資料(和後端)
						const transactionData = {
							transactionType: type,
							category: classify,
							expense: amount,
							transactionDate: date
						};

						// 發送 AJAX 請求到後端
						fetch('/rest/transaction', {
							method: 'POST',
							headers: {
								'Content-Type': 'application/json'
							},
							body: JSON.stringify(transactionData)
						})
							.then(response => response.json())
							.then(data => {
								if (data.status === 200) {
									alert('資料新增成功！');
									// 更新表格或重新加載數據
									location.reload();
								} else {
									alert('新增失敗，請稍後再試！');
								}
							})
							.catch(error => console.error('Error:', error));



						console.log(`提交內容：類型=${type}, 分類=${classify}, 金額=${amount}, 日期=${date}`);

						// 使用 DataTables API 新增一筆數據
						detailsTable.row.add([
							classify,
							type === 'income' ? '收入' : '支出',
							date,
							amount,
							null
						]).draw();


						// 更新圖表數據
						const currentMonth = date.slice(0, 7).replace('-', '年') + '月';
						if (!mockData[currentMonth]) {
							mockData[currentMonth] = { income: 0, expense: 0 };
						}
						mockData[currentMonth][type] += amount;

						// 更新月份選擇器中的值，設置選擇的日期為當前月份
						const $monthPicker = $('#datepicker').find('input');
						const formattedMonth = date.slice(0, 7);
						$monthPicker.val(formattedMonth);
						console.log('月份選擇器更新為:', formattedMonth);

						// 確保月份資料存在
						if (!mockData[formattedMonth]) {
							mockData[formattedMonth] = { income: 0, expense: 0 };
						}
						mockData[formattedMonth][type] += amount;

						updateChart(mockData[formattedMonth].income, mockData[formattedMonth].expense);
						console.log('更新圖表數據:', mockData[formattedMonth]);

						// 清空輸入框或返回初始畫面
						//$('#content').html('')...，要改成append時才不會被清空
						$('#content').append('').attr('data-expanded', 'false');

						// 篩選並更新圖表，以確保前端數據顯示最新狀態
						const startDate = new Date(formattedMonth + '-01');
						const endDate = new Date(startDate.getFullYear(), startDate.getMonth() + 1, 0);
						filterAndUpdate(startDate, endDate);
					}

				}
			});
		} else {
			console.error('找不到 addEntryBtn 按鈕，請檢查 HTML 結構');
		}


		// 格式化日期(函數)
		function formatDate(date) {
			const year = date.getFullYear();
			const month = String(date.getMonth() + 1).padStart(2, '0');
			return `${year}-${month}`;
		}

		// 篩選表格數據並更新圖表(函數)
		function filterAndUpdate(start, end) {
			console.log(`篩選範圍: ${start} ~ ${end}`);
			const $rows = $('#detailsTable tbody tr'); // 使用 jQuery 選擇表格中的所有行

			// 篩選表格資料
			$rows.each(function() {
				const $row = $(this);
				const $dateCell = $row.find('td').eq(2); // 找到第三個單元格（假設日期在第3列）

				if ($dateCell.length) {
					const rowDate = new Date($dateCell.text().trim());
					// 使用 jQuery 的 .toggle() 方法來控制顯示或隱藏
					$row.toggle(rowDate >= start && rowDate <= end);
				}
			});

			// 根據篩選範圍更新圖表數據，計算篩選範圍內的總收入和支出
			let totalIncome = 0;
			let totalExpense = 0;

			mockData.forEach(transaction => { //利用後端資料更新收入和支出，用於更新圖表
				const transactionDate = new Date(transaction.transactionDate);
				if (transactionDate >= start && transactionDate <= end) {
					if (transaction.transactionType === 'income') { //'收入'
						totalIncome += transaction.expense;
					} else if (transaction.transactionType === 'expense') { //'支出'
						totalExpense += transaction.expense;
					}
				}
			});
			console.log(mockData);
			console.log(`Total Income: ${totalIncome}, Total Expense: ${totalExpense}`);


			// 更新圖表
			updateChart(totalIncome, totalExpense);
		}

		// 更新圖表數據(函數)
		function updateChart(income, expense) {
			if (!myChart) {
				console.error('圖表未初始化');
				return;
			}
			myChart.data.datasets[0].data = [income, expense];
			myChart.update();
		}

		//更新表格
		function updateTransactionTable(transactions) {
			const tableBody = document.getElementById('transactionTableBody');
			tableBody.innerHTML = ''; // 清空表格內容

			transactions.forEach(transaction => {
				// 映射類型到中文
				const transactionType = transaction.transactionType === 'income' ? '收入' : '支出';

				const row = `
		            <tr>
		                <td>${transaction.category}</td>
						<td>${transactionType}</td> <!-- 顯示中文類型 -->
		                <td>${transaction.transactionDate.split('T')[0]}</td> <!-- 格式化日期 -->
		                <td>${transaction.expense}</td>
						<td>
						   	<button class="delete-btn btn btn-danger btn-sm" data-id="${transaction.transactionId}">刪除</button>
						</td>
		            </tr>
		        `;
				tableBody.insertAdjacentHTML('beforeend', row);
			});

			// 綁定刪除按鈕的點擊事件
			document.getElementById('transactionTableBody').addEventListener('click', function(event) {
				if (event.target.classList.contains('delete-btn')) {
					const transactionId = event.target.getAttribute('data-id');
					deleteTransaction(transactionId);
				}
			});
		}

		function deleteTransaction(transactionId) {
			fetch(`/rest/transaction/${transactionId}`, {
				method: 'DELETE',
				headers: {
					'Content-Type': 'application/json',
				},
			})
				.then(response => {
					if (!response.ok) {
						throw new Error('刪除失敗');
					}
					// 刪除成功後重新更新表格數據
					return fetch('/rest/transaction');
				})
				.then(response => response.json())
				.then(data => {
					const transactions = data.data.transactions; // 確認資料結構
					updateTransactionTable(transactions);
					location.reload();
				})
				.catch(error => {
					console.error('刪除失敗:', error);
					alert('刪除失敗');
				});
		}



	});
}

function initializeSummaryPage() {
	let detailsTable;

	$(document).ready(function() {
		const $yearBtn = $('#yearBtn');
		const $selectDateInput = $('#selectDateInput');

		//初始化圖表
		const ctx = $('#lineChart').get(0).getContext('2d');
		const myChart = new Chart(ctx, {
			type: 'line',
			data: {
				labels: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月',],
				datasets: [
					{
						label: '結餘',
						data: [],
						borderColor: 'rgba(255, 99, 132, 1)',
						backgroundColor: 'rgba(255, 99, 132, 0.2)',
						tension: 0.4
					},
					{
						label: '收入',
						data: [],
						borderColor: 'rgba(54, 162, 235, 1)',
						backgroundColor: 'rgba(54, 162, 235, 0.2)',
						tension: 0.4
					},
					{
						label: '支出',
						data: [],
						borderColor: 'rgba(88, 196, 67, 1)',
						backgroundColor: 'rgba(88, 196, 67, 0.2)',
						tension: 0.4
					}
				]
			},
			options: {
				responsive: true,
				maintainAspectRatio: false, // 允許高度自適應
				plugins: {
					legend: {
						display: true,
						position: 'top',
					},
					tooltip: {
						enabled: true,
					}
				},
				scales: {
					x: {
						beginAtZero: true
					},
					y: {
						beginAtZero: true
					}
				}
			}
		});

		$selectDateInput.datepicker({
			format: 'yyyy' + '年', // 僅顯示年份
			autoclose: true, // 選擇後自動關閉
			startView: 2, // 預設顯示十年的範圍
			minViewMode: 2, // 僅允許選擇年份
			language: 'zh-TW', // 語言設置為繁體中文
		}).on('changeDate', function() {
			const selectedYear = $(this).datepicker('getDate').getFullYear();

			if ($yearBtn.hasClass('active')) {
				filterAndUpdate(selectedYear); //更新圖表並更新圖表
			}
		});

		// 會從後端API獲取數據，並更新圖表
		function filterAndUpdate(selectedYear) {
			//從後端API獲取數據
			fetch(`/api/data/line-chart?year=${selectedYear}`)
				.then(response => response.json)
				.then(data => {
					// 更新圖表數據
					const labels = data.labels; // 月份標籤
					const balanceData = data.datasets.結餘; // 結餘數據
					const incomeData = data.datasets.收入; // 收入數據
					const expenseData = data.datasets.支出; // 支出數據

					// 更新圖表
					myChart.data.labels = labels;
					myChart.data.datasets[0].data = balanceData;
					myChart.data.datasets[1].data = incomeData;
					myChart.data.datasets[2].data = expenseData;

					// 重新渲染圖表
					myChart.update();

				})

				.catch(error => {
					console.error('Error fetching data:', error);
					alert('無法獲取數據，請稍後重試');
				});

		}

		// 初始化 DataTable 並存儲在變數中
		const detailsTable = $('#detailsTable').DataTable({ //
			"lengthChange": false, // 隱藏 "entries per page" 選項
			"searching": false,     // 隱藏搜尋框
			"info": false,           // 隱藏 "Showing X to Y of Z entries"
			"language": {
				"emptyTable": "目前無資料"
			},
			"pageLength": 5 // 設定每頁顯示的筆數
		});

	});
}

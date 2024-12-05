<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- 用於數字、日期格式化和國際化 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- 用於字符串操作 -->

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main_page</title>
    <!-- 引入bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

    <!-- 引入 Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <!-- 引入自訂的 CSS -->
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">

    <!--引入 Google Fonts 的字型-->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">

    <!--引入 Icons-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

</head>


<body>
    <!-- 加載 Bootstrap 的 JavaScript 功能 
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>-->
        
    <script src="<c:url value='/js/script.js'/>"></script>

	<!-- <h1>${message}</h1> -->
    <!-- 導覽列部分 -->
    <!-- .navbar: 這是 Bootstrap 預設的導覽列樣式類別 -->
    <!-- <nav> 元素包含了一個類別 class="navbar navbar-expand-lg navbar-light fixed-top"，這表示這是一個導覽列 -->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top">
        <div class="container-fluid"> <!--  Bootstrap 提供的一個類別，始終佔據視窗的全部寬度，無論螢幕大小 -->
            <a class="navbar-brand" href="#">XX理財</a>

            <!-- 漢堡按鈕，控制 Offcanvas，適用於小螢幕 -->
            <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
                aria-controls="offcanvasNavbar" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <!-- 導覽列內容（僅在大螢幕顯示） -->
            <!-- .collapse 是 Bootstrap 的類別，用於隱藏或顯示內容，通常透過 JavaScript 進行控制，比如通過按鈕來折疊或展開某個區塊 -->
            <!-- .navbar-collapse 是 Bootstrap 提供的一個特定類別，用於控制導覽列（導航欄）在小螢幕和大螢幕中的折疊和展開 -->
            <!-- 使用 .collapse.navbar-collapse 的情況下，它是為了更加具體地選擇那些同時包含 collapse 和 navbar-collapse 的元素。
                這樣可以提高 CSS 的選擇權重，確保你針對該元素的樣式會生效 -->
            <!-- d-flex justify-content-between 如果添加這個d-flex 類別使 <div> 成為一個 Flex 容器，justify-content-between 則讓容器內的元素在兩端均勻分布。
                這個佈局方法能確保 "XX理財" 標題在左邊，而 "註冊" 和 "Login" 按鈕在右邊，兩者之間保持適當的距離 -->
            <!-- collapse 類別用於折疊導覽列的內容 -->
            <div class="collapse navbar-collapse " id="navbarContent">
                
                 <!-- 搜尋框 -->
                 <div id="searchBox" class="search-box ">
                    <input type="text" placeholder="Search...">
                    <button id="closeSearch">✖</button>
                </div>

                <!-- 搜尋按鈕，僅在大螢幕顯示 -->
                <!--  <button class="btn btn-outline-secondary ms-2 d-none d-lg-inline-block" id="searchBtn" type="button">-->
                    <!-- <i class="fas fa-search"></i>  Font Awesome 放大鏡圖示 -->
                    <!--<i class="material-icons">search</i>-->
                <!--</button>-->

                <!-- 註冊和登入 -->
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0 ">
                    <li class="nav-item">
                        <a class="nav-link" href="/">首頁</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">關於我們</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">個人頁面</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/users">後臺登入</a>
                    </li>
                </ul>
               


            </div>
        </div>
    </nav>

    <!-- 導覽列下方的圖片 -->
    <div class="container-fluid p-0 position-relative" style="text-align: center;">
        <img src="<c:url value='/img/bg16.jpg' />" class="full-width-image" alt="...">

        <!-- 疊加在圖片上的文字 -->
        <div class="overlay-content position-absolute w-100" style="top: 50%; left: 50%; transform: translate(-50%, -50%); color: white;">
            <h2>圖片上文字描述</h2>
            <!-- mt-3讓按鈕和標題之間保持適當的距離 -->
            <!-- custom-button 自訂的 CSS -->
            <a href="register" class="btn btn-primary custom-button mt-3">註冊</a>
            <a href="login" class="btn btn-primary custom-button mt-3">登入</a>
        </div>

    </div>


    <!-- Offcanvas 組件，用於左側滑出菜單 -->
    <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
        <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="offcanvasNavbarLabel"></h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">
            <!-- 搜尋框 -->
            <div class="input-group mb-3"> <!-- 將搜尋欄合併一起 -->
                <input type="text" class="form-control" placeholder="Search...">
                <button class="btn btn-primary" type="button">
                    <i class="fas fa-search"></i>
                </button>
            </div>
            <!-- 導覽選單 -->
            <ul class="navbar-nav">
                <li><a class="nav-link" href="#">首頁</a></li>
                <li><a class="nav-link" href="#">關於我們</a></li>
            </ul>
        </div>
    </div>

    <!-- 搜尋 Modal -->
    <div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="searchModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="searchModalLabel">搜尋</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="請輸入搜尋內容...">
                        <button class="btn btn-primary" type="button">
                            <i class="fas fa-search"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 主內容部分，增加頁面高度以便於滾動 -->
    <!--  
    <div style="height: 2000px; padding-top: 100px;">
        <h1 class="text-center">滾動以查看導覽列變化</h1>-->

        <div class="container my-5 custom-padding">
            <div class="row ">
                <div class="col-md-4 col-sm-6 col-12 mb-4" style="height: 350px;">
                    <div class="card h-100 border-0 shadow-sm rounded" >
                        <div class="card-body d-flex flex-column justify-content-center align-items-center text-center" style="height: 100%;">
                            <i class="material-icons one" style="font-size: 48px; color: #6c757d;">help_outline</i>
                            <h5 class="card-title mt-3">當父母母輩的理財經驗不再管用</h5>
                            <p class="card-text text-muted">不理財，真的可以嗎？</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-sm-6 col-12 mb-4">
                    <div class="card h-100 border-0 shadow-sm rounded">
                        <div class="card-body d-flex flex-column justify-content-center align-items-center text-center" style="height: 100%;">
                            <i class="material-icons" style="font-size: 48px; color: #6c757d;">help_outline</i>
                            <h5 class="card-title mt-3">當我還在猶豫何時開始投資理財</h5>
                            <p class="card-text text-muted">沒有計劃，更面對不了變化。</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-sm-6 col-12 mb-4">
                    <div class="card h-100 border-0 shadow-sm rounded">
                        <div class="card-body d-flex flex-column justify-content-center align-items-center text-center" style="height: 100%;">
                            <i class="material-icons" style="font-size: 48px; color: #6c757d;">help_outline</i>
                            <h5 class="card-title mt-3">為什麼理財經驗不那麼愉快？</h5>
                            <p class="card-text text-muted">陪伴我的財務旅途專家。</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>




    <!-- JavaScript：監聽滾動事件 -->
    <script>
        window.addEventListener('scroll', function () {
            const navbar = document.querySelector('.navbar');
            if (window.scrollY > 50) { // 當滾動超過 50px 時
                navbar.classList.add('scrolled');
            } else {
                navbar.classList.remove('scrolled');
            }
        });

        const getButtonById=(id)=>{
            document.getElementById(id);
        }
        document.getElementById("searchBtn").addEventListener("click",  ()=> {
            var searchBox =getButtonById("searchBox");
            var searchBtn = getButtonById("searchBtn");


            // 獲取搜尋按鈕的位置
            var rect = searchBtn.getBoundingClientRect();

            // 設定搜尋框的位置 - 對齊按鈕尾部(rect.right - searchBox.offsetWidth)
            searchBox.style.top = (rect.bottom + window.scrollY) + "px";
            searchBox.style.left = (rect.left + window.scrollX) + "px";

            // 顯示搜尋框
            searchBox.style.display = "block";
        });

        document.getElementById("closeSearch").addEventListener("click", function () {
            document.getElementById("searchBox").style.display = "none";
        });


    </script>


</body>

</html>
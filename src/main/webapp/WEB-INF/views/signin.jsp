<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
			integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
		<title>登入</title>
	</head>

	<body>

		<div class="container">
			<div class="row">
				<div class="col-7">
					<!-- advertisement -->
				</div>
				<div class="col-5">
					<h1 class="mt-5 mb-5 fw-bold">歡迎登入</h1>
					<form class="mb-5 d-flex flex-column " action="${ pageContext.request.contextPath }/mvc/signInValid"
						method="post" id="myform">
						<div class="mb-4">
							<input type="text" class="form-control mb-2" required="true" id="sMail" name="smail"
								placeholder="請輸入註冊信箱" />
							<input type="password" class="form-control mb-2" required="true" id="sPwd" name="spwd"
								placeholder="請輸入密碼">
							<div class="text-danger ms-3" style="font-size:12px;">${ loginMsg }</div>
						</div>
						
						<div>
							<input type="submit" id="in" class="btn btn-outline-primary me-2" value="登入" />
							<a class="btn btn-outline-primary me-2" href="${ pageContext.request.contextPath }/mvc/signup">註冊</a>
							<a class="btn btn-outline-primary" href="${ pageContext.request.contextPath }/mvc/forgetpwd">忘記密碼</a>
						</div>
					</form>
					<a class="btn btn-outline-danger h2" href="./entityList">測試API 會員JSON</a>
				</div>
			</div>
			


		</div>

		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
			crossorigin="anonymous"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		
	</body>

	</html>
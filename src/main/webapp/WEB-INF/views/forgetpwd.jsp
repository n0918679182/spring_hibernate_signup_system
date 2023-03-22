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
			<div class="row w-100 jsutify-content-center">
				<div class="col-5">
					<h1 class="mt-5 mb-5 fw-bold">忘記密碼</h1>
					<form class="mb-5 d-flex flex-column " action="${ pageContext.request.contextPath }/mvc/sendMail"
						method="post" id="myform">
						<div class="mb-4">
							<input type="text" class="form-control mb-2" required="true" name="smail"
								placeholder="請輸入註冊信箱" />
						</div>
						<div>
							<input type="submit" class="btn btn-outline-primary me-2" value="送出" />
							<a class="btn btn-outline-primary me-2" href="${ pageContext.request.contextPath }/mvc/signIn">返回登入</a>
						</div>
					</form>
				</div>
			</div>
		</div>

		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
			crossorigin="anonymous"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		
	</body>

	</html>
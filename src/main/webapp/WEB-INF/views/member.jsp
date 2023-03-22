<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spform" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<title>會員資訊</title>
</head>
<body>
 	<div class="container d-flex flex-column justify-content-center align-items-center">
 		<h2 class="mt-5 mb-3 fw-bold">會員資料</h2>
		
		<div class="row w-100 d-flex justify-content-center mb-5">
			<div class="col-6">
				<spform:form class="d-flex flex-column mb-4 w-100" 
							 modelAttribute="loginMember"
							 action="${ pageContext.request.contextPath }/mvc/updatepwd">
					<div class="w-100 mb-3">
						<label class="me-3 mb-2">信箱： </label>
						<spform:input class="me-3 form-control w-100 mb-2" path="smail" readonly="true"/>
					</div>
					<div class="w-100 mb-3">
						<label class="me-3 mb-2">密碼： </label>
						<spform:input type="password" onfocus="clearInpput()" id="pwd" class="me-3 form-control w-100 mb-2" path="spwd"/>
					</div>
					<div class="w-100 mb-3">
						<label class="me-3 mb-2">編號： </label>
						<spform:input class="me-3 form-control w-100 mb-2" path="sno" readonly="true"/>
					</div>
					<div class="w-100 mb-3">
						<label class="me-3 mb-2">姓名： </label>
						<spform:input class="me-3 form-control w-100 mb-2" path="sname" readonly="true"/>
					</div>
					<div class="w-100 mb-3">
						<label class="me-3 mb-2">身分證號： </label>
						<spform:input class="me-3 form-control w-100 mb-2" path="siden" readonly="true"/>
					</div>
					<div class="w-100 mb-3">
						<label class="me-3 mb-2">生日： </label>
						<spform:input class="me-3 form-control w-100 mb-2" path="sbday" readonly="true"/>
					</div>
					<div class="w-100 mb-3">
						<label class="me-3 mb-2">性別： </label>
						<spform:input class="me-3 form-control w-100 mb-2" path="ssexStr" readonly="true"/>
					</div>
					<input class="btn btn-outline-primary" type="submit" id="realSignUpSubmit" value="修改" />
				</spform:form>
			</div>
		</div>
		
		
		<div class="d-flex justify-content-center fixed-bottom bg-white py-2">
			<a class="btn btn-outline-primary me-3" href="http://localhost:8080/tlTrainSignUpSys/">返回首頁</a>
		</div>
		
 	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
	<script type="text/javascript">
		function clearInpput(){
			const pwd = document.getElementById("pwd");
			pwd.value="";
		}
	</script>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<title>新增學生</title>
</head>
<body>
	<div class="row justify-content-center w-100">
		<div class="col-6">
			<div class="container d-flex flex-column justify-content-center align-items-start">
		 		<h2 class="mt-5 mb-3 fw-bold">會員註冊</h2>
				<form class="d-flex flex-column mb-4 w-100" 
					  action="${ pageContext.request.contextPath }/mvc/createMember" 
					  method="post">
					
					<div class="w-100 mb-3">
						<label class="me-3 mb-2" for="smail">信箱： </label>
						<input class="me-3 form-control w-100 mb-2" required="true" type="text" id="smail" name="smail" placeholder="請輸入信箱" onblur="checkMail()">
						<p class="text-danger ps-3" style="font-size:12px;" id="sMailError"></p>
					</div>
					
					<div class="w-100 mb-3">
						<label class="me-3 mb-2" for="spwd">密碼： </label>
						<input class="me-3 form-control w-100" required="true" type="password" id="spwd" name="spwd" placeholder="請輸入密碼">
					</div>
					
					<div class="w-100 mb-3">
						<label class="me-3 mb-2" for="sname">姓名： </label>
						<input class="me-3 form-control w-100" required="true" type="text" id="sname" name="sname" placeholder="請輸入姓名">
					</div>
					
					<div class="w-100 mb-3">
						<label class="me-3 mb-2" for="siden">身分證號： </label>
						<input class="me-3 form-control w-100" required="true" type="text" id="siden" name="siden" placeholder="請輸入身分證號">
					</div>
					
					<div class="w-100 mb-3">
						<input type="radio" id="male" name="ssex" value="1" checked>
      					<label class="me-2" for="male">男生</label>
      					<input type="radio" id="female" name="ssex" value="0">
      					<label for="female">女生</label>
					</div>
					
					<div class="w-100 mb-5">
						<label class="me-3 mb-2" for="sbday">生日： (格式: yyyy/MM/dd)</label>
						<input class="me-3 form-control w-100" required="true" type="text" id="sbday" name="sbday" placeholder="請輸入生日 (ex:1993/10/05)">
					</div>
					
					<input class="btn btn-outline-primary insertBT" type="button" onclick="signupValid()" value="註冊" />
					<input class="d-none" type="submit" id="realSignUpSubmit" value="註冊" />
				</form>
				<a class="btn btn-outline-primary me-2" href="http://localhost:8080/tlTrainSignUpSys/mvc/signIn">返回登入</a>
		 	</div>
		</div>
	</div>
 	
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" 
		 	integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" 
		 	crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script type="text/javascript">
	
		const sname = document.getElementById("sname");
		const smail = document.getElementById("smail");
		const sMailError = document.getElementById("sMailError");
		const spwd = document.getElementById("spwd");
		const siden = document.getElementById("siden");
		const ssexAry = document.getElementsByName("ssex");
		let ssex = 0;
		ssexAry.forEach(o=>{
			if(o.checked){
				ssex = o.value
			}
		});
		const sbday = document.getElementById("sbday");
		
		let haveError = true;
		
		function checkMail(){
			// 判斷信箱格式
			const regexSmail = new RegExp(/^[^@\s]+@[^@\s]+\.[^@\s]+$/);
			if(regexSmail.test(smail.value)){
				// 判斷註冊信箱是否重複
				$.ajax({
					url : "./signUpValid", // controller的mapping路徑
					type : 'POST',
					//若是POST方法，可多加入一行 data: JSON.stringify({ parameter: value }) 放置要傳的引數
					data : "smail="+smail.value,
					error : function() {
						haveError = true;
						alert('請求失敗!');
					},
					success : function(a) {
						if(a==0){
							sMailError.innerHTML = "信箱已註冊! 請直接登入或更換其他信箱註冊!";
							haveError = true;
						}else if(a==1){
							sMailError.innerHTML ="";
							haveError = false;
						}
					}
				});
			}else{
				sMailError.innerHTML ="信箱格式錯誤! 請重新輸入!";
				haveError = true;
			}
		}
	
		function signupValid(){
			if(!haveError){
				$("#realSignUpSubmit").click();
			}
		}
		
		
	</script>

</body>
</html>
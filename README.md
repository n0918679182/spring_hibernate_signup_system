# 註冊登入系統

- 此作品是使用 Springboot 搭配 Hibernate 以及 mySQL 來模擬登入註冊的作品
- 另外還有搭配使用 javaMail 來做忘記密碼的信件寄送

- 主要功能有：
    - 在註冊的時候，帳號能透過 Ajax 判斷是否已經註冊過了，如果有重複就不能註冊並且跑出提示訊息
    - 密碼的部分都使用 md5 做加密
    - 註冊完之後登入，可以顯示註冊資料
    - 忘記密碼頁，可以輸入信箱，送出後會將密碼自動更改為一個隨機的密碼，並寄送此密碼到輸入的信箱
    - 登入後，可以更改自己的密碼

## 導覽

### - 登入畫面
<kbd>
    <img src="https://github.com/n0918679182/spring_hibernate_signup_system/blob/master/readme_img/signin.png?raw=true">
</kbd>

### - 註冊畫面
##### input 失焦時會透過 ajax 送出請求給後端來判斷信箱是否重複註冊
<kbd>
    <img src="https://github.com/n0918679182/spring_hibernate_signup_system/blob/master/readme_img/signup.png?raw=true">
</kbd>

### - 會員資料畫面
##### 密碼使用 md5 做加密
<kbd>
    <img src="https://github.com/n0918679182/spring_hibernate_signup_system/blob/master/readme_img/detail.png?raw=true">
</kbd>

### - 忘記密碼畫面
##### 透過 javamail 寄信
<kbd>
    <img src="https://github.com/n0918679182/spring_hibernate_signup_system/blob/master/readme_img/forget.png?raw=true">
</kbd>

### - Mailtrap 收到信件
##### Mailtrap 模擬收到信件
<kbd>
    <img src="https://github.com/n0918679182/spring_hibernate_signup_system/blob/master/readme_img/mailtrap.png?raw=true">
</kbd>

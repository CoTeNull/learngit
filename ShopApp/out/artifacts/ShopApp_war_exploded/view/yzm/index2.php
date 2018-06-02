<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登陆页面</title>
    <style>
        *{
            margin: 0;
            padding: 0;
            font-family: 楷体;
        }
        .form{
            box-sizing: border-box;
            padding: 50px;
            width: 500px;
            height: 300px;
            background: lightskyblue;
            margin: 20px auto;
            border-radius: 10px;
            text-align: center;
        }
        .form p{
           display: inline-block;
        }
        .form input[type="submit"]{
            display: inline-block;
            width: 80px;
            height: 30px;
            border-radius: 10px;
        }
        .show {
            display: block;
        }
        .hide {
            display: none!important;
        }
        div{
            margin: 0 auto;
        }
    </style>
    <script src="js/jquery.js"></script>
    <script src="js/gt.js"></script>
</head>
<body>
<div class="form">
    <h1>登陆界面</h1><br>
    <form action="./web/VerifyLoginServlet.php" method="post" >
        <p>用名:</p><input name="name" type="text"><br><br>
        <p>密码:</p><input name="pass" type="text"><br><br>
        <p id="wait" class="show">正在加载验证码......</p>
        <p id="notice" class="hide">请先完成验证</p>
        <div id="nowlogin"></div>
        <br>
        <input type="submit" value="登陆" id="embed-submit">
    </form>
</div>
<script>
    var handlerEmbed = function (captchaObj) {
        $("#embed-submit").click(function (e) {
            var validate = captchaObj.getValidate();
            if (!validate) {//如果未完成验证的方法
                $("#notice")[0].className = "show";
                setTimeout(function () {
                    $("#notice")[0].className = "hide";
                }, 2000);
                e.preventDefault(); //阻止验证
            }
        });
        // 将验证码加到id为captcha的元素里，同时会有三个input的值：geetest_challenge, geetest_validate, geetest_seccode
        captchaObj.appendTo("#nowlogin");
        captchaObj.onReady(function () {
            $("#wait")[0].className = "hide";
        });
        // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
    };
    $.ajax({
        // 获取id，challenge，success（是否启用failback）
        url: "./web/StartCaptchaServlet.php?t=" + (new Date()).getTime(), // 加随机数防止缓存
        type: "get",
        dataType: "json",
        success: function (data) {
            console.log(data);
            // 使用initGeetest接口
            // 参数1：配置参数
            // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
            initGeetest({
                gt: data.gt,
                challenge: data.challenge,
                new_captcha: data.new_captcha,
                product: "embed", // 产品形式，包括：float，embed，popup。注意只对PC版验证码有效
                offline: !data.success // 表示用户后台检测极验服务器是否宕机，一般不需要关注
                // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
            }, handlerEmbed);
        }
    });
</script>
</body>
</html>


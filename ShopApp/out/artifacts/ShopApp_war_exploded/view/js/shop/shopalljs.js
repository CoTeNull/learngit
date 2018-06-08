
// var Data=null;
var indexLi=1;
function getShop(){$.get("/Shop/shop/getShop?pageId="+indexLi,function (data) {
    console.log(data);
    // Data=data;
    showShop(data);
});}
getShop();
$(".pagination li a").click(function () {
    indexLi=$(this).html();
    console.log(indexLi);
    $(".All_article_main").empty();
    getShop();
});
function showShop(data) {
    if(data.length==0){
        $(".All_article_main_message").addClass("messageShow");
        console.log("执行");
    }else{$(".All_article_main_message").removeClass("messageShow"); console.log("执行2");}
    for(var i=0;i<data.length && i<6;i++){
        $(".All_article_main").append("<div>" +
            "<img src="+data.data[i].commodityImg+">" +
            "<h2 data-num='"+data.data[i].commodityId+"'>"+data.data[i].commodityName+"</h2>" +
            "<span>"+data.data[i].commodityPrives+"</span>" +
            "<p>"+data.data[i].commodityMessage+"</p>" +
            "<p>"+data.data[i].commodityMasterId+"</p>" +
            "<a href='javascript:;'>加入购物车</a>" +
            "</div>");
    }
    //购物车功能！
    $(".All_article_main a").click(function () {
        console.log("666");
        var userName=getCookie("userName");
        if(userName!=""){
            if(confirm("看上了它嘛？点击确定加入购物车！")){
                var buyNameId=$(this).parent().find("h2").data("num");
                $.post('../app/buyShop',{shopId:buyNameId},function (data) {
                    if(data){
                        alert("已经放入购物车！");
                    }
                })
            }
            else{}
        }
        else{
            if(confirm("登陆以后才能购买哦！")){ window.location.href="/";}
            else{ window.location.href="/";}
        }
    });
}

$("#find").click(function () {
    if($("#findText").val()!=""){
        $.post("../app/findShop",{name:$("#findText").val()},function (data) {
            if(data){
                data=JSON.parse(data);
                console.log(data);
                $(".All_article_main").empty();
                for(var i=0;i<data.length;i++){
                    $(".All_article_main").append("<div>" +
                        "<img src="+data[i].shopimg+">" +
                        "<h2>"+data[i].shopname+"</h2>" +
                        "<span>"+data[i].shopprice+"</span>" +
                        "<p>"+data[i].shopmessage+"</p>" +
                        "<p>"+data[i].masterid+"</p>" +
                        "<a href='javascript:;'>加入购物车</a>" +
                        "</div>");
                }
                //购物车功能！
                $(".All_article_main a").click(function () {
                    var userName=getCookie("userName");
                    if(userName!=""){
                        if(confirm("看上了它嘛？点击确定加入购物车！")){
                            var buyNameId=$(this).parent().find("h2").data("num");
                            $.post('../app/buyShop',{shopId:buyNameId},function (data) {
                                if(data){
                                    alert("已经放入购物车！");
                                }
                            })
                        }
                        else{}
                    }
                    else{
                        if(confirm("登陆以后才能购买哦！")){ window.location.href="/index.html";}
                        else{ window.location.href="/index.html";}
                    }
                });
            }else{
                console.log("未找到");
            }

        })
    }
    else{
        $("#findText").css("border","1px solid #ff3854");
        $("#findText").attr("placeholder","请输入要搜索的内容")
    }
});
$("#findText").focus(function () {
    $("#findText").css("border","1px solid #00959E");
})
$("#findText").change(function () {
    if($("#findText").val()==""){
        $(".All_article_main").empty();
        showShop(0);
    }
})

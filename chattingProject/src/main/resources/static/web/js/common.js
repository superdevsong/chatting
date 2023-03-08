$(function(){
    $("body > div").fadeIn(500,function(){
        $(this).animate({
			"opacity":"1",
            "margin-top": "0"
        },1000);
    });
    
    $("a.sign").click(function(){
        var url = $(this).attr("href");
        $("body > div").animate({
            "opacity": "0",
            "margin-top": "-100px"
        },500,function(){
            document.location.href = url;
        });        
        return false;
    });	
});

window.addEventListener('DOMContentLoaded', function(){
    var pwImg = document.querySelectorAll('.txtPW_box img');
    pwImg.forEach(function(element,index){
        element.addEventListener('click',function(){
            var input = document.querySelectorAll('.txtPW_box input');
            if(input[0].type == 'password'){
                for(i=0;i<pwImg.length;i++){
                    pwImg[i].src = '/web/img/icon_eye_02.png'
                    input[i].type = 'text'
                }
            }else{
                for(i=0;i<pwImg.length;i++){
                    pwImg[i].src = '/web/img/icon_eye_01.png'
                    input[i].type = 'password'
                }
            }
        });
    });
});
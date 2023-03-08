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

    $(".txtPW_box img").click(function(){
		$(".txtPW_box").each(function(){
			var $this = $(this).children("img");
			var input = $(this).children("input");
			if(input.attr("type") == "password"){
				$this.attr("src","/web/img/icon_eye_02.png");
				input.attr("type","text");
			}else{
				$this.attr("src","/web/img/icon_eye_01.png");
				input.attr("type","password");
			}
			
		});
    });
});

window.addEventListener('DOMContentLoaded', function(){
    const imgs = document.querySelectorAll('.txtPW_box img');
    imgs.forEach(function(){
        this.addEventListener('click',function(){
            alert('hi');
        });
    });
    // items.forEach(item => {
    // item.addEventListener('click', e => {
    //     item.classList.toggle('active')
    // })
    // })
});
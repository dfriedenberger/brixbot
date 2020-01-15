

var chatRightSource   = document.getElementById("chat-right-template").innerHTML;
var chatRightTemplate = Handlebars.compile(chatRightSource);

var chatLeftSource   = document.getElementById("chat-left-template").innerHTML;
var chatLeftTemplate = Handlebars.compile(chatLeftSource);



function formatAMPM(date) {
    var hours = date.getHours();
    var minutes = date.getMinutes();
    minutes = minutes < 10 ? '0'+minutes : minutes;
    var strTime = hours + ':' + minutes+' Uhr';
    return strTime;
}            

//-- No use time. It is a javaScript effect.
function insertChat(who, text, time){
    var control = "";
    var date = formatAMPM(new Date(time));

    if (who == "bot"){
    	var context = {avatar: "images/bot.png", text: text , date : date };
    	control    = chatLeftTemplate(context);
    } else {
    	var context = {avatar: "images/avatar.png", text: text , date : date };
    	control    = chatRightTemplate(context);
    }
               
    $("ul").append(control);
    
}

function resetChat(){
    $("ul").empty();
}



function postToChat(type, text = "")
{
    
    console.log(text);
    $.ajax
    ({
        type: "POST",
        //the url where you want to sent the userName and password to
        url: '/api/v1/chat',
    	data: JSON.stringify({ "time": new Date().getTime(), "text" : text }),
    	contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data){
        	
        	console.log(JSON.stringify(data));

        	
        	insertChat("bot", data.text, parseInt(data.time));  
        	
        	
        },
        failure: function(errMsg) {
              alert(errMsg);
        }
    })
}

$(".mytext").on("keyup", function(e){
    if (e.which == 13){
        var text = $(this).val();
        if (text !== ""){
            insertChat("me", text, new Date().getTime());           
            postToChat("send",text);
            $(this).val('');
        }
    }
});

//-- Clear Chat
resetChat();




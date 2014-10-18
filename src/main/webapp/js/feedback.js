if (typeof jQuery === 'undefined') { throw new Error('jQuery required!') }

$( document ).ready(function() {
      loadFeedbackPage();
      $("#previewButton").click(function(){ previewFeedback();});
      $("#submitButton").click(function(){ submitFeedback();});
});

function previewFeedback(){
    if($("#preview").lenght){
       $("#preview").empty();
    }else{
        var converter = new Showdown.converter();
        $("#preview").html(converter.makeHtml($("#preview").text($("#feedbackText").val()).html()));
    }
}

function submitFeedback(){
    $(".loading").show();
    var postData = $("#feedbackForm").serializeArray();
    $.ajax({
        url: "data/feedback",
        type: "POST",
        dataType : "json",
        data : postData,
        success: function( json ) {
            $("<div class=\"alert alert-info fade in\" role=\"alert\" id=\"info\">" +
                    "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" ><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>" +
                    "Ваш отзыв был отправлен на модерацию, в скором времени он появится на сайте." +
                    "</div>").prependTo("#feedbackForm");
            $("#feedbackForm")[0].reset();
            $("#info").alert();
        },
        error: function( xhr, status, errorThrown ) {
            console.log( "json error!" );
            console.log( xhr );
            console.log( status );
            console.log( errorThrown );

            if(!$("#error").lenght){
                $("<div class=\"alert alert-danger fade in\" role=\"alert\" id=\"error\">" +
                    "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" ><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>" +
                    "Случилась ошибка. Попробуйте повторить запрос позднее." +
                    "</div>").prependTo("#feedbackForm");
            }
            $("#error").alert();
        },
        complete: function( xhr, status ) {
            console.log( "complete!" );
            $(".loading").hide();
        }
    });
}

function loadFeedbackPage(page){
    page = page || 1;
    console.log("page=" + page);
    $(".loading").show();
    $.ajax({
        url: "data/feedback",
        type: "GET",
        dataType : "json",
        data : {
             "page" : page,
        },

        success: function( json ) {
            console.log( "json ok!" );
            var pageCount = Math.ceil(json.count/FEEDBACK_PER_PAGE);
            var converter = new Showdown.converter();
            $("#content").empty();
            $("#content").hide();
            if(pageCount == 0){
                $("<p>").text("Никто пока не оставил отзывов, будьте первым!").appendTo("#content");
                return;
            }
            $("<ul class=\"pagination\"><li><a href=\"#\" data-row=\""+ (json.currentPage - 1) + "\">&laquo;</a></li></ul>").appendTo("#content");
            for(var i = 1; i<= pageCount; i++){
                var item = $("<li><a href=\"#\" data-row=\""+ i +"\">"+ i +"</a></li>");
                if(json.currentPage == i){
                    item.addClass("active");
                }
                $(".pagination").append(item);
            }
            $(".pagination").append("<li><a href=\"#\" data-row=\""+ (json.currentPage + 1) +"\">&raquo;</a></li>");
            if(json.currentPage == 1){
               $(".pagination li").first().addClass("disabled");
            }
            if(json.currentPage >= pageCount){
               $(".pagination li").last().addClass("disabled");
            }

            $(".pagination li a").click( function(event){
                  event.preventDefault();
                  if(!$(this).parent().hasClass("disabled")){
                    loadFeedbackPage($( this ).data("row"));
                  }
            });
            for(var i=0; i< json.feedbacks.length; i++){
                var panel = $("<div class=\"panel panel-default\">");
                $("<div class=\"panel-heading\">").text(json.feedbacks[i].author).appendTo(panel);
                var d = $("<div class=\"panel-body\">");
                d.html(converter.makeHtml(d.text(json.feedbacks[i].text).html())).appendTo(panel);
                panel.appendTo("#content");

            }
            $("#content").show();
        },

        error: function( xhr, status, errorThrown ) {
            console.log( "json error!" );
            console.log( xhr );
            console.log( status );
            console.log( errorThrown );

            if(!$("#error").lenght){
                $("<div class=\"alert alert-danger fade in\" role=\"alert\" id=\"error\">" +
                    "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" ><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>" +
                    "Случилась ошибка. Попробуйте повторить запрос позднее." +
                    "</div>").prependTo("#feedbackForm");
            }
            $("#error").alert();
        },
        complete: function( xhr, status ) {
            console.log( "complete!" );
            $(".loading").hide();
        }
    });
}
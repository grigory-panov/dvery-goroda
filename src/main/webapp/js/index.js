if (typeof jQuery === 'undefined') { throw new Error('jQuery required!') }

$( document ).ready(function() {
      loadPage(1);
});

function loadPage(pageNumber){
    console.log( "pageNumber = " + pageNumber);
    console.log( "ready!" );
    $(".loading").show();
    console.log( "alert show!" );
    $.ajax({
        url: "data/category",
        type: "GET",
        dataType : "json",
        data : {
            "id" : getParameterByName("category"),
            "page" : pageNumber
        },
        success: function( json ) {
            var recPerPage = 6;
            console.log( "json ok!" );
            $("h2").text(json.category);
            $(".lead").text(json.description);
            $("#products").empty();
            var pageCount = json.count/recPerPage;
            pageCount = Math.ceil(pageCount);
            if(pageCount == 0){
                $("<p>").text("В данной категории пока нет товаров").appendTo("#products");
                return;
            }
            $("<ul class=\"pagination\"><li><a href=\"#\" data-row=\""+ (json.currentPage - 1) + "\">&laquo;</a></li></ul>").appendTo("#products");


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

            $(".pagination li a").click( function(){
                  if(!$(this).parent().hasClass("disabled")){
                    loadPage($( this ).data("row"));
                  }
            });

            var recordPerRow = 3;
            for(var i = 0; i < json.product.length; i++){
                if((i % recordPerRow) === 0){
                    $("<div class=\"row\"/>").appendTo("#products");
                }
                var wrapper = $("<div class=\"col-sm-6 col-md-4\"/>");
                var thumbnail = $("<div class=\"thumbnail\"/>");
                var image = $("<a href=\"product.html?id="+ json.product[i].id +"\"><img src=\"image/product/"+ json.product[i].id +"\"/></a>");

                image.appendTo(thumbnail);
                var caption = $("<div class=\"caption\"/>");
                caption.appendTo(thumbnail);
                $("<h3/>").text(json.product[i].name).appendTo(caption);
                $("<p>").text(json.product[i].description).appendTo(caption);
                $("<p>").text("Цена: " + json.product[i].price +" руб.").appendTo(caption);
                $("<p><a href=\"product.html?id="+ json.product[i].id + " \" class=\"btn btn-primary\" role=\"button\">Подробнее</a></p>").appendTo(caption);
                thumbnail.appendTo(wrapper);
                $(".row").last().append(wrapper);
            }
        },

        error: function( xhr, status, errorThrown ) {
            console.log( "json error!" );
            console.log( xhr );
            console.log( status );
            console.log( errorThrown );

            if(!$("#error").lenght){
                $("<div class=\"alert alert-danger fade in\" role=\"alert\" id=\"error\">" +
                    "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" ><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>" +
                    "Случилось ошибка. Попробуйте повторить запрос позднее." +
                    "</div>").prependTo("#container");
            }
            $("#error").alert();
        },
        complete: function( xhr, status ) {
            console.log( "complete!" );
            $(".loading").hide();
        }
    });
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results == null ? "1" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
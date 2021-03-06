if (typeof jQuery === 'undefined') { throw new Error('jQuery required!') }

$( document ).ready(function() {
      var productId = getParameterByName("id");
      loadProduct(productId);
      loadRelated(productId);
});


function loadProduct(productId){
    console.log("productId=" + productId);
    $(".loading").show();
    console.log( "alert show!" );
    $.ajax({
        url: "data/product",
        type: "GET",
        dataType : "json",
        data : {
             "id" : productId,
        },

        success: function( json ) {
            console.log( "json ok!" );
            $("h2").text(json.name);
            $(".lead").text(json.description);
            $(".carousel-inner").empty();
            $(".carousel-indicators").empty();
            for(var i = 0; i< json.versions.length; i++){
                var item = $("<div class=\"item\"/>");
                var indicator = $("<li data-target=\"#carousel-example-generic\" data-slide-to=\"" +i+ "\"></li>");
                if(i === 0){
                    item.addClass("active");
                    indicator.addClass("active");
                }
                var image = $("<img src=\"image/version/"+ productId + "/" + json.versions[i].id +"\" class=\"center-block img600\" alt=\"...\">");
                image.appendTo(item);
                var caption = $("<div class=\"carousel-caption\"/>");
                caption.appendTo(item);
                $("<p>").text(json.versions[i].description).appendTo(caption);
                $("<p>").text(json.versions[i].size + ", цена: " + json.versions[i].price).appendTo(caption);
                $(".carousel-inner").append(item);
                $(".carousel-indicators").append(indicator);
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
                    "Случилась ошибка. Попробуйте повторить запрос позднее." +
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

function loadRelated(productId){
    $.ajax({
        url: "data/related",
        type: "GET",
        dataType : "json",
        data : {
             "id" : productId,
        },

        success: function( json ) {
            console.log( "related json ok!" );
            $("#related").empty();
            if(json.product.length > 0){
                $("<h3/>").text("Дополнительные товары").appendTo("#related");
                var row = $("<div class='row'/>")
                row.appendTo("#related");

                for(var i = 0; i< json.product.length; i++){
                    var wrapper = $("<div class=\"col-sm-6 col-md-4\"/>");
                    var thumbnail = $("<div class=\"thumbnail\"/>");
                    var image = $("<a href=\"product.html?id="+ json.product[i].id +"\"><img src=\"image/product/"+ json.product[i].id +"\"/></a>");
                    image.appendTo(thumbnail);
                    var caption = $("<div class=\"caption\"/>");
                    caption.appendTo(thumbnail);
                    $("<h4/>").text(json.product[i].name).appendTo(caption);
                    $("<p>").text(json.product[i].description).appendTo(caption);
                    thumbnail.appendTo(wrapper);
                    wrapper.appendTo(row);
                }
                row.appendTo("#related");
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
                    "Случилась ошибка. Попробуйте повторить запрос позднее." +
                    "</div>").prependTo("#container");
            }
            $("#error").alert();
        },
        complete: function( xhr, status ) {
            console.log( "complete!" );
        }
    });
}


function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
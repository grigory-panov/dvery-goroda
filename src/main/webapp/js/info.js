if (typeof jQuery === 'undefined') { throw new Error('jQuery required!') }

$( document ).ready(function() {
      loadInfo();
});

function loadInfo(infoId){
    infoId = infoId || 0;
    console.log("infoId=" + infoId);
    $(".loading").show();
    $.ajax({
        url: "data/info",
        type: "GET",
        dataType : "json",
        data : {
             "id" : infoId,
        },

        success: function( json ) {
            console.log( "json ok!" );
            var converter = new Showdown.converter();
            $("#content").empty();
            $("<p class=\"lead\">").text(json.header).appendTo("#content");
            $("<div/>").html(converter.makeHtml(json.body)).appendTo("#content");
//                    $("<div/>").text(json.body).appendTo("#content");
            $(".previous").unbind("click");
            if(json.prevId){
                $(".previous").removeClass("disabled");
                $(".previous").click( function() {loadInfo(json.prevId);} );
            }else{
                $(".previous").addClass("disabled");
            }

            $(".next").unbind("click");
            if(json.nextId){
                $(".next").removeClass("disabled");
                $(".next").click( function() {loadInfo(json.nextId);} );
            }else{
                $(".next").addClass("disabled");
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
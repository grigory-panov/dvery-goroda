if (typeof jQuery === 'undefined') { throw new Error('jQuery required!') }

$( document ).ready(function() {
      loadPartners();
});


function loadPartners(){
    $(".loading").show();
    $.ajax({
        url: "data/partners",
        type: "GET",
        dataType : "json",
        success: function( json ) {
            console.log( "json ok!" );
            for(var i = 0; i< json.partners.length; i++){
                var item = $("<div class=\"item\"/>");
                var link = $("<a href=\""+ json.partners[i].url + "\"/>");
                var image = $("<img src=\"image/partner/"+ json.partners[i].id +"\" class=\"img200\">");
                image.attr("alt", json.partners[i].name);
                image.appendTo(link);
                link.appendTo(item);
                item.appendTo("#partners");
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


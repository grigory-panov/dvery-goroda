$( document ).ready(function() {
     $("#category").on("change", function(){
         $("#product").prop('disabled', true);
         $("#product").empty();
         $("#product").append("<option>Loading...</option>")
         var catId = $(this).val();

         $.ajax({
                 url: "../data/categoryFull",
                 data: {"id": catId},
                 type: "GET",
                 dataType : "json",
                 success: function( json ) {
                     $("#product").empty();
                     $.each(json.product, function(index, value) {
                         $('#product').append($("<option/>", {
                             value: value.id,
                             text: value.name
                         }));
                     });
                 },

                 complete: function( xhr, status ) {
                     console.log( "complete!" );
                     $("#product").prop('disabled', false);
                 },

                 error: function() {
                    $("#product").empty();
                    alert('error!');
                 }
         });

     });
});
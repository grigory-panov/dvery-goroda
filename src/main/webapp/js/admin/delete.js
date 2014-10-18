$( document ).ready(function() {
     $(".delete, .restore").click(function(event){
         event.preventDefault();
         var delURL = $(this).prop("href");
         var tr = $(this).closest('tr');
         $.ajax({
                 url: delURL,
                 type: "DELETE",
                 dataType : "json",
                 success: function( json ) {
                    if(json.error === 'OK'){
                        tr.empty();
                    }else{
                        alert(json.error);
                    }
                 },
                 error: function() {
                    alert('error!');
                 }
         });

     });
     $(".approve").click(function(event){
              event.preventDefault();
              var delURL = $(this).prop("href");
              var tr = $(this).closest('tr');
              var td = $(this).parent();
              $.ajax({
                      url: delURL,
                      type: "POST",
                      dataType : "json",
                      success: function( json ) {
                         if(json.error === 'OK'){
                             tr.addClass("success");
                             td.empty();
                         }else{
                             alert(json.error);
                         }
                      },
                      error: function() {
                         alert('error!');
                      }
              });

     });

});
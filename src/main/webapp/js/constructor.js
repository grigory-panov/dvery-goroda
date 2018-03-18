if (typeof jQuery === 'undefined') { throw new Error('jQuery required!') }

$( document ).ready(function() {
      $(".img-select").chosen(
          {
            width:"200px",
            html_template: '<img class="chose-image" src="{url}"> {txt}'
          }
      );
      if (SVG.supported) {
          $("select").on("change", function() {
             drawDoor($("#size").val(), getSelectedColor(), $("#model").val())
          });
          $(".color_box").click(function() {
            $('.selected_color').removeClass('selected_color')
            $(this).addClass('selected_color');
            drawDoor($("#size").val(), getSelectedColor(), $("#model").val());
          });
          $('#wallColor').colorpicker();
          $('#wallColor').colorpicker().on('changeColor.colorpicker', function(event){
            $('#room').css("background-color", event.color);
          });
          drawDoor($("#size").val(), getSelectedColor(), $("#model").val());
      }else{
        $("#content").empty();
        $("#content").text("SVG supporting browser required!");
      }
});

function getSelectedColor(color){
    return $('.selected_color').css('background-color');
}


function drawDoor(size, actualColor, model){
    $("#content").empty();
    var draw = SVG('content').size(310, 666);
   drawLeftPart(size, actualColor, model, draw);
   drawRightPart(size, actualColor, model, draw);
   drawMiddlePart(size, actualColor, model, draw);
   drawFurniture(size, actualColor, model, draw);
   draw.spof(); // sub-pixel offset
}

function drawFurniture(size, color, model, draw){
    var bokW = 33;
    var bokH = 666;
    draw.rect(3, 30).fill({ color: 'gray' }).x(size).y(bokH -100);
    draw.rect(3, 30).fill({ color: 'gray' }).x(size).y(100);
    draw.circle(20).fill({ color: 'gray' }).center(bokW/2, bokH/2 + bokW);
    draw.circle(10).fill({ color: 'gray' }).center(bokW/2, bokH/2 + bokW).stroke('black');
    draw.rect(40, 7).fill({ color: 'gray' }).x(bokW/2).y(bokH/2 + bokW -3.5).radius(2).attr('id', 'furniture');
}


function drawLeftPart(size, color, model, draw){
    var opacity = 0.8;
    var bokW = 33;
    var faska = 3;
    var bokH = 666;
    var rect = draw.rect(bokW, bokH).fill('img/texture-vertical.png');
    rect.clone().fill({ color: color, opacity: opacity });
    rect = draw.rect(faska, bokH - bokW * 2).fill('img/texture-vertical.png')
         .x(bokW).y(bokW);
    svgColor = new SVG.Color(color).morph('#fff').at(0.2);
    rect.clone().fill({ color: svgColor, opacity: opacity});//.stroke({ color: svgColor, opacity: opacity, width: 1 });
}

function drawRightPart(size, color, model, draw){
    var opacity = 0.8;
    var bokW = 33;
    var bokH = 666;
    var faska = 3;
    var rect = draw.rect(bokW, bokH).fill('img/texture-vertical.png').attr('x', size - bokW);
    rect.clone().fill({ color: color, opacity: opacity });
    svgColor = new SVG.Color(color).morph('#000').at(0.2);
    rect = draw.rect(faska, bokH - bokW*2).fill('img/texture-vertical.png')
        .x(size - bokW - faska).y(bokW);
    rect.clone().fill({ opacity: opacity, color: svgColor });

}

function drawMiddlePart(size, color, model, draw){
    var opacity = 0.8;
    var bokW = 33;
    var bokH = 666;
    var faska = 3;
    var innerSize = size - 2 * bokW;
    var colorTop = new SVG.Color(color).morph('#fff').at(0.2);
    var colorBottom = new SVG.Color(color).morph('#000').at(0.2);

    if(model === '112'){
        var little = 10;
        var rect = draw.rect( innerSize, bokW ).fill('img/texture-horizontal.png').x(bokW);
        rect.clone().fill({ color: color, opacity: opacity });

        rect = draw.rect(innerSize, bokW).fill('img/texture-horizontal.png').x(bokW).y(bokH-bokW);
        rect.clone().fill({ color: color, opacity: opacity });

        draw.rect(innerSize, little * 2).fill('img/texture-horizontal.png').x(bokW).y(bokH/1.5 - 0.5 *little);
        draw.rect(innerSize, little * 2).fill('img/texture-horizontal.png').x(bokW).y(bokH/1.5 - 0.5 *little - bokW - 2 *little);


        draw.rect(innerSize, little).fill({ color: color, opacity: opacity }).x(bokW).y(bokH/1.5);
        draw.rect(innerSize, little).fill({ color: color, opacity: opacity }).x(bokW).y(bokH/1.5 - bokW - 2 * little);

        draw.rect(innerSize, little/2).fill({ color: colorTop, opacity: opacity }).x(bokW).y(bokH/1.5 - 0.5 *little);
        draw.rect(innerSize, little/2).fill({ color: colorBottom, opacity: opacity}).x(bokW).y(bokH/1.5 + little);

        draw.rect(innerSize, little/2).fill({ color: colorTop, opacity: opacity }).x(bokW).y(bokH/1.5 - 0.5 *little - bokW - 2 *little);
        draw.rect(innerSize, little/2).fill({ color: colorBottom, opacity: opacity}).x(bokW).y(bokH/1.5 + little - bokW - 2* little);

        //glasses
        draw.rect(innerSize - 2 *faska, bokH/1.5 - 2*bokW -2.5*little)
            .fill({ color: 'white', opacity: opacity -0.2 }).x(bokW + faska).y(bokW)
            .style({cursor : 'pointer'})
            .click(replaceRect);
        draw.rect(innerSize - 2 *faska, bokW).fill({ color: 'white', opacity: opacity -0.2 })
            .x(bokW + faska).y(bokH/1.5-bokW -0.5 * little)
            .style({cursor : 'pointer'})
            .click(replaceRect);

        draw.rect(innerSize - 2 *faska, bokH - bokH/1.5 - bokW - 1.5* little)
            .fill({ color: 'white', opacity: opacity -0.2 })
            .x(bokW + faska).y(bokH - bokH/3 +1.5*little)
            .style({cursor : 'pointer'})
            .click(replaceRect);

    } else {
        var curve = 15;
        var extraTop = 12;
        var rect = draw.rect(innerSize, bokW).fill('img/texture-horizontal.png').x(bokW).y(bokH-bokW);
        rect.clone().fill({ color: color, opacity: opacity });
        var pathArray = new SVG.PathArray([
                              ['M', bokW, 0]
                            , ['h', innerSize]
                            , ['v', bokW + extraTop]
                            , ['h', -bokW]
                            , ['C', size/2+20, bokW + extraTop - curve, size/2-20, bokW + extraTop - curve, 2*bokW, bokW + extraTop ]
                            , ['L', 2*bokW, bokW + extraTop]
                            , ['h', -bokW]
                            , ['Z']
                            ]);
        var path = draw.path(pathArray);
        path.fill('img/texture-horizontal.png');
        path.clone().fill({ color: color, opacity: opacity });

        var offset = 3*bokH/4;
        var little = 21;
        pathArray = new SVG.PathArray([
                      ['M', bokW, offset]
                    , ['h', bokW]
                    , ['C', size/2-20, offset - curve, (size/2+20), offset - curve, size-2*bokW, offset ]
                    , ['h', bokW]
                    , ['v', little]
                    , ['h', -bokW]
                    , ['C', size/2+20, offset + little - curve, (size/2-20), offset + little - curve, 2*bokW, offset +little ]
                    , ['L', 2*bokW, offset+little]
                    , ['h', -bokW]
                    , ['Z']
                    ]);
        var pathArrayTop = new SVG.PathArray([
                      ['M', bokW, offset]
                    , ['h', bokW]
                    , ['C', size/2-20, offset - curve, (size/2+20), offset - curve, size-2*bokW, offset ]
                    , ['h', bokW]
                    , ['v', faska]
                    , ['h', -bokW]
                    , ['C', size/2+20, offset + faska - curve, (size/2-20), offset + faska - curve, 2*bokW, offset +faska ]
                    , ['L', 2*bokW, offset+faska]
                    , ['h', -bokW]
                    , ['Z']
                    ]);


        path = draw.path(pathArray);
        path.fill('img/texture-horizontal.png');
        path.clone().fill({ color: color, opacity: opacity });

        path = draw.path(pathArrayTop).fill({ color: 'white', opacity: 0.2 });
        path.clone().dy(little-faska).fill({ color: 'black', opacity: 0.2 });

        var littleV = 12;
        rect = draw.rect(littleV, offset-bokW-extraTop+ faska).fill('img/texture-vertical.png').x(size - 2*bokW).y(bokW+extraTop);
        rect.clone().fill({ color: color, opacity: opacity });
        draw.rect(3, offset-bokW-extraTop).fill({ color: 'black', opacity: 0.2 }).x(rect.x()).y(rect.y());
        draw.rect(3, offset-bokW-extraTop).fill({ color: 'white', opacity: 0.2 }).x(rect.x()+littleV-3).y(rect.y());

        rect = rect.clone().x(2*bokW-littleV);
        rect.clone().fill({ color: color, opacity: opacity });
        draw.rect(3, offset-bokW-extraTop).fill({ color: 'black', opacity: 0.2 }).x(rect.x()).y(rect.y());
        draw.rect(3, offset-bokW-extraTop).fill({ color: 'white', opacity: 0.2 }).x(rect.x()+littleV-3).y(rect.y());
        //glasses
        rect = draw.rect(bokW-littleV-faska, offset-bokW-extraTop).fill({ color: 'white', opacity: opacity -0.2 })
            .x(bokW + faska).y(bokW+extraTop)
            .style({cursor : 'pointer'})
            .click(replaceRectPlain);

        rect.clone().x(innerSize +littleV)
            .style({cursor : 'pointer'})
            .click(replaceRectPlain);;

        var bigGlass = offset-bokW-extraTop;
        var offset = bokW + extraTop;
        pathArray = new SVG.PathArray([
                              ['M', 2*bokW, offset]
                            , ['C', size/2-20, offset - curve, (size/2+20), offset - curve, size-2*bokW, offset ]
                            , ['v', bigGlass]
                            , ['C', size/2+20, offset + bigGlass - curve, (size/2-20), offset + bigGlass - curve, 2*bokW, offset + bigGlass ]
                            , ['L', 2*bokW, offset+bigGlass]
                            , ['Z']
                            ]);
        path = draw.path(pathArray);
        path.fill({ color: 'white', opacity: opacity -0.2 });
        path.style({cursor : 'pointer'})
            .click(replaceGammaBigGlass);

        var bottomGlass = bokH - bigGlass-2*bokW-little -extraTop;
        offset = bokH - bottomGlass - bokW;

        pathArray = new SVG.PathArray([
                              ['M', bokW + faska, offset]
                            , ['h', bokW]
                            , ['C', size/2-20, offset - curve, (size/2+20), offset - curve, size-2*bokW, offset ]
                            , ['h', bokW-faska]
                            , ['v', bottomGlass]
                            , ['h', -(innerSize-2*faska)]
                            , ['Z']
                            ]);
        path = draw.path(pathArray);
        path.fill({ color: 'white', opacity: opacity -0.2 });
        path.style({cursor : 'pointer'})
            .click(replaceGammaBottomGlass);

    }

    function replaceRect(){
        var opacity = 0.8;
        if(this.parent() === this.doc()){
            // we are OK
        }else{
            this.parent().remove();
            return;
        }
        if(this.attr('fill') == 'white'){
            var filenka = draw.nested();
            var h = this.height();
            var w = this.width();
            var r = filenka.rect(this.width(), this.height()).move(this.x(),  this.y())
                .fill('img/texture.png');
            r.clone().fill({color:  getSelectedColor(), opacity: opacity} )
            .stroke({ color: getSelectedColor(), opacity: opacity, width: 1 })
            .style({cursor : 'pointer'})
            .click(replaceRect);

            var f = 9;
            var h = this.height()-f*2;
            var w = this.width() -f*2;
            var pathArray = new SVG.PathArray([
                            ['M', 0, 0]
                          , ['h', w]
                          , ['v', h]
                          ]);
            var path = filenka.path(pathArray);
            path.move(this.x()+f, this.y()+f);
            path.fill('none');
            path.stroke({ color: colorTop, opacity: opacity, width: 3 });

            pathArray = new SVG.PathArray([
                            ['M', 0, 0]
                          , ['v', h]
                          , ['h', w]
                          ]);
            path = filenka.path(pathArray);
            path.move(this.x()+f, this.y()+f);
            path.fill('none');
            path.stroke({ color: colorBottom, opacity: opacity, width: 3 });

            SVG.get('furniture').front();

        }else{
            this.fill({ color: 'white', opacity: opacity -0.2 });
        }
    }

    function replaceRectPlain(){
        var opacity = 0.8;
        if(this.parent() === this.doc()){
            // we are OK
        }else{
            this.parent().remove();
            return;
        }
        if(this.attr('fill') == 'white'){
            var filenka = draw.nested();
            var r = filenka.rect(this.width(), this.height()).move(this.x(),  this.y())
                .fill('img/texture.png');
            r.clone().fill({color:  getSelectedColor(), opacity: opacity} )
                .style({cursor : 'pointer'})
                .click(replaceRectPlain);
            SVG.get('furniture').front();
        }else{
            this.fill({ color: 'white', opacity: opacity -0.2 });
        }
    }

    function replaceGammaBigGlass(){
         var opacity = 0.8;
         var curve = 15;
         var size = parseInt($("#size").val(),10);
         var bokW = 33;
         if(this.parent() === this.doc()){
             // we are OK
         }else{
             this.parent().remove();
             return;
         }
         if(this.attr('fill') == 'white'){
              var filenka = draw.nested();
              var h = this.height()-10;
              var w = this.width();
              var pathArray = new SVG.PathArray([
                ['M', 0, 0]
              , ['C', w/2-20, -curve, (w/2+20), -curve, w, 0 ]
              , ['v', h]
              , ['C', w/2+20, h - curve, (w/2-20),  h - curve, 0, h ]
              , ['L', 0, h]
              , ['Z']
              ]);
              var path = filenka.path(pathArray);
              path.fill('img/texture.png');
              path.move(2*bokW, bokW);
              path.clone().fill({ color: getSelectedColor(), opacity: opacity})
                .style({cursor : 'pointer'})
                .click(replaceGammaBigGlass);

              var faska = 9;
              var h = this.height()-10 - 2*faska -3;
              var w = this.width() - 2* faska;
              var colorTop = new SVG.Color(color).morph('#fff').at(0.2);
              var colorBottom = new SVG.Color(color).morph('#000').at(0.2);

              pathArray = new SVG.PathArray([
                      ['M', 0, 0]
                    , ['C', w/2-20, -curve, (w/2+20), -curve, w, 0 ]
                    , ['v', h]
                    ]);
              path = filenka.path(pathArray);
              path.move(2*bokW+faska, bokW+faska);
              path.fill('none');
              path.stroke({ color: colorTop, opacity: opacity, width: 3 });

              pathArray = new SVG.PathArray([
                    ['M', 0, 0]
                  , ['v', h]
                  , ['C', w/2-20, h-curve, (w/2+20), h-curve, w, h ]
                  ]);
              path = filenka.path(pathArray);
              path.move(2*bokW+faska, bokW+2*faska);
              path.fill('none');
              path.stroke({ color: colorBottom, opacity: opacity, width: 3 });

              //path
         }else{
             this.fill({ color: 'white', opacity: opacity -0.2 });
         }
    }
    function replaceGammaBottomGlass(){
         var opacity = 0.8;
         var curve = 15;
         var size = parseInt($("#size").val(),10);
         var bokW = 33;
         if(this.parent() === this.doc()){
             // we are OK
         }else{
             this.parent().remove();
             return;
         }
         if(this.attr('fill') == 'white'){
              var filenka = draw.nested();
              var h = this.height()-10;
              var w = this.width();
              var pathArray = new SVG.PathArray([
                ['M', 0, 0]
              , ['h', bokW-3]
              , ['C', w/2-20, -curve, (w/2+20), -curve, w-bokW+3, 0 ]
              , ['h', bokW-3]
              , ['v', h]
              , ['h', -w]
              , ['Z']
              ]);
              var path = filenka.path(pathArray);
              path.fill('img/texture.png');
              path.move(this.x(), this.y());
              path.clone().fill({ color: getSelectedColor(), opacity: opacity})
                .style({cursor : 'pointer'})
                .click(replaceGammaBottomGlass);

              var faska = 9;
              var h = this.height()-10 - 2*faska -3;
              var w = this.width() - 2* faska;
              var colorTop = new SVG.Color(color).morph('#fff').at(0.2);
              var colorBottom = new SVG.Color(color).morph('#000').at(0.2);

              var pathArray = new SVG.PathArray([
                ['M', 0, 0]
              , ['h', bokW-3]
              , ['C', w/2-20, -curve, (w/2+20), -curve, w-bokW+3, 0 ]
              , ['h', bokW-3]
              , ['v', h]
              ]);
              path = filenka.path(pathArray);
              path.move(this.x()+faska, this.y()+faska);
              path.fill('none');
              path.stroke({ color: colorTop, opacity: opacity, width: 3 });

              pathArray = new SVG.PathArray([
                    ['M', 0, 0]
                  , ['v', h]
                  , ['h', w]
                  ]);
              path = filenka.path(pathArray);
              path.move(this.x()+faska, this.y()+2*faska);
              path.fill('none');
              path.stroke({ color: colorBottom, opacity: opacity, width: 3 });

              //path
         }else{
             this.fill({ color: 'white', opacity: opacity -0.2 });
         }
    }

}
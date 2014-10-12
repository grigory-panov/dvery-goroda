ymaps.ready(init);

// Инициализация и уничтожение карты при нажатии на кнопку.
function init () {
    var myMap;

    $('#toggle').bind({
        click: function () {
            if (!myMap) {
                myMap = new ymaps.Map('map', {
                    center: [56.61836962, 47.83551537], // Йошкар-Ола
                    zoom: 13
                });
                myPlacemark = new ymaps.Placemark([ 56.619331, 47.836851], {
                    // Чтобы балун и хинт открывались на метке, необходимо задать ей определенные свойства.
                    hintContent: "Мы находимся здесь"
                });
                myMap.geoObjects.add(myPlacemark);
                $("#toggle").attr('value', 'Скрыть карту');
            }
            else {
                myMap.destroy();// Деструктор карты
                myMap = null;
                $("#toggle").attr('value', 'Показать карту снова');
            }
        }
    });
}
$(document).ready(function () {
    var search = location.search;
    var tab = ['send'];
    var a = 0;
    for (var i = 0; i < tab.length; i++) {
        if (search.includes(tab[i])) {
            $('#'.concat(tab[i])).addClass('active');
            a++;
        }
    }
    if (a == 0) {
        $('#list').addClass('active');
    }
});
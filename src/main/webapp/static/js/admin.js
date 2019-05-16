$(document).ready(function() {
    var search = location.search;
    var tab = ['add-asso', 'admins-visep'];
    var a = 0;
    for (var i = 0; i < tab.length; i++) {
        if (search.includes(tab[i])) {
            $('#'.concat(tab[i])).addClass('active');
            a++;
        }
    }
    if (a == 0) {
        $('#assos').addClass('active');
    }
});
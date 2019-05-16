particlesJS.load('particles-js', 'static/particles.json', function () {
    console.log('callback - particles.js config loaded');
});

$(document).ready(function () {
    var path = location.pathname;
    var tab = ['home', 'association', 'messenger', 'admin'];
    for (var i = 0; i < tab.length; i++) {
        if (path.includes(tab[i])) {
            $('#'.concat(tab[i])).addClass('active');
        }
    }
    var search = location.search;
    var tab2 = ['add-asso', 'admins-visep'];
    var a = 0;
    for (var i = 0; i < tab2.length; i++) {
        if (search.includes(tab2[i])) {
            $('#'.concat(tab2[i])).addClass('active');
            a++;
        }
    }
    if (a == 0) {
        $('#assos').addClass('active');
    }
});
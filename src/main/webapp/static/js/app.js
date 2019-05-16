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
});
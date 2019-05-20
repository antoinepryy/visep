particlesJS.load('particles-js', 'static/particles.json', function() {});
var path = window.location.pathname;
console.log(path);


$(document).ready(function () {
    var path = location.pathname;
    var tab = ['home', 'association', 'messenger', 'admin'];
    for (var i = 0; i < tab.length; i++) {
        if (path.includes(tab[i])) {
            $('#'.concat(tab[i])).addClass('active');
        }
    }
});

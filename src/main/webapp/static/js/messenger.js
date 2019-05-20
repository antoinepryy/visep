$(document).ready(function() {
    var search = location.search;
    var tab = ['send', 'list'];
    for (var i = 0; i < tab.length; i++) {
        if (search.includes(tab[i])) {
            $('#'.concat(tab[i])).addClass('active');
        }
    }
});
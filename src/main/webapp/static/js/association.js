$(document).ready(function() {
    var search = location.search;
    if (search.includes('members')) {
        $('#members').addClass('active');
    }
    else {
        $('#description').addClass('active');
    }
});
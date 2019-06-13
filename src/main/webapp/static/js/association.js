$(document).ready(function() {
    var search = location.search;
    var tab = ['events', 'members'];
    var a = 0;
    for (var i = 0; i < tab.length; i++) {
        if (search.includes(tab[i])) {
            $('#'.concat(tab[i])).addClass('active');
            a++;
        }
    }
    if (a == 0) {
        $('#description').addClass('active');
    }

    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd
    }
    if(mm<10){
        mm='0'+mm
    }
    today = yyyy+'-'+mm+'-'+dd;
    $('#date').prop("min", today);
    $.post('association?action=follower', {associationId: $(':checkbox').attr('id')}, function(isFollower) {
        if (isFollower) {
            $(':checkbox').prop('checked', true);
        }
    });
});

$(':checkbox').change(function () {
    if ($(this).is(':checked')) {
        $.post('association?action=add-follower', {associationId: $(this).attr('id')});
    }
    else {
        $.post('association?action=del-follower', {associationId: $(this).attr('id')});
    }
});
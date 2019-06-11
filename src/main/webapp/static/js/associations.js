$(document).ready(function () {
   $.post('association?action=followers', function(followers) {
       followers.forEach(function(follower) {
           $('#' + follower.associationId).prop('checked', true);
       });
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
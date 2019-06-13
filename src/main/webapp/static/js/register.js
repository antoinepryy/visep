$('form').submit(function (event) {
    $.post('register?action=checkCode', {code: $('#code').val()}, function (isAvailable) {
        if (!isAvailable) {
            event.preventDefault();
            $('#code').css('border-color', 'red');
        }
    });
    if ($('#password').val() !== $('#confPassword').val()) {
        event.preventDefault();
        $('#password').css('border-color', 'red');
        $('#confPassword').css('border-color', 'red');
    }
});
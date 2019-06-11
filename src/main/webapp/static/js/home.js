$(document).ready(function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: ['dayGrid', 'bootstrap'],
        locale: 'fr',
        themeSystem: 'bootstrap',
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,dayGridWeek'
        }
    });
    $.post('home', function(events) {
        events[0].forEach(function(event) {
            calendar.addEvent({title: event.association.name + ' - ' + event.description, start: event.dateEvent});
        });
        events[1].forEach(function(event) {
            calendar.addEvent({title: event.association.name + ' - ' + event.description, start: event.dateEvent, backgroundColor: 'gainsboro', borderColor: 'gainsboro'})
        });
    });
    calendar.render();
    $('.fc-today-button').html("aujourd'hui");
    $('.fc-dayGridMonth-button').html("mois");
    $('.fc-dayGridWeek-button').html("semaine");
});
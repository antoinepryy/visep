$(document).ready(function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: [ 'dayGrid' ]
    });
    $.post('home', function(events) {
        console.log(events);
        events.forEach(function(event) {
            calendar.addEvent({title: event.association.name + ' - ' + event.description, start: event.dateEvent});
        });
    });
    calendar.render();
});
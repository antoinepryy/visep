document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: [ 'dayGrid' ]
    });
    $.post('home', function(events) {
        events.forEach(function(event) {
            calendar.addEvent({title: event.description , start: event.dateEvent});
        });
    }, "json");
    calendar.render();
});
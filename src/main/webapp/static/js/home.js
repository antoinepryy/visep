document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: [ 'dayGrid' ],
        events: [
            {
                title: 'Barbecue',
                start: '2019-05-28',
                end: '2019-05-28'
            }
        ]
    });
    calendar.render();
});
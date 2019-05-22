$(function () {

    getNoise();
    function getNoise() {
        var url = window.location.protocol + '//' + window.location.host + window.location.pathname;
        url = url.replace('/web/dashboard.html', '/api/sensors/noise');
        $.ajax({
            url: url,
            method: 'GET'
        }).done(function (noise) {
            $('#noise').val(noise.value).trigger('change');
            getState();
        });
    }

    function getState() {
        var wealthVal = Math.random() * (100 - 99.5) + 99.5;
        $('#wealth').val(wealthVal).trigger('change');
        var healthVal = Math.random() * (100 - 99.5) + 99.5;
        $('#health').val(healthVal).trigger('change');
    }

    setInterval(function () { getNoise(); }, 5000);

});
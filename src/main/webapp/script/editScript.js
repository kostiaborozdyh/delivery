
function initAutocomplete() {
    new google.maps.places.Autocomplete(
        (document.getElementById('idCityFrom1')),
        {types: ['geocode']}
    );
    new google.maps.places.Autocomplete(
    (document.getElementById('idCityTo1')),
{types: ['geocode']}
    );
}
var stompClient = null;

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        $("#mainTable tbody").html("");
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/chip', function (chip) {
            parseChip = JSON.parse(chip.body);
            parseChip.forEach(function(item) { showGreeting(item.name, item.address, item.cost) });
        });
    });
}

function sendSearchingText() {
    stompClient.send("/app/search", {}, JSON.stringify({'searchItem': $("#search").val()}));
}

function showGreeting(name, address, cost) {
    $("#mainTable tbody").append("<tr><td>" + name + "</td><td>" + address + "</td><td>" + cost + "</td></tr>");
}

$(function () {
    connect();

    $("form").on('submit', function (e) {
        e.preventDefault();
        sendSearchingText();
    });
});
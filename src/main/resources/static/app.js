var stompClient = null, lastId = 0;

function windowScroll() {
    if($(window).scrollTop() + $(window).height() >= $(document).height()){
        sendNeedNextChips();
    }
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        $("#mainTable tbody").html("");
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/search', writeFirstChips);
        stompClient.subscribe('/topic/nextChips', nextChips);
        stompClient.subscribe('/topic/information', changeChipInformation);
    });
}

function changeChipInformation(chipInformation) {
    chip = JSON.parse(chipInformation.body);

    addInformationPopover(chip.imgSrc, chip.information, chip.idElement);
}

function nextChips(chips) {
    JSON.parse(chips.body).forEach(function(item) { addChip(item.name, item.address, item.cost) });
}

function writeFirstChips (chips) {
    nextChips(chips);
    $(window).bind("scroll", windowScroll);
}

function sendSearchingText() {
    $(window).unbind("scroll", windowScroll);
    stompClient.send("/app/search", {}, JSON.stringify({'message': $("#search").val(), 'id': "0"}));
}

function sendNeedNextChips() {
    stompClient.send("/app/nextChips", {}, {});
}

function addChip(name, address, cost) {
    let idElem = lastId++;

    elem = $('<li></li>');

    p = createElement('<p></p>', '');
    linkElem = createElement('<a></a>', '');

    linkElem.attr('id', 'popover' + idElem);
    linkElem.attr('href', address);
    linkElem.text(name+ cost);

    p.append(linkElem);
    p.append(createElement('<p></p>','').text('cost ' + cost));
    elem.append(p);

    $("#mainTable").append(elem);
    addLoadPopover('popover' + idElem);
    $('#popover' + idElem).on('shown.bs.popover', function(){
        sendMoreInfo(address, 'popover' + idElem);
    });
}

function addLoadPopover(idElem) {
    divMain = createElement("<div></div>", "");
    spiner = createElement("<span></span>", "fas fa-spinner fa-7x glyphicon-refresh-animate");

    spiner.attr('data-html', "true");
    spiner.attr('data-toggle', "popover");
    spiner.attr('id', "popoverElemId");

    divMain.append(spiner);

    addPopover(divMain, '#' + idElem);
}

function addInformationPopover(srcImg, information, idElem) {
    p = createElement("<p></p>", "card-text");
    p.text(information);

    divP = createElement("<div></div>", "card-body");

    img = createElement("<img></img>", "card-img-top mx-auto");
    img.attr("src", srcImg);

    divMain = createElement("<div></div>", "card");
    divMain.attr('data-html', "true");
    divMain.attr('data-toggle', "popover");
    divMain.attr('id', "popoverElemId");
    divMain.css('width', "18rem");

    divMainInner = createElement("<div></div>", "");
    divMainInner.append(divMain);
    divP.append(p);
    divMain.append(img);
    divMain.append(divP);

    addPopover(divMainInner, '#' + idElem);

    $('#' + idElem).popover('show');
}

function addPopover(divMain, idElem) {
    $(idElem).attr('data-toggle', "tooltip");
    $(idElem).attr('data-html', "true");
    $(idElem).attr('data-original-title', divMain.html());
    $(idElem).attr('data-placement', "auto");
    $(idElem).popover( { delay: {show: 500, hide: 500}, trigger: 'hover' } );
}

function createElement(tagElement, classElement) {
    return $(tagElement).attr('class', classElement);
}

function sendMoreInfo(address, idElem) {
    $('#' + idElem).off('shown.bs.popover');

    stompClient.send("/app/information", {}, JSON.stringify({'message': address, 'id': idElem}));
}

$( document ).ready(function () {
    connect();

    $("form").on('submit', function (e) {
        e.preventDefault();
        sendSearchingText();
    });

    lastId = 0;
});
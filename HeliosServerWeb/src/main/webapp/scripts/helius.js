function HeliusJSObject() {
    HeliusJSObject.prototype.load = function () {
        var items = [];
        $.getJSON("status.json", function (data) {
            $.each(data, function (key, val) {
                items.push("<li id='" + key + "'>" + val + "</li>");
            });

            $("<ul/>", {
                "class": "my-new-list",
                html: items.join("")
            }).appendTo("#json");
        });
    };

    HeliusJSObject.prototype.update = function () {
        $.getJSON("status.json", function (data) {
            if (data["udprunning"]) {
                $("#startStop").text("Parar");
            } else {
                $("#startStop").text("Iniciar");

            }
            $("#statusUDP").text(data["udpstatus"]);
        });
    };

    HeliusJSObject.prototype.startStop = function () {
        if ($("#startStop").text() === "Parar") {
            $.get("status.json?action=stop");
        } else {
            $.get("status.json?action=start");
        }
        heliusjs.update();
    };
}

var heliusjs = new HeliusJSObject(); 
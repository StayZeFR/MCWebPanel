const SERVER = "http://localhost:8080/";
$(document).ready(function () {
    $("#nav-logout").click(function () {
        $.ajax({
            url: SERVER,
            type: "POST",
            data: { logout: true },
            success: function () {
                //alert("OK");
            },
            error: function () {
                //alert("NON");
            }
        });
    });
});
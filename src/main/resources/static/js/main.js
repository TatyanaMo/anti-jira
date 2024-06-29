function getUsers() {
    $.get("/api/users", function(data) {
        var htmlToInsert = "";

        $.each(data, function(index, value) {
            htmlToInsert += "<div>";
            htmlToInsert += value.firstName + " " + value.lastName;
            htmlToInsert += "</div";
        });

        $("#user-list").html(htmlToInsert);
    });
}

function getBookmarks() {
    $.get("/bookmarks/all", function(data) {
        var htmlToInsert = "";

        $.each(data, function(index, value) {
            htmlToInsert += "<div class=\"ticket\">";
            htmlToInsert += "<div><img src=\"/img/delete.svg\" class=\"bookmark-ico\" onclick=\"removeBookmark(" + value.id + ");\"></div>";
            htmlToInsert += "<div class=\"tickets-id\">" + value.ticket.id + "</div>";

            htmlToInsert += "<a href=\"/ticket/" + value.ticket.id + "\">";
            htmlToInsert += "<div class=\"tickets-summary\">" + value.ticket.summary + "</div>";
            htmlToInsert += "</a>";

            htmlToInsert += "<div class=\"tickets-reporter\">" + value.ticket.user.firstName + " " + value.ticket.user.lastName + "</div>";

            htmlToInsert += "</div>";
        });

        $("#bookmark-list").html(htmlToInsert);
    });
}

function getBookmarksCount() {
    $.get("/bookmarks/count", function(data) {
        $("#bookmarks-count").html(data);
    });
}

function addBookmark(id) {
    $.post("/bookmarks/add/" + id, function(data) {
        getBookmarksCount();
    });
}

$("#assignee").on("paste keyup", function() {
    if ($("#assignee").val().length >= 3) {
        $.get("/api/users/" + $("#assignee").val(), function(data) {
            var htmlToInsert = "";

            $.each(data, function(index, value) {
                htmlToInsert += "<div>";
                htmlToInsert += value.firstName + " " + value.lastName;
                htmlToInsert += "</div>";
            });

            $("#user-list").html(htmlToInsert);
        });
    }
});


function openModal() {
    document.getElementById("modal").style.display = "block";
}

function closeModal() {
    document.getElementById("modal").style.display = "none";
}
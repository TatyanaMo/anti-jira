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

$("#assignee").on("paste keyup", function() {
    if ($("#assignee").val().length >=3) {
        $.get("/api/users/" + $("#assignee").val(), function(data) {
            var htmlToInsert = "";

            $.each(data, function(index, value) {
                htmlToInsert += "<div>";
                htmlToInsert += value.firstName + " " + value.lastName;
                htmlToInsert += "</div";
            });

            $("#user-list").html(htmlToInsert);
        });
    }
});
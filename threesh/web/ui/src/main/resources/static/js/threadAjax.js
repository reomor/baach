let modal = $("#threadReplyModal");
let ajaxUrl = "/rest/thread";
let imageUrl = "/rest/image";

function replyThread(id) {
    $("#inputThreadId").val(id);
    modal.modal();
}

function replyThreadSave() {
    let file = $("#inputPostFile")[0].files[0];
    let id = $("#inputThreadId").val();
    let formData = new FormData();
    formData.append("file", file);
    let xhr = new XMLHttpRequest();
    xhr.open("POST", imageUrl + "/" + id);
    xhr.send(formData);

    formData = {
        message: $("#inputPostMessage").val()
    };

     $.ajax({
        url: ajaxUrl + "/post/" + id,
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(formData)
    }).done(function () {
        modal.modal("hide");
        location.reload(true);
    }).fail(function(xhr, tStatus, err) {
        alert(err);
    });
}
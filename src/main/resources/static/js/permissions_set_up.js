$(function () {
    danqianjiaose();
    permissions();
})

function danqianjiaose() {
    $.ajax({
        type: "get",
        url: "getDepartment"
    }).success(function (data) {
        var tables = '';
        for (var i = 0; i < data.data.length; i++) {
            tables += "<option onmousedown='userAll(" + data.data[i].id + ")' value='" + data.data[i].id + "'>" + data.data[i].departmentName + "</option>"
        }
        $("#departments").append(tables);
        userAll($("#departments").val());
    })
}

function userAll() {
    var xuanzhong = $("#departments").val();
    var datas = '';
    $.ajax({
        type: "post",
        url: "getUserAll",
        data: {
            "id": xuanzhong
        }
    }).success(function (data) {
        $("#left").empty();
        if (data != null) {
            for (var i = 0; i < data.data.length; i++) {
                datas += "<input type='button' id='left" + data.data[i].id + "' onclick='userLeft(" + data.data[i].id + ")' style='width: 100%;text-align: center' value='" + data.data[i].userName + "'/>"
            }
        } else {
            datas = '<div style="text-align: center; margin-top: 30px;"><span>无数据</span></div>'
        }
        $("#left").append(datas);
    })
}


function userLeft(id) {
    var arrays = new Array();
    var boot = true;
    $("#right > input").each(function () {
        arrays.push($(this).val());
    })
    for (var i = 0; i < arrays.length; i++) {
        if (arrays[i] === $("#left" + id).val()) {
            boot = false;
        }
    }
    if (boot) {
        var data = $("#left" + id).val();
        $("#right").append("<input type='button' id='right" + id + "' onclick='userRight(" + id + ")' style='width: 100%;text-align: center' value='" + data + "' />")
        $("#left" + id).remove();
    } else {
        alert("用户已存在");
    }
}

function userRight(id) {
    var arrays = new Array();
    var boot = true;
    $("#left > input").each(function () {
        arrays.push($(this).val());
    })
    for (var i = 0; i < arrays.length; i++) {
        if (arrays[i] === $("#right" + id).val()) {
            boot = false;
        }
    }
    if (boot) {
        var data = $("#right" + id).val();
        $("#left").append("<input type='button' id='left" + id + "' onclick='userLeft(" + id + ")' style='width: 100%;text-align: center' value='" + data + "'/>")
        $("#right" + id).remove();
    } else {
        alert("用户已存在");
    }

}

function permissions() {
    var datas = '';
    $.ajax({
        type: "post",
        url: "getPermissionByIdAllUserName",
        data: {
            "id": $("#permissionsId").val()
        }
    }).success(function (data) {
        $("#right").empty();
        if (data != null) {
            for (var i = 0; i < data.data.length; i++) {
                datas += '<input type="button" id="right' + data.data[i].id + '" onclick="userRight(' + data.data[i].id + ')" style="width: 100%;text-align: center" value="' + data.data[i].userName + '"/>'
            }
        } else {
            datas += '<div style="text-align: center; margin-top: 30px;"><span>无数据</span></div>'
        }
        $("#right").append(datas);
    })
}

function tijiao() {
    var userName = $("#userName").val();
    var datas = '';
    $.ajax({
        type: "post",
        url: "getUserByUserName",
        data: {
            "userName": userName
        }
    }).success(function (data) {
        $("#right").empty();
        if (data != null) {
            for (var i = 0; i < data.data.length; i++) {
                datas += '<input type="button" id="left' + data.data[i].id + '" onclick="userLeft(' + data.data[i].id + ')" style="width: 100%;text-align: center" value="' + data.data[i].userName + '"/>'
            }
        } else {
            datas += '<div style="text-align: center; margin-top: 30px;"><span>无数据</span></div>'
        }
        $("#right").append(datas);
    })
}

function nextAll() {

}

function prevAll() {

}

function updateUser(){
    var userName = new Array() ;
    $("#right > input").each(function(){
        userName.push($(this).val())
    })
    console.log(userName);
    console.log(userName.toString());
    $.ajax({
        type:"post",
        url:"updatePermissionsByUserName",
        data:{
            "userName":userName.toString(),
            "id":$("#permissionsId").val()
        }
    }).success(function(data){
        alert(data.data)
    })
}

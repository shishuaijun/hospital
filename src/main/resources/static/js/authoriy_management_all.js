$(function(){
    departmentName();
})

function departmentName(){
    $.ajax({
        type:"post",
        url:"getDepartmentName",
    }).success(function (data) {
        console.log(data.data)
        var departmentNameS = '' ;
        var departmentBossS = '' ;
        for (var i = 0; i <data.data.length ; i++) {
            departmentNameS += '<option value="'+data.data[i].departmentName+'">'+data.data[i].departmentName+'</option>'
            departmentBossS += '<option value="'+data.data[i].departmentBoss+'">'+data.data[i].departmentBoss+'</option>'
        }
        $("#departmentName").append(departmentNameS);
        $("#departmentBoss").append(departmentBossS);
    })
}

function submitDepartment(){
    var departmentName = $("[name='departmentName']").val();
    var hospitalId = $("[name='hospitalId']").val();
    var departmentBoss = $("[name='departmentBoss']").val();
    var is_department= '' ;
    if (departmentName == null && departmentName == undefined ){
        is_department +="科室,"
    }else if (hospitalId == '' && hospitalId == undefined){
        is_department +="医院,"
    }else if (departmentBoss == null && departmentBoss ==undefined){
        is_department += "负责人"
    }
    if (is_department != '' && is_department != undefined){
        alert(is_department+"不能为没数据")
    }else{
        $.ajax({
            type:"post",
            url:"addDepartment",
            data:{
                'departmentName':departmentName,
                'hospitalId':hospitalId,
                'departmentBoss':departmentBoss
            }
        }).success(function(data){
            alert(data.data)
            window.location.go(0);
        })
    }


}
function departmentName(){
    var departmentName = $("#addDepartment > [name='departmentName']").val();
    if (departmentName == null && departmentName == undefined){
        $("#addDepartment > [name='departmentName']").append('<span style="color: red">*</span>')
    }
}
function departmentBoss(){
    var departmentBoss = $("#addDepartment > [name='departmentBoss']").val();
    if (departmentBoss == null && departmentBoss == undefined){
        $("#departmentBoss > [name='departmentName']").append('<span style="color: red">*</span>')
    }
}
function updateDepartment(){
    var departmentNameUpdate = $("#departmentNameUpdate").val();
    var departmentBossUpdate = $("#departmentBossUpdate").val();
    $.ajax({
        type:"post",
        url:"updateDepartment",
        data:{
            'id':$("#departmentId").val(),
            'hospitalId':$("#hospitalId").val(),
            'departmentName': departmentNameUpdate,
            'departmentBoss': departmentBossUpdate
        }
    }).success(function (data) {
        layer.msg(data.data);
        history.go(0);
    })
}
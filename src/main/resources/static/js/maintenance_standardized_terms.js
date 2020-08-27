$(function () {
    treeData()
})

function treeData() {
    $.ajax({
        type: "post",
        url: "getTermInforamtion"
    }).success(function (data) {
        var dataTerm = data.data;
        for (var i = 0; i < dataTerm.length; i++) {
            var yijiId = "'" + "yiji-" + dataTerm[i].id + "'";
            //一级目录
            if (dataTerm[i].erjiId == null & dataTerm[i].sanjiId == null) {

                $("#term").append('<div id="yiji-' + dataTerm[i].id + '" ><input type="checkbox" onclick="zhangshi(' + yijiId + ') ; inputall(' + yijiId + ')" />&nbsp;&nbsp;<a onclick="xianshi(' + yijiId + ') ">' + dataTerm[i].termName + '</a></div>')
            }
        }
        //二级目录
        $("#term > div").each(function () {
            for (var i = 0; i < dataTerm.length; i++) {
                var erjiId = "'" + "erji-" + dataTerm[i].id + "'";
                if ($(this).attr('id').substring($(this).attr('id').length - 1, $(this).attr('id').length) == dataTerm[i].erjiId) {

                    $(this).append('<div id="erji-' + dataTerm[i].id + '" style="margin-left: 20px; display: none" ><input type="checkbox" onclick="zhangshi(' + erjiId + ') ; inputall(' + erjiId + ')" />&nbsp;&nbsp;<a onclick="xianshi(' + erjiId + ')">' + dataTerm[i].termName + '</a></div>')
                }
            }
        })
        //三级目录
        $("#term > div > div").each(function () {
            var arr = $(this).attr('id').split('-');
            arr.splice(0, 1);
            for (var i = 0; i < dataTerm.length; i++) {
                var sanjiId = "'" + "sanji-" + dataTerm[i].id + "'";
                if (arr == dataTerm[i].sanjiId) {

                    $(this).append('<div id="sanji-' + dataTerm[i].id + '" style="margin-left: 20px ;display: none " ><input type="checkbox" onclick="zhangshi(' + sanjiId + ') ; inputall(' + sanjiId + ')" />&nbsp;&nbsp;<a onclick="xianshi(' + sanjiId + ')">' + dataTerm[i].termName + '</a></div>')
                }
            }
        })
    })
}

function xianshi(id) {
    var dataDiv = $("#" + id.trim() + "> div");
    if (dataDiv != null) {
        for (var i = 0; i < dataDiv.length; i++) {
            if ($("#" + dataDiv[i].id).css('display') == 'none') {
                $("#" + dataDiv[i].id).css('display', 'block');
            } else {
                $("#" + dataDiv[i].id).css('display', 'none');
            }
        }
    }
}

function zhangshi(id) {
    var dataDiv = $("#" + id.trim() + "> div");
    if (dataDiv != null) {
        for (var i = 0; i < dataDiv.length; i++) {
            if ($("#" + dataDiv[i].id + " input ").is(':checked')) {
                $("#" + dataDiv[i].id + " input ").prop('checked', false);
                $("#input-" + dataDiv[i].id).remove();
            } else {
                $("#" + dataDiv[i].id + " input ").prop('checked', true);
            }
        }
    }
}

function inputall(id) {
    var dataDiv = $("#" + id.trim() + " > div");
    //判断下方是否有子标签
    if (dataDiv.length > 0) {
        //下面所有的div
        for (var i = 0; i < dataDiv.length; i++) {
            if ($("#" + dataDiv[i].id + " > div").length > 0) {
                var sanjiInput = $("#" + dataDiv[i].id + " > div")
                for (var j = 0; j < sanjiInput.length; j++) {
                    if ($("#" + sanjiInput[j].id + " input").is(':checked')) {
                        if ($("#input-" + sanjiInput[j].id) == null) {
                            $("#inputDiv").append('<div id="input-' + sanjiInput[j].id + '" style="width: 200px ; margin-right: 15px;display: block;"><span>' + sanjiInput[j].innerText + ':</span><input type="text"  placeholder="请输入" /></div>')
                        } else {
                            $("#inputDiv").append('<div id="input-' + sanjiInput[j].id + '" style="width: 200px ; margin-right: 15px;display: block;"><span>' + sanjiInput[j].innerText + ':</span><input type="text"  placeholder="请输入" /></div>')
                        }
                    } else {
                        $("#input-" + sanjiInput[j].id).css('display','none');
                    }
                }
            } else {
                if ($("#" + dataDiv[i].id + " input").is(':checked')) {
                    if ($("#input-" + dataDiv[i].id) == null) {
                        $("#inputDiv").append('<div id="input-' + dataDiv[i].id + '" style="width: 200px ; margin-right: 15px;display:block;"><span>' + dataDiv[i].innerText + ':</span><input type="text"  placeholder="请输入" /></div>')
                    } else {
                        $("#inputDiv").append('<div id="input-' + dataDiv[i].id + '" style="width: 200px ; margin-right: 15px;display:block;"><span>' + dataDiv[i].innerText + ':</span><input type="text"  placeholder="请输入" /></div>')
                    }
                } else {
                    $("#input-" + dataDiv[i].id).css('display','none');
                }
            }
        }
    } else {
        //单个的进这
        var dataInput = $("#" + id.trim());
        if ($("#" + dataInput[0].id + " input").is(':checked')) {
            if ($("#input-" + dataInput[0].id) == null) {
                $("#inputDiv").append('<div id="input-' + dataInput[0].id + '" style="width: 200px ; margin-right: 15px;display: block;"><span>' + dataInput[0].innerText + ':</span><input type="text"  placeholder="请输入" /></div>')
            } else {
                $("#inputDiv").append('<div id="input-' + dataInput[0].id + '" style="width: 200px ; margin-right: 15px;display: block;"><span>' + dataInput[0].innerText + ':</span><input type="text"  placeholder="请输入" /></div>')
            }
        } else {
            $("#input-" + dataInput[0].id).css('display', 'none');
        }

    }
}

function submit() {
    var inputAll = $("#inputDiv > div ");
    var dataInput = new Array();
    var dataInputId = new Array();
    for (var i = 0; i <inputAll.length; i++) {
        if($("#"+inputAll[i].id).css('display') != 'none'){
            if ($("#"+inputAll[i].id + " > input").val() !=  null && $("#"+inputAll[i].id + " > input").val() !='' && $("#"+inputAll[i].id + " > input").val() != undefined){
                dataInput.push($("#"+inputAll[i].id + " > input").val());
                var ids = inputAll[i].id.split('-');
                ids.splice(0,2);
                dataInputId.push(ids[0])

            }else{
                $("#"+inputAll[i].id + " > input").append('<span style="color: red">数据为空</span>')
            }
        }
    }
    console.log(dataInput)
    console.log(dataInputId)
    if (dataInput.length > 0 && dataInputId.length > 0){
        $.ajax({
            type:"post",
            url:"addByupdateTermDataInformation",
            data:{
                'dataValue':dataInput.toString(),
                'dataId':dataInputId.toString(),
                'userId':'1'
            }
        }).success(function (data) {
            alert(data.data)
        })
    }


}
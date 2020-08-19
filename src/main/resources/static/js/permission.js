
function qinchu (){
	var userName  = $("[name='userName']").val();
	var remarks = $("[ name='remarks']").val();
	if(userName != null && userName != undefined){
		$("[name='userName']").val('') ;
	}
	if(remarks != null && remarks != undefined){
		$("[ name='remarks']").val('')  ;
	}
}

function queryPermissions(){
	var userName = $("input[name='userName']").val();
	var remarks = $("input[name='remarks']").val();
	$.ajax({
		type:"post",
		url:"",
		data:{
			"userName":userName,
			"remarks":remarks
		}
	}).success(function(data){

	})
}
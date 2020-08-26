
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

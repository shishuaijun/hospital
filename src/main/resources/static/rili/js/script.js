$(document).ready(function(){
// $(function(){
	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();
	var event = [];
	$.ajax({
		url:"getdateview",
		type:"post"
	}).success(function (data) {
		var datas= data.data;
		for (let i = 0; i <datas.length; i++) {
			event.push({title:datas[i].t,start: new Date(datas[i].y,datas[i].m,datas[i].d)})
		}
	})


	if($('.calendar').length > 0){

		$('.calendar').fullCalendar({
			header: {
				left: 'prev,next,today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			buttonText:{
				prev:'上月份',
				next:'下月份',
				today:'跳转到当天',
				month:'月',
				agendaWeek:'周',
				agendaDay:'天'
			},
			editable: true,events:event
		});
	}
	
});
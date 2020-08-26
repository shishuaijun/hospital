$(function () {
    treeData()
})

function treeData(){
    $.ajax({
        type:"post",
        url:"getTermInforamtion"
    }).success(function(data){
       var dataTerm = data.data ;
       for (var i =  0 ;  i < dataTerm.length; i++){
           if (dataTerm[i].beanId == null){
               $("#term").append('<li id="yiji'+dataTerm[i].id+'">'+dataTerm[i].termName+'</li>');
           }
           for (var j = 0; j < dataTerm.length; j++) {
           }
       }
    })
}
$(function(){
	var obj ={trending:"yes"};
	$.ajax({
		url:"ApolloServlet",
		data: obj,
		dataType: "json",
		success:createTable,
		complete:function() {
			
		}
		
	})	
});

function createTable(data) {
	
	$("#trending").append("<table id=\"restable\" border=\"1\" cellspacing=\"0\"></table>");
	var table = $("#trending");
	table.append("<th>Party Name</th><th>End Time</th><th>Score</th>");
	$.each(data,function(index, obj){
		var row = "<tr><td>" + obj.name
		table.append(row);
		
	});
 }
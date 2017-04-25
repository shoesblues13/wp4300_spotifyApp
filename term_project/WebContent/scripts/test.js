$(function(){
	var obj ={trending:"yes"};
	$.ajax({
		url:"ApolloServlet",
		data: obj,
		dataType: "json",
		success:createTable
		
	})	
});

function createTable(data) {
	
	var table = $("#trending");
	var row = "<tr><td>" + data;
	table.append("<th>Party Name</th><th>End Time</th><th>Score</th>");
 }


var addItem;
var addSong;
var addGuest;

$(function(){
	$("#addItem").click(function(){
		var val = {bringitem: $("#bringListEdit").val()};
		addItem = $("#bringListEdit").val();
		$.ajax({
			url:"ApolloServlet",
			data: val,
			datatype:"json",
			success:createTable
			
		})
		
		
	});
	
	$("#addSong").click(function(){
		var val = {musicEdit: $("#musicEdit").val()};
		addSong = $("#musicEdit").val();
		$.ajax({
			url:"ApolloServlet",
			data: val,
			datatype:"json",
			success:createMusic
			
		})
		
		
	});
	
	$("#addGuest").click(function(){
		var val = {guestListEdit: $("#guestListEdit").val()};
		addGuest = $("#guestListEdit").val();
		$.ajax({
			url:"ApolloServlet",
			data: val,
			datatype:"json",
			success:createGuest
			
		})
		
		
	});
	
	
});

function createTable(data) {
	
	
	var table = $("#bringTable");

		var row = "<tr><td>" + addItem;
		table.append(row);
		add = "";
	

		
 }

function createMusic(data) {
	
	
	var table = $("#musicTable");

		var row = "<tr><td>" + addSong;
		table.append(row);
		addSong = "";


		
 }

function createGuest(data) {
	
	var check = JSON.stringify(data);
	var test = check.indexOf("true");
	if(check.indexOf("true")!=-1){
		var table = $("#guestTable");
		var row = "<tr><td>" + addGuest;
		table.append(row);	
	}
	addGuest = "";
		
 }

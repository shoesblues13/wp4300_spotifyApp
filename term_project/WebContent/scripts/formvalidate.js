$(function(){
	$("#email").change(emailValidate());
	$("#uname").change(unameValidate());
	
});

function emailValidate() {
	var mydata = {"name":"email","value":$("#email").val()};
	var myJSON = JSON.stringify(obj);
	$.post("ApolloServlet", {
		data: mydata,
		
	});
}

function unameValidate() {
	var mydata = {"name":"uname", "value":$("#uname").val()};
	$.post("ApolloServlet", {
		name: "uname",
		
	});
}

function checkResponse(response){
	
}
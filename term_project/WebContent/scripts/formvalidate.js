$(function(){
	$(".email").change(function(){
		var email = $(".email").val();
		if(email.includes("@")){
		$.post("ApolloServlet",
				{
				name: "email",
				page: "validate",
				value: email
				}	
		);
		}
		else {
			alert("Not a valid email");
		}
		
	});
			
	
	
	
});

function emailValidate() {
	alert($("#email").val());
	
	/* $.post("ApolloServlet",
			    {
			        name: "email",
			        input: $("#email").val(),
			        page: "validate"
			    },
			    function(data, status){
			        alert("Data: " + data + "\nStatus: " + status);
			    });
			    */
}

function unameValidate() {
	alert($("#uname").val());
	//var mydata = {"name":"uname", "value":$("#uname").val()};
	/*$.post("ApolloServlet", 
			{
				name: "uname",
				input: $("#uname").val(),
				page: "validate"
			},
			function(data, status){
		        alert("Data: " + data + "\nStatus: " + status);
		    });*/
}


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="../../CSS/finalProject.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="finalProject.js"></script>
	<title>Apollo Dashboard</title>
	<#include "css/finalProject.css">
</head>
<body>
	<div id="topBar" class="homeDivs">
		<span id="title">Apollo Dashboard</span>
		<div id="signin">
			<input type="submit" name="button" value="Sign Out" form="homePageForm" />
		</div>
	</div>
	<br/>
	<br/>
	<div id="dashboard" class="homeDivs">
		<div id="userDash">
			<h1>Welcome ${name}!</h1>
			<div id="homeButtons">
				<form method="get" action="signIn.html">
					<input type="submit" name="button" value="View Invites" />
					<input type="submit" name="button" value="Host a New Party" />
					<input type="submit" name="button" value="Find a Party" />
				</form>	
			</div>
		</div>

		<div id="hostingParties">
			Parties Hosted
		</div>

		<div id="attendingParties">
			Parties Attending
		</div>
		<span class="stretch"></span>
	</div>
</body>
</html>

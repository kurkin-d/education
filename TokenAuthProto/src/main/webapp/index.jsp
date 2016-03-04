<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app="exampleApp">
<head>
	<title>Angular Rest SpringSecurity Example</title>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" integrity="sha256-MfvZlkHCEqatNoGiOXveE8FIwMzZg4W85qfrfIFBfYc= sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==" crossorigin="anonymous">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha256-k2/8zcNbxVIh5mnQ52A0r3a6jAgMGxFJFE2707UxGCk= sha512-ZV9KawG2Legkwp3nAlxLIVFudTauWuBpC10uEafMHYL0Sarrz5A7G79kXh5+5+woxQ5HM559XX2UZjMJ36Wplg==" crossorigin="anonymous">
	<link href="css/custom.css" rel="stylesheet">
</head>
<body>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.js"></script>
	<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha256-Sk3nkD6mLTMOF0EOpNtsIry+s1CsaqQC1rVLTAy+0yc= sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.2/angular.js"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.2/angular-resource.js"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.2/angular-route.js"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.2/angular-cookies.js"></script>
	<script type="text/javascript" src="js/libs/angular-ui-router.js"></script>
	<script type="text/javascript" src="js/config.js"></script>
	<script type="text/javascript" src="js/libs/lodash.compat.js"></script>
	<script type="text/javascript" src="js/libs/restangular.js"></script>
	<script type="text/javascript" src="js/app_rest_angular.js"></script>




	<div class="loader" ng-hide="initialized">
		Loading...
	</div>

	<nav class="navbar navbar-fixed-top navbar-default ng-hide" role="navigation" ng-show="initialized">
		<input id="templatePrefix" type="hidden" value="<c:url value="/"/>">
		<div class="container">

			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Angular+Rest+SpringSecurity</a>
			</div>

			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="#!/">Home</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<!-- 
						If you like this example please flattr or donate before removing the links below, thank you!
					-->
					<li class="dropdown" ng-show="user">
						<a href="" class="dropdown-toggle" data-toggle="dropdown">{{user.name}} <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="" ng-click="logout()"><span class="fa fa-sign-out"></span> Logout</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container ng-hide" ng-show="initialized">
		<div class="alert alert-danger" ng-show="error">{{error}}</div>
		<div ui-view></div>
	</div>


</body>
</html>
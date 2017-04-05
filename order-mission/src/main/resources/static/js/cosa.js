app.run(function($rootScope, auth)
		{
	console.log(auth.getRole());
	auth.checkStatus();
})
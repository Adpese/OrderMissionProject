app.factory("auth", function($cookies, $cookieStore, $sessionStorage, $window, $location) {
	return {
		login : function(username, role) {
			
			$cookies.username = username;
			
			$window.location.href = "http://localhost:8080/home";
		},
		logout : function() {
			$cookieStore.remove("username");
			$cookieStore.remove("role");
			$window.location.href = "http://localhost:8080/login";
			sessionStorage.removeItem('incre');
			
		},
		checkStatus : function() {
			var privateRoutes = [ "http://localhost:8080/home"];
			var publicRoutes = ["http://localhost:8080/login"];			
			if (this.in_array($location.absUrl(), privateRoutes) && typeof ($cookies.username) == "undefined") {
				$window.location.href = "http://localhost:8080/login";
			}

			if (this.in_array($location.absUrl(), publicRoutes) && typeof ($cookies.username) != "undefined") {
				$window.location.href = "http://localhost:8080/home";
			}
		},
		in_array : function(needle, haystack) {
			var key = '';
			for (key in haystack) {
				if (haystack[key] == needle) {
					return true;
				}
			}
			return false;
		},
		getRole : function() {
			return $cookies.role;
		},
		
		getMissionDetails : function (id){
			
			

			$http.get('/busquedaMission/'+ x).success(function(data) {
				
				return data;

			});
			
			
		}
	}


});
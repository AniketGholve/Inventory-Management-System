
let app = angular.module("loginApp", ['ngRoute']);

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: "/login.html"

        })
        .when('/home/:param1', {
            templateUrl: "/home.html"
        })
       .when('/elp_users' ,{
        templateUrl: "/elp_users.html"
       })
       .when('/mlp_users' ,{
        templateUrl: "/mlp_users.html"
       })
       .when('/clp_users' ,{
        templateUrl: "/clp_users.html"
       })
       .when('/alp_users' ,{
        templateUrl: "/alp_users.html"
       })
      

});

app.controller("loginCtrl", ($scope, $http, $window) => {


    $scope.title = "Login Page";
    $scope.getRequest = (v) => {
        console.log("I've been pressed!");
       
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/login/',
            data: $scope.submit,
            headers: { 'Content-Type': 'application/json' }
        }).then((response) => {
            $scope.data = response.data;
            console.log($scope.data);

            if ($scope.data == 2) {
                $window.location.href = "#!home/" + v;
            }
            else {
                alert("Access Denied ");
            }
        })
    }
});

app.controller("homeCtrl", function ($scope, $window, $location, $http, $routeParams) {

    $scope.getUserForMLP = function () {
        console.log("Get user data for MLP")
        $http({
            method: 'GET',
            url: 'http://localhost:8080/api/login/get/' + $routeParams.param1


        }).then((response) => {
            $scope.data = response.data;
            console.log(response.data);
            if (response.data.role === "MLP") {

                $window.location.href = "#!mlp_users";
            }
            else {
                $windows.location.href = 'http://localhost:8080/api/login/get/' + $routeParams.param1;
            }


        })
    }
    $scope.getUserForELP = function () {
        console.log("Get user data for ELP")
        $http({
            method: 'GET',
            url: 'http://localhost:8080/api/login/get/' + $routeParams.param1


        }).then((response) => {
            $scope.data = response.data;
            console.log(response.data);

            if (response.data.role === "ELP") {
                $window.location.href = "#!elp_users";

            }
            else {
                $windows.location.href = 'http://localhost:8080/api/login/get/' + $routeParams.param1;
            }


        })
    }

    $scope.getUserForCLP = function () {
        console.log("Get user data for CLP")
        $http({
            method: 'GET',
            url: 'http://localhost:8080/api/login/get/' + $routeParams.param1


        }).then((response) => {
            $scope.data = response.data;
            console.log(response.data);

            if (response.data.role === "CLP") {
                $window.location.href = "#!clp_users";

            }
            else {
                $windows.location.href = 'http://localhost:8080/api/login/get/' + $routeParams.param1;
            }


        })
    }

    $scope.getUserForALP = function () {
        console.log("Get user data for ALP")
        $http({
            method: 'GET',
            url: 'http://localhost:8080/api/login/get/' + $routeParams.param1


        }).then((response) => {
            $scope.data = response.data;
            console.log(response.data);

            if (response.data.role === "ALP") {
                $window.location.href = "#!alp_users";

            }
            else {
                $windows.location.href = 'http://localhost:8080/api/login/get/' + $routeParams.param1;
            }


        })
    }
    
    app.controller("elp_userCtrl", function ($scope) {

            // $scope.tab = 1;
            // console.log("running");
            // $scope.setTab = function (newTab) {
            //     console.log("hit");
            //     $scope.tab = newTab;
            // };

            // $scope.isSet = function (tabNum) {
            //     console.log("run");
            //     return $scope.tab === tabNum;
            // };

        });

    app.controller("mlp_userCtrl", function ($scope, $window, $location, $http, $routeParams) {

        $scope

    });

    app.controller("alp_userCtrl", function ($scope, $window, $location, $http, $routeParams) {

        $scope

    });

    app.controller("clp_userCtrl", function ($scope, $window, $location, $http, $routeParams) {

        $scope

    });

});









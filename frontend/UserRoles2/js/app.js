
let app = angular.module("loginApp", ['ngRoute']);

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: "view/login.html"
        })
        .when('/home/:param1', {
            templateUrl: "view/home.html"
        })
        .when('/elp_users', {
            templateUrl: "view/elp_users.html"
        })
        .when('/mlp_users', {
            templateUrl: "view/mlp_users.html"
        })
        .when('/clp_users', {
            templateUrl: "view/clp_users.html"
        })
        .when('/alp_users', {
            templateUrl: "view/alp_users.html"
        })
});

app.controller("loginCtrl", ($scope, $http, $window) => {


    $scope.title = "Login Page";
    $scope.getRequest = (v) => {
        console.log("I've been pressed!");

        $http({
            method: 'POST',
            url: 'http://localhost:7890/login/',
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
            url: 'http://localhost:7890/api/get/' + $routeParams.param1


        }).then((response) => {
            $scope.data = response.data;
            console.log(response.data);
            if (response.data.role === "MLP") {

                $window.location.href = "#!mlp_users";
            }
            else {
                $windows.location.href = 'http://localhost:7890/api/get/' + $routeParams.param1;
            }


        })
    }
    $scope.getUserForELP = function () {
        console.log("Get user data for ELP")
        $http({
            method: 'GET',
            url: 'http://localhost:7890/api/get/' + $routeParams.param1


        }).then((response) => {
            $scope.data = response.data;
            console.log(response.data);

            if (response.data.role === "ELP") {
                $window.location.href = "#!elp_users";

            }
            else {
                $windows.location.href = 'http://localhost:7890/api/get/' + $routeParams.param1;
            }


        })
    }

    $scope.getUserForCLP = function () {
        console.log("Get user data for CLP")
        $http({
            method: 'GET',
            url: 'http://localhost:7890/api/get/' + $routeParams.param1


        }).then((response) => {
            $scope.data = response.data;
            console.log(response.data);

            if (response.data.role === "CLP") {
                $window.location.href = "#!clp_users";

            }
            else {
                $windows.location.href ="#!unauthorized";
            }


        })
    }

    $scope.getUserForALP = function () {
        console.log("Get user data for ALP")
        $http({
            method: 'GET',
            url: 'http://localhost:7890/api/get/' + $routeParams.param1


        }).then((response) => {
            $scope.data = response.data;
            console.log(response.data);

            if (response.data.role === "ALP") {
                $window.location.href = "#!alp_users";

            }
            else {
                $windows.location.href = 'http://localhost:7890/api/get/' + $routeParams.param1;
            }
        })
    }
});

app.controller("mlp",($scope,$http)=>{
    let token='eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmlrZXRAMDIxMCIsImV4cCI6MTY3NjQ3MjY3MiwiaWF0IjoxNjc2NDU0NjcyfQ.fCL9451lDm3fhuikG1m1B_kg9gxEJnUOVayZPChwfZKQn2yoBghgsPpzBn19P-_gSnB4LQmA-UUk8IlpijBd9w';
    $http({
        method: 'GET',
        url: 'http://localhost:7890/api/get/aniket@0210',
        headers:{ 
    } 
    }).then((response) => {
        $scope.data = response.data;
        console.log(response.data);
    })
});
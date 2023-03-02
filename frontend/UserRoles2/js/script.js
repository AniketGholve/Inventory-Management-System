
function myFunction() {
    var x = document.getElementById("myDIV");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}
app.controller("elp", ['$scope', '$http', function ($scope, $http) {
    $scope.navOption3Link = "#!";
    $scope.navOption3 = "Logout";
    $scope.navOption5Link = "#!edit_user";
    $scope.navOption5 = "MyAccount";
    $scope.navOption6Link = "#!clinics";
    $scope.navOption6 = "Clinics";
    $scope.navOption7Link = "#!success_orders";
    $scope.navOption7 = "Success Orders";
    $scope.navOption8Link = "#!error_orders";
    $scope.navOption8 = "Error Orders";
    $scope.tabs = [{
        title: 'Success Orders',
        url: 'successOrders.html'
    }, {
        title: 'Error Orders',
        url: 'errorOrders.html'
    }];
    $scope.currentTab = 'elp_users.html'

    $scope.onClickTab = function (tab) {
        $scope.currentTab = tab.url;
    }

    $scope.isActiveTab = function (tabUrl) {
        return tabUrl == $scope.currentTab;
    }

    $http({
        method: 'GET',
        url: 'http://localhost:7890/api/SuccessOrders/1',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        console.log(response.data);
        $scope.success_data = response.data;
    },
        (error) => {
            console.log(error);
        });

    $http({
        method: 'GET',
        url: 'http://localhost:7890/api/ErrorOrders/2',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        console.log(response.data);
        $scope.error_data = response.data;
    },
        (error) => {
            console.log(error);
        });
}]);


app.controller('logoutCtrl', function ($scope, $window) {
    $scope.logout = () => {
        sessionStorage.removeItem("token")
        $window.location.href = "#!";
    }
});







app.controller("edit_userCtrl", function ($scope, $http, $window, $route) {
    $scope.navOption1Link = "#!";
    $scope.navOption1 = "Login";
    $scope.navOption2Link = "#!register";
    $scope.navOption2 = "Register";
    $scope.navOption3Link = "#!";
    $scope.navOption3 = "Logout";
    $scope.navOption5Link = "#!edit_user";
    $scope.navOption5 = "MyAccount";

    $http({
        method: 'GET',
        url: "http://localhost:7890/api/getUserDetails/" + sessionStorage.getItem("username"),
        headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
    }).then((response) => {
        $scope.edit = response.data;
        console.log($scope.edit);
        // $scope.edit.password = null;
        $scope.edit.dateofBirth = new Date($scope.edit.dateofBirth);
    }, (error) => {
        console.log(error);
    });
    $scope.updatedData = () => {
        // delete $scope.edit.confirmPassword;
        console.log($scope.edit);
         $scope.edit.username = sessionStorage.getItem("username");

        if ($scope.edit.confirmPassword === $scope.edit.password) {
            document.getElementById("valid").style.display = "block";
            document.getElementById("Invalid").style.display = "none";
        }
        else {
            document.getElementById("Invalid").style.display = "block";
            document.getElementById("valid").style.display = "none";
        }
        if (document.getElementById("valid").style.display === "block") {
            $http({
                method: 'PUT',
                url: "http://localhost:7890/api/editUser/" + $scope.edit.username,
                headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
                data: $scope.edit
            }).then((response) => {
                console.log($scope.edit);
                $window.location.href = "#!";
            }, (error) => {
                console.log(error);
            });
        };
    };
});


app.controller('clinicController', function ($scope, $http, $window) {
    $scope.navOption1Link = "#!clinics";
    $scope.navOption1 = "Clinic";
    $scope.navOption3Link = "#!";
    $scope.navOption3 = "Logout";
    $scope.hide2 = "d-none";
    $scope.hide = "d-none";
    console.log("run");
    $http({

        method: 'GET',
        url: 'http://localhost:7890/getAllEnterprise',
        headers: {
            'Content-Type': 'application/json'
        }

    }).then((response) => {

        console.log(response.data);

        $scope.clinic_data = response.data;

    },
        (error) => {

            console.log(error);
        }
    );
    $scope.deleteClinic = (id) => {
        console.log("delete");
        console.log(id);
        $http({
            method: 'DELETE',
            url: 'http://localhost:7890/deleteEnterprise/' + id,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => {
            $window.location.reload();
        }, (error) => {
            console.log(error);
        })
    }
});

app.controller("successController", ['$scope', '$http', function ($scope, $http) {
    $scope.navOption1Link = "#!success_orders";
    $scope.navOption1 = "Success Orders";
    $scope.navOption2Link = "#!error_orders";
    $scope.navOption2 = "Error Orders";
    $scope.navOption3Link = "#!";
    $scope.navOption3 = "Logout";
    $http({
        method: 'GET',
        url: 'http://localhost:7890/api/SuccessOrders/1',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        console.log(response.data);
        $scope.success_data = response.data;
    },
        (error) => {
            console.log(error);
        });
}]);

app.controller("errorController", ['$scope', '$http', function ($scope, $http) {
    $scope.navOption1Link = "#!success_orders";
    $scope.navOption1 = "Success Orders";
    $scope.navOption2Link = "#!error_orders";
    $scope.navOption2 = "Error Orders";
    $scope.navOption3Link = "#!";
    $scope.navOption3 = "Logout";
    $http({
        method: 'GET',
        url: 'http://localhost:7890/api/ErrorOrders/2',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        console.log(response.data);
        $scope.error_data = response.data;
    },
        (error) => {
            console.log(error);
        });
}]);





app.controller("elp", ['$scope', '$http', function ($scope, $http) {
   console.log("Hello")
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


app.controller('logoutCtrl', function($scope,$window,$http){
    $scope.logout = () => {
        sessionStorage.removeItem("token")
        $window.location.href = "#!";

}
});


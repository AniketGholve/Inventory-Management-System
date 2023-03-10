const app = angular.module("myApp", ["ngRoute"]);
var formData;
app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "/view/insertPatient.html"
        })
        .when("/patientData", {
            templateUrl: "/view/patientData.html"
        })
        .when("/updatePatient/:param1", {
            templateUrl: "/view/updatePatient.html"
        })
        .when("/findById", {
            templateUrl: "/view/searchById.html"
        })
        .when("/productList", {
            templateUrl: "/view/productList.html"
        })
});

app.controller("MyController", ($scope, $http) => {

    $http.get("http://localhost:7890/getAllData").then((response) => {
        $scope.allPatientData = response.data;
    }, (error) => {
        console.log(error)
    })

    $scope.getById = () => {
        var url = "http://localhost:7890/getPatientById/" + $scope.variable;
        console.log(url)
        $http.get(url).then((response) => {
            $scope.searchPatientData = response.data;
        }, (error) => {
            $scope.searchPatientData = null;
            alert("No Data Found");
        })
    }
    $scope.deleteById = (id) => {
        $http.delete("http://localhost:7890/deletePatient/" + id).then((response) => { }, (error) => { })
    }
});

app.controller('updateController', function ($scope, $http, $routeParams, $filter) {
    $http.get("http://localhost:7890/getPatientById/" + $routeParams.param1).then((response) => {
        $scope.updateFormData = response.data;
        $scope.updateFormData.patientDob = new Date(response.data.patientDob);
    }, (error) => {
        console.log(error);
    })
    $scope.updatePatientForm = function () {
        $http({
            method: 'PUT',
            url: 'http://localhost:7890/updatePatient',
            data: $scope.updateFormData,
            headers: { 'Content-Type': 'application/json' }
        }).then(function (data) {
            if (data.errors) {
                $scope.errorUserName = data.errors;
            } else {
                console.log($scope.updateFormData)
                alert("Updated Data")
            }
        });
    };
});

app.controller('insertController', function ($scope, $http) {
    $scope.submit = {};
    $scope.submitForm = function () {
        $http({
            method: 'POST',
            url: 'http://localhost:7890/createPatient',
            data: $scope.formDataFields,
            headers: { 'Content-Type' : 'application/json' }
        }).then(function (data) {
            if (data.errors) {
                $scope.errorUserName = data.errors;
            } else {
                alert("Data Added Successfully");
                $scope.formDataFields = null;
            }
        });
    };
});

app.controller("findByIdController", ($scope, $http) => {
    $scope.serach_patient_id;
    $scope.getById = () => {
        var url = "http://localhost:7890/getPatientById/" + $scope.serach_patient_id;
        console.log(url)
        $http.get(url).then((response) => {
            console.log(response)
            $scope.data = response.data;
        }, (error) => {
            $scope.data = null
            alert("No Data Found");
        })
    }
});

app.controller("inventoryController", ($scope, $http) => {
    $http.get("http://localhost:7890/getAllData").then((response) => {
        $scope.data = response.data;
    }, (error) => {
        console.log(error)
    })
})


app.controller("productList", ($scope, $http) => {
    $http.get("http://localhost:7890/getScreen").then((response) => {
        $scope.data = response.data;
    }, (error) => {
        console.log(error)
    })
    $scope.getOnHand = (id) => {
        $http.get("http://localhost:7890/getSerialNumber/" + id).then((response) => {
            $scope.onHandData = response.data;
        }, (error) => {
            console.log(error)
        })
    }
    $scope.getExpired = (id) => {
        $http.get("http://localhost:7890/getExpiredSerialDetails/" + id).then((response) => {
            $scope.expiredData = response.data;
        }, (error) => {
            console.log(error)
        })
    }
})



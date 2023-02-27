function reloadWindow() {
    location.reload();
}
let app = angular.module("myApp", ['ngRoute']);
app.factory('myInterceptor', function ($q) {
    var interceptor = {
        responseError: function (rejection) {
            if (rejection.status === 401) {
                window.location.href = '#!';
                console.log("Unauthorized To access the page");
            }
        }
    };
    return interceptor;
});


app.directive("fileInput", function ($parse) {
    return {
        link: function (scope, element, attrs) {
            element.on("change", function (event) {
                // var files = event.target.files;
                // console.log(attrs)
                // console.log(files[0].name);
                $parse(attrs.fileInput).assign(scope, element[0].files);
                scope.$apply();
            });
        },
    }
});


app.config(function ($routeProvider, $httpProvider) {
    $routeProvider
        .when('/', {
            templateUrl: "view/login.html"
        })
        .when('/register', {
            templateUrl: "view/registration.html"
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
        .when("/updatePatient/:param1", {
            templateUrl: "view/updatePatient.html"
        })
        .when("/insertPatient", {
            templateUrl: "/view/insertPatient.html"
        });
    $httpProvider.interceptors.push('myInterceptor');
});

app.controller("loginCtrl", ($scope, $http, $window) => {
    $scope.navOption1Link = "#!";
    $scope.navOption1 = "Login";
    $scope.navOption2Link = "#!register";
    $scope.navOption2 = "Register";
    $scope.getRequest = (v) => {
        $http({
            method: 'POST',
            url: 'http://localhost:7890/login',
            data: $scope.submit,
            headers: { 'Content-Type': 'application/json', 'Authorization': 'qweryui' }
        }).then((response) => {
            $scope.data = response.data;
            sessionStorage.setItem("token", "Bearer " + $scope.data.token)
            if ($scope.data) {
                $http({
                    method: 'GET',
                    url: 'http://localhost:7890/api/get/' + $scope.submit.username,
                    headers: { 'Authorization': sessionStorage.getItem("token") }
                }).then((response) => {
                    $scope.data = response.data;
                    if ($scope.data.role == "CLP") {
                        $window.location.href = "#!clp_users";
                    }
                    else if ($scope.data.role == "MLP") {
                        $window.location.href = "#!mlp_users";
                    }
                    else if ($scope.data.role == "ALP") {
                        $window.location.href = "#!alp_users";
                    }
                    else if ($scope.data.role == "ELP") {
                        $window.location.href = "#!elp_users";
                    }
                })
            }
        }, (error) => {
            alert("Wrong User Id Or Password");
        })
    }
});

app.controller("mlp", ($scope, $http) => {
    $scope.hide = "d-none";
    $scope.navOption1 = "Inventory"
    $scope.navOption1Link = "#!mlp_users"
    $http({
        method: 'GET',
        url: 'http://localhost:7890/getScreen',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        $scope.data = response.data;
    }, (error) => {
        console.log(error)
    })
    $scope.getOnHand = (id) => {
        $http({
            method: 'GET',
            url: "http://localhost:7890/getSerialNumber/" + id,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => {
            $scope.onHandData = response.data;
        }, (error) => {
            console.log(error)
        })
    }
    $scope.getExpired = (id) => {
        $http({
            method: 'GET',
            url: "http://localhost:7890/getExpiredSerialDetails/" + id,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => {
            $scope.expiredData = response.data;
        }, (error) => {
            console.log(error)
        })
    }
});

app.controller("clp", function ($scope, $http) {
    $scope.navOption1Link = "#!/clp_users";
    $scope.navOption1 = "Patient";
    $scope.navOption2Link = "#!/insertPatient";
    $scope.navOption2 = "Insert Patient";

    $http({
        method: 'GET',
        url: "http://localhost:7890/getAllData",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        $scope.allPatientData = response.data;
        let arrayCount = $scope.allPatientData.length;
        $scope.searchedPatientFileRange = function (min, max) {
            var input = [];
            for (var i = min; i <= max; i++) {
                input.push(i);
            }
            return input;
        };
        var range = [];
        for (var i = 0; i < arrayCount; i++) {
            var data = [];
            for (var j = 0; j < $scope.allPatientData[i].patientFile.length; j++) {
                data.push(j);
            }
            range.push(data);
        }
        $scope.range = range;
    }, (error) => {
        console.log(error)
    })

    $scope.getById = () => {
        $http({
            method: 'GET',
            url: "http://localhost:7890/getPatientById/" + $scope.variable,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => {
            if(response==null)
            {
                alert("No Data Found");
            }
            else
            {
                $scope.searchPatientData = response.data;
            }
            
        }, (error) => {
            alert("No Data Found");
            $scope.searchPatientData = null;
        });
    }
    $scope.deleteById = (id) => {
        $http({
            method: 'delete',
            url: "http://localhost:7890/deletePatient/" + id,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => { }, (error) => { })
    }
    $scope.fileDownload = (id) => {
        $http({
            method: 'get',
            url: "http://localhost:7890/downloadFile?id=" + id,
            headers: {
                'Authorization': sessionStorage.getItem("token")
            },
            responseType: 'arraybuffer'
        }).then((response) => {
            var file = new Blob([response.data], { type: 'application/pdf' });
            var fileURL = URL.createObjectURL(file);
            window.open(fileURL);
        }, (error) => { console.log(error) })
    }
});

app.controller('registerController', function ($scope, $http, $window) {
    $scope.navOption1Link = "#!";
    $scope.navOption1 = "Login";
    $scope.navOption2Link = "#!register";
    $scope.navOption2 = "Register";

    $scope.register = {};
    $scope.formData = () => {
        console.log($scope.register);
        $http({
            method: 'Post',
            url: "http://localhost:7890/api/addUser",
            headers: { 'Content-Type': 'application/json' },
            data: $scope.register
        }).then((response) => {
            $window.location.href = "#!";
        }, (error) => {
            console.log(error);
        });
    };
});


app.controller('updateController', function ($scope, $http, $routeParams, $window, $rootScope) {
    $scope.navOption1Link = "#!/clp_users";
    $scope.navOption1 = "Patient";
    $scope.hide = "d-none";
    $rootScope.dataFile = null;
    $scope.fileData = (files) => {
        if($rootScope.dataFile==null)
        {
            $rootScope.dataFile = files;
        }
    }
    $scope.deleteData = (id) => {
        updatedFiles = $rootScope.dataFile;
        files = [];
        for (var i = 0; i < updatedFiles.length; i++) {
            if (updatedFiles[i].name != updatedFiles[id].name) {
                files.push(updatedFiles[i]);
            }
        }
        $rootScope.dataFile = files
    }
    $http({
        method: 'GET',
        url: "http://localhost:7890/getPatientById/" + $routeParams.param1,
        data: $scope.updateFormData,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        $scope.updateFormData = response.data;
        $scope.updateFormData.patientDob = new Date(response.data.patientDob);
        $scope.fileCount = $scope.updateFormData.patientFile.length;
        var range = [];
        for (var i = 0; i < $scope.fileCount; i++) {
            range.push(i);
        }
        $scope.range = range;
    }, (error) => {
        console.log(error);
    })
    $scope.updatePatientForm = function () {
        $http({
            method: 'PUT',
            url: 'http://localhost:7890/updatePatient',
            data: $scope.updateFormData,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then(function (data) {
            if (data.errors) {
                $scope.errorUserName = data.errors;
                alert("Error Occured No Data was changed");
            } else {
                var form_data = new FormData();
                angular.forEach($rootScope.dataFile, function (file) {
                    form_data.append('file', file);
                });
                if ($rootScope.dataFile != null) {
                    form_data.append('patient', JSON.stringify(data.data));
                    //$http.post("http://localhost:7890/createPatientForFile", data.data).then(function (response) { });
                    $http.put("http://localhost:7890/updateUploadMultiplePatientFile", form_data,
                        {
                            transformRequest: angular.identity,
                            headers: { 'Content-Type': undefined, 'Process-Data': false, 'Authorization': sessionStorage.getItem("token") }
                        }).then(function (response) {
                            var uplodeSuccessSign = document.getElementById("uplodeSuccessSign");
                            uplodeSuccessSign.style.display = "block";
                        });
                }
                alert("Data Updated Successfully");
                $window.location.href = "#!/clp_users";
            }
        });
    };

    $scope.deleteFile = (fileId) => {
        console.log(fileId);
        var deleteUrl = "http://localhost:7890/deleteFile?fileId=" + fileId;
        $http.delete(deleteUrl, {
            headers: { 'Authorization': sessionStorage.getItem("token") }
        }).then(function (response) {
            $window.location.reload();
        });
    }
});

app.controller('insertController', function ($scope, $http, $window, $rootScope) {
    $scope.navOption1Link = "#!/clp_users";
    $scope.navOption1 = "Patient";
    $scope.hide = "d-none";
    $scope.submit = {};
    $rootScope.dataFile = null;
    $scope.fileData = (files) => {
        if($rootScope.dataFile==null)
        {
            $rootScope.dataFile = files;
        }
    }
    $scope.deleteData = (id) => {
        updatedFiles = $rootScope.dataFile;
        files = [];
        for (var i = 0; i < updatedFiles.length; i++) {
            if (updatedFiles[i].name != updatedFiles[id].name) {
                files.push(updatedFiles[i]);
            }
        }
        $rootScope.dataFile = files
    }

    $scope.submitForm = function () {
        $http({
            method: 'POST',
            url: 'http://localhost:7890/createPatient',
            data: $scope.formDataFields,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then(function (data) {
            console.log(data);
            if (data.errors) {
                $scope.errorUserName = data.errors;
            } else {
                var form_data = new FormData();
                angular.forEach($rootScope.dataFile, function (file) {
                    form_data.append('file', file);
                });
                if ($rootScope.dataFile != null) {
                    form_data.append('patient', JSON.stringify(data.data));
                    $scope.updateFileData = {}
                    //$http.post("http://localhost:7890/createPatientForFile", data.data).then(function (response) { });
                    $http.post("http://localhost:7890/uploadMultiplePatientFile", form_data,
                        {
                            transformRequest: angular.identity,
                            headers: { 'Content-Type': undefined, 'Process-Data': false, 'Authorization': sessionStorage.getItem("token") }
                        }).then(function (response) {
                            var uplodeSuccessSign = document.getElementById("uplodeSuccessSign");
                            uplodeSuccessSign.style.display = "block";
                        }, (error) => {
                            alert("file not uploaded");
                            console.log(error)
                        });
                }
                alert("Data Added Successfully");
                //$window.location.href = "#!clp_users";
            }
        });
    };
});
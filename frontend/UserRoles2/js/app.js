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
            templateUrl: "view/success_orders.html"
        })
        .when('/mlp_users', {
            templateUrl: "view/mlp_users.html"
        })
        .when('/inventory', {
            templateUrl: "view/inventory.html"
        })
        .when('/clp_users', {
            templateUrl: "view/clp_users.html"
        })
        .when('/alp_users', {
            templateUrl: "view/clinics.html"
        })
        .when("/updatePatient/:param1", {
            templateUrl: "view/updatePatient.html"
        })
        .when("/insertPatient", {
            templateUrl: "/view/insertPatient.html"
        })
        .when("/clinics", {
            templateUrl: "/view/clinics.html"
        })
        .when("/edit_user", {
            templateUrl: "/view/edit_user.html"
        })
        .when("/updateClinic/:param1",
        {
            templateUrl: "/view/updateClinic.html"
        })
        .when("/success_orders", {
            templateUrl: "/view/success_orders.html"
        })
        .when("/error_orders", {
            templateUrl: "/view/error_orders.html"
        })
        .when("/insertClinic",
            {
                templateUrl: "/view/insertClinic.html"
            })
        .when("/addUser/:param1",
            {
                templateUrl: "/view/addClinicUser.html"
            })
        .when("/updateUser/:param1",
            {
                templateUrl: "/view/updateClinicUser.html"
            })
        .when("/clinicUsers",
            {
                templateUrl: "/view/clinicUsers.html"
            })
        .when("/clinicDetails/:param1",
            {
                templateUrl: "/view/clinicDetails.html"
            });
    $httpProvider.interceptors.push('myInterceptor');
});

app.controller("loginCtrl", ($scope, $http, $window) => {

    $scope.navOption1Link = "#!";
    $scope.navOption1 = "Login";
    $scope.navOption2Link = "#!register";
    $scope.navOption2 = "Register";
    $scope.hideUser = "d-none";


    $scope.getRequest = (v) => {
        console.log($scope.submit)
        $http({
            method: 'POST',
            url: 'http://localhost:7890/login',
            data: $scope.submit,
            headers: { 'Content-Type': 'application/json', 'Authorization': 'qweryui' }
        }).then((response) => {
            $scope.data = response.data;
            console.log($scope.data)
            console.log(response.data)
            sessionStorage.setItem("token", "Bearer " + $scope.data.token)
            if ($scope.data) {
                sessionStorage.setItem("username", $scope.submit.username)
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

app.controller("clp", function ($scope, $http) {

    $scope.navOption1Link = "#!/clp_users";
    $scope.navOption1 = "Patients";
    $scope.navOption4Link = "#!/insertPatient";
    $scope.navOption4 = "Insert Patient";
    $scope.navOption6Link = "#!/clinicUsers"
    $scope.navOption6 = "Users"
    $scope.navOption3Link = "#!";
    $scope.navOption3 = "Logout";
    $scope.navOption2Link = "#!inventory";
    $scope.navOption2 = "Inventory";
    $scope.navOption5Link = "#!edit_user";
    $scope.navOption5 = "My Account";
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
            if (response == null) {
                alert("No Data Found");
            }
            else {
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


app.controller('registerController', function ($scope, $http,$window) {
    $scope.navOption1Link="#!";
    $scope.navOption1="Login";
    $scope.navOption2Link="#!register";
    $scope.navOption2="Register";
    $scope.hideUser="d-none"
    
  
 
    $scope.register = {};
    $scope.formData = () => {
        console.log($scope.register);
        if($scope.register.confirmPassword === $scope.register.password){
            document.getElementById("valid").style.display = "block";
            document.getElementById("Invalid").style.display = "none";
        }
        else {
            document.getElementById("Invalid").style.display = "block";
            document.getElementById("valid").style.display = "none";
        }
        if(document.getElementById("valid").style.display === "block"){
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
        }
    };
});



app.controller('updateController', function ($scope, $http, $routeParams, $window, $rootScope) {
    $scope.navOption1Link = "#!/clp_users";
    $scope.navOption1 = "Patients";
    $scope.hide = "d-none";
    $scope.hide2 = "d-none";
    $rootScope.dataFile = null;
    $scope.fileData = (files) => {
        if ($rootScope.dataFile == null) {
            $rootScope.dataFile = files;
        }
        else {
            updatedFiles = $rootScope.dataFile;
            newfiles = [];
            for (var i = 0; i < updatedFiles.length; i++) {
                newfiles.push(updatedFiles[i]);
            }
            updatedFiles = files
            for (var i = 0; i < updatedFiles.length; i++) {
                newfiles.push(updatedFiles[i]);
            }
            document.getElementById("fileInputField").value = "";
            $rootScope.dataFile = newfiles;
        }
        console.log($rootScope.dataFile.length)
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
    $scope.navOption1 = "Patients";
    $scope.navOption3Link = "#!";
    $scope.navOption3 = "Logout";
    $scope.hide2 = "d-none";
    $scope.hide = "d-none";

    $scope.submit = {};
    $rootScope.dataFile = null;
    $scope.fileData = (files) => {
        if ($rootScope.dataFile == null) {
            $rootScope.dataFile = [...files];
            document.getElementById("fileInputField").value = "";
        }
        else {
            updatedFiles = $rootScope.dataFile;
            newfiles = [];
            for (var i = 0; i < updatedFiles.length; i++) {
                newfiles.push(updatedFiles[i]);
            }
            updatedFiles = files
            for (var i = 0; i < updatedFiles.length; i++) {
                newfiles.push(updatedFiles[i]);
            }
            document.getElementById("fileInputField").value = "";
            $rootScope.dataFile = newfiles;
        }
        console.log($rootScope.dataFile.length)
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
                $window.location.href = "#!clp_users";
            }
        });
    };
});



app.controller('insertClinic', function ($scope, $http, $window) {
    $scope.navOption1Link = "#!clinics";
    $scope.navOption1 = "Clinics";
    $scope.navOption3Link = "#!";
    $scope.navOption3 = "Logout";
    $scope.hide2 = "d-none";
    $scope.hide = "d-none";
    $scope.formDataFields = {};
    $scope.createEnterpriseForm = () => {
        console.log($scope.formDataFields);
        $http({
            method: 'Post',
            url: "http://localhost:7890/createClinic",
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
            data: $scope.formDataFields
        }).then((response) => {
            $window.location.href = "#!clinics";
        }, (error) => {
            console.log(error);
        });
    }
});

app.controller('updateClinic', function ($scope, $http, $window, $routeParams) {
    $scope.navOption3Link = "#!";
    $scope.navOption3 = "Logout";
    $scope.navOption1Link = "#!clinics";
    $scope.navOption1 = "Clinics";
    $scope.hide2 = "d-none";
    $scope.hide = "d-none";
    $http({
        method: 'get',
        url: "http://localhost:7890/getByClinicId/" + $routeParams.param1,
        headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
    }).then((response) => {
        $scope.updateEnterpriseFormData = response.data;
    }, (error) => {
        console.log(error);
    });
    $scope.updateEnterpriseForm = () => {
        $http({
            method: 'put',
            url: "http://localhost:7890/updateClinic",
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
            data: $scope.updateEnterpriseFormData
        }).then((response) => {
            alert("Data Update Sussessfully")
            $window.location.href = "#!clinics"
        }, (error) => {
            console.log(error);
        });
    }
});

app.controller('clinicSelect',function($scope,$http){
  

        $http({
            method: 'get',
            url: "http://localhost:7890/getClinicNames",
            headers: { 'Content-Type': 'application/json' ,'Authorization': sessionStorage.getItem("token")}
        }).then((response)=>{
            $scope.clinicNames=response.data;
        },(error)=>{})

});

        
    
app.controller('allClinicsUsers', function ($scope, $http, $window) {
    $scope.navOption3Link = "#!";
    $scope.navOption3 = "Logout";
    $scope.navOption1Link = "#!clinics";
    $scope.navOption1 = "Clinics";
    $scope.hide2 = "d-none";
    $scope.hide = "d-none";
    $http({
        method: 'get',
        url: "http://localhost:7890/api/getAllUsers",
        headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }
    }).then((response) => {
        $scope.clinicsUsers = response.data;
        console.log($scope.clinicsUsers)
    }, (error) => { })
    $scope.deleteUsers = (id) => {
        $http({
            method: 'delete',
            url: "http://localhost:7890/api/deleteUsers/" + id,
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
        }).then((response) => {
            $window.location.reload();
        }, (error) => {
            console.log(error);
        })
    }
});

// app.controller('clinicSelect', function ($scope, $http) {
//     $scope.navOption3Link = "#!";
//     $scope.navOption3 = "Logout";
//     $scope.navOption1Link = "#!clinics";
//     $scope.navOption1 = "Clinics";
//     $scope.hide2 = "d-none";
//     $scope.hide = "d-none";
//     $scope.getClinicData = () => {
//         $http({
//             method: 'get',
//             url: "http://localhost:7890/getClinicNames",
//             headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }
//         }).then((response) => {
//             $scope.clinicNames = response.data;
//         }, (error) => { })
//     }
// });

app.controller('registerUserFields', function ($scope, $window, $http, $routeParams) {
    $scope.navOption3Link = "#!";
    $scope.navOption3 = "Logout";
    $scope.navOption1Link = "#!clinics";
    $scope.navOption1 = "Clinics";
    $scope.hide2 = "d-none";
    $scope.hide = "d-none";
    $scope.addUserFields = () => {
        $scope.addUserData.defaultLocationId = $routeParams.param1;
        $http({
            method: 'post',
            url: "http://localhost:7890/api/addUsers",
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
            data: $scope.addUserData,
        }).then((response) => {
            console.log(response.data)
            alert("Data Added Successfully");
            $window.location.href = "#!/clinicDetails/" + $scope.addUserData.defaultLocationId;
        }, (error) => {
            alert("Error Occured In Storing Data Please try again");
            console.log(error);
        })
    }
});

app.controller('updateUser', function ($scope, $window, $routeParams, $http) {
    $scope.navOption3Link = "#!";
    $scope.navOption3 = "Logout";
    $scope.navOption1Link = "#!clinics";
    $scope.navOption1 = "Clinics";
    $scope.hide2 = "d-none";
    $scope.hide = "d-none";
    $scope.updateUserData = {};
    $http({
        method: 'get',
        url: "http://localhost:7890/api/getUserByUserId/" + $routeParams.param1,
        headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
        data: $scope.updateUserData,
    }).then((response) => {
        $scope.updateUserData = response.data;
        $scope.password = $scope.updateUserData.password;
        $scope.updateUserData.password = "";
    }, (error) => {
        console.log(error);
    })
    $scope.updateUserFileds = () => {
        $scope.navOption3Link = "#!";
        $scope.navOption3 = "Logout";
        $scope.navOption1Link = "#!clinics";
        $scope.navOption1 = "Clinics";
        $scope.hide2 = "d-none";
        $scope.hide = "d-none";
        console.log($scope.updateUserData)
        if ($scope.updateUserData.password == null) {
            $scope.updateUserData.password = $scope.password;
        }
        $http({
            method: 'put',
            url: "http://localhost:7890/api/editUsers/" + $routeParams.param1,
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
            data: $scope.updateUserData,
        }).then((response) => {
            alert("Data Updated Successfully");
            $window.location.href = "#!/clinicDetails/" + $scope.updateUserData.defaultLocationId;

        }, (error) => {
            alert("Error Occured In Storing Data Please try again");
            console.log(error);
        })
    }
});
app.controller('clinicDetails', function ($scope, $window, $routeParams, $http) {
    $scope.navOption1Link = "#!clinics";
    $scope.navOption1 = "Clinics";
    $scope.navOption3Link = "#!";
    $scope.navOption3 = "Logout";
    $scope.hide2 = "d-none";
    $scope.hide = "d-none";
    $scope.addUserData = {};
    $http({
        method: 'get',
        url: "http://localhost:7890/getByClinicId/" + $routeParams.param1,
        headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
    }).then((response) => {
        $scope.clinicData = response.data;
    }, (error) => {
        console.log(error);
    });
    $http({
        method: 'get',
        url: "http://localhost:7890/api/getUsersByLocationId/" + $routeParams.param1,
        headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
    }).then((response) => {
        $scope.clinicUserData = response.data;
    }, (error) => {
        console.log(error);
    })
    $scope.deleteUsers = (id) => {
        $http({
            method: 'delete',
            url: "http://localhost:7890/api/deleteUsers/" + id,
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
        }).then((response) => {
            $window.location.reload();
        }, (error) => {
            console.log(error);
        })
    }
});
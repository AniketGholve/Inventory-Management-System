function reloadWindow() {
    location.reload();
}
let app = angular.module("myApp", ['ngRoute']);
window.onload = () => {
    document.querySelector("#preloader").style.display = "none";
}
app.factory('myInterceptor', function ($q) {
    var interceptor = {
        responseError: function (rejection) {
            if (rejection.status === 401) {
                sessionStorage.removeItem("token")
                sessionStorage.removeItem("locationId")
                sessionStorage.removeItem("username")
                window.location.href = '#!';
                console.log("Unauthorized To access the page");
            }
        }
    };
    return interceptor;
});

app.controller("headerController", ($scope, $http, $location) => {
    var path = $location.path();
    switch (path) {
        case '/': $scope.activeTab = 'login';
            break;
        case '/clp_users': $scope.activeTab = 'clpHome';
            break;
        case '/register': $scope.activeTab = 'register';
            break;
        case '/inventory': $scope.activeTab = 'inventory';
            break;
        case '/clp_users': $scope.activeTab = 'patient';
            break;
        case '/alp_users': $scope.activeTab = 'clinic';
            break;
        case '/clinics': $scope.activeTab = 'clinic';
            break;
        case '/clinicUsers': $scope.activeTab = 'user';
            break;
        case '/ordersInfo': $scope.activeTab = 'orderInfo';
            break;
        case '/orders': $scope.activeTab = 'order';
            break;
        case '/shipping': $scope.activeTab = 'shipping';
            break;
    }
    if (sessionStorage.getItem("username") != undefined) {
        $http({
            method: 'GET',
            url: 'http://localhost:7890/api/get/' + sessionStorage.getItem("username"),
            headers: { 'Authorization': sessionStorage.getItem("token") }
        }).then((response) => {
            $scope.data = response.data;
            if ($scope.data.role == "CLP") {
                $scope.alp = "d-none";
                $scope.mlp = "d-none";
                $scope.elp = "d-none";
                $scope.login = "d-none";
            }
            else if ($scope.data.role == "MLP") {
                $scope.alp = "d-none";
                $scope.clp = "d-none";
                $scope.elp = "d-none";
                $scope.login = "d-none";
            }
            else if ($scope.data.role == "ALP") {
                $scope.clp = "d-none";
                $scope.mlp = "d-none";
                $scope.elp = "d-none";
                $scope.login = "d-none";
            }
            else if ($scope.data.role == "ELP") {
                $scope.clp = "d-none";
                $scope.mlp = "d-none";
                $scope.alp = "d-none";
                $scope.login = "d-none";
            }
        })
    }
    else {
        $scope.clp = "d-none";
        $scope.alp = "d-none";
        $scope.userIcon = "d-none";
    }
})

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
            templateUrl: "view/login.html",
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
            templateUrl: "view/clp_home.html"
        })
        .when('/patient', {
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
            })
        .when("/userDetails/:param1",
            {
                templateUrl: "/view/clinicUserView.html"
            })
        .when("/ordersInfo",
            {
                templateUrl: "/view/ordersInfo.html"
            })
        .when('/orders',
            {
                templateUrl: "view/orders.html"
            })
        .when('/shipping',
            {
                templateUrl: "view/shipping.html"
            });
    $httpProvider.interceptors.push('myInterceptor');
});

app.controller("loginCtrl", ($scope, $http, $window,) => {
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

app.controller("clp", function ($scope, $http, $window, $location) {
    $scope.placeOrder = (inventoryData) => {
        $http({
            method: 'POST',
            url: 'http://localhost:7890/createOrder/' + sessionStorage.getItem("locationId"),
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => {
            $scope.createdOrderData = response.data;
            console.log(inventoryData);

            $http({
                method: 'POST',
                url: 'http://localhost:7890/createOrderEvent/' + $scope.createdOrderData.orderId,
                data: inventoryData,
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': sessionStorage.getItem("token")
                }
            }).then((response) => {
                alert("Order Placed Successfully")
                $window.location.reload();
                console.log(response);
            }, (error) => {
                console.log(error);
            })
        }, (error) => {
            console.log(error)
        })
    }

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
    $http({
        method: 'get',
        url: "http://localhost:7890/getClinicNames",
        headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }
    }).then((response) => {
        $scope.clinicNames = response.data;
    }, (error) => { })

    $scope.clinicName = (id) => {
        sessionStorage.setItem("locationId", id);
        $window.location.reload();
    }
    $scope.screensName = (id) => {
        sessionStorage.setItem("screensName", id);
        if (sessionStorage.getItem("screensName") == "ordering") {
            $window.location.href = "#!/orders";
        }
        else if (sessionStorage.getItem("screensName") == "inventory") {
            $window.location.href = "#!/inventory";
            $window.location.reload();
        }
    }
    $scope.inventoryLocation = () => {
        let allPath = $location.path();
        if (allPath === "/inventory" || allPath === "/orders") {
            return true;
        }
        return false;
    }
    if (sessionStorage.getItem("locationId") != undefined || sessionStorage.getItem("locationId") != null) {
        $scope.id = sessionStorage.getItem("locationId");
        $scope.selectedDropdownTab = sessionStorage.getItem("screensName");
        const elements = document.querySelectorAll(".isDisabled");
        for (let i = 0; i < elements.length; i++) {
            elements[i].classList.remove("isDisabled");
        }
        $http({
            method: 'get',
            url: "http://localhost:7890/getPatientByClinic/" + sessionStorage.getItem("locationId"),
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }
        }).then((response) => {
            getPatientDetails(response.data);
        }, (error) => { })

        $http({
            method: 'get',
            url: "http://localhost:7890/getInventoryByClinic/" + sessionStorage.getItem("locationId"),
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }
        }).then((response) => {
            getInventoryDetails(response.data);
        }, (error) => { })
    }

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
    getPatientDetails = (data) => {
        $scope.patientData = data;
        for (let index = 0; index < $scope.patientData.length; index++) {
            localStorage.setItem("locationId" + $scope.patientData[index].id, $scope.patientData[index].patientLocationId);
        }

    }
    getInventoryDetails = (data) => {
        $scope.invetoryData = data;
    };
});

app.controller("alp", ($scope, $http, $window) => {
    // clinic controller
    $http({
        method: 'GET',
        url: 'http://localhost:7890/getAllClinic',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        console.log(response.data);
        $scope.clinic_data = response.data;
    }, (error) => {
        console.log(error);
    });
    $scope.deleteClinic = (id) => {
        console.log("delete");
        console.log(id);
        $http({
            method: 'DELETE',
            url: 'http://localhost:7890/deleteClinic/' + id,
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
    // Order Controller
    $http({
        method: 'GET',
        url: 'http://localhost:7890/getOrderingScreen',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        $scope.orderData = response.data;
    }, (error) => {
        console.log(error);
    });
    $scope.changeOrderStatus = (orderEventId) => {
        $http({
            method: 'POST',
            url: 'http://localhost:7890/changeStatus/' + orderEventId,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => {
            alert("Status Changed Successfully");
            $window.location.reload();
        }, (error) => {
            console.log(error);
        });
    }
    $scope.cancleOrder = (orderEventId) => {
        $http({
            method: 'Delete',
            url: 'http://localhost:7890/cancelOrder/' + orderEventId,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => {
            alert("Order Deleted");
            $window.location.reload();
        }, (error) => {
            console.log(error);
        });
    }
    $scope.viewInventory = (productId, locationId) => {
        $http({
            method: 'GET',
            url: 'http://localhost:7890/getinventoryByProductId/' + productId + "/" + locationId,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => {
            $scope.orderInventoryData = response.data;
        }, (error) => {
            console.log(error);
        });
    }
});
app.controller("shipping", ($scope, $http, $window) => {
    $scope.clinicShipToName;
    $http({
        method: 'Get',
        url: "http://localhost:7890/getAllShipToId",
        headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }
    }).then((response) => {
        $scope.clinicDropdownName = response.data;
        $scope.orderIdFunction = () => {
            console.log($scope.clinicShipToName)
            $http({
                method: 'Get',
                url: "http://localhost:7890/getShippingDataByShippingId/" + $scope.clinicShipToName,
                headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }
            }).then((response) => {
                $scope.clinicShipToData = response.data;
                $scope.demoVar = false;
            }, (error) => {
                console.log(error);
            });
        }
    }, (error) => {
        console.log(error);
    });
    $scope.demoVar = true;
    $scope.orderIdFunction = () => {
        if ($scope.clinicName != undefined && $scope.clinicName != '') {
            $scope.orderId = 234;
            if ($scope.orderId != undefined && $scope.orderId != '') {
                $scope.name = "Amanora";
                $scope.address = "1234567";
                $scope.city = "Maharashtra";
                $scope.demoVar = false;
            }
        }
        else {
            $scope.orderId = "";
            $scope.name = "";
            $scope.address = "";
            $scope.city = "";
            $scope.demoVar = true;
        }
    }
});

app.controller('registerController', function ($scope, $http, $window) {
    $scope.register = {};
    $scope.formData = () => {
        console.log($scope.register);
        if ($scope.register.confirmPassword === $scope.register.password) {
            document.getElementById("valid").style.display = "block";
            document.getElementById("Invalid").style.display = "none";
        }
        else {
            document.getElementById("Invalid").style.display = "block";
            document.getElementById("valid").style.display = "none";
        }
        if (document.getElementById("valid").style.display === "block") {
            $http({
                method: 'Post',
                url: "http://localhost:7890/api/addUser",
                headers: { 'Content-Type': 'application/json' },
                data: $scope.register
            }).then((response) => {
                $window.location.href = "#!";
                console.log(response.data)
            }, (error) => {
                console.log(error);
            });
        }
    };

});

app.controller('updateController', function ($scope, $http, $routeParams, $window, $rootScope) {
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

// app.controller('clinicSelect', function ($scope, $http, $route) {

//     $http({
//         method: 'get',
//         url: "http://localhost:7890/getClinicNames",
//         headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }
//     }).then((response) => {
//         $scope.clinicNames = response.data;
//     }, (error) => { })

//     $scope.clinicName = (id) => {
//         sessionStorage.setItem("locationId", id);
//         let locationId = sessionStorage.getItem("locationId");
//         console.log(locationId)
//         $http({
//             method: 'get',
//             url: "http://localhost:7890/getPatientByClinic/" + locationId,
//             headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }
//         }).then((response) => {
//             console.log(response)
//             getPatientDetails(response.data);
//         }, (error) => { })

//         $http({
//             method: 'get',
//             url: "http://localhost:7890/getInventoryByClinic/" + locationId,
//             headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }
//         }).then((response) => {
//             getInventoryDetails(response.data);
//         }, (error) => { })

//     }

// });

app.controller('allClinicsUsers', function ($scope, $http, $window) {
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
        if ($scope.updateUserData.password == null) {
            $scope.updateUserData.password = $scope.password;
        }
        $http({
            method: 'put',
            url: "http://localhost:7890/api/editUser/" + $routeParams.param1,
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

app.controller('clinicUserView', function ($scope, $window, $routeParams, $http) {
    $scope.updateUserData = {};
    $http({
        method: 'get',
        url: "http://localhost:7890/api/getUserByUserId/" + $routeParams.param1,
        headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
    }).then((response) => {
        $scope.userDetails = response.data;
    }, (error) => {
        console.log(error);
    })
});




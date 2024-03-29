function reloadWindow() {
    location.reload();
    // var lnk = document.getElementById("themeChange");
    // if (lnk.getAttribute("href") === "css/themecss.css") {
    //     themeIcon.setAttribute("class", "fa-sharp fa-solid fa-moon")
    // } else if (lnk.getAttribute("href") === "css/logincss.css") {
    //     themeIcon.setAttribute("class", "fa-sharp fa-solid fa-sun")
    // }
}
theme = () => {
    var lnk = document.getElementById("themeChange");
    var themeIcon = document.getElementById("themeIcon")
    if (lnk.getAttribute("href") === "css/logincss.css") {
        lnk.setAttribute("href", "css/themecss.css")
        themeIcon.setAttribute("class", "fa-sharp fa-solid fa-moon")

    } else if (lnk.getAttribute("href") === "css/themecss.css") {
        lnk.setAttribute("href", "css/logincss.css")
        themeIcon.setAttribute("class", "fa-sharp fa-solid fa-sun")
    }
    console.log(lnk);
}

let app = angular.module("myApp", ['ngRoute']);

function initKeycloak() {
    var keycloak = new Keycloak();
    document.querySelector("#preloader").style.display = "none";
    keycloak.init({ onLoad: 'login-required' }).then(isAuthenticated => {

        // location.href="http://localhost:5501/#!/"

        console.log(keycloak)
        if (keycloak.hasRealmRole("CLP")) {
            location.href = "#!clp_users";
        }
        else if (keycloak.hasRealmRole("MLP")) {
            location.href = "#!mlp_users";
        }
        else if (keycloak.hasRealmRole("ALP")) {
            location.href = "#!alp_users";
        }
        else if (keycloak.hasRealmRole("ELP")) {
            location.href = "#!elp_users";
        }
        sessionStorage.setItem("token", keycloak.token)
        sessionStorage.setItem("username", keycloak.idTokenParsed.preferred_username)


    }).catch(function () {
        alert('failed to initialize');
    });
}
app.factory('myInterceptor', function ($q) {
    var interceptor = {
        responseError: function (rejection) {
            if (rejection.status === 401) {
                sessionStorage.removeItem("token")
                sessionStorage.removeItem("locationId")
                sessionStorage.removeItem("username")
                window.location.href = 'http://localhost:8080/';
                console.log("Unauthorized To access the page");
            }
        }
    };
    return interceptor;
});

app.controller("headerController", ($scope, $http, $location) => {
    var path = $location.path();
    $scope.administrator = () => {
        let reportingPath = $location.path();
        if (reportingPath === "/administrator") {
            return 'true';
        }
        return 'false';
    }
    switch (path) {
        case '/': $scope.activeTab = 'login';
            break;
        case '/clp_users': $scope.activeTab = 'clpHome';
            break;
        case '/register': $scope.activeTab = 'register';
            break;
        case '/inventory': $scope.activeTab = 'inventory';
            break;
        case '/patient': $scope.activeTab = 'patient';
            break;
        case '/alp_users': $scope.activeTab = 'clinic';
            break;
        case '/clinics': $scope.activeTab = 'clinic';
            break;
        case '/clinicUsers': $scope.activeTab = 'user';
            break;
        case '/ordersInfo': $scope.activeTab = 'orderInfo';
            break;
        case '/transferInventory': $scope.activeTab = 'transferInventory';
            break;
        case '/orders': $scope.activeTab = 'order';
            break;
        case '/transferInventory': $scope.activeTab = 'transferInventory';
            break;
        case '/elp_users': $scope.activeTab = 'successOrders';
            break;
        case '/error_orders': $scope.activeTab = 'errorOrders';
            break;
        case '/shipping': $scope.activeTab = 'shipping';
            break;
        case '/addToInventory': $scope.activeTab = 'clpHome';
            break;
        case '/dispenseToPatient': $scope.activeTab = 'clpHome';
            break;
        case '/administrator': $scope.activeTab = 'administrator';
            break;
        case '/addPhysician': $scope.activeTab = 'administrator';
            break;
        case '/addNurse': $scope.activeTab = 'administrator';
            break;
        case '/reports': $scope.activeTab = 'reports';
            break;
        case '/setup': $scope.activeTab = 'setup';
            break;
        case '/product': $scope.activeTab = 'product';
            break;
        case '/editCorporate': $scope.activeTab = 'setup';
            break;
        case '/addfacility': $scope.activeTab = 'setup';
            break;
        case '/reporting': $scope.activeTab = 'reporting';
            break;
        case '/clinicInventory': $scope.activeTab = 'reporting';
            break;
        case '/injectionIn30': $scope.activeTab = 'patient';
            break;
        case '/serialInfo': $scope.activeTab = 'inventory';
            break;
        case '/removeInventory': $scope.activeTab = 'inventory';
            break;

    }
    if (sessionStorage.getItem("username") !== null) {
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
                $scope.notificationIcon = "d-none";
            }
            else if ($scope.data.role == "ALP") {
                $scope.clp = "d-none";
                $scope.mlp = "d-none";
                $scope.elp = "d-none";
                $scope.login = "d-none";
                $scope.notificationIcon = "d-none";
            }
            else if ($scope.data.role == "ELP") {
                $scope.clp = "d-none";
                $scope.mlp = "d-none";
                $scope.alp = "d-none";
                $scope.login = "d-none";
                $scope.notificationIcon = "d-none";
            }
        })
    }
    else {
        $scope.clp = "d-none";
        $scope.alp = "d-none";
        $scope.elp = "d-none";
        $scope.userIcon = "d-none";
        $scope.notificationIcon = "d-none";
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
        .when("/elp_users", {
            templateUrl: "view/elp_users.html"
        })
        .when("/error_orders", {
            templateUrl: "view/error_orders.html"
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
        .when('/transferInventory',
            {
                templateUrl: "view/transferInventory.html"
            })
        .when('/removeInventory',
            {
                templateUrl: "view/removeInventory.html"
            })
        .when('/home',
            {
                templateUrl: "view/home.html"
            })
        .when('/shipping',
            {
                templateUrl: "view/shipping.html"
            })
        .when('/setup',
            {
                templateUrl: "view/setup.html"
            })
        .when("/productInformation/:param1",
            {
                templateUrl: "view/productInformation.html"
            })
        .when('/product',
            {
                templateUrl: "view/product.html"
            })
        .when('/reporting',
            {
                templateUrl: "view/reporting.html"
            })
        .when('/clinicInventory',
            {
                templateUrl: "view/clinicInventory.html"
            })

        .when('/editCorporate',
            {
                templateUrl: "view/editCorporate.html"
            })
        .when('/addfacility',
            {
                templateUrl: "view/addfacility.html"
            })
        .when("/editfacility/:param1",
            {
                templateUrl: "view/editFacility.html"
            })

        .when('/addUserSetup',
            {
                templateUrl: "view/addUserSetup.html"
            })
        .when("/editUserSetup/:param1",
            {
                templateUrl: "view/editUserSetup.html"
            })
        .when('/addToInventory',
            {
                templateUrl: "view/addToInventory.html"
            })
        .when('/dispenseToPatient/:param1',
            {
                templateUrl: "view/dispenseToPatient.html"
            })
        .when('/addPhysician',
            {
                templateUrl: "view/addPhysician.html"
            })
        .when('/addNurse',
            {
                templateUrl: "view/addNurse.html"
            })
        .when('/administrator',
            {
                templateUrl: "view/administrator.html"
            })
        .when('/reports',
            {
                templateUrl: "view/reports.html"
            })
        .when('/injectionIn30',
            {
                templateUrl: "view/injectionIn30Days.html"
            })
        .when('/serialInfo',
            {
                templateUrl: "view/serialInfo.html"
            });
    // $httpProvider.interceptors.push('myInterceptor');

});

// app.controller("loginCtrl", ($scope, $http, $window,) => {

    // $scope.getRequest = (v) => {
    //     console.log($scope.submit)
    //     $http({
    //         method: 'POST',
    //         url: 'http://localhost:7890/login',
    //         data: $scope.submit,
    //         headers: { 'Content-Type': 'application/json', 'Authorization': 'qweryui' }
    //     }).then((response) => {
    //         $scope.data = response.data;
    //         console.log($scope.data)
    //         console.log(response.data)
    //         sessionStorage.setItem("token", "Bearer " + sessionStorage.getItem("token"))
    //         if (sessionStorage.getItem("username")!=null) {
    //             $http({
    //                 method: 'GET',
    //                 url: 'http://localhost:7890/getEnterpriseIdByUsername/' + $scope.submit.username,
    //                 headers: { 'Authorization': sessionStorage.getItem("token") }
    //             }).then((response) => {
    //                 $scope.dataEnterprise = response.data;
    //                 sessionStorage.setItem("EnterpriseId", $scope.dataEnterprise.enterpriseId)

    //             })
    //             $http({
    //                 method: 'GET',
    //                 url: 'http://localhost:7890/api/get/' + $scope.submit.username,
    //                 headers: { 'Authorization': sessionStorage.getItem("token") }
    //             }).then((response) => {
    //                 $scope.data = response.data;
    //                 if ($scope.data.role == "CLP") {
    //                     $window.location.href = "#!clp_users";
    //                 }
    //                 else if ($scope.data.role == "MLP") {
    //                     $window.location.href = "#!mlp_users";
    //                 }
    //                 else if ($scope.data.role == "ALP") {
    //                     $window.location.href = "#!alp_users";
    //                 }
    //                 else if ($scope.data.role == "ELP") {
    //                     $window.location.href = "#!elp_users";
    //                 }
    //             })
    //         }
    //     }, (error) => {
    //         alert("Wrong User Id Or Password");
    //     })
    // }
// });

app.controller("clp", function ($scope, $http, $window, $location,$routeParams) {
    $scope.dispance = {}
    $scope.dispancePatientDetials = (x) => {
        $scope.dispance.patientId = x.id;
        $scope.dispensePatientData = {};
        $scope.dispensePatientData.firstName = x.patientFirstName;
        $scope.dispensePatientData.lastName = x.patientLastName;
        $scope.dispensePatientData.middleName = x.patientMiddleName;
    }
    $scope.dispancePhysicianDetials = (x) => {
        $scope.dispance.physicianId = x.id;
        $scope.dispensePhysicianData = {};
        $scope.dispensePhysicianData.firstName = x.firstName;
        $scope.dispensePhysicianData.lastName = x.lastName;
    }
    $scope.dispanceNurseDetials = (x) => {
        $scope.dispance.nurseId = x.id;
        $scope.dispenseNurseData = {};
        $scope.dispenseNurseData.firstName = x.firstName;
        $scope.dispenseNurseData.lastName = x.lastName;


    }
    // $scope.gettingClinicId
    $scope.gettingTransferSerialNo
    
    $scope.getTransitDoses = (productId) => {
        $http({
            method: 'get',
            url: 'http://localhost:7890/getTransitDoses/' + productId + '/' + sessionStorage.getItem("locationId"),
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            },
        }).then((response) => {
            console.log("HELLO")
            $scope.transitDoseDetails = response.data;
            console.log(response.data)
            console.error(response.data)
        })
    }

    $scope.transferDose = () => {
        let gettingTransferSerialNo = $scope.serialNumberValue
        let splitter = gettingTransferSerialNo.split("-")
        console.log(splitter);
        $http({
            method: 'POST',
            url: 'http://localhost:7890/transferDose/' + splitter[0] + "/" + $scope.gettingClinicId,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            },
        }).then((response) => {
            console.log($scope.gettingClinicId, $scope.gettingTransferSerialNo);
            alert("Transfered Dose Successfully");
            $window.location.reload();
        })
        // console.log($scope.gettingClinicId)

    }
    $http({
        method: 'GET',
        url: 'http://localhost:7890/getAllDispenseNext30Days/' + sessionStorage.getItem("locationId"),
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        },
    }).then((response) => {
        console.log("countNoOfPatients")
        $scope.injectionIn30Days = response.data;
    })

    $scope.removeSerial=()=>{
        let gettingTransferSerialNo = $scope.serialNumberValue
        let splitter = gettingTransferSerialNo.split("-")
        console.log(splitter);
        $http({
            method: 'POST',
            url: 'http://localhost:7890/removeSerial/' + splitter[0],
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            },  
    }).then((response) => {
        alert("Removed Successfully");
    })
    }

    $scope.dispenseToPatient = () => {
        console.log($scope.dispance)
        $http({
            method: 'POST',
            url: 'http://localhost:7890/createDispense/' + sessionStorage.getItem("username"),
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            },
            data: $scope.dispance

        }).then((response) => {
            alert("Dispensed Successfully");


        })
    }
    $scope.closedispenseTable = false;
    $scope.closeTable = () => {
        $scope.closedispenseTable = true;
    }

    $scope.viewTable = () => {
        $scope.closedispenseTable = false;


    }
    $scope.serialDataFunction = () => {
        let gettingTransferSerialNo = $scope.serialNumberValue
        let splitter = gettingTransferSerialNo.split("-")
        console.log(splitter);
        if ($scope.serialNumberValue != null) {
            $http({
                method: 'GET',
                url: 'http://localhost:7890/getSerialBySerialNo/' + splitter[0] + "/" + sessionStorage.getItem("locationId"),
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': sessionStorage.getItem("token")
                }
            }).then((response) => {
                $scope.dispenseSerial

            }), (error) => {
                console.log(error);
            };

            $http({
                method: 'GET',
                url: 'http://localhost:7890/getProductBySerialNo/' + splitter[0] ,
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': sessionStorage.getItem("token")
                }
            }).then((response) => {
                $scope.serialData = response.data;
                console.log($scope.serialData)
                console.log("Dispense")
                console.error("demo")
                $scope.dispance.productId = $scope.serialData.productId;
                $scope.dispance.locationId = $scope.serialData.locationId;
                $scope.dispance.enterpriseId = $scope.serialData.enterpriseId;
                $scope.dispance.serialId = $scope.serialData.serialId;

                if ($scope.serialData.productId != null) {

                    $http({
                        method: 'GET',
                        url: 'http://localhost:7890/getDoseName/' + $scope.serialData.productId + "/" + splitter[0] ,
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': sessionStorage.getItem("token")
                        }
                    }).then((response) => {

                        $scope.serialDespense = response.data
                        console.log(response.data)

                    }), (error) => {
                        console.log(error);
                    };



                    console.log($scope.serialData.patientSpecific);
                    $http({
                        method: 'GET',
                        url: "http://localhost:7890/getPatientByPatientId/" + $scope.serialData.patientSpecific,
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': sessionStorage.getItem("token")
                        }
                    }).then((response) => {
                        console.log("GetCALL")
                        console.log(response.data);
                        $scope.patientSpecificdata = response.data;
                        $scope.dispance.patientId = $scope.patientSpecificdata.id
                    }, (error) => {
                        console.log(error);
                    });


                }
                $http({
                    method: 'GET',
                    url: "http://localhost:7890/getAllPhysicians",
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': sessionStorage.getItem("token")
                    }
                }).then((response) => {
                    console.log(response.data);
                    $scope.physician_data = response.data;
                }, (error) => {
                    console.log(error);
                });



                $http({
                    method: 'GET',
                    url: "http://localhost:7890/getAllNurse",
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': sessionStorage.getItem("token")
                    }
                }).then((response) => {
                    console.log(response.data);
                    $scope.nurse_data = response.data;
                }, (error) => {
                    console.log(error);
                });

            }, (error) => {
                console.log(error)
            })


        }
    }
    $scope.dispanceValueSetter = (x) => {
        console.log(x);
    }
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

                check = true
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
        // $window.location.reload();
        console.log($scope.clinicNames);
    }, (error) => { })
    
    $http({
        method: 'get',
        url: "http://localhost:7890/getClinicNames",
        headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }
    }).then((response) => {
        $scope.clinictransferNames = response.data;
        // $window.location.reload();
        console.log("TransferNaMe")
        console.log($scope.clinictransferNames);
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
            // $window.location.reload();
        }
        else if (sessionStorage.getItem("screensName") == "transfer") {
            $window.location.href = "#!/transferInventory";
        }
        else if (sessionStorage.getItem("screensName") == "serialInfo") {
            $window.location.href = "#!/serialInfo";
        }
        else if (sessionStorage.getItem("screensName") == "removeInventory") {
            $window.location.href = "#!/removeInventory";
        }
    }
    $scope.inventoryLocation = () => {
        let allPath = $location.path();

        if (allPath === "/inventory" || allPath === "/orders" || allPath === "/transferInventory" || allPath=== "/serialInfo" || allPath==="/removeInventory") {
            return true;
        }
        return false;
    }


    $scope.reportName = (id) => {
        sessionStorage.setItem("reportName", id);
        if (sessionStorage.getItem("reportName") == "addPatient") {
            $window.location.href = "#!/insertPatient";
        }
        else if (sessionStorage.getItem("reportName") == "addPhysician") {
            $window.location.href = "#!addPhysician";
        }
        else if (sessionStorage.getItem("reportName") == "addNurse") {
            $window.location.href = "#!addNurse";
        }
        else if (sessionStorage.getItem("reportName") == "orders") {
            $window.location.href = "#!orders";
        }
    }
    $scope.reportLocation = () => {
        let thePath = $location.path();
        if (thePath === "/reports") {
            return true;
        }
        return false;
    }
    $scope.linkData = {};
    $scope.saveLinks = () => {
        $http({
            method: 'post',
            url: "http://localhost:7890/addLink",
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
            data: $scope.linkData
        }).then((response) => {
            $window.location.reload()

        }, (error) => { })
    }
    $http({
        method: 'get',
        url: "http://localhost:7890/getAllQuickLink",
        headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },

    }).then((response) => {
        $scope.getLinksData = response.data;
        console.log(response)

    }, (error) => { })

    $scope.deleteLinks = (id) => {
        $http({
            method: 'delete',
            url: "http://localhost:7890/deleteQucikLink/" + id,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => { $window.location.reload() }, (error) => { })
    }
    $scope.updateLinkData = {};
    $scope.updateLinks = () => {
        $http({
            method: 'put',
            url: "http://localhost:7890/updateQuickLink",
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
            data: $scope.updateLinkData
        }).then((response) => {
            $window.location.reload()

        }, (error) => { })
    }

    $scope.getQuickLinkData = (id) => {
        console.log()
        $http({
            method: 'get',
            url: "http://localhost:7890/getQuickLink/" + id,
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }
        }).then((response) => {
            $scope.updateLinkData = response.data;
            console.log(response);

        }, (error) => { })

    }
    $http({
        method: 'GET',
        url: 'http://localhost:7890/getAllProduct',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        },
    }).then((response) => {
        console.log("Before")
        $scope.product_data = [...response.data];
        console.log($scope.product_data)

    }, (error) => {
        console.log(error);
    });

    //Minimum Days
    $http({
        method: 'GET',
        url: 'http://localhost:7890/getAllProduct',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        },


    }).then((response) => {
        console.log("Before")

        $scope.product_data1 = [...response.data];
    }, (error) => {
        console.log(error);
    });

    $scope.editMinimumDays = () => {
        $http({
            method: 'PUT',
            url: 'http://localhost:7890/editProduct',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            },
            data: $scope.product_data1

        }).then((response) => {
            console.log("update")
            console.log($scope.product_data1);
        },
            (error) => {

            })

    }



    $http({
        method: 'GET',
        url: "http://localhost:7890/getAllDispensedDose",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        console.log("Bye");
        console.log(response.data);
        $scope.dispenseDoseTable = response.data;
    }, (error) => {
        console.log(error);
    });

    $scope.onOff = true;
    $scope.addMonths = [];
    $scope.marchMonth;

    $http({
        method: 'GET',
        url: "http://localhost:7890/getAutoReorderDose",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        console.log("Auto");
        console.log(response.data);
        $scope.autoTableData = [...response.data];
    }, (error) => {
        console.log(error);
    });

    function autoTableData() {
        $http({
            method: 'GET',
            url: "http://localhost:7890/getAutoReorderDose",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => {
            console.log("Auto");
            console.log(response.data);
            $scope.autoTableData = [...response.data];
        }, (error) => {
            console.log(error);
        });
    }
    $http({
        method: 'GET',
        url: "http://localhost:7890/getAutoReorderDose",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        console.log("Autoedit");
        console.log(response.data);
        $scope.autoTableData1 = [...response.data];
    }, (error) => {
        console.log(error);
    });

    $scope.autoTableUpdate = () => {
        $http({
            method: 'PUT',
            url: 'http://localhost:7890/updateAutoReorder',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            },
            data: $scope.autoTableData1

        }).then((response) => {
            console.log("updateAutoTable")
            console.log($scope.autoTableData1);
            autoTableData();
            // $window.location.reload();       
        },
            (error) => {

            })

    }

    $http({
        method: 'GET',
        url: "http://localhost:7890/getManualReorder",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        console.log("Manual");
        console.log(response.data);
        $scope.manualReorder = [...response.data];
    }, (error) => {
        console.log(error);
    });

    $http({
        method: 'GET',
        url: "http://localhost:7890/getManualReorder",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        console.log("Manualedit");
        console.log(response.data);
        $scope.manualReorder1 = [...response.data];
    }, (error) => {
        console.log(error);
    });

    $scope.manualTableUpdate = () => {
        $http({
            method: 'PUT',
            url: 'http://localhost:7890/updateManualReorder',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            },
            data: $scope.manualReorder1

        }).then((response) => {
            console.log("updateManualTable")
            console.log($scope.manualReorder1);
            manualTableData()
            // $window.location.reload();

        },
            (error) => {

            })

    }
    function manualTableData() {
        $http({
            method: 'GET',
            url: "http://localhost:7890/getManualReorder",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => {
            console.log("Manual");
            console.log(response.data);
            $scope.manualReorder = [...response.data];
        }, (error) => {
            console.log(error);
        });
    }

    $http({
        method: 'GET',
        url: "http://localhost:7890/getAllNotifications",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        console.log("Notification");
        $scope.notificationData = [...response.data];
        console.log(response.data);
    }, (error) => {
        console.log(error);
    });

    $scope.deleteNotifications = () => {
        $http({
            method: 'DELETE',
            url: "http://localhost:7890/deleteAllNotifications",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => { }, (error) => { })
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
            console.log("Patients");
            console.log(response.data);
        }, (error) => { })
        $http({
            method: 'get',
            url: "http://localhost:7890/getInventoryByClinic/" + sessionStorage.getItem("locationId"),
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }
        }).then((response) => {
            getInventoryDetails(response.data);
        }, (error) => { })
        $http({
            method: 'get',
            url: "http://localhost:7890/shippedInventoryDetails/" + sessionStorage.getItem("locationId"),
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
        }).then((response) => {
            $scope.shippedDetails = response.data;
        }, (error) => {
            console.log(error);
        })

        $http({
            method: 'GET',
            url: "http://localhost:7890/getAllDispense/" + sessionStorage.getItem("locationId") + "/" + sessionStorage.getItem("username"),
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => {
            console.log("Hello Dispense");
            console.log(response.data);
            $scope.dispenseShow = response.data;
        }, (error) => {
            console.log(error);
        })

        $scope.shippedSerial = () => {
            if ($scope.demodata != null && sessionStorage.getItem("locationId") != null) {
                $http({
                    method: 'get',
                    url: "http://localhost:7890/getSerialBySerialId/" + $scope.demodata + "/" + sessionStorage.getItem("locationId"),
                    headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
                }).then((response) => {
                    console.log("AddToInventory")
                    $scope.shippedSerialDetails = response.data;
                    console.log($scope.shippedSerialDetails)
                }, (error) => {
                    console.log(error);
                })
            }
            else {
                $scope.shippedSerialDetails = null;
            }
        }
    }

    $scope.getOnHand = (id) => {
        $http({
            method: 'GET',
            url: "http://localhost:7890/getSerialNumber/" + id + "/" + sessionStorage.getItem("locationId"),
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => {
            $scope.onHandData = response.data;
            console.log("onhand")
            console.log($scope.onHandData)
        }, (error) => {
            console.log(error)
        })
    }
    $scope.getExpired = (id) => {
        $http({
            method: 'GET',
            url: "http://localhost:7890/getExpiredSerialDetails/" + id + "/" + sessionStorage.getItem("locationId"),
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

    $http({
        method: 'GET',
        url: "http://localhost:7890/getAllPhysicians",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        console.log(response.data);
        $scope.physician_data = response.data;
    }, (error) => {
        console.log(error);
    });

    $http({
        method: 'GET',
        url: "http://localhost:7890/getAllNurse",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        console.log(response.data);
        $scope.nurse_data = response.data;
    }, (error) => {
        console.log(error);
    });


    getPatientDetails = (data) => {
        $scope.patientData = data;
        for (let index = 0; index < $scope.patientData.length; index++) {
            localStorage.setItem("locationId" + $scope.patientData[index].id, $scope.patientData[index].patientLocationId);
        }

    }
    getInventoryDetails = (data) => {
        $scope.invetoryData = data;
        console.warn($scope.invetoryData);
    };

    $scope.inventoryStatusAvailable = () => {
        $scope.shippedSerialDetails;
        $http({
            method: 'POST',
            url: 'http://localhost:7890/changeStatusAvailable/' + $scope.shippedSerialDetails.serialId + "/" + $scope.shippedSerialDetails.locationId + "/" + $scope.dispance.patientId + "/" + sessionStorage.getItem("username"),
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => {
            console.log("Add to Inventory Updated")
            alert("Status Changed Successfully");
            $window.location.reload();

        }, (error) => {
            console.log(error);
        });
    }
    //  console.log($scope.dispance.patientId)
    //  console.log($scope.dispance)
    // $scope.patientSpecificdata.patientSpecific= $scope.dispance.patientId


    $http({
        method: 'get',

        url: "http://localhost:7890/getClinicNames",

        headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }

    }).then((response) => {

        $scope.clinictransferNames = response.data;

        // $window.location.reload();

        console.log("TransferNaMe")

        console.log($scope.clinictransferNames);

    }, (error) => { })
    $scope.serialNumberValue;
    $scope.getSerialNumbers = () => {
        $http({

            method: 'GET',

            url: "http://localhost:7890/getSerialReceivedByLocationId" + "/" + sessionStorage.getItem("locationId"),

            headers: {

                'Content-Type': 'application/json',

                'Authorization': sessionStorage.getItem("token")

            }

        }).then((response) => {
            
            console.log("Transfer");
            $scope.validSerial=true;
            // console.log(response);
            let gettingTransferSerialNo = $scope.serialNumberValue
            let splitter = gettingTransferSerialNo.split("-")
            $scope.serialNumber=splitter[0];
            $scope.serialDetailsClp();
            
            $scope.serialNo = response.data;
            console.log($scope.serialNo)

        }, (error) => {

            console.log(error);

        });

    }

    $scope.serialDetailsClp= () => {

    $http({

        method: 'GET',

        url: "http://localhost:7890/getSerialDetailsBySerialNo/" + $scope.serialNumber,

        headers: {

            'Content-Type': 'application/json',

            'Authorization': sessionStorage.getItem("token")

        }

    }).then((response) => {

        console.log("Serialdetails");
        $scope.serialDetails=response.data;
        console.log(response.data);

    }, (error) => {

        console.log(error);

    });
    }
    $scope.patientDetails=null
  if ($location.path()==="/dispenseToPatient/"+$routeParams.param1 && $routeParams.param1!==null){
    $http({
        method: 'GET',
        url: "http://localhost:7890/getPatientById/" + $routeParams.param1,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        $scope.patientDetails=response.data
        console.warn(response.data)
    }, (error) => {

    });
}

    









});

app.controller("alp", ($scope, $http, $window, $location, $routeParams) => {
    // clinic controller
    if (sessionStorage.getItem("username") != null) {
        $http({
            method: 'GET',
            url: 'http://localhost:7890/getEnterpriseIdByUsername/' + sessionStorage.getItem("username"),
            headers: { 'Authorization': sessionStorage.getItem("token") }
        }).then((response) => {
            $scope.dataEnterprise = response.data;
            sessionStorage.setItem("EnterpriseId", $scope.dataEnterprise.enterpriseId)

        })
    }
    $http({
        method: 'GET',
        url: 'http://localhost:7890/getAllClinic',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        console.log("CLinic")
        console.log(response.data);
        $scope.clinic_data = response.data;
    }, (error) => {
        console.log(error);
    });

    $scope.closeDropDown = false;
    $scope.downloadfiles = () => {
        $scope.closeDropDown = true;
    }

    $scope.options = [
        { label: 'XLS', value: 'option1', url: 'http://localhost:7890/download/excel' },
        { label: 'PDF', value: 'option2', url: 'http://localhost:7890/download/pdf' },

    ];
    $scope.selectedOption = '';
    $scope.openUrl = function () {
        const selectedOption = $scope.options.find(o => o.value === $scope.selectedOption);
        if (selectedOption) {
            window.open(selectedOption.url);
        }
    };
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
        console.log("OrderALP")
        $scope.orderData = response.data;
        console.log(response)
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


    $scope.setUpLocation = () => {
        let setUpPath = $location.path();
        if (setUpPath === "/setup" || setUpPath === "/product") {
            return true;
        }
        return false;
    }
    $scope.setupDropdownTab = sessionStorage.getItem("setupName");
    $scope.setupName = (id) => {
        sessionStorage.setItem("setupName", id);
        if (sessionStorage.getItem("setupName") == "setup") {
            $window.location.href = "#!/setup";
        }
        else if (sessionStorage.getItem("setupName") == "product") {
            $window.location.href = "#!/product";

        }
    }

    $http({
        method: 'GET',
        url: 'http://localhost:7890/getAllProduct',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        },
    }).then((response) => {
        console.log("Product Dropdown")
        $scope.productAlp = response.data;
        console.log($scope.productAlp)

    }, (error) => {
        console.log(error);
    });

    $http({
        method: 'GET',
        url: 'http://localhost:7890/getByEnterpriseId/' + sessionStorage.getItem("EnterpriseId"),
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        },
    }).then((response) => {
        console.log("Enterprise")
        $scope.enterpriseData = response.data;
        console.log($scope.enterpriseData)

    }, (error) => {
        console.log(error);
    });


    $scope.enterpriseUpdate = () => {
        $http({
            method: 'PUT',
            url: 'http://localhost:7890/updateEnterprise',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            },
            data: $scope.enterpriseData

        }).then((response) => {
            console.log("updateEnterprise Table");
            console.log($scope.enterpriseData);
            // alert("Enterprise Data Added Successfully");
            // $window.location.reload(); 
        },
            (error) => {
            })
    }


    //Facility ApI

    $scope.facilityAddData = {};

    $scope.saveFacility = () => {
        $scope.facilityAddData.enterpriseId = sessionStorage.getItem("EnterpriseId");
        $http({
            method: 'post',
            url: "http://localhost:7890/addFacility/" + sessionStorage.getItem("EnterpriseId"),
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
            data: $scope.facilityAddData
        }).then((response) => {
            alert("Facility Added Successfully")
            $window.location.href = "#!/setup"

        }, (error) => { })
    }
    $http({
        method: 'GET',
        url: 'http://localhost:7890/getAllFacilityByEnterpriseId/' + sessionStorage.getItem("EnterpriseId"),
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        },
    }).then((response) => {
        console.log("Facility")
        $scope.facilitydata = response.data;
        console.log($scope.facilitydata)

    }, (error) => {
        console.log(error);
    });
    $scope.deletefacility = (facilityId) => {
        $http({
            method: 'POST',
            url: "http://localhost:7890/deleteFacility/" + facilityId,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => { $window.location.reload() }, (error) => { })
    }
    //user
    $http({
        method: 'GET',
        url: 'http://localhost:7890/getAllUsersByEnterpriseId/' + sessionStorage.getItem("EnterpriseId"),
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        },
    }).then((response) => {
        console.log("UserData")
        $scope.userDataSetup = response.data;
        console.log($scope.userDataSetup)

    }, (error) => {
        console.log(error);
    });


    $scope.userAddData = {};

    $scope.saveUser = () => {
        $scope.userAddData.enterpriseId = sessionStorage.getItem("EnterpriseId");
        if ($scope.userAddData.confirmPassword === $scope.userAddData.password) {
            document.getElementById("valid").style.display = "block";
            document.getElementById("Invalid").style.display = "none";
        }
        else {
            document.getElementById("Invalid").style.display = "block";
            document.getElementById("valid").style.display = "none";
        }
        if (document.getElementById("valid").style.display === "block") {
            $http({
                method: 'post',
                url: "http://localhost:7890/api/addUser",
                headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
                data: $scope.userAddData
            }).then((response) => {
                alert("User Added Successfully")
                $window.location.href = "#!/setup"
            }, (error) => { })
        }
    }

    $scope.deleteuser = (id) => {
        $http({
            method: 'POST',
            url: "http://localhost:7890/api/deleteById/" + id,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => { $window.location.reload() }, (error) => { })
    }


    // Location Reporting


    // $scope.reportName = (id) => {
    //     sessionStorage.setItem("reportName", id);
    //     if (sessionStorage.getItem("reportName") == "addPatient") {
    //         $window.location.href = "#!/insertPatient";
    //     }
    //     else if (sessionStorage.getItem("reportName") == "addPhysician") {
    //         $window.location.href = "#!addPhysician";
    //     }
    //     else if (sessionStorage.getItem("reportName") == "addNurse") {
    //         $window.location.href = "#!addNurse";
    //     }
    //     else if (sessionStorage.getItem("reportName") == "orders") {
    //         $window.location.href = "#!orders";
    //     }
    // }
    // $scope.reportLocation = () => {
    //     let thePath = $location.path();
    //     if (thePath === "/reports") {
    //         return true;
    //     }
    //     return false;
    // }
    $scope.reportingLocation = () => {
        let reportingPath = $location.path();
        if (reportingPath === "/reporting" || reportingPath === "/clinicInventory") {
            return true;
        }
        return false;
    }
    $scope.reportingDropdownTab = sessionStorage.getItem("reportingName");
    $scope.reportingName = (id) => {

        if (id == "") {
            sessionStorage.setItem("reportingName", id);
            $window.location.href = "#!/reporting";


        }
        else if (id == "clinicInventory") {
            sessionStorage.setItem("reportingName", id);
            $window.location.href = "#!/clinicInventory";


        }
        else {
            sessionStorage.removeItem("reportingName");

        }

    }
    $scope.id = sessionStorage.getItem("locationId");
    $http({
        method: 'get',
        url: "http://localhost:7890/getClinicNames",
        headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }
    }).then((response) => {
        $scope.clinicNames = response.data;

        // $window.location.reload();
        console.log($scope.clinicNames);
    }, (error) => { })

    $scope.clinicName = (id) => {
        sessionStorage.setItem("locationId", id);
        $window.location.reload();
    }

    if ($scope.id != null) {
        $http({
            method: 'get',
            url: "http://localhost:7890/getInventoryByClinic/" + sessionStorage.getItem("locationId"),
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }
        }).then((response) => {
            getInventoryDetails(response.data);
        }, (error) => { })


    }

    getInventoryDetails = (data) => {
        $scope.invetoryData = data;
        console.warn($scope.invetoryData);
    };



    $scope.downloadLocation = sessionStorage.getItem("locationId");

    $scope.getOnHand = (id) => {
        $http({
            method: 'GET',
            url: "http://localhost:7890/getSerialNumber/" + id + "/" + sessionStorage.getItem("locationId"),
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => {
            $scope.onHandData = response.data;
            console.log("onhand")
            console.log($scope.onHandData)
        }, (error) => {
            console.log(error)
        })
    }
    $scope.getExpired = (id) => {
        $http({
            method: 'GET',
            url: "http://localhost:7890/getExpiredSerialDetails/" + id + "/" + sessionStorage.getItem("locationId"),
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => {
            console.log("Expired")
            $scope.expiredData = response.data;
            console.log($scope.expiredData)

        }, (error) => {
            console.log(error)
        })
    }




});

app.controller('updateUserSetupController', function ($scope, $http, $routeParams, $window) {


    $scope.setUpLocation = () => {
        let setUpPath = $location.path();
        if (setUpPath === "/setup" || setUpPath === "/product") {
            return true;
        }
        return false;
    }
    $scope.setupDropdownTab = sessionStorage.getItem("setupName");
    $scope.setupName = (id) => {
        sessionStorage.setItem("setupName", id);
        if (sessionStorage.getItem("setupName") == "setup") {
            $window.location.href = "#!/setup";
        }
        else if (sessionStorage.getItem("setupName") == "product") {
            $window.location.href = "#!/product";

        }
    }


    $http({
        method: 'get',
        url: "http://localhost:7890/api/getUserById/" + $routeParams.param1,
        headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }
    }).then((response) => {

        console.log("User Update")
        console.log($routeParams.param1)
        $scope.userdataUpdate = response.data;
        console.log($scope.userdataUpdate);

    }, (error) => { })

    $scope.saveUserSetup = () => {
        delete $scope.userdataUpdate.authorities;
        if ($scope.userdataUpdate.confirmPassword === $scope.userdataUpdate.password) {
            document.getElementById("valid").style.display = "block";
            document.getElementById("Invalid").style.display = "none";
        }
        else {
            document.getElementById("Invalid").style.display = "block";
            document.getElementById("valid").style.display = "none";
        }
        if (document.getElementById("valid").style.display === "block") {
            $http({
                method: 'put',
                url: "http://localhost:7890/api/editUser",
                headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
                data: $scope.userdataUpdate
            }).then((response) => {
                console.log("Hello")
                console.log($scope.userdataUpdate)
                $window.location.href = "#!setup";


            }, (error) => { })
        }
    }
});


app.controller('updateFacilityController', function ($scope, $http, $routeParams, $window) {

    $scope.setUpLocation = () => {
        let setUpPath = $location.path();
        if (setUpPath === "/setup" || setUpPath === "/product") {
            return true;
        }
        return false;
    }
    $scope.setupDropdownTab = sessionStorage.getItem("setupName");
    $scope.setupName = (id) => {
        sessionStorage.setItem("setupName", id);
        if (sessionStorage.getItem("setupName") == "setup") {
            $window.location.href = "#!/setup";
        }
        else if (sessionStorage.getItem("setupName") == "product") {
            $window.location.href = "#!/product";

        }
    }




    $http({
        method: 'get',
        url: "http://localhost:7890/getFacilityById/" + $routeParams.param1,
        headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }
    }).then((response) => {
        console.log("Facility Update")
        console.log($routeParams.param1)
        $scope.facilityUpdateData = response.data;
        console.log($scope.facilityUpdateData);

    }, (error) => { })

    $scope.UpdateFacility = () => {
        $http({
            method: 'put',
            url: "http://localhost:7890/editFacility",
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
            data: $scope.facilityUpdateData
        }).then((response) => {
            console.log("Hello")
            $window.location.href = "#!setup"

        }, (error) => { })
    }




});

app.controller('updateProductController', function ($scope, $http, $routeParams, $window) {



    $scope.setUpLocation = () => {
        let setUpPath = $location.path();
        if (setUpPath === "/setup" || setUpPath === "/product") {
            return true;
        }
        return false;
    }
    $scope.setupDropdownTab = sessionStorage.getItem("setupName");
    $scope.setupName = (id) => {
        sessionStorage.setItem("setupName", id);
        if (sessionStorage.getItem("setupName") == "setup") {
            $window.location.href = "#!/setup";
        }
        else if (sessionStorage.getItem("setupName") == "product") {
            $window.location.href = "#!/product";

        }
    }



    $http({
        method: 'GET',
        url: "http://localhost:7890/getProductById/" + $routeParams.param1,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        console.log("productSetup")
        $scope.productSetup = response.data;
        console.log($scope.productSetup)
        console.log(response)

    }, (error) => {
        console.log(error);
    });

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

            let clinicNameAndLocation = $scope.clinicNameAndLocation;




            $http({

                method: 'Get',

                url: "http://localhost:7890/getShippingDataByShippingId/" + clinicNameAndLocation,

                headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }

            }).then((response) => {

                $scope.clinicShipToData = response.data;

                console.log($scope.clinicShipToData == "")

                if ($scope.clinicShipToData != "") {

                    let nameAndLocation = clinicNameAndLocation.split("_");
                    console.log(nameAndLocation);
                    $http({

                        method: 'Get',

                        url: "http://localhost:7890/getprocessedorderEvents/" + nameAndLocation[1],

                        headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }

                    }).then((response) => {

                        $scope.orderEventData = response.data;

                        if ($scope.productIdAndOrderEventId != undefined) {

                            var productIdAndOrderEventId = $scope.productIdAndOrderEventId;

                            // var productIdOrderEventId = productIdAndOrderEventId.split(",");

                            $scope.orderEventId = $scope.productIdAndOrderEventId;

                            $scope.demoVar = false;

                            $http({

                                method: 'Get',

                                url: "http://localhost:7890/getserialbyproductId/" + $scope.productIdAndOrderEventId,

                                headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }

                            }).then((response) => {

                                $scope.serialId = response.data;

                                console.log($scope.serialId)

                            }, (error) => {

                                console.log(error);

                            });

                        }

                        else {

                            $scope.demoVar = true;

                        }

                    }, (error) => {

                        console.log(error);

                    });

                }

            }, (error) => {

                console.log(error);

            });

        }

    }, (error) => {

        console.log(error);

    });




    $scope.shipOrder = () => {

        $http({

            method: 'Get',

            url: "http://localhost:7890/changeSerialAndOrderStatusToShipped/" + $scope.orderEventId,

            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }

        }).then((response) => {

            $scope.shipmentDetails = response.data;

            alert("Ordered Shipped");

            $window.location.href = "#!/shipping";

        }, (error) => {
            console.log(error);

        });

    }

    $scope.demoFunction = () => {

        if ($scope.scanShipmentDetails != undefined) {

            console.log($scope.scanShipmentDetails)

            let scanShipmentDetails = $scope.scanShipmentDetails;

            let serialIdAndProductId = scanShipmentDetails.split(",");

            console.log($scope.orderEventId);

            $http({

                method: 'Get',

                url: "http://localhost:7890/scannedShipmentDetails/" + serialIdAndProductId[0] + "/" + serialIdAndProductId[1] + "/" + $scope.orderEventId,

                headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }

            }).then((response) => {

                $scope.shipmentDetails = response.data;

                console.log($scope.shipmentDetails)

            }, (error) => {

                console.log(error);

            });

        }

    }

    $scope.demoVar = true;

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
    $scope.deleteById = (id) => {
        $http({
            method: 'delete',
            url: "http://localhost:7890/deletePatient/" + id,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => {$window.location.href="#!patient"
           console.log(response) 
    }, (error) => { })
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
        console.log("Kiet")
        console.log(response.data)
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
    $http({

        method: 'get',

        url: "http://localhost:7890/getClinicNames",

        headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }

    }).then((response) => {

        $scope.clinicNames = response.data;

    }, (error) => { })


    $scope.id = sessionStorage.getItem("locationId");


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
        let myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");
        myHeaders.append("Authorization", sessionStorage.getItem("token"));
        myHeaders.append("Access-Control-Allow-Origin", "*")
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

app.controller('addToInventory', function ($scope, $http) {
    // $scope.id = sessionStorage.getItem("locationId");

});

app.controller("addPhysicianNurseCtrl", function ($scope, $http, $window, $route) {
    $http({

        method: 'get',

        url: "http://localhost:7890/getClinicNames",

        headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }

    }).then((response) => {

        $scope.clinicNames = response.data;

    }, (error) => { })
    $scope.clinicName = (id) => {

        sessionStorage.setItem("locationId", id);

        // $window.location.reload();

    }

    $scope.id = sessionStorage.getItem("locationId");
    $scope.addPhysician = function () {
        $http({
            method: 'POST',
            url: "http://localhost:7890/createPhysician",
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
            data: $scope.addPhysicianData
        }).then((response) => {
            alert("Physicians Added Successfully");
            $window.location.href = "#!/administrator"
        })


    }

    $scope.addNurse = function () {
        $http({
            method: 'POST',
            url: "http://localhost:7890/createNurse",
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
            data: $scope.addNurseData
        }).then((response) => {
            alert("Nurse added Successfully");
            $window.location.href = "#!/administrator";
        })


    }



});


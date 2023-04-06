function reloadWindow() {
    location.reload();  
    var lnk=document.getElementById("themeChange");
    if(lnk.getAttribute("href")==="css/themecss.css"){
        themeIcon.setAttribute("class","fa-sharp fa-solid fa-moon")
    }else if(lnk.getAttribute("href")==="css/logincss.css"){
        themeIcon.setAttribute("class","fa-sharp fa-solid fa-sun")
    } 
}
theme=()=>{
    var lnk=document.getElementById("themeChange");
    var themeIcon = document.getElementById("themeIcon")
    if(lnk.getAttribute("href")==="css/logincss.css"){
        lnk.setAttribute("href","css/themecss.css")
        themeIcon.setAttribute("class","fa-sharp fa-solid fa-moon")

    }else if(lnk.getAttribute("href")==="css/themecss.css"){
        lnk.setAttribute("href","css/logincss.css")
        themeIcon.setAttribute("class","fa-sharp fa-solid fa-sun")
    }
    console.log(lnk);
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
        case '/orders': $scope.activeTab = 'order';
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
        $scope.elp = "d-none";
        $scope.userIcon = "d-none";
        $scope.notificationIcon="d-none";
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

app.config(function ($routeProvider, $httpProvider ) {
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
        .when('/home',
            {
                templateUrl: "view/home.html"
            })
        .when('/shipping',
            {
                templateUrl: "view/shipping.html"
            })
        .when('/addToInventory',
            {
                templateUrl: "view/addToInventory.html"
            })
        .when('/dispenseToPatient',
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
    $scope.dispance = {}
    $scope.dispancePatientDetials = (x) => {
        $scope.dispance.patientId= x.id;
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
    

    $scope.dispenseToPatient =() => {
        console.log($scope.dispance)
        $http ({
            method: 'POST',
            url: 'http://localhost:7890/createDispense',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            },
            data : $scope.dispance

            }).then((response)=> {
                alert("Dispensed Successfully");
                

            })
        }
        $scope.closedispenseTable=false;
        $scope.closeTable = () => {
            $scope.closedispenseTable = true;
        }

        $scope.viewTable = () => {
            $scope.closedispenseTable = false;


        }
    $scope.serialDataFunction = () => {


        if ($scope.serialNumber != null) {
            $http({
                method: 'GET',
                url: 'http://localhost:7890/getSerialBySerialNo/' + $scope.serialNumber+"/"+sessionStorage.getItem("locationId"),
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': sessionStorage.getItem("token")
                }
            }).then((response) => {
                $scope.dispenseSerial

            }),(error) => {
                console.log(error);
            };
            
            $http({
                method: 'GET',
                url: 'http://localhost:7890/getProductBySerialNo/' + $scope.serialNumber,
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': sessionStorage.getItem("token")
                }
            }).then((response) => {
                $scope.serialData = response.data;
                console.log($scope.serialData)
                console.log("demo")
                $scope.dispance.productId = $scope.serialData.productId;
                $scope.dispance.locationId = $scope.serialData.locationId;
                $scope.dispance.enterpriseId = $scope.serialData.enterpriseId;
                $scope.dispance.serialId = $scope.serialData.serialId;

                if($scope.serialData.productId != null){
                    
                $http({
                    method: 'GET',
                    url: 'http://localhost:7890/getDoseName/'+$scope.serialData.productId +"/"+ $scope.serialNumber,
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': sessionStorage.getItem("token")
                    }
                }).then((response) => {
                    
                    $scope.serialDespense = response.data
                    console.log(response.data)
    
                }),(error) => {
                    console.log(error);
                };
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
                
                check=true
                //alert("Order Placed Successfully")

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
        if (thePath === "/reports" ) {
            return true;
        }
        return false;
    }
    $scope.linkData ={};
    $scope.saveLinks = () =>{
        $http({
            method: 'post',
            url: "http://localhost:7890/addLink",
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
            data : $scope.linkData
        }).then((response) => {
            $window.location.reload()
            
        }, (error) => { })     
    }
    $http({
        method: 'get',
        url: "http://localhost:7890/getAllQuickLink",
        headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
        
    }).then((response) => {
        $scope.getLinksData=response.data;
        console.log(response)
        
    }, (error) => { })  
    
    $scope.deleteLinks= (id) =>{
        $http({
            method: 'delete',
            url: "http://localhost:7890/deleteQucikLink/" + id,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem("token")
            }
        }).then((response) => {$window.location.reload() }, (error) => { })
    }
    $scope.updateLinkData={};
    $scope.updateLinks = () =>{
        $http({
            method: 'put',
            url: "http://localhost:7890/updateQuickLink" ,
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
            data : $scope.updateLinkData
        }).then((response) => {
            $window.location.reload()
            
        }, (error) => { })     
    }
    
    $scope.getQuickLinkData = (id) =>{
        console.log()
        $http({
            method: 'get',
            url: "http://localhost:7890/getQuickLink/" + id,
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") }
        }).then((response) => {
            $scope.updateLinkData = response.data;
            
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
        $scope.product_data = response.data;
        $scope.product_data1 = [...response.data];
    }, (error) => {
        console.log(error);
    });
    // $scope.product_data={};
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
        url: "http://localhost:7890/getAllDispense",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem("token")
        }
    }).then((response) => {
        console.log("Hello");
        console.log(response.data);
        $scope.dispenseShow = response.data;
    }, (error) => {
        console.log(error);
    }); 

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


    // $http({
    //     method: 'GET',
    //     url: "http://localhost:7890/getAutoReorderDose", http://localhost:7890/getAutoReorderDose
    //     headers: {
    //         'Content-Type': 'application/json',
    //         'Authorization': sessionStorage.getItem("token")
    //     }
    // }).then((response) => {
    //     console.log("Go");
    //     console.log(response.data);
    //     $scope.autoTableData = response.data;
    // }, (error) => {
    //     console.log(error);
    // });

    // $http({
    //     method: 'GET',
    //     url: "http://localhost:7890/getManualReorder",
    //     headers: {
    //         'Content-Type': 'application/json',
    //         'Authorization': sessionStorage.getItem("token")
    //     }
    // }).then((response) => {
    //     console.log("Manual");
    //     console.log(response.data);
    //     $scope.manualReorder = response.data;
    // }, (error) => {
    //     console.log(error);
    // }); 
    
    
    
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
        $http({
            method: 'get',
            url: "http://localhost:7890/shippedInventoryDetails/" + sessionStorage.getItem("locationId"),
            headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
        }).then((response) => {
            $scope.shippedDetails = response.data;
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
        console.log($scope.invetoryData);
    };

    $scope.inventoryStatusAvailable=()=>{
        $scope.shippedSerialDetails;
        $http({
            method: 'POST',
            url: 'http://localhost:7890/changeStatusAvailable/' +$scope.shippedSerialDetails.serialId +"/"+$scope.shippedSerialDetails.locationId, 
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

    $scope.closeDropDown=false;
    $scope.downloadfiles = () => {
        $scope.closeDropDown = true;
    }

    $scope.options = [
        { label: 'XLS', value: 'option1', url: 'http://localhost:7890/download/excel' },
        { label: 'PDF', value: 'option2', url: 'http://localhost:7890/download/pdf' },
        
      ];
    $scope.selectedOption = '';
      $scope.openUrl = function() {
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

        $window.location.reload();

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


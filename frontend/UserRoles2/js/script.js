
function userFunction() {
    var x = document.getElementById("userDropDown");
    console.log(x.style.display);
    if (x.style.display == "none" || x.style.display == "") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}


function notificationFunction() {
    var x = document.getElementById("notificationDropDown");
    console.log(x.style.display);
    if (x.style.display == "none" || x.style.display == "") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}



function downloadFunction() {
    var x = document.getElementById("downloadDropdown");
    console.log(x.style.display);
    if (x.style.display == "none" || x.style.display == "") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}
function editFunction() {
    var x = document.getElementById("editDropdown");
    console.log(x.style.display);
    if (x.style.display == "none" || x.style.display == "") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}

// function reorderFunction() {
//     var x = document.getElementById("reorderDropDown");
//     console.log(x.style.display);
//     if (x.style.display == "none" || x.style.display == "") {
//         x.style.display = "block";
//     } else {
//         x.style.display = "none";
//     }
// }

function toggleTables() {
    var auto1 = document.getElementById("autoTableMain");
    var manual1= document.getElementById("manualTableMain");
    var auto = document.getElementById("auto");
    var manual = document.getElementById("manual");
    if (auto.innerText==="Auto") {
        var autoTable = document.getElementById("autoTable");
        var manualTable = document.getElementById("manualTable");
        manualTable.classList.add("d-none");
        autoTable.classList.remove("d-none");
        auto1.classList.remove("d-none");
        manual1.classList.add("d-none");
        auto.classList.add("check");
        manual.classList.remove("check");
    }
  }
  
function toggleTables1() {
    var auto = document.getElementById("auto");
    var manual = document.getElementById("manual");
    var auto1 = document.getElementById("autoTableMain");
    var manual1= document.getElementById("manualTableMain");
    if (manual.innerText==="Manual") {
        var autoTable = document.getElementById("autoTable");
        var manualTable = document.getElementById("manualTable");
        manualTable.classList.remove("d-none");
        autoTable.classList.add("d-none");
        auto1.classList.add("d-none");
        manual1.classList.remove("d-none");
        manual.classList.add("check");
        auto.classList.remove("check");
    }
}



app.controller("elp", function ($scope, $http) {
    
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
});


app.controller('logoutCtrl', function ($scope, $window) {
    $scope.logout = () => {
        sessionStorage.removeItem("token")
        sessionStorage.removeItem("locationId")
        sessionStorage.removeItem("username")
        // $window.location.href = "#!";
        window.location.href = `http://localhost:8080/realms/InventoryManagementSystem/protocol/openid-connect/logout`
     }
});

app.controller("edit_userCtrl", function ($scope, $http, $window, $route) {
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
        delete $scope.edit.authorities;        
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
                url: "http://localhost:7890/api/editUser" ,
                headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
                data: $scope.edit
            }).then((response) => {
                console.log($scope.edit);
                $window.location.href = "#!";
                sessionStorage.removeItem("token");
                sessionStorage.removeItem("locationId");
                sessionStorage.removeItem("username");
            }, (error) => {
                console.log(error);
            });
        };
    };
});

// app.controller("successController", ['$scope', '$http', function ($scope, $http) {
    
//     $http({
//         method: 'GET',
//         url: 'http://localhost:7890/api/SuccessOrders/1',
//         headers: {
//             'Content-Type': 'application/json',
//             'Authorization': sessionStorage.getItem("token")
//         }
//     }).then((response) => {
//         console.log(response.data);
//         $scope.success_data = response.data;
//     },
//         (error) => {
//             console.log(error);
//         });
// }]);


// app.controller("errorController", ['$scope', '$http', function ($scope, $http) {
//     $http({
//         method: 'GET',
//         url: 'http://localhost:7890/api/ErrorOrders/2',
//         headers: {
//             'Content-Type': 'application/json',
//             'Authorization': sessionStorage.getItem("token")
//         }
//     }).then((response) => {
//         console.log(response.data);
//         $scope.error_data = response.data;
//     },
//         (error) => {
//             console.log(error);
//         });
// }]);

// app.controller("addPhysicianNurseController", function ($scope, $http, $window, $route) {
//    $scope.addPhysician() = function (){
//     $http({
//         method: 'POST',
//         url: "http://localhost:7890/createPhysician" ,
//         headers: { 'Content-Type': 'application/json', 'Authorization': sessionStorage.getItem("token") },
//         data: $scope.addPhysicianData
//    }).then((response) => {
       
//    })


//    }
// });

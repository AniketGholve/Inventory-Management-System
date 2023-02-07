let URL = "http://localhost:1111/getAllPatientList";
let app = angular.module("MyApp" , []);

app.controller('MyCtrl', ($scope , $http)=>{
   // This is controller

   $scope.title = "Patient List";

   

   $http.get(URL).then( (response) => {
    console.log(response.data);

    $scope.all_data = response.data;

   
},
(error) =>{
    console.log(error)
});
});

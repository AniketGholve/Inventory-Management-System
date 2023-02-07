const URL = "https://disease.sh/v3/covid-19/all"; 
const URL1 = "https://disease.sh/v3/covid-19";
let app = angular.module("MyApp", []);

app.controller("MyCtrl", ($scope , $http) => {

    $scope.title = "Stay Home Stay Safe";
   
    console.log("App Loaded");

    $http.get(URL).then( (response) => {
        console.log(response.data);

        $scope.all_data=response.data;

       
    },
    (error) =>{
        console.log(error)
    });
    
    $scope.get_c_data = () => {
        let country = $scope.c;
        if(country == "") {
            $scope.c_data = undefined;
            return;
        }

        $http.get(`${URL1}/countries/${country}`).then(
            (response) => {
                console.log(response.data);
                $scope.c_data=response.data;
            },
            (error) => {
                console.log(error); }
            );
            };  
    
});
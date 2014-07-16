function Hello($scope, $http) {
    $http.get('http://localhost:8080/app/').
        success(function(data) {
            $scope.greeting = data;
        });
}
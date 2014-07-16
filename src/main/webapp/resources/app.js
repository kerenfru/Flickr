var app = angular.module("app", []);

app.controller("AppCtrl", function($http) {
    var app = this;
    
    $http.get("http://localhost:8080/app/")
      .success(function(data) {
        app.people = data.rss.channel.item;
      });

//    app.addPerson = function(person) {
//        $http.post("http://localhost:8080/app/", person)
//          .success(function(data) {
//            app.people = data;
//          });
//    };
});
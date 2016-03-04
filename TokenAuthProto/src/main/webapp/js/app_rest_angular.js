angular.module('exampleApp', ['ui.router', 'restangular', 'ngCookies', 'exampleApp.services'])
    .config(
        [ '$stateProvider', '$urlRouterProvider','RestangularProvider', '$locationProvider', function($stateProvider, $urlRouterProvider,  RestangularProvider, $locationProvider) {
            var templatePrefix = document.getElementById('templatePrefix').value;

            $urlRouterProvider.otherwise('/');

            $stateProvider
                .state('app',{
                    abstract: true,
                    url: "",
                    template: '<ui-view/>',
                    resolve :{
                        authorize:['$state', '$timeout', '$cookieStore', '$rootScope','UserService' ,'RoleService', function($state, $timeout, $cookieStore, $rootScope, UserService, RoleService){
                            var authToken = $cookieStore.get('authToken');
                            if(!angular.isDefined(authToken) && !angular.isDefined($rootScope.user)){
                                $timeout(function () {
                                    $state.go("login");
                                }, 0);
                            }
                            if (authToken !== undefined && !angular.isDefined($rootScope.user)) {
                                $rootScope.authToken = authToken;
                                UserService.getUserInfo().then(function(user) {
                                    $rootScope.user = user;
                                    RoleService.setUser(user);
                                });
                            }
                        }]
                    }
                })
                .state('app.admin',{
                    abstract: true,
                    template: '<ui-view/>',
                    resolve : {authorize: ['RoleService','$timeout', '$state' ,function(RoleService, $timeout, $state){
                        var hasRole = RoleService.hasRole('admin');
                        if(!hasRole){
                            $timeout(function () {
                                $state.go("login");
                            }, 0);
                        }
                        return hasRole;
                    }]}
                })
                .state('app.admin.create', {
                    url: "/create",
                    templateUrl: '/partials/create.html',
                    controller: CreateController,
                })
                .state('app.admin.edit', {
                    url: "/edit/:id",
                    templateUrl: '/partials/edit.html',
                    controller: EditController
                })
                .state('login', {
                    url: "/login",
                    templateUrl: '/partials/login.html',
                    controller: LoginController
                })
                .state('app.main', {
                    url: "/",
                    templateUrl: '/partials/index.html',
                    controller: IndexController
                });

            $locationProvider.hashPrefix('!');

        } ]

    ).run(function($rootScope,  $location, $cookieStore, Restangular, UserService, RoleService) {


    Restangular.setErrorInterceptor(function(response, deferred, responseHandler) {
        var status = response.status;
        var config = response.config;
        var method = config.method;
        var url = config.url;

        if (response.status == 401) {
            $location.path( "/login" );
            return false;
        } else {
            $rootScope.error = method + " on " + url + " failed with status " + status;
        }
        return true;
    });

    Restangular.setFullRequestInterceptor(function(element, operation, route, url, headers, params, httpConfig) {
        var isRestCall = url.indexOf('rest') !=-1;
        if(isRestCall&& angular.isDefined($rootScope.authToken)){
            if (exampleAppConfig.useAuthTokenHeader) {
                headers['X-Auth-Token'] = $rootScope.authToken;
            }else{
                params['token']=$rootScope.authToken;
            }
        }
        return {
            element: element,
            params: params,
            headers: headers,
            httpConfig: httpConfig
        };
    });

    /* Reset error when a new view is loaded */
    $rootScope.$on('$viewContentLoaded', function() {
        delete $rootScope.error;
    });



    $rootScope.logout = function() {
        delete $rootScope.user;
        delete $rootScope.authToken;
        RoleService.clearUser();
        $cookieStore.remove('authToken');
        $location.path("/login");
    };

    /* Try getting valid user from cookie or go to login page */
    /*var originalPath = $location.path();
    $location.path("/login");
    var authToken = $cookieStore.get('authToken');
    if (authToken !== undefined) {
        $rootScope.authToken = authToken;
        UserService.getUserInfo().then(function(user) {
            $rootScope.user = user;
            $location.path(originalPath);
        });
    }*/

    $rootScope.initialized = true;
});


function IndexController($scope, NewsService, RoleService) {

    NewsService.getAllNews().then(function(result){

        $scope.newsEntries = result;
    });

    $scope.deleteEntry = function(newsEntry) {
        NewsService.deleteNews(newsEntry.id);
    };

    $scope.hasRole = function(role) {
        return RoleService.hasRole(role);
    };
};


function EditController($scope,  $stateParams, $location, NewsService) {

    NewsService.getNewsById($stateParams.id).then(function(news){
        $scope.newsEntry=news;
    });

    $scope.save = function() {
        NewsService.save($scope.newsEntry).then(function(res){
            $location.path('/');
        });
    };
};


function CreateController($scope, $location, NewsService) {

    /*$scope.newsEntry = new NewsService();

    $scope.save = function() {
        $scope.newsEntry.$save(function() {
            $location.path('/');
        });
    };*/
};


function LoginController($scope, $rootScope, $location, $cookieStore, UserService, RoleService) {
    $scope.rememberMe = false;
    $scope.login = function() {
        var userForm={username: $scope.username, password: $scope.password}
        var promises = UserService.authenticate(userForm);
        promises.then(function (authenticationResult){
            var authToken = authenticationResult.token;
            $rootScope.authToken = authToken;
            if ($scope.rememberMe) {
                $cookieStore.put('authToken', authToken);
            }
            UserService.getUserInfo().then(function(user) {
                $rootScope.user = user;
                RoleService.setUser(user);
                $location.path("/");
            });
        });

    };
};


var services = angular.module('exampleApp.services', ['restangular']);

services.factory('UserService', function(Restangular) {
    return {
        authenticate : function(userForm){
            return Restangular.all('rest/user/authenticate').post(userForm);
        },
        getUserInfo : function(){
            return Restangular.one('rest/user').get();
        },
        hasRole : function(user, role) {
            if (user === undefined) {
                return false;
            }

            if (user.roles[role] === undefined) {
                return false;
            }
            return user.roles[role];
        }
    };
});

services.factory('NewsService', function(Restangular) {
    return {
        getAllNews: function(){
            return Restangular.all('rest/news').getList();
        },
        getNewsById : function(id){
            return Restangular.one('rest/news', id).get();
        },
        deleteNews : function(id){
            return Restangular.one('rest/news', id).remove();
        },
        save : function(news){
            if(angular.isUndefined(news.id)){
                return Restangular.all('rest/news').post(news);
            }else{
                var newsDTO = {id:news.id, date:news.date, content:news.content}
                var res = Restangular.one('rest/news',newsDTO.id).post("", newsDTO);
                return res;
            }

        }
    };
});

services.factory('RoleService', function(){
    return{
        user: null,
        setUser : function(user){
            this.user=user;
        },

        clearUser : function(){
            this.user=null;
        },

        hasRole : function(role) {
            if (this.user==null || !angular.isDefined(this.user)) {
                return false;
            }

            if (this.user.roles[role] === undefined) {
                return false;
            }
            return this.user.roles[role];
        }
    }
});


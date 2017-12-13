var app = angular.module("managerModule", []);

app.controller("managerController", function ($scope, $http) {

    $scope.projects = [{
        projectId: null,
        name: 'not init'
    }];
    $scope.users = [{
        userId : null,
        role : 'DEVELOPER',
        name : '',
        lastName : ''
    }];
    $scope.tasks = [{
        taskId : null,
        description : '',
        status : '',
        developerId : null,
        developerName : '',
        developerLastName : ''
    }];
    $scope.status = {
        waiting : 'WAITING',
        implementation : 'IMPLEMENTATION',
        verifying : 'VERIFYING',
        releasing : 'RELEASING'
    };
    $scope.newProject = {
        projectId: null,
        name: 'not init'
    };
    $scope.newTask = {
        taskId : null,
        description : '',
        status : 'WAITING',
        developerId : null
    };
    $scope.newStatus = '';
    $scope.selectProject = null;
    $scope.selectTask = null;
    $scope.selectUser = null;
    $scope.myResult = "";
    $scope.registrationTaskResult = '';
    $scope.registrationUserResult = '';
    $scope.registrationProjectResult = '';
    $scope.registrationTaskToUser = '';
    $scope.resultNewStatus = '';

    ///////////////////////////////////////////////////////////////////////////////////////////

    $scope.installPage = function () {
        $http.get("/taskTracker/project/manager/" )
            .then(function success(response) {
                    $scope.projects = response.data;
                    $scope.selectProject = $scope.projects[0];
                    $scope.getTasksOfProject($scope.selectProject.projectId);
                    $scope.getAllDeveloper();
                    $scope.newStatus = $scope.status.waiting;
                },
                function error(response) {
                    $scope.myResult = response.data;
                });
    };

    ////////////////////////////////////////////////////////////////////////////////////////////////

    $scope.installPage();

    //////////////////////////////////////////////////////////////////////////////////////////////

    $scope.addProject = function () {
        $http.post("/taskTracker/project", $scope.newProject)
            .then(function success(response) {
                $scope.projects.push(response.data);
                $scope.registrationProjectResult = 'проект зарегистрирован';
                $scope.selectProject = $scope.projects[0];
                $scope.getAllDeveloper();
                },
                function error() {
                    $scope.registrationProjectResult = 'повторите попытку';
                });
    };

    $scope.getTasksOfProject = function (projectId){
        $http.get("/taskTracker/task/" + projectId.toString())
            .then(function success(response) {
                    $scope.tasks = response.data;
                    $scope.selectTask = $scope.tasks[0];
                    },
                    function error(response) {
                        $scope.myResult = response.data;
                    });
    };

    $scope.addTasksOfProject = function () {
        $http.post("/taskTracker/task/"
                   + $scope.selectProject.projectId.toString(),
            $scope.newTask)
            .then(function success(response) {
                    $scope.tasks.push(response.data);
                    $scope.registrationTaskResult = 'задача зарегистрирована';
                    $scope.getTasksOfProject();
                   // $scope.selectTask = $scope.tasks[0];
                    },
                    function error() {
                        $scope.registrationTaskResult = 'повторите попытку';
                    });
    };

    $scope.setTaskToUser = function () {
        $http.put("/taskTracker/task/" + $scope.selectTask.taskId.toString(),
                  $scope.selectUser)
             .then(function success() {
                    $scope.registrationTaskToUser = 'задача назначена';
                    $scope.getTasksOfProject($scope.selectProject.projectId);
                    },
                    function error() {
                        $scope.registrationTaskToUser  = 'повторите попытку';
                    });
    };

    $scope.getAllDeveloper = function () {
        $http.get("/taskTracker/user/")
             .then(function  success(response) {
                 $scope.users = response.data;
                 $scope.selectUser = $scope.users[0];
                 $scope.registrationUsersResult = 'успех';
                 },
                 function error() {
                     $scope.registrationUsersResult = 'повторите попытку';
                 });
    };

    $scope.setNewStatus = function () {
        $scope.newTask.taskId = $scope.selectTask.taskId;
        $scope.newTask.status = $scope.newStatus;
        $http.put("/taskTracker/task/",
                  $scope.newTask)
             .then(function success() {
                    $scope.selectTask.status = $scope.newStatus;
                    $scope.resultNewStatus = 'статус зарегистрирован';
                },
                function error() {
                    $scope.registrationTaskToUser  = 'повторите попытку';
                });
    };

});


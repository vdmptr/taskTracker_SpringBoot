var app = angular.module("developerModule", []);

app.controller("developerController", function ($scope, $http) {

    $scope.projects = [{
        projectId: null,
        name: 'not init',
        managerId : null
    }];

    $scope.tasks = [{
        taskId : null,
        description : '',
        status : '',
        projectId : null,
        developerId : null,
        developerName : '',
        developerLastName : ''
        // comments : null
    }];

    $scope.status ={
        waiting : 'WAITING',
        implementation : 'IMPLEMENTATION',
        verifying : 'VERIFYING',
        releasing : 'RELEASING'
    };

    $scope.newTask = {
        taskId : null,
        description : 'not init',
        status : 'WAITING',
        projectId : null,
        developerId : null
    };

    $scope.newStatus = '';
    $scope.resultNewStatus = '';
    $scope.resultGetTask = '';
    $scope.developerId = null;
    $scope.developerTasks = null;
    $scope.selectProject = null;
    $scope.selectTask = $scope.newTask;
    $scope.myResult = "";
    $scope.selectResult = null;
    $scope.taskResult = '';
    $scope.registrationTaskResult = '';
    $scope.registrationProjectResult = '';
    $scope.registrationTaskToUser = '';
    $scope.oldSelect = null;
       ///////////////////////////////////////////////////////////////////////////////////////////

    $scope.installPage = function () {
        $http.get("/taskTracker/project/developer" )
            .then(function success(response) {
                    $scope.projects = response.data;
                    $scope.selectProject = $scope.projects[0];
                    $scope.getTasksOfProject($scope.selectProject.projectId);
                    $scope.selectTask = $scope.tasks[0];
                    $scope.getDeveloperId();
                },
                function error(response) {
                    $scope.myResult = response.data;
                });
    };

    ////////////////////////////////////////////////////////////////////////////////////////////////

    $scope.installPage();

    //////////////////////////////////////////////////////////////////////////////////////////////

    $scope.getDeveloperId = function () {
        $http.get("/taskTracker/user/developer")
            .then(function success(response) {
                    $scope.developerId = response.data;
                },
                function error(response) {
                    $scope.myResult = response.data;
                });
    };

    $scope.getTasksOfProject = function (){
        $http.get("/taskTracker/task/"
                  + $scope.selectProject.projectId.toString())
            .then(function success(response) {
                    $scope.tasks = response.data;
                    $scope.selectTask = $scope.tasks[0];
                },
                function error(response) {
                    $scope.tasks = undefined;
                    $scope.resultGetTask = 'нет результата с сервера';
                    $scope.myResult = response.data;
                });
    };

    $scope.addTasksOfProject = function () {
        $http.post("/taskTracker/task/"
                    + $scope.selectProject.projectId.toString()
                    + "/" + $scope.developerId.toString(),
                    $scope.newTask)
             .then(function success(response) {
                    $scope.tasks.push(response.data);
                    $scope.registrationTaskResult = 'задача зарегистрирована';
                },
                function error() {
                    $scope.registrationTaskResult = 'повторите попытку';
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


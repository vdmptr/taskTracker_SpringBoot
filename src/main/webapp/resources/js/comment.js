var app = angular.module("commentModule", []);

app.controller("commentController", function ($scope, $http) {

    $scope.taskId = 0;

    $scope.commentsTask = [{
        commentId : null,
        comment : ''
    }];

    $scope.selectMyComment = null;

    $scope.correct = null;
    $scope.description = null;
    $scope.resultDelete = '';
    $scope.resultCorrect = '';
    $scope.resultAdd = '';
    $scope.newComment = null;
    $scope.translateCorrect = {
        commentId : null,
        comment : ''
    };


   ///////////////////////////////////////////////////////////////////////////////////////////
    $scope.installPage = function () {
        $http.get("/taskTracker/task/init/id")
            .then(function success(response) {
                    $scope.taskId = response.data;
                    $scope.getComments();
                    $scope.getDescription();
                },
                function error(response) {
                    $scope.myResult = response.data;
                });
    };

   ////////////////////////////////////////////////////////////////////////////////////////////////

   $scope.installPage();

   //////////////////////////////////////////////////////////////////////////////////////////////

    $scope.getComments = function () {
        $http.get("/taskTracker/comment/"
            + $scope.taskId.toString())
            .then(function success(response) {
                    $scope.commentsTask = response.data;
                    $scope.selectMyComment = $scope.commentsTask[0];
                    $scope.correct = $scope.selectMyComment.comment;
                },
                function error(response) {
                    $scope.myResult = response.data;
                });
    };

    $scope.getDescription = function () {
        $http.get("/taskTracker/task/description/"
            + $scope.taskId.toString())
            .then(function success(response) {
                    $scope.description = response.data;
                },
                function error(response) {
                    $scope.myResult = response.data;
                });
    };

    $scope.addComment = function () {
        $http.post("/taskTracker/comment/"
            + $scope.taskId.toString(),
            $scope.newComment)
            .then(function success(response) {
                    $scope.commentsTask = response.data;
                    $scope.selectMyComment = $scope.commentsTask[0];
                    $scope.correct = $scope.selectMyComment.comment;
                    $scope.resultAdd = "комментарий добавлен";
                },
                function error(response) {
                    $scope.myResult = response.data;
                });
    };

    $scope.correctComment = function () {
        $scope.translateCorrect.comment = $scope.correct;
        $scope.translateCorrect.commentId = $scope.selectMyComment.commentId;
        $http.put("/taskTracker/comment/"
                  + $scope.taskId.toString(),
                  $scope.translateCorrect)
            .then(function success(response) {
                    $scope.commentsTask = response.data;
                    $scope.selectMyComment = $scope.commentsTask[0];
                    $scope.resultCorrect = 'сохранен';
                },
                function error(response) {
                    $scope.myResult = response.data;
                });
    };

    $scope.deleteComment = function () {
        $http.delete("/taskTracker/comment/"
                     + $scope.selectMyComment.commentId.toString()
                     + "/"
                     + $scope.taskId.toString())
             .then(function success(response) {
                     $scope.commentsTask = response.data;
                     $scope.selectMyComment = $scope.commentsTask[0];
                     $scope.correct = $scope.selectMyComment.comment;
                    // $scope.resultDelete = 'предыдущий комментарий был удален';
                },
                function error(response) {
                    $scope.myResult = response.data;
                });
    };

    $scope.changeCorrect = function () {
        if($scope.selectMyComment){
            $scope.correct = $scope.selectMyComment.comment;
        }

    };
});
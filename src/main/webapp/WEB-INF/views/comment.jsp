<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="commentModule" id="ng-app" >
<head>
    <title>manager</title>
    <meta charset="UTF-8" />
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js">
    </script>
    <script src="/taskTracker/resources/js/comment.js?074"></script>
</head>
<body >
<div ng-controller="commentController" >
    <div id="descriptionTask" >
        <div>taskId = {{taskId}}</div>
        <div> <h3> описание задачи: </h3> </div>
            {{description.description}}
    </div>

    <div id="commentsTask">
        <div><h3>комментарии задачи:</h3></div>
        <label>
            <select size="1" ng-model="selectMyComment" ng-change="changeCorrect()">
                <option ng-repeat="comment in commentsTask"
                        ng-value="comment">
                    {{comment.comment}}
                </option>
            </select>
        </label>
        <br/> {{resultDelete}}<br/>
        <div>
            <button ng-click="deleteComment()">удалить комментарий</button>
        </div>
        <br/>
    </div>

    <div>
        <div id="correctComment"><h5>поле редактирования:</h5>
            <label >
                 <textarea rows="3" cols="40" ng-model="correct"
                           ng-change="resultCorrect = ''">
                 </textarea>
            </label>
        </div>
        <br/> {{resultCorrect}}<br/>
        <div>
            <button ng-click="correctComment()">
                сохранить отредактированный комментарий
            </button>
        </div><br/>
    </div>

    <div id="newComment">
        <div>
            <label>
            <TEXTAREA NAME=name ROWS=3 COLS=40 ng-model="newComment.comment"
            ng-change="resultAdd = ''">
            </TEXTAREA>
            </label>
            <br/>
        </div>
        <br/> {{resultAdd}}<br/>
        <div>
            <button ng-click="addComment()">добавить комментарий</button>
        </div>

    </div>
</div>
</body>
</html>

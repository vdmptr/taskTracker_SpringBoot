<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="developerModule" id="ng-app">
<head>
    <title>manager</title>
    <meta charset="UTF-8" />
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
    <script src="/taskTracker/resources/js/developer.js?038"></script>
</head>
<body ng-controller="developerController" >

<div id="selectProject"><h1>выберите проект:</h1>
    <div>
        <div><h3>{{resultGetTask }}</h3>
            <label>
                <select size="1" ng-model="selectProject"
                        ng-options="project.name for project in projects"
                        ng-change="getTasksOfProject()">
                </select>
            </label>
        </div>
        <div>select projectId :   {{selectProject.projectId}}</div>
    </div>
</div>

<div id="tasksOfProject">
    <div > <br/>задачи проекта</div>
    <div>
        <table>
            <tr>
                <th>task description</th>
                <th>task status</th>
                <th>task developerName</th>
                <th>task developerLastName</th>
                <th>developer id</th>
            </tr>
            <tr ng-repeat="task in tasks ">
                <th>{{task.description}}</th>
                <th>{{task.status}}</th>
                <th>{{task.developerName}}</th>
                <th>{{task.developerLastName}}</th>
                <th>{{task.developerId}}</th>
        </table>
        {{myResult}}<br/>

    </div>
</div>

<div id="developerTasks">
    <div><h3>задачи разработчика: </h3></div>
    <div>
        <table>
            <tr>
                <th>task description</th>
                <th>task status</th>
                <th>task developerName</th>
                <th>task developerLastName</th>
                <th>developer id</th>
            </tr>
            <tr ng-repeat="task in tasks | filter : {'developerId' : developerId} ">
                <th>
                    <a href="/taskTracker/page/task/{{task.taskId}}">
                        {{task.description}}
                    </a>
                </th>
                <th>{{task.status}}</th>
                <th>{{task.developerName}}</th>
                <th>{{task.developerLastName}}</th>
                <th>{{task.developerId}}</th>
        </table>
    </div>
</div>

<div id="statusTask">
    <div><h3>установка нового статуса задачи</h3></div>
    <div><h4>выберете задачу:</h4>
        <label>
            <select size="1" ng-model="selectTask"
                    ng-options="task.description for task in tasks
                                | filter : {'developerId' : developerId}"
                    ng-change="resultNewStatus = ''">
            </select>
        </label>
    </div>
    <div><h4>выберете новый статус:</h4>
        <label>
            <select size="1" ng-model="newStatus"
                    ng-options="stt for stt in status"
                    ng-change="resultNewStatus = ''">
            </select>
        </label>
    </div>
    <div><br/>{{resultNewStatus}}<br/>
        <button ng-click="setNewStatus()">
            зарегистрировать новый статус задачи
        </button>
        <br/>
    </div>
</div>

<div id="newTask">
    <h2>создание навой задачи</h2>
    <div>
        введите описание новой задачи
        <label>
            <input type="text" ng-model="newTask.description"
                   ng-change="registrationTaskResult = ''"/>
        </label><br/>
    </div>
    <br/>{{registrationTaskResult}}<br/>
    <div>
        <button ng-click="addTasksOfProject()">
            зарегистрировать задачу в текущем проекте
        </button>
        <br/>
    </div>
</div>

</body>
</html>

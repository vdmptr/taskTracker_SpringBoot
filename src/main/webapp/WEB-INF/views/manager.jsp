
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="managerModule" id="ng-app">
<head>
    <title>manager</title>
    <meta charset="UTF-8" />
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js">
    </script>
    <script src="/taskTracker/resources/js/manager.js?222"></script>
</head>
<body ng-controller="managerController" >

    <div id="selectProject"><h1>выберите проект:</h1>
        <div>
            <div>
                <label>
                    <select size="1" ng-model="selectProject"
                            ng-options="project.name for project in projects"
                            ng-change="getTasksOfProject(selectProject.projectId)">
                    </select>
                </label>
            </div>
            <div>select projectId :   {{selectProject}}</div>
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
                 <tr ng-repeat="task in tasks">

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
             {{myResult}}<br/>

         </div>
     </div>

     <div id="newTask">
         <h2>создание новой задачи</h2>
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

    <div id="addUserToTask">
         <div>
            <h2>назначение разработчику задачи</h2>
         </div>
         <div>
             выберите задачу
             <label>
                 <select size="1" ng-model="selectTask"
                         ng-options="task.description for task in tasks"
                         ng-change="registrationTaskToUser = ''"> </select>
             </label><br/>
             выберите разработчика <h5> имя: {{selectUser.name}}</h5>
             <h5>фамилмя: {{selectUser.lastName}}</h5>
             <h5>Id разработчика:
             <label>
                 <select size="1" ng-model="selectUser"
                         ng-options="developer.userId for developer in users"
                         ng-change="registrationTaskToUser = ''"></select>
             </label>
             </h5>
         </div>
         <br/> <div>{{registrationTaskToUser}}</div><br/>

         <div>
             <button ng-click="setTaskToUser()">
                 назначить задачу
             </button>
         </div>
     </div>

    <div id="searchDeveloper">
        <div><h2>поиск разработчика</h2></div>

        <div>найденный разработчик: </div><br/>


        <div> id : {{selectUser.userId}}</div>
        <div> имя : {{selectUser.name}}</div>
        <div> фамилия : {{selectUser.lastName}}</div><br/>

        <div>выберите фамилию : </div>
        <label>
            <select size="1" ng-model="selectUser"
                    ng-options="developer.lastName  for developer in users ">
            </select>
        </label>

        <div>выберите имя в соотвествии с фамилией: </div>
        <label>
            <select size="1" ng-model="selectUser"
                    ng-options="developer.name  for developer in users
                                | filter : {'lastName' : selectUser.lastName}">
            </select>
        </label>

        <div>выберите id в соотвествии с именем и фамилией: </div>
        <label>
            <select size="1" ng-model="selectUser"
                    ng-options="developer.userId  for developer in users
                                | filter :
                                {'lastName' : selectUser.lastName,
                                 'name' : selectUser.name}">
            </select>
        </label>
    </div>

    <div id="statusTask">
        <div><h3>установка нового статуса задачи</h3></div>
        <div><h4>выберите задачу:</h4>
            <label>
                <select size="1" ng-model="selectTask"
                        ng-options="task.description for task in tasks "
                        ng-change="resultNewStatus = ''">
                </select>
            </label>
        </div>
        <div><h4>выберите новый статус:</h4>
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


    <div id="newProject">
         <h2>создание  нового проекта</h2>
         <div>
             введите название нового проекта
             <input type="text" ng-model="newProject.name"
                    ng-change="registrationProjectResult = ''"
                    title=""/><br/><br/>

             <br/>{{registrationProjectResult}}<br/>
         </div>
         <div>
             <button ng-click="addProject()">зарегистрировать проект</button>
             <br/>
         </div>
    </div>

</body>
</html>

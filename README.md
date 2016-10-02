# SudokuWebService

#Sudoku Rest WEB Service

This is a RESTful web service that can validate successive moves on a Sudoku board. 
It is also able to recognise and indicate if the Sudoku is finished with the current move.

#Requirements
JAVA 8
MAVEN 

You can run the application using >>> mvn spring-boot:run
Or build the JAR file with >>> mvn clean package  and Run it

#Operations

GET http://localhost:8080/sudoku/board 
[[7,0,0,0,4,0,5,3,8],[0,0,5,0,0,8,0,1,0],[0,0,8,5,0,9,0,4,0],[5,3,9,0,6,0,0,0,1],[0,0,0,0,1,0,0,0,5],[8,0,0,7,2,0,9,0,0],[9,0,7,4,0,0,0,0,0],[0,0,0,0,5,7,0,0,0],[6,0,0,0,0,0,0,5,0]]

PUT http://localhost:8080/sudoku/moves?row=3&column=0&value=1

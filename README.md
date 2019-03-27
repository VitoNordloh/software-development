# Software Development
This repository explains how to install and run the application.

## Requirements
- Gradle 5.1
- Java 1.8
- npm 6.7.0

## Starting the Backend
- Go to the `backend` folder
- Copy the file `config/application.properties.template` to `config/application.properties` and replace the conncetion URI for the database (can be found in the report)
- Run `gradle bootRun`

## Starting the Frontend
- Go to the `frontend` folder
- Run `npm install`
- Run `npm run start`
- Open http://localhost:4200 in a webbrowser

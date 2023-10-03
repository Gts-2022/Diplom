# Процедура запуска автотестов

1.Склонировать [проект](https://github.com/Gts-2022/Diplom) командой **Git clone**

2.Установить Docker Desktop 

3.Запустить Docker в терминале командой **docker-compose up**

4.Запустить в свободном терминале приложения для БД : 

-для MySQL: **java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar**

-для Postgresgl: **java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar**

5.Запустить в свободном терминале тесты для БД:

-для MySQL: **./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"**

-для Postgresgl: **./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"**

6.После прогона автотестов создать отчет в Allure командой в терминале **./gradlew allureServe**


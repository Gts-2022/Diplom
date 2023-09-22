# Процедура запуска автотестов

1.Склонировать [проект](https://github.com/Gts-2022/Diplom) командой **Git clone**

2.Установить Docker Desktop 

3.Запустить Docker в терминале командой **docker-compose up**

4.Запустить в свободном терминале jar-файл командой **java -jar./artifacts/aqa-shop.jar**

5.Запустить в свободном терминале тесты командой **./gradlew clean test**

6.После прогона автотестов создать отчет в Allure командой в терминале **./gradlew allureServe**


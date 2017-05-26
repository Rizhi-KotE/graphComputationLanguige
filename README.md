# graphComputationLanguige

Что бы запустить программу на языке для начала нужно собрать компилятор.

Для этого нужна система сборки gradle. В корне запускаем команду

> gradle jar

После этого можно запускать программы на выполнение.
Скрипты для запуска находятся в папке 
> gclc

Копирование jar файла компилятора папку :). Это необхоимо делать каждый раз после сборки компилятора

> compile.sh

Компиляция и запуск программы

> run.sh <путь_к_файлу>

Грамматика
> src/main/antln/*.g4

Для работы компилятора нужен установленный scala компилятор.
Если не установлена система сборки gradle, для сборки компилятора использовать 

> ./gradlew jar //linux
>
> gradlew.bat jar //windows

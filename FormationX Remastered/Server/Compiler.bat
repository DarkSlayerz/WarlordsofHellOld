@echo off
:build
cls
"C:\Program Files\Java\jdk1.6.0_25\bin\javac.exe" -classpath deps/log4j-1.2.15.jar;deps/jython.jar;deps/xstream.jar;deps/mina.jar;deps/mysql.jar;deps/poi.jar;deps/slf4j.jar;deps/slf4j-nop.jar -d bin src\server\event\*.java src\server\model\items\*.java src\server\model\minigames\*.java src\server\model\npcs\*.java src\server\model\objects\*.java src\server\model\players\*.java src\server\model\players\skills\*.java src\server\model\players\packets\*.java src\server\model\shops\*.java src\server\net\*.java src\server\task\*.java src\server\util\*.java src\server\world\*.java src\server\util\log\*.java src\server\*.java src\server\world\map\*.java src\server\model\players\combat\*.java src\server\model\minigames\TreasureTrails\*.java src\server\model\players\Content\*.java src\server\model\players\combat\Ranged\*.java src\server\model\players\combat\Magic\*.java src\server\model\players\combat\Melee\*.java
pause
goto :build 

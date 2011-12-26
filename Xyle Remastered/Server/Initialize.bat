@echo off
title Warlords of Hell - Initialize Server
java -Xmx1024m -cp bin;deps/netty-3.2.jar;deps/poi.jar;deps/mysql.jar;deps/mina.jar;deps/slf4j.jar;deps/slf4j-nop.jar;deps/jython.jar;log4j-1.2.15.jar; server.Server
pause
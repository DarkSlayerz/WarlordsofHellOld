@echo off
Title Client
cd ./Class/
START java -Xmx1000m client 30 0 lowmem members 32
exit
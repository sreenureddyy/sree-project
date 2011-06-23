set JPDA_TRANSPORT=dt_socket
rem set JPDA_TRANSPORT=dt_shmem
set JPDA_ADDRESS=8000
set JAVA_OPTS=-XX:PermSize=256m -XX:MaxPermSize=256m -Xmx512m
rem set JPDA_ADDRESS=8000
catalina.bat jpda start
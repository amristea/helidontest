#!/bin/bash

case in

start)
	java -jar target/quickstart-mp.jar &
;;
stop)
	pkill java
;;
restart)
	stop();
	start();
;;
*)
	echo "Usage: ./script/quickstart.sh start|stop|restart"
;;

exit 0	

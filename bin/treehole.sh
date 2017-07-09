#!/bin/bash

env_args="-Xms128m -Xmx128m"
sleeptime=0
arglen=$#

# get treehole pid
get_pid(){
    pname="`find .. -name 'treehole*.jar'`"
    pname=${pname:3}
    pid=`ps -ef | grep $pname | grep -v grep | awk '{print $2}'`
    echo "$pid"
}

# get path
get_path(){
    path = $0
    if [$path == "./treehole.sh"]
    then
        echo "aaaaaa"
    else
        echo "bbbb"
    fi

}

show_status(){
    pid=$(get_pid)
    if [ "$pid" != "" ]
    then
        echo "TreeHole is running with pid: $pid"
    else
        echo "TreeHole is stop!"
    fi
}

startup(){
    pid=$(get_pid)
    if [ "$pid" != "" ]
    then
        echo "TreeHole already startup!"
    else
        jar_path=`find .. -name 'treehole*.jar'`
        echo "jarfile=$jar_path"
        cmd="java $1 -jar $jar_path > ../treehole.out < /dev/null &"
        echo "cmd: $cmd"
        java $1 -jar $jar_path > ./treehole.out < /dev/null &
        show_log
    fi
}

shut_down(){
    pid=$(get_pid)
    if [ "$pid" != "" ]
    then
        kill -9 $pid
        echo "TreeHole is stop!"
    else
        echo "TreeHole already stop!"
    fi
}

show_log(){
    tail -f ../treehole.out
}

show_help(){
    echo -e "\r\n\t欢迎使用TreeHole Blog"
    echo -e "\r\nUsage: sh treehole.sh start|stop|reload|status|log"
    exit
}

if [ $arglen -eq 0 ]
 then
    show_help
else
    if [ "$2" != "" ]
    then
        env_args="$2"
    fi
    case "$1" in
        "start")
            startup "$env_args"
            ;;
        "stop")
            shut_down
            ;;
        "reload")
            echo "reload"
            ;;
        "status")
            show_status
            ;;
        "log")
            show_log
            ;;
    esac
fi
#!/bin/bash

TREEHOLE_HOME="/home/treehole/treehole-jekyll-0.0.1-SNAPSHOT"
LOG_NAME="treehole.out"

env_args="-Xms128m -Xmx128m"
arglen=$#

function to_home(){
    cd $TREEHOLE_HOME
}

function get_home(){
    echo $TREEHOLE_HOME
}

function get_log_name(){
    echo $LOG_NAME
}

function get_log_home(){
    echo $(get_home)
}

function to_log_home(){
    cd $(get_log_home)
}

function get_jar_name(){
    to_home
    jarname=`find *.jar`
    echo $jarname
}

function get_pid(){
    to_home
    pname=$(get_jar_name)
    pid=`ps -ef | grep $pname | grep -v grep | awk '{print $2}'`
    echo $pid
}

function startup(){
    to_home
    pid=$(get_pid)
    if [ -n "$pid" ]; then
        echo "Treehole is already running..."
    else
        jar_path=$(get_home)/$(get_jar_name)
        echo $jar_path
        cmd="java $env_args -jar $jar_path > $(get_log_home)/$(get_log_name) < /dev/null &"
        echo $cmd
        java $env_args -jar $jar_path > $(get_log_home)/$(get_log_name) < /dev/null &
        show_log
    fi
}

function show_log(){
    to_log_home
    tail -f $(get_log_name)
}

function shut_down(){
    to_home
    pid=$(get_pid)
    if [ -n "$pid" ]; then
        kill -9 $pid
        echo "kill pid $pid ,Treehole is stop!"
    else
        echo "Treehole is not running"
    fi
}

function show_status(){
    pid=$(get_pid)
    if [ -n "$pid" ]; then
        echo "Treehole is running with pid $pid"
    else
        echo "Treehole is stop!"
    fi
}


function show_help(){
    echo -e "\r\n\t欢迎使用TreeHole Blog"
    echo -e "\r\nUsage: sh treehole.sh start|stop|reload|status|log"
    exit
}

function reload_th(){
    echo "reload..."
    shut_down
    startup
}

# start
if [ $arglen -eq 0  ]; then
    show_help
else
    case "$1" in
        "start")
            startup
            ;;
        "stop")
            shut_down
            ;;
        "reload")
            reload_th
            ;;
        "status")
            show_status
            ;;
        "log")
            show_log
        ;;
    esac
fi
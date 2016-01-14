#!/usr/bin/env python

import sys
import atexit
import time
import os
import commands
import signal
import psutil
import socket
import subprocess

def main():
    # pidTest()
    subprocessTest()

def subprocessTest():
    out = subprocess.call("/Users/allen/myshell/test.sh start", shell=True)

def pidTest():
    pid = getPidByType('Application', 'JAVA')
    try:
        p = psutil.Process(pid)
        print p.name()
        print p.exe()
        print p.cwd()
        print p.cmdline()
        print p.status()
        print p.username()
        print p.create_time
        print os.getpid()
    except:
        print 'error'

def getPidByType(name, p_type):
    pid = ''
    if p_type == 'JAVA':
        pid = commands.getoutput('jps | grep ' + name + " | awk 'NR==1 {print $1}'")
    elif p_type == 'SYS':
        pid = check_output(["pidof","-s",name])
        print pid

    pid = (None if pid == '' else pid)
    return int(pid)

if __name__ == "__main__":
    main()

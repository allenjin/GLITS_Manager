#!/usr/bin/env python

import sys
import atexit
import time
import os
import commands
import signal
import psutil
import socket

def main():
    print psutil.pids()
    p = psutil.Process(91853)
    print p.name()
    print p.exe()
    print p.cwd()
    print p.cmdline()
    print p.status()
    print p.username()
    print p.create_time
    print os.getpid()
    output= getPid('Application')
    print output

def getPid(name):
    grep_result = commands.getoutput('jps | grep ' + name + " | awk '{print $1}'")
    return grep_result

if __name__ == "__main__":
    main()

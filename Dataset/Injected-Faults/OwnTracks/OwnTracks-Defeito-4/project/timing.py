#python3
import atexit
from time import process_time, strftime, localtime
from datetime import timedelta

def secondsToStr(elapsed=None):
    if elapsed is None:
        return strftime("%Y-%m-%d %H:%M:%S", localtime())
    else:
        return str(timedelta(seconds=elapsed))

def log(s, elapsed=None):
    line = "="*40
    print(line)
    print(secondsToStr(), '-', s)
    if elapsed:
        print("Elapsed time:", elapsed)
    print(line)
    print()

def endlog():
    end = process_time()
    elapsed = end-start
    #log("End Program", secondsToStr(elapsed))
    log("End Program", elapsed)

start = process_time()
atexit.register(endlog)
log("Start Program")

import csv
from subprocess import check_output
import timing 
from time import perf_counter

fileName = 'configCombinations-Random.csv'

print(check_output(f"mkdir testReportsMult-Random/execution3_1", shell="True").decode('cp850'))

with open(fileName) as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')

    neededExecs = [45,34,32,17,39,42,26,20,15,5,46,13,18,27,23,10,2,21,8,25,22,49,16]

    execTimeFile = open("executionTime.txt","a")

    for exec in neededExecs:
        for i in range(256): # count from 0 to 255
            if (i != exec):
                next(csv_reader)     # and discard the rows
            else:
                break
        row = next(csv_reader)


        config = {'location':False,'wifi':False,'mobiledata':False,'bluetooth':False,'autorotate':False,'batterysaver':False,'donotdisturb':False, 'camera':False,'accelerometer':False,'gyroscope':False,'light':False,'magneticfield':False,'orientation':False,'proximity':False} 

        for column in row:
            if column != '':
                config[column] = True

        print(f"Current configuration: {config}")
        startTime_Config = perf_counter()

        command1 = f"adb shell dumpsys battery unplug"
        print(check_output(command1, shell=True).decode('cp850'))

        command = f"./gradlew -Pandroid.testInstrumentationRunnerArguments.locationEnabled={config['location']} \
            -Pandroid.testInstrumentationRunnerArguments.wifiEnabled={config['wifi']} \
            -Pandroid.testInstrumentationRunnerArguments.mobileDataEnabled={config['mobiledata']} \
            -Pandroid.testInstrumentationRunnerArguments.bluetoothEnabled={config['bluetooth']} \
            -Pandroid.testInstrumentationRunnerArguments.batterySaverEnabled={config['batterysaver']} \
            -Pandroid.testInstrumentationRunnerArguments.autoRotateEnabled={config['autorotate']} \
            -Pandroid.testInstrumentationRunnerArguments.doNotDisturbEnabled={config['donotdisturb']} \
            -Pandroid.testInstrumentationRunnerArguments.cameraEnabled={config['camera']} \
            -Pandroid.testInstrumentationRunnerArguments.accelerometerEnabled={config['accelerometer']} \
            -Pandroid.testInstrumentationRunnerArguments.gyroscopeEnabled={config['gyroscope']} \
            -Pandroid.testInstrumentationRunnerArguments.lightEnabled={config['light']} \
            -Pandroid.testInstrumentationRunnerArguments.magneticFieldEnabled={config['magneticfield']} \
            -Pandroid.testInstrumentationRunnerArguments.orientationEnabled={config['orientation']} \
            -Pandroid.testInstrumentationRunnerArguments.proximityEnabled={config['proximity']} \
                connectedDebugAndroidTest"

        #print(command)
        print(check_output(command, shell=True).decode('cp850'))
        
        command2 = f"adb shell dumpsys battery reset"
        print(check_output(command2, shell=True).decode('cp850'))
  
        
        print(check_output(f"mkdir testReportsMult-Random/execution3_1/report{exec}", shell="True").decode('cp850'))
        print(check_output(f"cp -Rf app/build/reports testReportsMult-Random/execution3_1/report{exec}", shell="True").decode('cp850'))

        csv_file.seek(0)
        
        timeTakenConfig = perf_counter()-startTime_Config
        print(f"Time taken for the current config: {timeTakenConfig}") 
        execTimeFile.write(f"c{exec},{timeTakenConfig}")
        execTimeFile.write("\n")
        execTimeFile.flush()

execTimeFile.close()


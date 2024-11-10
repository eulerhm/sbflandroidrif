from subprocess import check_output
import timing 
from time import perf_counter

print(check_output(f"mkdir testReports-SeparatelyTests", shell="True").decode('cp850'))

execTimeFile = open("executionTime-SeparatelyTests.txt","a")

testMethods = ['com.google.android.gnd.AcceptTermsOfServiceTest#acceptTerms',
               'com.google.android.gnd.AddFeatureTest#tappingCrosshairOnEmptyMapDoesNothing',
               'com.google.android.gnd.MapTypeDialogTest#tappingMapTypeButton_shouldOpenDialog',
               'com.google.android.gnd.MapTypeDialogTest#selectingMapTypeItem_shouldUpdateBasemapType']

startTime = perf_counter()


for tm in testMethods:
    command1 = f"adb shell dumpsys battery unplug"
    print(check_output(command1, shell=True).decode('cp850'))

    command = f"./gradlew -Pandroid.testInstrumentationRunnerArguments.class={tm} \
            createDebugCoverageReport"

    #print(command)
    print(check_output(command, shell=True).decode('cp850'))

    command2 = f"adb shell dumpsys battery reset"
    print(check_output(command2, shell=True).decode('cp850'))


    print(check_output(f"mkdir testReports-SeparatelyTests/report-{tm}", shell="True").decode('cp850'))
    print(check_output(f"cp -Rf gnd/build/reports testReports-SeparatelyTests/report-{tm}", shell="True").decode('cp850'))


timeTaken = perf_counter()-startTime
print(f"Time taken: {timeTaken}") 
execTimeFile.write(f"c{exec},{timeTaken}")
execTimeFile.write("\n")
execTimeFile.flush()

execTimeFile.close()


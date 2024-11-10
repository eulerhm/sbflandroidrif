from subprocess import check_output
import timing 
from time import perf_counter

print(check_output(f"mkdir testReports-SeparatelyTests", shell="True").decode('cp850'))

execTimeFile = open("executionTime-SeparatelyTests.txt","a")


testMethods = ['com.health.openscale.gui.AddMeasurementTest#addMeasurementMaleTest',
                'com.health.openscale.gui.AddMeasurementTest#addMeasurementFemaleTest',
                'com.health.openscale.gui.ScreenshotRecorder#captureScreenshots',
                'com.health.openscale.DatabaseMigrationTest#migrate1To2',
                'com.health.openscale.DatabaseMigrationTest#migrate2To3',
                'com.health.openscale.DatabaseMigrationTest#migrate3To4',
                'com.health.openscale.DatabaseTest#userOperations',
                'com.health.openscale.DatabaseTest#measurementOperations',
                'com.health.openscale.TrisaBodyAnalyzeLibTest#getBase10FloatTests',
                'com.health.openscale.TrisaBodyAnalyzeLibTest#convertJavaTimestampToDeviceTests',
                'com.health.openscale.TrisaBodyAnalyzeLibTest#convertDeviceTimestampToJavaTests',
                'com.health.openscale.TrisaBodyAnalyzeLibTest#parseScaleMeasurementData_validUserData',
                'com.health.openscale.TrisaBodyAnalyzeLibTest#parseScaleMeasurementData_missingUserData',
                'com.health.openscale.TrisaBodyAnalyzeLibTest#parseScaleMeasurementData_invalidUserData']

startTime = perf_counter()

for tm in testMethods:
        command = f"./gradlew -Pandroid.testInstrumentationRunnerArguments.class={tm} \
                createDebugCoverageReport"

        #print(command)
        print(check_output(command, shell=True).decode('cp850'))

        print(check_output(f"mkdir testReports-SeparatelyTests/report-{tm}", shell="True").decode('cp850'))
        print(check_output(f"cp -Rf app/build/reports testReports-SeparatelyTests/report-{tm}", shell="True").decode('cp850'))


timeTaken = perf_counter()-startTime
print(f"Time taken: {timeTaken}") 
execTimeFile.write(f"Time taken: {timeTaken}")
execTimeFile.write("\n")
execTimeFile.flush()

execTimeFile.close()


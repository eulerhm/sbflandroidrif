from subprocess import check_output
import timing 
from time import perf_counter

print(check_output(f"mkdir testReports-SeparatelyTests-v2", shell="True").decode('cp850'))

execTimeFile = open("executionTime-SeparatelyTests.txt","a")


testMethods = ['fr.free.nrw.commons.MainActivityTest#testNearby',
               'fr.free.nrw.commons.MainActivityTest#testExplore',
               'fr.free.nrw.commons.MainActivityTest#testBookmarks',
               'fr.free.nrw.commons.MainActivityTest#testNotifications']

startTime = perf_counter()

for tm in testMethods:
        command = f"./gradlew -Pandroid.testInstrumentationRunnerArguments.class={tm} \
                -Djacoco.haltOnFailure=false -Djacoco.ignoreFailure=true createProdDebugCoverageReport"
        print(check_output(command, shell=True).decode('cp850'))

        print(check_output(f"mkdir testReports-SeparatelyTests-v2/report-{tm}", shell="True").decode('cp850'))
        print(check_output(f"cp -Rf app/build/reports testReports-SeparatelyTests-v2/report-{tm}", shell="True").decode('cp850'))


timeTaken = perf_counter()-startTime
print(f"Time taken: {timeTaken}") 
execTimeFile.write(f"c{exec},{timeTaken}")
execTimeFile.write("\n")
execTimeFile.flush()

execTimeFile.close()


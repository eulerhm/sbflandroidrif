import glob
from pyquery import PyQuery

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

for tm in testMethods:
    reportFile = glob.glob(f"/media/euler/SSD_2/workspace-SBES-ExtendedPaper/INJECTED FAULT ANALYSIS/openScale/openScale-Defeito-4/android_app/testReports-SeparatelyTests/report-{tm}/reports/androidTests/connected/flavors/debugAndroidTest/index.html")
    
    #reportFile = glob.glob('**/index.html',recursive=True)
    if reportFile:
        with open(reportFile[0]) as file_object:
            html = file_object.read()
            #print(html)
            doc = PyQuery(html)
            tagtext = doc("div").text()
            #chunks = tagtext.split('\n')
            chunks = tagtext.split()
            #print(chunks)
            if (int(chunks[4]) > 0):
                print("TOTAL FAILURES IN TEST REPORT " + tm + ": " + chunks[4])
            else:
                print("NO FAILURE!")

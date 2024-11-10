import glob
from pyquery import PyQuery

totalReports = 20

for x in range(totalReports):
    reportFile = glob.glob(f"/home/eulerhm/Documents/workspaceSQJ/RadioDroid/testReportsMult-Pairwise/execution3/report{x}/reports/androidTests/connected/flavors/freeDebugAndroidTest/index.html")
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
            #if (int(chunks[4]) > 1):
            print("TOTAL FAILURES IN TEST REPORT " + str(x) + ": " + chunks[4])

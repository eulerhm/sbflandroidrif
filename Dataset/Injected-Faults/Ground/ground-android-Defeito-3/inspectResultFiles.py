import glob
from pyquery import PyQuery

totalReports = 14

for x in range(totalReports):
    reportFile = glob.glob(f"/home/eulerhm/Documents/workspaceSQJ/EXECUCOES_REVISAO/ground-android/testReportsMult-OneEnabled/execution3/report{x}/reports/androidTests/connected/index.html")
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
            print("TOTAL FAILURES IN TEST REPORT " + str(x) + ": " + chunks[4])

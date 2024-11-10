import csv
import glob
from pyquery import PyQuery

#----Reading all configurations ------------------------------
inputFileName = 'configCombinations-v2.csv'

with open(inputFileName) as csv_file:
	csv_reader = csv.reader(csv_file, delimiter=',')
	line_count = 0
	allConfig = {}

	for row in csv_reader:
		config = {'sensors':False,'location':False,'wifi':False,'mobiledata':False,'bluetooth':False,'batterysaver':False,'autorotate':False,'donotdisturb':False} 
		
		for column in row:
			if (column != ''): 
				config[column] = True

		allConfig[line_count]=config
		line_count += 1

#print(allConfig)
#----Reading all configurations ------------------------------

#----Tabulating execution time of the reports -----------------------------
with open('configTestExecTime.csv', mode='w') as configTest_file:
	configTest_file_writer = csv.writer(configTest_file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)


	totalReports = 256
	totalExec = 1
	
	configTest_file_header = ["EXECUTION","CONFIG_ID","SENSORS","LOCATION","WIFI","MOBILEDATA","BLUETOOTH","BATTERYSAVER","AUTOROTATE","DONOTDISTURB","TIME"]
	configTest_file_writer.writerow(configTest_file_header)

	for exec in range(1,totalExec+1):
		print(f"Execution: {exec}")

		for x in range(totalReports):
			reportFile = glob.glob(f"/home/eulerhm/Documents/workspaceICST/openScale/android_app/testReportsMult/execution{exec}/report{x}/reports/androidTests/connected/flavors/debugAndroidTest//index.html")
			with open(reportFile[0]) as file_object:
				html = file_object.read()
				#print(html)
				doc = PyQuery(html)
				
				tagtext = doc("div").text()
				#chunks = tagtext.split('\n')
				chunks = tagtext.split()
				#print(chunks)
				print("EXECUTION TIME IN TEST REPORT " + str(x) + ": " + chunks[6])
				
				configVals = allConfig[x].values()
				
				testTime = []
				testTime.append(chunks[6])
				
				timeInSecondsStr = str(chunks[6])
				minutes = timeInSecondsStr[:timeInSecondsStr.index('m')]
				seconds = timeInSecondsStr[timeInSecondsStr.index('m')+1:timeInSecondsStr.index('.')]
				#print("Minutes: " + minutes + " - Seconds: " + seconds)
				timeInSeconds = int(minutes)*60 + int(seconds)
				#print("Time in seconds: " + str(timeInSeconds))
				
				timeInSecondsL = []
				timeInSecondsL.append(timeInSeconds)
                
				configTest_file_writer.writerow([exec]+[x]+list(configVals)+testTime+timeInSecondsL)


			#print(chunks2)
#----Tabulating execution time of the reports -----------------------------




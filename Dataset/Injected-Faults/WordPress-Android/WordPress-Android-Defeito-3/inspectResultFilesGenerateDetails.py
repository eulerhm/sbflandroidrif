import csv
import glob
from pyquery import PyQuery
from bitarray import bitarray

def setBitColumnName(columnName):
    global configDescription
    if (columnName == 'location'):
        configDescription[0] = 1
    elif (columnName == 'wifi'):
        configDescription[1] = 1
    elif (columnName == 'mobiledata'):
        configDescription[2] = 1
    elif (columnName == 'bluetooth'):
        configDescription[3] = 1
    elif (columnName == 'autorotate'):
        configDescription[4] = 1
    elif (columnName == 'batterysaver'):
        configDescription[5] = 1
    elif (columnName == 'donotdisturb'):
        configDescription[6] = 1
    elif (columnName == 'camera'):
        configDescription[7] = 1
    elif (columnName == 'accelerometer'):
        configDescription[8] = 1
    elif (columnName == 'gyroscope'):
        configDescription[9] = 1
    elif (columnName == 'light'):
        configDescription[10] = 1
    elif (columnName == 'magneticfield'):
        configDescription[11] = 1
    elif (columnName == 'orientation'):
        configDescription[12] = 1
    else:
        configDescription[13] = 1

def convertBitArrayToDecimal(myBitArray):
	#Solution from: https://stackoverflow.com/questions/42464514/how-to-convert-bitarray-to-an-integer-in-python
	j = 0
	for bit in myBitArray:
		j = (j << 1) | bit #Operator (<<): bitwise left shift; Operator (|): bitwise OR
	print(j)
	print(hex(j))
	return j

#----Reading all configurations ------------------------------
inputFileName = 'configCombinations-Pairwise_new.csv'

with open(inputFileName) as csv_file:
	csv_reader = csv.reader(csv_file, delimiter=',')
	line_count = 0
	allConfig = {}
	allConfigDescription = {}
	configDescription = bitarray(14,endian='big')

	for row in csv_reader:
		config = {'location':False,'wifi':False,'mobiledata':False,'bluetooth':False,'autorotate':False,'batterysaver':False,'donotdisturb':False, 'camera':False,'accelerometer':False,'gyroscope':False,'light':False,'magneticfield':False,'orientation':False,'proximity':False}

		configDescription.setall(0)

		i = 0
		for column in row:
			if (column != ''):
				config[column] = True
				setBitColumnName(column)
			i+=1

		allConfig[line_count]=config
		allConfigDescription[line_count] = convertBitArrayToDecimal(configDescription)

		print(configDescription)
		print()

		line_count += 1

#print(allConfig)
#----Reading all configurations ------------------------------

#----Finding failures on reports -----------------------------
with open('configTestResult.csv', mode='w') as configTest_file:
	configTest_file_writer = csv.writer(configTest_file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)


	totalReports = 8
	totalExec = 3

	configTest_file_header = ["EXECUTION","CONFIG_ID","GLOBAL_CONFIG_ID","LOCATION","WIFI","MOBILEDATA","BLUETOOTH","AUTOROTATE","BATTERYSAVER", "DONOTDISTURB","CAMERA","ACCELEROMETER","GYROSCOPE","LIGHT","MAGNETICFIELD","ORIENTATION","PROXIMITY","FAILED_TEST"]

	configTest_file_writer.writerow(configTest_file_header)

	for exec in range(1,totalExec+1):
		print(f"Execution: {exec}")

		for x in range(totalReports):
			reportFile = glob.glob(f"/home/eulerhm/Documents/workspaceSQJ/EXECUCOES_REVISAO/WordPress-Android/testReportsMult-Pairwise-new/execution{exec}/report{x}/reports/androidTests/connected/flavors/wordpressJalapeno/index.html")
			with open(reportFile[0]) as file_object:
				html = file_object.read()
				#print(html)
				doc = PyQuery(html)

				tagtext = doc("div").text()
				#chunks = tagtext.split('\n')
				chunks = tagtext.split()
				#print(chunks)
				print("TOTAL FAILURES IN TEST REPORT " + str(x) + ": " + chunks[4])

				#table = doc("tr").find("td.failures")
				tables = doc("div.tab")
				tab0 = tables.eq(0)
				tab0text = tables.eq(0).text()
				#chunks2 = tab0text.split('\n')
				chunks2 = tab0text.split()

				#print(chunks2)

				configVals = allConfig[x].values()

				if (chunks2[0] == "Failed"):
					print("FAILED TESTS:")
					print(chunks2[4:])
					#Concateneting Test Class with Test Name
					list_length = len(chunks2)
					paired_list = [chunks2[i] + "." + chunks2[i+1] for i in range(4, list_length-1, 2)]
					list_length2 = len(paired_list)
					print("PAIRED LIST:")
					print(paired_list)

					for y in range(list_length2):
						failedTestList = []
						failedTestList.append(paired_list[y])
						if ("ConnectedDevice.No" not in failedTestList and "tests.found." not in failedTestList and "AllTransactionsFragmentTest.testFilter" not in failedTestList):
							configTest_file_writer.writerow([exec]+[x]+[allConfigDescription[x]]+list(configVals)+failedTestList)
			#print(chunks2)
#----Finding failures on reports -----------------------------




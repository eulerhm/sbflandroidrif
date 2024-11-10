import csv
import glob
import json
from pyquery import PyQuery
from pathlib import Path

#----Reading all lines from result------------------------------
inputFileName = 'configTestResult.csv'

with open(inputFileName) as csv_file:
	csv_reader = csv.reader(csv_file, delimiter=',')
	failureCodes = {}

	next(csv_reader)

	failureCodesFile = Path("failureCodesDict.json")
	failureCodesFileExists = False

	try:
		failureCodesFile.resolve(strict=True)
	except FileNotFoundError:
		# failureCodes file doesn't exist
		print("failureCodesFile does not exist!")
		idCode = 0
	else:
		# failureCodes file exists
		#Performs the deserialization
		failureCodesFile = open("failureCodesDict.json")
		failureCodes = json.load(failureCodesFile)
		failureIdList = []
		for fId in failureCodes.values():
			failureIdList.append(int(fId[1:]))
		idCode = max(failureIdList)		
		#print(idCode)


	for row in csv_reader:
		if (row[17] not in failureCodes.keys()):
			idCode += 1
		
			failureId = 'F' + str(idCode)
			failureCodes[row[17]] = failureId
	
	#Performs the serialization
	with open("failureCodesDict.json","w") as failureCodesFile: 		
		json.dump(failureCodes, failureCodesFile)	 	

with open(inputFileName) as csv_file:
	csv_reader = csv.reader(csv_file, delimiter=',')
	line_count = 0
	allLines = {}

	next(csv_reader)

	for row in csv_reader:
		line = {'EXECUTION':-1,'CONFIG_ID':-1,'GLOBAL_CONFIG_ID':-1,'FAILURE_ID':''} 
		
		line['EXECUTION'] = row[0]
		line['CONFIG_ID'] = row[1]
		line['GLOBAL_CONFIG_ID'] = row[2]
		line['FAILURE_ID'] = failureCodes[row[17]]	

		allLines[line_count] = line
		line_count += 1

#print(allLines)
#----Reading all lines from result------------------------------

#----Tabulating failures ---------------------------------------
with open('tabulatedFailures.csv', mode='w') as tabFailures_file:
	tabFailures_file_writer = csv.writer(tabFailures_file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
	tabFailures_file_header = ["CONFIG_ID","GLOBAL_CONFIG_ID","FAILURE ID","EXEC1","EXEC2","EXEC3"]	
	tabFailures_file_writer.writerow(tabFailures_file_header)
	allFailures = {}
	failureNames = {}
	execInf = {}
	failureLocalConfigIds = {}

	for x in range(line_count):
		l = allLines[x]
		failureName = 'C' + l['GLOBAL_CONFIG_ID'] + '-' + l['FAILURE_ID']
		allFailures[failureName] = [0,0,0]

		failureNames[x] = failureName
		execInf[x] = l['EXECUTION']
		failureLocalConfigIds[failureName] = l['CONFIG_ID']

	for k in failureNames.keys():

		failureName = failureNames.get(k)

		if (execInf.get(k) == '1'):
			y = allFailures.get(failureName)
			y[0] = 1
			allFailures[failureName] = y
		elif (execInf.get(k) == '2'):
			y = allFailures.get(failureName)
			y[1] = 1
			allFailures[failureName] = y
		else:
			y = allFailures.get(failureName)
			y[2] = 1
			allFailures[failureName] = y

	#for z in allFailures.keys():
	# 	failureNameList = []
	# 	failureNameList.append(z)
	# 	tabFailures_file_writer.writerow(failureNameList+allFailures[z])

	for z in allFailures.keys():
		globalConfigIdList = []
		failureIdList = []
		localConfigIdList = []
		posHiphen = z.index('-')
		globalConfigIdList.append(z[0:posHiphen])
		failureIdList.append(z[posHiphen+1:])
		localConfigIdList.append(failureLocalConfigIds[z])
		tabFailures_file_writer.writerow(localConfigIdList+globalConfigIdList+failureIdList+allFailures[z])
			
	print(allFailures)
		

#----Tabulating failures ---------------------------------------




import csv
import math

baseDirectory = "/home/anonymous/Documents/PycharmProjects/pythonParseReport/Results/WordPress/"
inputFileName = 'mut_28409'
finalFileName = baseDirectory + inputFileName + '.csv'

with open(finalFileName) as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')

    outputFileName = baseDirectory + inputFileName + '_exp.csv'

    with open(outputFileName, mode='w') as coefficientRank_file:
        coefficientRank_file_writer = csv.writer(coefficientRank_file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
        coefficientRank_file_header = ["METHOD", "OCHIAI_COEFFICIENT", "RANK"]
        coefficientRank_file_writer.writerow(coefficientRank_file_header)

        next(csv_reader)

        current_coefficient = 0
        rank = 0

        for row in csv_reader:
            print(row)

            methodList = []
            coefficientList = []
            rankList = []

            methodList.append(row[0])
            coefficientList.append(row[1])

            if math.isclose(float(row[1]), current_coefficient):
                print(rank)
                coefficientList.append(rank)
            else:
                rank += 1
                print(rank)
                coefficientList.append(rank)
                current_coefficient = float(row[1])

            coefficientRank_file_writer.writerow(methodList + coefficientList + rankList)

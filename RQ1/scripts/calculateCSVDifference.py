# https://www.geeksforgeeks.org/compare-two-csv-files-using-python/
# https://stackoverflow.com/questions/16108526/how-to-obtain-the-total-numbers-of-rows-from-a-csv-file-in-python

import csv
from itertools import combinations

def sum1forline(filename):
    with open(filename) as f:
        next(f,None)
        return sum(1 for line in f)

# Function to compare two CSV files
def compare(file1, file2):
    differences = []

    # Open both CSV files in read mode
    with open(file1, 'r') as csv_file1, open(file2, 'r') as csv_file2:
        reader1 = csv.reader(csv_file1)
        reader2 = csv.reader(csv_file2)

        next(reader1,None)
        next(reader2,None)

        total_items_file1 = sum1forline(file1)
        #print(total_items_file1)
        total_items_file2 = sum1forline(file2)
        #print(total_items_file2)

        # Iterate over rows in both files simultaneously
        equal_items = 0
        for row1, row2 in zip(reader1, reader2):
            if row1 != row2:
                differences.append((row1, row2))
            else:
                equal_items += 1

        #print(f"Equal items: {equal_items}")
        percentual_difference = 1-equal_items/total_items_file1
        #print(f"Percentual of difference: {percentual_differences}")


    return percentual_difference

# Define file paths
app_name = "AnkiDroid"
file_path = f"/media/euler/SSD_2_Linux/PythonProjects/pythonParseReport/Results_RQs_4_5/{app_name}/ochiaiCoefficients-Setting"

files = []

for s in range(8):
    files.append(file_path + str(s) + ".csv")

unique_comb = combinations(range(8),2)

outputFile_path = file_path[0:file_path.rfind('/')]
outputFileName = outputFile_path + "/" + "Rank_Differences.csv"

with open(outputFileName, mode='w') as rankDifference_file:
        rankDifference_file_writer = csv.writer(rankDifference_file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
        rankDifference_file_header = ["SETTING_1", "SETTING_2", "DIFFERENCE"]
        rankDifference_file_writer.writerow(rankDifference_file_header)

        number_comb = 0
        for comb in unique_comb:
            setting1_list = []
            setting2_list = []
            rankDifference_list = []

            #print(comb)
            number_comb += 1
            
            first_file = files[comb[0]]
            setting1_list.append(comb[0])

            second_file = files[comb[1]]
            setting2_list.append(comb[1])

            percentual_difference = compare(first_file, second_file)
            rankDifference_list.append(percentual_difference)
            first_file_name = first_file[first_file.rfind('/')+1:]
            second_file_name = second_file[second_file.rfind('/')+1:]
            print(f"Files: {first_file_name} and {second_file_name}: {percentual_difference}")
            print()
            rankDifference_file_writer.writerow(setting1_list + setting2_list + rankDifference_list)

print(f"Total combinations: {number_comb}")

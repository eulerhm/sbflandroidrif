import csv
import json
from collections import defaultdict
from xml.dom.pulldom import parse, START_ELEMENT


# Check if the method was covered in test execution
def process_method_coverage(parent):
    counter_tags = parent.getElementsByTagName("counter")
    for ct in counter_tags:
        if ct.getAttribute("type") == "METHOD" and int(ct.getAttribute("covered")) > 0:
            # print(ct.toxml())
            return True


# Check if the method was covered in test execution
def process_method_line(parent):
    return int(parent.getAttribute("line"))


def extract_class_name(full_classname):
    last_slash = full_classname.rfind("/")
    short_class_name = full_classname[last_slash + 1:]
    # print(f"NAME: {class_name}")
    return short_class_name


def extract_test_name(full_path):
    hash_character = full_path.find("#")
    test_name = full_path[hash_character + 1:]
    # print(f"TEST NAME: {test_name}")
    return test_name


coverage_info = defaultdict(list)

method_line_info = {}

method_counter_info = {}

coverage_report_file_name = f"/Users/anonymous/PycharmProjects/pythonInspectMutantCoverage/Reports/AnkiDroid/" \
                            f"coverage/report.xml"

event_stream = parse(coverage_report_file_name)

test_outcome = True  # Test failed

for event, node in event_stream:
    # print(event, node)
    if event == START_ELEMENT:
        if node.tagName == "class":
            attr_class_name = node.getAttribute("name")
        if node.tagName == "method":
            event_stream.expandNode(node)
            attr_method_name = node.getAttribute("name")
            class_name = extract_class_name(attr_class_name)
            test_name = "ANY_TEST"
            if process_method_coverage(node):
                test_exec = ((test_name.upper(), True), test_outcome)  # Format ((TestName, ElementHit),
                # TestOutcome)
            else:
                test_exec = ((test_name.upper(), False), test_outcome)

            key = class_name + "#" + attr_method_name

            method_line = process_method_line(node)

            if key not in method_line_info:
                method_line_info[key] = method_line
                method_counter_info[key] = 1
            else:
                key_found = False

                for i in range(1, method_counter_info[key] + 1):
                    tempKey = key

                    if i != 1:
                        tempKey += "#" + str(i)

                    if method_line_info[tempKey] == method_line:
                        key_found = True

                if not key_found:
                    method_counter_info[key] += 1
                    key += "#" + str(method_counter_info[key])
                    method_line_info[key] = method_line

            # print(f"Method: {key} Line: {method_line}")

            coverage_info[key].append(test_exec)

# print(coverage_info)
with open("coverageInfo.json", "w") as coverageInfoFile:
    json.dump(coverage_info, coverageInfoFile)

mutant_catalog_file_name = f"/Users/anonymous/PycharmProjects/pythonInspectMutantCoverage/Reports/AnkiDroid/" \
                           f"mutants/mutants-catalog.txt"

mutant_info = {}

mutant_line_info = defaultdict(list)

mutant_counter_info = {}

with open(mutant_catalog_file_name) as mutant_catalog_file:
    mutant_catalog_reader = csv.reader(mutant_catalog_file, delimiter=',')

    for row in mutant_catalog_reader:
        mutant_id = row[0]
        mutant_operator = row[1]
        full_class_name = row[2]
        lastDotIndex = full_class_name.rfind('.') + 1
        class_name = full_class_name[lastDotIndex:]

        if mutant_operator != "SBR": # Excluding Expression Removal
            full_method_name = row[3]
            if "null" not in full_method_name:
                if "__nrs" not in full_method_name:
                    auxIndex = len(full_method_name)
                    lastUnderlineIndex = full_method_name.rfind('_')
                else:
                    auxIndex = full_method_name.rfind("__")
                    lastUnderlineIndex = full_method_name[:auxIndex].rfind("_")

                method_name = full_method_name[:lastUnderlineIndex]

                mutant_line = full_method_name[lastUnderlineIndex + 1:auxIndex]
                # print(f"Mutant Line: {mutant_line}")

            else:
                method_name = "null"
                mutant_line = -1

            # print(f"Class: {class_name} - Method: {method_name}")

            mut_key = class_name + "#" + method_name

            if mut_key not in mutant_line_info:
                mutant_line_info[mut_key].append(mutant_line)
                mutant_counter_info[mut_key] = 1
                mutant_info[mut_key] = mutant_id
            else:
                key_found = False

                for i in range(1, mutant_counter_info[mut_key] + 1):
                    tempKey = mut_key

                    if i != 1:
                        tempKey += "#" + str(i)

                    if mutant_line in mutant_line_info[tempKey]:
                        key_found = True

                if not key_found:
                    mutant_counter_info[mut_key] += 1
                    mut_key += "#" + str(mutant_counter_info[mut_key])
                    mutant_info[mut_key] = mutant_id

            # if mut_key not in mutant_line_info:
            #    mutant_line_info[mut_key].append(mutant_line)
            #    mutant_counter_info[mut_key] = 1
            #    mutant_info[mut_key] = mutant_id
            # else:
            #    if mutant_line not in mutant_line_info[mut_key]:
            #        mutant_line_info[mut_key].append(mutant_line)
            #        mutant_counter_info[mut_key] += 1
            #        mut_key += "#" + str(mutant_counter_info[mut_key])
            #        mutant_info[mut_key] = mutant_id

# print(mutant_info)

with open('mutantCoverage.csv', mode='w') as mutCoverage_file:
    mutCoverage_file_writer = csv.writer(mutCoverage_file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
    mutCoverage_file_header = ["MUTANT_ID", "MUTATED METHOD"]
    mutCoverage_file_writer.writerow(mutCoverage_file_header)
    for minfo in mutant_info:
        # print(f"Searched mutant {minfo} - id {mutant_info[minfo]}: {c_info}")
        c_info = coverage_info[minfo]
        for ci in c_info:
            if ci[0][1]:
                id_list = []
                method_list = []
                id_list.append(mutant_info[minfo])
                method_list.append(minfo)
                mutCoverage_file_writer.writerow(id_list+method_list)

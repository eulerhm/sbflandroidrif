import numpy as np
import matplotlib.pyplot as plt 

 
# creating the dataset
data = {'ROR':39, 'AOR':18, 'LCR':13}

operators = list(data.keys())
values = list(data.values())
 
#fig = plt.figure(figsize = (10, 5))
fig, ax = plt.subplots(figsize=(10, 5))

# creating the bar plot
plt.bar(operators, values, color ='gray', width = 0.4)

ax.tick_params(axis='both', which='major', labelsize=36)
plt.xlabel("Mutation Operators",fontsize=36)
plt.ylabel("Occurrences",fontsize=36)
plt.title("Occurrences of mutation operators in ranked faults",fontsize=36)
plt.show()
 

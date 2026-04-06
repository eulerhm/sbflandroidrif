import numpy as np
import matplotlib.pyplot as plt 

 
# creating the dataset
data = {'IF-CC':5, 'MC-DAP':3, 'AS-CE':2, 'IF-APC':2}

fix_patterns = list(data.keys())
values = list(data.values())
 
#fig = plt.figure(figsize = (10, 5))
fig, ax = plt.subplots(figsize=(10, 5))

# creating the bar plot
plt.bar(fix_patterns, values, color ='gray', width = 0.4)

ax.tick_params(axis='both', which='major', labelsize=36)
plt.xlabel("Bug Fix Patterns",fontsize=36)
plt.ylabel("Occurrences",fontsize=36)
plt.title("Occurrences of bug fix patterns in ranked faults",fontsize=36)
plt.show()
 

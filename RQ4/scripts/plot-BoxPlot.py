#import library
import pandas as pd
import matplotlib.pyplot as plt

#add csv file to dataframe
#df = pd.read_csv('OchiaiAllApps-Mutants.csv')

df = pd.read_csv('OchiaiAllApps-InjectedFaults.csv')

#create boxplot
boxplot = df.boxplot(figsize = (5,5), grid = False)

plt.show()

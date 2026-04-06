# importing package
import matplotlib.pyplot as plt
import numpy as np

# Set the global font size
plt.rcParams.update({'font.size': 18}) # Adjust 14 to your desired size

# create data
y = ["AnkiDroid", "Commons", "Ground", "OneBusAway", "OpenScale", "OwnTracks", "PocketHub", "RadioDroid", "Threema", "WordPress"]

x1 = np.array([16, 0, 13, 16, 28, 4, 10, 28, 4, 0]) # 0%
x2 = np.array([0, 1, 0, 0, 0, 2, 0, 0, 0, 0]) # (0%,25%]
x3 = np.array([0, 2, 0, 0, 0, 0, 0, 0, 1, 1]) # (25%,50%]
x4 = np.array([12, 0, 15, 0, 0, 6, 5, 0, 3, 3]) # (50%,75]
x5 = np.array([0, 25, 0, 12, 0, 16, 13, 0, 20, 24]) # (75%,100%]

# plot bars in stack manner
plt.barh(y, x1, color='antiquewhite')
plt.barh(y, x2, left=x1, color='seashell')
plt.barh(y, x3, left=x1+x2, color='lightgray')
plt.barh(y, x4, left=x1+x2+x3, color='darkgray')
plt.barh(y, x5, left=x1+x2+x3+x4, color='dimgray')

plt.xlabel("Difference")
plt.ylabel("Applications")
plt.legend(["0%", "(0%,25%]", "(25%,50%]", "(50%,75%]", "(75%,100%]"])
plt.title("Difference ranges")
plt.show()

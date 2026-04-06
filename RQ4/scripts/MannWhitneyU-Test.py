# import useful library
from scipy.stats import mannwhitneyu

data_group1_mutants = [0.29, 0.29, 0.29, 0.29, 0.29, 0.00, 0.00, 0.00, 0.00, 0.00, 1.0, 1.0, 1.0, 1.0, 0.0, 0.10, 0.10, 0.10, 0.10, 0.10, 0.58, 0.58, 0.58, 0.50, 
0.50, 0.91, 0.41, 0.82, 0.82, 1.00, 0.21, 0.95, 0.83, 1.00, 1.00, 0.28]

data_group2_mutants = [0.29, 0.29, 0.20, 0.20, 0.00, 0.00, 0.28, 0.28, 0.82, 0.32, 0.71, 0.12, 0.12, 0.0, 0.0, 0.0, 0.0, 0.10, 0.10, 0.58, 1.00,
1.00, 0.58, 0.38, 1.00, 0.50, 0.50, 0.50, 0.71, 0.27, 0.98, 0.47, 0.98, 0.98, 1.00, 0.47, 1.00, 0.74, 0.75, 1.00, 1.00, 0.35,
0.35, 0.52, 0.52, 0.73, 0.73, 0.25]

data_group1_injected_faults = [0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.26, 0.31, 0.0]

data_group2_injected_faults = [0.0, 0.0, 0.0, 0.19, 1.0, 1.0, 0.81, 1.0, 0.0]


# conduct the Mann-Whitney-U Test
print(mannwhitneyu(data_group1_mutants,data_group2_mutants,alternative='two-sided'))
print()
print(mannwhitneyu(data_group1_injected_faults,data_group2_injected_faults,alternative='two-sided'))
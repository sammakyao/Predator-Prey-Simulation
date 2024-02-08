# ComputerSci-II

#This program simulates predator/prey population dynamics over time. The simulation runs for a bit in order to collect data about the sizes of your populations, write this data to file, and then use python to plot the data as a line chart. The simulation has:

An new type, Animal, that contains attributes and methods for a general animal
Subtypes, Predator and Prey (or Lynx and Hare, or anything that appeals), that override and/or overload any behaviors that need to be more specific
A class that handles data storage, formatting, and output to file
A class that ties everything together, and allows you to run your simulation over multiple time steps.
Each time step should do the necessary calculations and updates to simulate the core behaviors:
Animals should all reproduce (when possible)
Animals that are too old should die off
Animals should all eat

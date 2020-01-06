# DEVELOPMENT-OF-A-CITY-FOR-WAYNE-ENTERPRISES
The main idea of this project was to develop a city for Wayne Enterprises. This was done using a RED-BLACK TREE AND MINIMUM HEAP. Also, we must keep a track of what all buildings are been constructed and what buildings have to be constructed in the future. This depends upon the building records.

buildingNum: unique integer identifier for each building.
executed_time: total number of days spent so far on this building.
total_time: the total number of days needed to complete the construction of the building.


The operations done on it are:
1. Print (buildingNum) prints the triplet buildingNume,executed_time,total_time.
2. Print (buildingNum1, buildingNum2) prints all triplets bn, executed_tims, total_time for which buildingNum1 <= bn <= buildingNum2.
3. Insert (buildingNum,total_time) where buildingNum is different from existing building numbers and executed_time = 0.


A min heap stores (buildingNums,executed_time,total_time) triplets ordered by executed_time.
An RBT should stores (buildingNums,executed_time,total_time) triplets ordered by buildingNum.
Here, each building is given a time of 5 days to work on and then we move on to the next building to work on if present. The building is chosen based on lowest executed_time.
The above project is written in Java.

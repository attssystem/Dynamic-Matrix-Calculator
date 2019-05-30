# Dynamic-Matric-Calculator

## Description

We're trying to conceive a matrix calculator which is able to perform most of the operations you can apply on matrixes and between them. Moreover we build the whole program behind a "command prompt" kind of interface.

## UML Diagrams

| MainMatrix     	 | Matrix    |
| :-------------     | :---------- |
| *Variables* | *Variables* |
| + boolean quit | + double[][] data |
| + Matrix[] matricesTab | + String name |
| + Matrix[] vectorsTab | |
| + String cmd | |
| + String info | |
| + String name | |
| + String name2 | |
| + int a | |
| + int b | |
| + int id1 | |
| + int id2 | |
| *Functions* | *Functions* |
| + void showMatrices(Matrix[], Scanner) | + Matrix gaussJourdan()	|
| + boolean showMatrix(int, Matrix[], boolean, boolean, Scanner)  	 | + Matrix reverse() |
| + int searchMatrix(String, Matrix[]) | + Matrix multiplyM(Matrix) |
| + boolean dynamicEditor(String, Matrix[], Scanner, String) | + Matrix multiplyS(int)			|
| + Matrix[] saveTemp(Matrix, Matrix[]) | + double[] eigenValues() |
| + Matrix[] updateTab(Matrix[], Matrix) | + getSousMatrice(int) |
| + void pause(Scanner) | + double determinant()|
| + void clearScreen() | + Matrix transpose() |
|						| + double trace() |
|					| + double[] getColumn(int) |
|					| + double[] getLine(int) |
|					| + double[] getDiagonal() |
|					| + boolean testCarree() |

## Authors
- Hugues KADI | ATTSSystem
	--UI, cmd interpreter and some matrix functions
- Camille MARTIN | AstraleK
	--Matrix object, most of matric functions
- Gautier ESCRIVA

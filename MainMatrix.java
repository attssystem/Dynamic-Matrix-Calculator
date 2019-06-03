import java.util.Scanner;
import java.util.Arrays; 
import java.text.DecimalFormat;

public class MainMatrix {
	
	public static void main (String args[]) {
		boolean quit = false;
		Matrix[] matricesTab = new Matrix[0];
		Matrix[] vectorsTab = new Matrix[1];
		String cmd;
		String info;
		String name;
		String name2;
		int a = -1;
		int b = -1;
		int id1;
		int id2;
        
        Scanner sc = new Scanner(System.in);
        
        // Tests
        
        matricesTab = updateTab(matricesTab, new Matrix(3,3,"TEST", true));
		
		// Starting UI
		
		clearScreen();
		info = "To begin, you can create your first Matrix using the 'CREATE' command\nand by letting the program guide you through the creation process,\nthen you can use 'EDIT', 'CALC', 'CREATE', 'SHOW', 'SHOW_ALL' again, 'HELP' or 'QUIT'.";
		System.out.println();
        
        while(!quit) {
			System.out.println("Info : "+info);
			System.out.print("CMD>");
			cmd = sc.next();
			Scanner mainWhile_sc = new Scanner(cmd);
			mainWhile_sc.useDelimiter("-");
			clearScreen();
			if(mainWhile_sc.hasNext("EDIT") || mainWhile_sc.hasNext("edit")){
				System.out.print("Matrix Name ? >");
				cmd = sc.next();
				if(!dynamicEditor(cmd, matricesTab, sc, info)){
					info = "Error while searching matrix";
				}
			}else if(mainWhile_sc.hasNext("CALC") || mainWhile_sc.hasNext("calc")){
				if(mainWhile_sc.hasNext()){
					mainWhile_sc.next();
					name = mainWhile_sc.next();
					id1 = searchMatrix(name, matricesTab);
					if(id1 != -1){
						if(mainWhile_sc.hasNext("DET")){
							if(matricesTab[id1].testCarree()){
								clearScreen();
								System.out.println(name+"'s det = "+matricesTab[id1].determinant());
								pause(sc);
							}else{
								info = "The matrix isn't squared";
							}
						}else if(mainWhile_sc.hasNext("COL")){
							mainWhile_sc.next();
							if(mainWhile_sc.hasNextInt()){
								id2 = mainWhile_sc.nextInt();
								if(id2 > 0 && id2 <= matricesTab[id1].data[0].length){
									showResult(matricesTab[id1].getColumn(id2-1), sc);
								}else{
									info = "CALC-matrix1-COL waits as last parameter the column you're looking for [1;n]";
								}
							}else{
								info = "CALC-matrix1-COL waits as last parameter the column you're looking for [1;n]";
							}
						}else if(mainWhile_sc.hasNext("LINE")){
							mainWhile_sc.next();
							if(mainWhile_sc.hasNextInt()){
								id2 = mainWhile_sc.nextInt();
								if(id2 > 0 && id2 <= matricesTab[id1].data.length){
									showResult(matricesTab[id1].getLine(id2-1), sc);
								}else{
									info = "CALC-matrix1-LINE waits as last parameter the line you're looking for [1;m]";
								}
							}else{
								info = "CALC-matrix1-LINE waits as last parameter the line you're looking for [1;m]";
							}
						}else if(mainWhile_sc.hasNext("DIAG")){
							if(matricesTab[id1].getDiagonal() == null){
								info = "The matrix isn't squared";
							}else{
								showResult(matricesTab[id1].getDiagonal(), sc);
							}
						}else if(mainWhile_sc.hasNext("VAL")){
							// Can't compute eigenval for now
						}else if(mainWhile_sc.hasNext("VEC")){
							// Can't compute eigenvec for now
						}else if(mainWhile_sc.hasNext("REV")){
							if(matricesTab[id1].testCarree() && matricesTab[id1].determinant() != 0){
								matricesTab = saveTemp(matricesTab[id1].reverse(), matricesTab);
								showMatrix(matricesTab.length-1, matricesTab, true, sc);
							}else{
								info = "The matrix isn't squared";
							}
						}else if(mainWhile_sc.hasNext("TRANS")){
							matricesTab = saveTemp(matricesTab[id1].transpose(), matricesTab);
							showMatrix(matricesTab.length-1, matricesTab, true, sc);
						}else if(mainWhile_sc.hasNext("RANK")){
							matricesTab = saveTemp(matricesTab[id1].rank(), matricesTab);
							showMatrix(matricesTab.length-1, matricesTab, true, sc);
						}else if(mainWhile_sc.hasNext("MULS")){
							mainWhile_sc.next();
							if(mainWhile_sc.hasNextInt()){
								id2 = mainWhile_sc.nextInt();
								matricesTab = saveTemp(matricesTab[id1].multiplyS(id2), matricesTab);
								showMatrix(matricesTab.length-1, matricesTab, true, sc);
							}else{
								info = "CALC-matrix1-MULS waits as last parameter the scalar you want to multiply with";
							}
						}else if(mainWhile_sc.hasNext("MULM")){
							mainWhile_sc.next();
							if(mainWhile_sc.hasNext()){
								name2 = mainWhile_sc.next();
								id2 = searchMatrix(name2, matricesTab);
								if(id2 != -1){
									if(matricesTab[id1].multiplyM(matricesTab[id2]) != null){
										matricesTab = saveTemp(matricesTab[id1].multiplyM(matricesTab[id2]), matricesTab);
										showMatrix(matricesTab.length-1, matricesTab, true, sc);
									}else{
										info = "This function only works if matrix1_n = matrix2_m";
									}
								}else{
									info = "CALC-matrix1-MULM waits as last parameter the matrix2's name you want to multiply with";
								}
							}else{
								info = "CALC-matrix1-MULM waits as last parameter the matrix2's name you want to multiply with";
							}
						}else if(mainWhile_sc.hasNext("DIV")){
							mainWhile_sc.next();
							if(mainWhile_sc.hasNext()){
								name2 = mainWhile_sc.next();
								id2 = searchMatrix(name2, matricesTab);
								if(id2 != -1){
									if(matricesTab[id1].divide(matricesTab[id2]) != null){
										matricesTab = saveTemp(matricesTab[id1].divide(matricesTab[id2]), matricesTab);
										showMatrix(matricesTab.length-1, matricesTab, true, sc);
									}else{
										info = "This function only works if matrix1_n = matrix2_m";
									}
								}else{
									info = "CALC-matrix1-DIV waits as last parameter the matrix2's name you want to divide by";
								}
							}else{
								info = "CALC-matrix1-DIV waits as last parameter the matrix2's name you want to divide by";
							}
						}else if(mainWhile_sc.hasNext("ADD")){
							mainWhile_sc.next();
							if(mainWhile_sc.hasNext()){
								name2 = mainWhile_sc.next();
								id2 = searchMatrix(name2, matricesTab);
								if(id2 != -1){
									if(matricesTab[id1].add(matricesTab[id2]) != null){
										matricesTab = saveTemp(matricesTab[id1].add(matricesTab[id2]), matricesTab);
										showMatrix(matricesTab.length-1, matricesTab, true, sc);
									}else{
										info = "This function only works with matrices which have the same size";
									}
								}else{
									info = "CALC-matrix1-ADD waits as last parameter the matrix2's name you want to add";
								}
							}else{
								info = "CALC-matrix1-ADD waits as last parameter the matrix2's name you want to add";
							}
						}else if(mainWhile_sc.hasNext("SUB")){
							mainWhile_sc.next();
							if(mainWhile_sc.hasNext()){
								name2 = mainWhile_sc.next();
								id2 = searchMatrix(name2, matricesTab);
								if(id2 != -1){
									if(matricesTab[id1].subtract(matricesTab[id2]) != null){
										matricesTab = saveTemp(matricesTab[id1].subtract(matricesTab[id2]), matricesTab);
										showMatrix(matricesTab.length-1, matricesTab, true, sc);
									}else{
										info = "This function only works with matrices which have the same size";
									}
								}else{
									info = "CALC-matrix1-SUB waits as last parameter the matrix2's name you want to subtract";
								}
							}else{
								info = "CALC-matrix1-SUB waits as last parameter the matrix2's name you want to subtract";
							}
						}else{
							info = "CALC function waits for a valid command to process";
						}
					}else{
						info = "Matrix "+name+" doesn't exist";
					}
				}else{
					info = "CALC function waits for a command to process";
				}
			}else if(mainWhile_sc.hasNext("CREATE") || mainWhile_sc.hasNext("create")){
				System.out.print("Which size ? (x,y)>");
				cmd = sc.next();
				Scanner size_sc = new Scanner(cmd);
				size_sc.useDelimiter(",");
				// Check the input syntax
				if(size_sc.hasNextInt()) {
					a = size_sc.nextInt();
				}
				if(size_sc.hasNextInt()) {
					b = size_sc.nextInt();
				}
				name = "deleted";
				while(name.equals("deleted")){				// Check for an already existing matrix
					System.out.print("Which name ?>");
					name = sc.next();
					if(searchMatrix(name, matricesTab) != -1){
						name="deleted";
						System.out.println("Matrix already exist, choose another name");
					}
				}
				System.out.print("How ? 'RANDOM', 'VECTORIAL' or 'CUSTOMISED' ? >");
				cmd = sc.next();
				Scanner createWhile_sc = new Scanner(cmd);
				if(createWhile_sc.hasNext("RANDOM") || createWhile_sc.hasNext("random")){
					matricesTab = updateTab(matricesTab, new Matrix(a, b, name, true));
					showMatrix(searchMatrix(name, matricesTab), matricesTab, true, sc);
				}else if(createWhile_sc.hasNext("VECTORIAL") || createWhile_sc.hasNext("vectorial")){
					matricesTab = updateTab(matricesTab, new Matrix(a, b, name, false));
					//Using specifically sized matrix instead of vectors
					for(int i = 0; i < b; i++){
						vectorsTab[0] = new Matrix(a, 1, "Vector", false);
						dynamicEditor("Vector", vectorsTab, sc, info);
						for(int j = 0; j < a; j++){
							matricesTab[searchMatrix(name, matricesTab)].data[j][i] = vectorsTab[0].data[j][0];
						}
					}
				}else if(createWhile_sc.hasNext("CUSTOMISED") || createWhile_sc.hasNext("customised")){
					matricesTab = updateTab(matricesTab, new Matrix(a, b, name, false));
					dynamicEditor(name, matricesTab, sc, info);
				}else{
					info = "Unknown command, went back to main menu";
				}
			}else if(mainWhile_sc.hasNext("SHOW_ALL") || mainWhile_sc.hasNext("show_all")){
				showMatrices(matricesTab, sc);
			}
			else if(mainWhile_sc.hasNext("SHOW") || mainWhile_sc.hasNext("show")){
				System.out.print("Matrix Name ? >");
				cmd = sc.next();
				if(!showMatrix(searchMatrix(cmd, matricesTab), matricesTab, true, sc)){
					info = "Error while searching matrix";
				}
			}else if(mainWhile_sc.hasNext("HELP") || mainWhile_sc.hasNext("help")){
				System.out.println("_'CREATE' allows you to create a new matrix, you'll be guided through the whole process\n__'RANDOM' create a matrix composed of N-values from -99 to 99\n__'VECTORIAL' allows you to create the column vectors of your matrix\n__'CUSTOMISED' allows you to edit your empty matric\n_'EDIT' allows you to edit a matrix, you'll be asked the matrix's name (case sensitive)\n_'SHOW_ALL' displays all the matrixes\n_'SHOW' displays one matrix, you'll be asked the matrix's name (case sensitive)\n_'CALC' allows you to compute matrices and waits parameters separated by '-' like 'CALC-matrix-DET'\n__'matrix1 name' takes the first matrix to comupte\n___'DET' returns the determinant of matrix1\n___'VAL' returns the eigenvalues of matrix1 (only for 2x2 matrixes)\n___'VEC' returns the eigenvectors of matrix1 (only for 2x2 matrixes)\n___'RANK' returns the ranked matrix of matrix1\n___'REV' saves and displays the reversed matrix of matrix1\n___'TRANS' saves and displays the transposed matrix of matrix1\n___'MULM' multiplies matrix1 by matrix2, waits for matrix2's name as last parameter\n___'MULS' multiplies matrix1 by a scalar, waits for a scalar as last parameter\n___'DIV' divides matrix1 by matrix2, waits for matrix2's name as last parameter\n___'ADD' adds matrix1 to matrix2, waits for matrix2's name as last parameter\n___'SUB' subtracts matrix2 to matrix1, waits for matrix2's name as last parameter\n____'matrix2 name' takes the second matrix to compute (only for MULM, DIV, ADD, SUB)\n____'scalar' takes a scalar to compute MULS operation\n_'QUIT' stops the programm\n\nIMPORTANT NOTES :\n_If you edit a matrix, all its computed versions (like transposed or reversed) will be deleted but not the multiplications, subtractions, additions or divisions\n\n");
				pause(sc);
			}else if(mainWhile_sc.hasNext("QUIT") || mainWhile_sc.hasNext("quit")){
				quit = true;
			}else{
				info = "Unknown command, try 'CREATE', 'EDIT', 'CALC', 'SHOW', 'SHOW_ALL', 'HELP' or 'QUIT'";
			}
            clearScreen();
        }
        
        System.out.println("Thanks for using");
        sc.close();
	}
  
	// Show all matrices
	
    public static void showMatrices(Matrix[] matricesTab, Scanner sc) {
        for(int i = 0; i < matricesTab.length; i++) {
			if(!matricesTab[i].name.equals("deleted")) {
				showMatrix(i, matricesTab, false, sc);
				System.out.println();
			}
        }
        pause(sc);
    }
    
    // Show a result which doesn't belong to the matricesTab
    
    public static void showResult(double[] result, Scanner sc) {
		for(int i = 0; i < result.length; i++) {
			System.out.println((i+1)+" ["+result[i]+"]");
		}
		pause(sc);
	}
    
    // Show one identified matrix
    
    public static boolean showMatrix(int id, Matrix[] matricesTab, boolean pause, Scanner sc) {
        DecimalFormat df = new DecimalFormat("###.##");
        String temp;
        if(id != -1) {
			System.out.println("| Matrix name :"+matricesTab[id].name);
			System.out.println();
			System.out.print("    ");
			for(int i = 0; i < matricesTab[id].data[0].length; i++) {
				System.out.print("  "+(i+1));
				for(int k = 0; k < 6-String.valueOf((i+1)).length(); k++){
					System.out.print(" ");
				}
			}
			System.out.println();
			for(int i = 0; i < matricesTab[id].data.length; i++) {
				System.out.print(" "+(i+1)+" ");
				System.out.print("|");
				for(int j = 0; j < matricesTab[id].data[0].length; j++) {
					temp = df.format(matricesTab[id].data[i][j]);
					System.out.print(" "+temp+" ");
					for(int k = 0; k < 6-String.valueOf(temp).length(); k++){
						System.out.print(" ");
					}
				}
				System.out.println("|");
			}
			System.out.println();
			if(pause){
				pause(sc);
			}
			return true;
		}else {
			return false;
		}
    }
    
    // Look for a specific matrix using his name
    
    public static int searchMatrix(String name, Matrix[] matricesTab) {
        int id = -1;
        int i = 0;
        while(id == -1 && i < matricesTab.length) {
			if(matricesTab[i].name.equals(name)){
				id = i;
			}
            i++;
        }
        return id;
	}
	
	// Dynamic Editor
	
	public static boolean dynamicEditor(String name, Matrix[] matricesTab, Scanner sc, String info) {
		int id = searchMatrix(name, matricesTab);
		System.out.println(id);
		if(id == -1){
			return false;
		}else{
			String com = "Type x,y,new_value or 0 to leave";
			boolean quit = false;
			String input;
			int a = -1;
			int b = -1;
			int c = -1;
			while(!quit) {
				clearScreen();
				System.out.println("------------------");
				System.out.println("| DYNAMIC EDITOR |");
				System.out.println("------------------");
				System.out.println("| Info :"+com);
				showMatrix(id, matricesTab, false, sc);
				System.out.print("DynEdit_CMD> ");
				input = sc.next();
				Scanner dynEdit_sc = new Scanner(input);
				dynEdit_sc.useDelimiter(",");
				// Check the input syntax
				if(dynEdit_sc.hasNextInt()) {
					a = dynEdit_sc.nextInt();
				}
				if(dynEdit_sc.hasNextInt()) {
					b = dynEdit_sc.nextInt();
				}
				if(dynEdit_sc.hasNextInt()) {
					c = dynEdit_sc.nextInt();
				}
				// Compute inputs
				if(a == 0) {
					dynEdit_sc.close();
					quit = true;
					info = "To begin, you can create your first Matrix using the 'CREATE' command\nand by letting the program guide you through the creation process,\nthen you can use 'EDIT', 'CALC', 'CREATE' again, 'SHOW', 'SHOW_ALL' or 'QUIT'.";
				}else{
					dynEdit_sc.close();
					if(a <= matricesTab[id].data.length && 0 < a && b <= matricesTab[id].data[0].length && 0 < b) {
						matricesTab[id].data[a-1][b-1] = c;
						com = "Change done !";
					}else {
						com = "Wrong input, retry (type 0 to leave)";
					}
				}
			}
			// Delete computed matrixes from the edited one
			id = searchMatrix(name+"_ech", matricesTab);
			if(id != -1) {
				matricesTab[id].name = "deleted";
			}
			id = searchMatrix(name+"_trans", matricesTab);
			if(id != -1) {
				matricesTab[id].name = "deleted";
			}
			id = searchMatrix(name+"_rev", matricesTab);
			if(id != -1) {
				matricesTab[id].name = "deleted";
			}
			return true;
		}
	}
	
	// Save calculated temp matrices as a user matrix
	
	public static Matrix[] saveTemp(Matrix m, Matrix[] matricesTab){
		Matrix[] container = {new Matrix(m.data.length, m.data[0].length, m.name, false)};
		for (int i = 0; i < m.data.length; i++){
			for (int j = 0; j < m.data[0].length; j++){
				container[0].data[i][j] = m.data[i][j];
			}
		}
		return updateTab(matricesTab, container[0]);
	}
	
	// Update function which enlarge matrices tab and add the last one
	
	public static Matrix[] updateTab(Matrix[] matricesTab, Matrix m) {
		Matrix[] tab = Arrays.copyOf(matricesTab, matricesTab.length+1);
		tab[tab.length-1] = m;
		return tab;
	}
	
	// Block program
	
	public static void pause(Scanner sc) {
		System.out.println("Type 'quit' or 'QUIT' to go back to menu");
        sc.next();
	}
	
	// Clear the screen
	
	public static void clearScreen() {  
		for(int i = 0; i < 30; i++) {
			System.out.println();
		}
		System.out.println("-----------------------------------");
		System.out.println("| INSA MATRIX CALCULATOR & EDITOR |");
		System.out.println("|    Maths at your fingertips     |");
		System.out.println("-----------------------------------");
		System.out.println();
		System.out.println();
	}
}

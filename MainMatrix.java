import java.util.Scanner;
import java.util.Arrays; 

public class MainMatrix {
	
	public static void main (String args[]) {
		boolean quit = false;
		Matrix[] matricesTab = new Matrix[0];
		Matrix[] vectorsTab = new Matrix[1];
		String cmd;
		String info;
		int a = -1;
		int b = -1;
		String name;
		String name2;
		int id1;
		int id2;
        
        Scanner sc = new Scanner(System.in);
        
        // Tests
        
        matricesTab = updateTab(matricesTab, new Matrix(3,3,"TEST", true));
		
		// Starting UI
		
		clearScreen();
		info = "To begin, you can create your first Matrix using the 'CREATE' command\nand by letting the program guide you through the creation process,\nthen you can use 'EDIT', 'CALC', 'CREATE', 'SHOW', 'SHOW_ALL' again or 'QUIT'.";
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
				//doOperation();
				
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
						}else if(mainWhile_sc.hasNext("VAL")){
							// Can't compute eigenval for now
						}else if(mainWhile_sc.hasNext("VEC")){
							// Can't compute eigenvec for now
						}else if(mainWhile_sc.hasNext("REV")){
							if(matricesTab[id1].testCarree() && matricesTab[id1].determinant() != 0){
								matricesTab = saveTemp(matricesTab[id1].reverse(), matricesTab);
								showMatrix(matricesTab.length-1, matricesTab, true, true, sc);
							}else{
								info = "The matrix isn't squared";
							}
						}else if(mainWhile_sc.hasNext("ECH")){
							matricesTab = saveTemp(matricesTab[id1].gaussJourdan(), matricesTab);
							showMatrix(matricesTab.length-1, matricesTab, true, true, sc);
						}else if(mainWhile_sc.hasNext("TRANS")){
							matricesTab = saveTemp(matricesTab[id1].transpose(), matricesTab);
							showMatrix(matricesTab.length-1, matricesTab, true, true, sc);
						}else if(mainWhile_sc.hasNext("DIAG")){
							
						}else if(mainWhile_sc.hasNext("MULS")){
							
						}else if(mainWhile_sc.hasNext("MULM")){
							
						}else if(mainWhile_sc.hasNext("COL")){
							
						}else if(mainWhile_sc.hasNext("LINE")){
							
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
				System.out.print("Which name ?>");
				name = sc.next();
				System.out.print("How ? 'RANDOM', 'VECTORIAL' or 'CUSTOMISED' ? >");
				cmd = sc.next();
				Scanner createWhile_sc = new Scanner(cmd);
				if(createWhile_sc.hasNext("RANDOM") || createWhile_sc.hasNext("random")){
					matricesTab = updateTab(matricesTab, new Matrix(a, b, name, true));
					showMatrix(searchMatrix(name, matricesTab), matricesTab, false, true, sc);
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
				if(!showMatrix(searchMatrix(cmd, matricesTab), matricesTab, false, true, sc)){
					info = "Error while searching matrix";
				}
			}else if(mainWhile_sc.hasNext("QUIT") || mainWhile_sc.hasNext("quit")){
				quit = true;
			}else{
				info = "Unknown command, try 'CREATE', 'EDIT', 'CALC', 'SHOW', 'SHOW_ALL' or 'QUIT'";
			}
            clearScreen();
        }
        
        System.out.println("Thanks for using");
        sc.close();
	}
  
	// Show all matrices
	
    public static void showMatrices(Matrix[] matricesTab, Scanner sc) {
        for(int i = 0; i < matricesTab.length; i++) {
            showMatrix(i, matricesTab, false, false, sc);
            System.out.println();
        }
        pause(sc);
    }
    
    // Show one identified matrix
    
    public static boolean showMatrix(int id, Matrix[] matricesTab, boolean num, boolean pause, Scanner sc) {
        if(id != -1) {
			System.out.println("| Matrix name :"+matricesTab[id].name);
			System.out.println();
			System.out.print("    ");
			if(num){
				for(int i = 0; i < matricesTab[id].data[0].length; i++) {
					System.out.print("  "+(i+1)+" ");
				}
			}
			System.out.println();
			for(int i = 0; i < matricesTab[id].data.length; i++) {
				if(num) {
					System.out.print(" "+(i+1)+" ");
				}
				System.out.print("|");
				for(int j = 0; j < matricesTab[id].data[0].length; j++) {
					System.out.print(" "+matricesTab[id].data[i][j]+" ");
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
				showMatrix(id, matricesTab, true, false, sc);
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
				}else if(a == -1 || b == -1 || c == -1){
					com = "Wrong input, retry (type 0 to leave)";	
				}else{
					dynEdit_sc.close();
					if(a <= matricesTab[id].data.length && 0 < a && b <= matricesTab[id].data[a].length && 0 < b) {
						matricesTab[id].data[a-1][b-1] = c;
						com = "Change done !";
					}else {
						com = "Wrong input, retry (type 0 to leave)";
					}
				}
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

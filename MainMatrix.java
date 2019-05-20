import java.util.Scanner;
import java.util.Arrays; 

public class MainMatrix {
	
	public static void main (String args[]) {
		boolean quit = false;
		Matrix[] matricesTab = new Matrix[0];
		String cmd;
		String info;
		int a;
		int b;
		String name;
        
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
			clearScreen();
			if(mainWhile_sc.hasNext("EDIT") || mainWhile_sc.hasNext("edit")){
				System.out.print("Matrix Name ? >");
				cmd = sc.next();
				if(!dynamicEditor(cmd, matricesTab, sc, info)){
					info = "Error while searching matrix";
				}
			}else if(mainWhile_sc.hasNext("CALC") || mainWhile_sc.hasNext("calc")){
				//doOperation();
			}else if(mainWhile_sc.hasNext("CREATE") || mainWhile_sc.hasNext("create")){
				System.out.print("Which size ? (x,y)>");
				cmd = sc.next();
				Scanner size_sc = new Scanner(cmd);
				size_sc.useDelimiter(",");
				a = size_sc.nextInt();
				b = size_sc.nextInt();
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
					//Need to add vectors
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
    
    public static boolean showMatrix(int id, Matrix[] matricesTab, boolean b, boolean one, Scanner sc) {
        if(id != -1) {
			System.out.println("| Matrix name :"+matricesTab[id].name);
			System.out.println();
			System.out.print("    ");
			if(b){
				for(int i = 0; i < matricesTab[id].data.length; i++) {
					System.out.print("  "+(i+1)+" ");
				}
			}
			System.out.println();
			for(int i = 0; i < matricesTab[id].data.length; i++) {
				if(b) {
					System.out.print(" "+(i+1)+" ");
				}
				System.out.print("|");
				for(int j = 0; j < matricesTab[id].data.length; j++) {
					System.out.print(" "+matricesTab[id].data[i][j]+" ");
				}
				System.out.println("|");
			}
			System.out.println();
			if(one){
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
	
	// Update function which enlarge matrices tab and add the last one
	
	public static Matrix[] updateTab(Matrix[] matricesTab, Matrix m) {
		Matrix[] tab = Arrays.copyOf(matricesTab, matricesTab.length+1);
		tab[tab.length-1] = m;
		return tab;
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
			int a;
			int b;
			int c;
			while(!quit) {
				clearScreen();
				System.out.println("------------------");
				System.out.println("| DYNAMIC EDITOR |");
				System.out.println("------------------");
				System.out.println("| Info :"+com);
				showMatrix(id, matricesTab, true, false, sc);
				System.out.print("DynEdit_CMD> ");
				input = sc.next();
				// Write text will stop program !
				Scanner dynEdit_sc = new Scanner(input);
				dynEdit_sc.useDelimiter(",");
				a = dynEdit_sc.nextInt();
				if(a == 0) {
					dynEdit_sc.close();
					quit = true;
					info = "To begin, you can create your first Matrix using the 'CREATE' command\nand by letting the program guide you through the creation process,\nthen you can use 'EDIT', 'CALC', 'CREATE' again, 'SHOW', 'SHOW_ALL' or 'QUIT'.";
				}else{
					b = dynEdit_sc.nextInt();
					c = dynEdit_sc.nextInt();
					dynEdit_sc.close();
					if(a <= matricesTab[id].data.length && 0 < a && b <= matricesTab[id].data[a].length && 0 < b) {
						matricesTab[id].data[a-1][b-1] = c;
						com = "Change done !";
					}else {
						com = "Wrong input, retry";
					}
				}
			}
			return true;
		}
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

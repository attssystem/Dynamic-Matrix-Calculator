import java.util.Scanner;
import java.util.regex.Pattern;

public class MainMatrix {
	
	public static void main (String args[]) {
		boolean quit = false;
		Matrix[] matricesTab = new Matrix[1];
        
        Scanner sc = new Scanner(System.in);
        
        // Tests
        
        Matrix m = new Matrix(3,3, "TEST");
        matricesTab[0] = m;
		showMatrix(0, matricesTab,true);
		dynamicEditor("TEST", matricesTab, sc);
        
        
        
        while(!quit) {
             //doOperation(base_sc.next());
        }
        
        System.out.println("Thanks for using");
        sc.close();
	}
  
	// Show all matrices
	
    public static void showMatrices(Matrix[] matricesTab) {
        for(int i = 0; i < matricesTab.length; i++) {
            showMatrix(i, matricesTab, false);
            System.out.println();
        }
    }
    
    // Show one identified matrix
    
    public static void showMatrix(int id, Matrix[] matricesTab, boolean b) {
        System.out.println("| Matrix name :"+matricesTab[id].name);
        System.out.println();
        System.out.print("    ");
        for(int i = 0; i < matricesTab[id].data.length; i++) {
			System.out.print("  "+(i+1)+" ");
		}
		System.out.println();
        for(int i = 0; i < matricesTab[id].data.length; i++) {
			if(b) {
				System.out.print(" "+(i+1)+" ");
			}
			System.out.print("[");
            for(int j = 0; j < matricesTab[id].data.length; j++) {
				System.out.print(" "+matricesTab[id].data[i][j]+" ");
			}
			System.out.println("]");
		}
		System.out.println();
    }
    
    // Look for a specific matrix using his name
    
    public static int searchMatrix(String name, Matrix[] matricesTab) {
        int id = -1;
        int i = 0;
        while(id == -1) {
            if(matricesTab[i].name == name) {
                id = i;
            }
            i++;
        }
        return id;
	}
	
	// Update function which enlarge matrices tab and add the last one
	
	public static void updateTab(Matrix[] tab, Matrix m) {
		Matrix[] newTab = new Matrix[tab.length+1];
		for(int i = 0; i < tab.length; i++) {
			newTab[i] = tab[i];
		}
		newTab[tab.length] = m;
		tab = newTab;
	}
	
	// Dynamic Editor
	
	public static void dynamicEditor(String name, Matrix[] matricesTab, Scanner sc) {
		String com = "Type x,y,new_value or 0 to leave";
		boolean quit = false;
		String input;
		int a;
		int b;
		int c;
		
		int id = searchMatrix("TEST", matricesTab);
		while(!quit) {
			clearScreen();
			System.out.println("------------------");
			System.out.println("| DYNAMIC EDITOR |");
			System.out.println("------------------");
			System.out.println("| Info :"+com);
			showMatrix(searchMatrix("TEST", matricesTab), matricesTab, true);
			input = sc.next();
			// Write text will stop program !
			Scanner dynEdit_sc = new Scanner(input);
			dynEdit_sc.useDelimiter(",");
			a = dynEdit_sc.nextInt();
			b = dynEdit_sc.nextInt();
			c = dynEdit_sc.nextInt();
			dynEdit_sc.close();
			if(a == 0) {
				quit = true;
			}else if(a <= matricesTab[id].data.length && 0 < a && b <= matricesTab[id].data[a].length && 0 < b) {
				matricesTab[id].data[a-1][b-1] = c;
				com = "Change done !";
			}else {
				com = "Wrong input, retry";
			}
		}
	}
	
	// Clear the screen
	
	public static void clearScreen() {  
		for(int i = 0; i < 30; i++) {
			System.out.println();
		}
	}
}

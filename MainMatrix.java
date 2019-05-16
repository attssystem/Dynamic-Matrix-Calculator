import java.util.Scanner;

public class MainMatrix {
	
	public static void main (String args[]) {
		boolean quit = false;
		Matrix[] matricesTab = new Matrix[1];
        
        Scanner sc = new Scanner(System.in);
        
        Matrix m = new Matrix(3,3);
        matricesTab[0] = m;
        showMatrix(0, matricesTab);
        
        
        
        while(!quit) {
             //doOperation(sc.next());
        }
        
        System.out.println("Thanks for using");
        sc.close();
	}
    
    // Can't use without Matrix Object
  
	// Show all matrices
	
    public static void showMatrices(Matrix[] matricesTab) {
        for(int i = 0; i < matricesTab.length; i++) {
            showMatrix(i, matricesTab);
            System.out.println();
        }
    }
    
    // Show one identified matrix
    
    public static void showMatrix(int id, Matrix[] matricesTab) {
        System.out.println(matricesTab[id].name);
        for(int i = 0; i < matricesTab[id].data.length; i++) {
			System.out.print("[");
            for(int j = 0; j < matricesTab[id].data.length; j++) {
				System.out.print(" "+matricesTab[id].data[i][j]+" ");
			}
			System.out.println("]");
		}
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
	
	public static void dynamicEditor(Matrix m) {
		
	}
	
	// Clear the screen
	
	public static void clearScreen() {  
		System.out.print("\033[H\033[2J");  
		System.out.flush();  
	}
}

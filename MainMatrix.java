import java.util.Scanner;
//import org.fusesource.jansi.AnsiConsole;
//import static org.fusesource.jansi.Ansi.*;
//import static org.fusesource.jansi.Ansi.Color.*;

public class MainMatrix {
	
	public static void main (String args[]) {
		boolean quit = false;
        
        Scanner sc = new Scanner(System.in);
        //AnsiConsole.systemInstall();
        
        //System.out.println( ansi().eraseScreen().fg(RED).a("Hello").fg(GREEN).a(" World").reset() );
        
        while(!quit) {
             //doOperation(sc.next());
        }
        
        //System.out.println(ansi().eraseScreen());
        System.out.println("Thanks for using");
        sc.close();
        //AnsiConsole.systemUninstall();
	}
    
     /*Can't use without Matrix Object
  
	// Show all matrices
	
    public static void showMatrices() {
        for(int i = 0; imatricesTab.length; i++) {
            showMatrix(i);
            System.out.println();
        }
    }
    
    // Show one identified matrix
    
    public static void showMatrix(int id) {
        System.out.println(matrices[id].name);
        for(int i = 0; i  matrices[id].length; i++) {
			System.out.print([);
            for(int j = 0; j  matrices[id].length; i++) {
				System.out.print( +matrices[id][i][j]+ );
			}
			System.out.print(]);
		}
    }
    
    // Look for a specific matrix using his name
    
    public static int searchMatrix(String name) {
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
		for(int i = 0; i  tab.length; i++) {
			newTab[i] = tab[i];
		}
		newTab[tab.length] = m;
		tab = newTab;
	}  */
	
	
	public static void clearScreen() {  
		System.out.print("\033[H\033[2J");  
		System.out.flush();  
	}  
}

// Thanks fusesource for JANSI (APACHE 2 License) httpsgithub.comfusesourcejansi

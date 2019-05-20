
public class Matrix{
	int[][] data;
    String name;
    
	public Matrix (int l, int n, String na, boolean rand) { //Génération aléatoire avec nombre de ligne et de colonne données
    	this.data = new int [l][n];
    	this.name = na;
    	if(rand) {
			for (int i=0;i<this.data.length;i++){
				for (int j=0; j<this.data[i].length;j++){
				this.data[i][j]=(int)(Math.random()*100); //entre 0 et 99    	 
				}
			}
    	}
	}
    
    
	public Matrix transpose(){
   	 Matrix m01T = new Matrix(this.data[0].length,this.data.length, "temp", true);
   	 for (int i=0;i<this.data.length;i++){
   		 for (int j=0;j<this.data[0].length;j++){
   			 m01T.data[j][i]=this.data[i][j];
   		 }
   	 }
   	 return m01T;
    }
   		 
   		 
    
	public double trace(){ // Ne fonctionne qu'avec matrices carrées !
   	 int[] diagonal = this.getDiagonal();
   	 int Trace = 0;
   	 for (int i=0; i<diagonal.length;i++){
   		 Trace = Trace + diagonal[i];
   	 }
    return Trace;
    }
    
	public int[] getColumn(int whichColumn){ //Renvoie un tableau 1D contenant une colonne de la matrice que l'utilisateur selectionnera
   	 int[] Column = new int[this.data.length];
   	 for (int i=0;i<Column.length;i++){
   		 Column[i]=this.data[i][whichColumn];
   	 }
   	 return Column;
    }
    
	public int[] getLine(int whichLine){ //Renvoie un tableau 1D contenant une ligne de la matrice que l'utilisateur selectionnera
   	 int[] line = new int[this.data[0].length];
   	 for (int i=0;i<line.length;i++){
   		 line[i]=this.data[whichLine][i];
   	 }
   	 return line;
    }
    
	public int[] getDiagonal() { //Renvoie un tableau 1D contenant la diagonale de la matrice
   	 int[] diagonal = new int[this.data.length]; // Ne fonctionne que si la matrice est carrée, penser à trouver un moyen de dire que l'opération n'est pas possible
   	 for (int i=0;i<diagonal.length;i++){
   		 diagonal[i]=this.data[i][i];
   	 }
   			 
   	 return diagonal;
    }
   	 
    
    
	public boolean testCarree() { // teste si la matrice est carrée : renvoie "true" si elle est carrée, false sinon. Penser à intégrer ça dans les fonctions.
   	 if (this.data.length == this.data[0].length) {
   		 return true;
   	 }else{
   		 return false;
   	 }
    }
    

	public void afficheMatrice(){ //temporaire : Juste pour tester les programmes
    	for (int i=0;i<this.data.length;i++){
        	for (int j=0; j<this.data[i].length;j++){
            	System.out.print(this.data[i][j]+" ");
        	}
        	System.out.println();
    	}
	}
    
}

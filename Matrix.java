public class Matrix{
	int[][] data;
    String name;
	public Matrix (int l, int n, String na, boolean rand) { //Génération aléatoire avec nombre de ligne et de colonne données
    	this.data = new int [l][n];
        this.name = na;
        if(rand) {
            for (int i=0;i<this.data.length;i++){
                for (int j=0; j<this.data[i].length;j++){
                    this.data[i][j]=(int)((Math.random()*200)-100); //entre -99 et 99    	 
                }
            }
        }
	}

    public double[] eigenValues(){
        double[] lambda;
        double delta = Math.pow(this.trace(),2)-4*this.determinant();
        System.out.println(delta);
        if(delta>0){
			lambda = new double[2];
            lambda[0]=(this.trace()+Math.sqrt(delta))/2;
            lambda[1]=(this.trace()-Math.sqrt(delta))/2;
		}else{
			if(delta==0){
				lambda = new double[1];
				lambda[0]=this.trace()/2;
				}else{
					lambda = new double[3]; // Penser à afficher un message d'erreur dans ce cas là. Si la taille du tableau = 3, c'est que les valeurs propres sont complexes et on ne prend pas en compte ce cas.
				}
			}
				return lambda;
    }
        
    
	public Matrix getSousMatrice(int quelInd){
    	int n=0;
    	Matrix m01S = new Matrix (this.data.length-1,this.data.length-1, "m01S", false);
    	if (quelInd==-1){
   		 m01S=this;
   	 }else{
   		 for (int i=0;i<this.data.length;i++){
   			 for (int j=1;j<this.data.length;j++){
   				 if(i!=quelInd){
   					 if(i<quelInd){
   						 m01S.data[i][j-1]=this.data[i][j];
   					 }else{
   						 m01S.data[i-1][j-1]=this.data[i][j];
   					 }
   				 }
   			 }
   		 }    
   	 }    
   	 return m01S;
	}
           	 
     	 
	public double determinant(){
    	double d=0;
    	if(this.data.length == 1){
        	d=this.getSousMatrice(-1).data[0][0];
   	 }else{
   		 for(int i=0;i<this.data.length;i++){
   			 d+=(Math.pow(-1,i)*this.data[i][0]*getSousMatrice(i).determinant());
   		 }
   	 }
   	 return d;
    }
    
    
	public Matrix transpose(){
    	Matrix m01T = new Matrix(this.data[0].length,this.data.length, "m01T", false);
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

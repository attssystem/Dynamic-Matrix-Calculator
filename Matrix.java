public class Matrix{
	double[][] data;
    String name;

	/* Constructeur
	 * L'utilisateur donne un nombre de lignes et un nombre de colonnes.
	 * Permet de générer aléatoirement une matrice. Si rand == true
	 * Ou de générer une matrice nulle. Si rand == false */
	public Matrix (int l, int n, String na, boolean rand) {
    	this.data = new double [l][n];
        this.name = na;
        if(rand) { //Génération aléatoire si rand==true. Sinon, génère une matrice nulle
            for (int i=0;i<this.data.length;i++){
                for (int j=0; j<this.data[i].length;j++){
                    this.data[i][j]=(int)((Math.random()*200)-100); //entre -99 et 99     
                }
			}
        }
	}
	
	public Matrix rank() {
		Matrix m01G = new Matrix(this.data.length, this.data[0].length, this.name+"_rank", false);
		for (int i = 0; i < this.data.length; i++){
			for (int j = 0; j < this.data[0].length; j++){
				m01G.data[i][j] = this.data[i][j];
			}
		}
		
		int ind = -1;
		int j = 0;
		double coef;
		double temp;
		for(int i = 0; i < m01G.data.length; i++){
			ind = -1;
			j = i;
			while(j < m01G.data.length && ind  != -1){
				if(m01G.data[i][j] != 0){
					ind = j;
				}
				j++;
			}
			if(ind == -1){
				// Do nothing and waits for the next column
			}else{
				for(int k = 0; k < m01G.data[0].length; k++) {
					temp = m01G.data[1][k];
					m01G.data[i][k] = m01G.data[ind][k];
					m01G.data[ind][k] = temp;
				}
			}
			for(int l = i+1; l < m01G.data.length; l++){
					coef = m01G.data[l][i]/m01G.data[i][i];
				for(int m = i; m < m01G.data[0].length; m++){
					// System.out.println(m01G.data[l][m]);
					m01G.data[l][m] = m01G.data[l][m]-m01G.data[i][m]*coef;
					// System.out.println(m01G.data[l][m]);
				}
			}
		}
		return m01G;
	}
    
    public Matrix gaussJourdan() {
		Matrix m01G = new Matrix(this.data.length, this.data[0].length, this.name+"_rankr", false);
		for (int i = 0; i < this.data.length; i++){
			for (int j = 0; j < this.data[0].length; j++){
				m01G.data[i][j] = this.data[i][j];
			}
		}
		
		int r = -1;
		int k;
		double max;
		double temp;
		double temp2;
		double temp3;
		
		for(int j = 0; j < m01G.data[0].length; j++) {
			if((r+1) < m01G.data.length) {
				// Look for a maximum
				// System.out.println((r+1)+" "+j);
				max = m01G.data[r+1][j];
				k = r+1;
				for(int i = r+2; i < m01G.data.length; i++) {
					if(m01G.data[i][j] > max) {
						max = m01G.data[i][j];
						k = i;
					}
				}
				
				if(m01G.data[k][j] != 0) {
					r++;
					// Divide k line
					temp2 = m01G.data[k][j];
					for(int i = 0; i < m01G.data[0].length; i++) {
						m01G.data[k][i] = m01G.data[k][i]/temp2;
					}
					// System.out.println("divide "+k);
					// System.out.println(temp2);
					// m01G.afficheMatrice();
					
					// Switching k and r lines
					for(int i = 0; i < m01G.data[0].length; i++) {
						temp = m01G.data[k][i];
						m01G.data[k][i] = m01G.data[r][i];
						m01G.data[r][i] = temp;
					}
					// System.out.println("switch");
					// m01G.afficheMatrice();
					
					// Other lines simplified
					for(int i = 0; i < m01G.data.length; i++) {
						temp3 = m01G.data[i][j];
						if(i != r) {
							for(int h = 0; h < m01G.data[0].length; h++) {
								m01G.data[i][h] = m01G.data[i][h]-(m01G.data[r][h]*temp3);
							}
						}
					}
					// System.out.println("simplification");
					// m01G.afficheMatrice();
				}
			}
		}
		// m01G.afficheMatrice();
		return m01G;
	}
    
    public Matrix reverse(){
        // Test square parameters and if det != 0 !
        Matrix m01I = new Matrix(this.data.length, (this.data.length*2), "m01I", false);
        Matrix reversed = new Matrix(this.data.length, this.data.length, this.name+"_rev", false);
        // Copy of initial Matrix on the left side
        for(int i = 0; i < this.data.length; i++){
            for(int j = 0; j < this.data.length; j++){
                m01I.data[i][j] = this.data[i][j];
            }
        }
        // Copy of Identity Matrix on the right side
        for(int i = 0; i < this.data.length; i++){
            for(int j = (this.data.length-1); j < (this.data.length*2); j++){
                if(i == (j-this.data.length)){
                    m01I.data[i][j] = 1;
                }
            }
        }
        // Calculations
        m01I = m01I.gaussJourdan();
        // Gathering only the reversed Matrix
        for(int i = 0; i < this.data.length; i++){
            for(int j = 0; j < this.data.length; j++){
                reversed.data[i][j] = m01I.data[i][j+this.data.length];
            }
        }
        return reversed;
    }

	// soustractions de 2 matrices
	public Matrix subtract(Matrix m02){
		if(this.data.length==m02.data.length && this.data[0].length==m02.data[0].length){
			Matrix mResult = new Matrix(this.data.length,this.data[0].length, this.name+"_sub_"+m02.name, false);
			for (int i=0;i<this.data.length;i++){
				for (int j=0;j<this.data[0].length;j++){
					mResult.data[i][j]=this.data[i][j]-m02.data[i][j];
				}
			}
			return mResult;
		}else{
			return null;
		}
	}
	
	// additions de 2 matrices
	public Matrix add(Matrix m02){
		if(this.data.length==m02.data.length && this.data[0].length==m02.data[0].length){
			Matrix mResult = new Matrix(this.data.length,this.data[0].length, this.name+"_add_"+m02.name, false);
			for (int i=0;i<this.data.length;i++){
				for (int j=0;j<this.data[0].length;j++){
					mResult.data[i][j]=this.data[i][j]+m02.data[i][j];
				}
			}
			return mResult;
		}else{
			return null;
		}
	}
	
	//division de matrice 1 par matrice 2 : multiplication par l'inverse de 1 par 2
	public Matrix divide(Matrix m02){
		if(this.testCarree() && m02.testCarree() && this.determinant() != 0  && m02.determinant() != 0) {
			Matrix m01D = this.multiplyM(m02.reverse());
			m01D.name = this.name+"_divBy_"+m02.name;
			return m01D;
		}else{
			return null;
		}
	}

	//Produit de 2 matrices
	public Matrix multiplyM (Matrix m02){ 
		if(this.data[0].length!=m02.data.length){ //Si le nombres de colonnes de la matrice n'est pas égale aux nombres de lignes de m02, retourne le pointeur null
			return null;
		}else{
			Matrix mResult = new Matrix(this.data.length,m02.data[0].length, this.name+"_mulm_"+m02.name, false);
			for (int i=0;i<mResult.data.length;i++){ 
				for (int j=0;j<mResult.data[0].length;j++){
					for (int n=0;n<this.data[0].length;n++){
						mResult.data[i][j]+=this.data[i][n]*m02.data[n][j];
					}
				}
			}
			return mResult;
		}
	}

		
		
	// produit d'une matrice par un scalaire
	public Matrix multiplyS (int scalaire){
		Matrix mResult = new Matrix(this.data.length, this.data[0].length, this.name+"_muls_"+scalaire, false);
		for (int i=0;i<this.data.length;i++){
			for (int j=0;j<this.data[0].length;j++){
				mResult.data[i][j]=scalaire*this.data[i][j];
			}
		}
		return mResult;
	}
	
	//Diagonalise une matrice 2*2 à partir des valeurs propres
	public Matrix diagonalise(){
		Matrix mdiagonal = new Matrix(2,2,this.name+"_diagonal",false);
		mdiagonal.data[0][0]=eigenValues()[0];
		mdiagonal.data[1][1]=eigenValues()[1];
		return mdiagonal; // Si la matrice n'est pas 2*2, la classe MainMatrix gère l'erreur
	}
	
	//Matrices de passage. Ne sont pas "optimisées" mais permettent de décrire deux vecteurs pour former la méthode de passage
	public Matrix passage(){
		Matrix mPassage = new Matrix(2,2,this.name+"_passage",false);
		mPassage.data[0][0]=1; // On fixe la première coordonnée à 1
		mPassage.data[0][1]=1; // idem
		mPassage.data[1][0]=-(this.data[0][0]-this.eigenValues()[0]); // solution générique de l'équation permettant de décrire les vecteurs propres
		mPassage.data[1][1]=-(this.data[0][0]-this.eigenValues()[1]); // idem
		return mPassage; 
	}
		
	//calcul des valeurs propres pour les matrices 2*2
    public double[] eigenValues(){ 
		if(this.data.length==this.data[0].length && this.data.length == 2){ //teste si la matrice est de taille 2*2
			double[] lambda;
			double delta = Math.pow(this.trace(),2)-4*this.determinant();
			if(delta>0){ 
				lambda = new double[2];
				lambda[0]=Math.max(((this.trace()+Math.sqrt(delta))/2),((this.trace()-Math.sqrt(delta))/2));
				lambda[1]=Math.min(((this.trace()+Math.sqrt(delta))/2),((this.trace()-Math.sqrt(delta))/2));
			}else{
				if(delta==0){
					lambda = new double[1];
					lambda[0]=this.trace()/2;
					}else{ //si le delta n'est pas nul ou n'est pas positif, alors il est négatif. Les valeurs propres sont donc complexes, et nous ne gérons pas les complexes
						return null; 
					}
				}
				return lambda;
		}else{ // si la matrice n'est pas 2*2, on renvoie le pointeur null. MainMatrix gère l'erreur
			return null;
		}
    }
        
    /* Permet d'extraire une "sous matrice" (voir compte rendu pour les aspects mathématiques)
     * est utilisé pour le calcul du déterminant */
	public Matrix getSousMatrice(int quelInd){
    	int n=0;
    	Matrix m01S = new Matrix (this.data.length-1,this.data.length-1, "m01S", false); 
    	if (quelInd==-1){ //si la matrice est de taille 1*1 (voir code déterminant)
   		 m01S=this; //le déterminant d'une matrice 1*1 est le terme lui-même
   	 }else{
   		 for (int i=0;i<this.data.length;i++){
   			 for (int j=1;j<this.data.length;j++){ //On parcours le tableau depuis la 2ème colonne. (cofacteurs de j=0)
   				 if(i!=quelInd){ // On supprime la ligne de cofacteurs de coordonnées i=quelInd et j=0
   					 if(i<quelInd){ //Avant la ligne à supprimer : remplit le tableau en suivant i
   						 m01S.data[i][j-1]=this.data[i][j];
   					 }else{ // après la ligne à supprimer : remplit le tableau en suivant i+1
   						 m01S.data[i-1][j-1]=this.data[i][j];
   					 }
   				 }
   			 }
   		 }    
   	 }    
   	 return m01S;
	}
           	 
    /* Calcul du déterminant
     * utilisation des fonctions récursives
     * voir le compte rendu pour l'explication algorithmique
     */ 	 
	public double determinant(){ // ne fonctionne que pour les matrices carrées. Erreur gérée depuis la MainMatrix.
    	double d=0; 
    	if(this.data.length == 1){ //"Première condition" : matrice de taille 1*1
        	d=this.getSousMatrice(-1).data[0][0];
   	 }else{
   		 for(int i=0;i<this.data.length;i++){
   			 d+=(Math.pow(-1,i)*this.data[i][0]*getSousMatrice(i).determinant()); //Fonction récursive
   		 }
   	 }
   	 return d;
    }
    
    // transposition : inverse lignes et colonnes
	public Matrix transpose(){
    	Matrix m01T = new Matrix(this.data[0].length,this.data.length, this.name+"_trans", false);
    	for (int i=0;i<this.data.length;i++){
        	for (int j=0;j<this.data[0].length;j++){
            	m01T.data[j][i]=this.data[i][j];
        	}
    	}
    	return m01T;
	}
       	 
    
	/*calcul de la trace
	 * somme des éléments diagonaux
	 * pour les matrices carrées : erreur gérée depuis MainMatrix
	 */
	public double trace(){ 
    	double[] diagonal = this.getDiagonal();
    	double Trace = 0;
    	for (int i=0; i<diagonal.length;i++){
        	Trace = Trace + diagonal[i];
    	}
	return Trace;
	}
    
    /* Extraire la colonne d'une matrice
     * Renvoie un tableau 1D contenant une colonne de la matrice que l'utilisateur selectionnera
     */
	public double[] getColumn(int whichColumn){ 
    	double[] Column = new double[this.data.length];
    	for (int i=0;i<Column.length;i++){
        	Column[i]=this.data[i][whichColumn];
    	}
    	return Column;
	}
    
    /* Extraire la ligne d'une matrice
     * Renvoie un tableau 1D contenant une ligne de la matrice que l'utilisateur selectionnera
     */
	public double[] getLine(int whichLine){ 
    	double[] line = new double[this.data[0].length];
    	for (int i=0;i<line.length;i++){
        	line[i]=this.data[whichLine][i];
    	}
    	return line;
	}
    
    /*Extraire la diagonale d'une matrice
     * Renvoie un tableau 1D contenant la diagonale de la matrice
     */
	public double[] getDiagonal() {

    	if(!this.testCarree()){ // Si la matrice n'est pas carrée, retourne le pointeur null
			return null;
		}else{
			double[] diagonal = new double[this.data.length]; 
			for (int i=0;i<diagonal.length;i++){
				diagonal[i]=this.data[i][i];
			}
				 
			return diagonal;
		}
	}

    
    // Teste si la matrice est carrée
	public boolean testCarree() { 
    	if (this.data.length == this.data[0].length) {
        	return true;
    	}else{
        	return false;
    	}
	}
    
	//pour debug
	/*public void afficheMatrice(){ 
    	for (int i=0;i<this.data.length;i++){
        	for (int j=0; j<this.data[i].length;j++){
            	System.out.print(this.data[i][j]+" ");
        	}
        	System.out.println();
    	}
    	System.out.println();
	}*/
	
    
}

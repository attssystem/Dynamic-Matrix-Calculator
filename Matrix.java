public class Matrix{
	double[][] data;
    String name;

	public Matrix (int l, int n, String na, boolean rand) { //Génération aléatoire avec nombre de ligne et de colonne données
    	this.data = new double [l][n];
        this.name = na;
        if(rand) {
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
	
	public Matrix divide(Matrix m02){
		if(this.testCarree() && m02.testCarree() && this.determinant() != 0  && m02.determinant() != 0) {
			Matrix m01D = this.multiplyM(m02.reverse());
			m01D.name = this.name+"_divBy_"+m02.name;
			return m01D;
		}else{
			return null;
		}
	}

	public Matrix multiplyM (Matrix m02){ 
		if(this.data[0].length!=m02.data.length){ //Si le nombres de colonnes de la matrice n'est pas égale aux nombres de lignes de m02, retourne le pointeur nul
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

		
		

	public Matrix multiplyS (int scalaire){
		Matrix mResult = new Matrix(this.data.length, this.data[0].length, this.name+"_muls_"+scalaire, false);
		for (int i=0;i<this.data.length;i++){
			for (int j=0;j<this.data[0].length;j++){
				mResult.data[i][j]=scalaire*this.data[i][j];
			}
		}
		return mResult;
	}
	
	public Matrix diagonalise(){
		Matrix mdiagonal = new Matrix(2,2,this.name+"_diagonal",false);
		mdiagonal.data[0][0]=eigenValues()[0];
		mdiagonal.data[1][1]=eigenValues()[1];
		return mdiagonal;
	}
	
	public Matrix passage(){
		Matrix mPassage = new Matrix(2,2,this.name+"_passage",false);
		mPassage.data[0][0]=1;
		mPassage.data[0][1]=1;
		mPassage.data[1][0]=-(this.data[0][0]-this.eigenValues()[0]);
		mPassage.data[1][1]=-(this.data[0][0]-this.eigenValues()[1]);
		return mPassage; 
	}
		

    public double[] eigenValues(){ 
		if(this.data.length==this.data[0].length && this.data.length == 2){
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
					}else{
						return null; 
					}
				}
				return lambda;
		}else{
			return null;
		}
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
           	 
     	 
	public double determinant(){ // ne fonctionne que pour les matrices carrées. Trouver comment faire le garde fou.
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
    	Matrix m01T = new Matrix(this.data[0].length,this.data.length, this.name+"_trans", false);
    	for (int i=0;i<this.data.length;i++){
        	for (int j=0;j<this.data[0].length;j++){
            	m01T.data[j][i]=this.data[i][j];
        	}
    	}
    	return m01T;
	}
       	 
    
	public double trace(){ // Ne fonctionne qu'avec matrices carrées !
    	double[] diagonal = this.getDiagonal();
    	double Trace = 0;
    	for (int i=0; i<diagonal.length;i++){
        	Trace = Trace + diagonal[i];
    	}
	return Trace;
	}
    
	public double[] getColumn(int whichColumn){ //Renvoie un tableau 1D contenant une colonne de la matrice que l'utilisateur selectionnera
    	double[] Column = new double[this.data.length];
    	for (int i=0;i<Column.length;i++){
        	Column[i]=this.data[i][whichColumn];
    	}
    	return Column;
	}
    
	public double[] getLine(int whichLine){ //Renvoie un tableau 1D contenant une ligne de la matrice que l'utilisateur selectionnera
    	double[] line = new double[this.data[0].length];
    	for (int i=0;i<line.length;i++){
        	line[i]=this.data[whichLine][i];
    	}
    	return line;
	}
    
	public double[] getDiagonal() { //Renvoie un tableau 1D contenant la diagonale de la matrice

    	if(!this.testCarree()){ // Si la matrice n'est pas carrée, retourne le pointeur nul.
			return null;
		}else{
			double[] diagonal = new double[this.data.length]; 
			for (int i=0;i<diagonal.length;i++){
				diagonal[i]=this.data[i][i];
			}
				 
			return diagonal;
		}
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
    	System.out.println();
	}
	
    
}

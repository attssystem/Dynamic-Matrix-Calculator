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

	/* public Matrix gaussJourdan(Matrix m){
        int r = -1; // Num from last "Pivot"
        int k = 0; // Line of maximum
        int max; // Max
        int temp;
        for(int j = 0; j<m.data.length; j++) {
            //Looking for a maximum
            max = m.data[0][j];
            for(int h = r+2; h < m.data.length; h++) {
                if(max < m.data[h][j]) {
                    k = h;
                    max = m.data[h][j];
                }
            }
            
            if(m.data[k][j] != 0) {
                r++;
                // Dividing k line by m.data[k][j]
                for(int l = 0; l < m.data.length; l++) {
                    m.data[k][l] = m.data[k][l]/m.data[k][j];
                }
                // Switching k and r lines
                for(int n = 0; n < m.data.length; n++) {
                    temp = m.data[k][n];
                    m.data[k][n] = m.data[r][n];
                    m.data[r][n] = temp;
                }
                
                for(int o = 0; o < m.data.length; o ++) {
                    if(o != r) {
                        for(int p = 0; p < m.data.length; p++) {
                            m.data[o][p] -= m.data[r][p]*m.data[o][j];
                        }
                    }
                }
            }
        }
        return m;
    } */
    
    public void gaussJourdan() {
		int r = -1;
		int k;
		double max;
		double temp;
		double temp2;
		double temp3;
		
		for(int j = 0; j < this.data[0].length; j++) {
			// Look for a maximum
			System.out.println((r+1)+" "+j);
			max = Math.abs(this.data[r+1][j]);
			k = r+1;
			for(int i = r+2; i < this.data.length; i++) {
				if(Math.abs(this.data[i][j]) > max) {
					max = this.data[i][j];
					k = i;
				}
			}
			
			if(this.data[k][j] != 0) {
				r++;
				// Divide k line
				temp2 = this.data[k][j];
				for(int i = 0; i < this.data[0].length; i++) {
					this.data[k][i] = this.data[k][i]/temp2;
				}
				System.out.println("divide "+k);
				System.out.println(temp2);
				this.afficheMatrice();
				
				// Switching k and r lines
				for(int i = 0; i < this.data[0].length; i++) {
					temp = this.data[k][i];
					this.data[k][i] = this.data[r][i];
					this.data[r][i] = temp;
				}
				System.out.println("switch");
				this.afficheMatrice();
				
				// Other lines simplified
				for(int i = 0; i < this.data.length; i++) {
					temp3 = this.data[i][j];
					if(i != r) {
						for(int h = 0; h < this.data[0].length; h++) {
							this.data[i][h] = this.data[i][h]-(this.data[r][h]*temp3);
						}
					}
				}
				System.out.println("simplification");
				this.afficheMatrice();
			}
		}
	}
    
    public Matrix reverse(){
        // Test square parameters and if det != 0 !
        Matrix m01I = new Matrix(this.data.length, (this.data.length*2), "m01I", false);
        Matrix reversed = new Matrix(this.data.length, this.data.length, "reversed", false);
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
        m01I.afficheMatrice();
        m01I.gaussJourdan();
        m01I.afficheMatrice();
        // Gathering only the reversed Matrix
        for(int i = 0; i < this.data.length; i++){
            for(int j = 0; j < this.data.length; j++){
                reversed.data[i][j] = m01I.data[i][j+this.data.length];
            }
        }
        return reversed;
    }


	public Matrix multiplyM (Matrix m02){ // Ne fonctionne que si nbr de colonnes de m01=nbr de lignes de m02
		Matrix mResult = new Matrix(this.data.length,m02.data[0].length, "mResult", false);
		for (int i=0;i<mResult.data.length;i++){
			for (int j=0;j<mResult.data[0].length;j++){
				for (int n=0;n<this.data[0].length;n++){
					mResult.data[i][j]+=this.data[i][n]*m02.data[n][j];
				}
			}
		}
		return mResult;
	}

		
		

	public Matrix multiplyS (int scalaire){
		Matrix mResult = new Matrix(this.data.length, this.data[0].length, "mResult", false);
		for (int i=0;i<this.data.length;i++){
			for (int j=0;j<this.data[0].length;j++){
				mResult.data[i][j]=scalaire*this.data[i][j];
			}
		}
		return mResult;
	}

    public double[] eigenValues(){
        double[] lambda;
        double delta = Math.pow(this.trace(),2)-4*this.determinant();
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
    	double[] diagonal = new double[this.data.length]; // Ne fonctionne que si la matrice est carrée, penser à trouver un moyen de dire que l'opération n'est pas possible
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
    	System.out.println();
	}
	
    
}

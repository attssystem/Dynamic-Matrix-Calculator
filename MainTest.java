public class MainTest {
    public static void main(String[] args) {
		 
		 
		 
		 Matrix m01 = new Matrix(3,3,"truc",true);
		 /*m01.afficheMatrice();
		 Matrix m02 = new Matrix(3,3,"machin",true);
		 m02.afficheMatrice();
		 Matrix m03 = m01.subtract(m02);
		 
		 m03.afficheMatrice();*/
		 
		 m01.data[0][0] = 2;
		 m01.data[0][1] = -1;
		 m01.data[0][2] = 0;
		 m01.data[1][0] = -1;
		 m01.data[1][1] = 2;
		 m01.data[1][2] = -1;
		 m01.data[2][0] = 0;
		 m01.data[2][1] = -1;
		 m01.data[2][2] = 2;
		 
		 m01.afficheMatrice();
		 
		 m01 = m01.reverse();
		 
		 m01.afficheMatrice();
		 

		 if (m01.getDiagonal() == null){
			 System.out.println("erreur");
		 }

		 
		 
		 //Matrix m02 = m01.reverse();
		 //m02.afficheMatrice();
		 
		 //Matrix m03 = m01.multiplyM(m02);
		 //m03.afficheMatrice();

	}
}

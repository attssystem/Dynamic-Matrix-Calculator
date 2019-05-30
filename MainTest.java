public class MainTest {
    public static void main(String[] args) {
		 
		 Matrix m01 = new Matrix(3,2,"truc",true);
		 m01.afficheMatrice();
		 
		 if (m01.getDiagonal() == null){
			 System.out.println("erreur");
		 }
	}
}

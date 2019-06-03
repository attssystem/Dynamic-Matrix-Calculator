public class MainTest {
    public static void main(String[] args) {
		 
		 Matrix m01 = new Matrix(2,2,"truc",true);
		 m01.afficheMatrice();
		 Matrix m02 = m01.passage();
		 m02.afficheMatrice();
		 Matrix m03 = m01.diagonalise();
		 m03.afficheMatrice();
	}
}

public class MainTest {
    public static void main(String[] args) {
		 
		 Matrix m01 = new Matrix(6,6,"truc",true);
		 m01.afficheMatrice();
		 
		 Matrix m02 = m01.reverse();
		 m02.afficheMatrice();
		 
		 Matrix m03 = m01.multiplyM(m02);
		 m03.afficheMatrice();
	}
}

package it.unibs.ing.fp.esame;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][] mappa;
		mappa= new int [3][3];
		int k=0;
		for (int i=0; i<3; i++)
		{
			for (int u=0; u<3; u++)
			{
				mappa[i][u]=k;
				k++;
			}
		}
		Matrice m = new Matrice (mappa);
		m.printMatrice();
		m.dimensione();
		System.out.println(m.laPlace());
		
	}

}

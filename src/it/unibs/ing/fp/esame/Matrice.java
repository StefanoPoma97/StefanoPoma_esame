package it.unibs.ing.fp.esame;

public class Matrice {
	private int tempoSarrus;
	private int tempoLaPlace;
	private int detSarrus;
	private int detLaPlace;
	private int dimensione;
	private String id;
	private int matrice [][];
	private int out =0;
	
	
	/**
	 * costruttore che riceve matrice, inizializza la matrice presente come variabile privata e inizializza lunghezza
	 * @param _matrice matrice passata al construttore
	 */
	public Matrice (int _matrice [][])
	{
		matrice = _matrice;
		dimensione=matrice.length;
	}
	
/**
 * stampa la dimensione della matrice
 */
	public void dimensione()
	{
		System.out.println(dimensione);
	}
	
	/**
	 * stampa la matrice 
	 */
	public void printMatrice ()
	{
		
		for (int righe=0; righe<dimensione; righe++)
		{
			for (int colonne=0; colonne<dimensione; colonne++)
			{
				System.out.print(matrice[righe][colonne]);
			}
			System.out.println();
		}
	}
	
	/**
	 * calcola il determinanante della matrice appoggiandosi al metodo privato LaPlace()
	 * @return determinante
	 */
	public int determinante()
	{
		
			detLaPlace=laPlace();
			return detLaPlace;
		
	}
	private int laPlace()
	{
		int determinanante= calcoloLaPlace(matrice, dimensione);
		System.out.println("passo matrice di dim"+dimensione);
		return determinanante;
		
	}
	
	private int calcoloLaPlace(int mat[][], int dim)
	{
		
		
		if (dim==2)
		{
			System.out.println("la dimensione è 2");
			int risultato= (mat[0][0]*mat[1][1])-(mat[0][1]*mat[1][0]);
			return risultato;
		}
		else
		{
			int i=0;
			int posizioneX=0;
			while(i<dim)
			{
				int posizione= mat[posizioneX][i];
				if ((posizioneX+i+2)%2!=0)
					posizione=posizione*-1;
				int matRidotta[][]= new int [dim-1][dim-1];
				int x1=0;
				int y1=0;
				if (posizione!=0)
				{
				for (int x=0; x<dim; x++)
				{
					for (int y=0; y<dim; y++)
					{
						if (x==posizioneX || y==i)
						{
							
						}
						else
						{
							matRidotta[x1][y1]=mat[x][y];
							if (y1<dim-2)
								y1++;
							else
							{
								x1++;
								y1=0;
							}
							
						}
						
					}
				}
				
				out = out + (posizione*calcoloLaPlace(matRidotta, dim-1));
				}
				i++;
			}
			return out;
		}
	}
	

}

package it.unibs.ing.fp.esame;

import java.util.ArrayList;

public class Tensor {
	
	private int dimensione;
	private ArrayList<Matrice> matrici;
	
	public Tensor (ArrayList<Matrice> a)
	{
		matrici = new ArrayList<>(a);
		dimensione=matrici.size();
	}
	
	public int calcolaIndice ()
	{
		int indice=0;
		for (Matrice m: matrici)
		{
			indice=indice+m.determinante();
		}
		return indice;
	}

}

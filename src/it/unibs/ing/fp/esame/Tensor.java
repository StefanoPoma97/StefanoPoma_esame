package it.unibs.ing.fp.esame;

import java.util.ArrayList;

public class Tensor {
	
	private int dimensione;
	private ArrayList<Matrice> matrici;
	
	/**
	 * costruttore che ricevendo un'ArrayList di matrice inizializza la variabile privata matrici e la dimensione del Tensor
	 * @param a un arrayList di Matrice
	 */
	public Tensor (ArrayList<Matrice> a)
	{
		matrici = new ArrayList<>(a);
		dimensione=matrici.size();
	}
	
	/**
	 * calcola l'indice di un tensor: la somma di tutti i determinananti delle sue matrici
	 * @return indice
	 */
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

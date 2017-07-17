package it.unibs.ing.fp.esame;

import java.util.ArrayList;
/**
 * classe tensor
 * @author Stefano
 *crea un tensor: insieme di più matrici.
 *permette di calcolarne l'indice: la somma di tutti i determinanti delle matrici da cui è composto
 *la dimensione del Tensor equivale alla dimensione delle singole matrici che lo compongono
 */
public class Tensor {
	
	private int dimensione;
	private ArrayList<Matrice> matrici;
	
	/**
	 * ritorna la dimensione del tensor
	 * @return dimensione
	 */
	public int getDimensione()
	{
		return dimensione;
	}
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
	 * si appoggia quindi al metodo determinante() di Matrice
	 * 
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

package it.unibs.ing.fp.esame;

import java.util.ArrayList;
/**
 * classe TensorNode
 * @author Stefano
 *nodo costituito da più tensor, matrici, e altri nodi figli
 *a seconda che sia un nodo radice o meno permette di calcolare il massimo tra gli indici dei Tensor e nodi figli o il minimo
 *la classe è concepita di modo che da lettura da Xml risulti un unico nodo radice con connessi ad esso altri nodi figli o direttamente dei Tensor o Matrici libere
 */
public class TensorNode {
	private String id;
	private ArrayList<Tensor> tensori = new ArrayList<>();
	private ArrayList<TensorNode> nodi= new ArrayList<>();
	private ArrayList<Matrice> matrici= new ArrayList<>();
	private boolean root=false;
	
	/**
	 * ritorna il numero di nodi connessi al nodo seguente
	 * @return numero di nodi connessi al seguente TensorNode
	 */
	public int numNodi()
	{
		return nodi.size();
	}
/**
 * stampa tutti i nodi connessi al TensorNode e per ognuno di essi indica il suo TensorUnit, ovvero l'indice massimo tra quello dei Tensor ad esso connessi
 */
	public void print()
	{
		System.out.println("numero di nodi connessi al nodo radice :"+nodi.size());
		for (int i=0; i<nodi.size(); i++)
		{
			System.out.println("nodo numero: "+ i);
			System.out.println("tensorunit del nodo: "+nodi.get(i).tensorUnit());
		}
	}
	/**
	 * 
	 * @param _id id Stringa
	 */
	public void setId(String _id)
	{
		id=_id;
	}
	/**
	 * 
	 * @return array di Tensor connessi al nodo
	 */
	public ArrayList<Tensor> getTensori ()
	{
		return tensori;
	}
	/**
	 * setta se il nodo è la radice
	 */
	public void setRoot()
	{
		root=true;
	}
	/**
	 * setta se il nodo non è la radice
	 */
	public void setRootFalse()
	{
		root=true;
	}
	/**
	 * aggiunge una matrice connessa a questo nodo
	 * @param a Matrice
	 */
	public void addMatrice (Matrice a)
	{
		matrici.add(a);
	}
	/**
	 * Aggiunge un tensor connesso a questo nodo
	 * @param a Tensor
	 */
	public void addTensor(Tensor a)
	{
		tensori.add(a);
	}
	/**
	 * aggiunge l'Arraylist di Tensor connessi a questo nodo
	 * @param a ArrayList di Tensor
	 */
	public void addTensori(ArrayList<Tensor>a)
	{
		tensori=new ArrayList<>(a);
	}
	/**
	 * aggiunge un nodo connesso a questo nodo
	 * @param a TensorNode
	 */
	public void addNode(TensorNode a)
	{
		nodi.add(a);
	}
	/**
	 * aggiunge un ArrayList di TensorNode connessi a questo nodo
	 * @param a ArrayList di TensorNode
	 */
	public void addNodi(ArrayList<TensorNode>a)
	{
		nodi=new ArrayList<>(a);
	}
	
	private int maxTensori()
	{
		int max=0;
		for (Tensor t: tensori)
		{
			if (t.calcolaIndice()>max)
				max=t.calcolaIndice();
			
		}
		return max;
	}
	
	private int indiceMatrici()
	{
		int out=0;
		for (Matrice m: matrici)
		{
			out=out+m.determinante();
		}
		return out;
	}
	private int minTensori()
	{
		int min=tensori.get(0).calcolaIndice();
		for (Tensor t: tensori)
		{
			if (t.calcolaIndice()<min)
				min=t.calcolaIndice();
			
		}
		return min;
	}
	
	private int maxNodi()
	{
		int max=0;
		for (TensorNode n: nodi)
		{
			for (Tensor t: n.getTensori())
			{
				if (t.calcolaIndice()>max)
					max=t.calcolaIndice();
			}
		}
		return max;
	}
	
	private int minNodi()
	{
		int min=nodi.get(0).getTensori().get(0).calcolaIndice();
		for (TensorNode n: nodi)
		{
			for (Tensor t: n.getTensori())
			{
				if (t.calcolaIndice()<min)
					min=t.calcolaIndice();
			}
		}
		return min;
	}
	
	private int massimo()
	{
		ArrayList<Integer>valori= new ArrayList<>();
		if (nodi.size()!=0)
		{
			valori.add(maxNodi());
		}
		if (matrici.size()!=0)
		{
			valori.add(indiceMatrici());
		}
		if (tensori.size()!=0)
		{
			valori.add(maxTensori());
		}
		int max=0;
		if (valori.size()==0)
			System.out.println("nodo vuoto");
		else
		{
			for (Integer value: valori)
			{
				if (value>max)
					max=value;
			}
		}
		return max;
	}
	
	private int minimo()
	{
		ArrayList<Integer>valori= new ArrayList<>();
		if (nodi.size()!=0)
		{
			valori.add(minNodi());
		}
		if (matrici.size()!=0)
		{
			valori.add(indiceMatrici());
		}
		if (tensori.size()!=0)
		{
			valori.add(minTensori());
		}
		int min=valori.get(0);
		if (valori.size()==0)
			System.out.println("nodo vuoto");
		else
		{
			min=valori.get(0);
			for (Integer value: valori)
			{
				if (value<min)
					min=value;
			}
		}
		return min;
	}
	/**
	 * restituisce il tensorUnit, ovvero il massimo se si tratta di un nodo qualsiasi, e il minimo se si tratta del nodo radice
	 * @return tensorUnit
	 */
	public int tensorUnit()
	{
		int output;
		if (root)
			output=minimo();
		else
			output=massimo();
		return output;
			
	}
	
	
	

	


}

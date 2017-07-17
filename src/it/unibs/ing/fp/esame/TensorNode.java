package it.unibs.ing.fp.esame;

import java.util.ArrayList;

public class TensorNode {
	private String id;
	private ArrayList<Tensor> tensori = new ArrayList<>();
	private ArrayList<TensorNode> nodi= new ArrayList<>();
	private ArrayList<Matrice> matrici= new ArrayList<>();
	private boolean root=false;
	
	public void setId(String _id)
	{
		id=_id;
	}
	
	public ArrayList<Tensor> getTensori ()
	{
		return tensori;
	}
	public void setRoot()
	{
		root=true;
	}
	
	public void addMatrice (Matrice a)
	{
		matrici.add(a);
	}
	public void addTensor(Tensor a)
	{
		tensori.add(a);
	}
	public void addTensori(ArrayList<Tensor>a)
	{
		tensori=new ArrayList<>(a);
	}
	public void addNode(TensorNode a)
	{
		nodi.add(a);
	}
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
		int min=0;
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

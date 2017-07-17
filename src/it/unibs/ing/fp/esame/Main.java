package it.unibs.ing.fp.esame;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][] mappa;
		mappa= new int [3][3];
		int k=0;
		mappa[0][0]=3;
		mappa[0][1]=1;
		mappa[0][2]=4;
		mappa[1][0]=0;
		mappa[1][1]=1;
		mappa[1][2]=0;
		mappa[2][0]=3;
		mappa[2][1]=2;
		mappa[2][2]=5;
		
//		for (int i=0; i<3; i++)
//		{
//			for (int u=0; u<3; u++)
//			{
//				mappa[i][u]=k;
//				k++;
//			}
//		}
		Matrice m = new Matrice (mappa);
		m.printMatrice();
		m.dimensione();
		System.out.println(m.determinante());
		
		ArrayList<TensorNode> elenco= new ArrayList<>();
		XmlParser xp = new XmlParser();
		try {
			elenco = xp.parseXml("input_0.xml");
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}
		
	}

}

package it.unibs.ing.fp.esame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;






public class XmlParser {

	File filename;
	/**
	 * permette la lettura da xml e la creazione di un array li tensorNode
	 * @param filename file di input
	 * @return ArrayList di TensorNode
	 * @throws FileNotFoundException
	 * @throws XMLStreamException
	 */
	public ArrayList<TensorNode> parseXml(String filename) throws FileNotFoundException, XMLStreamException {

		ArrayList<TensorNode> output = new ArrayList<>();
		try {
			this.filename = new File(filename);
		} catch (Exception e) {
			System.err.println("Il file " + filename + " non è disponibile o non è presente nella directory");
			return null;
		}

		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(this.filename));
		TensorNode tmp=new TensorNode();
		boolean root=true;
		boolean primaRigaMatrice=true;
		int cont=0;
		boolean e =false;
		int x=0;
		int y=0;
		int matrice [][]=new int [5][5];
		ArrayList<Integer> primaRiga= new ArrayList<>();
		String label="";
		ArrayList<Matrice> matrici= new ArrayList<>();
		ArrayList<Tensor> tensori= new ArrayList<>();
		
		ArrayList<TensorNode>nodiAdd=new ArrayList<>();
		int open=0;
		
		
		while (reader.hasNext()) {

			switch (reader.next()) {

			case XMLStreamConstants.START_DOCUMENT:
									System.out.println("Inizio a leggere il documento");
									break;

			case XMLStreamConstants.START_ELEMENT:
									switch (reader.getLocalName()) {
									case "TTree":
										// Se trovo il tag <tree> creo il grafo
										System.out.println("Inizio a leggere l'albero");
										break;
									case "TensorNode":
										open++;
										tmp=new TensorNode();
										
										break;
									case "label":
										// Se trovo il tag <node>, creo il nodo e imposto i
										// parametri start e end
										// Se nell'attributo start/end trovo il valore "true" allora
										// i parametri diventano true altrimenti false.ù
										if (root)
										{
											tmp.setRoot();
											root=false;
										}
										break;
									case "tensor":
										break;
									case "matrix":
										primaRigaMatrice=true;
										primaRiga= new ArrayList<>();
										x=0;
										y=0;
										// leggo il peso
										
										break;
									case "row":
										// leggo il peso
										break;
									case "column":
										break;
									}
									break;

			case XMLStreamConstants.CHARACTERS:
									if (reader.getTextLength() > 0) {
										 label = reader.getText().trim();
									}
									break;

			case XMLStreamConstants.END_ELEMENT:
									switch (reader.getLocalName()) {
									case "TTree":
										System.out.println("Ho finito di leggere il documento");
										break;
									case "column":
										if (primaRigaMatrice)
										{
											primaRiga.add(Integer.parseInt(label));
											label=null;
										}
										else
										{
											matrice[x][y]=Integer.parseInt(label);
											y++;
										}
										break;
									case "row":
										if (primaRigaMatrice)
										{
											matrice = new int [primaRiga.size()][primaRiga.size()];
											int i=0;
											for (Integer a: primaRiga)
											{
												matrice[0][i]=a;
												i++;
											}
											primaRigaMatrice=false;
												
										}
										else
										{
											x++;
											y=0;
										}
										break;
									case "label":
										tmp.setId(label);
//										System.out.println("inizio nodo"+label);
//										if (label.equalsIgnoreCase("G"))
//											e=true;
										break;
									case "matrix":
										matrici.add(new Matrice(matrice));
										break;
									case "tensor":
										cont++;
//										if (cont==15 && e==true)
//											System.out.println("quasi");
											
//										System.out.println("sto per aggiunger tensor numero: "+cont);
										tensori.add(new Tensor(matrici));
										matrici=new ArrayList<>();
										break;
										
									case "TensorNode":
										open--;
										if (tensori.size()==0)
											tmp=new TensorNode();
										if (open ==0)
										{
											
											tmp.addNodi(nodiAdd);
											nodiAdd=new ArrayList<>();
											output.add(tmp);
											tensori=new ArrayList<>();
											tmp=null;
										}
										
										else if (tensori.size()!=0)
										{
											tmp.addTensori(tensori);
											nodiAdd.add(tmp);
											tmp=null;
											tensori=new ArrayList<>();
										}
										
										cont=0;
										
										break;
									}
									
									break;
			case XMLStreamConstants.END_DOCUMENT:
				break;
			}

		}
		return output;

	}
	
}

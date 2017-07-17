package it.unibs.ing.fp.esame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Vector;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;




public class XmlParser {

	File filename;
	public ArrayList<TensorNode> parseXml(String filename) throws FileNotFoundException, XMLStreamException
	{
		ArrayList<TensorNode> output = new ArrayList<>();
		try {
			this.filename = new File(filename);
		} catch (Exception e) {
			System.err.println("Il file " + filename + " non è disponibile o non è presente nella directory");
			return null;
		}
		
		// Inizializzo le variabili
				XMLInputFactory factory = XMLInputFactory.newInstance();
				XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(this.filename));
				TensorNode tmp= null;
				boolean root=true;
				boolean primaRigaMatrice=true;
				int x=0;
				int y=0;
				int matrice [][];
				ArrayList<Integer> primaRiga= new ArrayList<>();
				String label;
				ArrayList<Matrice> matrici= new ArrayList<>();
				Tensor tensore;
				ArrayList<Tensor> tensori= new ArrayList<>();
				ArrayList<TensorNode>ritorno=new ArrayList<>();
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
							tensore= null;
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
							break;
						case "matrix":
							matrici.add(new Matrice(matrice));
							break;
						case "tensor":
							tensori.add(new Tensor(matrici));
							matrici=null;
							break;
							
						case "TensorNode":
							if (open >1)
							{
								tmp.addTensori(tensori);
								nodiAdd.add(tmp);
								tmp=null;
							}
							else
							{
								tmp.addNodi(nodiAdd);
								nodiAdd=null;
								ritorno.add(tmp);
								tmp=null;
							}
							break;
						}
						
						break;
					case XMLStreamConstants.END_DOCUMENT:
						break;
					}
					
					
				}
				
				return ritorno;

					}

					
		
					
		
				}

package modele;

import javax.swing.table.DefaultTableModel;

public class ModeleLivreTable extends DefaultTableModel{

	String[] intitules = {"ID_LIVRE","TITRE","AUTEUR"};
	int nbLignes;
	int nbColonnes = 3;
	public ModeleLivreTable(Livre[] data) {
		this.nbLignes = data.length;
		this.setColumnIdentifiers(intitules);
		this.setRowCount(nbLignes);
	
		for(int i = 0; i < data.length; i++) {
			setValueAt(data[i].getId_livre(),i,0);
			setValueAt(data[i].getTitre(),i,1);
			setValueAt(data[i].getAuteur(),i,2);
		}
	}
	
	public boolean isCellEditable (int indiceLigne, int indiceColonne)  {
		return false ;
		} 
	/*
	public Class getColumnClass (int indiceColonne)  {
		if  (indiceColonne == 3)
			return Integer.class;
		else return String.class;
		}
		*/
}

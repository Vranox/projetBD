package modele;

import javax.swing.table.DefaultTableModel;

public class ModeleResTable extends DefaultTableModel{

	String[] intitules = {"ID_ET","NOM","PRENOM","TITRE","DATE_RES"};
	int nbLignes;
	int nbColonnes = 5;
	public ModeleResTable(Reservation[] data) {
		this.nbLignes = data.length;
		this.setColumnIdentifiers(intitules);
		this.setRowCount(nbLignes);
		//System.out.println(data.length+" "+data[0].getId_et());
		for(int i = 0; i < data.length; i++) {
			setValueAt(data[i].getId_et(),i,0);
			setValueAt(data[i].getNom(),i,1);
			setValueAt(data[i].getPrenom(),i,2);
			setValueAt(data[i].getTitre(),i,3);
			setValueAt(data[i].getDate_res(),i,4);
			
		}
	}
}

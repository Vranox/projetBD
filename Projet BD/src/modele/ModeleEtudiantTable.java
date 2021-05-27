package modele;

import javax.swing.table.DefaultTableModel;

public class ModeleEtudiantTable extends DefaultTableModel{

	String[] intitules = {"ID_ET","NOM","PRENOM","EMAIL","MDP"};
	int nbLignes;
	int nbColonnes = 5;
	public ModeleEtudiantTable(Etudiant[] data) {
		this.nbLignes = data.length;
		this.setColumnIdentifiers(intitules);
		this.setRowCount(nbLignes);
		//System.out.println(data.length+" "+data[0].getId_et());
		for(int i = 0; i < data.length; i++) {
			setValueAt(data[i].getId_et(),i,0);
			setValueAt(data[i].getNom(),i,1);
			setValueAt(data[i].getPrenom(),i,2);
			setValueAt(data[i].getEmail(),i,3);
			setValueAt(data[i].getPwd(),i,4);
			
		}
	}
	
	public boolean isCellEditable (int indiceLigne, int indiceColonne)  {
		return false ;
		} 
}


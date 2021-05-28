package vue;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.*;

public class FenetreWarning extends JDialog{
	PanelWarningEtudiant panelWarningEtudiant;
	public FenetreWarning(String etudiant) {
		super((Window)null);
		setModal(true);
		panelWarningEtudiant = new PanelWarningEtudiant(etudiant);
		setContentPane(panelWarningEtudiant);
		setSize(500,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
	}
	public PanelWarningEtudiant getPanelWarningEtudiant() {
		return panelWarningEtudiant;
	}
}

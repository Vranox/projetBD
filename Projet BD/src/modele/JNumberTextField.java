package modele;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class JNumberTextField extends HintTextField {
    public JNumberTextField(String hint, Color parCouleur) {
		super(hint, parCouleur);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

    @Override
    public void processKeyEvent(KeyEvent ev) {
    	char c = ev.getKeyChar();
        if (Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            super.processKeyEvent(ev);
        }
        
        ev.consume();
        return;
    }

    /**
     * As the user is not even able to enter a dot ("."), only integers (whole numbers) may be entered.
     */
    public Long getNumber() {
        Long result = null;
        String text = getText();
        if (text != null && !"".equals(text)) {
            result = Long.valueOf(text);
        }
        return result;
    }
}
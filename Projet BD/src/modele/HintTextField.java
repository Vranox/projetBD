package modele;
import java.awt.Color;  
import java.awt.Font;  
import java.awt.event.FocusAdapter;  
import java.awt.event.FocusEvent;  
import javax.swing.JTextField;  
  
public class HintTextField extends JTextField {  
  
  Font gainFont = new Font("Tahoma", Font.PLAIN, 14);  
  Font lostFont = new Font("Tahoma", Font.ITALIC, 14);  
  Color blanc = new Color(230,230,230);
  Color couleur;
  public HintTextField(final String hint,Color parCouleur) {  
	couleur = parCouleur;
    setText(hint);  
    setFont(lostFont);  
    setForeground(couleur);  
  
    this.addFocusListener(new FocusAdapter() {  
  
      @Override  
      public void focusGained(FocusEvent e) {  
        if (getText().equals(hint)) {  
          setText("");  
          setFont(gainFont);  
        } else {  
          setText(getText());  
          setFont(gainFont);  
        }  
      }  
  
      @Override  
      public void focusLost(FocusEvent e	) {  
        if (getText().equals(hint)|| getText().length()==0) {  
          setText(hint);  
          setFont(lostFont);  
          setForeground(couleur);  
        } else {  
          setText(getText());  
          setFont(gainFont);  
          setForeground(couleur);  
        }  
      }  
    });  
  
  }  
}  
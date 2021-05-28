package modele;
import java.awt.Color;  
import java.awt.Font;  
import java.awt.event.FocusAdapter;  
import java.awt.event.FocusEvent;

import javax.swing.JPasswordField;
import javax.swing.JTextField;  
  
public class HintPasswordField extends JPasswordField {  
  
  Font gainFont = new Font("Tahoma", Font.PLAIN, 11);  
  Font lostFont = new Font("Tahoma", Font.ITALIC, 11);  
  Color blanc = new Color(230,230,230);
  public HintPasswordField(final String hint,Color couleur) {  
  
    setText(hint);  
    setFont(lostFont);  
    setForeground(couleur);  
  
    this.addFocusListener(new FocusAdapter() {  
  
      @Override  
      public void focusGained(FocusEvent e) {  
        if (new String(getPassword()).equals(hint)) {  
          setText("");  
          setFont(gainFont);  
        } else {  
          setText(new String(getPassword()));  
          setFont(gainFont);  
        }  
      }  
  
      @Override  
      public void focusLost(FocusEvent e) {  
        if (new String(getPassword()).equals(hint)|| getPassword().length==0) {  
          setText(hint);  
          setFont(lostFont);  
          setForeground(blanc);  
        } else {  
          setText(new String(getPassword()));  
          setFont(gainFont);  
          setForeground(blanc);  
        }  
      }  
    });  
  
  }  
}  

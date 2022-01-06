package ui;

import Model.Money;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DisplaySwing extends JPanel implements Display {
    
    private final JTextArea showMoney;

    public DisplaySwing() {
        showMoney = new JTextArea(2,10);
        this.showMoney.setEditable(false);
        this.add(this.showMoney);
    }
    
    
    
    @Override
    public void display(Money money) {
        this.showMoney.setText(money.toString());
    }  
}
    


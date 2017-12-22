import java.awt.* ;
import javax.swing.* ;

public class FloodIt {

    public static void main(String[] args) {
		//StudentInfo.display();
		int v ;
		GameController game ;
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		JLabel firstLabel = new JLabel("Give a size for the grid: ");
		JLabel secondLabel = new JLabel("(Just press OK if you want to continue previous game.)");
		JTextField text = new JTextField();
		panel.add(firstLabel);
		panel.add(text);
		panel.add(secondLabel);
		JOptionPane.showMessageDialog(null, panel, "Flood-It--Grid size", JOptionPane.PLAIN_MESSAGE, null) ;
		String txt = text.getText();
		
		if(txt.length()==0){
			game=new GameController();
		} else {
			try{
				v=Integer.parseInt(txt);
				if (v<10) {
					v=12;
				}
			} catch (NumberFormatException e){
				v=12;
			}
			game = new GameController(v);
		
		}
		
    }
	
}
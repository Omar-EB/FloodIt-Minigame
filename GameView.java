import java.awt.*;
import javax.swing.*;

public class GameView extends JFrame {
	private GameModel model;
	private GameController gameController;
	private JLabel input;
	private DotButton[][] buttonTable;
	private JButton undo;
	private JButton redo;
	private JButton quit;


    public GameView(GameModel model, GameController gameController) {
		this.model=model;
		this.gameController=gameController;
		setLayout(new BorderLayout());
		this.setTitle("Flood-It");
		buttonTable= new DotButton[model.getSize()][model.getSize()];
		
		JPanel settingsPanel=new JPanel();
		settingsPanel.setLayout(new FlowLayout());
		settingsPanel.setBackground(Color.white);
		add(settingsPanel, BorderLayout.NORTH);
		
		undo = new JButton("Undo");
		undo.addActionListener( gameController );
		settingsPanel.add(undo);
		deactivateUndo();
		
		redo = new JButton("Redo");
		redo.addActionListener( gameController );
		settingsPanel.add(redo);
		deactivateRedo();
		
		JButton settings = new JButton("Settings");
		settings.addActionListener( gameController );
		settingsPanel.add(settings);
		
		
		
		
		JPanel viewPanel=new JPanel();
		viewPanel.setLayout(new GridLayout(model.getSize(),model.getSize()));
		viewPanel.setBackground(Color.white);
		add(viewPanel, BorderLayout.CENTER);
		
		
		
		int s ;
		if (model.getSize()>=25){
			s = -1;
		} else {
			s = 0 ; 
		}
		for (int Y=model.getSize()-1; Y>-1;Y--){
			for (int X=0;X<model.getSize();X++){
				buttonTable[Y][X]=new DotButton(Y,X,model.getColor(X,Y),s);
				buttonTable[Y][X].setActionCommand("Initial Dot");
				buttonTable[Y][X].addActionListener( gameController );
			}
		}
		
		
		for (int j=model.getSize()-1; j>-1;j--){
			for (int i=0;i<model.getSize();i++){
				viewPanel.add(buttonTable[j][i]);
			}
		}
		
		for (int j=model.getSize()-1; j>-1;j--){
			for (int i=0;i<model.getSize();i++){
				viewPanel.add(buttonTable[j][i]);
			}
		}
		
		
		
		JButton reset = new JButton("Reset");
		reset.addActionListener( gameController );
		
		quit = new JButton("Quit");
		quit.addActionListener( gameController );
		
		JPanel thirdPanel = new JPanel();
		thirdPanel.setLayout(new FlowLayout());
		thirdPanel.setBackground(Color.white);
		add(thirdPanel, BorderLayout.SOUTH);
		
		input = new JLabel ();
		input.setText("Select initial dot");
		thirdPanel.add(input);
		
		thirdPanel.add(reset);
		thirdPanel.add(quit);
		
		pack();
		setLocationRelativeTo(null);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
    }
		 
	public void updateModel(GameModel model){
		this.model=model;
	}
	 	 
	public void deactivateUndo(){
		undo.setEnabled(false);
	}
	 
	public void activateUndo(){
		undo.setEnabled(true);
	}
	 	 
	public void deactivateRedo(){
		redo.setEnabled(false);
	}
	 	 
	public void activateRedo(){
		redo.setEnabled(true);
	}
	
	public void settingsPanel(){
		JPanel options = new JPanel();
		options.setLayout(new GridLayout(0,1));
		JRadioButton plane = new JRadioButton("Plane");
		JRadioButton torus = new JRadioButton("Torus");
		ButtonGroup gameMode = new ButtonGroup();
		gameMode.add(plane);
		gameMode.add(torus);
		
		JRadioButton orthogonal = new JRadioButton("Orthogonal");
		JRadioButton diagonal = new JRadioButton("Diagonal");
		ButtonGroup gameMoves = new ButtonGroup();
		gameMoves.add(orthogonal);
		gameMoves.add(diagonal);
		
		options.add(new JLabel("Would you like to play on a Plane or a Torus?"));
		options.add(plane);
		options.add(torus);
		if(!model.getTorus()){
			plane.setSelected(true);
		} else {
			torus.setSelected(true);
		}
		options.add(new JLabel("Would you like to activate Diagonal moves?"));
		options.add(orthogonal);
		options.add(diagonal);
		if(!model.getDiagonal()){
			orthogonal.setSelected(true);
		} else {
			diagonal.setSelected(true);
		}
		JOptionPane.showMessageDialog(this, options, "Settings", JOptionPane.PLAIN_MESSAGE, null);
		
		if (plane.isSelected()){
			model.setTorus(false);
		} else if (torus.isSelected()){
			model.setTorus(true);
		}
		//
		if (diagonal.isSelected()){
			model.setDiagonal(true);
		} else if (orthogonal.isSelected()){
			model.setDiagonal(false);
		}
		
	}

    public void update() {
			for (int Y=model.getSize()-1; Y>-1;Y--){
				for (int X=0;X<model.getSize();X++){
					buttonTable[Y][X].setColor(model.getColor(X,Y));	
				}
			}
			if(model.getNumberOfSteps()==-1){
				input.setText("Select Initial Dot");
				for (int Y=model.getSize()-1; Y>-1;Y--){
					for (int X=0;X<model.getSize();X++){
						buttonTable[Y][X].setActionCommand("Initial Dot");	
					}
				}
				
			} else {
				activateDots();
			}
			if (model.isFinished()){
				Object[] possibleValues = {"Play again", "Quit"};
				Object selectedValue = JOptionPane.showOptionDialog(this, "You won in "+model.getNumberOfSteps()+" steps!"+System.lineSeparator()+"Would you like to play again?", "WIN!",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, possibleValues, possibleValues[0]);
				if ( selectedValue.equals(1)){
					quit.doClick();
				} else {
					gameController.reset();
				}
			}
			
    }
	
	public void activateDots(){
		for (int j=model.getSize()-1; j>-1;j--){
			for (int i=0;i<model.getSize();i++){
				buttonTable[j][i].setActionCommand(Integer.toString(model.getColor(i,j)));
			}
		}
			
		input.setText("Steps: "+model.getNumberOfSteps());
		
	}

}
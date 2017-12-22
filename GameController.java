import java.awt.event.*;
import java.io.* ;

@SuppressWarnings("unchecked")


public class GameController implements ActionListener{
	private GameModel model;
	private GameView gameView;
	//private TextView view;
	private int color;
	private int size;
	private Stack<GameModel>undoStack;
	private Stack<GameModel>redoStack;
	

	 
    public GameController(int size) {
		this.size=size;
		model= new GameModel(size);
		gameView= new GameView(model,this);
		undoStack= new GenericLinkedStack<GameModel>();
		redoStack= new GenericLinkedStack<GameModel>();
		gameView.update();
		gameView.settingsPanel();
		
		File f1 = new File("savedGame.ser");
		File f2 = new File("savedUndo.ser");
		File f3 = new File("savedRedo.ser");
		if (f1.exists()){
			f1.delete();
		}
		if (f2.exists()){
			f2.delete();
		}
		if (f3.exists()){
			f3.delete();
		}

		//view= new TextView(model);
		//System.out.println(model);
		//view.update();
		
    }
	
	public GameController(){
		boolean construct = true;
		FileInputStream savedGame=null;
		FileInputStream savedUndo=null;
		FileInputStream savedRedo=null;
		try{
			savedGame = new FileInputStream("savedGame.ser");
			savedUndo = new FileInputStream("savedUndo.ser");
			savedRedo = new FileInputStream("savedRedo.ser");
			readObject(new ObjectInputStream(savedGame));
			readUndo(new ObjectInputStream(savedUndo));
			readRedo(new ObjectInputStream(savedRedo));
			File f1 = new File("savedGame.ser");
			File f2 = new File("savedUndo.ser");
			File f3 = new File("savedRedo.ser");
			f1.delete();
			f2.delete();
			f3.delete();
		} catch(FileNotFoundException x){
			try{
				if(savedGame!=null){
					savedGame.close();
				}
				if(savedUndo!=null){
					savedUndo.close();
				}
				if(savedRedo!=null){
					savedRedo.close();
				}
				
			} catch (IOException exc){
				exc.printStackTrace();
			}
			try{
				readObjectNoData();
			} catch(ObjectStreamException ex) {
				System.out.println(ex);
			}
			construct = false;
		} catch(IOException x){
			System.out.println(x);
		} catch (ClassNotFoundException x){
			System.out.println(x);
		}
		if(construct){
			size=model.getSize();
			gameView= new GameView(model,this);
			if(!redoStack.isEmpty()){
				gameView.activateRedo();
			}
			if(!undoStack.isEmpty()){
				gameView.activateUndo();
			}
			gameView.update();
		}
	}

    public void reset() {
		model.reset();
		gameView.update();
		gameView.deactivateUndo();
		gameView.deactivateRedo();
		while(!undoStack.isEmpty()){
			undoStack.pop();
		}
		while(!redoStack.isEmpty()){
			redoStack.pop();
		}
		gameView.settingsPanel();
		//view.update();
		
    }
	
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeObject(model);
		out.close();
		
	}
	private void writeUndo(ObjectOutputStream out) throws IOException {
		out.writeObject(undoStack);
		out.close();
		
	}
	private void writeRedo(ObjectOutputStream out) throws IOException {
		out.writeObject(redoStack); 
		out.close();
	
	}
	
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		model = (GameModel) in.readObject();
		in.close();
		
	}
	private void readUndo(ObjectInputStream in) throws IOException, ClassNotFoundException{ 
		undoStack = (Stack<GameModel>) in.readObject(); 
		in.close();
		
	}
	private void readRedo(ObjectInputStream in) throws IOException, ClassNotFoundException{
		redoStack = (Stack<GameModel>) in.readObject(); 
		in.close();
	}
	
	
	
	private void readObjectNoData()throws ObjectStreamException{
		this.size=12;
		model= new GameModel(12);
		gameView= new GameView(model,this);
		undoStack= new GenericLinkedStack<GameModel>();
		redoStack= new GenericLinkedStack<GameModel>();
		gameView.update();
		gameView.settingsPanel();
		File f1 = new File("savedGame.ser");
		File f2 = new File("savedUndo.ser");
		File f3 = new File("savedRedo.ser");
			f1.delete();
			f2.delete();
			f3.delete();
	}


    public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Initial Dot")) {
			GameModel previousModel=model.clone();
			undoStack.push(previousModel);
			gameView.activateUndo();
			gameView.deactivateRedo();
				while(!redoStack.isEmpty()){
					redoStack.pop();
				}
			
			DotButton selectedButton =  (DotButton) e.getSource();
			int X = selectedButton.getColumn();
			int Y = selectedButton.getRow();
			model.capture(X,Y);
			selectColor(selectedButton.getColor());
		}
		// //
		if(e.getActionCommand().equals("0")) {
			if (model.getCurrentSelectedColor()!=0){
				GameModel previousModel=model.clone();
				undoStack.push(previousModel);
				gameView.activateUndo();
				gameView.deactivateRedo();
				while(!redoStack.isEmpty()){
					redoStack.pop();
				}
				//
				selectColor(0);
				
			}
		}
		// //
		if(e.getActionCommand().equals("1")) {
			if (model.getCurrentSelectedColor()!=1){
				GameModel previousModel=model.clone();
				undoStack.push(previousModel);
				gameView.activateUndo();
				gameView.deactivateRedo();
				while(!redoStack.isEmpty()){
					redoStack.pop();
				}
				//
				selectColor(1);
			}
		}
		// //
		if(e.getActionCommand().equals("2")) {
			if (model.getCurrentSelectedColor()!=2){
				GameModel previousModel=model.clone();
				undoStack.push(previousModel);
				gameView.activateUndo();
				gameView.deactivateRedo();
				while(!redoStack.isEmpty()){
					redoStack.pop();
				}
				//
				selectColor(2);
			}
		}
		// //
		if(e.getActionCommand().equals("3")) {
			if (model.getCurrentSelectedColor()!=3){
				GameModel previousModel=model.clone();
				undoStack.push(previousModel);
				gameView.activateUndo();
				gameView.deactivateRedo();
				while(!redoStack.isEmpty()){
					redoStack.pop();
				}
				//
				selectColor(3);
			}
		}
		// //
		if(e.getActionCommand().equals("4")) {
			if (model.getCurrentSelectedColor()!=4){
				GameModel previousModel=model.clone();
				undoStack.push(previousModel);
				gameView.activateUndo();
				gameView.deactivateRedo();
				while(!redoStack.isEmpty()){
					redoStack.pop();
				}
				//
				selectColor(4);
			}
		}
		// //
		if(e.getActionCommand().equals("5")) {
			if (model.getCurrentSelectedColor()!=5){
				GameModel previousModel=model.clone();
				undoStack.push(previousModel);
				gameView.activateUndo();
				gameView.deactivateRedo();
				while(!redoStack.isEmpty()){
					redoStack.pop();
				}
				//
				selectColor(5);
			}
		}
		// //
		if(e.getActionCommand().equals("Reset")) {
			reset();
		}
		// //
		if(e.getActionCommand().equals("Quit")) {
			if( model.getNumberOfSteps()>0 && !model.isFinished()){
				try{
					FileOutputStream savedGame = new FileOutputStream("savedGame.ser");
					FileOutputStream savedUndo = new FileOutputStream("savedUndo.ser");
					FileOutputStream savedRedo = new FileOutputStream("savedRedo.ser");
					writeObject(new ObjectOutputStream(savedGame));
					writeUndo(new ObjectOutputStream(savedUndo));
					writeRedo(new ObjectOutputStream(savedRedo));
					
				} catch(FileNotFoundException x){
					System.out.println(x);
				} catch(IOException x){
					System.out.println(x);
				}
			}
			System.exit(0);
			
		}
		// //
		if(e.getActionCommand().equals("Settings")) {
			GameModel dummyModel=model.clone();
			gameView.settingsPanel();
			if((model.getTorus()!=dummyModel.getTorus()) || (model.getDiagonal()!=dummyModel.getDiagonal()) ){
				gameView.deactivateRedo();
				while(!redoStack.isEmpty()){
					redoStack.pop();
				}
				selectColor(model.getCurrentSelectedColor());
				model.stepBack(); // utilisé juste au cas ou le joueur change les réglages pendant le jeu et que des nouvelles billes doivent etre conquises.
			}
		}
		// //
		if(e.getActionCommand().equals("Undo")) {

			GameModel previousModel=undoStack.pop();
			GameModel currentModel=model;

			redoStack.push(currentModel);
				
			gameView.updateModel(previousModel);
			model=previousModel;
			
			if (undoStack.isEmpty()){
				gameView.deactivateUndo();
			}
			gameView.activateRedo();
		}
		// //
		if(e.getActionCommand().equals("Redo")) {
			
			GameModel nextModel = redoStack.pop();
			GameModel currentModel=model;
				
			undoStack.push(currentModel);
				
			gameView.updateModel(nextModel);
			model=nextModel;
				
			if (redoStack.isEmpty()){
				gameView.deactivateRedo();
			}
			gameView.activateUndo();
		}
		gameView.update();
		
		//view.update();

    }

    public void selectColor(int color) {
			model.step();
			Stack<DotInfo> s = new GenericLinkedStack<DotInfo>();
			this.color = color;
			model.setCurrentSelectedColor(color);
			for (int j=0;j<size;j++){
				for (int i=0;i<size;i++){
					if (model.isCaptured(i,j)==true){
						model.capture(i,j);
						s.push(model.get(i,j));
					}	
				}
			}
			while (!s.isEmpty()){
				DotInfo d = s.pop();
				int X= d.getX();
				int Y= d.getY();
				if (X!=0 && X!=size-1 && Y!=0 && Y!=size-1){
					for (int i=X-1;i<X+2;i++){
						if (i!= X){
							if(model.isCaptured(i,Y) == false && model.getColor(i,Y) == color){
								model.capture(i,Y);
								s.push(model.get(i,Y));
							}
						}
					}
					for (int j=Y-1;j<Y+2;j++){
						if (j!= Y){
							if(model.isCaptured(X,j) == false && model.getColor(X,j) == color){
								model.capture(X,j);
								s.push(model.get(X,j));
							}
						}
					}
					
					if(model.getDiagonal()){
						for(int a = X-1;a<X+2;a++){
							for(int b = Y+1;b>Y-2;b--){
								if(a!=X && b!=Y){
									if(model.isCaptured(a,b) == false && model.getColor(a,b) == color){
										model.capture(a,b);
										s.push(model.get(a,b));
									}
								}
							}
						}
					}
				} else if(X==0 && 0<Y && Y<size-1){
					for (int i=X;i<X+2;i++){
						if (i!= X){
							if(model.isCaptured(i,Y) == false && model.getColor(i,Y) == color){
								model.capture(i,Y);
								s.push(model.get(i,Y));
							}
						}
					}
					for (int j=Y-1;j<Y+2;j++){
						if (j!= Y){
							if(model.isCaptured(X,j) == false && model.getColor(X,j) == color){
								model.capture(X,j);
								s.push(model.get(X,j));
							}
						}
					}
					
					if (model.getTorus()){
						if(model.isCaptured(size-1,Y)==false && model.getColor(size-1,Y) == color){
							model.capture(size-1,Y);
							s.push(model.get(size-1,Y));
						
						}
						
						if (model.getDiagonal()){ 
							for(int b = Y+1;b>Y-2;b--){
								if(b!=Y){
									if(model.isCaptured(size-1,b) == false && model.getColor(size-1,b) == color){
										model.capture(size-1,b);
										s.push(model.get(size-1,b));
									}
								}
							}
						}
					}
					
					if(model.getDiagonal()){
							for(int b = Y+1;b>Y-2;b--){
								if(b!=Y){
									if(model.isCaptured(X+1,b) == false && model.getColor(X+1,b) == color){
										model.capture(X+1,b);
										s.push(model.get(X+1,b));
									}
								}
							}
					}
					
				
				} else if (X==size-1 && 0<Y && Y<size-1){
					for (int i=X-1;i<X+1;i++){
						if (i!= X){
							if(model.isCaptured(i,Y) == false && model.getColor(i,Y) == color){
								model.capture(i,Y);
								s.push(model.get(i,Y));
							}
						}
					}
					for (int j=Y-1;j<Y+2;j++){
						if (j!= Y){
							if(model.isCaptured(X,j) == false && model.getColor(X,j) == color){
								model.capture(X,j);
								s.push(model.get(X,j));
							}
						}
					}
					
					if (model.getTorus()){
						if(model.isCaptured(0,Y)==false && model.getColor(0,Y) == color){
							model.capture(0,Y);
							s.push(model.get(0,Y));
						
						}
						
						if (model.getDiagonal()){ 
							for(int b = Y+1;b>Y-2;b--){
								if(b!=Y){
									if(model.isCaptured(0,b) == false && model.getColor(0,b) == color){
										model.capture(0,b);
										s.push(model.get(0,b));
									}
								}
							}
						}
					}
					
					if(model.getDiagonal()){
							for(int b = Y+1;b>Y-2;b--){
								if(b!=Y){
									if(model.isCaptured(X-1,b) == false && model.getColor(X-1,b) == color){
										model.capture(X-1,b);
										s.push(model.get(X-1,b));
									}
								}
							}
					}
				
					
				} else if(0<X && X<size-1 && Y==0){
					for (int i=X-1;i<X+2;i++){
						if (i!= X){
							if(model.isCaptured(i,Y) == false && model.getColor(i,Y) == color){
								model.capture(i,Y);
								s.push(model.get(i,Y));			
							}
						}
					}
					for (int j=Y;j<Y+2;j++){
						if (j!= Y){
							if(model.isCaptured(X,j) == false && model.getColor(X,j) == color){
								model.capture(X,j);
								s.push(model.get(X,j));						
							}
						}
					}
					
					if (model.getTorus()){
						if(model.isCaptured(X,size-1)==false && model.getColor(X,size-1) == color){
							model.capture(X,size-1);
							s.push(model.get(X,size-1));
						
						}
						
						if(model.getDiagonal()){ 
							for(int a = X-1;a<X+2;a++){
								if(a!=X){
									if(model.isCaptured(a,size-1) == false && model.getColor(a,size-1) == color){
										model.capture(a,size-1);
										s.push(model.get(a,size-1));
									}
								}
							}
						}
					}
					
					if(model.getDiagonal()){
							for(int a = X-1;a<X+2;a++){
								if(a!=X){
									if(model.isCaptured(a,Y+1) == false && model.getColor(a,Y+1) == color){
										model.capture(a,Y+1);
										s.push(model.get(a,Y+1));
									}
								}
							}
					}
					
				
				} else if(0<X && X<size-1 && Y==size-1){
					for (int i=X-1;i<X+2;i++){
						if (i!= X){
							if(model.isCaptured(i,Y) == false && model.getColor(i,Y) == color){
								model.capture(i,Y);
								s.push(model.get(i,Y));								
							}
						}
					}
					for (int j=Y-1;j<Y+1;j++){
						if (j!= Y){
							if(model.isCaptured(X,j) == false && model.getColor(X,j) == color){
								model.capture(X,j);
								s.push(model.get(X,j));
							}
						}
					}
					
					if (model.getTorus()){
						if(model.isCaptured(X,0)==false && model.getColor(X,0) == color){
							model.capture(X,0);
							s.push(model.get(X,0));
						}
						if(model.getDiagonal()){ 
							for(int a = X-1;a<X+2;a++){
								if(a!=X){
									if(model.isCaptured(a,0) == false && model.getColor(a,0) == color){
										model.capture(a,0);
										s.push(model.get(a,0));
									}
								}
							}
						}
					}
					
					if(model.getDiagonal()){
							for(int a = X-1;a<X+2;a++){
								if(a!=X){
									if(model.isCaptured(a,Y-1) == false && model.getColor(a,Y-1) == color){
										model.capture(a,Y-1);
										s.push(model.get(a,Y-1));
									}
								}
							}
					}
				
				} else if (X==0 && Y==0){
					if (model.isCaptured(X+1,Y) == false && model.getColor(X+1,Y) == color){
								model.capture(X+1,Y);
								s.push(model.get(X+1,Y));
					}
					if (model.isCaptured(X,Y+1) == false && model.getColor(X,Y+1) == color){
								model.capture(X,Y+1);
								s.push(model.get(X,Y+1));
					}
					
					if (model.getTorus()){
						if(model.isCaptured(X,size-1)==false && model.getColor(X,size-1) == color){
							model.capture(X,size-1);
							s.push(model.get(X,size-1));
						}
						if(model.isCaptured(size-1,Y)==false && model.getColor(size-1,Y) == color){
							model.capture(size-1,Y);
							s.push(model.get(size-1,Y));
						}
						
						if(model.getDiagonal()){ 
							if(model.isCaptured(size-1,Y+1)==false && model.getColor(size-1,Y+1) == color){
								model.capture(size-1,Y+1);
								s.push(model.get(size-1,Y+1));
							}
							if(model.isCaptured(X+1,size-1)==false && model.getColor(X+1,size-1) == color){
								model.capture(X+1,size-1);
								s.push(model.get(X+1,size-1));
							}
						}
					}
					
					if(model.getDiagonal()){
						if(model.isCaptured(X+1,Y+1)==false && model.getColor(X+1,Y+1) == color){
							model.capture(X+1,Y+1);
							s.push(model.get(X+1,Y+1));
						}
					}
					
				} else if (X==0 && Y==size-1){
					if (model.isCaptured(X+1,Y) == false && model.getColor(X+1,Y) == color){
								model.capture(X+1,Y);
								s.push(model.get(X+1,Y));
					}
					if (model.isCaptured(X,Y-1) == false && model.getColor(X,Y-1) == color){
								model.capture(X,Y-1);
								s.push(model.get(X,Y-1));
					}
					
					if (model.getTorus()){
						if(model.isCaptured(0,0)==false && model.getColor(0,0) == color){
							model.capture(0,0);
							s.push(model.get(0,0));
						}
						if(model.isCaptured(size-1,size-1)==false && model.getColor(size-1,size-1) == color){
							model.capture(size-1,size-1);
							s.push(model.get(size-1,size-1));
						}
						
						if(model.getDiagonal()){ 
							if(model.isCaptured(size-1,Y-1)==false && model.getColor(size-1,Y-1) == color){
								model.capture(size-1,Y-1);
								s.push(model.get(size-1,Y-1));
							}
							if(model.isCaptured(X+1,0)==false && model.getColor(X+1,0) == color){
								model.capture(X+1,0);
								s.push(model.get(X+1,0));
							}
						}
					}
					
					if(model.getDiagonal()){
						if(model.isCaptured(X+1,Y-1)==false && model.getColor(X+1,Y-1) == color){
							model.capture(X+1,Y-1);
							s.push(model.get(X+1,Y-1));
						}
					}
					
				} else if (X==size-1 && Y==0){
					if (model.isCaptured(X-1,Y) == false && model.getColor(X-1,Y) == color){
								model.capture(X-1,Y);
								s.push(model.get(X-1,Y));
					}
					if (model.isCaptured(X,Y+1) == false && model.getColor(X,Y+1) == color){
								model.capture(X,Y+1);
								s.push(model.get(X,Y+1));
					}
					
					if (model.getTorus()){
						if(model.isCaptured(0,0)==false && model.getColor(0,0) == color){
							model.capture(0,0);
							s.push(model.get(0,0));
						}
						if(model.isCaptured(size-1,size-1)==false && model.getColor(size-1,size-1) == color){
							model.capture(size-1,size-1);
							s.push(model.get(size-1,size-1));
						}
						
						if(model.getDiagonal()){ 
							if(model.isCaptured(X-1,size-1)==false && model.getColor(X-1,size-1) == color){
								model.capture(X-1,size-1);
								s.push(model.get(X-1,size-1));
							}
							if(model.isCaptured(0,Y+1)==false && model.getColor(0,Y+1) == color){
								model.capture(0,Y+1);
								s.push(model.get(0,Y+1));
							}
						}
					}
					
					if(model.getDiagonal()){
						if(model.isCaptured(X-1,Y+1)==false && model.getColor(X-1,Y+1) == color){
							model.capture(X-1,Y+1);
							s.push(model.get(X-1,Y+1));
						}
					}
					
				} else if (X==size-1 && Y==size-1){
					if (model.isCaptured(X-1,Y) == false && model.getColor(X-1,Y) == color){
								model.capture(X-1,Y);
								s.push(model.get(X-1,Y));
					}
					if (model.isCaptured(X,Y-1) == false && model.getColor(X,Y-1) == color){
								model.capture(X,Y-1);
								s.push(model.get(X,Y-1));
					}
					
					if (model.getTorus()){
						if(model.isCaptured(0,Y)==false && model.getColor(0,Y) == color){
							model.capture(0,Y);
							s.push(model.get(0,Y));
						}
						if(model.isCaptured(X,0)==false && model.getColor(X,0) == color){
							model.capture(X,0);
							s.push(model.get(X,0));
						}
						
						if(model.getDiagonal()){ 
							if(model.isCaptured(X-1,0)==false && model.getColor(X-1,0) == color){
								model.capture(X-1,0);
								s.push(model.get(X-1,0));
							}
							if(model.isCaptured(0,Y-1)==false && model.getColor(0,Y-1) == color){
								model.capture(0,Y-1);
								s.push(model.get(0,Y-1));
							}
						}
					}
					
					if(model.getDiagonal()){
						if(model.isCaptured(X-1,Y-1)==false && model.getColor(X-1,Y-1) == color){
							model.capture(X-1,Y-1);
							s.push(model.get(X-1,Y-1));
						}
					}
				}
			}
		

    }
	
}
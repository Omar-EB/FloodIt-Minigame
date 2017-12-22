import java.io.Serializable ;

public class GenericLinkedStack<D> implements Stack<D>, Serializable{
	
	private static class Elem<V> implements Serializable{
		private V object;
		private Elem<V> next;
		public Elem(V object,Elem<V> next){
			this.object=object;
			this.next=next;
		}
	}
	
	private Elem<D> top;
	
	public boolean isEmpty(){
		return (top==null);
	}

	public D peek(){
		if (isEmpty()){
			throw new EmptyStackException();
		} 
		return top.object;
	}

	public D pop(){
		if (isEmpty()){
			throw new EmptyStackException();
		}
		D value = top.object;
		top=top.next;
		return value;
	}
	
	public void push(D value){
		if (value==null){
			throw new NullPointerException();	
		}
		top= new Elem<D>(value,top);
	}
	
}
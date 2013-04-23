package findthebomb;
public  class NodeStack implements MyStack{
private Node top;
private int size;
public NodeStack(){
 top=null;
 size=0;
}
public int size(){
 return size;
}
public boolean isEmpty(){
 if(top==null){
  return true;
 }
 return false;
}
public void push(Object o){
 Node v = new Node(o,top);
 top=v;
 size++;
}
public Object top(){
 if(isEmpty()){
  throw new RuntimeException("Empty Stack");
 }
 else{
  return top.getElement();
 }
}
public Object pop (){
 if(isEmpty()){
  throw new RuntimeException("Empty Stack");
 }else{
  Object temp=top.getElement();
  top=top.getNext();
  size--;
  return temp;
 }
}
}

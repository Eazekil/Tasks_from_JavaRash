package task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;


/* 
Построй дерево(1)
*/

public  class CustomTree extends AbstractList<String> implements Cloneable, Serializable{
    //private AbstractList<String> list;
    private int index;
    private String element;
    Entry<String> root;
    private int count=0;
    private String res=null;

    public void set(int index) {
        throw new UnsupportedOperationException();
    }
    public String set(int index, String element){
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(String element) {
        Entry node = new Entry(element);
        if(root==null){
            root = node;
            count++;
        }else {
            addElement(root,element);
        }
        return true;


        /*if(root==null){
            root = node;
            return true;
        }else{
            Entry current = root;
            if(current.isAvailableToAddChildren()){
                if(current.leftChild==null){
                    current.leftChild = node;
                    current.availableToAddLeftChildren=false;
                    return true;
                }else{
                    current.rightChild=node;
                    current.availableToAddRightChildren=false;
                    return true;
                }
            }else{
                if(current.leftChild.isAvailableToAddChildren()){
                    if(current.leftChild.leftChild==null){
                        current.leftChild.leftChild=node;
                        current.leftChild.availableToAddLeftChildren=false;
                        return true;
                    }else{
                        current.rightChild.rightChild=node;
                        current.rightChild.availableToAddRightChildren=false;
                        return true;
                    }
                }else{
                    if(current.rightChi
                    ld.leftChild==null){
                        current.leftChild.leftChild=node;
                        current.leftChild.availableToAddLeftChildren=false;
                        return true;
                    }else{
                        current.rightChild.rightChild=node;
                        current.rightChild.availableToAddRightChildren=false;
                        return true;
                    }
                }
            }
        }*/
    }

    public void addElement(Entry current, String element){
        if(current!=null){
            if(element.hashCode()<current.elementName.hashCode()){
                addElement(current.leftChild,element);
            }else{
                addElement(current.rightChild,element);
            }
        }else{
            current=new Entry(element);
            count++;
        }
    }

    /*public boolean isNull(Entry current){

    }*/

    public void add(int index, String element){throw new UnsupportedOperationException();}
    public String remove(int index){
        throw new UnsupportedOperationException();
    }
    public List<String> subList(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }
    protected void removeRange(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }
    public boolean addAll(int index, Collection<? extends String> c){
        throw new UnsupportedOperationException();
    }

    public CustomTree() {
        this.root=new Entry<String>("");
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
        //return list.get(index);
    }

    @Override
    public int size() {
        //throw new UnsupportedOperationException();
        return count;
    }

    public String getParent(String s){
        if(s.hashCode()==root.elementName.hashCode()){
            return res;
        }else{
            parent(s,root);
        }
        return res;
    }
    public String parent(String s,Entry current){
        if(s.equals(current.leftChild) | s.equals(current.rightChild)){
            res= current.elementName;
            return res;
        }else{
            if(s.hashCode()<current.elementName.hashCode()){
                parent(s,current.leftChild);
            }else{
                parent(s,current.rightChild);
            }
            return res;
        }
    }

    static class Entry<T> implements Serializable{
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren=true;
            availableToAddRightChildren=true;
        }
        public boolean isAvailableToAddChildren(){
            return availableToAddLeftChildren | availableToAddRightChildren;
        }
    }
}

package task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;


/* 
Построй дерево(1)
*/

public  class CustomTree extends AbstractList<String> implements Cloneable, Serializable{
    Entry<String> root;
    private int count=0;

    public void set(int index) {
        throw new UnsupportedOperationException();
    }
    public String set(int index, String element){
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(String element) {
        if(root.elementName==null) {
            root = new Entry(element);
            count++;
            //System.out.println("root element is "+element);
            //root.parent=null;
        }else{
            ArrayDeque<Entry> elementQueue = new ArrayDeque<Entry>();
            elementQueue.offer(root);
            int lastCount=count;
            while (lastCount==count){
                Entry current=elementQueue.poll();
                if(current.isAvailableToAddChildren()){
                    count++;
                    if(current.leftChild==null){
                        current.leftChild=new Entry(element);
                        current.availableToAddLeftChildren=false;
                        current.leftChild.parent=current;
                        //System.out.println("added new left element: "+element);
                        //System.out.println(current.elementName);
                        //System.out.println(current.leftChild.parent.elementName);
                    }else{
                        current.rightChild=new Entry(element);
                        current.availableToAddRightChildren=false;
                        current.rightChild.parent=current;
                        //System.out.println("added new right element: "+element);
                    }
                }else{
                    elementQueue.offer(current.leftChild);
                    elementQueue.offer(current.rightChild);
                }
            }
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(o instanceof String){
            String s=String.valueOf(o);
            ArrayDeque<Entry> elementQueue = new ArrayDeque<Entry>();
            elementQueue.offer(root);
            while (!elementQueue.isEmpty()){
                Entry current=elementQueue.poll();
                if(current.leftChild!=null){
                    if(current.leftChild.elementName.equals(s)) {
                        count=count-countForDelete(current.leftChild);
                        current.leftChild = null;
                        current.availableToAddLeftChildren = true;
                        break;
                    }else {
                        elementQueue.offer(current.leftChild);
                    }
                }
                if(current.rightChild!=null) {
                    if(current.rightChild.elementName.equals(s)) {
                        count=count-countForDelete(current.rightChild);
                        current.rightChild = null;
                        current.availableToAddRightChildren = true;
                        break;
                    }else {
                        elementQueue.offer(current.rightChild);
                    }
                }
            }
        }else{
            throw new UnsupportedOperationException();
        }
        return true;
    }

    public int countForDelete(Entry element){
        int res=1;
        ArrayDeque<Entry> elementQueue = new ArrayDeque<Entry>();
        elementQueue.offer(element);
        while (!elementQueue.isEmpty()){
            Entry current=elementQueue.poll();
            if(current.leftChild!=null){
                res++;
                elementQueue.offer(current.leftChild);
            }
            if(current.rightChild!=null){
                res++;
                elementQueue.offer(current.rightChild);
            }
        }
        return res;
    }

    public void add(int index, String element){throw new UnsupportedOperationException();}
    public String remove(int index){throw new UnsupportedOperationException();}
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
        this.root=new Entry<String>("0");
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
        String parent=null;
        ArrayDeque<Entry> elementQueue = new ArrayDeque<Entry>();
        elementQueue.offer(root);
        while (!elementQueue.isEmpty()){
            //System.out.println("its ok");
            Entry current=elementQueue.poll();
            if(current.elementName.equals(s)){
                //System.out.println("first");
               parent=current.parent.elementName;
            }else{
                //System.out.println("second");
                if(!current.isAvailableToAddChildren()){
                    elementQueue.offer(current.leftChild);
                    elementQueue.offer(current.rightChild);
                }
            }
        }
        return parent;
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

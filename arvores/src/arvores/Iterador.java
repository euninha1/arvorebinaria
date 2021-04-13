package arvores;

import java.util.Iterator;

public class Iterador<T> implements Iterator{

    private node atual;

    public Iterador(node atual) {
        this.atual = atual;
    }
    
    @Override
    public boolean hasNext() {
        return atual!=null;
    }


    @Override
    public Object next() {
    //T elemento = (T) atual.getContent();
    //atual = atual.getProximo();
    return null;
    }
    
    public Object previous(){
        T elemento = (T) atual.getParent();
        atual = atual.getParent();
        return elemento;
    }
    
    public int countingPrevious(node start){
        
        int count = -1; 
        
        this.setAtual(start);
        
        while (true) {            
           
            try { // conte quantas vezes consegue chamar o parente
                atual = atual.getParent();
                count++;
            }
            catch (Exception e) { // não conseguindo chamar o parente (chegou na raiz), mostrar a contagem
                return count;
            }

        } 
    }

    public node getAtual() {
        return atual;
    }

    public void setAtual(node atual) {
        this.atual = atual;
    }
    
    
}

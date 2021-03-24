package arvores;

import java.util.Iterator;

public class Iterador<T> implements Iterator{

    private no atual;

    public Iterador(no atual) {
        this.atual = atual;
    }
    
    @Override
    public boolean hasNext() {
        return atual!=null;
    }


    @Override
    public Object next() {
    T elemento = (T) atual.getContent();
    //atual = atual.getProximo();
    return elemento;
    }
    
    public Object previous(){
        T elemento = (T) atual.getParent();
        atual = atual.getParent();
        return elemento;
    }
    
    public int countingPrevious(no start){ // alerta de gambiarra
        
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

    public no getAtual() {
        return atual;
    }

    public void setAtual(no atual) {
        this.atual = atual;
    }
    
    
}

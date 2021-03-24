
package arvores;

public class Arvores {
    

    public static void main(String[] args) {
        
        ArvoreBinaria arvore = new ArvoreBinaria();
        
        arvore.createRoot();
        arvore.getRoot().setName("A");
        arvore.getRoot().addChild();
            arvore.getRoot().getLeft().setName("B");
        arvore.getRoot().addChild();
            arvore.getRoot().getRight().setName("C");
        arvore.getRoot().getLeft().addChild();
            arvore.getRoot().getLeft().getLeft().setName("D");
        arvore.getRoot().getLeft().addChild();
            arvore.getRoot().getLeft().getRight().setName("E");
        arvore.getRoot().getRight().addChild();
            arvore.getRoot().getRight().getLeft().setName("F");
        arvore.getRoot().getRight().addChild();
            arvore.getRoot().getRight().getRight().setName("G");
        
        
        arvore.doMenu();

    
            
    }
    
}


package arvores;

import java.util.ArrayList;

public class Arvores {
    


    public static void main(String[] args) {
        
        BinaryTree arvore = new BinaryTree();
        ArrayList al = new ArrayList();
        
        
        /*arvore.createRoot();
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
        arvore.getRoot().getRight().getRight().setName("G");*/
        
        
        
        arvore.createRoot();
        arvore.getRoot().setValue(5);
        

        arvore.addRecursive(arvore.getRoot(), 3);
        arvore.addRecursive(arvore.getRoot(), 2);
        arvore.addRecursive(arvore.getRoot(), 4);
        arvore.addRecursive(arvore.getRoot(), 7);
        arvore.addRecursive(arvore.getRoot(), 6);
        arvore.addRecursive(arvore.getRoot(), 8);

        /*
        arvore.addRecursive(arvore.getRoot(), 8);
        arvore.addRecursive(arvore.getRoot(), 7);
        arvore.addRecursive(arvore.getRoot(), 6);
        arvore.addRecursive(arvore.getRoot(), 4);
        arvore.addRecursive(arvore.getRoot(), 3);
        arvore.addRecursive(arvore.getRoot(), 2);
        */
        //arvore.importToArray();
        arvore.doMenu();

        
            
    }
    
}

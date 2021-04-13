
package arvores;

public class node {
    
    private String name;
    private int value;
    public int nivel;
    private node parent;
    private node left;
    private node right;
    
    public node(String name){
        this.name = name;
        this.value = 0;
        this.nivel = 0;
        this.parent = null;
        this.left = null;
        this.right = null;
    }
    
    public node(){
        this.name = "nameless";
        this.value = 0;
        this.nivel = 0;
        this.parent = null;
        this.left = null;
        this.right = null;
    }
    
    public node(int value){
        this.name = "nameless";
        this.value = value;
        this.nivel = 0;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    public node getParent() {
        return parent;
    }

    public void setParent(node parent) {
        this.parent = parent;
    }

    public node getLeft() {
        return left;
    }

    public void setLeft(node left) {
        this.left = left;
    }

    public node getRight() {
        return right;
    }

    public void setRight(node right) {
        this.right = right;
    }
    
    public int doHeight() // calcular altura
    {
        if((left == null) && (right == null)) // não há filhos = 0.
            return 0;

        int auxLeft = 0, auxRight = 0; // contadores
        
        if(left != null)
            auxLeft = left.doHeight(); 

        if(right != null)
            auxRight = right.doHeight();
        
        return 1 + Math.max(auxLeft, auxRight);
        // máximo da contagem tanto da esquerda quanto da direita, achando assim o caminho mais distante.
        
    }
    
    public boolean isEndpoint() { // Retorna se é nó folha ou não.
        return (this.getLeft() == null && this.getRight() == null);
    }
    
    public String StringEndpoint() { // Retorna se é nó folha ou não.
       if(this.getLeft() == null && this.getRight() == null){
           return "Yes";
       }
       else{
           return "No";
       }
    }
    
    public int grau() { // Retorna grau
        
        int count = 0;
        
        if (this.getLeft()!= null) {
            count++;
        }
        if (this.getRight() != null){
            count++;
        }
        
        return count;
    }
    
    public int getDepth(){ // profundidade de um nó
        Iterador it = new Iterador(this);
        return it.countingPrevious(this); // conte quantas vezes consegue chamar o parente.
    }
    
    public node getChildIndex(String side){ //retorna o filho da esquerda ou direita (l)eft / (r)ight
        
        switch(side){
            
            case "l":
                return this.getLeft();
            case "r":
                return this.getRight();
            default:
                System.err.println("[WARNING] Default value on method getChild!");
                return null;
                
        }
    }

    public void printChildren() { //imprime os filhos e suas informações, se possível
        try {
            System.out.println("l: " + this.getLeft().getName() + " | Value: " + this.getLeft().getValue() + " | Object: " + this.getLeft());
        } catch (Exception e) {
            System.out.println("l: Empty");
        }
        try {
            System.out.println("r: " + this.getRight().getName() + " | Value: " + this.getRight().getValue() + " | Object: " + this.getRight());
        } catch (Exception e) {
            System.out.println("r: Empty");
        }    
    }

    public void addChild() { //adiciona novo filho, da esquerda para a direita.
        
        node newNode = new node();
        
        if (this.left == null){
            this.setLeft(newNode);
            this.getLeft().setParent(this);
            this.getLeft().nivel = this.nivel + 1;
            
            System.out.println("Left space is open. Added to left");
        }

        else if(this.right == null){
            this.setRight(newNode);
            this.getRight().setParent(this);
            this.getRight().nivel = this.nivel + 1;
            
            System.out.println("Right space is open. Added to Right");
        }
        
        else{
            System.err.println("No available space");
        }
    }
}

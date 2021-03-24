
package arvores;

public class no {
    
    private String name;
    private Object content;
    public int nivel;
    private no parent;
    private no left;
    private no right;
    
    public no(String name){
        this.name = name;
        this.content = null;
        this.nivel = 0;
        this.parent = null;
        this.left = null;
        this.right = null;
    }
    
    public no(){
        this.name = "nameless";
        this.content = null;
        this.nivel = 0;
        this.parent = null;
        this.left = null;
        this.right = null;
    }
    
    public no(Object content){
        this.name = "nameless";
        this.content = content;
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
    
    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public no getParent() {
        return parent;
    }

    public void setParent(no parent) {
        this.parent = parent;
    }

    public no getLeft() {
        return left;
    }

    public void setLeft(no left) {
        this.left = left;
    }

    public no getRight() {
        return right;
    }

    public void setRight(no right) {
        this.right = right;
    }
    
    public int doHeight() // calcular altura
    {
        if((left == null) && (right == null))
            return 0;

        int auxLeft = 0, auxRight = 0;

        if(left != null)
            auxLeft = left.doHeight();

        if(right != null)
            auxRight = right.doHeight();

        return 1 + Math.max(auxLeft, auxRight);
    }
    
    public boolean isEndpoint() { // Retorna se é nó folha ou não.
        return (this.getLeft() == null && this.getRight() == null);
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
        return it.countingPrevious(this);
    }
    
    /* Não utilizado / deprecado
    public int height(){
        Iterador it = new Iterador(this);
        return it.countingPrevious(this);
    }
    */
    
    public no getChildIndex(String side){ //retorna o filho da esquerda ou direita (l)eft / (r)ight
        
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
            System.out.println("l: " + this.getLeft().getName() + " Object: " + this.getLeft());
        } catch (Exception e) {
            System.out.println("l: Empty");
        }
        try {
            System.out.println("r: " + this.getRight().getName() + " Object: " + this.getRight());
        } catch (Exception e) {
            System.out.println("r: Empty");
        }    
    }

    public void addChild() { //adiciona novo filho, da esquerda para a direita.
        
        no newNode = new no();
        
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

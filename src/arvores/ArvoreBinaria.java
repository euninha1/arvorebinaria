package arvores;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBinaria<T> {
    
    private no root;
    public String userinput;
    private no targetedNode;
    
    public ArvoreBinaria(){
        
        this.root = null;
        this.userinput = "";
        this.targetedNode = null;
        
    }
    
    public void doMenu(){ // cria menu para interação com a árvore
        Scanner readinput = new Scanner(System.in); // leitor de entrada de usuário
        System.out.println("Do /h for help");
        while (!"stop".equals(this.userinput)) {            
            this.update(); // imprime informações do nó selecionado após todo comando completado
            System.out.print(">> ");
            this.userinput = readinput.nextLine(); // aguarda o usuário
            switch(this.userinput){ // checa a entrada do usuário e corresponde com os comandos
                
                case "/h":
                    System.out.println("commands here");
                    break;
                    
                case "start": // inicia a árvore, criando uma raiz
                    this.createRoot();
                    break;
                    
                case "stop": // para o programa
                    System.out.println("Stopping.");
                    break;
                  
                case "info": // informação do nó selecionado
                    System.out.println("Grau: " + this.targetedNode.grau());
                    System.out.println("Profundidade: " + this.targetedNode.getDepth());
                    System.out.println("Nível: " + this.targetedNode.nivel);
                    System.out.println("Altura: " + (this.targetedNode.doHeight()));
                    break;
                
                case "back": // seleciona o parente.
                    if (this.targetedNode.getParent() == null) {
                        System.err.println("[WARNING] Failed. No parent");
                    }
                    else{
                        this.targetedNode = this.targetedNode.getParent();
                        System.out.println("Pointing to parent");
                    }    
                    break;
                    
                case "new": // adiciona filho, da esquerda para a direita
                    if ((this.targetedNode.getLeft() == null) | (this.targetedNode.getRight() == null)) {
                        this.targetedNode.addChild();
                    }
                    else{
                        System.err.println("[Warning] No available space");
                    }
                    break;
                
                case "rename": // renomeia o nó.
                    System.out.print(">>rename: ");
                    this.userinput = readinput.nextLine();
                    this.targetedNode.setName(userinput);
                    System.out.println("Renamed.");
                    break;
                
                case "insert": // insere objeto no conteúdo do nó.
                    //TODO
                    break;
                
                case "select": // seleciona esquerda ou direita.
                    System.out.println("Select child: (l)eft,(r)ight");
                    this.userinput = readinput.nextLine();
                    System.out.print(">>select: ");
                    switch(this.userinput){
                        case "l":
                            this.targetedNode = this.targetedNode.getLeft();
                            break;    
                        case "r":
                            this.targetedNode = this.targetedNode.getRight();
                            break;
                        default:
                            System.err.println("Unknown case");
                            break;
                    }
                    break;
                    
                case "treeinfo": // imprimir informações da árvore
                    this.calculateNodes(root);
                    System.out.println("Pre Order NLR:");
                    this.traversePreOrder(root);
                    System.out.println("\nPost Order LRN:");
                    this.traversePostOrder(root);
                    System.out.println("\nPost Order LNR:");
                    this.traverseInOrder(root);
                    break;
                    
                default:
                    System.err.println("Syntax Error or unknown command");
                    break; 
            }
        }
    }
    
    public void update(){
        try {
            System.out.println("\n######\nName: " + this.targetedNode.getName());
            System.out.println("Object: " + this.targetedNode);
            System.out.println("Content: " + this.targetedNode.getContent());
            System.out.println("Parent: " + this.targetedNode.getParent());
            this.targetedNode.printChildren();
        } catch (Exception e) {
            System.err.println("[INFO] No pointer.");
        }
        
    }

    public no getRoot() {
        return root;
    }
 
    /*
    public void insertRoot(T element){
        node newNode = new node(element);
        this.root = newNode;
        this.numElements++; //NECESSITA CORREÇÃO
        this.targetedNode = this.root;
        System.out.println("Root Created with value. Pointing to root.");
    }
    */
    
    public void createRoot(){ 
        no newNode = new no();
        if (this.root == null) {
            this.root = newNode;
            System.out.println("Root Created. Pointing to root.");
        }
        else {
            this.root = newNode;
            System.out.println("Root REPLACED. Pointing to root.");
        }
        this.root.setName("ROOT");
        this.targetedNode = this.root;
        
    }
    
    public void calculateNodes(no root) { //calcular número de nós
        Queue<no> queue = new LinkedList<>(); // cria uma lista para saber quais os próximos nós a serem vistiados
        queue.add(root); //começa pela raiz.
        int num = 0; //número de nós contados
        while(!queue.isEmpty()) //mantem o código rodando até não sobrarem nós para percorrer
        {
            no temp = queue.remove(); //remover nós da lista, pois este já foi percorrido
            num++; //adicionar à contagem
            if(temp.getLeft() !=null) // ir pra esquerda se existir
                queue.add(temp.getLeft()); // repete o processo com o filho esquerdo
            if(temp.getRight() !=null) // ir pra direita se existir
                queue.add(temp.getRight()); // repete o processo com o filho direito
        }
        System.out.println("Total Size (Recursive function): " + num);
    }
    
    // ####################
    // METODOS DE NAVEGAÇÃO
    // ####################
    
    public void traversePreOrder(no node) { // NRL
    if (node != null) {
        System.out.print(" " + node.getName());
        traversePreOrder(node.getLeft());
        traversePreOrder(node.getRight());
        }
    }
    
    public void traverseInOrder(no node) { // LNR
    if (node != null) {
        traverseInOrder(node.getLeft());
        System.out.print(" " + node.getName());
        traverseInOrder(node.getRight());
        }
    }
    
    public void traversePostOrder(no node) { // LRN
    if (node != null) {
        traversePostOrder(node.getLeft());
        traversePostOrder(node.getRight());
        System.out.print(" " + node.getName());
        }
    }
    
}

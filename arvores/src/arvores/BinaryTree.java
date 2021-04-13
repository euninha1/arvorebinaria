package arvores;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue; //implementa linkedlist
//import java.util.Arrays;
import java.util.ArrayList;

public class BinaryTree<T> {
    
    private node root;
    private node targetedNode;
    public String userinput;
    public int userinputint;
    
    public BinaryTree(){
        
        this.root = null;
        this.userinput = "";
        this.userinputint = 0;
        this.targetedNode = null;
        
    }
    
    public void doMenu(){ // cria menu para interação com a árvore
        Scanner readinput = new Scanner(System.in); // leitor de entrada de usuário
        System.out.println("Do 'help' for list of commands");
        while (!"stop".equals(this.userinput)) {            
            this.update(); // imprime informações do nó selecionado após todo comando completado
            System.out.print(">> ");
            this.userinput = readinput.nextLine(); // aguarda o usuário
            switch(this.userinput){ // checa a entrada do usuário e corresponde com os comandos
                
                case "help":
                    System.out.println("\nAvailable commands:"
                            + "\n'start': create a new empty root"
                            + "\n'stop': quits the program"
                            + "\n'root': points to the root"
                            + "\n'info': prints information of current node"
                            + "\n'back': points back to parent"
                            + "\n'new': add new child to nearest empty pointer"
                            + "\n'rename': rename current node"
                            + "\n'insert': recursively inserts new node with value"
                            + "\n'select': select left or right child"
                            + "\n'treeinfo': print information about the tree"
                            + "\n'import': print value of the nodes in an array list"
                            + "\n'export': transform current tree to an unbalanced binary search tree.");
                    break;
                    
                case "start": // inicia a árvore, criando uma raiz
                    this.createRoot();
                    break;
                    
                case "stop": // para o programa
                    System.out.println("Stopping.");
                    break;
                    
                case "root":
                    System.out.println("Pointing to root.");
                    this.targetedNode = this.root;  // dará erro se não existir a raiz.
                  
                case "info": // informação do nó selecionado
                    System.out.println("Grau: " + this.targetedNode.grau() + ", Endpoint?: " +this.targetedNode.StringEndpoint());
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
                    
                case "remove": //remover certo valor
                    System.out.print(">>value to be removed: ");
                    this.userinputint = readinput.nextInt();
                    this.remove(root, userinputint);
                    break;
                    
                case "insert": // insere objeto no conteúdo do nó.
                        System.out.print(">>value: ");
                        this.userinputint = readinput.nextInt();
                        this.addRecursive(this.root, userinputint);
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
                
                case "invert": //inverter subarvores
                    this.invertTrees(root);
                    break;
                
                case "import": // para array
                    this.importToArray();
                    break;
                
                case "export": // binaria para binaria de busca    
                    this.convert();
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
            System.out.println("Value: " + this.targetedNode.getValue());
            System.out.println("Parent: " + this.targetedNode.getParent());
            this.targetedNode.printChildren();
        } catch (Exception e) {
            System.out.println("[INFO] No pointer.");
        }
    }

    public node getRoot() {
        return root;
    }
    
    public void createRoot(){ //criar raiz
        node newNode = new node();
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
    
    public int calculateNodes(node root) { //calcular número de nós
        Queue<node> queue = new LinkedList<>(); // cria uma lista para saber quais os próximos nós a serem visitados
        queue.add(root); //começa pela raiz.
        int num = 0; //número de nós contados
        while(!queue.isEmpty()) //mantem o código rodando até não sobrarem nós para percorrer
        {
            node temp = queue.remove(); //remover nós da lista, pois este já foi percorrido, retorna o nó que foi removido para servir como eixo.
            num++; //adicionar à contagem
            if(temp.getLeft() !=null) // ir pra esquerda se existir
                queue.add(temp.getLeft()); // repete o processo com o filho esquerdo
            if(temp.getRight() !=null) // ir pra direita se existir
                queue.add(temp.getRight()); // repete o processo com o filho direito
        }
        System.out.println("Total Size (Recursive function): " + num);
        return num;
    }
    
    
    // ####################
    // METODOS DE NAVEGAÇÃO
    // ####################
    
    public void traversePreOrder(node node) { // NRL
        if (node != null) {
            System.out.print(" " + node.getValue());
            traversePreOrder(node.getLeft());
            traversePreOrder(node.getRight());
        }
    }
    
    public void traverseInOrder(node node) { // LNR
        if (node != null) {
            traverseInOrder(node.getLeft());
            System.out.print(" " + node.getValue());
            traverseInOrder(node.getRight());
        }
    }
    
    public void traversePostOrder(node node) { // LRN
        if (node != null) {
            traversePostOrder(node.getLeft());
            traversePostOrder(node.getRight());
            System.out.print(" " + node.getValue());
        }
    }
    
    
    public void WritePostOrder(node node, ArrayList output) { // NLR - Escrever valor dos nós em um array.
        if (node != null) {
            output.add(node.getValue());
            WritePostOrder(node.getLeft(), output);
            
            WritePostOrder(node.getRight(), output);
        }
    }
    
    // ##################################
    // METODOS DE ÁRVORE BINÁRIA DE BUSCA
    // ##################################
    
    public node addRecursive(node current, int integer) { // adicionar recursivamente
        
        if (current == null) { // achou espaço vazio? adicionar.
            return new node(integer);
        }

        if (integer < current.getValue()) { // navegar até à esquerda, em busca de espaço vazio.
            current.setLeft(addRecursive(current.getLeft(), integer));
        }

        else if (integer > current.getValue()) { // navegar até à direita, em busca de espaço vazio.
            current.setRight(addRecursive(current.getRight(), integer));
        }

        else {
            // caso valor já exista
            return current;
        }

        return current;
        
    }
    
    
    private node biggestValue(node current) { //pegar o maior valor (o mais a direita). ajuda na remoção do terceiro caso
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current;
    }
    
    
    public node remove(node current, int value) {
        // chave não encontrada na árvore
        if (current == null) {
            return current;
        }

        // valor menor, procurar na sub-árvore esquerda
        if (value < current.getValue()) {
            current.setLeft(remove(current.getLeft(), value));
        } else if (value > current.getValue()) {
            // valor maior, procurar na sub-árvore direita
            current.setRight(remove(current.getRight(), value));
        } else { // valor encontrado
            // caso 1: nó é uma folha (não tem filhos)
            if (current.getLeft() == null && current.getRight() == null) {
                // remove-o (seta a "raiz" deste nó para null)
                return null;
            } else if (current.getLeft() != null && current.getRight() != null) {
                // caso 3: nó tem 2 filhos
                // encontrar o maior dos filhos que antecede o nó
                node maiorAntecessor = biggestValue(current.getLeft());

                // copia o valor do antecessor para este nó
                current.setValue(maiorAntecessor.getValue());

                // remove o antecessor recursivamente
                current.setLeft(remove(current.getLeft(), maiorAntecessor.getValue()));
            } else {
                // caso 2: nó só tem um filho
                node child = (current.getLeft() != null) ? current.getLeft() : current.getRight();
                current = child;
            }
        }

        return current;
    }
    
    
    public void invertTrees(node current){ //inverter as subárvores
        
        if(current != null){ // nó deve ser não nulo, ou dará NullPointException.
            
            node aux = current.getLeft(); //nó auxiliar para evitar duplicidade

            current.setLeft(current.getRight()); // trocar esquerda pela direita
            current.setRight(aux); //trocar direita pela esquerda (armazenada)
            
            invertTrees(current.getRight()); // vá para o nó direito
            invertTrees(current.getLeft()); // vá para o nó esquerdo
            
        }
    }
    
    public ArrayList importToArray() { // LRN - nós da árvore para arraylist
        
        ArrayList outputarray = new ArrayList();
        
        this.WritePostOrder(root, outputarray);
        
        System.out.println("Import: " + outputarray.toString());
        return outputarray;
        

    }
    
    public void arrayToBST(ArrayList importArray){ // utiliza o arraylist para criar uma BST DESBALANCEADA.
        
        this.createRoot();
        
        for (int k = 0; k < importArray.size(); k++) {
            this.addRecursive(this.getRoot(), Integer.valueOf(importArray.get(k).toString()));
        }
     
    }

    public void convert(){
        this.arrayToBST(this.importToArray());
    }
    
}

public class ArvoreBinaria {

    private No raiz;
    private int numComparacao;
    private int numElementos;

    private static class No {
        Item reg;
        No esq, dir;
    }

    public ArvoreBinaria(Item regRaiz) { // Construtor para inicializar a raiz, inicialmente vazia.
        raiz = new No();
        this.raiz.reg = regRaiz;
        this.raiz.esq = this.raiz.dir = null;

        numComparacao = 0; // Comeca no -1 para evitar a repeticao de codigo dentro do metodo recursivo
        numElementos = 1; //Comeca com 1 por causa da raiz que ja conta como um elemento (No)
    }

    // Função recursiva para pesquisar um elemento na arvore
    public Item pesquisa(Item reg, No p) {

        numComparacao++;

        if (p == null) { // Verifica se é um nó existente

            return null;

        } else if (reg.compara(p.reg) < 0) { // Verifica o valor na subarvoré esquerda

            return pesquisa(reg, p.esq);

        } else if (reg.compara(p.reg) > 0) { // Verifica o valor na subarvoré direita

            return pesquisa(reg, p.dir);

        } else { // Caso tenha achado o valor solicitado, retorna este.
            return p.reg;
        }
    }

    // Função recursiva para inserir um elemento na arvore
    public No insere(Item reg, No p) {

        if (p == null) { // Verifica se o nó atual é nulo (inexistente), caso seja, ele será o ponto de
                         // inserção
            p = new No();

            p.reg = reg;
            p.esq = p.dir = null;
            numElementos++;

        } else if (reg.compara(p.reg) < 0) {

            p.esq = insere(reg, p.esq);

        } else if (reg.compara(p.reg) > 0) {

            p.dir = insere(reg, p.dir);

        } else { // Caso não seja encontrado nenhum nó nulo, significa que o elemento a ser
                 // inserido ja existe!

            System.out.println(Cores.ANSI_RED + "Erro: Registro ja existente" + Cores.ANSI_RESET);
        }

        return p;
    }

    public No getRaiz() {
        return this.raiz;
    }

    public int getNumComparacao() {
        return numComparacao;
    }

    public void setNumComparacao(int numComparacao) {
        this.numComparacao = numComparacao;
    }

    public int getNumElementos() {
        return numElementos;
    }

}

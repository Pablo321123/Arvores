public class ArvoreB {

    private static class Pagina {
        private int n; // Numero de itens na página;
        private Item r[]; // Vetor contendo os REGISTROS da pagina de tamanho 2m = mm
        private Pagina p[]; // Vetor de objetos para as paginas a um nivel abaixo de tamanho 2m+1

        public Pagina(int mm) { // mm = Quantidade maxima de descendentes
            this.n = 0;
            this.r = new Item[mm];
            this.p = new Pagina[mm + 1];
        }
    }

    private Pagina raiz; // Raiz da arvore
    private int m, mm; // m = Quantidade minima de descendentes
    private int numComparacao;
    private int numElementos;

    public ArvoreB(int m) {
        numComparacao = -1;
        numElementos = 0;
        raiz = null;
        this.m = m;
        mm = 2 * m;
    }

    public Item pesquisa(Item reg) {
        return pesquisa(reg, raiz);
    }

    public Item pesquisa(Item reg, Pagina ap) {
        numComparacao++;
        if (ap == null) {
            return null; // Registro inexistente
        } else {
            int i = 0;

            while ((i < (ap.n - 1)) && (reg.compara(ap.r[i]) > 0)) { // Verifica se a pos atual eh valida e se o
                                                                     // registro;
                // procutado eh maior que o atual;
                i++; // Pesquisa elementos na pagina;
            }

            if (reg.compara(ap.r[i]) == 0) { // Se for igual, retorna o elemento;
                return ap.r[i];

            } else if (reg.compara(ap.r[i]) < 0) { // Se for menor, chama recursivamente a busca na sub-arvore esquerda;
                return pesquisa(reg, ap.p[i]);

            } else {
                return pesquisa(reg, ap.p[i + 1]); // caso contrário, chama recursivamente a busca na sub-arvore
                                                   // direita;
            }
        }
    }

    private void insereNaPagina(Pagina ap, Item reg, Pagina apDir) {
        int k = ap.n - 1; // Posicao valida mais a direita

        while ((k >= 0) && (reg.compara(ap.r[k]) < 0)) {
            // Desloca-se os elementos de r e p para direita ate que a posicao correta de
            // insercao seja encontrada;
            ap.r[k + 1] = ap.r[k];
            ap.p[k + 2] = ap.p[k + 1];
            k--;
        }

        // reg e apDir sao inseridos na pagina corrente;
        ap.r[k + 1] = reg;
        ap.p[k + 2] = apDir;
        ap.n++;
    }

    public void insere(Item reg) {
        Item regRetorno[] = new Item[1]; // Indica o item que sera inserido
        boolean cresceu[] = new boolean[1]; // Informa qe um registro "subiu" proveniente de uma insercao
        Pagina apRetorno = this.insere(reg, this.raiz, regRetorno, cresceu);

        if (cresceu[0]) { // Verifica, depois da insercao do elemento REG a partir de this.raiz, eh
                          // verificado se houve crescimento ou nao da Raiz.
            Pagina apTemp = new Pagina(this.mm); // Caso positivo, uma nova Pagina eh criada e atribuida a raiz.
            apTemp.r[0] = regRetorno[0];
            apTemp.p[0] = this.raiz;
            apTemp.p[1] = apRetorno;
            this.raiz = apTemp;
            this.raiz.n++;
        } else {
            this.raiz = apRetorno;
        }
    }

    private Pagina insere(Item reg, Pagina ap, Item[] regRetorno, boolean[] cresceu) {
        Pagina apRetorno = null;

        if (ap == null) { // Caso ap se nulo, a pagina em que o registro deve ser inserido foi encotrado
            cresceu[0] = true;
            regRetorno[0] = reg;
            numElementos++;
        } else {
            int i = 0;

            while ((i < ap.n - 1) && (reg.compara(ap.r[i]) > 0)) { // a partir da esquerda, i é posicionado no primeiro
                i++; // elemento que seja maior ou igual ao registro a ser // inserido – como em
                     // pesquisa
            }

            if (reg.compara(ap.r[i]) == 0) { // Se o registro a ser inserido for encotrado
                System.out.println("Erro: Registro ja existente");
                cresceu[0] = false;

            } else {
                /*
                 * verifica se a próxima página a ser pesquisa deve ser a da direita ou a da
                 * esquerda. Essa verificação é realizada pois i pode estar posicionado na
                 * última posição de r[]; invoca insere novamente;
                 */
                if (reg.compara(ap.r[i]) > 0) {
                    i++;
                }

                apRetorno = insere(reg, ap.p[i], regRetorno, cresceu);
                if (cresceu[0]) {
                    if (ap.n < this.mm) { // Verifica se a pagina tem espaço após a chamada recursiva regRetorno[0] =
                                          // reg;
                        this.insereNaPagina(ap, regRetorno[0], apRetorno);
                        cresceu[0] = false;
                        apRetorno = ap;
                    } else { // Overflow: Página tem que ser dividida
                        Pagina apTemp = new Pagina(this.mm); // É criada uma nova Pagina apTemp;
                        apTemp.p[0] = null;

                        if (i <= this.m) { // (b) Verifica se o novo registro deve ficar na nova Pagina apTemp ou na
                                           // Pagina
                                           // atual ap;
                            this.insereNaPagina(apTemp, ap.r[this.mm - 1], ap.p[this.mm]);
                            ap.n--;
                            this.insereNaPagina(ap, regRetorno[0], apRetorno); // (b)
                        } else {
                            this.insereNaPagina(apTemp, regRetorno[0] , apRetorno);// (b)
                        }

                        for (int j = this.m + 1; j < this.mm; j++) {
                            this.insereNaPagina(apTemp, ap.r[j], ap.p[j + 1]); // Transfere a metade direita da Pagina
                                                                               // atual
                                                                               // ap para a nova Pagina apTemp;
                            ap.p[j + 1] = null; // transfere a posse da memoria
                        }

                        ap.n = this.m;
                        apTemp.p[0] = ap.p[this.m + 1];
                        regRetorno[0] = ap.r[this.m]; // Atribui o registro do meio à regRetorno → vai subir na árvore;
                        apRetorno = apTemp; // Atribui o objeto apTemp à apRetorno → para ser referenciado pelo novo
                                            // ponteiro da Pagina “superior”;

                    }
                }
            }
        }

        return (cresceu[0] ? apRetorno : ap);
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

    public void setNumElementos(int numElementos) {
        this.numElementos = numElementos;
    }
}

public class ArvoreSBB {

    private int numComparacao;
    private int numElementos;

    private static class No {
        Item reg;
        No esq, dir;
        byte incE, incD;
    }

    private No raiz;
    private static final byte Horizontal = 0;
    private static final byte Vertical = 1;
    private boolean propSBB;

    public ArvoreSBB(Item reg) {

        raiz = new No();
        raiz.reg = reg;
        raiz.esq = raiz.dir = null;
        raiz.incE = raiz.incD = 0; // Idicam o tipo de referência (horizontal ou vertcal) que sai do no
        propSBB = true;
        numComparacao = -1;
        numElementos = 0;
    }

    // Metodos auxiliares para reordenar a arvore para que fique no padrao SBB.

    private No ee(No ap) {
        No ap1 = ap.esq;
        ap.esq = ap1.dir;
        ap1.dir = ap;
        ap1.incE = Vertical;
        ap.incE = Vertical;
        ap = ap1;
        return ap;
    }

    private No ed(No ap) {
        No ap1 = ap.esq;
        No ap2 = ap1.dir;
        ap1.incD = Vertical;
        ap.incE = Vertical;
        ap1.dir = ap2.esq;
        ap2.esq = ap1;
        ap.esq = ap2.dir;
        ap2.dir = ap;
        ap = ap2;
        return ap;
    }

    private No dd(No ap) {
        No ap1 = ap.dir;
        ap.dir = ap1.esq;
        ap1.esq = ap;
        ap1.incD = Vertical;
        ap.incD = Vertical;
        ap = ap1;
        return ap;
    }

    private No de(No ap) {
        No ap1 = ap.dir;
        No ap2 = ap1.esq;
        ap1.incE = Vertical;
        ap.incD = Vertical;
        ap1.esq = ap2.dir;
        ap2.dir = ap1;
        ap.dir = ap2.esq;
        ap2.esq = ap;
        ap = ap2;
        return ap;
    }

    // ---------------------------------------------------------------

    public No insere(Item reg, No pai, No filho, boolean filhoEsq) {

        if (filho == null) {
            numElementos++;

            filho = new No(); // A partir de uma ligação vertical, um novo nó é inserido.
            filho.reg = reg;
            filho.incE = Vertical;
            filho.incD = Vertical;
            filho.esq = null;
            filho.dir = null;
            
            if (pai != null) { // Como a altura (vertical) da árvore é aumentada, a ligação vertical se torna
                               // horizontal.
                if (filhoEsq) {
                    
                    pai.incE = Horizontal;
                } else {
                    pai.incD = Horizontal;
                }
                this.propSBB = false; // Marca a arvore para o rearranjamento SBB
            }

        } else if (reg.compara(filho.reg) < 0) {
            filho.esq = insere(reg, filho, filho.esq, true);

            if (!propSBB) {
                if (filho.incE == Horizontal) {
                    if (filho.esq.incE == Horizontal) {
                        filho = this.ee(filho); // Aqui realizamos a transformação da árvore do tipo EE;

                        if (pai != null) {
                            if (filhoEsq) {
                                pai.incE = Horizontal;
                            } else {
                                pai.incD = Horizontal;
                            }
                        }
                    } else if (filho.esq.incD == Horizontal) {
                        filho = this.ed(filho); // Transformação do tipo ED;
                        if (pai != null) {
                            if (filhoEsq) {
                                pai.incE = Horizontal;
                            } else {
                                pai.incD = Horizontal;
                            }
                        }
                    }
                } else {
                    propSBB = true;
                }
            }
        } else if (reg.compara(filho.reg) > 0) {

            filho.dir = insere(reg, filho, filho.dir, false);

            if (!propSBB) {
                if (filho.incD == Horizontal) {
                    if (filho.dir.incD == Horizontal) {
                        filho = this.dd(filho); // Transformação DD
                        if (pai != null) {
                            if (filhoEsq) {
                                pai.incE = Horizontal;
                            } else {
                                pai.incD = Horizontal;
                            }
                        }
                    } else if (filho.dir.incE == Horizontal) {
                        filho = this.de(filho); // Transformação do tipo DE;

                        if (pai != null) {
                            if (filhoEsq) {
                                pai.incE = Horizontal;
                            } else {
                                pai.incD = Horizontal;
                            }
                        }
                    }
                } else { // Caso todas as inclinações horizontais ja tenham sido devidamente
                    propSBB = true; // transformadas, o padrao SBB esta sendo seguido
                }
            }

        } else {
            System.out.println("Erro: Registro ja existente");
            propSBB = true;
        }
        return filho;
    }

    public Item pesquisar(Item reg, No p) {
        numComparacao++;
        if (p == null) {
            return null;

        } else if (reg.compara(p.reg) < 0) {
            return pesquisar(reg, p.esq);

        } else if (reg.compara(p.reg) > 0) {
            return pesquisar(reg, p.dir);

        } else {
            return p.reg;
        }
    }

    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }

    public No getRaiz() {
        return this.raiz;
    }

    public void setNumComparacao(int numComparacao) {
        this.numComparacao = numComparacao;
    }

    public int getNumComparacao() {
        return numComparacao;
    }

    public int getNumElementos() {
        return numElementos;
    }

}
import java.util.Scanner;

public class testeArvoreB {

    private static final int MAXVALUE = 100000;
    private static final int MINVALUE = 10000;
    private static final int INTERVALO = 10000;
    private static final int NUMPESQUISA = 100001;

    public static void pesquisaOrdenada(ArvoreB ab, int valIni) {

        System.out.println(Cores.ANSI_CYAN + "\nÁrvore B ORDENADA: \n" + Cores.ANSI_RESET
                + "---------------------------------------------");

        while (true) {
            if (valIni > MAXVALUE) {
                break;
            } else {
                Item it = new Item(valIni);
                ab.insere(it);
                valIni += INTERVALO;

                // Tempo gasto na pesquisa e numero de comparacao:
                ab.setNumComparacao(-1);
                long initTime = System.nanoTime();
                ab.pesquisa(new Item(NUMPESQUISA));
                long finishTime = System.nanoTime();
                System.out.println(Cores.ANSI_YELLOW + "Tempo de pesquisa: " + Cores.ANSI_GREEN
                        + (finishTime - initTime) + Cores.ANSI_RESET + " ns" // NanoSegundos (ns) = 10^-9 segs
                        + Cores.ANSI_YELLOW + "\nNumero de comparacoes: " + Cores.ANSI_GREEN + ab.getNumComparacao()
                        + Cores.ANSI_YELLOW + "\nQuantidade de elementos: " + Cores.ANSI_GREEN + ab.getNumElementos()
                        + Cores.ANSI_RESET + "\n---------------------------------------------");

            }
        }
    }

    public static void main(String[] args) {

        System.out.println(Cores.ANSI_YELLOW + "Digite a ordem da arvore B a ser gerada: " + Cores.ANSI_RESET);
        Scanner sc = new Scanner(System.in);
        int mm = sc.nextInt();

        ArvoreB ab = new ArvoreB(mm);
        pesquisaOrdenada(ab, MINVALUE);
        return;

    }
}

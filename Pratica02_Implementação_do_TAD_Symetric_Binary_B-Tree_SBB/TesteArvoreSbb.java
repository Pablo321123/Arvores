import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TesteArvoreSbb {

    private static final int MAXVALUE = 100000;
    private static final int MINVALUE = 10000;
    private static final int INTERVALO = 10000;
    private static final int NUMPESQUISA = 55001;

    public static void pesquisaOrdenada(ArvoreSBB as, int valIni) {

        System.out.println(Cores.ANSI_CYAN + "\nÁrvore ORDENADA: \n" + Cores.ANSI_RESET
                + "---------------------------------------------");

        while (true) {
            if (valIni > MAXVALUE) {
                break;
            } else {
                Item it = new Item(valIni);
                as.setRaiz(as.insere(it, null, as.getRaiz(), false));
                valIni += INTERVALO;

                // Tempo gasto na pesquisa e numero de comparacao:
                as.setNumComparacao(-1);
                long initTime = System.nanoTime();
                as.pesquisar(new Item(NUMPESQUISA), as.getRaiz());
                long finishTime = System.nanoTime();
                System.out.println(Cores.ANSI_YELLOW + "Tempo de pesquisa: " + Cores.ANSI_GREEN
                        + (finishTime - initTime) + Cores.ANSI_RESET + " ns" // NanoSegundos (ns) = 10^-9 segs
                        + Cores.ANSI_YELLOW + "\nNumero de comparacoes: " + Cores.ANSI_GREEN + as.getNumComparacao()
                        + Cores.ANSI_YELLOW + "\nQuantidade de elementos: " + Cores.ANSI_GREEN + as.getNumElementos()
                        + Cores.ANSI_RESET + "\n---------------------------------------------");

            }
        }
    }

    public static void pesquisaAleatoria(ArvoreSBB as, int valIni) {
        System.out.println(Cores.ANSI_CYAN + "\nÁrvore ALEATÓRIA: \n" + Cores.ANSI_RESET
                + "---------------------------------------------");

        ArrayList<Integer> valores = new ArrayList<Integer>();

        for (; valIni <= MAXVALUE;) {
            valores.add(valIni);
            valIni += INTERVALO;
        }

        Random random = new Random(19700621);
        int tam = valores.size();

        for (int i = 0; i < tam; i++) {
            int indexAleatorio = random.nextInt(valores.size());
            as.setRaiz(as.insere(new Item(valores.get(indexAleatorio)), null, as.getRaiz(), false));

            valores.remove(indexAleatorio);

            // Tempo gasto na pesquisa e numero de comparacao:
            as.setNumComparacao(-1);
            long initTime = System.nanoTime();
            as.pesquisar(new Item(NUMPESQUISA), as.getRaiz());
            long finishTime = System.nanoTime();
            System.out.println(Cores.ANSI_YELLOW + "Tempo de pesquisa: " + Cores.ANSI_GREEN + (finishTime - initTime)
                    + Cores.ANSI_RESET + " ns" // NanoSegundos (ns) = 10^-9 segs
                    + Cores.ANSI_YELLOW + "\nNumero de comparacoes: " + Cores.ANSI_GREEN + as.getNumComparacao()
                    + Cores.ANSI_YELLOW + "\nQuantidade de elementos: " + Cores.ANSI_GREEN + as.getNumElementos()
                    + Cores.ANSI_RESET + "\n---------------------------------------------");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println(Cores.ANSI_YELLOW + "Digite o valor da raiz da arvore binaria: " + Cores.ANSI_RESET);
        int valRaiz = sc.nextInt();

        ArvoreSBB as = new ArvoreSBB(new Item(valRaiz));
        pesquisaOrdenada(as, MINVALUE);

        as = new ArvoreSBB(new Item(valRaiz));
        pesquisaAleatoria(as, MINVALUE);

        return;
    }
}

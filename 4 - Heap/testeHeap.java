import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class testeHeap {

    private static final int INTERVALO = 10000;
    private static final int MAX_VALUE = 100000;
    private static final int MIN_VALUE = 10000;

    public static void main(String[] args) {

        int i = 0, j = 0, valor = 0;
        Item matrixTestes[][] = new Item[3][10];

        while (i < 3) {

            switch (i) {
                case 0:
                    valor += INTERVALO;
                    break;
                case 1:
                    valor -= INTERVALO;
                    break;
                case 2:
                    ArrayList<Integer> valores = new ArrayList<>();

                    for (j = 0; j < 10; j++) {
                        valores.add(matrixTestes[0][j].getChave());
                    }

                    Random random = new Random(19700621);
                    int tam = valores.size();

                    for (j = 0; j < 10; j++) {
                        int indexAleatorio = random.nextInt(tam);
                        matrixTestes[i][j] = new Item(valores.get(indexAleatorio));
                        valores.remove(indexAleatorio);
                        tam -= 1;
                    }

                    i++;
                    continue;
            }

            if (valor > MAX_VALUE || valor < MIN_VALUE) {
                i += 1;
                j = 0;
                continue;
            }

            matrixTestes[i][j++] = new Item(valor);
        }

        HeapSort.numComparacao = 0;
        HeapSort.heapsort(matrixTestes[0], 9);
        int numHsOrdenado = HeapSort.numComparacao;
        System.out.println("Numero de comaparacao ORDENADO: " + numHsOrdenado);

        HeapSort.numComparacao = 0;
        HeapSort.heapsort(matrixTestes[1], 9);
        int numHsDesordenado = HeapSort.numComparacao;
        System.out.println("Numero de comaparacao DESORDENADO: " + numHsDesordenado);

        HeapSort.numComparacao = 0;
        HeapSort.heapsort(matrixTestes[2], 9);
        int numHsAleatorio = HeapSort.numComparacao;
        System.out.println("Numero de comaparacao ALEATORIO: " + numHsAleatorio);

        return;
    }
}

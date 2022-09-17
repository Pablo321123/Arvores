/**
 * Item
 */
public class Item {

    private int chave;

    public Item(int chave) {
        this.chave = chave;
    }

    public int compara(Item it) {

        if (chave < it.chave) {
            return -1;
        } else if (chave > it.chave) {
            return 1;
        } else {
            return 0;
        }

    }

    public int getChave() {
        return chave;
    }

}
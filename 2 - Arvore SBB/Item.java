public class Item {

    private int chave;

    public Item(int chave) {
        this.chave = chave;
    }

    public int compara(Item it) {

        if (it.getChave() > chave) {
            return -1;
        } else if (it.getChave() < chave) {
            return 1;
        } else {
            return 0;
        }

    }

    public int getChave() {
        return chave;
    }

}
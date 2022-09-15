public class Entry {
    public int id;
    public int number1;
    public int number2;

    public Entry(int id, int number1, int number2) {
        this.id = id;
        this.number1 = number1;
        this.number2 = number2;
    }

    public String printEntry() {
        return id + " " + number1 + " " + number2;
    }

    public int getId() {
        return this.id;
    }

    public int getNumber1() {
        return this.number1;
    }

    public int getNumber2() {
        return this.number2;
    }
}

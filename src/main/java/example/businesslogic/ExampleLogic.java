package example.businesslogic;

public class ExampleLogic {

    public String getResultString() {
        return "I am your result!";
    }

    public String getSlowResultsString() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getResultString();
    }

    public int getSquare(int input) {
        return input*input;
    }

    public int getSlowSquare(int input) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getSquare(input);
    }

}

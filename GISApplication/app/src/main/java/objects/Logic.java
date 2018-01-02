package objects;

/**
 * Created by Samuel on 02/01/2018.
 */

public class Logic {

    private String logic;

    public Logic(String logic) {
        if (logic.equals("Or") || logic.equals("And") || logic.equals("None"))
            this.logic = logic;
    }

    public String getLogic() {
        if (logic.equals("Or"))
            return "||";
        else if (logic.equals("And"))
            return "&&";
        else return "None";
    }

    public void setLogic(String logic) {
        if (logic.equals("Or") || logic.equals("And") || logic.equals("None"))
            this.logic = logic;
    }

    @Override
    public String toString() {
        return "Logic{" +
                "logic='" + logic + '\'' +
                '}';
    }
}

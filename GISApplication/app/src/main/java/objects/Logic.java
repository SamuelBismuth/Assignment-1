package objects;

/**
 * Created by Samuel on 02/01/2018.
 */

public class Logic {

    private String logic;

    public Logic(String logic) {
        if (logic.equals("or") || logic.equals("and") || logic.equals("none"))
            this.logic = logic;
    }

    public String getLogic() {
        if (logic.equals("or"))
            return "||";
        else if (logic.equals("and"))
            return "&&";
        else return "none";
    }
}

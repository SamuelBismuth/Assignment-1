package objects;

/**
 * This class represents the object logic.
 *
 * @author Orel and Samuel.
 */
public class Logic {

    private String logic;

    /**
     * Constructor.
     *
     * @param logic
     */
    public Logic(String logic) {
        if (logic.equals("Or") || logic.equals("And") || logic.equals("None"))
            this.logic = logic;
    }

    /**
     * @return string which is the logic.
     */
    public String getLogic() {
        if (logic.equals("Or"))
            return "||";
        else if (logic.equals("And"))
            return "&&";
        else return "None";
    }

    /**
     * @param logic
     */
    public void setLogic(String logic) {
        if (logic.equals("Or") || logic.equals("And") || logic.equals("None"))
            this.logic = logic;
    }

    /**
     * To string to display in the Show Database.
     *
     * @return
     */
    @Override
    public String toString() {
        return " logic = " + logic;
    }
}

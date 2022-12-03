public class Chain {

    private boolean isDirty;
    private String lubType;
    // lubType = dry or wet  todo:enum


    public boolean isDirty() {
        return isDirty;
    }

    public void setDirty(boolean dirty) {
        isDirty = dirty;
    }

    public String getLubType() {
        return lubType;
    }

    public void lubeDry() {
        lubType = "dry";
    }
    public void lubeWet() {
        lubType = "wet";
    }
}

package notes;

public enum Note {
    A_FLAT,
    A,
    A_SHARP,
    B_FLAT,
    B,
    C,
    C_SHARP,
    D_FLAT,
    D,
    D_SHARP,
    E_FLAT,
    E,
    F,
    F_SHARP,
    G_FLAT,
    G,
    G_SHARP;

    @Override
    public String toString() {
        String tone = name().substring(0,1);
        if (name().endsWith("_FLAT"))
            tone += "\u266d";
        else if (name().endsWith("_SHARP"))
            tone += "\u266f";
        return tone;
    }
}

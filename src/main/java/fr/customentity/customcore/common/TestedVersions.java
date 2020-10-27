package fr.customentity.customcore.common;

public enum TestedVersions {

    NONE,
    VER_1_7,
    VER_1_8,
    VE_1_9,
    VER_1_10,
    VER_1_11,
    VER_1_12,
    VER_1_13,
    VER_1_14,
    VER_1_15,
    VER_1_16,
    ALL;


    public String getName() {
        return this.name().substring(this.name().indexOf("_") + 1).replace("_", ".");
    }

    public int getIntegerVersion() {
        return Integer.parseInt(getName().replace(".", ""));
    }
}

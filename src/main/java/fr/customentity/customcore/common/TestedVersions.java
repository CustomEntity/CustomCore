package fr.customentity.customcore.common;

public enum TestedVersions {

    NONE(),
    VERSION_1_7,
    VERSION_1_8,
    VERSION_1_9,
    VERSION_1_10,
    VERSION_1_11,
    VERSION_1_12,
    VERSION_1_13,
    VERSION_1_14,
    VERSION_1_15,
    VERSION_1_16,
    ALL;



    public String getName() {
        return this.name().substring(this.name().indexOf("_") + 1).replace("_", ".");
    }

    public int getIntegerVersion() {
        return Integer.parseInt(getName().replace(".", ""));
    }
}

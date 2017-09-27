package muenchen.praxis.mfem.security;

public enum AccessType {
    READ_ACCESS("READ_ACCESS"),
    CREATE_CLASSI("CREATE_CLASSI"),
    CREATE_REQ("CREATE_REQ"),
    CREATE_QUEST("CREATE_QUEST"),
    CREATE_METRIC("CREATE_METRIC"),
    CREATE_CAT("CREATE_CAT"),
    EVALUATION("EVALUATION"),
    ADMIN_ACCESS("ADMIN_ACCESS");

    private String name;

    AccessType(String name) {
        this.name = name;
    }
}

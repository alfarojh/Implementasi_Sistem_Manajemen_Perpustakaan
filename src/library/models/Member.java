package library.models;

public class Member {
    // Penggunaan encapsulation untuk id, nama dan status
    private final String id;
    private final String name;
    private String status;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.status = "active";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void deactivated() {
        this.status = "non active";
    }
}

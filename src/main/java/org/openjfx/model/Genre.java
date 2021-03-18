package org.openjfx.model;

/**
 * GENRE DETAIL
 * @author Dang Ngan Ha
 */
public class Genre {
    private String id;
    private String name;

    public Genre(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre() {
        this.id = "0";
        this.name = "";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.id + " " + this.name;
    }
}

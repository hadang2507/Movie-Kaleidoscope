package org.openjfx.model;

/**
 * PRODUCTION COMPANY DETAIL
 * @author Dang Ngan Ha
 */
public class ProductionCompany {
    private String id;
    private String logo_path;
    private String name;
    private String origin_country;

    public ProductionCompany(String id, String logo_path, String name, String origin_country){
        this.id = id;
        this.logo_path = logo_path;
        this.name = name;
        this.origin_country = origin_country;
    }
}

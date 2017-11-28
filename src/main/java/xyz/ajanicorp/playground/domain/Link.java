package xyz.ajanicorp.playground.domain;

import javax.persistence.*;

/**
 * Created by Ajani on 17/10/2017.
 */
@Entity(name="link")
@Table(name="links")
public class Link {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 4,unique = true)
    private String code;

    private String name;

    private String destinationUrl;

    public Link(Integer id, String code, String name, String destinationUrl) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.destinationUrl = destinationUrl;
    }

    public Link(String code, String name, String destinationUrl) {
        this.code = code;
        this.name = name;
        this.destinationUrl = destinationUrl;
    }

    public Link() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestinationUrl() {
        return destinationUrl;
    }

    public void setDestinationUrl(String destinationUrl) {
        this.destinationUrl = destinationUrl;
    }
}

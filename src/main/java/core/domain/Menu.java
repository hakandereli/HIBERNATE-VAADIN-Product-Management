package core.domain;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
@Table(name = "menu")
public class Menu extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ad", length = 50)
    private String ad;

    @Column(name = "classdirectory", length = 255)
    private String classdirectory;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getClassdirectory() {
        return classdirectory;
    }

    public void setClassdirectory(String classdirectory) {
        this.classdirectory = classdirectory;
    }
}

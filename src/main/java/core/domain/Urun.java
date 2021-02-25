package core.domain;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
@Table(name = "urun")
public class Urun extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_kategori", referencedColumnName = "id", foreignKey = @ForeignKey(name = "ID_KATEGORI"))
    private Kategori kategorisi;

    @Column(name = "ad", length = 200)
    private String ad;

    @Lob
    @Column(name = "aciklama")
    private String aciklama;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kategori getKategorisi() {
        return kategorisi;
    }

    public void setKategorisi(Kategori kategorisi) {
        this.kategorisi = kategorisi;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    @Override
    public String toString() {
        return "Urun{" +
                "id=" + id +
                ", kategorisi=" + kategorisi +
                ", ad='" + ad + '\'' +
                ", aciklama='" + aciklama + '\'' +
                '}';
    }
}

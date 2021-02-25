package core.service;

import core.data.KategoriDao;
import core.domain.Kategori;

import java.util.List;

public class KategoriService {
    KategoriDao kategoriDao = new KategoriDao();

    public void addKategori(Kategori kategori) {
        kategoriDao.addKategori(kategori);
    }

    public List<Kategori> findAll() {
        return kategoriDao.listingKategori();
    }

    public void deleteKategori(Kategori kategori) {
        kategoriDao.deleteKategori(kategori);
    }

}

package core.service;

import core.data.UrunDao;
import core.domain.Urun;

import java.util.List;

public class UrunService {
    UrunDao urunDao = new UrunDao();

    public void addOrUpdate(Urun urun) {
        urunDao.addOrUpdate(urun);
    }

    public List<Urun> findAll() {
        return urunDao.findAll();
    }

    public void deleteUrun(Urun urun) {
        urunDao.deleteUrun(urun);
    }
}

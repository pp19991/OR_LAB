package com.OR.OR;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Repository
public class AllRepository {
    private final JdbcTemplate jdbc;


    public AllRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    //GET
    public List<Podatak> dohvatiSve(Integer id) {
        String query="SELECT \n" +
                "    igraci.id AS igrac_id,\n" +
                "    igraci.ime AS igrac_ime,\n" +
                "    igraci.prezime AS igrac_prezime,\n" +
                "    igraci.pozicija AS igrac_pozicija,\n" +
                "    igraci.klub AS igrac_klub,\n" +
                "    klub.naziv AS klub_naziv,\n" +
                "    klub.grad AS klub_grad,\n" +
                "    klub.godina_osnutka AS klub_godina_osnutka,\n" +
                "    klub.stadion AS klub_stadion,\n" +
                "    klub.trener AS klub_trener,\n" +
                "    klub.broj_trofeja_ligi AS klub_broj_trofeja_ligi,\n" +
                "    klub.broj_igraca AS klub_broj_igraca,\n" +
                "    klub.prosjek_godina AS klub_prosjek_godina,\n" +
                "    klub.broj_bodova_prosle_godine AS klub_broj_bodova_prosle_godine,\n" +
                "    klub.broj_ligi_prvaka AS klub_broj_ligi_prvaka,\n" +
                "    klub.broj_kupova AS klub_broj_kupova,\n" +
                "    klub.tm_vrijednost AS klub_tm_vrijednost,\n" +
                "    stadion.naziv AS stadion_naziv,\n" +
                "    stadion.godina_osnutka AS stadion_godina_osnutka,\n" +
                "    stadion.kapacitet AS stadion_kapacitet,\n" +
                "    trener.id AS trener_id,\n" +
                "    trener.ime AS trener_ime,\n" +
                "    trener.prezime AS trener_prezime\n" +
                "FROM klub \n" +
                "JOIN stadion ON klub.stadion = stadion.naziv\n" +
                "JOIN trener ON klub.trener = trener.id\n" +
                "JOIN igraci ON igraci.klub = klub.naziv";
        RowMapper<Podatak> purchaseRowMapper = (r, i) -> {
            Podatak rowObject = new Podatak();

            Igrac igrac = new Igrac();
            igrac.setId(r.getInt("igrac_id"));
            igrac.setIme(r.getString("igrac_ime"));
            igrac.setPrezime(r.getString("igrac_prezime"));
            igrac.setPozicija(r.getString("igrac_pozicija"));
            igrac.setKlub(r.getString("igrac_klub"));

            Klub klub = new Klub();
            klub.setNaziv(r.getString("klub_naziv"));
            klub.setGrad(r.getString("klub_grad"));
            klub.setGodinaOsnutka(r.getInt("klub_godina_osnutka"));
            klub.setStadion(r.getString("klub_stadion"));
            klub.setTrener(r.getInt("klub_trener"));
            klub.setBrojTrofejaLigi(r.getInt("klub_broj_trofeja_ligi"));
            klub.setBrojIgraca(r.getInt("klub_broj_igraca"));
            klub.setProsjekGodina(r.getDouble("klub_prosjek_godina"));
            klub.setBrojBodovaProsleGodine(r.getInt("klub_broj_bodova_prosle_godine"));
            klub.setBrojLigiPrvaka(r.getInt("klub_broj_ligi_prvaka"));
            klub.setBrojKupova(r.getInt("klub_broj_kupova"));
            klub.setTmVrijednost(r.getDouble("klub_tm_vrijednost"));

            Stadion stadion = new Stadion();
            stadion.setNaziv(r.getString("stadion_naziv"));
            stadion.setGodinaOsnutka(r.getInt("stadion_godina_osnutka"));
            stadion.setKapacitet(r.getInt("stadion_kapacitet"));

            Trener trener = new Trener();
            trener.setId(r.getInt("trener_id"));
            trener.setIme(r.getString("trener_ime"));
            trener.setPrezime(r.getString("trener_prezime"));

            rowObject.setKlub(klub);
            rowObject.setIgrac(igrac);
            rowObject.setStadion(stadion);
            rowObject.setTrener(trener);

            System.out.println(rowObject);
            return rowObject;
        };
        if(id!=null){
            query = query+" where igraci.id=?";
            return jdbc.query(query, purchaseRowMapper,id);
        }
        return jdbc.query(query, purchaseRowMapper);
    }
    public List<Stadion> getAllStadion(String naziv) {
        String querry = "SELECT * FROM stadion";
        //String querry=generateQuerry("SELECT * FROM stadion",value);
        RowMapper<Stadion> purchaseRowMapper = (r, i) -> {
            Stadion rowObject = new Stadion();
            rowObject.setNaziv(r.getString("naziv"));
            rowObject.setGodinaOsnutka(r.getInt("godina_osnutka"));
            rowObject.setKapacitet(r.getInt("kapacitet"));
            return rowObject;
        };
        if(naziv!=null){
            querry = "SELECT * FROM stadion where naziv=?";
            return jdbc.query(querry, purchaseRowMapper,naziv);
        }
        return jdbc.query(querry, purchaseRowMapper);
    }


    public List<Klub> getAllKlub(String naziv) {
        String querry = "SELECT * FROM klub";
        //String querry=generateQuerry("SELECT * FROM klub",value);
        RowMapper<Klub> purchaseRowMapper = (r, i) -> {
            Klub klub = new Klub();
            klub.setNaziv(r.getString("naziv"));
            klub.setGrad(r.getString("grad"));
            klub.setGodinaOsnutka(r.getInt("godina_osnutka"));
            klub.setStadion(r.getString("stadion"));
            klub.setTrener(r.getInt("trener"));
            klub.setBrojTrofejaLigi(r.getInt("broj_trofeja_ligi"));
            klub.setBrojIgraca(r.getInt("broj_igraca"));
            klub.setProsjekGodina(r.getDouble("prosjek_godina"));
            klub.setBrojBodovaProsleGodine(r.getInt("broj_bodova_prosle_godine"));
            klub.setBrojLigiPrvaka(r.getInt("broj_ligi_prvaka"));
            klub.setBrojKupova(r.getInt("broj_kupova"));
            klub.setTmVrijednost(r.getDouble("TM_vrijednost"));
            return klub;
        };
        if(naziv!=null){
            querry = "SELECT * FROM klub where naziv=?";
            return jdbc.query(querry, purchaseRowMapper,naziv);
        }
        return jdbc.query(querry, purchaseRowMapper);

    }

    public List<Igrac> getAllIgrac(Integer id) {
        String querry = "SELECT * FROM igraci";

        //String querry=generateQuerry("SELECT * FROM igraci",value);
        RowMapper<Igrac> purchaseRowMapper = (r, i) -> {
            Igrac rowObject = new Igrac();
            rowObject.setId(r.getInt("id"));
            rowObject.setIme(r.getString("ime"));
            rowObject.setPrezime(r.getString("prezime"));
            rowObject.setPozicija(r.getString("pozicija"));
            rowObject.setKlub(r.getString("klub"));
            return rowObject;
        };
        if(id!=null){
            querry = "SELECT * FROM igraci where id=?";
            return jdbc.query(querry, purchaseRowMapper,id);
        }
        return jdbc.query(querry, purchaseRowMapper);
    }

    public List<Trener> getAllTrener(Integer id) {
        String querry = "SELECT * FROM trener";
        //String querry=generateQuerry("SELECT * FROM trener",value);
        RowMapper<Trener> purchaseRowMapper = (r, i) -> {
            Trener rowObject = new Trener();
            rowObject.setId(r.getInt("id"));
            rowObject.setIme(r.getString("ime"));
            rowObject.setPrezime(r.getString("prezime"));
            return rowObject;
        };
        if(id!=null){
            querry = "SELECT * FROM trener where id=?";
            return jdbc.query(querry, purchaseRowMapper,id);
        }
        return jdbc.query(querry, purchaseRowMapper);
    }

    //POST
    public void postKlub(Klub klub) {
        String querry = "INSERT INTO klub (naziv, grad, godina_osnutka, stadion, trener, broj_trofeja_ligi, " +
                "broj_igraca, prosjek_godina, broj_bodova_prosle_godine, broj_ligi_prvaka, broj_kupova, tm_vrijednost) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbc.update(querry,
                klub.getNaziv(),
                klub.getGrad(),
                klub.getGodinaOsnutka(),
                klub.getStadion(),
                klub.getTrener(),
                klub.getBrojTrofejaLigi(),
                klub.getBrojIgraca(),
                klub.getProsjekGodina(),
                klub.getBrojBodovaProsleGodine(),
                klub.getBrojLigiPrvaka(),
                klub.getBrojKupova(),
                klub.getTmVrijednost()
        );
    }

    public void postIgrac(Igrac igrac) {
        String querry = "INSERT INTO igraci (id,ime,prezime,pozicija,klub) VALUES (?, ?, ?, ?, ?)";
        jdbc.update(querry,
                igrac.getId(),igrac.getIme(),igrac.getPrezime(),igrac.getPozicija(),igrac.getKlub()
        );
    }

    public void postTrener(Trener trener) {
        String querry = "INSERT INTO trener (id,ime,prezime) VALUES (?, ?, ?)";
        jdbc.update(querry,
                trener.getId(),trener.getIme(),trener.getPrezime()
        );
    }

    public void postStadion(Stadion stadion) {
        String querry = "INSERT INTO stadion (naziv,kapacitet,godina_osnutka) VALUES (?, ?, ?)";
        jdbc.update(querry,
                stadion.getNaziv(),stadion.getKapacitet(),stadion.getGodinaOsnutka()
        );
    }

    public void postSve(Podatak podatak) {
        postKlub(podatak.getKlub());
        postIgrac(podatak.getIgrac());
        postStadion(podatak.getStadion());
        postTrener(podatak.getTrener());
    }


    //DELETE
    public void deleteSve(Integer id) {
        String querry = "DELETE FROM igraci where id=?";
        jdbc.update(querry,
                id
        );
    }

    public void deleteAllKlub(String naziv) {
        String querry = "DELETE FROM klub where naziv=?";
        jdbc.update(querry,
                naziv
        );
    }

    public void deleteAllIgrac(Integer id) {
        String querry = "DELETE FROM igraci where id=?";
        jdbc.update(querry,
                id
        );
    }

    public void deleteAllTrener(Integer id) {
        String querry = "DELETE FROM trener where id=?";
        jdbc.update(querry,
                id
        );
    }

    public void deleteAllStadion(String naziv) {
        String querry = "DELETE FROM stadion where naziv=?";
        jdbc.update(querry,
               naziv
        );
    }


    //PUT
    public void putSve(Podatak podatak) {
        putKlub(podatak.getKlub());
        putStadion(podatak.getStadion());
        putIgrac(podatak.getIgrac());
        putTrener(podatak.getTrener());

    }

    public void putKlub(Klub klub) {
        String query = "UPDATE klub SET grad = ?, godina_osnutka = ?, stadion = ?, trener = ?, " +
                "broj_trofeja_ligi = ?, broj_igraca = ?, prosjek_godina = ?, broj_bodova_prosle_godine = ?, " +
                "broj_ligi_prvaka = ?, broj_kupova = ?, tm_vrijednost = ? WHERE naziv = ?";

        jdbc.update(query,

                klub.getGrad(),
                klub.getGodinaOsnutka(),
                klub.getStadion(),
                klub.getTrener(),
                klub.getBrojTrofejaLigi(),
                klub.getBrojIgraca(),
                klub.getProsjekGodina(),
                klub.getBrojBodovaProsleGodine(),
                klub.getBrojLigiPrvaka(),
                klub.getBrojKupova(),
                klub.getTmVrijednost(),
                klub.getNaziv()
        );

    }

    public void putIgrac(Igrac igrac) {
        String query = "UPDATE igraci SET ime = ?, prezime = ?, pozicija = ?, klub = ? WHERE id = ?";
        jdbc.update(query,
                igrac.getIme(),
                igrac.getPrezime(),
                igrac.getPozicija(),
                igrac.getKlub(),
                igrac.getId()
        );
    }

    public void putTrener(Trener trener) {
        String query = "UPDATE trener SET ime = ?, prezime = ? WHERE id = ?";
        jdbc.update(query,
                trener.getIme(),
                trener.getPrezime(),
                trener.getId()
        );
    }

    public void putStadion(Stadion stadion) {
        String query = "UPDATE stadion SET kapacitet = ?, godina_osnutka = ? WHERE naziv = ?";
        jdbc.update(query,
                stadion.getKapacitet(),
                stadion.getGodinaOsnutka(),
                stadion.getNaziv() // Assuming you have an `id` field to identify the record to update
        );
    }

    public void osFajlove(List<Podatak> podaci) throws IOException, IllegalAccessException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(podaci);
        Files.write(Paths.get("C:\\Users\\Korisnik\\Desktop\\labos-4\\REACT\\my-app\\public\\klubovi_bundeslige.json"), jsonString.getBytes());
        StringBuilder string_csv = new StringBuilder("k_naziv,k_grad,k_godina_osnutka,k_stadion,k_trener,k_broj_trofeja_ligi,k_broj_igraca,k_prosjek_godina,k_broj_bodova_prosle_godine,k_broj_ligi_prvaka,k_broj_kupova,k_tm_vrijednost,t_id,t_ime,t_prezime,i_id,i_ime,i_prezime,i_pozicija,i_klub,s_naziv,s_kapacitet,s_godina_osnutka\n");
        for(Podatak podatak:podaci){
            Field[] fields;
            fields=podatak.getKlub().getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true); // Allow access to private fields

                string_csv.append(field.get(podatak.getKlub())+",");
            }
            fields=podatak.getTrener().getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true); // Allow access to private fields
                string_csv.append(field.get(podatak.getTrener())+",");
            }
            fields=podatak.getIgrac().getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true); // Allow access to private fields
                string_csv.append(field.get(podatak.getIgrac())+",");
            }
            fields=podatak.getStadion().getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true); // Allow access to private fields
                string_csv.append(field.get(podatak.getStadion())+",");
            }
            string_csv.deleteCharAt(string_csv.length() - 1);
            string_csv.append("\n");
        }
        Files.write(Paths.get("C:\\Users\\Korisnik\\Desktop\\labos-4\\REACT\\my-app\\public\\klubovi_bundeslige.csv"), string_csv.toString().getBytes());

    }
}



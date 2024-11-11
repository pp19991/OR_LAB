package com.OR.OR;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class AllRepository {
    private final JdbcTemplate jdbc;


    public AllRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    public List<Stadion> getAllStadion(String value) {
        //String querry = "SELECT * FROM stadion";
        String querry=generateQuerry("SELECT * FROM stadion",value);
        RowMapper<Stadion> purchaseRowMapper = (r, i) -> {
            Stadion rowObject = new Stadion();
            rowObject.setNaziv(r.getString("naziv"));
            rowObject.setGodinaOsnutka(r.getInt("godina_osnutka"));
            rowObject.setKapacitet(r.getInt("kapacitet"));
            return rowObject;
        };
        return jdbc.query(querry, purchaseRowMapper);
    }

    private String generateQuerry(String s, String value) {
        if(value==""){
            return s;
        }
        System.out.println(s);
        System.out.println(value);
        String[] strings = value.split("\\?");
        System.out.println(Arrays.toString(strings));
        //String result= String.join(s, " WHERE ");
        StringBuilder result = new StringBuilder(s);
        result.append(" WHERE ");
        System.out.println("Rezultat "+result);
        for (int i = 0; i < strings.length; i++) {
            String[] sarray=strings[i].split("=");
            System.out.println(Arrays.toString(sarray));
            //result= String.join(result,sarray[0]);
            result.append(sarray[0]);
            System.out.println("Rezultat "+result);
            if(sarray[1].contains(",")){
                //result= String.join(result," IN (");
                result.append(" IN (");
                System.out.println("Rezultat "+result);
                String[] sarray1=sarray[1].split(",");
                for (int j = 0; j < sarray1.length; j++) {
                    //result= String.join(result,sarray1[j]);
                    //result.append(sarray1[j]);
                    if (sarray1[j].matches(".*\\d.*")) {
                        result.append(sarray1[j]);
                    }
                    else{
                        result.append("'");
                        result.append(sarray1[j]);
                        result.append("'");
                    }
                    System.out.println("Rezultat "+result);
                    //result= String.join(result,",");
                    result.append(",");
                    System.out.println("Rezultat "+result);
                }
                System.out.println("Rezultat "+result);
                //result=result.substring(0,result.length()-1);
                //result= String.join(result,")");
                result.setCharAt(result.length() - 1, ')');
                }
            else if(sarray[1].contains("-")){
                result.append(" BETWEEN ");
                System.out.println("Rezultat "+result);
                String[] sarray1=sarray[1].split("-");
                for (int j = 0; j < sarray1.length; j++) {
                    //result= String.join(result,sarray1[j]);
                    //result.append(sarray1[j]);
                    if (sarray1[j].matches(".*\\d.*")) {
                        result.append(sarray1[j]);
                    }
                    else{
                        result.append("'");
                        result.append(sarray1[j]);
                        result.append("'");
                    }
                    System.out.println("Rezultat "+result);
                    //result= String.join(result,",");
                    result.append(" AND ");
                    System.out.println("Rezultat "+result);
                }
                System.out.println("Rezultat "+result);
                //result=result.substring(0,result.length()-1);
                //result= String.join(result,")");
                result.delete(result.length() - 5, result.length());
            }
            else{
                result.append("=");
                if (sarray[1].matches(".*\\d.*")) {
                    result.append(sarray[1]);
                }
                else{
                    result.append("'");
                    result.append(sarray[1]);
                    result.append("'");
                }

            }
            result.append(" AND ");
            }
        result.delete(result.length() - 5, result.length());
        System.out.println(result.toString());

        return result.toString();
    }

    public List<Klub> getAllKlub(String value) {
        //String querry = "SELECT * FROM klub";
        String querry=generateQuerry("SELECT * FROM klub",value);
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
        return jdbc.query(querry, purchaseRowMapper);

    }

    public List<Igrac> getAllIgrac(String value) {
        //String querry = "SELECT * FROM igraci";
        String querry=generateQuerry("SELECT * FROM igraci",value);
        RowMapper<Igrac> purchaseRowMapper = (r, i) -> {
            Igrac rowObject = new Igrac();
            rowObject.setId(r.getInt("id"));
            rowObject.setIme(r.getString("ime"));
            rowObject.setPrezime(r.getString("prezime"));
            rowObject.setPozicija(r.getString("pozicija"));
            rowObject.setKlub(r.getString("klub"));
            return rowObject;
        };
        return jdbc.query(querry, purchaseRowMapper);
    }

    public List<Trener> getAllTrener(String value) {
        //String querry = "SELECT * FROM trener";
        String querry=generateQuerry("SELECT * FROM trener",value);
        RowMapper<Trener> purchaseRowMapper = (r, i) -> {
            Trener rowObject = new Trener();
            rowObject.setId(r.getInt("id"));
            rowObject.setIme(r.getString("ime"));
            rowObject.setPrezime(r.getString("prezime"));
            return rowObject;
        };
        return jdbc.query(querry, purchaseRowMapper);
    }
}



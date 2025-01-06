package com.OR.OR;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {
    private AllRepository repo;

    public Controller(AllRepository repo) {
        this.repo = repo;
    }

    //GET
    @GetMapping("/sve")
    public ResponseEntity<ResponseC<List<Podatak>>> sviPodaci(@RequestParam(required = false) Integer id) {
        List<Podatak> podaci=repo.dohvatiSve(id);
        ResponseC<List<Podatak>> r=new ResponseC<>("OK","Dohvaceni cjelokupni podaci",podaci);
        return ResponseEntity.ok().body(r);
    }
    @GetMapping("/klubovi")
    public ResponseEntity<ResponseC<List<Klub>>> klubovi(@RequestParam(required = false) String naziv) {
        List<Klub> podaci=repo.getAllKlub(naziv);
        ResponseC<List<Klub>> r=new ResponseC<>("OK","Dohvaceni podaci o klubovima",podaci);
        return ResponseEntity.ok().body(r);
    }
    @GetMapping("/igraci")
    public ResponseEntity<ResponseC<List<Igrac>>> igraci(@RequestParam(required = false) String id) {
        Integer i=null;
        if(id!=null){
            i=Integer.valueOf(id);
        }
        List<Igrac> podaci=repo.getAllIgrac(i);
        ResponseC<List<Igrac>> r=new ResponseC<>("OK","Dohvaceni podaci o igracima",podaci);
        return ResponseEntity.ok().body(r);

    }
    @GetMapping("/treneri")
    public ResponseEntity<ResponseC<List<Trener>>> treneri(@RequestParam(required = false) String id) {
        Integer i=null;
        if(id!=null){
            i=Integer.valueOf(id);
        }
        List<Trener> podaci=repo.getAllTrener(i);
        ResponseC<List<Trener>> r=new ResponseC<>("OK","Dohvaceni podaci o trenerima",podaci);
        return ResponseEntity.ok().body(r);
    }
    @GetMapping("/stadioni")
    public ResponseEntity<ResponseC<List<Stadion>>>  stadioni(@RequestParam(required = false) String naziv) {
        List<Stadion> podaci=repo.getAllStadion(naziv);
        ResponseC<List<Stadion>> r=new ResponseC<>("OK","Dohvaceni podaci o stadionima",podaci);
        return ResponseEntity.ok().body(r);
    }

    //POST
    @PostMapping("/sve")
    public ResponseEntity<ResponseC<String>> postPodaci(@RequestBody Podatak podatak) {
        repo.postSve(podatak);
        ResponseC<String> r=new ResponseC<>("OK","Dodan cijelokupni podatak",null);
        return ResponseEntity.ok().body(r);
    }
    @PostMapping("/klubovi")
    public ResponseEntity<ResponseC<String>> postKlubovi(@RequestBody Klub klub) {
        repo.postKlub(klub);
        ResponseC<String> r=new ResponseC<>("OK","Dodan klub",null);
        return ResponseEntity.ok().body(r);
    }
    @PostMapping("/igraci")
    public ResponseEntity<ResponseC<String>> postIgraci(@RequestBody Igrac igrac) {
        repo.postIgrac(igrac);
        ResponseC<String> r=new ResponseC<>("OK","Dodan igrac",null);
        return ResponseEntity.ok().body(r);
    }
    @PostMapping("/treneri")
    public ResponseEntity<ResponseC<String>> postTreneri(@RequestBody Trener trener) {
        repo.postTrener(trener);
        ResponseC<String> r=new ResponseC<>("OK","Dodan trener",null);
        return ResponseEntity.ok().body(r);
    }
    @PostMapping("/stadioni")
    public ResponseEntity<ResponseC<String>> postStadioni(@RequestBody Stadion stadion) {
        repo.postStadion(stadion);
        ResponseC<String> r=new ResponseC<>("OK","Dodan stadion",null);
        return ResponseEntity.ok().body(r);
    }

    //DELETE
    @DeleteMapping("/sve")
    public ResponseEntity<ResponseC<String>> deletePodaci(@RequestParam Integer id) {
         repo.deleteSve(id);
        ResponseC<String> r=new ResponseC<>("OK","Izbrisan podatak",null);
        return ResponseEntity.ok().body(r);
    }
    @DeleteMapping("/klubovi")
    public ResponseEntity<ResponseC<String>> deleteKlubovi(@RequestParam String naziv) {
        repo.deleteAllKlub(naziv);
        ResponseC<String> r=new ResponseC<>("OK","Izbrisan klub",null);
        return ResponseEntity.ok().body(r);
    }
    @DeleteMapping("/igraci")
    public ResponseEntity<ResponseC<String>> deleteIgraci(@RequestParam Integer id) {
        repo.deleteAllIgrac(id);
        ResponseC<String> r=new ResponseC<>("OK","Izbrisan igrac",null);
        return ResponseEntity.ok().body(r);
    }
    @DeleteMapping("/treneri")
    public ResponseEntity<ResponseC<String>> deleteTreneri(@RequestParam Integer id) {
        repo.deleteAllTrener(id);
        ResponseC<String> r=new ResponseC<>("OK","Izbrisan trener",null);
        return ResponseEntity.ok().body(r);
    }
    @DeleteMapping("/stadioni")
    public ResponseEntity<ResponseC<String>> deleteStadioni(@RequestParam String naziv) {
        repo.deleteAllStadion(naziv);
        ResponseC<String> r=new ResponseC<>("OK","Izbrisan stadion",null);
        return ResponseEntity.ok().body(r);
    }

    //PUT
    @PutMapping("/sve")
    public ResponseEntity<ResponseC<String>> putPodaci(@RequestBody Podatak podatak) {
        repo.putSve(podatak);
        ResponseC<String> r=new ResponseC<>("OK","Promjenjen cijelokupni podatak podatak",null);
        return ResponseEntity.ok().body(r);
    }
    @PutMapping("/klubovi")
    public ResponseEntity<ResponseC<String>> putKlubovi(@RequestBody Klub klub) {
        repo.putKlub(klub);
        ResponseC<String> r=new ResponseC<>("OK","Promjenjen podatak o klubu",null);
        return ResponseEntity.ok().body(r);
    }
    @PutMapping("/igraci")
    public ResponseEntity<ResponseC<String>> putIgraci(@RequestBody Igrac igrac) {
        repo.putIgrac(igrac);
        ResponseC<String> r=new ResponseC<>("OK","Promjenjen podatak o igracu",null);
        return ResponseEntity.ok().body(r);
    }
    @PutMapping("/treneri")
    public ResponseEntity<ResponseC<String>> putTreneri(@RequestBody Trener trener) {
        repo.putTrener(trener);
        ResponseC<String> r=new ResponseC<>("OK","Promjenjen podatak o treneru",null);
        return ResponseEntity.ok().body(r);
    }
    @PutMapping("/stadioni")
    public ResponseEntity<ResponseC<String>> putStadioni(@RequestBody Stadion stadion) {
        repo.putStadion(stadion);
        ResponseC<String> r=new ResponseC<>("OK","Promjenjen podatak o stadionu",null);
        return ResponseEntity.ok().body(r);
    }
    @GetMapping("/openapi")
    public ResponseEntity<Resource> downloadFile() throws Exception {

        Path filePath = Paths.get("C:\\Users\\Korisnik\\Desktop\\labos-or3\\openapi.json");
        Resource resource = new UrlResource(filePath.toUri());  // Correct way to pass the URI

        if (resource.exists()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            throw new Exception("File not found");
        }
    }

}

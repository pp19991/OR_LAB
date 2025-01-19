package com.OR.OR;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {
    private AllRepository repo;

    public Controller(AllRepository repo) {
        this.repo = repo;
    }

    //GET
    @GetMapping("/sve")
    public List<Podatak> sviPodaci(@RequestParam(required = false) Integer id) {
        return repo.dohvatiSve(id);
    }
    @PostMapping("/osvjezi")
    public void osvjezi(@RequestParam(required = false) Integer id) throws IOException, IllegalAccessException {
       List<Podatak> podaci= repo.dohvatiSve(null);
       repo.osFajlove(podaci);
    }
    @GetMapping("/klubovi")
    public List<Klub> klubovi(@RequestParam(required = false) String naziv) {
        return repo.getAllKlub(naziv);
    }
    @GetMapping("/igraci")
    public List<Igrac> igraci(@RequestParam(required = false) String id) {
        Integer i=null;
        if(id!=null){
            i=Integer.valueOf(id);
        }
        return repo.getAllIgrac(i);
    }
    @GetMapping("/treneri")
    public List<Trener> treneri(@RequestParam(required = false) String id) {
        Integer i=null;
        if(id!=null){
            i=Integer.valueOf(id);
        }
        return repo.getAllTrener(i);
    }
    @GetMapping("/stadioni")
    public List<Stadion> stadioni(@RequestParam(required = false) String naziv) {
        return repo.getAllStadion(naziv);
    }
    @GetMapping("/user-info")
    public Map<String, Object> userinfo(@AuthenticationPrincipal OidcUser principal) {
        Map<String, Object> claims = principal.getClaims();
        return claims;
    }
    @GetMapping("/loggedIn")
    public boolean loggedin(@AuthenticationPrincipal OidcUser principal) {
        if(principal==null){
            return false;
        }
        return true;
    }
    //POST
    @PostMapping("/sve")
    public void postPodaci(@RequestBody Podatak podatak) {
        System.out.println(podatak);
        repo.postSve(podatak);
    }
    @PostMapping("/klubovi")
    public void postKlubovi(@RequestBody Klub klub) {
        repo.postKlub(klub);
    }
    @PostMapping("/igraci")
    public void postIgraci(@RequestBody Igrac igrac) {
        repo.postIgrac(igrac);
    }
    @PostMapping("/treneri")
    public void postTreneri(@RequestBody Trener trener) {
        repo.postTrener(trener);
    }
    @PostMapping("/stadioni")
    public void postStadioni(@RequestBody Stadion stadion) {
        repo.postStadion(stadion);
    }

    //DELETE
    @DeleteMapping("/sve")
    public void deletePodaci(@RequestParam Integer id) {
         repo.deleteSve(id);
    }
    @DeleteMapping("/klubovi")
    public void deleteKlubovi(@RequestParam String naziv) {
        repo.deleteAllKlub(naziv);
    }
    @DeleteMapping("/igraci")
    public void deleteIgraci(@RequestParam Integer id) {
        repo.deleteAllIgrac(id);
    }
    @DeleteMapping("/treneri")
    public void deleteTreneri(@RequestParam Integer id) {
        repo.deleteAllTrener(id);
    }
    @DeleteMapping("/stadioni")
    public void deleteStadioni(@RequestParam String naziv) {
        repo.deleteAllStadion(naziv);
    }
    //PUT
    @PutMapping("/sve")
    public void putPodaci(@RequestBody Podatak podatak) {
        repo.putSve(podatak);
    }
    @PutMapping("/klubovi")
    public void putKlubovi(@RequestBody Klub klub) {
        repo.putKlub(klub);
    }
    @PutMapping("/igraci")
    public void putIgraci(@RequestBody Igrac igrac) {
        repo.putIgrac(igrac);
    }
    @PutMapping("/treneri")
    public void putTreneri(@RequestBody Trener trener) {
        repo.putTrener(trener);
    }
    @PutMapping("/stadioni")
    public void putStadioni(@RequestBody Stadion stadion) {
        repo.putStadion(stadion);
    }

}

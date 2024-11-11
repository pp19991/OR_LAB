package com.OR.OR;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
public class Controller {
    private AllRepository repo;

    public Controller(AllRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/klubovi")
    public List<Klub> klubovi(@RequestParam(required = false) Map<String, String> mapa) {
        String parameterValue = (mapa != null && !mapa.isEmpty()) ? mapa.entrySet().iterator().next().getValue() : "";
        String parameterValue1 = (mapa != null && !mapa.isEmpty()) ? mapa.entrySet().iterator().next().getKey() : "";

        String parametar="";
        if(parameterValue!="" && parameterValue1!=""){
            parametar=String.join("=",parameterValue1,parameterValue);
        }
        System.out.println(parametar);
        return repo.getAllKlub(parametar);
    }
    @GetMapping("/igraci")
    public List<Igrac> igraci(@RequestParam(required = false) Map<String, String> mapa) {
        String parameterValue = (mapa != null && !mapa.isEmpty()) ? mapa.entrySet().iterator().next().getValue() : "";
        String parameterValue1 = (mapa != null && !mapa.isEmpty()) ? mapa.entrySet().iterator().next().getKey() : "";

        String parametar="";
        if(parameterValue!="" && parameterValue1!=""){
            parametar=String.join("=",parameterValue1,parameterValue);
        }
        System.out.println(parametar);
        return repo.getAllIgrac(parametar);
    }
    @GetMapping("/treneri")
    public List<Trener> treneri(@RequestParam(required = false) Map<String, String> mapa) {
        String parameterValue = (mapa != null && !mapa.isEmpty()) ? mapa.entrySet().iterator().next().getValue() : "";
        String parameterValue1 = (mapa != null && !mapa.isEmpty()) ? mapa.entrySet().iterator().next().getKey() : "";

        String parametar="";
        if(parameterValue!="" && parameterValue1!=""){
            parametar=String.join("=",parameterValue1,parameterValue);
        }
        System.out.println(parametar);
        return repo.getAllTrener(parametar);
    }
    @GetMapping("/stadioni")
    public List<Stadion> stadioni(@RequestParam(required = false) Map<String, String> mapa) {
        String parameterValue = (mapa != null && !mapa.isEmpty()) ? mapa.entrySet().iterator().next().getValue() : "";
        String parameterValue1 = (mapa != null && !mapa.isEmpty()) ? mapa.entrySet().iterator().next().getKey() : "";

        String parametar="";
        if(parameterValue!="" && parameterValue1!=""){
            parametar=String.join("=",parameterValue1,parameterValue);
        }
        System.out.println(parametar);
        return repo.getAllStadion(parametar);
    }
}

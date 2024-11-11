
function JSONclick(){
 
  console.log("klikno sam");
}

function prikazi(){
    let optvalue=document.getElementById("atribut").value;
    let text=document.getElementById("uv").value;
    let path="http://localhost:8080/"+document.getElementById("tablica").value;
    if(optvalue=="sva"){
      if(text!=""){
        let polje=text.split(", ");
        console.log(polje);
        polje.forEach(p=>{
          let pomocno=p.split(":");
          path=path+"?"+pomocno[0]+"="+pomocno[1];
        });
      };
    }
    else{
      if(text!=""){
        path=path+"?"+optvalue+"="+text;
      };
    }
    
    let podaci;
    console.log(text);
    console.log(text.lenght);
    console.log(path);
    fetch(path, {
        method: 'GET', 
        headers: {
          'Content-Type': 'application/json; charset=UTF-8' 
        }
      })
        .then(response => response.json())
        .then(data => {
           console.log(data);
           document.getElementById("prostor").remove();

           let table=document.createElement("table");
           table.id="prostor";
        
           let CSVstring="";
           let tr=document.createElement("tr");
           let thead=document.createElement("thead");
            let lenght=Object.keys(data[0]).length;
           Object.keys(data[0]).forEach((key,index)=>{
              let th=document.createElement("th");
              th.innerText=key;
              tr.appendChild(th);
              CSVstring+=key;
              CSVstring+=(index==(lenght-1)?"\n":",");
           });
          
           thead.appendChild(tr);

           let tbody=document.createElement("tbody");
           data.forEach(o=>{
                let tr=document.createElement("tr");
                let lenght1=Object.values(o).length;
                Object.values(o).forEach((v,index)=>{
                    let td=document.createElement("td");
                    td.innerText=v;
                    tr.appendChild(td);
                    CSVstring+=v;
                    CSVstring+=(index==(lenght1-1)?"\n":",");
                });
                tbody.appendChild(tr);
           });

           table.appendChild(thead);
           table.appendChild(tbody);
           


           document.getElementById("linkovi").remove();
           let div=document.createElement("div");
           div.id="linkovi";
           div.class="linkovi";
           //JSON
           let JSONlink=document.createElement("a");
           JSONlink.innerText="Preuzmi podatke u JSON formatu";
           JSONlink.href="#";
           JSONlink.addEventListener("click",function() {
              console.log("Uso sam");
              const json = JSON.stringify(data, null, 2);
              const b = new Blob([json], { type: 'application/json' });
              const url = URL.createObjectURL(b);
              JSONlink.href = url;
              JSONlink.download = 'bundesliga.json';
           });

           //CSV
           let CSVlink=document.createElement("a");
           CSVlink.innerText="Preuzmi podatke u CSV formatu";
           CSVlink.href="#";
           CSVlink.addEventListener("click",function() {
            const b = new Blob([CSVstring], { type: 'text/csv' });
            const url = URL.createObjectURL(b);
            CSVlink.href = url;
            CSVlink.download = 'bundesliga.csv';
         });

           div.appendChild(JSONlink);
            div.appendChild(document.createElement("br"));
           div.appendChild(CSVlink);

           document.body.appendChild(div);
           document.body.appendChild(table);
          
           console.log(CSVstring);
        })
        .catch(error => {
          console.error('Error:', error);
        });
    
}
function promjeni(){
    let tablica=document.getElementById("tablica").value;
    let array=[];
    let atribut=document.getElementById("atribut")
    atribut.options.length = 0;
    if(tablica=="stadioni"){
        array=[
            "naziv",      
            "kapacitet",
            "godina_osnutka"
          ];
    }
    else if(tablica=="klubovi"){
        array= [
            "naziv",                        
            "grad",
            "godina_osnutka",
            "stadion",                      
            "trener",                       
            "broj_trofeja_ligi",
            "broj_igraca",
            "prosjek_godina",
            "broj_bodova_prosle_godine",
            "broj_ligi_prvaka",
            "broj_kupova",
            "TM_vrijednost"
          ];
    }
    else if(tablica=="treneri"){
        array=[
            "id",         
            "ime",
            "prezime"
          ];
    }
    else if(tablica=="igraci"){
        array=[
            "id",         
            "ime",
            "prezime",
            "pozicija",
            "klub"        
          ];
    }
    let option=document.createElement("option");
    option.value="sva";
    option.innerText="Sva polja(wildcard)";
    atribut.appendChild(option);
    array.forEach(el=>{
        let option=document.createElement("option");
        option.value=el;
        option.innerText=el;
        atribut.appendChild(option);
    });
}
document.getElementById("prostor").style.display="none";

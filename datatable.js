import React, { useState,useEffect,useRef } from "react";



function Datatable(){
    const [tablica, setTablica] = useState("klubovi");
    const [stupac, setStupac] = useState("naziv");
    const [vrijednost, setVrijednost] = useState("");
    const [data,setData]=useState(null);
    let variable="klubovi";
    const hSubmit = async (event) => {
        event.preventDefault();        
        let url=`http://localhost:8080/${tablica}`;
        console.log(url);
        try {
          const response = await fetch(url);
          if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
          }
          const result = await response.json();
          setData(result);
          console.log(data);
        } catch (err) {
          console.log(err.message);

      }
    }
    const schange=(event)=>{
      variable=event.target.value;
      setTablica(event.target.value);
    }
    
    
    const atribut = new Map([ ["sve", [
      "k_naziv",
            "k_grad",
            "k_godina_osnutka",
            "k_stadion",
            "k_trener",
            "k_broj_trofeja_ligi",
            "k_broj_igraca",
            "k_prosjek_godina",
            "k_broj_bodova_prosle_godine",
            "k_broj_ligi_prvaka",
            "k_broj_kupova",
            "k_tm_vrijednost",

            "t_id",
            "t_ime",
            "t_prezime",

            "i_id",
            "i_ime",
            "i_prezime",
            "i_pozicija",
            "i_klub",

            "s_naziv",
            "s_kapacitet",
            "s_godina_osnutka"
    ]],
        ["klubovi", [
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
            "tm_vrijednost"
          ]],
        ["treneri", [
            "id",
            "ime",
            "prezime"
          ]],
        ["igraci", [
            "id",
            "ime",
            "prezime",
            "pozicija",
            "klub"
          ]
        ],
        ["stadioni", [
            "naziv",
            "kapacitet",
            "godina_osnutka"
          ]]
    ]);
    return (
      <div>
        <form >
        <h3>Odaberi tablicu</h3>
        <select value={tablica} onChange={schange} id="tablica">
                <option value="sve">Sve</option>
                <option value="klubovi">Klub</option>
                <option value="treneri">Trener</option>
                <option value="igraci">Igrac</option>
                <option value="stadioni">Stadion</option>
        </select>
        <h3>Odaberi stupac</h3>
        <select value={stupac} onChange={(event)=>setStupac(event.target.value)}>
                {atribut.get(tablica)?.map((value)=>{
                
                    return (
                        <option key={value} value={value}>
                        {value}
                        </option>
                    );
                    
                })}
        </select>
        <h3>Upisi vrijednost</h3>
        <input type="text" value={vrijednost} onChange={(event)=>setVrijednost(event.target.value)}/>
        <h3>{vrijednost}</h3>
        <button type="submit" onClick={hSubmit}>Sumbit</button>
        </form>
        {data!=null?(
        <table>
                <thead>
                  <tr>
                  {
                    Object.keys(data[0])[0] === "igrac" ?(
                      Object.keys(data[0]).map((key)=>{
                        return(
                          Object.keys(data[0][key]).map((v,idx)=>{
                            return(
                              <th key={idx}>{key+"_"+v}</th>
                            );

                          }
                          )
                        );

                      }

                      )
                    ):(Object.keys(data[0]).map((k,idx)=>{
                      return(
                        <th key={idx}>{k}</th>
                      );
                    }

                    ))
                    
                  }
                  </tr>
                </thead>
                <tbody>
                  {console.log(data)}
                {
                  
                data?.map((o,idx)=>{
                  if(Object.keys(o)[0]=="igrac"){
                      return(
                        <tr key={idx}>
                          {
                            Object.values(o).map((o1)=>{
                              
          

                                return(
                                  Object.values(o1).map((v,idx)=>{
                                      return(
                                        <td key={idx}>
                                          {v}
                                        </td>
                                      );
                                  })
                                
                                )


                                
                                }


                                )
                                  
                            
                          }
                        </tr>
                      );
                  }
                  else{
                    return(
                      <tr key={idx}>
                        {Object.values(o).map((v,idx)=>{

                          return (
                            <td key={idx}>{v}</td>
                          );

                      })}
                      </tr>
                    );
                }
                })}
                </tbody>
        </table>):(<></>)
        } 
        </div>
    );
    
}
export default Datatable;
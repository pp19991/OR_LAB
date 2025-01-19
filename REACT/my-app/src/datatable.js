import React, { useState,useEffect,useRef } from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash,faEdit, faTabletButton } from '@fortawesome/free-solid-svg-icons';


function Datatable(){
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
      
        ["igraci", [
          "id",
          "ime",
          "prezime",
          "pozicija",
          "klub"
        ]
      ],
      ["treneri", [
          "id",
          "ime",
          "prezime"
        ]],
      
      ["stadioni", [
          "naziv",
          "kapacitet",
          "godina_osnutka"
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
        ]]
  ]);
  
    const [tablica, setTablica] = useState("klubovi");
    const [stupac, setStupac] = useState("naziv");
    const [vrijednost, setVrijednost] = useState("");
    const [data,setData]=useState(null);
    const [fvrijednost,setFVrijednost]=useState("");
    const [uredivanje,setUredivanje]=useState(false);
    const [dodavanje,setDodavanje]=useState(false);
    const[id_uredivanje,setIDU]=useState(-1000);
    const[dataobject,setDO]=useState();
    const[addObject,setAO]=useState();
    let variable="klubovi";
  
    
    const hSubmit = async (event) => {
        event.preventDefault();        
        let url=`http://localhost:8080/${tablica}`;
        setFVrijednost(tablica);
        if(vrijednost!=""){
          if(tablica=="igraci" || tablica=="treneri" || tablica=="sve"){
              url=url+"?id="+vrijednost;
          }
          else{
            url=url+"?naziv="+vrijednost;
          }
        }
       
        try {
          const response = await fetch(url, {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
            },
            credentials: "include", // Include cookies or authorization headers if needed
          })
          if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
          }
          const result = await response.json();
          setData(result);
          console.log(result);
        } catch (err) {
          console.log(err.message);

      }
    }

    const addPromjeni=(event,k)=>{
      setAO((prev) => ({ ...prev, [k]: event.target.value }));
    }

    const addPromjeni1 = (event, k, k1) => {
      const copy = { ...addObject };
      copy[k][k1]=event.target.value;
      setAO(copy)
    }
    const schange=(event)=>{
      variable=event.target.value;
      setTablica(event.target.value);
      setStupac(atribut.get(event.target.value)[0]);
    }

    const uredi=(id,idx)=>{
      setUredivanje(!uredivanje);
      console.log(idx);
      console.log(data[idx]);
      setIDU(idx);
      setDO(data[idx]);
      console.log(data[idx]);
    }


    const ppromjeni=(event,key)=>{
      console.log(key);
      setDO((prev) => ({ ...prev, [key]: event.target.value }));
      console.log(dataobject);
    }


    const ppromjeni1=(event,key,key1)=>{
      const copy = { ...dataobject };
      copy[key][key1]=event.target.value;
      setDO(copy);
    }


    const Addrow=()=>{
      if(fvrijednost!=""){
        setDodavanje(true);
        if(fvrijednost=="sve"){
          const pomObject={}
          console.log(atribut)
          atribut.forEach((v,k)=>{
            console.log("k="+k+",v="+v)
            const pomObject1={}
            if(k!="sve"){
                v.forEach((v1)=>{
                  console.log("v1="+v1)
                  pomObject1[v1]=""
              })
              console.log(pomObject1)
              console.log(k)
              if(k=="klubovi"){
                k="klub"
              }
              else if(k=="stadioni"){
                k="stadion"
              }
              else if(k=="treneri"){
                k="trener"
              }
              else if(k=="igraci"){
                k="igrac"
              }
              pomObject[k]=pomObject1;
            }
            setAO(pomObject)
          })
        }
        else{
          const pomObject={}
          atribut.get(fvrijednost).forEach((v)=>{
                pomObject[v]=""
          })
        setAO(pomObject);
        }
        console.log(addObject)
      }
    }

    const brisi=async (id)=>{
      
      let url=`http://localhost:8080/${fvrijednost}`;
      if(tablica=="igraci" || tablica=="treneri" || tablica=="sve"){
              url=url+"?id="+id;
      }
      else{
            url=url+"?naziv="+id;
      }
      
       try {
        const response = await fetch(url,{
          method: 'DELETE',
          credentials: "include"
        });
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        setData((fvrijednost=="sve")?
        (data.filter(o=>o["igrac"]["id"]!=id)):
        (data.filter(o=>o[atribut.get(fvrijednost)[0]]!=id)))
        
      } catch (err) {
        console.log(err.message);
      }
    }



    const editSave=async ()=>{
      let url=`http://localhost:8080/${fvrijednost}`;
      try {
        const response = await fetch(url,{
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
        },
        credentials: "include",
        body: JSON.stringify(dataobject)

        });
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        setUredivanje(false);
        const copied = [...data];
        copied[id_uredivanje]=dataobject
        setData(copied)

      } catch (err) {
        console.log(err.message);
      }
    }
    const addSave=async ()=>{
      console.log(addObject)
      let url=`http://localhost:8080/${fvrijednost}`;
      try {
        const response = await fetch(url,{
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
        },
        credentials: "include", 
        body: JSON.stringify(addObject)

        });
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        setDodavanje(false);
        setData([...data, addObject]);
      } catch (err) {
        console.log(err.message);
      }
    }
      
    

    
   
    return (
      <div>
        <div className="divForma">
          <form >
          <h3>Odaberi tablicu</h3>
          <select className="select" value={tablica} onChange={schange} id="tablica">
                  <option value="sve">Sve</option>
                  <option value="klubovi">Klub</option>
                  <option value="treneri">Trener</option>
                  <option value="igraci">Igrac</option>
                  <option value="stadioni">Stadion</option>
          </select>
          <h3>Odaberi stupac</h3>
          <select className="select" value={stupac} onChange={(event)=>setStupac(event.target.value)}>
                  {atribut.get(tablica)?.map((value)=>{
                  
                      return (
                          <option key={value} value={value}>
                          {value}
                          </option>
                      );
                      
                  })}
          </select>
          <h3>Upisi vrijednost</h3>
          <input type="text" className="input" value={vrijednost} onChange={(event)=>setVrijednost(event.target.value)}/>
          <h3>{vrijednost}</h3>
          <button className="button" type="submit" onClick={hSubmit}>Sumbit</button>
          <button className="button" type="button" onClick={Addrow}>Add</button>

          </form>
        </div>
        {(data!=null && data && data.length>0)?(
        <div className="divt">
          
        <table>
                <thead>
                  <tr>
                  <td>Uredivanje</td>
                  <td>Brisanje</td>
                  {
                    console.log(data.length)
                  }
                  {
                    atribut.get(fvrijednost).map((v,idx)=>{
                      return(
                        <td key={idx}>{v}</td>
                      )
                    })
                  }
                  </tr>
                </thead>
                <tbody>
                 
                {
                  
                data?.sort((a, b) => ((fvrijednost=="klubovi" || fvrijednost=="stadioni") ? ((a.naziv?.toLowerCase() < b.naziv?.toLowerCase() ? -1 : 1)):(fvrijednost=="sve"?(a["igrac"]?.id < b["igrac"]?.id ? -1 : 1):(a.id < b.id ? -1 : 1)))).map((o,idx)=>{
                  if(Object.keys(o)[0]=="igrac"){
                      return(
                        <tr key={idx}>
                          <td><button  onClick={()=>uredi(o["igrac"]["id"],idx)}><FontAwesomeIcon  icon={faEdit} /></button></td>
                          <td><button  onClick={()=>brisi(o["igrac"]["id"])}><FontAwesomeIcon  icon={faTrash} style={{ color: 'red' }} /></button></td>
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
                          
                          <td><button  onClick={()=>uredi(Object.values(o)[0],idx)}><FontAwesomeIcon  icon={faEdit} /></button></td>
                          <td><button  onClick={()=>brisi(Object.values(o)[0])}><FontAwesomeIcon  icon={faTrash} style={{ color: 'red' }} /></button></td>
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
        </table></div>):(<h2 className="rez">Nema rezultata</h2>)
        }
        {uredivanje && (
        <div className="modal">
          <div className="modal-content">
            <h1>Edit</h1>
            <div className="div">
            {(fvrijednost=="sve"?Object.keys(dataobject).map((k)=>{
                return(
                  <div key={k} className="divsve">
                    <h2>{k}</h2>
                    {Object.keys(dataobject[k]).map((k1)=>{
                          return(
                            <label key={k1} className="label">{k1}: {(["id","naziv"].includes(k1)? <input type="text" value={dataobject[k][k1]} readOnly/> :<input type="text" value={dataobject[k][k1]} onChange={(event)=>ppromjeni1(event,k,k1)} 
                          />)}</label>
                          )
                    })}
                  </div>
                )
              }):Object.keys(dataobject).map((k)=>{
                return(
                  <label key={k} className="label">{k}: {(["id","naziv"].includes(k)? <input type="text" value={dataobject[k]} readOnly/> :<input type="text" value={dataobject[k]} onChange={(event)=>ppromjeni(event,k)} 
                  />)}</label>
                )
              })) 
              
            }
            </div>
            <button onClick={editSave} className="button1">Save</button>
            <button onClick={() => setUredivanje(false)} className="button1">Cancel</button>
          </div>
        </div>
      )} 
      {dodavanje && (
        <div className="modal">
          <div className="modal-content">
            <h1>Add</h1>
            <div className="divadd">
            {fvrijednost!="sve" && atribut.get(fvrijednost).map((k=>{
              return(
                <label key={k} className="label">{k}: <input type="text" value={addObject[k]} onChange={(event)=>addPromjeni(event,k)}/></label>
              )
            }))}
            {
              fvrijednost=="sve" && Object.keys(addObject).map((k,idx)=>{
                return(
                    <div key={idx} className="divsve">
                      <h3>{k}</h3>
                      {
                        Object.keys(addObject[k]).map((k1)=>{
                          return(
                            <label key={k1} className="label">{k1}: <input type="text" value={addObject[k][k1]} onChange={(event)=>addPromjeni1(event,k,k1)}/></label>
                          )
                          
                        })
                      }
                    </div>
                )
                
              })
            }
            <button onClick={addSave} className="button1">Add</button>
            <button onClick={() => setDodavanje(false)} className="button1">Cancel</button>
            </div>
          </div> 
          </div>
      )}
        </div>
    );
    
}

export default Datatable;

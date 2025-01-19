import { BrowserRouter, Routes, Route } from "react-router-dom";
import Index1 from "./index1.js";
import Datatable from "./datatable.js";
import './datatable.css';
import Pocetna from "./pocetna.js";
import Info from "./info.js";
import Error from "./error.js";


import React, { useState,useEffect,useRef } from "react";
function App(){
    const [authIs, setAuthIs] = useState(false);
    useEffect(() => {
        const fetchAuth = async () => {
          try {
            const response = await fetch('http://localhost:8080/loggedIn', {
              credentials: 'include', 
            });
    
            if (!response.ok) {
              throw new Error('Failed to fetch user');
            }
    
            const b = await response.json();
            setAuthIs(b)
            
          } catch (error) {
            console.error('Error fetching user:', error);
           
          } 
        };
    
        fetchAuth();
      },[])
    return (
    <BrowserRouter>
        <Routes>
            <Route path="/index" element={<Index1/>} />
            <Route path="/error" element={<Error/>}/>
            <Route path="/datatable" element={<Datatable />} />
            <Route path="*" element={<h1>404 - Page Not Found</h1>} />
            <Route path="/" element={ <Pocetna />
                
                } />
            <Route path="/info" element={
                
                    <Info />
                    }
                 />
        </Routes>
    </BrowserRouter>

    );
}
export default App;
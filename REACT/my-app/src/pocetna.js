
import React, { useState,useEffect,useRef } from "react";

function Pocetna(){
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
  const login = () => {
    window.location.href = 'http://localhost:8080/login';
  };

  const logout = () => {
    window.location.href = 'http://localhost:8080/logout';
  };  
  const osvjezi= async() => {
    
    try {
      const response = await fetch("http://localhost:8080/osvjezi", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include", 
      })
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
    } catch (err) {
      console.log(err.message);

  }
  };
    return (
      <div className="divgla">
            <div className="glavni1">
           
            {!authIs && <button className="button2" onClick={login}>Login with Auth0</button>}
            {authIs && <button className="button2" onClick={logout}>LogOut with Auth0</button>}
            {authIs && <button className="button2" type="button" onClick={osvjezi}>Osvjezi preslike</button>}
                
                
            {authIs && <a href="info">Informacije o korisniku</a>}
                
            
            <a href="klubovi_bundeslige.json" download="klubovi_bundeslige.json"> Preuzmi podatke u JSON formatu</a>
            <a href="klubovi_bundeslige.csv">Preuzmi podatke u CSV formatu</a>
            <a href="datatable">Odi na stranicu datatable.html</a>
        </div>
        </div>
    );
    
}
export default Pocetna;
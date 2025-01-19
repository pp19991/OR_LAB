import React, { useState,useEffect,useRef } from "react";

function Info(){
  const [userInfo, setUserInfo] = useState({});
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
                if(b==false){
                    setAuthIs(false);
                    window.location.href = "http://localhost:3000/error";
                }
                setAuthIs(true)
              } catch (error) {
                console.error('Error fetching user:', error);
               
              } 
            };
    const fetchInfo = async () => {
      try {
        const response = await fetch('http://localhost:8080/user-info', {
          credentials: 'include', 
        });

        if (!response.ok) {
          throw new Error('Failed to fetch user');
        }

        const u = await response.json();
        setUserInfo(u);
        console.log(u)
      } catch (error) {
        console.error('Error fetching user:', error);
       
      } 
    };
    fetchAuth();
    fetchInfo();
  },[])
  
  return (
    authIs && (
        <div>
            <h1>Informacije o korisniku</h1>
            <ol>
                {userInfo && 
                    Object.keys(userInfo).map((key) => (
                        <li key={key}>
                            {key}: {userInfo[key]}
                        </li>
                    ))
                }
            </ol>
        </div>
    )
);

    
}
export default Info;
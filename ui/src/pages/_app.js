//import '@/styles/globals.css'

import { NavigationBar } from "@/components/navigationBar";
import { LoginContext } from "@/contexts/loginContext";
import { useEffect, useMemo, useState } from "react";

export default function App({ Component, pageProps }) {
  const [loginState, setLoginState] = useState(null);

  useEffect(() => {
    const authToken = sessionStorage.getItem('auth-token');
    const userDetails = sessionStorage.getItem('userDetails');
    if (authToken && userDetails) {
      setLoginState({
        authToken,
        ...(JSON.parse(userDetails)),
      })
    }
  },[]);

  const memorizedLoginState = useMemo(() => {
    return {
      loginState, setLoginState
    }
  }, [loginState]);

  return (
    <>
     <LoginContext.Provider value={{loginState, setLoginState}}>
       <NavigationBar />
       <Component {...pageProps} />
     </LoginContext.Provider>
    </>
  )
}

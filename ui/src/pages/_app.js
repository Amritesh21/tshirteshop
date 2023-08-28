//import '@/styles/globals.css'

import { FooterComponent } from "@/components/footer";
import { NavigationBar } from "@/components/navigationBar";
import { LoginContext } from "@/contexts/loginContext";
import { useEffect, useMemo, useState } from "react";

export default function App({ Component, pageProps }) {
  const [loginState, setLoginState] = useState(null);
  const [cartProductCount, setCartProductCount] = useState(0);

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
      loginState, setLoginState,
      cartProductCount, setCartProductCount
    }
  }, [loginState, cartProductCount]);

  return (
    <>
     <LoginContext.Provider value={{loginState, setLoginState, cartProductCount, setCartProductCount}}>
       <NavigationBar />
       <Component {...pageProps} />
       <FooterComponent />
     </LoginContext.Provider>
    </>
  )
}

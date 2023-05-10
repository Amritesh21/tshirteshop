//import '@/styles/globals.css'

import { NavigationBar } from "@/components/navigationBar";
import { LoginContext } from "@/contexts/loginContext";
import { useMemo, useState } from "react";

export default function App({ Component, pageProps }) {
  const [loginState, setLoginState] = useState(null);

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

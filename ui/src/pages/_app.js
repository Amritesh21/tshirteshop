//import '@/styles/globals.css'

import { NavigationBar } from "@/components/navigationBar";

export default function App({ Component, pageProps }) {
  return (
    <>
     <NavigationBar />
     <Component {...pageProps} />
    </>
  )
}

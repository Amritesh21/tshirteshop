
import { Box } from "@mui/material"
import Image from "next/image"
import { BrandIcon } from "./brandIcon"
import { NavBarMenu } from "./navBarMenu"
import { SearchBar } from "./searchBar"
import { UserDetailsField } from "./userDetailsField"
import { useContext, useEffect, useState } from "react"
import axios from "axios"
import { LoginContext } from "@/contexts/loginContext"
import { authFetcher } from "@/utilities/baseFetcher"
import { menuConstants } from "@/constants/menuConstants"

export const NavigationBar = () => {

  const {loginState, cartProductCount, setCartProductCount} = useContext(LoginContext);

  useEffect(() => {
    if (!loginState || loginState.userType !== menuConstants.buyer) { return; }
    authFetcher("api/auth/buyer/get/cart/products")
    .then((response) => response.json())
    .then((response) => { setCartProductCount(response?.length ?? 0); });
  }, [loginState]);

    return (
        <Box
          sx={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "space-between",
            height: "80px"
          }}
        >
            <BrandIcon />
            <NavBarMenu />
            <Box 
              sx={{
                display: "flex",
                flexDireaction: "row",
                height: "48px",
                alignSelf: "center",
              }}
            >
                <UserDetailsField cartOrderMeta={cartProductCount} />
                {/* <SearchBar /> */}
            </Box>
        </Box>
    )
}
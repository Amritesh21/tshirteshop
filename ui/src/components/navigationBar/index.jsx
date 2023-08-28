
import { Box } from "@mui/material"
import Image from "next/image"
import { BrandIcon } from "./brandIcon"
import { NavBarMenu } from "./navBarMenu"
import { SearchBar } from "./searchBar"
import { UserDetailsField } from "./userDetailsField"
import { useContext, useEffect, useState } from "react"
import axios from "axios"
import { LoginContext } from "@/contexts/loginContext"

export const NavigationBar = () => {

  const {loginState, cartProductCount, setCartProductCount} = useContext(LoginContext);

  useEffect(() => {
    axios.get("http://localhost/api/auth/buyer/get/cart/products", {
            headers: {
                "Auth-Token": loginState?.authToken
            }
        }).then((response) => { setCartProductCount(response.data?.length ?? 0); });
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
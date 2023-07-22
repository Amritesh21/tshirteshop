
import { Box } from "@mui/material"
import Image from "next/image"
import { BrandIcon } from "./brandIcon"
import { NavBarMenu } from "./navBarMenu"
import { SearchBar } from "./searchBar"
import { UserDetailsField } from "./userDetailsField"

export const NavigationBar = () => {
    return (
        <Box
          sx={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "space-around",
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
                <UserDetailsField />
                <SearchBar />
            </Box>
        </Box>
    )
}
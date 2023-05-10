import { Box, IconButton } from "@mui/material"
import ShoppingBagOutlinedIcon from '@mui/icons-material/ShoppingBagOutlined';
import FavoriteBorderOutlinedIcon from '@mui/icons-material/FavoriteBorderOutlined';
import PersonOutlinedIcon from '@mui/icons-material/PersonOutlined';
import { useState } from "react";
import { UserLoginManager } from "./userLoginManager";

export const UserDetailsField = () => {
    const [openUserLoginManager, setOpenUserLoginManager] = useState(null); 
    return (
        <>
        <Box
          sx={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "space-evenly"
          }}
        >
            <IconButton>
                <ShoppingBagOutlinedIcon />
            </IconButton>
            <IconButton>
                <FavoriteBorderOutlinedIcon />
            </IconButton>
            <IconButton onClick={(event) => setOpenUserLoginManager(event.currentTarget)}>
                <PersonOutlinedIcon />
            </IconButton>
        </Box>
        <UserLoginManager
          openUserLoginManager={openUserLoginManager}
          setOpenUserLoginManager={setOpenUserLoginManager}
         />
        </>
    )
}
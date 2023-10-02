import { Badge, Box, IconButton } from "@mui/material"
import ShoppingBagOutlinedIcon from '@mui/icons-material/ShoppingBagOutlined';
import FavoriteBorderOutlinedIcon from '@mui/icons-material/FavoriteBorderOutlined';
import PersonOutlinedIcon from '@mui/icons-material/PersonOutlined';
import { useContext, useState } from "react";
import { UserLoginManager } from "./userLoginManager";
import { UserProfileManager } from "./userProfileDrawer";
import { useRouter } from "next/router";
import { LoginContext } from "@/contexts/loginContext";
import { menuConstants } from "@/constants/menuConstants";

export const UserDetailsField = ({cartOrderMeta}) => {
    const [openUserLoginManager, setOpenUserLoginManager] = useState(null); 
    const {loginState} = useContext(LoginContext);
    const router = useRouter();
    return (
        <>
        <Box
          sx={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "space-evenly"
          }}
        >
            {loginState?.userType !== menuConstants.seller && <IconButton onClick={() => {router.push("/orderCart")}}>
                <Badge badgeContent={cartOrderMeta} color="error">
                  <ShoppingBagOutlinedIcon />
                </Badge>
            </IconButton>}
            {/* <IconButton>
                <FavoriteBorderOutlinedIcon />
            </IconButton> */}
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
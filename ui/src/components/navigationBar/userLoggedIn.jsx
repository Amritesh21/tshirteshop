import { Avatar, List, ListItemButton, ListItemIcon, ListItemText } from "@mui/material"
import PermIdentityIcon from '@mui/icons-material/PermIdentity';
import LogoutIcon from '@mui/icons-material/Logout';
import { useContext } from "react";
import { LoginContext } from "@/contexts/loginContext";

export const UserLoggedIn = () => {
    const {setLoginState} = useContext(LoginContext);
    const handleLogout = () => {
        setLoginState(null);
        sessionStorage.removeItem('auth-token');
        sessionStorage.removeItem('userDetails');
    }
    return(
        <List sx={{alignContent: "center", display: "flex", flexDirection: "column"}}>
            <Avatar sx={{
                bgcolor: "blue",
                height: "100px",
                width: "100px",
                fontSize: "45px",
                alignSelf: "center"
            }}>
                {{firstName: "hello"}.firstName.toUpperCase().slice(0,2)}
            </Avatar>
            <ListItemButton sx={{mt: "10px"}}>
                <ListItemIcon><PermIdentityIcon /></ListItemIcon>
                <ListItemText>My Profile</ListItemText>
            </ListItemButton>
            <ListItemButton
              sx={{mt: "10px"}}
              onClick={() => handleLogout()}
            >
                <ListItemIcon><LogoutIcon/></ListItemIcon>
                <ListItemText>Logout</ListItemText>
            </ListItemButton>
        </List>
    )
}
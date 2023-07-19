import { LoginContext } from "@/contexts/loginContext"
import { Button, Popover } from "@mui/material"
import { useContext, useState } from "react"
import { UserNotLoggedIn } from "./userNotLoggedIn";
import { UserLoggedIn } from "./userLoggedIn";
import { UserProfileManager } from "./userProfileDrawer";

export const UserLoginManager = ({openUserLoginManager, setOpenUserLoginManager}) => {
    const {loginState} = useContext(LoginContext);
    const [openProfileDrawer, setOpenProfileDrawer] = useState(null);
    return (
      <>
        <Popover
          open={Boolean(openUserLoginManager)}
          anchorEl={openUserLoginManager}
          onClose={() => setOpenUserLoginManager(null)}
          anchorOrigin={{
            vertical: 'bottom',
            horizontal: 'left',
          }}
        >
            {!loginState && <UserNotLoggedIn setOpenUserLoginManager={setOpenUserLoginManager} />}
            {loginState && <UserLoggedIn setOpenUserLoginManager={setOpenUserLoginManager} setOpenProfileDrawer={setOpenProfileDrawer} />}
        </Popover>
        <UserProfileManager openProfileDrawer={openProfileDrawer} setOpenProfileDrawer={setOpenProfileDrawer} />
      </>
    )
}
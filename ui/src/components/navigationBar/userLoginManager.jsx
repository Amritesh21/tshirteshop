import { LoginContext } from "@/contexts/loginContext"
import { Button, Popover } from "@mui/material"
import { useContext } from "react"
import { UserNotLoggedIn } from "./userNotLoggedIn";

export const UserLoginManager = ({openUserLoginManager, setOpenUserLoginManager}) => {
    const {loginState} = useContext(LoginContext);
    return (
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
        </Popover>
    )
}
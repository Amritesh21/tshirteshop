import { Box, Button } from '@mui/material';
import PersonOutlinedIcon from '@mui/icons-material/PersonOutlined';
import LoginIcon from '@mui/icons-material/Login';
import { useRouter } from 'next/router';

export const UserNotLoggedIn = ({setOpenUserLoginManager}) => {
    const router = useRouter();
    const onLoginClick = () => {
        setOpenUserLoginManager(false);
        router.push("/login")
    }
    return (
        <Box sx={{
            width: "250px",
            height: "200px",
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
         }}
        >
            <PersonOutlinedIcon sx={{fontSize: "100px", alignSelf: "center"}} />\
            <Button
              startIcon={<LoginIcon />}
              variant="outlined"
              sx={{
                width: "150px",
                alignSelf: "center",
              }}
              onClick={() => onLoginClick()}
            >
                Login
            </Button>
        </Box>
    )
}
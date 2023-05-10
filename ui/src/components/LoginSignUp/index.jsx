import { Box, Button, Grid, TextField, Typography } from "@mui/material"
import GoogleIcon from '@mui/icons-material/Google';
import FacebookIcon from '@mui/icons-material/Facebook';
import { SignUpComponent } from "./signUp"
import { LoginComponent } from "./login"
import { Router, useRouter } from "next/router";


export const LoginOrSignUp = (props) => {
    const {isSignUp, isLogin} = props;
    const router = useRouter();
    return(<Box sx={{
        backgroundImage: `url(http://localhost:3000/pictures/loginBackground.png)`,
        backgroundImage: `linear-gradient(90deg, rgba(107, 120, 255, 0.99) 0%, #8609E9 48.55%, rgba(134, 9, 233, 0) 97.4%), url(http://localhost:3000/pictures/loginBackground.png)`,
        backgroundRepeat: 'no-repeat',
        backgroundSize: 'cover',
        height: "100%",
        width: "100%",
    }}>
        <Box sx={{
            padding: "120px"
        }}>
            <Typography sx={{color: "white", wordSpacing: "5px"}}>
                Home / My Account
            </Typography>
            <Typography variant="h4" sx={{fontWeight: 700, color: "white", mt: "24px", mb: "45px"}}>
                My Account
            </Typography>
            <Typography variant="h4" sx={{fontWeight: 700, color: "white", mt: "24px", mb: "45px"}}>
                Sign Up
            </Typography>
            <Typography sx={{fontWeight: 400, color: "white", mt: "24px", mb: "45px"}}>
                You {isSignUp ? "Login": "Sign Up" } have account ? <Button sx={{color: "white", textTransform: "capitalize", px: "0px", justifyContent: "flex-start", fontWeight: 700}} onClick={() => router.push(isLogin ? "/signUp" : "/login")}>{isSignUp ? "Login": "Sign Up" }</Button>
            </Typography>
            <Box sx={{maxWidth: isSignUp ? "60%": "50%"}}>
            <Grid container spacing={4}>
            <Grid item xs={4}>
                <Button
                    sx={{ backgroundColor: "white", color: "black", textTransform: "capitalize", px: "0px", fontWeight: 700, width: "180px" }}
                    variant="contained"
                    startIcon={<GoogleIcon />}
                >
                    Google
                </Button>
            </Grid>
            <Grid item xs={4}>
                <Button
                    sx={{ backgroundColor: "white", color: "black", textTransform: "capitalize", px: "0px", fontWeight: 700, width: "180px" }}
                    variant="contained"
                    startIcon={<FacebookIcon />}
                >
                    Facebook
                </Button>
            </Grid>
            </Grid>
            {isSignUp && <SignUpComponent />}
            {isLogin && <LoginComponent />}
            </Box>
        </Box>
    </Box>)
}
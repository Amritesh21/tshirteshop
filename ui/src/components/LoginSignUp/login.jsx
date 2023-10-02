import { Grid, TextField } from "@mui/material"
import { LoginSignUpButton } from "./loginSignUpBtn";
import { useContext, useEffect, useState } from "react";
import { textFieldStyle } from "./styleConstants";
import axios from "axios";
import { useRouter } from "next/router";
import { LoginContext } from "@/contexts/loginContext";
import { publicFetcher } from "@/utilities/baseFetcher";
import { UserDetailsClass } from "@/utilities/UserDetailsClass";

export const LoginComponent = () => {

    const router = useRouter();
    const {setLoginState} = useContext(LoginContext);

    const [email, setEmail] = useState("");
    const [emailError, setEmailError] = useState(false);
    const [password, setPassword] = useState("");
    const [passwordError, setPasswordError] = useState(false);
    const [payLoad, setPayload] = useState(null);

    useEffect(() => {
        if (!payLoad?.username || !payLoad?.password) { return; }
        const userLoginObj = new UserDetailsClass();
        publicFetcher("api/public/user/login", {
            method: "POST",
            body: JSON.stringify(payLoad)
        }).then((response) => {
            console.log(response.headers.get('Auth-Token'));
            userLoginObj.setAuthToken(response.headers.get('Auth-Token'));
            sessionStorage.setItem("auth-token", response.headers.get('Auth-Token'));
            return response.json();
        }).then((response) => {
            console.log(response)
            if (response.logInMessage === 'Authentication successful') {
                userLoginObj.setFirstName(response.firstName);
                userLoginObj.setLastName(response.lastName);
                userLoginObj.setUserName(response.username);
                userLoginObj.setUserType(response.userType);
                setLoginState(userLoginObj.getUserLoginObj());
                sessionStorage.setItem("userDetails", JSON.stringify(userLoginObj.getUserLoginObj()))
                router.push("/");
            } else {
                alert("Invalied credentials error");
            }
        })
    }, [payLoad]);

    const onClickLogin = () => {
        let hasError = false;
        if (!email.trim()) {
            setEmailError(true)
            hasError = true;
        } if (!password.trim()) {
            setPasswordError(true);
            hasError = true;
        } if (!hasError) {
            setEmailError(false);
            setPasswordError(false);
            setPayload({
                username: email,
                password
            })
        }
    }


    return (
        <Grid container spacing={4} sx={{ pt: "40px" }}>
            <Grid item xs={12}>
                <TextField 
                  placeholder="Email"
                  value={email}
                  onChange={(event) => setEmail(event.target.value)}
                  error={emailError}
                  sx={textFieldStyle}
                  required
                  helperText={ emailError && "Enter Email"}
                  size="small"
                />
            </Grid>
            <Grid item xs={12}>
                <TextField 
                  placeholder="password"
                  value={password}
                  onChange={(event) => setPassword(event.target.value)}
                  error={passwordError}
                  sx={textFieldStyle}
                  type="password"
                  required
                  helperText={passwordError && "Enter Password"}
                  size="small"
                />
            </Grid>
            <LoginSignUpButton handleOnClick={onClickLogin} btnText={"Login"}/>
        </Grid>
    )
}
import { Grid, TextField } from "@mui/material"
import { LoginSignUpButton } from "./loginSignUpBtn";
import { useContext, useEffect, useState } from "react";
import { textFieldStyle } from "./styleConstants";
import axios from "axios";
import { useRouter } from "next/router";
import { LoginContext } from "@/contexts/loginContext";

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
        axios.post("api/public/user/login", payLoad).then((response) => {
            if (response.data.logInMessage === 'Authentication successful') {
                setLoginState({
                    firstName: response.data.firstName,
                    lastName: response.data.lastName,
                    username: response.data.username,
                    authToken: response.headers[`auth-token`],
                });
                sessionStorage.setItem("auth-token", response.headers[`auth-token`]);
                sessionStorage.setItem("userDetails", JSON.stringify(response.data))
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
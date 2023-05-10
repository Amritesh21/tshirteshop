import { Grid, TextField } from "@mui/material"
import { LoginSignUpButton } from "./loginSignUpBtn";
import { useState } from "react";
import { textFieldStyle } from "./styleConstants";

export const LoginComponent = () => {

    const [email, setEmail] = useState("");
    const [emailError, setEmailError] = useState(false);
    const [password, setPassword] = useState("");
    const [passwordError, setPasswordError] = useState(false);
    const [payLoad, setPayload] = useState(null);

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
                firstName,
                lastName,
                email,
                password
            })
        }
    }


    return (
        <Grid container spacing={4} sx={{ pt: "40px" }}>
            <Grid item xs={12}>
                <TextField 
                  label="Email"
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
                  label="password"
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
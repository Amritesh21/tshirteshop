import { Box, Button, Grid, TextField } from "@mui/material"
import { useSignUp } from "dataAccessObj/useSignUp";
import { useState } from "react";
import { textFieldStyle } from "./styleConstants";
import { LoginSignUpButton } from "./loginSignUpBtn";

export const SignUpComponent = () => {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");

    const[firstNameError, setFirstNameError] = useState(false);
    const[lastNameError, setLastNameError] = useState(false);
    const[emailError, setEmailError] = useState(false);
    const[passwordError, setPasswordError] = useState(false);
    const[confirmPasswordError, setConfirmPasswordError] = useState(false);

    const [signUpPayload, setSignUpPayload] = useState(null);

    useSignUp(signUpPayload);

    const onClickSignUp = () => {
        let hasError = false;
        if (!firstName.trim()) {
            setFirstNameError(true)
            hasError = true;
        } if (!lastName.trim()) {
            setLastNameError(true);
            hasError = true;
        } if (!email.trim()) {
            setEmailError(true);
            hasError = true;
        } if (!password.trim()) {
            setPasswordError(true);
            hasError = true;
        } if (!confirmPassword.trim()) {
            setConfirmPasswordError(true);
            hasError = true;
        } else if (password !== confirmPassword) {
            setConfirmPasswordError(true);
            hasError = true;
        }
        if (!hasError) {
            setFirstNameError(false);
            setLastNameError(false);
            setEmailError(false);
            setPasswordError(false);
            setConfirmPasswordError(false);
            setSignUpPayload({
                firstName,
                lastName,
                email,
                password
            })
        }
    }

    return (
        <>
        <Grid container spacing={4} sx={{ pt: "40px" }}>
                <Grid item xs={6}>
                    <TextField
                        label="First Name"
                        value={firstName}
                        onChange={(event) => setFirstName(event.target.value)}
                        error={firstNameError}
                        sx={textFieldStyle}
                        required
                        helperText={firstNameError && "Enter first name"}
                        size="small" />
                </Grid>
                <Grid item xs={6}>
                    <TextField
                        label="Last Name"
                        value={lastName}
                        onChange={(event) => setLastName(event.target.value)}
                        sx={textFieldStyle}
                        error={lastNameError}
                        required
                        helperText={lastNameError && "Enter last name"}
                        size="small" />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        label="E-Mail Address"
                        value={email}
                        sx={textFieldStyle}
                        onChange={(event) => setEmail(event.target.value)}
                        error={emailError}
                        required
                        helperText={emailError && "Enter email"}
                        size="small" />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        label="Enter Password"
                        value={password}
                        sx={textFieldStyle}
                        type="password"
                        onChange={(event) => setPassword(event.target.value)}
                        error={passwordError}
                        required
                        helperText={passwordError && "Enter password"}
                        size="small" />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        label="Confirm Password"
                        value={confirmPassword}
                        sx={textFieldStyle}
                        type="password"
                        onChange={(event) => setConfirmPassword(event.target.value)}
                        error={confirmPasswordError}
                        required
                        helperText={confirmPasswordError && "Confirm password"}
                        size="small" />
                </Grid>
                <LoginSignUpButton handleOnClick={onClickSignUp} btnText={"Sign Up"}/>
            </Grid></>
    )
}
import { Autocomplete, Box, Button, Grid, TextField } from "@mui/material"
import { useSignUp } from "dataAccessObj/useSignUp";
import { useState } from "react";
import { textFieldStyle } from "./styleConstants";
import { LoginSignUpButton } from "./loginSignUpBtn";
import axios from "axios";
import { useEffect } from "react";
import { useRouter } from "next/router";
import { publicFetcher } from "@/utilities/baseFetcher";

export const SignUpComponent = () => {

    const router = useRouter();

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [userTypeOptions, setUserTypeOptions] = useState([]);
    const [userType, setUserType] = useState(null);

    const[firstNameError, setFirstNameError] = useState(false);
    const[lastNameError, setLastNameError] = useState(false);
    const[emailError, setEmailError] = useState(false);
    const[passwordError, setPasswordError] = useState(false);
    const[confirmPasswordError, setConfirmPasswordError] = useState(false);

    const [signUpPayload, setSignUpPayload] = useState(null);

    useEffect(() => {
        if (!signUpPayload?.userName || !signUpPayload?.password) { return; }
        publicFetcher("api/public/create/user", {
          body: JSON.stringify({...signUpPayload}),
          method: "POST"
        }).then((response) => response.text())
          .then((response) => {
            if (response === 'User successfully added') {
                router.push("/login");
            } else {
                console.log(response);
                alert("Invalid values error!");
            }
        });
    }, [signUpPayload]);

    useEffect(() => {
        if(!userTypeOptions.length) {
          publicFetcher("api/public/get/account/types").then((response) => response.json()).then((response) => { setUserTypeOptions(response) });
        }
    }, []);

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
                userName: email,
                password,
                authorities: [userType]
            })
        }
    }

    return (
        <>
        <Grid container spacing={4} sx={{ pt: "40px" }}>
                <Grid item xs={6}>
                    <TextField
                        placeholder="First Name"
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
                        placeholder="Last Name"
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
                        placeholder="E-Mail Address"
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
                        placeholder="Enter Password"
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
                        placeholder="Confirm Password"
                        value={confirmPassword}
                        sx={textFieldStyle}
                        type="password"
                        onChange={(event) => setConfirmPassword(event.target.value)}
                        error={confirmPasswordError}
                        required
                        helperText={confirmPasswordError && "Confirm password"}
                        size="small" />
                </Grid>
                <Grid item xs={12}>
                    <Autocomplete
                      options={userTypeOptions}
                      value={userType}
                      onChange={(event, newValue) => setUserType(newValue) }
                      sx={{...textFieldStyle}}
                      renderInput={(params) => <TextField {...params} sx={{'.MuiInputBase-input': {top: "-7px", position: "relative"}}} placeholder="Select account type"/>} 
                    />
                </Grid>
                <LoginSignUpButton handleOnClick={onClickSignUp} btnText={"Sign Up"}/>
            </Grid></>
    )
}
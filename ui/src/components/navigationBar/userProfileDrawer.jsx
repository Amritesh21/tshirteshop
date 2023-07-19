import { LoginContext } from "@/contexts/loginContext"
import { Avatar, Box, Button, Divider, Drawer, Grid, IconButton, TextField, TextareaAutosize, Typography } from "@mui/material"
import CloseFullscreenIcon from '@mui/icons-material/CloseFullscreen';
import { useContext, useEffect, useState } from "react"
import axios from "axios";

const UserProfileForm = ({setOpenProfileDrawer}) => {

    const {loginState, setLoginState} = useContext(LoginContext);

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName]  = useState("");
    const [phoneNo, setPhoneNo] = useState("");
    const [alternatePhoneNo, setAlternatePhoneNo] = useState("");
    const [address, setAddress] = useState("");

    const [newPassword, setNewPassword] = useState("");
    const [currentPassword, setPassword] = useState("");

    const onClickUpdate = () => {
        axios.post("/api/public/update/user", {
            firstName,
            lastName,
            username: loginState?.username,
            address,
            phoneNo,
            alternatePhoneNo
        }).then((res) => {
            setFirstName(res.data.firstName);
            setLastName(res.data.lastName);
            setPhoneNo(res.data.phoneNo);
            setAlternatePhoneNo(res.data.alternatePhoneNo);
            setAddress(res.data.address);
            setOpenProfileDrawer(false);
            alert("values successfully updated");
            setLoginState((prev) => {return {
                ...prev,
                firstName: res.data.firstName,
                lastName: res.data.lastName
            }})
            const persistedUserDetails = JSON.parse(sessionStorage.getItem("userDetails"));
            persistedUserDetails.firstName = res.data.firstName;
            persistedUserDetails.lastName = res.data.lastName;
            sessionStorage.setItem("userDetails", JSON.stringify(persistedUserDetails));

        })
    }

    useEffect(() => {
        axios.get(`/api/public/user/complete/info/${loginState?.username}`)
        .then((res) => {
            setFirstName(res.data.firstName);
            setLastName(res.data.lastName);
            setPhoneNo(res.data.phoneNo);
            setAlternatePhoneNo(res.data.alternatePhoneNo);
            setAddress(res.data.address);
        });
    }, [])

    return (
        <Box sx={{
            width: "100%",
            py: "30px"
        }}>
            <Grid container spacing={2}>
                <Grid item md={6}>
                    <TextField label="First Name" size="small" required
                      value={firstName}
                      onChange={(event) => setFirstName(event.target.value)}
                    />
                </Grid>
                <Grid item md={6}>
                    <TextField label="Last Name" size="small" required
                      value={lastName}
                      onChange={(event) => setLastName(event.target.value)}
                    />
                </Grid>
                <Grid item md={12}>
                    <Typography>
                        Contact and address details
                    </Typography>
                    <Divider />
                </Grid>
                <Grid item md={6}>
                    <TextField label="Phone Number" size="small" required
                      value={phoneNo}
                      onChange={(event) => setPhoneNo(event.target.value)}
                    />
                </Grid>
                <Grid item md={6}>
                    <TextField label="Alternate Phone Number" size="small"
                      value={alternatePhoneNo}
                      onChange={(event) => setAlternatePhoneNo(event.target.value)}
                    />
                </Grid>
                <Grid item md={12}>
                    <TextField minRows={3} label="Address" multiline sx={{width: "96%", position: "relative"}}
                      value={address}
                      onChange={(event) => setAddress(event.target.value)}
                    />
                </Grid>
                <Grid item md={6}>
                    <Button variant="outlined"
                      onClick={() => onClickUpdate()}
                    >
                        Update user details
                    </Button>
                </Grid>
                <Grid item md={12}>
                    <Typography> Update password</Typography>
                    <Divider />
                </Grid>
                <Grid item md={6}>
                    <TextField label="New Password" size="small" required/>
                </Grid>
                <Grid item md={6}>
                    <TextField label="Current Password" size="small" required/>
                </Grid>
                <Grid item md={6}>
                    <Button variant="outlined">Change Password</Button>
                </Grid>
            </Grid>
        </Box>
    )
}

export const UserProfileManager = ({
    openProfileDrawer, setOpenProfileDrawer
}) => {
    const {loginState} = useContext(LoginContext);
    return (
        <Drawer
            open={openProfileDrawer}
            onClose={() => setOpenProfileDrawer(false)}

        >
            <Box sx={{
                display: "flex", justifyContent: 'flex-end', px:"20px"
            }}>
                <IconButton
                  onClick={() => setOpenProfileDrawer(false)}
                >
                    <CloseFullscreenIcon />
                </IconButton>
            </Box>
            <Box
              sx={{
                width: "500px",
                display: "flex",
                flexDirection: "column",
                pt: "20px",
                px: "20px",
                alignContent: "center",
                alignItems: "center",
              }}
            >
                <Box sx={{alignSelf: "center", display: "flex", justifyContent: "center", flexDirection: "column"}}>
                <Avatar sx={{
                    width: "100px",
                    height: "100px"
                }}>
                    {loginState?.firstName ?? "test value"}
                </Avatar>
                <Typography
                  sx={{alignSelf: "center", alignItems: 'center'}}
                >
                    {loginState?.firstName ?? "test value"}
                </Typography>
                </Box>
                <UserProfileForm setOpenProfileDrawer={setOpenProfileDrawer} />
            </Box>
        </Drawer>
    )
}
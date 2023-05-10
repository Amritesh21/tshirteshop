import { Box, Button, Grid } from "@mui/material"

export const LoginSignUpButton = ({handleOnClick, btnText}) => {
    return(
        <>
    <Grid item xs={12}>
        <Box sx={{ width: "100%", display: "flex", justifyContent: 'flex-end' }}>
            <Button
                sx={{ backgroundColor: "white", color: "black", textTransform: "capitalize", px: "0px", fontWeight: 700, width: "180px", alignSelf: "flex-end" }}
                variant="contained"
                onClick={() => handleOnClick()}
            >
                {btnText}
            </Button>
        </Box>
    </Grid>
        </>
    )
}
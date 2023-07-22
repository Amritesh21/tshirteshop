import { Box, Grid, TextField, Typography } from "@mui/material";
import LocationOnIcon from '@mui/icons-material/LocationOn';
import CallIcon from '@mui/icons-material/Call';

const ContactUs = () => {
  return (
    <Box
      sx={{
        backgroundImage: "linear-gradient(180deg, #7D89FF 0%, #AB40FF 40.67%, white 10%)",
        pt: "2%",
        px: "5%",
      }}
    >
      <Typography sx={{ color: "white" }}>
        Home / Contact
      </Typography>
      <Typography
        sx={{
          fontSize: "40px",
          color: "white",
          fontWeight: 700,
          pt: "2%"
        }}
      >
        Contact
      </Typography>
      <Typography
        sx={{
          width: "30%",
          color: "white",
          pt: "2%"
        }}
      >
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eget gravida leo, nec iaculis diam. Nam bibendum mi sed sem finibus ullamcorper.
      </Typography>
      <Box sx={{ padding: "2%", display: "flex", justifyContent: "space-evenly" }}>
        <Box
          sx={{
            height: "168px",
            width: "450px",
            backgroundColor: "white",
            padding: "55px",
            boxShadow: "0px 20px 20px 0px rgba(0, 0, 0, 0.10)"
          }}
        >
          <Box sx={{ borderRadius: "50%", backgroundColor: "blue", width: "45px", height: "45px", display: "flex", justifyContent: "center" }}>
            <LocationOnIcon sx={{ fontSize: "35px", color: "white", alignSelf: "center" }} />
          </Box>
          <Typography sx={{ fontSize: "24px", fontWeight: 700, py: "10px" }}>
            Location
          </Typography>
          <Typography sx={{ color: "#969696" }}>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eget gravida leo, nec iaculis diam.
          </Typography>
        </Box>
        <Box
          sx={{
            width: "450px",
            height: "168px",
            backgroundColor: "white",
            padding: "55px",
            boxShadow: "0px 20px 20px 0px rgba(0, 0, 0, 0.10)"
          }}
        >
          <Box sx={{ borderRadius: "50%", backgroundColor: "blue", width: "45px", height: "45px", display: "flex", justifyContent: "center" }}>
            <CallIcon sx={{ fontSize: "35px", color: "white", alignSelf: "center" }} />
          </Box>
          <Typography sx={{ fontSize: "24px", fontWeight: 700, py: "10px" }}>
            Contact
          </Typography>
          <Typography sx={{ py: "10px", color: "#969696" }}>
            T-shirt12@gmail.com
          </Typography>
          <Typography sx={{ py: "10px", color: "#969696" }}>
            (021) 12345678
          </Typography>
        </Box>
      </Box>
      <Box
        sx={{
          display: "flex",
          justifyContent: "center",
          py: "3%",
          flexDirection: "column",
        }}
      >
        <Box sx={{width: "100%", display: "flex", justifyContent: "center"}} >
          <Typography sx={{ fontSize: "40px", fontWeight: 700, padding: "20px"}}>
            Send a Message
          </Typography>
        </Box>
        <Box sx={{width: "70%", display: "flex", justifyContent: "center", pl: "15%"}}>
          <Grid container spacing={4}>
            <Grid item md={6}>
              <TextField sx={{ width: "100%" }} size="small" label="First Name" />
            </Grid>
            <Grid item md={6}>
              <TextField sx={{ width: "100%" }} size="small" label="Last Name" />
            </Grid>
            <Grid item md={12}>
              <TextField sx={{ width: "100%" }} size="small" label="Your E-mail" />
            </Grid>
            <Grid item md={12}>
              <TextField sx={{ width: "100%" }} size="small" label="Description" multiline rows={10}/>
            </Grid>
          </Grid>
        </Box>
      </Box>
    </Box>
  )
}

export default ContactUs;
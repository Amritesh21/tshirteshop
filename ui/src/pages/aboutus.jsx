const { Box, Typography } = require("@mui/material")
import Image from "next/image";
import aboutUsImg1 from "../../public/pictures/aboutUsImg1.svg"
import aboutUsImg2 from "../../public/pictures/aboutUsImg2.svg"
import aboutUsImg3 from "../../public/pictures/aboutUsImg3.svg"

const AboutUs = () => {
    return (
        <Box>
            <Box sx={{
                pt: "2%",
                px: "5%",
                backgroundImage: "linear-gradient(175deg, #7D89FF 0%, #AB40FF 66.67%, transparent 20%)",
                display: "flex"
            }}
            >
                <Box>
                    <Typography sx={{ color: "white" }}>
                        Home  / About Us
                    </Typography>
                    <Typography sx={{ fontSize: "40px", fontWeight: 700, color: "white", pt: "2%" }}>
                        About Us
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
                </Box>
                <Box>
                    <Image src={aboutUsImg1} />
                </Box>
            </Box>
            <Box
             sx={{
                pt: "10%",
                px: "5%",
                display: "flex",
                justifyContent: "space-between"
             }}
            >
                <Box sx={{width: "30%"}}>
                <Typography 
                  sx={{
                    fontSize: "40px",
                    fontWeight: 700,
                  }}
                >
                    Profile
                </Typography>
                <Typography sx={{py: "10px"}}>
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eget gravida leo, nec iaculis diam. Nam bibendum mi sed sem finibus ullamcorper.
                </Typography>
                <Typography sx={{py: "10px"}}>
                  Klara is an AI powered automated Solution Builder of Growth Strategy, Branding and Digital marketing planning for your business.
                </Typography>
                </Box>
                <Box sx={{display: "flex", flexDirection: "column"}}>
                    <Image src={aboutUsImg3} />
                    <Typography
                      sx={{
                        color: "#7D89FF",
                        position: "relative",
                        top: "-100px",
                        backgroundColor: "white",
                        width: "85%",
                        alignSelf: "flex-end",
                        padding: "10px",
                        fontWeight: 700
                      }}
                    >
                        Mangcoding Store
                    </Typography>
                </Box>
            </Box>
            <Box
             sx={{
                py: "5%",
                px: "5%",
                display: "flex",
                justifyContent: "space-between"
             }}
            >
                <Box sx={{display: "flex", flexDirection: "column"}}>
                    <Image src={aboutUsImg2} />
                    <Typography
                      sx={{
                        color: "#7D89FF",
                        position: "relative",
                        top: "-100px",
                        backgroundColor: "white",
                        width: "85%",
                        alignSelf: "flex-end",
                        padding: "10px",
                        fontWeight: 700
                      }}
                    >
                        Mangcoding Store
                    </Typography>
                </Box>
                <Box sx={{width: "30%"}}>
                <Typography 
                  sx={{
                    fontSize: "40px",
                    fontWeight: 700,
                  }}
                >
                    Our Mission
                </Typography>
                <Typography sx={{py: "10px"}}>
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eget gravida leo, nec iaculis diam. Nam bibendum mi sed sem finibus ullamcorper.
                </Typography>
                <Typography sx={{py: "10px"}}>
                  Klara is an AI powered automated Solution Builder of Growth Strategy, Branding and Digital marketing planning for your business.
                </Typography>
                </Box>
            </Box>
        </Box>
    )
}

export default AboutUs;
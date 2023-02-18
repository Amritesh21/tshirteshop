import { Box, Button, IconButton, Typography } from "@mui/material"
import Image from "next/image"
import ShirtImage from '../../../public/pictures/shirtImage.svg'
import SuitImage from '../../../public/pictures/suitImage.svg'
import { ProductViewSlider } from "./productViewSlider"

const ProductList = () => {
    return (
        <Box
         sx={{
            top: '80px',
            position: 'relative',
            display: 'flex',
            flexDirection: 'column',
            width: '173px',
            pl: '30px',
         }}
        >
            <Button
              variant="text"
              sx={{
                color: 'white'
              }}
            >
                More List
            </Button>
            <IconButton>
                <Image
                src={SuitImage}
                style={{alignSelf: 'center', paddingTop: '20px', paddingBottom: '10px'}}
                />
                <Typography
                  sx={{
                    position: 'absolute',
                    left: '10px',
                    top: '250px',
                    color: 'white',
                    fontWeight: 600,
                  }}
                >
                    Suit
                </Typography>
            </IconButton>
            <IconButton>
                <Image
                src={ShirtImage}
                style={{alignSelf: 'center', paddingTop: '10px', paddingBottom: '20px'}}
                />
                <Typography
                  sx={{
                    position: 'absolute',
                    left: '20px',
                    top: '210px',
                    color: 'white',
                    fontWeight: 600,
                  }}
                >
                    Shirt
                </Typography>
            </IconButton>
        </Box>
    )
}

export const LandingComponent = () => {
    return (
        <Box
          sx={{
            backgroundImage: "linear-gradient(#7D89FF,#AB40FF)",
            height: "700px",
            display: 'flex',
            flexDirection: 'row',
            alignContent: 'center',
            justifyContent: 'space-evenly',
          }}
        >
            <Box sx={{display: 'flex', alignContent: 'center'}}>
                <Box
                sx={{
                    height: "352px",
                    width: "508px",
                    alignSelf: "center",
                    position: 'relative',
                    top: '15%'
                }}
                >
                    <Typography
                    variant="h4"
                    sx={{
                        color: "white",
                        fontWeight: '600',
                    }}
                    >
                    Get the Latest Dress Models From Us
                    </Typography>
                    <Typography
                    sx={{
                        color: "white",
                    }}
                    >
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eget gravida leo, nec iaculis diam. Nam bibendum mi sed sem finibus ullamcorper.
                    </Typography>
                    <Button variant="contained"
                    sx={{
                        backgroundColor: 'white',
                        color: "#7D89FF",
                        fontWeight: 600,
                        '&:hover': {
                            backgroundColor: 'white',
                        }
                    }}
                    >
                        Shop Now
                    </Button>
                </Box>
            </Box>
            <ProductList />
            <ProductViewSlider />
        </Box>
    )
}
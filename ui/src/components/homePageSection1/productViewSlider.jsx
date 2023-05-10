import { Box, IconButton } from "@mui/material"
import { borderColor } from "@mui/system"
import ArrowLeftOutlinedIcon from '@mui/icons-material/ArrowLeftOutlined';
import ArrowRightOutlinedIcon from '@mui/icons-material/ArrowRightOutlined';
import Image from "next/image"
import { useState } from "react";
import landingViewImage1 from '../../../public/pictures/landingViewImage1.png';
import landingViewImage2 from '../../../public/pictures/landingViewImage2.png';

const imageArray = [landingViewImage1, landingViewImage2, landingViewImage2, landingViewImage1, landingViewImage2, landingViewImage2];

const Slider = ({ selectedImage, setSelectedImage }) => {

    return (
        <Box
            sx={{
                height: '80px',
                position: 'absolute',
                top: '600px',
                display: 'flex',
                flexDirection: 'row',
                backgroundColor: 'black',
                width: '100%',
                maxWidth: '300px',
                ml: '50px',
                backgroundColor: 'rgba(0, 0, 0, 0.5)',
            }}
        >
            <IconButton
              sx={{
                position: 'absolute',
                left: '-10px',
                padding: '0px',
                top: '30px',
                backgroundColor: 'blue'
              }}
            >
                <ArrowLeftOutlinedIcon sx={{color: 'white'}} />
            </IconButton>
            <Image
                src={selectedImage}
                style={{
                    height: '80px',
                    width: '80px',
                    fontSize: '20px',
                    alignSelf: 'center',
                }}
            />
            <Box sx={{overflow: 'hidden', display: 'flex', flexDirection: 'row'}}>
            {imageArray.map((image) => {
                if (image !== selectedImage) {
                    return (
                        <IconButton sx={{borderRadius: "0px"}} key={image} onClick={() => setSelectedImage(image)}>
                            <Image
                                src={image}
                                style={{
                                    height:  '60px',
                                    width: '60px',
                                    paddingLeft: '40px',
                                    paddingRight: '10px',
                                    fontSize: '20px',
                                    alignSelf: 'center',
                                }}
                            />
                        </IconButton>
                    )
                }
            })}
            </Box>
            <IconButton
               sx={{
                position: 'absolute',
                left: '95%',
                padding: '0px',
                top: '30px',
                backgroundColor: 'blue'
              }}
            >
                <ArrowRightOutlinedIcon sx={{color: 'white'}} />
            </IconButton>
        </Box>
    )
}

export const ProductViewSlider = () => {
    const [selectedImage, setSelectedImage] = useState(landingViewImage1);
    return (
        <Box
            sx={{
                height: "522px",
                width: '400px',
                border: '10px solid white',
                alignSelf: 'center',
                borderTopLeftRadius: '100px',
                borderTopRightRadius: '100px',
                borderBottomRightRadius: '100px',
                overflow: 'hidden',
                justifyContent: 'center',
            }}
        >
            <Image src={selectedImage} style={{ height: '100%', width: '100%' }} />
            <Slider selectedImage={selectedImage} setSelectedImage={setSelectedImage} />
        </Box>
    )
}
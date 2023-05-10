import { Box, IconButton } from '@mui/material';
import { useState } from 'react';
import landingViewImage1 from '../../../public/pictures/landingViewImage1.png';
import landingViewImage2 from '../../../public/pictures/landingViewImage2.png';
import ArrowLeftOutlinedIcon from '@mui/icons-material/ArrowLeftOutlined';
import ArrowRightOutlinedIcon from '@mui/icons-material/ArrowRightOutlined';
import Image from 'next/image';

export const CategorySlider = () => {
    const [selectedImage, setSelectedImage] = useState(0);
    const [imageArr, setImageArr] = useState([landingViewImage1, landingViewImage2, landingViewImage1, landingViewImage2, landingViewImage1, landingViewImage2, landingViewImage1, landingViewImage2, landingViewImage1, landingViewImage2, landingViewImage1, landingViewImage2]);
    const onLeftButtonPress = () => {
        const imgArrTemp = [...imageArr]
        imgArrTemp.splice(0,0,imgArrTemp.pop()); 
        setImageArr(imgArrTemp);
    }
    const onRightButtonPress = () => {
        const imgArrTemp = [...imageArr]
        console.log(imgArrTemp)
        const val = imgArrTemp.splice(0,1)[0];
        console.log(imgArrTemp)
        imgArrTemp.push(val); 
        console.log(imgArrTemp);
        setImageArr(imgArrTemp);
    }

    const onSelectImage = (index) => {
        setSelectedImage(index);
    }

    return (
        <Box
         sx={{
            display: "flex",
            flexDirection: "row",
            position: "relative",
            height: "230px",
            transition: "1s smooth",
            animation: "slide 10s ease infinite",
          }}
        >
            <IconButton sx={{
                position: "absolute",
                left: "-20px",    
                top: "45%",
                zIndex: "5",
                backgroundColor: "blue",
                height: "40px",
                width: "40px",
                borderRadius: "0px",
             }}
             onClick={() => onLeftButtonPress()}
            >
              <ArrowLeftOutlinedIcon sx={{color: 'white'}} />
            </IconButton>
            <Box 
            id = "slider-component"
            sx={{
            height: "230px",
            width: "650x",
            display: "flex",
            flexDirection: "row",
            overflow: "hidden",
            top:"80%",
            left: "15%"
          }}
           >
            {imageArr.map((image, index) => (
                <Box key={index} sx={{
                    padding: selectedImage === index ? "0px" : "15px", marginRight: (selectedImage === index && index === 0) ? "15px" : "0px",
                    //transition: "width 2s, height 4s"
                    alignSelf: "center",
                }}
                  onClick={() => onSelectImage(index)}
                >
                    <Image style={{
                        position: "relative",
                        width: "360px", height: selectedImage === index?  "230px" : "163px",
                        transition: "width 2s, height 2s, transform 2s",
                    }} src={image} alt={`${index}`} />
                </Box>
            ))}
            </Box>
            <IconButton
             sx={{
                position: "absolute",
                left: "340px",    
                top: "45%",
                zIndex: "5",
                backgroundColor: "blue",
                height: "40px",
                width: "40px",
                borderRadius: "0px"
             }}
             onClick={() => onRightButtonPress()}
            >
              <ArrowRightOutlinedIcon sx={{color: 'white'}} />
            </IconButton>
        </Box>
    )
}
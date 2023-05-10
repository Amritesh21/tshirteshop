import { Box, IconButton } from "@mui/material"
import Image from "next/image"
import ArrowLeftOutlinedIcon from '@mui/icons-material/ArrowLeftOutlined';
import ArrowRightOutlinedIcon from '@mui/icons-material/ArrowRightOutlined';

export const SliderComponent = ({imageArr, setSelectedImage, selectedImage, setImageArr}) => {
    const onLeftButtonPress = () => {
        let sliderBox = document?.getElementById("slider-component")
        let width = sliderBox.clientWidth;
        console.log(width);
        console.log(sliderBox.scrollLeft);
        sliderBox.scrollLeft = sliderBox.scrollLeft - 180;
        /*const imgArrTemp = [...imageArr]
        imgArrTemp.splice(0,0,imgArrTemp.pop()); 
        setImageArr(imgArrTemp);*/
    }
    const onRightButtonPress = () => {
        let sliderBox = document?.getElementById("slider-component")
        let width = sliderBox.clientWidth;
        console.log(width);
        sliderBox.scrollLeft = sliderBox.scrollLeft + 180;
        /*const imgArrTemp = [...imageArr]
        console.log(imgArrTemp)
        const val = imgArrTemp.splice(0,1)[0];
        console.log(imgArrTemp)
        imgArrTemp.push(val); 
        // setSelectedImage((preval) => preval < imageArr.length-1 ? preval + 1 : preval);
        console.log(imgArrTemp);
        setImageArr(imgArrTemp);*/
    }

    const onSelectImage = (index) => {
        setSelectedImage(index);
    }

    return (
        <Box 
         sx={{
            height: "80px",
            width: `290px`,
            backgroundColor: "black",
            display: "flex",
            flexDirection: "row",
            position: "absolute",
            top:"80%",
            left: "15%",
            transition: "2s all ease",
            animation: "slide 10s ease infinite",
            scrollBehavior: "smooth"
          }}
        >
            <IconButton sx={{
                position: "absolute",
                left: "-10px",    
                top: "30px",
                zIndex: "5",
                backgroundColor: "blue",
                height: "18px",
                width: "18px",
                borderRadius: "0px",
             }}
             onClick={() => onLeftButtonPress()}
            >
              <ArrowLeftOutlinedIcon sx={{color: 'white'}} />
            </IconButton>
            <Box 
            id = "slider-component"
            sx={{
            height: "80px",
            width: "290px",
            backgroundColor: "black",
            display: "flex",
            flexDirection: "row",
            overflow: "hidden",
            top:"80%",
            left: "15%",
            transition: "2s all ease",
            scrollBehavior: "smooth"
          }}
           >
            {imageArr.map((image, index) => (
                <IconButton sx={{
                    width: "80px", height: "80px", padding: selectedImage === index ? "0px" : "15px", marginRight: (selectedImage === index && index === 0) ? "15px" : "0px",
                    transition: "width 2s, height 4s"
                }}
                  key={index}
                  onClick={() => onSelectImage(index)}
                >
                    <Image style={{
                        position: "relative",
                        height: "100%",
                        width: "100%",
                        transition: "width 2s, height 2s, transform 2s",
                    }} src={image} alt={`${index}`} />
                </IconButton>
            ))}
            </Box>
            <IconButton
             sx={{
                position: "absolute",
                left: "280px",    
                top: "30px",
                zIndex: "5",
                backgroundColor: "blue",
                height: "18px",
                width: "18px",
                borderRadius: "0px"
             }}
             onClick={() => onRightButtonPress()}
            >
              <ArrowRightOutlinedIcon sx={{color: 'white'}} />
            </IconButton>
        </Box>
    )
}
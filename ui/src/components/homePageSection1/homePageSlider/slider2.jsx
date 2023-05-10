import { Box, IconButton } from "@mui/material"
import Image from "next/image"
import ArrowLeftOutlinedIcon from '@mui/icons-material/ArrowLeftOutlined';
import ArrowRightOutlinedIcon from '@mui/icons-material/ArrowRightOutlined';
import { useState } from "react";

export const SliderComponent2 = ({ imageArr, setSelectedImage, selectedImage, setImageArr }) => {

    const [activeIndex, setActiveIndex] = useState(0);

    const onLeftButtonPress = () => {
        if (activeIndex <= 0) {
            return;
        }
        setActiveIndex(preval => preval - 1);
        setSelectedImage(activeIndex - 1);
    }

    const onRightButtonPress = () => {
        if (activeIndex >= imageArr.length - 1) {
            return;
        }
        setActiveIndex(preval => preval + 1);
        setSelectedImage(activeIndex + 1)
    }

    const onSelectImage = (index) => {
        setActiveIndex(index)
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
                overflow: "hidden",
                top: "80%",
                left: "15%",
            }}
        >
            <IconButton sx={{
                position: "absolute",
                left: "0px",
                top: "30px",
                zIndex: "5",
                backgroundColor: "blue",
                height: "18px",
                width: "18px",
                borderRadius: "0px",
                zIndex: "10",
            }}
                onClick={() => onLeftButtonPress()}
            >
                <ArrowLeftOutlinedIcon sx={{ color: 'white' }} />
            </IconButton>
            <Box
                id="slider-component"
                sx={{
                    height: "80px",
                    width: "290px",
                    display: "flex",
                    flexDirection: "row",
                    top: "80%",
                    left: "15%",
                    transform: `translate(-${activeIndex * 80}px)`,
                    transition: "0.5s transform",
                }}
            >
                {imageArr.map((image, index) => (
                    <IconButton sx={{
                        width: "80px", height: "80px",
                    }}
                        onClick={() => onSelectImage(index)}
                        key={index}
                    >
                        <Image style={{
                            position: "relative",
                            height: "100%",
                            width: activeIndex === index ? "100%" : "80%",
                            transition: "0.5s all",
                            transform: activeIndex === index ? "scaleY(100%)" : "scaleY(70%)",
                        }} src={image} alt={`${index}`} />
                    </IconButton>
                ))}
            </Box>
            <IconButton
                sx={{
                    position: "absolute",
                    left: "276px",
                    top: "30px",
                    zIndex: "5",
                    backgroundColor: "blue",
                    height: "18px",
                    width: "18px",
                    borderRadius: "0px"
                }}
                onClick={() => onRightButtonPress()}
            >
                <ArrowRightOutlinedIcon sx={{ color: 'white' }} />
            </IconButton>
        </Box>
    )
}
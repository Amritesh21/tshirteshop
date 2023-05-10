import { Box, IconButton } from "@mui/material"
import Image from "next/image"
import ArrowLeftOutlinedIcon from '@mui/icons-material/ArrowLeftOutlined';
import ArrowRightOutlinedIcon from '@mui/icons-material/ArrowRightOutlined';
import { useState } from "react";

export const CommonSliderComponent = ({
    imageArr,
    setSelectedImage,
    setImageArr,
    hasImageSelection = true,
    containerBgColor = "black",
    sliderDimension = {
        containerHeight: "80px",
        containerWidth: "290px",
        containerPosition: "absolute",
        containerTopPos: "80%",
        containerLeftPos: "15%",
        selectionHeight: "80px",
        selectionWidth: "80px",
        transformSpan: 80,
        toggleBtnTop:"30px",
        toggleBtnLeft: "276px",
    }
    }) => {

    const [activeIndex, setActiveIndex] = useState(0);

    const onLeftButtonPress = () => {
        if (activeIndex <= 0) {
            return;
        }
        setActiveIndex(preval => preval - 1);
        if (hasImageSelection) {
          setSelectedImage(activeIndex - 1);
        }
    }

    const onRightButtonPress = () => {
        /**
         * TODo : this condition for infinite slider needs to be revisited
         */
        if (activeIndex >= imageArr.length - 3) {
            setImageArr(preval => [...preval].concat(imageArr))
        }
        setActiveIndex(preval => preval + 1);
        if (hasImageSelection) {
          setSelectedImage(activeIndex + 1);
        }
    }

    const onSelectImage = (index) => {
        setActiveIndex(index)
        if (hasImageSelection) {
          setSelectedImage(index);
        }
    }

    return (
        <Box
            sx={{
                height: sliderDimension.containerHeight,
                width: sliderDimension.containerWidth,
                backgroundColor: containerBgColor,
                display: "flex",
                flexDirection: "row",
                position: sliderDimension.containerPosition,
                overflow: "hidden",
                top: sliderDimension.containerTopPos,
                left: sliderDimension.containerLeftPos,
            }}
        >
            <IconButton sx={{
                position: "absolute",
                left: "0px",
                top: sliderDimension.toggleBtnTop,
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
                    height: sliderDimension.containerHeight,
                    width: sliderDimension.containerWidth,
                    display: "flex",
                    flexDirection: "row",
                    top: "80%",
                    left: "15%",
                    transform: `translate(-${activeIndex * sliderDimension.transformSpan}px)`,
                    transition: "0.5s transform",
                }}
            >
                {imageArr.map((image, index) => (
                    <IconButton sx={{
                        width: sliderDimension.selectionWidth, height: sliderDimension.selectionHeight,
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
                    left: sliderDimension.toggleBtnLeft,
                    top: sliderDimension.toggleBtnTop,
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
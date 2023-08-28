import { Box, Typography } from "@mui/material"
import { CategorySlider } from "./categorySlider"
import landingViewImage1 from '../../../public/pictures/landingViewImage1.png';
import landingViewImage2 from '../../../public/pictures/landingViewImage2.png';
import { useState } from "react";
import { CommonSliderComponent } from "../sliderComponent";

export const HomePageSection2 = () => {
    const [imageArr, setImageArr] = useState([landingViewImage1, landingViewImage2])//, landingViewImage1, landingViewImage2, landingViewImage1, landingViewImage2, landingViewImage1, landingViewImage2, landingViewImage1, landingViewImage2, landingViewImage1, landingViewImage2]);
    return (
        <Box sx={{ display: 'flex', flexDirection: 'row', py: "84px" }}>
            <Box sx={{ px: "120px", width: "60%" }}>
                <Typography sx={{ fontSize: "18px", fontWeight: 400, color: "blue" }}>
                    Choose Your Category
                </Typography>
                <Typography sx={{ fontSize: "48px", fontWeight: 700 }}>
                    Categories Style
                </Typography>
                <Typography sx={{ width: "508px" }}>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eget gravida leo, nec iaculis diam. Nam bibendum mi sed sem finibus ullamcorper.
                </Typography>
            </Box>
            <Box sx={{ width: "40%" }}>
                <CommonSliderComponent
                    imageArr={imageArr}
                    setImageArr={setImageArr}
                    hasImageSelection={false}
                    containerBgColor={"white"}
                    maxImageInOneFrame={1}
                    sliderDimension={{
                        containerHeight: "300px",
                        containerWidth: "100%",
                        containerPosition: "relative",
                        containerTopPos: "0%",
                        containerLeftPos: "0%",
                        selectionHeight: "100%",
                        selectionWidth: "290px",
                        transformSpan: 290,
                        toggleBtnTop:"50%",
                        toggleBtnLeft: "276px",
                    }}
                />
            </Box>
        </Box>
    )
}
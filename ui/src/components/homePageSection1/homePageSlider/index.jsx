import { Box } from "@mui/material";
import Image from "next/image";
import { useEffect, useRef, useState } from "react";
import landingViewImage1 from '../../../../public/pictures/landingViewImage1.png';
import landingViewImage2 from '../../../../public/pictures/landingViewImage2.png';
import { SliderComponent } from "./sliderComponent";
import { SliderComponent2 } from "./slider2";
import { CommonSliderComponent } from "@/components/sliderComponent";

export const HomePageSlider = () => {
  const [selectedImage, setSelectedImage] = useState(0);
  const [imageList, setImageList] = useState(null);
  const currentIndex = useRef(null);
  const [imageRefIndex, setImageRefIndex] = useState(null);
 // const [imageArr, setImageArr] = useState([landingViewImage1, landingViewImage2, landingViewImage1, landingViewImage2, landingViewImage1, landingViewImage2, landingViewImage1, landingViewImage2, landingViewImage1, landingViewImage2, landingViewImage1, landingViewImage2]);
  const [imageArr, setImageArr] = useState([]);
  useEffect(() => {
      fetch('/api/public/getImageList')
        .then(response => response.json())
        .then(res => {
          console.log(res["publicImageList"])
          setImageList(res["publicImageList"]);
          setImageRefIndex(res["publicImageList"][0]);
          currentIndex.current = 0;
        });
  }, []);
  useEffect(() => {
      if (!imageRefIndex) { return; }
      fetch(`/api/public/getImage/${imageRefIndex}`)
        .then(response => response?.blob())
        .then(imageBlob => {
          const imageURL = URL.createObjectURL(imageBlob);
          setImageArr([...imageArr, imageURL]);
          if (currentIndex.current < imageList.length) {
             setImageRefIndex(currentIndex.current + 1);
             currentIndex.current = currentIndex.current + 1;
          }
        });
    }, [imageRefIndex]);
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
        position: "relative",
      }}
    >
      <Image src={imageArr[selectedImage]} style={{ height: '100%', width: '100%' }} alt="Image-value" />
      {/*<SliderComponent imageArr={imageArr} setSelectedImage={setSelectedImage} selectedImage={selectedImage} setImageArr={setImageArr} />*/}
      {/*imageArr.length && <SliderComponent2 imageArr={imageArr} setSelectedImage={setSelectedImage} selectedImage={selectedImage} setImageArr={setImageArr} />*/}
      {imageArr.length &&
        <CommonSliderComponent
          imageArr={imageArr}
          setSelectedImage={setSelectedImage}
          setImageArr={setImageArr}
          hasImageSelection
          containerBgColor={"black"}
          sliderDimension={{
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
          }}
        />}
    </Box>
  )
}
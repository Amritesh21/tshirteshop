import { Box } from "@mui/material";
import Image from "next/image";
import { useEffect, useRef, useState } from "react";
import landingViewImage1 from '../../../../public/pictures/landingViewImage1.png';
import landingViewImage2 from '../../../../public/pictures/landingViewImage2.png';
import { SliderComponent } from "./sliderComponent";
import { SliderComponent2 } from "./slider2";
import { CommonSliderComponent } from "@/components/sliderComponent";
import { imageFetcher, publicFetcher } from "@/utilities/baseFetcher";

export const HomePageSlider = () => {
  const [selectedImage, setSelectedImage] = useState(0);
  const [imageList, setImageList] = useState(null);
  const currentIndex = useRef(null);
  const [imageRefIndex, setImageRefIndex] = useState(null);
 // const [imageArr, setImageArr] = useState([landingViewImage1, landingViewImage2, landingViewImage1, landingViewImage2, landingViewImage1, landingViewImage2, landingViewImage1, landingViewImage2, landingViewImage1, landingViewImage2, landingViewImage1, landingViewImage2]);
  const [imageArr, setImageArr] = useState([]);
  const [productsMetaBuffer, setProductsMetaBuffer] = useState([]);
  useEffect(() => {
    // console.log(startPosition, "start position changed");
    publicFetcher(`api/public/get/products/meta?startPosition=0&pageSize=10`)
      .then((response) => response.json())
      .then((response) => setProductsMetaBuffer(response));
  }, []);

  useEffect(() => {
    if (!productsMetaBuffer.length) { return; }
    const imageArrTemp = [];
    productsMetaBuffer.forEach((product) => {
      imageArrTemp.push(imageFetcher(`api/public/get/thumbNail/${product.productId}`));
    }
    );
    setImageArr(imageArrTemp);
  }, [productsMetaBuffer]);

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
      <Image src={imageArr[selectedImage]} style={{ height: '100%', width: '100%' }} alt="Image-value" width={100} height={100} />
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
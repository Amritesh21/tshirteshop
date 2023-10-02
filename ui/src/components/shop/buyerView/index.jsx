import { Accordion, AccordionDetails, AccordionSummary, Box, Button, Grid, Slider, Typography } from "@mui/material";
import TuneIcon from '@mui/icons-material/Tune';
import StarIcon from '@mui/icons-material/Star';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { useEffect, useState } from "react";
import axios from "axios";
import { useRouter } from "next/router";
import { imageFetcher, publicFetcher } from "@/utilities/baseFetcher";

const ProductListComponent = () => {
    const [productsMeta, setProductsMeta] = useState([]);
    const [productMetaBuffer, setProductMetaBuffer] = useState([]);
    const [productThumbNailArr, setProductThumbNailArr] = useState({});
    const [startPosition, setStartPosition] = useState(0);
    const [totalRecords, setTotalRecords] = useState(0);

    const router = useRouter();

    useEffect(() => {
        publicFetcher(`api/public/get/products/count`)
        .then((response) => response.json())
        .then((response) => setTotalRecords(response));
    }, []);

    useEffect(() => {
        console.log(startPosition, "start position changed");
        publicFetcher(`api/public/get/products/meta?startPosition=${startPosition}&pageSize=${6}`)
        .then((response) => response.json())
        .then((response) => setProductMetaBuffer(response));
    }, [startPosition]);

    useEffect(() => {
        if (!productMetaBuffer.length) {return;}
        const thumbnailObj = {};
        productMetaBuffer.forEach((product) => {
            thumbnailObj[product.productId] = imageFetcher(`api/public/get/thumbNail/${product.productId}`);
        }
        );
        setProductThumbNailArr(thumbnailObj);
        setProductsMeta((preval) => [...preval, ...productMetaBuffer])
    }, [productMetaBuffer]);

    return (
        <>
        {productsMeta.map((product) => (
            <Grid item md={3} key={product.productId}>
            <Button sx={{
                width: "100%", height: "366px", position: "relative",
                boxShadow: "0px 20px 20px 0px rgba(0, 0, 0, 0.05)",
                padding: "10px"
                }} onClick={() => router.push(`/buy/product?productId=${product.productId}`)}>
                <Box sx={{display: "flex", flexDirection: "column", position: "relative", width: "100%", height: "100%"}}>
                   <img src={productThumbNailArr[product.productId]} alt={product.productId} style={{position: "relative", width: "100%", height: "80%"}}/>
                    <Box sx={{display: "flex", justifyContent: "space-between", textTransform: "capitalize", pt: "5px", color: "black"}}>
                        <Typography sx={{fontWeight: 700}}>
                            {product.productCategory}
                        </Typography>
                        <Typography sx={{fontWeight: 700}}>
                            {`${product.productPrice}$`}
                        </Typography>
                    </Box>
                    <Typography sx={{alignSelf: "flex-start", textTransform: "capitalize", color: "#969696"}}>
                            Best Quality
                    </Typography>
                    <Box sx={{display: "flex"}}>
                    {Array.from(Array(product.reviewStar ?? 4), (_, index) => index + 1).map((_, index) => <StarIcon key={index} sx={{color: "yellow"}} />)}
                    </Box>
                </Box>
            </Button>
        </Grid>
        ))}
        <Grid item md={12}>
            <Box sx={{ width: "100%", display: "flex", justifyContent: "center" }}>
                <Button 
                 variant="contained"
                 onClick={() => setStartPosition((preval) => preval + 6 )}
                 disabled={(startPosition + 6) >= totalRecords}
                >
                    Load more items
                </Button>
            </Box>
        </Grid>
        </>
    );
}

export const BuyerView = ({selectedCategory}) => {
    return (
        <Box sx={{
            py: "100px",
            px: "50px"
        }}>
            <Typography sx={{fontSize: "40px", fontWeight: 700}}>
                {selectedCategory}
            </Typography>
            <Grid container spacing={4}>
                {/* <Grid item md={4}>
                    <Box sx={{display: "flex", "& > *": {padding: "5px"}}}>
                        <Typography>
                            Filter
                        </Typography>
                        <TuneIcon />
                    </Box>
                    <Box sx={{pr: "50px"}}>
                    <Accordion defaultExpanded>
                        <AccordionSummary
                            expandIcon={<ExpandMoreIcon />}
                            aria-controls="panel1a-content"
                            id="panel1a-header"
                        >
                            <Typography>Target Gender</Typography>
                        </AccordionSummary>
                        <AccordionDetails>
                            <Box sx={{display: "flex", "& > *": {padding: "10px"}}}>
                                <Button>Male</Button>
                                <Button>Female</Button>
                                <Button>Others</Button>
                            </Box>
                        </AccordionDetails>
                    </Accordion>
                    <Accordion defaultExpanded>
                        <AccordionSummary
                            expandIcon={<ExpandMoreIcon />}
                            aria-controls="panel1a-content"
                            id="panel1a-header"
                        >
                            <Typography>Price</Typography>
                        </AccordionSummary>
                        <AccordionDetails>
                            <Box sx={{display: "flex", "& > *": {padding: "10px"}}}>
                                <Slider
                                  
                                />
                            </Box>
                        </AccordionDetails>
                    </Accordion>
                    </Box>
                </Grid> */}
                <Grid item md={12}>
                    <Grid container spacing={6}>
                        <ProductListComponent />
                    </Grid>
                </Grid>
            </Grid>            
        </Box>
    )
}
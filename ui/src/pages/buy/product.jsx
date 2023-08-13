import { SliderComponent } from "@/components/homePageSection1/homePageSlider/sliderComponent";
import axios from "axios";
import { useRouter } from "next/router";
import { useContext, useEffect, useState } from "react";
import CircleIcon from '@mui/icons-material/Circle';
import RemoveCircleOutlineIcon from '@mui/icons-material/RemoveCircleOutline';
import StarIcon from '@mui/icons-material/Star';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';
import ShoppingBagIcon from '@mui/icons-material/ShoppingBag';
import { LoginContext } from "@/contexts/loginContext";

const { Box, Typography, Grid, IconButton, Button, Avatar, TextField, InputAdornment } = require("@mui/material")

const BuyProduct = () => {
    const colorsArray = [];
    const sizes = [];
    const {loginState} = useContext(LoginContext);
    const [productDetailsMeta, setProductDetailsMeta] = useState(null);
    const [productImageListMeta, setProductImageListMeta] = useState([]);
    const [productImageArray, setProductImageArray] = useState([]);
    const [selectedImage, setSelectedImage] = useState(null);

    const [selectColor, setSelectColor] = useState(null);
    const [selectSize, setSelectSize] = useState(null);
    const [selectQuantity, setSelectQuantity] = useState(0);

    const router = useRouter();
    const {productId} = router.query;
    const baseURL = "http://localhost/";
    const params = {
        color: selectColor,
        size: selectSize,
        quantity: selectQuantity
    };

    const handleAddToCart = () => {
        if (!loginState?.username) {
            router.push("/login");
        }
        const url = `${baseURL}api/auth/buyer/add/${productId}?${new URLSearchParams(params).toString()}`;
        axios.post(url,{}, {headers: {
            "Auth-Token": loginState?.authToken
        }}).then((response) => {
            if(response.status >= 200 && response.status <= 210) {
                router.push("/orderCart");
            }
        });
    }

    useEffect(() => {
        if (!productId) { return; }
        axios.get(`${baseURL}api/public/get/product/meta/${productId}`)
        .then((response) => {
            setProductDetailsMeta({
                ...response.data,
                colorsArray: response.data.colorsArray.map((color) => color.replaceAll("[", "").replaceAll('"',"").replaceAll("]","")),
                selectSizes: response.data.selectSizes.map((size) => size.replaceAll("[", "").replaceAll('"',"").replaceAll("]","")),
            })
        });
        axios.get(`${baseURL}api/public/get/product/images/meta/${productId}`)
        .then((response) => {
            setProductImageListMeta(response.data);
        })
    }, [productId]);

    useEffect(() => {
        if (!productImageListMeta.length) {return;}
        productImageListMeta.forEach(async (productImageId) => {
            await fetch(`${baseURL}api/public/get/product/image/${productImageId}`)
            .then(response => response?.blob())
            .then(imageBlob => {
                setProductImageArray((preval) => [...preval, imageBlob]);
            })
        })
    }, [productImageListMeta]);

    useEffect(() => {
        if (productImageArray.length > 0) {
          setSelectedImage(productImageArray.length - 1);
        }
    }, [productImageArray])

    return(
        <Box sx={{
            padding: "20px"
        }}>
            <Typography sx={{ fontSize: "16px", fontWeight: 700, color: "#AB40FF", pl: "100px", py: "50px"}}>
                Shop / Buy / Product
            </Typography>
            <Grid container spacing={5}>
                <Grid item md={6}>
                    {productImageArray.length > 0 && <Grid container>
                            <Grid item md={12}>
                                <Box sx={{ display: "flex", justifyContent: "center", padding: "0px" }}>
                                    <Box sx={{
                                        height: "500px",
                                        width: "500px",
                                    }}>
                                        <img src={productImageArray[selectedImage] && URL.createObjectURL(productImageArray[selectedImage])} style={{ height: "100%", width: "100%", position: "relative" }} />
                                    </Box>
                                </Box>
                            </Grid>
                        <SliderComponent imageArr={productImageArray.map((image) => URL.createObjectURL(image))} setSelectedImage={setSelectedImage} setImageArr={setProductImageArray} />
                    </Grid>}
                </Grid>
                <Grid item md={6} sx={{ padding: "20px" }}>
                    <Grid container spacing={4}>
                        <Grid item md={6}>
                            <Typography sx={{fontSize: "40px",  fontWeight: 700}}>
                                {productDetailsMeta?.productCategory}
                            </Typography>
                        </Grid>
                        <Grid item md={10}>
                            <Typography sx={{fontWeight: 400, fontSize: "18px"}}>
                                {productDetailsMeta?.productDescription}
                            </Typography>
                        </Grid>
                        <Grid item md={10}>
                            <Box sx={{display: "flex"}}>
                                {Array.from(Array(4), (_, index) => index + 1).map((_, index) => <StarIcon key={index} sx={{color: "yellow"}} />)}
                                <Typography sx={{px: "20px"}}>(2k)</Typography>
                            </Box>
                        </Grid>
                        <Grid item md={4}>
                            <Typography sx={{ fontWeight: 700 }}>
                                Color
                            </Typography>
                            <Box>
                                {productDetailsMeta?.colorsArray.map((color) => (
                                    <IconButton key={color} onClick={() => setSelectColor(color)} sx={{border: color === selectColor ? "1px solid #5463FF": "none"}}>
                                        <CircleIcon sx={{ color: color }} />
                                    </IconButton>
                                ))}
                            </Box>
                        </Grid>
                        <Grid item md={6}>
                            <Box>
                                <Typography sx={{fontWeight: 700}}>
                                    Stock
                                </Typography>
                                <Box sx={{display: "flex", "& > *": {px: "5px"}}}>
                                    <Typography>
                                        ({productDetailsMeta?.totalStock})
                                    </Typography>
                                    <ShoppingCartIcon />
                                </Box>
                            </Box>
                        </Grid>
                        <Grid item md={10}>
                            <Typography sx={{ fontWeight: 700 }}>
                                Size
                            </Typography>
                            <Box>
                                {productDetailsMeta?.selectSizes?.map((size) => <IconButton key={size} sx={{ margin: "2px" }}
                                    onClick={() => setSelectSize(size)}
                                >
                                    <Avatar sx={{backgroundColor: selectSize === size ? "#5463FF" : "#F4F4F4", color: selectSize === size ? "#F4F4F4" : "#5463FF"}}>
                                      <Typography sx={{fontSize: "14px"}}>{size}</Typography>
                                    </Avatar>
                                </IconButton>)}
                            </Box>
                        </Grid>
                        <Grid item md={4}>
                            <Typography sx={{fontWeight: 700, py: "5px"}}>Quantity</Typography>
                            <TextField
                              size="small"
                              value={selectQuantity}
                              sx={{width: "150px"}}
                              InputProps={{
                                readOnly: true,
                                startAdornment: <InputAdornment position="start">
                                    <IconButton onClick={() => setSelectQuantity((preval) => preval+1)}>
                                        <AddIcon />
                                    </IconButton>
                                </InputAdornment>,
                                endAdornment: <InputAdornment position="end">
                                    <IconButton onClick={() => setSelectQuantity((preval) => preval > 0 ? preval-1 : 0)}>
                                        <RemoveIcon />
                                    </IconButton>
                                </InputAdornment>
                              }}
                            />
                        </Grid>
                        <Grid item md={6}>
                            <Typography sx={{fontSize: "40px", fontWeight: 700, pt: "20px", pl: "50px"}}>
                                {productDetailsMeta?.productPrice}$
                            </Typography>
                        </Grid>
                        <Grid item md={10}>
                            <Box sx={{ width: "100%", display: "flex"}}>
                                <Button sx={{ width: "70%", backgroundColor: "#AB40FF", color: "white", "&:hover": { color: "black" } }}>
                                    Checkout
                                </Button>
                                <Box sx={{pl: "5px"}}>
                                    <Button 
                                      onClick={handleAddToCart}
                                      sx={{ width: "10%", backgroundColor: "#AB40FF", color: "white", "&:hover": { color: "black" } }}>
                                        <ShoppingBagIcon />
                                    </Button>
                                </Box>
                            </Box>
                        </Grid>
                    </Grid>
                </Grid>
            </Grid>
        </Box>
    )
}

export default BuyProduct;
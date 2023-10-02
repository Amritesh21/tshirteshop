import { useContext, useEffect, useMemo, useState } from "react";
import AddIcon from '@mui/icons-material/Add';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import CircleIcon from '@mui/icons-material/Circle';
import RemoveCircleOutlineIcon from '@mui/icons-material/RemoveCircleOutline';
import { SliderComponent } from "@/components/homePageSection1/homePageSlider/sliderComponent";
import axios from "axios";
import { LoginContext } from "@/contexts/loginContext";
import { useRouter } from "next/router";
import { getBaseURL, imageFetcher, publicFetcher } from "@/utilities/baseFetcher";

const { Box, Typography, Button, Grid, TextField, Autocomplete, IconButton } = require("@mui/material")

const AddProduct = () => {
    const {loginState} = useContext(LoginContext);
    const [productImageArray, setProductImageArray] = useState([]);
    const [colorsArray, setColorsArray] = useState([]);
    const [colorSelectorActive, setColorSelectorActive] = useState(false);
    const sizes = ["S", "M", "L", "XL", "XXL", "XXXL"];
    const [selectSizes, setSelectSizes] = useState([]);
    const [selectedImage, setSelectedImage] = useState(null);

    const [productCategory, setProductCategory] = useState("");
    const [productDescription, setProductDescription] = useState("");
    const [totalStock, setTotalStock] = useState(0);
    const [productPrice, setProductPrice] = useState(0);
    const [targetGender, setTargetGender] = useState(""); 
    const [productImageListMeta, setProductImageListMeta] = useState([]);
    const router = useRouter();
    const {productId} = router.query
    const baseURL = "http://localhost/";

    const productIdState = useMemo(() => {
        return productId;
    }, [productId]);

    useEffect(() => {
        if (!productId) { return; }
        publicFetcher(`api/public/get/product/meta/${productIdState}`)
        .then((response) => response.json())
        .then((response) => {
            setColorsArray(response.colorsArray.map((color) => color.replaceAll("[", "").replaceAll('"',"").replaceAll("]","")));
            setSelectSizes(response.selectSizes.map((size) => size.replaceAll("[", "").replaceAll('"',"").replaceAll("]","")));
            setProductCategory(response.productCategory);
            setProductDescription(response.productDescription);
            setTotalStock(response.totalStock);
            setProductPrice(response.productPrice);
            setTargetGender(response.targetGender);
        });
        publicFetcher(`api/public/get/product/images/meta/${productIdState}`)
        .then((response) => response.json())
        .then((response) => {
            setProductImageListMeta(response);
        })
    }, [productIdState]);

    useEffect(() => {
        if (!productImageListMeta.length) {return;}
        productImageListMeta.forEach(async (productImageId) => {
            await fetch(`${getBaseURL()}/api/public/get/product/image/${productImageId}`)
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

    const handleImageUpload = (event) => {
        const file = event.target.files[0];
        const reader = new FileReader();
        setProductImageArray((preval) => [...preval, event.target.files[0]]);
    }

    const handleClick = (size) => {
        if (selectSizes.indexOf(size) === -1) {
            setSelectSizes([...selectSizes, size]);
        } else {
            const itemIndex = selectSizes.indexOf(size);
            const setSizeArrayTemp = [...selectSizes];
            setSizeArrayTemp.splice(itemIndex, 1);
            setSelectSizes([...setSizeArrayTemp]);
        }
    }

    const handleProductSave = () => {
        const formData = new FormData();
        formData.append("productId", productIdState || 0);
        formData.append("userName", loginState.username);
        formData.append("productCategory", productCategory);
        formData.append("productDescription", productDescription);
        formData.append("productPrice", productPrice);
        formData.append("colorsArray", JSON.stringify(colorsArray));
        formData.append("selectSizes", JSON.stringify(selectSizes));
        formData.append("targetGender", targetGender);
        formData.append("totalStock", totalStock);
        for (let i = 0; i < productImageArray.length; i++) {
          formData.append('productImageArray', productImageArray[i]);
        }
        axios.post(`${getBaseURL()}/api/auth/seller/add/new/product`, formData, {headers: {
            'Content-Type': 'multipart/form-data',
            "Auth-Token": loginState?.authToken
        }}).then((response) => {
            if(response.status >= 200 && response.status <= 210) {
                router.push("/shop");
            }
        });
    }

    const handleRemoveImage = (event, index) => {
        event.stopPropagation();
        const imageArrTemp = [...productImageArray];
        imageArrTemp.splice(index, 1);
        console.log(imageArrTemp);
        setProductImageArray(imageArrTemp);
        setSelectedImage(imageArrTemp.length ? 0 : null)
    }

    const ImageInputField = (<input type="file" accept="Image/*" style={{ display: "none" }} onChange={(event) => handleImageUpload(event)} />)

    const ImageUploader = (
        <Button
            startIcon={<AddIcon />}
            sx={{ height: "100%", width: "100%", backgroundColor: "white", color: "blue", "&:hover": { backgroundColor: "lightblue" } }}
            variant="contained"
            component="label"
        >
            Add Image
            {ImageInputField}
        </Button>
    );

    return (
        <Box sx={{
            padding: "20px"
        }}>
            <Typography sx={{ fontSize: "16px", fontWeight: 700, color: "#AB40FF", pl: "100px", py: "50px"}}>
                Shop / Seller Account / Add New Product
            </Typography>
            <Grid container spacing={5}>
                <Grid item md={6}>
                    {productImageArray.length <= 0 && <Box sx={{ display: "flex", justifyContent: "center", padding: "20px" }}>
                        <Box sx={{
                            height: "500px",
                            width: "500px",
                            border: "2px dashed red"
                        }}>
                            {ImageUploader}
                        </Box>
                    </Box>}
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
                        <SliderComponent imageArr={productImageArray.map((image) => URL.createObjectURL(image))} setSelectedImage={setSelectedImage} setImageArr={setProductImageArray} removeImageActive handleRemoveImage={handleRemoveImage}>
                            <Box
                                sx={{
                                    width: "50%",
                                    height: "100%",
                                }}
                            >
                                <IconButton
                                   variant="contained"
                                   component="label"
                                >
                                    <AddIcon sx={{fontSize: "60px", color: "white"}}/>
                                    {ImageInputField}
                                </IconButton>
                            </Box>
                        </SliderComponent>
                    </Grid>}
                </Grid>
                <Grid item md={6} sx={{ padding: "20px" }}>
                    <Grid container spacing={4}>
                        <Grid item md={6}>
                            <Autocomplete
                                value={productCategory}
                                options={["T-Shirt", "Shirt", "Trousers", "Jackets", "Jeans"]}
                                renderInput={(params) => <TextField {...params} label="Category" />}
                                onChange={(event, newValue) => setProductCategory(newValue)}
                            />
                        </Grid>
                        <Grid item md={10}>
                            <TextField
                                label="Enter product description"
                                multiline
                                minRows={5}
                                sx={{ width: "100%" }}
                                value={productDescription}
                                onChange={(event) => setProductDescription(event.target.value)}
                            />
                        </Grid>
                        <Grid item md={4}>
                            <Typography sx={{ fontWeight: 700 }}>
                                Color
                            </Typography>
                            <Box>
                                {colorsArray.map((color) => (
                                    <IconButton key={color}>
                                        <CircleIcon sx={{ color: color }} />
                                    </IconButton>
                                ))}
                                {colorsArray.length <= 10 && <IconButton onClick={() => setColorSelectorActive(true)}>
                                    <AddCircleIcon />
                                </IconButton>}
                                {colorSelectorActive && <Box sx={{ display: "flex", justifyContent: "center" }}>
                                    <input type="color" onChange={(event) => { setColorsArray((preval) => [...preval, event.target.value]); setColorSelectorActive(false); }} />
                                    <RemoveCircleOutlineIcon onClick={() => setColorSelectorActive(false)} />
                                </Box>}
                            </Box>
                        </Grid>
                        <Grid item md={6}>
                            <TextField
                                label="Total stock present"
                                type="number"
                                size="small"
                                sx={{ width: "100%", height: "100%" }}
                                value={totalStock}
                                onChange={(event) => setTotalStock(event.target.value)}
                            />
                        </Grid>
                        <Grid item md={10}>
                            <Typography sx={{ fontWeight: 700 }}>
                                Size
                            </Typography>
                            <Box>
                                {sizes.map((size) => <Button key={size} sx={{ borderRadius: "50%", backgroundColor: selectSizes.indexOf(size) !== -1 ? "blue" : "white", color: selectSizes.indexOf(size) !== -1 ? "white" : "blue", margin: "2px" }}
                                    onClick={() => handleClick(size)}
                                >
                                    {size}
                                </Button>)}
                            </Box>
                        </Grid>
                        <Grid item md={4}>
                            <Autocomplete
                                options={["Male", "Female", "Other"]}
                                size="small"
                                renderInput={(params) => <TextField {...params} size="small" label="Associated Gender" />}
                                value={targetGender}
                                onChange={(event, newValue) => setTargetGender(newValue)}
                            />
                        </Grid>
                        <Grid item md={6}>
                            <TextField
                                label="Enter Price"
                                size="small"
                                type="number"
                                sx={{ height: "100%", width: "100%", position: "relative" }}
                                value={productPrice}
                                onChange={(event) => setProductPrice(event.target.value)}
                            />
                        </Grid>
                        <Grid item md={12}>
                            <Box sx={{ width: "100%" }}>
                                <Button sx={{ width: "70%", backgroundColor: "#AB40FF", color: "white", "&:hover": { color: "black" } }} onClick={handleProductSave}>
                                    {productIdState ? "Update Product" : "Save Product"}
                                </Button>
                            </Box>
                        </Grid>
                    </Grid>
                </Grid>
            </Grid>
        </Box>
    )
}

export default AddProduct;
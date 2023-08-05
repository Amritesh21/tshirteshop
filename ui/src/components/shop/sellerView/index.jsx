import { Box, Button, Grid, IconButton, Typography } from "@mui/material";
import AddIcon from '@mui/icons-material/Add';
import { useRouter } from "next/router";
import { useContext, useEffect, useState } from "react";
import { LoginContext } from "@/contexts/loginContext";
import axios from "axios";
import Image from "next/image";

const AddNewProductTile = () => {
    const router = useRouter();
    return (
        <Box sx={{
            border: "1px dashed red",
            height: "300px",
            width: "300px"
        }}>
            <Button 
             startIcon={<AddIcon/>}
             sx={{
                height: "100%",
                width: "100%"
             }}
             onClick={() => router.push("/seller/addProduct")}
            >
                Add New Product
            </Button>
        </Box>
    );
}

const ProductViewTile = (props) => {
    const router = useRouter();
    return (
        <Button sx={{
            height: "300px",
            width: "300px"
         }}
         onClick={() => router.push(`/seller/addProduct?productId=${props.productId}`)}
        >
            <Image src={props.children} style={{width: "100%", height: "100%"}} />
        </Button>
    )
}

export const SellerView = () => {
    const [productsArray, setProductsArray] = useState([]);
    const [productImage, setProductImage] = useState([]);
    const {loginState} = useContext(LoginContext);
    const testUser = "sellerUser1";

    useEffect(() => {
        axios.get(`api/public/seller/get/all/products/meta/${loginState.username ?? testUser}`)
        .then((response) => { setProductsArray(response.data) });
    }, []);

    useEffect(() => {
        productsArray.forEach((product) => {
        fetch(`api/public/seller/get/thumbNail/${loginState.username ?? testUser}/${product.productId}`)
        .then(response => response?.blob())
        .then(imageBlob => {
          setProductImage((preval) => [...preval, {image: URL.createObjectURL(imageBlob), productId: product.productId}]);
          }
        )
        });
    }, [productsArray]);

    useEffect(() => {
        console.log(productImage)
    }, [productImage]);

    return (
        <Box sx={{
            display: "flex",
            justifyContent: "center",
            flexDirection: "column",
            p: "20px"
        }}>
            <Typography sx={{
                fontSize: "24px",
                fontWeight: 600,
                alignSelf: "center"
            }}>
                My All Listed Products
            </Typography>
            <Grid container spacing={5}>
                <Grid item md={3}>
                    <AddNewProductTile />
                </Grid>
                {productImage.map((product, index) => (
                    <Grid item md={3} key={product.productId}>
                     <ProductViewTile productId={product.productId}>
                        {product.image}
                     </ProductViewTile>
                    </Grid>
                ))}
            </Grid>
        </Box>
    )
}
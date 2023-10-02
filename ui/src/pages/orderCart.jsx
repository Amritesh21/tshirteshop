import { LoginContext } from "@/contexts/loginContext";
import { Box, Button, IconButton, InputAdornment, TextField, Typography } from "@mui/material";
import axios from "axios";
import Image from "next/image";
import { useRouter } from "next/router";
import { useContext, useEffect, useRef, useState } from "react";
import DeleteOutlineIcon from '@mui/icons-material/DeleteOutline';
import AddIcon from '@mui/icons-material/Add';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import CircleIcon from '@mui/icons-material/Circle';
import RemoveCircleOutlineIcon from '@mui/icons-material/RemoveCircleOutline';
import RemoveIcon from '@mui/icons-material/Remove';
import { Checkout } from "@/components/checkout";
import { authFetcher, getBaseURL, imageFetcher } from "@/utilities/baseFetcher";

const fetchProductMethod = (loginState, setProductListMeta, setCartProductCount) => {
    authFetcher("api/auth/buyer/get/cart/products")
    .then(response => response.json())
    .then((response) => {
            setCartProductCount(response?.length ?? 0);
            if (!response.length) { setProductListMeta([]); }
            const productListMetaArr = [];
            response.forEach((product, index) => {
                    product.thumbNailImage = imageFetcher(`api/public/get/thumbNail/${product.newProductDTO.productId}`);
                    productListMetaArr.push(product);
            });
            setProductListMeta(productListMetaArr);
        });
}

const ProductTile = ({product, setTotalAmount, setProductListMeta}) => {
    const [quantity, setQuantity] = useState(0);
    const {loginState, cartProductCount, setCartProductCount} = useContext(LoginContext);

    const router = useRouter();

    useEffect(() => {
        setQuantity(product.customerOrderCart.quantity);
    }, [product]);

    const handleIncrementQuantity = () => {
        setQuantity((preval) => preval + 1);
        setTotalAmount((preval) => preval + product.newProductDTO.productPrice );
    }

    const handleDecrementQuantity = () => {
        if (quantity > 0) {
           setQuantity((preval) => preval - 1);
           setTotalAmount((preval) => preval - product.newProductDTO.productPrice );
        }
    }

    const deleteProductFromCart = () => {
        authFetcher(`api/auth/buyer/remove/${product.newProductDTO.productId}/from/cart`, {
            method: "PUT"
        })
        .then((response) => {
            if (response.status === 200) {
                return response.text();
            }
        })
        .then((response) => {
            alert(response);
            fetchProductMethod(loginState, setProductListMeta, setCartProductCount);
        })
    }
    
    return(
        <Box
          sx={{height: "245px", backgroundColor: "#F4F4F4", py: "24px"}}
        >
            <Box sx={{padding: "40px", display: "flex", justifyContent: "space-between"}}>
              <Image style={{width: "80px", height: "80px"}} src={product?.thumbNailImage} alt="productImage"/>
              <Box>
                <Typography sx={{fontWeight: 700, fontSize: "24px"}}>{product.newProductDTO.productCategory}</Typography>
                <Typography>Color: </Typography><CircleIcon sx={{color: product.customerOrderCart.color}} />
              </Box>
              <Box>
                <Typography sx={{fontWeight: 700, fontSize: "24px"}}>{product.newProductDTO.productPrice}$</Typography>
                <Typography>Price</Typography>
              </Box>
              <Box>
                <Typography sx={{fontWeight: 700, fontSize: "24px"}}>In Stock</Typography>
                <Typography>{product.newProductDTO.totalStock}</Typography>
              </Box>
              <IconButton sx={{alignSelf: "baseline"}} onClick={() => deleteProductFromCart()}>
                <DeleteOutlineIcon />
              </IconButton>
            </Box>
            <Box sx={{display: "flex", justifyContent: "space-between", padding: "40px"}}>
                <Button onClick={() => { router.push(`${getBaseURL()}/buy/product?productId=${product.newProductDTO.productId}`) }}>View Product Details</Button>
                <TextField
                    size="small"
                    value={quantity}
                    sx={{ width: "150px" }}
                    InputProps={{
                        readOnly: true,
                        startAdornment: <InputAdornment position="start">
                            <IconButton onClick={() => handleIncrementQuantity()}>
                                <AddIcon />
                            </IconButton>
                        </InputAdornment>,
                        endAdornment: <InputAdornment position="end">
                            <IconButton onClick={() => handleDecrementQuantity()}>
                                <RemoveIcon />
                            </IconButton>
                        </InputAdornment>
                    }}
                />
            </Box>
        </Box>
    )
}

const OrderCart = () => {
    const [productListMeta, setProductListMeta] = useState([]);
    const {loginState, cartProductCount, setCartProductCount} = useContext(LoginContext);
    const [totalAmount, setTotalAmount] = useState(0);
    const [proceedToCheckOut, setProceedToCheckOut] = useState(false);
    const imageSet = useRef(false);
    
    useEffect(() => {
        if (!loginState) { return; }
        fetchProductMethod(loginState, setProductListMeta, setCartProductCount);
    }, [loginState]);

    useEffect(() => {
        const totalAmountCopy = productListMeta.reduce((total, current) => total + (current.newProductDTO.productPrice * current.customerOrderCart.quantity), 0);
        setTotalAmount(totalAmountCopy);
        console.log(totalAmountCopy);
    }, [productListMeta])

    
    return (
        <Box>
            <Box sx={{
                height: "428px",
                width: "100%",
                backgroundImage: "linear-gradient(180deg, #7D89FF 0%, #AB40FF 66.67%)"
            }}>
                <Typography sx={{fontSize: "40px", fontWeight: 700, color: "white", pl: "120px", pt: "102px"}}>
                    Cart
                </Typography>
                <Typography sx={{fontSize: "18px", fontWeight: 400, color: "white", pl: "120px", width: "30%"}}>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eget gravida leo, nec iaculis diam. Nam bibendum mi sed sem finibus ullamcorper.
                </Typography>
            </Box>
            <Box>
              <Typography sx={{fontSize: "36px", fontWeight: 700, pl: "120px", pt: "80px"}}>
                    Cart Product
              </Typography>
            </Box>
            <Box sx={{pl: "120px", position: "relative", display: "flex"}}>
                <Box sx={{width: "60%", position: "relative"}}>
                   {productListMeta.map((product) => (
                    <Box sx={{py: "24px"}}  key={product.newProductDTO.productId}>
                        <ProductTile product={product} setTotalAmount={setTotalAmount} setProductListMeta={setProductListMeta} />
                    </Box>
                   ))}
                   {productListMeta.length <= 0 && <Box sx={{width: "60%", display: "flex", justifyContent: "center", top: "50%", left: "10%", position: "relative"}}><Typography sx={{alignSelf: "center"}}>No item present in cart</Typography></Box>}
                </Box>

                <Box sx={{width: "30%", position: "relative", pl: "15px"}}>
                    <Box sx={{ display: "flex", flexDirection: "column", p: "25px", backgroundColor: "#F4F4F4"}}>
                        <TextField
                        size="small"
                        label="Coupon Code"
                        sx={{width: "100%"}}
                        />
                        <Button size="large" variant="contained" sx={{width: "50%", alignSelf: "flex-end", mt: "25px", backgroundColor: "#AB40FF"}} disabled={!productListMeta.length}>
                            Apply Coupon
                        </Button>
                        <Box sx={{display: "flex", flexDirection: "row", justifyContent: "space-between", mt: "25px", borderBottom: "1px dashed gray"}}>
                            <Box sx={{display: "flex", flexDirection: "column", pb: "10px"}}>
                              <Typography sx={{fontWeight: 700}}>Sub Total</Typography>
                              <Typography sx={{color: "#969696"}}>{productListMeta.length} Product</Typography>
                            </Box>
                            <Typography>${totalAmount} USD</Typography>
                        </Box>
                        <Box sx={{display: "flex", flexDirection: "row", justifyContent: "space-between", mt: "25px"}}>
                            <Box sx={{display: "flex", flexDirection: "column", pb: "10px"}}>
                              <Typography sx={{fontWeight: 700}}>Total</Typography>
                            </Box>
                            <Typography>${totalAmount} USD</Typography>
                        </Box>
                        <Button size="large" variant="contained" sx={{width: "50%", alignSelf: "flex-end", mt: "25px", backgroundColor: "#AB40FF"}}
                           disabled={!productListMeta.length}
                          onClick={() => setProceedToCheckOut(true)}
                        >
                            Checkout
                        </Button>
                    </Box>
                </Box>
            </Box>
            <Checkout open={proceedToCheckOut} setOpen={setProceedToCheckOut} productListMeta={productListMeta} />
        </Box>
    );
}

export default OrderCart;
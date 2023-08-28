import { Box, Button, InputAdornment, Typography } from "@mui/material"
import Image from "next/image"
import DeleteOutlineIcon from '@mui/icons-material/DeleteOutline';
import AddIcon from '@mui/icons-material/Add';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import CircleIcon from '@mui/icons-material/Circle';
import RemoveCircleOutlineIcon from '@mui/icons-material/RemoveCircleOutline';
import RemoveIcon from '@mui/icons-material/Remove';
import { useRouter } from "next/router";

export const OrderProductView = ({
    productCategory,
    thumbNailImage,
    color,
    productPrice,
    orderStatus,
    productId,
    totalStock,
    handleIncrementQuantity, handleDecrementQuantity, deleteProductFromCart,
    orderId,
}) => {

    const router = useRouter();

    return (
        <>
            <Box sx={{ padding: "40px", display: "flex", justifyContent: "space-between", backgroundColor: "#F4F4F4"}}>
                <Image style={{ width: "80px", height: "80px" }} src={thumbNailImage} alt="productImage" />
                <Box>
                    <Typography sx={{ fontWeight: 700, fontSize: "24px" }}>{productCategory}</Typography>
                    <Typography>Color: </Typography><CircleIcon sx={{ color: color }} />
                </Box>
                <Box>
                    <Typography sx={{ fontWeight: 700, fontSize: "24px" }}>{productPrice}$</Typography>
                    <Typography>Total Cost</Typography>
                </Box>
                <Box>
                    <Typography sx={{ fontWeight: 700, fontSize: "24px" }}>Order Status</Typography>
                    <Typography>{orderStatus}</Typography>
                </Box>
                <Button sx={{ alignSelf: "baseline" }} onClick={() => deleteProductFromCart(orderId)}>
                    Cancel Order
                </Button>
                <Button sx={{ alignSelf: "baseline" }} onClick={() => {
                    router.push(`/buy/product?productId=${productId}`)
                }}>
                    View Product Details
                </Button>
            </Box>
        </>
    )
}
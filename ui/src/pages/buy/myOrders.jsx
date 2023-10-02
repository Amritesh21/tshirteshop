import { OrderProductView } from "@/components/orderProductView";
import { LoginContext } from "@/contexts/loginContext";
import { authFetcher } from "@/utilities/baseFetcher";
import { Box, Grid, Typography } from "@mui/material";
import axios from "axios";
import { useContext, useEffect, useState } from "react";

const MyOrders = () => {
    const {loginState} = useContext(LoginContext);

    const [ordersMeta, setOrdersMeta] = useState([]);

    useEffect(() => {
        if (!loginState) { return; }
        authFetcher(`api/auth/buyer/get/all/my/orders`)
        .then((response) => response.json())
        .then((response) => setOrdersMeta(response));
    },[loginState]);

    const cancelOrder = (orderId) => {
      authFetcher(`api/auth/buyer/order/cancel?orderId=${orderId}`, {
          method: "DELETE",
          body: JSON.stringify({})
        })
        .then((response) => {
          authFetcher(`api/auth/buyer/get/all/my/orders`)
          .then((response) => response.json())
          .then((response) => setOrdersMeta(response));
        });
    }

    return (
        <Box
      sx={{
        backgroundImage: "linear-gradient(180deg, #7D89FF 0%, #AB40FF 66.67%)",
        pt: "2%",
        px: "5%",
      }}
    >
      <Typography sx={{ color: "white" }}>
        Home / My Orders
      </Typography>
      <Typography
        sx={{
          fontSize: "40px",
          color: "white",
          fontWeight: 700,
          pt: "2%"
        }}
      >
        Contact
      </Typography>
      <Box
          id="order-details-container"
          sx={{height: "100%", padding: "24px", width: "100%", position: "relative"}}
        >
            <Grid container spacing={5}>
                {ordersMeta.map((orderMeta, index) => {
                    return (
                        <Grid item md={10} xs={12} key={index}>
                          <OrderProductView
                            key={index}
                            productCategory={orderMeta.productCategory}
                            thumbNailImage={`data:image/jpeg;base64,${orderMeta.thumbNail}`}
                            color={orderMeta.color}
                            productPrice={orderMeta.totalCost}
                            orderStatus={orderMeta.orderStatus}
                            productId={orderMeta.productId}
                            orderId={orderMeta.orderId}
                            deleteProductFromCart={cancelOrder}
                        />
                        </Grid>
                    )
                })}
            </Grid>
        </Box>
      </Box>
    )

}

export default MyOrders;
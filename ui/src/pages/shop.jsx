import { BuyerView } from "@/components/shop/buyerView";
import { SellerView } from "@/components/shop/sellerView";
import { LoginContext } from "@/contexts/loginContext";
import { Box, Typography } from "@mui/material"
import { useRouter } from "next/router";
import { useContext, useEffect } from "react";

const Shop = () => {
    const {loginState} = useContext(LoginContext);
    const router = useRouter();
    console.log(loginState);

    useEffect(() => {
        if (!loginState?.authToken) {
          // router.push("/login");
        }
    }, []);

    return (
        <Box>
            <Box sx={{
                height: "300px",
                backgroundImage: 'linear-gradient(180deg, #7D89FF 0%, #AB40FF 66.67%)'
            }}>
                <Box sx={{py: 5, px: 10}}>
                <Typography sx={{color: "white"}}>
                    Home / Shop
                </Typography>
                <Box>
                    <Typography sx={{fontSize: "40px", fontWeight: 700, color: "white", pt: 10}}>
                        Shop
                    </Typography>
                </Box>
                </Box>
            </Box>
            {loginState?.userType === "SELLER" && <SellerView />}
            {loginState?.userType !== "SELLER" && <BuyerView />}
        </Box>
    )
}

export default Shop;
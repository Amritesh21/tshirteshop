import { BuyerView } from "@/components/shop/buyerView";
import { SellerView } from "@/components/shop/sellerView";
import { LoginContext } from "@/contexts/loginContext";
import { Avatar, Box, IconButton, Typography } from "@mui/material"
import { useRouter } from "next/router";
import { useContext, useEffect, useState } from "react";
import jacket from '../../public/pictures/shopImages/jacket.svg';
import tshirt from '../../public/pictures/shopImages/tshirt.svg';
import shirts from '../../public/pictures/shopImages/shirts.svg';
import jeans from '../../public/pictures/shopImages/jeans.svg';
import Image from "next/image";

const categoryMetaData = [
    { icon: tshirt, title: "T-Shirt" },
    { icon: jacket, title: "Jacket" },
    { icon: shirts, title: "Shirt" },
    { icon: jeans, title: "Jeans" }
]

const Shop = () => {
    const { loginState } = useContext(LoginContext);
    const router = useRouter();
    console.log(loginState);

    const [selectedCategory, setSelectedCategory] = useState("T-Shirt");

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
                <Box sx={{ py: 5, px: 10, display: "flex", justifyContent: "space-between" }}>
                    <Box>
                        <Typography sx={{ color: "white" }}>
                            Home / Shop
                        </Typography>
                        <Box>
                            <Typography sx={{ fontSize: "40px", fontWeight: 700, color: "white", pt: 10 }}>
                                Shop
                            </Typography>
                        </Box>
                    </Box>
                    <Box sx={{ display: "flex", pt: "70px", flexDirection: "row", alignSelf: "center", '& > *': { padding: "10px", margin: "10px" } }}>
                        {categoryMetaData.map((category) => {
                            return (
                                <Box sx={{
                                    display: "flex",
                                    justifyContent: "center",
                                    flexDirection: "column"
                                 }}
                                 key={category.title}
                                >
                                    <IconButton 
                                      sx={{border: category.title === selectedCategory ? "5px solid red" : "none", padding: category.title === selectedCategory ? "0px" : "auto"}}
                                      onClick={() => setSelectedCategory(category.title)}
                                    >
                                        <Image src={category.icon} alt={category.title} />
                                    </IconButton>
                                    <Typography sx={{ alignSelf: "center", color: "white", fontWeight: 500 }}>{category.title}</Typography>
                                </Box>
                            )
                        })}
                    </Box>
                </Box>
            </Box>
            {loginState?.userType === "SELLER" && <SellerView />}
            {loginState?.userType !== "SELLER" && <BuyerView selectedCategory={selectedCategory} />}
        </Box>
    )
}

export default Shop;
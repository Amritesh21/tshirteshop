import { Box } from "@mui/material"
import Image from "next/image"
import brand1 from '../../../public/brandLogos/brand1.png'
import brand2 from '../../../public/brandLogos/brand2.png'
import brand3 from '../../../public/brandLogos/brand3.png'
import brand4 from '../../../public/brandLogos/brand4.png'
import brand5 from '../../../public/brandLogos/brand5.png'

const brandLogosArr = [
    brand1, brand2, brand3, brand4, brand5
];

export const BrandLogosComponent = () => {
    return (
        <Box sx={{
            display: "flex", flexDirection: "row", justifyContent: "center", backgroundColor: "#F4F4F4", py: "15px",
            }}
        >
            {brandLogosArr.map((image) => (
               <Image key={Math.random()} src={image} style={{padding: "0px 25px 0px 25px"}}/>
            ))}
        </Box>
    )
}
import { Box, Typography } from "@mui/material"
import Image from "next/image"
import menuBarLogo from '../../../public/pictures/menuBarLogo.svg'

export const BrandIcon = () => {
    return (
        <Box
          sx={{
            display: "flex",
            flexDirection: "row",
            alignSelf: 'center',
            alignContent: 'center'
          }}
        >
            <Image src={menuBarLogo} />
            <Typography 
              sx={{
                fontWeight: 600,
                fontFamily: 'Roboto',
                fontSize: '14px',
                alignSelf: 'center',
                pl: "10px"
              }}
            >
                mangcoding Store
            </Typography>
        </Box>
    )
}
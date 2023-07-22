import { Box, IconButton, Typography } from "@mui/material"
import { BrandIcon } from "../navigationBar/brandIcon"
import FacebookIcon from '@mui/icons-material/Facebook';
import TwitterIcon from '@mui/icons-material/Twitter';
import InstagramIcon from '@mui/icons-material/Instagram';
import LinkedInIcon from '@mui/icons-material/LinkedIn';
import Link from "next/link"

const QuickAccessColumns = ({columnItems, columnHeader}) => {
    return (
        <Box sx={{
            display: "flex",
            flexDirection: "column", 
            px: "15%"
          }}
        >
            <Typography
              sx={{
                pb: "15px",
                fontSize: "18px"
              }}
            >
                <Link href={columnHeader.link} style={{textDecoration: "none", color: "#FFFFFF"}}>
                  {columnHeader.label}
                </Link>
            </Typography>
            {columnItems.map((columnItem) => {
                return (
                    <Typography key={columnItem.label} sx={{py: "5px", fontSize: "14px"}}>
                        <Link href={columnItem.link} style={{textDecoration: "none", color: "#F4F4F4"}}>
                          {columnItem.label}
                        </Link>
                    </Typography>
                )
            })}
        </Box>
    )
}

export const FooterComponent = () => {
    return (
        <Box
          sx={{
            padding: "80px",
            backgroundColor: "#393E46",
            color: "#FFFFFF",
            display: "flex"
          }}
        >
            <Box sx={{
                display: "flex",
                flexDirection: "column",
                alignContent: "flex-start"
             }}
            >
              <Box sx={{py: "10px"}}>
                <BrandIcon />
              </Box>
              <Typography
                sx={{
                    width: "424px",
                    height: "72px",
                    fontSize: "14px"
                }}
              >
                 Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam mollis, justo nec porttitor auctor, erat sapien faucibus lectus, vel tempor dolor augue et lectus. 
              </Typography>
            </Box>
            <Box sx={{
                display: "flex",
                flexDirection: "row",
                px: "10%"
              }}
            >
                <QuickAccessColumns
                 columnHeader={{label: "Home", link: "/"}}
                 columnItems={[
                    {label: "Product", link: "/product"},
                    {label: "Categories", link: "/categories"},
                    {label: "Shop", link: "/shop"}
                ]}/>
                <QuickAccessColumns
                 columnHeader={{label: "Shop", link: "/shop"}}
                 columnItems={[
                    {label: "T-Shirt", link: "/tshirt"},
                    {label: "Jacket", link: "/jacket"},
                    {label: "Shirt", link: "/shirt"},
                    {label: "Jeans", link: "/jeans"}
                ]}/>
                <QuickAccessColumns
                 columnHeader={{label: "Categories", link: "/categories"}}
                 columnItems={[
                    {label: "Men", link: "/men"},
                    {label: "Women", link: "/women"},
                    {label: "Children", link: "/children"}
                ]}/>
                <Box sx={{display: "flex", flexDirection: "column"}}>
                    <Typography sx={{fontSize: "18px"}}>
                        Contact
                    </Typography>
                    <Typography sx={{fontSize: "14px", pt: "20px"}}>
                      mangcoding@gmail.com
                    </Typography>
                    <Box sx={{display: "flex"}}>
                        <IconButton>
                            <LinkedInIcon  sx={{color: "white"}} />
                        </IconButton>
                        <IconButton>
                            <InstagramIcon sx={{color: "white"}} />
                        </IconButton>
                        <IconButton>
                            <FacebookIcon sx={{color: "white"}} />
                        </IconButton>
                        <IconButton>
                            <TwitterIcon sx={{color: "white"}} />
                        </IconButton>
                    </Box>
                </Box>
            </Box>
        </Box>
    )
}
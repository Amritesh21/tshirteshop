import { BrandLogosComponent } from "@/components/brandLogos"
import { HomePageSection1 } from "@/components/homePageSection1"
import { HomePageSection2 } from "@/components/homePageSection2"
import { Box } from "@mui/material"

function HomePage() {
  return (
    <Box>
      <HomePageSection1 />
      <BrandLogosComponent />
      <HomePageSection2 />
    </Box>
  )
}

export default HomePage
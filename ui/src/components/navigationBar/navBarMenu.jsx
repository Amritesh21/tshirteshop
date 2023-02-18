import { Box, Button, Link } from "@mui/material"

/** TODO: create an API to show navigation bar links based on user role */

const navBarMenuItems = [
    {
        name: "Home",
        link: "/"
    },
    {
        name: "Shop",
        link: "/shop"
    },
    {
        name: "about Us",
        link: "/aboutus"
    },
    {
        name: "contact",
        link: "/contact"
    }
]

export const NavBarMenu = () => {
    return (
        <Box
          sx={{
            display: "flex",
            flexDirection: "row",
            justifyContent: 'space-evenly',
          }}
        >
            {navBarMenuItems.map((linkItem) => (
                <Button key={linkItem.name}>
                    <Link 
                      sx={{color: 'black'}}
                      href={linkItem.link}
                      underline="none"
                    >
                        {linkItem.name}
                    </Link>
                </Button>
            ))}
        </Box>
    )
}
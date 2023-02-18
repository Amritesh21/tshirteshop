import { InputAdornment, TextField } from "@mui/material"
import SearchOutlinedIcon from '@mui/icons-material/SearchOutlined';

export const SearchBar = () => {
    return (
        <TextField 
          label="Search"
          variant="outlined"
          sx={{
            // height: "48px",
            alignSelf: "center",
            '.MuiOutlinedInput-root' :{
                height: "40px",
            },
            '.MuiInputLabel-root': {
                position: "absolute",
                top: "-6px",
            }
        }}
        InputProps={{
            endAdornment: <InputAdornment position="start"> <SearchOutlinedIcon/> </InputAdornment>,
          }}
        />
    )
}
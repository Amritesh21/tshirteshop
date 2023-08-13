import { LoginContext } from "@/contexts/loginContext";
import { Box, Button, FormControlLabel, Modal, Popover, Radio, RadioGroup, Step, StepContent, StepLabel, Stepper, TextField } from "@mui/material"
import axios from "axios";
import { useRouter } from "next/router";
import { useContext, useState } from "react"

export const Checkout = ({open, setOpen, productListMeta}) => {
    const [deliveryDetails, setDeliveryDetails] = useState({address: "", phno: ""});
    const [activeStep, setActiveStep] = useState(0);
    const [paymentMethod, setPaymentMethod] = useState("COD");
    const {loginState} = useContext(LoginContext);
    const router = useRouter();
    
    const setNextActiveStep = () => {
        if (activeStep < 3) {
            setActiveStep(activeStep + 1);
        }
    }

    const setBackActiveStep = () => {
        if (activeStep > 0) {
            setActiveStep(activeStep - 1);
        }
    } 

    const handlePlaceOrder = () => {
        axios.post("http://localhost/api/auth/buyer/place/order",
            productListMeta.map((product) => {
                return {
                    size: product.customerOrderCart.size,
                    color: product.customerOrderCart.color,
                    quantity: product.customerOrderCart.quantity,
                    productId: product.newProductDTO.productId,
                    paymentMethod: paymentMethod,
                    address: deliveryDetails.address,
                    phno: Number(deliveryDetails.phno),
                }
            }),
            {
                headers: {
                    "Auth-Token": loginState?.authToken
                }
            }
        ).then((response) => alert(response.data));
        setNextActiveStep();
    }

    const handleClose = () => {
        setOpen(false);
        setPaymentMethod("COD");
        setActiveStep(0);   
    }

    const NextButton = (<Button variant="contained" sx={{mt: "5px"}} onClick={setNextActiveStep}>Next</Button>);
    const BackButton = (<Button variant="contained" sx={{mt: "5px", ml: "10px"}} onClick={setBackActiveStep}>Back</Button>);

    return (
        <Modal open={open}
        >
            <Box sx={{
                 position: 'absolute',
                 top: '50%',
                 left: '50%',
                 transform: 'translate(-50%, -50%)',
                 width: 400,
                 bgcolor: 'background.paper',
                 boxShadow: 24,
                 p: 4,
            }}>
                <Stepper activeStep={activeStep} orientation="vertical">
                    <Step>
                        <StepLabel>Set delivery address</StepLabel>
                        <StepContent>
                            <TextField label="Delivery Address"
                                minRows={5}
                                multiline
                                sx={{ width: "100%" }}
                                value={deliveryDetails.address}
                                onChange={(event) => setDeliveryDetails((preval) => {return {...preval, address: event.target.value}})}
                            />
                            <TextField label="Contact Number"
                              sx={{width: "100%", mt: "10px"}}
                              value={deliveryDetails.phno}
                              onChange={(event) => setDeliveryDetails((preval) => {return {...preval, phno: event.target.value}})}
                            />
                            {NextButton}
                        </StepContent>
                    </Step>
                    <Step>
                        <StepLabel>Set Payment Method</StepLabel>
                        <StepContent>
                            <Box sx={{width: "100%"}}>
                            <RadioGroup value={paymentMethod} onChange={(event) => setPaymentMethod(event.target.value)}>
                                <FormControlLabel value="UPI" control={<Radio />} label="UPI"/>
                                <FormControlLabel value="COD" control={<Radio/>} label="Cash On Delivery" />
                            </RadioGroup>
                            </Box>
                            {NextButton}
                            {BackButton}
                        </StepContent>
                    </Step>
                    <Step>
                        <StepLabel>Confirm Order</StepLabel>
                        <StepContent>
                           <Button variant="contained" sx={{mt: "5px"}} onClick={handlePlaceOrder}>Place Order</Button>
                            {BackButton}
                        </StepContent>
                    </Step>
                </Stepper>
                {activeStep != 3 && <Button variant="outlined" sx={{mt: "10px"}} onClick={() => handleClose()}>Cancel</Button>}
                {activeStep === 3 && <Button variant="outlined" sx={{mt: "10px"}} onClick={() => {router.push("/shop")}} >Continue Shopping</Button>}
            </Box>
        </Modal>
    )
}
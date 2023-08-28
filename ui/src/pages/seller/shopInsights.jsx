import { BarChart } from "@/components/graphs/barChart";
import LineChart from "@/components/graphs/lineChart";
import { Box } from "@mui/material";
import axios from "axios";
import { useEffect, useState } from "react";

const ShopInsights = () => {
    
    const [dataSet, setDataSet] = useState(null);

    useEffect(() => {
        axios.get(`http://localhost/api/public/order/insights`)
        .then((response) => {
            setDataSet(response.data);
        })
    }, []);

    return (
        <Box sx={{padding: "24px"}}>
            <LineChart dataSet={dataSet} setDataSet={setDataSet} />
            <BarChart dataSet={dataSet} setDataSet={setDataSet} />
        </Box>
    )

}

export default ShopInsights;
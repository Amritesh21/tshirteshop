import { Box, Card, FormControl, FormControlLabel, FormLabel, Grid, IconButton, Paper, Radio, RadioGroup, Table, TableBody, TableCell, TableContainer, TableHead, TablePagination, TableRow, Typography } from "@mui/material"
import SvgGraph from "../svgGraph";
import { LineChart } from "@/components/svgGraphs/lineChart";
import { BarChart } from "@/components/svgGraphs/barChart";
import React, { useContext, useMemo, useState } from "react";
import { useRef } from "react";
import { useEffect } from "react";
import { LoginContext } from "@/contexts/loginContext";
import GridOnIcon from '@mui/icons-material/GridOn';
import GridOffIcon from '@mui/icons-material/GridOff';
import axios from "axios";
import { authFetcher } from "@/utilities/baseFetcher";

// const data = [
//     { x: 0, y: 5000, xLabel: "sunday" },
//     { x: 1, y: 8, xLabel: "moday" },
//     { x: 2, y: 3000, xLabel: "tudesday" },
//     { x: 3, y: 10, xLabel: "wednesday" },
//     { x: 4, y: 600, xLabel: "thursday" },
//     { x: 5, y: 5000, xLabel: "friday" },
//     { x: 6, y: 8, xLabel: "saturday" },
//     // { x: 7, y: 3000, xLabel: "sunday"},
//     // { x: 8, y: 10, xLabel: "monday"},
//     // { x: 9, y: 600, xLabel: "tuesday"},
// ];

function createData(name, calories, fat, carbs, protein) {
    return { name, calories, fat, carbs, protein };
}

const rows = [
    createData('Frozen yoghurt', 159, 6.0, 24, 4.0),
    createData('Ice cream sandwich', 237, 9.0, 37, 4.3),
    createData('Eclair', 262, 16.0, 24, 6.0),
    createData('Cupcake', 305, 3.7, 67, 4.3),
    createData('Gingerbread', 356, 16.0, 49, 3.9),
];

const SellerDashboard = () => {
    const [showGridLines, setShowGridLines] = useState(false);
    const firstChartRef = useRef(null);
    const firstChartPadding = 200;
    const secondChartRef = useRef(null);
    const thirdChartRef = useRef(null);
    const secondBlockPadding = 200;
    const [secondChartWidth, setSecondChartWidth] = useState(0);
    const [firstChartWidth, setFirstChartWidth] = useState(0);
    const [timeWiseInsightData, setTimeWiseInsightData] = useState([]);
    const [categoryWiseInsightData, setCategoryWiseInsightData] = useState([]);
    const [allOrdersDetails, setAllOrderDetails] = useState([]);
    const [rowPerPage, setRowPerPage] = useState(5);
    const [page, setPage] = useState(0);
    const [timeFrame, setTimeFrame] = useState("month");
    const [showGridForFirstGraph, setShowGridForFirstGraph] = useState(false);
    const [showGridForSecondGraph, setShowGridForSecondGraph] = useState(false);
    const { loginState } = useContext(LoginContext);

    const handleChangeRowsPerPage = (event) => {
        setRowPerPage(parseInt(event.target.value, 10));
        setPage(0);
    };

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const visibleRows = useMemo(() => {
        const startIndex = page * rowPerPage;
        const lastIndex = startIndex + rowPerPage;
        return allOrdersDetails.filter((row, index) => {
            if (index >= startIndex && index < lastIndex) {
                return true;
            } else {
                return false;
            }
        })
    }, [page, rowPerPage, allOrdersDetails]);

    useEffect(() => {
        console.log("client width : ", secondChartRef?.current?.clientWidth);
        setSecondChartWidth((secondChartRef?.current?.clientWidth ?? 1000) - 50)
    }, [secondChartRef?.current?.clientWidth])

    useEffect(() => {
        setFirstChartWidth((firstChartRef?.current?.clientWidth ?? 1000) - 50);
    }, [firstChartRef?.current?.clientWidth]);

    useEffect(() => {
        if (!loginState) { return; }
        authFetcher(`api/auth/seller/getSales/${timeFrame}`)
        .then((response) => response.json())
        .then((response) => {
            setTimeWiseInsightData(response.data);
        })
    }, [loginState, timeFrame])

    useEffect(() => {
        if (!loginState) { return; }
        authFetcher(`api/auth/seller/category/wise/sales/insight`)
        .then((response) => response.json())
        .then((response) => {
            setCategoryWiseInsightData(response)
        })
        authFetcher(`api/auth/seller/getAllOrders`)
        .then((response) => response.json())
        .then((response) => {
            setAllOrderDetails(response)
        })
    }, [loginState]);

    return (
        <Box>
            <Box
                sx={{
                    backgroundImage: "linear-gradient(175deg, #7D89FF 0%, #AB40FF 66.67%, transparent 20%)",
                    pt: "2%",
                    px: "5%",
                    height: "300px"
                }}
            >
                <Typography sx={{ color: "white" }}>
                    Home / Seller / Dashboard
                </Typography>
                <Typography
                    sx={{
                        fontSize: "40px",
                        color: "white",
                        fontWeight: 700,
                        pt: "2%"
                    }}
                >
                    Shop Insights
                </Typography>
            </Box>
            <Box sx={{ backgroundColor: "#cfc9c9de", pb: "20px" }}>
                <Grid container spacing={1}>
                    <Grid item md={11.6} xs={11} ref={firstChartRef} sx={{ alignSelf: "center", width: "100%", ml: "25px" }}>
                        <Box sx={{ px: "20px", py: "20px", backgroundColor: "white", display: "flex", flexDirection: "row", justifyContent: "space-between"}}>
                           <Typography sx={{alignSelf: "center"}}>Total Earning Time Wise</Typography>
                            <FormControl sx={{flexDirection: "row"}}>
                                <FormLabel id="demo-radio-buttons-group-label" sx={{alignSelf: "center", pr: "20px"}}>Select Aggregation Time Frame : </FormLabel>
                                <RadioGroup
                                    aria-labelledby="demo-radio-buttons-group-label"
                                    defaultValue="month"
                                    name="radio-buttons-group"
                                    sx={{flexDirection: "row"}}
                                    value={timeFrame}
                                    onChange={(event) => {
                                        setTimeFrame(event.target.value);
                                    }}
                                >
                                    <FormControlLabel value="month" control={<Radio />} label="Month" />
                                    <FormControlLabel value="week" control={<Radio />} label="Week" />
                                    <FormControlLabel value="year" control={<Radio />} label="Year" />
                                </RadioGroup>
                                <FormLabel sx={{alignSelf: "center", px: "10px"}}>Show/Hide Grid : </FormLabel>
                                <IconButton onClick={() => setShowGridForFirstGraph(!showGridForFirstGraph)}>
                                    {showGridForFirstGraph && <GridOnIcon />}
                                    {!showGridForFirstGraph && <GridOffIcon />}
                                </IconButton>
                            </FormControl>
                        </Box>
                        <Card sx={{ pt: "20px" }}>
                            <LineChart data={timeWiseInsightData ?? []} showGridLines={showGridForFirstGraph} firstChartWidth={firstChartWidth} firstChartPadding={firstChartPadding} />
                        </Card>
                    </Grid>
                    {/* <Box sx={{alignSelf: "center", width: "100%", display: "flex"}}> */}
                    <Grid md={11.6} xs={11} ref={secondChartRef} sx={{ margin: "20px", ml: "35px" }}>
                        {/* <Typography sx={{ backgroundColor: "white", px: "20px", py: "20px" }}>Total Earning Product Category Wise</Typography> */}
                        <Box sx={{ px: "20px", py: "20px", backgroundColor: "white", display: "flex", flexDirection: "row", justifyContent: "space-between"}}>
                           <Typography sx={{alignSelf: "center"}}>Total Earning Product Category Wise</Typography>
                           <FormControl sx={{flexDirection: "row"}}>
                                <FormLabel sx={{alignSelf: "center", px: "10px"}}>Show/Hide Grid : </FormLabel>
                                <IconButton onClick={() => setShowGridForSecondGraph(!showGridForSecondGraph)}>
                                    {showGridForSecondGraph && <GridOnIcon />}
                                    {!showGridForSecondGraph && <GridOffIcon />}
                                </IconButton>
                            </FormControl>
                        </Box>
                        <Card sx={{ pt: "20px" }}>
                            <BarChart data={categoryWiseInsightData} showGridLines={showGridForSecondGraph} secondChartWidth={secondChartWidth} />
                        </Card>
                    </Grid>
                    {/* <Grid md={5.6} xs={11} ref={thirdChartRef} sx={{ margin: "20px" }}>
                        <Typography sx={{ backgroundColor: "white", px: "20px", py: "20px" }}>Total Earning Time Wise</Typography>
                        <Card sx={{ pt: "20px" }}>
                            <BarChart data={[]} showGridLines={showGridLines} secondChartWidth={secondChartWidth} />
                        </Card>
                    </Grid> */}
                    {/* </Box> */}
                </Grid>
                <Box sx={{ m: "25px" }}>
                    <TableContainer component={Paper}>
                        <Table sx={{ minWidth: 650 }} aria-label="simple table">
                            <TableHead>
                                <TableRow>
                                    <TableCell>Order Id</TableCell>
                                    <TableCell align="right">Amount Received</TableCell>
                                    <TableCell align="right">Order Status</TableCell>
                                    <TableCell align="right">Order Date</TableCell>
                                    <TableCell align="right">Product Category</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {visibleRows.map((row, index) => (
                                    <TableRow
                                        key={index}
                                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                    >
                                        <TableCell component="th" scope="row">
                                            {row.orderId}
                                        </TableCell>
                                        <TableCell align="right">{row.totalCost}</TableCell>
                                        <TableCell align="right">{row.orderStatus}</TableCell>
                                        <TableCell align="right">{row.orderDate}</TableCell>
                                        <TableCell align="right">{row.productCategory}</TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>
                    </TableContainer>
                    <TablePagination
                        sx={{ backgroundColor: "white" }}
                        rowsPerPageOptions={[5, 10, 25]}
                        component="div"
                        count={allOrdersDetails.length}
                        rowsPerPage={rowPerPage}
                        page={page}
                        onPageChange={handleChangePage}
                        onRowsPerPageChange={handleChangeRowsPerPage}
                    />
                </Box>
            </Box>
        </ Box>
    )
}

export default SellerDashboard;
import { InsightConstants } from "@/constants/InsightConstants";
import axios from "axios";

const { Box, Button, IconButton, Tooltip } = require("@mui/material")
const { useRef, useEffect, useState } = require("react")
import GridOnIcon from '@mui/icons-material/GridOn';
import GridOffIcon from '@mui/icons-material/GridOff';

const LineChart = ({dataSet, setDataSet}) => {
    const lineChartRef = useRef(null);
    const containerRef = useRef(null);
    const xGap = 50;
    const yGap = 50;

    const [xStep, setXStep] = useState(0);
    const [yStep, setYStep] = useState(0);
    const [isShowGridLineActive, setIsShowGridLineActive] = useState(false);
    const [activateLiveinsights, setActivateLiveInsights] = useState(false);
    const intervalIds = useRef([]);

    // const dataSet = {
    //     dataType : {
    //         xAxis: InsightConstants.string,
    //         yAxis: InsightConstants.number, 
    //     },
    //     data: [
    //         { x: "January", y: 3000 },
    //         { x: "February", y: 250 },
    //         { x: "March", y: 8000 },
    //         { x: "April", y: 800 },
    //         { x: "May", y: 400 }
    //     ],
    //     legend: {
    //         x: "Month",
    //         y: "Sales"
    //     },
    //     gridLinesFrequency : {
    //         x: 10,
    //         y: 10
    //     }
    // }



    const dragXandYAxis = (canvasContext, canvas) => {
        canvasContext.beginPath();
        canvasContext.moveTo(xGap, 0);
        canvasContext.lineTo(xGap, canvas.height - 2 * yGap);
        canvasContext.lineTo(canvas.width - xGap, canvas.height - 2 * yGap);
        canvasContext.strokeStyle ="black"
        canvasContext.stroke();

        canvasContext.font = "12px Arial";
        canvasContext.fillStyle = "red";
        // canvasContext.fillText(InsightConstants.xAxis, canvas.width - xGap, canvas.height - 2*yGap + 5);
        // canvasContext.fillText(InsightConstants.yAxis, xGap - 10, yGap);
    }

    const renderChart = () => {
        if (!dataSet) { return; }
        const canvas = lineChartRef.current;
        const canvasContext = canvas.getContext("2d");
        canvasContext.clearRect(0, 0 , canvas.width, canvas.height,);
        dragXandYAxis(canvasContext, canvas);

        // calculate scale
        let xStep = 0;
        let yStep = 0;
        const width = canvas.width;
        const height = canvas.height;
        /*
          1 unit = screenWidth/MaxValue px (divided 400 px screen into 3000 parts/units)
          Logic is similar to dividing a line of 10cm in 10 equal parts then each part will be 1 cm long
          ex :- 
          1 unit = 400/3000 = 0.13px
          250 unit = 250 * 0.13px
        */
        if (dataSet.dataType.xAxis === InsightConstants.string && dataSet.dataType.yAxis === InsightConstants.number) {
            xStep = (width - 2*xGap) / (dataSet.data.length + 1);
            yStep = (height - 2*yGap) / (Math.max(...dataSet.data.map((d) => d.y)) + 500);
        }

        setXStep(xStep);
        setYStep(yStep);

        canvasContext.beginPath();
        canvasContext.font = "12px Arial";
        canvasContext.fillStyle = "red";
        dataSet.data.forEach((dataPoint, index) => {
            if (index === 0) {
                canvasContext.moveTo(xGap, height - 2*yGap - dataPoint.y * yStep);
                // canvasContext.fillText(dataPoint.x, xGap, height - yGap);
                // canvasContext.fillText(dataPoint.y, 0, height - yGap - (dataPoint.y * yStep) + 3);
            } else {
                canvasContext.lineTo(xStep * (index) + xGap, height - 2*yGap - dataPoint.y * yStep);
                // canvasContext.fillText(dataPoint.x, xStep * (index) - 10 + xGap, height - yGap);
                // canvasContext.fillText(dataPoint.y, 0, height - 2*yGap - (dataPoint.y * yStep) + 3);
            }
            canvasContext.save();  // Save the current context state
            const labelX = xStep * index + xGap;
            const labelY = height - yGap;
            canvasContext.translate(labelX, labelY);  // Translate to the label position
            canvasContext.rotate(-Math.PI / 4);  // Rotate by 45 degrees
            canvasContext.fillText(dataPoint.x, 0, 0);  // Draw the rotated label
            canvasContext.restore();  
        });
        canvasContext.strokeStyle = "gray";
        canvasContext.stroke();

        canvasContext.fillStyle = "red";
        dataSet.data.forEach((dataPoint, index) => {
            setTimeout(() => {
                    canvasContext.beginPath();
                if (index === 0) {
                    canvasContext.arc(xGap, height - 2*yGap - dataPoint.y * yStep, 5, 0, Math.PI*2);
                    canvasContext.fill();
                } else {
                    canvasContext.arc(xStep * (index) + xGap, height - 2*yGap - dataPoint.y * yStep, 5, 0, Math.PI*2);
                    canvasContext.fill();
                }
              }, (index +1) * 300);
        });
        // canvasContext.fillStyle = "red";
        // requestAnimationFrame(renderChart);

        // showGridLines(canvas, canvasContext)

        //  canvas.addEventListener('mousemove', function(e) {
        //      var x = e.pageX - canvas.offsetLeft;
        //      var y = e.pageY - canvas.offsetTop;
        //      var str = 'X : ' + x + ', ' + 'Y :' + y;
        //      canvasContext.font = 'bold 20px verdana';
        //      canvasContext.fillText(str, x + 20, y + 30, 60);
        //    }, 0);
        
        console.log(xStep, yStep);
    }

    const showGridLines = (canvas, canvasContext) => {
        // canvasContext.clearRect(0, 0, canvas.width, canvas.height);
        renderChart();
        const xStepN = (canvas.width - xGap)  / dataSet.gridLineFrequency.x;
        const yStepN = (canvas.height - yGap) / dataSet.gridLineFrequency.y;

        for(let i = 0; i<dataSet.gridLineFrequency.x - 1; i++) {
            canvasContext.beginPath();
            if (i === 0) {
                continue;
            } else {
                canvasContext.moveTo(xStepN * i + xGap, 0);
                canvasContext.lineTo(xStepN * i + xGap, canvas.height - 2*yGap);
                canvasContext.stroke();
                canvasContext.strokeStyle = "#ddd";
            }
        }

        for(let i = 0; i<dataSet.gridLineFrequency.y  - 1; i++) {
            canvasContext.beginPath();
            if (i === 0) {
                continue;
            } else {
                canvasContext.moveTo(xGap, canvas.height - 2*yGap - yStepN * i);
                canvasContext.lineTo(canvas.width - xGap, canvas.height - 2*yGap - yStepN * i);
                canvasContext.fillText(parseInt((i*yStepN)/yStep) ,0, canvas.height - 2*yGap - yStepN * i);
                // canvasContext.fillText(xStep * xStepN * i ,0, canvas.height - 2*yGap - yStepN * i);
                canvasContext.strokeStyle = "#ddd";
                canvasContext.stroke();
                canvasContext.beginPath();
                canvasContext.moveTo(40, canvas.height - 2*yGap - yStepN * i);
                canvasContext.lineTo(xGap, canvas.height - 2*yGap - yStepN * i);
                canvasContext.strokeStyle = "red";
                canvasContext.stroke();
            }
        }
    } 

    if (activateLiveinsights) {
        const intervalId =  setInterval(() => {
            axios.get(`http://localhost/api/public/order/insights`)
            .then((response) => {
                setDataSet(response.data);
            })
        }, [10000])
        intervalIds.current.push(intervalId);
    }

    useEffect(() => {
        if (isShowGridLineActive) {
            showGridLines(lineChartRef.current, lineChartRef.current.getContext('2d'));
        } else {
            renderChart();
        }
    }, [dataSet]);

    return (
        <>
            {!isShowGridLineActive && 
            <Tooltip title = "Show Grid Lines">
                <IconButton onClick={() => {
                showGridLines(lineChartRef.current, lineChartRef.current.getContext('2d'));
                setIsShowGridLineActive(true);
                }}>
                  <GridOnIcon />
                </IconButton>
            </Tooltip>}
            {isShowGridLineActive &&
            <Tooltip title = "Hide Grid Lines">
                <IconButton onClick={() => {
                renderChart();
                setIsShowGridLineActive(false);
                }}>
                  <GridOffIcon />
                </IconButton>
            </Tooltip>}
          <Button onClick={() => {
            setActivateLiveInsights(!activateLiveinsights);
            intervalIds.current.forEach((intervalId) => {
                clearInterval(intervalId);
            })
          }}>
            {activateLiveinsights ? "Turn off live insights" : "Turn on live insights"}
          </Button>
          <Box ref={containerRef} sx={{width: "60%", height: "100%", position: "relative"}}>
            <canvas ref={lineChartRef} width={containerRef?.current?.clientWidth} height={500}></canvas>
          </Box>
        </>
    )
}

export default LineChart;